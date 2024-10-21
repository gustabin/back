/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.sucursales.bo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.sucursales.bo.ConsultarSucursalesBO;
import ar.com.santanderrio.obp.servicios.comun.sucursales.dao.ConsultarSucursalesDAO;
import ar.com.santanderrio.obp.servicios.comun.sucursales.entities.Sucursal;

/**
 * The Class ConsultarSucursalesBOImpl.
 *
 * @author B039542
 */
@Component
public class ConsultarSucursalesBOImpl implements ConsultarSucursalesBO {

	/** The Constant NO_SE_ENCONTRARON_RESULTADOS_MSG. */
	private static final String NO_SE_ENCONTRARON_RESULTADOS_MSG = "No se encontraron resultados";

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsultarSucursalesBOImpl.class);

	/** The consultar sucursales dao. */
	@Autowired
	private ConsultarSucursalesDAO consultarSucursalesDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.comun.sucursales.bo.ConsultarSucursalesBO#
	 * consultarSucursalPorId(java.lang.String)
	 */

	@Override
	public Respuesta<Sucursal> consultarSucursalPorId(String id) {
		Sucursal sucursal = null;
		Respuesta<Sucursal> respuesta = new Respuesta<Sucursal>();
		try {
			sucursal = consultarSucursalesDAO.consultarSucursalPorId(id);
			if (sucursal == null) {
				respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
				respuesta.setRespuestaVacia(true);
				ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta(NO_SE_ENCONTRARON_RESULTADOS_MSG);
				respuesta.add(itemMensajeRespuesta);
			} else {
				respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
				respuesta.setRespuesta(sucursal);
				respuesta.setRespuestaVacia(false);
			}
		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
			respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
			respuesta.setRespuestaVacia(true);
			ItemMensajeRespuesta mensaje = new ItemMensajeRespuesta(e.getMessage());
			respuesta.add(mensaje);
		}
		return respuesta;
	}

}
