package ar.com.santanderrio.obp.servicios.echeq.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ar.com.santanderrio.obp.servicios.echeq.dao.ECheqDAO;
import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

import ar.com.santanderrio.obp.generated.webservices.echeq.Cheque;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoDocumentoEnum;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.echeq.dto.BeneficiarioDTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.ComprobanteECheqDTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.ConfirmarOperacionInDTO;
import ar.com.santanderrio.obp.servicios.echeq.entities.AltaECheqInEntity;
import ar.com.santanderrio.obp.servicios.echeq.utils.ECheqUtils;

public class OperacionAltaECheq extends IatxBaseEcheqOperation {

	private List<Cuenta> cuentasHabilitadas;
	private static String tipoCuentasHabilitadasPrefix = "ECHEQ.TIPO.CUENTAS.HABILITADAS.ALTA";

	public OperacionAltaECheq(ECheqDAO eCheqDAO) {
        super(eCheqDAO);
    }

	@Override
	public OperacionEcheqEnum getOperacion() {
		return OperacionEcheqEnum.ALTA;
	}

	@Override
	public Boolean getOperacionDisponible() {
		return ECheqUtils.esHorarioHabilitado();
	}

    @Override
    public AltaECheqInEntity getInEntity() throws ParseException {
    	AltaECheqInEntity altaECheqInEntity = new AltaECheqInEntity();
		Cuenta cuentaOperacion = obtenerCuenta(cuentasHabilitadas, getOperationDetails().getCuentaSeleccionada());
		altaECheqInEntity.setSucursalCuenta(ISBANStringUtils.formatearSucursal(cuentaOperacion.getNroSucursal()));
		altaECheqInEntity.setTipoCuenta(ECheqUtils.obtenerTipoCuentaEcheq(cuentaOperacion));
		altaECheqInEntity.setNroCuenta(StringUtils.right(cuentaOperacion.getNroCuentaProducto(), 7));
		altaECheqInEntity.setCantTalonarios("01");
		altaECheqInEntity.setCantCheques("01");
		altaECheqInEntity.setIndPapelElectronico("E");
		altaECheqInEntity.setTipoCheque(ECheqUtils.obtenerTipoCheque(getOperationDetails().getFechaPago()));
		altaECheqInEntity.setImporte(ISBANStringUtils.ajustadorBigDecimalIatx(getOperationDetails().getImporte(), 14));
		altaECheqInEntity.setFechaPago(ECheqUtils.formatearFechaIatx(getOperationDetails().getFechaPago(), ECheqUtils.MASCARA_FECHA_FRONT));
		BeneficiarioDTO beneficiario = getOperationDetails().getBeneficiario();
		altaECheqInEntity.setTipoDocBenef(ISBANStringUtils.fillStr(beneficiario.getBenTipoDocumento(), 4));
		altaECheqInEntity.setNroDocBenef(ISBANStringUtils.fillNum(beneficiario.getBenDocumento(), 11));
		altaECheqInEntity.setNombreBenef(ISBANStringUtils.fillStr(beneficiario.getBenRazonSocial(), 100));
		altaECheqInEntity.setOrden(getOperationDetails().getModalidad());
		altaECheqInEntity.setMotivo(ISBANStringUtils.repeat(" ", 150));
		altaECheqInEntity.setConcepto(ISBANStringUtils.repeat(" ", 20));
		altaECheqInEntity.setCruzado(ECheqUtils.TIPO_CHEQUE_CRUZADO);
		altaECheqInEntity.setMailBeneficiario(ISBANStringUtils.repeat(" ", 150));
        String tipoDoc = TipoDocumentoEnum.obtenerTipoDocumento(cliente.getTipoDocCnsDocXNup()).getDescripcion();
        altaECheqInEntity.setTipoDoc(ISBANStringUtils.fillStr(tipoDoc, 4));
        altaECheqInEntity.setNroDoc(ISBANStringUtils.fillStr(cliente.getNroDocCnsDocXNup(), 11));
		altaECheqInEntity.setReferencia(ISBANStringUtils.repeat(" ", 50));
		altaECheqInEntity.setReferenciaValor(ISBANStringUtils.repeat(" ", 50));
		altaECheqInEntity.setJSessionId(ISBANStringUtils.fillStr(getOperationDetails().getjSessionId(), 50));
        return altaECheqInEntity;
    }

	@Override
	public void cargarCuentasHabilitadas(List<Cuenta> cuentasHabilitadas) {
		this.cuentasHabilitadas = cuentasHabilitadas;
	}

	@Override
	public Boolean tieneComprobante() {
		return Boolean.TRUE;
	}

