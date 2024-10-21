/*
 * 
 */
package ar.com.santanderrio.obp.servicios.obe.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;

/**
 * The Interface ObeManager.
 */
public interface ObeManager {

	/**
	 * Obtener token encriptado Y logout.
	 *
	 * @param mc
	 *            the mc
	 * @return the respuesta
	 */
	@SuppressWarnings("rawtypes")
	Respuesta obtenerTokenEncriptado(org.apache.cxf.jaxrs.ext.MessageContext mc);
}
