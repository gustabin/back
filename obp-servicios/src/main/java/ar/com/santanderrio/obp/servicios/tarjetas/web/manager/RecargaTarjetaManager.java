/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.manager;

import javax.servlet.http.HttpServletRequest;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.BajaAdhesionTarjConfirmacion;
import ar.com.santanderrio.obp.servicios.tarjetas.view.ComprobanteFeedbackView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.Reintentar;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ComprobanteRecargaTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.RecargaTarjetaInfoView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.RecargaTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.TarjetasView;

/**
 * The Interface RecargaTarjetaManager.
 */
public interface RecargaTarjetaManager {

    /**
	 * Recargar tarjeta.
	 *
	 * @param request
	 *            the request
	 * @param recargaTarjetaView
	 *            the recarga tarjeta view
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
    Respuesta<ComprobanteRecargaTarjetaView> recargarTarjeta(HttpServletRequest request,
            ComprobanteRecargaTarjetaView recargaTarjetaView, Cliente cliente);

    /**
	 * Programar recargar tarjeta.
	 *
	 * @param request
	 *            the request
	 * @param recargaTarjetaView
	 *            the recarga tarjeta view
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
    Respuesta<ComprobanteRecargaTarjetaView> programarRecargarTarjeta(HttpServletRequest request,
            ComprobanteRecargaTarjetaView recargaTarjetaView, Cliente cliente);

    /**
	 * Baja recargar tarjeta.
	 *
	 * @param request
	 *            the request
	 * @param recargaTarjetaView
	 *            the recarga tarjeta view
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
    Respuesta<ComprobanteFeedbackView> bajaRecargarTarjeta(HttpServletRequest request,
            RecargaTarjetaView recargaTarjetaView, Cliente cliente);

    /**
	 * Stop debit tarjeta recargable.
	 *
	 * @param request
	 *            the request
	 * @param recargaTarjetaView
	 *            the recarga tarjeta view
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
    Respuesta<ComprobanteFeedbackView> stopDebitTarjetaRecargable(HttpServletRequest request,
            RecargaTarjetaView recargaTarjetaView, Cliente cliente);

    /**
     * Generar comprobante recarga.
     *
     * @return the respuesta
     */
    Respuesta<ReporteView> generarComprobanteRecarga();

    /**
     * Gets the datos tarjeta recargable.
     *
     * @param cliente
     *            the cliente
     * @param reintentar
     *            the reintentar
     * @return the datos tarjeta recargable
     */
    Respuesta<RecargaTarjetaInfoView> getDatosTarjetaRecargable(Cliente cliente, Reintentar reintentar);

    /**
     * Obtener datos baja adhesion tarjeta.
     *
     * @param nroTarjeta
     *            the nro tarjeta
     * @param cliente
     *            the cliente
     * @return the respuesta
     */
    Respuesta<BajaAdhesionTarjConfirmacion> obtenerDatosBajaAdhesionTarjeta(String nroTarjeta, Cliente cliente);

    /**
     * Tarjetas calendario.
     *
     * @return the respuesta
     */
    Respuesta<TarjetasView> tarjetasCalendario();

    /**
	 * Modificar programar recargar tarjeta.
	 *
	 * @param request
	 *            the request
	 * @param recargaTarjetaView
	 *            the recarga tarjeta view
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
    Respuesta<ComprobanteRecargaTarjetaView> modificarProgramarRecargarTarjeta(HttpServletRequest request,
            RecargaTarjetaView recargaTarjetaView, Cliente cliente);

    /**
     * Vaciar desafio.
     */
    void vaciarDesafio();

	/**
	 * Obtiene mensaje tooltip informativo de agendamiento de recarga de tarjeta
	 * @return
	 */
	Respuesta<String> tooltipAgendamientoRecarga();
}
