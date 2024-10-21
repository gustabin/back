/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;

/**
 * The Interface TarjetasManager.
 *
 * @author sabrina.cis
 */
public interface TarjetasManager {

	/**
	 * Obtener id cuenta. Representacion de numero de cuenta Rio. Ejemplo:
	 * sucursal: 221, numeroCuenta: 2579806/6, IdentificacionCuenta:
	 * 221-579806/6. Desde los datos de una Cuenta:
	 * nroCuentaProducto=0000000025798066, nroSucursal=0221
	 *
	 * @param identificacionCuenta
	 *            the identificacion cuenta
	 * @return the identificacion cuenta
	 */
	IdentificacionCuenta obtenerIdCuenta(String identificacionCuenta);

	/**
	 * Crear log estadistica.
	 *
	 * @param codTransaccion
	 *            the cod transaccion
	 * @param codigoError
	 *            the codigo error
	 */
	void crearLogEstadistica(String codTransaccion, String codigoError);

	/**
	 * Armar respuesta error limites Y disponibles.
	 *
	 * @param <T>
	 *            the generic type
	 * @param descripcionTag
	 *            the descripcion tag
	 * @param tipoError
	 *            the tipo error
	 * @param codigoMensaje
	 *            the codigo mensaje
	 * @return the respuesta
	 */
	// TODO: usar respuestaFactory y eliminarlo de la clase. Accederlo mediante
	// super.getRespuestaFactory
	<T> Respuesta<T> crearRespuestaError(String descripcionTag, TipoError tipoError, String codigoMensaje);

}
