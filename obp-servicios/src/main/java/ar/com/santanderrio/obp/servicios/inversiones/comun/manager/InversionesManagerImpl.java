/*
 * 
 */

package ar.com.santanderrio.obp.servicios.inversiones.comun.manager;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaBancaPrivada;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.web.manager.impl.AdministradorPermisos;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.manager.AnalisisCarteraManager;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RentabilidadTotalInView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RentabilidadTotalView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.ValoresRentabilidadCabeceraView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoOperacionInversionesEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoProductoEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.bo.InversionesBO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dto.DetalleCustodiaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dto.TenenciaConsolidadaPorProductoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dto.TotalesTenenciaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DetalleCustodiaCuentaMonedaView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DetalleCustodiaCuentaView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DetalleCustodiaInView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DetalleCustodiaTenenciaExperesada;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DetalleCustodiaView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.InicioInversionesViewRequest;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TarjetaTenenciaConsolidadaView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaConsolidadaBPrivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaConsolidadaBPrivView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaConsolidadaView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaPorCuentaBPrivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaPorProductoBPrivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaPorProductoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaPorProductoInView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaPorProductoView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaProductosMonedaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaProductosPorMonedaView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaReexpresada;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.InicioFondoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CuentaTituloView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.InicioFondoView;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;

/**
 * The Class InversionesManagerImpl.
 */
@Component
public class InversionesManagerImpl extends BaseManager implements InversionesManager {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(InversionesManagerImpl.class);

	/** The Constant CODIGO_ERROR_OK. */
	private static final String CODIGO_ERROR_OK = "0";

	/** The Constant INVERSIONES_CONSOLIDADO_ERROR_PARCIAL. */
	public static final String INVERSIONES_CONSOLIDADO_ERROR_PARCIAL = "10485";

	/** The Constant OK. */
	private static final String OK = "1";

	/** The Constant ERROR. */
	private static final String ERROR = "2";

	/** The Constant PARCIAL. */
	private static final String PARCIAL = "9";

	/** The Constant GUION. */
	private static final String GUION = "-";

	/** The inversiones BO. */
	@Autowired
	private InversionesBO inversionesBO;

	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	@Autowired
	private SesionParametros sesionParametros;

	/** The ModuloPermiso BO. */
	@Autowired
	private ModuloPermisoBO moduloPermisoBO;

	/** The administrador permisos. */
	@Autowired
	protected AdministradorPermisos administradorPermisos;

	/** The analisis cartera manager. */
	@Autowired
	private AnalisisCarteraManager analisisCarteraManager;

	/** The dato firmado tipo. */
	@Value("${INVERSIONES.LICITACIONES.DATOFIRMADO}")
	private String dato;

	/** The cartera a consultar. */
	@Value("${INVERSIONES.RENDIMIENTO.RENTABILIDAD.DEFECTO:TOT}")
	private String carteraAConsultar;

	/** private the periodo. */
	@Value("${INVERSIONES.RENDIMIENTO.PERIODO.DEFECTO}")
	private String periodo;

	private static final String MENSAJE_ERROR_EXCEL = "Ocurrió un error y no se pudo realizar la descarga. Por favor, volvé a intentar";

	/**
	 * Recibe el tipo de operacion y obtiene las cuentas y los totales de la
	 * tenencia segun el tipo de operacion que se este realizando.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	@Override
	public Respuesta<InicioFondoView> inicioInversiones(InicioInversionesViewRequest request) {

		Respuesta<InicioFondoView> rtaValidacion = super.validate(request, new InicioFondoView());
		if (!rtaValidacion.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		String tipoDeOperacion = request.getTipoDeOperacion();
		LOGGER.info("Invocando al BO incio inversiones");
		Respuesta<InicioFondoDTO> respuestaBO = inversionesBO.inicioInversiones(sesionCliente.getCliente(),
				tipoDeOperacion);

		InicioFondoView inicioFondoView = new InicioFondoView();

		revisarSiDeshabilitarOperacionesBP(inicioFondoView);
		requiereMostrarRendimientoTenencia(inicioFondoView);

		inicioFondoView.setMensajeErrorCuentas(
				mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS).getMensaje());
		inicioFondoView.setErrorCuentasBloqueadas(respuestaBO.getRespuesta().isErrorCuentasBloqueadas());
		inicioFondoView.setErrorCuentasBPriv(respuestaBO.getRespuesta().isErrorCuentasBPriv());
		inicioFondoView.setNuevaApertura(respuestaBO.getRespuesta().isNuevaApertura());

		if (EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta())) {
			inicioFondoView.getBancaPersonal()
					.setCuentas(cuentasTituloDtoToView(respuestaBO.getRespuesta().getCuentasBancaPersonal()));
			List<CuentaTituloView> cuentasPrivadas = cuentasTituloDtoToView(
					respuestaBO.getRespuesta().getCuentasBancaPrivada());
			List<CuentaTituloView> cuentasPrivadasConOperativas = asignarCuentasOperativas(cuentasPrivadas);
			inicioFondoView.getBancaPrivada().setCuentas(cuentasPrivadasConOperativas);
			sesionParametros.setCuentasInversionesPFBpriv(inicioFondoView.getBancaPrivada().getCuentas());
			if (!validarCuentaOperativa(cuentasPrivadasConOperativas)
					&& TipoOperacionInversionesEnum.PLAZO_FIJO.getCodigoProducto().equalsIgnoreCase(tipoDeOperacion)) {
				inicioFondoView.setErrorCuentasBPriv(true);
			}
			inicioFondoView.setTieneCuentasMonetarias(respuestaBO.getRespuesta().getTieneCuentasMonetarias());

			if (!operacionSinCuentaTitulos(tipoDeOperacion)) {
				if (!("").equals(respuestaBO.getRespuesta().getMensajeErrorSinCuentas())) {
					if ("S".equalsIgnoreCase(respuestaBO.getRespuesta().getTieneCuentasMonetarias())) {
						inicioFondoView.setMensajeErrorSinCuentas(mensajeBO.obtenerMensajePorCodigo(
								operacionSinCuentaTitulo(tipoDeOperacion, inicioFondoView.getMensajeErrorSinCuentas()))
								.getMensaje());
					}

					if (inicioFondoView.getBancaPrivada().getCuentas().isEmpty()) {
						return respuestaFactory.crearRespuestaWarning(InicioFondoView.class, inicioFondoView,
								respuestaBO.getRespuesta().getMensajeErrorSinCuentas()
										.equals(CodigoMensajeConstantes.ERROR_SIN_CUENTA_OPERATIVA)
												? TipoError.ERROR_SIN_CUENTAS_OPERATIVAS
												: TipoError.ERROR_SIN_CUENTAS_TITULO,
								operacionSinCuentaTitulo(tipoDeOperacion,
										respuestaBO.getRespuesta().getMensajeErrorSinCuentas()),
								"");
					}
//					else {
//                        return respuestaFactory.crearRespuestaOk(InicioFondoView.class, inicioFondoView);
//                    }

				}
			}
		}

		if (TipoOperacionInversionesEnum.ANALISIS_CARTERA.getCodigoProducto().equals(request.getTipoDeOperacion())) {
			Respuesta<RentabilidadTotalView> rtaCartera = obtenerRentabilidadTotalAnalisisCartera();

			inicioFondoView.setRentabilidadTotal(rtaCartera.getRespuesta());
			if (EstadoRespuesta.OK.equals(rtaCartera.getEstadoRespuesta())) {
				return respuestaFactory.crearRespuestaOk(InicioFondoView.class, inicioFondoView);
			}
			// if (EstadoRespuesta.ERROR.equals(rtaCartera.getEstadoRespuesta())
			// &&
			// TipoError.WARNING_RENTABILIDAD_TOTAL.getDescripcion().equals(rtaCartera.getItemsMensajeRespuesta().get(0).getTipoError()))
			// {
			// inicioFondoView.setRentabilidadTotal(rtaCartera.getRespuesta());
			// return respuestaFactory.crearRespuestaWarning(inicioFondoView,
			// "", TipoError.WARNING_RENTABILIDAD_TOTAL,
			// CodigoMensajeConstantes.ERROR_ANALISIS_CARTERA);
			// } else if
			// (EstadoRespuesta.ERROR.equals(rtaCartera.getEstadoRespuesta()) &&
			// TipoError.WARNING_SIN_INVERSIONES.getDescripcion().equals(rtaCartera.getItemsMensajeRespuesta().get(0).getTipoError()))
			// {
			// inicioFondoView.setRentabilidadTotal(rtaCartera.getRespuesta());
			// return respuestaFactory.crearRespuestaWarning(inicioFondoView,
			// "", TipoError.WARNING_SIN_INVERSIONES,
			// CodigoMensajeConstantes.MENSAJE_CLIENTE_SIN_INVERSIONES);
			// }

			return respuestaFactory.crearRespuestaWarning(inicioFondoView, rtaCartera.getItemsMensajeRespuesta());
			// return
			// rtaCartera.getItemsMensajeRespuesta().get(0).getTipoError()
			// .equals(TipoError.WARNING_RENTABILIDAD_TOTAL_RTL.getDescripcion())
			// ? respuestaFactory.crearRespuestaWarning(inicioFondoView, "",
			// TipoError.WARNING_RENTABILIDAD_TOTAL_RTL,
			// CodigoMensajeConstantes.ERROR_ANALISIS_CARTERA)
			// : respuestaFactory.crearRespuestaWarning(inicioFondoView, "",
			// TipoError.WARNING_RENTABILIDAD_TOTAL_BPRIV,
			// CodigoMensajeConstantes.ERROR_ANALISIS_CARTERA);
		}

		Respuesta<TotalesTenenciaDTO> respuestaTotalesTenencia = inversionesBO
				.obtenerTotalesTenencia(sesionCliente.getCliente(), tipoDeOperacion, TipoBancaEnum.AMBAS_BANCAS);

		
		if((TipoOperacionInversionesEnum.TITULO_VALORES.getCodigoProducto()).equals(request.getTipoDeOperacion())) {
			//inicioFondoView=filtraRepatriacion(inicioFondoView);
			inicioFondoView=filtraRepatriacionBP(inicioFondoView);
		}
		
		/**
		 * Guardar cuentas Titulo banca personal en la sesion si la lista de cuentas
		 * tiene alguna cuenta
		 */
		if (!inicioFondoView.getBancaPersonal().getCuentas().isEmpty()) {
			sesionCliente.getCliente().setCuentasTituloRTL(inicioFondoView.getBancaPersonal().getCuentas());
		}

