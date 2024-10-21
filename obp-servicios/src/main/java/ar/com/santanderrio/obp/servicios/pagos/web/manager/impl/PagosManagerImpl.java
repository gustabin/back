/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.web.manager.impl;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.base.web.view.FeedbackMensajeView;
import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaCerrada;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.IdentificacionCuentaView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.AdhesionDebitoAutomatico;
import ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.bo.PagoTarjetaCreditoBO;
import ar.com.santanderrio.obp.servicios.pagos.bo.TipoMedioPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.DatosPagoAutomaticoEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.InformacionAdicional;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoView;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;
import ar.com.santanderrio.obp.servicios.pagos.entities.TipoDeModificacion;
import ar.com.santanderrio.obp.servicios.pagos.entities.TipoDePagoEnum;
import ar.com.santanderrio.obp.servicios.pagos.route.PagosTotalesRouteBuilder;
import ar.com.santanderrio.obp.servicios.pagos.service.DatosAdicionalesService;
import ar.com.santanderrio.obp.servicios.pagos.service.PagosService;
import ar.com.santanderrio.obp.servicios.pagos.service.StopDebitService;
import ar.com.santanderrio.obp.servicios.pagos.web.manager.PagosManager;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultaPagosView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoPendienteView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagosPendientesView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PeriodoView;
import ar.com.santanderrio.obp.servicios.route.RouteBuilderException;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.EncabezadoTarjetasBO;

/**
 * The Class PagosManagerImpl.
 *
 * @author B039636
 */
@Component
public class PagosManagerImpl implements PagosManager {

	/** The Constant CERO. */
	private static final String CERO = "0";

	/** The Constant OCURRIO_UN_ERROR_AL_OBTENER_EL_PAGO_EN_LA_SESION. */
	private static final String OCURRIO_UN_ERROR_AL_OBTENER_EL_PAGO_EN_LA_SESION = "Ocurrió un error al obtener el pago en la sesion.";

	/** The Constant ERROR_GRABANDO_LA_ESTADISTICA_DE_MOVIMIENTOS_PENDIENTES. */
	private static final String ERROR_GRABANDO_LA_ESTADISTICA_DE_MOVIMIENTOS_PENDIENTES = "Error grabando la estadistica de movimientos pendientes.";

	/** The Constant INFO_ESTADISTICA. */
	private static final String INFO_ESTADISTICA = "Grabando estadisticas con el cod: ";

	/** Tipo de accion para ALTPAUDIC, siempre A. */
	private static final String ACCION_ALTA = "A";

	/** Mensaje ayuda importe limite*. */
	private static final String IMPORTE_LIMITE = "1265";

	/** Tipo de accion para texto a. */
	private static final String TXT_A = "a";

	/** Tipo de accion para texto a. */
	private static final String TIPO_DESCRIPCION_TR = "Recarga - Tarjeta Recargable";

	/** Tipo de accion para texto a. */
	private static final String TXT_PAGO_MANUALMENTE = "Este pago se debe realizar manualmente";

	/** The fecha formater. */
	private final SimpleDateFormat fechaFormater = new SimpleDateFormat("dd/MM/yyyy");

