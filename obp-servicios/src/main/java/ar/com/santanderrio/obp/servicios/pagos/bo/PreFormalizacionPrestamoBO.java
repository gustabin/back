/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.pagos.entities.PreFormalizacion;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;

/**
 * The Interface PreFormalizacionPrestamoBO.
 */
public interface PreFormalizacionPrestamoBO {

	/**
	 * Obtener pre formalizacion.
	 *
	 * @param prestamo
	 *            the prestamo
	 * @return the respuesta
	 */
	Respuesta<PreFormalizacion> obtenerPreFormalizacion(Prestamo prestamo);

	/**
	 * Obtener pre formalizacion.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
	Respuesta<PreFormalizacion> obtenerPreFormalizacion(Cuenta cuenta);

	/**
	 * Obtener pre formalizacion.
	 *
	 * @param cliente
	 *            the cliente
	 * @param sucursal
	 *            the sucursal
	 * @param numeroDePrestamo
	 *            the numero de prestamo
	 * @return the pre formalizacion
	 */
	PreFormalizacion obtenerPreFormalizacion(Cliente cliente, String sucursal, String numeroDePrestamo);
}
