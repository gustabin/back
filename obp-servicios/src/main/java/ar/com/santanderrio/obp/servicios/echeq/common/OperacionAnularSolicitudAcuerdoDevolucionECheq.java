package ar.com.santanderrio.obp.servicios.echeq.common;

import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.echeq.dao.ECheqDAO;
import ar.com.santanderrio.obp.servicios.echeq.dto.ConfirmarOperacionInDTO;
import ar.com.santanderrio.obp.servicios.echeq.entities.AceptarRepudiarAcuerdoDevolucionECheqInEntity;
import ar.com.santanderrio.obp.servicios.echeq.entities.SolicitarAnularSolicitudAcuerdoDevolucionECheqInEntity;
import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;

public class OperacionAnularSolicitudAcuerdoDevolucionECheq extends OperacionSolicitarAcuerdoDevolucionECheq {

    public OperacionAnularSolicitudAcuerdoDevolucionECheq(ECheqDAO eCheqDAO) {
        super(eCheqDAO);
    }

    @Override
    public OperacionEcheqEnum getOperacion() {
        return OperacionEcheqEnum.ANULAR_ACUERDO_DEVOLUCION;
    }

    @Override
    public AceptarRepudiarAcuerdoDevolucionECheqInEntity getInEntity() {
        SolicitarAnularSolicitudAcuerdoDevolucionECheqInEntity solicitarPedidoDevolucionEcheqInEntity =
            (SolicitarAnularSolicitudAcuerdoDevolucionECheqInEntity) super.getInEntity();
        solicitarPedidoDevolucionEcheqInEntity.setTipoSolicitud("A");
        return solicitarPedidoDevolucionEcheqInEntity;
    }

    @Override
    public String getCodigoEstadistica() {
        return EstadisticasConstants.ECHEQ_ANULAR_SOLICITUD_ACUERDO_DEVOLUCION;
    }

    @Override
    public String getMensajeOk(ConfirmarOperacionInDTO confirmarOperacionInDTO, MensajeBO mensajeBO) {
        String codigoMensaje = CodigoMensajeConstantes.ECHEQ_FEEDBACK_OK_ANULAR_SOLICITUD_ACUERDO_DEVOLUCION_ENDOSADOS;
        if (confirmarOperacionInDTO.getIngresoDesdeEmitidos()) {
            codigoMensaje = CodigoMensajeConstantes.ECHEQ_FEEDBACK_OK_ANULAR_SOLICITUD_ACUERDO_DEVOLUCION_EMITIDOS;
        }
        return mensajeBO.obtenerMensajePorCodigo(codigoMensaje, cheque.getChequeNumero()).getMensaje();
    }

    @Override
    public String getMensajeError(String errorCodeItax) {
        return CodigoMensajeConstantes.ECHEQ_FEEDBACK_ERROR_ANULAR_SOLICITUD_ACUERDO_DEVOLUCION;
    }

    public static String getCodigoMensajePopUp() {
        return CodigoMensajeConstantes.ECHEQ_MENSAJE_POP_UP_ANULAR_SOLICITUD_ACUERDO_DEVOLUCION;
    }

}