		if (EstadoRespuesta.OK.equals(respuestaTotalesTenencia.getEstadoRespuesta())) {
			cargarTotalesTenencia(inicioFondoView, respuestaTotalesTenencia.getRespuesta());

			grabarEstadisticaSegunOperacionYResultado(tipoDeOperacion, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(InicioFondoView.class, inicioFondoView);
		}
		if (EstadoRespuesta.WARNING.equals(respuestaTotalesTenencia.getEstadoRespuesta())) {
			cargarTotalesTenencia(inicioFondoView, respuestaTotalesTenencia.getRespuesta());
			cargarMensajeErrorParcial(inicioFondoView, respuestaTotalesTenencia.getRespuesta());
			inicioFondoView.setEsParcialBPRIV(respuestaTotalesTenencia.getRespuesta().isEsParcialBPRIV());
			inicioFondoView.setEsParcialRTL(respuestaTotalesTenencia.getRespuesta().isEsParcialRTL());
			grabarEstadisticaSegunOperacionYResultado(tipoDeOperacion,
					EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL);
			return respuestaFactory.crearRespuestaOk(InicioFondoView.class, inicioFondoView);

		} else {
			grabarEstadisticaSegunOperacionYResultado(tipoDeOperacion, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			String codigoMensajeError = CodigoMensajeConstantes.TOTALES_TENENCIA_INCOMPLETOS;
			return respuestaFactory.crearRespuestaWarning(InicioFondoView.class, inicioFondoView,
					TipoError.WARNING_TOTALES_TENENCIA, codigoMensajeError, "");
		}
	}

	//private InicioFondoView filtraRepatriacion(InicioFondoView inicioFondoView) {
	private InicioFondoView filtraRepatriacionBP(InicioFondoView inicioFondoView) {
		Cliente cliente = sesionCliente.getCliente();
		List<Cuenta> ctasTitBPRep=cliente.getCuentasTitBPRepatriacion();
		List<CuentaTituloView> ctasDTO = new ArrayList<CuentaTituloView>(inicioFondoView.getBancaPrivada().getCuentas());
		
		for(Cuenta ctaTitRep : ctasTitBPRep) {
			for(CuentaTituloView ctaDto : ctasDTO) {
				if(Integer.parseInt(ctaDto.getCuentaOperativa().replaceAll("/",""))==ctaTitRep.getCuentaOPRepatriacion()) {
					inicioFondoView.getBancaPrivada().getCuentas().remove(ctaDto);
				}
			}
		}
		return inicioFondoView;
	}

	/**
	 * Validar cuenta operativa.
	 *
	 * @param cuentasPrivadas the cuentas privadas
	 * @return true, if successful
	 */
	private boolean validarCuentaOperativa(List<CuentaTituloView> cuentasPrivadas) {
		for (CuentaTituloView cuentaTitulo : cuentasPrivadas) {
			if (cuentaTitulo.getCuentaOperativa() == null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Marca el view si debe mostrar el grafico y habilita o no los permisos sobre
	 * el link.
	 *
	 * @param inicioFondoView the inicio fondo view
	 */
	private void requiereMostrarRendimientoTenencia(InicioFondoView inicioFondoView) {
		boolean activoAccion = moduloPermisoBO
				.obtenerModuloPermisoPorAccion(AccionController.RENDIMIENTO_TENENCIA_INVERSIONES)
				.tienePermisoDeVisibilidad();
		if (activoAccion) {
			inicioFondoView.setMostrarRendimientoTenencia(activoAccion);
			administradorPermisos.addNewGrant(AccionController.RENDIMIENTO_TENENCIA_INVERSIONES);
		} else {
			administradorPermisos.removeGrant(AccionController.RENDIMIENTO_TENENCIA_INVERSIONES);
		}
	}

	/**
	 * Asignar cuentas operativas.
	 *
	 * @param cuentasPrivadas the cuentas privadas
	 * @return the list
	 */
	private List<CuentaTituloView> asignarCuentasOperativas(List<CuentaTituloView> cuentasPrivadas) {
		Cliente cliente = sesionCliente.getCliente();
		List<CuentaBancaPrivada> cuentasCliente = cliente.getCuentasBancaPrivada();
		for (CuentaTituloView cuentaTituloView : cuentasPrivadas) {
			for (CuentaBancaPrivada cuentaBancaPrivada : cuentasCliente) {
				if (cuentaTituloView.getNroCuenta()
						.equals(cuentaBancaPrivada.getCuentaTitulo().getNroCuentaProducto())) {
					cuentaTituloView.setSucursal(
							StringUtils.stripStart(cuentaBancaPrivada.getCuentaOperativa().getNroSucursal(), "0"));
					cuentaTituloView.setIntervinientes(cuentaBancaPrivada.getCuentaOperativa().getIntervinientes());
					cuentaTituloView.setCuentaOperativa(ISBANStringUtils
							.formatearNumeroCuenta(cuentaBancaPrivada.getCuentaOperativa().getNroCuentaProducto()));
				}
			}
		}
		return cuentasPrivadas;
	}

	/**
	 * Valida la operacion que puede efectuarse sin tener una cuenta titulo.
	 *
	 * @param tipoOperacion the tipo operacion
	 * @return true, if successful
	 */
	private boolean operacionSinCuentaTitulos(String tipoOperacion) {
		return TipoOperacionInversionesEnum.TENENCIA_VALUADA.getCodigoProducto().equals(tipoOperacion)
				|| TipoOperacionInversionesEnum.PLAZO_FIJO.getCodigoProducto().equals(tipoOperacion)
				|| TipoOperacionInversionesEnum.ANALISIS_CARTERA.getCodigoProducto().equals(tipoOperacion);
	}

	/**
	 * Guarda la estadistica correspondiente segun la operacion que se esta
	 * realizando.
	 *
	 * @param tipoDeOperacion the tipo de operacion
	 * @param resultado       the resultado
	 */
	private void grabarEstadisticaSegunOperacionYResultado(String tipoDeOperacion, String resultado) {
		if (TipoOperacionInversionesEnum.FONDO_COMUN_DE_INVERSION.getCodigoProducto().equals(tipoDeOperacion)) {
			estadisticaManager.add(EstadisticasConstants.TOTALES_TENENCIA_FONDO_COMUN_DE_INVERSION, resultado);
			return;
		}
		if (TipoOperacionInversionesEnum.PLAZO_FIJO.getCodigoProducto().equals(tipoDeOperacion)) {
			estadisticaManager.add(EstadisticasConstants.TOTALES_TENENCIA_PLAZO_FIJO, resultado);
			return;
		}
		if (TipoOperacionInversionesEnum.TENENCIA_VALUADA.getCodigoProducto().equals(tipoDeOperacion)) {
			estadisticaManager.add(EstadisticasConstants.TENENCIA_CONSOLIDADA_TOTALES_CABECERA, resultado);
			return;
		}
	}

	/**
	 * Operacion sin cuenta titulo.
	 *
	 * @param tipoOperacion          the tipo operacion
	 * @param mensajeSinCuentaTitulo the mensaje sin cuenta titulo
	 * @return the string
	 */
	private String operacionSinCuentaTitulo(String tipoOperacion, String mensajeSinCuentaTitulo) {

		if (CodigoMensajeConstantes.ERROR_SIN_CUENTA_OPERATIVA.equals(mensajeSinCuentaTitulo)) {
			return mensajeSinCuentaTitulo;
		}

		if (TipoOperacionInversionesEnum.FONDO_COMUN_DE_INVERSION.getCodigoProducto().equals(tipoOperacion)) {

			mensajeSinCuentaTitulo = CodigoMensajeConstantes.ERROR_SIN_CUENTA_TITULOS_FONDOS;

		} else if (TipoOperacionInversionesEnum.TITULO_VALORES.getCodigoProducto().equals(tipoOperacion)) {

			mensajeSinCuentaTitulo = CodigoMensajeConstantes.ERROR_SIN_CUENTA_TITULOS_TITULOS_VALORES;
		} else if (TipoOperacionInversionesEnum.TENENCIA_VALUADA.getCodigoProducto().equals(tipoOperacion)) {

			mensajeSinCuentaTitulo = CodigoMensajeConstantes.MENSAJE_WARNING_INVERSIONES_TENENCIA_CONSOLIDADA;
		}

		return mensajeSinCuentaTitulo;
	}

	/**
	 * Setea los totales de la tenencia en el view.
	 *
	 * @param inicioFondoView    the inicio fondo view
	 * @param totalesTenenciaDTO the totales tenencia DTO
	 */
	private void cargarTotalesTenencia(InicioFondoView inicioFondoView, TotalesTenenciaDTO totalesTenenciaDTO) {
		inicioFondoView.setTenenciaBPersDolares(totalesTenenciaDTO.getTenenciaBPersDolares());
		inicioFondoView.setTenenciaBPersPesos(totalesTenenciaDTO.getTenenciaBPersPesos());
		inicioFondoView.setTenenciaBPrivDolares(totalesTenenciaDTO.getTenenciaBPrivDolares());
		inicioFondoView.setTenenciaBPrivPesos(totalesTenenciaDTO.getTenenciaBPrivPesos());
		return;
	}

	/**
	 * Cargar mensaje error parcial.
	 *
	 * @param inicioFondoView    the inicio fondo view
	 * @param totalesTenenciaDTO the totales tenencia DTO
	 */
	private void cargarMensajeErrorParcial(InicioFondoView inicioFondoView, TotalesTenenciaDTO totalesTenenciaDTO) {
		if (totalesTenenciaDTO.isEsParcialBPRIV() || totalesTenenciaDTO.isEsParcialRTL()) {
			String mensaje = mensajeBO.obtenerMensajePorCodigo(INVERSIONES_CONSOLIDADO_ERROR_PARCIAL).getMensaje();
			inicioFondoView.setMensajeErrorParcial(mensaje);
		}
	}

	/**
	 * Obtener tenencia Consolidada por producto.
	 *
	 * @param estadisticasTotales the estadisticas totales
	 * @return the respuesta
	 */
	@Override
	public Respuesta<TenenciaConsolidadaView> obtenerTenenciaConsolidadaPorProducto(
			TenenciaPorProductoInView estadisticasTotales) {

		Respuesta<String> respuestaLegales = legalBO.buscarLegal(CodigoMensajeConstantes.LEGALES_TENENCIA_CONSOLIDADA);

		if (!respuestaLegales.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			estadisticaManager.add(EstadisticasConstants.ESTADISTICA_TENENCIA_CONSOLIDADA_GENERAL,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.TENENCIA_RESCATE_FONDO_FALLO_GENERICO);
		}

		Respuesta<TenenciaConsolidadaPorProductoDTO> rtaTenencia = inversionesBO
				.obtenerTenenciaConsolidadaPorProducto(sesionCliente.getCliente());
		Respuesta<TenenciaConsolidadaView> rta = new Respuesta<TenenciaConsolidadaView>();
		String estadisticasGrilla;
		TenenciaConsolidadaView tenenciaConsolidadaView = new TenenciaConsolidadaView();
		if (EstadoRespuesta.ERROR.equals(rtaTenencia.getEstadoRespuesta())) {
			estadisticasGrilla = EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR;
			rta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.TENENCIA_RESCATE_FONDO_FALLO_GENERICO);
		} else {
			tenenciaConsolidadaView = createTenenciaView(rtaTenencia.getRespuesta());
			tenenciaConsolidadaView.setLegales(respuestaLegales.getRespuesta());
			if (tenenciaConsolidadaView.isMostrarTotales()) {
				estadisticasGrilla = EstadisticasConstants.CODIGO_ESTADISTICAS_OK;
				rta = respuestaFactory.crearRespuestaOk(TenenciaConsolidadaView.class, tenenciaConsolidadaView);
			} else {
				estadisticasGrilla = EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL;
				List<ItemMensajeRespuesta> listItemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
				ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
				itemMensajeRespuesta
						.setMensaje(mensajeBO
								.obtenerMensajePorCodigo(
										CodigoMensajeConstantes.MENSAJE_WARNING_INVERSIONES_TENENCIA_CONSOLIDADA)
								.getMensaje());
				listItemMensajeRespuesta.add(itemMensajeRespuesta);
				rta.setItemMensajeRespuesta(listItemMensajeRespuesta);
				rta.setEstadoRespuesta(EstadoRespuesta.WARNING);
				rta.setRespuesta(tenenciaConsolidadaView);
			}
		}
		// TODO Eliminar cuando funcione CUSTODIA.
		tenenciaConsolidadaView = eliminarCustodiaLista(tenenciaConsolidadaView);
		rta=validarRepatriacion(sesionCliente.getCliente(),rta);
		grabarEstadisticasTenenciaConsolidada(estadisticasTotales.getEstadisticas().trim(), estadisticasGrilla);
		return rta;
	}

	private Respuesta<TenenciaConsolidadaView> validarRepatriacion(Cliente cliente,
			Respuesta<TenenciaConsolidadaView> rta) {
		
		List<Cuenta> ctasTitRtlRep=cliente.getCuentasTitRtlRepatriacion();
//		List<Cuenta> ctasTitBpRep=cliente.getCuentasTitBPRepatriacion();
		int cantCuentasRep=0;
		for(Cuenta cta : cliente.getCuentas()) {
			if(cta.isRepatriacion()) {
				cantCuentasRep++;
			}
		}
//		for(Cuenta cta : cliente.getCuentasPrivadas()) {
//			if(cta.isRepatriacion()) {
//				cantCuentasRep++;
//			}
//		}
		
//		if(cantCuentasRep>(ctasTitRtlRep.size()+ctasTitBpRep.size())){
		if(cantCuentasRep>ctasTitRtlRep.size()){
			rta.getRespuesta().setHabilitadaCtaTitRep(true);
		}
		return rta;
	}

	/**
	 * Eliminar custodia lista.
	 *
	 * @param lista the lista
	 * @return the tenencia consolidada view
	 */
	private static TenenciaConsolidadaView eliminarCustodiaLista(TenenciaConsolidadaView lista) {
		for (TenenciaProductosPorMonedaView tenenciaPorProducto : lista.getListaTenencias()) {
			CollectionUtils.filter(tenenciaPorProducto.getListaTenenciaProductos(), new Predicate() {
				@Override
				public boolean evaluate(Object object) {
					TenenciaPorProductoView tenencia = (TenenciaPorProductoView) object;
					if ("CUS".equals(tenencia.getProducto()) && "0,00".equals(tenencia.getTenenciaValuadaHoy())) {
						return false;
					}
					return true;
				}
			});
		}
		return lista;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.comun.manager.
	 * InversionesManager#obtenerTenenciaConsolidadaPorProductoBPriv(ar.com.
	 * santanderrio.obp.servicios.inversiones.comun.view. TenenciaPorProductoInView)
	 */
	@Override
	public Respuesta<TenenciaConsolidadaBPrivView> obtenerTenenciaConsolidadaPorProductoBPriv(
			TenenciaPorProductoInView estadisticasTotales) {

		TenenciaProductosMonedaDTO tenenciaProductosPesosDTO = new TenenciaProductosMonedaDTO();
		TenenciaProductosMonedaDTO tenenciaProductosDolaresDTO = new TenenciaProductosMonedaDTO();

		Respuesta<String> respuestaLegales = legalBO
				.buscarLegal(CodigoMensajeConstantes.LEGALES_TENENCIA_CONSOLIDADA_BPRIV);

		if (!EstadoRespuesta.OK.equals(respuestaLegales.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.TENENCIA_CONSOLIDADA_GENERAL,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.TENENCIA_RESCATE_FONDO_FALLO_GENERICO);
		}

		Respuesta<TenenciaConsolidadaBPrivDTO> rtaTenencia = inversionesBO
				.obtenerTenenciaConsolidadaPorProductoBPriv(sesionCliente.getCliente());
		Respuesta<TenenciaConsolidadaBPrivView> rta = new Respuesta<TenenciaConsolidadaBPrivView>();
		String estadisticasGrilla;
		TenenciaConsolidadaBPrivView tenenciaConsolidadaBPrivView = new TenenciaConsolidadaBPrivView();
		if (EstadoRespuesta.ERROR.equals(rtaTenencia.getEstadoRespuesta())) {
			estadisticasGrilla = EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR;
			rta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.TENENCIA_RESCATE_FONDO_FALLO_GENERICO);
		} else {
			tenenciaConsolidadaBPrivView = createTenenciaDtoToView(rtaTenencia.getRespuesta());
			tenenciaConsolidadaBPrivView.setLegales(respuestaLegales.getRespuesta());

			if (EstadoRespuesta.OK.equals(rtaTenencia.getEstadoRespuesta())) {
				estadisticasGrilla = EstadisticasConstants.CODIGO_ESTADISTICAS_OK;
				obtenerPorcentajeProductoTenencia(tenenciaProductosPesosDTO, tenenciaProductosDolaresDTO, rtaTenencia);

				rta = respuestaFactory.crearRespuestaOk(TenenciaConsolidadaBPrivView.class,
						tenenciaConsolidadaBPrivView);
			} else {
				estadisticasGrilla = EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL;
				rta = respuestaFactory.crearRespuestaWarning(TenenciaConsolidadaBPrivView.class,
						tenenciaConsolidadaBPrivView, TipoError.WARNING_PARCIAL_INVERSIONES_CONSOLIDADO,
						CodigoMensajeConstantes.MENSAJE_WARNING_INVERSIONES_TENENCIA_CONSOLIDADA, "");
			}
		}
		grabarEstadisticasTenenciaConsolidadaBPriv(estadisticasTotales.getEstadisticas().trim(), estadisticasGrilla);
		
		return rta;
	}

	

	/**
	 * Obtener porcentaje producto tenencia.
	 *
	 * @param tenenciaProductosPesosDTO   the tenencia productos pesos DTO
	 * @param tenenciaProductosDolaresDTO the tenencia productos dolares DTO
	 * @param rtaTenencia                 the rta tenencia
	 */
	private void obtenerPorcentajeProductoTenencia(TenenciaProductosMonedaDTO tenenciaProductosPesosDTO,
			TenenciaProductosMonedaDTO tenenciaProductosDolaresDTO,
			Respuesta<TenenciaConsolidadaBPrivDTO> rtaTenencia) {

		for (TenenciaPorCuentaBPrivDTO tenenciaPorCuentaBPriv : rtaTenencia.getRespuesta()
				.getListaTenenciaPorCuenta()) {

			for (TenenciaPorProductoBPrivDTO tenenciaDolaresBPriv : tenenciaPorCuentaBPriv.getTenenciaDolares()
					.getListaTenenciaProductos()) {
				tenenciaProductosDolaresDTO
						.acumularTotaltotalTenenciaValuadaHoyParcial(tenenciaDolaresBPriv.getTenenciaValuadaHoy());
			}
			for (TenenciaPorProductoBPrivDTO tenenciaPesosBPriv : tenenciaPorCuentaBPriv.getTenenciaPesos()
					.getListaTenenciaProductos()) {
				tenenciaProductosPesosDTO
						.acumularTotaltotalTenenciaValuadaHoyParcial(tenenciaPesosBPriv.getTenenciaValuadaHoy());
			}

			obtenerPorcentajeTenenciaDolares(tenenciaProductosDolaresDTO, tenenciaPorCuentaBPriv);
			redondearPorcentajeBPriv(tenenciaPorCuentaBPriv.getTenenciaDolares().getListaTenenciaProductos());

			obtenerPorcentajeTenenciaPesos(tenenciaProductosPesosDTO, tenenciaPorCuentaBPriv);
			redondearPorcentajeBPriv(tenenciaPorCuentaBPriv.getTenenciaPesos().getListaTenenciaProductos());

			tenenciaProductosPesosDTO = new TenenciaProductosMonedaDTO();
			tenenciaProductosDolaresDTO = new TenenciaProductosMonedaDTO();

		}
	}

	/**
	 * Obtener porcentaje tenencia pesos.
	 *
	 * @param tenenciaProductosPesosDTO the tenencia productos pesos DTO
	 * @param tenenciaPorCuentaBPriv    the tenencia por cuenta B priv
	 */
	private void obtenerPorcentajeTenenciaPesos(TenenciaProductosMonedaDTO tenenciaProductosPesosDTO,
			TenenciaPorCuentaBPrivDTO tenenciaPorCuentaBPriv) {
		for (TenenciaPorProductoBPrivDTO tenenciaPesosBPriv : tenenciaPorCuentaBPriv.getTenenciaPesos()
				.getListaTenenciaProductos()) {

			if ("-".equals(tenenciaPesosBPriv.getTenenciaValuadaHoy())) {
				tenenciaPesosBPriv.setTenenciaValuadaHoy("0");
			}

			BigDecimal tenenciaValuadaHoy = new BigDecimal(tenenciaPesosBPriv.getTenenciaValuadaHoy());
			BigDecimal tenenciaValuadaHoyTotal = new BigDecimal(
					tenenciaProductosPesosDTO.getTotalTenenciaValuadaHoyParcial());
			tenenciaPesosBPriv.setPorcentaje(tenenciaProductosPesosDTO
					.obtenerPorcentaje(tenenciaValuadaHoy, tenenciaValuadaHoyTotal).intValue());
		}
	}

	/**
	 * Obtener porcentaje tenencia dolares.
	 *
	 * @param tenenciaProductosDolaresDTO the tenencia productos dolares DTO
	 * @param tenenciaPorCuentaBPriv      the tenencia por cuenta B priv
	 */
	private void obtenerPorcentajeTenenciaDolares(TenenciaProductosMonedaDTO tenenciaProductosDolaresDTO,
			TenenciaPorCuentaBPrivDTO tenenciaPorCuentaBPriv) {
		for (TenenciaPorProductoBPrivDTO tenenciaDolaresBPriv : tenenciaPorCuentaBPriv.getTenenciaDolares()
				.getListaTenenciaProductos()) {

			if ("-".equals(tenenciaDolaresBPriv.getTenenciaValuadaHoy())) {
				tenenciaDolaresBPriv.setTenenciaValuadaHoy("0");
			}

			BigDecimal tenenciaValuadaHoy = new BigDecimal(tenenciaDolaresBPriv.getTenenciaValuadaHoy());
			BigDecimal tenenciaValuadaHoyTotal = new BigDecimal(
					tenenciaProductosDolaresDTO.getTotalTenenciaValuadaHoyParcial());
			tenenciaDolaresBPriv.setPorcentaje(tenenciaProductosDolaresDTO
					.obtenerPorcentaje(tenenciaValuadaHoy, tenenciaValuadaHoyTotal).intValue());
		}
	}

	/**
	 * Me fijo si los porcentajes redondeados dan 100 en total, si no dan 100 ajusto
	 * el valor del major porcentaje para que de 100% el total.
	 *
	 * @param listaTenenciaProductos the lista tenencia productos
	 * @return lista de tenencias con los porcentajes actualizados
	 */
	public List<TenenciaPorProductoBPrivDTO> redondearPorcentajeBPriv(
			List<TenenciaPorProductoBPrivDTO> listaTenenciaProductos) {
		int sumatotal = 0;
		TenenciaPorProductoBPrivDTO tenenciaMajor = listaTenenciaProductos.get(0);
		for (TenenciaPorProductoBPrivDTO tenencia : listaTenenciaProductos) {
			sumatotal = sumatotal + tenencia.getPorcentaje();
			if (tenencia.getPorcentaje() > tenenciaMajor.getPorcentaje()) {
				tenenciaMajor = tenencia;
			}
		}
		if (sumatotal != 100 && sumatotal != 0) {
			int dif = 100 - sumatotal;
			tenenciaMajor.setPorcentaje(tenenciaMajor.getPorcentaje() + dif);
		}

		return listaTenenciaProductos;
	}

	/**
	 * Grabar estadisticas tenencia consolidada B priv.
	 *
	 * @param estadisticaTotales the estadistica totales
	 * @param estadisticaGrilla  the estadistica grilla
	 */
	private void grabarEstadisticasTenenciaConsolidadaBPriv(String estadisticaTotales, String estadisticaGrilla) {

		estadisticaManager.add(EstadisticasConstants.TENENCIA_CONSOLIDADA_GRILLA, estadisticaGrilla);

		String estadoEstadisticaGlobal = "";

		if (OK.equals(estadisticaTotales) && OK.equals(estadisticaGrilla)) {
			estadoEstadisticaGlobal = EstadisticasConstants.CODIGO_ESTADISTICAS_OK;
		}

		if (ERROR.equals(estadisticaTotales) && ERROR.equals(estadisticaGrilla)
				|| (PARCIAL.equals(estadisticaTotales) && ERROR.equals(estadisticaGrilla))) {
			estadoEstadisticaGlobal = EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR;
		}

		if ((PARCIAL.equals(estadisticaTotales) && PARCIAL.equals(estadisticaGrilla))
				|| (PARCIAL.equals(estadisticaTotales) && OK.equals(estadisticaGrilla))
				|| (OK.equals(estadisticaTotales) && PARCIAL.equals(estadisticaGrilla))
				|| (OK.equals(estadisticaTotales) && ERROR.equals(estadisticaGrilla))
				|| (ERROR.equals(estadisticaTotales) && PARCIAL.equals(estadisticaGrilla))
				|| (ERROR.equals(estadisticaTotales) && OK.equals(estadisticaGrilla))) {
			estadoEstadisticaGlobal = EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL;
		}

		estadisticaManager.add(EstadisticasConstants.TENENCIA_CONSOLIDADA_GENERAL, estadoEstadisticaGlobal);
	}

	/**
	 * Creates the tenencia dto to view.
	 *
	 * @param respuesta the respuesta
	 * @return the tenencia consolidada B priv view
	 */
	private TenenciaConsolidadaBPrivView createTenenciaDtoToView(TenenciaConsolidadaBPrivDTO respuesta) {

		TenenciaConsolidadaBPrivView tenenciaConsolidadaBPrivView = new TenenciaConsolidadaBPrivView();

		for (TenenciaPorCuentaBPrivDTO tenenciaPorCuenta : respuesta.getListaTenenciaPorCuenta()) {
			String totalTenenciaValuadaCostoDolares = GUION;
			String totalResultadoDolares = GUION;
			String totalTenenciaValuadaCostoPesos = GUION;
			String totalResultadoPesos = GUION;

			for (TenenciaPorProductoBPrivDTO tenenciaPorProductoDolares : tenenciaPorCuenta.getTenenciaDolares()
					.getListaTenenciaProductos()) {

				if (!GUION.equals(tenenciaPorProductoDolares.getTenenciaValuadaCosto())) {
					if (GUION.equals(totalTenenciaValuadaCostoDolares)) {
						totalTenenciaValuadaCostoDolares = "0";
					}
					BigDecimal valorASumar = new BigDecimal(tenenciaPorProductoDolares.getTenenciaValuadaCosto());
					BigDecimal totalTenenciaValuadaCostoBigDecimal = new BigDecimal(totalTenenciaValuadaCostoDolares);
					totalTenenciaValuadaCostoDolares = valorASumar.add(totalTenenciaValuadaCostoBigDecimal).toString();
				}

				if (!GUION.equals(tenenciaPorProductoDolares.getResultado())) {
					if (GUION.equals(totalResultadoDolares)) {
						totalResultadoDolares = "0";
					}
					BigDecimal valorASumar = new BigDecimal(tenenciaPorProductoDolares.getResultado());
					BigDecimal totalResultadoBigDecimal = new BigDecimal(totalResultadoDolares);
					totalResultadoDolares = valorASumar.add(totalResultadoBigDecimal).toString();
				}

				if (GUION.equals(tenenciaPorProductoDolares.getTenenciaValuadaHoy())) {
					tenenciaPorCuenta.getTenenciaDolares().setMostrarGraficos(false);
				}

				tenenciaPorCuenta.getTenenciaDolares().setTotalTenenciaValuadaCosto(totalTenenciaValuadaCostoDolares);
				tenenciaPorCuenta.getTenenciaDolares().setTotalResultado(totalResultadoDolares);
			}
			// Pesos
			for (TenenciaPorProductoBPrivDTO tenenciaPorProductoPesos : tenenciaPorCuenta.getTenenciaPesos()
					.getListaTenenciaProductos()) {

				if (!GUION.equals(tenenciaPorProductoPesos.getTenenciaValuadaCosto())) {
					if (GUION.equals(totalTenenciaValuadaCostoPesos)) {
						totalTenenciaValuadaCostoPesos = "0";
					}
					BigDecimal valorASumar = new BigDecimal(tenenciaPorProductoPesos.getTenenciaValuadaCosto());
					BigDecimal totalTenenciaValuadaCostoBigDecimal = new BigDecimal(totalTenenciaValuadaCostoPesos);
					totalTenenciaValuadaCostoPesos = valorASumar.add(totalTenenciaValuadaCostoBigDecimal).toString();
				}

				if (!GUION.equals(tenenciaPorProductoPesos.getResultado())) {
					if (GUION.equals(totalResultadoPesos)) {
						totalResultadoPesos = "0";
					}
					BigDecimal valorASumar = new BigDecimal(tenenciaPorProductoPesos.getResultado());
					BigDecimal totalResultadoBigDecimal = new BigDecimal(totalResultadoPesos);
					totalResultadoPesos = valorASumar.add(totalResultadoBigDecimal).toString();
				}

				if (GUION.equals(tenenciaPorProductoPesos.getTenenciaValuadaHoy())) {
					tenenciaPorCuenta.getTenenciaPesos().setMostrarGraficos(false);
				}

			}
			tenenciaPorCuenta.getTenenciaPesos().setTotalTenenciaValuadaCosto(totalTenenciaValuadaCostoPesos);
			tenenciaPorCuenta.getTenenciaPesos().setTotalResultado(totalResultadoPesos);
		}
		sesionParametros.setListaTenenciaConsolidadaBP(respuesta.getListaTenenciaPorCuenta());
		try {
			BeanUtils.copyProperties(tenenciaConsolidadaBPrivView, respuesta);
		} catch (IllegalAccessException e) {
			LOGGER.error("Error creando DTO", e);
		} catch (InvocationTargetException e) {
			LOGGER.error("Error creando DTO", e);
		}
		return tenenciaConsolidadaBPrivView;
	}

	/**
	 * Retorna la tenencia consolidada en Pesos y Dolares para la tarjeta home.
	 *
	 * @param requestView the request view
	 * @return the respuesta
	 */
	@Override
	public Respuesta<TarjetaTenenciaConsolidadaView> obtenerTenenciaConsolidadaHome(
			InicioInversionesViewRequest requestView, TipoBancaEnum banca) {
		LOGGER.info("Obtener Cajas de tenenecias con tipo operacion {} y banca {}.", requestView.getTipoDeOperacion(),
				banca);
		Respuesta<TarjetaTenenciaConsolidadaView> respuestaBO = inversionesBO
				.obtenerTotalesTenenciaHome(sesionCliente.getCliente(), banca);

//		if (EstadoRespuesta.ERROR.equals(respuestaBO.getEstadoRespuesta())) {
//			TarjetaTenenciaConsolidadaView tarjetaTenenciaConsolidadaView = createViewTarjetaHomeError();
//			return respuestaFactory.crearRespuestaOk(TarjetaTenenciaConsolidadaView.class,
//					tarjetaTenenciaConsolidadaView);
//		}
//
//		if (EstadoRespuesta.WARNING.equals(respuestaBO.getEstadoRespuesta())) {
//			TarjetaTenenciaConsolidadaView tarjetaTenenciaConsolidadaView = createViewTarjetaHomeWarninig(
//					respuestaBO.getRespuesta());
//			return respuestaFactory.crearRespuestaWarning(tarjetaTenenciaConsolidadaView, "",
//					TipoError.respuestaBO, "");
//		}
//
//		TarjetaTenenciaConsolidadaView tarjetaTenenciaConsolidadaView = createViewTarjetaHome(
//				respuestaBO.getRespuesta());
////        TarjetaTenenciaConsolidadaView tarjetaTenenciaConsolidadaView = createViewTarjetaHomeError();
		return respuestaBO;
	}

	/**
	 * Crea un objeto TenenciaConsolidadaView con las tenencias por producto y las
	 * tenencias reexpresadas cargadas.
	 *
	 * @param tenenciaDTO the tenencia DTO
	 * @return the tenencia consolidada view
	 */
	private TenenciaConsolidadaView createTenenciaView(TenenciaConsolidadaPorProductoDTO tenenciaDTO) {
		TenenciaConsolidadaView tenenciaConsolidadaView = new TenenciaConsolidadaView();
		// tenenciaConsolidadaView.setMensaje(tenenciaDTO.getMensajeWarning());
		tenenciaConsolidadaView.setResultadoOkDolares(tenenciaDTO.isResultadoOkDolares());
		tenenciaConsolidadaView.setResultadoOkPesos(tenenciaDTO.isResultadoOkPesos());

		List<TenenciaProductosPorMonedaView> listTenenciaPorMoneda = new ArrayList<TenenciaProductosPorMonedaView>();

		TenenciaProductosPorMonedaView tenenciaPesos = new TenenciaProductosPorMonedaView();
		TenenciaProductosPorMonedaView tenenciaDolares = new TenenciaProductosPorMonedaView();

		if (tenenciaDTO.getResultadoPesos() != null) {
			tenenciaPesos.setTotalResultado(ISBANStringUtils.formatearSaldoSinAbs(tenenciaDTO.getResultadoPesos()));
		} else {
			tenenciaPesos.setTotalResultado(GUION);
		}

		if (tenenciaDTO.getTotalTenenciaValuadaCostoPesos() != null) {
			tenenciaPesos.setTotalTenenciaValuadaCosto(
					ISBANStringUtils.formatearSaldoSinAbs(tenenciaDTO.getTotalTenenciaValuadaCostoPesos()));
		} else {
			tenenciaPesos.setTotalTenenciaValuadaCosto(GUION);
		}

		tenenciaPesos.setTotalTenenciaValuadaHoy(
				ISBANStringUtils.formatearSaldoSinAbs(tenenciaDTO.getTotalTenenciaValuadaHoyPesos()));
		tenenciaPesos.setMoneda("ARS");

		if (tenenciaDTO.getResultadoDolares() != null) {
			tenenciaDolares.setTotalResultado(ISBANStringUtils.formatearSaldoSinAbs(tenenciaDTO.getResultadoDolares()));
		} else {
			tenenciaDolares.setTotalResultado(GUION);
		}

		if (tenenciaDTO.getTotalTenenciaValuadaCostoDolares() != null) {
			tenenciaDolares.setTotalTenenciaValuadaCosto(
					ISBANStringUtils.formatearSaldoSinAbs(tenenciaDTO.getTotalTenenciaValuadaCostoDolares()));
		} else {
			tenenciaDolares.setTotalTenenciaValuadaCosto(GUION);
		}
		tenenciaDolares.setTotalTenenciaValuadaHoy(
				ISBANStringUtils.formatearSaldoSinAbs(tenenciaDTO.getTotalTenenciaValuadaHoyDolares()));
		tenenciaDolares.setMoneda("USD");

		for (TenenciaPorProductoDTO tenencia : tenenciaDTO.getListaTenencia()) {
			TenenciaPorProductoView tenenciaProductoPesos = new TenenciaPorProductoView();
			TenenciaPorProductoView tenenciaProductoDolares = new TenenciaPorProductoView();
			tenenciaProductoDolares.setProducto(TipoProductoEnum.fromCodigoString(tenencia.getProducto()).getCodigo());
			tenenciaProductoPesos.setProducto(TipoProductoEnum.fromCodigoString(tenencia.getProducto()).getCodigo());
			// TODO borrar IF cuando terminen las pruebas de tenencia
			// consolidada o cuando funcione el servicio.
			if (tenencia.getProducto().trim().equals(TipoProductoEnum.CUSTODIA.getCodigo())) {
				tenencia.setCodigoError("0");
			}
			if (tenencia.getCodigoError().trim().equals(CODIGO_ERROR_OK)) {
				if (tenencia.getResultadoDolares() != null) {
					tenenciaProductoDolares
							.setResultado(ISBANStringUtils.formatearSaldoSinAbs(tenencia.getResultadoDolares()));
				} else {
					tenenciaProductoDolares.setResultado("-");
				}

				if (tenencia.getTenenciaValuadaCostoDolares() != null) {
					tenenciaProductoDolares.setTenenciaValuadaCosto(
							ISBANStringUtils.formatearSaldoSinAbs(tenencia.getTenenciaValuadaCostoDolares()));
				} else {
					tenenciaProductoDolares.setTenenciaValuadaCosto(GUION);
				}
				tenenciaProductoDolares.setTenenciaValuadaHoy(
						ISBANStringUtils.formatearSaldoSinAbs(tenencia.getTenenciaValuadaHoyDolares()));
				tenenciaProductoDolares.setPorcentaje(
						tenenciaProductoDolares.obtenerPorcentaje(tenencia.getTenenciaValuadaHoyDolares(),
								tenenciaDTO.getTotalTenenciaValuadaHoyDolares()).intValue());
				if (tenencia.getResultadoPesos() != null) {
					tenenciaProductoPesos
							.setResultado(ISBANStringUtils.formatearSaldoSinAbs(tenencia.getResultadoPesos()));
				} else {
					tenenciaProductoPesos.setResultado("-");
				}

				if (tenencia.getTenenciaValuadaCostoPesos() != null) {
					tenenciaProductoPesos.setTenenciaValuadaCosto(
							ISBANStringUtils.formatearSaldoSinAbs(tenencia.getTenenciaValuadaCostoPesos()));
				} else {
					tenenciaProductoPesos.setTenenciaValuadaCosto(GUION);
				}
				tenenciaProductoPesos.setTenenciaValuadaHoy(
						ISBANStringUtils.formatearSaldoSinAbs(tenencia.getTenenciaValuadaHoyPesos()));
				tenenciaProductoPesos
						.setPorcentaje(tenenciaProductoPesos.obtenerPorcentaje(tenencia.getTenenciaValuadaHoyPesos(),
								tenenciaDTO.getTotalTenenciaValuadaHoyPesos()).intValue());
			} else {
				tenenciaProductoDolares.setResultado("-");
				tenenciaProductoDolares.setTenenciaValuadaCosto("-");
				tenenciaProductoDolares.setTenenciaValuadaHoy("-");
				tenenciaProductoPesos.setResultado("-");
				tenenciaProductoPesos.setTenenciaValuadaCosto("-");
				tenenciaProductoPesos.setTenenciaValuadaHoy("-");
				tenenciaConsolidadaView.setMostrarTotales(false);

			}
			tenenciaPesos.getListaTenenciaProductos().add(tenenciaProductoPesos);
			tenenciaDolares.getListaTenenciaProductos().add(tenenciaProductoDolares);
		}

		tenenciaPesos.setListaTenenciaProductos(porcentajeOK(tenenciaPesos.getListaTenenciaProductos()));
		tenenciaDolares.setListaTenenciaProductos(porcentajeOK(tenenciaDolares.getListaTenenciaProductos()));

		listTenenciaPorMoneda.add(tenenciaPesos);
		listTenenciaPorMoneda.add(tenenciaDolares);
		tenenciaConsolidadaView.setListaTenencias(listTenenciaPorMoneda);
		sesionParametros.setListaTenenciaConsolidadaRTL(listTenenciaPorMoneda);
		tenenciaConsolidadaView = cargarTenenciaReexp(tenenciaConsolidadaView, tenenciaDTO);

		for (TenenciaProductosPorMonedaView tenenciaPorProducto : tenenciaConsolidadaView.getListaTenencias()) {
			for (TenenciaPorProductoView tenencia : tenenciaPorProducto.getListaTenenciaProductos()) {
				if ("CUS".equals(tenencia.getProducto())) {
					tenencia.setTenenciaValuadaCosto("-");
					tenencia.setResultado("-");
				}
			}
		}
		return tenenciaConsolidadaView;
	}

	/**
	 * Revisar si deshabilitar operaciones BP.
	 *
	 * @param inicioFondoView the inicio fondo view
	 */
	private void revisarSiDeshabilitarOperacionesBP(InicioFondoView inicioFondoView) {
		boolean activoAccion = moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.BANCA_PRIVADA)
				.tienePermisoDeVisibilidad();
		if (!activoAccion) {
			inicioFondoView.setDeshabilitarBP(true);
		}
	}

	/**
	 * Carga las tenencias reexpresadas al objeto TenenciaConsolidadaView para cada
	 * moneda al objeto que se devuelve.
	 *
	 * @param tenenciaConsolidadaView the tenencia consolidada view
	 * @param tenenciaDTO             the tenencia DTO
	 * @return the tenencia consolidada view
	 */
	private TenenciaConsolidadaView cargarTenenciaReexp(TenenciaConsolidadaView tenenciaConsolidadaView,
			TenenciaConsolidadaPorProductoDTO tenenciaDTO) {
		TenenciaReexpresada tenenciaReexpPesos = new TenenciaReexpresada();
		TenenciaReexpresada tenenciaReexpDolares = new TenenciaReexpresada();

		tenenciaReexpPesos.setMoneda("ARS");
		tenenciaReexpPesos.setTenencia(ISBANStringUtils
				.formatearConComaYDosDecimales(String.valueOf(tenenciaDTO.getTotalTenenciaValuadaHoyPesos())));
		tenenciaReexpPesos.setTenenciaEnMoneda(ISBANStringUtils
				.formatearConComaYDosDecimales(String.valueOf(tenenciaDTO.getTotalTenenciaValuadaHoyDolares())));
		tenenciaReexpPesos.setTenenciaExpresadaOtraMoneda(ISBANStringUtils
				.formatearConComaYDosDecimales(String.valueOf(tenenciaDTO.getTenenciaReexpresadaDolares())));
		tenenciaReexpPesos.setTotalTenenciaReexpresada(ISBANStringUtils
				.formatearConComaYDosDecimales(String.valueOf(tenenciaDTO.getTotalTenenciaReexpresadaDolares())));

		tenenciaReexpDolares.setMoneda("USD");
		tenenciaReexpDolares.setTenencia(ISBANStringUtils
				.formatearConComaYDosDecimales(String.valueOf(tenenciaDTO.getTotalTenenciaValuadaHoyDolares())));
		tenenciaReexpDolares.setTenenciaEnMoneda(ISBANStringUtils
				.formatearConComaYDosDecimales(String.valueOf(tenenciaDTO.getTotalTenenciaValuadaHoyPesos())));
		tenenciaReexpDolares.setTenenciaExpresadaOtraMoneda(ISBANStringUtils
				.formatearConComaYDosDecimales(String.valueOf(tenenciaDTO.getTenenciaReexpresadaPesos())));
		tenenciaReexpDolares.setTotalTenenciaReexpresada(ISBANStringUtils
				.formatearConComaYDosDecimales(String.valueOf(tenenciaDTO.getTotalTenenciaReexpresadaPesos())));

		tenenciaConsolidadaView.setTotalTenenciaReexpresadaDolares(ISBANStringUtils
				.formatearConComaYDosDecimales(String.valueOf(tenenciaDTO.getTotalTenenciaReexpresadaDolares())));
		tenenciaConsolidadaView.setTotalTenenciaReexpresadaPesos(ISBANStringUtils
				.formatearConComaYDosDecimales(String.valueOf(tenenciaDTO.getTotalTenenciaReexpresadaPesos())));

		tenenciaConsolidadaView.setPorcentajeMonedaPesos(tenenciaReexpPesos.obtenerPorcentaje(
				tenenciaDTO.getTotalTenenciaValuadaHoyPesos(), tenenciaDTO.getTotalTenenciaReexpresadaPesos()));
		tenenciaConsolidadaView.getListaTenenciaReexpresada().add(tenenciaReexpDolares);
		tenenciaConsolidadaView.getListaTenenciaReexpresada().add(tenenciaReexpPesos);

		return tenenciaConsolidadaView;
	}

	/**
	 * Me fijo si los porcentajes redondeados dan 100 en total, si no dan 100 ajusto
	 * el valor del major porcentaje para que de 100% el total.
	 *
	 * @param listaTenenciaProductos the lista tenencia productos
	 * @return lista de tenencias con los porcentajes actualizados
	 */
	public List<TenenciaPorProductoView> porcentajeOK(List<TenenciaPorProductoView> listaTenenciaProductos) {
		int sumatotal = 0;
		TenenciaPorProductoView tenenciaMajor = listaTenenciaProductos.get(0);
		for (TenenciaPorProductoView tenencia : listaTenenciaProductos) {
			sumatotal = sumatotal + tenencia.getPorcentaje();
			if (tenencia.getPorcentaje() > tenenciaMajor.getPorcentaje()) {
				tenenciaMajor = tenencia;
			}
		}
		if (sumatotal != 100 && sumatotal != 0) {
			int dif = 100 - sumatotal;
			tenenciaMajor.setPorcentaje(tenenciaMajor.getPorcentaje() + dif);
		}

		return listaTenenciaProductos;
	}

	/**
	 * Obtener porcentaje reexpresado dolares.
	 *
	 * @param porcentajePesos the porcentaje pesos
	 * @return the int
	 */
	public int obtenerPorcentajeReexpresadoDolares(int porcentajePesos) {
		return 100 - porcentajePesos;
	}

	/**
	 * Grabar estadisticas tenencia consolidada.
	 *
	 * @param estadisticaTotales the estadistica totales
	 * @param estadisticaGrilla  the estadistica grilla
	 */
	private void grabarEstadisticasTenenciaConsolidada(String estadisticaTotales, String estadisticaGrilla) {
		estadisticaManager.add(EstadisticasConstants.ESTADISTICA_TENENCIA_CONSOLIDADA_TOTALES, estadisticaTotales);
		estadisticaManager.add(EstadisticasConstants.ESTADISTICA_TENENCIA_CONSOLIDADA_GRILLA, estadisticaGrilla);

		if ("1".equals(estadisticaTotales) && "1".equals(estadisticaGrilla)) {
			estadisticaManager.add(EstadisticasConstants.ESTADISTICA_TENENCIA_CONSOLIDADA_GENERAL,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}

		if ("2".equals(estadisticaTotales) && "2".equals(estadisticaGrilla)) {
			estadisticaManager.add(EstadisticasConstants.ESTADISTICA_TENENCIA_CONSOLIDADA_GENERAL,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}

		if (("1".equals(estadisticaTotales) && "2".equals(estadisticaGrilla))
				|| ("2".equals(estadisticaTotales) && "1".equals(estadisticaGrilla))) {
			estadisticaManager.add(EstadisticasConstants.ESTADISTICA_TENENCIA_CONSOLIDADA_GENERAL,
					EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL);
		}

		if (("2".equals(estadisticaTotales) && "9".equals(estadisticaGrilla))
				|| ("1".equals(estadisticaTotales) && "9".equals(estadisticaGrilla))) {
			estadisticaManager.add(EstadisticasConstants.ESTADISTICA_TENENCIA_CONSOLIDADA_GENERAL,
					EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.comun.manager.
	 * InversionesManager#obtenerDetalleCuentaCustodiaPorProducto(ar.com.
	 * santanderrio.obp.servicios.inversiones.comun.view.DetalleCustodiaInView)
	 */
	@Override
	public Respuesta<DetalleCustodiaView> obtenerDetalleCuentaCustodiaPorProducto(DetalleCustodiaInView detalleIn) {

		String codigoLegalDetalleCustodia = "10049";

		Respuesta<String> legal = legalBO.buscarLegal(codigoLegalDetalleCustodia);
		DetalleCustodiaView detalleCustodiaView = new DetalleCustodiaView();
		if (EstadoRespuesta.ERROR.equals(legal.getEstadoRespuesta())) {
			grabadoDeEstadisticasLegalesDetalleCustodia(legal.getEstadoRespuesta(), detalleIn.getEsBancaPrivada());
			Respuesta<DetalleCustodiaView> respuesta = respuestaFactory.crearRespuestaError("",
					TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ORDEN_VENTA_TITULOS_ERROR_SERVICIO);
			armarMenuConCuentasParaRespuestaError(respuesta, detalleIn);
			return respuesta;
		} else {
			grabadoDeEstadisticasLegalesDetalleCustodia(legal.getEstadoRespuesta(), detalleIn.getEsBancaPrivada());
			detalleCustodiaView.setLegales(legal.getRespuesta());
		}

		DetalleCustodiaDTO detalleCustodiaDTO = inversionesBO.obtenerCustodia(sesionCliente.getCliente(), detalleIn);
		if (detalleCustodiaDTO.getHayError()) {
			grabadoDeEstadisticasArmadoGrillaDetalleCustodia(detalleCustodiaDTO.getHayError(),
					detalleIn.getEsBancaPrivada());
			Respuesta<DetalleCustodiaView> respuesta = respuestaFactory.crearRespuestaError("",
					TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ORDEN_VENTA_TITULOS_ERROR_SERVICIO);
			armarMenuConCuentasParaRespuestaError(respuesta, detalleIn);
			return respuesta;
		} else {
			for (DetalleCustodiaCuentaView detalleCustodiaCuentaView : detalleCustodiaDTO
					.getListaDetalleCustodiaCuentaView()) {

				detalleCustodiaCuentaView
						.setNumeroCuenta(detalleIn.getEsBancaPrivada()
								? detalleCustodiaCuentaView.getSucursalCuenta() + "-"
										+ ISBANStringUtils
												.formatearNumeroCuenta(detalleCustodiaCuentaView.getNumeroCuenta())
								: ISBANStringUtils.formatearNumeroCuenta(detalleCustodiaCuentaView.getNumeroCuenta()));
				formatearImportesTenenciaExpresada(detalleCustodiaCuentaView.getTenenciaExpresadaPesos(),
						DivisaEnum.PESO.getSimbolo(), DivisaEnum.DOLAR.getSimbolo());
				formatearImportesTenenciaExpresada(detalleCustodiaCuentaView.getTenenciaExpresadaDolares(),
						DivisaEnum.DOLAR.getSimbolo(), DivisaEnum.PESO.getSimbolo());
				for (DetalleCustodiaCuentaMonedaView detalleCustodiaCuentaMonedaView : detalleCustodiaCuentaView
						.getDetalleCustodiaCuentaPeso()) {
					formatearImportesCustodia(detalleCustodiaCuentaMonedaView, DivisaEnum.PESO.getSimbolo());
				}

				for (DetalleCustodiaCuentaMonedaView detalleCustodiaCuentaMonedaView : detalleCustodiaCuentaView
						.getDetalleCustodiaCuentaDolar()) {
					formatearImportesCustodia(detalleCustodiaCuentaMonedaView, DivisaEnum.DOLAR.getSimbolo());
				}
			}
		}
		detalleCustodiaView.setListaDetalleCustodiaCuentaView(detalleCustodiaDTO.getListaDetalleCustodiaCuentaView());
		grabadoDeEstadisticasArmadoGrillaDetalleCustodia(detalleCustodiaDTO.getHayError(),
				detalleIn.getEsBancaPrivada());
		return respuestaFactory.crearRespuestaOk(detalleCustodiaView);
	}

	/**
	 * Grabado de estadisticas legales detalle custodia.
	 *
	 * @param estadoRespuesta the estado respuesta
	 * @param esBancaPrivada  the es banca privada
	 */
	private void grabadoDeEstadisticasLegalesDetalleCustodia(EstadoRespuesta estadoRespuesta, Boolean esBancaPrivada) {

		String codigoMensaje = esBancaPrivada ? EstadisticasConstants.INGRESO_DETALLE_CUSTODIA_BANCA_PRIVADA
				: EstadisticasConstants.INGRESO_DETALLE_CUSTODIA_BANCA_PERSONAL;

		if (EstadoRespuesta.ERROR.equals(estadoRespuesta)) {
			estadisticaManager.add(codigoMensaje, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		} else {
			estadisticaManager.add(codigoMensaje, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}
	}

	/**
	 * Armar menu con cuentas para respuesta error.
	 *
	 * @param respuesta the respuesta
	 * @param detalleIn the detalle in
	 */
	private void armarMenuConCuentasParaRespuestaError(Respuesta<DetalleCustodiaView> respuesta,
			DetalleCustodiaInView detalleIn) {

		DetalleCustodiaDTO detalleCustodiaDTO = inversionesBO
				.obtenerDatosCustodiaRespuestaError(sesionCliente.getCliente(), detalleIn);
		DetalleCustodiaView detalleCustodiaView = new DetalleCustodiaView();
		detalleCustodiaView.setListaDetalleCustodiaCuentaView(detalleCustodiaDTO.getListaDetalleCustodiaCuentaView());
		respuesta.setRespuesta(detalleCustodiaView);
	}

	/**
	 * Grabado de estadisticas armado grilla detalle custodia.
	 *
	 * @param hayError       the hay error
	 * @param esBancaPrivada the es banca privada
	 */
	private void grabadoDeEstadisticasArmadoGrillaDetalleCustodia(Boolean hayError, Boolean esBancaPrivada) {

		String codigoMensaje = esBancaPrivada ? EstadisticasConstants.CONSULTA_SERVICIO_DETALLE_BANCA_PRIVADA
				: EstadisticasConstants.CONSULTA_SERVICIO_DETALLE_BANCA_PERSONAL;

		if (hayError) {
			estadisticaManager.add(codigoMensaje, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		} else {
			estadisticaManager.add(codigoMensaje, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}
	}

	/**
	 * Formatear importes custodia.
	 *
	 * @param detalleCustodiaCuentaMonedaView the detalle custodia cuenta moneda
	 *                                        view
	 * @param moneda                          the moneda
	 */
	private void formatearImportesCustodia(DetalleCustodiaCuentaMonedaView detalleCustodiaCuentaMonedaView,
			String moneda) {

		detalleCustodiaCuentaMonedaView.setCantidadValorNominal(ISBANStringUtils
				.formatearSaldo(new BigDecimal(detalleCustodiaCuentaMonedaView.getCantidadValorNominal())));
		detalleCustodiaCuentaMonedaView
				.setPrecioMercado(GUION.equals(detalleCustodiaCuentaMonedaView.getPrecioMercado()) ? GUION
						: moneda + " " + ISBANStringUtils
								.formatearSaldo(new BigDecimal(detalleCustodiaCuentaMonedaView.getPrecioMercado())));
		detalleCustodiaCuentaMonedaView
				.setTenenciaValuadaHoy(GUION.equals(detalleCustodiaCuentaMonedaView.getTenenciaValuadaHoy()) ? GUION
						: moneda + " " + ISBANStringUtils.formatearSaldo(
								new BigDecimal(detalleCustodiaCuentaMonedaView.getTenenciaValuadaHoy())));
	}

	/**
	 * Formatear importes tenencia expresada.
	 *
	 * @param tenenciaExpresada the tenencia expresada
	 * @param moneda            the moneda
	 * @param monedaSecundaria  the moneda secundaria
	 */
	private void formatearImportesTenenciaExpresada(DetalleCustodiaTenenciaExperesada tenenciaExpresada, String moneda,
			String monedaSecundaria) {

		tenenciaExpresada.setTenenciaMonedaPrincipal(tenenciaExpresada.getTenenciaMonedaPrincipal().contains(GUION)
				? tenenciaExpresada.getTenenciaMonedaPrincipal()
				: moneda + " " + ISBANStringUtils
						.formatearSaldo(new BigDecimal(tenenciaExpresada.getTenenciaMonedaPrincipal())));
		tenenciaExpresada
				.setTenenciaPrincipalEnOtraMoneda(tenenciaExpresada.getTenenciaPrincipalEnOtraMoneda().contains(GUION)
						? tenenciaExpresada.getTenenciaPrincipalEnOtraMoneda()
						: monedaSecundaria + " " + ISBANStringUtils
								.formatearSaldo(new BigDecimal(tenenciaExpresada.getTenenciaPrincipalEnOtraMoneda())));
		tenenciaExpresada.setTenenciaMonedaSecundaria(tenenciaExpresada.getTenenciaMonedaSecundaria().contains(GUION)
				? tenenciaExpresada.getTenenciaMonedaSecundaria()
				: monedaSecundaria + " " + ISBANStringUtils
						.formatearSaldo(new BigDecimal(tenenciaExpresada.getTenenciaMonedaSecundaria())));
		tenenciaExpresada.setTotalMonedaSecundaria(tenenciaExpresada.getTotalMonedaSecundaria().contains(GUION)
				? tenenciaExpresada.getTotalMonedaSecundaria()
				: monedaSecundaria + " " + ISBANStringUtils
						.formatearSaldo(new BigDecimal(tenenciaExpresada.getTotalMonedaSecundaria())));

	}

	/**
	 * Obtener rentabilidad total analisis cartera.
	 *
	 * @return the respuesta
	 */
	private Respuesta<RentabilidadTotalView> obtenerRentabilidadTotalAnalisisCartera() {

		Respuesta<RentabilidadTotalView> rtaCartera = analisisCarteraManager
				.obtenerRentabilidadTotal(new RentabilidadTotalInView(carteraAConsultar, periodo));
		if (EstadoRespuesta.ERROR.equals(rtaCartera.getEstadoRespuesta())) {
			RentabilidadTotalView rentabilidadView = new RentabilidadTotalView();

			ValoresRentabilidadCabeceraView valoresRTL = new ValoresRentabilidadCabeceraView();
			valoresRTL.setTotalPesos("$ -");
			valoresRTL.setTotalDolares("u$s -");
			rentabilidadView.setValoresSelectorRetail(valoresRTL);

			ValoresRentabilidadCabeceraView valoresBP = new ValoresRentabilidadCabeceraView();
			valoresBP.setTotalPesos("$ -");
			valoresBP.setTotalDolares("u$s -");
			rentabilidadView.setValoresSelectorPrivada(valoresBP);

			rtaCartera.setRespuesta(rentabilidadView);
		}

		return rtaCartera;

	}

	@Override
	public Response exportarTenenciaConsolidada(TipoBancaEnum tipoBanca) {

		Respuesta<Reporte> respuestaReporte;
		String nombreDeArchivo;

		if (TipoBancaEnum.BANCA_PERSONAL.equals(tipoBanca)) {
			respuestaReporte = inversionesBO.obtenerTenenciaConsolidadaReporte(
					sesionParametros.getListaTenenciaConsolidadaRTL(), sesionCliente.getCliente());
			nombreDeArchivo = "attachment; filename=\"TenenciaConsolidada.xls\"";
		} else {
			respuestaReporte = inversionesBO.obtenerTenenciaConsolidadaReporteBP(
					sesionParametros.getListaTenenciaConsolidadaBP(), sesionCliente.getCliente());
			nombreDeArchivo = "attachment; filename=\"TenenciaConsolidadaPrivada.xls\"";
		}
		ResponseBuilder responseBuilder = null;
		Respuesta<ReporteView> respuestaView = new Respuesta<ReporteView>();
		if (EstadoRespuesta.OK.equals(respuestaReporte.getEstadoRespuesta())) {
			ReporteView resumenesView = ReporteView.fromReporte(respuestaReporte.getRespuesta());
			respuestaView.setRespuesta(resumenesView);
			respuestaView.setEstadoRespuesta(EstadoRespuesta.OK);
			responseBuilder = Response.ok((Object) respuestaView.getRespuesta().getByteArray());

			responseBuilder.header("Content-Disposition", nombreDeArchivo);
			estadisticaManager.add(
					TipoBancaEnum.BANCA_PERSONAL.equals(tipoBanca)
							? EstadisticasConstants.DESCARGA_EXCEL_TENENCIA_CONSOLIDADA_RETAIL
							: EstadisticasConstants.DESCARGA_EXCEL_TENENCIA_CONSOLIDADA_PRIVADA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			estadisticaManager.add(
					TipoBancaEnum.BANCA_PERSONAL.equals(tipoBanca)
							? EstadisticasConstants.DESCARGA_EXCEL_TENENCIA_CONSOLIDADA_RETAIL
							: EstadisticasConstants.DESCARGA_EXCEL_TENENCIA_CONSOLIDADA_PRIVADA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			respuestaView = respuestaFactory.crearRespuestaErrorPersonalizado(ReporteView.class, MENSAJE_ERROR_EXCEL,
					TipoError.ERROR_DESCARGA_EXCEL.getDescripcion());
			responseBuilder = Response.ok((Object) respuestaView);
		}
		return responseBuilder.build();

	}

}