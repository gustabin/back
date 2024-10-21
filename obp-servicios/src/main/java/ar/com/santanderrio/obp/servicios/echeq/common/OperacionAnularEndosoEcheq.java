package ar.com.santanderrio.obp.servicios.echeq.common;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.generated.webservices.echeq.Endoso;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.model.TipoDocumentoEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.echeq.dto.ConfirmarOperacionInDTO;
import ar.com.santanderrio.obp.servicios.echeq.entities.AnularEndosoECheqInEntity;
import ar.com.santanderrio.obp.servicios.echeq.entities.OperationEcheqResponse;
import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;
import ar.com.santanderrio.obp.servicios.echeq.utils.ECheqUtils;
import ar.com.santanderrio.obp.servicios.echeqapi.connector.EcheqApi;
import ar.com.santanderrio.obp.servicios.echeqapi.dto.EcheqEndorsementCancellationResponse;
import ar.com.santanderrio.obp.servicios.echeqapi.model.Echeq;
import ar.com.santanderrio.obp.servicios.echeqapi.dto.EcheqEndorsementCancellationRequest;
import ar.com.santanderrio.obp.servicios.echeqapi.model.EndorsementSigner;
import org.apache.commons.collections.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

public class OperacionAnularEndosoEcheq extends AbstractOperacionECheq {

	private static final Logger LOGGER = LoggerFactory.getLogger(OperacionAnularEndosoEcheq.class);
	private static final String ESTADO_PENDIENTE = "pendiente";
	private static final Predicate PREDICATE_ENDOSO_ACTIVO_PENDIENTE = new Predicate() {
		@Override
		public boolean evaluate(Object object) {
			return ESTADO_PENDIENTE.equals(((Endoso) object).getEstadoEndoso());
		}
	};

	private final EcheqApi echeqApi;

	public OperacionAnularEndosoEcheq(EcheqApi echeqApi) {
        super();
        this.echeqApi = echeqApi;
    }

	@Override
	public OperacionEcheqEnum getOperacion() {
		return OperacionEcheqEnum.ANULAR_ENDOSO;
	}

	@Override
	public Boolean getOperacionDisponible() {
    	return ECheqUtils.esHorarioHabilitado();
    }

    @Override
    public AnularEndosoECheqInEntity getInEntity() {
		final Endoso endoso = ECheqUtils.getFirstEndosoByFilter(cheque.getEndosos(), PREDICATE_ENDOSO_ACTIVO_PENDIENTE);

		if(endoso == null) {
			throw new IllegalArgumentException("No se encontró Endoso pendiente");
		}

		final EndorsementSigner endorser = new EndorsementSigner(endoso.getEmisorRazonSocial().trim(), endoso.getEmisorDocumento(),
				TipoDocumentoEnum.getTipoDocumento(endoso.getEmisorDocumentoTipo()));
		final Echeq echeq = Echeq.builder()
				.coelsaId(cheque.getIntchequeId())
				.number(cheque.getChequeNumero())
				.cmc7(cheque.getCmc7())
				.endorseeName(endoso.getBenefRazonSocial().trim())
				.endorseeDocumentNumber(endoso.getBenefDocumento().trim())
				.endorseeDocumentType(endoso.getBenefDocumentoTipo().trim())
				.endorsementType(endoso.getTipoEndoso())
				.build();

		final EcheqEndorsementCancellationRequest wsRequest = EcheqEndorsementCancellationRequest.builder()
				.cancelMotive(operationDetails.getMotivoAnulacion())
				.endorser(endorser)
				.echeqs(Collections.singletonList(echeq))
				.signers(Collections.singletonList(endorser))
				.build();

		return new AnularEndosoECheqInEntity(wsRequest);
    }

	@Override
	public void execute() throws DAOException {
		//null params because who knows
		final EcheqEndorsementCancellationRequest requestObject = getInEntity().generateRequestData(null, null);
		final EcheqEndorsementCancellationResponse response = echeqApi.postEndorsementCancellation(requestObject);
		if (response.getCode() != null && response.getStatus() != null) {
			LOGGER.error("Ocurrió un error en la solicitud al WS. Error code:{}, Status: {}, Message {}",
					response.getCode(), response.getStatus(), response.getMessage());
			this.operationResult = OperationEcheqResponse.builder()
					.status(Boolean.FALSE)
					.build();
		} else {
			LOGGER.info("Finalizo la operación ANULAR_ENDOSO con estado {}, orderId: {}", response.getOrderStatus(),
				response.getOrderId());
			this.operationResult = OperationEcheqResponse.builder()
					.status("OK".equals(response.getEcheqs().get(0).getStatus()))
					.build();
		}
	}

	@Override
	public String getMensajeOk(ConfirmarOperacionInDTO confirmarOperacionInDTO, MensajeBO mensajeBO) {
		return mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ECHEQ_FEEDBACK_OK_ANULAR_ENDOSO,
				cheque.getChequeNumero()).getMensaje();
	}

	@Override
	public String getMensajeError(String errorCodeItax) {
		return CodigoMensajeConstantes.ECHEQ_FEEDBACK_ERROR_ANULAR_ENDOSO;
	}

	@Override
	public String getCodigoEstadistica() {
		return EstadisticasConstants.ECHEQ_ANULAR_ENDOSO;
	}

	@Override
	public Estadistica getEstadisticaOperacion() {
		Estadistica estadistica = super.getEstadisticaOperacion();

		Cuenta cuentaOperacion = obtenerCuentaPorCbu(cliente, cheque.getCuentaEmisora().getEmisorCbu());
		if (cuentaOperacion != null) {
			StringBuilder sb = new StringBuilder(cuentaOperacion.getTipoCuentaEnum().getAbreviatura())
					.append(" ")
					.append(cuentaOperacion.obtenerNroCuentaFormateado());
			estadistica.setNroCtaOrigen(sb.toString());
		}
		return estadistica;
	}
}
