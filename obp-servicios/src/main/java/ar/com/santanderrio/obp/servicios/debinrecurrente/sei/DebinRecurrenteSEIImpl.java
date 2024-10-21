package ar.com.santanderrio.obp.servicios.debinrecurrente.sei;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.bo.DebinRecurrenteBOImpl;
import ar.com.santanderrio.obp.servicios.debinrecurrente.manager.DebinRecurrenteManager;
import ar.com.santanderrio.obp.servicios.debinrecurrente.manager.ObtenerRecurrenciasView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.*;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * The Class DebinRecurrenteSEIImpl.
 */
@Component
public class DebinRecurrenteSEIImpl implements DebinRecurrenteSEI {

	/** The debin recurrente manager. */
	@Autowired
	private DebinRecurrenteManager debinRecurrenteManager;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	@Value("${DEBINREC.DESCONOCER.PAGO}")
	private Boolean desconocerPago;

	private static final Logger LOGGER = LoggerFactory.getLogger(DebinRecurrenteBOImpl.class);

	@Autowired
	private RespuestaFactory respuestaFactory;

	/**
	 * Crear recurrencia.
	 *
	 * @param recurrencia the recurrencia
	 * @return the respuesta
	 */
	@Override
	public Respuesta<CrearRecurrenciaView> crearRecurrencia(CrearRecurrenciaView recurrencia) {
		return debinRecurrenteManager.crearRecurrencia(recurrencia);
	}

	/**
	 * Obtener info cliente config.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<DatosConfigClienteDebinRecurrenteView> obtenerInfoClienteConfig() {
		return debinRecurrenteManager.obtenerInfoClienteConfig();
	}

	/**
	 * Indica si activar o no la funcionalidad dependiendo si el cliente se encuentra
	 * dentro de la lista de nups habilitados o no.
	 * @return true si el cliente está dentro de la lista de nups habilitados; caso contrario, false.
	 */
	@Override
	public Respuesta<ActivarDebinRecurrenteView> activar() {
		return debinRecurrenteManager.activar();
	}

	/**
	 * Pausar recurrencia.
	 *
	 * @param recurrencia the recurrencia
	 * @return the respuesta
	 */
	@Override
	public Respuesta<AccionRecurrenciaResponseView> pausarRecurrencia(RecurrenciaView recurrencia) {
		return debinRecurrenteManager.pausarRecurrencia(recurrencia);
	}

	/**
	 * Reanudar recurrencia.
	 *
	 * @param recurrencia the recurrencia
	 * @return the respuesta
	 */
	@Override
	public Respuesta<AccionRecurrenciaResponseView> reanudarRecurrencia(RecurrenciaView recurrencia) {
		return debinRecurrenteManager.reanudarRecurrencia(recurrencia);
	}

	/**
	 * Desubscribir recurrencia.
	 *
	 * @param recurrencia the recurrencia
	 * @return the respuesta
	 */
	@Override
	public Respuesta<AccionRecurrenciaResponseView> desubscribirRecurrencia(RecurrenciaView recurrencia) {
		return debinRecurrenteManager.desubscribirRecurrencia(recurrencia);
	}

	/**
	 * Obtener recurrencias por comprador.
	 *
	 * @param view the view
	 * @return the respuesta
	 */
	@Override
	public Respuesta<RecurrenciasView> obtenerRecurrenciasPorComprador(ObtenerRecurrenciasView view) {
		return debinRecurrenteManager.obtenerRecurrenciasPorComprador(view);
	}

	/**
	 * Grabar estadistica detalle recurrencia.
	 */
	@Override
	public void grabarEstadisticaDetalleRecurrencia() {
		estadisticaManager.add(EstadisticasConstants.CONSULTA_DETALLE_RECURRENCIA, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	@Override
	public Respuesta<Void> desconocerPago(DebinView debin){
		if (desconocerPago){
			return debinRecurrenteManager.desconocerPago(debin);
		}else{
			LOGGER.error("El Endpoint desconocerPago se encuentra deshabilitado");
			return this.respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_GENERICO, CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
		}
	}

	/**
	 * Generar comprobante PDF.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ReporteView> generarComprobantePDF() {
		return debinRecurrenteManager.generarComprobantePDF();
	}

	/**
	 * Grabar estadistica ver comprobante adhesion.
	 */
	@Override
	public void grabarEstadisticaVerComprobanteAdhesion() {
		estadisticaManager.add(EstadisticasConstants.VER_COMPROBANTE_ADHESION_DEBIN_RECURRENTE, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

}
