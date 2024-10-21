/*
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.GrupoAfinidadClienteOutEntity;

/**
 * The Interface AdvantageDAO.
 */
public interface AdvantageDAO {

	/**
	 * Consultar usuario es superclub.
	 *
	 * @param cliente
	 *            the cliente
	 * @return true, if successful
	 * @throws DAOException
	 *             the DAO exception
	 */
	Respuesta<GrupoAfinidadClienteOutEntity> consultarUsuarioEsSuperclub(Cliente cliente) throws DAOException;

}
