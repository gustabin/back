/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.legal.bo.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.legal.dao.LegalDAO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class LegalBOImpl.
 */
@Component
public class LegalBOImpl implements LegalBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(LegalBOImpl.class);

	/** The legal dao. */
	@Autowired
	private LegalDAO legalDAO;

	/** generador de respuestas. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO#buscarLegal(java
	 * .lang.String)
	 */
	/*
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO#buscarLegal(java
	 * .lang.String)
	 */
	@Override
	public Respuesta<String> buscarLegal(String codigoLegal) {
		try {
			String textoLegal = legalDAO.obtenerLegal(codigoLegal);
			return respuestaFactory.crearRespuestaOk(textoLegal);
		} catch (DAOException e) {
			LOGGER.error("ERROR al buscar el legal {}.", codigoLegal, e);
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO, "");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO#
	 * obtenerTextoLegal(java.lang.String)
	 */
	/*
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO#
	 * obtenerTextoLegal(java.lang.String)
	 */
	@Override
	public String obtenerTextoLegal(String codigoLegal) {
		Respuesta<String> respuestaLegal = buscarLegal(codigoLegal);
		if (EstadoRespuesta.OK.equals(respuestaLegal.getEstadoRespuesta())) {
			return respuestaLegal.getRespuesta();
		} else {
			return respuestaLegal.getItemsMensajeRespuesta().get(0).getMensaje();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO#limpiarLegales()
	 */
	/*
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO#limpiarLegales()
	 */
	@Override
	public Respuesta<Boolean> limpiarLegales() {
		legalDAO.limpiarLegales();
		return respuestaFactory.crearRespuestaOk(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.legal.dao.LegalDAO#
	 * obtenerLegalPorCodigo(java.lang.String)
	 */
	@Override
	public String obtenerLegal(final String codigo) throws DAOException {
		/**
		 * Pide el bean al contexto ya que para utilizar la cache hay que entrar
		 * por metodos publicos por ser un proxy. No ir directo ya que no
		 * devuelve el valor cacheado.
		 *
		 */
		String legal = ContextHolder.getContext().getBean(LegalDAO.class).obtenerLegales().get(codigo);
		if (StringUtils.isEmpty(legal)) {
			LOGGER.info("El codigo {} de legal consultado no es valido.", codigo);
			throw new DAOException("El codigo consultado no es valido.");
		}
		return legal;
	}

	/**
	 * Obtener legales por codigo.
	 *
	 * @param codLegal
	 *            the cod legal
	 * @return the string
	 */
	@Override
	public String obtenerLegalesPorCodigo(String codLegal) {
		try {
			return legalDAO.obtenerLegal(codLegal);
		} catch (DAOException e) {
			LOGGER.error("No se pudo obtener el legal...", e);
			return null;
		}
	}
}
