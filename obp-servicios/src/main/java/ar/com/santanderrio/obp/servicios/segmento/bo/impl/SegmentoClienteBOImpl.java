/*
 * 
 */
package ar.com.santanderrio.obp.servicios.segmento.bo.impl;

import java.text.MessageFormat;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.segmento.GetClientSegmentSelectLaborableDataResponse;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.OperadorEjecutivo;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.segmento.bo.SegmentoClienteBO;
import ar.com.santanderrio.obp.servicios.segmento.dao.SegmentoClienteDAO;
import isban.commons.cryptography.crypto.utils.Base64;

/**
 * The Class SegmentoClienteBOImpl.
 */
@Component
public class SegmentoClienteBOImpl implements SegmentoClienteBO {

	/** The segmento cliente dao. */
	@Autowired
	private SegmentoClienteDAO segmentoClienteDao;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The legal BO. */
	@Autowired
	private LegalBO legalBO;

	@Autowired
	private SesionParametros sesionParametros;
	
	/** Numero del canal del portal. */
	private static final String CANAL = "04";

	/** Numero del subcanal. */
	private static final String SUB_CANAL = "99";

	/** The cuadrante c1. */
	private static final String CUADRANTE_C1 = "C1";
	
	/** The Constant CUADRANTE_P1. */
	private static final String CUADRANTE_P1 = "P1";
	
