/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.manager;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.prototype.FondosTenenciasPrototype;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
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
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.TipoMonedaInversionEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.manager.BaseManager;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.ComprobanteRescateFondo;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DatosComprobante;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RescateDesdeGrillaInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.bo.FondoBO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.bo.RescateBO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentaConTenenciaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentasConsultaFondoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FinalizarRescateBPrivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FinalizarRescateBPrivInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FinalizarRescateDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FinalizarRescateInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SimulacionInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SimularRescateInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SimularRescateOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TenenciasFondoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RescateFondoRsa;
import ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager;

/**
 * The Class RescateManagerImpl.
 *
 * @author b039920
 */
@Component("rescateManager")
public class RescateManagerImpl extends BaseManager implements RescateManager {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RescateManagerImpl.class);

	/** The Constant BANCA_PRIVADA. */
	private static final String BANCA_PRIVADA = "BP";

	/** The Constant BANCA_RETAIL. */
	private static final String BANCA_RETAIL = "BR";

	/** The Constant MENSAJE_LEGALES_CONFIGURACION_SUSCRIPCION. */
	private static final String MENSAJE_LEGALES_CONFIGURACION_SUSCRIPCION = "50017";

	/** The Constant MENSAJE_LEGALES_COMPROBANTE_RESCATE. */
	private static final String MENSAJE_LEGALES_COMPROBANTE_RESCATE = "50014";

	private static final String SUPER_AHORRO_PESOS_CUOTA = "SUPER AHORRO $ CUOTA";

	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The cuenta manager. */
	@Autowired
	private CuentaManager cuentaManager;

	/** The Rescate BO. */
	@Autowired
	private RescateBO rescateBO;

	/** The fondo BO. */
	@Autowired
	private FondoBO fondoBO;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The session parametros. */
	@Autowired
	private SesionParametros sessionParametros;

	/** The rsa manager. */
	@Autowired
	private RsaManager rsaManager;

	/** The reporte BO. */
	@Autowired
	private ReporteComprobantePDFBO reporteBO;

	/** The rescate Fondo Especie. */
	@Autowired
	private RescateFondoEspecie rescateFondoEspecie;

	/** Fondos tenencias singleton */
	@Autowired
	private FondosTenenciasPrototype fondosTenenciasPrototype;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.
	 * RescateManager#configuracionRescate(ar.com.santanderrio.obp.servicios.
	 * inversiones.fondos.view.ConfiguracionRescateInView)
	 */
	@Override
	public Respuesta<ConfiguracionRescateOutView> configuracionRescate(ConfiguracionRescateInView viewRequest) {

		if(!validarHorarioDeFondo(viewRequest.getCodigoFondo())) return respuestaFactory
				.crearRespuestaWarning(fondoBO.crearItemsWarningFueraHorarioAgendarFondo(), new ConfiguracionRescateOutView());

		LOGGER.info("Invocando al manager de configuracionRescate");
		Respuesta<ConfiguracionRescateOutView> respuesta = super.validate(viewRequest,
				new ConfiguracionRescateOutView());
		if (!respuesta.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.SUSCRIPCION_FONDO_FALLO_GENERICO);
		}

		Respuesta<CuentasView> respuestaCuentas = cuentaManager.getCuentasSaldoPorMoneda(viewRequest.getMoneda());
		Respuesta<String> respuestaLegales = legalBO.buscarLegal(MENSAJE_LEGALES_CONFIGURACION_SUSCRIPCION);
		Respuesta<String> respuestaLegalesEspecie = legalBO
				.buscarLegal(CodigoMensajeConstantes.CODIGO_LEGAL_RESCATE_POR_ESPECIE);
		if (validarRespuesta(respuestaCuentas, respuestaLegales)
				|| validarRespuesta(respuestaCuentas, respuestaLegalesEspecie)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		List<CuentasAdhesionDebitoView> cuentasDestino = respuestaCuentas.getRespuesta().getCuentas();

		List<Cuenta> cuentasCepo = cuentaManager.getCuentasCepo(sesionCliente.getCliente());
		List<Cuenta> cuentasAnses = getCuentasAnses(sesionCliente.getCliente());

		List<CuentasAdhesionDebitoView> cuentasDestinoFiltradas = filtrarCuentasCepo(cuentasCepo, cuentasDestino);
		cuentasDestino = filtrarCuentasAnses(cuentasAnses, cuentasDestinoFiltradas);

		if (cuentasDestino.isEmpty()) {
			String moneda = TipoMonedaInversionEnum.fromSimboloString(viewRequest.getMoneda()).getMonedas();
			String mensaje = mensajeBO
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_SIN_CUENTA_MONEDA_RESCATE_FONDO)
					.getMensaje();
			mensaje = MessageFormat.format(mensaje, moneda);
			return respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(ConfiguracionRescateOutView.class,
					mensaje, TipoError.SIN_CUENTAS_TIPO_MONEDA.getDescripcion());
		}
		ConfiguracionRescateOutView responseView = new ConfiguracionRescateOutView();
		responseView.setCuentasDestino(cuentasDestino);
		if (EstadoRespuesta.WARNING.equals(respuestaCuentas.getEstadoRespuesta())) {
			for (CuentasAdhesionDebitoView cuenta : responseView.getCuentasDestino()) {
				cuenta.setSaldoPesos("-");
				cuenta.setSaldoDolares("-");
			}
		}
		Respuesta<String> respuestaTooltip = legalBO
				.buscarLegal(CodigoMensajeConstantes.CODIGO_TOOLTIP_RESCATE_POR_ESPECIE);
		if (EstadoRespuesta.OK.equals(respuestaTooltip.getEstadoRespuesta())) {
			responseView.setTooltip(respuestaTooltip.getRespuesta());
		}
		responseView.setLegales(respuestaLegales.getRespuesta());
		responseView.setLegalesEspecie(respuestaLegalesEspecie.getRespuesta());
		responseView=inyectaRepatriacion(responseView);
		return respuestaFactory.crearRespuestaOk(ConfiguracionRescateOutView.class, responseView);
	}

	private ConfiguracionRescateOutView inyectaRepatriacion(ConfiguracionRescateOutView responseView) {
		List<Cuenta> ctasOp=sesionCliente.getCliente().getCuentas();
		List<CuentasAdhesionDebitoView> ctasDeb=responseView.getCuentasDestino();
		
		for(CuentasAdhesionDebitoView ctaDeb : ctasDeb) {
			for(Cuenta ctaOp : ctasOp) {
				if(ctaOp.getCbu().equals(ctaDeb.getCbu())) {
					ctaDeb.setRepatriacion(ctaOp.isRepatriacion());
				}
			}
		}
		
		return responseView;
	}

	private boolean validarRespuesta(Respuesta<CuentasView> respuestaCuentas, Respuesta<String> respuestaLegales) {
		return respuestaCuentas.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)
				|| !respuestaLegales.getEstadoRespuesta().equals(EstadoRespuesta.OK);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.
	 * RescateManager#configuracionRescateBPriv(ar.com.santanderrio.obp.
	 * servicios.inversiones.fondos.view.ConfiguracionRescateBPrivInView)
	 */
	@Override
	public Respuesta<ConfiguracionRescateOutView> configuracionRescateBPriv(
			ConfiguracionRescateBPrivInView viewRequest) {

		if(!validarHorarioDeFondo(viewRequest.getCodigoFondo())) return respuestaFactory
				.crearRespuestaWarning(fondoBO.crearItemsWarningFueraHorarioAgendarFondo(), new ConfiguracionRescateOutView());

		LOGGER.info("Invocando al manager de configuracionRescateBPriv");
		Respuesta<ConfiguracionRescateOutView> respuesta = super.validate(viewRequest,
				new ConfiguracionRescateOutView());
		if (!respuesta.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.SUSCRIPCION_FONDO_FALLO_GENERICO);
		}
		Respuesta<String> respuestaLegales =
				legalBO.buscarLegal(MENSAJE_LEGALES_CONFIGURACION_SUSCRIPCION);
		Respuesta<String> respuestaLegalesEspecie =
				legalBO.buscarLegal(CodigoMensajeConstantes.CODIGO_LEGAL_RESCATE_POR_ESPECIE);

		if (!respuestaLegales.getEstadoRespuesta().equals(EstadoRespuesta.OK)
				|| !respuestaLegalesEspecie.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		Respuesta<CuentasAdhesionDebitoView> cuentaConSaldo = fondoBO
				.obtenerSaldosCuentaOperativa(viewRequest.getCuentaOperativa(), sesionCliente.getCliente(), true);
		List<CuentasAdhesionDebitoView> cuentasDestino = new ArrayList<CuentasAdhesionDebitoView>();
		cuentasDestino.add(cuentaConSaldo.getRespuesta());

		ConfiguracionRescateOutView responseView = new ConfiguracionRescateOutView();
		responseView.setCuentasDestino(cuentasDestino);
		responseView.setLegales(respuestaLegales.getRespuesta());
		responseView.setLegalesEspecie(respuestaLegalesEspecie.getRespuesta());

		Respuesta<String> respuestaTooltip = legalBO
				.buscarLegal(CodigoMensajeConstantes.CODIGO_TOOLTIP_RESCATE_POR_ESPECIE);
		if (EstadoRespuesta.OK.equals(respuestaTooltip.getEstadoRespuesta())) {
			responseView.setTooltip(respuestaTooltip.getRespuesta());
		}

		return respuestaFactory.crearRespuestaOk(ConfiguracionRescateOutView.class, responseView);
	}

	/**
	 * Creates the rescate citi DTO.
	 *
	 * @param viewRequest the view request
	 * @return the finalizar rescate in DTO
	 */
	private FinalizarRescateInDTO createRescateCitiDTO(FinalizarRescateCitiInView viewRequest) {
		FinalizarRescateInDTO finalizarRescateInDTO = new FinalizarRescateInDTO();

		try {
			finalizarRescateInDTO.setCodigoFondo(viewRequest.getCodigoFondo());
			finalizarRescateInDTO.setNombreFondo(viewRequest.getNombreFondo());
			finalizarRescateInDTO.setCuentaTitulo(viewRequest.getNroCuentaTitulos());
			finalizarRescateInDTO.setExCiti(FinalizarRescateCitiInView.getExciti());
			finalizarRescateInDTO.setImporte(viewRequest.getImporteNeto());
			finalizarRescateInDTO.setMoneda(viewRequest.getMoneda());
			finalizarRescateInDTO.setNumeroCtaCred(viewRequest.getNumeroCuenta());
			finalizarRescateInDTO.setSucursalCtaCred(viewRequest.getSucursalCuenta());
			finalizarRescateInDTO.setTipoCtaCred(viewRequest.getTipoCuenta());
		} catch (Exception e) {
			LOGGER.error("Error creando DTO", e);
		}

		return finalizarRescateInDTO;
	}

	/**
	 * Crea el DTO para el llamado al BO a partir del view para la suscripcion.
	 *
	 * @param viewRequest the view request
	 * @return the finalizar rescate in DTO
	 */
	private FinalizarRescateInDTO createRescateDTO(FinalizarRescateInView viewRequest) {
		FinalizarRescateInDTO finalizarRescateInDTO = new FinalizarRescateInDTO();

		try {
			BeanUtils.copyProperties(finalizarRescateInDTO, viewRequest);

		} catch (Exception e) {
			LOGGER.error("Error creando DTO", e);
		}

		return finalizarRescateInDTO;
	}

	/**
	 * crear un FinalizarRescateView para retornar al RescateSEI.
	 *
	 * @param dto the dto
	 * @return FinalizarRescateView.
	 */
	private FinalizarRescateView createRetornoView(FinalizarRescateDTO dto) {
		FinalizarRescateView viewReturn = new FinalizarRescateView();
		if (dto != null) {
			viewReturn.setImporteRescateNeto(dto.getImporteRescateNeto());
			viewReturn.setMoneda(dto.getMoneda());
			viewReturn.setNombreFondo(dto.getNombreFondo());
			viewReturn.setNroRescate(dto.getNroRescate());
			viewReturn.setTotalCuotasPartes(dto.getTotalCuotasPartes());
			viewReturn.setMensajeSuscripcion(dto.getMensajeSuscripcion());
			viewReturn.setFechaHora(dto.getFechaHora());
		}
		return viewReturn;
	}

	/**
	 * Efectuar logica ok B rescaste.
	 *
	 * @param respuestaBO the respuesta BO
	 * @return the respuesta
	 */
	private Respuesta<FinalizarRescateView> efectuarLogicaOkBRescaste(Respuesta<FinalizarRescateDTO> respuestaBO) {
		estadisticaManager.add(EstadisticasConstants.CODIGO_CONFIRMAR_RESCATE,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		FinalizarRescateView view = createRetornoView(respuestaBO.getRespuesta());
		return respuestaFactory.crearRespuestaOk(FinalizarRescateView.class, view);
	}

	/**
	 * Efectuar logica ok B rescaste.
	 *
	 * @param respuestaBO the respuesta BO
	 * @return the respuesta
	 */
	private Respuesta<FinalizarRescateView> efectuarLogicaOkBRescasteExCiti(
			Respuesta<FinalizarRescateDTO> respuestaBO) {
		estadisticaManager.add(EstadisticasConstants.FINALIZAR_RESCATE_EX_CITI,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

		FinalizarRescateView view = createRetornoView(respuestaBO.getRespuesta());
		return respuestaFactory.crearRespuestaOk(FinalizarRescateView.class, view);
	}

	/**
	 * Efectuar logica error rescate.
	 *
	 * @param respuestaBO the respuesta BO
	 * @return the respuesta
	 */
	private Respuesta<FinalizarRescateView> efectuarLogicaErrorRescate(Respuesta<FinalizarRescateDTO> respuestaBO) {
		estadisticaManager.add(EstadisticasConstants.CODIGO_CONFIRMAR_RESCATE,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
	}

	/**
	 * Efectuar logica error rescate.
	 *
	 * @param respuestaBO the respuesta BO
	 * @return the respuesta
	 */
	private Respuesta<FinalizarRescateView> efectuarLogicaErrorRescateExCiti(
			Respuesta<FinalizarRescateDTO> respuestaBO) {
		estadisticaManager.add(EstadisticasConstants.FINALIZAR_RESCATE_EX_CITI,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		if (TipoError.ERROR_SERVICIO_CERRADO_EXCITI.getDescripcion()
				.equals(respuestaBO.getItemsMensajeRespuesta().get(0).getTipoError())) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_RESCATE_FONDO_CITI);
		}

		if (TipoError.ERROR_RESCATE_FONDO_CITI_COMPASS.getDescripcion()
				.equals(respuestaBO.getItemsMensajeRespuesta().get(0).getTipoError())) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_RESCATE_FONDO_CITI_COMPASS);
		}
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.RescateManager#
	 * finalizarRescateCiti(ar.com.santanderrio.obp.servicios.inversiones.fondos.
	 * view.FinalizarRescateCitiInView)
	 */
	@Override
	public Respuesta<FinalizarRescateView> finalizarRescateCiti(FinalizarRescateCitiInView viewRequest) {

		validarHashRescateFondosCiti(viewRequest);

		Respuesta<FinalizarRescateView> respuesta = super.validate(viewRequest, new FinalizarRescateView());
		if (!respuesta.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.SUSCRIPCION_FONDO_FALLO_GENERICO);
		}

		rsaManager.analizar(new RescateFondoRsa(), null);

		Respuesta<FinalizarRescateDTO> respuestaBO = rescateBO.finalizarRescateFDC(createRescateCitiDTO(viewRequest),
				sesionCliente.getCliente());

		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			crearHashContinuarConfirmacionRescateExciti(respuestaBO.getRespuesta(), viewRequest);
			return efectuarLogicaOkBRescasteExCiti(respuestaBO);
		}
		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
			return respuestaFactory.crearRespuestaWarning(FinalizarRescateView.class, new FinalizarRescateView(),
					respuestaBO.getItemsMensajeRespuesta());
		} else {
			return efectuarLogicaErrorRescateExCiti(respuestaBO);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.
	 * RescateManager#finalizarRescate(ar.com.santanderrio.obp.servicios.
	 * inversiones.fondos.view.FinalizarRescateInView)
	 */
	@Override
	public Respuesta<FinalizarRescateView> finalizarRescate(FinalizarRescateInView viewRequest) {

		if (sesionCliente.getCliente() != null) {
			// Vaciar cache de la transaccion CNSTENVAL 110
			fondosTenenciasPrototype.cleanCacheConsultaTenenciaFCI(sesionCliente.getCliente());
		}

		if (viewRequest.isEsEspecie()) {
			estadisticaManager.add(EstadisticasConstants.ACEPTACION_TERMINOS_Y_CONDICIONES_RESCATE_POR_ESPECIE_RTL,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			Respuesta<FinalizarRescateView> rta = rescateBO.finalizarRescateFondoPorEspecie(viewRequest,
					sesionCliente.getCliente(), "RTL");
			grabarEstadisticaSegunBanca(rta.getEstadoRespuesta(), "RTL");
			return rta;
		}
		validarHashRescateFondos(viewRequest);

		Respuesta<FinalizarRescateView> respuesta = super.validate(viewRequest, new FinalizarRescateView());
		if (!respuesta.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.SUSCRIPCION_FONDO_FALLO_GENERICO);
		}

		rsaManager.analizar(new RescateFondoRsa(), null);
		Respuesta<FinalizarRescateDTO> respuestaBO = rescateBO.finalizarRescate(createRescateDTO(viewRequest),
				sesionCliente.getCliente());

		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			cargarHashDeLaVistaComprobante(viewRequest, respuestaBO.getRespuesta());
			return efectuarLogicaOkBRescaste(respuestaBO);
		}
		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
			return respuestaFactory.crearRespuestaWarning(FinalizarRescateView.class, new FinalizarRescateView(),
					respuestaBO.getItemsMensajeRespuesta());
		} else {
			return efectuarLogicaErrorRescate(respuestaBO);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.
	 * RescateManager#simularRescateFondo(ar.com.santanderrio.obp.servicios.
	 * inversiones.fondos.view.SimulacionRescateInView)
	 */
	@Override
	public Respuesta<SimulacionRescateOutView> simularRescateFondo(SimulacionRescateInView viewRequest) {
		return simularRescateSegunBanca(viewRequest, BANCA_RETAIL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.
	 * RescateManager#simularRescateFondoBPriv(ar.com.santanderrio.obp.servicios
	 * .inversiones.fondos.view.SimulacionRescateInView)
	 */
	@Override
	public Respuesta<SimulacionRescateOutView> simularRescateFondoBPriv(SimulacionRescateInView viewRequest) {
		return simularRescateSegunBanca(viewRequest, BANCA_PRIVADA);
	}

	/**
	 * Simular rescate segun banca.
	 *
	 * @param viewRequest the view request
	 * @param tipoBanca   the tipo banca
	 * @return the respuesta
	 */
	private Respuesta<SimulacionRescateOutView> simularRescateSegunBanca(SimulacionRescateInView viewRequest,
			String tipoBanca) {

		if (viewRequest.isEsEspecie()) {
			if (!validarHorarioFondo(viewRequest.getCodigoFondo())) {
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_FUERAHORARIO_ESPECIE,
						CodigoMensajeConstantes.ERROR_SERVICIO_RESCATE_FUERA_DE_HORARIO);
			}
			SimulacionRescateOutView simulacion = new SimulacionRescateOutView();

			LOGGER.info("Codigo legal rescate por especie:"+CodigoMensajeConstantes.CODIGO_LEGAL_RESCATE_POR_ESPECIE);
			Respuesta<String> respuestaLegalEspecie = legalBO
					.buscarLegal(CodigoMensajeConstantes.CODIGO_LEGAL_RESCATE_POR_ESPECIE);

			if (!respuestaLegalEspecie.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
						CodigoMensajeConstantes.SUSCRIPCION_FONDO_FALLO_GENERICO);
			}
			simulacion.setLegales(respuestaLegalEspecie.getRespuesta());

			return respuestaFactory.crearRespuestaOk(SimulacionRescateOutView.class, simulacion);
		}

		Respuesta<SimulacionRescateOutView> respuesta = super.validate(viewRequest, new SimulacionRescateOutView());
		if (!respuesta.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			grabarEstadisticaSegunBancaSimulacionRescate(tipoBanca, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.SUSCRIPCION_FONDO_FALLO_GENERICO);
		}

		crearHashSimularRescateFondos(viewRequest);

		try {
			Respuesta<SimularRescateOutDTO> rtaBO;
			if (BANCA_RETAIL.equals(tipoBanca)) {
				rtaBO = rescateBO.simularRescateFondo(generateDTO(viewRequest), sesionCliente.getCliente());
			} else {
				rtaBO = rescateBO.simularRescateFondoBPriv(crearDTOBPriv(viewRequest), sesionCliente.getCliente());
			}

			if (rtaBO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
				LOGGER.info("Codigo para el legal de la simulacion del rescate para el FCI de Super Ahorro Pesos:"+
						CodigoMensajeConstantes.CODIGO_LEGAL_RESCATE_SIMULACION_SUPER_AHORRO_PESOS);
				Respuesta<String> respuestaLegalSuperAhorroPesos = legalBO
						.buscarLegal(CodigoMensajeConstantes.CODIGO_LEGAL_RESCATE_SIMULACION_SUPER_AHORRO_PESOS);
				rtaBO.getRespuesta().setLegalSuperAhorroPesos(respuestaLegalSuperAhorroPesos.getRespuesta());
			}

			return manejarRtaSimularRescate(rtaBO, tipoBanca);
		} catch (BusinessException e) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
	}

	private boolean validarHorarioFondo(String codigoFondo) {
		DateTimeFormatter df = DateTimeFormat.forPattern("HH:mm");

		String horaInicio = rescateFondoEspecie.getFondosMap().get(codigoFondo).getHorarioInicio();
		String horaFin = rescateFondoEspecie.getFondosMap().get(codigoFondo).getHorarioFin();

		DateTime horarioBancarioInicio = df.parseLocalTime(horaInicio).toDateTimeToday();
		DateTime horarioBancarioFinal = df.parseLocalTime(horaFin).toDateTimeToday();

		Interval intervaloFueraHorario = new Interval(horarioBancarioInicio, horarioBancarioFinal);
		return intervaloFueraHorario.contains(new DateTime());
	}

	/**
	 * Maneja la respuesta del servicio simular rescate.
	 *
	 * @param rtaBO the rta BO
	 * @return the respuesta
	 */
	private Respuesta<SimulacionRescateOutView> manejarRtaSimularRescate(Respuesta<SimularRescateOutDTO> rtaBO,
			String tipoBanca) {
		if (rtaBO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			SimularRescateOutDTO dto = rtaBO.getRespuesta();
			grabarEstadisticaSegunBancaSimulacionRescate(tipoBanca, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(SimulacionRescateOutView.class, generateView(dto));
		} else {
			if (rtaBO.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
				Respuesta<SimulacionRescateOutView> warning = new Respuesta<SimulacionRescateOutView>();
				warning.setItemMensajeRespuesta(rtaBO.getItemsMensajeRespuesta());
				warning.setEstadoRespuesta(rtaBO.getEstadoRespuesta());
				grabarEstadisticaSegunBancaSimulacionRescate(tipoBanca,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR_FH);
				return warning;
			} else {
				List<ItemMensajeRespuesta> itemsMensajeRespuesta = rtaBO.getItemsMensajeRespuesta();
				grabarEstadisticaSegunBancaSimulacionRescate(tipoBanca,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				if (itemsMensajeRespuesta.get(0).getTipoError()
						.equals(TipoError.SERVICIO_FONDOS_DESHABILITADO.getDescripcion())) {
					return respuestaFactory.crearRespuestaError("", TipoError.SERVICIO_FONDOS_DESHABILITADO,
							CodigoMensajeConstantes.ERROR_SERVICIO_RESCATE_FONDOS_DESHABILITADO);
				} else {
					return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
							CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
				}
			}
		}
	}

	/**
	 * Crear DTOB priv.
	 *
	 * @param viewRequest the view request
	 * @return the simulacion in DTO
	 */
	private SimulacionInDTO crearDTOBPriv(SimulacionRescateInView viewRequest) {
		SimulacionInDTO simulacionDTO = new SimulacionInDTO();
		simulacionDTO.setCodigoFondo(viewRequest.getCodigoFondo());
		simulacionDTO.setImporte(ISBANStringUtils.convertirABigDecimal(viewRequest.getImporteNeto().replace(".", ",")));
		simulacionDTO.setNroCuentaBancaPrivada(viewRequest.getNumeroCuenta());

		return simulacionDTO;
	}

	/**
	 * Creo el dto de entrada el BO a partir del view.
	 *
	 * @param view the view
	 * @return the simular rescate in DTO
	 * @throws BusinessException the business exception
	 */
	private SimularRescateInDTO generateDTO(SimulacionRescateInView view) throws BusinessException {
		SimularRescateInDTO dto = new SimularRescateInDTO();
		try {
			BeanUtils.copyProperties(dto, view);
			TipoCuenta tipoCuenta = TipoCuenta.fromAbreviatura(view.getTipoCuenta());
			dto.setNumeroCuenta(view.getNumeroCuenta().replaceAll("/", ""));
			dto.setTipoCuenta(tipoCuenta.getCodigo().toString());
			String cuentaTitulos = view.getNroCuentaTitulos().replaceAll("/", "");
			dto.setCodigoCliente(cuentaTitulos);

		} catch (IllegalAccessException e) {
			LOGGER.error("Error generando DTO para llamado a BO", e);
			throw new BusinessException(e);
		} catch (InvocationTargetException e) {
			LOGGER.error("Error generando DTO para llamado a BO", e);
			throw new BusinessException(e);
		}
		return dto;
	}

	/**
	 * Genera el view de salida a partir de la rta del BO.
	 *
	 * @param dto the dto
	 * @return the simulacion rescate out view
	 */
	private SimulacionRescateOutView generateView(SimularRescateOutDTO dto) {
		SimulacionRescateOutView view = new SimulacionRescateOutView();
		view.setLegales(dto.getLegales());
		view.setLegalSuperAhorroPesos(dto.getLegalSuperAhorroPesos());
		view.setCuotaPartes(dto.getCuotaPartes());
		view.setImporteRescateComision(dto.getImporteRescateComision());
		view.setImporteRescateNeto(dto.getImporteRescateNeto());
		view.setDisclaimer(dto.getDisclaimer());
		view.setDentroDelPerfil(dto.getDentroDelPerfil());
		return view;
	}

	/**
	 * Efectuar logica ok obtener fondos suscriptos.
	 *
	 * @param respuestaBO       the respuesta BO
	 * @param codigoEstadistica the codigo estadistica
	 * @return the respuesta
	 */
	private Respuesta<RescateView> efectuarLogicaOkObtenerFondosSuscriptos(
			Respuesta<CuentasConsultaFondoDTO> respuestaBO, String codigoEstadistica) {
		estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(RescateView.class, dtoToView(respuestaBO.getRespuesta()));

	}

	/**
	 * Efectuar logica error obtener fondos suscriptos.
	 *
	 * @param respuestaBO       the respuesta BO
	 * @param codigoEstadistica the codigo estadistica
	 * @return the respuesta
	 */
	private Respuesta<RescateView> efectuarLogicaErrorObtenerFondosSuscriptos(
			Respuesta<CuentasConsultaFondoDTO> respuestaBO, String codigoEstadistica) {
		estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);

		List<ItemMensajeRespuesta> itemsMensajeRespuesta = respuestaBO.getItemsMensajeRespuesta();
		if (itemsMensajeRespuesta.get(0).getTipoError()
				.equals(TipoError.SERVICIO_FONDOS_DESHABILITADO.getDescripcion())) {
			return respuestaFactory.crearRespuestaError("", TipoError.SERVICIO_FONDOS_DESHABILITADO,
					CodigoMensajeConstantes.ERROR_SERVICIO_FONDOS_DESHABILITADO);
		} else {
			return respuestaFactory.crearRespuestaError(RescateView.class,
					respuestaBO.getItemsMensajeRespuesta().get(0).getMensaje(), "");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.
	 * RescateManager#obtenerFondosSuscriptos(ar.com.santanderrio.obp.servicios.
	 * inversiones.fondos.view.RescateInView)
	 */
	@Override
	public Respuesta<RescateView> obtenerFondosSuscriptos(RescateInView viewRequest) {
		sessionParametros.setContador(new ContadorIntentos(2));
		Respuesta<RescateView> respuesta = super.validate(viewRequest, new RescateView());
		if (!respuesta.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.SUSCRIPCION_FONDO_FALLO_GENERICO);
		}
		LOGGER.info("Invocando al BO obtenerFondosSuscriptos");
		CuentasConsultaFondoDTO requestDTO = viewToDTO(viewRequest.getCuentasTitulo(), viewRequest.getTipoBanca());
		Respuesta<CuentasConsultaFondoDTO> respuestaBO = rescateBO.obtenerFondosSuscriptos(requestDTO,
				sesionCliente.getCliente());

		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return efectuarLogicaOkObtenerFondosSuscriptos(respuestaBO, EstadisticasConstants.CONFIGURACION_RESCATE);
		}
		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
			if (TipoError.FUERADEHORARIO.toString()
					.equals(respuestaBO.getItemsMensajeRespuesta().get(0).getTipoError())) {
				estadisticaManager.add(EstadisticasConstants.FUERA_HORARIO_RESCATE_PASO1_RTL,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR_FH);
			}
			return respuestaFactory.crearRespuestaWarning(RescateView.class, new RescateView(),
					respuestaBO.getItemsMensajeRespuesta());
		} else {
			return efectuarLogicaErrorObtenerFondosSuscriptos(respuestaBO, EstadisticasConstants.CONFIGURACION_RESCATE);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.
	 * RescateManager#obtenerFondosSuscriptosBPriv(ar.com.santanderrio.obp.
	 * servicios.inversiones.fondos.view.RescateInView)
	 */
	@Override
	public Respuesta<RescateView> obtenerFondosSuscriptosBPriv(RescateInView viewRequest) {
		sessionParametros.setContador(new ContadorIntentos(2));
		LOGGER.info("Validando parametros de entrada");
		Respuesta<RescateView> respuesta = super.validate(viewRequest, new RescateView());
		if (!respuesta.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.SUSCRIPCION_FONDO_FALLO_GENERICO);
		}

		CuentasConsultaFondoDTO requestDTO = viewToDTO(viewRequest.getCuentasTitulo(), viewRequest.getTipoBanca());
		LOGGER.info("Invocando al BO obtenerFondosSuscriptosBPriv");
		Respuesta<CuentasConsultaFondoDTO> respuestaBO = rescateBO.obtenerFondosSuscriptosBPriv(requestDTO,
				sesionCliente.getCliente());

		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return efectuarLogicaOkObtenerFondosSuscriptos(respuestaBO,
					EstadisticasConstants.CONFIGURACION_RESCATE_BPRIV);
		}

		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
			if (TipoError.FUERADEHORARIO.toString()
					.equals(respuestaBO.getItemsMensajeRespuesta().get(0).getTipoError())) {
				estadisticaManager.add(EstadisticasConstants.FUERA_HORARIO_RESCATE_PASO1_BPRIV,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR_FH);
			}
			return respuestaFactory.crearRespuestaWarning(RescateView.class, new RescateView(),
					respuestaBO.getItemsMensajeRespuesta());
		}

		return efectuarLogicaErrorObtenerFondosSuscriptos(respuestaBO,
				EstadisticasConstants.CONFIGURACION_RESCATE_BPRIV);
	}

	/**
	 * Dto to view.
	 *
	 * @param cuentasRetornoBO the cuentas retorno BO
	 * @return the rescate view
	 */
	private RescateView dtoToView(CuentasConsultaFondoDTO cuentasRetornoBO) {
		RescateView retornoView = new RescateView();
		retornoView.setMensajeInformacion(cuentasRetornoBO.getMensajeInformacion());
		retornoView.setTipoBanca(cuentasRetornoBO.getTipoBanca());

		List<CuentaTituloView> cuentasTituloView = obtenerCuentasTituloView(cuentasRetornoBO);

		retornoView.setCuentasTitulo(cuentasTituloView);
		return retornoView;
	}

	/**
	 * {@inheritDoc}
	 */
	public Respuesta<FinalizarRescateView> finalizarRescateBPriv(FinalizarRescateBPrivInView viewRequest) {

		if (viewRequest.isEsEspecie()) {
			estadisticaManager.add(EstadisticasConstants.ACEPTACION_TERMINOS_Y_CONDICIONES_RESCATE_POR_ESPECIE_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			Respuesta<FinalizarRescateView> rta = rescateBO.finalizarRescateFondoPorEspecieBPriv(viewRequest,
					sesionCliente.getCliente(), "BP");
			grabarEstadisticaSegunBanca(rta.getEstadoRespuesta(), "BP");
			return rta;
		}

		String transtactionToken = sessionParametros.getTransactionTokenRedemptionFund();
		// Verificar si viene del bff de rescate
		if (transtactionToken == null) {
			Respuesta<FinalizarRescateView> respuesta = super.validate(viewRequest, new FinalizarRescateView());
			if (!respuesta.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
						CodigoMensajeConstantes.SUSCRIPCION_FONDO_FALLO_GENERICO);
			}
			validarHashRescateFondos(viewRequest);
			rsaManager.analizar(new RescateFondoRsa(), null);
		}

		Respuesta<FinalizarRescateBPrivDTO> respuestafinalizarRescateBPriv = rescateBO
				.finalizarRescateBPriv(createRescateDTOBPriv(viewRequest), sesionCliente.getCliente());
		if (EstadoRespuesta.OK.equals(respuestafinalizarRescateBPriv.getEstadoRespuesta())) {
			cargarHashDeLaVistaComprobanteBpriv(viewRequest, respuestafinalizarRescateBPriv.getRespuesta());
			FinalizarRescateBPrivDTO finalizarRescateBPrivDTO = respuestafinalizarRescateBPriv.getRespuesta();
			FinalizarRescateView finalizarRescateView = dtoFinalizarRescateBPrivToView(finalizarRescateBPrivDTO,
					viewRequest);
			estadisticaManager.add(EstadisticasConstants.CODIGO_CONFIRMAR_RESCATE_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(FinalizarRescateView.class, finalizarRescateView);
		}
		if (EstadoRespuesta.ERROR.equals(respuestafinalizarRescateBPriv.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.CODIGO_CONFIRMAR_RESCATE_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(FinalizarRescateView.class,
					respuestafinalizarRescateBPriv.getItemsMensajeRespuesta().get(0).getMensaje(),
					respuestafinalizarRescateBPriv.getItemsMensajeRespuesta().get(0).getTag());
		} else {
			return respuestaFactory.crearRespuestaWarning(FinalizarRescateView.class, new FinalizarRescateView(),
					respuestafinalizarRescateBPriv.getItemsMensajeRespuesta());
		}

	}

	private void grabarEstadisticaSegunBanca(EstadoRespuesta estadoRespuesta, String banca) {
		String estadistica = "BP".equals(banca) ? EstadisticasConstants.FINALIZAR_Y_FEEDBACK_RESCATE_POR_ESPECIE_BPRIV
				: EstadisticasConstants.FINALIZAR_Y_FEEDBACK_RESCATE_POR_ESPECIE_RTL;
		String estado = EstadoRespuesta.OK.equals(estadoRespuesta) ? EstadisticasConstants.CODIGO_ESTADISTICAS_OK
				: EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR;
		estadisticaManager.add(estadistica, estado);
	}

	private void grabarEstadisticaSegunBancaSimulacionRescate(String banca, String codigoError) {
		String estadistica = "BP".equals(banca) ? EstadisticasConstants.CONFIRMACION_RESCATE_FONDO_BPRIV
				: EstadisticasConstants.CONFIRMACION_RESCATE_FONDO_RTL;
		estadisticaManager.add(estadistica, codigoError);
	}

	/**
	 * Dto finalizar rescate B priv to view.
	 *
	 * @param finalizarRescateBPrivDTO the finalizar rescate B priv DTO
	 * @param viewRequest              the view request
	 * @return the finalizar rescate view
	 */
	private FinalizarRescateView dtoFinalizarRescateBPrivToView(FinalizarRescateBPrivDTO finalizarRescateBPrivDTO,
			FinalizarRescateBPrivInView viewRequest) {
		FinalizarRescateView finalizarRescateView = new FinalizarRescateView();
		finalizarRescateView.setImporteRescateNeto(finalizarRescateBPrivDTO.getImporte());
		finalizarRescateView.setMensajeSuscripcion(finalizarRescateBPrivDTO.getMensajeSuscripcion());
		finalizarRescateView.setNroRescate(finalizarRescateBPrivDTO.getNroCertificado());
		// La moneda y el nombre del fondo no los devuelve el servicio por eso
		// se agregan de lo que viene del front
		finalizarRescateView.setMoneda(viewRequest.getMoneda());
		finalizarRescateView.setNombreFondo(viewRequest.getNombreFondo());
		finalizarRescateView.setFechaHora(finalizarRescateBPrivDTO.getFechaHora());
		finalizarRescateView.setTotalCuotasPartes(finalizarRescateBPrivDTO.getCuotaPartes());
		return finalizarRescateView;
	}

	/**
	 * Crea el DTO para el llamado al BO a partir del view para el rescate en banca
	 * provada.
	 *
	 * @param viewRequest the view request
	 * @return the finalizar rescate B priv in DTO
	 */
	private FinalizarRescateBPrivInDTO createRescateDTOBPriv(FinalizarRescateBPrivInView viewRequest) {
		FinalizarRescateBPrivInDTO finalizarRescateInDTO = new FinalizarRescateBPrivInDTO();
		try {
			BeanUtils.copyProperties(finalizarRescateInDTO, viewRequest);

		} catch (Exception e) {
			LOGGER.error("Error creando DTO", e);
		}

		return finalizarRescateInDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.FondoManager
	 * #verComprobanteBPriv()
	 */
	@Override
	public Respuesta<ComprobanteSuscripcionView> verComprobante(DatosComprobante viewRequest) {
		ComprobanteSuscripcionView comprobanteSuscripcionView = new ComprobanteSuscripcionView();
		Respuesta<String> respuestaLegales = legalBO.buscarLegal(MENSAJE_LEGALES_COMPROBANTE_RESCATE);
		if (viewRequest.getNombreFondo() != null && !viewRequest.getNombreFondo().isEmpty() && viewRequest.getNombreFondo().equals(SUPER_AHORRO_PESOS_CUOTA)) {
			respuestaLegales = legalBO.buscarLegal(
					CodigoMensajeConstantes.CODIGO_LEGAL_RESCATE_COMPROBANTE_SUPER_AHORRO_PESOS);
		}
		if (EstadoRespuesta.OK.equals(respuestaLegales.getEstadoRespuesta())) {
			Estadistica estadistica = new Estadistica(EstadisticasConstants.VER_COMPROBANTE_RESCATE_BANCA_PERSONAL,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			estadistica.setNroComprobante(viewRequest.getNumeroComprobante());
			estadisticaManager.add(estadistica, sessionParametros.getRegistroSession(), sesionCliente.getCliente());
			comprobanteSuscripcionView.setLegales(respuestaLegales.getRespuesta());
			return respuestaFactory.crearRespuestaOk(ComprobanteSuscripcionView.class, comprobanteSuscripcionView);
		} else {
			estadisticaManager.add(EstadisticasConstants.VER_COMPROBANTE_RESCATE_BANCA_PERSONAL,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, ERROR_GENERICO);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.
	 * RescateManager#verComprobanteBPriv(ar.com.santanderrio.obp.servicios.
	 * inversiones.comun.view.DatosComprobante)
	 */
	@Override
	public Respuesta<ComprobanteSuscripcionView> verComprobanteBPriv(DatosComprobante viewRequest) {
		ComprobanteSuscripcionView comprobanteSuscripcionView = new ComprobanteSuscripcionView();
		Respuesta<String> respuestaLegales = legalBO.buscarLegal(MENSAJE_LEGALES_COMPROBANTE_RESCATE);
		if (viewRequest.getNombreFondo().equals(SUPER_AHORRO_PESOS_CUOTA)) {
			respuestaLegales = legalBO.buscarLegal(
					CodigoMensajeConstantes.CODIGO_LEGAL_RESCATE_COMPROBANTE_SUPER_AHORRO_PESOS);
		}
		if (EstadoRespuesta.OK.equals(respuestaLegales.getEstadoRespuesta())) {
			Estadistica estadistica = new Estadistica(EstadisticasConstants.VER_COMPROBANTE_RESCATE_BANCA_PRIVADA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			estadistica.setNroComprobante(viewRequest.getNumeroComprobante());
			estadisticaManager.add(estadistica, sessionParametros.getRegistroSession(), sesionCliente.getCliente());
			comprobanteSuscripcionView.setLegales(respuestaLegales.getRespuesta());
			return respuestaFactory.crearRespuestaOk(ComprobanteSuscripcionView.class, comprobanteSuscripcionView);
		} else {
			estadisticaManager.add(EstadisticasConstants.VER_COMPROBANTE_RESCATE_BANCA_PRIVADA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, ERROR_GENERICO);
		}
	}

	/*
	 * Obtener el plazo de efectivo, la cuenta operativa sin formatear y los limites
	 * minimo y maximos del rescate de la cuenta de la que se desea rescatar
	 * seleccionada desde la grilla.
	 * 
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.
	 * RescateManager#ObtenerRescateDesdeGrilla(ar.com.santanderrio.obp.
	 * servicios.inversiones.comun.view.RescateDesdeGrillaIN)
	 */
	@Override
	public Respuesta<RescateDesdeGrilla> obtenerRescateDesdeGrilla(RescateDesdeGrillaInView viewRequest) {

		sessionParametros.setContador(new ContadorIntentos(2));

		Respuesta<RescateDesdeGrilla> respuesta;
		Cliente cliente = sesionCliente.getCliente();
		respuesta = rescateBO.obtenerRescateDesdeGrilla(viewRequest, cliente);

		if (respuesta.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			List<ItemMensajeRespuesta> itemsMensajeRespuesta = respuesta.getItemsMensajeRespuesta();
			if (itemsMensajeRespuesta.get(0).getTipoError()
					.equals(TipoError.SERVICIO_FONDOS_DESHABILITADO.getDescripcion())) {
				return respuestaFactory.crearRespuestaError("", TipoError.SERVICIO_FONDOS_DESHABILITADO,
						CodigoMensajeConstantes.ERROR_SERVICIO_FONDOS_DESHABILITADO);
			} else {
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, ERROR_GENERICO);
			}
		}

		if (respuesta.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
			if (TipoError.FUERADEHORARIO.toString()
					.equals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError())) {
				estadisticaManager.add(obtenerEstadisticaSegunBanca(viewRequest.getBanca()),
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR_FH);
			}
		}
		
		return respuesta;
	}

	private String obtenerEstadisticaSegunBanca(String banca) {
		if("BP".equalsIgnoreCase(banca)) {
			return EstadisticasConstants.FUERA_HORARIO_RESCATE_PASO1_BPRIV;
		}
		return EstadisticasConstants.FUERA_HORARIO_RESCATE_PASO1_RTL;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.
	 * RescateManager#descargarComprobanteRescatePDF(ar.com.santanderrio.obp.
	 * servicios.inversiones.comun.view.ComprobanteRescateFondo)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteRescatePDF(ComprobanteRescateFondo viewRequest) {
		Respuesta<Reporte> reporte;
		Respuesta<ReporteView> respuestaView;
		// validarHashRescateComprobante(viewRequest);
		reporte = reporteBO.obtenerComprobantePDF(viewRequest);
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
			estadisticaManager.add(EstadisticasConstants.COMPROBANTE_RESCATE_FONDO,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			respuestaView = respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
			estadisticaManager.add(EstadisticasConstants.COMPROBANTE_RESCATE_FONDO,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaView;
	}

	/**
	 * Cargar hash de la vista simulacion.
	 *
	 * @param view the view
	 */
	private void crearHashSimularRescateFondos(SimulacionRescateInView view) {
		String hash = HashUtils.obtenerHash(crearMapSimularRescateFondos(view));
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
	private Map<String, Object> crearMapSimularRescateFondos(SimulacionRescateInView fondoView) {
		LOGGER.info(MSJ_INFO_CREANDO_HASH_ATRIBUTOS);
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		mapaAtributos.put("codigoFondo", fondoView.getCodigoFondo());
		// mapaAtributos.put("cuentaTitulos", fondoView.getNroCuentaTitulos());
		mapaAtributos.put("importe", fondoView.getImporteNeto());
		mapaAtributos.put("moneda", fondoView.getMoneda());
		// mapaAtributos.put("numeroCuentaDebito", fondoView.getNumeroCuenta());
		// mapaAtributos.put("sucursalCuentaDebito", fondoView.getSucursalCuenta());
		LOGGER.info("String mapa vista: " + mapaAtributos.toString());
		return mapaAtributos;
	}

	/**
	 * Validar hash flujo fondos.
	 *
	 * @param fondoView the fondo view
	 */
	private void validarHashRescateFondos(FinalizarRescateHash fondoView) {
		String inputHash = HashUtils.obtenerHash(crearMapFinalizarRescateFondos(fondoView));
		LOGGER.info(MSJ_INFO_VALIDANDO_HASH, sessionParametros.getValidacionHash(), inputHash);
		HashUtils.compareHash(sessionParametros.getValidacionHash(), inputHash);
	}

	/**
	 * Crear mapa de la vista calcular intereses.
	 *
	 * @param fondoView the fondo view
	 * @return the map
	 */
	private Map<String, Object> crearMapFinalizarRescateFondos(FinalizarRescateHash fondoView) {
		LOGGER.info(MSJ_INFO_CREANDO_HASH_ATRIBUTOS);
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		mapaAtributos.put("codigoFondo", fondoView.getCodigoFondo());
		// mapaAtributos.put("cuentaTitulos", fondoView.getCuentaTitulo());
		mapaAtributos.put("importe", fondoView.getImporte());
		mapaAtributos.put("moneda", fondoView.getMoneda());
		// mapaAtributos.put("numeroCuentaDebito", fondoView.getNumeroCtaCred());
		// mapaAtributos.put("sucursalCuentaDebito", fondoView.getSucursalCtaCred());
		LOGGER.info("String mapa vista: " + mapaAtributos.toString());
		return mapaAtributos;
	}

	/**
	 * Cargar hash de la vista simulacion.
	 *
	 * @param inView   the in view
	 * @param fondoDTO the fondo DTO
	 */
	private void cargarHashDeLaVistaComprobante(FinalizarRescateInView inView, FinalizarRescateDTO fondoDTO) {
		String hashView = HashUtils.obtenerHash(crearMapaDeLaVistaComprobante(inView, fondoDTO));
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
	 * @param fondoView the fondo view
	 * @param fondoDTO  the fondo DTO
	 * @return the map
	 */
	private Map<String, Object> crearMapaDeLaVistaComprobante(FinalizarRescateInView fondoView,
			FinalizarRescateDTO fondoDTO) {
		LOGGER.info(MSJ_INFO_CREANDO_HASH_ATRIBUTOS);
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		mapaAtributos.put("cuentaTitulos", fondoView.getCuentaTitulo());
		mapaAtributos.put("nombreFondo", fondoView.getNombreFondo());
		mapaAtributos.put("numeroCuentaCredito", fondoView.getSucursalCtaCred() + "-" + fondoView.getNumeroCtaCred());
		mapaAtributos.put("importe", formatearImporteHash(fondoDTO.getImporteRescateNeto()));
		mapaAtributos.put("fecha", fondoDTO.getFechaHora());
		mapaAtributos.put("cuotapartes", fondoDTO.getTotalCuotasPartes());
		mapaAtributos.put("comprobante", fondoDTO.getNroRescate());
		LOGGER.info("String mapa vista: " + mapaAtributos.toString());
		return mapaAtributos;
	}

	/**
	 * Validar hash constitucion plazo fijo.
	 *
	 * @param comprobanteView the comprobante view
	 */
	private void validarHashRescateComprobante(ComprobanteRescateFondo comprobanteView) {
		String inputHash = HashUtils.obtenerHash(crearMapaDeLaVistaFinalizarRescateComprobante(comprobanteView));
		LOGGER.info(MSJ_INFO_VALIDANDO_HASH, sessionParametros.getValidacionHash(), inputHash);
		HashUtils.compareHash(sessionParametros.getValidacionHash(), inputHash);
	}

	/**
	 * Crear mapa de la vista calcular intereses.
	 *
	 * @param comprobanteView the comprobante view
	 * @return the map
	 */
	private Map<String, Object> crearMapaDeLaVistaFinalizarRescateComprobante(ComprobanteRescateFondo comprobanteView) {
		LOGGER.info(MSJ_INFO_CREANDO_HASH_ATRIBUTOS);
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		mapaAtributos.put("cuentaTitulos", comprobanteView.getCuentaTitulos());
		mapaAtributos.put("nombreFondo", comprobanteView.getFondo());
		mapaAtributos.put("numeroCuentaCredito", comprobanteView.getCuentaDestino());
		mapaAtributos.put("importe", formatearImporteHash(comprobanteView.getImporte()));
		mapaAtributos.put("fecha", comprobanteView.getFechaActual());
		mapaAtributos.put("cuotapartes", comprobanteView.getCuotapartes());
		mapaAtributos.put("comprobante", comprobanteView.getComprobante());
		LOGGER.info("String mapa vista: " + mapaAtributos.toString());
		return mapaAtributos;
	}

	/**
	 * Cargar hash de la vista simulacion.
	 *
	 * @param inView   the in view
	 * @param fondoDTO the fondo DTO
	 */
	private void cargarHashDeLaVistaComprobanteBpriv(FinalizarRescateBPrivInView inView,
			FinalizarRescateBPrivDTO fondoDTO) {
		String hashView = HashUtils.obtenerHash(crearMapaDeLaVistaComprobanteBpriv(inView, fondoDTO));
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
	 * @param fondoView the fondo view
	 * @param fondoDTO  the fondo DTO
	 * @return the map
	 */
	private Map<String, Object> crearMapaDeLaVistaComprobanteBpriv(FinalizarRescateBPrivInView fondoView,
			FinalizarRescateBPrivDTO fondoDTO) {
		LOGGER.info(MSJ_INFO_CREANDO_HASH_ATRIBUTOS);
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		mapaAtributos.put("cuentaTitulos", fondoView.getCuentaTitulo());
		mapaAtributos.put("nombreFondo", fondoView.getNombreFondo());
		mapaAtributos.put("numeroCuentaCredito", fondoView.getSucursalCtaCred() + "-" + fondoView.getNumeroCtaCred());
		// mapaAtributos.put("importe", fondoDTO.getImporte().trim());
		mapaAtributos.put("importe",
				ISBANStringUtils.formatearConComaYDosDecimales(fondoView.getImporte()).replaceAll("\\$", "").trim());
		mapaAtributos.put("fecha", fondoDTO.getFechaHora());
		mapaAtributos.put("cuotapartes", fondoDTO.getCuotaPartes());
		mapaAtributos.put("comprobante", fondoDTO.getNroCertificado());
		LOGGER.info("String mapa vista: " + mapaAtributos.toString());
		return mapaAtributos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.RescateManager#
	 * continuarConfiguracionAConfirmacionRescateExciti(ar.com.santanderrio.obp.
	 * servicios.inversiones.fondos.view.FinalizarRescateCitiInView)
	 */
	@Override
	public Respuesta<Void> continuarConfiguracionAConfirmacionRescateExciti(FinalizarRescateCitiInView viewRequest) {
		TenenciasFondoDTO tenenciasFondoDTO = sessionParametros.getTenenciasFondoDTO();
		if (tenenciasFondoDTO == null) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		boolean existeCuenta = false;
		for (CuentaConTenenciaDTO cuentaConTenenciaDTO : tenenciasFondoDTO.getListaCuentas()) {
			if (cuentaConTenenciaDTO.getNumeroCuentaTitulo().equals(viewRequest.getNroCuentaTitulos())) {
				existeCuenta = true;
				break;
			}
		}
		if (!existeCuenta) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		existeCuenta = false;
		for (Cuenta c : sesionCliente.getCliente().getCuentas()) {
			String numeroCuenta = ISBANStringUtils.formatearNumeroCuenta(c.getNroCuentaProducto());
			if (numeroCuenta.equals(viewRequest.getNumeroCuenta())) {
				existeCuenta = true;
				break;
			}
		}
		if (!existeCuenta) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		crearHashcontinuarConfiguracionAConfirmacionRescateExciti(viewRequest);
		return respuestaFactory.crearRespuestaOk(null);
	}

	/**
	 * Crear hash continuar confirmacion rescate exciti.
	 *
	 * @param respuesta   the respuesta
	 * @param viewRequest the view request
	 */
	private void crearHashContinuarConfirmacionRescateExciti(FinalizarRescateDTO respuesta,
			FinalizarRescateCitiInView viewRequest) {
		String hash = HashUtils.obtenerHash(crearMapFinalizarRescateFondosCiti(respuesta, viewRequest));
		LOGGER.info(MSJ_INFO_GUARDANDO_HASH_EN_SESION);
		sessionParametros.setValidacionHash(hash);
	}

	/**
	 * Crear map configuracion rescate fondos citi. Es el mapa de validacion de
	 * datos del stack de la confirmacion para luego tenerlos por si se desea
	 * descargar el pdf y reutilizar el mismo endpont para los demas fondos
	 *
	 * @param respuesta   the respuesta
	 * @param viewRequest the view request
	 * @return the object
	 */
	private Object crearMapFinalizarRescateFondosCiti(FinalizarRescateDTO respuesta,
			FinalizarRescateCitiInView viewRequest) {
		LOGGER.info(MSJ_INFO_CREANDO_HASH_ATRIBUTOS);
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		mapaAtributos.put("cuentaTitulos", viewRequest.getNroCuentaTitulos());
		mapaAtributos.put("nombreFondo", respuesta.getNombreFondo());
		mapaAtributos.put("numeroCuentaCredito", viewRequest.getSucursalCuenta() + "-" + viewRequest.getNumeroCuenta());
		mapaAtributos.put("importe", formatearImporteHash(respuesta.getImporteRescateNeto()));
		mapaAtributos.put("fecha", respuesta.getFechaHora());
		mapaAtributos.put("cuotapartes", respuesta.getTotalCuotasPartes());
		mapaAtributos.put("comprobante", respuesta.getNroRescate());
		LOGGER.info("String mapa vista confirmacion rescate citi: {}", mapaAtributos.toString());
		return mapaAtributos;
	}

	/**
	 * Crear hashcontinuar configuracion A confirmacion rescate exciti.
	 *
	 * @param viewRequest the view request
	 */
	private void crearHashcontinuarConfiguracionAConfirmacionRescateExciti(FinalizarRescateCitiInView viewRequest) {
		String hash = HashUtils.obtenerHash(crearMapFinalizarRescateFondosCiti(viewRequest));
		LOGGER.info(MSJ_INFO_GUARDANDO_HASH_EN_SESION);
		sessionParametros.setValidacionHash(hash);
	}

	/**
	 * Validar hash rescate fondos citi.
	 *
	 * @param viewRequest the view request
	 */
	private void validarHashRescateFondosCiti(FinalizarRescateCitiInView viewRequest) {
		String inputHash = HashUtils.obtenerHash(crearMapFinalizarRescateFondosCiti(viewRequest));
		LOGGER.info(MSJ_INFO_VALIDANDO_HASH, sessionParametros.getValidacionHash(), inputHash);
		HashUtils.compareHash(sessionParametros.getValidacionHash(), inputHash);
	}

	/**
	 * 
	 * Crear map configuracion rescate fondos citi. Es el mapa de validacion de
	 * datos del stack entre configuracion y confirmacion
	 *
	 * @param viewRequest the view request
	 * @return the map
	 */
	private Map<String, Object> crearMapFinalizarRescateFondosCiti(FinalizarRescateCitiInView viewRequest) {
		LOGGER.info(MSJ_INFO_CREANDO_HASH_ATRIBUTOS);
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		mapaAtributos.put("cuentaTitulos", viewRequest.getNroCuentaTitulos());
		mapaAtributos.put("nombreFondo", viewRequest.getNombreFondo());
		mapaAtributos.put("numeroCuentaCredito", viewRequest.getNumeroCuenta());
		mapaAtributos.put("importe", formatearImporteHash(viewRequest.getImporteNeto()));
		mapaAtributos.put("fecha", null);
		mapaAtributos.put("cuotapartes", null);
		mapaAtributos.put("comprobante", null);
		LOGGER.info("String mapa vista configuracion rescate citi: " + mapaAtributos.toString());
		return mapaAtributos;
	}


	private boolean validarHorarioDeFondo(String codigoFondo) {
		LOGGER.info("Invocando al bo de Fondos para obtener informacion");
		try {
			Respuesta<CotizacionDeFondosView> cotizaciones = fondoBO.consultarCotizaciones();
			for(DatosCotizacionFondoView datos : cotizaciones.getRespuesta().getFondos()) {
				if(datos.getCodigoFondo() == null ? codigoFondo == null : datos.getCodigoFondo().equals(codigoFondo)) {
					LOGGER.info("Valido horario de fondo seleccionado");
					if(!fondoBO.validarHorarioDeFondo(datos.getHorario())) return false;
				}
			}
		}catch (Exception ex) {
			LOGGER.error("Ocurrio un error al consultar informacion de fondos, continúa", ex);
		}

		return true;
	}
}
