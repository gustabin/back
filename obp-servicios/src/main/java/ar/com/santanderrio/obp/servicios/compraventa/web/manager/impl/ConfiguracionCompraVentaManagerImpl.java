/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.web.manager.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.bo.ConfiguracionCompraVentaDolaresBO;
import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.CompraVentaDolaresException;
import ar.com.santanderrio.obp.servicios.compraventa.dto.CompraVentaInicioDTO;
import ar.com.santanderrio.obp.servicios.compraventa.web.entity.Cotizacion;
import ar.com.santanderrio.obp.servicios.compraventa.web.manager.ConfiguracionCompraVentaManager;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.CompraVentaStringUtil;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.ErrorCompraVentaEnum;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.CompraVentaDolarView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.bo.CuentasBancaPrivadaBO;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.util.CuentasBancaPrivadaUtil;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.queue.bo.QueueSTBO;
import ar.com.santanderrio.obp.servicios.queue.entities.QueueSTDTO;
import ar.com.santanderrio.obp.servicios.queue.entities.QueueSTOperations;
import ar.com.santanderrio.obp.servicios.queue.utils.QueueSTUtils;
import ar.com.santanderrio.obp.servicios.tenencias.bo.TenenciasDetalleBO;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DatosRespuestaHabilitaCompraVentaUSDEntity;

/**
 * The Class ConfiguracionCompraVentaManagerImpl.
 *
 * @author sabrina.cis
 */
