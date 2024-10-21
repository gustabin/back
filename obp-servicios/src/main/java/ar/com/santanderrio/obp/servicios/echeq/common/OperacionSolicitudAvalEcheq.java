package ar.com.santanderrio.obp.servicios.echeq.common;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.model.TipoDocumentoEnum;
import ar.com.santanderrio.obp.servicios.echeq.dto.BeneficiarioDTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.ConfirmarOperacionInDTO;
import ar.com.santanderrio.obp.servicios.echeq.entities.OperacionAvalEcheqInEntity;
import ar.com.santanderrio.obp.servicios.echeq.entities.OperationEcheqResponse;
import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;
import ar.com.santanderrio.obp.servicios.echeqapi.connector.EcheqApiConnector;
import ar.com.santanderrio.obp.servicios.echeqapi.dto.GuarantyOrderCreationRequest;
import ar.com.santanderrio.obp.servicios.echeqapi.dto.GuarantyOrderCreationResponse;
import ar.com.santanderrio.obp.servicios.echeqapi.model.Guarantor;
import ar.com.santanderrio.obp.servicios.echeqapi.model.Guaranty;
import ar.com.santanderrio.obp.servicios.echeqapi.model.GuarantyClient;
import ar.com.santanderrio.obp.servicios.echeqapi.model.GuarantyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Collections;

public class OperacionSolicitudAvalEcheq extends OperacionAval {
    private static final Logger LOGGER = LoggerFactory.getLogger(OperacionSolicitudAvalEcheq.class);

    public OperacionSolicitudAvalEcheq(EcheqApiConnector apiConnector) {
        super(apiConnector);
    }

    @Override
    public OperacionEcheqEnum getOperacion() {
        return OperacionEcheqEnum.SOLICITAR_AVAL;
    }

    @Override
    public OperacionAvalEcheqInEntity getInEntity() {
        final BeneficiarioDTO beneficiario = getOperationDetails().getBeneficiario();

        final Guarantor guarantor = Guarantor.guarantorBuilder()
                .documentType(TipoDocumentoEnum.getTipoDocumento(beneficiario.getBenTipoDocumento()))
                .documentNumber(beneficiario.getBenDocumento())
                .name(beneficiario.getBenRazonSocial())
                .address(getOperationDetails().getDireccion())
                .build();

        final Guaranty guaranty = Guaranty.builder()
                .type(GuarantyType.EMISSION)
                .echeq(mapEcheq())
                .guarantor(guarantor)
                .amount(new BigDecimal(cheque.getMonto()))
                .holder(mapHolder())
                .motive(MOTIVO_NA)
                .build();

        GuarantyOrderCreationRequest request = GuarantyOrderCreationRequest.builder()
                .requester(mapOperationRequester())
                .signers(Collections.singletonList(mapOperationSigner()))
                .guaranties(Collections.singletonList(guaranty))
                .build();
		return new OperacionAvalEcheqInEntity(request);
    }

    @Override
    protected GuarantyClient mapHolder() {
        return GuarantyClient.builder()
                .name(cheque.getTenencia().getBeneficiarioNombre())
                .documentNumber(cheque.getTenencia().getBeneficiarioDocumento())
                .documentType(TipoDocumentoEnum.getTipoDocumento(cheque.getTenencia().getBeneficiarioDocumentoTipo()))
                .build();
    }

    @Override
	public void execute() throws DAOException, ParseException {
        //null params because who knows ( Refactor IOperationEntity as Generic )
        GuarantyOrderCreationRequest request = getInEntity().generateRequestData(null, null);
        validateRequest(request);

        final GuarantyOrderCreationResponse response = echeqApiConnector.postGuarantyEmissionOrder(request);

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

    private void validateRequest(GuarantyOrderCreationRequest request) throws DAOException {
        Guarantor avalista = request.getGuaranties().get(0).getGuarantor();
        if (avalista.getDocumentNumber().equals(cliente.getNumeroCUILCUIT().replace("-",""))) {
            throw new DAOException("Error de validacion. CUIT Avalista es igual al Poseedor del Cheque");
        }
    }

    @Override
    public String getCodigoEstadistica() {
        return EstadisticasConstants.ECHEQ_EMITIR_SOLICITUD_AVAL;
    }

    @Override
    public String getMensajeOk(ConfirmarOperacionInDTO confirmarOperacionInDTO, MensajeBO mensajeBO) {
        return mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ECHEQ_FEEDBACK_OK_SOLICITAR_AVAL,
                cheque.getChequeNumero()).getMensaje();
    }

    @Override
    public String getMensajeError(String errorCodeItax) {
		return CodigoMensajeConstantes.ECHEQ_FEEDBACK_ERROR_SOLICITAR_AVAL;
    }

    public static String getCodigoLegalAyuda() {
        return CodigoMensajeConstantes.ECHEQ_TOOLTIP_LEGAL_AVAL;
    }
}