	/** The Constant CUADRANTE_P2. */
	private static final String CUADRANTE_P2 = "P2";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SegmentoClienteBOImpl.class);
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.segmento.bo.SegmentoClienteBO#
	 * obtenerSegmento()
	 */
	@Override
	public Respuesta<Segmento> obtenerSegmento(Cliente cliente) {
		Respuesta<Segmento> respuesta = null;
		GetClientSegmentSelectLaborableDataResponse getClientSelectSegmentLaborableDataResponse = null;
		try {
			getClientSelectSegmentLaborableDataResponse = segmentoClienteDao.getClientSelectSegmentLaborable(cliente.getNup(), CANAL,
			        SUB_CANAL, cliente.getUsuarioRacf(), cliente.getPasswordRacf());
			Segmento segmento = mapearSegmento(getClientSelectSegmentLaborableDataResponse);
			respuesta = respuestaFactory.crearRespuestaOk(segmento);
		} catch (DAOException e) {
			LOGGER.error("Error al recuperar el segmento del cliente", e);
			Segmento segmento = mapearSegmento(getClientSelectSegmentLaborableDataResponse);
			respuesta = respuestaFactory.crearRespuestaError(Segmento.class, segmento, "", TipoError.ERROR_GENERICO,
			        "");
		}
		return respuesta;
	}

	/**
	 * Mapear segmento.
	 *
	 * @param getClientSelectSegmentResponse the get client segment data response
	 * @return the segmento
	 */
	private Segmento mapearSegmento(GetClientSegmentSelectLaborableDataResponse getClientSelectLaborableSegmentResponse) {

		Segmento segmento = new Segmento();

		if (getClientSelectLaborableSegmentResponse != null) {
			if (getClientSelectLaborableSegmentResponse.isIsSelect() != null && getClientSelectLaborableSegmentResponse.isIsSelect()) {
				segmento.setSelect(true);
			} else if (getClientSelectLaborableSegmentResponse.getCuadrante() != null
					&& CUADRANTE_C1.equalsIgnoreCase(getClientSelectLaborableSegmentResponse.getCuadrante().getValue())) {
				segmento.setDuo(true);
				segmento.setPyme(false);
			} else if (getClientSelectLaborableSegmentResponse.getCuadrante() != null
					&& (CUADRANTE_P1.equalsIgnoreCase(getClientSelectLaborableSegmentResponse.getCuadrante().getValue())
							|| CUADRANTE_P2.equalsIgnoreCase(getClientSelectLaborableSegmentResponse.getCuadrante().getValue()))) {
				segmento.setDuo(false);
				segmento.setPyme(true);
			} else if (getClientSelectLaborableSegmentResponse.isIsUniv() != null && getClientSelectLaborableSegmentResponse.isIsUniv()) {
				segmento.setUniversidad(true);
			} else {
				segmento.setDuo(false);
				segmento.setPyme(false);
				segmento.setSelect(false);
				segmento.setUniversidad(false);
			}
			try {

				segmento.setOperadorEjecutivo(obtenerDatosEjectivo(getClientSelectLaborableSegmentResponse));
                OperadorEjecutivo ejecutivoAsignado = new OperadorEjecutivo();
                ejecutivoAsignado.setIdEjecutivo(getClientSelectLaborableSegmentResponse.getIdEjecutivo().getValue());
                ejecutivoAsignado.setNombreEjecutivo(getClientSelectLaborableSegmentResponse.getEjecutivo().getValue());
                sesionParametros.setOperadorEjecutivo(ejecutivoAsignado);
                if ("S".equals(getClientSelectLaborableSegmentResponse.getMarcaSelectOnline().getValue())) {
                    segmento.setOperadorEjecutivo(obtenerDatosEjectivo(getClientSelectLaborableSegmentResponse));
                }

			} catch (Exception e) {
				OperadorEjecutivo operadorEjecutivo = new OperadorEjecutivo();
				operadorEjecutivo.setErrorOperador(true);
				segmento.setOperadorEjecutivo(operadorEjecutivo);
			}
			segmento.setCuadrante(getClientSelectLaborableSegmentResponse.getCuadrante().getValue());
			segmento.setPesubseg(getClientSelectLaborableSegmentResponse.getPesubseg().getValue());
			segmento.setEjecutivo(getClientSelectLaborableSegmentResponse.getEjecutivo().getValue());
			segmento.setPesucadm(getClientSelectLaborableSegmentResponse.getPesucadm().getValue());
		} else {
			segmento.setDuo(false);
			segmento.setPyme(false);
			segmento.setSelect(false);
			segmento.setUniversidad(false);
			segmento.setEjecutivo("");
			segmento.setPesucadm("");
			segmento.setCuadrante("");
			segmento.setPesubseg("");
		}

		return segmento;
	}

	/**
	 * Obtener datos ejectivo.
	 *
	 * @param getClientSelectSegmentResponse the get client select segment response
	 * @return the operador ejecutivo
	 */
	private OperadorEjecutivo obtenerDatosEjectivo(GetClientSegmentSelectLaborableDataResponse getClientSelectLaborableSegmentResponse) {
		OperadorEjecutivo operadorEjecutivo = null;
		if ("S".equals(getClientSelectLaborableSegmentResponse.getMarcaSelectOnline().getValue())) {
			String horarioEjectivo = getClientSelectLaborableSegmentResponse.getExecutiveAttentionSchedule().getValue();
			String horarioEjectivoRango = getClientSelectLaborableSegmentResponse.getSelectOnlineAttentionSchedule().getValue();
			operadorEjecutivo = new OperadorEjecutivo();
			operadorEjecutivo
			        .setNombreEjecutivo("¡Hola! Soy <span>" + ISBANStringUtils.formateoStringPrimeraLetraMayuscula(
			        		getClientSelectLaborableSegmentResponse.getEjecutivo().getValue()) + "</span>");
			if (StringUtils.isNotBlank(horarioEjectivo)) {
				String[] horario = horarioEjectivo.split(",");
				operadorEjecutivo.setHorarioAtencionInicio(horario[0]);
				operadorEjecutivo.setHorarioAtencionFin(horario[1]);
			}
			
			Boolean diaLaborable = "S".equals(getClientSelectLaborableSegmentResponse.getDiaLaborable().getValue());
			
			if (StringUtils.isNotBlank(horarioEjectivo)) {				
				operadorEjecutivo.setDiaLaborable(diaLaborable);
			}
			

			if ("P".equals(getClientSelectLaborableSegmentResponse.getExecutiveLoan().getValue())) {
				operadorEjecutivo.setMensajeEjecutivoTransitorio(
				        legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.CODIGO_MENSAJE_EJECUTIVO_TRANSITORIO));
			} else {
				operadorEjecutivo.setMensajeEjecutivoTransitorio(null);
			}
			
			try {
				if (!ISBANStringUtils.isEmptyOrNull(new String(
				        Base64.getEncoder().encodeToString(getClientSelectLaborableSegmentResponse.getPicture().getValue())))) {
					operadorEjecutivo.setFotoEjecutivo("data:image/png;base64," + new String(
					        Base64.getEncoder().encodeToString(getClientSelectLaborableSegmentResponse.getPicture().getValue())));
				} else {
					operadorEjecutivo.setFotoEjecutivo("");
				}
			} catch (Exception e) {
				operadorEjecutivo.setFotoEjecutivo("");
			}
		

			if (!ISBANStringUtils.isEmptyOrNull(getClientSelectLaborableSegmentResponse.getCelularEjecutivo().getValue())) {
				operadorEjecutivo.setWhatsappEjecutivo(
				        obtenerFormatoWhatsapp(getClientSelectLaborableSegmentResponse.getCelularEjecutivo().getValue()));

			} else {
				operadorEjecutivo.setWhatsappEjecutivo("");
			}

			if (!ISBANStringUtils.isEmptyOrNull(getClientSelectLaborableSegmentResponse.getTelefonoMasivo().getValue())) {
				operadorEjecutivo.setTelefonoMasivo(
				        obtenerDatosSeparadoPorComa(getClientSelectLaborableSegmentResponse.getTelefonoMasivo().getValue()));
			} else {
				operadorEjecutivo.setTelefonoMasivo("");
			}

			if (!ISBANStringUtils.isEmptyOrNull(getClientSelectLaborableSegmentResponse.getMailEjecutivo().getValue())) {
				operadorEjecutivo.setEmail(
				        obtenerDatosSeparadoPorComa(getClientSelectLaborableSegmentResponse.getMailEjecutivo().getValue()));
			} else {
				operadorEjecutivo.setEmail("");
			}
			if (!ISBANStringUtils.isEmptyOrNull(horarioEjectivoRango)) {
				String[] horario = horarioEjectivoRango.split(",");
				operadorEjecutivo.setHorarioAtencion(obtenerMensajeLegalesConParametros(
				        CodigoMensajeConstantes.CODIGO_MODELO_HORARIOS_OPERADOR_EJECUTIVO, horario[0], horario[1]));
			} else {
				operadorEjecutivo.setHorarioAtencion("");
			}
			operadorEjecutivo.setErrorOperador(false);
			operadorEjecutivo.setIdEjecutivo(getClientSelectLaborableSegmentResponse.getIdEjecutivo().getValue());
		}

		return operadorEjecutivo;
	}

	/**
	 * Obtener formato whatsapp.
	 *
	 * @param value the value
	 * @return the string
	 */
	private String obtenerFormatoWhatsapp(String value) {
		String numero = obtenerDatosSeparadoPorComa(value);
		numero = "+ 54 " + numero.replace(')', ' ').replace("(9", "9 ");
		return numero;
	}

	/**
	 * Obtener datos separado por coma.
	 *
	 * @param datos the datos
	 * @return the string
	 */
	private String obtenerDatosSeparadoPorComa(String datos) {
		String datoARetornar = null;
		if (!ISBANStringUtils.isEmptyOrNull(datos)) {
			if (datos.contains(",")) {
				datoARetornar = datos.split(",")[0];
			} else {
				datoARetornar = datos;
			}
		}
		return datoARetornar;
	}

	/**
	 * Obtener mensaje legales con parametros.
	 *
	 * @param codLegal   the cod legal
	 * @param parametros the parametros
	 * @return the string
	 */
	private String obtenerMensajeLegalesConParametros(String codLegal, Object... parametros) {
		return MessageFormat.format(legalBO.obtenerLegalesPorCodigo(codLegal), parametros);
	}

}
