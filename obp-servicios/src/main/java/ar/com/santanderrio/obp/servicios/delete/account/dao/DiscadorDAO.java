/*
 * 
 */
package ar.com.santanderrio.obp.servicios.delete.account.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.delete.account.web.dto.GuardarInfoClienteDto;

/**
 * @author A308529
 *
 */
public interface DiscadorDAO {

	
	/**
	 * Guadar info discador.
	 * 
	 * @param infoDto
	 * @throws DAOException
	 */
	public String guardarInfoDiscador(GuardarInfoClienteDto infoDto) throws DAOException;
	
	
}
