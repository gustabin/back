package ar.com.santanderrio.obp.servicios.echeq.common;

import java.math.BigDecimal;
import java.math.MathContext;

import ar.com.santanderrio.obp.servicios.echeq.dao.ECheqDAO;
import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;
import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.generated.webservices.echeq.Cheque;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoDocumentoEnum;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.echeq.dto.ConfirmarOperacionInDTO;
import ar.com.santanderrio.obp.servicios.echeq.entities.EmitirCertificadoECheqInEntity;
import ar.com.santanderrio.obp.servicios.echeq.utils.ECheqUtils;

public class OperacionEmitirCertificadoECheq extends IatxBaseEcheqOperation {
    
	
    public OperacionEmitirCertificadoECheq(ECheqDAO eCheqDAO) {
        super(eCheqDAO);
    }

    @Override
    public OperacionEcheqEnum getOperacion() {
        return OperacionEcheqEnum.EMITIR_CERTIFICADO;
    }

    @Override
    public EmitirCertificadoECheqInEntity getInEntity() {
        EmitirCertificadoECheqInEntity emitirCertificadoEcheqInEntity = new EmitirCertificadoECheqInEntity();
        emitirCertificadoEcheqInEntity.setIdCheque(ISBANStringUtils.fillStr(cheque.getIntchequeId(), 15));
        String tipoDoc = TipoDocumentoEnum.obtenerTipoDocumento(cliente.getTipoDocCnsDocXNup()).getDescripcion();
        emitirCertificadoEcheqInEntity.setTipoDocumento(ISBANStringUtils.fillStr(tipoDoc, 4));
        emitirCertificadoEcheqInEntity.setNroDocumento(ISBANStringUtils.fillStr(cliente.getNroDocCnsDocXNup(), 11));
        Cuenta cuentaAsociada = recuperarCuentaSegunCBU(cliente, cheque);
        if (cuentaAsociada != null) {
        	emitirCertificadoEcheqInEntity.setTipoCuenta(ECheqUtils.obtenerTipoCuentaEcheq(cuentaAsociada));
            emitirCertificadoEcheqInEntity.setSucursalCuenta(ISBANStringUtils.formatearSucursal(cuentaAsociada.getNroSucursal()));
            emitirCertificadoEcheqInEntity.setNumeroCuenta(StringUtils.right(cuentaAsociada.getNroCuentaProducto(), 7));
        } else {
            emitirCertificadoEcheqInEntity.setTipoCuenta(StringUtils.leftPad(StringUtils.EMPTY, 2, " ")); 
            emitirCertificadoEcheqInEntity.setSucursalCuenta(StringUtils.leftPad(StringUtils.EMPTY, 3, " "));
            emitirCertificadoEcheqInEntity.setNumeroCuenta(StringUtils.leftPad(StringUtils.EMPTY, 7, " "));
        }
        emitirCertificadoEcheqInEntity.setNroCheque(ISBANStringUtils.fillNum(cheque.getChequeNumero(), 9));
        emitirCertificadoEcheqInEntity.setImporteCuenta(ISBANStringUtils.ajustadorBigDecimalIatx(new BigDecimal(cheque.getMonto(), MathContext.DECIMAL64), 14));
        emitirCertificadoEcheqInEntity.setFechaEmision(ECheqUtils.parsearFecha(cheque.getFechaEmision(), ECheqUtils.MASCARA_FECHA_IATX));
        emitirCertificadoEcheqInEntity.setFechaPago(ECheqUtils.parsearFecha(cheque.getFechaPago(), ECheqUtils.MASCARA_FECHA_IATX));
        emitirCertificadoEcheqInEntity.setCuitEmisor(cheque.getCuentaEmisora().getEmisorCuit());
        emitirCertificadoEcheqInEntity.setRazonSocialEmisor(ISBANStringUtils.fillStr(cheque.getCuentaEmisora().getEmisorRazonSocial(), 100));
        emitirCertificadoEcheqInEntity.setJSessionId(ISBANStringUtils.fillStr(operationDetails.getjSessionId(), 50));
        return emitirCertificadoEcheqInEntity;
    }

    @Override
    public String getCodigoEstadistica() {
        return EstadisticasConstants.ECHEQ_EMITIR_CERTIFICADO;
    }

    @Override
    public String getMensajeOk(ConfirmarOperacionInDTO confirmarOperacionInDTO, MensajeBO mensajeBO) {
        return mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ECHEQ_FEEDBACK_OK_EMITIR_CERTIFICADO,
                cheque.getChequeNumero()).getMensaje();
    }

    @Override
    public String getMensajeError(String errorCodeItax) {
        return CodigoMensajeConstantes.ECHEQ_FEEDBACK_ERROR_EMITIR_CERTIFICADO;
    }

    @Override
	public Estadistica getEstadisticaOperacion() {
		final Estadistica estadisticaModel = super.getEstadisticaOperacion();

        if (cheque.getCbuDeposito() != null) {
            Cuenta cuentaOperacion = obtenerCuentaPorCbu(cliente, cheque.getCbuDeposito());
            if (cuentaOperacion != null) {
                StringBuilder sb = new StringBuilder(cuentaOperacion.getTipoCuentaEnum().getAbreviatura())
                        .append(" ")
                        .append(cuentaOperacion.obtenerNroCuentaFormateado());
                estadisticaModel.setNroCtaDestino(sb.toString());
            }
        }
		return estadisticaModel;
	}

    private Cuenta recuperarCuentaSegunCBU(Cliente cliente, Cheque echeq) {
        if (!StringUtils.isEmpty(echeq.getCbuDeposito())) {
            return obtenerCuentaPorCbu(cliente, echeq.getCbuDeposito());
        }
        if (!StringUtils.isEmpty(echeq.getCbuCustodia())) {
            return obtenerCuentaPorCbu(cliente, echeq.getCbuCustodia());
        }
        return null;
    }

    public static String getCodigoMensajePopUp() {
        return CodigoMensajeConstantes.ECHEQ_MENSAJE_POP_UP_EMITIR_CERTIFICADO;
    }

    public static String getCodigoLegal() {
        return CodigoMensajeConstantes.ECHEQ_LEGAL_EMITIR_CERTIFICADO;
    }

}
