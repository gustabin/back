/*
 * 
 */
package ar.com.santanderrio.obp.servicios.chat.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;

/**
 * The Interface ChatClientesHabilitadosDAO.
 *
 * @author Federico_Puente
 */
public interface ChatClientesHabilitadosDAO {
	
	/**
	 * Obtener lista clientes habilitados.
	 *
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	public List<String> obtenerListaClientesHabilitados()  throws DAOException;
}
