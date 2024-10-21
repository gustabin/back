/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaCerrada;

/**
 * The Interface CuentaCerradaBO.
 */
public interface CuentaCerradaBO {

	/**
	 * Obtener cuentas cerradas.
	 *
	 * @param resumenCliente
	 *            the resumen cliente
	 * @return the respuesta
	 */
	Respuesta<List<CuentaCerrada>> obtenerCuentasCerradas(Cliente resumenCliente);

}