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
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.echeq.dto.ConfirmarOperacionInDTO;
import ar.com.santanderrio.obp.servicios.echeq.entities.AcuerdoDevolucionECheqInEntity;
import ar.com.santanderrio.obp.servicios.echeq.utils.ECheqUtils;

public class OperacionSolicitarPedidoDevolucionECheq extends IatxBaseEcheqOperation {

    public OperacionSolicitarPedidoDevolucionECheq(ECheqDAO eCheqDAO) {
        super(eCheqDAO);
    }

    @Override
    public OperacionEcheqEnum getOperacion() {
        return OperacionEcheqEnum.SOLICITAR_PEDIDO_DEVOLUCION;
    }
    
    @Override
    public AcuerdoDevolucionECheqInEntity getInEntity() {
        AcuerdoDevolucionECheqInEntity acuerdoDevolucionECheqInEntity = new AcuerdoDevolucionECheqInEntity();
        if (operationDetails.getIngresoDesdeEmitidos()){
            Cuenta cuentaAsociada = obtenerCuentaPorCbu(cliente, cheque.getCuentaEmisora().getEmisorCbu());
            if (cuentaAsociada != null) {
            	acuerdoDevolucionECheqInEntity.setTipoCuenta(ECheqUtils.obtenerTipoCuentaEcheq(cuentaAsociada));
            	acuerdoDevolucionECheqInEntity.setSucursalCuenta(ISBANStringUtils.formatearSucursal(cuentaAsociada.getNroSucursal()));
            	acuerdoDevolucionECheqInEntity.setNumeroCuenta(StringUtils.right(cuentaAsociada.getNroCuentaProducto(), 7));
            } else {
            	acuerdoDevolucionECheqInEntity.setTipoCuenta(ISBANStringUtils.fillStr(StringUtils.EMPTY, 2)); 
                acuerdoDevolucionECheqInEntity.setSucursalCuenta(ISBANStringUtils.fillStr(StringUtils.EMPTY, 3));
                acuerdoDevolucionECheqInEntity.setNumeroCuenta(ISBANStringUtils.fillStr(StringUtils.EMPTY, 7));
            }
        }
        if (operationDetails.getIngresoDesdeEndosados()){
            acuerdoDevolucionECheqInEntity.setTipoCuenta(ISBANStringUtils.fillStr(StringUtils.EMPTY, 2)); 
            acuerdoDevolucionECheqInEntity.setSucursalCuenta(ISBANStringUtils.fillStr(StringUtils.EMPTY, 3));
            acuerdoDevolucionECheqInEntity.setNumeroCuenta(ISBANStringUtils.fillStr(StringUtils.EMPTY, 7));
        }
        acuerdoDevolucionECheqInEntity.setImporteCuenta(ISBANStringUtils.ajustadorBigDecimalIatx(new BigDecimal(cheque.getMonto(), MathContext.DECIMAL64), 14));
        acuerdoDevolucionECheqInEntity.setIdCheque(ISBANStringUtils.fillStr(cheque.getIntchequeId(), 15));
        acuerdoDevolucionECheqInEntity.setNroCheque(ISBANStringUtils.fillNum(cheque.getChequeNumero(), 9));
        acuerdoDevolucionECheqInEntity.setFechaEmision(ECheqUtils.parsearFecha(cheque.getFechaEmision(), ECheqUtils.MASCARA_FECHA_IATX));
        acuerdoDevolucionECheqInEntity.setFechaPago(ECheqUtils.parsearFecha(cheque.getFechaPago(), ECheqUtils.MASCARA_FECHA_IATX));
        acuerdoDevolucionECheqInEntity.setAccion("P");
        String tipoDoc = TipoDocumentoEnum.obtenerTipoDocumento(cliente.getTipoDocCnsDocXNup()).getDescripcion();
        acuerdoDevolucionECheqInEntity.setTipoDocumento(ISBANStringUtils.fillStr(tipoDoc, 4));
        acuerdoDevolucionECheqInEntity.setNroDocumento(ISBANStringUtils.fillStr(cliente.getNroDocCnsDocXNup(), 11));
        acuerdoDevolucionECheqInEntity.setCuitEmisor(cheque.getCuentaEmisora().getEmisorCuit());
        acuerdoDevolucionECheqInEntity.setRazonSocialEmisor(cheque.getCuentaEmisora().getEmisorRazonSocial());
        acuerdoDevolucionECheqInEntity.setTipoDocumentoBeneficiario(ISBANStringUtils.fillStr(cheque.getTenencia().getBeneficiarioDocumentoTipo().toUpperCase(), 4));
        acuerdoDevolucionECheqInEntity.setNroDocumentoBeneficiario(ISBANStringUtils.fillStr(cheque.getTenencia().getBeneficiarioDocumento(), 11));
        acuerdoDevolucionECheqInEntity.setRazonSocialBeneficiario(ISBANStringUtils.fillStr(cheque.getTenencia().getBeneficiarioNombre(), 100));
        acuerdoDevolucionECheqInEntity.setJSessionId(ISBANStringUtils.fillStr(operationDetails.getjSessionId(),50));
        return  acuerdoDevolucionECheqInEntity;
    }

    @Override
    public String getMensajeOk(ConfirmarOperacionInDTO confirmarOperacionInDTO, MensajeBO mensajeBO) {
        String codigoMensaje = CodigoMensajeConstantes.ECHEQ_FEEDBACK_OK_SOLICITAR_PEDIDO_DEVOLUCION_ENDOSADOS;
        if (confirmarOperacionInDTO.getIngresoDesdeEmitidos()) {
            codigoMensaje = CodigoMensajeConstantes.ECHEQ_FEEDBACK_OK_SOLICITAR_PEDIDO_DEVOLUCION_EMITIDOS;
        }
        return mensajeBO.obtenerMensajePorCodigo(codigoMensaje, cheque.getChequeNumero()).getMensaje();
    }

    @Override
    public String getMensajeError(String errorCodeItax) {
        return CodigoMensajeConstantes.ECHEQ_FEEDBACK_ERROR_REPUDIAR_PEDIDO_DEVOLUCION;
    }

    @Override
    public String getCodigoEstadistica() {
        return EstadisticasConstants.ECHEQ_SOLICITAR_PEDIDO_DEVOLUCION;
    }

    public static String getCodigoMensajePopUp() {
        return CodigoMensajeConstantes.ECHEQ_MENSAJE_POP_UP_SOLICITAR_PEDIDO_DEVOLUCION;
    }
}
