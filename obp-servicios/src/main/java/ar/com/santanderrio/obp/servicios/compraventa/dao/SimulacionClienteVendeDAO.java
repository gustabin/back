/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.entities.SimulacionClienteVendeEntity;
import ar.com.santanderrio.obp.servicios.compraventa.entities.SimulacionDatosEntrada;

/**
 * The Interface SimulacionClienteVendeDAO.
 *
 * @author sabrina.cis
 */
public interface SimulacionClienteVendeDAO {

	/**
	 * Obtener simulacion cliente vende.
	 *
	 * @param cliente
	 *            the cliente
	 * @param simulacionCompraVentaDolarData
	 *            the simulacion compra venta dolar data
	 * @param isBancaPrivada
	 *            the is banca privada
	 * @return the simulacion cliente vende DTO
	 * @throws DAOException
	 *             the DAO exception
	 */
	SimulacionClienteVendeEntity obtenerSimulacionClienteVende(Cliente cliente,
			SimulacionDatosEntrada simulacionCompraVentaDolarData, Boolean isBancaPrivada) throws DAOException;

}
