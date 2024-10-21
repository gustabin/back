/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.sei.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.canalesautomaticos.entity.AltaCanalAutomaticoOutEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.monedero.dto.AltaTag;
import ar.com.santanderrio.obp.servicios.monedero.entities.TipoDocumentoEnum;
import ar.com.santanderrio.obp.servicios.monedero.sei.MonederoSEI;
import ar.com.santanderrio.obp.servicios.monedero.web.manager.AltaTagMonederoManager;
import ar.com.santanderrio.obp.servicios.monedero.web.manager.DatosSolicitanteManager;
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
 * The Class MonederoSEIImpl.
 */
@Component
public class MonederoSEIImpl implements MonederoSEI {

	/** The datos solicitante manager. */
	@Autowired
	private DatosSolicitanteManager datosSolicitanteManager;

	/** The alta tag monedero manager. */
	@Autowired
	private AltaTagMonederoManager altaTagMonederoManager;

	/** The session client. */
	@Autowired
	private SesionCliente sessionClient;

	/** The Constant MENSAJE_RESPUESTA. */
	private static final String MENSAJE_RESPUESTA = "Respuesta: {}.";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MonederoSEIImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.sei.MonederoSEI#
	 * getDatosSolicitudTagAdicional()
	 */
	@Override
	public Respuesta<DatosSolicitudTagAdicionalView> getDatosSolicitudTagAdicional() {
		Respuesta<DatosSolicitudTagAdicionalView> respuesta = datosSolicitanteManager.getDatosSolicitudTagAdicional();
		LOGGER.debug(MENSAJE_RESPUESTA, respuesta);
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.sei.MonederoSEI#
	 * getDatosDelSolicitante(ar.com.santanderrio.obp.servicios.monedero.web.
	 * view.DatosSolicitanteCriterioView)
	 */
	@Override
	public Respuesta<DatosSolicitanteResponseView> getDatosDelSolicitante(
			DatosSolicitanteCriterioView datosSolicitanteCriterioView) {
		Respuesta<DatosSolicitanteResponseView> respuesta;
		/** Titular */
		if (!datosSolicitanteCriterioView.isAdicional()) {
			DatosSolicitanteCriterioView datos = creaDatosCriterioCliente(sessionClient.getCliente());
			respuesta = datosSolicitanteManager.getDatosDelSolicitante(datos);
			/** Adicional */
		} else {
			datosSolicitanteCriterioView.setDocTipo(
					TipoDocumentoEnum.obtenerTipoDocumento(datosSolicitanteCriterioView.getDocTipo()).getDescripcion());
			respuesta = datosSolicitanteManager.getDatosDelSolicitante(datosSolicitanteCriterioView);
		}
		LOGGER.debug(MENSAJE_RESPUESTA, respuesta);
		return respuesta;
	}

	/**
	 * Crea datos criterio cliente.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the datos solicitante criterio view
	 */
	private DatosSolicitanteCriterioView creaDatosCriterioCliente(Cliente cliente) {
		DatosSolicitanteCriterioView datos = new DatosSolicitanteCriterioView();
		datos.setDocTipo(cliente.getTipoDocumento());
		datos.setDoc(cliente.getDni());
		return datos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.sei.MonederoSEI#
	 * ejecutarAltaTagMonedero(ar.com.santanderrio.obp.servicios.monedero.web.
	 * view.DatosSolicitanteConfirmadoView)
	 */
	@Override
	public Respuesta<DatosSolicitanteConfirmadoInOutView> ejecutarAltaTagMonederoRsa(
			DatosSolicitanteConfirmadoInOutView datosSolicitanteView) {
		Respuesta<DatosSolicitanteConfirmadoInOutView> respuesta = altaTagMonederoManager
				.ejecutarAltaTagMonederoRsa(datosSolicitanteView);
		LOGGER.debug(MENSAJE_RESPUESTA, respuesta);
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.sei.MonederoSEI#
	 * consultaMonederoTag()
	 */
	@Override
	public Respuesta<DatosMonederoConMovimientosYSaldoView> consultaMonederoTag() {
		Respuesta<DatosMonederoConMovimientosYSaldoView> respuesta = datosSolicitanteManager
				.consultaMonederoTag(sessionClient.getCliente());
		LOGGER.debug(MENSAJE_RESPUESTA, respuesta);
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.sei.MonederoSEI#
	 * generarComprobanteAltaTagMonedero(ar.com.santanderrio.obp.servicios.
	 * monedero.web.view.ComprobanteAltaTagMonederoView)
	 */
	@Override
	public Respuesta<ReporteView> generarComprobanteAltaTagMonedero(
			ComprobanteAltaTagMonederoView comprobanteAltaTagMonederoView) {
		Respuesta<ReporteView> respuesta = altaTagMonederoManager
				.generarComprobanteAltaTagMonedero(comprobanteAltaTagMonederoView);
		LOGGER.debug(MENSAJE_RESPUESTA, respuesta);
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.sei.MonederoSEI#
	 * activarMonederoTag(ar.com.santanderrio.obp.servicios.monedero.web.view.
	 * DatosParaActivacionView)
	 */
	@Override
	public Respuesta<ComprobanteActivacionTagMonederoView> activarMonederoTag(
			DatosParaActivacionView datosParaActivacionView) {
		Respuesta<ComprobanteActivacionTagMonederoView> respuesta = altaTagMonederoManager
				.activarMonederoTag(datosParaActivacionView, sessionClient.getCliente());
		LOGGER.debug(MENSAJE_RESPUESTA, respuesta);
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.sei.MonederoSEI#
	 * generarComprobanteActivacionTagMonedero(ar.com.santanderrio.obp.servicios
	 * .monedero.web.view.ComprobanteActivacionTagMonederoView)
	 */
	@Override
	public Respuesta<ReporteView> generarComprobanteActivacionTagMonedero(
			ComprobanteActivacionTagMonederoView comprobanteActivacionTagMonederoView) {
		Respuesta<ReporteView> respuesta = altaTagMonederoManager
				.generarComprobanteActivacionTagMonedero(comprobanteActivacionTagMonederoView);
		LOGGER.debug(MENSAJE_RESPUESTA, respuesta);
		return respuesta;
	}

	/**
	 * Cargar terminos condiciones.
	 *
	 * @return the respuesta
	 * @Override public Respuesta<Legal> cargarTerminosCondiciones() { return
	 *           datosSolicitanteManager.cargarTerminosCondiciones(); }
	 */

	@Override
	public Respuesta<TerminosCondiciones> cargarTerminosCondiciones() {
		return altaTagMonederoManager.cargarTerminosCondiciones();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.sei.MonederoSEI#
	 * ejecutarAltaCanalesAutomaticos(ar.com.santanderrio.obp.servicios.monedero
	 * .web.view.DatosAltaCanalesAutomaticosView)
	 */
	@Override
	public Respuesta<AltaCanalAutomaticoOutEntity> ejecutarAltaCanalesAutomaticos(
			DatosAltaCanalesAutomaticosView datosAltaCanalesAutomaticosView) {
		return datosSolicitanteManager.ejecutarAltaCanalesAutomaticos(datosAltaCanalesAutomaticosView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.monedero.sei.MonederoSEI#datosPadron(ar
	 * .com.santanderrio.obp.servicios.monedero.web.view.
	 * DatosSolicitanteCriterioView)
	 */
	@Override
	public Respuesta<DatosSolicitanteResponseView> datosPadron(
			DatosSolicitanteCriterioView datosSolicitanteCriterioView) {
		return datosSolicitanteManager.datosPadron(datosSolicitanteCriterioView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.sei.MonederoSEI#
	 * validarOperacionRSA(ar.com.santanderrio.obp.servicios.monedero.dto.
	 * AltaTag)
	 */
	@Override
	public Respuesta<AltaTagRSAView> validarOperacionRSA(AltaTag altaTag) {
		return altaTagMonederoManager.validarOperacionRSA(altaTag);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.sei.MonederoSEI#
	 * validarMetodoSeguridad(ar.com.santanderrio.obp.servicios.monedero.web.
	 * view.AltaTagRSAReqView)
	 */
	@Override
	public Respuesta<AltaTagRSAReqView> validarMetodoSeguridad(AltaTagRSAReqView altaTagRSAReqView) {
		return altaTagMonederoManager.validarMetodoSeguridad(altaTagRSAReqView);
	}
}
