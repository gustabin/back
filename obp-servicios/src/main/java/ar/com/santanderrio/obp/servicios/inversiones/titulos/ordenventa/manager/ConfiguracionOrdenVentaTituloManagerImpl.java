/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.PoderCompraView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.inversiones.custodia.view.CuentaCustodiaView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo.TitulosBO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CuentasPDC;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteAceptacionCNV;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.FirmarCuentasViewRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.bo.ConfiguracionOrdenVentaTituloBO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.dto.CondicionesGeneralesCuentasCustodiaOrdenVentaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.dto.ConfiguracionOrdenVentaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.dto.PlazoAcreditacionOrdenVentaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.AceptacionCondicionesCuentasCustodiaViewIn;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.CondicionesGeneralesCuentaCustodiaView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.ConfiguracionOrdenVentaInView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.ConfiguracionOrdenVentaView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.OpcionSelectorConfiguracionView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.PlazoAcreditacionOrdenVentaView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.PrecioReferenciaView;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;

/**
 * The Class ConfiguracionOrdenVentaTituloManagerImpl.
 */
@Component
public class ConfiguracionOrdenVentaTituloManagerImpl implements ConfiguracionOrdenVentaTituloManager {

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The configuracion orden venta BO. */
	@Autowired
	private ConfiguracionOrdenVentaTituloBO configuracionOrdenVentaBO;

	/** The cuenta manager. */
	@Autowired
	private CuentaManager cuentaManager;

	/** The titulos BO. */
	@Autowired
	private TitulosBO titulosBO;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The reporte BO. */
	@Autowired
	private ReporteComprobantePDFBO reporteBO;
	
	@Autowired
	private ModuloPermisoBO moduloPermisoBO;

	/** The Constant SIN_COTIZACION. */
	private static final String SIN_COTIZACION = "Sin cotización";

	/** The Constant SIN_LIMITE. */
	private static final String SIN_LIMITE = "Sin límite";

	/** The Constant HS. */
	private static final String HS = " hs";

	/** The Constant INMEDIATO. */
	private static final String INMEDIATO = "Inmediato";

	/** The Constant CERO. */
	private static final String CERO = "0";

