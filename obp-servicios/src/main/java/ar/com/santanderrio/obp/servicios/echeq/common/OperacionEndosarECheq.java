package ar.com.santanderrio.obp.servicios.echeq.common;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ar.com.santanderrio.obp.servicios.echeq.dao.ECheqDAO;
import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;
import org.apache.commons.lang.WordUtils;

import ar.com.santanderrio.obp.generated.webservices.echeq.Cheque;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoDocumentoEnum;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.echeq.dto.BeneficiarioDTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.ComprobanteECheqDTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.ConfirmarOperacionInDTO;
import ar.com.santanderrio.obp.servicios.echeq.entities.EndosarECheqInEntity;
import ar.com.santanderrio.obp.servicios.echeq.utils.ECheqUtils;

public class OperacionEndosarECheq extends IatxBaseEcheqOperation {

    public OperacionEndosarECheq(ECheqDAO eCheqDAO) {
        super(eCheqDAO);
    }

    @Override
	public OperacionEcheqEnum getOperacion() {
		return OperacionEcheqEnum.ENDOSAR;
	}

	@Override
    public EndosarECheqInEntity getInEntity() throws ParseException {
    	EndosarECheqInEntity endosarECheqInEntity = new EndosarECheqInEntity();
		endosarECheqInEntity.setIdCheque(ISBANStringUtils.fillStr(cheque.getIntchequeId(), 15));
		String tipoDoc = TipoDocumentoEnum.obtenerTipoDocumento(cliente.getTipoDocCnsDocXNup()).getDescripcion();
		endosarECheqInEntity.setTipoDocumento(ISBANStringUtils.fillStr(tipoDoc, 4));
		endosarECheqInEntity.setNroDocumento(ISBANStringUtils.fillStr(cliente.getNroDocCnsDocXNup(), 11));
		BeneficiarioDTO beneficiario = operationDetails.getBeneficiario();
		endosarECheqInEntity.setTipoDocBenefNvo(ISBANStringUtils.fillStr(beneficiario.getBenTipoDocumento(), 4));
		endosarECheqInEntity.setNroDocBenefNvo(ISBANStringUtils.fillStr(beneficiario.getBenDocumento(), 11)); 
		endosarECheqInEntity.setRazonSocBenefNvo(ISBANStringUtils.fillStr(beneficiario.getBenRazonSocial(), 100));
		endosarECheqInEntity.setTipoCuenta(ISBANStringUtils.repeat(" ", 2));
		endosarECheqInEntity.setSucursalCuenta(ISBANStringUtils.repeat(" ", 3));
		endosarECheqInEntity.setNumeroCuenta(ISBANStringUtils.repeat(" ", 7));
		endosarECheqInEntity.setNroCheque(ISBANStringUtils.fillNum(cheque.getChequeNumero(), 9));
		endosarECheqInEntity.setImporte(ISBANStringUtils.ajustadorBigDecimalIatx(new BigDecimal(cheque.getMonto()), 14));
		endosarECheqInEntity.setFechaEmision(ECheqUtils.parsearFecha(cheque.getFechaEmision(), ECheqUtils.MASCARA_FECHA_IATX));
		endosarECheqInEntity.setFechaPago(ECheqUtils.parsearFecha(cheque.getFechaPago(), ECheqUtils.MASCARA_FECHA_IATX));
		endosarECheqInEntity.setCuitEmisor(cheque.getCuentaEmisora().getEmisorCuit());
		endosarECheqInEntity.setRazonSocEmisor(ISBANStringUtils.fillStr(cheque.getCuentaEmisora().getEmisorRazonSocial(), 100));
		endosarECheqInEntity.setTipoEndoso(operationDetails.getTipoEndoso());
		endosarECheqInEntity.setReferencia(ISBANStringUtils.repeat(" ", 50));
		endosarECheqInEntity.setReferenciaValor(ISBANStringUtils.repeat(" ", 50));
		endosarECheqInEntity.setJSessionId(ISBANStringUtils.fillStr(operationDetails.getjSessionId(), 50));
		endosarECheqInEntity.setTipoDocBenefOri(ISBANStringUtils.fillStr(cheque.getEmitidoA().getBeneficiarioDocumentoTipo().toUpperCase(), 4));
		endosarECheqInEntity.setNroDocBenefOri(ISBANStringUtils.fillStr(cheque.getEmitidoA().getBeneficiarioDocumento(), 11));
		endosarECheqInEntity.setRazonSocBenefOri(ISBANStringUtils.fillStr(cheque.getEmitidoA().getBeneficiarioNombre(), 100));
		return endosarECheqInEntity;
    }

