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
public interface SeccionTransaccionesHomeManager {

    /**
     * Grabar Estadisticas Acceso Calendario de pagos.
     *
     * @return the respuesta
     */
    Respuesta<Void> notificarAccesoCalendario();

    /**
     * Grabar Estadisticas Acceso Calendario de pagos.
     *
     * @return the respuesta
     */
    Respuesta<Void> notificarAccesoTransferencias();

    /**
     * Valida las opciones correspondientes a la seccion.
     *
     * @param cliente
     *            the cliente
     * @return the seccion view
     */
    SeccionView obtenerSeccion(Cliente cliente);

    /**
     * Notificar acceso pago haberes.
     *
     * @return the respuesta
     */
    Respuesta<Void> notificarAccesoPagoHaberes();

    /**
	 * Notificar acceso consulta descuento cheques.
	 *
	 * @return the respuesta
	 */
    Respuesta<Void> notificarAccesoConsultaDescuentoCheques();
    
    /**
	 * Notificar acceso transacciones transferencias banca privada.
	 *
	 * @return the respuesta
	 */
    Respuesta<Void> notificarAccesoTransaccionesTransferenciasBancaPrivada();

	/**
	 * Notificar acceso transacciones orden pago exterior
	 * @return
	 */
	Respuesta<Void> notificarAccesoTransaccionesOrdenPagoExterior();

}