	/** The Constant GUION. */
	private static final String GUION = "-";
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.manager.
	 * ConfiguracionOrdenVentaTituloManager#configuracionOrdenVenta(ar.com.
	 * santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.
	 * ConfiguracionOrdenVentaInView)
	 */
	@Override
	public Respuesta<ConfiguracionOrdenVentaView> configuracionOrdenVenta(ConfiguracionOrdenVentaInView datosEntrada) {
		if (datosEntrada.getReintentar() == null || !(datosEntrada.getReintentar())) {
			sesionParametros.setContador(new ContadorIntentos(2));
		}
		Respuesta<ConfiguracionOrdenVentaDTO> respuestaDTO = configuracionOrdenVentaBO
				.obtenerConfiguracionOrdenVenta(sesionCliente.getCliente(), datosEntrada);
		if (respuestaDTO.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			grabarEstadisticasError(datosEntrada);
			return respuestaFactory.crearRespuestaError(ConfiguracionOrdenVentaView.class,
					respuestaDTO.getItemsMensajeRespuesta());
		}
		return obtenerRespuesta(respuestaDTO.getRespuesta(), datosEntrada);
	}

	/**
	 * Obtener respuesta.
	 *
	 * @param configuracionDTO the configuracion DTO
	 * @param datosEntrada     the datos entrada
	 * @return the respuesta
	 */
	private Respuesta<ConfiguracionOrdenVentaView> obtenerRespuesta(ConfiguracionOrdenVentaDTO configuracionDTO,
			ConfiguracionOrdenVentaInView datosEntrada) {
		ConfiguracionOrdenVentaView view = new ConfiguracionOrdenVentaView();
		if (!datosEntrada.getEsBancaPrivada()) {
			Respuesta<CuentasView> cuentasView = cuentaManager.getCuentasSaldo();
			if (cuentasView.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
				grabarEstadisticasError(datosEntrada);
				return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_SERVICIO,
						CodigoMensajeConstantes.ORDEN_VENTA_TITULOS_ERROR_SERVICIO);
			}
			List<CuentasAdhesionDebitoView> cuentasPesos = new ArrayList<CuentasAdhesionDebitoView>();
			List<CuentasAdhesionDebitoView> cuentasDolar = new ArrayList<CuentasAdhesionDebitoView>();
			rellenarListasCuentasPorMoneda(cuentasView.getRespuesta().getCuentas(), cuentasPesos, cuentasDolar);
			setearPDCACuentas(cuentasPesos, cuentasDolar, configuracionDTO);
			view.setCuentasDestinoPesos(cuentasPesos);
			view.setCuentasDestinoDolar(cuentasDolar);
		} else {
			view.setCuentaOperativa(configuracionDTO.getCuentaOperativa());
		}
		completarViewConDatosAmbasBancas(configuracionDTO, datosEntrada, view);
		view=inyectaRepatriacion(view,datosEntrada);
		grabarEstadisticasOK(datosEntrada);
		view.setLegalCNV(configuracionDTO.getLegalCNV());
		return respuestaFactory.crearRespuestaOk(view);
	}

	private ConfiguracionOrdenVentaView inyectaRepatriacion(ConfiguracionOrdenVentaView view,
			ConfiguracionOrdenVentaInView datosEntrada) {
		
		if(datosEntrada.getEsBancaPrivada()) {
			
		}else {
			List<Cuenta> ctasDebOp=sesionCliente.getCliente().getCuentas();
			List<CuentasAdhesionDebitoView> ctasDebitoUSD=view.getCuentasDestinoDolar();
			List<CuentasAdhesionDebitoView> ctasDebitoARS=view.getCuentasDestinoPesos();
			for(Cuenta ctaOp : ctasDebOp) {
				for(CuentasAdhesionDebitoView  ctaUS: ctasDebitoUSD) {
					if(ctaOp.getCbu().equals(ctaUS.getCbu())) {
						ctaUS.setRepatriacion(ctaOp.isRepatriacion());
					}
				}
				for(CuentasAdhesionDebitoView  ctaARS: ctasDebitoARS) {
					if(ctaOp.getCbu().equals(ctaARS.getCbu())) {
						ctaARS.setRepatriacion(ctaOp.isRepatriacion());
					}
				}
			}
		}
		
		return view;
	}

	private void setearPDCACuentas(List<CuentasAdhesionDebitoView> cuentasPesos,
			List<CuentasAdhesionDebitoView> cuentasDolar, ConfiguracionOrdenVentaDTO configuracionDTO) {
		if (configuracionDTO.isPoderCompraOk()) {
			for (CuentasPDC cta : configuracionDTO.getListaCuentasPDC()) {
				if ("AD".equalsIgnoreCase(cta.getIndicadorPDC())) {
					if ("ARS".equalsIgnoreCase(cta.getMoneda())) {
						for (CuentasAdhesionDebitoView cuentaOper : cuentasPesos) {
							if (cta.getListaDatosSaldo().get(0).getNumeroCuenta().equals(String.valueOf(Long.valueOf(
									cuentaOper.getNumero().substring(3).replaceAll("-", "").replaceAll("/", ""))))) {
								cuentaOper.setPoderCompra(new PoderCompraView());
							}
						}
					} else {
						for (CuentasAdhesionDebitoView cuentaOper : cuentasDolar) {
							if (cta.getListaDatosSaldo().get(0).getNumeroCuenta().equals(String.valueOf(Long.valueOf(
									cuentaOper.getNumero().substring(3).replaceAll("-", "").replaceAll("/", ""))))) {
								cuentaOper.setPoderCompra(new PoderCompraView());
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Completar view con datos ambas bancas.
	 *
	 * @param configuracionDTO the configuracion DTO
	 * @param datosEntrada     the datos entrada
	 * @param view             the view
	 */
	private void completarViewConDatosAmbasBancas(ConfiguracionOrdenVentaDTO configuracionDTO,
			ConfiguracionOrdenVentaInView datosEntrada, ConfiguracionOrdenVentaView view) {
		view.setMoneda(configuracionDTO.getMoneda());
		view.setPlazos(obtenerPlazosView(configuracionDTO.getPlazos()));
		view.setOpciones(obtenerOpciones(configuracionDTO.getPlazos()));
		view.setPlazoDefault(calcularIdPlazoDefault(configuracionDTO.getPlazos(), datosEntrada));
		view.setPreciosReferencia(obtenerPreciosReferencia(view.getPlazos()));
		view.setTextoPrecioLimite(configuracionDTO.getMensajePrecioLimite());
		view.setTextoOrigenFondos(configuracionDTO.getTextoOrigenFondos());
		view.setLeyendaLegal(configuracionDTO.getLeyendaLegal());
		view.setCondicionesCuentasCustodia(
				obtenerCondicionesCuentasCustodiaView(configuracionDTO.getCondicionesGenerales()));
		if (!datosEntrada.getEsBancaPrivada()) {
			view.setLegalesPrecioReferencia(configuracionDTO.getLegalesPrecioReferencia());
		}
		view.setIndicadorPDCDolares(configuracionDTO.getIndicadorPDCDolares());
		view.setIndicadorPDCPesos(configuracionDTO.getIndicadorPDCPesos());
	}

	/**
	 * Grabar estadisticas OK.
	 *
	 * @param datosEntrada the datos entrada
	 */
	private void grabarEstadisticasOK(ConfiguracionOrdenVentaInView datosEntrada) {
		estadisticaManager.add(
				datosEntrada.getEsBancaPrivada() ? EstadisticasConstants.ORDEN_VENTA_TITULOS_CONFIGURACION_BPRIV
						: EstadisticasConstants.ORDEN_VENTA_TITULOS_CONFIGURACION,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		grabarEstadisticaSiIngresoPorGrilla(datosEntrada, false);
	}

	/**
	 * Grabar estadisticas error.
	 *
	 * @param datosEntrada the datos entrada
	 */
	private void grabarEstadisticasError(ConfiguracionOrdenVentaInView datosEntrada) {
		estadisticaManager.add(
				datosEntrada.getEsBancaPrivada() ? EstadisticasConstants.ORDEN_VENTA_TITULOS_CONFIGURACION_BPRIV
						: EstadisticasConstants.ORDEN_VENTA_TITULOS_CONFIGURACION,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		grabarEstadisticaSiIngresoPorGrilla(datosEntrada, true);
	}

	/**
	 * Rellenar listas cuentas por moneda.
	 *
	 * @param cuentas        the cuentas
	 * @param cuentasPesos   the cuentas pesos
	 * @param cuentasDolares the cuentas dolares
	 */
	private void rellenarListasCuentasPorMoneda(List<CuentasAdhesionDebitoView> cuentas,
			List<CuentasAdhesionDebitoView> cuentasPesos, List<CuentasAdhesionDebitoView> cuentasDolares) {
		for (CuentasAdhesionDebitoView cuenta : cuentas) {
			if (cuenta.getShowSaldoPesos()) {
				cuentasPesos.add(cuenta);
			}
			if (cuenta.getShowSaldoDolares()) {
				cuentasDolares.add(cuenta);
			}
		}
	}

	/**
	 * Obtener plazos view.
	 *
	 * @param plazosDTO the plazos DTO
	 * @return the list
	 */
	private List<PlazoAcreditacionOrdenVentaView> obtenerPlazosView(List<PlazoAcreditacionOrdenVentaDTO> plazosDTO) {
		List<PlazoAcreditacionOrdenVentaView> plazosView = new ArrayList<PlazoAcreditacionOrdenVentaView>();
		for (PlazoAcreditacionOrdenVentaDTO dto : plazosDTO) {
			PlazoAcreditacionOrdenVentaView view = new PlazoAcreditacionOrdenVentaView();
			view.setId(dto.getId());
			view.setNombre(dto.getNombre());
			view.setTenenciaNegociable(TitulosOrdenVentaManagerImpl.formatearTenencia(dto.getTenenciaNegociable()));
			String simboloMoneda = dto.getEsPesos() ? DivisaEnum.PESO.getSimbolo() : DivisaEnum.DOLAR.getSimbolo();
			view.setPrecioReferencia(dto.getPrecioReferencia() == null ? SIN_COTIZACION
					: simboloMoneda + " " + ISBANStringUtils.formatearSaldoPrecioReferencia(dto.getPrecioReferencia()));
			view.setFechaHoraReferencia(dto.getPrecioReferencia() == null || dto.getFechaReferencia() == null
					|| dto.getHoraReferencia() == null ? null
							: dto.getFechaReferencia() + " - " + dto.getHoraReferencia() + HS);
			view.setHoraCotizacionEspecie(dto.getHoraReferencia());
			view.setFechaCotizacionEspecie(dto.getFechaReferencia());
			view.setNominalMinimo(formatearMixMax(dto.getNominalMinimoValue()));
			view.setNominalMaximo(formatearMixMax(dto.getNominalMaximoValue()));
			view.setNominalMinimoValue(dto.getNominalMinimoValue());
			view.setNominalMaximoValue(dto.getNominalMaximoValue());
			view.setNominalIncremento(TitulosOrdenVentaManagerImpl.formatearTenencia(dto.getNominalIncrementoValue()));
			view.setNominalIncrementoValue(dto.getNominalIncrementoValue());
			view.setEsPesos(dto.getEsPesos());
			view.setPrecioMinimo(formatearMixMax(dto.getPrecioMinimoValue()));
			view.setPrecioMaximo(formatearMixMax(dto.getPrecioMaximoValue()));
			view.setPrecioMinimoValue(dto.getPrecioMinimoValue());
			view.setPrecioMaximoValue(dto.getPrecioMaximoValue());
			view.setPrecioIncremento(TitulosOrdenVentaManagerImpl.formatearTenencia(dto.getPrecioIncrementoValue()));
			view.setPrecioIncrementoValue(dto.getPrecioIncrementoValue());
			view.setRequierePrecioLimite(dto.getRequierePrecioLimite());
			view.setFechaLiquidacion(dto.getFechaDeLiquidacion());
			plazosView.add(view);
		}
		return plazosView;
	}

	/**
	 * Formatear mix max.
	 *
	 * @param valor the valor
	 * @return the string
	 */
	private String formatearMixMax(BigDecimal valor) {
		if (BigDecimal.ZERO.compareTo(valor) == 0) {
			return SIN_LIMITE;
		}
		return TitulosOrdenVentaManagerImpl.formatearTenencia(valor);
	}

	/**
	 * Obtener opciones.
	 *
	 * @param plazos the plazos
	 * @return the list
	 */
	private List<OpcionSelectorConfiguracionView> obtenerOpciones(List<PlazoAcreditacionOrdenVentaDTO> plazos) {
		Map<String, OpcionSelectorConfiguracionView> opcionesMap = new HashMap<String, OpcionSelectorConfiguracionView>();
		for (PlazoAcreditacionOrdenVentaDTO dto : plazos) {
			OpcionSelectorConfiguracionView opcionView;
			if (opcionesMap.containsKey(dto.getNombre())) {
				opcionView = opcionesMap.get(dto.getNombre());
			} else {
				opcionView = new OpcionSelectorConfiguracionView();
				opcionView.setNombre(dto.getNombre());
			}
			if (dto.getEsPesos()) {
				opcionView.setPermitePesos(true);
			} else {
				opcionView.setPermiteDolares(true);
			}
			opcionesMap.put(dto.getNombre(), opcionView);
		}
		List<OpcionSelectorConfiguracionView> listaOpcionesView = new ArrayList<OpcionSelectorConfiguracionView>();
		listaOpcionesView.addAll(opcionesMap.values());
		return listaOpcionesView;
	}

	/**
	 * Calcular id plazo default.
	 *
	 * @param plazos       the plazos
	 * @param datosEntrada the datos entrada
	 * @return the integer
	 */
	private Integer calcularIdPlazoDefault(List<PlazoAcreditacionOrdenVentaDTO> plazos,
			ConfiguracionOrdenVentaInView datosEntrada) {
		String nombrePlazo = obtenerNombrePlazo(datosEntrada.getPlazo());
		if (datosEntrada.getIngresoDesdeGrilla() == null || datosEntrada.getIngresoDesdeGrilla()) {
			BigDecimal tenenciaMayor = plazos.get(0).getTenenciaNegociable();
			Integer plazoDefault = plazos.get(0).getId();
			for (PlazoAcreditacionOrdenVentaDTO plazo : plazos) {
				if (tenenciaMayor.compareTo(plazo.getTenenciaNegociable()) == (2)) {
					tenenciaMayor = plazo.getTenenciaNegociable();
					plazoDefault = plazo.getId();
				}
			}
			return plazoDefault;
		} else {
			for (PlazoAcreditacionOrdenVentaDTO plazo : plazos) {
				if (StringUtils.equals(nombrePlazo, plazo.getNombre())) {
					return plazo.getId();
				}
			}
		}
		return 1;
	}

	/**
	 * Obtener nombre plazo.
	 *
	 * @param plazoDefault the plazo default
	 * @return the string
	 */
	private String obtenerNombrePlazo(String plazoDefault) {
		if (StringUtils.equals(plazoDefault, CERO)) {
			return INMEDIATO;
		} else {
			return plazoDefault + HS;
		}
	}

	/**
	 * Obtener precios referencia.
	 *
	 * @param plazos the plazos
	 * @return the list
	 */
	private List<PrecioReferenciaView> obtenerPreciosReferencia(List<PlazoAcreditacionOrdenVentaView> plazos) {
		Map<String, PrecioReferenciaView> preciosReferenciaMap = obtenerMapaPrecios(plazos);
		List<PrecioReferenciaView> preciosView = new ArrayList<PrecioReferenciaView>();
		preciosView.addAll(preciosReferenciaMap.values());
		for (PrecioReferenciaView precioView : preciosView) {
			if (precioView.getPesos() == null) {
				precioView.setPesos(GUION);
			} else if (precioView.getDolares() == null) {
				precioView.setDolares(GUION);
			}
		}
		return preciosView;
	}

	/**
	 * Obtener mapa precios.
	 *
	 * @param plazos the plazos
	 * @return the map
	 */
	private Map<String, PrecioReferenciaView> obtenerMapaPrecios(List<PlazoAcreditacionOrdenVentaView> plazos) {
		Map<String, PrecioReferenciaView> preciosReferenciaMap = new HashMap<String, PrecioReferenciaView>();
		for (PlazoAcreditacionOrdenVentaView plazo : plazos) {
			PrecioReferenciaView precioReferenciaView;
			if (preciosReferenciaMap.containsKey(plazo.getNombre())) {
				precioReferenciaView = preciosReferenciaMap.get(plazo.getNombre());
			} else {
				precioReferenciaView = new PrecioReferenciaView();
				precioReferenciaView.setPlazo(plazo.getNombre());
			}
			if (plazo.getEsPesos()) {
				precioReferenciaView.setPesos(SIN_COTIZACION.equals(plazo.getPrecioReferencia()) ? SIN_COTIZACION
						: plazo.getPrecioReferencia());
			} else {
				precioReferenciaView.setDolares(SIN_COTIZACION.equals(plazo.getPrecioReferencia()) ? SIN_COTIZACION
						: plazo.getPrecioReferencia());
			}
			preciosReferenciaMap.put(plazo.getNombre(), precioReferenciaView);
		}
		return preciosReferenciaMap;
	}

	/**
	 * Obtener condiciones cuentas custodia view.
	 *
	 * @param condicionesGenerales the condiciones generales
	 * @return the condiciones generales cuenta custodia view
	 */
	private CondicionesGeneralesCuentaCustodiaView obtenerCondicionesCuentasCustodiaView(
			CondicionesGeneralesCuentasCustodiaOrdenVentaDTO condicionesGenerales) {

		CondicionesGeneralesCuentaCustodiaView condicionesView = new CondicionesGeneralesCuentaCustodiaView();
		condicionesView.setCondicionesAceptadas(condicionesGenerales.getCondicionesAceptadas());
		condicionesView.setLegal1(condicionesGenerales.getIntro());
		condicionesView.setLegal2(condicionesGenerales.getCondiciones());
		
		if (moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.VENTAS_SIN_VALIDAR_FIRMAS).tienePermisoDeVisibilidad()) {
		condicionesView.setCuentasCustodia(condicionesGenerales.getCuentasFirmadasSinValidacion());
		condicionesView.setCuentasFirmadas(condicionesGenerales.getCuentasFirmadasSinValidacion());
		}
		else {
			condicionesView.setCuentasCustodia(condicionesGenerales.getCondicionesAceptadas()
					? transformarCuentasCustodiaView(condicionesGenerales.getCuentasFirmadas())
					: transformarCuentasCustodiaView(condicionesGenerales.getCuentasSinFirmar()));
			condicionesView.setCuentasFirmadas(transformarCuentasCustodiaView(condicionesGenerales.getCuentasFirmadas()));
		}
		condicionesView.setLegal3(condicionesGenerales.getLegal());
		condicionesView.setLegalAdicional(condicionesGenerales.getLegalMail());
		condicionesView.setEmail(condicionesGenerales.getMail());
		condicionesView.setComprobantesCNVDisponibles(condicionesGenerales.getComprobantesDisponibles());
		return condicionesView;
	}
	
	/**
	 * Transformar cuentas custodia view.
	 *
	 * @param cuentas the cuentas
	 * @return the list
	 */
	private List<CuentaCustodiaView> transformarCuentasCustodiaView(List<Cuenta> cuentas) {
		List<CuentaCustodiaView> listaView = new ArrayList<CuentaCustodiaView>();
		for (Cuenta cuenta : cuentas) {
			CuentaCustodiaView ccView = new CuentaCustodiaView();
			ccView.setNumeroCuenta(cuenta.getNroCuentaProducto());
			ccView.setSucursal(StringUtils.leftPad(cuenta.getNroSucursal(), 3, CERO));
			listaView.add(ccView);
		}
		return listaView;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.manager.
	 * ConfiguracionOrdenVentaTituloManager#aceptacionResolucionOrigenDeFondos(java.
	 * lang.Boolean)
	 */
	@Override
	public void aceptacionResolucionOrigenDeFondos(Boolean esBancaPrivada) {
		estadisticaManager.add(
				esBancaPrivada ? EstadisticasConstants.ORDEN_VENTA_ACEPTACION_RESOLUCION_ORIGEN_FONDOS_BPRIV
						: EstadisticasConstants.ORDEN_VENTA_ACEPTACION_RESOLUCION_ORIGEN_FONDOS,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.manager.
	 * ConfiguracionOrdenVentaTituloManager#aceptacionCondicionesCuentasOrdenVenta(
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.
	 * AceptacionCondicionesCuentasCustodiaViewIn)
	 */
	@Override
	public Respuesta<Void> aceptacionCondicionesCuentasOrdenVenta(
			AceptacionCondicionesCuentasCustodiaViewIn aceptacionView) {
		FirmarCuentasViewRequest request = new FirmarCuentasViewRequest();
		request.setEmail(aceptacionView.getEmail());
		request.setCuentasCustodia(aceptacionView.getCuentasCustodia());
		Respuesta<String> respuesta = titulosBO.firmarCuentas(sesionCliente.getCliente(), request);
		estadisticaManager.add(
				aceptacionView.getEsBancaPrivada()
						? EstadisticasConstants.ORDEN_VENTA_ACEPTACION_CONDICIONES_CUENTAS_BPRIV
						: EstadisticasConstants.ORDEN_VENTA_ACEPTACION_CONDICIONES_CUENTAS,
				respuesta.getEstadoRespuesta().equals(EstadoRespuesta.OK) ? EstadisticasConstants.CODIGO_ESTADISTICAS_OK
						: EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.manager.
	 * ConfiguracionOrdenVentaTituloManager#
	 * descargarComprobanteAceptacionCNVOrdenVenta(java.lang.Boolean)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteAceptacionCNVOrdenVenta(Boolean esBancaPrivada) {
		ComprobanteAceptacionCNV comprobante = new ComprobanteAceptacionCNV();
		comprobante.setComprobante(sesionParametros.getComprobanteCNVLicitaciones());
		Respuesta<Reporte> reporte = reporteBO.obtenerComprobantePDF(comprobante);
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			estadisticaManager.add(
					esBancaPrivada ? EstadisticasConstants.ORDEN_VENTA_DESCARGA_COMPROBANTE_CNV_BPRIV
							: EstadisticasConstants.ORDEN_VENTA_DESCARGA_COMPROBANTE_CNV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(reporteView);
		} else {
			estadisticaManager.add(
					esBancaPrivada ? EstadisticasConstants.ORDEN_VENTA_DESCARGA_COMPROBANTE_CNV_BPRIV
							: EstadisticasConstants.ORDEN_VENTA_DESCARGA_COMPROBANTE_CNV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
		}
	}

	/**
	 * Grabar estadistica si ingreso por grilla.
	 *
	 * @param datosEntrada the datos entrada
	 * @param hayError     the hay error
	 */
	private void grabarEstadisticaSiIngresoPorGrilla(ConfiguracionOrdenVentaInView datosEntrada, Boolean hayError) {
		if (datosEntrada.getIngresoDesdeGrilla() != null && datosEntrada.getIngresoDesdeGrilla() && hayError) {
			estadisticaManager.add(EstadisticasConstants.ACCESO_A_ORDEN_VENTA_BANCA_PERSONAL_DESDE_GRILLA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		} else if (datosEntrada.getIngresoDesdeGrilla() != null && datosEntrada.getIngresoDesdeGrilla() && !hayError) {
			estadisticaManager.add(EstadisticasConstants.ACCESO_A_ORDEN_VENTA_BANCA_PERSONAL_DESDE_GRILLA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}

	}

}