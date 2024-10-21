/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.manager.impl;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.TipoIdentificacion;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.sucursales.bo.ConsultarSucursalesBO;
import ar.com.santanderrio.obp.servicios.comun.sucursales.entities.Sucursal;
import ar.com.santanderrio.obp.servicios.comun.sucursales.service.ConsultarSucursalesService;
import ar.com.santanderrio.obp.servicios.cuentas.bo.AliasCuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AliasFavoritoProducto;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.SaldosConsolidadosCuentaDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.AliasCuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.DetalleCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.SessionDetalleCuentas;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.SessionMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.web.util.CuentasUtils;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.BoxCuentaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CabeceraCuentasView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaCuentaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CopiarMensajeView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentaSelectorDetalleView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentaSelectorView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentaViewBuilder;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasIntermedioView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.DetalleCBUView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.GrupoCajitaCuentasView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.SelectorCuentasView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.StackCuentaSelectorView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.StackSelectorCuentasView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

/**
 * The Class CuentaManagerImpl.
 */
@Component
public class CuentaManagerImpl implements CuentaManager {

	/** The Constant PAD_CERO. */
	private static final String PAD_CERO = "0";

	/** The Constant PAD_SIZE_SUCURSAL. */
	private static final int PAD_SIZE_SUCURSAL = 3;

	/** The Constant TIPO_IDENTIFICACION_DETALLE_CUENTA. */
	private static final String TIPO_IDENTIFICACION_DETALLE_CUENTA = "CUIT/CUIL";

	/** The Constant SALDOS_TOTALES. */
	private static final String SALDOS_TOTALES = "Saldos totales";

	/** The Constant GUION. */
	private static final String GUION = "-";

	/** producto altair de cuenta cepo *. */
	private static final String PRODUCTO_ALTAIR_CEPO = "03";

	/** sub producto altair de cuenta cepo *. */
	private static final String SUBPRODUCTO_ALTAIR_CEPO = "0001";

	/** The Constant TAG_FIN. */
	private static final String TAG_FIN = "]";

	/** The Constant CUENTAS_TAG. */
	private static final String CUENTAS_TAG = "cuentas[";

	/** The Constant PATH_TO_IMPRIMIR. */
	private static final String PATH_TO_IMPRIMIR = "cuentas/imprimir";

	/** The Constant PARAMETRO_CUENTA. */
	private static final String PARAMETRO_CUENTA = "?numeroCuenta=";

	/** The Constant PARAMETRO_SUCURSAL. */
	private static final String PARAMETRO_SUCURSAL = "&sucursal=";