	/** The hora formater. */
	private final SimpleDateFormat horaFormater = new SimpleDateFormat("HH:mm");

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PagosManagerImpl.class);

	/** The Constant DESCRIPCION_LOG. */
	private static final String DESCRIPCION_LOG = "Descripcion: {}.";

	/** The Constant ERROR_LOG. */
	private static final String ERROR_LOG = "Ha ocurrido un error en servicio {}. " + DESCRIPCION_LOG;

	/** The Constant MES_MAX. */
	private static final int MES_MAX = 12;

	/** The Constant ANIO_MAX. */
	private static final int ANIO_MAX = 2040;

	/** The Constant PES_CODIGO_RUBRO. */
	private static final String PES_CODIGO_RUBRO = "RCEL";

	/**
	 * Posiciones para extraccion de sucursal, Nro.Cuenta y digito verificador:
	 */
	private static final int CUENTA_SUC_INI = 0;

	/** The Constant CUENTA_SUC_FIN. */
	private static final int CUENTA_SUC_FIN = 3;

	/** The Constant CUENTA_NRO_INI. */
	private static final int CUENTA_NRO_INI = 4;

	/** The Constant CUENTA_NRO_FIN. */
	private static final int CUENTA_NRO_FIN = 10;

	/** The Constant CUENTA_DVR_INI. */
	private static final int CUENTA_DVR_INI = 11;

	/** The Constant CUENTA_DVR_FIN. */
	private static final int CUENTA_DVR_FIN = 12;

	/** The pagos service. */
	@Autowired
	private PagosService pagosService;

	/** The stop debit service. */
	@Autowired
	private StopDebitService stopDebitService;

	/** The datos adicionales service. */
	@Autowired
	private DatosAdicionalesService datosAdicionalesService;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The session parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The mensaje manager. */
	@Autowired
	private MensajeManager mensajeManager;

	/** The cuenta manager. */
	@Autowired
	private CuentaManager cuentaManager;

	/** The medios pago BO. */
	@Autowired
	private MediosPagoBO mediosPagoBO;

	/** The pago Tarjeta Credito BO. */
	@Autowired
	private PagoTarjetaCreditoBO pagoTarjetaCreditoBO;

	/** The mensaje DAO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The encabezado tarjetas BO. */
	@Autowired
	private EncabezadoTarjetasBO encabezadoTarjetasBO;

	/** The ModuloPermiso BO. */
	@Autowired
	private ModuloPermisoBO moduloPermisoBO;

	/** The respuestaFactory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.web.manager.PagosManager#
	 * getPagosTotales(ar.com.santanderrio.obp.servicios.pagos.web.view.
	 * ConsultaPagosView)
	 */
	@Override
	public Respuesta<PagosPendientesView> getPagosTotales(ConsultaPagosView consultaPagosView) {
		Respuesta<PagosPendientesView> respuestaPagosPendientesView = new Respuesta<PagosPendientesView>();
		try {
			if (!fechaValida(consultaPagosView)) {
				return getRespuestaErrorPagosTotales(respuestaPagosPendientesView);
			}
			// validamos si existen datos en la sesion
			Respuesta<List<PagoPendiente>> respuestaPagoPendienteList = sesionParametros.getRespuestaPagosPendientes();
			if (respuestaPagoPendienteList == null) {
				// orquestacion servicios
				PagosTotalesRouteBuilder pagosTotalesRouteBuilder = new PagosTotalesRouteBuilder(
						sesionCliente.getCliente(), estadisticaManager);
				try {
					pagosTotalesRouteBuilder.process();
					respuestaPagoPendienteList = pagosTotalesRouteBuilder.manageResponses();
					Collections.sort(respuestaPagoPendienteList.getRespuesta());
					sesionParametros.setRespuestaPagosPendientes(respuestaPagoPendienteList);
				} catch (RouteBuilderException e) {
					LOGGER.error(ERROR_LOG, e.getMessage(), e, e);
					return getRespuestaErrorPagosTotales(respuestaPagosPendientesView);
				}

			}
			grabarEstadisticaPagos(respuestaPagoPendienteList.getEstadoRespuesta(),
					EstadisticasConstants.PAGAR_Y_TRANSFERIR_CODIGO_TRANSACCION);
			respuestaPagosPendientesView = armarPagoView(respuestaPagoPendienteList, consultaPagosView);

			validarCuentasMonetariasActivasEnPesos(respuestaPagosPendientesView);
		} catch (Exception e) {
			LOGGER.error(ERROR_LOG, e.getMessage(), e, e);
			return getRespuestaErrorPagosTotales(respuestaPagosPendientesView);
		}
		respuestaPagosPendientesView.getRespuesta().setMensajeInformacionPagoAdebitar(mensajeBO
                .obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_MENSAJE_PAGO_PENDIENTE_DE_CONFIRMACION ).getMensaje());		
		return respuestaPagosPendientesView;
	}

	/**
	 * Consulta todos los pagos.
	 *
	 * @param consultaPagosView
	 *            the consulta pagos view
	 * @return the boolean
	 */
	private Boolean consultaTodosLosPagos(ConsultaPagosView consultaPagosView) {
		return StringUtils.equals(CERO, consultaPagosView.getMes())
				&& StringUtils.equals(CERO, consultaPagosView.getAnio());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.web.manager.PagosManager#
	 * getPagosTotales(ConsultaPagosView)
	 */

	/**
	 * Retorna una {@link Respuesta} con {@link EstadoRespuesta.ERROR} para
	 * {@link PagosPendientesView}.
	 *
	 * @param respuestaPagosTotales
	 *            the respuesta pagos totales
	 * @return the respuesta
	 */
	private Respuesta<PagosPendientesView> getRespuestaErrorPagosTotales(
			Respuesta<PagosPendientesView> respuestaPagosTotales) {
		respuestaPagosTotales.setEstadoRespuesta(EstadoRespuesta.ERROR);
		respuestaPagosTotales.setRespuestaVacia(Boolean.TRUE);
		Mensaje mensaje = mensajeManager
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.CALENDARIO_DE_PAGOS_ERROR_GENERICO);
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta(mensaje.getMensaje());
		respuestaPagosTotales.add(itemMensajeRespuesta);
		return respuestaPagosTotales;
	}

	/**
	 * Grabar estadistica pagos.
	 *
	 * @param estadoRespuesta
	 *            the estado respuesta
	 * @param codigoTransaccion
	 *            the codigo transaccion
	 */
	private void grabarEstadisticaPagos(EstadoRespuesta estadoRespuesta, String codigoTransaccion) {
		Estadistica estadistica = new Estadistica();

		estadistica.setCodigoError(String.valueOf(estadoRespuesta.ordinal() + 1));
		estadistica.setCodigoTransaccion(codigoTransaccion);
		if (!estadisticaManager.add(estadistica)) {
			LOGGER.debug(ERROR_LOG, ERROR_GRABANDO_LA_ESTADISTICA_DE_MOVIMIENTOS_PENDIENTES);
		}
	}

	/**
	 * Alta de informacion adicional de Pagomiscuentas DTF: 16416 - sprint 15.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 */
	@Override
	public Respuesta<Boolean> altaDeInformacionAdicional(PagoPendienteView pagoPendienteView) {
		Respuesta<Boolean> respuesta = new Respuesta<Boolean>();
		InformacionAdicional infoAdicional = armarInfoAdicional(pagoPendienteView);
		if (infoAdicional != null) {
			try {
				respuesta = datosAdicionalesService.altaInformacionAdicional(sesionCliente.getCliente(), infoAdicional);
				grabarEstadisticaPagos(respuesta.getEstadoRespuesta(),
						EstadisticasConstants.CODIGO_MODIFICACION_INFORMACION_ADICIONAL);
				return respuesta;
			} catch (ServiceException ex) {
				LOGGER.error(ex.getMessage(), ex);
			} catch (Exception ex) {
				LOGGER.error(ex.getMessage(), ex);
			}
		}
		respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		respuesta.setRespuestaVacia(true);

		grabarEstadisticaPagos(respuesta.getEstadoRespuesta(),
				EstadisticasConstants.CODIGO_MODIFICACION_INFORMACION_ADICIONAL);
		return respuesta;
	}

	/**
	 * DTF: 16416 - sprint 15.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the informacion adicional
	 */
	private InformacionAdicional armarInfoAdicional(PagoPendienteView pagoPendienteView) {
		InformacionAdicional informacionAdicional = new InformacionAdicional();
		if (pagoPendienteView.getCodigoEmpresa() != null) {
			informacionAdicional.setCodigoEmpresa(pagoPendienteView.getCodigoEmpresa());
		} else {
			return null;
		}
		if (pagoPendienteView.getCodigoClienteEmpresa() != null) {
			informacionAdicional.setIdentificacion(pagoPendienteView.getCodigoClienteEmpresa());
		} else {
			return null;
		}
		informacionAdicional.setAccion(ACCION_ALTA);
		if (pagoPendienteView.getInformacionAdicional() != null) {
			informacionAdicional.setMensaje(pagoPendienteView.getInformacionAdicional());
		}
		return informacionAdicional;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.web.manager.PagosManager#
	 * solicitarStopDebit(PagoPendienteView)
	 */
	@Override
	public Respuesta<PagoPendienteView> solicitarStopDebit(PagoPendienteView pagoPendienteView) {
		Respuesta<PagoPendienteView> respuesta = new Respuesta<PagoPendienteView>();
		// inicializacion de contador de intentos default
		sesionParametros.setContador(ContextHolder.getContext().getBean(ContadorIntentos.class));
		PagoPendiente pagoPendienteSesion = obtenerPagoPendienteSesion(pagoPendienteView.getId());
		if (pagoPendienteSesion == null) {
			// limpio pagoPendienteView en sesión
			sesionParametros.setPagoPendienteView(null);
			estadisticaManager.add(EstadisticasConstants.STOP_DEBIT_SOLICITUD,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
			return respuesta;
		}
		sesionParametros.setPagoPendienteView(pagoPendienteView);

		String codigoEstadisticaCorrespondiente = "";
		switch (pagoPendienteSesion.getTipoPago()) {
		case PAGOMISCUENTASDEBITO:
			codigoEstadisticaCorrespondiente = EstadisticasConstants.STOP_DEBIT_PAGO_MIS_CUENTAS_SOLICITUD;
			break;
		case SERVICIOADHERIDOADEBITOAUTOMATICOENCUENTA:
			codigoEstadisticaCorrespondiente = EstadisticasConstants.STOP_DEBIT_DEBITO_EN_CUENTA_SOLICITUD;
			break;
		default:
			break;
		}

		estadisticaManager.add(codigoEstadisticaCorrespondiente, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuesta(pagoPendienteView);
		return respuesta;

	}

	/**
	 * Busca el pago pendiente en sesión por ID.
	 *
	 * @param id
	 *            the id
	 * @return pago pendiente si existe en sesión, null en otro caso
	 */
	private PagoPendiente obtenerPagoPendienteSesion(String id) {
		List<PagoPendiente> respuesta = sesionParametros.getRespuestaPagosPendientes().getRespuesta();
		for (PagoPendiente pagoPendiente : respuesta) {
			if (id.equals(pagoPendiente.getId().toString())) {
				return pagoPendiente;
			}
		}
		return null;
	}

	/**
	 * Ejecuta Stop debit de debito Pago Mis Cuentas y servicio adherido a debito
	 * automatico en cuenta.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 * @see ar.com.santanderrio.obp.servicios.pagos.web.manager.PagosManager#
	 *      ejecutarStopDebit(PagoPendienteView)
	 */
	@Override
	public Respuesta<PagoPendienteView> ejecutarStopDebit(PagoPendienteView pagoPendienteView) {
		Respuesta<PagoPendienteView> respuesta = new Respuesta<PagoPendienteView>();
		Respuesta<ResultadoTransaccion> respuestaStopDebit = new Respuesta<ResultadoTransaccion>();
		try {
			if (!sesionParametros.getPagoPendienteView().getId().equals(pagoPendienteView.getId())) {
				return ejecucionFallidaRespuesta();
			}
			PagoPendiente pagoPendienteSesion = obtenerPagoPendienteSesion(pagoPendienteView.getId());
			if (pagoPendienteSesion == null) {
				return ejecucionFallidaRespuesta();
			}
			ContadorIntentos contadorIntentos = sesionParametros.getContador();
			if (!contadorIntentos.permiteReintentar()) {
				return errorStopDebit();
			}

			String codigoEstadisticaCorrespondiente = "";
			switch (pagoPendienteSesion.getTipoPago()) {
			case PAGOMISCUENTASDEBITO:
				respuestaStopDebit = stopDebitService.ejecutarStopDebitPagoMisCuentas(pagoPendienteSesion,
						sesionCliente.getCliente());
				codigoEstadisticaCorrespondiente = EstadisticasConstants.STOP_DEBIT_PAGO_MIS_CUENTAS_EJECUCION;
				break;
			case SERVICIOADHERIDOADEBITOAUTOMATICOENCUENTA:
				respuestaStopDebit = stopDebitService.ejecutarStopDebitoEnCuenta(pagoPendienteSesion,
						sesionCliente.getCliente());
				codigoEstadisticaCorrespondiente = EstadisticasConstants.STOP_DEBIT_DEBITO_EN_CUENTA_EJECUCION;
				break;
			default:
				respuesta = crearRespuestaWarningConEstadistica(codigoEstadisticaCorrespondiente);
				break;
			}

			if (respuestaStopDebit.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
				ResultadoTransaccion resultadoTransaccion = respuestaStopDebit.getRespuesta();
				pagoPendienteView.setNumeroComprobante(resultadoTransaccion.getNumeroComprobante());
				Date fechaTransaccion = resultadoTransaccion.getFechaTransaccion();
				pagoPendienteView.setFecha(fechaFormater.format(fechaTransaccion));
				pagoPendienteView.setHora(horaFormater.format(fechaTransaccion));
				Mensaje mensaje = mensajeManager
						.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_STOP_DEBIT_DE_DEBITO_AUTO_OK);
				String mensajeFormateado = MessageFormat.format(mensaje.getMensaje(),
						pagoPendienteView.getNombreEmpresa());
				pagoPendienteView.setMensaje(mensajeFormateado);

				respuesta.setRespuesta(pagoPendienteView);
				respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
				estadisticaManager.add(EstadisticasConstants.STOP_DEBIT_EJECUCION,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			} else {
				respuesta = warningStopDebit(respuesta);
				estadisticaManager.add(EstadisticasConstants.STOP_DEBIT_EJECUCION,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}
			return respuesta;
		} catch (Exception e) {
			String mensaje = e.getMessage();
			LOGGER.error(mensaje, e);
			estadisticaManager.add(EstadisticasConstants.STOP_DEBIT_EJECUCION,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return ejecucionFallidaRespuesta();
		}
	}

	/**
	 * Warning stop debit.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @return the respuesta
	 */
	public Respuesta<PagoPendienteView> warningStopDebit(Respuesta<PagoPendienteView> respuesta) {
		respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
		respuesta.setItemMensajeRespuesta(
				getItemMensajeRespuestaConMensaje(CodigoMensajeConstantes.CODIGO_STOP_DEBIT_FALLO));
		return respuesta;
	}

	/**
	 * Error stop debit.
	 *
	 * @return the respuesta
	 */
	public Respuesta<PagoPendienteView> errorStopDebit() {
		Respuesta<PagoPendienteView> respuesta = new Respuesta<PagoPendienteView>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		respuesta.setItemMensajeRespuesta(
				getItemMensajeRespuestaConMensaje(CodigoMensajeConstantes.CODIGO_STOP_DEBIT_FALLO));
		return respuesta;
	}

	/**
	 * Respuesta warning.
	 *
	 * @param codigoEstadisticaCorrespondiente
	 *            the codigo estadistica correspondiente
	 * @return the respuesta
	 */
	private Respuesta<PagoPendienteView> crearRespuestaWarningConEstadistica(
			String... codigoEstadisticaCorrespondiente) {
		Respuesta<PagoPendienteView> respuesta = new Respuesta<PagoPendienteView>();
		respuesta.setRespuestaVacia(true);
		respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
		for (String codigo : codigoEstadisticaCorrespondiente) {
			if (codigo != null) {
				estadisticaManager.add(codigo, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}
		}
		return respuesta;
	}

	/**
	 * Ejecucion fallida respuesta.
	 *
	 * @param codigoEstadisticaCorrespondiente
	 *            the codigo estadistica correspondiente
	 * @return the respuesta
	 */
	private Respuesta<PagoPendienteView> ejecucionFallidaRespuesta(String... codigoEstadisticaCorrespondiente) {
		Respuesta<PagoPendienteView> respuesta = new Respuesta<PagoPendienteView>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		respuesta.setRespuestaVacia(true);
		respuesta.addAll(getItemMensajeRespuestaConMensaje(CodigoMensajeConstantes.CODIGO_ERROR_GENERICO));
		return respuesta;
	}

	/**
	 *
	 * Ejecutar modificaciones DTF: 10281.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 * @see EstadisticasConstants
	 */
	@Override
	public Respuesta<PagoPendienteView> modificarAdhesionPagoAuto(PagoPendienteView pagoPendienteView) {

		if (!sesionParametros.getPagoPendienteView().getId().equals(pagoPendienteView.getId())) {
			return ejecucionFallidaRespuesta();
		}
		PagoPendiente pagoPendienteSesion = obtenerPagoPendienteSesion(pagoPendienteView.getId());
		if (pagoPendienteSesion == null
				|| (!pagoPendienteSesion.getTipoPago().equals(TipoDePagoEnum.SERVICIOADHERIDOADEBITOAUTOMATICOENCUENTA)
						& !pagoPendienteSesion.getTipoPago().equals(TipoDePagoEnum.PAGOMISCUENTASDEBITO))) {
			return ejecucionFallidaRespuesta();
		}

		ContadorIntentos contador = sesionParametros.getContador();
		if (!contador.permiteReintentar()) {
			return ejecucionFallidaRespuesta();
		}

		// Obtengo datos anteriores del pago automatico
		BigDecimal topeSesion = pagoPendienteSesion.getDatosPagoAutomatico().getTope();
		IdentificacionCuenta idCuentaSesion = pagoPendienteSesion.getDatosPagoAutomatico().getIdentificacionCuenta();

		String nuevoTopeString = pagoPendienteView.getNuevoImporteLimiteAdhesion();
		BigDecimal nuevoTope = null;
		if (nuevoTopeString == null || nuevoTopeString.isEmpty()) {
			nuevoTopeString = "0.00";
		}
		nuevoTope = new BigDecimal(nuevoTopeString);
		nuevoTope = nuevoTope.setScale(2);

		String nuevaCuentaString = pagoPendienteView.getMedioDePago().getNumeroDeCuenta();
		IdentificacionCuenta nuevaCuentaId = null;
		boolean mismaCuenta = idCuentaSesion.equals(nuevaCuentaString);
		if (mismaCuenta) {
			nuevaCuentaId = idCuentaSesion;
		} else {
			nuevaCuentaId = new IdentificacionCuenta(nuevaCuentaString);
		}

		Cuenta cuenta;
		AbstractCuenta tempAbsCuenta = null;
		tempAbsCuenta = cuentaManager.obtenerCuentaById(nuevaCuentaId.toString());
		if (tempAbsCuenta instanceof Cuenta) {
			cuenta = (Cuenta) tempAbsCuenta;
		} else {
			return ejecucionFallidaRespuesta();
		}
		Respuesta<PagoPendienteView> respuestaAdhesion = new Respuesta<PagoPendienteView>();
		Respuesta<ResultadoTransaccion> respuestaService = new Respuesta<ResultadoTransaccion>();
		boolean mismoTope = nuevoTope.compareTo(topeSesion) == 0;
		// Ejecucion para adhesion a Debito, Pago Auto o error:
		switch (pagoPendienteSesion.getTipoPago()) {
		case SERVICIOADHERIDOADEBITOAUTOMATICOENCUENTA:
			ejecutarModificacionAdhesionDebito(pagoPendienteView, respuestaAdhesion, respuestaService,
					pagoPendienteSesion, contador, topeSesion, nuevoTope, mismoTope, nuevaCuentaId, mismaCuenta,
					cuenta);
			break;
		case PAGOMISCUENTASDEBITO:
			String nro = pagoPendienteView.getMedioDePago().getNumeroDeCuenta();
			String cuentaDelServicio = nro.substring(CUENTA_SUC_INI, CUENTA_SUC_FIN)
					+ nro.substring(CUENTA_NRO_INI, CUENTA_NRO_FIN) + nro.substring(CUENTA_DVR_INI, CUENTA_DVR_FIN);
			ejecutarModificacionAdhesionPagoAutomatico(pagoPendienteView, respuestaAdhesion, respuestaService,
					pagoPendienteSesion, contador, topeSesion, nuevoTope, mismoTope, nuevaCuentaId, mismaCuenta, cuenta,
					cuentaDelServicio);
			break;
		default:
			respuestaAdhesion = ejecucionFallidaRespuesta();
			break;
		}
		return respuestaAdhesion;
	}

	/**
	 * Ejecutar modificacion adhesion pago automatico.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @param respuestaAdhesion
	 *            the respuesta adhesion
	 * @param respuestaService
	 *            the respuesta service
	 * @param pagoPendienteSesion
	 *            the pago pendiente sesion
	 * @param contador
	 *            the contador
	 * @param topeSesion
	 *            the tope sesion
	 * @param nuevoTope
	 *            the nuevo tope
	 * @param mismoTope
	 *            the mismo tope
	 * @param nuevaCuentaId
	 *            the nueva cuenta id
	 * @param mismaCuenta
	 *            the misma cuenta
	 * @param cuenta
	 *            the cuenta
	 * @param cuentaDelServicio
	 *            the cuenta del servicio
	 */
	private void ejecutarModificacionAdhesionPagoAutomatico(PagoPendienteView pagoPendienteView,
			Respuesta<PagoPendienteView> respuestaAdhesion, Respuesta<ResultadoTransaccion> respuestaService,
			PagoPendiente pagoPendienteSesion, ContadorIntentos contador, BigDecimal topeSesion, BigDecimal nuevoTope,
			boolean mismoTope, IdentificacionCuenta nuevaCuentaId, boolean mismaCuenta, Cuenta cuenta,
			String cuentaDelServicio) {

		respuestaService = this.pagosService.modificarAdhesionPagoAutoDePagoMisCuentas(sesionCliente.getCliente(),
				pagoPendienteSesion, cuenta, nuevoTope, TipoDeModificacion.MOD_AMBOS, cuentaDelServicio);

		switch (respuestaService.getEstadoRespuesta()) {
		case OK:
			estadisticaManager.add(EstadisticasConstants.EJECUCION_MODIF_ADHESION_PAGOAUTO_PMC,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			ResultadoTransaccion resultadoTransaccion = respuestaService.getRespuesta();
			pagoPendienteView.setNumeroComprobante(resultadoTransaccion.getNumeroComprobante());
			Date fechaTransaccion = resultadoTransaccion.getFechaTransaccion();
			pagoPendienteView.setFecha(fechaFormater.format(fechaTransaccion));
			pagoPendienteView.setHora(horaFormater.format(fechaTransaccion));
			// informo limite anterior
			String limiteAdhesionAnterior = topeSesion == null || topeSesion.compareTo(BigDecimal.ZERO) == 0 ? ""
					: ISBANStringUtils.formatearSaldo(topeSesion);

			pagoPendienteView.setImporteLimiteAdhesion(limiteAdhesionAnterior);
			// informo nuevo limite
			String nuevoLimiteAdhesion = nuevoTope.compareTo(BigDecimal.ZERO) == 0 ? ""
					: ISBANStringUtils.formatearSaldo(nuevoTope);
			pagoPendienteView.setNuevoImporteLimiteAdhesion(nuevoLimiteAdhesion);
			// Informo cuenta
			IdentificacionCuentaView medioDePago = new IdentificacionCuentaView();
			medioDePago.setAliasDeCuenta(cuenta.getAlias());
			medioDePago.setNumeroDeCuenta(nuevaCuentaId.toString());
			medioDePago.setTipoDeCuenta(cuenta.getTipoCuentaEnum().getDescripcion());
			pagoPendienteView.setMedioDePago(medioDePago);
			respuestaAdhesion.setRespuesta(pagoPendienteView);
			respuestaAdhesion.setEstadoRespuesta(EstadoRespuesta.OK);
			pagoPendienteView
					.setMensaje(MessageFormat.format(respuestaService.getItemsMensajeRespuesta().get(0).getMensaje(),
							pagoPendienteView.getNombreEmpresa()));
			break;
		default:
			Mensaje mensaje = mensajeManager
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.PAGO_AUTO_MODIFICADO_PMC_ERROR);
			respuestaAdhesion.add(new ItemMensajeRespuesta(mensaje.getMensaje()));
			if (!contador.excedeReintentos()) {
				respuestaAdhesion.setEstadoRespuesta(EstadoRespuesta.WARNING);
			} else {
				respuestaAdhesion.setEstadoRespuesta(EstadoRespuesta.ERROR);
			}
			break;
		}
	}

	/**
	 * Ejecutar modificacion adhesion debito.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @param respuestaAdhesion
	 *            the respuesta adhesion
	 * @param respuestaService
	 *            the respuesta service
	 * @param pagoPendienteSesion
	 *            the pago pendiente sesion
	 * @param contador
	 *            the contador
	 * @param topeSesion
	 *            the tope sesion
	 * @param nuevoTope
	 *            the nuevo tope
	 * @param mismoTope
	 *            the mismo tope
	 * @param nuevaCuentaId
	 *            the nueva cuenta id
	 * @param mismaCuenta
	 *            the misma cuenta
	 * @param cuenta
	 *            the cuenta
	 */
	private void ejecutarModificacionAdhesionDebito(PagoPendienteView pagoPendienteView,
			Respuesta<PagoPendienteView> respuestaAdhesion, Respuesta<ResultadoTransaccion> respuestaService,
			PagoPendiente pagoPendienteSesion, ContadorIntentos contador, BigDecimal topeSesion, BigDecimal nuevoTope,
			boolean mismoTope, IdentificacionCuenta nuevaCuentaId, boolean mismaCuenta, Cuenta cuenta) {
		if (!mismaCuenta) {
			if (!mismoTope) {
				respuestaService = this.pagosService.modificarAdhesion(sesionCliente.getCliente(), pagoPendienteSesion,
						cuenta, nuevoTope, TipoDeModificacion.MOD_AMBOS);
			} else {
				respuestaService = this.pagosService.modificarAdhesion(sesionCliente.getCliente(), pagoPendienteSesion,
						cuenta, nuevoTope, TipoDeModificacion.MOD_CUENTA_DEBITO);
			}
		} else {
			if (!mismoTope) {
				respuestaService = this.pagosService.modificarAdhesion(sesionCliente.getCliente(), pagoPendienteSesion,
						cuenta, nuevoTope, TipoDeModificacion.MOD_LIMITE_DEBITO);
			} else {
				ejecucionFallidaRespuesta();
			}
		}

		switch (respuestaService.getEstadoRespuesta()) {
		case OK:
			cargarRespuestaOK(pagoPendienteView, respuestaAdhesion, respuestaService, topeSesion, nuevoTope,
					nuevaCuentaId, cuenta);
			break;
		case WARNING:
			estadisticaManager.add(EstadisticasConstants.EJECUCION_MODIF_ADHESION_ADDI,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			respuestaAdhesion.setEstadoRespuesta(EstadoRespuesta.ERROR);
			respuestaAdhesion.setItemMensajeRespuesta(respuestaService.getItemsMensajeRespuesta());
			respuestaAdhesion.setRespuesta(null);
			respuestaAdhesion.setRespuestaVacia(true);
			break;
		case ERROR:
			cargarRespuestaError(respuestaAdhesion, contador);
			break;
		default:
			cargarRespuestaError(respuestaAdhesion, contador);
			break;
		}
	}

	/**
	 * Cargar respuesta error.
	 *
	 * @param respuestaAdhesion
	 *            the respuesta adhesion
	 * @param contador
	 *            the contador
	 */
	private void cargarRespuestaError(Respuesta<PagoPendienteView> respuestaAdhesion, ContadorIntentos contador) {
		estadisticaManager.add(EstadisticasConstants.EJECUCION_MODIF_ADHESION_ADDI,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		Mensaje mensajeError = mensajeManager
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.MODIFICACION_ADHESION_DEBITO_ERROR);
		respuestaAdhesion.add(new ItemMensajeRespuesta(mensajeError.getMensaje()));
		if (!contador.excedeReintentos()) {
			respuestaAdhesion.setEstadoRespuesta(EstadoRespuesta.WARNING);
		} else {
			respuestaAdhesion.setEstadoRespuesta(EstadoRespuesta.ERROR);
		}
	}

	/**
	 * Cargar respuesta OK.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @param respuestaAdhesion
	 *            the respuesta adhesion
	 * @param respuestaService
	 *            the respuesta service
	 * @param topeSesion
	 *            the tope sesion
	 * @param nuevoTope
	 *            the nuevo tope
	 * @param nuevaCuentaId
	 *            the nueva cuenta id
	 * @param cuenta
	 *            the cuenta
	 */
	private void cargarRespuestaOK(PagoPendienteView pagoPendienteView, Respuesta<PagoPendienteView> respuestaAdhesion,
			Respuesta<ResultadoTransaccion> respuestaService, BigDecimal topeSesion, BigDecimal nuevoTope,
			IdentificacionCuenta nuevaCuentaId, Cuenta cuenta) {
		estadisticaManager.add(EstadisticasConstants.EJECUCION_MODIF_ADHESION_ADDI,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		ResultadoTransaccion resultadoTransaccion = respuestaService.getRespuesta();
		pagoPendienteView.setNumeroComprobante(resultadoTransaccion.getNumeroComprobante());
		Date fechaTransaccion = resultadoTransaccion.getFechaTransaccion();
		pagoPendienteView.setFecha(fechaFormater.format(fechaTransaccion));
		pagoPendienteView.setHora(horaFormater.format(fechaTransaccion));

		String limiteAdhesionAnterior = topeSesion == null || topeSesion.compareTo(BigDecimal.ZERO) == 0 ? ""
				: ISBANStringUtils.formatearSaldo(topeSesion);

		pagoPendienteView.setImporteLimiteAdhesion(limiteAdhesionAnterior);

		String nuevoLimiteAdhesion = nuevoTope.compareTo(BigDecimal.ZERO) == 0 ? ""
				: ISBANStringUtils.formatearSaldo(nuevoTope);
		pagoPendienteView.setNuevoImporteLimiteAdhesion(nuevoLimiteAdhesion);

		IdentificacionCuentaView medioDePago = new IdentificacionCuentaView();
		medioDePago.setAliasDeCuenta(cuenta.getAlias());
		medioDePago.setNumeroDeCuenta(nuevaCuentaId.toString());
		medioDePago.setTipoDeCuenta(cuenta.getTipoCuentaEnum().getDescripcion());
		pagoPendienteView.setMedioDePago(medioDePago);
		respuestaAdhesion.setRespuesta(pagoPendienteView);
		respuestaAdhesion.setEstadoRespuesta(EstadoRespuesta.OK);
		Mensaje mensajeOk = mensajeManager
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.MODIFICACION_ADHESION_DEBITO_OK);
		pagoPendienteView
				.setMensaje(MessageFormat.format(mensajeOk.getMensaje(), pagoPendienteView.getNombreEmpresa()));
	}

	/**
	 * Solicitud de modificacion DTF 14368. Solicitar modicacion adhesion pago
	 * automatico DTF:10216
	 * 
	 * 13086 1,2 Ag.Vtos.- PMC PA Solicitud Modificación de adhesión
	 * 
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 */
	@Override
	public Respuesta<PagoPendienteView> solicitarAdhesionPagoAuto(PagoPendienteView pagoPendienteView) {
		Respuesta<PagoPendienteView> respuesta = new Respuesta<PagoPendienteView>();
		sesionParametros.setContador(new ContadorIntentos());
		PagoPendiente pagoPendienteSesion = obtenerPagoPendienteSesion(pagoPendienteView.getId());
		if (pagoPendienteSesion == null) {
			// limpio pagoPendienteView en sesion
			sesionParametros.setPagoPendienteView(null);
			estadisticaManager.add(EstadisticasConstants.SOLICITUD_MODIF_ADHESION_ADDI,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
			return respuesta;
		}
		sesionParametros.setPagoPendienteView(pagoPendienteView);

		String codigoEstadisticaCorrespondiente = "";
		switch (pagoPendienteSesion.getTipoPago()) {
		case PAGOMISCUENTASDEBITO:
			codigoEstadisticaCorrespondiente = EstadisticasConstants.CODIGO_SOLICITUD_MODIFICACION_PAGO_AUTOMATICO;
			break;
		case SERVICIOADHERIDOADEBITOAUTOMATICOENCUENTA:
			codigoEstadisticaCorrespondiente = EstadisticasConstants.SOLICITUD_MODIF_ADHESION_ADDI;
			break;
		default:
			break;
		}

		estadisticaManager.add(codigoEstadisticaCorrespondiente, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuesta(pagoPendienteView);
		return respuesta;
	}

	/**
	 * Solicitud de baja de servicio adherido a pago automatico desde lita de pagos
	 * y vencimientos
	 *
	 * DTF 9802.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 */
	@Override
	public Respuesta<PagoPendienteView> solicitarBajaAdhesion(PagoPendienteView pagoPendienteView) {
		pagoPendienteView.generarNuevoIdSesion();
		Respuesta<PagoPendienteView> respuesta = new Respuesta<PagoPendienteView>();
		PagoPendiente pagoPendienteSesion = obtenerPagoPendienteSesion(pagoPendienteView.getId());
		if (pagoPendienteSesion == null) {
			// limpio pagoPendienteView en sesion
			sesionParametros.setPagoPendienteView(null);
			if (pagoPendienteView.getTipoPago().equalsIgnoreCase(TipoDePagoEnum.PAGOMISCUENTASDEBITO.toString())) {
				estadisticaManager.add(EstadisticasConstants.SOLICITUD_BAJA_ADHESION_ADDI,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			} else {
				if (pagoPendienteView.getTipoPago()
						.equalsIgnoreCase(TipoDePagoEnum.SERVICIOADHERIDOADEBITOAUTOMATICOENCUENTA.toString())) {
					estadisticaManager.add(EstadisticasConstants.SOLICITUD_BAJA_ADHESION_DEBITOAUTOMATICO_ADDI,
							EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				}
			}
			respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
			respuesta.setRespuestaVacia(true);
			return respuesta;
		}
		sesionParametros.setPagoPendienteView(pagoPendienteView);

		String codigoEstadisticaCorrespondiente = "";
		switch (pagoPendienteSesion.getTipoPago()) {
		case PAGOMISCUENTASDEBITO:
			codigoEstadisticaCorrespondiente = EstadisticasConstants.SOLICITUD_BAJA_ADHESION_ADDI;
			break;
		case SERVICIOADHERIDOADEBITOAUTOMATICOENCUENTA:
			codigoEstadisticaCorrespondiente = EstadisticasConstants.SOLICITUD_BAJA_ADHESION_DEBITOAUTOMATICO_ADDI;
			break;
		default:
			LOGGER.info("El pago pendiente tiene un tipo inexistente {}", pagoPendienteSesion.getTipoPago());
			break;
		}

		estadisticaManager.add(codigoEstadisticaCorrespondiente, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuesta(pagoPendienteView);
		return respuesta;
	}

	/**
	 * Accion Baja de adhesion de Pago Automatico DTF: 9802, 9817.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return Respuesta
	 * @see PagoPendiente
	 * @see Cliente
	 */
	@Override
	public Respuesta<PagoPendienteView> ejecutarBajaAdhesion(PagoPendienteView pagoPendienteView) {
		inicializarContadorIntentos(pagoPendienteView);
		if (!isPagoPendienteValido(pagoPendienteView.getId())) {
			String codigoEstadistica = obtenerCodigoEstadisticaPorPagoPendienteView(pagoPendienteView);
			return crearRespuestaErrorConEstadistica(codigoEstadistica);
		} else {
			try {
				PagoPendiente pagoPendienteSesion = obtenerPagoPendienteSesion(pagoPendienteView.getId());
				switch (pagoPendienteSesion.getTipoPago()) {
				case PAGOMISCUENTASDEBITO:
					return sesionParametros.getContador().excedeReintentos(pagoPendienteView.getIdSesion(),
							ejecutarBajaAdhesionPagoMisCuentasDebito(pagoPendienteView, pagoPendienteSesion));
				case SERVICIOADHERIDOADEBITOAUTOMATICOENCUENTA:
					return sesionParametros.getContador().excedeReintentos(pagoPendienteView.getIdSesion(),
							ejecutarBajaAdhesionDebitoAutomatico(pagoPendienteView, pagoPendienteSesion));
				default:
					LOGGER.info("El pago pendiente tiene un tipo inexistente {}", pagoPendienteSesion.getTipoPago());
					String codigoEstadistica = obtenerCodigoEstadisticaPorPagoPendienteView(pagoPendienteView);
					return crearRespuestaWarningConEstadistica(codigoEstadistica);
				}

			} catch (ServiceException e) {
				LOGGER.error(ERROR_LOG, e.getMessage(), e, e);
				String codigoEstadistica = obtenerCodigoEstadisticaPorPagoPendienteView(pagoPendienteView);
				return ejecucionFallidaRespuesta(codigoEstadistica);
			} catch (Exception e) {
				LOGGER.error(ERROR_LOG, e.getMessage(), e, e);
				String codigoEstadistica = obtenerCodigoEstadisticaPorPagoPendienteView(pagoPendienteView);
				return ejecucionFallidaRespuesta(codigoEstadistica);
			}
		}
	}

	/**
	 * Obtener codigo estadistica por pago pendiente view.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the string
	 */
	private String obtenerCodigoEstadisticaPorPagoPendienteView(PagoPendienteView pagoPendienteView) {
		String codigoEstadistica = null;
		if (pagoPendienteView.getTipoPago().equalsIgnoreCase(TipoDePagoEnum.PAGOMISCUENTASDEBITO.toString())) {
			codigoEstadistica = EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_ACCION_BAJA_ADHESION;
		} else {
			if (pagoPendienteView.getTipoPago()
					.equalsIgnoreCase(TipoDePagoEnum.SERVICIOADHERIDOADEBITOAUTOMATICOENCUENTA.toString())) {
				codigoEstadistica = EstadisticasConstants.FEEDBACK_BAJA_ADHESION_DEBITOAUTOMATICO_ADDI;
			}
		}
		return codigoEstadistica;
	}

	/**
	 * Inicializar contador intentos.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 */
	private void inicializarContadorIntentos(PagoPendienteView pagoPendienteView) {
		String mensajeError = mensajeBO
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.DEBITO_AUTOMATICO_BAJA_ADHESION_ERROR).getMensaje();
		if (sesionParametros.getContador() == null) {
			ContadorIntentos contador = new ContadorIntentos();
			contador.setIdView(pagoPendienteView.getIdSesion(), 2, mensajeError);
			sesionParametros.setContador(contador);
		} else {
			sesionParametros.getContador().setIdView(pagoPendienteView.getIdSesion(), 2, mensajeError);
		}
	}

	/**
	 * Validar pago pendiente.
	 *
	 * @param id
	 *            the id
	 * @return true, if successful
	 */
	private boolean isPagoPendienteValido(String id) {
		if (id != null) {
			PagoPendiente pagoPendienteSesion = obtenerPagoPendienteSesion(id);
			if (pagoPendienteSesion != null) {
				IdentificacionCuenta idCuenta = pagoPendienteSesion.getDatosPagoAutomatico().getIdentificacionCuenta();
				AbstractCuenta tempAbsCuenta = cuentaManager.obtenerCuentaById(idCuenta.toString());
				if (tempAbsCuenta instanceof Cuenta || tempAbsCuenta instanceof CuentaCerrada) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Cargar respuesta final.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @param respuestaFinal
	 *            the respuesta final
	 * @param respuestaBaja
	 *            the respuesta baja
	 * @param codigoEstadisticaCorrespondiente
	 *            the codigo estadistica correspondiente
	 * @return the respuesta
	 */
	private Respuesta<PagoPendienteView> cargarRespuestaFinal(PagoPendienteView pagoPendienteView,
			Respuesta<PagoPendienteView> respuestaFinal, Respuesta<ResultadoTransaccion> respuestaBaja,
			String codigoEstadisticaCorrespondiente) {
		ResultadoTransaccion resultadoTransaccion = respuestaBaja.getRespuesta();
		pagoPendienteView.setNumeroComprobante(resultadoTransaccion.getNumeroComprobante());
		Date fechaTransaccion = resultadoTransaccion.getFechaTransaccion();
		pagoPendienteView.setFecha(fechaFormater.format(fechaTransaccion));
		pagoPendienteView.setHora(horaFormater.format(fechaTransaccion));
		respuestaFinal.setRespuesta(pagoPendienteView);
		respuestaFinal.setEstadoRespuesta(EstadoRespuesta.OK);

		Mensaje mensajeDebitoOk = mensajeManager
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.DEBITO_AUTOMATICO_BAJA_ADHESION_OK);
		pagoPendienteView
				.setMensaje(MessageFormat.format(mensajeDebitoOk.getMensaje(), pagoPendienteView.getNombreEmpresa()));

		estadisticaManager.add(EstadisticasConstants.FEEDBACK_BAJA_ADHESION_DEBITOAUTOMATICO_ADDI,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

		estadisticaManager.add(codigoEstadisticaCorrespondiente, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

		return respuestaFinal;
	}

	/**
	 * Ejecutar baja adeshion debito automatico.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @param pagoPendienteSesion
	 *            the pago pendiente sesion
	 * @return the respuesta
	 */
	private Respuesta<PagoPendienteView> ejecutarBajaAdhesionDebitoAutomatico(PagoPendienteView pagoPendienteView,
			PagoPendiente pagoPendienteSesion) {
		sesionParametros.setPagoPendienteView(pagoPendienteView);
		Respuesta<PagoPendienteView> respuestaFinal = new Respuesta<PagoPendienteView>();
		AdhesionDebitoAutomatico adhesionDebitoAutomatico = new AdhesionDebitoAutomatico();
		adhesionDebitoAutomatico.setCuit(ISBANStringUtils.eliminarGuionesDeCuil(pagoPendienteView.getCuit()));
		adhesionDebitoAutomatico.setNombreServicioEmpresa(pagoPendienteSesion.getNombreEmpresaIatx());
		adhesionDebitoAutomatico.setNroPartidaServicioEmpresa(pagoPendienteView.getCodigoClienteEmpresa());

		Respuesta<ResultadoTransaccion> respuestaBaja = this.pagosService
				.ejecutarBajaAdhesionDebitoAutomatico(sesionCliente.getCliente(), adhesionDebitoAutomatico);

		if (respuestaBaja.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {

			return cargarRespuestaFinal(pagoPendienteView, respuestaFinal, respuestaBaja,
					EstadisticasConstants.FEEDBACK_BAJA_ADHESION_DEBITOAUTOMATICO_ADDI);
		} else {
            estadisticaManager.add(EstadisticasConstants.FEEDBACK_BAJA_ADHESION_DEBITOAUTOMATICO_ADDI,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            return Respuesta.copy(PagoPendienteView.class, respuestaBaja);
		}

	}

	/**
	 * Ejecutar baja adeshion pago mis cuentas debito.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @param pagoPendienteSesion
	 *            the pago pendiente sesion
	 * @return the respuesta
	 * @throws ServiceException
	 *             the service exception
	 */
	private Respuesta<PagoPendienteView> ejecutarBajaAdhesionPagoMisCuentasDebito(PagoPendienteView pagoPendienteView,
			PagoPendiente pagoPendienteSesion) throws ServiceException {

		Respuesta<PagoPendienteView> respuestaFinal = new Respuesta<PagoPendienteView>();
		Respuesta<ResultadoTransaccion> respuestaBaja = pagosService.ejecutarBajaAdhesion(sesionCliente.getCliente(),
				pagoPendienteSesion);
		sesionParametros.setPagoPendienteView(pagoPendienteView);
		if (respuestaBaja.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			ResultadoTransaccion resultadoTransaccion = respuestaBaja.getRespuesta();
			pagoPendienteView.setNumeroComprobante(resultadoTransaccion.getNumeroComprobante());
			Date fechaTransaccion = resultadoTransaccion.getFechaTransaccion();
			pagoPendienteView.setFecha(fechaFormater.format(fechaTransaccion));
			pagoPendienteView.setHora(horaFormater.format(fechaTransaccion));
			respuestaFinal.setRespuesta(pagoPendienteView);
			respuestaFinal.setEstadoRespuesta(EstadoRespuesta.OK);
			respuestaFinal.setItemMensajeRespuesta(
					this.getItemMensajeRespuestaConMensaje(CodigoMensajeConstantes.DEBITO_AUTOMATICO_BAJA_ADHESION_OK));
			Mensaje mensajeDebitoOk = mensajeManager
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.PAGO_AUTOMATICO_BAJA_ADHESION_OK);
			pagoPendienteView.setMensaje(
					MessageFormat.format(mensajeDebitoOk.getMensaje(), pagoPendienteView.getNombreEmpresa()));
			estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_ACCION_BAJA_ADHESION,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFinal;
		} else if (respuestaBaja.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
			return crearRespuestaWarningConEstadistica(respuestaBaja,
					EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_ACCION_BAJA_ADHESION);
		} else {
			return crearRespuestaErrorConEstadistica(
					EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_ACCION_BAJA_ADHESION);
		}

	}

	/**
	 * Crear respuesta error con estadistica.
	 *
	 * @param codigoEstadisticaCorrespondiente
	 *            the codigo estadistica correspondiente
	 * @return the respuesta
	 */
	private Respuesta<PagoPendienteView> crearRespuestaErrorConEstadistica(String... codigoEstadisticaCorrespondiente) {
		LOGGER.info(OCURRIO_UN_ERROR_AL_OBTENER_EL_PAGO_EN_LA_SESION);
		Respuesta<PagoPendienteView> respuesta = new Respuesta<PagoPendienteView>();
		for (String codigo : codigoEstadisticaCorrespondiente) {
			if (codigo != null) {
				estadisticaManager.add(codigo, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}
		}
		respuesta.addAll(getItemMensajeRespuestaConMensaje(CodigoMensajeConstantes.CODIGO_ERROR_GENERICO));
		respuesta.setRespuestaVacia(true);
		respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		return respuesta;
	}

	/**
	 * Solicitar eliminacion pago. invoca a solicitarEliminacionPagoPuntual o
	 * solicitarEliminacionPagoProgramado
	 * 
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 * @see ar.com.santanderrio.obp.servicios.pagos.web.manager.PagosManager#solicitarEliminacionPago(ar.com.santanderrio.obp.servicios.pagos.web.view.PagoPendienteView)
	 */
	@Override
	public Respuesta<PagoPendienteView> solicitarEliminacionPago(PagoPendienteView pagoPendienteView) {
		if (pagoPendienteView.getIsPuntual() == true) {
			return solicitarEliminacionPagoPuntual(pagoPendienteView);
		}
		return solicitarEliminacionPagoProgramado(pagoPendienteView);
	}

	/**
	 * Solicita la eliminacion de un pago puntual de pago mis cuentas (NO la baja de
	 * adhesion a pago automatico) Se llama para guardar el elemento en sesion y
	 * para guardar la estadistica de la solicitud.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 * @see ar.com.santanderrio.obp.pagos.web.manager.PagosManager#
	 *      solicitarEliminacionAdhesion(ar.com.santanderrio.obp.pagos.web.view.
	 *      PagoPendienteView)
	 */
	@Override
	public Respuesta<PagoPendienteView> solicitarEliminacionPagoPuntual(PagoPendienteView pagoPendienteView) {
		Respuesta<PagoPendienteView> respuesta = new Respuesta<PagoPendienteView>();
		sesionParametros.setContador(ContextHolder.getContext().getBean(ContadorIntentos.class));
		PagoPendiente pagoPendienteSesion = obtenerPagoPendienteSesion(pagoPendienteView.getId().toString());
		if (pagoPendienteSesion == null) {
			sesionParametros.setPagoPendienteView(null);
			estadisticaManager.add(EstadisticasConstants.ELIMINACION_ADHESION_SOLICITUD,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
			return respuesta;
		}
		sesionParametros.setPagoPendienteView(pagoPendienteView);

		estadisticaManager.add(EstadisticasConstants.ELIMINACION_ADHESION_SOLICITUD,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuesta(pagoPendienteView);
		return respuesta;
	}

	/**
	 * (non-Javadoc).
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 * @see ar.com.santanderrio.obp.servicios.pagos.web.manager.PagosManager#
	 *      eliminarPagaoPuntual(PagoPendienteView)
	 */
	@Override
	public Respuesta<FeedbackMensajeView> eliminarPagoPuntual(PagoPendienteView pagoPendienteView) {

		inicializarContadorDeIntentos(pagoPendienteView);
		Respuesta<FeedbackMensajeView> respuesta = new Respuesta<FeedbackMensajeView>();

		PagoPendiente pagoPendienteSesion = obtenerPagoPendienteSesion(pagoPendienteView.getId().toString());
		if (pagoPendienteSesion == null) {
			return sesionParametros.getContador().excedeReintentos(pagoPendienteView.getIdSesion(), respuesta);
		}

		Respuesta<ResultadoTransaccion> respuestaEliminacion = pagosService
				.ejecutarEliminarPagoPuntual(pagoPendienteSesion, sesionCliente.getCliente());

		if (EstadoRespuesta.OK.equals(respuestaEliminacion.getEstadoRespuesta())) {
			respuesta = mapeo(respuestaEliminacion);
			estadisticaManager.add(EstadisticasConstants.ELIMINACION_ADHESION_EJECUCION,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuesta;
		} else {
			estadisticaManager.add(EstadisticasConstants.ELIMINACION_ADHESION_EJECUCION,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return sesionParametros.getContador().excedeReintentos(pagoPendienteView.getIdSesion(), respuesta);
		}
	}

	/**
	 * Inicializar contador de intentos.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 */
	private void inicializarContadorDeIntentos(PagoPendienteView pagoPendienteView) {
		String mensajeError = mensajeBO
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.PAGO_PUNTUAL_ELIMINADO_ERROR_201).getMensaje();

		if (sesionParametros.getContador() == null) {
			ContadorIntentos contador = new ContadorIntentos();
			contador.setIdView(pagoPendienteView.getIdSesion(), 2, mensajeError);
			sesionParametros.setContador(contador);
		} else {
			sesionParametros.getContador().setIdView(pagoPendienteView.getIdSesion(), 2, mensajeError);
		}

	}

	/**
	 * Mapeo.
	 *
	 * @param resp
	 *            the resp
	 * @return the respuesta
	 */
	private Respuesta<FeedbackMensajeView> mapeo(Respuesta<ResultadoTransaccion> resp) {
		Respuesta<FeedbackMensajeView> respuesta = new Respuesta<FeedbackMensajeView>();
		FeedbackMensajeView feedbackMensajeView = new FeedbackMensajeView();
		feedbackMensajeView.setMensaje(resp.getItemsMensajeRespuesta().get(0).getMensaje());
		respuesta.setEstadoRespuesta(resp.getEstadoRespuesta());
		respuesta.setRespuesta(feedbackMensajeView);
		return respuesta;
	}

	/**
	 * Mapeo.
	 *
	 * @param resp
	 *            the resp
	 * @return the respuesta
	 */
	private Respuesta<FeedbackMensajeView> mapeoToPagoPendienteView(Respuesta<ResultadoTransaccion> resp) {
		Respuesta<FeedbackMensajeView> respuesta = new Respuesta<FeedbackMensajeView>();
		FeedbackMensajeView mensajeView = new FeedbackMensajeView();
		mensajeView.setMensaje(resp.getItemsMensajeRespuesta().get(0).getMensaje());
		respuesta.setEstadoRespuesta(resp.getEstadoRespuesta());
		respuesta.setRespuesta(mensajeView);
		return respuesta;
	}

	/**
	 * Respuesta error.
	 *
	 * @return the respuesta
	 */
	private Respuesta<FeedbackMensajeView> respuestaError() {
		Respuesta<FeedbackMensajeView> respuesta = new Respuesta<FeedbackMensajeView>();
		respuesta.setItemMensajeRespuesta(
				getItemMensajeRespuestaConMensaje(CodigoMensajeConstantes.PAGO_PUNTUAL_ELIMINADO_ERROR_201));
		respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		respuesta.setRespuestaVacia(true);
		return respuesta;
	}

	/**
	 * Gets the mensaje.
	 *
	 * @param codigoMensaje
	 *            the codigo mensaje
	 * @return the mensaje
	 */
	private List<ItemMensajeRespuesta> getItemMensajeRespuestaConMensaje(String codigoMensaje) {
		List<ItemMensajeRespuesta> itemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
		Mensaje mensaje = mensajeManager.obtenerMensajePorCodigo(codigoMensaje);
		ItemMensajeRespuesta item = new ItemMensajeRespuesta(mensaje.getMensaje());
		itemMensajeRespuesta.add(item);
		return itemMensajeRespuesta;
	}

	/**
	 * Validar cuentas monetarias activas en pesos.
	 *
	 * @param respuestaPagosPendientesView
	 *            the respuesta pagos pendientes view
	 */
	private void validarCuentasMonetariasActivasEnPesos(Respuesta<PagosPendientesView> respuestaPagosPendientesView) {
		if (cuentaManager.hasCuentasMonetariasActivasEnPesos()) {
			respuestaPagosPendientesView.getRespuesta().setHabilitarPagoPrestamo(true);
		} else {
			respuestaPagosPendientesView.getRespuesta().setHabilitarPagoPrestamo(false);
		}
	}

	/**
	 * Arma la vista del pago.
	 *
	 * {@link #sumarPagosMensuales(PagoPendiente, PeriodoView)} {@link}
	 * 
	 * @param resp
	 *            the resp
	 * @param consultaPagosView
	 *            the consulta pagos view
	 * @return the respuesta
	 */
	private Respuesta<PagosPendientesView> armarPagoView(Respuesta<List<PagoPendiente>> resp,
			ConsultaPagosView consultaPagosView) {
		Respuesta<PagosPendientesView> respuesta = new Respuesta<PagosPendientesView>();
		PagosPendientesView pagos = new PagosPendientesView();
		setearPeriodos(pagos, consultaPagosView);
		if (resp.getRespuesta().isEmpty()) {
			pagos.setMensaje(this.mensajeManager
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.CALENDARIO_DE_PAGOS_VACIO).getMensaje());
		} else {
			List<PagoPendiente> listaPagosPendientesEnFecha = new ArrayList<PagoPendiente>();
			for (PagoPendiente pagoPendiente : resp.getRespuesta()) {
				if (fechaValida(consultaPagosView, pagoPendiente)) {
					listaPagosPendientesEnFecha.add(pagoPendiente);
				}
			}

			pagoTarjetaCreditoBO.actualizarStopDebit(sesionCliente.getCliente(), listaPagosPendientesEnFecha);

			for (PagoPendiente pagoPendiente : resp.getRespuesta()) {
				if (pagoPendiente == null) {
				}
				if (pagoPendiente.getVencimiento() == null && consultaTodosLosPagos(consultaPagosView)) {
					pagos.addPagoSinVencimiento(convertToView(pagoPendiente));
				} else if (pagoPendiente.getVencimiento() != null) {
					PeriodoView periodoView = getPeriodoView(pagos.getPeriodos(), pagoPendiente.getVencimiento());
					if (fechaValida(consultaPagosView, pagoPendiente)) {
						sumarPagos(pagoPendiente, pagos, periodoView);
					} else {
						sumarPagosMensuales(pagoPendiente, periodoView);
					}

				}
			}
		}
		pagos.setTotalDolares(ISBANStringUtils.formatearSaldoConSigno(pagos.getTotalDolaresN()));
		pagos.setTotalPesos(ISBANStringUtils.formatearSaldoConSigno(pagos.getTotalPesosN()));
		pagos.setTieneTarjetaCredito(revisarSiTieneTarjetaDeCredito());
		pagos.setMostrarOpcionPagoTarjetaCredito(revisarSiCorrespondeMostrarOpcionPagoTarjetaCredito());
		ModuloPermiso permisoPagoTarjetaCredito = moduloPermisoBO
				.obtenerModuloPermisoPorAccion(AccionController.IR_INICIO_PAGO_DE_TARJETA);
		pagos.setMensajeOpcionPagoTarjetaCredito(permisoPagoTarjetaCredito.isModuloOculto() ? permisoPagoTarjetaCredito.getMensaje() : "");
		pagos.setPeriodoTotal(obtenerPeriodoTotal(pagos.getPeriodos()));
		if (consultaTodosLosPagos(consultaPagosView)) {
			pagos.setPeriodoActual(pagos.getPeriodoTotal());
		}

		for (PeriodoView periodoView : pagos.getPeriodos()) {
			periodoView.setTotalDolares(ISBANStringUtils.formatearSaldoConSigno(periodoView.getTotalDolaresD()));
			periodoView.setTotalPesos(ISBANStringUtils.formatearSaldoConSigno(periodoView.getTotalPesosD()));
		}

		respuesta.setRespuesta(pagos);
		respuesta.setEstadoRespuesta(resp.getEstadoRespuesta());
		respuesta.setRespuestaVacia(false);

		Mensaje mensajeRespuesta = null;
		if (respuesta.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
			mensajeRespuesta = mensajeManager
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.CALENDARIO_DE_PAGOS_INCOMPLETO);
			respuesta.add(new ItemMensajeRespuesta(mensajeRespuesta.getMensaje()));
		} else if (respuesta.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			String mes = pagos.getPeriodoActual().getMesNombre();
			mensajeRespuesta = mensajeManager
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.CALENDARIO_DE_PAGOS_ERROR_GENERICO);
			ItemMensajeRespuesta itemMensaje = new ItemMensajeRespuesta();
			itemMensaje.setMensaje(mensajeRespuesta.getMensaje());
			itemMensaje.setExtra(mes);
			respuesta.add(itemMensaje);
		}	
		
		DateTime fecha = new DateTime();
		
		if ("0".equals(consultaPagosView.getMes()) || Integer.toString(fecha.getMonthOfYear()).equals(consultaPagosView.getMes())) {
			identificarPagosVencidosProductosSantander(respuesta);
		} else {
			respuesta.getRespuesta().setPagosPendientesVencidosProductosSantander(sesionParametros.getListaPagosVencidosProductosSantander());
		}
		return respuesta;
	}

	private void identificarPagosVencidosProductosSantander(Respuesta<PagosPendientesView> respuesta) {
		
		List<PagoPendienteView> pagosVencidosProductosSantander = new ArrayList<PagoPendienteView>();
		
		for (PagoPendienteView pago : respuesta.getRespuesta().getPagosPendientesConVencimiento()) {
			String dia = pago.getVencimiento().substring(0, 2);
			String mes = pago.getVencimiento().substring(3, 5);
			String anio = pago.getVencimiento().substring(6, pago.getVencimiento().length());
			DateTime fechaVencimiento = new DateTime(anio+"-"+mes+"-"+dia+"T23:59:59");

			if (!TipoDePagoEnum.PAGOMISCUENTASDEBITO.toString().equals(pago.getTipoPago()) && 
				!TipoDePagoEnum.PAGOMISCUENTASPUNTUAL.toString().equals(pago.getTipoPago()) &&
				!TipoDePagoEnum.PAGOMISCUENTASERRORCONSULTADEBITOAUTOMATICO.toString().equals(pago.getTipoPago()) &&
				fechaVencimiento.isBeforeNow()) {
				BigDecimal bigMonto = new BigDecimal(pago.getImporte().replaceAll("\\.", "").replaceAll("\\,", "\\."));
				if (bigMonto.compareTo(BigDecimal.ZERO) == (1)) {
					pagosVencidosProductosSantander.add(pago);
				}
			}
		}
		sesionParametros.setListaPagosVencidosProductosSantander(pagosVencidosProductosSantander);
		
		respuesta.getRespuesta().setPagosPendientesVencidosProductosSantander(pagosVencidosProductosSantander);
		respuesta.getRespuesta().getPagosPendientesConVencimiento().removeAll(pagosVencidosProductosSantander);
		
	}
	
	
	/**
	 * Obtener periodo total.
	 *
	 * @param periodos
	 *            the periodos
	 * @return the periodo view
	 */
	private PeriodoView obtenerPeriodoTotal(List<PeriodoView> periodos) {
		PeriodoView periodoTotal = new PeriodoView();
		BigDecimal totalPesos = BigDecimal.ZERO;
		BigDecimal totalDolares = BigDecimal.ZERO;
		for (PeriodoView periodo : periodos) {
			totalPesos = totalPesos.add(periodo.getTotalPesosD());
			totalDolares = totalDolares.add(periodo.getTotalDolaresD());
		}
		periodoTotal.setTotalPesos(ISBANStringUtils.formatearSaldoConSigno(totalPesos));
		periodoTotal.setTotalDolares(ISBANStringUtils.formatearSaldoConSigno(totalDolares));
		periodoTotal.setDia("0");
		periodoTotal.setMes("0");
		periodoTotal.setAnio("0");
		periodoTotal.setCantidadDias(31);
		periodoTotal.setMesNombre("");
		return periodoTotal;
	}

	/**
	 * Gets the periodo view.
	 *
	 * @param periodos
	 *            the periodos
	 * @param vencimiento
	 *            the vencimiento
	 * @return the periodo view
	 */
	private PeriodoView getPeriodoView(List<PeriodoView> periodos, Date vencimiento) {
		Calendar fecha = Calendar.getInstance();
		fecha.setTime(vencimiento);
		for (PeriodoView periodoView : periodos) {
			if (periodoView.getAnioInt() == fecha.get(Calendar.YEAR)
					&& periodoView.getMesInt() == fecha.get(Calendar.MONTH) + 1) {
				return periodoView;
			}
		}
		return periodos.get(0);
	}

	/**
	 * Sumar pagos totales.
	 *
	 * {@link #sumarPagosMensuales(PagoPendiente, PeriodoView)}
	 * 
	 * @param pagoPendiente
	 *            the pago pendiente
	 * @param pagos
	 *            the pagos
	 * @param periodoView
	 *            the periodo view
	 */
	private void sumarPagos(PagoPendiente pagoPendiente, PagosPendientesView pagos, PeriodoView periodoView) {
		if (pagoPendiente.getImporte() != null && pagoPendiente.getImporte().compareTo(BigDecimal.ZERO) > 0) {
			pagos.addTotalPesosN(pagoPendiente.getImporte());
		}
		if (pagoPendiente.getImporteUSS() != null && pagoPendiente.getImporteUSS().compareTo(BigDecimal.ZERO) > 0) {
			pagos.addTotalDolaresN(pagoPendiente.getImporteUSS());
		}
		// este if fue agregado nuevo por el dev3
		if (TipoDePagoEnum.TARJETARECARGABLEPROGRAMADO.equals(pagoPendiente.getTipoPago())) {
			pagos.addPagoConVencimiento(getPagoProgramadoTarjetaRecargable(pagoPendiente));
		} else {
			pagos.addPagoConVencimiento(convertToView(pagoPendiente));
		}
		sumarPagosMensuales(pagoPendiente, periodoView);
	}

	/**
	 * Sumar pagos mensuales. Para los totales de los periodos desplegables
	 *
	 * @param pagoPendiente
	 *            the pago pendiente
	 * @param periodoView
	 *            the periodo view
	 */
	private void sumarPagosMensuales(PagoPendiente pagoPendiente, PeriodoView periodoView) {
		if (perteneceAlPeriodoAnualConsultado(pagoPendiente, periodoView)) {
			periodoView.sumarPago();
			if (pagoPendiente.getImporte() != null && pagoPendiente.getImporte().compareTo(BigDecimal.ZERO) > 0) {
				periodoView.addTotalPesosD(pagoPendiente.getImporte());
			}
			if (pagoPendiente.getImporteUSS() != null && pagoPendiente.getImporteUSS().compareTo(BigDecimal.ZERO) > 0) {
				periodoView.addTotalDolaresD(pagoPendiente.getImporteUSS());
			}
		} else {
			LOGGER.info("La deuda " + pagoPendiente.getNombreEmpresa()
					+ " tiene un vencimiento que excede el periodo consultado, vencimiento: "
					+ pagoPendiente.getVencimiento());
		}
	}

	/**
	 * Arma los periodos consecutivos.
	 *
	 * @param pagos
	 *            the pagos
	 * @param consultaPagosView
	 *            the consulta pagos view
	 */
	private void setearPeriodos(PagosPendientesView pagos, ConsultaPagosView consultaPagosView) {
		Calendar now = Calendar.getInstance();
		PeriodoView periodoActual = null;
		if (consultaTodosLosPagos(consultaPagosView)) {
			Calendar cal = Calendar.getInstance();
			int mes = cal.get(Calendar.MONTH) + 1;
			int anioActual = cal.get(Calendar.YEAR);
			periodoActual = new PeriodoView(1, mes, anioActual);
		} else {
			periodoActual = new PeriodoView(1, Integer.parseInt(consultaPagosView.getMes()),
					Integer.parseInt(consultaPagosView.getAnio()));
		}
		pagos.setPeriodoActual(periodoActual);
		List<PeriodoView> periodos = new ArrayList<PeriodoView>();

		for (int i = 0; i < MES_MAX; i++) {
			now.set(Calendar.DAY_OF_MONTH, now.getActualMaximum(Calendar.DAY_OF_MONTH));
			PeriodoView periodo = new PeriodoView(now.get(Calendar.DATE), now.get(Calendar.MONTH) + 1,
					now.get(Calendar.YEAR));
			periodos.add(periodo);
			now.add(Calendar.MONTH, 1);

		}
		pagos.setPeriodos(periodos);
	}

	/**
	 * Checks if is mes actual.
	 *
	 * @param consultaPagosView
	 *            the consulta pagos view
	 * @param pagoPendiente
	 *            the pago pendiente
	 * @return true, if is mes actual
	 */
	private boolean isMesActual(ConsultaPagosView consultaPagosView, PagoPendiente pagoPendiente) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		int mes = c.get(Calendar.MONTH);
		int anio = c.get(Calendar.YEAR);
		boolean mesActual = false;
		if (consultaTodosLosPagos(consultaPagosView)) {
			mesActual = true;
		} else {
			mesActual = mes + 1 == Integer.parseInt(consultaPagosView.getMes())
					&& anio == Integer.parseInt(consultaPagosView.getAnio());			
		}
		if (mesActual && pagoPendiente.getVencimiento().before(c.getTime())) {
			return true;
		}
		return false;
	}

	/**
	 * Valida que el vencimiento este dentro del mes ingresado.
	 *
	 * @param consultaPagosView
	 *            the consulta pagos view
	 * @param pagoPendiente
	 *            the pago pendiente
	 * @return true, if successful
	 */
	private boolean fechaValida(ConsultaPagosView consultaPagosView, PagoPendiente pagoPendiente) {
		if (pagoPendiente.getVencimiento() == null) {
			return false;
		}
		if ((pagoPendiente.getTipoPago().isPermiteMesAnterior() && isMesActual(consultaPagosView, pagoPendiente))) {
			return true;
		}
		Date vencimiento = pagoPendiente.getVencimiento();
		try {
			SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
			Calendar c = Calendar.getInstance();
			Date minDate = null;
			if (consultaTodosLosPagos(consultaPagosView)) {
				Calendar cal = Calendar.getInstance();
				int mesActual = cal.get(Calendar.MONTH) + 1;
				int anioActual = cal.get(Calendar.YEAR);
				minDate = sf.parse("01/" + mesActual + "/" + anioActual);
				c.setTime(minDate);
				c.add(Calendar.MONTH, 11);
			} else {
				minDate = sf.parse("01/" + consultaPagosView.getMes() + "/" + consultaPagosView.getAnio());
				c.setTime(minDate);
			}
			c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			return (minDate.equals(vencimiento) || minDate.before(vencimiento)) && (vencimiento.equals(c.getTime()) || vencimiento.before(c.getTime()));
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * Chequea si la deuda corresponde al año en vigencia consultado, es decir, mes
	 * del periodo + 11.
	 *
	 * @param pagoPendiente
	 *            the pago pendiente
	 * @param periodoView
	 *            the periodo view
	 * @return true, if is mes actual
	 */
	private boolean perteneceAlPeriodoAnualConsultado(PagoPendiente pagoPendiente, PeriodoView periodoView) {
		SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
		Integer mes = periodoView.getMesInt();
		Integer anio = periodoView.getAnioInt() + 1;
		Date maxDate;
		try {
			maxDate = sf.parse("01/" + mes + "/" + anio);
			Calendar c = Calendar.getInstance();
			c.setTime(maxDate);
			return pagoPendiente.getVencimiento().before(c.getTime());
		} catch (ParseException e) {
			LOGGER.error("Ha ocurrido un error al validar las fechas de vencimiento con el periodo consultado.");
			return false;
		}
	}

	/**
	 * Hace la conversion entre el pago y la vista.
	 * 
	 * @param pagoPendiente
	 *            the pago pendiente
	 * @return the pago pendiente view
	 * @lastUpdate Manuel.vargas - Nov 8, 2016. 40787: Setea un mapa de mensajes
	 *             para el tooltip.
	 * @lastUpdate Manuel.vargas - Nov 7, 2016. 9775: Se detemina si es Recarga. Se
	 *             graba estadisticas de Recarga.
	 * @lastUpdate leonardo.medina - Sep 12, 2016
	 * @lastUpdate Manuel.Vargas 41673: alias de TC
	 */
	private PagoPendienteView convertToView(PagoPendiente pagoPendiente) {
		PagoPendienteView pagoView = null;
		
		if (TipoDePagoEnum.TARJETARECARGABLEPAGOPUNTUAL.equals(pagoPendiente.getTipoPago())) {
			return getPagoPuntualTarjetaRecargable(pagoPendiente);
		}
		pagoView = new PagoPendienteView(pagoPendiente.getTipoPago());
		pagoView.generarNuevoIdSesion();
		if (pagoPendiente.getId() != null) {
			pagoView.setId(pagoPendiente.getId().toString());
		}

		if (pagoPendiente.getFechaPagoProgramado() != null) {
			pagoView.setFechaPagoProgramado(fechaFormater.format(pagoPendiente.getFechaPagoProgramado()));
		}

		if (pagoPendiente.getPagoMinimo() != null) {
			pagoView.setPagoMinimo(ISBANStringUtils.formatearSaldoConSigno(pagoPendiente.getPagoMinimo()));
		}

		if (pagoPendiente.getNumeroCuentaBancoDolares() != null && pagoPendiente.getNumeroCuentaBancoDolares() != '0') {
			String nroCuentaBancoDolaresFormateado = StringUtils
					.leftPad(String.valueOf(pagoPendiente.getNumeroCuentaBancoDolares()), 16, CERO);
			Cuenta cuentaBancoDolares = sesionCliente.getCliente().getCuentaPorNumero(nroCuentaBancoDolaresFormateado);
			if (cuentaBancoDolares != null) {
				pagoView.setNumeroCuentaBancoDolares(
						ISBANStringUtils.formatearNumeroCuenta(cuentaBancoDolares.getNroCuentaProducto()));
				pagoView.setAliasCuentaDolares(cuentaBancoDolares.getAlias());
			}
		}

		if (pagoPendiente.getNumeroCuentaBancoPesos() != null && pagoPendiente.getNumeroCuentaBancoPesos() != '0') {
			String nroCuentaBancoPesosFormateado = StringUtils
					.leftPad(String.valueOf(pagoPendiente.getNumeroCuentaBancoPesos()), 16, CERO);
			Cuenta cuentaBancoPesos = sesionCliente.getCliente().getCuentaPorNumero(nroCuentaBancoPesosFormateado);
			if (cuentaBancoPesos != null) {
				pagoView.setNummeroCuentaBancoPesos(
						ISBANStringUtils.formatearNumeroCuenta(cuentaBancoPesos.getNroCuentaProducto()));
				pagoView.setAliasCuentaPesos(cuentaBancoPesos.getAlias());
			}
		}

		if (pagoPendiente.getTipoCuentaTarjeta() != null) {
			pagoView.setTipoTarjetaCodigo(pagoPendiente.getTipoCuentaTarjeta().getCodigo());
			pagoView.setTieneCuentaEnPesos(sesionCliente.getCliente().isTieneCuentaEnPesos());
		}
		pagoView.setCuit(ISBANStringUtils.formatearCuil(pagoPendiente.getCuitEmpresa()));
		pagoView.setCodigoClienteEmpresa(pagoPendiente.getCodigoClienteEmpresa().trim());
		pagoView.setCodigoEmpresa(pagoPendiente.getCodigoEmpresa().trim());
		pagoView.setIdentificacionFactura(pagoPendiente.getIdentificacionDeFactura().trim());
		if (pagoPendiente.getImporte() != null) {
			pagoView.setImporte(ISBANStringUtils.formatearSaldoConSigno(pagoPendiente.getImporte()));
		} else if (pagoPendiente.getTipoPago().equals(TipoDePagoEnum.SERVICIOADHERIDOADEBITOAUTOMATICOENCUENTA)){
			pagoView.setImporte(ISBANStringUtils.formatearSaldoConSigno(BigDecimal.ZERO));
		}
		if (pagoPendiente.getDivisa() != null) {
			pagoView.setMoneda(pagoPendiente.getDivisa().getSimbolo());
		}
		if (pagoPendiente.getImporteUSS() != null && pagoPendiente.getImporteUSS().signum() != 0) {
			pagoView.setImporteUSS(ISBANStringUtils.formatearSaldoConSigno(pagoPendiente.getImporteUSS()));
		}
		if (pagoPendiente.getTipoPago().equals(TipoDePagoEnum.PRESTAMOSPAGOPUNTUAL)
				|| pagoPendiente.getTipoPago().equals(TipoDePagoEnum.PRESTAMODEBITOENCUENTA)
				|| pagoPendiente.getTipoPago().equals(TipoDePagoEnum.PRESTAMOSERRORCONSULTADEBITOAUTOMATICO)) {
			pagoView.setNombreEmpresa(pagoPendiente.getTipoPrestamo());
			pagoView.setTipoPagoDescripcion(null);
			pagoView.setCodigoClienteEmpresa(formatearNumeroPrestamo(pagoView.getCodigoClienteEmpresa()));
		} else {
			if (StringUtils.contains(pagoPendiente.getNombreEmpresa(), "Tarjeta ")) {
				pagoView.setAlias(pagoPendiente.isAlias());
				pagoView.setNumeroTarjeta(ISBANStringUtils.mascaraTarjetaCredito(pagoPendiente.getNroTarjetaCredito(),
						pagoPendiente.getTipoCuentaTarjeta().getCodigo()));
				pagoView.setDescripcionTarjeta(
						pagoPendiente.getNombreEmpresaAbreviado() + " " + pagoView.getNumeroTarjeta());
				if (StringUtils.isEmpty(pagoView.getImporteUSS())) {
					pagoView.setImporteUSS("0,00");
				}
			}
			pagoView.setNombreEmpresa(pagoPendiente.getNombreEmpresa().replace("Tarjeta", ""));
			pagoView.setTipoPagoDescripcion(pagoPendiente.getTipoPago().getNombreTipoDePago());

		}
		// Agregacion de empresa para enviar esta información al stack de nuevo
		// pago.
		MedioPagoView empresa = new MedioPagoView();
		MedioPago medioPago = pagoPendiente.getMedioPago();
		if (medioPago != null) {
			empresa.setNombreFantasia(medioPago.getNombreFantasia());
			empresa.setFiid(medioPago.getFiid());
			empresa.setCuit(medioPago.getCuit());
			empresa.setTipoEmpresa(medioPago.getTipoEmpresa());
			empresa.setRubroFantasia(medioPago.getRubroFantasia());
			empresa.setDatosAdicionales(medioPago.getDatosAdicionales());
			empresa.setPesPrepago(medioPago.getPesPrepago());
			empresa.setPesGifFactura(medioPago.getPesGifFactura());
			if (medioPago.getPesIdentificacion() != null) {
				empresa.setPesIdentificacion(ISBANStringUtils.convertirPrimerLetraEnMayuscula(
						medioPago.getPesIdentificacion().toLowerCase(Locale.getDefault())));
			}
			empresa.setAddiHabilitado(medioPago.getAddiHabilitado());
			empresa.setPesPAHabilitado(medioPago.getPesPAHabilitado());
			empresa.setCuit(medioPago.getCuit());
			empresa.setPesTipoPago(medioPago.getTipoPago());
			if (StringUtils.isBlank(medioPago.getTipoImporte())) {
				empresa.setPesTipoImporte(null);
			} else {
				empresa.setPesTipoImporte(new Integer(medioPago.getTipoImporte()));
			}
			empresa.setAddiLeyendaIdentificacion(medioPago.getAddiLeyendaIdentificacion());
			empresa.setAddiLongitud(medioPago.getAddiLongitud());
			empresa.setAddiGifFactura(medioPago.getAddiGifFactura());
			empresa.setPesTipoPago(medioPago.getTipoPago());

			TipoMedioPagoBO tipoMedioPagoBO = mediosPagoBO.obtenerTipoMedioPago(medioPago);
			if (tipoMedioPagoBO != null) {
				empresa.setTipoNuevoPago(tipoMedioPagoBO.getTipoNuevoPagoEnum().getId());
			}
		}
		pagoView.setEmpresa(empresa);
		pagoView.setIcono(PagoPendienteView.ICONO_PAGO_AUTOMATICO);
		pagoView.setTooltip(
				mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.PAGO_DEBITADO_AUTOMATICO).getMensaje());
		if (pagoPendiente.getTipoPago().equals(TipoDePagoEnum.PAGOMISCUENTASDEBITO)) {
			if (pagoPendiente.getVencimiento() != null) {
				pagoView.setPermiteBajaAdhesion(faltanDosDiasOmas(pagoPendiente.getVencimiento()));
			} else {
				pagoView.setPermiteBajaAdhesion(true);
			}
		}

		if (pagoPendiente.getTipoPago().equals(TipoDePagoEnum.SERVICIOADHERIDOADEBITOAUTOMATICOENCUENTA)) {
			pagoView.setPermiteBajaAdhesion(true);
		}

		if (pagoPendiente.getTipoPago().equals(TipoDePagoEnum.TARJETAPAGOPUNTUAL)
				|| pagoPendiente.getTipoPago().equals(TipoDePagoEnum.PAGOMISCUENTASPUNTUAL)) {
			pagoView.setIcono(PagoPendienteView.ICONO_PAGO_PUNTUAL);
			pagoView.setTooltip(
					mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.PAGO_MANUAL).getMensaje());
			if (pagoPendiente.getTipoPago().equals(TipoDePagoEnum.PAGOMISCUENTASPUNTUAL)) {
				pagoView.setIsRecargar(this.permiteRecarga(pagoPendiente));

				if (pagoPendiente.getNumeroDeVEP() != null) {
					pagoView.setNumeroDeVEP(pagoPendiente.getNumeroDeVEP());
					pagoView.setCantidadDeCuotas(pagoPendiente.getCantidadCuotas());
					pagoView.setPeriodo(pagoPendiente.getPeriodo());
				} else {
					pagoView.setNumeroDeVEP(null);
				}
			}
		}

		if (pagoPendiente.getTipoPago().equals(TipoDePagoEnum.PAGOPROGRAMADO)) {
			pagoView.setTooltip(
					mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.PAGO_UNICO_PROGRAMADO).getMensaje());
		}

		if (pagoPendiente.getTipoPago().equals(TipoDePagoEnum.PAGOMISCUENTASERRORCONSULTADEBITOAUTOMATICO)) {
			pagoView.setIcono(PagoPendienteView.SIN_DESCRIPCION);
			pagoView.setTooltip(PagoPendienteView.SIN_DESCRIPCION);
		}
		if (pagoPendiente.getTipoPago().equals(TipoDePagoEnum.PRESTAMOSPAGOPUNTUAL)
				|| pagoPendiente.getTipoPago().equals(TipoDePagoEnum.PRESTAMODEBITOENCUENTA)
				|| pagoPendiente.getTipoPago().equals(TipoDePagoEnum.PRESTAMOSERRORCONSULTADEBITOAUTOMATICO)) {
			pagoView.setIcono(null);
			pagoView.setTooltip("");
		}

		pagoView.setPagoAutomatico(pagoPendiente.isPagoAutomatico());
		pagoView.setEditable(pagoPendiente.isEditable());
		pagoView.setEditableDetalle(pagoPendiente.isEditableDetalle());
		
		pagoView.setTipoPago(pagoPendiente.getTipoPago().name());
		if (pagoPendiente.getVencimiento() != null) {
			SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
			pagoView.setVencimiento(sf.format(pagoPendiente.getVencimiento()));
		} else  {			
			pagoView.setVencimiento("");
		}
		if (pagoPendiente.getDatosAdicionales() != null) {
			pagoView.setDatosAdicionales(pagoPendiente.getDatosAdicionales());
		} else {
			pagoView.setDatosAdicionales("");
		}
		if (pagoPendiente.getInformacionAdicional() != null) {
			pagoView.setInformacionAdicional(pagoPendiente.getInformacionAdicional());
		} else {
			pagoView.setInformacionAdicional("");
		}
		pagoView.setBotonHabilitado(esBotonHabilitado(pagoPendiente));

		boolean stopDebitRealizado = stopDebitRealizado(pagoPendiente);

		if (stopDebitRealizado) {
			pagoView.setTieneStopDebit(true);
			pagoView.setExisteMensajeContextual(true);
			String codigoMensajeContextual = null;

			switch (pagoPendiente.getTipoPago()) {
			case PAGOMISCUENTASDEBITO:
				codigoMensajeContextual = CodigoMensajeConstantes.CONTEXTUAL_STOPDEBIT_ACTIVO_PAGOS_AUTOMATICOS;
				pagoView.setTooltip("");
				break;
			case SERVICIOADHERIDOADEBITOAUTOMATICOENCUENTA:
				codigoMensajeContextual = CodigoMensajeConstantes.CONTEXTUAL_STOPDEBIT_ACTIVO_DEBITOS_AUTOMATICOS;
				break;
			case TARJETADEBITOAUTOMATICOPAGOMINIMO:
				codigoMensajeContextual = CodigoMensajeConstantes.CONTEXTUAL_STOPDEBIT_ACTIVO_TARJETAS;
				break;
			case TARJETADEBITOAUTOMATICOPAGOTOTAL:
				codigoMensajeContextual = CodigoMensajeConstantes.CONTEXTUAL_STOPDEBIT_ACTIVO_TARJETAS;
				break;
			default:
				break;
			}
			pagoView.setMensajeContextual(
					MessageFormat.format(mensajeManager.obtenerMensajePorCodigo(codigoMensajeContextual).getMensaje(),
							pagoView.getNombreEmpresa()));
		} else {
			pagoView.setTieneStopDebit(false);
			pagoView.setExisteMensajeContextual(false);
		}

		if (pagoPendiente.isPagoAutomatico()) {
			DatosPagoAutomaticoEntity datosPagoAutomatico = pagoPendiente.getDatosPagoAutomatico();
			BigDecimal tope = datosPagoAutomatico.getTope();
			String topeString = null;
			if (tope == null || BigDecimal.ZERO.compareTo(tope) == 0) {
				topeString = "";
			} else {
				topeString = ISBANStringUtils.formatearSaldo(tope);
			}
			pagoView.setImporteLimiteAdhesion(topeString);
			pagoView.setMedioDePago(obtenerMedioDePago(datosPagoAutomatico));

		}
		
		return pagoView;
	}

	/**
	 * Obtener medio de pago.
	 *
	 * @param datosPagoAutomatico
	 *            the datos pago automatico
	 * @return the identificacion cuenta view
	 */
	private IdentificacionCuentaView obtenerMedioDePago(DatosPagoAutomaticoEntity datosPagoAutomatico) {
		IdentificacionCuentaView medioDePago = null;
		IdentificacionCuenta identificacionCuenta = datosPagoAutomatico.getIdentificacionCuenta();
		if (identificacionCuenta != null) {
			medioDePago = new IdentificacionCuentaView();
			String numeroDeCuenta = identificacionCuenta.toString();
			medioDePago.setNumeroDeCuenta(numeroDeCuenta);
			AbstractCuenta cuenta = cuentaManager.obtenerCuentaById(numeroDeCuenta);
			if (cuenta != null) {
				if (StringUtils.isNotBlank(cuenta.getAlias())) {
					medioDePago.setAliasDeCuenta(cuenta.getAlias());
				}
				if (cuenta.isCuentaCerrada()) {
				    medioDePago.setTipoDeCuenta(TipoCuenta.fromCodigo(cuenta.getTipoCuenta()).getDescripcion());
				} else {
				    medioDePago.setTipoDeCuenta(cuenta.getTipoCuentaEnum().getDescripcion());
				}
			} else {
				medioDePago.setTipoDeCuenta(datosPagoAutomatico.getTipoCuenta().getDescripcion());
			}

		}
		return medioDePago;
	}

	/**
	 * Arma el campo que indica si el pago se puede recargar. Consulta de
	 * MediosdePago.txt-â€œPes_codigoRubroâ€� (Columna 15) = â€œRCELâ€�
	 * correspondiente a las empresas que retornaron los resultados de la consulta
	 * anterior.
	 * 
	 * @param pagoPendiente
	 *            the pago pendiente
	 * @return Boolean
	 */
	public Boolean permiteRecarga(PagoPendiente pagoPendiente) {

		Respuesta<MedioPago> mediosPagoRta = mediosPagoBO.getByCodigo(pagoPendiente.getCodigoEmpresa());
		String recargaSegunMedioPago = mediosPagoRta.getRespuesta().getPescodigorubro();
		return PES_CODIGO_RUBRO.equalsIgnoreCase(recargaSegunMedioPago);
	}

	/**
	 * Agrega una barra antes del ultimo numero del prestamo.
	 *
	 * @param numeroDePrestamo
	 *            the numero de prestamo
	 * @return the string
	 */
	private String formatearNumeroPrestamo(String numeroDePrestamo) {
		if (numeroDePrestamo != null) {
			return numeroDePrestamo.substring(0, numeroDePrestamo.length() - 1) + '/'
					+ numeroDePrestamo.substring(numeroDePrestamo.length() - 1);
		}
		return null;
	}

	/**
	 * Faltan dos dias omas.
	 *
	 * @param vencimiento
	 *            the vencimiento
	 * @return true, if successful
	 */
	private boolean faltanDosDiasOmas(Date vencimiento) {
		Calendar hoy = Calendar.getInstance();
		Calendar vencimientoPagoPendiente = Calendar.getInstance();
		vencimientoPagoPendiente.setTime(vencimiento);

		hoy.add(Calendar.DATE, 2);

		if (hoy.compareTo(vencimientoPagoPendiente) <= 0) {
			return true;
		}
		return false;
	}

	/**
	 * Es boton habilitado.
	 *
	 * @param pagoPendiente
	 *            the pago pendiente
	 * @return true, if successful
	 */
	private boolean esBotonHabilitado(PagoPendiente pagoPendiente) {
		boolean habilitado = true;

		// SI ES STOPDEBIT (BOTON HABILITADO), PRIMERO PAGAR (BOTON
		// DESHABILITADO)
		if (pagoPendiente.getTipoPago().equals(TipoDePagoEnum.PAGOMISCUENTASDEBITO)) {
			// ACA ADENTRO ESTA EL PROBLEMA
			if (pagoPendiente.getDatosPagoAutomatico().getFechaStopDebit() == null) {
				habilitado = true;
			} else {
				habilitado = false;
			}
		}

		if (pagoPendiente.getTipoPago().equals(TipoDePagoEnum.SERVICIOADHERIDOADEBITOAUTOMATICOENCUENTA)) {
		    if(pagoPendiente.getHabilitarStopDebit()) {
		        return Boolean.TRUE;
		    }
			String codigoEstado = pagoPendiente.getDatosPagoAutomatico().getCodigoEstadoDDI();

			if (pagoPendiente.getVencimiento() != null && esHoy(pagoPendiente.getVencimiento())) {
				if ("".equals(codigoEstado) || "R00".equals(codigoEstado)) {
					habilitado = true;
				} else {
					habilitado = false;
				}
			} else {
				if ("".equals(codigoEstado) || "R00".equals(codigoEstado) || "R08".equals(codigoEstado)) {
					habilitado = true;
				} else {
					habilitado = false;
				}
			}
		}
		return habilitado;
	}

	/**
	 * Es hoy.
	 *
	 * @param vencimiento
	 *            the vencimiento
	 * @return true, if successful
	 */
	private boolean esHoy(Date vencimiento) {

		Calendar hoy = Calendar.getInstance();
		Calendar vencimientoPagoPendiente = Calendar.getInstance();
		vencimientoPagoPendiente.setTime(vencimiento);

		if (hoy.get(Calendar.DAY_OF_MONTH) == vencimientoPagoPendiente.get(Calendar.DAY_OF_MONTH)
				&& hoy.get(Calendar.MONTH) == vencimientoPagoPendiente.get(Calendar.MONTH)
				&& hoy.get(Calendar.YEAR) == vencimientoPagoPendiente.get(Calendar.YEAR)) {
			return true;
		}
		return false;
	}

	/**
	 * Verifica que la fecha sea valida.
	 *
	 * @param consultaPagosView
	 *            the consulta pagos view
	 * @return true, if successful
	 */
	private boolean fechaValida(ConsultaPagosView consultaPagosView) {
		Calendar cal = Calendar.getInstance();
		int mes = cal.get(Calendar.MONTH);
		int anioActual = cal.get(Calendar.YEAR);
		if (consultaTodosLosPagos(consultaPagosView)) {
			/* Solo para la primera llamada */
			sesionParametros.setRespuestaPagosPendientes(null);
			// consultaPagosView.setMes(String.valueOf(mes + 1));
			// consultaPagosView.setAnio(String.valueOf(anioActual));
			return true;
		}
		int anio = 0;
		try {
			mes = Integer.parseInt(consultaPagosView.getMes());
			anio = Integer.parseInt(consultaPagosView.getAnio());
		} catch (Exception e) {
			LOGGER.error(ERROR_LOG + " Mes: {}, Año: {}.", "Validar fecha", "La fecha no es valida",
					consultaPagosView.getMes(), consultaPagosView.getAnio(), e);
			return false;
		}
		if ((mes < 1 || mes > MES_MAX) || (anio < anioActual || anio > ANIO_MAX)) {
			return false;
		}
		return true;
	}

	/**
	 * Indica si el pago pendiente ya tiene efectuado un stop debit True si esta
	 * hecho False si no esta hecho.
	 *
	 * @param pagoPendiente
	 *            the pago pendiente
	 * @return true, if successful
	 */
	private boolean stopDebitRealizado(PagoPendiente pagoPendiente) {
		boolean realizado = false;

		if (pagoPendiente.getTipoPago().equals(TipoDePagoEnum.PAGOMISCUENTASDEBITO)) {
			if (pagoPendiente.getDatosPagoAutomatico().getFechaStopDebit() != null) {
				realizado = true;
			}
		}
		if (pagoPendiente.getTipoPago().equals(TipoDePagoEnum.SERVICIOADHERIDOADEBITOAUTOMATICOENCUENTA)) {
			String codigoEstado = pagoPendiente.getDatosPagoAutomatico().getCodigoEstadoDDI();

			if ("R08".equals(codigoEstado)) {
				realizado = true;
			}
		}

		if (pagoPendiente.getTipoPago().equals(TipoDePagoEnum.TARJETADEBITOAUTOMATICOPAGOMINIMO)
				|| pagoPendiente.getTipoPago().equals(TipoDePagoEnum.TARJETADEBITOAUTOMATICOPAGOTOTAL)) {
			realizado = pagoPendiente.getTieneStopDebit();
		}
		return realizado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.web.manager.PagosManager#
	 * verDetalle(ar.com.santanderrio.obp.servicios.pagos.web.view.
	 * PagoPendienteView)
	 */
	@Override
	public Respuesta<PagoPendienteView> verDetalle(PagoPendienteView pagoPendienteInput) {
		String codigoEstadistica = EstadisticasConstants.VER_DETALLE_DE_PAGO_DE_SERVICIO_ADDI;
		try {
			PagoPendiente pagoPendienteSesion = obtenerPagoPendienteSesion(pagoPendienteInput.getId());
			if (pagoPendienteSesion != null) {
				switch (pagoPendienteSesion.getTipoPago()) {
				case SERVICIOADHERIDOADEBITOAUTOMATICOENCUENTA:
					LOGGER.info(INFO_ESTADISTICA + codigoEstadistica);
					codigoEstadistica = EstadisticasConstants.VER_DETALLE_DE_PAGO_DE_SERVICIO_ADDI;
					estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
					break;
				default:
					break;
				}
				Respuesta<PagoPendienteView> respuesta = new Respuesta<PagoPendienteView>();
				respuesta.setRespuesta(convertToView(pagoPendienteSesion));
				respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
				respuesta.setRespuestaVacia(false);
				return respuesta;
			}
			estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return ejecucionFallidaRespuesta();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return ejecucionFallidaRespuesta();
		}
	}

	/**
	 * Ejecutar baja pago programado de tarjeta credito. ver: DTF: 10303 iatx:
	 * CNSTJCPAGP
	 *
	 * @author B041299 Manuel.Vargas
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 * @see ar.com.santanderrio.obp.servicios.pagos.web.manager.PagosManager#ejecutarBajaPagoProgramadoDeTarjetaCredito(ar.com.santanderrio.obp.servicios.pagos.web.view.PagoPendienteView)
	 */
	@Override
	public Respuesta<FeedbackMensajeView> ejecutarBajaPagoProgramadoDeTarjetaCredito(
			PagoPendienteView pagoPendienteView) {
		inicializarContadorDeIntentos(pagoPendienteView);
		if (!sesionParametros.getContador().permiteReintentar()) {
			return respuestaError();
		}
		PagoPendiente pagoPendiente = obtenerPagoPendienteSesion(pagoPendienteView.getId().toString());
		if (pagoPendiente == null) {
			return error();
		}
		Respuesta<FeedbackMensajeView> respuesta = new Respuesta<FeedbackMensajeView>();
		Respuesta<ResultadoTransaccion> respuestaBaja = pagoTarjetaCreditoBO
				.ejecutarBajaPagoProgramadoDeTarjetaCredito(pagoPendiente, sesionCliente.getCliente());

		if (EstadoRespuesta.OK.equals(respuestaBaja.getEstadoRespuesta())) {
			respuesta = mapeoToPagoPendienteView(respuestaBaja);
			estadisticaManager.add(EstadisticasConstants.CODIGO_BAJA_PAGO_PROG_TCREDITO,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			respuesta = Respuesta.copy(FeedbackMensajeView.class, respuestaBaja);
			estadisticaManager.add(EstadisticasConstants.CODIGO_BAJA_PAGO_PROG_TCREDITO,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuesta;
	}

	/**
	 * Error.
	 *
	 * @return the respuesta
	 */
	private Respuesta<FeedbackMensajeView> error() {
		Respuesta<FeedbackMensajeView> respuesta = new Respuesta<FeedbackMensajeView>();
		respuesta.setItemMensajeRespuesta(
				getItemMensajeRespuestaConMensaje(CodigoMensajeConstantes.PAGO_PUNTUAL_ELIMINADO_ERROR_201));
		respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.web.manager.PagosManager#
	 * solicitarAdhesionDebitoAutomatico(ar.com.santanderrio.obp.servicios.pagos
	 * .web.view.PagoPendienteView)
	 */
	@Override
	public Respuesta<PagoPendienteView> solicitarAdhesionDebitoAutomatico(PagoPendienteView pagoPendienteView) {
		estadisticaManager.add(EstadisticasConstants.SOLICITUD_ADHESION_DEBITO_AUTOMATICO_CALENDARIO,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		Respuesta<PagoPendienteView> respuesta = new Respuesta<PagoPendienteView>();
		respuesta.setRespuesta(pagoPendienteView);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuestaVacia(false);
//		respuesta.getRespuesta().setMensajeInformacionPagoAdebitar(mensajeBO
//                .obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_MENSAJE_PAGO_PENDIENTE_DE_CONFIRMACION ).getMensaje());
		return respuesta;
	}

	/**
	 * Solicitar adhesion pago automatico.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 * @see ar.com.santanderrio.obp.servicios.pagos.web.manager.PagosManager#solicitarAdhesionPagoAutomatico(ar.com.santanderrio.obp.servicios.pagos.web.view.PagoPendienteView)
	 */
	@Override
	public Respuesta<PagoPendienteView> solicitarAdhesionPagoAutomatico(PagoPendienteView pagoPendienteView) {
		estadisticaManager.add(EstadisticasConstants.SOLICITUD_ADHESION_PAGO_AUTOMATICO_CALENDARIO,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

		pagoPendienteView.setImporteLimiteAdhesion(mensajeBO.obtenerMensajePorCodigo(IMPORTE_LIMITE).getMensaje());

		Respuesta<PagoPendienteView> respuesta = new Respuesta<PagoPendienteView>();
		respuesta.setRespuesta(pagoPendienteView);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuestaVacia(false);
		return respuesta;
	}

	/**
	 * Revisar si tiene tarjeta de credito.
	 *
	 * @return the boolean
	 */
	private Boolean revisarSiTieneTarjetaDeCredito() {
		Boolean tieneTarjetaDeCredito = false;
		for (Cuenta cuenta : sesionCliente.getCliente().getCuentas()) {
			if (cuenta.esTarjetaDeCredito()) {
				tieneTarjetaDeCredito = true;
				break;
			}
		}
		return tieneTarjetaDeCredito;
	}

	/**
	 * Crear respuesta warning con estadistica.
	 *
	 * @param respuestaBaja
	 *            the respuesta baja
	 * @param codigoTransaccionEstadisticasAccionBajaAdhesion
	 *            the codigo transaccion estadisticas accion baja adhesion
	 * @return the respuesta
	 */
	private Respuesta<PagoPendienteView> crearRespuestaWarningConEstadistica(
			Respuesta<ResultadoTransaccion> respuestaBaja, String codigoTransaccionEstadisticasAccionBajaAdhesion) {
		if (codigoTransaccionEstadisticasAccionBajaAdhesion != null) {
			estadisticaManager.add(codigoTransaccionEstadisticasAccionBajaAdhesion,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaFactory.crearRespuestaWarning(PagoPendienteView.class, null,
				respuestaBaja.getItemsMensajeRespuesta());
	}

	/**
	 * Revisar si corresponde mostrar opcion pago tarjeta credito.
	 *
	 * @return the boolean
	 */
	private Boolean revisarSiCorrespondeMostrarOpcionPagoTarjetaCredito() {
		ModuloPermiso permisoPagoTarjetaCredito = moduloPermisoBO
				.obtenerModuloPermisoPorAccion(AccionController.IR_INICIO_PAGO_DE_TARJETA);
		List<Cuenta> cuentasPago = sesionCliente.getCliente().getCuentasParaEfectuarPago();
		if (permisoPagoTarjetaCredito.isModuloOculto() || !revisarSiTieneTarjetaDeCredito()
				|| revisarSiTieneTarjetaDeCredito() && cuentasPago.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * Eliminar pago.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 * @see ar.com.santanderrio.obp.servicios.pagos.web.manager.PagosManager#eliminarPago(ar.com.santanderrio.obp.servicios.pagos.web.view.PagoPendienteView)
	 */
	@Override
	public Respuesta<FeedbackMensajeView> eliminarPago(PagoPendienteView pagoPendienteView) {
		if (pagoPendienteView.getIsPuntual() == true) {
			return eliminarPagoPuntual(pagoPendienteView);
		}
		return ejecutarBajaPagoProgramadoDeTarjetaCredito(pagoPendienteView);
	}

	/**
	 * Solicitar eliminacion pago programado de T.Credito.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 * @see ar.com.santanderrio.obp.servicios.pagos.web.manager.PagosManager#solicitarEliminacionPagoProgramado(ar.com.santanderrio.obp.servicios.pagos.web.view.PagoPendienteView)
	 */
	@Override
	public Respuesta<PagoPendienteView> solicitarEliminacionPagoProgramado(PagoPendienteView pagoPendienteView) {
		Respuesta<PagoPendienteView> respuesta = new Respuesta<PagoPendienteView>();
		sesionParametros.setContador(ContextHolder.getContext().getBean(ContadorIntentos.class));
		PagoPendiente pagoPendienteSesion = obtenerPagoPendienteSesion(pagoPendienteView.getId().toString());
		if (pagoPendienteSesion == null) {
			sesionParametros.setPagoPendienteView(null);
			estadisticaManager.add(EstadisticasConstants.ELIMINACION_ADHESION_SOLICITUD,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
			return respuesta;
		}
		sesionParametros.setPagoPendienteView(pagoPendienteView);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuesta(pagoPendienteView);
		return respuesta;
	}

	/**
	 * /* (non-Javadoc).
	 *
	 * @param pagoPendiente
	 *            the pago pendiente
	 * @return the pago programado tarjeta recargable
	 * @see ar.com.santanderrio.obp.servicios.pagos.web.manager.PagosManager#
	 *      getPagosTotales(ConsultaPagosView)
	 */

	private PagoPendienteView getPagoProgramadoTarjetaRecargable(PagoPendiente pagoPendiente) {
		PagoPendienteView pagoPendienteView = new PagoPendienteView();
		pagoPendienteView.generarNuevoIdSesion();
		pagoPendienteView.setFechaPagoProgramado(pagoPendiente.getFechaProxRecarga());
		pagoPendienteView.setVencimiento(pagoPendiente.getFechaProxRecarga());
		pagoPendienteView.setIsPagoProgramado(true);
		pagoPendienteView.setFrecuenciaRecarga(pagoPendiente.getFrecuenciaRecarga());
		pagoPendienteView.setFechaProxRecarga(pagoPendiente.getFechaProxRecarga());
		pagoPendienteView.setIsRecargable(Boolean.TRUE);
		pagoPendienteView.setNombreEmpresa(pagoPendiente.getNombreEmpresa());
		pagoPendienteView.setDescripcionTarjeta(pagoPendiente.getNombreEmpresa());
		pagoPendienteView.setNroCuentaOrigen(pagoPendiente.getNroCuentaDebito());
		pagoPendienteView.setNumeroTarjeta(pagoPendiente.getNroTarjetaCredito());
		pagoPendienteView.setImporte(pagoPendiente.getImporte().toString());
		pagoPendienteView.setTipoPagoDescripcion(TIPO_DESCRIPCION_TR);
		pagoPendienteView.setEditable(true);
		pagoPendienteView.setIcono(TXT_A);
		pagoPendienteView.setTooltip(TXT_PAGO_MANUALMENTE);
		pagoPendienteView.setBotonHabilitado(true);
		pagoPendienteView.setIsPagoMisCuentas(false);
		pagoPendienteView.setIsTarjetas(true);
		pagoPendienteView.setIsRecargable(Boolean.TRUE);
		pagoPendienteView.setIsRecargar(Boolean.TRUE);
		pagoPendienteView.setIsPuntual(false);
		return pagoPendienteView;
	}

	/**
	 * getPagoPendienteTarjetaRecargable.
	 *
	 * @param pagoPendiente
	 *            the pago pendiente
	 * @return the pago puntual tarjeta recargable
	 */
	private PagoPendienteView getPagoPuntualTarjetaRecargable(PagoPendiente pagoPendiente) {
		PagoPendienteView pagoPendienteView = new PagoPendienteView();

		pagoPendienteView.setIsRecargable(Boolean.TRUE);
		pagoPendienteView.setNombreEmpresa(pagoPendiente.getNombreEmpresa());
		pagoPendienteView.setNumeroTarjeta(pagoPendiente.getNroTarjetaCredito());
		pagoPendienteView.setDescripcionTarjeta(pagoPendienteView.getNombreEmpresa());
		pagoPendienteView.setImporte("0,00");
		pagoPendienteView.setTipoPagoDescripcion("Pago Puntual");
		pagoPendienteView.setEditable(true);
		pagoPendienteView.setIcono("p");
		pagoPendienteView.setTooltip("Este pago se debe realizar manualmente");
		pagoPendienteView.setBotonHabilitado(true);
		pagoPendienteView.setIsPagoMisCuentas(false);
		pagoPendienteView.setIsTarjetas(true);
		pagoPendienteView.setIsPuntual(true);
		pagoPendienteView.setIsRecargar(true);

		return pagoPendienteView;
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
	 * Gets the mensaje BO.
	 *
	 * @return the mensaje BO
	 */
	public MensajeBO getMensajeBO() {
		return mensajeBO;
	}

	/**
	 * Sets the mensaje BO.
	 *
	 * @param mensajeBO
	 *            the new mensaje BO
	 */
	public void setMensajeBO(MensajeBO mensajeBO) {
		this.mensajeBO = mensajeBO;
	}
}
