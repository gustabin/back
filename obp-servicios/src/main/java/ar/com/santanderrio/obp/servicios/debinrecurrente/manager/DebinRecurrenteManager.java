package ar.com.santanderrio.obp.servicios.debinrecurrente.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.*;

/**
 * The Interface DebinRecurrenteManager.
 */
public interface DebinRecurrenteManager {

	/**
	 * Crear recurrencia.
	 *
	 * @param recurrencia the recurrencia
	 * @return the respuesta
	 */
	Respuesta<CrearRecurrenciaView> crearRecurrencia(CrearRecurrenciaView recurrencia);

	/**
	 * Obtener info cliente config.
	 *
	 * @return the respuesta
	 */
	Respuesta<DatosConfigClienteDebinRecurrenteView> obtenerInfoClienteConfig();

	/**
	 * Indica si activar o no la funcionalidad dependiendo si el cliente se encuentra
	 * dentro de la lista de nups habilitados o no.
	 * @return true si el cliente está dentro de la lista de nups habilitados; caso contrario, false.
	 */
	Respuesta<ActivarDebinRecurrenteView> activar();

	/**
	 * Pausar recurrencia.
	 *
	 * @param recurrencia the recurrencia
	 * @return the respuesta
	 */
	Respuesta<AccionRecurrenciaResponseView> pausarRecurrencia(RecurrenciaView recurrencia);

	/**
	 * Reanudar recurrencia.
	 *
	 * @param recurrencia the recurrencia
	 * @return the respuesta
	 */
	Respuesta<AccionRecurrenciaResponseView> reanudarRecurrencia(RecurrenciaView recurrencia);

	/**
	 * Desubscribir recurrencia.
	 *
	 * @param recurrencia the recurrencia
	 * @return the respuesta
	 */
	Respuesta<AccionRecurrenciaResponseView> desubscribirRecurrencia(RecurrenciaView recurrencia);

	/**
	 * Obtener recurrencias por comprador.
	 *
	 * @param cuenta the cuenta
	 * @return the respuesta
	 */
	Respuesta<RecurrenciasView> obtenerRecurrenciasPorComprador(ObtenerRecurrenciasView cuenta);

	Respuesta<Void> desconocerPago(DebinView recurrencia);

	/**
	 * Generar comprobante PDF.
	 *
	 * @return the respuesta
	 */
	Respuesta<ReporteView> generarComprobantePDF();
}
