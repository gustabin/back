/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.EliminarOperacionEntity;

/**
 * The Interface EliminarOperacionDAO.
 */
public interface EliminarOperacionDAO {
	
	/**
	 * Eliminar operacion.
	 *
	 * @param cliente
	 *            the cliente
	 * @param nroTramite
	 *            the nro tramite
	 * @return the eliminar operacion entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	EliminarOperacionEntity eliminarOperacion(Cliente cliente, String nroTramite) throws DAOException;
}
