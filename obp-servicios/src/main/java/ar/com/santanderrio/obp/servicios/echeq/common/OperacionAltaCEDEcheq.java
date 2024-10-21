package ar.com.santanderrio.obp.servicios.echeq.common;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.echeq.dao.ECheqDAO;
import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.generated.webservices.echeq.Cheque;
import ar.com.santanderrio.obp.servicios.comex.transfext.common.TiposDocumentoComexEnum;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.echeq.dto.BeneficiarioDTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.ComprobanteECheqDTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.ConfirmarOperacionInDTO;
import ar.com.santanderrio.obp.servicios.echeq.entities.AltaCEDEcheqInEntity;
import ar.com.santanderrio.obp.servicios.echeq.entities.IOperacionECheqInEntity;
import ar.com.santanderrio.obp.servicios.echeq.utils.ECheqUtils;

public class OperacionAltaCEDEcheq extends IatxBaseEcheqOperation {

	protected OperacionAltaCEDEcheq(ECheqDAO eCheqDAO) {
		super(eCheqDAO);
	}

	@Override
	public OperacionEcheqEnum getOperacion() {
		return OperacionEcheqEnum.EMITIR_CED;
	}

	@Override
	public IOperacionECheqInEntity<IatxRequestData> getInEntity() throws ParseException {
		AltaCEDEcheqInEntity altaCedInEntity = new AltaCEDEcheqInEntity();
		altaCedInEntity.setSucursalCuenta(ISBANStringUtils.fillStr(StringUtils.EMPTY, 3));
		altaCedInEntity.setTipoCuenta(ISBANStringUtils.fillStr(StringUtils.EMPTY, 2));
		altaCedInEntity.setNumeroCuenta(ISBANStringUtils.fillStr(StringUtils.EMPTY, 7));
		altaCedInEntity.setIdCheque(ISBANStringUtils.fillStr(getCheque().getIntchequeId(),15));
		altaCedInEntity.setTipoDocumento(ISBANStringUtils.fillStr(TiposDocumentoComexEnum.obtenerDescripcionPorCodigo(cliente.getTipoDocCnsDocXNup()),4));
		altaCedInEntity.setNroDocumento(ISBANStringUtils.fillStr(cliente.getNroDocCnsDocXNup(),11));
		altaCedInEntity.setTipoDocumentoBeneficiario(ISBANStringUtils.fillStr(getOperationDetails().getBeneficiario().getBenTipoDocumento(),4));
		altaCedInEntity.setNroDocumentoBeneficiario(ISBANStringUtils.fillStr(getOperationDetails().getBeneficiario().getBenDocumento(),11));
		altaCedInEntity.setRazonSocialBeneficiario(ISBANStringUtils.fillStr(getOperationDetails().getBeneficiario().getBenRazonSocial(),100));
		altaCedInEntity.setDomicilioBeneficiario(ISBANStringUtils.fillStr(getOperationDetails().getBeneficiario().getBenDomicilio(),100));
		try {
			altaCedInEntity.setImporte(ISBANStringUtils.ajustadorBigDecimalIatx(ISBANStringUtils.convertirImporte(getCheque().getMonto()), 14));
		} catch (ImporteConvertException e) {
			altaCedInEntity.setImporte(ISBANStringUtils.fillNum("0", 14));
		}
		altaCedInEntity.setJSessionId(ISBANStringUtils.fillStr(getOperationDetails().getjSessionId(),50));
		return altaCedInEntity;
	}

	@Override
	public Boolean tieneComprobante() {
		return Boolean.TRUE;
	}

