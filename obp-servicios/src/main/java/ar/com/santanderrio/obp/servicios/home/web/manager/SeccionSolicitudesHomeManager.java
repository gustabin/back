/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.home.web.view.SeccionView;

/**
 * The Interface TopbarHomeManager.
 */
public interface SeccionSolicitudesHomeManager {

	/**
	 * Grabar Estadisticas Acceso a Prestamos.
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> notificarAccesoPrestamos();

	/**
	 * Valida las opciones correspondientes a la seccion.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the seccion view
	 */
	SeccionView obtenerSeccion(Cliente cliente);

	/**
	 * Notificar acceso solicitudes tarjeta monedero.
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> notificarAccesoSolicitudesTarjetaMonedero();

	/**
	 * Notificar acceso solicitudes tarjeta credito adicional.
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> notificarAccesoSolicitudesTarjetaCreditoAdicional();

	/**
	 * Notifica acceso a Billetera Virtual.
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> notificarAccesoTransaccionesBilleteraVirtual();

}
