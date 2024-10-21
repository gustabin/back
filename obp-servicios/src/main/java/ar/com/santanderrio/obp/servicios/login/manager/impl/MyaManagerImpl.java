package ar.com.santanderrio.obp.servicios.login.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.STRING;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaCodOperacionEnum;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaMsgMultiple;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.ClienteEstadoEnum;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.clientes.entities.TipoCompaniaEnum;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.login.bo.MyaBO;
import ar.com.santanderrio.obp.servicios.login.bo.MyaSuscripcionesBO;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMyaIn;
import ar.com.santanderrio.obp.servicios.login.manager.MyaManager;
import ar.com.santanderrio.obp.servicios.login.view.DatosMyaView;
import ar.com.santanderrio.obp.servicios.login.view.LoginResponseView;
import ar.com.santanderrio.obp.servicios.mya.web.view.InicioGeneralSuscripcionMyAView;
import ar.com.santanderrio.obp.servicios.mya.web.view.InicioProductoSuscripcionMyAView;
import ar.com.santanderrio.obp.servicios.mya.web.view.MyaTarjetaView;
import ar.com.santanderrio.obp.servicios.mya.web.view.MyaUpdateMensajeView;
import ar.com.santanderrio.obp.servicios.mya.web.view.OpcionAlertaMyAView;
import ar.com.santanderrio.obp.servicios.mya.web.view.ProductoMyAEnum;
import ar.com.santanderrio.obp.servicios.mya.web.view.UpdateSuscripcionMensajeMyaView;
import ar.com.santanderrio.obp.servicios.suscripciones.dto.SuscripcionesDTO;
import ar.com.santanderrio.obp.servicios.suscripciones.entities.MensajeSuscripcion;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;


/**
 * The Class MyaManagerImpl.
 */
@Component
public class MyaManagerImpl implements MyaManager {

	/** The Constant NO_SUSCRIPTO. */
	private static final String NO_SUSCRIPTO = "N";

	/** The Constant SUSCRIPTO. */
	private static final String SUSCRIPTO = "S";

