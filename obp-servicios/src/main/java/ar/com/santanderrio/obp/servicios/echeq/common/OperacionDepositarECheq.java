package ar.com.santanderrio.obp.servicios.echeq.common;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import ar.com.santanderrio.obp.servicios.echeq.dao.ECheqDAO;
import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoDocumentoEnum;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.echeq.dto.ConfirmarOperacionInDTO;
import ar.com.santanderrio.obp.servicios.echeq.entities.DepositarECheqInEntity;
import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;
import ar.com.santanderrio.obp.servicios.echeq.utils.ECheqUtils;

public class OperacionDepositarECheq extends IatxBaseEcheqOperation {

	private List<Cuenta> cuentasHabilitadas;
	private static final String tipoCuentasHabilitadasPrefix = "ECHEQ.TIPO.CUENTAS.HABILITADAS.DEPOSITO";

    public OperacionDepositarECheq(ECheqDAO eCheqDAO) {
        super(eCheqDAO);
    }

	@Override
	public OperacionEcheqEnum getOperacion() {
		return OperacionEcheqEnum.DEPOSITAR;
	}

	@Override
	public Boolean getOperacionDisponible() {
		return ECheqUtils.esHorarioHabilitado();
	}

    @Override
    public DepositarECheqInEntity getInEntity() throws ParseException {
    	DepositarECheqInEntity depositarEcheqInEntity = new DepositarECheqInEntity();
		Cuenta cuentaOperacion = obtenerCuenta(cuentasHabilitadas, operationDetails.getCuentaSeleccionada());
		depositarEcheqInEntity.setSucursalCuenta(ISBANStringUtils.formatearSucursal(cuentaOperacion.getNroSucursal()));
		depositarEcheqInEntity.setTipoCuenta(ECheqUtils.obtenerTipoCuentaEcheq(cuentaOperacion));
		depositarEcheqInEntity.setNumeroCuenta(StringUtils.right(cuentaOperacion.getNroCuentaProducto(), 7));
		depositarEcheqInEntity.setImporte(ISBANStringUtils.ajustadorBigDecimalIatx(new BigDecimal(cheque.getMonto()), 14));
		depositarEcheqInEntity.setIdCheque(ISBANStringUtils.fillStr(cheque.getIntchequeId(), 15));
		depositarEcheqInEntity.setNroCheque(ISBANStringUtils.fillNum(cheque.getChequeNumero(), 9));
		depositarEcheqInEntity.setFechaEmision(ECheqUtils.parsearFecha(cheque.getFechaEmision(), ECheqUtils.MASCARA_FECHA_IATX));
		depositarEcheqInEntity.setFechaPago(ECheqUtils.parsearFecha(cheque.getFechaPago(), ECheqUtils.MASCARA_FECHA_IATX));
		String tipoDoc = TipoDocumentoEnum.obtenerTipoDocumento(cliente.getTipoDocCnsDocXNup()).getDescripcion();
        depositarEcheqInEntity.setTipoDocumento(ISBANStringUtils.fillStr(tipoDoc, 4));
        depositarEcheqInEntity.setNroDocumento(ISBANStringUtils.fillStr(cliente.getNroDocCnsDocXNup(), 11));
        depositarEcheqInEntity.setCuitEmisor(cheque.getCuentaEmisora().getEmisorCuit());
        depositarEcheqInEntity.setRazonSocialEmisor(ISBANStringUtils.fillStr(cheque.getCuentaEmisora().getEmisorRazonSocial(), 100));
        depositarEcheqInEntity.setTipoCheque(ECheqUtils.obtenerTipoCheque(cheque.getFechaEmision(), cheque.getFechaPago()));
        depositarEcheqInEntity.setJSessionId(ISBANStringUtils.fillStr(operationDetails.getjSessionId(), 50));
		return depositarEcheqInEntity;
    }

	@Override
	public void cargarCuentasHabilitadas(List<Cuenta> cuentasHabilitadas) {
		this.cuentasHabilitadas = cuentasHabilitadas;
	}

	//
	@Override
	public String getCodigoEstadistica() {
		return EstadisticasConstants.CODIGO_DEPOSITAR_ECHEQ;
	}

	@Override
	public Estadistica getEstadisticaOperacion() {
		final Estadistica estadisticaModel = super.getEstadisticaOperacion();
		Cuenta cuentaOperacion = obtenerCuenta(cliente.getCuentas(), operationDetails.getCuentaSeleccionada());
		if ( cuentaOperacion != null ) {
			estadisticaModel.setMoneda(cuentaOperacion.getMonedaAltair());
			StringBuilder sb = new StringBuilder(cuentaOperacion.getTipoCuentaEnum().getAbreviatura())
					.append(" ")
					.append(operationDetails.getCuentaSeleccionada());
			estadisticaModel.setNroCtaDestino(sb.toString());
		}
		return estadisticaModel;
	}


	@Override
	public String getMensajeOk(ConfirmarOperacionInDTO confirmarOperacionInDTO, MensajeBO mensajeBO) {
		Cuenta cuentaOperacion = obtenerCuenta(cuentasHabilitadas, confirmarOperacionInDTO.getCuentaSeleccionada());
		return mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ECHEQ_FEEDBACK_OK_DEPOSITAR,
				cheque.getChequeNumero(),
				ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuentaOperacion)).getMensaje();
	}

	@Override
    public String getMensajeError(String errorCodeItax) {
    	String mensajeError = null;
    	if(errorCodeItax != null && "10000031".equals(errorCodeItax)){
    		mensajeError = CodigoMensajeConstantes.ECHEQ_ERROR_DEPOSITAR_2;
    	} else if (errorCodeItax != null && "10000033".equals(errorCodeItax)) {
    		mensajeError = CodigoMensajeConstantes.ECHEQ_ERROR_DEPOSITAR_3;
		} else if(errorCodeItax != null && "10000034".equals(errorCodeItax)) {
    		mensajeError = CodigoMensajeConstantes.ECHEQ_ERROR_DEPOSITAR_4;
		}
    	else {
    		mensajeError = CodigoMensajeConstantes.ECHEQ_ERROR_DEPOSITAR;
    	}
        return mensajeError;
    }

	public static String getTipoCuentasHabilitadasPrefix() {
		return tipoCuentasHabilitadasPrefix;
	}

	public static String getMensajeFueraHorario() {
		return CodigoMensajeConstantes.CODIGO_FUERA_HORA_DEPOSITAR;
	}

	public static String getCodigoLegal() {
        return CodigoMensajeConstantes.ECHEQ_LEGAL_DEPOSITAR;
    }

}
