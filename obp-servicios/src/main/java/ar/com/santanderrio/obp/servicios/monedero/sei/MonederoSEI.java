/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.canalesautomaticos.entity.AltaCanalAutomaticoOutEntity;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.monedero.dto.AltaTag;
import ar.com.santanderrio.obp.servicios.monedero.web.view.AltaTagRSAReqView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.AltaTagRSAView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.ComprobanteActivacionTagMonederoView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.ComprobanteAltaTagMonederoView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosAltaCanalesAutomaticosView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosMonederoConMovimientosYSaldoView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosParaActivacionView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosSolicitanteConfirmadoInOutView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosSolicitanteCriterioView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosSolicitanteResponseView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosSolicitudTagAdicionalView;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.TerminosCondiciones;

/**
 * The Interface MonederoSEI.
 */
@Path("/monedero")
public interface MonederoSEI {

	/**
	 * Get the datos del solicitante.
	 *
	 * @return the detalles
	 */

	@POST
	@Path("/datosSolicitudTagAdicional")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<DatosSolicitudTagAdicionalView> getDatosSolicitudTagAdicional();

	/**
	 * Gets the datos del solicitante.
	 *
	 * @param datosSolicitanteCriterioView
	 *            the datos solicitante criterio view
	 * @return the datos del solicitante
	 */
	@POST
	@Path("/datosDelSolicitante")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<DatosSolicitanteResponseView> getDatosDelSolicitante(
			DatosSolicitanteCriterioView datosSolicitanteCriterioView);

	/**
	 * Validar operacion RSA.
	 *
	 * @param altaTag
	 *            the alta tag
	 * @return the respuesta
	 */
	@POST
	@Path("/validarOperacionRSA")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<AltaTagRSAView> validarOperacionRSA(AltaTag altaTag);

	/**
	 * Validar metodo seguridad.
	 *
	 * @param altaTagRSAReqView
	 *            the alta tag RSA req view
	 * @return the respuesta
	 */
	@POST
	@Path("/validarMetodoSeguridad")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<AltaTagRSAReqView> validarMetodoSeguridad(AltaTagRSAReqView altaTagRSAReqView);

	/**
	 * Ejecutar alta tag monedero.
	 *
	 * @param datosSolicitanteConfirmadoView
	 *            the datos solicitante confirmado view
	 * @return the respuesta
	 */
	@POST
	@Path("/altaTagMonedero")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<DatosSolicitanteConfirmadoInOutView> ejecutarAltaTagMonederoRsa(
			DatosSolicitanteConfirmadoInOutView datosSolicitanteConfirmadoView);

	/**
	 * Generar comprobante alta tag monedero.
	 *
	 * @param comprobanteAltaTagMonederoView
	 *            the comprobante alta tag monedero view
	 * @return the respuesta
	 */
	@POST
	@Path("/comprobanteAltaTagMonedero")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> generarComprobanteAltaTagMonedero(
			ComprobanteAltaTagMonederoView comprobanteAltaTagMonederoView);

	/**
	 * Consulta que retorna la vista general de tags monederos Titular (activo o
	 * pendiente) Adicionales (activos o pendientes respectivamente) Movimientos
	 * y saldos (tanto para Titular como para Adicionales).
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/getTags")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<DatosMonederoConMovimientosYSaldoView> consultaMonederoTag();

	/**
	 * Activar monedero tag.
	 *
	 * @param datosParaActivacionView
	 *            the datos para activacion view
	 * @return the respuesta
	 */
	@POST
	@Path("/activarTag")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<ComprobanteActivacionTagMonederoView> activarMonederoTag(DatosParaActivacionView datosParaActivacionView);

	/**
	 * ver como se hace con la clase TerminosCondiciones.
	 *
	 * @return the respuesta
	 * @POST @Path("/terminosCondiciones")
	 * @Produces(value = { MediaType.APPLICATION_JSON })
	 * @Consumes(value = { MediaType.APPLICATION_JSON }) public Respuesta<Legal>
	 *                 cargarTerminosCondiciones();
	 */

	@POST
	@Path("/terminosSolicitarTagTitular")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<TerminosCondiciones> cargarTerminosCondiciones();

	/**
	 * Ejecutar alta canales automaticos.
	 *
	 * @param datosAltaCanalesAutomaticosView
	 *            the datos alta canales automaticos view
	 * @return the respuesta
	 */
	@POST
	@Path("/ejecutarAltaCanalesAutomaticos")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<AltaCanalAutomaticoOutEntity> ejecutarAltaCanalesAutomaticos(
			DatosAltaCanalesAutomaticosView datosAltaCanalesAutomaticosView);

	/**
	 * Datos padron.
	 *
	 * @param datosSolicitanteCriterioView
	 *            the datos solicitante criterio view
	 * @return the respuesta
	 */
	@POST
	@Path("/datosPadron")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<DatosSolicitanteResponseView> datosPadron(DatosSolicitanteCriterioView datosSolicitanteCriterioView);

	/**
	 * Generar comprobante activacion tag monedero.
	 *
	 * @param comprobanteActivacionView
	 *            the comprobante activacion view
	 * @return the respuesta
	 */
	@POST
	@Path("/comprobanteActivacionTagMonedero")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> generarComprobanteActivacionTagMonedero(
			ComprobanteActivacionTagMonederoView comprobanteActivacionView);
}
