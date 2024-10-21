package ar.com.santanderrio.obp.servicios.echeq.common;

import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.echeq.dao.ECheqDAO;
import ar.com.santanderrio.obp.servicios.echeq.dto.ConfirmarOperacionInDTO;
import ar.com.santanderrio.obp.servicios.echeq.entities.AceptarRepudiarAcuerdoDevolucionECheqInEntity;
import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;

public class OperacionRepudiarAcuerdoDevolucionECheq extends OperacionAceptarAcuerdoDevolucionECheq {

    public OperacionRepudiarAcuerdoDevolucionECheq(ECheqDAO eCheqDAO) {
        super(eCheqDAO);
    }

    @Override
    public OperacionEcheqEnum getOperacion() {
        return OperacionEcheqEnum.REPUDIAR_ACUERDO_DEVOLUCION;
    }

    @Override
    public AceptarRepudiarAcuerdoDevolucionECheqInEntity getInEntity() {
        AceptarRepudiarAcuerdoDevolucionECheqInEntity recepcionRepudioEcheqInEntity = super.getInEntity();
        recepcionRepudioEcheqInEntity.setTipoSolicitud("R"); 
        return recepcionRepudioEcheqInEntity;
    }

    @Override
    public String getCodigoEstadistica() {
        return EstadisticasConstants.ECHEQ_REPUDIAR_ACUERDO_DEVOLUCION;
    }

    @Override
    public String getMensajeOk(ConfirmarOperacionInDTO confirmarOperacionInDTO, MensajeBO mensajeBO) {
        return mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ECHEQ_FEEDBACK_OK_REPUDIAR_ACUERDO_DEVOLUCION,
                cheque.getChequeNumero()).getMensaje();
    }

    @Override
    public String getMensajeError(String errorCodeItax) {
        return CodigoMensajeConstantes.ECHEQ_FEEDBACK_ERROR_REPUDIAR_ACUERDO_DEVOLUCION;
    }

    public static String getCodigoMensajePopUp() {
        return CodigoMensajeConstantes.ECHEQ_MENSAJE_POP_UP_REPUDIAR_ACUERDO_DEVOLUCION;
    }
}
