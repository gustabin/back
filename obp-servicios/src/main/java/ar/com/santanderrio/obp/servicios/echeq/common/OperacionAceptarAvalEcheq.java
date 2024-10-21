package ar.com.santanderrio.obp.servicios.echeq.common;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.generated.webservices.echeq.Aval;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.model.TipoDocumentoEnum;
import ar.com.santanderrio.obp.servicios.echeq.dto.ConfirmarOperacionInDTO;
import ar.com.santanderrio.obp.servicios.echeq.entities.OperacionAvalEcheqInEntity;
import ar.com.santanderrio.obp.servicios.echeq.entities.OperationEcheqResponse;
import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;
import ar.com.santanderrio.obp.servicios.echeq.utils.ECheqUtils;
import ar.com.santanderrio.obp.servicios.echeqapi.connector.EcheqApiConnector;
import ar.com.santanderrio.obp.servicios.echeqapi.dto.GuarantyOrderCreationRequest;
import ar.com.santanderrio.obp.servicios.echeqapi.dto.GuarantyOrderCreationResponse;
import ar.com.santanderrio.obp.servicios.echeqapi.model.Guarantor;
import ar.com.santanderrio.obp.servicios.echeqapi.model.Guaranty;
import ar.com.santanderrio.obp.servicios.echeqapi.model.GuarantyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Collections;

public class OperacionAceptarAvalEcheq extends OperacionAval {
	private static final Logger LOGGER = LoggerFactory.getLogger(OperacionAceptarAvalEcheq.class);

    public OperacionAceptarAvalEcheq(EcheqApiConnector echeqEndorsementApi) {
        super(echeqEndorsementApi);
    }
 
    @Override
	public OperacionEcheqEnum getOperacion() {
		return OperacionEcheqEnum.ACEPTAR_AVAL;
	}

	@Override
    public OperacionAvalEcheqInEntity getInEntity() throws ParseException {
    	Aval aval = ECheqUtils.getAvalPendiente(cheque);

		if (aval == null) {
			throw new ParseException("No se encontro Aval en estado PENDIENTE", 0);
		}

		final Guarantor guarantor = Guarantor.guarantorBuilder()
				.documentType(TipoDocumentoEnum.getTipoDocumento(aval.getAvalDocumentoTipo()))
				.documentNumber(aval.getAvalDocumento())
				.name(aval.getAvalRazonSocial())
				.address(aval.getAvalDomicilio())
				.build();

		final Guaranty guaranty = Guaranty.builder()
				.type(GuarantyType.ACCEPTANCE)
				.echeq(mapEcheq())
				.guarantor(guarantor)
				.holder(mapHolder())
				.amount(BigDecimal.valueOf(aval.getAvalImporteAvalado()))
				.build();

		final GuarantyOrderCreationRequest request = GuarantyOrderCreationRequest.builder()
				.requester(mapOperationRequester())
				.signers(Collections.singletonList(mapOperationSigner()))
				.guaranties(Collections.singletonList(guaranty))
				.build();
		return new OperacionAvalEcheqInEntity(request);
	}

	@Override
	public void execute() throws DAOException, ParseException {
		//null params because who knows ( Refactor IOperationEntity as Generic )
		GuarantyOrderCreationRequest request = getInEntity().generateRequestData(null, null);
		final GuarantyOrderCreationResponse response = echeqApiConnector.postGuarantyAcceptanceOrder(request);

		if (response.getStatus() != null && !"COMPLETED".equals(response.getStatus())) {
			LOGGER.error("Ocurrió un error en la solicitud al WS. Status: {}, Action {}",
					response.getStatus(), response.getAction());
			this.operationResult = OperationEcheqResponse.builder()
					.status(Boolean.FALSE)
					.build();
		} else {
			LOGGER.info("Finalizo la operación {} con estado {}, orderId: {}",
					getOperacion().getAccion(), response.getStatus(), response.getOrderId());
			this.operationResult = OperationEcheqResponse.builder()
					.status("OK".equals(response.getGuaranties().get(0).getStatus()))
					.build();
		}
	}

	@Override
	public String getMensajeOk(ConfirmarOperacionInDTO confirmarOperacionInDTO, MensajeBO mensajeBO) {
		return mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ECHEQ_FEEDBACK_OK_ACEPTAR_AVAL,
			cheque.getChequeNumero()).getMensaje();
	}

	@Override
	public String getMensajeError(String errorCodeItax) {
		return CodigoMensajeConstantes.ECHEQ_FEEDBACK_ERROR_ACEPTAR_AVAL;
	}

	@Override
	public String getCodigoEstadistica() {
        return EstadisticasConstants.ECHEQ_ACEPTAR_AVAL;
    }

}