	/** The Constant SERVICIO_DEVOLVIO_LOG_LABEL. */
	private static final String SERVICIO_DEVOLVIO_LOG_LABEL = "El servicio devolvio: {}";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CuentaManagerImpl.class);

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The cuenta BO. */
	@Autowired
	private CuentaBO cuentaBO;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The session detalle cuentas. */
	@Autowired
	private SessionDetalleCuentas sessionDetalleCuentas;

	/** The consultar sucursales service. */
	@Autowired
	private ConsultarSucursalesService consultarSucursalesService;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The sesion mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The Alias Cuenta Manager. */
	@Autowired
	private AliasCuentaManager aliasCuentaManager;

	/** The consultar sucursales service. */
	@Autowired
	private ConsultarSucursalesBO consultarSucursalesBO;

	/** The legal BO. */
	@Autowired
	private LegalBO legalBO;

	/** The session movimientos. */
	@Autowired
	private SessionMovimientos sessionMovimientos;

	/** The alias cuenta BO. */
	@Autowired
	private AliasCuentaBO aliasCuentaBO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager#
	 * getCuentas(java.lang.String, java.lang.String)
	 */
	@Override
	public Respuesta<SelectorCuentasView> getCuentas(String userAgent, String numeroCuenta) {
		Respuesta<SelectorCuentasView> respuestaView = new Respuesta<SelectorCuentasView>();
		SelectorCuentasView selectorCuentasView = new SelectorCuentasView();
		respuestaView.setEstadoRespuesta(EstadoRespuesta.OK);
		try {
			Cliente cliente = sesionCliente.getCliente();
			Respuesta<List<ResumenDetalleCuenta>> respuesta = cuentaBO.obtenerInfoCuentas(cliente);
			respuestaView = Respuesta.copy(SelectorCuentasView.class, respuesta);
			if (!EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())) {
				CuentasUtils.ordenarCuentas(respuesta.getRespuesta());

				List<ResumenDetalleCuenta> listCuentas = respuesta.getRespuesta();
				if (!CollectionUtils.isEmpty(listCuentas)) {
					List<Cuenta> cuentas = cliente.getCuentas();
					actualizarSaldos(listCuentas, cuentas);
				}

				CabeceraCuentasView cabecera = obtenerCabeceraCuentas(respuesta.getRespuesta());
				List<CuentaSelectorDetalleView> cuentaSelector = obtenerSelectorCuentas(respuesta.getRespuesta(),
						cabecera);
				selectorCuentasView.setPermiteFavorito(permiteFavorita(respuesta.getRespuesta()));
				obtenerMensajeFavorita(selectorCuentasView);
				selectorCuentasView.setCuentas(cuentaSelector);
				Integer indiceCuenta = getCuentaSeleccionada(respuesta.getRespuesta(), numeroCuenta);
				selectorCuentasView.setSelected(indiceCuenta);
				// if (indiceCuenta > 0) {
				// selectorCuentasView.getCuentas().get(indiceCuenta).setAliasCbu(consultarCBU(userAgent,
				// selectorCuentasView.getCuentas().get(indiceCuenta).getCbu(),
				// respuestaView));
				// }
				selectorCuentasView.setHasCuentasActivas(Boolean.TRUE);
				obtenerLegales(selectorCuentasView);

				if (cuentaBO.tieneUnaSolaCuenta(cliente)) {
					selectorCuentasView.setUnicaCuenta(Boolean.TRUE);
					selectorCuentasView.setSelected(1);
				}

				respuestaView.setRespuesta(selectorCuentasView);
				ItemMensajeRespuesta item = verificarModoNocturno(respuesta.getRespuesta());

				if (item != null) {
					respuestaView.getItemsMensajeRespuesta().add(item);
					respuestaView.setEstadoRespuesta(EstadoRespuesta.WARNING);
				}
			}
			if (!EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())
					&& !tieneErrorItemCuenta(respuesta.getItemsMensajeRespuesta())) {
				estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_OBTENER_CTAS,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			} else {
				estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_OBTENER_CTAS,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}

		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_OBTENER_CTAS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			respuestaView = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
		} catch (RuntimeException ex) {
			LOGGER.error(ex.getMessage(), ex);
			estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_OBTENER_CTAS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			respuestaView = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
		}
		return respuestaView;
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager#
	 * getCuentas(java.lang.String, java.lang.String)
	 */
	@Override
	public Respuesta<StackSelectorCuentasView> getListaCuentasCliente(){
		Respuesta<StackSelectorCuentasView> respuestaView = new Respuesta<StackSelectorCuentasView>();
		StackSelectorCuentasView stackSelectorCuentasView = new StackSelectorCuentasView();		
		List<StackCuentaSelectorView> listaStackSelectorCuentas =new ArrayList<StackCuentaSelectorView>();				
		respuestaView.setEstadoRespuesta(EstadoRespuesta.OK);				
		stackSelectorCuentasView.setCuentas(listaStackSelectorCuentas);						
		respuestaView.setRespuesta(stackSelectorCuentasView);		
		for(Cuenta cuenta :sesionCliente.getCliente().getCuentas()) {																			
            if (cuenta.isTipoCuentaMonetaria()) {
            	StackCuentaSelectorView stackCuentaSelector=new StackCuentaSelectorView();				
				stackCuentaSelector.setDescripcionTipoCuenta(TipoCuenta.fromCodigo(cuenta.getTipoCuenta()).getDescripcion());				
				stackCuentaSelector.setNumero(ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuenta));				
				listaStackSelectorCuentas.add(stackCuentaSelector);														
            }				
		}				
		return respuestaView;
	}


	/**
	 * Obtener legales.
	 *
	 * @param selectorCuentasView
	 *            the selector cuentas view
	 */
	private void obtenerLegales(SelectorCuentasView selectorCuentasView) {

		try {
			selectorCuentasView
					.setLegales(legalBO.obtenerLegal(CodigoMensajeConstantes.LEGALES_FOOTER_CUENTA_MOVIMIENTOS));
		} catch (DAOException e) {
			LOGGER.error("Falla al obtenr legales cuentas.");
			selectorCuentasView.setLegales(StringUtils.EMPTY);
		}
	}

	/**
	 * Consultar CBU.
	 *
	 * @param userAgent
	 *            the user agent
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @param respuesta
	 *            the respuesta
	 * @return the string
	 */
	private String consultarCBU(String userAgent, String numeroCuenta, Respuesta<SelectorCuentasView> respuesta) {

		Respuesta<DetalleCBUView> detalleCbu = aliasCuentaManager.obtenerAliasCBU(numeroCuenta, userAgent);
		if (EstadoRespuesta.WARNING.equals(detalleCbu.getEstadoRespuesta())) {
			respuesta.getItemsMensajeRespuesta().addAll(detalleCbu.getItemsMensajeRespuesta());
			respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
			return null;
		}
		return detalleCbu.getRespuesta().getAliasCbu();
	}

	/**
	 * Permite favorita.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @return the boolean
	 */
	private Boolean permiteFavorita(List<ResumenDetalleCuenta> respuesta) {
		int i = 0;
		for (ResumenDetalleCuenta resumenDetalleCuenta : respuesta) {
			if (!resumenDetalleCuenta.isCuentaCerrada()) {
				i++;
				if (i > 1) {
					return Boolean.TRUE;
				}
			}
		}
		return Boolean.FALSE;
	}

	/**
	 * Tiene error item cuenta.
	 *
	 * @param itemsMensajeRespuesta
	 *            the items mensaje respuesta
	 * @return true, if successful
	 */
	private boolean tieneErrorItemCuenta(List<ItemMensajeRespuesta> itemsMensajeRespuesta) {
		if (!CollectionUtils.isEmpty(itemsMensajeRespuesta)) {
			for (ItemMensajeRespuesta item : itemsMensajeRespuesta) {
				if (TipoError.ERROR_ITEM_CUENTA.getDescripcion().equals(item.getTipoError())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Obtener mensaje favorita.
	 *
	 * @param selectorCuentasView
	 *            the selector cuentas view
	 */
	private void obtenerMensajeFavorita(SelectorCuentasView selectorCuentasView) {
		Mensaje mensajeFavorita = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MARCAR_FAVORITA);
		Mensaje mensajeNoFavorita = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MARCAR_NO_FAVORITA);
		selectorCuentasView
				.setMensajeFavorito((mensajeFavorita.getMensaje().isEmpty()) ? null : mensajeFavorita.getMensaje());
		selectorCuentasView.setMensajeNoFavorito(
				(mensajeNoFavorita.getMensaje().isEmpty()) ? null : mensajeNoFavorita.getMensaje());
	}

	/**
	 * Gets the cuenta seleccionada.
	 *
	 * @param listaCuentas
	 *            the lista cuentas
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @return the cuenta seleccionada
	 */
	private Integer getCuentaSeleccionada(List<ResumenDetalleCuenta> listaCuentas, String numeroCuenta) {
		Integer indice = 0;
		for (ResumenDetalleCuenta cuenta : listaCuentas) {
			if (cuenta.getNumeroCuentaYSucursal().equals(numeroCuenta)) {
				return indice + 1;
			}
			indice++;
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager#
	 * guardarEstadisticaMisProductos(java.lang.String)
	 */
	@Override
	public void guardarEstadisticaMisProductos(String codigoTransaccion) {
		estadisticaManager.add(codigoTransaccion, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager#
	 * obtenerReporteCuenta(ar.com.santanderrio.obp.servicios.cuentas.web.view.
	 * ConsultaCuentaView)
	 */
	@Override
	public Respuesta<ReporteView> obtenerReporteCuenta(ConsultaCuentaView cuenta) {

		Respuesta<ReporteView> respuesta = new Respuesta<ReporteView>();
		ReporteView reporteView = new ReporteView();
		IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta(cuenta.getNumeroCuenta());

		Respuesta<Reporte> reporteRespuesta = null;
		try {
			Cliente cliente = sesionCliente.getCliente();
			reporteRespuesta = cuentaBO.obtenerReporteCBUCuenta(identificacionCuenta, cliente, cuenta.getAlias());
			if (EstadoRespuesta.OK.equals(reporteRespuesta.getEstadoRespuesta())) {
				LOGGER.info(SERVICIO_DEVOLVIO_LOG_LABEL, reporteRespuesta.getEstadoRespuesta());
				estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_REPORTE,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				reporteView = ReporteView.fromReporte(reporteRespuesta.getRespuesta());
				respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
				respuesta.setRespuesta(reporteView);
			} else {
				LOGGER.debug(SERVICIO_DEVOLVIO_LOG_LABEL, reporteRespuesta.getEstadoRespuesta());
				estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_REPORTE,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY,
						TipoError.ERROR_DESCARGA_DETALLE_CBU, StringUtils.EMPTY);
			}
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_REPORTE,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}

		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager#
	 * actualizarAlias(java.lang.String, java.lang.String)
	 */
	@Override
	public Respuesta<Void> actualizarAlias(String cuenta, String alias) {
		Respuesta<Void> reporteAlias = null;
		try {
			Cliente cliente = sesionCliente.getCliente();

			IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta(cuenta);

			reporteAlias = cuentaBO.actualizarAlias(identificacionCuenta, alias, cliente);
			if (EstadoRespuesta.OK.equals(reporteAlias.getEstadoRespuesta())) {
				LOGGER.info(SERVICIO_DEVOLVIO_LOG_LABEL, EstadoRespuesta.OK);
				estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_ALIAS,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			} else {
				LOGGER.debug(SERVICIO_DEVOLVIO_LOG_LABEL, EstadoRespuesta.ERROR);
				estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_ALIAS,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_ALIAS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_ACTUALIZAR_ALIAS,
					CodigoMensajeConstantes.CODIGO_ERROR_ALIAS);
		}
		return reporteAlias;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager#
	 * marcarFavorita(java.lang.String)
	 */
	@Override
	public Respuesta<Void> marcarFavorita(String cuenta) {

		Respuesta<Void> reporteAlias = null;
		try {

			IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta(cuenta);

			Cliente cliente = sesionCliente.getCliente();
			reporteAlias = cuentaBO.marcarFavorita(identificacionCuenta, cliente);
			if (EstadoRespuesta.OK.equals(reporteAlias.getEstadoRespuesta())) {
				LOGGER.info(SERVICIO_DEVOLVIO_LOG_LABEL, EstadoRespuesta.OK);
				estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_FAVORITA,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			} else {
				LOGGER.debug(SERVICIO_DEVOLVIO_LOG_LABEL, EstadoRespuesta.ERROR);
				estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_FAVORITA,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_FAVORITA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_MARCAR_FAVORITA,
					CodigoMensajeConstantes.CODIGO_ERROR_CARGAR_ALIAS_FAVORITO);
		}
		return reporteAlias;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager#
	 * obtenerCuentaById(java.lang.String)
	 */
	@Override
	public AbstractCuenta obtenerCuentaById(String nroCuenta) {
		IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta(nroCuenta);
		Cliente cliente = sesionCliente.getCliente();
		return cuentaBO.getCuentaById(identificacionCuenta, cliente);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager#
	 * obtenerCuentaPorId(java.lang.String)
	 */
	/*
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager#
	 * obtenerCuentaPorId(java.lang.String)
	 * 
	 * @author emilio.watemberg Se refactorizo el metodo obtenerCuentaById de
	 * manera que devuelva una respuesta y no una excepcion.
	 */
	@Override
	public Respuesta<AbstractCuenta> obtenerCuentaPorId(String nroCuenta) {
		Respuesta<AbstractCuenta> respuesta = new Respuesta<AbstractCuenta>();
		IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta(nroCuenta);
		Cliente cliente = sesionCliente.getCliente();
		AbstractCuenta cuenta = cuentaBO.getCuentaById(identificacionCuenta, cliente);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuesta(cuenta);
		return respuesta;
	}

	/**
	 * Metodo verificarCuenta. Implementa la verificaci�n de un numero de
	 * cuenta. Ref:US 14435 Nueva Transferencia Rio-Rio acceso a Transferencia.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @param versionVerificarCuenta
	 *            the version verificar cuenta
	 * @return the respuesta
	 */
	@Override
	public Respuesta<Cliente> verificarCuenta(TransferenciaView transferenciaView, int versionVerificarCuenta) {
		Respuesta<Cliente> respuesta = new Respuesta<Cliente>();
		try {
			String cuenta = transferenciaView.getNroCuentaDestino();
			String tipoCuenta = transferenciaView.getTipoCuentaDestino();
			String sucursalCuenta;
			String nroCuenta;
			String[] numeroCuenta = cuenta.split(GUION);

			StringBuilder cuentaProducto = new StringBuilder(numeroCuenta[1]);
			cuentaProducto.deleteCharAt(numeroCuenta[1].length() - 2);
			sucursalCuenta = numeroCuenta[0];
			nroCuenta = cuentaProducto.toString();
			TipoCuenta tipoCuentaEnum = TipoCuenta.fromAbreviatura(tipoCuenta);
			TipoCuenta tipoCuentaEnumDesc = TipoCuenta.fromDescripcion(tipoCuenta);
			if (tipoCuentaEnumDesc != null) {
				tipoCuentaEnum = tipoCuentaEnumDesc;
			}
			if (tipoCuentaEnum == null) {
				throw new BusinessException("Tipo cuenta invalido: " + tipoCuenta);
			}
			tipoCuenta = StringUtils.leftPad(tipoCuentaEnum.getCodigo().toString(), 2, PAD_CERO);

			Cliente cliente = sesionCliente.getCliente();

			if (versionVerificarCuenta == 1) {
				respuesta = cuentaBO.verificarCuenta(cliente, tipoCuenta, sucursalCuenta, nroCuenta);
			} else {
				respuesta = cuentaBO.verificarCuenta2(cliente, tipoCuenta, sucursalCuenta, nroCuenta);
			}
		} catch (BusinessException ex) {
			LOGGER.error(ex.getMessage(), ex);
			respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_CONSULTA_MOVIMIENTOS,
					CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
		}
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager#
	 * getCuenta(java.lang.String)
	 */
	@Override
	public Respuesta<CuentasView> getCuenta(String nroCuenta) {
		List<CuentasAdhesionDebitoView> cuentas = new ArrayList<CuentasAdhesionDebitoView>();
		Respuesta<CuentasView> respuestaView = new Respuesta<CuentasView>();
		CuentasView cuentaView = new CuentasView();
		try {
			Cliente cliente = sesionCliente.getCliente();

			Respuesta<ResumenDetalleCuenta> respuesta = cuentaBO.obtenerCuenta(cliente, nroCuenta);
			armarRespuestaObtenerCuenta(cuentas, respuestaView, cuentaView, respuesta);
		} catch (BusinessException ex) {
			LOGGER.error(ex.getMessage(), ex);
			respuestaView = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
			estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_CTA_PRINCIPAL,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}

		return respuestaView;
	}

	/**
	 * Armar respuesta obtener cuenta.
	 *
	 * @param cuentas
	 *            the cuentas
	 * @param respuestaView
	 *            the respuesta view
	 * @param cuentaView
	 *            the cuenta view
	 * @param respuesta
	 *            the respuesta
	 */
	private void armarRespuestaObtenerCuenta(List<CuentasAdhesionDebitoView> cuentas,
			Respuesta<CuentasView> respuestaView, CuentasView cuentaView, Respuesta<ResumenDetalleCuenta> respuesta) {
		ResumenDetalleCuenta resumenDetalleCuenta = respuesta.getRespuesta();

		if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {

			CuentasAdhesionDebitoView cuenta = obtenerCuenta(resumenDetalleCuenta);
			cuentas.add(cuenta);
			if (resumenDetalleCuenta != null) {
				cuenta.setIsCerrada(resumenDetalleCuenta.isCuentaCerrada());
				cuenta.setIsCuentaUnica(resumenDetalleCuenta.isCuentaUnica());
			}
			cuentaView.setCuentas(cuentas);
			cuentaView.setSelected(0);
			cuentaView.setHasCuentasActivas(true);
			respuestaView.setRespuesta(cuentaView);
			respuestaView.setEstadoRespuesta(respuesta.getEstadoRespuesta());
			respuestaView.setRespuestaVacia(respuesta.isRespuestaVacia());
			LOGGER.info(SERVICIO_DEVOLVIO_LOG_LABEL, EstadoRespuesta.OK);
			estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_CTA_PRINCIPAL,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			respuestaView.setRespuesta(cuentaView);
			respuestaView.setEstadoRespuesta(respuesta.getEstadoRespuesta());
			respuestaView.setItemMensajeRespuesta(respuesta.getItemsMensajeRespuesta());
			respuestaView.setRespuestaVacia(respuesta.isRespuestaVacia());
			estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_CTA_PRINCIPAL,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			LOGGER.debug(SERVICIO_DEVOLVIO_LOG_LABEL, EstadoRespuesta.ERROR);
		}
	}

	/**
	 * Obtener cuenta.
	 *
	 * @param resumenDetalleCuenta
	 *            the resumen detalle cuenta
	 * @return the cuenta
	 */
	private CuentasAdhesionDebitoView obtenerCuenta(ResumenDetalleCuenta resumenDetalleCuenta) {
		CuentasAdhesionDebitoView cuenta = getCuentasAdhesionDebitoView(resumenDetalleCuenta.getCliente());

		cuenta.setDescripcionTipoCuenta(obtenerDescripcionTipoCuenta(resumenDetalleCuenta.getTipoCuenta()));
		cuenta.setAbreviaturaTipoCuenta(obtenerAbreviaturaTipoCuenta(resumenDetalleCuenta.getTipoCuenta()));
		cuenta.setNumero(resumenDetalleCuenta.getNumeroCuentaYSucursal());
		cuenta.setCbu(resumenDetalleCuenta.getCbu());
		cuenta.setIsTraspasoAutomatico(resumenDetalleCuenta.isCuentaTraspasoAutomatico());
		cuenta.setIsCerrada(resumenDetalleCuenta.isCuentaCerrada());
		cuenta.setShowSolicitarTraspaso(resumenDetalleCuenta.getShowSolicitarTraspaso());
		cuenta.setShowRealizarTraspaso(resumenDetalleCuenta.getShowRealizarTraspaso());

		cuenta.setIsCuentaUnica(resumenDetalleCuenta.isCuentaUnica());
		cuenta.setUrlReporteCBU(obtenerURLReporteCBU(resumenDetalleCuenta));
		cuenta.setIsFavorito(resumenDetalleCuenta.isFavorita());
		cuenta.setAlias(resumenDetalleCuenta.getAlias());
		cuenta.setFechaDesdeMovimiento(resumenDetalleCuenta.getFechaDesdeMovimiento());
		cuenta.setFechaHastaMovimiento(resumenDetalleCuenta.getFechaHastaMovimiento());
		if (StringUtils.isEmpty(cuenta.getAlias())) {
			cuenta.setHasAlias(false);
		} else {
			cuenta.setHasAlias(true);
		}

		cuenta.setSaldoDescubierto(
				ISBANStringUtils.formatearSaldo(resumenDetalleCuenta.getSaldoDescubiertoDisponible()));
		cuenta.setSaldoCuentaCorriente(
				ISBANStringUtils.formatearSaldo(resumenDetalleCuenta.getSaldoCuentaCorrientePesos()));
		cuenta.setSaldoCajaAhorro(ISBANStringUtils.formatearSaldo(resumenDetalleCuenta.getSaldoCajaAhorroPesos()));
		cuenta.setSignoSaldoCajaAhorro(StringUtils.EMPTY);
		cuenta.setSignoSaldoCuentaCorriente(StringUtils.EMPTY);

		// igual a cero
		if (resumenDetalleCuenta.getDisponibleDescubierto().compareTo(BigDecimal.ZERO) == 0) {
			cuenta.setIsSaldoDescubiertoCero(true);
		}

		boolean cuentaUnica = resumenDetalleCuenta.isCuentaUnica();
		if (cuentaUnica) {
			boolean traspasoAutomatico = resumenDetalleCuenta.isCuentaTraspasoAutomatico();

			if (traspasoAutomatico) {
				setSaldoPesos(cuenta, resumenDetalleCuenta.getSaldoPesos());
				setSaldoDolares(cuenta, resumenDetalleCuenta.getSaldoDolares());
			} else {
				setSaldoPesos(cuenta, resumenDetalleCuenta.getSaldoUnificado());
				setSaldoDolares(cuenta, resumenDetalleCuenta.getSaldoCajaAhorroDolares());

				// tooltip
				boolean convenioPas = resumenDetalleCuenta.isConvenioPAS();
				if (convenioPas) {
					setSaldoCajaAhorro(cuenta, resumenDetalleCuenta.getSaldoCuentaSueldoPesos());
					cuenta.setIsCuentaSueldo(Boolean.TRUE);
				} else {
					setSaldoCajaAhorro(cuenta, resumenDetalleCuenta.getSaldoCajaAhorroPesos());
					cuenta.setIsCuentaSueldo(Boolean.FALSE);
				}
				setSaldoCuentaCorriente(cuenta, resumenDetalleCuenta.getSaldoCuentaCorrientePesos());
			}
		} else {
			setSaldoDolares(cuenta, resumenDetalleCuenta.getSaldoDolares());
			setSaldoPesos(cuenta, resumenDetalleCuenta.getSaldoPesos());
		}
		cuenta.setShowSaldoPesos(isShowPesos(resumenDetalleCuenta.getTipoCuenta()));
		cuenta.setShowSaldoDolares(isShowDolares(resumenDetalleCuenta.getTipoCuenta()));

		// disponible utilizado
		cuenta.setIsDescubiertoUtilizado(resumenDetalleCuenta.isDescubiertoUtilizado());
		cuenta.setShowDescubierto(resumenDetalleCuenta.mostrarDescubierto());

		return cuenta;
	}

	/**
	 * Completar cliente.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the cuenta
	 */
	private CuentasAdhesionDebitoView getCuentasAdhesionDebitoView(Cliente cliente) {
		CuentasAdhesionDebitoView cuenta = new CuentasAdhesionDebitoView();

		cuenta.setNombreCliente(WordUtils.capitalizeFully(cliente.getNombre()));
		cuenta.setApellidoCliente(WordUtils.capitalizeFully(cliente.getApellido1()));
		TipoIdentificacion ti = TipoIdentificacion.fromKey(cliente.getTipoDocumento());
		cuenta.setTipoIdentificacion(ti.getDescripcion());
		cuenta.setNumeroIdentificacion(formatearDNIConPuntos(cliente.getDni()));

		return cuenta;
	}

	/**
	 * Formatear DNI con puntos.
	 *
	 * @param dni
	 *            the dni
	 * @return the string
	 */
	private String formatearDNIConPuntos(String dni) {

		NumberFormat numberFormatter = NumberFormat.getNumberInstance(Locale.GERMAN);
		String dniFinal = null;

		if (StringUtils.isNotBlank(dni)) {
			Long dniLong = Long.parseLong(dni);
			dniFinal = numberFormatter.format(dniLong);
		}

		return dniFinal;
	}

	/**
	 * Checks if is show dolares.
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @return true, if is show dolares
	 */
	private boolean isShowDolares(String tipoCuenta) {
		boolean result = false;
		if (TipoCuenta.CUENTA_UNICA.equals(TipoCuenta.fromCodigo(tipoCuenta))) {
			result = true;
		}

		if (TipoCuenta.fromCodigo(tipoCuenta).equals(TipoCuenta.CAJA_AHORRO_DOLARES)) {
			result = true;
		}
		if (TipoCuenta.fromCodigo(tipoCuenta).equals(TipoCuenta.CUENTA_CORRIENTE_DOLARES)) {
			result = true;
		}
		return result;
	}

	/**
	 * Checks if is show pesos.
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @return true, if is show pesos
	 */
	private boolean isShowPesos(String tipoCuenta) {
		boolean result = false;
		if (TipoCuenta.fromCodigo(tipoCuenta).equals(TipoCuenta.CUENTA_UNICA)) {
			result = true;
		}
		if (TipoCuenta.fromCodigo(tipoCuenta).equals(TipoCuenta.CAJA_AHORRO_PESOS)) {
			result = true;
		}
		if (TipoCuenta.fromCodigo(tipoCuenta).equals(TipoCuenta.CUENTA_CORRIENTE_PESOS)) {
			result = true;
		}
		return result;
	}

	/**
	 * Sets the saldo cuenta corriente.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param saldoCuentaCorrientePesos
	 *            the saldo cuenta corriente pesos
	 */
	private void setSaldoCuentaCorriente(CuentasAdhesionDebitoView cuenta, BigDecimal saldoCuentaCorrientePesos) {
		cuenta.setSaldoCuentaCorriente(ISBANStringUtils.formatearSaldo(saldoCuentaCorrientePesos));
		cuenta.setSignoSaldoCuentaCorriente(StringUtils.EMPTY);
		if (saldoCuentaCorrientePesos.compareTo(BigDecimal.ZERO) == 0) {
			cuenta.setIsSaldoCuentaCorrienteCero(true);
		} else if (saldoCuentaCorrientePesos.compareTo(BigDecimal.ZERO) < 0) {
			cuenta.setSignoSaldoCuentaCorriente(GUION);
			cuenta.setIsSaldoCuentaCorrienteNegativo(true);
		}
	}

	/**
	 * Sets the saldo caja ahorro.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param saldoCajaAhorroPesos
	 *            the saldo caja ahorro pesos
	 */
	private void setSaldoCajaAhorro(CuentasAdhesionDebitoView cuenta, BigDecimal saldoCajaAhorroPesos) {

		cuenta.setSaldoCajaAhorro(ISBANStringUtils.formatearSaldo(saldoCajaAhorroPesos));
		cuenta.setSignoSaldoCajaAhorro(StringUtils.EMPTY);
		if (saldoCajaAhorroPesos.compareTo(BigDecimal.ZERO) == 0) {
			cuenta.setIsSaldoCajaAhorroCero(true);
		} else if (saldoCajaAhorroPesos.compareTo(BigDecimal.ZERO) < 0) {
			cuenta.setSignoSaldoCajaAhorro(GUION);
			cuenta.setIsSaldoCajaAhorroNegativo(true);
		}
	}

	/**
	 * Sets the saldo dolares.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param saldoDolares
	 *            the saldo dolares
	 */
	private void setSaldoDolares(CuentasAdhesionDebitoView cuenta, BigDecimal saldoDolares) {
		cuenta.setSaldoDolares(ISBANStringUtils.formatearSaldo(saldoDolares));
		cuenta.setSignoSaldoDolares(StringUtils.EMPTY);
		if (saldoDolares.compareTo(BigDecimal.ZERO) == 0) {
			cuenta.setIsSaldoDolaresCero(true);
		} else if (saldoDolares.compareTo(BigDecimal.ZERO) < 0) {
			cuenta.setSignoSaldoDolares(GUION);
			cuenta.setIsSaldoDolaresNegativo(true);
		}
	}

	/**
	 * Sets the saldo pesos.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param saldoPesos
	 *            the saldo pesos
	 */
	private void setSaldoPesos(CuentasAdhesionDebitoView cuenta, BigDecimal saldoPesos) {
		cuenta.setSaldoPesos(ISBANStringUtils.formatearSaldo(saldoPesos));
		cuenta.setSignoSaldoPesos(StringUtils.EMPTY);
		// igual a cero
		if (saldoPesos.compareTo(BigDecimal.ZERO) == 0) {
			cuenta.setIsSaldoPesosCero(true);
		} else if (saldoPesos.compareTo(BigDecimal.ZERO) < 0) {
			cuenta.setSignoSaldoPesos(GUION);
			cuenta.setIsSaldoPesosNegativo(true);
		}
	}

	/**
	 * Obtener url reporte cbu.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the string
	 */
	private String obtenerURLReporteCBU(ResumenDetalleCuenta cuenta) {
		String nroCuenta = cuenta.getNroCuentaProducto();
		String sucursal = cuenta.getNroSucursal();

		StringBuilder sb = new StringBuilder();
		sb.append(PATH_TO_IMPRIMIR);
		sb.append(PARAMETRO_CUENTA);
		sb.append(nroCuenta);
		sb.append(PARAMETRO_SUCURSAL);
		sb.append(sucursal);
		return sb.toString();
	}

	/**
	 * Obtener abreviatura tipo cuenta.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the string
	 */
	private String obtenerAbreviaturaTipoCuenta(String codigo) {
		TipoCuenta tipoCuenta = TipoCuenta.fromCodigo(codigo);
		String descripcion = codigo;
		if (tipoCuenta != null) {
			descripcion = tipoCuenta.getAbreviatura();
		}
		return descripcion;
	}

	/**
	 * Obtener descripcion tipo cuenta.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the string
	 */
	private String obtenerDescripcionTipoCuenta(String codigo) {
		TipoCuenta tipoCuenta = TipoCuenta.fromCodigo(codigo);
		String descripcion = codigo;
		if (tipoCuenta != null) {
			descripcion = tipoCuenta.getDescripcionConMoneda();
		}
		return descripcion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager#
	 * hasCuentasMonetariasActivasEnPesos()
	 */
	/*
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager#
	 * hasCuentasMonetariasActivasEnPesos()
	 */
	@Override
	public Boolean hasCuentasMonetariasActivasEnPesos() {
		Cliente cliente = sesionCliente.getCliente();
		return cuentaBO.hasCuentasMonetariasActivasEnPesos(cliente);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager#
	 * getCuentasSaldo()
	 */
	/*
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager#
	 * getCuentasSaldo()
	 */
	@Override
	public Respuesta<CuentasView> getCuentasSaldo() {
		Respuesta<CuentasView> respuestaView = new Respuesta<CuentasView>();
		Respuesta<List<ResumenDetalleCuenta>> respuesta;
		try {
			respuesta = cuentaBO.getCuentasSaldo(sesionCliente.getCliente());
			respuestaView = convertirCuentasView(respuesta);
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			respuestaView = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_PAGO_TARJETAS_GENERAL,
					CodigoMensajeConstantes.ERROR_PAGO_TARJETAS_GENERAL);
		}
		return respuestaView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager#
	 * convertirCuentasView(ar.com.santanderrio.obp.base.respuesta.entities.
	 * Respuesta)
	 */
	/*
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager#
	 * convertirCuentasView(ar.com.santanderrio.obp.base.respuesta.entities.
	 * Respuesta)
	 */
	@Override
	public Respuesta<CuentasView> convertirCuentasView(Respuesta<List<ResumenDetalleCuenta>> respuesta)
			throws BusinessException {
		List<CuentasAdhesionDebitoView> cuentas = new ArrayList<CuentasAdhesionDebitoView>();
		List<CuentasAdhesionDebitoView> temp = new ArrayList<CuentasAdhesionDebitoView>();
		CuentasView cuentaView = new CuentasView();
		CuentasAdhesionDebitoView cuenta;
		List<DetalleCuenta> detalleCuentaList = new ArrayList<DetalleCuenta>();
		Respuesta<CuentasView> respuestaView = new Respuesta<CuentasView>();
		List<ResumenDetalleCuenta> resumenDetalleCuentas = respuesta.getRespuesta();
		if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())
				|| EstadoRespuesta.WARNING.equals(respuesta.getEstadoRespuesta())) {
			Iterator<ResumenDetalleCuenta> it = resumenDetalleCuentas.iterator();
			while (it.hasNext()) {
				ResumenDetalleCuenta resumenDetalleCuenta = it.next();

				cuenta = obtenerCuenta(resumenDetalleCuenta);
				cuentas.add(cuenta);

				IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
				identificacionCuenta.setNroCuentaProducto(resumenDetalleCuenta.getNroCuentaProducto());
				identificacionCuenta.setNroSucursal(resumenDetalleCuenta.getNroSucursal());
				DetalleCuenta detalleCuenta = new DetalleCuenta();
				detalleCuenta.setIdentificacionCuenta(identificacionCuenta);
				detalleCuenta.setIsTraspasoAutomatico(cuenta.getIsTraspasoAutomatico());
				detalleCuentaList.add(detalleCuenta);

			}
			sessionDetalleCuentas.setDetalleCuentaList(detalleCuentaList);
			cuentaView.setCuentas(cuentas);
			respuestaView.setRespuesta(cuentaView);
			respuestaView.setItemMensajeRespuesta(respuesta.getItemsMensajeRespuesta());
			respuestaView.setEstadoRespuesta(respuesta.getEstadoRespuesta());
			respuestaView.setRespuestaVacia(false);

			Respuesta<Integer> respuestaSeleccionada = cuentaBO.obtenerCuentaSeleccionada(respuesta);
			if (respuestaSeleccionada.getRespuesta() == null) {
				return respuestaView;
			}
			cuentaView.setSelected(respuestaSeleccionada.getRespuesta());

			// Agrego los items extras
			List<ItemMensajeRespuesta> itemsSeleccionada = respuestaSeleccionada.getItemsMensajeRespuesta();
			if (itemsSeleccionada != null) {
				for (ItemMensajeRespuesta itemMensajeRespuestaSeleccionada : itemsSeleccionada) {
					respuestaView.add(itemMensajeRespuestaSeleccionada);
				}
			}

			boolean hasErrorSeleccionada = false;
			if (respuestaView.getItemsMensajeRespuesta() != null
					&& !respuestaView.getItemsMensajeRespuesta().isEmpty()) {
				List<ItemMensajeRespuesta> items = respuestaView.getItemsMensajeRespuesta();
				for (ItemMensajeRespuesta itemMensajeRespuesta : items) {
					if (TipoError.ERROR_ITEM_CUENTA_SELECCIONADA.equals(itemMensajeRespuesta.getTipoError())) {
						hasErrorSeleccionada = true;
						break;
					}
				}
			}

			// agrego errores de alias si los hubiese
			List<ItemMensajeRespuesta> itemsRespuesta = sesionCliente.getItemsRespuesta();
			if (itemsRespuesta != null && !itemsRespuesta.isEmpty()) {
				for (ItemMensajeRespuesta itemMensajeRespuesta : itemsRespuesta) {
					if (itemMensajeRespuesta != null && TipoError.ERROR_OBTENER_FAVORITO.getDescripcion()
							.equals(itemMensajeRespuesta.getTipoError())) {
						respuestaView.add(itemMensajeRespuesta);
					}
				}
			}

			if (respuestaView.getItemsMensajeRespuesta() != null
					&& !respuestaView.getItemsMensajeRespuesta().isEmpty()) {
				respuestaView.setEstadoRespuesta(EstadoRespuesta.WARNING);
			}

			agregarSaldoErrorneoEnCuentasActivas(respuestaView, temp);
			LOGGER.info(SERVICIO_DEVOLVIO_LOG_LABEL, respuestaView.getEstadoRespuesta());
			if (hasErrorSeleccionada) {
				estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_OBTENER_CTAS,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			} else {
				estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_OBTENER_CTAS,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			}
		} else {
			respuestaView.setEstadoRespuesta(respuesta.getEstadoRespuesta());
			respuestaView.setItemMensajeRespuesta(respuesta.getItemsMensajeRespuesta());
			respuestaView.setRespuestaVacia(true);
			LOGGER.debug(SERVICIO_DEVOLVIO_LOG_LABEL, respuestaView.getEstadoRespuesta());
			estadisticaManager.add(EstadisticasConstants.ESTADISTICA_CONFIGURACION,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager#
	 * getCuentasActivas()
	 */
	/*
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager#
	 * getCuentasActivas()
	 */
	@Override
	public Respuesta<CuentasView> getCuentasActivas() {
		Respuesta<CuentasView> respuestaCuentasView = new Respuesta<CuentasView>();
		Respuesta<CuentasView> respuestaCuentas = this.getCuentasSaldo();

		if (EstadoRespuesta.ERROR.equals(respuestaCuentas.getEstadoRespuesta())) {
			respuestaCuentasView.setEstadoRespuesta(EstadoRespuesta.ERROR);
			respuestaCuentasView.setRespuestaVacia(true);
			respuestaCuentasView.setItemMensajeRespuesta(respuestaCuentas.getItemsMensajeRespuesta());
			return respuestaCuentasView;
		}
		List<CuentasAdhesionDebitoView> cuentasTemp = new ArrayList<CuentasAdhesionDebitoView>();
		// Filtra las cuentas que estan cerradas
		for (CuentasAdhesionDebitoView cuenta : respuestaCuentas.getRespuesta().getCuentas()) {
			if (!cuenta.getIsCerrada()) {
				cuentasTemp.add(cuenta);
			}
		}
		// Setea solo las cuentas activas
		respuestaCuentas.getRespuesta().setCuentas(cuentasTemp);
		// Agrega las cuentas activas a la respuesta
		respuestaCuentasView.setEstadoRespuesta(respuestaCuentas.getEstadoRespuesta());
		respuestaCuentasView.setRespuesta(respuestaCuentas.getRespuesta());
		if (EstadoRespuesta.WARNING.equals(respuestaCuentas.getEstadoRespuesta())) {
			respuestaCuentasView.setItemMensajeRespuesta(respuestaCuentas.getItemsMensajeRespuesta());
		}
		return respuestaCuentasView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager#
	 * agregarSaldoErrorneoEnCuentasActivas(ar.com.santanderrio.obp.base.
	 * respuesta.entities.Respuesta, java.util.List)
	 */
	@Override
	public Respuesta<CuentasView> agregarSaldoErrorneoEnCuentasActivas(Respuesta<CuentasView> respuestaCuentas,
			List<CuentasAdhesionDebitoView> cuentasTemp) {
		List<CuentasAdhesionDebitoView> cuentasActivas = respuestaCuentas.getRespuesta().getCuentas();
		for (CuentasAdhesionDebitoView cuenta : cuentasActivas) {
			if (EstadoRespuesta.WARNING.equals(respuestaCuentas.getEstadoRespuesta())) {
				List<ItemMensajeRespuesta> itemsMensajeRespuestaCuenta = respuestaCuentas.getItemsMensajeRespuesta();
				for (ItemMensajeRespuesta itemMensajeRespuesta : itemsMensajeRespuestaCuenta) {
					String cuentaTag = CUENTAS_TAG + cuenta.getNumero() + TAG_FIN;
					if (TipoError.ERROR_ITEM_CUENTA.getDescripcion().equals(itemMensajeRespuesta.getTipoError())
							&& cuentaTag.equals(itemMensajeRespuesta.getTag())) {
						cuenta.setSaldoPesos(GUION);
						cuenta.setSaldoDolares(GUION);
						cuenta.setSaldoCajaAhorro(GUION);
						cuenta.setSaldoCuentaCorriente(GUION);
						cuenta.setSaldoDescubierto(GUION);
					}
				}
			}
			cuentasTemp.add(cuenta);
		}
		respuestaCuentas.getRespuesta().setCuentas(cuentasTemp);
		return respuestaCuentas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager#
	 * obtenerDetalleCBU(ar.com.santanderrio.obp.servicios.cuentas.web.view.
	 * ConsultaCuentaView, java.lang.String)
	 */
	/*
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager#
	 * obtenerDetalleCBU(ar.com.santanderrio.obp.servicios.cuentas.web.view.
	 * ConsultaCuentaView)
	 */
	@Override
	public Respuesta<DetalleCBUView> obtenerDetalleCBU(ConsultaCuentaView cuenta, String userAgent) {
		DetalleCBUView detalleCBUView = new DetalleCBUView();

		String descripcionSucursal = StringUtils.EMPTY;

		Cliente cliente = sesionCliente.getCliente();

		Respuesta<DetalleCBUView> detalleAliasCBU = aliasCuentaBO.obtenerAliasCBU(cuenta.getCbu(),
				sesionCliente.getIpCliente(), sesionParametros.getRegistroSession().getDni(), userAgent);

		detalleCBUView.setTipoIdentificacion(TIPO_IDENTIFICACION_DETALLE_CUENTA);
		detalleCBUView.setIdentificacionCliente(getCUITCUIL());

		String nombreCliente = cliente.getNombre() + " " + cliente.getApellido1();
		detalleCBUView.setNombreCliente(WordUtils.capitalizeFully(nombreCliente));

		AbstractCuenta abstractCuenta = obtenerCuentaById(cuenta.getNumeroCuenta());
		detalleCBUView.setCbu(abstractCuenta.getCbu());
		detalleCBUView.setNumeroCuenta(cuenta.getNumeroCuenta());
		if (StringUtils.isNotBlank(abstractCuenta.getNroSucursal())) {
			Sucursal sucursal = consultarSucursalesService.consultarSucursalPorId(abstractCuenta.getNroSucursal())
					.getRespuesta();
			if (sucursal != null) {
				descripcionSucursal = " - " + sucursal.getDescripcion();
			}
			detalleCBUView.setNumeroSucursal(
					ISBANStringUtils.formatearSucursal(abstractCuenta.getNroSucursal()) + descripcionSucursal);
		}
		detalleCBUView.setTipoCuenta(TipoCuenta.fromCodigo(abstractCuenta.getTipoCuenta()).getDescripcionConMoneda());
		detalleCBUView.setNombreBanco(BancoEnum.SANTANDER_RIO.getDescripcion());
		if (detalleAliasCBU.getRespuesta() != null) {
			detalleCBUView.setAliasAnterior(detalleAliasCBU.getRespuesta().getAliasAnterior());
			detalleCBUView.setAliasCbu(detalleAliasCBU.getRespuesta().getAliasCbu());
		} else {
			detalleCBUView.setAliasAnterior(StringUtils.EMPTY);
			detalleCBUView.setAliasCbu(StringUtils.EMPTY);
		}

		if (EstadoRespuesta.WARNING.equals(detalleAliasCBU.getEstadoRespuesta()) && TipoError.SIN_OPCIONES_ALIAS_CBU.toString()
				.equals(detalleAliasCBU.getItemsMensajeRespuesta().get(0).getTipoError())) {
			return respuestaFactory.crearRespuestaWarning(detalleCBUView, null);
		}

		return respuestaFactory.crearRespuestaOk(detalleCBUView);

	}

	/**
	 * Gets the cuitcuil.
	 *
	 * @return the cuitcuil
	 */
	private String getCUITCUIL() {
		String cuitCuil = sesionCliente.getCliente().getNumeroCUILCUIT();
		return cuitCuil != null && cuitCuil.length() == 13 ? cuitCuil : null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager#
	 * compartirCBU()
	 */
	@Override
	public Respuesta<Void> compartirCBU() {
		guardarEstadisticaMisProductos(EstadisticasConstants.CODIGO_TRANSACCION_COMPARTIR_CBU);
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager#
	 * obtenerMensajeCopiarCBU()
	 */
	@Override
	public Respuesta<CopiarMensajeView> obtenerMensajeCopiarCBU() {
		Respuesta<CopiarMensajeView> resp = new Respuesta<CopiarMensajeView>();
		CopiarMensajeView copiarMensajeView = new CopiarMensajeView();
		Respuesta<String> respuesta = cuentaBO.obtenerMensajeCopiarCBU();
		copiarMensajeView.setMensaje(respuesta.getRespuesta());
		resp.setEstadoRespuesta(EstadoRespuesta.OK);
		resp.setRespuesta(copiarMensajeView);
		resp.setRespuestaVacia(false);
		return resp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager#
	 * copiarCBU()
	 */
	/*
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager#
	 * copiarCBU()
	 */
	@Override
	public Respuesta<CopiarMensajeView> copiarCBU() {
		Respuesta<CopiarMensajeView> respuesta = this.obtenerMensajeCopiarCBU();
		this.guardarEstadisticaMisProductos(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_REPORTE);
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager#
	 * getCuentasSaldoPorMoneda(java.lang.String)
	 */
	/*
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager#
	 * getCuentasSaldo()
	 */
	@Override
	public Respuesta<CuentasView> getCuentasSaldoPorMoneda(String tipoMoneda) {
		Respuesta<CuentasView> respuestaView = new Respuesta<CuentasView>();
		Respuesta<List<ResumenDetalleCuenta>> respuesta;
		try {
			respuesta = cuentaBO.getCuentasSaldoPorMoneda(sesionCliente.getCliente(), tipoMoneda);
			if (!respuesta.getRespuesta().isEmpty()) {
				respuestaView = convertirCuentasView(respuesta);
			} else {
				CuentasView cuentasView = new CuentasView();
				List<CuentasAdhesionDebitoView> cuentas = new ArrayList<CuentasAdhesionDebitoView>();
				cuentasView.setCuentas(cuentas);
				return respuestaFactory.crearRespuestaOk(CuentasView.class, cuentasView);
			}
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			respuestaView = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
		}
		return respuestaView;
	}

	/**
	 * Retorna la lista de cuentas cepo del cliente.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the cuentas cepo
	 */
	public List<Cuenta> getCuentasCepo(Cliente cliente) {
		List<Cuenta> cuentasCepo = new ArrayList<Cuenta>();
		for (Cuenta cuenta : cliente.getCuentas()) {
			if (isCuentaCepo(cuenta)) {
				cuentasCepo.add(cuenta);
			}
		}
		return cuentasCepo;
	}
	
	/**
	 * Retorna la lista de cuentas banca privada cepo del cliente.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the cuentas banca privada cepo
	 */
	public List<Cuenta> getCuentasCepoBP(Cliente cliente) {
		List<Cuenta> cuentasCepo = new ArrayList<Cuenta>();
		for (Cuenta cuenta : cliente.getCuentasPrivadas()) {
			if (isCuentaCepo(cuenta)) {
				cuentasCepo.add(cuenta);
			}
		}
		return cuentasCepo;
	}

	/**
	 * Retorna true si es una cuenta cepo.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return true, if is cuenta cepo
	 */
	private static boolean isCuentaCepo(Cuenta cuenta) {
		return TipoCuenta.CAJA_AHORRO_DOLARES.equals(TipoCuenta.fromCodigo(cuenta.getTipoCuenta()))
				&& PRODUCTO_ALTAIR_CEPO.equals(cuenta.getProductoAltair())
				&& SUBPRODUCTO_ALTAIR_CEPO.equals(cuenta.getSubproductoAltair());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager#
	 * obtenerInicioCuentas(java.lang.String)
	 */
	@Override
	public Respuesta<CuentasIntermedioView> obtenerInicioCuentas(String userAgent) {

		Respuesta<CuentasIntermedioView> respuestaView = new Respuesta<CuentasIntermedioView>();
		CuentasIntermedioView cuentasIntermedioView = new CuentasIntermedioView();

		try {
			sessionMovimientos.setFiltro(null);
			Cliente cliente = sesionCliente.getCliente();

			Respuesta<List<ResumenDetalleCuenta>> respuesta = cuentaBO.obtenerInfoCuentas(cliente);
			respuestaView = Respuesta.copy(CuentasIntermedioView.class, respuesta);

			if (!EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())) {

				List<ResumenDetalleCuenta> listaResumenDetalleCuenta = respuesta.getRespuesta();

				CuentasUtils.ordenarCuentas(listaResumenDetalleCuenta);
				cuentasIntermedioView.setGrupos(obtenerGrupoCajitas(listaResumenDetalleCuenta));
				List<Cuenta> cuentas = cliente.getCuentas();
				actualizarSaldos(listaResumenDetalleCuenta, cuentas);
				CabeceraCuentasView cabecera = obtenerCabeceraCuentas(listaResumenDetalleCuenta);
				cuentasIntermedioView.setCabecera(cabecera);
				cuentasIntermedioView
						.setSelector(obtenerSelectorCuentasIntermedio(listaResumenDetalleCuenta, cabecera));
				respuestaView.setRespuesta(cuentasIntermedioView);
				if (!tieneErrorItemCuenta(respuesta.getItemsMensajeRespuesta())) {
					estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_OBTENER_CTAS,
							EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				} else {
					estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_OBTENER_CTAS,
							EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				}
			} else {
				estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_OBTENER_CTAS,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_OBTENER_CTAS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			respuestaView = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
		}
		return respuestaView;
	}

	/**
	 * Verificar modo nocturno.
	 *
	 * @param listaResumenDetalleCuenta
	 *            the lista resumen detalle cuenta
	 * @return the item mensaje respuesta
	 */
	private ItemMensajeRespuesta verificarModoNocturno(List<ResumenDetalleCuenta> listaResumenDetalleCuenta) {
		ItemMensajeRespuesta item = null;
		Boolean modoNocturno = Boolean.FALSE;
		if (!CollectionUtils.isEmpty(listaResumenDetalleCuenta)) {
			for (ResumenDetalleCuenta cuenta : listaResumenDetalleCuenta) {

				if (cuenta.getModoNocturno()) {
					modoNocturno = Boolean.TRUE;
					break;
				}
			}
		}
		if (modoNocturno) {
			item = new ItemMensajeRespuesta();
			item.setTipoError(TipoError.ERROR_MODO_NOCTURNO.getDescripcion());
			Mensaje mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_ERROR_MODO_NOCTURNO);
			item.setMensaje(mensaje.getMensaje());
		}
		return item;
	}

	/**
	 * Actualizar saldos.
	 *
	 * @param listaResumenDetalleCuenta
	 *            the lista resumen detalle cuenta
	 * @param cuentas
	 *            the cuentas
	 */
	private void actualizarSaldos(List<ResumenDetalleCuenta> listaResumenDetalleCuenta, List<Cuenta> cuentas) {
		List<DetalleCuenta> detalleCuentaList = new ArrayList<DetalleCuenta>();
		for (ResumenDetalleCuenta resumenDetalleCuenta : listaResumenDetalleCuenta) {
			for (Cuenta cuenta : cuentas) {
				actualizarCuentaSesion(resumenDetalleCuenta, cuenta);
			}
			IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
			identificacionCuenta.setNroCuentaProducto(resumenDetalleCuenta.getNroCuentaProducto());
			identificacionCuenta.setNroSucursal(resumenDetalleCuenta.getNroSucursal());
			DetalleCuenta detalleCuenta = new DetalleCuenta();
			detalleCuenta.setIdentificacionCuenta(identificacionCuenta);
			detalleCuenta.setIsTraspasoAutomatico(resumenDetalleCuenta.getTraspasoAutomaticoActivo());
			detalleCuentaList.add(detalleCuenta);
		}
		sessionDetalleCuentas.setDetalleCuentaList(detalleCuentaList);
	}

	/**
	 * Actualizar cuenta sesion.
	 *
	 * @param resumenDetalleCuenta
	 *            the resumen detalle cuenta
	 * @param cuenta
	 *            the cuenta
	 */
	private void actualizarCuentaSesion(ResumenDetalleCuenta resumenDetalleCuenta, Cuenta cuenta) {
		if (cuenta.getNroCuentaProducto().equals(resumenDetalleCuenta.getNroCuentaProducto())) {
			cuenta.setTraspasoAutomaticoActivo(resumenDetalleCuenta.getTraspasoAutomaticoActivo());
			cuenta.setSolicitudPendienteTraspasoAutomatico(
					resumenDetalleCuenta.getSolicitudPendienteTraspasoAutomatico());
			cuenta.setIndicadorSobregiro(resumenDetalleCuenta.getIndicadorSobregiro());
			if (resumenDetalleCuenta.isCuentaUnica()) {
				cuenta.setSaldoCUDls(resumenDetalleCuenta.getSaldoDolares().toString());
				cuenta.setSaldoCUPesos(resumenDetalleCuenta.getSaldoPesos().toString());
				if (resumenDetalleCuenta.isConvenioPAS()) {
					cuenta.setSaldoAperCajaAhorroPesos(resumenDetalleCuenta.getSaldoCuentaSueldoPesos());
				} else {
					cuenta.setSaldoAperCajaAhorroPesos(resumenDetalleCuenta.getSaldoCajaAhorroPesos());
				}
				cuenta.setSaldoAperCuentaCorrientePesos(resumenDetalleCuenta.getSaldoCuentaCorrientePesos());
				cuenta.setSaldoCuenta(resumenDetalleCuenta.getSaldoPesos().toString());
			} else {
				if (cuenta.isCuentaPesos()) {
					cuenta.setSaldoCuenta(resumenDetalleCuenta.getSaldoPesos().toString());
				} else {
					cuenta.setSaldoCuenta(resumenDetalleCuenta.getSaldoDolares().toString());
				}
			}
		}
	}

	/**
	 * Obtener cabecera cuentas.
	 *
	 * @param listaResumenDetalleCuenta
	 *            the lista resumen detalle cuenta
	 * @return the cabecera cuentas view
	 */
	private CabeceraCuentasView obtenerCabeceraCuentas(List<ResumenDetalleCuenta> listaResumenDetalleCuenta) {
		CabeceraCuentasView cabeceraCuentasView = new CabeceraCuentasView();
		Respuesta<SaldosConsolidadosCuentaDTO> respuesta = cuentaBO
				.obtenerSaldoConsolidadoCliente(listaResumenDetalleCuenta);
		SaldosConsolidadosCuentaDTO saldosConsolidadosCuentaDTO = respuesta.getRespuesta();
		if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
			cabeceraCuentasView.setSaldoPesos(saldosConsolidadosCuentaDTO.getSaldoPesos());
			cabeceraCuentasView.setSaldoDolares(saldosConsolidadosCuentaDTO.getSaldoDolares());
			cabeceraCuentasView.setSaldoPesosValue(saldosConsolidadosCuentaDTO.getSaldoPesosValue());
			cabeceraCuentasView.setSaldoDolaresValue(saldosConsolidadosCuentaDTO.getSaldoDolaresValue());
		} else {
			cabeceraCuentasView.setSaldoPesos(GUION);
			cabeceraCuentasView.setSaldoDolares(GUION);
			cabeceraCuentasView.setSaldoPesosValue(null);
			cabeceraCuentasView.setSaldoDolaresValue(null);
		}
		return cabeceraCuentasView;
	}

	/**
	 * Obtener selector cuentas intermedio.
	 *
	 * @param listaResumenDetalleCuenta
	 *            the lista resumen detalle cuenta
	 * @param cabecera
	 *            the cabecera
	 * @return the list
	 */
	//TODO: CHECK IF REPATRIACION NEEDS TO BE REMOVED
	private List<CuentaSelectorView> obtenerSelectorCuentasIntermedio(
			List<ResumenDetalleCuenta> listaResumenDetalleCuenta, CabeceraCuentasView cabecera) {
		List<CuentaSelectorView> listaCuentaSelectorView = new ArrayList<CuentaSelectorView>();
		CuentaSelectorView cuentaSelector = agregarCabecera(cabecera, new CuentaSelectorView());
		listaCuentaSelectorView.add(cuentaSelector);
		for (ResumenDetalleCuenta resumenDetalleCuenta : listaResumenDetalleCuenta) {
			CuentaSelectorView c = getCuentaSelectorView(resumenDetalleCuenta, new CuentaSelectorView());
			listaCuentaSelectorView.add(c);
		}
		return listaCuentaSelectorView;
	}

	/**
	 * Obtener selector cuentas.
	 *
	 * @param listaResumenDetalleCuenta
	 *            the lista resumen detalle cuenta
	 * @param cabecera
	 *            the cabecera
	 * @return the list
	 */
	private List<CuentaSelectorDetalleView> obtenerSelectorCuentas(List<ResumenDetalleCuenta> listaResumenDetalleCuenta,
			CabeceraCuentasView cabecera) {
		List<CuentaSelectorDetalleView> listaCuentaSelectorDetalleView = new ArrayList<CuentaSelectorDetalleView>();
		CuentaSelectorDetalleView cuentaSelector = (CuentaSelectorDetalleView) agregarCabecera(cabecera,
				new CuentaSelectorDetalleView());
		listaCuentaSelectorDetalleView.add(cuentaSelector);

		for (ResumenDetalleCuenta resumenDetalleCuenta : listaResumenDetalleCuenta) {
			CuentaSelectorDetalleView c = (CuentaSelectorDetalleView) getCuentaSelectorView(resumenDetalleCuenta,
					new CuentaSelectorDetalleView());
			String sucursal = StringUtils.leftPad(ISBANStringUtils.eliminarCeros(resumenDetalleCuenta.getNroSucursal()),
					PAD_SIZE_SUCURSAL, PAD_CERO);
			String decripcionSucursal = sucursal;
			if (StringUtils.isNotBlank(sucursal)) {
				Sucursal sucursalCuenta = consultarSucursalesBO
						.consultarSucursalPorId(resumenDetalleCuenta.getNroSucursal()).getRespuesta();
				if (sucursalCuenta != null) {
					decripcionSucursal = sucursal + " - " + sucursalCuenta.getDescripcion();
				}
			}
			c.setSucursal(decripcionSucursal);
			c.setAbreviaturaTipoCuenta(obtenerAbreviaturaTipoCuenta(resumenDetalleCuenta.getTipoCuenta()));
			c.setCbu(resumenDetalleCuenta.getCbu());
			c.setIsTraspasoAutomatico(resumenDetalleCuenta.getTraspasoAutomaticoActivo());
			c.setUrlReporteCBU(obtenerURLReporteCBU(resumenDetalleCuenta));
			c.setNombreCliente(WordUtils.capitalizeFully(sesionCliente.getCliente().getNombre()));
			c.setApellidoCliente(WordUtils.capitalizeFully(sesionCliente.getCliente().getApellido1()));
			c.setTipoIdentificacion(TIPO_IDENTIFICACION_DETALLE_CUENTA);
			c.setNumeroIdentificacion(getCUITCUIL());
			if (StringUtils.isEmpty(resumenDetalleCuenta.getAlias())) {
				c.setHasAlias(false);
			} else {
				c.setHasAlias(true);
			}
			c.setFechaDesdeMovimiento(resumenDetalleCuenta.getFechaDesdeMovimiento());
			c.setFechaHastaMovimiento(resumenDetalleCuenta.getFechaHastaMovimiento());

			c.setHasTraspaso(resumenDetalleCuenta.isCuentaTraspasoAutomatico());
			c.setShowRealizarTraspaso(resumenDetalleCuenta.getShowRealizarTraspaso());
			c.setShowSolicitarTraspaso(resumenDetalleCuenta.getShowSolicitarTraspaso());
			c.setMotivoCuentaCerrada(resumenDetalleCuenta.getMotivoCuentaCerrada());
			listaCuentaSelectorDetalleView.add(c);

		}
		return listaCuentaSelectorDetalleView;
	}

	/**
	 * Gets the cuenta selector view.
	 *
	 * @param resumenDetalleCuenta
	 *            the resumen detalle cuenta
	 * @param cuentaSelectorView
	 *            the cuenta selector view
	 * @return the cuenta selector view
	 */
	private CuentaSelectorView getCuentaSelectorView(ResumenDetalleCuenta resumenDetalleCuenta,
			CuentaSelectorView cuentaSelectorView) {
		cuentaSelectorView = CuentaViewBuilder.construirCuentaSelectorView(resumenDetalleCuenta);
		return cuentaSelectorView;
	}

	/**
	 * Agregar cabecera.
	 *
	 * @param cabecera
	 *            the cabecera
	 * @param cuentaSelectorView
	 *            the cuenta selector view
	 * @return the cuenta selector view
	 */
	private CuentaSelectorView agregarCabecera(CabeceraCuentasView cabecera, CuentaSelectorView cuentaSelectorView) {
		cuentaSelectorView.setDescripcionTipoCuenta(SALDOS_TOTALES);
		cuentaSelectorView.setSaldoPesos(cabecera.getSaldoPesos());
		cuentaSelectorView.setSaldoPesosValue(cabecera.getSaldoPesosValue());
		cuentaSelectorView.setSaldoDolares(cabecera.getSaldoDolares());
		cuentaSelectorView.setSaldoDolaresValue(cabecera.getSaldoDolaresValue());

		return cuentaSelectorView;
	}

	/**
	 * Obtener grupo cajitas.
	 *
	 * @param listaResumenDetalleCuenta
	 *            the lista resumen detalle cuenta
	 * @return the list
	 */
	private List<GrupoCajitaCuentasView> obtenerGrupoCajitas(List<ResumenDetalleCuenta> listaResumenDetalleCuenta) {
		List<GrupoCajitaCuentasView> listaGrupoCajitaCuentasView = new ArrayList<GrupoCajitaCuentasView>();
		for (ResumenDetalleCuenta resumenDetalleCuenta : listaResumenDetalleCuenta) {
			if (!resumenDetalleCuenta.isCuentaCerrada()) {
				GrupoCajitaCuentasView grupoCajitaCuentasView = new GrupoCajitaCuentasView();
				List<BoxCuentaView> listaBoxCuentaView = new ArrayList<BoxCuentaView>();
				grupoCajitaCuentasView.setCajas(listaBoxCuentaView);
				if (resumenDetalleCuenta.isCuentaUnica()) {
					listaBoxCuentaView.add(crearCajitaCuenta(resumenDetalleCuenta, TipoCuenta.CUENTA_UNICA_PESOS));
					listaBoxCuentaView.add(crearCajitaCuenta(resumenDetalleCuenta, TipoCuenta.CUENTA_UNICA_DOLARES));
				} else {
					listaBoxCuentaView.add(crearCajitaCuenta(resumenDetalleCuenta,
							TipoCuenta.fromCodigo(resumenDetalleCuenta.getTipoCuenta())));
				}
				listaGrupoCajitaCuentasView.add(grupoCajitaCuentasView);
			}
		}
		return listaGrupoCajitaCuentasView;
	}

	/**
	 * Crear cajita cuenta.
	 *
	 * @param resumenDetalleCuenta
	 *            the resumen detalle cuenta
	 * @param tipoCajitaCuentaEnum
	 *            the tipo cajita cuenta enum
	 * @return the box cuenta view
	 */
	private BoxCuentaView crearCajitaCuenta(ResumenDetalleCuenta resumenDetalleCuenta,
			TipoCuenta tipoCajitaCuentaEnum) {
		BoxCuentaView boxCuentaView = CuentaViewBuilder.construirBoxCuentaView(resumenDetalleCuenta,
				tipoCajitaCuentaEnum);
		return boxCuentaView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager#
	 * grabarEstadisticaObtenerCuentasHome()
	 */
	public void grabarEstadisticaObtenerCuentasHome() {
		estadisticaManager.add(EstadisticasConstants.ESTADISTICA_ACCESO_CUENTAS_HOME,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	@Override
	public Respuesta<List<AliasFavoritoProducto>> obtenerAliasYFavoritos() {
		String nup = sesionCliente.getCliente().getNup();
	    List<AliasFavoritoProducto> aliasYFavoritos = cuentaBO.obtenerAliasYFavoritos(nup);
	    if (aliasYFavoritos == null) {
	        return respuestaFactory.crearRespuestaErrorPersonalizadoSinClase("Error Alias y Favoritos", 
	                TipoError.ERROR_GENERICO.getDescripcion());
	    }
	    return respuestaFactory.crearRespuestaOk(aliasYFavoritos);
	}

}
