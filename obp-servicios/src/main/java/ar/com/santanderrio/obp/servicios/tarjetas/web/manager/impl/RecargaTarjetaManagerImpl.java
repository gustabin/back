/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.manager.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.base.http.web.util.NetworkUtil;
import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.rsa.ActionCode;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.CasuisticaErrorTarjetasBO;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.EncabezadoTarjetasBO;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.RecargaTarjetaBO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.AdhesionTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.BajaAdhesionTarjConfirmacion;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ComprobanteRecargaTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosRecargaTREntity;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.view.ComprobanteFeedbackView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.Reintentar;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.InicioTarjetasManager;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.RecargaTarjetaManager;
import ar.com.santanderrio.obp.servicios.tarjetas.web.util.EstadisticasTarjetasUtil;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ComprobanteRecargaTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ComprobanteRecargaTarjetaView.TipoComprobanteRecarga;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.RecargaTarjetaInfoView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.RecargaTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.TarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.TarjetasView;

/**
 * The Class RecargaTarjetaManagerImpl.
 */
@Component
public class RecargaTarjetaManagerImpl implements RecargaTarjetaManager {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RecargaTarjetaManagerImpl.class);

	/** The Constant SERVICIO_DEVOLVIO_LOG_LABEL. */
	public static final String SERVICIO_DEVOLVIO_LOG_LABEL = "El servicio devolvio: ";

	/** The Constant STRING_VACIO. */
	private static final String STRING_VACIO = "";

	/** The Constant DIVISA. */
	public static final String DIVISA = "ARS";

	/** The Constant VISA. */
	public static final String VISA = "1";

	/** The Constant COORDENADAS. */
	public static final int COORDENADAS = 0;

	/** The Constant COORDENADAS_BANELCO. */
	public static final int COORDENADAS_BANELCO = 1;

	/** The Constant TOKEN_COORDENADAS_BANELCO. */
	public static final int TOKEN_COORDENADAS_BANELCO = 2;

	/** The Constant ERROR_VALIDACION. */
	private static final String ERROR_VALIDACION = "1032";

	/** The casuistica. */
	@Autowired
	protected CasuisticaErrorTarjetasBO casuistica;

	/** The recarga tarjeta BO. */
	@Autowired
	@Qualifier("recargaTarjetaBO")
	RecargaTarjetaBO recargaTarjetaBO;

	/** The mensaje BO. */
	@Autowired
	MensajeBO mensajeBO;

	/** The cuenta BO. */
	@Autowired
	CuentaBO cuentaBO;

	/** The inicio tarjetas manager. */
	@Autowired
	InicioTarjetasManager inicioTarjetasManager;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The respuesta factory. */
	@Autowired
	RespuestaFactory respuestaFactory;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The encabezado tarjetas BO. */
	@Autowired
	private EncabezadoTarjetasBO encabezadoTarjetasBO;

	/** Variable cuentaManager. */
	@Autowired
	CuentaManager cuentaManager;

	/** The session parametros. */
	@Autowired
	private SesionParametros sessionParametros;

	/** The rsa manager. */
	@Autowired
	private RsaManager rsaManager;

	/** The importe min rec. */
	@Value("${TRJREC.IMPORTEMINREC}")
	private String importeMinRec;

	/** The importe max rec. */
	@Value("${TRJREC.IMPORTEMAXREC}")
	private String importeMaxRec;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.
	 * RecargaTarjetaManager#recargarTarjeta(javax.servlet.http. HttpServletRequest,
	 * ar.com.santanderrio.obp.servicios.tarjetas.web.view.
	 * ComprobanteRecargaTarjetaView,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<ComprobanteRecargaTarjetaView> recargarTarjeta(HttpServletRequest request,
			ComprobanteRecargaTarjetaView recargaTarjetaView, Cliente cliente) {

		Respuesta<ComprobanteRecargaTarjetaView> respuestaFinal = new Respuesta<ComprobanteRecargaTarjetaView>();
		Respuesta<AbstractCuenta> respuestaAbstractCuenta = cuentaManager
				.obtenerCuentaPorId(recargaTarjetaView.getNroCuentaOrigen());

		if (respuestaAbstractCuenta.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			LOGGER.error("Ha ocurrido un error validando la cuenta origen.");
			respuestaFinal.setEstadoRespuesta(EstadoRespuesta.ERROR);
			cleanCuentaOrigen(respuestaFinal);
			return respuestaFinal;
		} else if (respuestaAbstractCuenta.getRespuesta() == null) {
			respuestaFinal = cargarMensajeRespuestaCuenta2(recargaTarjetaView.getNroCuentaOrigen(), respuestaFinal);
			cleanCuentaOrigen(respuestaFinal);
			return respuestaFinal;
		}
		LOGGER.info("Cuenta origen {} validada", recargaTarjetaView.getNroCuentaOrigen());

		// Codigo nuevo (10/02)
		Cuenta cuentaDestino = obtenerCuentaTarjeta(cliente, recargaTarjetaView.getNroTarjeta());
		if (TarjetaUtils.isRecargableAdicional(cuentaDestino)) {
			return crearRespuestaErrorGenerico(CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
		}

		recargaTarjetaView.setSaldoCuentaOrigen(
				recargaTarjetaView.getSaldoCuentaOrigen().replace(",", STRING_VACIO).replace(".", STRING_VACIO));
		Cuenta cuenta = (Cuenta) respuestaAbstractCuenta.getRespuesta();
		recargaTarjetaView.setSucursalCuentaOrigen(cuenta.getNroSucursal());
		recargaTarjetaView.setCuentaOrigen(cuenta);

		return ejecutarRecarga(request, recargaTarjetaView, cliente, cuentaDestino);

	}

	/**
	 * Ejecutar recarga.
	 *
	 * @param request
	 *            the request
	 * @param recargaTarjetaView
	 *            the recarga tarjeta view
	 * @param cliente
	 *            the cliente
	 * @param cuentaDestino
	 *            the cuenta destino
	 * @return the respuesta
	 */
	private Respuesta<ComprobanteRecargaTarjetaView> ejecutarRecarga(HttpServletRequest request,
			ComprobanteRecargaTarjetaView recargaTarjetaView, Cliente cliente, Cuenta cuentaDestino) {
		try {
			if (importeDentroDeRangoMinMax(recargaTarjetaView.getImporteRecargaPesos())) {
				Respuesta<ResumenDetalleCuenta> cuentaOrigen = this.obtieneCtaOrigen(recargaTarjetaView, cliente);
				Respuesta<ComprobanteRecargaTarjetaDTO> respuestaDTO = recargaTarjetaBO
						.recargar(generateDatosRecargaTR(recargaTarjetaView, cuentaOrigen, cuentaDestino), cliente);

				if (respuestaDTO.getEstadoRespuesta() != EstadoRespuesta.OK) {
					crearLogEstadistica(obtenerEstadisticaBean(request, null,
							EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_TARJETA_RECARGABLES_RECARGA,
							EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR));
					return respuestaFactory.crearRespuestaError(ComprobanteRecargaTarjetaView.class,
							respuestaDTO.getItemsMensajeRespuesta());

				}

				ComprobanteRecargaTarjetaView comprobanteView = new ComprobanteRecargaTarjetaView();
				BeanUtils.copyProperties(comprobanteView, respuestaDTO.getRespuesta());
				comprobanteView.setSucursalCuentaOrigen(recargaTarjetaView.getSucursalCuentaOrigen());
				comprobanteView.setNroDeComprobante(respuestaDTO.getRespuesta().getNroComprobante());
				comprobanteView.setComision(respuestaDTO.getRespuesta().getComision());
				comprobanteView.setComisionTotalIVAIncluido(respuestaDTO.getRespuesta().getComisionTotal());
				comprobanteView.setTipoComprobanteRecarga(TipoComprobanteRecarga.RECARGAR);
				sessionParametros.setComprobanteTarjetaRecargable(comprobanteView);
				crearLogEstadistica(obtenerEstadisticaBean(request, comprobanteView.getNroComprobante(),
						EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_TARJETA_RECARGABLES_RECARGA,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK));
				return respuestaFactory.crearRespuestaOk(ComprobanteRecargaTarjetaView.class, comprobanteView);
			} else {
				crearLogEstadistica(obtenerEstadisticaBean(request, null,
						EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_TARJETA_RECARGABLES_RECARGA,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR));
				return respuestaFactory.crearRespuestaWarning(ComprobanteRecargaTarjetaView.class,
						obtieneMensajeImporteInvalido(), null);
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage(), e);
			crearLogEstadistica(obtenerEstadisticaBean(request, null,
					EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_TARJETA_RECARGABLES_RECARGA,
					EstadisticasTarjetasUtil.getCodigoEstadisticaError()));
			return respuestaFactory.crearRespuestaError(ComprobanteRecargaTarjetaView.class,
					obtieneMensajeErorGenerico(), null);
		}
	}

	/**
	 * Obtener cuenta tarjeta.
	 *
	 * @param cliente
	 *            the cliente
	 * @param nroTarjeta
	 *            the nro tarjeta
	 * @return the cuenta
	 */
	private Cuenta obtenerCuentaTarjeta(Cliente cliente, String nroTarjeta) {
		try {
			return cuentaBO.buscarCuentaByNroTarjetaRecargable(cliente.getCuentas(), nroTarjeta);
		} catch (Exception e) {
			Log.debug("NO SE ENCONTRO LA TARJETA", e);
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.
	 * RecargaTarjetaManager#programarRecargarTarjeta(javax.servlet.http.
	 * HttpServletRequest, ar.com.santanderrio.obp.servicios.tarjetas.web.view.
	 * ComprobanteRecargaTarjetaView,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<ComprobanteRecargaTarjetaView> programarRecargarTarjeta(HttpServletRequest request,
			ComprobanteRecargaTarjetaView comprobanteRecargaTarjetaView, Cliente cliente) {

		Respuesta<ComprobanteRecargaTarjetaView> respuestaFinal = new Respuesta<ComprobanteRecargaTarjetaView>();
		Respuesta<AbstractCuenta> respuestaAbstractCuenta = cuentaManager
				.obtenerCuentaPorId(comprobanteRecargaTarjetaView.getNroCuentaOrigen());
		if (respuestaAbstractCuenta.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			LOGGER.error("Ha ocurrido un error validando la cuenta origen.");
			respuestaFinal.setEstadoRespuesta(EstadoRespuesta.ERROR);
			cleanCuentaOrigen(respuestaFinal);
			return respuestaFinal;
		} else if (respuestaAbstractCuenta.getRespuesta() == null) {
			respuestaFinal = cargarMensajeRespuestaCuenta2(comprobanteRecargaTarjetaView.getNroCuentaOrigen(),
					respuestaFinal);
			cleanCuentaOrigen(respuestaFinal);
			return respuestaFinal;
		}
		LOGGER.info("Cuenta origen {} validada", comprobanteRecargaTarjetaView.getNroCuentaOrigen());
		comprobanteRecargaTarjetaView.setSucursalCuentaOrigen(respuestaAbstractCuenta.getRespuesta().getNroSucursal());
		// Codigo nuevo (10/02)

		Cuenta cuentaDestino = obtenerCuentaTarjeta(cliente, comprobanteRecargaTarjetaView.getNumeroTarjetaDestino());
		if (TarjetaUtils.isRecargableAdicional(cuentaDestino)) {
			return crearRespuestaErrorGenerico(CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
		}

		comprobanteRecargaTarjetaView.setSaldoCuentaOrigen(comprobanteRecargaTarjetaView.getSaldoCuentaOrigen()
				.replace(",", STRING_VACIO).replace(".", STRING_VACIO));

		Cuenta cuenta = (Cuenta) respuestaAbstractCuenta.getRespuesta();
		comprobanteRecargaTarjetaView.setCuentaOrigen(cuenta);

		Respuesta<ActionCode> respuestaRiesgo = rsaManager.analizar(comprobanteRecargaTarjetaView, null);
		if (ActionCode.DENY.equals(respuestaRiesgo.getRespuesta())) {
			cleanCuentaOrigen(respuestaFinal);
			return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.DENY_RSA,
					CodigoMensajeConstantes.DENY_RSA);
		}

		RecargaTarjetaView recargaTarjetaView = new RecargaTarjetaView();
		recargaTarjetaView.setImporteRecargaPesos(comprobanteRecargaTarjetaView.getImporteRecargaPesos());
		recargaTarjetaView.setNroTarjeta(comprobanteRecargaTarjetaView.getNumeroTarjetaDestino());
		recargaTarjetaView.setDivisa(comprobanteRecargaTarjetaView.getDivisa());
		recargaTarjetaView.setImporteRecargaPesos(comprobanteRecargaTarjetaView.getImporteRecargaPesos());
		recargaTarjetaView.setTipoCuentaOrigen(comprobanteRecargaTarjetaView.getTipoCuentaOrigen());
		recargaTarjetaView.setNroCuentaOrigen(comprobanteRecargaTarjetaView.getNroCuentaOrigen());
		recargaTarjetaView.setFechaInicio(comprobanteRecargaTarjetaView.getFechaInicio());
		recargaTarjetaView.setFechaDeRecarga(comprobanteRecargaTarjetaView.getFechaDeRecarga());
		recargaTarjetaView.setFechaProxRecarga(comprobanteRecargaTarjetaView.getFechaProxRecarga());
		recargaTarjetaView.setFrecuencia(comprobanteRecargaTarjetaView.getFrecuencia());
		recargaTarjetaView.setAbreviaturaTipoCuenta(comprobanteRecargaTarjetaView.getAbreviaturaTipoCuenta());

		Respuesta<ComprobanteRecargaTarjetaView> respuesta = ejecutarProgramarRecargaTarjeta(request,
				recargaTarjetaView, cliente, false);
		setTipoComprobanteRecarga(respuesta, TipoComprobanteRecarga.AGENDAR);
		sessionParametros.setComprobanteTarjetaRecargable(respuesta.getRespuesta());
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.
	 * RecargaTarjetaManager#modificarProgramarRecargarTarjeta(javax.servlet.
	 * http.HttpServletRequest,
	 * ar.com.santanderrio.obp.servicios.tarjetas.web.view.RecargaTarjetaView,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<ComprobanteRecargaTarjetaView> modificarProgramarRecargarTarjeta(HttpServletRequest request,
			RecargaTarjetaView recargaTarjetaView, Cliente cliente) {
		Respuesta<ComprobanteRecargaTarjetaView> respuesta = ejecutarProgramarRecargaTarjeta(request,
				recargaTarjetaView, cliente, true);
		setTipoComprobanteRecarga(respuesta, TipoComprobanteRecarga.MODIFICAR_AGENDAR);
		sessionParametros.setComprobanteTarjetaRecargable(respuesta.getRespuesta());
		return respuesta;
	}

	/**
	 * Sets the tipo comprobante recarga.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @param tipoComprobante
	 *            the tipo comprobante
	 */
	private void setTipoComprobanteRecarga(Respuesta<ComprobanteRecargaTarjetaView> respuesta,
			TipoComprobanteRecarga tipoComprobante) {
		if (respuesta.getRespuesta() != null) {
			respuesta.getRespuesta().setTipoComprobanteRecarga(tipoComprobante);
		}

	}

	/**
	 * Ejecutar programar recarga tarjeta.
	 *
	 * @param request
	 *            the request
	 * @param recargaTarjetaView
	 *            the recarga tarjeta view
	 * @param cliente
	 *            the cliente
	 * @param modificar
	 *            the modificar
	 * @return the respuesta
	 */
	private Respuesta<ComprobanteRecargaTarjetaView> ejecutarProgramarRecargaTarjeta(HttpServletRequest request,
			RecargaTarjetaView recargaTarjetaView, Cliente cliente, boolean modificar) {

		Respuesta<ComprobanteRecargaTarjetaDTO> respuestaDTO = null;
		Respuesta<ResumenDetalleCuenta> cuentaOrigen = null;

		try {
			if (importeDentroDeRangoMinMax(recargaTarjetaView.getImporteRecargaPesos())) {
				cuentaOrigen = this.obtieneCtaOrigen(recargaTarjetaView, cliente);

				if (modificar) {
					respuestaDTO = recargaTarjetaBO.modificarRecarga(
							generateDatosRecargaTR(recargaTarjetaView, cliente, cuentaOrigen), cliente);
				} else {
					respuestaDTO = recargaTarjetaBO.programarRecarga(
							generateDatosRecargaTR(recargaTarjetaView, cliente, cuentaOrigen), cliente);
				}

				if (respuestaDTO.getEstadoRespuesta() != EstadoRespuesta.OK) {
					crearLogEstadistica(obtenerEstadisticaBean(request, null,
							EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_TARJETA_RECARGABLES_MODIFICA,
							EstadisticasTarjetasUtil.getCodigoEstadisticaError()));
					return respuestaFactory.crearRespuestaError(ComprobanteRecargaTarjetaView.class,
							respuestaDTO.getItemsMensajeRespuesta());

				}

				ComprobanteRecargaTarjetaView comprobanteView = new ComprobanteRecargaTarjetaView();
				comprobanteView.setFechaProxRecarga(respuestaDTO.getRespuesta().getFechaDeProxRecarga());
				BeanUtils.copyProperties(comprobanteView, respuestaDTO.getRespuesta());
				comprobanteView.setSucursalCuentaOrigen(cuentaOrigen.getRespuesta().getNroSucursal());
				crearLogEstadistica(obtenerEstadisticaBean(request, respuestaDTO.getRespuesta().getNroComprobante(),
						EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_TARJETA_RECARGABLES_MODIFICA,
						EstadisticasTarjetasUtil.getCodigoEstadisticaOk()));
				return respuestaFactory.crearRespuestaOk(ComprobanteRecargaTarjetaView.class, comprobanteView);
			} else {
				crearLogEstadistica(obtenerEstadisticaBean(request, null,
						EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_TARJETA_RECARGABLES_MODIFICA,
						EstadisticasTarjetasUtil.getCodigoEstadisticaError()));

				return respuestaFactory.crearRespuestaWarning(ComprobanteRecargaTarjetaView.class,
						obtieneMensajeImporteInvalido(), null);
			}

		} catch (Exception e) {
			LOGGER.info(e.getMessage(), e);
			crearLogEstadistica(obtenerEstadisticaBean(request, null,
					EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_TARJETA_RECARGABLES_MODIFICA,
					EstadisticasTarjetasUtil.getCodigoEstadisticaError()));
			return respuestaFactory.crearRespuestaError(ComprobanteRecargaTarjetaView.class,
					obtieneMensajeErorGenerico(), null);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.
	 * RecargaTarjetaManager#bajaRecargarTarjeta(javax.servlet.http.
	 * HttpServletRequest,
	 * ar.com.santanderrio.obp.servicios.tarjetas.web.view.RecargaTarjetaView,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<ComprobanteFeedbackView> bajaRecargarTarjeta(HttpServletRequest request,
			RecargaTarjetaView recargaTarjetaView, Cliente cliente) {

		try {
			Cuenta cuentaDestino = cuentaBO.buscarCuentaByNroTarjetaRecargable(cliente.getCuentas(),
					recargaTarjetaView.getNroTarjeta());
			if (TarjetaUtils.isRecargableAdicional(cuentaDestino)) {
				return crearRespuestaErrorGenerico(CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
			}

			Respuesta<AdhesionTarjeta> adhesionRespuesta = encabezadoTarjetasBO.obtenerAdhesionTarjeta(cliente,
					cuentaDestino);
			AdhesionTarjeta adTarjeta = adhesionRespuesta.getRespuesta();

			IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta(adTarjeta.getSucursalCuentaDebito(),
					adTarjeta.getNroCuentaDebito());
			Cuenta cuentaOrigen = (Cuenta) cuentaBO.buscarCuentaPorId(cliente, identificacionCuenta);
			DatosRecargaTREntity recargaTR = new DatosRecargaTREntity();
			recargaTR.setEsRecargaProgramada(false);
			recargaTR.setImporte(adTarjeta.getImporteDeAgendamiento().toString());
			recargaTR.setNroTarjeta(cuentaDestino.getNroTarjetaCredito().trim());// validar
			recargaTR.setNroCuentaOrigen(adTarjeta.getNroCuentaDebito());
			recargaTR.setNroCuentaOrigenSinFormato(cuentaOrigen.obtenerNroCuentaFormateado());// EN
																								// FORMATO
																								// 033-361253/2
			recargaTR.setFechaDeRecarga(ISBANStringUtils.formatearFecha(adTarjeta.getFechaDeProximaAgenda()));
			recargaTR.setSucursalCuentaOrigen(adTarjeta.getSucursalCuentaDebito());
			recargaTR.setTipoCuentaOrigenAbreviatura(cuentaOrigen.getTipoCuentaEnum().getAbreviatura());
			recargaTR.setFechaDeProxRecarga(ISBANStringUtils.formatearFecha(adTarjeta.getFechaDeProximaAgenda()));
			recargaTR.setSucursalCuentaDestino(cuentaDestino.getNroSucursal().trim());
			// validar
			recargaTR.setFechaInicio(ISBANStringUtils.formatearFecha(new Date()));
			recargaTR.setFrecuencia(adTarjeta.getFormaDePago().getCodigo());
			recargaTR.setDivisa(DIVISA);
			recargaTR.setNroCuentaDestino(cuentaDestino.getNroCuentaProducto().trim());
			// validar
			recargaTR.setTipoCuentaDestino(VISA);
			Respuesta<ComprobanteRecargaTarjetaDTO> respuesta = recargaTarjetaBO.bajaProgramacion(recargaTR, cliente);

			if (respuesta.getEstadoRespuesta() != EstadoRespuesta.OK) {
				crearLogEstadistica(obtenerEstadisticaBean(request, null,
						EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_TARJETA_RECARGABLES_BAJA,
						EstadisticasTarjetasUtil.getCodigoEstadisticaError()));
				return respuestaFactory.crearRespuestaError(ComprobanteFeedbackView.class,
						respuesta.getItemsMensajeRespuesta());
			}

			ComprobanteRecargaTarjetaView comprobante = new ComprobanteRecargaTarjetaView();
			BeanUtils.copyProperties(comprobante, respuesta.getRespuesta());

			crearLogEstadistica(obtenerEstadisticaBean(request, respuesta.getRespuesta().getNroComprobante(),
					EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_TARJETA_RECARGABLES_BAJA,
					EstadisticasTarjetasUtil.getCodigoEstadisticaOk()));

			ComprobanteFeedbackView comprobanteFeedback = new ComprobanteFeedbackView();
			comprobanteFeedback.setMensajeFeedback(respuesta.getRespuesta().getMensaje());
			comprobanteFeedback.setNroDeComprobante(respuesta.getRespuesta().getNroComprobante());
			comprobanteFeedback.setFechaHora(respuesta.getRespuesta().getFechaHora());
			comprobanteFeedback.setLegalesSEUO(respuesta.getRespuesta().getLegalesSEO());
			comprobanteFeedback.setReintentar(String.valueOf(Boolean.FALSE));
			return respuestaFactory.crearRespuestaOk(ComprobanteFeedbackView.class, comprobanteFeedback);
		} catch (Exception e) {
			crearLogEstadistica(obtenerEstadisticaBean(request, null,
					EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_TARJETA_RECARGABLES_BAJA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR));
			LOGGER.info(e.getMessage(), e);
			return respuestaFactory.crearRespuestaError(ComprobanteFeedbackView.class, obtieneMensajeErorGenerico(),
					null);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.
	 * RecargaTarjetaManager#tarjetasCalendario()
	 */
	@Override
	public Respuesta<TarjetasView> tarjetasCalendario() {
		try {

			Respuesta<TarjetasView> respuestaView = inicioTarjetasManager.getTarjetas(null);
			respuestaView.setEstadoRespuesta(EstadoRespuesta.OK);

			for (Iterator<TarjetaView> iter = respuestaView.getRespuesta().getTarjetas().iterator(); iter.hasNext();) {
				TarjetaView tarjetaView = iter.next();
				if (!tarjetaView.getIsRecargable()) {
					iter.remove();
				}
			}

			return respuestaView;
		} catch (Exception ex) {
			LOGGER.error(SERVICIO_DEVOLVIO_LOG_LABEL, ex);
			return crearRespuestaError();
		}
	}

	/**
	 * Armar respuesta no ok.
	 *
	 * @return the respuesta
	 */
	private Respuesta<TarjetasView> crearRespuestaError() {
		crearEstadisticaError();
		return crearRespuestaErrorGenerico(CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
	}

	/**
	 * Crear estadistica error.
	 */
	private void crearEstadisticaError() {
		this.crearLogEstadistica(EstadisticasTarjetasUtil.getCodigoEstadisticaTarjetas(),
				EstadisticasTarjetasUtil.getCodigoEstadisticaError());
	}

	/**
	 * Crear log estadistica.
	 *
	 * @param codTransaccion
	 *            the cod transaccion
	 * @param codigoError
	 *            the codigo error
	 */
	public void crearLogEstadistica(String codTransaccion, String codigoError) {
		estadisticaManager.add(codTransaccion, codigoError);
	}

	/**
	 * Crear log estadistica.
	 *
	 * @param estadistica
	 *            the estadistica
	 */
	public void crearLogEstadistica(Estadistica estadistica) {
		estadisticaManager.add(estadistica);
	}

	/**
	 * Respuesta ERROR TipoError.ERROR_CARGA_CUOTAS_PENDIENTE. (110005)
	 *
	 * @param <T>
	 *            the generic type
	 * @param codigoError
	 *            the codigo error
	 * @return the respuesta
	 */
	public <T> Respuesta<T> crearRespuestaErrorGenerico(String codigoError) {
		return casuistica.crearRespuestaError(null, TipoError.ERROR_GENERICO, codigoError);
	}

	/**
	 * Obtiene cta origen.
	 *
	 * @param recargaTarjetaView
	 *            the recarga tarjeta view
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	private Respuesta<ResumenDetalleCuenta> obtieneCtaOrigen(RecargaTarjetaView recargaTarjetaView, Cliente cliente)
			throws BusinessException {
		return cuentaBO.obtenerCuenta(cliente, recargaTarjetaView.getNroCuentaOrigen());
	}

	/**
	 * Obtiene cta origen.
	 *
	 * @param recargaTarjetaView
	 *            the recarga tarjeta view
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	private Respuesta<ResumenDetalleCuenta> obtieneCtaOrigen(ComprobanteRecargaTarjetaView recargaTarjetaView,
			Cliente cliente) throws BusinessException {
		return cuentaBO.obtenerCuenta(cliente, recargaTarjetaView.getNroCuentaOrigen());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.
	 * RecargaTarjetaManager#generarComprobanteRecarga()
	 */
	@Override
	public Respuesta<ReporteView> generarComprobanteRecarga() {

		Respuesta<Reporte> reporteRespuesta = recargaTarjetaBO
				.generarComprobanteRecarga(sessionParametros.getComprobanteTarjetaRecargable());
		Respuesta<ReporteView> respuestaView = Respuesta.copy(ReporteView.class, reporteRespuesta);
		if (reporteRespuesta.getRespuesta() != null) {
			ReporteView reporteView = ReporteView.fromReporte(reporteRespuesta.getRespuesta());
			respuestaView.setRespuesta(reporteView);
		}

		estadisticaManager.add(EstadisticasConstants.CARGA_DE_TARJETA_RECARGABLE,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		estadisticaManager.add(EstadisticasConstants.DESCARGA_COMPROBANTE_PDF,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaView;
	}

	/**
	 * Generate datos recarga TR.
	 *
	 * @param recargaTv
	 *            the recarga tv
	 * @param cliente
	 *            the cliente
	 * @param cuentaOrigen
	 *            the cuenta origen
	 * @return the datos recarga TR entity
	 * @throws BusinessException
	 *             the business exception
	 */
	private DatosRecargaTREntity generateDatosRecargaTR(RecargaTarjetaView recargaTv, Cliente cliente,
			Respuesta<ResumenDetalleCuenta> cuentaOrigen) throws BusinessException {
		Cuenta cuentaDestino = cuentaBO.buscarCuentaByNroTarjetaRecargable(cliente.getCuentas(),
				recargaTv.getNroTarjeta());
		DatosRecargaTREntity datosRecargaTR = new DatosRecargaTREntity();
		datosRecargaTR.setEsRecargaProgramada(false);
		datosRecargaTR.setNroTarjeta(cuentaDestino.getNroTarjetaCredito().trim());
		datosRecargaTR.setImporte(recargaTv.getImporteRecargaPesos());
		datosRecargaTR.setNroCuentaOrigen(this.getNroCtaOrigen(recargaTv.getNroCuentaOrigen().trim()));// formatear
																										// 033-361253/2
		datosRecargaTR.setNroCuentaOrigenSinFormato(recargaTv.getNroCuentaOrigen().trim());// EN
																							// FORMATO
																							// 033-361253/2
		datosRecargaTR.setTipoCuentaOrigenAbreviatura(recargaTv.getAbreviaturaTipoCuenta());
		datosRecargaTR.setTipoCuentaOrigen(recargaTv.getTipoCuentaOrigen());
		datosRecargaTR.setFechaDeRecarga(recargaTv.getFechaDeRecarga());
		datosRecargaTR.setSucursalCuentaOrigen(cuentaOrigen.getRespuesta().getNroSucursal().trim());
		datosRecargaTR.setSucursalCuentaDestino(cuentaDestino.getNroSucursal().trim());
		// cuando es una modificacion la fecha de proxima recarga es la fecha de
		// inicio.
		if (recargaTv.getFechaProxRecarga() != null) {
			datosRecargaTR.setFechaInicio(recargaTv.getFechaProxRecarga());
		} else {
			datosRecargaTR.setFechaInicio(recargaTv.getFechaInicio());
		}
		datosRecargaTR.setFechaDeProxRecarga(recargaTv.getFechaProxRecarga());
		datosRecargaTR.setFrecuencia(recargaTv.getFrecuencia());
		datosRecargaTR.setTipoCuentaDestino(VISA);
		datosRecargaTR.setNroCuentaDestino(cuentaDestino.getNroCuentaProducto().trim());
		datosRecargaTR.setDivisa(DIVISA);
		return datosRecargaTR;
	}

	/**
	 * Generate datos recarga TR.
	 *
	 * @param recargaTarjetaView
	 *            the recarga tarjeta view
	 * @param cuentaOrigen
	 *            the cuenta origen
	 * @param cuentaDestino
	 *            the cuenta destino
	 * @return the datos recarga TR entity
	 */
	private DatosRecargaTREntity generateDatosRecargaTR(ComprobanteRecargaTarjetaView recargaTarjetaView,
			Respuesta<ResumenDetalleCuenta> cuentaOrigen, Cuenta cuentaDestino) {
		DatosRecargaTREntity datosRecargaTR = new DatosRecargaTREntity();
		datosRecargaTR.setEsRecargaProgramada(false);
		datosRecargaTR.setNroTarjeta(cuentaDestino.getNroTarjetaCredito().trim());
		datosRecargaTR.setImporte(recargaTarjetaView.getImporteRecargaPesos());
		datosRecargaTR.setNroCuentaOrigen(this.getNroCtaOrigen(recargaTarjetaView.getNroCuentaOrigen().trim()));// formatear
																												// 033-361253/2
		datosRecargaTR.setNroCuentaOrigenSinFormato(recargaTarjetaView.getNroCuentaOrigen().trim());// EN
																									// FORMATO
																									// 033-361253/2
		datosRecargaTR.setTipoCuentaOrigenAbreviatura(recargaTarjetaView.getAbreviaturaTipoCuenta());
		datosRecargaTR.setTipoCuentaOrigen(recargaTarjetaView.getTipoCuentaOrigen());
		datosRecargaTR.setFechaDeRecarga(recargaTarjetaView.getFechaDeRecarga());
		datosRecargaTR.setSucursalCuentaOrigen(cuentaOrigen.getRespuesta().getNroSucursal().trim());
		datosRecargaTR.setSucursalCuentaDestino(cuentaDestino.getNroSucursal().trim());
		// cuando es una modificacion la fecha de proxima recarga es la fecha de
		// inicio.
		if (recargaTarjetaView.getFechaProxRecarga() != null) {
			datosRecargaTR.setFechaInicio(recargaTarjetaView.getFechaProxRecarga());
		} else {
			datosRecargaTR.setFechaInicio(recargaTarjetaView.getFechaInicio());
		}
		datosRecargaTR.setFechaDeProxRecarga(recargaTarjetaView.getFechaProxRecarga());
		datosRecargaTR.setFrecuencia(recargaTarjetaView.getFrecuencia());
		datosRecargaTR.setTipoCuentaDestino(VISA);
		datosRecargaTR.setNroCuentaDestino(cuentaDestino.getNroCuentaProducto().trim());
		datosRecargaTR.setDivisa(DIVISA);
		return datosRecargaTR;
	}

	/**
	 * sonar error. obliga a darle visibilidad publica
	 *
	 * @param nroCtaCanonico
	 *            the nro cta canonico
	 * @return the nro cta origen
	 */
	public String getNroCtaOrigen(String nroCtaCanonico) {
		return nroCtaCanonico.substring(4, 10) + nroCtaCanonico.substring(11, 12);
	}

	/**
	 * sonar error. obliga a darle visibilidad publica
	 *
	 * @param importeARecargar
	 *            the importe A recargar
	 * @return true, if successful
	 * @throws ParseException
	 *             the parse exception
	 * @throws NumberFormatException
	 *             the number format exception
	 * @throws ImporteConvertException
	 *             the importe convert exception
	 */
	public boolean importeDentroDeRangoMinMax(String importeARecargar) throws ParseException, ImporteConvertException {
		boolean respuesta = true;
		if (ISBANStringUtils.convertirImporte(importeARecargar)
				.compareTo(ISBANStringUtils.convertirImporte(this.importeMinRec)) == -1
				|| ISBANStringUtils.convertirImporte(importeARecargar)
						.compareTo(ISBANStringUtils.convertirImporte(this.importeMaxRec)) == 1) {
			respuesta = false;
		}
		return respuesta;
	}

	/**
	 * sonar error. obliga a darle visibilidad publica
	 *
	 * @param fechaDeRecarga
	 *            the fecha de recarga
	 * @return true, if successful
	 * @throws ParseException
	 *             the parse exception
	 */
	public boolean fechaDeRecargaIgualFechaActual(String fechaDeRecarga) throws ParseException {
		SimpleDateFormat formatoFechaR = new SimpleDateFormat("dd/MM/yyyy");
		Date dateFechaR = formatoFechaR.parse(fechaDeRecarga);
		return formatoFechaR.format(dateFechaR).equals(formatoFechaR.format(new Date()));
	}

	/**
	 * Obtiene mensaje saldo insuficiente.
	 *
	 * @return the string
	 */
	public String obtieneMensajeSaldoInsuficiente() {
		return mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_RECARGA_SALDO_INSUFICIENTE)
				.getMensaje();
	}

	/**
	 * Obtiene mensaje importe invalido.
	 *
	 * @return the string
	 */
	public String obtieneMensajeImporteInvalido() {

		return mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_IMPORTE_INVALIDO).getMensaje();
	}

	/**
	 * Obtiene mensaje fecha invalida.
	 *
	 * @return the string
	 */
	public String obtieneMensajeFechaInvalida() {

		return mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_FECHA_INVALIDA).getMensaje();
	}

	/**
	 * Obtiene mensaje eror generico.
	 *
	 * @return the string
	 */
	public String obtieneMensajeErorGenerico() {

		return mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_RECARGA_TARJETA).getMensaje();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.
	 * RecargaTarjetaManager#obtenerDatosBajaAdhesionTarjeta(java.lang.String,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<BajaAdhesionTarjConfirmacion> obtenerDatosBajaAdhesionTarjeta(String tarjetaNro, Cliente cliente) {
		BajaAdhesionTarjConfirmacion bajaConfirm = new BajaAdhesionTarjConfirmacion();
		try {
			Cuenta cuentaDestino = cuentaBO.buscarCuentaByNroTarjetaRecargable(cliente.getCuentas(), tarjetaNro);
			Respuesta<AdhesionTarjeta> adhesionRespuesta = encabezadoTarjetasBO
					.obtenerAdhesionTarjeta(sesionCliente.getCliente(), cuentaDestino);
			AdhesionTarjeta adhesion = adhesionRespuesta.getRespuesta();
			bajaConfirm.setNroTarjeta(tarjetaNro);
			bajaConfirm.setFechaProxRecarga(ISBANStringUtils.formatearFecha(adhesion.getFechaDeProximaAgenda()));

			Cuenta cuenta = cliente.getCuentaPorNumero(StringUtils.leftPad(adhesion.getNroCuentaDebito(), 16, "0"));
			bajaConfirm.setTipoCuenta(cuenta.getTipoCuentaEnum().getDescripcion());
			bajaConfirm.setNroCuenta(ISBANStringUtils.formatearSucursal(cuenta.getNroSucursal()) + "-"
					+ ISBANStringUtils.formatearNumeroCuenta(cuenta.getNroCuentaProducto()));
			bajaConfirm.setNroCuentaProducto(cuenta.getNroCuentaProducto());
			bajaConfirm.setAliasCuenta(cuenta.getAlias());
			bajaConfirm.setFrecuencia(adhesion.getFormaDePago().getDescripcionCorta());
			bajaConfirm.setIsDarBajaRecarga(true);
			bajaConfirm.setImporte(ISBANStringUtils.agregadorDecimales(adhesion.getImporteDeAgendamiento().toString()));
			bajaConfirm.setStopDebitRecarga(true);
			bajaConfirm.setMensaje("<h1>¿Realizar stop debit?</h1> <p> Al débito automático de VISA Gastos .</p>");

			return respuestaFactory.crearRespuestaOk(BajaAdhesionTarjConfirmacion.class, bajaConfirm);
		} catch (BusinessException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.
	 * RecargaTarjetaManager#getDatosTarjetaRecargable(ar.com.santanderrio.obp.
	 * servicios.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.tarjetas.view.Reintentar)
	 */
	@Override
	public Respuesta<RecargaTarjetaInfoView> getDatosTarjetaRecargable(Cliente cliente, Reintentar reintentar) {
		if (!"true".equals(reintentar.getReintentar())) {
			sessionParametros.setContador(new ContadorIntentos(2));
		}

		Respuesta<TarjetasView> tarjetas = inicioTarjetasManager.getTarjetas(null);
		Respuesta<List<TarjetaView>> tarjetasRecargables = filtrarTarjetasRecargables(tarjetas, true);

		if (EstadoRespuesta.ERROR.equals(tarjetasRecargables.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.INGRESO_TARJETA_RECARGA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(RecargaTarjetaInfoView.class,
					tarjetasRecargables.getItemsMensajeRespuesta());
		}

		Respuesta<CuentasView> cuentas = cuentaManager.getCuentasSaldo();

		if (EstadoRespuesta.ERROR.equals(cuentas.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.INGRESO_TARJETA_RECARGA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(RecargaTarjetaInfoView.class,
					cuentas.getItemsMensajeRespuesta());
		}
		estadisticaManager.add(EstadisticasConstants.INGRESO_TARJETA_RECARGA,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(RecargaTarjetaInfoView.class,
				new RecargaTarjetaInfoView(this.importeMinRec, this.importeMaxRec, tarjetasRecargables.getRespuesta(),
						cuentas.getRespuesta().getCuentas()));
	}

	/**
	 * Filtrar tarjetas recargables.
	 *
	 * @param tarjetas
	 *            the tarjetas
	 * @param isTitular
	 *            the is titular
	 * @return the respuesta
	 */
	private Respuesta<List<TarjetaView>> filtrarTarjetasRecargables(Respuesta<TarjetasView> tarjetas,
			Boolean isTitular) {
		List<TarjetaView> tarjetasRecargableViewList = new ArrayList<TarjetaView>();

		for (TarjetaView tarjetaView : tarjetas.getRespuesta().getTarjetas()) {
			if (tarjetaView.getIsRecargable() && (isTitular == null || (isTitular && !tarjetaView.getIsAdicional()))) {

				TarjetaView tarjetaRecargableView = new TarjetaView();
				tarjetaRecargableView.setMarca(tarjetaView.getMarca());
				tarjetaRecargableView.setNumero(tarjetaView.getNumero());
				tarjetaRecargableView.setIsRecargable(tarjetaView.getIsRecargable());
				tarjetaRecargableView.setIsAdicional(tarjetaView.getIsAdicional());
				tarjetasRecargableViewList.add(tarjetaRecargableView);
			}
		}

		Respuesta<List<TarjetaView>> respuesta = new Respuesta<List<TarjetaView>>();
		if (tarjetasRecargableViewList.isEmpty()) {
			respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		} else {
			respuesta.setRespuesta(tarjetasRecargableViewList);
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		}
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.
	 * RecargaTarjetaManager#stopDebitTarjetaRecargable(ar.com.santanderrio.obp.
	 * servicios.tarjetas.web.view.RecargaTarjetaView,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<ComprobanteFeedbackView> stopDebitTarjetaRecargable(HttpServletRequest request,
			RecargaTarjetaView recargaTarjetaView, Cliente cliente) {
		try {
			Cuenta cuentaDestino = cuentaBO.buscarCuentaByNroTarjetaRecargable(cliente.getCuentas(),
					recargaTarjetaView.getNroTarjeta());

			Respuesta<AdhesionTarjeta> adhesionRespuesta = encabezadoTarjetasBO.obtenerAdhesionTarjeta(cliente,
					cuentaDestino);
			AdhesionTarjeta adhesionTarjeta = adhesionRespuesta.getRespuesta();

			IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta(
					adhesionTarjeta.getSucursalCuentaDebito(), adhesionTarjeta.getNroCuentaDebito());
			Cuenta cuentaOrigen = (Cuenta) cuentaBO.buscarCuentaPorId(cliente, identificacionCuenta);
			DatosRecargaTREntity datosRecarga = new DatosRecargaTREntity();
			datosRecarga.setNroTarjeta(cuentaDestino.getNroTarjetaCredito().trim());
			datosRecarga.setEsRecargaProgramada(false);
			datosRecarga.setImporte(adhesionTarjeta.getImporteDeAgendamiento().toString());
			datosRecarga.setNroCuentaOrigenSinFormato(cuentaOrigen.obtenerNroCuentaFormateado());// EN
																									// FORMATO
																									// 033-361253/2
			datosRecarga.setNroCuentaOrigen(adhesionTarjeta.getNroCuentaDebito());
			datosRecarga.setNroCuentaDestino(cuentaDestino.getNroCuentaProducto().trim());
			datosRecarga.setTipoCuentaOrigenAbreviatura(cuentaOrigen.getTipoCuentaEnum().getAbreviatura());
			datosRecarga.setSucursalCuentaOrigen(adhesionTarjeta.getSucursalCuentaDebito());
			datosRecarga.setSucursalCuentaDestino(cuentaDestino.getNroSucursal().trim());
			datosRecarga.setTipoCuentaDestino(VISA);
			datosRecarga.setFechaDeRecarga(ISBANStringUtils.formatearFecha(adhesionTarjeta.getFechaDeProximaAgenda()));
			datosRecarga.setFechaInicio(ISBANStringUtils.formatearFecha(new Date()));
			datosRecarga
					.setFechaDeProxRecarga(ISBANStringUtils.formatearFecha(adhesionTarjeta.getFechaDeProximaAgenda()));
			datosRecarga.setFrecuencia(adhesionTarjeta.getFormaDePago().getCodigo());
			datosRecarga.setDivisa(DIVISA);
			Respuesta<ComprobanteRecargaTarjetaDTO> respuestaDTO = recargaTarjetaBO.stopDebit(datosRecarga, cliente);

			if (EstadoRespuesta.OK != respuestaDTO.getEstadoRespuesta()) {
				crearLogEstadistica(obtenerEstadisticaBean(request, null,
						EstadisticasConstants.FEEDBACK_STOPDEBIT,
						EstadisticasTarjetasUtil.getCodigoEstadisticaError()));
				return respuestaFactory.crearRespuestaError(ComprobanteFeedbackView.class,
						respuestaDTO.getItemsMensajeRespuesta());
			}

			ComprobanteRecargaTarjetaView comprobanteView = new ComprobanteRecargaTarjetaView();
			BeanUtils.copyProperties(comprobanteView, respuestaDTO.getRespuesta());
			comprobanteView.setTipoComprobanteRecarga(TipoComprobanteRecarga.STOP_DEBIT);
			comprobanteView.setSucursalCuentaOrigen(cuentaOrigen.getNroSucursal());
			comprobanteView.setFrecuencia(adhesionTarjeta.getFormaDePago().getDescripcionCorta());
			comprobanteView.setTipoCuentaOrigen(cuentaOrigen.getTipoCuentaEnum().getDescripcion());
			comprobanteView.setFechaProxRecarga(datosRecarga.getFechaDeProxRecarga());
			sessionParametros.setComprobanteTarjetaRecargable(comprobanteView);

			crearLogEstadistica(obtenerEstadisticaBean(request, respuestaDTO.getRespuesta().getNroComprobante(),
					EstadisticasConstants.FEEDBACK_STOPDEBIT,
					EstadisticasTarjetasUtil.getCodigoEstadisticaOk()));

			ComprobanteFeedbackView comprobante = new ComprobanteFeedbackView();
			comprobante.setMensajeFeedback(comprobanteView.getMensaje());
			comprobante.setNroDeComprobante(comprobanteView.getNroComprobante());
			comprobante.setFechaHora(comprobanteView.getFechaHora());
			comprobante.setLegalesSEUO(comprobanteView.getLegalesSEO());
			comprobante.setReintentar(String.valueOf(Boolean.FALSE));

			return respuestaFactory.crearRespuestaOk(ComprobanteFeedbackView.class, comprobante);
		} catch (Exception e) {
			LOGGER.info(e.getMessage(), e);
			crearLogEstadistica(obtenerEstadisticaBean(request, null,
					EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_TARJETA_RECARGABLES_BAJA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR));
			return respuestaFactory.crearRespuestaError(ComprobanteFeedbackView.class, obtieneMensajeErorGenerico(),
					null);
		}

	}

	/**
	 * Cargar mensaje respuesta cuenta. sonar error. obliga a darle visibilidad
	 * publica
	 * 
	 * @param nroCuenta
	 *            the nro cuenta
	 * @param respuesta
	 *            the respuesta
	 * @return the respuesta
	 */

	public Respuesta<ComprobanteRecargaTarjetaView> cargarMensajeRespuestaCuenta2(String nroCuenta,
			Respuesta<ComprobanteRecargaTarjetaView> respuesta) {
		LOGGER.info("Cuenta origen {} no corresponde con el cliente", nroCuenta);
		respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		String respMensaje = mensajeBO.obtenerMensajePorCodigo(ERROR_VALIDACION).getMensaje();
		ItemMensajeRespuesta item = new ItemMensajeRespuesta();
		item.setMensaje(respMensaje);
		item.setTag("cuenta");
		respuesta.add(item);
		LOGGER.error(item.getMensaje());

		return respuesta;
	}

	/**
	 * Gets the encabezado tarjetas BO.
	 *
	 * @return the encabezado tarjetas BO
	 */
	public EncabezadoTarjetasBO getEncabezadoTarjetasBO() {
		return encabezadoTarjetasBO;
	}

	/**
	 * Sets the encabezado tarjetas BO.
	 *
	 * @param encabezadoTarjetasBO
	 *            the new encabezado tarjetas BO
	 */
	public void setEncabezadoTarjetasBO(EncabezadoTarjetasBO encabezadoTarjetasBO) {
		this.encabezadoTarjetasBO = encabezadoTarjetasBO;
	}

	/**
	 * Obtener estadistica bean.
	 *
	 * @param request
	 *            the request
	 * @param nroComprobante
	 *            the nro comprobante
	 * @param codigoTransaccion
	 *            the codigo transaccion
	 * @param resultado
	 *            the resultado
	 * @return the estadistica
	 */
	public Estadistica obtenerEstadisticaBean(HttpServletRequest request, String nroComprobante,
			String codigoTransaccion, String resultado) {
		Estadistica estadistica = new Estadistica();
		estadistica.setCodigoTransaccion(codigoTransaccion);
		estadistica.setCodigoError(resultado);
		estadistica.setNroComprobante(nroComprobante);
		estadistica.setIp(NetworkUtil.getRemoteIp(request));
		return estadistica;
	}

	/**
	 * Gets the importe min rec.
	 *
	 * @return the importe min rec
	 */
	public String getImporteMinRec() {
		return importeMinRec;
	}

	/**
	 * Sets the importe min rec.
	 *
	 * @param importeMinRec
	 *            the new importe min rec
	 */
	public void setImporteMinRec(String importeMinRec) {
		this.importeMinRec = importeMinRec;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.
	 * RecargaTarjetaManager#vaciarDesafio()
	 */
	@Override
	public void vaciarDesafio() {
		sessionParametros.setDesafioEnCurso(null);
	}

	/**
	 * Gets the importe max rec.
	 *
	 * @return the importe max rec
	 */
	public String getImporteMaxRec() {
		return importeMaxRec;
	}

	/**
	 * Sets the importe max rec.
	 *
	 * @param importeMaxRec
	 *            the new importe max rec
	 */
	public void setImporteMaxRec(String importeMaxRec) {
		this.importeMaxRec = importeMaxRec;
	}

	/**
	 * Gets the session parametros.
	 *
	 * @return the session parametros
	 */
	public SesionParametros getSessionParametros() {
		return sessionParametros;
	}

	/**
	 * Sets the session parametros.
	 *
	 * @param sesionParametros
	 *            the new session parametros
	 */
	public void setSessionParametros(SesionParametros sesionParametros) {
		this.sessionParametros = sesionParametros;
	}

	/**
	 * limpia la cuenta origen.
	 *
	 * @param respuestaFinal
	 *            the respuesta final
	 */
	private void cleanCuentaOrigen(Respuesta<ComprobanteRecargaTarjetaView> respuestaFinal) {
		if (respuestaFinal != null && respuestaFinal.getRespuesta() != null) {
			respuestaFinal.getRespuesta().setCuentaOrigen(null);
		}
	}

	@Override
	public Respuesta<String> tooltipAgendamientoRecarga() {
		Mensaje mensajeTooltip = mensajeBO
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.TOOLTIP_INFORMATIVO_AGENDAMIENTO_RECARGA_TARJETA);
		if (StringUtils.isBlank(mensajeTooltip.getMensaje())) {
			return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		return respuestaFactory.crearRespuestaOk(mensajeTooltip.getMensaje());
	}
}
