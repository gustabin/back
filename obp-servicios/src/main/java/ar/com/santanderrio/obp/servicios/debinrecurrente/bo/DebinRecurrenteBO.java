package ar.com.santanderrio.obp.servicios.debinrecurrente.bo;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dto.BuyerRecurrenceListRequestDTO;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dto.RecurrenceDTO;
import ar.com.santanderrio.obp.servicios.debinrecurrente.manager.ObtenerRecurrenciasView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.AccionRecurrenciaResponseView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.CrearRecurrenciaView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.DatosComprobanteDebinRecurrente;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.RecurrenciaView;

/**
 * The Interface DebinRecurrenteBO.
 */
public interface DebinRecurrenteBO {

	/**
	 * Crear recurrencia.
	 *
	 * @param recurrencia the recurrencia
	 * @return the respuesta
	 */
	Respuesta<RecurrenceDTO> crearRecurrencia(CrearRecurrenciaView recurrencia);

	/**
	 * Pausar recurrencia.
	 ** @param recurrencia the recurrencia
	 * @return the respuesta
	 */
	Respuesta<AccionRecurrenciaResponseView> pausarRecurrencia(RecurrenciaView recurrencia);

	/**
	 * Desubscribir recurrencia.
	 *
	 * @param idRecurrencia the id recurrencia
	 * @param recurrencia the recurrencia
	 * @return the respuesta
	 */
	Respuesta<AccionRecurrenciaResponseView> desubscribirRecurrencia(RecurrenciaView recurrencia);
	
	/**
	 * Reactivar recurrencia.
	 *
	 * @param idRecurrencia the id recurrencia
	 * @param recurrencia the recurrencia
	 * @return the respuesta
	 */
	Respuesta<AccionRecurrenciaResponseView> reactivarRecurrencia(RecurrenciaView recurrencia);

	/**
	 * desconocerPago recurrencia.
	 *
	 * @param recurrencia y cliente
	 * @param recurrencia the recurrencia
	 * @return the respuesta
	 */
	Respuesta<Void> desconocerPago(Cliente cliente, String debinId);

	/**
	 * Obtener recurrencias por comprador.
	 *
	 * @param cliente the cliente
	 * @param view the view
	 * @return the respuesta
	 */
	Respuesta<BuyerRecurrenceListRequestDTO> obtenerRecurrenciasPorComprador(Cliente cliente, ObtenerRecurrenciasView view);

	/**
	 * Generar comprobante PDF.
	 *
	 * @param datosComprobante the datos comprobante
	 * @return the respuesta
	 */
	Respuesta<Reporte> generarComprobantePDF(DatosComprobanteDebinRecurrente datosComprobante);
	
}
