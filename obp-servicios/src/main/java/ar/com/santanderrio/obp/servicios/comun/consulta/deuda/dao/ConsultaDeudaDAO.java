/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.consulta.deuda.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.consulta.deuda.entity.ClasificacionDeuda;

/**
 * The Interface ConsultaDeudaDAO.
 */
public interface ConsultaDeudaDAO {

	/**
	 * Consulta deuda.
	 *
	 * @param nup
	 *            the nup
	 * @return the clasificacion deuda
	 * @throws DAOException
	 *             the DAO exception
	 */
	ClasificacionDeuda consultaDeuda(String nup) throws DAOException;

}
