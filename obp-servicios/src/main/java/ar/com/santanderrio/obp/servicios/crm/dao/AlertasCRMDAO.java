/*
 * 
 */
package ar.com.santanderrio.obp.servicios.crm.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.generated.webservices.crm.ResponseGrupal;

/**
 * The Interface AlertasCRMDAO.
 */
public interface AlertasCRMDAO {

	/**
	 * Consultar CRM.
	 *
	 * @param nup
	 *            the nup
	 * @return the response grupal
	 * @throws DAOException
	 *             the DAO exception
	 */
	ResponseGrupal consultarCRM(String nup) throws DAOException;

}
