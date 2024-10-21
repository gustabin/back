/*
 * 
 */
package ar.com.santanderrio.obp.servicios.citi.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.citi.entities.RepuestaSPClienteCitiEntity;

/**
 * The Interface ClienteCitiDAO.
 */
public interface ClienteCitiDAO {

	/**
	 * Consultar citi cliente identificacion.
	 *
	 * @param nup
	 *            the nup
	 * @return the repuesta SP cliente citi entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	RepuestaSPClienteCitiEntity consultarCitiClienteIdentificacion(String nup) throws DAOException;

}
