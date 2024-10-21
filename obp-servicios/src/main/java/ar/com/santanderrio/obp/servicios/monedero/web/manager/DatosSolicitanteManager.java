/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.canalesautomaticos.entity.AltaCanalAutomaticoOutEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosAltaCanalesAutomaticosView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosMonederoConMovimientosYSaldoView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosSolicitanteCriterioView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosSolicitanteResponseView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosSolicitudTagAdicionalView;

/**
 * The Interface DatosSolicitanteManager.
 */
public interface DatosSolicitanteManager {

	/**
	 * Gets the datos del solicitante.
	 *
	 * @param datos
	 *            the datos
	 * @return the datos del solicitante
	 */
	Respuesta<DatosSolicitanteResponseView> getDatosDelSolicitante(DatosSolicitanteCriterioView datos);

	/**
	 * Consulta monedero tag.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<DatosMonederoConMovimientosYSaldoView> consultaMonederoTag(Cliente cliente);

	/**
	 * ver como se hace con la clase TerminosCondiciones Respuesta<Legal>
	 * cargarTerminosCondiciones();.
	 *
	 * @param datosAltaCanalesAutomaticosView
	 *            the datos alta canales automaticos view
	 * @return the respuesta
	 */

	Respuesta<AltaCanalAutomaticoOutEntity> ejecutarAltaCanalesAutomaticos(
			DatosAltaCanalesAutomaticosView datosAltaCanalesAutomaticosView);

	/**
	 * Gets the datos solicitud tag adicional.
	 *
	 * @return the datos solicitud tag adicional
	 */
	Respuesta<DatosSolicitudTagAdicionalView> getDatosSolicitudTagAdicional();

	/**
	 * Datos padron.
	 *
	 * @param datosSolicitanteCriterioView
	 *            the datos solicitante criterio view
	 * @return the respuesta
	 */
	Respuesta<DatosSolicitanteResponseView> datosPadron(DatosSolicitanteCriterioView datosSolicitanteCriterioView);

}
