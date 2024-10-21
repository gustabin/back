/*
 * 
 */
package ar.com.santanderrio.obp.servicios.terminoscondiciones.bo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.TerminosCondiciones;
import ar.com.santanderrio.obp.servicios.terminoscondiciones.bo.TerminosCondicionesBO;
import ar.com.santanderrio.obp.servicios.terminoscondiciones.dao.TerminosCondicionesDAO;

/**
 * The Class TerminosCondicionesBOImpl.
 */
@Component
public class TerminosCondicionesBOImpl implements TerminosCondicionesBO {

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TerminosCondicionesBOImpl.class);

	/** The terminos condiciones DAO. */
	@Autowired
	private TerminosCondicionesDAO terminosCondicionesDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.terminoscondiciones.bo.
	 * TerminosCondicionesBO#cargarTerminosCondiciones()
	 */
	@Override
	public Respuesta<TerminosCondiciones> cargarTerminosCondiciones() throws BusinessException {

		try {
			Respuesta<TerminosCondiciones> respuesta = new Respuesta<TerminosCondiciones>();

			respuesta.setRespuesta(terminosCondicionesDAO.obtenerTerminosCondiciones());
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);

			return respuesta;

		} catch (DAOException e) {

			LOGGER.error("Error por no cargar archivo de terminos y condiciones", e);
			Respuesta<TerminosCondiciones> respuesta = new Respuesta<TerminosCondiciones>();
			ItemMensajeRespuesta itemMensaje = new ItemMensajeRespuesta();
			itemMensaje.setMensaje("No se pudo cargar archivo de terminos y condiciones");
			itemMensaje.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
			respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
			respuesta.add(itemMensaje);
			respuesta.setRespuestaVacia(true);

			return respuesta;
		}

	}

}
