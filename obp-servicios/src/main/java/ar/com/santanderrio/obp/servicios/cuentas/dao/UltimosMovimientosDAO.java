/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaUltimosMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.entities.UltimosMovimientosDTO;

/**
 * The Interface UltimosMovimientosDAO.
 */
public interface UltimosMovimientosDAO {

	/**
	 * Consultar extracto ultimos movimientos.
	 *
	 * @param consultaUltimosMovimientos
	 *            the consulta ultimos movimientos
	 * @return the ultimos movimientos
	 * @throws DAOException
	 *             the DAO exception
	 */
	UltimosMovimientosDTO consultarExtractoUltimosMovimientos(ConsultaUltimosMovimientos consultaUltimosMovimientos)
			throws DAOException;

}
