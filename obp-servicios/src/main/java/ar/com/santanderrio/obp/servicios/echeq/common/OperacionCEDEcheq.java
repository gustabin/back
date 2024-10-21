package ar.com.santanderrio.obp.servicios.echeq.common;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ar.com.santanderrio.obp.servicios.echeq.dao.ECheqDAO;
import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.generated.webservices.echeq.Cesion;
import ar.com.santanderrio.obp.generated.webservices.echeq.Cheque;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.echeq.dto.BeneficiarioDTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.ComprobanteECheqDTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.ConfirmarOperacionInDTO;
import ar.com.santanderrio.obp.servicios.echeq.entities.AceptarRepudiarAnularCEDEcheqInEntity;
import ar.com.santanderrio.obp.servicios.echeq.utils.ECheqUtils;

public abstract class OperacionCEDEcheq extends IatxBaseEcheqOperation {
	private final String tipoSolicitud;

	protected OperacionCEDEcheq(ECheqDAO eCheqDAO, String tipoSolicitud) {
		super(eCheqDAO);
		this.tipoSolicitud = tipoSolicitud;
	}

	@Override
	public AceptarRepudiarAnularCEDEcheqInEntity getInEntity() throws ParseException {
		AceptarRepudiarAnularCEDEcheqInEntity cedEcheqInEntity = new AceptarRepudiarAnularCEDEcheqInEntity();
		cedEcheqInEntity.setTipoCuenta(ISBANStringUtils.fillStr(StringUtils.EMPTY, 2));
		cedEcheqInEntity.setSucursalCuenta(ISBANStringUtils.fillStr(StringUtils.EMPTY, 3));
		cedEcheqInEntity.setNumeroCuenta(ISBANStringUtils.fillStr(StringUtils.EMPTY, 7));
		cedEcheqInEntity.setIdCheque(ISBANStringUtils.fillStr(cheque.getIntchequeId(), 15));
		cedEcheqInEntity.setNroCheque(ISBANStringUtils.fillNum(cheque.getChequeNumero(), 9));
		if(cheque.getCesiones() != null && !cheque.getCesiones().isEmpty()) {
			Cesion cesion = cheque.getCesiones().get(0);
			cedEcheqInEntity.setIdCesion(ISBANStringUtils.fillStr(cesion.getCesionId(), 15));
			cedEcheqInEntity.setTipoDocumento(ISBANStringUtils.fillStr(cesion.getCedenteDocumentoTipo(), 4));
			cedEcheqInEntity.setNroDocumento(ISBANStringUtils.fillStr(cesion.getCedenteDocumento(),11));
			cedEcheqInEntity.setTipoDocumentoBeneficiario(ISBANStringUtils.fillStr(cesion.getCesionarioDocumentoTipo(),4));
			cedEcheqInEntity.setNroDocumentoBeneficiario(ISBANStringUtils.fillStr(cesion.getCesionarioDocumento() == null ? "" : cesion.getCesionarioDocumento().longValue() + "",11));
		}
		cedEcheqInEntity.setImporte(ISBANStringUtils.ajustadorBigDecimalIatx(new BigDecimal(cheque.getMonto()), 14));
		cedEcheqInEntity.setTipoSolicitud(this.tipoSolicitud);
		cedEcheqInEntity.setMotivo(getMotivo());
		cedEcheqInEntity.setJSessionId(ISBANStringUtils.fillStr(operationDetails.getjSessionId(), 50));
		return cedEcheqInEntity;
	}

	protected String getMotivo() {
		return ISBANStringUtils.fillStr("", 100);
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
	    comprobanteDTO.setImporte(ECheqUtils.PREFIJO_PESOS + ISBANStringUtils.formatearConComaYDosDecimales(String.valueOf(confirmarOperacionInDTO.getImporte())));
	    comprobanteDTO.setModalidad(confirmarOperacionInDTO.getModalidad());
	    comprobanteDTO.setLegales(legales);
		return comprobanteDTO;
	}

	public static String getCodigoMensajePopUp() {
		return CodigoMensajeConstantes.ECHEQ_POPUP_ACEPTAR_CED;
	}
}
