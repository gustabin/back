package ar.com.santanderrio.obp.servicios.echeq.common;

import java.math.BigDecimal;
import java.text.ParseException;

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

public class OperacionRescatarECheq extends IatxBaseEcheqOperation {

	private static final String ACCION_RESCATAR = "R";
	
    public OperacionRescatarECheq(ECheqDAO eCheqDAO) {
        super(eCheqDAO);
    }

	@Override
	public OperacionEcheqEnum getOperacion() {
		return OperacionEcheqEnum.RESCATAR;
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
		Cuenta cuentaAsociada = obtenerCuentaPorCbu(cliente, cheque.getCbuCustodia());
		if (cuentaAsociada != null) {
			custodiarRescatarECheqInEntity.setTipoCuenta(ECheqUtils.obtenerTipoCuentaEcheq(cuentaAsociada));
			custodiarRescatarECheqInEntity.setSucursalCuenta(ISBANStringUtils.formatearSucursal(cuentaAsociada.getNroSucursal()));
			custodiarRescatarECheqInEntity.setNumeroCuenta(StringUtils.right(cuentaAsociada.getNroCuentaProducto(), 7));
		} else {
			custodiarRescatarECheqInEntity.setTipoCuenta(ISBANStringUtils.fillStr(StringUtils.EMPTY, 2)); 
			custodiarRescatarECheqInEntity.setSucursalCuenta(ISBANStringUtils.fillStr(StringUtils.EMPTY, 3));
			custodiarRescatarECheqInEntity.setNumeroCuenta(ISBANStringUtils.fillStr(StringUtils.EMPTY, 7));
		}
		custodiarRescatarECheqInEntity.setNroCheque(ISBANStringUtils.fillNum(cheque.getChequeNumero(), 9));
		custodiarRescatarECheqInEntity.setImporte(ISBANStringUtils.ajustadorBigDecimalIatx(new BigDecimal(cheque.getMonto()), 14));
		custodiarRescatarECheqInEntity.setFechaEmision(ECheqUtils.parsearFecha(cheque.getFechaEmision(), ECheqUtils.MASCARA_FECHA_IATX));
		custodiarRescatarECheqInEntity.setFechaPago(ECheqUtils.parsearFecha(cheque.getFechaPago(), ECheqUtils.MASCARA_FECHA_IATX));
		custodiarRescatarECheqInEntity.setTipoCheque(ECheqUtils.obtenerTipoCheque(cheque.getFechaEmision(), cheque.getFechaPago()));
		custodiarRescatarECheqInEntity.setTipoAccion(ACCION_RESCATAR);
		custodiarRescatarECheqInEntity.setCuitEmisor(cheque.getCuentaEmisora().getEmisorCuit());
        custodiarRescatarECheqInEntity.setRazonSocialEmisor(ISBANStringUtils.fillStr(cheque.getCuentaEmisora().getEmisorRazonSocial(), 100));
        custodiarRescatarECheqInEntity.setJSessionId(ISBANStringUtils.fillStr(operationDetails.getjSessionId(), 50));

        return custodiarRescatarECheqInEntity;
    }

	@Override
	public String getCodigoEstadistica() {
		return EstadisticasConstants.CODIGO_RESCATAR_ECHEQ;
	}

	@Override
	public String getMensajeOk(ConfirmarOperacionInDTO confirmarOperacionInDTO, MensajeBO mensajeBO) {
		return mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ECHEQ_FEEDBACK_OK_RESCATAR,
				cheque.getChequeNumero()).getMensaje();
	}

    @Override
    public String getMensajeError(String errorCodeItax) {
        return CodigoMensajeConstantes.ECHEQ_FEEDBACK_ERROR_RESCATAR_CON_REINTENTOS;
    }

	@Override
	public Estadistica getEstadisticaOperacion() {
		final Estadistica estadisticaModel = super.getEstadisticaOperacion();
		Cuenta cuentaoperacion = obtenerCuentaPorCbu(cliente, cheque.getCuentaEmisora().getEmisorCbu());
		if (cuentaoperacion != null) {
			estadisticaModel.setMoneda(cuentaoperacion.getMonedaAltair());	
		}
		return estadisticaModel;
	}

	public static String getCodigoMensajePopUp() {
		return CodigoMensajeConstantes.ECHEQ_MENSAJE_POP_UP_RESCATAR;
	}

	public static String getMensajeFueraHorario() {
		return CodigoMensajeConstantes.CODIGO_FUERA_HORA_RESCATAR;
	}

}
