/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.monedero.dto.AltaTag;
import ar.com.santanderrio.obp.servicios.monedero.web.view.AltaTagRSAReqView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.AltaTagRSAView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.ComprobanteActivacionTagMonederoView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.ComprobanteAltaTagMonederoView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosParaActivacionView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosSolicitanteConfirmadoInOutView;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.TerminosCondiciones;

/**
 * The Interface AltaTagMonederoManager.
 */
public interface AltaTagMonederoManager {

	/**
	 * Generar comprobante alta tag monedero.
	 *
	 * @param comprobanteAltaTagMonederoView
	 *            the comprobante alta tag monedero view
	 * @return the respuesta
	 */
	Respuesta<ReporteView> generarComprobanteAltaTagMonedero(
			ComprobanteAltaTagMonederoView comprobanteAltaTagMonederoView);

	/**
	 * Activar monedero tag.
	 *
	 * @param datosParaActivacionView
	 *            the datos para activacion view
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<ComprobanteActivacionTagMonederoView> activarMonederoTag(DatosParaActivacionView datosParaActivacionView,
			Cliente cliente);

	/**
	 * Generar comprobante activacion tag monedero.
	 *
	 * @param comprobanteActivacionTagMonederoView
	 *            the comprobante activacion tag monedero view
	 * @return the respuesta
	 */
	Respuesta<ReporteView> generarComprobanteActivacionTagMonedero(
			ComprobanteActivacionTagMonederoView comprobanteActivacionTagMonederoView);

	/**
	 * Validar operacion RSA.
	 *
	 * @param altaTag
	 *            the alta tag
	 * @return the respuesta
	 */
	Respuesta<AltaTagRSAView> validarOperacionRSA(AltaTag altaTag);

	/**
	 * Validar metodo seguridad.
	 *
	 * @param altaTagRSAReqView
	 *            the alta tag RSA req view
	 * @return the respuesta
	 */
	Respuesta<AltaTagRSAReqView> validarMetodoSeguridad(AltaTagRSAReqView altaTagRSAReqView);

	/**
	 * Cargar terminos condiciones.
	 *
	 * @return the respuesta
	 */
	Respuesta<TerminosCondiciones> cargarTerminosCondiciones();

	/**
	 * Ejecutar alta tag monedero rsa.
	 *
	 * @param datosSolicitanteView
	 *            the datos solicitante view
	 * @return the respuesta
	 */
	Respuesta<DatosSolicitanteConfirmadoInOutView> ejecutarAltaTagMonederoRsa(
			DatosSolicitanteConfirmadoInOutView datosSolicitanteView);

	/**
	 * Vaciar desafio.
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> vaciarDesafio();

}
