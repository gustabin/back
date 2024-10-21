/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.dao;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;

/**
 * The Interface ReporteCBUCuentaDAO.
 */
public interface ReporteCBUCuentaDAO {

	/**
	 * Obtener reporte cbu cuenta.
	 *
	 * @param cliente
	 *            the cliente
	 * @param cuenta
	 *            the cuenta
	 * @param alias
	 *            the alias
	 * @return the reporte
	 */
	Reporte obtenerReporteCBUCuenta(Cliente cliente, AbstractCuenta cuenta, String alias);

}