	@Override
	public ComprobanteECheqDTO generarComprobante(Cheque echeq, ConfirmarOperacionInDTO confirmarOperacionInDTO,
			String numeroComprobante, String legales) {
		ComprobanteECheqDTO comprobanteDTO = new ComprobanteECheqDTO();

		BeneficiarioDTO beneficiario = confirmarOperacionInDTO.getBeneficiario();
		StringBuilder cuitBeneficiario = new StringBuilder(beneficiario.getBenTipoDocumento().trim());
		String documento = ECheqUtils.obtenerDocumento(beneficiario.getBenDocumento().trim());

		comprobanteDTO.setOperacion(confirmarOperacionInDTO.getOperacion());
    	comprobanteDTO.setCuitBeneficiario(cuitBeneficiario.append(" ").append(documento).toString());
		comprobanteDTO.setNombreBeneficiario(beneficiario.getBenRazonSocial().trim());
		comprobanteDTO.setDomicilioBeneficiario(beneficiario.getBenDomicilio());
		SimpleDateFormat sdf = new SimpleDateFormat(ECheqUtils.MASCARA_FECHA_COMPROBANTE);
		comprobanteDTO.setFechaComprobante(sdf.format(new Date()));
		comprobanteDTO.setNumeroComprobante(numeroComprobante);
	    comprobanteDTO.setImporte(ECheqUtils.PREFIJO_PESOS + ISBANStringUtils.formatearSaldo(new BigDecimal(cheque.getMonto())));
	    comprobanteDTO.setCaracter(ISBANStringUtils.formatearFraseInicialMayuscula(echeq.getChequeCaracter()));
	    comprobanteDTO.setLegales(legales);
	    StringBuilder emisorCuit = new StringBuilder("CUIT/CUIL/CDI");
		String cuit = ECheqUtils.obtenerDocumento(echeq.getCuentaEmisora().getEmisorCuit().trim());
		comprobanteDTO.setEmisorCuit(emisorCuit.append(" ").append(cuit).toString());
		String nombreEmisor = ECheqUtils.eliminarEspacios(echeq.getCuentaEmisora().getEmisorRazonSocial());
	    comprobanteDTO.setEmisorRazonSocial(WordUtils.capitalizeFully(nombreEmisor));
	    comprobanteDTO.setNumeroCheque(echeq.getChequeNumero());
	    
	    SimpleDateFormat sdfAmco = new SimpleDateFormat(ECheqUtils.MASCARA_FECHA_AMCO);
	    try {
	    	sdf = new SimpleDateFormat(ECheqUtils.MASCARA_FECHA_FRONT);
	    	comprobanteDTO.setFechaEmision(sdf.format(sdfAmco.parse(echeq.getFechaEmision())));
			comprobanteDTO.setFechaPago(sdf.format(sdfAmco.parse(echeq.getFechaPago())));
		} catch (ParseException e) {
			comprobanteDTO.setFechaEmision("");
			comprobanteDTO.setFechaPago("");
		}
		return comprobanteDTO;
	}

	@Override
	public String getMensajeOk(ConfirmarOperacionInDTO confirmarOperacionInDTO, MensajeBO mensajeBO) {
		String razonSocial = WordUtils.capitalizeFully(confirmarOperacionInDTO.getBeneficiario().getBenRazonSocial());
		return mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ECHEQ_FEEDBACK_OK_EMITIR_CED,
				cheque.getChequeNumero(), ISBANStringUtils.formatearSaldo(new BigDecimal(cheque.getMonto())), razonSocial).getMensaje();
	}

	@Override
	public String getMensajeError(String errorCodeItax) {
		return CodigoMensajeConstantes.ECHEQ_MENSAJE_ERROR_ACTCLEECED_EMITIR_CED;
	}

	@Override
	public String getMensajeErrorTimeOut(ConfirmarOperacionInDTO confirmarOperacionInDTO) {
		return CodigoMensajeConstantes.ECHEQ_MENSAJE_ERROR_TIMED_OUT_ACTCLEECED_EMITIR_CED;
	}

	@Override
	public String getCodigoEstadistica() {
		return EstadisticasConstants.ECHEQ_EMITIR_CED;
	}

	public static String getCodigoLegal() {
		return CodigoMensajeConstantes.ECHEQ_EMITIR_CED;
	}

}
