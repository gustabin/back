/*
 * 
 */
package ar.com.santanderrio.obp.servicios.terminoscondiciones.bo;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.TerminosCondiciones;

/**
 * The Interface TerminosCondicionesBO.
 */
public interface TerminosCondicionesBO {

	/**
	 * Cargar terminos condiciones.
	 *
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	Respuesta<TerminosCondiciones> cargarTerminosCondiciones() throws BusinessException;
}
