package ar.com.santanderrio.obp.servicios.echeq.common;

import java.math.BigDecimal;
import java.math.MathContext;

import ar.com.santanderrio.obp.servicios.echeq.dao.ECheqDAO;
import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;
import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoDocumentoEnum;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.echeq.dto.ConfirmarOperacionInDTO;
import ar.com.santanderrio.obp.servicios.echeq.entities.AceptarRepudiarPedidoDevolucionECheqInEntity;
import ar.com.santanderrio.obp.servicios.echeq.utils.ECheqUtils;

public class OperacionAceptarPedidoDevolucionECheq extends IatxBaseEcheqOperation {

    public OperacionAceptarPedidoDevolucionECheq(ECheqDAO echeqDao) {
        super(echeqDao);
    }

    @Override
    public OperacionEcheqEnum getOperacion() {
        return OperacionEcheqEnum.ACEPTAR_PEDIDO_DEVOLUCION;
    }

    @Override
    public AceptarRepudiarPedidoDevolucionECheqInEntity getInEntity() {
        AceptarRepudiarPedidoDevolucionECheqInEntity aceptarRepudiarPedidoDevolucionECheqInEntity = new AceptarRepudiarPedidoDevolucionECheqInEntity();
        String tipoDoc = TipoDocumentoEnum.obtenerTipoDocumento(cliente.getTipoDocCnsDocXNup()).getDescripcion();

        aceptarRepudiarPedidoDevolucionECheqInEntity.setIdCheque(ISBANStringUtils.fillStr(getCheque().getIntchequeId(), 15));
        aceptarRepudiarPedidoDevolucionECheqInEntity.setTipoDocumento(ISBANStringUtils.fillStr(tipoDoc, 4));
        aceptarRepudiarPedidoDevolucionECheqInEntity.setNroDocumento(ISBANStringUtils.fillStr(cliente.getNroDocCnsDocXNup(), 11));
        aceptarRepudiarPedidoDevolucionECheqInEntity.setTipoCuenta(StringUtils.leftPad(StringUtils.EMPTY, 2, " ")); 
        aceptarRepudiarPedidoDevolucionECheqInEntity.setSucursalCuenta(StringUtils.leftPad(StringUtils.EMPTY, 3, " "));
        aceptarRepudiarPedidoDevolucionECheqInEntity.setNumeroCuenta(StringUtils.leftPad(StringUtils.EMPTY, 7, " "));
        aceptarRepudiarPedidoDevolucionECheqInEntity.setImporte(ISBANStringUtils.ajustadorBigDecimalIatx(new BigDecimal(getCheque().getMonto(), MathContext.DECIMAL64), 14));
        aceptarRepudiarPedidoDevolucionECheqInEntity.setNroCheque(ISBANStringUtils.fillNum(getCheque().getChequeNumero(), 9));
        aceptarRepudiarPedidoDevolucionECheqInEntity.setFechaEmision(ECheqUtils.parsearFecha(getCheque().getFechaEmision(), ECheqUtils.MASCARA_FECHA_IATX));
        aceptarRepudiarPedidoDevolucionECheqInEntity.setFechaPago(ECheqUtils.parsearFecha(getCheque().getFechaPago(), ECheqUtils.MASCARA_FECHA_IATX));
        aceptarRepudiarPedidoDevolucionECheqInEntity.setAccion("A");
        aceptarRepudiarPedidoDevolucionECheqInEntity.setCuitEmisor(getCheque().getCuentaEmisora().getEmisorCuit());
        aceptarRepudiarPedidoDevolucionECheqInEntity.setRazonSocialEmisor(ISBANStringUtils.fillStr(getCheque().getCuentaEmisora().getEmisorRazonSocial(), 100));
        aceptarRepudiarPedidoDevolucionECheqInEntity.setJSessionId(ISBANStringUtils.fillStr(getOperationDetails().getjSessionId(), 50));
        return aceptarRepudiarPedidoDevolucionECheqInEntity;
    }

    @Override
    public String getCodigoEstadistica() {
        return EstadisticasConstants.ECHEQ_ACEPTAR_PEDIDO_DEVOLUCION;
    }

    @Override
    public String getMensajeOk(ConfirmarOperacionInDTO confirmarOperacionInDTO, MensajeBO mensajeBO) {
        return mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ECHEQ_FEEDBACK_OK_ACEPTAR_PEDIDO_DEVOLUCION, 
        		cheque.getChequeNumero()).getMensaje();
    }

    @Override
    public String getMensajeError(String errorCodeItax) {
        return CodigoMensajeConstantes.ECHEQ_FEEDBACK_ERROR_ACEPTAR_PEDIDO_DEVOLUCION;
    }

    public static String getCodigoMensajePopUp() {
        return CodigoMensajeConstantes.ECHEQ_MENSAJE_POP_UP_ACEPTAR_PEDIDO_DEVOLUCION;
    }
}
