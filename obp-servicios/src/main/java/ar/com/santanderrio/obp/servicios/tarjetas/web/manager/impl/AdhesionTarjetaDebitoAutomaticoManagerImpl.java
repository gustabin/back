/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.manager.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuentaTarjeta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.bo.PagoTarjetaCreditoBO;
import ar.com.santanderrio.obp.servicios.pagos.bo.PagosPendientesBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.DebitoAutomatico;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.AdhesionTarjetaDebitoAutomaticoBO;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.DatosAdhesionDebitoTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.view.BajaAdhesionTarjView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.ComprobanteFeedbackView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.CuentasAdhesionDebitoAutomaticoView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.DatosAdhesionDebitoTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.InfoTarjetaAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.NroTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.Reintentar;
import ar.com.santanderrio.obp.servicios.tarjetas.view.TarjetaModificacionAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.TarjetasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.AdhesionTarjetaDebitoAutomaticoManager;

/**
 * The Class AdhesionTarjetaDebitoAutomaticoManagerImpl.
 * 
 * @author mariano.g.pulera
 * 
 */
@Component
public class AdhesionTarjetaDebitoAutomaticoManagerImpl implements AdhesionTarjetaDebitoAutomaticoManager {

	/** The adhesion tarjeta debito automatico bo. */
	@Autowired
	private AdhesionTarjetaDebitoAutomaticoBO adhesionTarjetaDebitoAutomaticoBo;

	/** The respuesta factory. */
	@Autowired
	RespuestaFactory respuestaFactory;

	/** The session parametros. */
	@Autowired
	private SesionParametros sessionParametros;

	/** The cuenta manager. */
	@Autowired
	CuentaManager cuentaManager;

	/** The pagos pendientes bo. */
	@Autowired
	PagosPendientesBO pagosPendientesBo;

	/** The estadistica manager. */
	@Autowired
	EstadisticaManager estadisticaManager;

	/** The mensaje bo. */
	@Autowired
	MensajeBO mensajeBo;

	/** The pago tarjeta credito BO. */
	@Autowired
	PagoTarjetaCreditoBO pagoTarjetaCreditoBO;

