/*
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.entities.OperacionClienteVentaEntity;
import ar.com.santanderrio.obp.servicios.compraventa.entities.OperacionCompraVentaDatosEntrada;

/**
 * The Interface OperacionClienteVendeDAO.
 */
public interface OperacionClienteVendeDAO {

	/**
	 * Operacion cliente vende.
	 *
	 * @param cliente
	 *            the cliente
	 * @param operacionVentaDatosEntrada
	 *            the operacion venta datos entrada
	 * @param isBancaPrivada
	 *            the is banca privada
	 * @return the operacion cliente venta entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	OperacionClienteVentaEntity operacionClienteVende(Cliente cliente,
			OperacionCompraVentaDatosEntrada operacionVentaDatosEntrada, Boolean isBancaPrivada) throws DAOException;
}
