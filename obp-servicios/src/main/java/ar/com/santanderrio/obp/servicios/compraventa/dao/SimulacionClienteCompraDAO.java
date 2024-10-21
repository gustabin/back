/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.entities.SimulacionClienteCompraEntity;
import ar.com.santanderrio.obp.servicios.compraventa.entities.SimulacionDatosEntrada;

/**
 * The Interface SimulacionClienteCompraDAO.
 *
 * @author sabrina.cis
 */
public interface SimulacionClienteCompraDAO {

	/**
	 * Obtener simulacion cliente compra.
	 *
	 * @param cliente
	 *            the cliente
	 * @param simulacionCompraVentaDolarData
	 *            the simulacion compra venta dolar data
	 * @param isBancaPrivada
	 *            the is banca privada
	 * @return the simulacion cliente compra DTO
	 * @throws DAOException
	 *             the DAO exception
	 */
	SimulacionClienteCompraEntity obtenerSimulacionClienteCompra(Cliente cliente,
			SimulacionDatosEntrada simulacionCompraVentaDolarData, Boolean isBancaPrivada) throws DAOException;
}
