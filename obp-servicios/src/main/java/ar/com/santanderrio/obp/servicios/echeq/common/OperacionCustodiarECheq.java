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
import ar.com.santanderrio.obp.servicios.echeq.entities.CustodiarRescatarECheqInEntity;
import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;
import ar.com.santanderrio.obp.servicios.echeq.utils.ECheqUtils;

public class OperacionCustodiarECheq extends IatxBaseEcheqOperation {

	private static final String ACCION_CUSTODIAR = "C";
	private List<Cuenta> cuentasHabilitadas;
	private static String tipoCuentasHabilitadasPrefix = "ECHEQ.TIPO.CUENTAS.HABILITADAS.CUSTODIA";

    public OperacionCustodiarECheq(ECheqDAO eCheqDAO) {
        super(eCheqDAO);
    }

	@Override
	public OperacionEcheqEnum getOperacion() {
		return OperacionEcheqEnum.CUSTODIAR;
	}

	@Override
	public Boolean getOperacionDisponible() {
		return ECheqUtils.esHorarioHabilitado();
	}

	@Override
    public CustodiarRescatarECheqInEntity getInEntity() throws ParseException {
    	CustodiarRescatarECheqInEntity custodiarRescatarECheqInEntity = new CustodiarRescatarECheqInEntity();
        custodiarRescatarECheqInEntity.setIdCheque(ISBANStringUtils.fillStr(cheque.getIntchequeId(), 15));
        String tipoDoc = TipoDocumentoEnum.obtenerTipoDocumento(cliente.getTipoDocCnsDocXNup()).getDescripcion();
        custodiarRescatarECheqInEntity.setTipoDocumento(ISBANStringUtils.fillStr(tipoDoc, 4));
        custodiarRescatarECheqInEntity.setNroDocumento(ISBANStringUtils.fillStr(cliente.getNroDocCnsDocXNup(), 11));
		Cuenta cuentaSeleccionada = obtenerCuenta(cuentasHabilitadas, operationDetails.getCuentaSeleccionada());
		custodiarRescatarECheqInEntity.setSucursalCuenta(ISBANStringUtils.formatearSucursal(cuentaSeleccionada.getNroSucursal()));
		custodiarRescatarECheqInEntity.setTipoCuenta(ECheqUtils.obtenerTipoCuentaEcheq(cuentaSeleccionada));
		custodiarRescatarECheqInEntity.setNumeroCuenta(StringUtils.right(cuentaSeleccionada.getNroCuentaProducto(), 7));
		custodiarRescatarECheqInEntity.setNroCheque(ISBANStringUtils.fillNum(cheque.getChequeNumero(), 9));
		custodiarRescatarECheqInEntity.setImporte(ISBANStringUtils.ajustadorBigDecimalIatx(new BigDecimal(cheque.getMonto()), 14));
		custodiarRescatarECheqInEntity.setFechaEmision(ECheqUtils.parsearFecha(cheque.getFechaEmision(), ECheqUtils.MASCARA_FECHA_IATX));
		custodiarRescatarECheqInEntity.setFechaPago(ECheqUtils.parsearFecha(cheque.getFechaPago(), ECheqUtils.MASCARA_FECHA_IATX));
		custodiarRescatarECheqInEntity.setTipoCheque(ECheqUtils.obtenerTipoCheque(cheque.getFechaEmision(), cheque.getFechaPago()));
		custodiarRescatarECheqInEntity.setTipoAccion(ACCION_CUSTODIAR);
		custodiarRescatarECheqInEntity.setCuitEmisor(cheque.getCuentaEmisora().getEmisorCuit());
        custodiarRescatarECheqInEntity.setRazonSocialEmisor(ISBANStringUtils.fillStr(cheque.getCuentaEmisora().getEmisorRazonSocial(), 100));
        custodiarRescatarECheqInEntity.setJSessionId(ISBANStringUtils.fillStr(operationDetails.getjSessionId(), 50));
        return custodiarRescatarECheqInEntity;
    }

	@Override
	public void cargarCuentasHabilitadas(List<Cuenta> cuentasHabilitadas) {
		this.cuentasHabilitadas = cuentasHabilitadas;
	}

	//
	@Override
	public String getMensajeOk(ConfirmarOperacionInDTO confirmarOperacionInDTO, MensajeBO mensajeBO) {
		Cuenta cuentaOperacion = obtenerCuenta(cuentasHabilitadas, confirmarOperacionInDTO.getCuentaSeleccionada());
		return mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ECHEQ_FEEDBACK_OK_CUSTODIAR,
				cheque.getChequeNumero(),
				ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuentaOperacion)).getMensaje();
	}

	@Override
    public String getMensajeError(String errorCodeItax) {
        return CodigoMensajeConstantes.ECHEQ_FEEDBACK_ERROR_CUSTODIAR_CON_REINTENTOS;
    }

	@Override
	public String getCodigoEstadistica() {
    	return EstadisticasConstants.CODIGO_CUSTODIAR_ECHEQ;
    }

	@Override
	public Estadistica getEstadisticaOperacion() {
		Estadistica estadisticaModel = super.getEstadisticaOperacion();
		Cuenta cuentaOperacion = obtenerCuenta(cliente.getCuentas(), operationDetails.getCuentaSeleccionada());
		if (cuentaOperacion != null) {
			estadisticaModel.setMoneda(cuentaOperacion.getMonedaAltair());
			StringBuilder sb = new StringBuilder(cuentaOperacion.getTipoCuentaEnum().getAbreviatura())
					.append(" ")
					.append(operationDetails.getCuentaSeleccionada());
			estadisticaModel.setNroCtaDestino(sb.toString());
		}
		return estadisticaModel;
	}

	public static String getTipoCuentasHabilitadasPrefix() {
		return tipoCuentasHabilitadasPrefix;
	}

	public static String getMensajeFueraHorario() {
		return CodigoMensajeConstantes.CODIGO_FUERA_HORA_CUSTODIAR;
	}

	public static String getCodigoLegal() {
		return CodigoMensajeConstantes.ECHEQ_LEGAL_CUSTODIAR;
	}

}
