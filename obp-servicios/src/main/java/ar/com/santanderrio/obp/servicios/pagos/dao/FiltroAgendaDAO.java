/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;

/**
 * The Interface FiltroAgendaDAO.
 *
 * @author B039636
 */
public interface FiltroAgendaDAO {

	/**
	 * Existe empresa.
	 *
	 * @param cuit
	 *            the cuit
	 * @param nombreEmpresa
	 *            the nombre empresa
	 * @return true, if successful
	 * @throws DAOException
	 *             the DAO exception
	 */
	boolean existeEmpresa(String cuit, String nombreEmpresa) throws DAOException;

	/**
	 * Load.
	 */
	void load();

}
