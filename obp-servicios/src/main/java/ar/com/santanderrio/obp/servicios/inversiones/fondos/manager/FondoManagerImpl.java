/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.manager;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.singleton.FondosCotizacionesSingleton;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.prototype.FondosTenenciasPrototype;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.inversiones.DatosEvaluacionRiesgo;
import ar.com.santanderrio.obp.generated.webservices.inversiones.EvaluacionDeRiesgoResponse;
import ar.com.santanderrio.obp.generated.webservices.inversiones.ParametroDatosConfirmacionOrden;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.DatoItemMensaje;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.TipoMonedaInversionEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.InversionWSHelper;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoProductoEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dao.InversionDAO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dto.RendimientoBPrivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dto.RendimientoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.manager.BaseManager;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DatosComprobante;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RendimientoBPrivView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RendimientoView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.bo.FondoBO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.consultas.dao.ConsultaFondoDAO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentaConTenenciaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentaTituloDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentasConsultaFondoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FondoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FondoResumidoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.GraficoFondosBPrivOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.GraficoFondosRTLOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.PorcentajeGraficoFondos;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SimulacionInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SimularSuscripcionOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SuscripcionInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SuscripcionOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TenenciaFondosSuscritosDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TenenciaPorFondoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TenenciasFondoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TipoBancaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ConsultaFondoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.MovimientoFondoEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.MovimientosFondoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.bo.RendimientoBOImpl;

/**
 * The Class FondoManagerImpl.
 *
 * @author
 */
@Component("fondoManager")
public class FondoManagerImpl extends BaseManager implements FondoManager {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(FondoManagerImpl.class);

	/** The Constant INVERSOR_RIESGO_MEDIO. */
	private static final Object INVERSOR_RIESGO_MEDIO = "1";

	/** The Constant INVERSOR_RIESGO_BLOQUEANTE. */
	private static final Object INVERSOR_RIESGO_BLOQUEANTE = "2";

	/** The Constant MENSAJE_LEGALES_COMPROBANTE. */
	private static final String MENSAJE_LEGALES_COMPROBANTE = "50006";

	/** The Constant MENSAJE_LEGALES_DETALLE_FONDO. */
	private static final String MENSAJE_LEGALES_DETALLE_FONDO = "10062";

	/** The Constant MENSAJE_LEGALES_CONFIGURACION_SUSCRIPCION. */
	private static final String MENSAJE_LEGALES_CONFIGURACION_SUSCRIPCION = "10015";

	/** The Constant LARGO_SUCURSAL_CUENTA. */
	private static final int LARGO_SUCURSAL_CUENTA = 4;
	/** The Constant LARGO_CUENTA_DEBITO. */
	private static final int LARGO_CUENTA_DEBITO = 2;

	/** The Constant DECIMALES_IMPORTE_MOVIMIENTOFONDO. */
	private static final int DECIMALES_IMPORTE_MOVIMIENTO_FONDO = 2;

	/** The Constant DECIMALES_CANTIDAD_CUOTAPARTES. */
	private static final int DECIMALES_CANTIDAD_CUOTAPARTES = 4;

	/** The Constant DECIMALES_VALOR_CUOTAPARTE. */
	private static final int DECIMALES_VALOR_CUOTAPARTE = 6;

	/** The Constant CODIGO_LEGALES_TENENCIAS. */
	private static final String CODIGO_LEGALES_TENENCIAS = "50011";

	/** The Constant CODIGO_LEGALES_TENENCIAS_BP. */
	private static final String CODIGO_LEGALES_TENENCIAS_BP = "10032";

	/** The Constant TIPO_BANCA_PERSONAL. */
	private static final String TIPO_BANCA_PERSONAL = "BR";

	/** The Constant FONDO_DISPONIBLES_REQUERIDO_TRUE. */
	private static final boolean FONDO_DISPONIBLES_REQUERIDO_TRUE = true;

	/** The Constant FONDO_DISPONIBLES_REQUERIDO_FALSE. */
	private static final boolean FONDO_DISPONIBLES_REQUERIDO_FALSE = false;

	/** The Constant FONDO_EXCITI. */
	private static final String FONDO_EXCITI = "1";

	private static final String MENSAJE_ERROR_EXCEL = "Ocurrió un error y no se pudo realizar la descarga. Por favor, volvé a intentar";

	/** The Constant SIN_DATOS. */
	private static final String SIN_DATOS = "10477";

	/** The Constant SIN_MOVIMIENTOS_BUSQUEDA */
	private static final String SIN_MOVIMIENTOS_BUSQUEDA = "SIN_MOVIMIENTOS_BUSQUEDA";

	/** The Constant fondosEspeciesPlazoI */
	List<String> fondosEspeciesPlazoI = Arrays.asList("255", "190", "265", "270", "295", "300", "240", "238",
			"250", "275", "260", "280", "286", "290");

	/** The fondo BO. */
	@Autowired
	private FondoBO fondoBO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The cuenta manager. */
	@Autowired
	private CuentaManager cuentaManager;

	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** Dao de inversiones. */
	@Autowired
	private InversionDAO inversionDAO;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The session parametros. */
	@Autowired
	private SesionParametros sessionParametros;

	/** The reporte BO. */
	@Autowired
	private ReporteComprobantePDFBO reporteBO;

	/** The rendimiento BO impl. */
	@Autowired
	private RendimientoBOImpl rendimientoBOImpl;

	/** The consulta fondo DAO. */
	@Autowired
	protected ConsultaFondoDAO consultaFondoDAO;
	
	@Autowired
	private InversionWSHelper inversionWSHelper;

	@Autowired
	private FondosCotizacionesSingleton fondosCotizacionesSingleton;

