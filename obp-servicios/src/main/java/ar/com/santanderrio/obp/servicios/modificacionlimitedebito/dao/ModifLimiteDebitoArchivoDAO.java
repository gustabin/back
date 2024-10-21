/*
 * 
 */
package ar.com.santanderrio.obp.servicios.modificacionlimitedebito.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.entities.LimiteDebito;

/**
 * The Interface ModifLimiteDebitoArchivoDAO.
 */
public interface ModifLimiteDebitoArchivoDAO {

	/**
	 * Cargar limites debito.
	 *
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<LimiteDebito> cargarLimitesDebito() throws DAOException;

}
