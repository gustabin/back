package ar.com.santanderrio.obp.servicios.echeq.common;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.echeq.dao.ECheqDAO;
import ar.com.santanderrio.obp.servicios.echeq.dto.ConfirmarOperacionInDTO;
import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;

public class OperacionRepudiarCEDEcheq extends OperacionCEDEcheq {
    public OperacionRepudiarCEDEcheq(ECheqDAO eCheqDAO) {
        super(eCheqDAO, "R");
    }

    @Override
    public OperacionEcheqEnum getOperacion() {
        return OperacionEcheqEnum.REPUDIAR_CED;
    }

    @Override
    public String getCodigoEstadistica() {
        return EstadisticasConstants.ECHEQ_REPUDIAR_CED;
    }

    @Override
    public String getMensajeOk(ConfirmarOperacionInDTO confirmarOperacionInDTO, MensajeBO mensajeBO) {
        return mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ECHEQ_FEEDBACK_OK_REPUDIAR_CED,
                cheque.getChequeNumero()).getMensaje();
    }

    @Override
    public String getMensajeError(String errorCodeItax) {
        return CodigoMensajeConstantes.ECHEQ_MENSAJE_ERROR_ACTCLEACED_REPUDIAR_CED;
    }

    @Override
    protected String getMotivo() {
        return ISBANStringUtils.fillStr(operationDetails.getMotivoRepudio(), 100);
    }
}