@Component
public class ConfiguracionCompraVentaManagerImpl extends CompraVentaDolaresManagerImpl
		implements ConfiguracionCompraVentaManager {

	/** The LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfiguracionCompraVentaManagerImpl.class);

	/** El horario de inicio del servivio compraVenta por property. */
	@Value("${TRANSFERENCIAS.HORAINICIOCOMPRAVENTA}")
	private String horaInicioCompraVenta;

	/** El horario de fin del servivio compraVenta por property. */
	@Value("${TRANSFERENCIAS.HORAFINCOMPRAVENTA}")
	private String horaFinCompraVenta;

	/** The configuracion compra venta dolares BO. */
	@Autowired
	private ConfiguracionCompraVentaDolaresBO configuracionCompraVentaDolaresBO;

	/** The CuentaManager. */
	@Autowired
	private CuentaManager cuentaManager;

	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The Constant COMPRA. */
	private static final String COMPRA = "C";

	/** The Constant VENTA. */
	private static final String VENTA = "V";
	

	/** The cuenta banca privada BO. */
	@Autowired
	private CuentasBancaPrivadaBO cuentaBancaPrivadaBO;

	/** The ModuloPermiso BO. */
	@Autowired
	private ModuloPermisoBO moduloPermisoBO;

	/** The queue ST BO. */
	@Autowired
	private QueueSTBO queueSTBO;

	
	@Autowired
	private TenenciasDetalleBO tenenciasBO;
	
	/**
	 * Genera la respuesta con las cuentas y la cotizacion de la cuenta favorita
	 * o principal. Si tiene cuentas habilitadas obtiene la cotizacion. Caso
	 * contrario genera la respuesta con mensaje de error 1083. Si no se puede
	 * obtener la informacion genera la respuesta con mensaje de error 1085
	 *
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @param tipoOperacion
	 *            the tipoOperacion
	 * @param fromCuentas
	 *            the from cuentas
	 * @param numeroCuentaDestino
	 *            the numero cuenta destino
	 * @return the Respuesta<CompraVentaDolarView>
	 */
	@Override
	public Respuesta<CompraVentaDolarView> obtenerDatosIniciales(String numeroCuenta, String tipoOperacion, boolean fromCuentas, String numeroCuentaDestino) {
		try {
			if (verificarHoraHabilitada()) {
				Cliente cliente = getCliente();
				if (moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.QUEUE_ST_COMPRAVENTA).tienePermisoDeVisibilidad()
						&& QueueSTUtils.verificarHorarioQueue(QueueSTOperations.COMPRAVENTA)) {
					if (cliente.getQueueTurnId() != null) {
						if (!validarTurno(cliente.getQueueTurnId())) {
							cliente.setQueueTurnId(null);
							estadisticaManager.add(EstadisticasConstants.QUEUE_ST_WAIT_COMPRAVENTA, 
									EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
							return casuistica.crearRespuestaErroneaInformacionNoDisponible();
						} else {
							estadisticaManager.add(EstadisticasConstants.QUEUE_ST_WAIT_COMPRAVENTA, 
									EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
						}
					} else {
						Respuesta<QueueSTDTO> respuestaTurno = queueSTBO.crearTurno(cliente.getDni());
						if (EstadoRespuesta.OK.equals(respuestaTurno.getEstadoRespuesta())) {
							QueueSTDTO queueSTDTO = respuestaTurno.getRespuesta();
							cliente.setQueueTurnId(queueSTDTO.getTurn().getId());
							List<ItemMensajeRespuesta> itemsMensaje = crearMensajeWarningEsperaTurno();
							CompraVentaDolarView view = new CompraVentaDolarView();
							view.setN(queueSTDTO.getCount());
							view.setwT(queueSTDTO.getTurn().getRemainingTime());
							estadisticaManager.add(EstadisticasConstants.QUEUE_ST_WAIT_COMPRAVENTA, 
									EstadisticasConstants.CODIGO_ESTADISTICAS_WARNING);
							return casuistica.crearRespuestaWarning(CompraVentaDolarView.class, view, itemsMensaje);
						} else {
							estadisticaManager.add(EstadisticasConstants.QUEUE_ST_WAIT_COMPRAVENTA, 
									EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
						}
					}
				}
				iniciarContadorReintentos(numeroCuenta);
				List<CuentasAdhesionDebitoView> listaCuentasView = obtenerCuentas(cliente, numeroCuenta);
				if (cliente.getClienteBancaPrivada()) {
					List<CuentasAdhesionDebitoView> listaCuentasBancaPrivadaView = obtenerCuentasBancaPrivada(cliente);
					listaCuentasView.addAll(listaCuentasBancaPrivadaView);
				}
				List<CuentasAdhesionDebitoView> listaCuentasViewOrigen = obtenerLasCuentasOrigen(listaCuentasView,
						numeroCuenta, tipoOperacion);
				List<CuentasAdhesionDebitoView> listaCuentasViewDestino = obtenerLasCuentasDestino(listaCuentasView,
						tipoOperacion);
				if (listaCuentasViewOrigen.isEmpty() && listaCuentasViewDestino.isEmpty()) {
					return casuistica.crearRespuestaErroneaCuentaInhabilitada2();
				}
				if (listaCuentasViewOrigen.isEmpty()) {
					return casuistica.crearRespuestaErroneaCuentaInhabilitada1();
				}
				Cuenta cuentaOrigen = obtenerCuentaHabilitadaCV(cliente, listaCuentasViewOrigen,
						listaCuentasViewDestino, tipoOperacion);

				if (cuentaOrigen == null) {
					return casuistica.crearRespuestaErroneaCuentaInhabilitada2();
				}

				return crearRespuestaInicialConCotizacion(cliente, obtenerTipoOperacion(tipoOperacion),
						listaCuentasViewOrigen, listaCuentasViewDestino, cuentaOrigen , fromCuentas, numeroCuentaDestino);
			}
			grabarEstadistica(fromCuentas, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);

			return casuistica.crearRespuestaErroneaCuentaOrigenFueraHorario();
		} catch (Exception e) {
			LOGGER.info(SERVICIO_DEVOLVIO_LOG_LABEL, e);
			return casuistica.crearRespuestaErroneaInformacionNoDisponible();
		}
	}

    /**
     * Validar turno.
     *
     * @param turnId the turn id
     * @return true, if successful
     */
    private boolean validarTurno(String turnId) {
		Respuesta<QueueSTDTO> respuestaTurno = queueSTBO.verificarTurno(turnId);
		if (EstadoRespuesta.OK.equals(respuestaTurno.getEstadoRespuesta())) {
			QueueSTDTO queueSTDTO = respuestaTurno.getRespuesta();
			return QueueSTUtils.esTurnoHabilitado(queueSTDTO.getTurn());
		} else if (EstadoRespuesta.ERROR.equals(respuestaTurno.getEstadoRespuesta())
				&& TipoError.QUEUE_ERROR_TURN_NOT_FOUND.getDescripcion()
						.equals(respuestaTurno.getItemsMensajeRespuesta().get(0).getTipoError())) {
			return false;			
		}
		// Ante falla se permite continuar
		return true;
	}

	/**
	 * Crear mensaje warning espera turno.
	 *
	 * @return the list
	 */
	private List<ItemMensajeRespuesta> crearMensajeWarningEsperaTurno() {
		List<ItemMensajeRespuesta> itemsMensaje = new ArrayList<ItemMensajeRespuesta>();
		ItemMensajeRespuesta itemMensaje = new ItemMensajeRespuesta();
		itemMensaje.setTipoError(TipoError.QUEUE_WAIT.getDescripcion());	
		itemsMensaje.add(itemMensaje);
		return itemsMensaje;
	}

	/**
	 * Grabar estadistica.
	 *
	 * @param fromCuentas
	 *            the from cuentas
	 * @param resultado
	 *            the resultado
	 */
	private void grabarEstadistica(boolean fromCuentas, String resultado) {
		grabarEstadisticaSegunOrigen(fromCuentas,
				EstadisticasConstants.CODIGO_ESTADISTICA_COMPRA_DE_DOLARES_DESDE_CUENTAS,
				EstadisticasConstants.CODIGO_ESTADISTICA_COMPRA_DE_DOLARES_DESDE_TRANSACCIONES, resultado);
	}

	/**
	 * Grabar estadistica segun origen.
	 *
	 * @param fromCuentas
	 *            the from cuentas
	 * @param estadisticaFromCentas
	 *            the estadistica from centas
	 * @param estadisticaFromTrans
	 *            the estadistica from trans
	 * @param resultado
	 *            the resultado
	 */
	private void grabarEstadisticaSegunOrigen(boolean fromCuentas, String estadisticaFromCentas,
			String estadisticaFromTrans, String resultado) {
		if (fromCuentas) {
			estadisticaManager.add(estadisticaFromCentas, resultado);
		} else {
			estadisticaManager.add(estadisticaFromTrans, resultado);
		}
	}

	/**
	 * Obtener cuenta habilitada CV.
	 *
	 * @param cliente
	 *            the cliente
	 * @param listaCuentasViewOrigen
	 *            the lista cuentas view origen
	 * @param listaCuentasViewDestino
	 *            the lista cuentas view destino
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @return the cuenta
	 */
	private Cuenta obtenerCuentaHabilitadaCV(Cliente cliente, List<CuentasAdhesionDebitoView> listaCuentasViewOrigen,
			List<CuentasAdhesionDebitoView> listaCuentasViewDestino, String tipoOperacion) {
		if ("compra".equals(tipoOperacion)) {
			return obtenerCuentaHabilitada(cliente, listaCuentasViewOrigen, true);
		} else {
			return obtenerCuentaHabilitada(cliente, listaCuentasViewDestino, false);
		}
	}

	/**
	 * Obtener las cuentas destino.
	 *
	 * @param listaCuentasView
	 *            the lista cuentas view
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @return the list
	 */
	private List<CuentasAdhesionDebitoView> obtenerLasCuentasDestino(List<CuentasAdhesionDebitoView> listaCuentasView,
			String tipoOperacion) {
		// Aca traigo la destino de sesion, separo y despues reescribo la
		// favorita
		List<CuentasAdhesionDebitoView> listaCopiaDestino = new ArrayList<CuentasAdhesionDebitoView>();
		for (CuentasAdhesionDebitoView cuentaDestino : listaCuentasView) {
			if (cuentaDestino.getShowSaldoDolares()) {
				if ("compra".equals(tipoOperacion)) {
					if ((sesionCompraVenta.getNumeroCuentaDestino() != null)
							&& !(sesionCompraVenta.getNumeroCuentaDestino() == sesionCompraVenta
									.getNumeroCuentaOrigen())
							&& ((sesionCompraVenta.getNumeroCuentaDestino().equals(cuentaDestino.getNumero()))
									|| cuentaDestino.getIsFavorito())) {
						// Si esFavoritaTrue(que seria favorita de origen o es
						// la que voy a poner en favorita la copio, else la
						// mando como esta
						listaCopiaDestino.add(copiarCuentaYCambiarFavorito(cuentaDestino));
					} else {
						listaCopiaDestino.add(cuentaDestino);
					}
				} else {
					listaCopiaDestino.add(cuentaDestino);
				}
			}
		}
		return listaCopiaDestino;
	}

	/**
	 * Copiar cuenta Y cambiar favorito.
	 *
	 * @param cuentaDestino
	 *            the cuenta destino
	 * @return the cuentas adhesion debito view
	 */
	private CuentasAdhesionDebitoView copiarCuentaYCambiarFavorito(CuentasAdhesionDebitoView cuentaDestino) {
		CuentasAdhesionDebitoView cuentaDestinoCopia = new CuentasAdhesionDebitoView();
		cuentaDestinoCopia.setAbreviaturaTipoCuenta(cuentaDestino.getAbreviaturaTipoCuenta());
		cuentaDestinoCopia.setAlias(cuentaDestino.getAlias());
		cuentaDestinoCopia.setApellidoCliente(cuentaDestino.getApellidoCliente());
		cuentaDestinoCopia.setCbu(cuentaDestino.getCbu());
		cuentaDestinoCopia.setDescripcionTipoCuenta(cuentaDestino.getDescripcionTipoCuenta());
		cuentaDestinoCopia.setFechaDesdeMovimiento(cuentaDestino.getFechaDesdeMovimiento());
		cuentaDestinoCopia.setFechaHastaMovimiento(cuentaDestino.getFechaHastaMovimiento());
		cuentaDestinoCopia.setHasAlias(cuentaDestino.getHasAlias());
		cuentaDestinoCopia.setIsCerrada(cuentaDestino.getIsCerrada());
		cuentaDestinoCopia.setIsCuentaSueldo(cuentaDestino.getIsCuentaSueldo());
		cuentaDestinoCopia.setIsCuentaUnica(cuentaDestino.getIsCuentaUnica());
		cuentaDestinoCopia.setIsDescubiertoUtilizado(cuentaDestino.getIsDescubiertoUtilizado());
		cuentaDestinoCopia.setIsFavorito(!cuentaDestino.getIsFavorito());
		cuentaDestinoCopia.setIsSaldoCajaAhorroCero(cuentaDestino.getIsSaldoCajaAhorroCero());
		cuentaDestinoCopia.setIsSaldoCajaAhorroNegativo(cuentaDestinoCopia.getIsSaldoCajaAhorroNegativo());
		cuentaDestinoCopia.setIsSaldoCuentaCorrienteCero(cuentaDestino.getIsSaldoCuentaCorrienteCero());
		cuentaDestinoCopia.setIsSaldoCuentaCorrienteNegativo(cuentaDestinoCopia.getIsSaldoCuentaCorrienteNegativo());
		cuentaDestinoCopia.setIsSaldoDescubiertoCero(cuentaDestino.getIsSaldoDescubiertoCero());
		cuentaDestinoCopia.setIsSaldoDescubiertoNegativo(cuentaDestino.getIsSaldoDescubiertoNegativo());
		cuentaDestinoCopia.setIsSaldoDolaresCero(cuentaDestino.getIsSaldoDolaresCero());
		cuentaDestinoCopia.setIsSaldoDolaresNegativo(cuentaDestino.getIsSaldoDolaresNegativo());
		cuentaDestinoCopia.setIsSaldoPesosCero(cuentaDestino.getIsSaldoPesosCero());
		cuentaDestinoCopia.setIsSaldoPesosNegativo(cuentaDestino.getIsSaldoPesosNegativo());
		cuentaDestinoCopia.setIsTraspasoAutomatico(cuentaDestino.getIsTraspasoAutomatico());
		cuentaDestinoCopia.setNombreCliente(cuentaDestino.getNombreCliente());
		cuentaDestinoCopia.setNumero(cuentaDestino.getNumero());
		cuentaDestinoCopia.setNumeroIdentificacion(cuentaDestino.getNumeroIdentificacion());
		cuentaDestinoCopia.setSaldoCajaAhorro(cuentaDestino.getSaldoCajaAhorro());
		cuentaDestinoCopia.setSaldoCuentaCorriente(cuentaDestino.getSaldoCuentaCorriente());
		cuentaDestinoCopia.setSaldoDescubierto(cuentaDestino.getSaldoDescubierto());
		cuentaDestinoCopia.setSaldoDolares(cuentaDestino.getSaldoDolares());
		cuentaDestinoCopia.setSaldoPesos(cuentaDestino.getSaldoPesos());
		cuentaDestinoCopia.setShowDescubierto(cuentaDestino.getShowDescubierto());
		cuentaDestinoCopia.setShowSaldoDolares(cuentaDestino.getShowSaldoDolares());
		cuentaDestinoCopia.setShowSaldoPesos(cuentaDestino.getShowSaldoPesos());
		cuentaDestinoCopia.setSignoSaldoCajaAhorro(cuentaDestino.getSignoSaldoCajaAhorro());
		cuentaDestinoCopia.setSignoSaldoCuentaCorriente(cuentaDestino.getSignoSaldoCuentaCorriente());
		cuentaDestinoCopia.setSignoSaldoDolares(cuentaDestino.getSignoSaldoDolares());
		cuentaDestinoCopia.setSignoSaldoPesos(cuentaDestino.getSignoSaldoPesos());
		cuentaDestinoCopia.setTipoIdentificacion(cuentaDestino.getTipoIdentificacion());
		cuentaDestinoCopia.setUrlReporteCBU(cuentaDestino.getUrlReporteCBU());
		return cuentaDestinoCopia;
	}

	/**
	 * Obtener las cuentas origen.
	 *
	 * @param listaCuentasView
	 *            the lista cuentas view
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @return the list
	 */
	private List<CuentasAdhesionDebitoView> obtenerLasCuentasOrigen(List<CuentasAdhesionDebitoView> listaCuentasView,
			String numeroCuenta, String tipoOperacion) {
		// Aca solo separo las origen pues ya estan ordenadas y con su favorita
		List<CuentasAdhesionDebitoView> listaCopiaOrigen = new ArrayList<CuentasAdhesionDebitoView>();
		for (CuentasAdhesionDebitoView cuentaOrigen : listaCuentasView) {
			if (cuentaOrigen.getShowSaldoPesos()) {
				if ("compra".equals(tipoOperacion)) {
					listaCopiaOrigen.add(cuentaOrigen);
				} else {
					if ((sesionCompraVenta.getNumeroCuentaDestino() != null)
							&& !(sesionCompraVenta.getNumeroCuentaDestino() == sesionCompraVenta
									.getNumeroCuentaOrigen())
							&& ((sesionCompraVenta.getNumeroCuentaDestino().equals(cuentaOrigen.getNumero()))
									|| cuentaOrigen.getIsFavorito())) {
						// Si esFavoritaTrue(que seria favorita de origen o es
						// la que voy a poner en favorita la copio, else la
						// mando como esta
						listaCopiaOrigen.add(copiarCuentaYCambiarFavorito(cuentaOrigen));
					} else {
						listaCopiaOrigen.add(cuentaOrigen);
					}
				}
			}
		}
		return listaCopiaOrigen;
	}

	/**
	 * Obtener la Cotizacion para la Compra de la Cuenta Origen seleccionada.
	 *
	 * @param cotizacion
	 *            the cotizacion
	 * @return the Respuesta<CompraVentaDolarView>
	 */
	@Override
	public Respuesta<CompraVentaDolarView> obtenerCotizacionParaCompra(Cotizacion cotizacion) {
		try {
			return crearRespuestaCotizacion(getCliente(), cotizacion, CompraVentaStringUtil.OPERACION_COMPRA);
		} catch (Exception e) {
			LOGGER.info(SERVICIO_DEVOLVIO_LOG_LABEL, e);
			return casuistica.crearRespuestaErroneaInformacionNoDisponible();
		}
	}

	/**
	 * Obtener la Cotizacion para la Venta de la Cuenta Origen seleccionada.
	 *
	 * @param cotizacion
	 *            the cotizacion
	 * @return the Respuesta<CompraVentaDolarView>
	 */
	@Override
	public Respuesta<CompraVentaDolarView> obtenerCotizacionParaVenta(Cotizacion cotizacion) {
		try {
			return crearRespuestaCotizacion(getCliente(), cotizacion, CompraVentaStringUtil.OPERACION_VENTA);
		} catch (Exception e) {
			LOGGER.info(SERVICIO_DEVOLVIO_LOG_LABEL, e);
			return casuistica.crearRespuestaErroneaInformacionNoDisponible();
		}
	}

	/**
	 * Iniciar contador reintentos, cuando se ingresa a la configuracion por
	 * primera ves.
	 *
	 * @param numeroCuenta
	 *            the numero cuenta
	 */
	private void iniciarContadorReintentos(String numeroCuenta) {
		if (numeroCuenta == null) {
			sesionCompraVenta.setContadorCompra(null);
			sesionCompraVenta.setContadorVenta(null);
			sesionCompraVenta.setNumeroCuentaDestino(null);
			sesionCompraVenta.setNumeroCuentaOrigen(null);
		}
	}

	/**
	 * Verifica este en horario habilitado para operar.
	 *
	 * @return the boolean
	 * @throws ParseException
	 *             the parse exception
	 */
	public Boolean verificarHoraHabilitada() throws ParseException {
		if (!ISBANStringUtils.isNullOrBlank(horaInicioCompraVenta)
				&& !ISBANStringUtils.isNullOrBlank(horaFinCompraVenta)) {
			return ISBANStringUtils.compararHoras(horaInicioCompraVenta, horaFinCompraVenta);
		}
		return false;
	}

	/**
	 * Obtener tipo operacion.
	 *
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @return the string
	 */
	private String obtenerTipoOperacion(String tipoOperacion) {
		if (tipoOperacion != null) {
			if (tipoOperacion.equals(CompraVentaStringUtil.OPERACION_COMPRA_DETALLE)) {
				return CompraVentaStringUtil.OPERACION_COMPRA;
			} else {
				return CompraVentaStringUtil.OPERACION_VENTA;
			}
		}
		return CompraVentaStringUtil.OPERACION_COMPRA;
	}

	/**
	 * Obtener cuentas.
	 *
	 * @param cliente
	 *            the cliente
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @return the list
	 */
	private List<CuentasAdhesionDebitoView> obtenerCuentas(Cliente cliente, String numeroCuenta) {
		List<CuentasAdhesionDebitoView> listaCuentasView = new ArrayList<CuentasAdhesionDebitoView>();
		try {
			Respuesta<CuentasView> respuestaCuentasView = obtenerCuentasOrdenadas(cliente, numeroCuenta);
			if (!respuestaCuentasView.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)
					&& !respuestaCuentasView.getRespuesta().getCuentas().isEmpty()
					&& tieneCuentasHabilitadas(respuestaCuentasView, cliente)) {
				listaCuentasView = respuestaCuentasView.getRespuesta().getCuentas();
				util.formatearSaldosYTipoCuentaDeCuentasList(listaCuentasView);
				subirCuentasASesion(listaCuentasView);
				return listaCuentasView;
			}
			return listaCuentasView;
		} catch (Exception e) {
			LOGGER.info(SERVICIO_DEVOLVIO_LOG_LABEL, e);
			return listaCuentasView;
		}
	}

	/**
	 * Subir cuentas A sesion.
	 *
	 * @param cuentasAdhesionDebitoView
	 *            the cuentas adhesion debito view
	 */
	private void subirCuentasASesion(List<CuentasAdhesionDebitoView> cuentasAdhesionDebitoView) {
		List<CuentasAdhesionDebitoView> cuentasPesos = new ArrayList<CuentasAdhesionDebitoView>();
		List<CuentasAdhesionDebitoView> cuentasDolares = new ArrayList<CuentasAdhesionDebitoView>();
		for (CuentasAdhesionDebitoView cuenta : cuentasAdhesionDebitoView) {
			if (cuenta.getShowSaldoPesos()) {
				cuentasPesos.add(cuenta);
			}
			if (cuenta.getShowSaldoDolares()) {
				cuentasDolares.add(cuenta);
			}
		}
		sesionCompraVenta.setCuentasPesos(cuentasPesos);
		sesionCompraVenta.setCuentasDolares(cuentasDolares);
	}

	/**
	 * Genera la respuesta con la cotizacion de la cuenta seleccionada. Si tiene
	 * cuentas habilitadas obtiene la cotizacion. Caso contrario genera la
	 * respuesta con mensaje de error 1083. Si no se puede obtener la
	 * informacion genera la respuesta con mensaje de error 1085
	 *
	 * @param cliente
	 *            the cliente
	 * @param cotizacion
	 *            the cotizacion
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @return the respuesta
	 */
	private Respuesta<CompraVentaDolarView> crearRespuestaCotizacion(Cliente cliente, Cotizacion cotizacion,
			String tipoOperacion) {
		try {
			boolean canalBancaPrivada = false;
			Cuenta cuentaOrigen = new Cuenta();
			IdentificacionCuenta idOrigen = new IdentificacionCuenta(cotizacion.getCuentaOrigen());
			IdentificacionCuenta idDestino = new IdentificacionCuenta(cotizacion.getCuentaDestino());

			if (!CuentasBancaPrivadaUtil.isCuentaBP(idOrigen.getNroSucursal())) {
				cuentaOrigen = obtenerCuentaHabilitada(cliente, cotizacion.getCuentaOrigen());
			} else {
				cuentaOrigen = (Cuenta) cuentaBancaPrivadaBO.buscarCuentaPorId(cliente, idOrigen);
				canalBancaPrivada = true;
			}
			if (CuentasBancaPrivadaUtil.isCuentaBP(idDestino.getNroSucursal())) {
				canalBancaPrivada = true;
			}
			return obtenerRespuestaCotizacion(cliente, cuentaOrigen, tipoOperacion, cotizacion.getFromCuentas(), canalBancaPrivada);
		} catch (Exception e) {
			LOGGER.info(SERVICIO_DEVOLVIO_LOG_LABEL, e);
			return casuistica.crearRespuestaErroneaInformacionNoDisponible();
		}
	}

	/**
	 * Obtener cuenta habilitada.
	 *
	 * @param cliente
	 *            the cliente
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @return the cuenta
	 * @throws ServiceException
	 *             the service exception
	 */
	private Cuenta obtenerCuentaHabilitada(Cliente cliente, String numeroCuenta) throws ServiceException {
		Cuenta cuenta = (Cuenta) obtenerCuenta(cliente, numeroCuenta);
		if (cuenta != null && estaHabilitadaParaCompraVentaDolar(cuenta)) {
			return cuenta;
		}
		Cuenta cuentaPrivada = (Cuenta) cuentaBancaPrivadaBO.buscarCuentaPorId(cliente, new IdentificacionCuenta(numeroCuenta));;
		if (cuentaPrivada != null) {
			return cuentaPrivada;
		}
		throw new ServiceException();
	}

	/**
	 * Obtener respuesta inicial con cotizacion.
	 *
	 * @param cliente
	 *            the cliente
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @param listaCuentasViewOrigen
	 *            the lista cuentas view origen
	 * @param listaCuentasViewDestino
	 *            the lista cuentas view destino
	 * @param cuentaOrigen
	 *            the cuenta origen
	 * @param fromCuentas
	 *            the from cuentas
	 * @param numeroCuentaDestino
	 *            the numero cuenta destino
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	private Respuesta<CompraVentaDolarView> crearRespuestaInicialConCotizacion(Cliente cliente, String tipoOperacion,
			List<CuentasAdhesionDebitoView> listaCuentasViewOrigen,
			List<CuentasAdhesionDebitoView> listaCuentasViewDestino, Cuenta cuentaOrigen, boolean fromCuentas, String numeroCuentaDestino)
			throws BusinessException, CompraVentaDolaresException {
		boolean isBancaPrivada = false;
		IdentificacionCuenta idDestino = null;	
		if (numeroCuentaDestino != null) {
		   idDestino = new IdentificacionCuenta(numeroCuentaDestino);		
		}

		if (CuentasBancaPrivadaUtil.isCuentaBP(cuentaOrigen.getNroSucursal()) 
		    || (idDestino != null && CuentasBancaPrivadaUtil.isCuentaBP(idDestino.getNroSucursal()))) {
			isBancaPrivada = true;
		}
		Respuesta<CompraVentaInicioDTO> respuestaCompraVentaInicioDTO = configuracionCompraVentaDolaresBO
				.obtenerCotizacion(cliente, cuentaOrigen, tipoOperacion, fromCuentas, isBancaPrivada);
		if (casuistica.esRespuestaOK(respuestaCompraVentaInicioDTO.getEstadoRespuesta())) {
			return casuistica.crearRespuestaOk(CompraVentaDolarView.class, new CompraVentaDolarView(
					respuestaCompraVentaInicioDTO.getRespuesta(), listaCuentasViewOrigen, listaCuentasViewDestino));
		}
		if (TipoError.ERROR_BOTON_PANICO.getDescripcion().equals(respuestaCompraVentaInicioDTO.getItemsMensajeRespuesta().get(0).getTipoError())) {
			/*return casuistica.crearRespuestaErrorPanico(
					respuestaCompraVentaInicioDTO.getItemsMensajeRespuesta().get(0).getMensaje());*/
			return casuistica.crearRespuestaErroneaConDatosBotonPanico(respuestaCompraVentaInicioDTO, listaCuentasViewOrigen, listaCuentasViewDestino);
		}
		if (casuistica.esRespuestaErrorFueraDeHorario(respuestaCompraVentaInicioDTO)) {
			if (tieneCuentaEnDistintaSucursal(listaCuentasViewOrigen, cuentaOrigen)) {
				return casuistica.crearRespuestaWarningFueraHorario(respuestaCompraVentaInicioDTO,
						listaCuentasViewOrigen);
			}
			return casuistica.crearRespuestaWarning(CompraVentaDolarView.class, null,
					respuestaCompraVentaInicioDTO.getItemsMensajeRespuesta());
		}
		if (casuistica.esRespuestaErrorCotizacion(respuestaCompraVentaInicioDTO)) {
			return casuistica.crearRespuestaError(ErrorCompraVentaEnum.ERROR_SERVICIO_CNSCOTCN_INACTIVO);
		}
		return casuistica.crearRespuestaErroneaInformacionNoDisponible();
	}

	/**
	 * Tiene cuenta en distinta sucursal.
	 *
	 * @param listaCuentasView
	 *            the lista cuentas view
	 * @param cuentaOrigen
	 *            the cuenta origen
	 * @return the boolean
	 */
	private Boolean tieneCuentaEnDistintaSucursal(List<CuentasAdhesionDebitoView> listaCuentasView,
			Cuenta cuentaOrigen) {
		for (CuentasAdhesionDebitoView cuenta : listaCuentasView) {
			if (cuenta.getShowSaldoPesos() && !this.coincideSucursal(cuenta, cuentaOrigen.getNroSucursal())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Coincide sucursal.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param sucursalCuentaOrigen
	 *            the sucursal cuenta origen
	 * @return the boolean
	 */
	private Boolean coincideSucursal(CuentasAdhesionDebitoView cuenta, String sucursalCuentaOrigen) {
		IdentificacionCuenta idCuenta = new IdentificacionCuenta(cuenta.getNumero());
		return idCuenta.getNroSucursal().equals(sucursalCuentaOrigen);
	}

	/**
	 * Obtener cuenta habilitada.
	 *
	 * @param cliente
	 *            the cliente
	 * @param listaCuentasView
	 *            the lista cuentas view
	 * @param esCompra
	 *            the es compra
	 * @return the cuenta
	 */
	private Cuenta obtenerCuentaHabilitada(Cliente cliente, List<CuentasAdhesionDebitoView> listaCuentasView,
			boolean esCompra) {
		return util.obtenerCuentaOrigen(listaCuentasView, cliente, esCompra);
	}

	/**
	 * Obtener respuesta cotizacion.
	 *
	 * @param cliente
	 *            the cliente
	 * @param cuentaOrigen
	 *            the cuenta origen
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @param fromCuentas
	 *            the from cuentas
	 * @param canalBancaPrivada
	 *            the canal banca privada
	 * @return the respuesta
	 */
	private Respuesta<CompraVentaDolarView> obtenerRespuestaCotizacion(Cliente cliente, Cuenta cuentaOrigen,
			String tipoOperacion , boolean fromCuentas, boolean canalBancaPrivada) {
		try {
			Respuesta<CompraVentaInicioDTO> respuestaCompraVentaInicioDTO = configuracionCompraVentaDolaresBO
					.obtenerCotizacion(cliente, cuentaOrigen, tipoOperacion, fromCuentas, canalBancaPrivada);
			if (respuestaCompraVentaInicioDTO != null) {
				if (casuistica.esRespuestaOK(respuestaCompraVentaInicioDTO.getEstadoRespuesta())) {
					return casuistica.crearRespuestaOk(CompraVentaDolarView.class,
							new CompraVentaDolarView(respuestaCompraVentaInicioDTO.getRespuesta(), null, null));
				}
				if (casuistica.esRespuestaErrorFueraDeHorario(respuestaCompraVentaInicioDTO)) {
					return crearRespuestaErrorFueraDeHorario(tipoOperacion, cuentaOrigen,
							respuestaCompraVentaInicioDTO);
				}
			}
			if (TipoError.ERROR_BOTON_PANICO.getDescripcion()
					.equals(respuestaCompraVentaInicioDTO.getEstadoRespuesta())
				|| TipoError.ERROR_BOTON_PANICO.getDescripcion()
				.equals(respuestaCompraVentaInicioDTO.getItemsMensajeRespuesta().get(0).getTipoError())) {
				//return casuistica.crearRespuestaErrorPanico(
					//	respuestaCompraVentaInicioDTO.getItemsMensajeRespuesta().get(0).getMensaje());
				List<CuentasAdhesionDebitoView> listaCuentasViewOrigen = null;
				List<CuentasAdhesionDebitoView> listaCuentasViewDestino = null;
				return casuistica.crearRespuestaErroneaConDatosBotonPanico(respuestaCompraVentaInicioDTO, listaCuentasViewOrigen, listaCuentasViewDestino);
			}
			return casuistica.crearRespuestaWarning(CompraVentaDolarView.class, new CompraVentaDolarView("-"), null);
		} catch (Exception e) {
			LOGGER.info(SERVICIO_DEVOLVIO_LOG_LABEL, e);
			return casuistica.crearRespuestaErroneaInformacionNoDisponible();
		}
	}

	/**
	 * Crear respuesta error fuera de horario.
	 *
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @param cuentaOrigen
	 *            the cuenta origen
	 * @param rtaDto
	 *            the respuesta compra venta inicio DTO
	 * @return the respuesta
	 */
	private Respuesta<CompraVentaDolarView> crearRespuestaErrorFueraDeHorario(String tipoOperacion, Cuenta cuentaOrigen,
			Respuesta<CompraVentaInicioDTO> rtaDto) {
		try {
			if (tieneCuentaEnDistintaSucursal(obtenerCuentasPesosODolares(tipoOperacion), cuentaOrigen)) {
				return casuistica.crearRespuestaWarningFueraHorario(rtaDto, null);
			}
			if (rtaDto.getItemsMensajeRespuesta() != null && !rtaDto.getItemsMensajeRespuesta().isEmpty()) {
				return casuistica.crearRespuestaError(CompraVentaDolarView.class, rtaDto.getItemsMensajeRespuesta());
			}
			return casuistica.crearRespuestaErrorCotizacion();
		} catch (Exception e) {
			LOGGER.info(SERVICIO_DEVOLVIO_LOG_LABEL, e);
			return casuistica.crearRespuestaErrorCotizacion();
		}
	}

	/**
	 * Obtener cuentas pesos O dolares.
	 *
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @return the list
	 */
	private List<CuentasAdhesionDebitoView> obtenerCuentasPesosODolares(String tipoOperacion) {
		if (tipoOperacion.equals(CompraVentaStringUtil.OPERACION_COMPRA)) {
			return sesionCompraVenta.getCuentasPesos();
		}
		return sesionCompraVenta.getCuentasDolares();
	}

	/**
	 * Obtiene las cuentas con saldos actualizados y ordenadas.
	 *
	 * @param cliente
	 *            the cliente
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 * @throws ServiceException
	 *             the service exception
	 */
	private Respuesta<CuentasView> obtenerCuentasOrdenadas(Cliente cliente, String numeroCuenta)
			throws BusinessException, ServiceException {
		return cuentaManager.convertirCuentasView(configuracionCompraVentaDolaresBO.getCuentasSaldoOrdenadas(cliente,
				obtenerIdentificacionCuenta(numeroCuenta),
				obtenerIdentificacionCuenta(sesionCompraVenta.getNumeroCuentaDestino())));
	}

	/**
	 * Obtiene las cuentas de banca privada.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the listaCuentasBancaPrivadaView
	 * @throws BusinessException
	 *             the business exception
	 * @throws ServiceException
	 *             the service exception
	 * @throws SQLException
	 *             the SQL exception
	 * @throws ImporteConvertException
	 *             the importe convert exception
	 */
	private List<CuentasAdhesionDebitoView> obtenerCuentasBancaPrivada(Cliente cliente) throws BusinessException, ServiceException, SQLException, ImporteConvertException {
		List<CuentasAdhesionDebitoView> listaCuentasBancaPrivadaView = new ArrayList<CuentasAdhesionDebitoView>();	
			 Respuesta<CuentasView> respuestaCuentasView = null;
				respuestaCuentasView = obtenerCuentasOrdenadasBP(cliente);
			 listaCuentasBancaPrivadaView = respuestaCuentasView.getRespuesta().getCuentas();
				util.formatearSaldosYTipoCuentaDeCuentasList(listaCuentasBancaPrivadaView);
			return listaCuentasBancaPrivadaView;
	}

	/**
	 * Obtener cuentas ordenadas BP.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 * @throws ServiceException
	 *             the service exception
	 * @throws SQLException
	 *             the SQL exception
	 * @throws ImporteConvertException
	 *             the importe convert exception
	 */
	private Respuesta<CuentasView> obtenerCuentasOrdenadasBP(Cliente cliente) 
		throws BusinessException, ServiceException, SQLException, ImporteConvertException {
			return	CuentasBancaPrivadaUtil.convertirCuentasView(configuracionCompraVentaDolaresBO.getCuentasSaldoOrdenadasBP(cliente)); 
			 
		}

	/**
	 * Consulta si el nup está habilitado para realizar operaciones de compra y venta de USD
	 */
	@Override
	public Respuesta<DatosRespuestaHabilitaCompraVentaUSDEntity> cnsHabilitadoCompraVtaUSD(String nup) {
		Respuesta<DatosRespuestaHabilitaCompraVentaUSDEntity> respuesta =  tenenciasBO.cnsHabilitaCompraVentaUSD(nup);
		
		if(EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta()) && respuesta.getRespuesta().getDatos().getCodigo() == 0) {
			estadisticaManager.add(EstadisticasConstants.CNS_HABILITA_COMPRA_VTA_USD, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else if(EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta()) && respuesta.getRespuesta().getDatos().getCodigo() != 0) {
			estadisticaManager.add(EstadisticasConstants.CNS_HABILITA_COMPRA_VTA_USD, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR_CNS_HABILITADO_A_COMPRA_VTA_USD);
		} else {
			estadisticaManager.add(EstadisticasConstants.CNS_HABILITA_COMPRA_VTA_USD, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuesta;
	}

}