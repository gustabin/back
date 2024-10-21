/*
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.entities.OperacionClienteCompraEntity;
import ar.com.santanderrio.obp.servicios.compraventa.entities.OperacionCompraVentaDatosEntrada;

/**
 * The Interface OperacionClienteCompraDAO.
 */
public interface OperacionClienteCompraDAO {

	/**
	 * Operacion cliente compra.
	 *
	 * @param cliente
	 *            the cliente
	 * @param operacionCompraDatosEntrada
	 *            the operacion compra datos entrada
	 * @param isCuentaPrivada
	 *            the is cuenta privada
	 * @return the operacion cliente compra DTO the operacion cliente compra
	 *         entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	OperacionClienteCompraEntity operacionClienteCompra(Cliente cliente,
			OperacionCompraVentaDatosEntrada operacionCompraDatosEntrada, Boolean isCuentaPrivada) throws DAOException;
}