	@Override
	public ComprobanteECheqDTO generarComprobante(Cheque echeq, ConfirmarOperacionInDTO
			confirmarOperacionInDTO, String numeroComprobante, String legales) {
		ComprobanteECheqDTO comprobanteDTO = new ComprobanteECheqDTO();
		comprobanteDTO.setOperacion(confirmarOperacionInDTO.getOperacion());
		BeneficiarioDTO beneficiario = confirmarOperacionInDTO.getBeneficiario();
		StringBuilder cuitBeneficiario = new StringBuilder(beneficiario.getBenTipoDocumento().trim());
		String documento = ECheqUtils.obtenerDocumento(beneficiario.getBenDocumento().trim());
		comprobanteDTO.setCuitBeneficiario(cuitBeneficiario.append(" ").append(documento).toString());
		comprobanteDTO.setNombreBeneficiario(beneficiario.getBenRazonSocial().trim());
		SimpleDateFormat sdf = new SimpleDateFormat(ECheqUtils.MASCARA_FECHA_COMPROBANTE);
		comprobanteDTO.setFechaComprobante(sdf.format(new Date()));
		comprobanteDTO.setNumeroComprobante(numeroComprobante);
		Cuenta cuentaAlta = obtenerCuenta(cuentasHabilitadas, confirmarOperacionInDTO.getCuentaSeleccionada());
		comprobanteDTO.setCuenta(cuentaAlta);
		comprobanteDTO.setFechaPago(confirmarOperacionInDTO.getFechaPago());
		comprobanteDTO.setImporte(ECheqUtils.PREFIJO_PESOS + ISBANStringUtils.formatearConComaYDosDecimales(String.valueOf(confirmarOperacionInDTO.getImporte())));
		comprobanteDTO.setModalidad(confirmarOperacionInDTO.getModalidad());
		comprobanteDTO.setLegales(legales);
		return comprobanteDTO;
	}

	@Override
	public String getMensajeOk(ConfirmarOperacionInDTO confirmarOperacionInDTO, MensajeBO mensajeBO) {
		String razonSocial = WordUtils.capitalizeFully(confirmarOperacionInDTO.getBeneficiario().getBenRazonSocial());
		return mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ECHEQ_FEEDBACK_OK_ALTA,
				ECheqUtils.PREFIJO_PESOS + ISBANStringUtils.formatearConComaYDosDecimales(String.valueOf(confirmarOperacionInDTO.getImporte())),
				razonSocial).getMensaje();
	}

	@Override
    public String getMensajeError(String errorCodeItax) {
			String codMensajeError = null; 
		if (errorCodeItax != null && "10000032".equals(errorCodeItax)){
			codMensajeError = CodigoMensajeConstantes.ECHEQ_ERROR_ALTA_2;
		} else if (errorCodeItax != null && "10000051".equals(errorCodeItax)){
			codMensajeError = CodigoMensajeConstantes.ECHEQ_ERROR_ALTA_3; 
		} else if (errorCodeItax != null && "10000061".equals(errorCodeItax)) {
			codMensajeError = CodigoMensajeConstantes.ECHEQ_ERROR_ALTA_4; 
		} else if (errorCodeItax != null && "10010011".equals(errorCodeItax)) {
			codMensajeError = CodigoMensajeConstantes.ECHEQ_ERROR_ALTA_5; 
		} else if (errorCodeItax != null && "10010012".equals(errorCodeItax)) {
			codMensajeError = CodigoMensajeConstantes.ECHEQ_ERROR_ALTA_6; 
		} else {
			codMensajeError = CodigoMensajeConstantes.ECHEQ_ERROR_ALTA;
		}
        return codMensajeError;
    }

	@Override
	public String getCodigoEstadistica() {
		return EstadisticasConstants.CODIGO_ALTA_ECHEQ;
	}

	@Override
	public Estadistica getEstadisticaOperacion() {
		Estadistica estadistica = super.getEstadisticaOperacion();
		Cuenta cuenta = obtenerCuenta(cliente.getCuentas(), operationDetails.getCuentaSeleccionada());
		if (cuenta != null) {
			StringBuilder sb = new StringBuilder(cuenta.getTipoCuentaEnum().getAbreviatura())
					.append(" ")
					.append(operationDetails.getCuentaSeleccionada());
			estadistica.setMoneda(cuenta.getMonedaAltair());	
			estadistica.setNroCtaOrigen( sb.toString() );
		}
		return estadistica;
	}

	public static String getTipoCuentasHabilitadasPrefix() {
		return tipoCuentasHabilitadasPrefix;
	}

	public static String getMensajeFueraHorario() {
		return CodigoMensajeConstantes.CODIGO_FUERA_HORA_ALTA;
	}

	public static String getCodigoLegalAyuda() {
		return CodigoMensajeConstantes.TOOLTIP_CARACTER;
	}
}
