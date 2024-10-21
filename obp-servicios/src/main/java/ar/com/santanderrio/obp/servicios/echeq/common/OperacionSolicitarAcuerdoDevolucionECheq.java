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
import ar.com.santanderrio.obp.servicios.echeq.entities.AceptarRepudiarAcuerdoDevolucionECheqInEntity;
import ar.com.santanderrio.obp.servicios.echeq.entities.SolicitarAnularSolicitudAcuerdoDevolucionECheqInEntity;
import ar.com.santanderrio.obp.servicios.echeq.utils.ECheqUtils;

public class OperacionSolicitarAcuerdoDevolucionECheq extends IatxBaseEcheqOperation {

    public OperacionSolicitarAcuerdoDevolucionECheq(ECheqDAO eCheqDAO) {
        super(eCheqDAO);
    }

    @Override
    public OperacionEcheqEnum getOperacion() {
        return OperacionEcheqEnum.SOLICITAR_ACUERDO_DEVOLUCION;
    }

    @Override
    public AceptarRepudiarAcuerdoDevolucionECheqInEntity getInEntity() {
        SolicitarAnularSolicitudAcuerdoDevolucionECheqInEntity solicitarPedidoDevolucionEcheqInEntity =
                new SolicitarAnularSolicitudAcuerdoDevolucionECheqInEntity();

        if (operationDetails.getIngresoDesdeEmitidos()){
            Cuenta cuentaAsociada = obtenerCuentaPorCbu(cliente, cheque.getCuentaEmisora().getEmisorCbu());
            if (cuentaAsociada != null) {
	            solicitarPedidoDevolucionEcheqInEntity.setTipoCuenta(ECheqUtils.obtenerTipoCuentaEcheq(cuentaAsociada));
	            solicitarPedidoDevolucionEcheqInEntity.setSucursalCuenta(ISBANStringUtils.formatearSucursal(cuentaAsociada.getNroSucursal()));
	            solicitarPedidoDevolucionEcheqInEntity.setNumeroCuenta(StringUtils.right(cuentaAsociada.getNroCuentaProducto(), 7));
            } else {
            	solicitarPedidoDevolucionEcheqInEntity.setTipoCuenta(ISBANStringUtils.fillStr(StringUtils.EMPTY, 2)); 
                solicitarPedidoDevolucionEcheqInEntity.setSucursalCuenta(ISBANStringUtils.fillStr(StringUtils.EMPTY, 3));
                solicitarPedidoDevolucionEcheqInEntity.setNumeroCuenta(ISBANStringUtils.fillStr(StringUtils.EMPTY, 7));
            }
        } 
        if (operationDetails.getIngresoDesdeEndosados()){
            solicitarPedidoDevolucionEcheqInEntity.setTipoCuenta(ISBANStringUtils.fillStr(StringUtils.EMPTY, 2)); 
            solicitarPedidoDevolucionEcheqInEntity.setSucursalCuenta(ISBANStringUtils.fillStr(StringUtils.EMPTY, 3));
            solicitarPedidoDevolucionEcheqInEntity.setNumeroCuenta(ISBANStringUtils.fillStr(StringUtils.EMPTY, 7));
        }
        solicitarPedidoDevolucionEcheqInEntity.setImporteCuenta(ISBANStringUtils.ajustadorBigDecimalIatx(
                new BigDecimal(cheque.getMonto(), MathContext.DECIMAL64), 14));
        solicitarPedidoDevolucionEcheqInEntity.setIdCheque(ISBANStringUtils.fillStr(cheque.getIntchequeId(), 15));
        solicitarPedidoDevolucionEcheqInEntity.setNroCheque(ISBANStringUtils.fillNum(cheque.getChequeNumero(), 9));
        solicitarPedidoDevolucionEcheqInEntity.setFechaEmision(ECheqUtils.parsearFecha(cheque.getFechaEmision(), ECheqUtils.MASCARA_FECHA_IATX));
        solicitarPedidoDevolucionEcheqInEntity.setFechaPago(ECheqUtils.parsearFecha(cheque.getFechaPago(), ECheqUtils.MASCARA_FECHA_IATX));
        String tipoDoc = TipoDocumentoEnum.obtenerTipoDocumento(cliente.getTipoDocCnsDocXNup()).getDescripcion();
        solicitarPedidoDevolucionEcheqInEntity.setTipoDocumento(ISBANStringUtils.fillStr(tipoDoc, 4));
        solicitarPedidoDevolucionEcheqInEntity.setNroDocumento(ISBANStringUtils.fillStr(cliente.getNroDocCnsDocXNup(), 11));
        solicitarPedidoDevolucionEcheqInEntity.setCuitEmisor(cheque.getCuentaEmisora().getEmisorCuit());
        solicitarPedidoDevolucionEcheqInEntity.setRazonSocialEmisor(ISBANStringUtils.fillStr(cheque.getCuentaEmisora().getEmisorRazonSocial(), 100));
        solicitarPedidoDevolucionEcheqInEntity.setTipoDocumentoBeneficiario(ISBANStringUtils.fillStr(cheque.getTenencia().getBeneficiarioDocumentoTipo().toUpperCase(), 4));
        solicitarPedidoDevolucionEcheqInEntity.setNroDocumentoBeneficiario(ISBANStringUtils.fillStr(cheque.getTenencia().getBeneficiarioDocumento(), 11));
        solicitarPedidoDevolucionEcheqInEntity.setRazonSocialBeneficiario(ISBANStringUtils.fillStr(cheque.getTenencia().getBeneficiarioNombre(), 100));
        solicitarPedidoDevolucionEcheqInEntity.setTipoSolicitud("P");
        solicitarPedidoDevolucionEcheqInEntity.setJSessionId(ISBANStringUtils.fillStr(operationDetails.getjSessionId(), 50));
        return  solicitarPedidoDevolucionEcheqInEntity;
    }

    @Override
    public String getCodigoEstadistica() {
        return EstadisticasConstants.ECHEQ_SOLICITAR_ACUERDO_DEVOLUCION;
    }

    @Override
    public String getMensajeOk(ConfirmarOperacionInDTO confirmarOperacionInDTO, MensajeBO mensajeBO) {
        String codigoMensaje = CodigoMensajeConstantes.ECHEQ_FEEDBACK_OK_SOLICITAR_ACUERDO_DEVOLUCION_ENDOSADOS;
        if (confirmarOperacionInDTO.getIngresoDesdeEmitidos()) {
            codigoMensaje = CodigoMensajeConstantes.ECHEQ_FEEDBACK_OK_SOLICITAR_ACUERDO_DEVOLUCION_EMITIDOS;
        }
        return mensajeBO.obtenerMensajePorCodigo(codigoMensaje, cheque.getChequeNumero()).getMensaje();
    }

    @Override
    public String getMensajeError(String errorCodeItax) {
        return CodigoMensajeConstantes.ECHEQ_FEEDBACK_ERROR_SOLICITAR_ACUERDO_DEVOLUCION;
    }

    public static String getCodigoMensajePopUp() {
        return CodigoMensajeConstantes.ECHEQ_MENSAJE_POP_UP_SOLICITAR_ACUERDO_DEVOLUCION;
    }
}
