/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.BotonPanicoCompraventaException;
import ar.com.santanderrio.obp.servicios.compraventa.entities.ConsultaCotizacionEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;

/**
 * The Interface CotizacionDolaresDAO.
 *
 * @author sabrina.cis
 */
public interface CotizacionDolaresDAO {

	/**
	 * Obtener cotizacion.
	 *
	 * @param cliente
	 *            the cliente
	 * @param cuentaOrigen
	 *            the cuenta origen
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @param canalBancaPrivada 
	 * @return the consulta cotizacion
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BotonPanicoCompraventaException 
	 */
	ConsultaCotizacionEntity obtenerCotizacion(Cliente cliente, Cuenta cuentaOrigen, String tipoOperacion, Boolean canalBancaPrivada)
			throws DAOException, BotonPanicoCompraventaException;

}