	/** Fondos tenencias singleton */
	@Autowired
	private FondosTenenciasPrototype fondosTenenciasPrototype;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.FondoManager
	 * #simularSuscripcion(ar.com.santanderrio.obp.servicios.inversiones.fondos.
	 * view.FondoView)
	 */
	@Override
	public Respuesta<FondoView> simularSuscripcion(FondoView viewRequest) {
		FondoDTO requestDTO;
		crearHashSimularFondos(viewRequest);
		try {
			requestDTO = createDTO(viewRequest);
		} catch (BusinessException e) {
			estadisticaManager.add(EstadisticasConstants.CONFIRMACION_SUSCRIPCION_FONDO_RTL,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, ERROR_GENERICO);
		}
		Respuesta<FondoDTO> respuestaBO = fondoBO.simularSuscripcion(requestDTO, sesionCliente.getCliente());

		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			List<ItemMensajeRespuesta> itemsMensajeRespuesta = respuestaBO.getItemsMensajeRespuesta();
			estadisticaManager.add(EstadisticasConstants.CONFIRMACION_SUSCRIPCION_FONDO_RTL,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			if (itemsMensajeRespuesta.get(0).getTipoError()
					.equals(TipoError.SERVICIO_FONDOS_DESHABILITADO.getDescripcion())) {
				return respuestaFactory.crearRespuestaError("", TipoError.SERVICIO_FONDOS_DESHABILITADO,
						CodigoMensajeConstantes.ERROR_SERVICIO_FONDOS_DESHABILITADO);
			} else {
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, ERROR_GENERICO);
			}
		}

		FondoView viewResponse = createRetornoView(respuestaBO.getRespuesta());
		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
			// WARNING POR FUERA DE HORARIO
			estadisticaManager.add(EstadisticasConstants.CONFIRMACION_SUSCRIPCION_FONDO_RTL,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR_FH);
			return respuestaFactory.crearRespuestaWarning(FondoView.class, viewResponse,
					respuestaBO.getItemsMensajeRespuesta());
		} else {
			estadisticaManager.add(EstadisticasConstants.CONFIRMACION_SUSCRIPCION_FONDO_RTL,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			// SI RESPUESTA OK HAGO EVALUACION DE RIESGO
			try {
				viewResponse.setLegales(respuestaBO.getRespuesta().getLegales());
				EvaluacionDeRiesgoResponse evaluacionDeRiesgoResponse = inversionDAO
						.evaluacionDeRiesgo(cargarDatosEvalRiesgo(requestDTO));
				// CARGO CABECERA Y PIE SI CORRESPONDE
				if (evaluacionDeRiesgoResponse != null && !"0".equals(evaluacionDeRiesgoResponse.getTipoDisclaimer())
						&& evaluacionDeRiesgoResponse.getMensaje().getCantidadDeDisclaimers() > 0) {
					viewResponse.setCabecera(evaluacionDeRiesgoResponse.getCabecera());
					viewResponse.setPie(evaluacionDeRiesgoResponse.getPie());
				}
				// se setea el id evaluacion, necesario para el WS de finalizar
				// suscripcion.
				if (evaluacionDeRiesgoResponse != null) {
					sessionParametros.setIdEv(evaluacionDeRiesgoResponse.getIdEvaluacion());
				}
				if (evaluacionDeRiesgoResponse.getTipoDisclaimer().equals(INVERSOR_RIESGO_BLOQUEANTE)) {
					List<ItemMensajeRespuesta> itemsMensajeRespuesta = cargarMensajesPopup(evaluacionDeRiesgoResponse,
							TipoError.RIESGO_BLOQUEANTE);

					return respuestaFactory.crearRespuestaWarning(FondoView.class, viewResponse, itemsMensajeRespuesta);

				} else if (evaluacionDeRiesgoResponse.getTipoDisclaimer().equals(INVERSOR_RIESGO_MEDIO)) {
					List<ItemMensajeRespuesta> itemsMensajeRespuesta = cargarMensajesPopup(evaluacionDeRiesgoResponse,
							TipoError.RIESGO_MEDIO);

					return respuestaFactory.crearRespuestaWarning(FondoView.class, viewResponse, itemsMensajeRespuesta);
				}
			} catch (DAOException e) {
				LOGGER.error("Servicio evaluacion de riesgo caido", e);
				return respuestaFactory.crearRespuestaWarning(viewResponse, "", TipoError.RIESGO_MEDIO,
						CodigoMensajeConstantes.SERVICIO_EVALUACION_RIESGO_CAIDO);
			}
			return respuestaFactory.crearRespuestaOk(FondoView.class, viewResponse);
		}
	}

	/**
	 * carga los datos para el request de evaluacion de riesgo.
	 * 
	 * @param requestDTO the request DTO
	 * @return the datos evaluacion riesgo
	 */
	private DatosEvaluacionRiesgo cargarDatosEvalRiesgo(FondoDTO requestDTO) {
		DatosEvaluacionRiesgo parametroDatos = super.cargarDatosEvalRiesgo();

		parametroDatos.setTipoOperacion(TIPO_OPERACION_SUSCRIPCION_FONDO);
		// VARIABLES
		if (requestDTO.getTipoCtaDeb().equals(TipoCuenta.CUENTA_UNICA.getAbreviatura())) {
			if (requestDTO.getMoneda().equals(TipoMonedaInversionEnum.USD.getCodigo())) {
				parametroDatos.setTipoCuenta(StringUtils.leftPad(TipoCuenta.CUENTA_UNICA_DOLARES.getCodigo().toString(),
						LARGO_CUENTA_DEBITO, "0"));
			} else {
				parametroDatos.setTipoCuenta(StringUtils.leftPad(TipoCuenta.CUENTA_UNICA_PESOS.getCodigo().toString(),
						LARGO_CUENTA_DEBITO, "0"));
			}
		} else {
			parametroDatos.setTipoCuenta(
					StringUtils.leftPad(TipoCuenta.fromAbreviatura(requestDTO.getTipoCtaDeb()).getCodigo().toString(),
							LARGO_CUENTA_DEBITO, "0"));
		}
		parametroDatos
				.setSucursalCuenta(StringUtils.leftPad(requestDTO.getSucursalCtaDeb(), LARGO_SUCURSAL_CUENTA, "0"));
		parametroDatos.setNroCuenta(requestDTO.getNumeroCtaDeb().replaceAll("/", ""));// HACER
		// EL
		// SPLIT
		// DE
		// LA
		// BARRA
		parametroDatos.setNroCuentaTitulo(requestDTO.getCuentaTitulo().replaceAll("/", ""));
		parametroDatos.setEspecie(requestDTO.getCodigoFondo());
		parametroDatos.setEspecieOrigen(null);
		parametroDatos.setMonto(requestDTO.getImporte().replace('.', ' ').replaceAll(" ", "").replaceAll(",", "."));
		parametroDatos
				.setImporteDebCred(requestDTO.getImporte().replace('.', ' ').replaceAll(" ", "").replaceAll(",", "."));
		parametroDatos.setMoneda(TipoMonedaInversionEnum.fromSimboloString(requestDTO.getMoneda()).getCodigo2());

		return parametroDatos;
	}

	/**
	 * crear respuesta view para devolver al sei.
	 * 
	 * @param viewRequest the view request
	 * @return respuesta view.
	 */
	@Override
	public Respuesta<FondoView> suscribir(FondoView viewRequest) {
		FondoDTO dto;
		try {
			dto = createDTO(viewRequest);
		} catch (BusinessException e) {
			LOGGER.error("Error creando DTO", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, ERROR_GENERICO);
		}
		Respuesta<FondoDTO> respuestaBO = fondoBO.suscribir(dto, sesionCliente.getCliente());

		// supone OK
		FondoView view = createRetornoView(respuestaBO.getRespuesta());
		return respuestaFactory.crearRespuestaOk(FondoView.class, view);
	}

	/**
	 * crear un DTO para llamar al BO.
	 *
	 * @param view the view request
	 * @return dto.
	 * @throws BusinessException the business exception
	 */
	private FondoDTO createDTO(FondoView view) throws BusinessException {
		FondoDTO dto = new FondoDTO();
		try {
			BeanUtils.copyProperties(dto, view);
		} catch (IllegalAccessException ex) {
			LOGGER.error("Error convirtiendo view en DTO. ", ex);
			throw new BusinessException();
		} catch (InvocationTargetException ex) {
			LOGGER.error("Error convirtiendo view en DTO. ", ex);
			throw new BusinessException();
		}
		return dto;
	}

	/**
	 * crear un View para retornar al FondoSEI.
	 * 
	 * @param dto the dto
	 * @return view.
	 */
	private FondoView createRetornoView(FondoDTO dto) {
		FondoView viewReturn = new FondoView();
		if (dto != null) {
			viewReturn.setUrlReglamento(dto.getUrlReglamento());
			viewReturn.setMensajeSuscripcion(dto.getMensajeSuscripcion());
			viewReturn.setImporte(dto.getImporte());
			viewReturn.setTerminosYCondiciones(dto.getTerminosYCondiciones());
			viewReturn.setNumeroComprobante(dto.getNumeroCertificado());
			viewReturn.setFechaHora(dto.getFechaHora());
		}
		return viewReturn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.FondoManager
	 * #obtenerFondosSuscriptosYDisponibles(ar.com.santanderrio.obp.servicios.
	 * inversiones.fondos.view.CuentasConsultaFondoView)
	 */
	@Override
	public Respuesta<CuentasConsultaFondoView> obtenerFondosSuscriptosYDisponibles(
			CuentasConsultaFondoView viewRequest) {
		sessionParametros.setContador(new ContadorIntentos(2));
		LOGGER.info("Invocando al BO obtenerFondosSuscriptosYDisponibles");

		Respuesta<CuentasConsultaFondoDTO> respuestaBO = null;
		CuentasConsultaFondoDTO requestDTO = new CuentasConsultaFondoDTO();

		requestDTO = viewToDTO(viewRequest);
		respuestaBO = fondoBO.obtenerFondosSuscriptosYDisponibles(requestDTO, sesionCliente.getCliente(),
				FONDO_DISPONIBLES_REQUERIDO_TRUE, false);

		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			return respuestaFactory.crearRespuestaError(CuentasConsultaFondoView.class,
					respuestaBO.getItemsMensajeRespuesta().get(0).getMensaje(), "");
		}

		if(respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
			if(TipoError.FUERADEHORARIO.toString().equals(respuestaBO.getItemsMensajeRespuesta().get(0).getTipoError())) {
				estadisticaManager.add(EstadisticasConstants.FUERA_HORARIO_SUSCRIBIR_PASO1_RTL,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR_FH);
				return respuestaFactory.crearRespuestaWarning(CuentasConsultaFondoView.class, null,
						respuestaBO.getItemsMensajeRespuesta());
			}
		}
		
		CuentasConsultaFondoView respuestaView = dtoToView(respuestaBO.getRespuesta());

		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaOk(CuentasConsultaFondoView.class, respuestaView);
		}
		return respuestaFactory.crearRespuestaWarning(CuentasConsultaFondoView.class, respuestaView,
				respuestaBO.getItemsMensajeRespuesta());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.FondoManager
	 * #obtenerFondosSuscriptosYDisponiblesBPriv(ar.com.santanderrio.obp.
	 * servicios.inversiones.fondos.view.CuentasConsultaFondoView)
	 */
	@Override
	public Respuesta<CuentasConsultaFondoView> obtenerFondosSuscriptosYDisponiblesBPriv(
			CuentasConsultaFondoView viewRequest) {
		sessionParametros.setContador(new ContadorIntentos(2));
		LOGGER.info("Invocando al BO obtenerFondosSuscriptosYDisponiblesBPriv");

		Respuesta<CuentasConsultaFondoDTO> respuestaBO = null;
		CuentasConsultaFondoDTO requestDTO = viewToDTO(viewRequest);
		respuestaBO = fondoBO.obtenerFondosSuscriptosYDisponiblesBPriv(requestDTO, sesionCliente.getCliente(),
				FONDO_DISPONIBLES_REQUERIDO_TRUE);

		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			return respuestaFactory.crearRespuestaError(CuentasConsultaFondoView.class,
					respuestaBO.getItemsMensajeRespuesta().get(0).getMensaje(), "");
		}

		if(respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
			if(TipoError.FUERADEHORARIO.toString().equals(respuestaBO.getItemsMensajeRespuesta().get(0).getTipoError())) {
				estadisticaManager.add(EstadisticasConstants.FUERA_HORARIO_SUSCRIBIR_PASO1_BPRIV,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR_FH);
				return respuestaFactory.crearRespuestaWarning(CuentasConsultaFondoView.class, new CuentasConsultaFondoView(),
						respuestaBO.getItemsMensajeRespuesta());
			}
		}
		
		CuentasConsultaFondoView respuestaView = dtoToView(respuestaBO.getRespuesta());

		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaOk(CuentasConsultaFondoView.class, respuestaView);
		}
		return respuestaFactory.crearRespuestaWarning(CuentasConsultaFondoView.class, respuestaView,
				respuestaBO.getItemsMensajeRespuesta());
	}

	/**
	 * DT oto view.
	 *
	 * @param cuentasRetornoBO the cuentas retorno BO
	 * @return the cuentas consulta fondo view
	 */
	private CuentasConsultaFondoView dtoToView(CuentasConsultaFondoDTO cuentasRetornoBO) {
		CuentasConsultaFondoView retornoView = new CuentasConsultaFondoView();
		retornoView.setTipoBanca(cuentasRetornoBO.getTipoBanca());
		retornoView.setMensajeInformacion(cuentasRetornoBO.getMensajeInformacion());
		retornoView.setTextoBuscador(cuentasRetornoBO.getTextoBuscador());
		retornoView.setFondosTotales(new ArrayList<FondoResumidoView>());

		for (FondoResumidoDTO fondoResumidoDTO : cuentasRetornoBO.getFondosTotales()) {
			FondoResumidoView fondoResumidoView = new FondoResumidoView();
//			if (!fondoResumidoDTO.isFondoNuevo()) {
				fondoResumidoView.setImporteMaximo(fondoResumidoDTO.getImporteMaximo());
				fondoResumidoView.setImporteMinimo(fondoResumidoDTO.getImporteMinimo());
				fondoResumidoView.setMoneda(fondoResumidoDTO.getMoneda());
				fondoResumidoView.setNombreFondo(fondoResumidoDTO.getNombreFondo());
				fondoResumidoView.setSaldo(fondoResumidoDTO.getSaldo());
				fondoResumidoView.setGrupo(fondoResumidoDTO.getGrupo());
				fondoResumidoView.setCodigoFondo(fondoResumidoDTO.getCodigoFondo());
				if (fondoResumidoDTO.getIdMensajeGastos() != null) {
					fondoResumidoView.setIdMensajeGastos(fondoResumidoDTO.getIdMensajeGastos());
					fondoResumidoView.setTieneGastos(true);
				}
				fondoResumidoView.setFondoNuevo(fondoResumidoDTO.isFondoNuevo());
				if(fondoResumidoDTO.isRepatriacion()) {
					fondoResumidoView.setRepatriacion(true);
				}
				retornoView.getFondosTotales().add(fondoResumidoView);
//			}

		}

		List<CuentaTituloView> cuentasTituloView = obtenerCuentasTituloView(cuentasRetornoBO);

		retornoView.setCuentasTitulo(cuentasTituloView);
		return retornoView;
	}

	/**
	 * View to DTO.
	 *
	 * @param viewRequest the view request
	 * @return the cuentas consulta fondo DTO
	 */
	private CuentasConsultaFondoDTO viewToDTO(CuentasConsultaFondoView viewRequest) {
		CuentasConsultaFondoDTO retorno = new CuentasConsultaFondoDTO();
		retorno.setTipoBanca(viewRequest.getTipoBanca());
		retorno.setFondosTotales(new ArrayList<FondoResumidoDTO>());
		List<CuentaTituloDTO> cuentasTituloDTO = new ArrayList<CuentaTituloDTO>();
		for (CuentaTituloView cuentaTituloView : viewRequest.getCuentasTitulo()) {
			CuentaTituloDTO cuentaTituloDTO = new CuentaTituloDTO();
			cuentaTituloDTO.setNroCuenta(cuentaTituloView.getNroCuenta());
			cuentaTituloDTO.setSucursal(cuentaTituloView.getSucursal());
			cuentaTituloDTO.setNroCuentaFormateado(cuentaTituloView.getNroCuentaFormateado());
			cuentaTituloDTO.setCuentaOperativa(cuentaTituloView.getCuentaOperativa());
			cuentaTituloDTO.setIntervinientes(cuentaTituloView.getIntervinientes());
			cuentasTituloDTO.add(cuentaTituloDTO);
		}
		retorno.setCuentasTitulo(cuentasTituloDTO);
		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<ConfigFondoView> obtenerDatosConfig(ConfigFondoView requestView) {
		LOGGER.info("Invocando al BO obtenerDatosConfig");
		cleanCacheConsultarCotizaciones(fondosCotizacionesSingleton.getCleanCache());
		ConfigFondoView configFondoView = new ConfigFondoView();
		try {
			configFondoView
					.setContratoAceptado(fondoBO.obtenerMarcaContrato(sesionCliente.getCliente()).getRespuesta());
		} catch (BusinessException e) {
			LOGGER.error("Error obteniendo el estado del contrato", e);
			respuestaFactory.crearRespuestaError(ConfigFondoView.class, "Error obteniendo contrato", null);
		}

		Respuesta<CuentasView> respuesta = cuentaManager.getCuentasSaldoPorMoneda(requestView.getMoneda());

		List<CuentasAdhesionDebitoView> cuentasDebito = respuesta.getRespuesta().getCuentas();
		List<Cuenta> cuentasCepo = cuentaManager.getCuentasCepo(sesionCliente.getCliente());
		List<Cuenta> cuentasAnses = getCuentasAnses(sesionCliente.getCliente());

		List<CuentasAdhesionDebitoView> cuentasDebitoFiltradas = filtrarCuentasCepo(cuentasCepo, cuentasDebito);
		cuentasDebitoFiltradas = filtrarCuentasAnses(cuentasAnses, cuentasDebitoFiltradas);
		cuentasDebito = inyectarCtasRepatriacion(sesionCliente.getCliente(), cuentasDebitoFiltradas);

		if (cuentasDebito.isEmpty()) {
			String moneda = TipoMonedaInversionEnum.fromSimboloString(requestView.getMoneda()).getMonedas();
			String mensaje = mensajeBO
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_SIN_CUENTA_MONEDA_SUSCRIPCION_FONDO)
					.getMensaje();
			mensaje = MessageFormat.format(mensaje, moneda);
			return respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(ConfigFondoView.class, mensaje,
					TipoError.SIN_CUENTAS_TIPO_MONEDA.getDescripcion());
		} else {
			configFondoView.setCuentasDebito(cuentasDebito);
		}

		if ("true".equals(requestView.getTieneGastos())) {
			configFondoView.setInformacionGastos(legalBO.buscarLegal(requestView.getIdMensajeGastos()).getRespuesta());
		}
		Respuesta<String> respuestasLegales;
		respuestasLegales = legalBO.buscarLegal(MENSAJE_LEGALES_CONFIGURACION_SUSCRIPCION);
		if (EstadoRespuesta.ERROR.equals(respuestasLegales.getEstadoRespuesta())) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, ERROR_GENERICO);
		}
		configFondoView.setLegales(respuestasLegales.getRespuesta());
		
		try {
			configFondoView.setUrlReglamento(fondoBO.obtenerReglamento(requestView.getCodigoFondo()));
		} catch (BusinessException e) {
			LOGGER.error("Error obteniendo la url Contrato", e);
			respuestaFactory.crearRespuestaError(ConfigFondoView.class, "Error obteniendo url Contrato", null);
		}


		try {
			Respuesta<CotizacionDeFondosView> cotizacionRespuesta = fondoBO.consultarCotizaciones();
			for(DatosCotizacionFondoView datos : cotizacionRespuesta.getRespuesta().getFondos()) {
				if(datos.getCodigoFondo() == null ? requestView.getCodigoFondo() == null : datos.getCodigoFondo().equals(requestView.getCodigoFondo())) {
					configFondoView.setInformacion(datos);
					break;
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error al obtener la cotizacion del fondo", e);
		}

		configFondoView.setCodigoFondo(requestView.getCodigoFondo());

		try {
			LOGGER.info("Valido horario de fondo");
			if(!fondoBO.validarHorarioDeFondo(configFondoView.getInformacion().getHorario())) return respuestaFactory
					.crearRespuestaWarning(fondoBO.crearItemsWarningFueraHorarioAgendarFondo(), new ConfigFondoView());
		}catch (Exception ex) {
			LOGGER.error("Ocurrio un error al validar el horario del fondo. Permite continuar con la simulación", ex);
		}

		return respuestaFactory.crearRespuestaOk(ConfigFondoView.class, configFondoView);
	}

	private List<CuentasAdhesionDebitoView> inyectarCtasRepatriacion(Cliente cliente,
			List<CuentasAdhesionDebitoView> cuentasDebito) {
		
		for(Cuenta cta : cliente.getCuentas()) {
			for(CuentasAdhesionDebitoView ctaDeb : cuentasDebito ) {
				if(cta.getCbu().equals(ctaDeb.getCbu())) {
					ctaDeb.setRepatriacion(cta.isRepatriacion());
				}
			}
		}
		
		return cuentasDebito;
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<ConfigFondoView> aceptarContrato(ConfigFondoView requestView) {
		LOGGER.info("Invocando al BO aceptarContrato");
		ConfigFondoView configFondoView = new ConfigFondoView();

		if (requestView.isContratoAceptado()) {
			try {
				fondoBO.aceptarContrato(sesionCliente.getCliente());
				estadisticaManager.add(EstadisticasConstants.ACEPTACION_DECLARACION_ORIGEN_DE_FONDOS_FCI,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			} catch (BusinessException e) {
				LOGGER.error("Error al querer aceptar el contrato", e);
				String mensaje = mensajeBO
						.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS).getMensaje();
				estadisticaManager.add(EstadisticasConstants.ACEPTACION_DECLARACION_ORIGEN_DE_FONDOS_FCI,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				return respuestaFactory.crearRespuestaError(ConfigFondoView.class, mensaje,
						TipoError.ERROR_GENERICO.getDescripcion());
			}
			return respuestaFactory.crearRespuestaOk(ConfigFondoView.class, configFondoView);
		}
		return respuestaFactory.crearRespuestaError(ConfigFondoView.class, "Error NO Tildo la Aceptacion de contrato!!",
				null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<TenenciasFondoView> obtenerTenencias(TipoBancaView requestView) {
		LOGGER.info("Recuperando tenencias para el cliente nup:" + sesionCliente.getCliente().getNup());
		Respuesta<String> respuestasLegales = new Respuesta<String>();

		respuestasLegales = legalBO.buscarLegal(CODIGO_LEGALES_TENENCIAS);
		
		if (EstadoRespuesta.ERROR.equals(respuestasLegales.getEstadoRespuesta())) {
			LOGGER.error("Error legales");
			if (TIPO_BANCA_PERSONAL.equals(requestView.getTipoBanca())) {
				estadisticaManager.add(EstadisticasConstants.CODIGO_ESTADISTICA_INICIO_BASE_FONDOS,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			} else {
				estadisticaManager.add(EstadisticasConstants.CODIGO_ESTADISTICA_INICIO_BASE_FONDOS_BPRIV,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);

			}
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, ERROR_GENERICO);
		}

		TipoBancaDTO tipoBancaDTO = new TipoBancaDTO();
		tipoBancaDTO.setTipoBanca(requestView.getTipoBanca());
		// this.vaciarCache();

		// Obtiene las tenencias ni bien ingresa a la seccion
		Respuesta<TenenciasFondoDTO> respuesta = fondoBO.obtenerTenencias(tipoBancaDTO, sesionCliente.getCliente());

		if (EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_TENENCIA_FONDOS_NUEVOS);
		}

		if (requestView.getTipoBanca().equals(TIPO_BANCA_PERSONAL)) {
			grabarEstadisticasTenenciasFondos(EstadisticasConstants.CODIGO_ESTADISTICA_INICIO_BASE_FONDOS,
					EstadisticasConstants.CODIGO_ESTADISTICA_TENENCIA_FONDOS_BANCA_PERSONAL,
					respuesta.getRespuesta().getListaCuentas().get(0).getRespuesta());
		} else {
			grabarEstadisticasTenenciasFondos(EstadisticasConstants.CODIGO_ESTADISTICA_INICIO_BASE_FONDOS_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICA_TENENCIA_FONDOS_BANCA_PRIVADA,
					respuesta.getRespuesta().getListaCuentas().get(0).getRespuesta());
		}

		TenenciasFondoView viewResponse = new TenenciasFondoView();
		viewResponse = createViewResponse(respuesta.getRespuesta(), respuestasLegales.getRespuesta());
		viewResponse.setNuevaApertura(inversionWSHelper.enriFlag());

		if (TIPO_BANCA_PERSONAL.equals(requestView.getTipoBanca())) {
			sessionParametros.setListaCuentasFondos(viewResponse.getListaCuentas());
		} else {
			sessionParametros.setListaCuentasFondosBP(viewResponse.getListaCuentas());
		}

		if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
			return respuestaFactory.crearRespuestaOk(TenenciasFondoView.class, viewResponse);
		}
		return respuestaFactory.crearRespuestaWarning(TenenciasFondoView.class, viewResponse, TipoError.WARNING,
				CodigoMensajeConstantes.TOTALES_TENENCIA_INCOMPLETOS, "");

	}

	/**
	 * Graba estadisticas para tenencia de fondos a partir del estado respuesta de
	 * las tenencias de fondos.
	 *
	 * @param estadisticaFondos    the estadistica fondos
	 * @param estadisticaTenencias the estadistica tenencias
	 * @param respuesta            the respuesta
	 */
	private void grabarEstadisticasTenenciasFondos(String estadisticaFondos, String estadisticaTenencias,
			Respuesta<TenenciaPorFondoDTO> respuesta) {
		if (EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta()) && !TipoError.ERROR_CUENTA_SIN_TENENCIA
				.getDescripcion().equals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError())) {
			estadisticaManager.add(estadisticaFondos, EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL);
			estadisticaManager.add(estadisticaTenencias, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		} else {
			estadisticaManager.add(estadisticaFondos, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			estadisticaManager.add(estadisticaTenencias, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}

	}

	/**
	 * Efectuar logica ok.
	 *
	 * @param respuestaBO the respuesta BO
	 * @return the respuesta
	 */
	private Respuesta<FondoView> efectuarLogicaOk(Respuesta<FondoDTO> respuestaBO) {
		estadisticaManager.add(EstadisticasConstants.CODIGO_FINALIZAR_SUSCRIPCION,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		estadisticaManager.add(EstadisticasConstants.ACEPTACION_REGLAMENTO_GESTION_BPERS,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		try {
			if (sessionParametros.getIdEv() != null) {
				inversionDAO.confirmacionOrden(cargarDatosConfirmarOrden(respuestaBO));
			}
		} catch (DAOException e) {
			LOGGER.error("Error al invocar el Ws.", e);
		}
		FondoView view = createRetornoView(respuestaBO.getRespuesta());
		return respuestaFactory.crearRespuestaOk(FondoView.class, view);
	}

	/**
	 * Efectuar logica error.
	 *
	 * @param respuestaBO the respuesta BO
	 * @return the respuesta
	 */
	private Respuesta<FondoView> efectuarLogicaError(Respuesta<FondoDTO> respuestaBO) {
		estadisticaManager.add(EstadisticasConstants.CODIGO_FINALIZAR_SUSCRIPCION,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		return respuestaFactory.crearRespuestaError(FondoView.class,
				respuestaBO.getItemsMensajeRespuesta().get(0).getMensaje(),
				respuestaBO.getItemsMensajeRespuesta().get(0).getTag());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<FondoView> finalizarSuscribirFondos(FondoView viewRequest) {
		FondoDTO requestDTO;
		validarHashSuscripcionFondos(viewRequest);

		if (sesionCliente.getCliente() != null) {
			// Vaciar cache de la transaccion CNSTENVAL 110
			fondosTenenciasPrototype.cleanCacheConsultaTenenciaFCI(sesionCliente.getCliente());
		}

		try {
			requestDTO = createDTO(viewRequest);
		} catch (BusinessException e) {
			LOGGER.error("Error al crear el DTO {}.", viewRequest, e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, ERROR_GENERICO);
		}
		Respuesta<FondoDTO> respuestaBO = fondoBO.finalizarSuscribirFondos(requestDTO, sesionCliente.getCliente());
		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			cargarHashDeLaVistaComprobante(viewRequest, respuestaBO.getRespuesta());
			return efectuarLogicaOk(respuestaBO);
		}
		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
			return respuestaFactory.crearRespuestaWarning(FondoView.class, viewRequest,
					respuestaBO.getItemsMensajeRespuesta());
		}
		return efectuarLogicaError(respuestaBO);
	}

	/**
	 * carga los datos para el request de confirmar orden.
	 *
	 * @param respuestaBO the respuesta BO
	 * @return the parametro datos confirmacion orden
	 */
	private ParametroDatosConfirmacionOrden cargarDatosConfirmarOrden(Respuesta<FondoDTO> respuestaBO) {
		ParametroDatosConfirmacionOrden parametroDatos = new ParametroDatosConfirmacionOrden();
		parametroDatos.setCodCanal(COD_CANAL);
		parametroDatos.setNroOperacion(respuestaBO.getRespuesta().getNumeroCertificado());
		parametroDatos.setIdEvaluacion(sessionParametros.getIdEv());
		return parametroDatos;
	}

	/**
	 * Graba estadistica de aceptacion de legales de banca personal.
	 *
	 * @param requestView the request view
	 */
	@Override
	public void grabarEstadisticaLegalBPersonal(ConfigFondoView requestView) {
		if ("true".equals(requestView.getTieneGastos())) {
			estadisticaManager.add(EstadisticasConstants.SUSCRIPCION_LEGALES_BANCA_PERSONAL,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}
	}

	/**
	 * Graba estadistica de aceptacion de legales de banca privada.
	 *
	 * @param requestView the request view
	 */
	@Override
	public void grabarEstadisticaLegalBPriv(ConfigFondoView requestView) {
		if ("true".equals(requestView.getTieneGastos())) {
			estadisticaManager.add(EstadisticasConstants.SUSCRIPCION_LEGALES_BANCA_PRIVADA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.FondoManager
	 * #obtenerDatosConfigBpriv(ar.com.santanderrio.obp.servicios.inversiones.
	 * fondos.view.ConfigFondoBPrivView)
	 */
	@Override
	public Respuesta<ConfigFondoBPrivView> obtenerDatosConfigBpriv(ConfigFondoBPrivView requestView) {

		LOGGER.info("Invocando al BO obtenerDatosConfig Banca Privada");
		cleanCacheConsultarCotizaciones(fondosCotizacionesSingleton.getCleanCache());
		ConfigFondoBPrivView configFondoView = new ConfigFondoBPrivView();
		try {
			configFondoView
					.setContratoAceptado(fondoBO.obtenerMarcaContrato(sesionCliente.getCliente()).getRespuesta());
		} catch (BusinessException e) {
			LOGGER.error("Error obteniendo el estado del contrato", e);
			respuestaFactory.crearRespuestaError(ConfigFondoView.class, "Error obteniendo contrato", null);
		}

		// obtener el saldo de la cuenta :
		// requestView.get

		CuentasAdhesionDebitoView cuenta = fondoBO
				.obtenerSaldosCuentaOperativa(requestView.getCuentaBancaPrivada(), sesionCliente.getCliente(), false)
				.getRespuesta();
		List<CuentasAdhesionDebitoView> cuentasDebito = new ArrayList<CuentasAdhesionDebitoView>();
		cuentasDebito.add(cuenta);
		cuentasDebito = inyectarCtasRepatriacionBP(sesionCliente.getCliente(),cuentasDebito);
		configFondoView.setCuentasDebito(cuentasDebito);
		if ("true".equals(requestView.getTieneGastos())) {
			configFondoView.setInformacionGastos(legalBO.buscarLegal(requestView.getIdMensajeGastos()).getRespuesta());
		}
		Respuesta<String> respuestasLegales;
		respuestasLegales = legalBO.buscarLegal(MENSAJE_LEGALES_CONFIGURACION_SUSCRIPCION);
		if (EstadoRespuesta.ERROR.equals(respuestasLegales.getEstadoRespuesta())) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, ERROR_GENERICO);
		}
		configFondoView.setLegales(respuestasLegales.getRespuesta());
		
		try {
			configFondoView.setUrlReglamento(fondoBO.obtenerReglamento(requestView.getCodFondo()));
		} catch (BusinessException e) {
			LOGGER.error("Error obteniendo la url Contrato", e);
			respuestaFactory.crearRespuestaError(ConfigFondoView.class, "Error obteniendo url Contrato", null);
		}

		try {
			Respuesta<CotizacionDeFondosView> cotizacionRespuesta = fondoBO.consultarCotizaciones();

			for(DatosCotizacionFondoView datos : cotizacionRespuesta.getRespuesta().getFondos()) {
				if(datos.getCodigoFondo() == null ? requestView.getCodFondo() == null : datos.getCodigoFondo().equals(requestView.getCodFondo())) {
					configFondoView.setInformacion(datos);
				}
			}
		} catch (Exception e){
			LOGGER.error("Error al obtener cotizacion del fondo", e);
		}

		configFondoView.setCodFondo(requestView.getCodFondo());

		try {
			LOGGER.info("Valido horario de fondo");
			if(!fondoBO.validarHorarioDeFondo(configFondoView.getInformacion().getHorario())) return respuestaFactory
					.crearRespuestaWarning(fondoBO.crearItemsWarningFueraHorarioAgendarFondo(), new ConfigFondoBPrivView());
		}catch (Exception ex) {
			LOGGER.error("Ocurrio un error al validar el horario del fondo. Permite continuar con la simulación", ex);
		}
		
		return respuestaFactory.crearRespuestaOk(ConfigFondoBPrivView.class, configFondoView);
	}

	private List<CuentasAdhesionDebitoView> inyectarCtasRepatriacionBP(Cliente cliente,
			List<CuentasAdhesionDebitoView> cuentasDebito) {
		List<Cuenta> ctasTitRepBP=cliente.getCuentasTitBPRepatriacion();
		for(CuentasAdhesionDebitoView ctaDeb : cuentasDebito) {
			
			for(Cuenta ctaRep : ctasTitRepBP) {
				if(ctaRep.getCuentaOPRepatriacion()==Integer.parseInt(ctaDeb.getNumero().replaceAll("/", "").substring(4, 11))) {
					ctaDeb.setRepatriacion(true);
				}
			}
			
		}
		
		
		
		return cuentasDebito;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.FondoManager
	 * #simularSuscripcionBPriv(ar.com.santanderrio.obp.servicios.inversiones.
	 * fondos.view.SimulacionInView)
	 */
	@Override
	public Respuesta<SimulacionSuscripcionOutView> simularSuscripcionBPriv(SimulacionInView viewRequest) {
		Respuesta<SimulacionSuscripcionOutView> rta = super.validate(viewRequest, new SimulacionSuscripcionOutView());
		if (!rta.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			estadisticaManager.add(EstadisticasConstants.CONFIRMACION_SUSCRIPCION_FONDO_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.SUSCRIPCION_FONDO_FALLO_GENERICO);
		}

		crearHashSimularFondosBpriv(viewRequest);

		LOGGER.info("Invocando al BO para simulacion de suscripcion Banca Privada");
		Respuesta<SimularSuscripcionOutDTO> rtaBO = fondoBO.simularSuscripcionBPriv(createDTO(viewRequest),
				sesionCliente.getCliente());
		if (rtaBO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			estadisticaManager.add(EstadisticasConstants.CONFIRMACION_SUSCRIPCION_FONDO_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(SimulacionSuscripcionOutView.class,
					createView(rtaBO.getRespuesta()));
		}

		estadisticaManager.add(EstadisticasConstants.CONFIRMACION_SUSCRIPCION_FONDO_BPRIV,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		return respuestaFactory.crearRespuestaError(SimulacionSuscripcionOutView.class,
				rtaBO.getItemsMensajeRespuesta().get(0).getMensaje(), rtaBO.getItemsMensajeRespuesta().get(0).getTag());

	}

	/**
	 * Crea el DTO para el llamado al BO a partir del view.
	 *
	 * @param viewRequest the view request
	 * @return the simulacion in DTO
	 */
	private SimulacionInDTO createDTO(SimulacionInView viewRequest) {
		SimulacionInDTO simulacionDTO = new SimulacionInDTO();

		try {
			BeanUtils.copyProperties(simulacionDTO, viewRequest);
		} catch (IllegalAccessException e) {
			LOGGER.error("Error creando DTO", e);
		} catch (InvocationTargetException e) {
			LOGGER.error("Error creando DTO", e);
		}
		return simulacionDTO;
	}

	/**
	 * Creates the view.
	 *
	 * @param dto the dto
	 * @return the simulacion suscripcion out view
	 */
	private SimulacionSuscripcionOutView createView(SimularSuscripcionOutDTO dto) {
		SimulacionSuscripcionOutView view = new SimulacionSuscripcionOutView();
		try {
			BeanUtils.copyProperties(view, dto);
		} catch (IllegalAccessException e) {
			LOGGER.error("Error creando VIEW", e);
		} catch (InvocationTargetException e) {
			LOGGER.error("Error creando VIEW", e);
		}
		return view;
	}

	/**
	 * crear un View para retornar al FondoSEI.
	 * 
	 * @param dto the dto
	 * @return view.
	 */
	private SuscripcionOutView createRetornoViewBPriv(SuscripcionOutDTO dto) {
		SuscripcionOutView viewReturn = new SuscripcionOutView();
		if (dto != null) {
			viewReturn.setCodigoRetorno(dto.getCodigoRetorno());
			viewReturn.setCuotaPartes(dto.getCuotaPartes());
			viewReturn.setDescripcion(dto.getDescripcion());
			viewReturn.setDisclaimer(dto.getDisclaimer());
			viewReturn.setImporte(dto.getImporte());
			viewReturn.setMensajeSuscripcion(dto.getMensajeSuscripcion());
			viewReturn.setNumeroComprobante(dto.getNroCertificado());
			viewReturn.setNroOrden(dto.getNroOrden());
			viewReturn.setRetorno(dto.getRetorno());
			viewReturn.setNombreFondo(dto.getNombreFondo());
			viewReturn.setFechaHora(dto.getFechaHora());
		}
		return viewReturn;
	}

	/**
	 * Efectuar logica ok B priv.
	 *
	 * @param respuestaBO the respuesta BO
	 * @return the respuesta
	 */
	private Respuesta<SuscripcionOutView> efectuarLogicaOkBPriv(Respuesta<SuscripcionOutDTO> respuestaBO) {
		estadisticaManager.add(EstadisticasConstants.CODIGO_FINALIZAR_SUSCRIPCION_BPRIV,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		estadisticaManager.add(EstadisticasConstants.ACEPTACION_REGLAMENTO_GESTION_BPRIV,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

		SuscripcionOutView view = createRetornoViewBPriv(respuestaBO.getRespuesta());
		return respuestaFactory.crearRespuestaOk(SuscripcionOutView.class, view);
	}

	/**
	 * Efectuar logica error B priv.
	 *
	 * @param respuestaBO the respuesta BO
	 * @return the respuesta
	 */
	private Respuesta<SuscripcionOutView> efectuarLogicaErrorBPriv(Respuesta<SuscripcionOutDTO> respuestaBO) {
		estadisticaManager.add(EstadisticasConstants.CODIGO_FINALIZAR_SUSCRIPCION_BPRIV,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		return respuestaFactory.crearRespuestaError(SuscripcionOutView.class,
				respuestaBO.getItemsMensajeRespuesta().get(0).getMensaje(),
				respuestaBO.getItemsMensajeRespuesta().get(0).getTag());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.FondoManager
	 * #finalizarSuscribirFondosBPriv(ar.com.santanderrio.obp.servicios.
	 * inversiones.fondos.view.SuscripcionInView)
	 */
	@Override
	public Respuesta<SuscripcionOutView> finalizarSuscribirFondosBPriv(SuscripcionInView viewRequest) {
		Respuesta<SuscripcionOutView> rtaValidacion = super.validate(viewRequest, new SuscripcionOutView());
		if (!rtaValidacion.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.SUSCRIPCION_FONDO_FALLO_GENERICO);
		}

		validarHashSuscripcionFondosBpriv(viewRequest);
		LOGGER.info("Invocando al BO para ejecutar suscripcion Banca Privada");

		Respuesta<SuscripcionOutDTO> rtaBO = fondoBO.finalizarSuscribirFondosBPriv(createDTO(viewRequest),
				sesionCliente.getCliente());
		if (rtaBO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			cargarHashDeLaVistaComprobanteBpriv(viewRequest, rtaBO.getRespuesta());
			return efectuarLogicaOkBPriv(rtaBO);
		}
		if (rtaBO.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
			return respuestaFactory.crearRespuestaWarning(SuscripcionOutView.class, new SuscripcionOutView(),
					rtaBO.getItemsMensajeRespuesta());
		}
		return efectuarLogicaErrorBPriv(rtaBO);
	}

	/**
	 * Crea el DTO para el llamado al BO a partir del view para la suscripcion.
	 *
	 * @param viewRequest the view request
	 * @return the suscripcion in DTO
	 */
	private SuscripcionInDTO createDTO(SuscripcionInView viewRequest) {
		SuscripcionInDTO suscripcionInDTO = new SuscripcionInDTO();

		try {
			BeanUtils.copyProperties(suscripcionInDTO, viewRequest);
		} catch (IllegalAccessException e) {
			LOGGER.error("Error creando DTO", e);
		} catch (InvocationTargetException e) {
			LOGGER.error("Error creando DTO", e);
		}

		return suscripcionInDTO;
	}

	/**
	 * Creates the DTO.
	 *
	 * @param teneneciasDTO the tenenecias DTO
	 * @param legales       the legales
	 * @return the tenencias fondo view
	 */
	private TenenciasFondoView createViewResponse(TenenciasFondoDTO teneneciasDTO, String legales) {
		TenenciasFondoView tenenciasFondoView = new TenenciasFondoView();
		tenenciasFondoView.setListaCuentas(teneneciasDTO.getListaCuentas());
		tenenciasFondoView.setLegales(legales);
		tenenciasFondoView.setMensajeSinTenenciasMobile(teneneciasDTO.getMensajeSinTenenciasMobile());
		tenenciasFondoView.setMensajeCuentaBloqueada(teneneciasDTO.getMensajeCuentaBloqueada());
		return tenenciasFondoView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.FondoManager
	 * #verComprobanteBPriv()
	 */
	@Override
	public Respuesta<ComprobanteSuscripcionView> verComprobanteBPriv(DatosComprobante viewRequest) {

		ComprobanteSuscripcionView comprobanteSuscripcionView = new ComprobanteSuscripcionView();
		Respuesta<String> respuestaLegales = legalBO.buscarLegal(MENSAJE_LEGALES_COMPROBANTE);
		if (respuestaLegales.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			Estadistica estadistica = new Estadistica(EstadisticasConstants.VER_COMPROBANTE_SUSCRIPCION_BANCA_PRIVADA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			estadistica.setNroComprobante(viewRequest.getNumeroComprobante());
			estadisticaManager.add(estadistica, sessionParametros.getRegistroSession(), sesionCliente.getCliente());
			comprobanteSuscripcionView.setLegales(respuestaLegales.getRespuesta());
			return respuestaFactory.crearRespuestaOk(ComprobanteSuscripcionView.class, comprobanteSuscripcionView);
		} else {
			estadisticaManager.add(EstadisticasConstants.VER_COMPROBANTE_SUSCRIPCION_BANCA_PRIVADA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, ERROR_GENERICO);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.FondoManager
	 * #verComprobante()
	 */
	@Override
	public Respuesta<ComprobanteSuscripcionView> verComprobante(DatosComprobante viewRequest) {
		{
			ComprobanteSuscripcionView comprobanteSuscripcionView = new ComprobanteSuscripcionView();
			Respuesta<String> respuestaLegales = legalBO.buscarLegal(MENSAJE_LEGALES_COMPROBANTE);
			if (EstadoRespuesta.OK.equals(respuestaLegales.getEstadoRespuesta())) {
				comprobanteSuscripcionView.setLegales(respuestaLegales.getRespuesta());
				Estadistica estadistica = new Estadistica(EstadisticasConstants.VER_COMPROBANTE,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				estadistica.setNroComprobante(viewRequest.getNumeroComprobante());
				estadisticaManager.add(estadistica, sessionParametros.getRegistroSession(), sesionCliente.getCliente());
				return respuestaFactory.crearRespuestaOk(ComprobanteSuscripcionView.class, comprobanteSuscripcionView);
			} else {
				estadisticaManager.add(EstadisticasConstants.VER_COMPROBANTE,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, ERROR_GENERICO);
			}

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.FondoManager
	 * #vaciarCache()
	 */
	@Override
	public Respuesta<Boolean> vaciarCache() {
		return fondoBO.vaciarCache();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.FondoManager
	 * #consultarCotizaciones()
	 */
	@Override
	public Respuesta<CotizacionDeFondosView> consultarCotizaciones() {
		cleanCacheConsultarCotizaciones(fondosCotizacionesSingleton.getCleanCache());
		Respuesta<CotizacionDeFondosView> respuestaView = fondoBO.consultarCotizaciones();
		if (EstadoRespuesta.OK.equals(respuestaView.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.VER_COTIZACION_DE_FONDOS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(CotizacionDeFondosView.class, respuestaView.getRespuesta());
		}
		estadisticaManager.add(EstadisticasConstants.VER_COTIZACION_DE_FONDOS,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CONSULTAR_COTIZACIONES_FONDOS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.FondoManager
	 * #consultarHorariosYHonorarios()
	 */
	@Override
	public Respuesta<ConsultaHorariosYHonorariosView> consultarHorariosYHonorarios() {
		return fondoBO.obtenerFondosHorariosYHonorarios();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<MovimientosView> ultimosMovimientos(MovimientosInView viewRequest) {
		MovimientosView movimientosView = new MovimientosView();
		Respuesta<MovimientosView> respuesta = new Respuesta<MovimientosView>();
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		Date ayer = DateUtils.addDays(new Date(), -1);
		Date desde;
		String concepto;
		// Por defecto trae: (fecha actual - 30)dias hasta (fecha actual -1)dias
		if (viewRequest.getFechaDesde() == null) {
			desde = DateUtils.addDays(new Date(), -30);
			viewRequest.setFechaDesde(sdf.format(desde));
			viewRequest.setFechaHasta(sdf.format(ayer));
		} else {
			try {
				desde = new SimpleDateFormat("ddMMyyyy").parse(viewRequest.getFechaDesde());
				Date hasta = new SimpleDateFormat("ddMMyyyy").parse(viewRequest.getFechaHasta());
				Date fechaHoy = DateUtils.addDays(new Date(), 0);
				if (hasta.compareTo(fechaHoy) == 0) {
					viewRequest.setFechaHasta(sdf.format(ayer));
				} else {
					viewRequest.setFechaHasta(sdf.format(hasta));
				}
				viewRequest.setFechaDesde(sdf.format(desde));
			} catch (ParseException e) {
				LOGGER.error("Error al finalizar precancelacion. ", e);
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_FALLO_SERVICIOS);
			}
		}

		ConsultaFondoOutEntity fondoSeleccionado;
		try {
			fondoSeleccionado = consultaFondoDAO.obtenerPorCodigo(viewRequest.getCodigoFondo());
		} catch (DAOException e) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_FALLO_SERVICIOS);
		}
		viewRequest.setExCiti(fondoSeleccionado.getExCiti());

		try {
			Respuesta<MovimientosFondoOutEntity> responseMovimientos = FONDO_EXCITI
					.equalsIgnoreCase(fondoSeleccionado.getExCiti())
							? fondoBO.consultarMovimientosCiti(sesionCliente.getCliente(), viewRequest)
							: fondoBO.consultarMovimientos(sesionCliente.getCliente(), viewRequest);
			if (EstadoRespuesta.OK.equals(responseMovimientos.getEstadoRespuesta())) {

				estadisticaManager.add(EstadisticasConstants.ESTADISTICA_MOVIMIENTOS_FONDO,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				movimientosView = createResponse(responseMovimientos);
				for (MovimientoFondoView movFondos: movimientosView.getMovimientos()) {
					concepto=movFondos.getConcepto().substring(0, 6).replace(".", "").trim();
					if (concepto.equals("SUSC")) {
							movFondos.setConcepto("Suscripción");
					}if (concepto.equals("RESC")) {
						movFondos.setConcepto("Rescate");
					}if (concepto.equals("SUSCBJ")) {
						movFondos.setConcepto("Baja de suscripción");
					}if (concepto.equals("RESCBJ")) {
						movFondos.setConcepto("Baja de rescate");
					}if (concepto.equals("TRANEN")) {
						movFondos.setConcepto("Transferencia de titularidad entrada");
					}if (concepto.equals("TRANSL")) {
						movFondos.setConcepto("Transferencia de titularidad salida");
					}
				}
				respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
				respuesta.setRespuesta(movimientosView);
			} else {
				respuesta = respuestaFactory.crearRespuestaError(new ArrayList<DatoItemMensaje>());
				respuesta.setEstadoRespuesta(responseMovimientos.getEstadoRespuesta());
				// respuesta.setItemMensajeRespuesta(responseMovimientos.getItemsMensajeRespuesta());

				List<ItemMensajeRespuesta> itemsMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
				ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
				itemMensajeRespuesta.setTipoError(SIN_MOVIMIENTOS_BUSQUEDA);
				String mensajeSinDatos = mensajeBO.obtenerMensajePorCodigo(SIN_DATOS).getMensaje();
				itemMensajeRespuesta.setMensaje(mensajeSinDatos);
				itemsMensajeRespuesta.add(itemMensajeRespuesta);
				respuesta.setItemMensajeRespuesta(itemsMensajeRespuesta);

				if (respuesta.getItemsMensajeRespuesta().get(0).getTipoError()
						.equals(TipoError.SIN_MOVIMIENTOS.getDescripcion())) {
					estadisticaManager.add(EstadisticasConstants.ESTADISTICA_MOVIMIENTOS_FONDO,
							EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				} else {
					estadisticaManager.add(EstadisticasConstants.ESTADISTICA_MOVIMIENTOS_FONDO,
							EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				}
			}
		} catch (ImporteConvertException e) {
			estadisticaManager.add(EstadisticasConstants.ESTADISTICA_MOVIMIENTOS_FONDO,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_FALLO_SERVICIOS);
		}
		return respuesta;
	}

	/**
	 * Obtener fondo desde tenencias por codigo.
	 *
	 * @param codigoFondo the codigo fondo
	 * @return the tenencia fondos suscritos DTO
	 */
	private TenenciaFondosSuscritosDTO obtenerFondoDesdeTenenciasPorCodigo(String codigoFondo) {

		TenenciasFondoDTO fondosConTenencia = sessionParametros.getTenenciasFondoDTO();
		TenenciaFondosSuscritosDTO respuestaFondo = new TenenciaFondosSuscritosDTO();

		List<CuentaConTenenciaDTO> listaCuentas = fondosConTenencia.getListaCuentas();
		boolean fondoEncontrado = false;
		for (CuentaConTenenciaDTO cuentaConTenencia : listaCuentas) {
			if (fondoEncontrado) {
				break;
			}

			List<TenenciaFondosSuscritosDTO> tenenciaFondosSuscritos = new ArrayList<TenenciaFondosSuscritosDTO>();
			if (cuentaConTenencia.getRespuesta().getRespuesta() != null) {
				if (cuentaConTenencia.getRespuesta().getRespuesta().getTenenciaFondosSuscritosDolares() != null) {
					tenenciaFondosSuscritos.addAll(
							cuentaConTenencia.getRespuesta().getRespuesta().getTenenciaFondosSuscritosDolares());
				}

				if (cuentaConTenencia.getRespuesta().getRespuesta().getTenenciaFondosSuscritosPesos() != null) {
					tenenciaFondosSuscritos
							.addAll(cuentaConTenencia.getRespuesta().getRespuesta().getTenenciaFondosSuscritosPesos());
				}
			}

			for (TenenciaFondosSuscritosDTO fondoConTenencia : tenenciaFondosSuscritos) {
				if (codigoFondo.equalsIgnoreCase(fondoConTenencia.getCodigoFondo())) {
					fondoEncontrado = true;
					respuestaFondo = fondoConTenencia;
					break;
				}
			}
		}
		return respuestaFondo;
	}

	/**
	 * Crea una respuesta MovimientosView para devolver al SEI.
	 *
	 * @param responseMovimientos the response movimientos
	 * @return the movimientos view
	 * @throws ImporteConvertException the importe convert exception
	 */
	MovimientosView createResponse(Respuesta<MovimientosFondoOutEntity> responseMovimientos)
			throws ImporteConvertException {
		MovimientosFondoOutEntity movimientoFondos = responseMovimientos.getRespuesta();
		List<MovimientoFondoEntity> movimientoFondo = movimientoFondos.getMovimientos();
		MovimientosView movimientoView = new MovimientosView();
		List<MovimientoFondoView> listaMovimientos = new ArrayList<MovimientoFondoView>();

		for (MovimientoFondoEntity mov : movimientoFondo) {
			MovimientoFondoView movimiento = new MovimientoFondoView();
			movimiento.setConcepto(mov.getDescripcionMovimiento());
			movimiento.setImporte(
					ISBANStringUtils.convertirStrToBigDecimal(mov.getImporte(), DECIMALES_IMPORTE_MOVIMIENTO_FONDO));
			movimiento.setFecha(mov.getFechaDePedido());
			movimiento.setNroCertificado(mov.getNumeroCertificado());
			movimiento.setCantidadCuotapartes(ISBANStringUtils.convertirStrToBigDecimal(mov.getCantidadCuotaPartes(),
					DECIMALES_CANTIDAD_CUOTAPARTES));
			movimiento.setValorCuotapartes(
					fondoBO.convertirStrToBigDecimalVC(mov.getValorCuota(), DECIMALES_VALOR_CUOTAPARTE));
			listaMovimientos.add(movimiento);
		}
		movimientoView.setMovimientos(listaMovimientos);
		return movimientoView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.FondoManager
	 * #obtenerDetalleDeFondo(ar.com.santanderrio.obp.servicios.inversiones.
	 * fondos.view.DetalleDeFondoIn)
	 */
	@Override
	public Respuesta<DetalleDeFondoOutView> obtenerDetalleDeFondo(DetalleDeFondoIn viewRequest) {
		Respuesta<String> respuestasLegales;
		respuestasLegales = legalBO.buscarLegal(MENSAJE_LEGALES_DETALLE_FONDO);

		if (EstadoRespuesta.OK.equals(respuestasLegales.getEstadoRespuesta())) {
			Respuesta<DetalleDeFondoOutView> respuestaDetalleDeFondoOutView;
			TenenciaFondosSuscritosDTO fondoSeleccionado = obtenerFondoDesdeTenenciasPorCodigo(
					viewRequest.getCodigoFondo());
			respuestaDetalleDeFondoOutView = fondoBO.obtenerDetalleDeFondo(viewRequest, sesionCliente.getCliente(),
					fondoSeleccionado);
			if (EstadoRespuesta.OK.equals(respuestaDetalleDeFondoOutView.getEstadoRespuesta())) {
				DetalleDeFondoOutView detalleDeFondoOutView = respuestaDetalleDeFondoOutView.getRespuesta();
				detalleDeFondoOutView.setLegales(respuestasLegales.getRespuesta());
				return respuestaFactory.crearRespuestaOk(DetalleDeFondoOutView.class, detalleDeFondoOutView);
			} else {
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
		} else {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.SUSCRIPCION_FONDO_FALLO_GENERICO);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<MovimientosView> busquedaMovimientos(MovimientosInView viewRequest) {
		MovimientosView movimientosView = new MovimientosView();
		Respuesta<MovimientosView> respuesta = new Respuesta<MovimientosView>();

		ConsultaFondoOutEntity fondoSeleccionado;
		try {
			fondoSeleccionado = consultaFondoDAO.obtenerPorCodigo(viewRequest.getCodigoFondo());
		} catch (DAOException e) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_FALLO_SERVICIOS);
		}
		viewRequest.setExCiti(fondoSeleccionado.getExCiti());

		try {
			Respuesta<MovimientosFondoOutEntity> responseMovimientos = FONDO_EXCITI
					.equalsIgnoreCase(fondoSeleccionado.getExCiti())
							? fondoBO.consultarMovimientosCiti(sesionCliente.getCliente(), viewRequest)
							: fondoBO.consultarMovimientos(sesionCliente.getCliente(), viewRequest);
			if (EstadoRespuesta.OK.equals(responseMovimientos.getEstadoRespuesta())) {
				movimientosView = createResponse(responseMovimientos);
				respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
				respuesta.setRespuesta(movimientosView);
			} else {
				respuesta = respuestaFactory.crearRespuestaError(new ArrayList<DatoItemMensaje>());
				respuesta.setEstadoRespuesta(responseMovimientos.getEstadoRespuesta());
				respuesta.setItemMensajeRespuesta(responseMovimientos.getItemsMensajeRespuesta());
				if (respuesta.getItemsMensajeRespuesta().get(0).getTipoError()
						.equals(TipoError.SIN_MOVIMIENTOS.getDescripcion())) {
					return respuestaFactory.crearRespuestaError("", TipoError.SIN_MOVIMIENTOS_BUSQUEDA,
							CodigoMensajeConstantes.ERROR_FONDO_SIN_MOVIMIENTOS_MAYOR_30_DIAS);
				}
			}
		} catch (ImporteConvertException e) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_FALLO_SERVICIOS);
		}
		return respuesta;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<GraficoFondosRTLOutView> obtenerGraficosRTL() {
		Respuesta<GraficoFondosRTLOutDTO> rtaBO = fondoBO.obtenerGraficosRTL(sesionCliente.getCliente());
		if (rtaBO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			GraficoFondosRTLOutView rtaManager = createViewResponse(rtaBO.getRespuesta());
			return respuestaFactory.crearRespuestaOk(GraficoFondosRTLOutView.class, rtaManager);
		} else {
			Respuesta<GraficoFondosRTLOutView> rtaError = new Respuesta<GraficoFondosRTLOutView>();
			rtaError.setEstadoRespuesta(EstadoRespuesta.ERROR);
			return rtaError;
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<GraficoFondosBPrivOutView> obtenerGraficosBPriv() {
		Respuesta<GraficoFondosBPrivOutDTO> rtaBO = fondoBO.obtenerGraficosBpriv(sesionCliente.getCliente());
		if (rtaBO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			GraficoFondosBPrivOutView rtaManager = createViewResponseBPriv(rtaBO.getRespuesta());
			return respuestaFactory.crearRespuestaOk(GraficoFondosBPrivOutView.class, rtaManager);
		} else {
			Respuesta<GraficoFondosBPrivOutView> rtaError = new Respuesta<GraficoFondosBPrivOutView>();
			rtaError.setEstadoRespuesta(EstadoRespuesta.ERROR);
			return rtaError;
		}
	}

	/**
	 * Crea el view de respuesta para graficos de banca personal.
	 *
	 * @param respuesta the respuesta
	 * @return the grafico fondos RTL out view
	 */
	private GraficoFondosRTLOutView createViewResponse(GraficoFondosRTLOutDTO respuesta) {
		GraficoFondosRTLOutView rtaView = new GraficoFondosRTLOutView();
		List<PorcentajeGraficoFondosView> listaPorcentaje = new ArrayList<PorcentajeGraficoFondosView>();
		for (PorcentajeGraficoFondos lista : respuesta.getListaPorcentaje()) {
			PorcentajeGraficoFondosView porcentajes = new PorcentajeGraficoFondosView();
			porcentajes.setTipoFondo(lista.getTipoFondo());
			porcentajes.setPorcentajeTenenciaPesos(lista.getPorcentajeTenenciaPesos());
			porcentajes.setPorcentajeTenenciaDolares(lista.getPorcentajeTenenciaDolares());
			listaPorcentaje.add(porcentajes);
		}
		rtaView.setListaPorcentaje(listaPorcentaje);
		rtaView.setPorcentajeTenenciaPesos(respuesta.getPorcentajeTenenciaPesos());
		rtaView.setGraficosEnCero(respuesta.isGraficosEnCero());
		return rtaView;
	}

	/**
	 * Crea el view de respuesta para graficos de banca privada.
	 *
	 * @param respuesta the respuesta
	 * @return the grafico fondos B priv out view
	 */
	private GraficoFondosBPrivOutView createViewResponseBPriv(GraficoFondosBPrivOutDTO respuesta) {
		GraficoFondosBPrivOutView rtaView = new GraficoFondosBPrivOutView();
		rtaView.setListGraficosPorCuenta(respuesta.getListGraficosPorCuenta());
		return rtaView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.FondoManager
	 * #obtenerFondosUltimosMovimientos(ar.com.santanderrio.obp.servicios.
	 * inversiones.fondos.view.CuentasConsultaFondoView)
	 */
	@Override
	public Respuesta<CuentasConsultaFondoView> obtenerFondosUltimosMovimientos(CuentasConsultaFondoView viewRequest) {

		Respuesta<CuentasConsultaFondoDTO> respuestaBO = null;
		CuentasConsultaFondoDTO requestDTO = new CuentasConsultaFondoDTO();

		requestDTO = viewToDTO(viewRequest);
		respuestaBO = fondoBO.obtenerFondosSuscriptosYDisponibles(requestDTO, sesionCliente.getCliente(),
				FONDO_DISPONIBLES_REQUERIDO_FALSE, true);

		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			return respuestaFactory.crearRespuestaError(CuentasConsultaFondoView.class,
					respuestaBO.getItemsMensajeRespuesta().get(0).getMensaje(), "");
		}

		// Filtrar los fondos del tipo especies que tienen PLAZO I
		List<FondoResumidoDTO> fondosTotales = respuestaBO.getRespuesta().getFondosTotales();

		for (Iterator<FondoResumidoDTO> iterator = fondosTotales.iterator(); iterator.hasNext(); ) {
			FondoResumidoDTO fondo = iterator.next();
			if(fondosEspeciesPlazoI.contains(fondo.getCodigoFondo())) {
				iterator.remove();
			}
		}
		// Asignar los fondos restantes a la respuesta final
		respuestaBO.getRespuesta().setFondosTotales(fondosTotales);

		CuentasConsultaFondoView respuestaView = dtoToView(respuestaBO.getRespuesta());

		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaOk(CuentasConsultaFondoView.class, respuestaView);
		}
		return respuestaFactory.crearRespuestaWarning(CuentasConsultaFondoView.class, respuestaView,
				respuestaBO.getItemsMensajeRespuesta());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.FondoManager
	 * #descargarComprobanteSuscripcionPDF(ar.com.santanderrio.obp.servicios.
	 * inversiones.fondos.view.ComprobanteSuscripcionFondo)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteSuscripcionPDF(ComprobanteSuscripcionFondo viewRequest) {
		Respuesta<Reporte> reporte;
		Respuesta<ReporteView> respuestaView;
		validarHashSuscripcionComprobante(viewRequest);
		reporte = reporteBO.obtenerComprobantePDF(viewRequest);
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
			estadisticaManager.add(EstadisticasConstants.COMPROBANTE_SUSCRIPCION_FONDO,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			respuestaView = respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
			estadisticaManager.add(EstadisticasConstants.COMPROBANTE_SUSCRIPCION_FONDO,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaView;
	}

	/**
	 * Cargar hash de la vista simulacion.
	 *
	 * @param view the view
	 */
	private void crearHashSimularFondos(FondoView view) {
		String hash = HashUtils.obtenerHash(crearMapSimularFondos(view));
		LOGGER.info(MSJ_INFO_GUARDANDO_HASH_EN_SESION);
		sessionParametros.setValidacionHash(hash);
	}

	/**
	 * Cargar hash de la vista en sesion.
	 * 
	 * Solo crea un hash de los datos estaticos, es decir, que el usuario no puede
	 * cambiar desde la vista
	 *
	 * @author marcelo.ruiz
	 * @param fondoView the fondo view
	 * @return the map
	 */
	private Map<String, Object> crearMapSimularFondos(FondoView fondoView) {
		LOGGER.info(MSJ_INFO_CREANDO_HASH_ATRIBUTOS);
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		mapaAtributos.put("codigoFondo", fondoView.getCodigoFondo());
		mapaAtributos.put("cuentaTitulos", fondoView.getCuentaTitulo());
		mapaAtributos.put("importe", fondoView.getImporte());
		mapaAtributos.put("moneda", fondoView.getMoneda());
		mapaAtributos.put("numeroCuentaDebito", fondoView.getNumeroCtaDeb());
		mapaAtributos.put("sucursalCuentaDebito", fondoView.getSucursalCtaDeb());
		mapaAtributos.put("tipoCuentaDebito", fondoView.getTipoCtaDeb());
		LOGGER.info("String mapa vista: " + mapaAtributos.toString());
		return mapaAtributos;
	}

	/**
	 * Validar hash flujo fondos.
	 *
	 * @param fondoView the fondo view
	 */
	private void validarHashSuscripcionFondos(FondoView fondoView) {
		String inputHash = HashUtils.obtenerHash(crearMapFinalizarSuscripcionFondos(fondoView));
		LOGGER.info(MSJ_INFO_VALIDANDO_HASH, sessionParametros.getValidacionHash(), inputHash);
		HashUtils.compareHash(sessionParametros.getValidacionHash(), inputHash);
	}

	/**
	 * Crear mapa de la vista calcular intereses.
	 *
	 * @param fondoView the fondo view
	 * @return the map
	 */
	private Map<String, Object> crearMapFinalizarSuscripcionFondos(FondoView fondoView) {
		LOGGER.info(MSJ_INFO_CREANDO_HASH_ATRIBUTOS);
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		mapaAtributos.put("codigoFondo", fondoView.getCodigoFondo());
		mapaAtributos.put("cuentaTitulos", fondoView.getCuentaTitulo());
		mapaAtributos.put("importe", fondoView.getImporte());
		mapaAtributos.put("moneda", fondoView.getMoneda());
		mapaAtributos.put("numeroCuentaDebito", fondoView.getNumeroCtaDeb());
		mapaAtributos.put("sucursalCuentaDebito", fondoView.getSucursalCtaDeb());
		mapaAtributos.put("tipoCuentaDebito", fondoView.getTipoCtaDeb());
		LOGGER.info("String mapa vista: " + mapaAtributos.toString());
		return mapaAtributos;
	}

	/**
	 * Cargar hash de la vista en sesion.
	 * 
	 * <P>
	 * Solo crea un hash de los datos estaticos, es decir, que el usuario no puede
	 * cambiar desde la vista
	 * </P>
	 *
	 * @author marcelo.ruiz
	 * @param fondoView the fondo view
	 * @param fondoDTO  the fondo DTO
	 * @return the map
	 */
	private Map<String, Object> crearMapaDeLaVistaComprobante(FondoView fondoView, FondoDTO fondoDTO) {
		LOGGER.info(MSJ_INFO_CREANDO_HASH_ATRIBUTOS);
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		mapaAtributos.put("cuentaTitulos", fondoView.getCuentaTitulo());
		mapaAtributos.put("nombreFondo", fondoView.getNombreFondo());
		mapaAtributos.put("numeroCuentaDebito", fondoView.getNumeroCtaDeb());
		mapaAtributos.put("nroComprobante", fondoDTO.getNumeroCertificado());
		mapaAtributos.put("importe", fondoDTO.getImporte().trim());
		mapaAtributos.put("fecha", fondoDTO.getFechaHora());
		LOGGER.info("String mapa vista: " + mapaAtributos.toString());
		return mapaAtributos;
	}

	/**
	 * Cargar hash de la vista simulacion.
	 *
	 * @param inView   the in view
	 * @param fondoDTO the fondo DTO
	 */
	private void cargarHashDeLaVistaComprobante(FondoView inView, FondoDTO fondoDTO) {
		String hashView = HashUtils.obtenerHash(crearMapaDeLaVistaComprobante(inView, fondoDTO));
		LOGGER.info(MSJ_INFO_GUARDANDO_HASH_EN_SESION);
		sessionParametros.setValidacionHash(hashView);
	}

	/**
	 * Validar hash constitucion plazo fijo.
	 *
	 * @param comprobanteView the comprobante view
	 */
	private void validarHashSuscripcionComprobante(ComprobanteSuscripcionFondo comprobanteView) {
		String inputHash = HashUtils.obtenerHash(crearMapaDeLaVistaFinalizarSuscripcionComprobante(comprobanteView));
		LOGGER.info(MSJ_INFO_VALIDANDO_HASH, sessionParametros.getValidacionHash(), inputHash);
		HashUtils.compareHash(sessionParametros.getValidacionHash(), inputHash);
	}

	/**
	 * Crear mapa de la vista calcular intereses.
	 *
	 * @param comprobanteView the comprobante view
	 * @return the map
	 */
	private Map<String, Object> crearMapaDeLaVistaFinalizarSuscripcionComprobante(
			ComprobanteSuscripcionFondo comprobanteView) {
		LOGGER.info(MSJ_INFO_CREANDO_HASH_ATRIBUTOS);
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		mapaAtributos.put("cuentaTitulos", ISBANStringUtils.formatearNumeroCuenta(comprobanteView.getCuentaTitulo()));
		mapaAtributos.put("nombreFondo", comprobanteView.getFondo());
		mapaAtributos.put("numeroCuentaDebito", comprobanteView.getNumeroCuentaDebito());
		mapaAtributos.put("nroComprobante", comprobanteView.getNumeroComprobante());
		mapaAtributos.put("importe", comprobanteView.getImporte().trim());
		mapaAtributos.put("fecha", comprobanteView.getFechaActual());
		LOGGER.info("String mapa vista: " + mapaAtributos.toString());
		return mapaAtributos;
	}

	/**
	 * Cargar hash de la vista simulacion.
	 *
	 * @param view the view
	 */
	private void crearHashSimularFondosBpriv(SimulacionInView view) {
		String hash = HashUtils.obtenerHash(crearMapSimularFondosBpriv(view));
		LOGGER.info(MSJ_INFO_GUARDANDO_HASH_EN_SESION);
		sessionParametros.setValidacionHash(hash);
	}

	/**
	 * Cargar hash de la vista en sesion.
	 * 
	 * Solo crea un hash de los datos estaticos, es decir, que el usuario no puede
	 * cambiar desde la vista
	 *
	 * @author marcelo.ruiz
	 * @param simulacionView the simulacion view
	 * @return the map
	 */
	private Map<String, Object> crearMapSimularFondosBpriv(SimulacionInView simulacionView) {
		LOGGER.info(MSJ_INFO_CREANDO_HASH_ATRIBUTOS);
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		mapaAtributos.put("codigoFondo", simulacionView.getCodigoFondo());
		mapaAtributos.put("importe", simulacionView.getImporte());
		mapaAtributos.put("nroCuentaBancaPrivada", simulacionView.getNroCuentaBancaPrivada());
		LOGGER.info("String mapa vista: " + mapaAtributos.toString());
		return mapaAtributos;
	}

	/**
	 * Validar hash flujo fondos.
	 *
	 * @param suscripcionInView the suscripcion in view
	 */
	private void validarHashSuscripcionFondosBpriv(SuscripcionInView suscripcionInView) {
		String inputHash = HashUtils.obtenerHash(crearMapFinalizarSuscripcionFondosBpriv(suscripcionInView));
		LOGGER.info(MSJ_INFO_VALIDANDO_HASH, sessionParametros.getValidacionHash(), inputHash);
		HashUtils.compareHash(sessionParametros.getValidacionHash(), inputHash);
	}

	/**
	 * Crear mapa de la vista calcular intereses.
	 *
	 * @param suscripcionInView the suscripcion in view
	 * @return the map
	 */
	private Map<String, Object> crearMapFinalizarSuscripcionFondosBpriv(SuscripcionInView suscripcionInView) {
		LOGGER.info(MSJ_INFO_CREANDO_HASH_ATRIBUTOS);
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		mapaAtributos.put("codigoFondo", suscripcionInView.getCodigoFondo());
		mapaAtributos.put("importe", suscripcionInView.getImporte());
		mapaAtributos.put("nroCuentaBancaPrivada", suscripcionInView.getNroCuentaBancaPrivada());
		LOGGER.info("String mapa vista: " + mapaAtributos.toString());
		return mapaAtributos;
	}

	/**
	 * Cargar hash de la vista simulacion.
	 *
	 * @param inView            the in view
	 * @param suscripcionOutDTO the suscripcion out DTO
	 */
	private void cargarHashDeLaVistaComprobanteBpriv(SuscripcionInView inView, SuscripcionOutDTO suscripcionOutDTO) {
		String hashView = HashUtils.obtenerHash(crearMapaDeLaVistaComprobanteBpriv(inView, suscripcionOutDTO));
		LOGGER.info(MSJ_INFO_GUARDANDO_HASH_EN_SESION);
		sessionParametros.setValidacionHash(hashView);
	}

	/**
	 * Cargar hash de la vista en sesion.
	 * 
	 * <P>
	 * Solo crea un hash de los datos estaticos, es decir, que el usuario no puede
	 * cambiar desde la vista
	 * </P>
	 *
	 * @author marcelo.ruiz
	 * @param inView            the in view
	 * @param suscripcionOutDTO the suscripcion out DTO
	 * @return the map
	 */
	private Map<String, Object> crearMapaDeLaVistaComprobanteBpriv(SuscripcionInView inView,
			SuscripcionOutDTO suscripcionOutDTO) {
		LOGGER.info(MSJ_INFO_CREANDO_HASH_ATRIBUTOS);
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		mapaAtributos.put("cuentaTitulos", ISBANStringUtils.formatearNumeroCuenta(inView.getCuentaTitulos()));
		mapaAtributos.put("nombreFondo", inView.getNombreFondo());
		mapaAtributos.put("numeroCuentaDebito", inView.getNumeroCuentaDebito());
		mapaAtributos.put("nroComprobante", suscripcionOutDTO.getNroCertificado());
		mapaAtributos.put("importe", suscripcionOutDTO.getImporte().trim());
		mapaAtributos.put("fecha", suscripcionOutDTO.getFechaHora());
		LOGGER.info("String mapa vista: " + mapaAtributos.toString());
		return mapaAtributos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.FondoManager
	 * #obtenerRendimiento()
	 */
	@Override
	public Respuesta<RendimientoView> obtenerRendimiento() {
		Respuesta<RendimientoDTO> rtaRendimiento = rendimientoBOImpl
				.obtenerRendimientoTenenciaRTL(sesionCliente.getCliente(), TipoProductoEnum.FONDOS.getCodigo());
		String codigoEstadistica = EstadisticasConstants.RENDIMIENTO_FONDOS;

		RendimientoDTO rendimientoDTO = rtaRendimiento.getRespuesta();
		RendimientoView rendimientoView = createRetornoView(rendimientoDTO);
		rendimientoView.setTooltip(obtenerMensajeRendimiento());

		if (EstadoRespuesta.OK.equals(rtaRendimiento.getEstadoRespuesta())) {
			estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(RendimientoView.class, rendimientoView);
		}
		if (EstadoRespuesta.WARNING.equals(rtaRendimiento.getEstadoRespuesta())) {
			estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL);
			return respuestaFactory.crearRespuestaWarning(rendimientoView, "", TipoError.WARNING_PARCIAL_FONDOS, "");
		}
		estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
	}

	/*
	 * Creates the retorno view.
	 *
	 * @param dto the dto
	 * @return the rendimiento view
	 */
	private RendimientoView createRetornoView(RendimientoDTO dto) {
		RendimientoView outView = new RendimientoView();
		if (dto != null) {
			try {
				BeanUtils.copyProperties(outView, dto);
			} catch (Exception e) {
				LOGGER.error("Error creando View", e);
			}
		}
		return outView;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.FondoManager
	 * #obtenerRendimientoBPriv()
	 */
	@Override
	public Respuesta<RendimientoBPrivView> obtenerRendimientoBPriv() {

		Respuesta<RendimientoBPrivDTO> rtaRendimiento = rendimientoBOImpl
				.obtenerRendimientoTenenciaBPriv(sesionCliente.getCliente(), TipoProductoEnum.FONDOS.getCodigo());
		String codigoEstadistica = EstadisticasConstants.RENDIMIENTO_FONDOS_BP;
		RendimientoBPrivDTO rendimientoBPrivDTO = rtaRendimiento.getRespuesta();
		RendimientoBPrivView rendimientoBPrivView = createRetornoViewBPriv(rendimientoBPrivDTO);
		rendimientoBPrivView.setTooltip(obtenerMensajeRendimiento());

		if (EstadoRespuesta.OK.equals(rtaRendimiento.getEstadoRespuesta())) {
			estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(RendimientoBPrivView.class, rendimientoBPrivView);
		}
		if (EstadoRespuesta.WARNING.equals(rtaRendimiento.getEstadoRespuesta())) {
			estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR_PARCIAL);
			return respuestaFactory.crearRespuestaWarning(rendimientoBPrivView, "", TipoError.WARNING_PARCIAL_FONDOS,
					"");
		}
		estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
	}

	/**
	 * Creates the retorno view B priv.
	 *
	 * @param dto the dto
	 * @return the rendimiento B priv view
	 */
	private RendimientoBPrivView createRetornoViewBPriv(RendimientoBPrivDTO dto) {
		RendimientoBPrivView outView = new RendimientoBPrivView();
		if (dto != null) {
			try {
				BeanUtils.copyProperties(outView, dto);
			} catch (Exception e) {
				LOGGER.error("Error creando View", e);
			}
		}
		return outView;
	}

	@Override
	public Response exportarGrillaFondos(TipoBancaEnum tipoBanca) {

		Respuesta<Reporte> respuestaReporte = fondoBO.obtenerFondosReporte(
				TipoBancaEnum.BANCA_PERSONAL.equals(tipoBanca) ? sessionParametros.getListaCuentasFondos()
						: sessionParametros.getListaCuentasFondosBP(),
				tipoBanca, sesionCliente.getCliente());
		ResponseBuilder responseBuilder = null;
		Respuesta<ReporteView> respuestaView = new Respuesta<ReporteView>();
		if (EstadoRespuesta.OK.equals(respuestaReporte.getEstadoRespuesta())) {
			ReporteView resumenesView = ReporteView.fromReporte(respuestaReporte.getRespuesta());
			respuestaView.setRespuesta(resumenesView);
			respuestaView.setEstadoRespuesta(EstadoRespuesta.OK);
			responseBuilder = Response.ok((Object) respuestaView.getRespuesta().getByteArray());
			responseBuilder.header("Content-Disposition", "attachment; filename=\"TenenciaFondosComunes.xls\"");
			estadisticaManager.add(
					TipoBancaEnum.BANCA_PERSONAL.equals(tipoBanca) ? EstadisticasConstants.DESCARGA_EXCEL_FONDOS_RETAIL
							: EstadisticasConstants.DESCARGA_EXCEL_FONDOS_PRIVADA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			estadisticaManager.add(
					TipoBancaEnum.BANCA_PERSONAL.equals(tipoBanca) ? EstadisticasConstants.DESCARGA_EXCEL_FONDOS_RETAIL
							: EstadisticasConstants.DESCARGA_EXCEL_FONDOS_PRIVADA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			respuestaView = respuestaFactory.crearRespuestaErrorPersonalizado(ReporteView.class, MENSAJE_ERROR_EXCEL,
					TipoError.ERROR_DESCARGA_EXCEL.getDescripcion());
			responseBuilder = Response.ok((Object) respuestaView);
		}
		return responseBuilder.build();

	}

	@Override
	public Respuesta<Void> grabarEstadisticaGoToAGD() {
		estadisticaManager.add(EstadisticasConstants.GOTO_AGD_DESDE_SUSCRIBIR_PASO1_RTL, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(null);
	}
	
	@Override
	public Respuesta<Void> grabarEstadisticaGoToAGDBpriv() {
		estadisticaManager.add(EstadisticasConstants.GOTO_AGD_DESDE_SUSCRIBIR_PASO1_BPRIV, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(null);
	}

	@CacheEvict(value = CacheConstants.Names.CACHE_FONDOS_COTIZACIONES, condition = "#clean")
	public void cleanCacheConsultarCotizaciones(boolean clean) {
		if(clean) LOGGER.info("Limpiando cache {}.", CacheConstants.CACHE_FONDOS_COTIZACIONES);
	}
}
