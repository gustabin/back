/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagocompras.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.AlianzaEntity;

/**
 * The Interface AlianzasDAO.
 */
public interface AlianzasDAO {
    
    /**
	 * Consultar alianzas.
	 *
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
    List<AlianzaEntity> consultarAlianzas() throws DAOException;

}