	/** The cuenta bo. */
	@Autowired
	CuentaBO cuentaBo;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AdhesionTarjetaDebitoAutomaticoManagerImpl.class);

	/** The Constant NRO_TC. */
	public static final String NRO_TC = "00000000000000000000";

	/** The Constant CODIGO_MENSAJE_AVISO_MONTO_ADHESION. */
	private static final String CODIGO_MENSAJE_AVISO_MONTO_ADHESION = "1111";

	/** The Constant CODIGO_MENSAJE_INFORMATIVO_MODIFICAR_ADHESION. */
	private static final String CODIGO_MENSAJE_INFORMATIVO_MODIFICAR_ADHESION = "1224";

	/** The Constant CODIGO_MENSAJE_CONFIGURACION_MODIFICAR_ADHESION. */
	private static final String CODIGO_MENSAJE_CONFIGURACION_MODIFICAR_ADHESION = "1225";

	/** The Constant CODIGO_MENSAJE_MODIFICACION_FEEDBACK_ERROR. */
	private static final String CODIGO_MENSAJE_MODIFICACION_FEEDBACK_ERROR = "1227";

	/** The Constant CODIGO_MENSAJE_ERROR. */
	private static final String CODIGO_MENSAJE_ERROR = "1228";

	/** The Constant CODIGO_MENSAJE_ERROR_GENERICO. */
	private static final String CODIGO_MENSAJE_ERROR_GENERICO = "1129";

	/** The Constant CODIGO_MENSAJE_FEEDBACK_ERROR. */
	private static final String CODIGO_MENSAJE_FEEDBACK_ERROR = "1136";

	/** The Constant CODIGO_ERROR_BAJA_ADHESION. */
	private static final String CODIGO_ERROR_BAJA_ADHESION = "1134";

	/** The Constant ERROR_REINTENTAR_ADHESION_DEBITO_AUTOMATICO_TARJETAS. */
	private static final String ERROR_REINTENTAR_ADHESION_DEBITO_AUTOMATICO_TARJETAS = "ERROR_REINTENTAR_ADHESION_DEBITO_AUTOMATICO_TARJETAS";

	/** The Constant RESPUESTA_WARNING. */
	private static final String RESPUESTA_WARNING = "WARNING";

	/** The Constant RESPUESTA_ERROR. */
	private static final String RESPUESTA_ERROR = "ERROR";

	/** The Constant ADHESION_DEBITO. */
	private static final String ADHESION_DEBITO = "ADHESION";

	/** The Constant MODIFICACION_DEBITO. */
	private static final String MODIFICACION_DEBITO = "MODIFICACION";

	/** The Constant ADHESION_MINIMO. */
	private static final String ADHESION_MINIMO = "mínimo";

	/** The Constant ADHESION_TOTAL. */
	private static final String ADHESION_TOTAL = "total";

	/**
	 * The Constant ERROR_REINTENTAR_MODIFICACION_DEBITO_AUTOMATICO_TARJETAS.
	 */
	private static final String ERROR_REINTENTAR_MODIFICACION_DEBITO_AUTOMATICO_TARJETAS = "ERROR_REINTENTAR_MODIFICACION_DEBITO_AUTOMATICO_TARJETAS";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.
	 * AdhesionTarjetaDebitoAutomaticoManager#tarjetasCuentasParaDebito(ar.com.
	 * santanderrio.obp.servicios.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.tarjetas.view.Reintentar)
	 */
	@Override
	public Respuesta<InfoTarjetaAdhesionDebitoView> tarjetasCuentasParaDebito(Cliente cliente, Reintentar reintentar) {
		if (!"true".equals(reintentar.getReintentar())) {
			sessionParametros.setContador(new ContadorIntentos(2));
		}
		InfoTarjetaAdhesionDebitoView infoTarjetasCuentas = new InfoTarjetaAdhesionDebitoView();
		List<CuentasAdhesionDebitoAutomaticoView> listaCuentasView = new ArrayList<CuentasAdhesionDebitoAutomaticoView>();
		List<TarjetasAdhesionDebitoView> tarjetasView = new ArrayList<TarjetasAdhesionDebitoView>();
		try {
			tarjetasView = pagosPendientesBo.obtenerDatosTarjetasPagoPuntual(cliente);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage(), e);
			estadisticaManager.add(EstadisticasConstants.CODIGO_INICIO_ADHESION_DEBITO_AUTOMATICO_TARJETAS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			String mensajeError = mensajeBo.obtenerMensajePorCodigo(CODIGO_MENSAJE_ERROR_GENERICO).getMensaje();
			return respuestaFactory.crearRespuestaError(InfoTarjetaAdhesionDebitoView.class, mensajeError, null);
		}
		Respuesta<CuentasView> cuentas = cuentaManager.getCuentasSaldo();

		for (CuentasAdhesionDebitoView cuentasAdhesionDebitoView : cuentas.getRespuesta().getCuentas()) {
			CuentasAdhesionDebitoAutomaticoView cuentasAdhesionDebitoAutomaticoView = new CuentasAdhesionDebitoAutomaticoView();
			BeanUtils.copyProperties(cuentasAdhesionDebitoView, cuentasAdhesionDebitoAutomaticoView);
			listaCuentasView.add(cuentasAdhesionDebitoAutomaticoView);
		}

		infoTarjetasCuentas.setTarjetas(tarjetasView);
		infoTarjetasCuentas.setCuentas(listaCuentasView);

		String mensaje = mensajeBo.obtenerMensajePorCodigo(CODIGO_MENSAJE_AVISO_MONTO_ADHESION).getMensaje();

		String mensajeAdhesionMinimo = MessageFormat.format(mensaje, "mínimo");
		String mensajeAdhesionTotal = MessageFormat.format(mensaje, "total");

		infoTarjetasCuentas.setMensajeAdhesionMinimo(mensajeAdhesionMinimo);
		infoTarjetasCuentas.setMensajeAdhesionTotal(mensajeAdhesionTotal);

		if (EstadoRespuesta.ERROR.equals(cuentas.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.CODIGO_INICIO_ADHESION_DEBITO_AUTOMATICO_TARJETAS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			String mensajeError = mensajeBo.obtenerMensajePorCodigo(CODIGO_MENSAJE_ERROR_GENERICO).getMensaje();
			return respuestaFactory.crearRespuestaError(InfoTarjetaAdhesionDebitoView.class, mensajeError, null);
		}

		estadisticaManager.add(EstadisticasConstants.CODIGO_INICIO_ADHESION_DEBITO_AUTOMATICO_TARJETAS,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(InfoTarjetaAdhesionDebitoView.class, infoTarjetasCuentas);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.
	 * AdhesionTarjetaDebitoAutomaticoManager#adherirTarjetaDebitoAutomatico(ar.
	 * com.santanderrio.obp.servicios.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.tarjetas.view.
	 * DatosAdhesionDebitoTarjetaView)
	 */
	@Override
	public Respuesta<ComprobanteFeedbackView> adherirTarjetaDebitoAutomatico(Cliente cliente,
			DatosAdhesionDebitoTarjetaView datosAdhesionDebitoView) {

		try {
			ComprobanteFeedbackView comprobante = debitoAutomatico(cliente, datosAdhesionDebitoView);
			estadisticaManager.add(EstadisticasConstants.CODIGO_EJECUCION_SERVICIO_ADHESION_DEBITO_AUTOMATICO_TARJETAS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			String mensaje = mensajeBo.obtenerMensajePorCodigo("1135").getMensaje();
			mensaje = MessageFormat.format(mensaje, datosAdhesionDebitoView.getNroTarjetaEnmascarado(),
					datosAdhesionDebitoView.getNroCuentaFormateado());
			comprobante.setMensajeFeedback(mensaje);
			return respuestaFactory.crearRespuestaOk(ComprobanteFeedbackView.class, comprobante);
		} catch (DAOException e) {
			if (!sessionParametros.getContador().permiteReintentar()) {
				LOGGER.info(e.getMessage(), e);
				return armarRespuestaWarningOError(RESPUESTA_ERROR, ADHESION_DEBITO);
			} else {
				return armarRespuestaWarningOError(RESPUESTA_WARNING, ADHESION_DEBITO);
			}
		}
	}

	/**
	 * Armar respuesta warning o error.
	 *
	 * @param tipoRespuesta
	 *            the tipo respuesta
	 * @param tipoAccion
	 *            the tipo accion
	 * @return the respuesta
	 */
	private Respuesta<ComprobanteFeedbackView> armarRespuestaWarningOError(String tipoRespuesta, String tipoAccion) {

		String mensajeAdhesion = mensajeBo.obtenerMensajePorCodigo(CODIGO_MENSAJE_FEEDBACK_ERROR).getMensaje();
		String mensajeModificacion = mensajeBo.obtenerMensajePorCodigo(CODIGO_MENSAJE_MODIFICACION_FEEDBACK_ERROR)
				.getMensaje();

		if (ADHESION_DEBITO.equals(tipoAccion) && (RESPUESTA_ERROR.equals(tipoRespuesta))) {
			estadisticaManager.add(EstadisticasConstants.CODIGO_EJECUCION_SERVICIO_ADHESION_DEBITO_AUTOMATICO_TARJETAS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaErrorPersonalizado(ComprobanteFeedbackView.class, mensajeAdhesion,
					ERROR_REINTENTAR_ADHESION_DEBITO_AUTOMATICO_TARJETAS);
		} else if (ADHESION_DEBITO.equals(tipoAccion) && (RESPUESTA_WARNING.equals(tipoRespuesta))) {
			estadisticaManager.add(EstadisticasConstants.CODIGO_EJECUCION_SERVICIO_ADHESION_DEBITO_AUTOMATICO_TARJETAS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(ComprobanteFeedbackView.class,
					mensajeAdhesion, ERROR_REINTENTAR_ADHESION_DEBITO_AUTOMATICO_TARJETAS);
		} else if (MODIFICACION_DEBITO.equals(tipoAccion) && (RESPUESTA_ERROR.equals(tipoRespuesta))) {
			estadisticaManager.add(EstadisticasConstants.CODIGO_MODIFICACION_DEBITO_AUTOMATICO_TARJETAS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaErrorPersonalizado(ComprobanteFeedbackView.class, mensajeModificacion,
					ERROR_REINTENTAR_MODIFICACION_DEBITO_AUTOMATICO_TARJETAS);
		} else if (MODIFICACION_DEBITO.equals(tipoAccion) && (RESPUESTA_WARNING.equals(tipoRespuesta))) {
			estadisticaManager.add(EstadisticasConstants.CODIGO_MODIFICACION_DEBITO_AUTOMATICO_TARJETAS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(ComprobanteFeedbackView.class,
					mensajeModificacion, ERROR_REINTENTAR_MODIFICACION_DEBITO_AUTOMATICO_TARJETAS);
		}
		return null;
	}

	/**
	 * Armar respuesta warning O error baja adhesion.
	 *
	 * @param tipoError
	 *            the tipo error
	 * @param mensajeAdhesion
	 *            the mensaje adhesion
	 * @return the respuesta
	 */
	private Respuesta<ComprobanteFeedbackView> armarRespuestaWarningOErrorBajaAdhesion(String tipoError,
			String mensajeAdhesion) {
		estadisticaManager.add(EstadisticasConstants.CODIGO_ESTADISTICA_BAJA_ADHESION,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);

		if (EstadoRespuesta.ERROR.toString().equals(tipoError)) {
			return respuestaFactory.crearRespuestaErrorPersonalizado(ComprobanteFeedbackView.class, mensajeAdhesion,
					TipoError.ERROR_REINTENTAR.getDescripcion());

		} else {
			return respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(ComprobanteFeedbackView.class,
					mensajeAdhesion, TipoError.ERROR_REINTENTOS_OPERACION.getDescripcion());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.
	 * AdhesionTarjetaDebitoAutomaticoManager#
	 * estadisticaVisualizacionComprobanteAdhesionDebito()
	 */
	@Override
	public void estadisticaVisualizacionComprobanteAdhesionDebito() {
		estadisticaManager.add(EstadisticasConstants.VISUALIZACION_COMPROBANTE_ADHESION_DEBITO_TARJETAS,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.
	 * AdhesionTarjetaDebitoAutomaticoManager#inicioModificacionAdhesionDebito(
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.tarjetas.view.NroTarjetaView)
	 */
	@Override
	public Respuesta<TarjetaModificacionAdhesionDebitoView> inicioModificacionAdhesionDebito(Cliente cliente,
			NroTarjetaView nroTarjeta) {

		TarjetaModificacionAdhesionDebitoView datosTarjetaView = new TarjetaModificacionAdhesionDebitoView();
		Cuenta tarjetaSeleccionada = cliente.getTarjeta(nroTarjeta.getNroTarjeta());
		String aliasTarjeta = tarjetaSeleccionada.getAlias();
		String tipoCuentaTarjeta = TipoCuentaTarjeta.fromCodigo(tarjetaSeleccionada.getTipoCuenta()).getAbreviatura();
		List<Cuenta> cuentasParaPago = cliente.getCuentasParaEfectuarPago();
		String mensajeInfo = mensajeBo.obtenerMensajePorCodigo(CODIGO_MENSAJE_INFORMATIVO_MODIFICAR_ADHESION)
				.getMensaje();
		String mensajeConfig = mensajeBo.obtenerMensajePorCodigo(CODIGO_MENSAJE_CONFIGURACION_MODIFICAR_ADHESION)
				.getMensaje();
		DatosTarjeta datosTarjeta = null;

		
			for (Cuenta cuenta : cuentasParaPago) {
				try {
					if (!tarjetaSeleccionada.getTipoCuenta().equals("06")) {
						List<DebitoAutomatico> listaDebitos = pagoTarjetaCreditoBO.obtenerDeudasConDebitoAutomatico(cliente,
								cuenta);
						if (listaDebitos != null) {
							for (DebitoAutomatico debitoAutom : listaDebitos) {
								String numeroPartidaDebito = debitoAutom.getNumeroPartida();
								String numeroPartidaDebitoFormateado = ISBANStringUtils.eliminarCeros(numeroPartidaDebito);
								String numeroCuentaTarjeta = tarjetaSeleccionada.getNroCuentaProducto();
								String numeroCuentaTarjetaFormateado = ISBANStringUtils.eliminarCeros(numeroCuentaTarjeta);
								if (numeroPartidaDebitoFormateado.equals(numeroCuentaTarjetaFormateado)) {
									datosTarjetaView.setNroCuentaDebito(formatearNroCuenta(cuenta));
									datosTarjetaView.setAliasCuentaDebito(setearAliasCuentaDebito(cuenta.getAlias()));
									datosTarjetaView.setTipoCuentaDebito(
											TipoCuenta.fromCodigo(cuenta.getTipoCuenta()).getDescripcion());
									Cuenta cuentaDebito = cuentaBo.obtenerSaldoActualizado(cuenta);
									datosTarjetaView.setSaldoCuentaDebito("$" + cuentaDebito.getSaldoCuenta());
									datosTarjetaView.setCbu(cuenta.getCbu());
									datosTarjeta = pagosPendientesBo.obtenerDatosUnaTarjeta(cliente, tarjetaSeleccionada);
	
									if ("02".equals(datosTarjeta.getFormaPagoTarjetaCredito())
											|| "04".equals(datosTarjeta.getFormaPagoTarjetaCredito())) {
										datosTarjetaView.setIsAdhesionMinimo(true);
										datosTarjetaView.setIsAdhesionTotal(false);
									} else {
										datosTarjetaView.setIsAdhesionMinimo(false);
										datosTarjetaView.setIsAdhesionTotal(true);
									}
								}
	
							}
						}
					} else {
						datosTarjetaView.setNroCuentaDebito(formatearNroCuenta(cuenta));
						datosTarjetaView.setAliasCuentaDebito(setearAliasCuentaDebito(cuenta.getAlias()));
						datosTarjetaView.setTipoCuentaDebito(
								TipoCuenta.fromCodigo(cuenta.getTipoCuenta()).getDescripcion());
						Cuenta cuentaDebito = cuentaBo.obtenerSaldoActualizado(cuenta);
						datosTarjetaView.setSaldoCuentaDebito("$" + cuentaDebito.getSaldoCuenta());
						datosTarjetaView.setCbu(cuenta.getCbu());
						datosTarjeta = pagosPendientesBo.obtenerDatosUnaTarjeta(cliente, tarjetaSeleccionada);

						if ("02".equals(datosTarjeta.getFormaPagoTarjetaCredito())
								|| "04".equals(datosTarjeta.getFormaPagoTarjetaCredito())) {
							datosTarjetaView.setIsAdhesionMinimo(true);
							datosTarjetaView.setIsAdhesionTotal(false);
						} else {
							datosTarjetaView.setIsAdhesionMinimo(false);
							datosTarjetaView.setIsAdhesionTotal(true);
						}
					  }
					} catch (BusinessException e) {

					LOGGER.info(e.getMessage(), e);
					String mensajeError = mensajeBo.obtenerMensajePorCodigo(CODIGO_MENSAJE_ERROR).getMensaje();
					estadisticaManager.add(EstadisticasConstants.INICIO_FLUJO_MODIFICAR_DEBITO_TARJETAS,
							EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
					return respuestaFactory.crearRespuestaError(TarjetaModificacionAdhesionDebitoView.class, mensajeError,
							null);
				}

				if (datosTarjeta != null) {
					break;
				}
			}
		
		

		datosTarjetaView.setTarjetaParaTitulo(StringUtils.isNotBlank(aliasTarjeta)
				? (tipoCuentaTarjeta + " " + "\"" + aliasTarjeta + "\"") : nroTarjeta.getNroTarjeta());

		String nroTarjetaEnmascarado = TarjetaUtils.crearMascaraNroTarjeta(tarjetaSeleccionada.getNroTarjetaCredito(),
				TipoCuentaTarjeta.fromCodigo(tarjetaSeleccionada.getTipoCuenta()));

		datosTarjetaView.setTarjetaSubtitulo(StringUtils.isBlank(aliasTarjeta) ? "" : nroTarjetaEnmascarado);

		datosTarjetaView.setNroTarjetaEnmascarado(nroTarjeta.getNroTarjeta());
		datosTarjetaView.setTipoTarjeta(tarjetaSeleccionada.getTipoCuenta());

		if (datosTarjetaView.getIsAdhesionMinimo()) {
			String mensajeInformativo = MessageFormat.format(mensajeInfo, ADHESION_MINIMO);
			String mensajeConfiguracion = MessageFormat.format(mensajeConfig, ADHESION_TOTAL);
			datosTarjetaView.setMensajeInformacion(mensajeInformativo);
			datosTarjetaView.setMensajeAdhesion(mensajeConfiguracion);
		} else {
			String mensajeInformativo = MessageFormat.format(mensajeInfo, ADHESION_TOTAL);
			String mensajeConfiguracion = MessageFormat.format(mensajeConfig, ADHESION_MINIMO);
			datosTarjetaView.setMensajeInformacion(mensajeInformativo);
			datosTarjetaView.setMensajeAdhesion(mensajeConfiguracion);
		}

		estadisticaManager.add(EstadisticasConstants.INICIO_FLUJO_MODIFICAR_DEBITO_TARJETAS,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(TarjetaModificacionAdhesionDebitoView.class, datosTarjetaView);
	}

	/**
	 * Formatear nro cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the string
	 */
	private String formatearNroCuenta(Cuenta cuenta) {

		String nroSucursal = cuenta.getNroSucursal().substring(1, cuenta.getNroSucursal().length());
		String nrocuentaProducto = ISBANStringUtils.formatearNumeroCuenta(cuenta.getNroCuentaProducto());
		return nroSucursal + "-" + nrocuentaProducto;
	}

	/**
	 * Setear alias cuenta debito.
	 *
	 * @param aliasCuentaDebito
	 *            the alias cuenta debito
	 * @return the string
	 */
	private String setearAliasCuentaDebito(String aliasCuentaDebito) {

		if (StringUtils.isBlank(aliasCuentaDebito)) {
			return null;
		} else {
			return aliasCuentaDebito;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.
	 * AdhesionTarjetaDebitoAutomaticoManager#modificarAdhesionDebito(ar.com.
	 * santanderrio.obp.servicios.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.tarjetas.view.
	 * DatosAdhesionDebitoTarjetaView)
	 */
	@Override
	public Respuesta<ComprobanteFeedbackView> modificarAdhesionDebito(Cliente cliente,
			DatosAdhesionDebitoTarjetaView datosAdhesionDebitoView) {
		if (!"true".equals(datosAdhesionDebitoView.getReintentar())) {
			sessionParametros.setContador(new ContadorIntentos(2));
		}
		try {
			ComprobanteFeedbackView comprobante = debitoAutomatico(cliente, datosAdhesionDebitoView);
			estadisticaManager.add(EstadisticasConstants.CODIGO_MODIFICACION_DEBITO_AUTOMATICO_TARJETAS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			String mensaje = mensajeBo.obtenerMensajePorCodigo("1226").getMensaje();
			mensaje = MessageFormat.format(mensaje, datosAdhesionDebitoView.getNroTarjetaEnmascarado());
			comprobante.setMensajeFeedback(mensaje);
			return respuestaFactory.crearRespuestaOk(ComprobanteFeedbackView.class, comprobante);
		} catch (DAOException e) {
			if ("TIMEOUT".equals(e.getMessage())) {
				String mensajeTimeOut = mensajeBo.obtenerMensajePorCodigo(CODIGO_MENSAJE_ERROR).getMensaje();
				estadisticaManager.add(EstadisticasConstants.CODIGO_MODIFICACION_DEBITO_AUTOMATICO_TARJETAS,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				return respuestaFactory.crearRespuestaError(ComprobanteFeedbackView.class, mensajeTimeOut,
						"ERROR_TIMEOUT");
			} else if (!sessionParametros.getContador().permiteReintentar()) {
				LOGGER.info(e.getMessage(), e);
				return armarRespuestaWarningOError(RESPUESTA_ERROR, MODIFICACION_DEBITO);
			} else {
				return armarRespuestaWarningOError(RESPUESTA_WARNING, MODIFICACION_DEBITO);
			}
		}
	}

	/**
	 * Debito automatico.
	 *
	 * @param cliente
	 *            the cliente
	 * @param datosAdhesionDebitoView
	 *            the datos adhesion debito view
	 * @return the comprobante feedback
	 * @throws DAOException
	 *             the DAO exception
	 */
	public ComprobanteFeedbackView debitoAutomatico(Cliente cliente,
			DatosAdhesionDebitoTarjetaView datosAdhesionDebitoView) throws DAOException {

		DatosAdhesionDebitoTarjeta datos = new DatosAdhesionDebitoTarjeta();
		BeanUtils.copyProperties(datosAdhesionDebitoView, datos);

		Cuenta tarjetaSeleccionada = cliente.getTarjeta(datosAdhesionDebitoView.getNroTarjetaEnmascarado());
		Cuenta cuentaSeleccionada = cliente.getCuenta(datosAdhesionDebitoView.getCbu());

		if (TipoCuenta.CAJA_AHORRO_PESOS.equals(cuentaSeleccionada.getTipoCuentaEnum())) {
			datos.setTipoCuentaDebito("01");
			if (datosAdhesionDebitoView.getIsAdhesionMinimo()) {
				datos.setFormaPago("04");
			} else {
				datos.setFormaPago("05");
			}
		} else if (TipoCuenta.CUENTA_CORRIENTE_PESOS.equals(cuentaSeleccionada.getTipoCuentaEnum())
				|| cuentaSeleccionada.esCuentaUnica()) {
			datos.setTipoCuentaDebito("00");
			if (datosAdhesionDebitoView.getIsAdhesionMinimo()) {
				datos.setFormaPago("02");
			} else {
				datos.setFormaPago("03");
			}
		}

		if (TipoCuenta.VISA.equals(tarjetaSeleccionada.getTipoCuentaEnum())) {
			datos.setTipoTarjeta("07");
		} else if (TipoCuenta.AMEX.equals(tarjetaSeleccionada.getTipoCuentaEnum())) {
			datos.setTipoTarjeta("06");
		} else if (TipoCuenta.MASTERCARD.equals(tarjetaSeleccionada.getTipoCuentaEnum())) {
			datos.setTipoTarjeta("06");
		}

		// Para adaptar la longitud del dato a 7, para pasar al servicio
		String nroCuentaDebitoSinCeros = ISBANStringUtils.eliminarCeros(cuentaSeleccionada.getNroCuentaProducto());
		datos.setNroCuentaDebito(nroCuentaDebitoSinCeros);

		// Para adaptar la longitud del dato a 3, para pasar al servicio
		String nroSucursalParaServicio = cuentaSeleccionada.getNroSucursal().substring(1, 4);
		datos.setSucursalCuentaDebito(nroSucursalParaServicio);

		datos.setNroFirmante(cuentaSeleccionada.getNroOrdenFirmante());
		String nroCuentaProductoFormateado = ISBANStringUtils.eliminarCeros(tarjetaSeleccionada.getNroCuentaProducto());
		nroCuentaProductoFormateado = StringUtils.leftPad(nroCuentaProductoFormateado, 10, "0");
		datos.setNroCuentaTarjeta(nroCuentaProductoFormateado);

		return adhesionTarjetaDebitoAutomaticoBo.adherirTarjetaDebitoAutomatico(cliente, datos);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.
	 * AdhesionTarjetaDebitoAutomaticoManager#bajaAdhesionTarjeta(ar.com.
	 * santanderrio.obp.servicios.tarjetas.view.BajaAdhesionTarjView,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<ComprobanteFeedbackView> bajaAdhesionTarjeta(BajaAdhesionTarjView view, Cliente cliente) {
		DatosAdhesionDebitoTarjeta datos = new DatosAdhesionDebitoTarjeta();
		Respuesta<ComprobanteFeedbackView> respuesta;

		if (!"true".equals(view.getReintentar())) {
			sessionParametros.setContador(new ContadorIntentos(2));
		}

		Cuenta tarjetaSeleccionada = cliente.getTarjeta(view.getNroTarjeta());

		if (TipoCuenta.VISA.equals(tarjetaSeleccionada.getTipoCuentaEnum())) {
			datos.setTipoTarjeta("07");
		} else if (TipoCuenta.AMEX.equals(tarjetaSeleccionada.getTipoCuentaEnum())) {
			datos.setTipoTarjeta("06");
		} else if (TipoCuenta.MASTERCARD.equals(tarjetaSeleccionada.getTipoCuentaEnum())) {
			datos.setTipoTarjeta("06");
		} 

		String nroCuentaProductoFormateado = ISBANStringUtils.eliminarCeros(tarjetaSeleccionada.getNroCuentaProducto());
		nroCuentaProductoFormateado = StringUtils.leftPad(nroCuentaProductoFormateado, 10, "0");

		datos.setNroCuentaTarjeta(nroCuentaProductoFormateado);
		datos.setTipoCuentaDebito("  ");
		datos.setSucursalCuentaDebito("000");
		datos.setNroCuentaDebito("0000000");
		datos.setNroFirmante("000");
		datos.setFormaPago("01");

		try {
			ComprobanteFeedbackView comprobante = adhesionTarjetaDebitoAutomaticoBo
					.adherirTarjetaDebitoAutomatico(cliente, datos);

			String mensaje = mensajeBo.obtenerMensajePorCodigo("1133").getMensaje();
			mensaje = MessageFormat.format(mensaje, "<b>" + view.getNroTarjeta() + "</b>");

			comprobante.setMensajeFeedback(mensaje);
			estadisticaManager.add(EstadisticasConstants.CODIGO_ESTADISTICA_BAJA_ADHESION,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

			respuesta = respuestaFactory.crearRespuestaOk(ComprobanteFeedbackView.class, comprobante);

		} catch (DAOException e) {
			ComprobanteFeedbackView comprobante = new ComprobanteFeedbackView();
			comprobante.setReintentar("true");
			String msjError = "";

			if ("TIMEOUT".equals(e.getMessage())) {
				msjError = mensajeBo.obtenerMensajePorCodigo(CODIGO_MENSAJE_ERROR).getMensaje();
			} else {
				msjError = mensajeBo.obtenerMensajePorCodigo(CODIGO_ERROR_BAJA_ADHESION).getMensaje();
			}

			if (!sessionParametros.getContador().permiteReintentar()) {
				LOGGER.info(e.getMessage(), e);
				respuesta = armarRespuestaWarningOErrorBajaAdhesion(RESPUESTA_ERROR, msjError);
			} else {
				respuesta = armarRespuestaWarningOErrorBajaAdhesion(RESPUESTA_WARNING, msjError);
			}
			respuesta.setRespuesta(comprobante);
		}

		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.
	 * AdhesionTarjetaDebitoAutomaticoManager#
	 * estadisticaVisualizacionComprobanteModificacionDebito()
	 */
	@Override
	public void estadisticaVisualizacionComprobanteModificacionDebito() {
		estadisticaManager.add(EstadisticasConstants.VISUALIZACION_COMPROBANTE_MODIFICAR_DEBITO_TARJETAS,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

}