    @Override
    public String getMensajeError(String errorCodeItax) {
        return CodigoMensajeConstantes.ECHEQ_ERROR_ENDOSAR;
    }

    @Override
    public String getMensajeOk(ConfirmarOperacionInDTO confirmarOperacionInDTO, MensajeBO mensajeBO) {
        String razonSocial = WordUtils.capitalizeFully(confirmarOperacionInDTO.getBeneficiario().getBenRazonSocial());
        return mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ECHEQ_FEEDBACK_OK_ENDOSAR, 
        		cheque.getChequeNumero(), ISBANStringUtils.formatearSaldo(new BigDecimal(cheque.getMonto())),
        		razonSocial).getMensaje();
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
        StringBuilder sb = new StringBuilder(beneficiario.getBenTipoDocumento().trim());
    	String documento = ECheqUtils.obtenerDocumento(beneficiario.getBenDocumento().trim());
    	comprobanteDTO.setCuitBeneficiario(sb.append(" ").append(documento).toString());
		comprobanteDTO.setNombreBeneficiario(beneficiario.getBenRazonSocial().trim());
		SimpleDateFormat sdf = new SimpleDateFormat(ECheqUtils.MASCARA_FECHA_COMPROBANTE);
		comprobanteDTO.setFechaComprobante(sdf.format(new Date()));
		comprobanteDTO.setNumeroComprobante(numeroComprobante);
		StringBuilder emisorCuit = new StringBuilder("CUIT/CUIL/CDI");
		String cuit = ECheqUtils.obtenerDocumento(echeq.getCuentaEmisora().getEmisorCuit().trim());
		comprobanteDTO.setEmisorCuit(emisorCuit.append(" ").append(cuit).toString());
		String nombreEmisor = ECheqUtils.eliminarEspacios(echeq.getCuentaEmisora().getEmisorRazonSocial());
		comprobanteDTO.setEmisorRazonSocial(WordUtils.capitalizeFully(nombreEmisor));
    	comprobanteDTO.setFechaEmision(ECheqUtils.parsearFecha(echeq.getFechaEmision(), ECheqUtils.MASCARA_FECHA_FRONT));
    	comprobanteDTO.setFechaPago(ECheqUtils.parsearFecha(echeq.getFechaPago(), ECheqUtils.MASCARA_FECHA_FRONT));
    	comprobanteDTO.setImporte(ECheqUtils.PREFIJO_PESOS + ISBANStringUtils.formatearSaldo(new BigDecimal(echeq.getMonto())));
    	comprobanteDTO.setNumeroCheque(echeq.getChequeNumero());
    	comprobanteDTO.setEstado(echeq.getEstado());
    	comprobanteDTO.setTipoEndoso(confirmarOperacionInDTO.getTipoEndoso());
    	comprobanteDTO.setLegales(legales);
		return comprobanteDTO;
	}

	@Override
	public String getCodigoEstadistica() {
		return EstadisticasConstants.CODIGO_ENDOSAR_ECHEQ;
	}

	@Override
	public Estadistica getEstadisticaOperacion() {
		final Estadistica estadisticaModel = super.getEstadisticaOperacion();
		estadisticaModel.setCodigoTransaccion("NEG".equals(operationDetails.getTipoEndoso())
				? EstadisticasConstants.CODIGO_ENDOSAR_ECHEQ_NEGOOCIACION
				: EstadisticasConstants.CODIGO_ENDOSAR_ECHEQ_NOMINADO);
		return estadisticaModel;
	}

	public static String getCodigoLegalAyuda() {
		return CodigoMensajeConstantes.TOOLTIP_TIPOS_ENDOSO;
	}
}