	/** The Constant OBTENIENDO_SUSCRIPCIONES. */
	private static final String OBTENIENDO_SUSCRIPCIONES = "Obteniendo suscripciones";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MyaManagerImpl.class);

	/** The Constant MASK. */
	private static final String MASK = "XXXX";

	/** The Constant ACEPTO_CONTRATO. */
	private static final Long ACEPTO_CONTRATO = 1L;

	/** The Constant MENSAJE_SORPRESA. */
	private static final String MENSAJE_SORPRESA = "102";
	
	private static final String E_RESUMEN = "E-resumen tarjeta";

	/** The mya BO. */
	@Autowired
	private MyaBO myaBO;

	/** The mya suscripciones BO. */
	@Autowired
	private MyaSuscripcionesBO myaSuscripcionesBO;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The legal BO. */
	@Autowired
	private LegalBO legalBO;

	@Autowired
	private MensajeBO mensajeBO;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.login.manager.MyaManager#
	 * obtenerEstadoMya(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente)
	 */
	public Respuesta<CredencialesMya> obtenerEstadoMya(Cliente cliente) {

		RegistroSesion registroSesion = sesionParametros.getRegistroSession();
		Respuesta<CredencialesMya> respuestaCredencialesMya = myaBO.obtenerEstadoMya(cliente,
				registroSesion.getClienteSinonimo());
		CredencialesMya credencialesMya = respuestaCredencialesMya.getRespuesta();
		// Si mya me devuelve OK lo convierto en un warning para front
		if (EstadoRespuesta.WARNING.equals(respuestaCredencialesMya.getEstadoRespuesta())) {
			respuestaCredencialesMya = respuestaFactory.crearRespuestaWarning(credencialesMya, "",
					TipoError.SUSCRIPCION_MYA, "");
		}
		if (!EstadoRespuesta.ERROR.equals(respuestaCredencialesMya.getEstadoRespuesta())) {

			sesionParametros.setCredencialesMya(credencialesMya);
			CredencialesMya credencialesMyaView = prepararDatosVista(credencialesMya);
			respuestaCredencialesMya.setRespuesta(credencialesMyaView);
		}
		return respuestaCredencialesMya;
	}

	/**
	 * Preparar datos vista.
	 *
	 * @param credencialesMya
	 *            the credenciales mya
	 * @return the credenciales mya
	 */
	private CredencialesMya prepararDatosVista(CredencialesMya credencialesMya) {

		CredencialesMya credencialView = new CredencialesMya();
		credencialView.setAceptacionContrato(credencialesMya.getAceptacionContrato());
		credencialView.setCelular(credencialesMya.getCelular());
		credencialView.setCelularId(credencialesMya.getCelularId());
		credencialView.setClienteEstado(credencialesMya.getClienteEstado());
		credencialView.setCodigoArea(credencialesMya.getCodigoArea());
		credencialView.setCompanias(credencialesMya.getCompanias());
		credencialView.setCompaniaSeleccionada(credencialesMya.getCompaniaSeleccionada());
		credencialView.setEmail(credencialesMya.getEmail());
		credencialView.setEmailId(credencialesMya.getEmailId());
		credencialView.setSinonimo(credencialesMya.getSinonimo());

		credencialView.setMostrarStackActualizarDatos(credencialesMya.getMostrarStackActualizarDatos());
		credencialView.setMostrarBotonConfirmacion(credencialView.getEmail() == null && credencialView.getEmailSecundario() == null ? Boolean.FALSE : Boolean.TRUE);
		credencialView.setMensajePrincipal(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_PRINCIPAL_ACTUALIZACION_DATOS).getMensaje());
		credencialView.setMensajeSecundario(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_SECUNDARIO_ACTUALIZACION_DATOS).getMensaje());
		
		return credencialView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.login.manager.MyaManager#
	 * confirmarDatosMya(ar.com.santanderrio.obp.servicios.login.view.
	 * DatosMyaView)
	 */
	@Override
	public Respuesta<CredencialesMya> confirmarDatosMya(DatosMyaView datosMyaView) {
		Respuesta<CredencialesMya> respuestaMya = respuestaFactory.crearRespuestaOk(CredencialesMya.class);
		CredencialesMya credencialesMya = sesionParametros.getCredencialesMya();

		Respuesta<Void> respuestaContrato = actualizarEstadoContratoMya(credencialesMya);

		if (EstadoRespuesta.OK.equals(respuestaContrato.getEstadoRespuesta())) {

			credencialesMya.setAceptacionContrato(ACEPTO_CONTRATO);

			Cliente cliente = sesionCliente.getCliente();

			CredencialesMyaIn credencialesMyaIn = validarData(datosMyaView, credencialesMya);

			Respuesta<Void> respuestaMyaWS = null;

			// Llama a Mya
			if (ClienteEstadoEnum.SUSCRIPTO_ACTIVO.getCodigo().equals(credencialesMya.getClienteEstado())) {
				respuestaMyaWS = myaBO.updateDestinos(credencialesMyaIn, cliente);
			}
			if (ClienteEstadoEnum.NO_SUSCRIPTO.getCodigo().equals(credencialesMya.getClienteEstado())) {
				respuestaMyaWS = myaBO.registrarClienteMya(cliente, credencialesMyaIn);
			}
			if (ClienteEstadoEnum.SUSCRIPTO_NO_ACTIVO.getCodigo().equals(credencialesMya.getClienteEstado())) {
				Respuesta<Void> respuestaActualizarEstado = myaBO.actualizarEstadoMya(cliente);
				if (EstadoRespuesta.OK.equals(respuestaActualizarEstado.getEstadoRespuesta())) {
					respuestaMyaWS = myaBO.updateDestinos(credencialesMyaIn, cliente);
				}
			}

			if (ClienteEstadoEnum.TIMEOUT.getCodigo().equals(credencialesMya.getClienteEstado())) {
				respuestaMyaWS = respuestaFactory.crearRespuestaWarning(StringUtils.EMPTY, TipoError.MYA_ERROR, null);
			}

			boolean soloAceptacionContrato = soloAceptacionContrato(datosMyaView);

			if (soloAceptacionContrato && EstadoRespuesta.OK.equals(respuestaMyaWS.getEstadoRespuesta())) {
				respuestaMya.setRespuesta(credencialesMya);
				grabarEstadisticaOK(EstadisticasConstants.CODIGO_TRANSACION_LOGIN_CONFIRMACION_MYA);
			}

			if (!soloAceptacionContrato && !EstadoRespuesta.ERROR.equals(respuestaMyaWS.getEstadoRespuesta())) {

				if (!errorCelularRegistrado(respuestaMyaWS) && !errorMya(respuestaMyaWS)) {
					if (StringUtils.isBlank(datosMyaView.getCelular())
							&& StringUtils.isNotBlank(credencialesMya.getCelular())) {
						LOGGER.info("No es posible actualizar el celular  por este canal.-");
						respuestaMya = respuestaFactory.crearRespuestaWarning(StringUtils.EMPTY,
								TipoError.MYA_CELULAR_ACTUALIZADO, CodigoMensajeConstantes.MYA_CELULAR_ACTUALIZADO);
					} else {
						credencialesMya.setCelular(datosMyaView.getCelular());
						credencialesMya.setCompaniaSeleccionada(datosMyaView.getCompania());
					}
					credencialesMya.setEmail(datosMyaView.getEmail());
					respuestaMya.setRespuesta(credencialesMya);
					grabarEstadisticaOK(EstadisticasConstants.CODIGO_TRANSACION_LOGIN_CONFIRMACION_MYA);
				}
				if (errorMya(respuestaMyaWS)) {
					credencialesMya.setClienteEstado(ClienteEstadoEnum.TIMEOUT.getCodigo());
					grabarEstadisticaError(EstadisticasConstants.CODIGO_TRANSACION_LOGIN_CONFIRMACION_MYA);
				}
			}

			if (EstadoRespuesta.ERROR.equals(respuestaMyaWS.getEstadoRespuesta())) {
				respuestaMya.setEstadoRespuesta(EstadoRespuesta.WARNING);
				respuestaMya = Respuesta.copy(CredencialesMya.class, respuestaMyaWS);
				grabarEstadisticaError(EstadisticasConstants.CODIGO_TRANSACION_LOGIN_CONFIRMACION_MYA);
				estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_MYA_LOGIN,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			}
			if (EstadoRespuesta.WARNING.equals(respuestaMyaWS.getEstadoRespuesta())) {
				respuestaMya = Respuesta.copy(CredencialesMya.class, respuestaMyaWS);
			}
			sesionParametros.setCredencialesMya(credencialesMya);
		} else {
			respuestaMya = Respuesta.copy(CredencialesMya.class, respuestaContrato);
		}

		return respuestaMya;
	}

	

	/**
	 * Solo aceptacion contrato.
	 *
	 * @param datosMyaView
	 *            the datos mya view
	 * @return true, if successful
	 */
	private boolean soloAceptacionContrato(DatosMyaView datosMyaView) {

		return datosMyaView.getCelular() == null 
				&& datosMyaView.getCodigoArea() == null
				&& datosMyaView.getCompania() == null
				&& datosMyaView.getEmail() == null;
	}

	
	/**
	 * Obtener mya.
	 *
	 * @param respuestaLoginResponseView
	 *            the respuesta login response view
	 * @return the respuesta
	 */
	@Override
	public Respuesta<LoginResponseView> obtenerMya(Respuesta<LoginResponseView> respuestaLoginResponseView) {

		Cliente cliente = sesionCliente.getCliente();
		LoginResponseView loginResponseView = respuestaLoginResponseView.getRespuesta();
		Respuesta<CredencialesMya> respuestaCredencialesMya = obtenerEstadoMya(cliente);

		if (EstadoRespuesta.WARNING.equals(respuestaCredencialesMya.getEstadoRespuesta())) {
			Respuesta<String> respuestaLegal = legalBO
					.buscarLegal(CodigoMensajeConstantes.LEGALES_TERMINOS_Y_CONDICIONES);
			loginResponseView.setTerminosCondiciones(respuestaLegal.getRespuesta());
			loginResponseView.setCredencialesMya(respuestaCredencialesMya.getRespuesta());
			respuestaLoginResponseView = Respuesta.copy(LoginResponseView.class, respuestaCredencialesMya);
			respuestaLoginResponseView.setRespuesta(loginResponseView);
		}

		if (EstadoRespuesta.ERROR.equals(respuestaCredencialesMya.getEstadoRespuesta())) {
			respuestaLoginResponseView = Respuesta.copy(LoginResponseView.class, respuestaCredencialesMya);
		} else {
		    if(cliente != null) {
		        loginResponseView.setNombreUsuario(cliente.getNombre());
		        loginResponseView.setCredencialesMya(respuestaCredencialesMya.getRespuesta());
		    }
		}

		return respuestaLoginResponseView;
	}

	/**
	 * Actualiza el estado del contrato Mya en BD.
	 *
	 * @param credencialesMya
	 *            the credenciales mya
	 * @return the respuesta
	 */
	private Respuesta<Void> actualizarEstadoContratoMya(CredencialesMya credencialesMya) {

		Respuesta<Void> respuestaContrato = respuestaFactory.crearRespuestaOk(Void.class);
		Boolean existeCliente = credencialesMya.getAceptacionContrato() != null;
		RegistroSesion registroSesion = sesionParametros.getRegistroSession();
		Boolean existeSesion = sesionParametros.getExisteClienteTyC();
		Cliente cliente = sesionCliente.getCliente();

		if (existeSesion == Boolean.TRUE) {

			respuestaContrato = myaBO.altaCliente(cliente);
		}

		if (existeCliente && !ACEPTO_CONTRATO.equals(credencialesMya.getAceptacionContrato())) {

			respuestaContrato = myaBO.actualizaContrato(cliente, registroSesion.getClienteSinonimo());
		}

		if (EstadoRespuesta.ERROR.equals(respuestaContrato.getEstadoRespuesta())) {

			this.grabarEstadisticaError(EstadisticasConstants.CODIGO_TRANSACION_LOGIN_CONFIRMACION_CONTRATO);
			respuestaContrato = Respuesta.copy(Void.class, respuestaContrato);
		} else {

			this.grabarEstadisticaOK(EstadisticasConstants.CODIGO_TRANSACION_LOGIN_CONFIRMACION_CONTRATO);
		}
		return respuestaContrato;

	}

	/**
	 * Error Mya.
	 *
	 * @param respuestaMyaWS
	 *            the respuesta mya WS
	 * @return true, if successful
	 */
	private boolean errorMya(Respuesta<Void> respuestaMyaWS) {

		try {
			String tipoError = obtenerEstado(respuestaMyaWS);

			return EstadoRespuesta.WARNING.equals(respuestaMyaWS.getEstadoRespuesta())
					&& TipoError.MYA_ERROR.getDescripcion().equals(tipoError);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return false;
		}
	}

	/**
	 * Obtener estado.
	 *
	 * @param respuestaMyaWS
	 *            the respuesta mya WS
	 * @return the string
	 */
	private String obtenerEstado(Respuesta<Void> respuestaMyaWS) {
		if (CollectionUtils.isEmpty(respuestaMyaWS.getItemsMensajeRespuesta())) {
			return "";
		}
		return respuestaMyaWS.getItemsMensajeRespuesta().get(0).getTipoError();
	}

	/**
	 * Error celular registrado.
	 *
	 * @param respuestaMyaWS
	 *            the respuesta mya WS
	 * @return true, if successful
	 */
	private boolean errorCelularRegistrado(Respuesta<Void> respuestaMyaWS) {
		try {
			String tipoError = obtenerEstado(respuestaMyaWS);
			return EstadoRespuesta.WARNING.equals(respuestaMyaWS.getEstadoRespuesta())
					&& TipoError.MYA_CELULAR_YA_REGISTRADO.getDescripcion().equals(tipoError);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return false;
		}
	}

	/**
	 * Validar data.
	 *
	 * @param datosMyaView
	 *            the datos mya view
	 * @param credencialesMya
	 *            the credenciales mya
	 * @return the credenciales mya in
	 */
	private CredencialesMyaIn validarData(DatosMyaView datosMyaView, CredencialesMya credencialesMya) {
		CredencialesMyaIn credencialesMyaIn = new CredencialesMyaIn();

		if (StringUtils.isNotBlank(datosMyaView.getCelular()) && StringUtils.isNotBlank(credencialesMya.getCelular())) {
			String celular = quitarMascara(datosMyaView.getCelular(), credencialesMya.getCelular());
			if (!celular.equals(credencialesMya.getCelular())) {
				credencialesMyaIn.setTipoOperacionCelular(MyaCodOperacionEnum.MODIFICACION);
			}
		}

		if (StringUtils.isNotBlank(datosMyaView.getCompania())
				&& StringUtils.isNotBlank(credencialesMya.getCompaniaSeleccionada())
				&& !datosMyaView.getCompania().equals(credencialesMya.getCompaniaSeleccionada())) {
			credencialesMyaIn.setTipoOperacionCelular(MyaCodOperacionEnum.MODIFICACION);
		}

		if (StringUtils.isNotBlank(datosMyaView.getCodigoArea())
				&& StringUtils.isNotBlank(credencialesMya.getCodigoArea())
				&& !datosMyaView.getCodigoArea().contains("X")
				&& !datosMyaView.getCodigoArea().equals(credencialesMya.getCodigoArea())) {
			credencialesMyaIn.setTipoOperacionCelular(MyaCodOperacionEnum.MODIFICACION);
		}

		if (StringUtils.isNotBlank(datosMyaView.getEmail()) && StringUtils.isNotBlank(credencialesMya.getEmail())
				&& !datosMyaView.getEmail().equals(credencialesMya.getEmail())) {
			credencialesMyaIn.setTipoOperacionEmail(MyaCodOperacionEnum.MODIFICACION);
		}

		if (StringUtils.isNotBlank(datosMyaView.getCelular()) && StringUtils.isBlank(credencialesMya.getCelular())) {
			credencialesMyaIn.setTipoOperacionCelular(MyaCodOperacionEnum.ALTA);
		}

		if (StringUtils.isNotBlank(datosMyaView.getCompania())
				&& StringUtils.isBlank(credencialesMya.getCompaniaSeleccionada())) {
			credencialesMyaIn.setTipoOperacionCelular(MyaCodOperacionEnum.ALTA);
		}

		if (StringUtils.isNotBlank(datosMyaView.getCodigoArea())
				&& StringUtils.isBlank(credencialesMya.getCodigoArea())) {
			credencialesMyaIn.setTipoOperacionCelular(MyaCodOperacionEnum.ALTA);
		}

		if (StringUtils.isNotBlank(datosMyaView.getEmail()) && StringUtils.isBlank(credencialesMya.getEmail())) {
			credencialesMyaIn.setTipoOperacionEmail(MyaCodOperacionEnum.ALTA);
		}

		/*** Mapeo los datos ***/
		credencialesMyaIn.setCelular(quitarMascara(datosMyaView.getCelular(), credencialesMya.getCelular())); // Aca
																												// voy
																												// desenmascararar
		credencialesMyaIn.setCodigoArea(getCodigoArea(datosMyaView.getCodigoArea(), credencialesMya.getCodigoArea()));
		credencialesMyaIn.setCompaniaSeleccionada(TipoCompaniaEnum.fromDescripcion(datosMyaView.getCompania()));
		credencialesMyaIn.setEmail(datosMyaView.getEmail());

		return credencialesMyaIn;
	}

	/**
	 * Gets the codigo area.
	 *
	 * @param codigoView the codigo view
	 * @param oldCodigo the old codigo
	 * @return the codigo area
	 */
	private String getCodigoArea(String codigoView, String oldCodigo) {

		if (!StringUtils.isEmpty(codigoView) && !codigoView.contains("X")) {
			return codigoView;
		}
		return oldCodigo;
	}

	/**
	 * Quitar mascara.
	 *
	 * @param celular
	 *            the celular
	 * @param celularOld
	 *            the celular old
	 * @return the string
	 */
	private String quitarMascara(String celular, String celularOld) {
		if (!StringUtils.isEmpty(celular)) {
			String value = celular.substring(0, 4);
			if (value.equals(MASK)) {
				return celularOld;
			}
		}

		return celular;
	}

	/**
	 * Grabar estadistica error.
	 *
	 * @param codigoTransaccion
	 *            the codigo transaccion
	 */
	private void grabarEstadisticaError(String codigoTransaccion) {
		estadisticaManager.add(codigoTransaccion, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
	}

	/**
	 * Graba la estadistica cuando sale ok.
	 *
	 * @param codigoTransaccion
	 *            the codigo transaccion
	 */
	private void grabarEstadisticaOK(String codigoTransaccion) {
		estadisticaManager.add(codigoTransaccion, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.login.manager.MyaManager#
	 * obtenerTerminosCondicionesPDF()
	 */
	@Override
	public Respuesta<ReporteView> obtenerTerminosCondicionesPDF() {
		Respuesta<ReporteView> respuesta = respuestaFactory.crearRespuestaOk(ReporteView.class);
		ReporteView reporteView = new ReporteView();
		Respuesta<Reporte> reporteRespuesta = null;
		Respuesta<String> respuestaLegal = legalBO.buscarLegal(CodigoMensajeConstantes.LEGALES_TERMINOS_Y_CONDICIONES);
		if (EstadoRespuesta.ERROR.equals(respuestaLegal.getEstadoRespuesta())) {

			LOGGER.debug("Descarga PDF Obtener Terminos y Condiciones");
			grabarEstadisticaError(EstadisticasConstants.CODIGO_TRANSACCION_DESCARGA_T_Y_C_M_Y_A);
			respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_DESCARGA_TYC_MYA, null);
		} else {

			try {
				reporteRespuesta = myaBO.obtenerTerminosCondicionesPDF(respuestaLegal.getRespuesta());

				if (EstadoRespuesta.ERROR.equals(respuestaLegal.getEstadoRespuesta())) {

					LOGGER.debug("Descarga PDF - Generacion de PDF");
					grabarEstadisticaError(EstadisticasConstants.CODIGO_TRANSACCION_DESCARGA_T_Y_C_M_Y_A);
					respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY,
							TipoError.ERROR_DESCARGA_TYC_MYA, null);
				} else {
					reporteView = ReporteView.fromReporte(reporteRespuesta.getRespuesta());
					respuesta.setRespuesta(reporteView);
					estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_DESCARGA_T_Y_C_M_Y_A,
							EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				}
			} catch (Exception ex) {
				LOGGER.error(ex.getMessage(), ex);
				grabarEstadisticaError(EstadisticasConstants.CODIGO_TRANSACCION_DESCARGA_T_Y_C_M_Y_A);
				respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_DESCARGA_TYC_MYA,
						null);
			}
		}

		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.login.manager.MyaManager#
	 * obtenerInicioMensajesAvisos()
	 */
	@Override
	public Respuesta<InicioGeneralSuscripcionMyAView> obtenerInicioMensajesAvisos() {

		InicioGeneralSuscripcionMyAView inicioGeneralSuscripcionMyAView = new InicioGeneralSuscripcionMyAView();
		CredencialesMya credencialesMya = sesionParametros.getCredencialesMya();

		if ("TO".equals(credencialesMya.getClienteEstado())) {
			estadisticaManager.add(EstadisticasConstants.INICIO_MYA, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_INICIO_MYA,
					CodigoMensajeConstantes.ERROR_INICIO_MYA);
		}

		String email = credencialesMya.getEmail();
		String emailSecundario = credencialesMya.getEmailSecundario();
		
		email = ISBANStringUtils.ofuscarMail(email);
		emailSecundario = ISBANStringUtils.ofuscarMail(emailSecundario);
		
		inicioGeneralSuscripcionMyAView.setEmail1(email);
		inicioGeneralSuscripcionMyAView.setEmail2(emailSecundario);
		inicioGeneralSuscripcionMyAView.setProductos(obtenerOpcionesMya(sesionCliente.getCliente()));
		estadisticaManager.add(EstadisticasConstants.INICIO_MYA, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(inicioGeneralSuscripcionMyAView);
	}

	/**
	 * Obtener opciones mya.
	 *
	 * @return the list
	 */
	private List<String> obtenerOpcionesMya(Cliente cliente) {

		List<String> listaProductos = new ArrayList<String>();
		listaProductos.add(ProductoMyAEnum.CUENTAS.getLabel());
		listaProductos.add(ProductoMyAEnum.PAGOS_TRANSFERENCIAS.getLabel());
		listaProductos.add(ProductoMyAEnum.TARJETAS.getLabel());
		/*
		if (clienteTitularDeTarjetaDebitoCredito(cliente)) {
			listaProductos.add(ProductoMyAEnum.SORPRESA.getLabel());
		}
		*/
		listaProductos.add(ProductoMyAEnum.INVERSIONES.getLabel());
		return listaProductos;
	}

	private Boolean clienteTitularDeTarjetaDebitoCredito(Cliente cliente) {
		for (Cuenta cuenta : cliente.getCuentas()) {
			if ((cuenta.esTarjetaDeCredito() || cuenta.esBanelco()) && cuenta.esTitular()) {
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.login.manager.MyaManager#
	 * obtenerInicioProductoMyA(java.lang.String)
	 */
	@Override
	public Respuesta<InicioProductoSuscripcionMyAView> obtenerInicioProductoMyA(String producto) {
		LOGGER.info(OBTENIENDO_SUSCRIPCIONES);
		ProductoMyAEnum productoEnum = ProductoMyAEnum.obtenerProductoMyAEnum(producto);
		Respuesta<SuscripcionesDTO> suscripcionesDTO = myaSuscripcionesBO.obtenerMensajesSuscripciones(
				sesionCliente.getCliente(), sesionParametros.getCredencialesMya(), productoEnum);
		if (suscripcionesDTO.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			return respuestaFactory.crearRespuestaError(InicioProductoSuscripcionMyAView.class,
					suscripcionesDTO.getItemsMensajeRespuesta());
		} else {
			InicioProductoSuscripcionMyAView inicioProductoView = new InicioProductoSuscripcionMyAView(
					suscripcionesDTO.getRespuesta());
			if ("Alertas de Tarjetas".equals(inicioProductoView.getCabecera())) {
				revisarSiCorrespondeMostrarEresumen(inicioProductoView, sesionCliente.getCliente().getCuentas());
				suscripcionesDTO.setEstadoRespuesta(EstadoRespuesta.OK);
			}
			
			if (suscripcionesDTO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
				sesionParametros.setListaMsgMultiples(obtenerListaMsgMultiples(suscripcionesDTO.getRespuesta()));
				return respuestaFactory.crearRespuestaOk(inicioProductoView);
			} else {
				return respuestaFactory.crearRespuestaWarning(inicioProductoView,
						suscripcionesDTO.getItemsMensajeRespuesta());
			}
		}
	}
	
	private void revisarSiCorrespondeMostrarEresumen(InicioProductoSuscripcionMyAView inicioProductoView, List<Cuenta> cuentas) {
		Boolean tieneTarjetasTitulares = Boolean.FALSE;
		for (Cuenta cuenta : cuentas) {
			
			if (cuenta.getCodigoTitularidad().equals("TI") && (cuenta.getTipoCuentaEnum().equals(TipoCuenta.VISA) || cuenta.getTipoCuentaEnum().equals(TipoCuenta.MASTERCARD) || cuenta.getTipoCuentaEnum().equals(TipoCuenta.AMEX))) {
				tieneTarjetasTitulares = Boolean.TRUE;
			}
		}
		
		if (!tieneTarjetasTitulares) {
			List<OpcionAlertaMyAView> listaOpcionesModificada = new ArrayList<OpcionAlertaMyAView>();
			for (OpcionAlertaMyAView opcion : inicioProductoView.getOpcionesAlertas()) {
				if (!E_RESUMEN.equals(opcion.getLabel())) {
					listaOpcionesModificada.add(opcion);
				}
			}
			inicioProductoView.setOpcionesAlertas(listaOpcionesModificada);
		}
	}


	/**
	 * Obtener lista msg multiples.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @return the list
	 */
	private Map<String, List<MyaMsgMultiple>> obtenerListaMsgMultiples(SuscripcionesDTO respuesta) {
		List<MensajeSuscripcion> mensajes = respuesta.getMensajes();
		Map<String, List<MyaMsgMultiple>> mapaTarjetas = new HashMap<String, List<MyaMsgMultiple>>();
		for (MensajeSuscripcion mensajeSuscripcion : mensajes) {
			if (CollectionUtils.isNotEmpty(mensajeSuscripcion.getListaMsgMultiples())) {
				mapaTarjetas.put(mensajeSuscripcion.getNumeroMensaje(), mensajeSuscripcion.getListaMsgMultiples());
			}
		}
		return mapaTarjetas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.login.manager.MyaManager#updateMensajes
	 * (ar.com.santanderrio.obp.servicios.mya.web.view.MyaUpdateMensajeView)
	 */
	@Override
	public Respuesta<Void> updateMensajes(MyaUpdateMensajeView myaUpdateMensajeView) {
		if (validarCelularAdherido()) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SERVICIO_MYA,
					CodigoMensajeConstantes.MENSAJE_ERROR_CELULAR_NO_ADHERIDO);
		}

		Cliente cliente = sesionCliente.getCliente();
		List<MyaTarjetaView> listaTarjetas = myaUpdateMensajeView.getDatos().getListaTarjetas();
		if (CollectionUtils.isNotEmpty(listaTarjetas)) {
			List<MyaMsgMultiple> listaMsgMultiples = obtenerListaMsgMultiples(listaTarjetas,
					myaUpdateMensajeView.getDatos().getListaUpdatesSuscripciones().get(0).getNumeroMensaje());
			myaUpdateMensajeView.setListaMsgMultiples(listaMsgMultiples);
		}
		Respuesta<Void> respuesta = myaSuscripcionesBO.updateMensajes(cliente, myaUpdateMensajeView);
		if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta()) && MENSAJE_SORPRESA
				.equals(myaUpdateMensajeView.getDatos().getListaUpdatesSuscripciones().get(0).getNumeroMensaje())) {
			grabarEstadisticaSorpresa(myaUpdateMensajeView.getDatos().getListaUpdatesSuscripciones(), 
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else if (EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta()) && MENSAJE_SORPRESA
				.equals(myaUpdateMensajeView.getDatos().getListaUpdatesSuscripciones().get(0).getNumeroMensaje())) {
			grabarEstadisticaSorpresa(myaUpdateMensajeView.getDatos().getListaUpdatesSuscripciones(), 
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuesta;
	}

	/**
	 * Graba estadistica de procesamiento de alta o baja para sorpresa MyA
	 * 
	 * @param listaUpdatesSuscripciones
	 * @param codigo 1 para OK y 2 para ERROR
	 */
	private void grabarEstadisticaSorpresa(List<UpdateSuscripcionMensajeMyaView> listaUpdatesSuscripciones,
			String codigo) {
		for(UpdateSuscripcionMensajeMyaView view : listaUpdatesSuscripciones) {
			if(view.getOperacionUpdate() != null && view.getOperacionUpdate().equals("alta")) {
				estadisticaManager.add(EstadisticasConstants.CODIGO_ESTADISTICA_MYA_SUSCRIPCIONES_SORPRESA_ALTA,
						codigo);				
			}else if(view.getOperacionUpdate() != null && view.getOperacionUpdate().equals("baja")) {
				estadisticaManager.add(EstadisticasConstants.CODIGO_ESTADISTICA_MYA_SUSCRIPCIONES_SORPRESA_BAJA,
						codigo);
			}
		}
	}

	/**
	 * Validar celular adherido.
	 *
	 * @return true, if successful
	 */
	public boolean validarCelularAdherido() {
		CredencialesMya credencialesMya = sesionParametros.getCredencialesMya();
		if ((credencialesMya.getCelular() == null || credencialesMya.getCelular().isEmpty())
				&& (credencialesMya.getCelularSecundario() == null
						|| credencialesMya.getCelularSecundario().isEmpty())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Obtener lista msg multiples.
	 *
	 * @param listaTarjetas
	 *            the lista tarjetas
	 * @param numeroMensaje
	 *            the numero mensaje
	 * @return the list
	 */
	private List<MyaMsgMultiple> obtenerListaMsgMultiples(List<MyaTarjetaView> listaTarjetas, String numeroMensaje) {
		Map<String, List<MyaMsgMultiple>> mapaTarjetas = sesionParametros.getListaMsgMultiples();
		List<MyaMsgMultiple> lista = mapaTarjetas.get(numeroMensaje);
		List<MyaMsgMultiple> listaModificada = new ArrayList<MyaMsgMultiple>();
		for (MyaTarjetaView tarjetaView : listaTarjetas) {
		    MyaMsgMultiple msjeMultiple = buscarMsgMultiple(lista, tarjetaView.getNroTarjetaEnmascarado());
		    if (msjeMultiple!= null)
		        if (obtenerMontoSuscripcion(tarjetaView.getMonto()).equalsIgnoreCase(msjeMultiple.getMonto()) 
	                            || obtenerSuscripcion(tarjetaView.getSuscripcion())!=msjeMultiple.getSuscripto()) {
    		        msjeMultiple.setMonto(obtenerMontoSuscripcion(tarjetaView.getMonto()));
    		        msjeMultiple.setSuscripto(obtenerSuscripcion(tarjetaView.getSuscripcion()));
    		        listaModificada.add(msjeMultiple);
		        }
		}
		return listaModificada;
	}
	
    private MyaMsgMultiple buscarMsgMultiple(List<MyaMsgMultiple> lista, String nroTarjeta) {
        for (MyaMsgMultiple tarjeta: lista) {
            String nroTarjetaEnmascarado = "XXXX-"
                    + TarjetaUtils.cortarNumeroTarjeta(tarjeta.getNroTarjeta(), tarjeta.getDescripcion().contains("VISA") ? "VISA" : "AMEX");
            if(nroTarjetaEnmascarado.equals(nroTarjeta)) {
                return tarjeta;
            }
        }
        return null;
    }

	/**
	 * Obtener monto suscripcion.
	 *
	 * @param monto
	 *            the monto
	 * @return the string
	 */
	private String obtenerMontoSuscripcion(String monto) {
		if (monto == null) { 
			return " ";
		}
		return monto;
	}

	/**
	 * Obtener suscripcion.
	 *
	 * @param suscripcion
	 *            the suscripcion
	 * @return the string
	 */
	private String obtenerSuscripcion(Boolean suscripcion) {
		if (suscripcion) {
			return SUSCRIPTO;
		} else {
			return NO_SUSCRIPTO;
		}
	}
	
}
