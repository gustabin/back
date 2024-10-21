/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.DetalleOperacionesPrecargadasEntity;

/**
 * The Interface DetalleOperacionesPrecargadasDAO.
 */
public interface DetalleOperacionesPrecargadasDAO {

	/**
	 * Obtener detalle operaciones precargadas.
	 *
	 * @param cliente
	 *            the cliente
	 * @param nroTramite
	 *            the nro tramite
	 * @return the detalle operaciones precargadas entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	DetalleOperacionesPrecargadasEntity obtenerDetalleOperacionesPrecargadas(Cliente cliente, String nroTramite)
			throws DAOException;
}
