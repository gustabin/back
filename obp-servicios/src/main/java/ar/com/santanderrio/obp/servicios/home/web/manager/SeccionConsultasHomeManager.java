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
public interface SeccionConsultasHomeManager {

	/**
	 * Grabar Estadisticas Acceso cuentas.
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> notificarAccesoCuentas();
	
	/**
	 * Grabar Estadisticas Acceso cuentas banca privada.
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> notificarAccesoCuentasBancaPrivada();
	
	/**
	 * Grabar Estadisticas Acceso Tarjetas.
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> notificarAccesoTarjetas();

	/**
	 * Grabar Estadisticas Acceso Prestamos.
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
	 * Grabar Estadisticas Acceso Monedero.
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> notificarAccesoMonedero();

	/**
	 * Grabar estadistica acceso comprobantes.
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> notificarAccesoComprobantes();

	/**
	 * Notificar acceso consulta resumen impositivo.
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> notificarAccesoConsultaResumenImpositivo();

	
}
