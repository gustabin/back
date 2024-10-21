package ar.com.santanderrio.obp.servicios.echeq.common;

import java.math.BigDecimal;

import ar.com.santanderrio.obp.servicios.echeq.dao.ECheqDAO;
import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;
import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoDocumentoEnum;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.echeq.dto.ConfirmarOperacionInDTO;
import ar.com.santanderrio.obp.servicios.echeq.entities.AnularECheqInEntity;
import ar.com.santanderrio.obp.servicios.echeq.utils.ECheqUtils;

public class OperacionAnularECheq extends IatxBaseEcheqOperation {

    public OperacionAnularECheq(ECheqDAO echeqDao) {
        super(echeqDao);
    }

	@Override
	public OperacionEcheqEnum getOperacion() {
		return OperacionEcheqEnum.ANULAR;
	}

	@Override
	public Boolean getOperacionDisponible() {
    	return ECheqUtils.esHorarioHabilitado();
    }

	@Override
    public AnularECheqInEntity getInEntity() {
    	AnularECheqInEntity anularECheqInEntity = new AnularECheqInEntity();
		String tipoDoc = TipoDocumentoEnum.obtenerTipoDocumento(cliente.getTipoDocCnsDocXNup()).getDescripcion();
		Cuenta cuentaAsociada = obtenerCuentaPorCbu(cliente, getCheque().getCuentaEmisora().getEmisorCbu());

		anularECheqInEntity.setIdCheque(ISBANStringUtils.fillStr(getCheque().getIntchequeId(), 15));
		anularECheqInEntity.setTipoDocumento(ISBANStringUtils.fillStr(tipoDoc, 4));
		anularECheqInEntity.setNroDocumento(ISBANStringUtils.fillStr(cliente.getNroDocCnsDocXNup(), 11));
		anularECheqInEntity.setMotivoAnulacion(ISBANStringUtils.fillStr(getOperationDetails().getMotivoAnulacion(), 500));
		if (cuentaAsociada != null) {
			anularECheqInEntity.setTipoCuenta(ECheqUtils.obtenerTipoCuentaEcheq(cuentaAsociada));
			anularECheqInEntity.setSucursalCuenta(ISBANStringUtils.formatearSucursal(cuentaAsociada.getNroSucursal()));
			anularECheqInEntity.setNumeroCuenta(StringUtils.right(cuentaAsociada.getNroCuentaProducto(), 7));
		} else {
			anularECheqInEntity.setTipoCuenta(ISBANStringUtils.fillStr(StringUtils.EMPTY, 2)); 
			anularECheqInEntity.setSucursalCuenta(ISBANStringUtils.fillStr(StringUtils.EMPTY, 3));
			anularECheqInEntity.setNumeroCuenta(ISBANStringUtils.fillStr(StringUtils.EMPTY, 7));
		}
		anularECheqInEntity.setNroCheque(ISBANStringUtils.fillNum(getCheque().getChequeNumero(), 9));
		anularECheqInEntity.setImporte(ISBANStringUtils.ajustadorBigDecimalIatx(new BigDecimal(getCheque().getMonto()), 14));
		anularECheqInEntity.setFechaEmision(ECheqUtils.parsearFecha(getCheque().getFechaEmision(), ECheqUtils.MASCARA_FECHA_IATX));
		anularECheqInEntity.setFechaPago(ECheqUtils.parsearFecha(getCheque().getFechaPago(), ECheqUtils.MASCARA_FECHA_IATX));
		anularECheqInEntity.setTipoDocumentoBeneficiario(ISBANStringUtils.fillStr(getCheque().getEmitidoA().getBeneficiarioDocumentoTipo().toUpperCase(), 4));
		anularECheqInEntity.setNroDocumentoBeneficiario(ISBANStringUtils.fillStr(getCheque().getEmitidoA().getBeneficiarioDocumento(), 11));
		anularECheqInEntity.setRazonSocialBeneficiario(ISBANStringUtils.fillStr(getCheque().getEmitidoA().getBeneficiarioNombre(), 100));
        anularECheqInEntity.setJSessionId(ISBANStringUtils.fillStr(getOperationDetails().getjSessionId(), 50));

        return anularECheqInEntity;
    }

    @Override
    public String getMensajeError(String errorCodeItax) {
        return CodigoMensajeConstantes.ECHEQ_FEEDBACK_ERROR_ANULAR_CON_REINTENTOS;
    }

    @Override
    public String getMensajeOk(ConfirmarOperacionInDTO confirmarOperacionInDTO, MensajeBO mensajeBO) {
        return mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ECHEQ_FEEDBACK_OK_ANULAR, 
        		cheque.getChequeNumero()).getMensaje();
    }

    @Override
    public String getCodigoEstadistica() {
        return EstadisticasConstants.CODIGO_ANULAR_ECHEQ;
    }

    @Override
	public Estadistica getEstadisticaOperacion() {
		Estadistica estadisticaModel = super.getEstadisticaOperacion();
		Cuenta cuentaOperacion = obtenerCuentaPorCbu(cliente, cheque.getCuentaEmisora().getEmisorCbu());
		if (cuentaOperacion != null) {
			StringBuilder sb = new StringBuilder(cuentaOperacion.getTipoCuentaEnum().getAbreviatura())
					.append(" ")
					.append(cuentaOperacion.obtenerNroCuentaFormateado());
			estadisticaModel.setNroCtaOrigen(sb.toString());
		}
		return estadisticaModel;
	}

}
