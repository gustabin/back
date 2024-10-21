/*
 * 
 */
package ar.com.santanderrio.obp.servicios.aadvantage.dao;


import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.aadvantage.entities.SolicitudMillasAadvantageInEntity;
import ar.com.santanderrio.obp.servicios.aadvantage.entities.SolicitudMillasAadvantageOutEntity;

/**
 * The Interface AadvantageDAO.
 */
public interface AadvantageDAO {

	/**
	 * Consultar millas.
	 *
	 * @param inEntity
	 *            the in entity
	 * @return the solicitud millas aadvantage out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	SolicitudMillasAadvantageOutEntity consultarMillas(SolicitudMillasAadvantageInEntity inEntity) throws DAOException;
	
}
