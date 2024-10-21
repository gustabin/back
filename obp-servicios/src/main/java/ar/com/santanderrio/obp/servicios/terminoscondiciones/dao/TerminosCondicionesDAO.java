/*
 * 
 */
package ar.com.santanderrio.obp.servicios.terminoscondiciones.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.TerminosCondiciones;

/**
 * The Interface TerminosCondicionesDAO.
 */
public interface TerminosCondicionesDAO {

	/**
	 * Obtener terminos condiciones.
	 *
	 * @return the terminos condiciones
	 * @throws DAOException
	 *             the DAO exception
	 */
	TerminosCondiciones obtenerTerminosCondiciones() throws DAOException;

}
