/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.estadistica.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;

/**
 * Consumir los storeprocedures de Estadistica.
 * 
 * The Interface EstadisticaDAO.
 */
public interface EstadisticaDAO {

	/**
	 * Registrar una estadistica.
	 *
	 * @param estadistica
	 *            the estadistica
	 * @throws DAOException
	 *             the DAO exception
	 */
	void add(Estadistica estadistica) throws DAOException;
}
