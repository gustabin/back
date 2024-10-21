/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
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
import ar.com.santanderrio.obp.servicios.comun.utils.FechaUtils;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaBancaPrivada;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.TipoMonedaInversionEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.CodigoSistemaLoadOrdenesEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.OrdenBaseView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoProductoEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dto.RendimientoBPrivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dto.RendimientoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.manager.BaseManager;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DetallePFInteresanteInView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.FrecuenciaCobroPFInteresanteView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RendimientoBPrivView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RendimientoView;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.bo.CuentasBancaPrivadaBO;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.dto.CuentaIntermedioDTO;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.entity.ConsultaSaldoCtasConAperturaOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.util.CuentasBancaPrivadaUtil;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.bo.OrdenesBO;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.ConfirmarOrdenInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.OrdenBaseDTO;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.OrdenPlazoFijoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.OrdenesDTO;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.ConfirmarOrdenPFView;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.CuentaView;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.FiltrosOrdenesView;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.OrdenPlazoFijoView;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.OrdenesView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.bo.PlazoFijoBO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.AltaComprobantePlazoFijoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.ComprobantePlazoFijoInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.ComprobantePlazoFijoOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.FinalizarPlazoFijoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.InteresesDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.MinimosPlazoFijoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.SimulacionPrecancelableDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.SimulacionPrecancelableUVADTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.SimularPlazoFijoInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.SimularPlazoFijoOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.TenenciaPlazoFijoBprivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.TenenciaTotalPlazoFijoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.FrecuenciaCobroPFInteresanteOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.PlazoFijoEnum;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.RecomendacionResponseEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.AccionesAlVencimientoInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.AccionesAlVencimientoOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.AltaComprobantePlazoFijoView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.CalcularInteresesInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.CalcularInteresesOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ComprobanteCancelacionPrecancelable;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ComprobanteCancelacionPrecancelableUVA;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ComprobanteConstitucionPlazoFijo;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ComprobanteModificacionAccionVencimiento;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ComprobantePlazoFijoInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ComprobantePlazoFijoOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ConfigPlazoFijoInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ConfigPlazoFijoOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ConfirmacionPlazoFijoOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ConsultaTasasPlazosFijoView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.DetalleCobroInteresesViewResponse;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.DetalleInteresesViewRequest;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.FinalizarPlazoFijoInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.FinalizarPlazoFijoView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.FinalizarPrecancelableOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.FinalizarPrecancelarUVAView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.InteresesView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ModificarAccionInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ModificarAccionOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ObtenerOrdenesViewRequest;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ObtenerTenenciasPlazoFijoInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.RecomendacionInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.SimulacionPrecancelableOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.SimularPlazoFijoInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.SimularPlazoFijoOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.SimularPrecancelarInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.SimularPrecancelarUVAView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.SolicitarPrecancelarInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.SolicitarPrecancelarOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.TenenciaPlazoFijoBprivView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.TenenciaPlazoFijoView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.VerComprobanteModificacionVencimientoView;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.bo.RendimientoBOImpl;
import ar.com.santanderrio.obp.servicios.tenencias.dto.SolicitarPrecancelarOutDTO;

/**
 * The Class PlazoFijoManagerImpl.
 *
 * @author juan.pablo.picate
 */
@Component
public class PlazoFijoManagerImpl extends BaseManager implements PlazoFijoManager {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PlazoFijoManagerImpl.class);

	/** The Plazo Fijo BO. */
	@Autowired
	private PlazoFijoBO plazoFijoBO;

	/** The Mensaje dao. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The cuenta manager. */
	@Autowired
	private CuentaManager cuentaManager;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The session parametros. */
	@Autowired
	private SesionParametros sessionParametros;

	/** The reporte BO. */
	@Autowired
	private ReporteComprobantePDFBO reporteBO;

	/** The ordenes BO. */
	@Autowired
	private OrdenesBO ordenesBO;

	/** The rendimiento BO impl. */
	@Autowired
	private RendimientoBOImpl rendimientoBOImpl;

	/** The formatter. */
	SimpleDateFormat formatterOut = new SimpleDateFormat("dd-MM-yyyy");

	/** The formatter. */
	SimpleDateFormat formatterIn = new SimpleDateFormat("ddMMyyyy");

	/** The Constant MENSAJE_LEGALES. */
	private static final String MENSAJE_LEGALES = "10022";

	/** The Constant ULTIMOS_DOS. */
	private static final int ULTIMOS_DOS = 2;

	/** The Constant OPCION_SIMULACION. */
	private static final String OPCION_SIMULACION = "S";

	/** The Constant OPCION_FINALIZAR. */
	private static final String OPCION_FINALIZAR = "D";

	/** The Constant FEEDBACK POSITIVO. */
	public static final String FEED_BACK_POSITIVO = "10466";

	/** The Constant FEEDBACK NEGATIVO. */
	public static final String FEED_BACK_NEGATIVO = "10467";

	/** The Constant DECIMALES_CAPITAL. */
	private static final int DECIMALES_CAPITAL = 2;

	/** The Constant PLAZO_FIJO. */
	private static final String PLAZO_FIJO = "Plazo Fijo ";

	/** The Constant DECIMALES_PORCENTAJE_PENALIZACION. */
	private static final int DECIMALES_PORCENTAJE_PENALIZACION = 5;

	/** The Constant OPERACIONES. */
	private static final String OPERACIONES = "operaciones";

	/** The cuentas Banca Privada BO. */
	@Autowired
	private CuentasBancaPrivadaBO cuentasBancaPrivadaBO;

	/** The formatter. */
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

	private static final String MENSAJE_ERROR_EXCEL = "Ocurrió un error y no se pudo realizar la descarga. Por favor, volvé a intentar";

	/** The Constant BANCA_RETAIL. */
	private static final String BANCA_RETAIL = "BR";

	/** The Constant BP. */
	private static final String BANCA_PRIVADA = "BP";

	private static final String SIGNO_PORCENTAJE = "%";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<SimularPlazoFijoOutView> simularPlazoFijo(SimularPlazoFijoInView inView) {

		Respuesta<SimularPlazoFijoOutView> rtaValidacion = super.validate(inView, new SimularPlazoFijoOutView());
		if (!rtaValidacion.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		LOGGER.info("Invocando al BO para ejecutar la consulta");
		cargarHashDeLaVistaSimulacion(inView);
		SimularPlazoFijoInDTO dTORequest = createDTORequest(inView);

		CuentasView cuentasView = new CuentasView();
		if (BANCA_RETAIL.equals(sessionParametros.getTipoBancaPlazoFijo())) {
			cuentasView = cuentaManager.getCuentasSaldo().getRespuesta();
		} else if (BANCA_PRIVADA.equals(sessionParametros.getTipoBancaPlazoFijo())) {
			Respuesta<List<ResumenDetalleCuenta>> respuestaResumenes = obtenerCuentasySaldosBP();
			cuentasView = CuentasBancaPrivadaUtil.convertirCuentasView(respuestaResumenes).getRespuesta();

		}

		Respuesta<SimularPlazoFijoOutDTO> respuestaBO = plazoFijoBO.simularPlazoFijo(dTORequest,
				sesionCliente.getCliente(), cuentasView.getCuentas().size());
		SimularPlazoFijoOutView outView = createRetornoView(respuestaBO.getRespuesta());

		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return efectuarLogicaOk(dTORequest, respuestaBO);

		}

		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			return efectuarLogicaError(dTORequest, respuestaBO, cuentasView.getCuentas().size());
		}

		return respuestaFactory.crearRespuestaWarning(SimularPlazoFijoOutView.class, outView,
				respuestaBO.getItemsMensajeRespuesta());
	}

	/**
	 * Llena los campos necesarios para grabar la estadistica para Simular Plazo
	 * Fijo.
	 *
	 * @param importe the importe
	 * @param moneda  the moneda
	 * @return the estadistica
	 */
	public Estadistica crearEstadisticaSimularPlazoFijo(String importe, String moneda) {
		String signoMoneda = "u$s";
		if ("ARS".equals(moneda) || "$".equals(moneda)) {
			signoMoneda = "$";
		}
		Estadistica estadistica = new Estadistica();
		estadistica.setImporte(importe);
		estadistica.setMoneda(signoMoneda);
		if (BANCA_RETAIL.equals(sessionParametros.getTipoBancaPlazoFijo())) {
			estadistica.setCodigoTransaccion(EstadisticasConstants.CODIGO_ESTADISTICA_SIMULACION_PLAZO_FIJO);
		} else if (BANCA_PRIVADA.equals(sessionParametros.getTipoBancaPlazoFijo())) {
			estadistica.setCodigoTransaccion(EstadisticasConstants.CODIGO_ESTADISTICA_SIMULACION_PLAZO_FIJO_BP);
		}
		estadistica.setCodigoTransaccion(EstadisticasConstants.CODIGO_ESTADISTICA_SIMULACION_PLAZO_FIJO);
		return estadistica;
	}

	/**
	 * Efectuar logica ok.
	 *
	 * @param dTORequest  the d TO request
	 * @param respuestaBO the respuesta BO
	 * @return the respuesta
	 */
	private Respuesta<SimularPlazoFijoOutView> efectuarLogicaOk(SimularPlazoFijoInDTO dTORequest,
			Respuesta<SimularPlazoFijoOutDTO> respuestaBO) {

		String importeFinal = "";
		String moneda = dTORequest.getDivisa();

		BigDecimal importe = dTORequest.getImportePlazoFijo();
		importeFinal = importe.toString();
		Estadistica estadistica = crearEstadisticaSimularPlazoFijo(importeFinal, moneda);
		estadistica.setCodigoError(EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		estadisticaManager.add(estadistica, sessionParametros.getRegistroSession(), sesionCliente.getCliente());

		SimularPlazoFijoOutView outView = createRetornoView(respuestaBO.getRespuesta());
		return respuestaFactory.crearRespuestaOk(SimularPlazoFijoOutView.class, outView);

	}

	/**
	 * Efectuar logica error.
	 *
	 * @param dTORequest  the d TO request
	 * @param respuestaBO the respuesta BO
	 * @param cantCuentas the cant cuentas
	 * @return the respuesta
	 */
	private Respuesta<SimularPlazoFijoOutView> efectuarLogicaError(SimularPlazoFijoInDTO dTORequest,
			Respuesta<SimularPlazoFijoOutDTO> respuestaBO, int cantCuentas) {

		String importeFinal = "";
		String moneda = dTORequest.getDivisa();
		BigDecimal importe = dTORequest.getImportePlazoFijo();
		importeFinal = importe.toString();

		Estadistica estadistica = crearEstadisticaSimularPlazoFijo(importeFinal, moneda);
		estadistica.setCodigoError(EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		estadisticaManager.add(estadistica, sessionParametros.getRegistroSession(), sesionCliente.getCliente());
		if ("ERROR_SALDO_INSUFICIENTE".equalsIgnoreCase(respuestaBO.getItemsMensajeRespuesta().get(0).getTipoError())) {

			String codigoError = CodigoMensajeConstantes.SALDO_INSUFICIENTE_UNA_CUENTA;
			if (cantCuentas > 1) {
				codigoError = CodigoMensajeConstantes.SALDO_INSUFICIENTE_MULTIPLES_CUENTAS;
			}
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SALDO_INSUFICIENTE, codigoError);
		}

		if ("CUENTA_INACTIVA".equalsIgnoreCase(respuestaBO.getItemsMensajeRespuesta().get(0).getTipoError())) {
			String codigoErrorCuentaInactiva = "10551";
			String tipoErrorCuentaInactiva = "CUENTA_INACTIVA";
			return respuestaFactory.crearRespuestaError("", tipoErrorCuentaInactiva, codigoErrorCuentaInactiva);
		}

		return respuestaFactory.crearRespuestaError(SimularPlazoFijoOutView.class,
				respuestaBO.getItemsMensajeRespuesta().get(0).getMensaje(), "");
	}

	/**
	 * Crea DTO de entrada para el BO.
	 *
	 * @param inView the in view
	 * @return the simular plazo fijo in DTO
	 */
	private SimularPlazoFijoInDTO createDTORequest(SimularPlazoFijoInView inView) {
		SimularPlazoFijoInDTO inDTO = new SimularPlazoFijoInDTO();
		if (inView != null) {
			inDTO.setNumeroCuentaDebito(inView.getNumeroCuentaDebito());
			inDTO.setPlazo(inView.getPlazo());
			inDTO.setImportePlazoFijo(inView.getImportePlazoFijo());
			inDTO.setDivisa(inView.getDivisa());
		}
		return inDTO;
	}

	/**
	 * Crear view de retorno para enviar al SEI.
	 *
	 * @param dto the dto
	 * @return the simular plazo fijo out view
	 */
	private SimularPlazoFijoOutView createRetornoView(SimularPlazoFijoOutDTO dto) {
		SimularPlazoFijoOutView outView = new SimularPlazoFijoOutView();
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
	 * {@inheritDoc}
	 * 
	 * @throws BusinessException
	 */
	@Override
	public Respuesta<ConfigPlazoFijoOutView> configuracionPlazoFijo(ConfigPlazoFijoInView configPlazoFijoInView) {
		LOGGER.info("Inicio metodo configuracionPlazoFijo");
		LOGGER.info("Obteniendo Cuentas");
		sessionParametros.setContador(new ContadorIntentos(2));
		ConfigPlazoFijoOutView configPlazoFijoOutView = new ConfigPlazoFijoOutView();
		List<CuentasAdhesionDebitoView> cuentasDebito = new ArrayList<CuentasAdhesionDebitoView>();
		// Obtengo solo las Cuentas Retail.
		if (BANCA_RETAIL.equals(configPlazoFijoInView.getTipoBanca())) {
			Respuesta<CuentasView> respuesta = cuentaManager.getCuentasSaldo();
			if (respuesta.getRespuesta().getCuentas() == null
					|| respuesta.getRespuesta().getCuentas().isEmpty() == true) {
				LOGGER.error("El cliente no tiene cuentas debito");
				estadisticaManager.add(EstadisticasConstants.CODIGO_CONFIGURACION_PLAZO_FIJO,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				return respuestaFactory.crearRespuestaWarning("", TipoError.WARNING,
						CodigoMensajeConstantes.ERROR_SIN_CUENTAS_DEBITO);
			}

			List<CuentasAdhesionDebitoView> cuentasDebitoRetail = respuesta.getRespuesta().getCuentas();
			List<Cuenta> cuentasCepo = cuentaManager.getCuentasCepo(sesionCliente.getCliente());

			if (!cuentasCepo.isEmpty()) {
				cuentasDebitoRetail = filtrarCuentasCepo(cuentasCepo, cuentasDebito);
			}
			cuentasDebito = cuentasDebitoRetail;
			// Obtengo solo las cuentas de banca privada
		} else if (BANCA_PRIVADA.equals(configPlazoFijoInView.getTipoBanca())) {
			Respuesta<List<ResumenDetalleCuenta>> respuestaResumenes = obtenerCuentasySaldosBP();
			List<CuentasAdhesionDebitoView> cuentasDebitoBP = CuentasBancaPrivadaUtil
					.convertirCuentasView(respuestaResumenes).getRespuesta().getCuentas();
			List<Cuenta> cuentasCepoBP = cuentaManager.getCuentasCepoBP(sesionCliente.getCliente());
			if (!cuentasCepoBP.isEmpty()) {
				cuentasDebitoBP = filtrarCuentasCepo(cuentasCepoBP, cuentasDebito);
			}
			cuentasDebito = cuentasDebitoBP;
		}

		configPlazoFijoOutView.setCuentasDebito(cuentasDebito);

		LOGGER.info("Obteniendo Legales");
		Respuesta<String> respuestaLegales = legalBO.buscarLegal(MENSAJE_LEGALES);

		// Si el legal no se obtuvo ok, respuesta error
		if (!respuestaLegales.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			LOGGER.error("El legal no fue obtenido correctamente");
			if (BANCA_RETAIL.equals(configPlazoFijoInView.getTipoBanca())) {
				estadisticaManager.add(EstadisticasConstants.CODIGO_CONFIGURACION_PLAZO_FIJO,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			} else if (BANCA_PRIVADA.equals(configPlazoFijoInView.getTipoBanca())) {
				estadisticaManager.add(EstadisticasConstants.CODIGO_CONFIGURACION_PLAZO_FIJO_BP,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		// Agrego legales al view de salida
		configPlazoFijoOutView.setLegales(respuestaLegales.getRespuesta());

		LOGGER.info("Consultando importe y plazo minimos");
		// Obtengo los importes y plazos minimos
		String tipoBanca = configPlazoFijoInView.getTipoBanca();
		sessionParametros.setTipoBancaPlazoFijo(tipoBanca);
		Respuesta<MinimosPlazoFijoDTO> respuestaBO = plazoFijoBO.consultarMinimos(sesionCliente.getCliente(),
				tipoBanca);
		configPlazoFijoOutView.setMinimos(respuestaBO.getRespuesta());

		// Agrego estadisticas por error o por OK
		if (!respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			if (BANCA_RETAIL.equals(configPlazoFijoInView.getTipoBanca())) {
				estadisticaManager.add(EstadisticasConstants.CODIGO_CONFIGURACION_PLAZO_FIJO,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			} else if (BANCA_PRIVADA.equals(configPlazoFijoInView.getTipoBanca())) {
				estadisticaManager.add(EstadisticasConstants.CODIGO_CONFIGURACION_PLAZO_FIJO_BP,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}
			estadisticaManager.add(EstadisticasConstants.CODIGO_PLAZO_FIJO_HABILITADO_CON_TIPOS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		if (BANCA_RETAIL.equals(configPlazoFijoInView.getTipoBanca())) {
			estadisticaManager.add(EstadisticasConstants.CODIGO_CONFIGURACION_PLAZO_FIJO,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else if (BANCA_PRIVADA.equals(configPlazoFijoInView.getTipoBanca())) {
			estadisticaManager.add(EstadisticasConstants.CODIGO_CONFIGURACION_PLAZO_FIJO_BP,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}
		estadisticaManager.add(EstadisticasConstants.CODIGO_PLAZO_FIJO_HABILITADO_CON_TIPOS,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(ConfigPlazoFijoOutView.class, configPlazoFijoOutView);
	}

	private Respuesta<List<ResumenDetalleCuenta>> obtenerCuentasySaldosBP() {

		List<Cuenta> cuentasBP = sesionCliente.getCliente().getCuentasPrivadas();
		List<CuentaIntermedioDTO> saldosCuentaBP = new ArrayList<CuentaIntermedioDTO>();

		try {
			saldosCuentaBP = cuentasBancaPrivadaBO.consultarSaldosCuenta(sesionCliente.getCliente());
		} catch (SQLException e) {
			LOGGER.info("SQL Exception. consultarSaldosCuenta   ", e);
		} catch (BusinessException e) {
			LOGGER.info("SQL BusinessException. consultarSaldosCuenta   ", e);
		}

		for (CuentaIntermedioDTO cuentaDTO : saldosCuentaBP) {
			// si alguna cuentaDTO tiene un error en la consulta de saldo, grabo
			// estadisticas ERROR y retorno warning. Flujo alternativo
			if (cuentaDTO.getSaldosServicioIatx().getErrorEnConsulta()) {
				LOGGER.info("ERROR al consultarSaldosCuenta: cuentaDTO.getSaldosServicioIatx().getErrorEnConsulta() ");
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
		}

		List<ResumenDetalleCuenta> respuestaDetalleCuenta = new ArrayList<ResumenDetalleCuenta>();
		for (Cuenta cuentaBP : cuentasBP) {

			// filtro las cuentas de banca privada que necesito
			if (("07".equals(cuentaBP.getProductoAltair()) || plazoFijoBO.esCuentaRepatriacion(cuentaBP))
					&& CuentasBancaPrivadaUtil.isCuentaPrivada(cuentaBP)) {

				ResumenDetalleCuenta resumenDetalleCuenta = new ResumenDetalleCuenta();
				ConsultaSaldoCtasConAperturaOutEntity saldoCuenta = null;
				for (CuentaIntermedioDTO saldoCuentaBP : saldosCuentaBP) {
					// obtengo los salgos de las banca privada filtradas
					if (cuentaBP.obtenerNroCuentaFormateado().equals(saldoCuentaBP.getNumeroCuenta())) {
						saldoCuenta = saldoCuentaBP.getSaldosServicioIatx();
						resumenDetalleCuenta = CuentasBancaPrivadaUtil.initResumenDetalleCuenta(cuentaBP, saldoCuenta);
					}
				}

				respuestaDetalleCuenta.add(resumenDetalleCuenta);
				resumenDetalleCuenta.setAlias(null);
				resumenDetalleCuenta.setFavorita(Boolean.FALSE);
			}
		}

		return respuestaFactory.crearRespuestaOk(respuestaDetalleCuenta);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.
	 * PlazoFijoManager#accionesAlVencimiento(ar.com.santanderrio.obp.servicios.
	 * inversiones.plazofijo.view.AccionesAlVencimientoInView)
	 */
	@Override
	public Respuesta<AccionesAlVencimientoOutView> accionesAlVencimiento(AccionesAlVencimientoInView inView) {
		Respuesta<AccionesAlVencimientoOutView> rtaValidacion = super.validate(inView,
				new AccionesAlVencimientoOutView());
		if (!rtaValidacion.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		Respuesta<AccionesAlVencimientoOutView> respuestaAcciones = plazoFijoBO.accionesAlVencimiento(inView,
				sesionCliente.getCliente());
		if (EstadoRespuesta.OK.equals(respuestaAcciones.getEstadoRespuesta())) {
			return respuestaFactory.crearRespuestaOk(AccionesAlVencimientoOutView.class,
					respuestaAcciones.getRespuesta());
		}
		LOGGER.error("No se pudieron obtener las acciones al vencimiento. ");
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
	}

	/**
	 * Grabar estadistica.
	 *
	 * @param codigoEstadistica the codigo estadistica
	 * @param codigoError       the codigo error
	 * @param cliente           the cliente
	 */
	private void grabarEstadistica(String codigoEstadistica, String codigoError, Cliente cliente) {
		Estadistica estadistica = new Estadistica();
		estadistica.setCodigoTransaccion(codigoEstadistica);
		estadistica.setCodigoError(codigoError);
		estadisticaManager.add(estadistica, sessionParametros.getRegistroSession(), cliente);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.
	 * PlazoFijoManager#grabarEstadisticaPlazoFijo()
	 */
	@Override
	public Respuesta<Void> grabarEstadisticaPlazoFijo() {
		grabarEstadistica(EstadisticasConstants.PLAZO_FIJO_INICIO_BANCA_PERSONAL,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK, sesionCliente.getCliente());
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.
	 * PlazoFijoManager#grabarEstadisticaPlazoFijoBPriv()
	 */
	@Override
	public Respuesta<Void> grabarEstadisticaPlazoFijoBPriv() {
		grabarEstadistica(EstadisticasConstants.PLAZO_FIJO_INICIO_BANCA_PRIVADA,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK, sesionCliente.getCliente());
		return respuestaFactory.crearRespuestaOk(Void.class);
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
	 * @param simularPlazoFijoInView the simular plazo fijo in view
	 * @return the map
	 */
	private Map<String, Object> crearMapaDeLaVista(SimularPlazoFijoInView simularPlazoFijoInView) {
		LOGGER.info(MSJ_INFO_CREANDO_HASH_ATRIBUTOS);
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		mapaAtributos.put("numeroCuentaDebito", simularPlazoFijoInView.getNumeroCuentaDebito());
		mapaAtributos.put("importePlazoFijo", simularPlazoFijoInView.getImportePlazoFijo());
		LOGGER.info("String mapa vista: " + mapaAtributos.toString());
		return mapaAtributos;
	}

	/**
	 * Cargar hash de la vista simulacion.
	 *
	 * @param inView the in view
	 */
	private void cargarHashDeLaVistaSimulacion(SimularPlazoFijoInView inView) {
		String hashView = HashUtils.obtenerHash(crearMapaDeLaVista(inView));
		LOGGER.info(MSJ_INFO_GUARDANDO_HASH_EN_SESION);
		sessionParametros.setValidacionHash(hashView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.
	 * PlazoFijoManager#confirmacionPlazoFijo(java.lang.String)
	 */
	@Override
	public Respuesta<ConfirmacionPlazoFijoOutView> confirmacionPlazoFijo(String codigoPlazoFijo, String banca) {
		codigoPlazoFijo = ISBANStringUtils.eliminarCeros(codigoPlazoFijo);
		Respuesta<String> respuestaLegal = legalBO.buscarLegal(CodigoMensajeConstantes.CONFIRMACION_PLAZO_FIJO);
		if (EstadoRespuesta.OK.equals(respuestaLegal.getEstadoRespuesta())) {
			ConfirmacionPlazoFijoOutView confirmacionView = new ConfirmacionPlazoFijoOutView();
			confirmacionView.setListaLegales(new ArrayList<String>());
			if (PlazoFijoEnum.TRADICIONAL.getCodigo().equals(codigoPlazoFijo)
					|| PlazoFijoEnum.PRECANCELABLE.getCodigo().equals(codigoPlazoFijo)
					|| PlazoFijoEnum.INTERESANTE_TASA_FIJA.getCodigo().equals(codigoPlazoFijo)) {
				Respuesta<String> respuestaLegalDDJJ = legalBO.buscarLegal(CodigoMensajeConstantes.CONFIRMACION_PLAZO_FIJO_DDJJ);
				confirmacionView.getListaLegales().add(respuestaLegalDDJJ.getRespuesta());
			}
			confirmacionView.getListaLegales().add(respuestaLegal.getRespuesta());
			if (PlazoFijoEnum.PRECANCELABLE.getCodigo().equals(codigoPlazoFijo)) {
				Respuesta<String> respuestaLegalPrecancelable = legalBO
						.buscarLegal(CodigoMensajeConstantes.PRECANCELABLE_PLAZO_FIJO_LEGAL_CONFIRMACION);
				confirmacionView.getListaLegales().add(0, respuestaLegalPrecancelable.getRespuesta());
			} else if (PlazoFijoEnum.INTERESANTE_TASA_FIJA.getCodigo().equals(codigoPlazoFijo)) {
				Respuesta<String> respuestaLegalPrecancelable = legalBO
						.buscarLegal(CodigoMensajeConstantes.INTERESANTE_PLAZO_FIJO_LEGAL_CONFIRMACION);
				confirmacionView.getListaLegales().add(0, respuestaLegalPrecancelable.getRespuesta());
			} else if (PlazoFijoEnum.UVA.getCodigo().equals(codigoPlazoFijo)) {
				Respuesta<String> respuestaLegalUva = legalBO
						.buscarLegal(CodigoMensajeConstantes.UVA_PLAZO_FIJO_LEGAL_CONFIRMACION);
				confirmacionView.getListaLegales().add(0, respuestaLegalUva.getRespuesta());
			} else if (PlazoFijoEnum.UVA_PRECANCELABLE.getCodigo().equals(codigoPlazoFijo)) {
				Respuesta<String> respuestaLegalUvaPrec = legalBO
						.buscarLegal(CodigoMensajeConstantes.UVA_PLAZO_FIJO_PRECANCELABLE_CONFIGURACION);
				confirmacionView.getListaLegales().add(0, respuestaLegalUvaPrec.getRespuesta());
			}
			if (PlazoFijoEnum.TRADICIONAL_REPATRIACION.getCodigo().equals(codigoPlazoFijo)) {
				grabarEstadisticaConfirmacionRepatriacionSegunBanca(EstadisticasConstants.CODIGO_ESTADISTICAS_OK,
						banca);
			}

			return respuestaFactory.crearRespuestaOk(ConfirmacionPlazoFijoOutView.class, confirmacionView);
		}
		if (PlazoFijoEnum.TRADICIONAL_REPATRIACION.getCodigo().equals(codigoPlazoFijo)) {
			grabarEstadisticaConfirmacionRepatriacionSegunBanca(EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR, banca);
		}
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
	}

	private void grabarEstadisticaConfirmacionRepatriacionSegunBanca(String codigoEstadisticasError, String banca) {
		if (BANCA_PRIVADA.equalsIgnoreCase(banca)) {
			estadisticaManager.add(EstadisticasConstants.SIMULAR_PF_TRADICIONAL_REPATRIACION_BPRIV,
					codigoEstadisticasError);
		} else {
			estadisticaManager.add(EstadisticasConstants.SIMULAR_PF_TRADICIONAL_REPATRIACION_RTL,
					codigoEstadisticasError);
		}
	}

	private ArrayList<String> obtenerLegalesUvaPrecancelable() {
		ArrayList<String> listaLegales = new ArrayList<String>();
		listaLegales.add(legalBO.buscarLegal(CodigoMensajeConstantes.UVA_PLAZO_FIJO_PRECANCELABLE_1).getRespuesta());
		listaLegales.add(legalBO.buscarLegal(CodigoMensajeConstantes.UVA_PLAZO_FIJO_PRECANCELABLE_2).getRespuesta());
		listaLegales.add(legalBO.buscarLegal(CodigoMensajeConstantes.UVA_PLAZO_FIJO_PRECANCELABLE_3).getRespuesta());
		listaLegales.add(legalBO.buscarLegal(CodigoMensajeConstantes.UVA_PLAZO_FIJO_PRECANCELABLE_4).getRespuesta());
		listaLegales.add(legalBO.buscarLegal(CodigoMensajeConstantes.UVA_PLAZO_FIJO_PRECANCELABLE_5).getRespuesta());
		return listaLegales;
	}

	/**
	 * Creates the comprobante DTO.
	 *
	 * @param comprobantePlazoFijoInView the comprobante plazo fijo in view
	 * @return the comprobante plazo fijo in DTO
	 * @throws BusinessException the business exception
	 */
	private ComprobantePlazoFijoInDTO createComprobanteDTO(ComprobantePlazoFijoInView comprobantePlazoFijoInView)
			throws BusinessException {
		ComprobantePlazoFijoInDTO dto = new ComprobantePlazoFijoInDTO();
		try {
			BeanUtils.copyProperties(dto, comprobantePlazoFijoInView);
		} catch (IllegalAccessException ex) {
			LOGGER.error("Error convirtiendo view en DTO. ", ex);
			throw new BusinessException();
		} catch (InvocationTargetException ex) {
			LOGGER.error("Error convirtiendo view en DTO. ", ex);
			throw new BusinessException();
		}
		return dto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.
	 * PlazoFijoManager#verComprobante(ar.com.santanderrio.obp.servicios.
	 * inversiones.plazofijo.view.ComprobantePlazoFijoInView)
	 */
	@Override
	public Respuesta<ComprobantePlazoFijoOutView> verComprobante(
			ComprobantePlazoFijoInView comprobantePlazoFijoInView) {

		Respuesta<ComprobantePlazoFijoOutView> rtaValidacion = super.validate(comprobantePlazoFijoInView,
				new ComprobantePlazoFijoOutView());
		if (!rtaValidacion.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		try {
			ComprobantePlazoFijoInDTO comprobantePlazoFijoInDTO = createComprobanteDTO(comprobantePlazoFijoInView);
			Respuesta<ComprobantePlazoFijoOutDTO> respuesta = plazoFijoBO.verComprobante(comprobantePlazoFijoInDTO);

			if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
				ComprobantePlazoFijoOutDTO comprobanteDTO = respuesta.getRespuesta();
				ComprobantePlazoFijoOutView comprobantePlazoFijoOutView = new ComprobantePlazoFijoOutView();
				comprobantePlazoFijoOutView.setLegales(comprobanteDTO.getLegales());
				guardarEstadisticaComprobante(EstadisticasConstants.CODIGO_ESTADISTICAS_OK,
						comprobantePlazoFijoInView.getNumeroComprobante(), PlazoFijoEnum.TRADICIONAL_REPATRIACION
								.getCodigo().equals(comprobantePlazoFijoInView.getCodigoPlazo()),
						comprobantePlazoFijoInView.getBanca());
				return respuestaFactory.crearRespuestaOk(ComprobantePlazoFijoOutView.class,
						comprobantePlazoFijoOutView);
			} else {
				guardarEstadisticaComprobante(EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR,
						comprobantePlazoFijoInView.getNumeroComprobante(), PlazoFijoEnum.TRADICIONAL_REPATRIACION
								.getCodigo().equals(comprobantePlazoFijoInView.getCodigoPlazo()),
						comprobantePlazoFijoInView.getBanca());
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, ERROR_GENERICO);
			}
		} catch (BusinessException e) {
			LOGGER.error("Error al crear el DTO {}.", comprobantePlazoFijoInView, e);
			guardarEstadisticaComprobante(EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR,
					comprobantePlazoFijoInView.getNumeroComprobante(), PlazoFijoEnum.TRADICIONAL_REPATRIACION
							.getCodigo().equals(comprobantePlazoFijoInView.getCodigoPlazo()),
					comprobantePlazoFijoInView.getBanca());
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, ERROR_GENERICO);
		}
	}

	/**
	 * Guardar estadistica comprobante.
	 *
	 * @param codigoError       the codigo error
	 * @param numeroComprobante the numero comprobante
	 */
	private void guardarEstadisticaComprobante(String codigoError, String numeroComprobante, boolean pfRepatriacion,
			String banca) {
		Estadistica estadistica = new Estadistica();
		estadistica.setNroComprobante(numeroComprobante);
		if (pfRepatriacion) {
			if (BANCA_PRIVADA.equalsIgnoreCase(banca)) {
				estadisticaManager.add(EstadisticasConstants.VER_COMPROBANTE_PLAZO_FIJO_REPATRIACION_BPRIV, codigoError);
			} else {
				estadisticaManager.add(EstadisticasConstants.VER_COMPROBANTE_PLAZO_FIJO_REPATRIACION_RTL,
						codigoError);
			}
		} else {
			estadisticaManager.add(EstadisticasConstants.VER_COMPROBANTE_PLAZO_FIJO, codigoError);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.
	 * PlazoFijoManager#finalizarPlazoFijo(ar.com.santanderrio.obp.servicios.
	 * inversiones.plazofijo.view.FinalizarPlazoFijoInView)
	 */
	@Override
	public Respuesta<FinalizarPlazoFijoView> finalizarPlazoFijo(FinalizarPlazoFijoInView finalizarPlazoFijoInView) {

		validarHashConstitucionPlazoFijo(finalizarPlazoFijoInView.getNroCuentaDebito(),
				finalizarPlazoFijoInView.getImporteCertificado());

		Respuesta<FinalizarPlazoFijoView> rtaValidacion = super.validate(finalizarPlazoFijoInView,
				new FinalizarPlazoFijoView());
		if (!rtaValidacion.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		FinalizarPlazoFijoDTO finalizarPlazoFijoInDTO = null;
		try {
			finalizarPlazoFijoInDTO = createFinalizarPlazoFijoDTO(finalizarPlazoFijoInView);
		} catch (BusinessException e) {
			LOGGER.error("Error al crear el DTO {}.", finalizarPlazoFijoInView, e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, ERROR_GENERICO);
		}
		Respuesta<FinalizarPlazoFijoDTO> respuestaBO = plazoFijoBO.finalizarPlazoFijo(finalizarPlazoFijoInDTO,
				sesionCliente.getCliente());
		if (EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta())) {
			return crearRespuestaOkFinalizarPlazoFijo(respuestaBO);
		} else {
			return crearRespuestaErrorFinalizarPlazoFijo(respuestaBO, finalizarPlazoFijoInDTO);
		}
	}

	/**
	 * Crear respuesta error finalizar plazo fijo.
	 *
	 * @param respuestaBO             the respuesta BO
	 * @param finalizarPlazoFijoInDTO the finalizar plazo fijo in DTO
	 * @return the respuesta
	 */
	private Respuesta<FinalizarPlazoFijoView> crearRespuestaErrorFinalizarPlazoFijo(
			Respuesta<FinalizarPlazoFijoDTO> respuestaBO, FinalizarPlazoFijoDTO finalizarPlazoFijoInDTO) {
		guardarEstadisticaSegunTipoPlazoFijo(EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR,
				finalizarPlazoFijoInDTO.getTipoPF(), finalizarPlazoFijoInDTO.getDivisa(),
				finalizarPlazoFijoInDTO.getNroCuentaDebito(), finalizarPlazoFijoInDTO.getImporteCertificado());
		String mensaje = null;
		String tipoError = TipoError.ERROR_GENERICO.toString();
		if (respuestaBO.getItemsMensajeRespuesta() != null && !respuestaBO.getItemsMensajeRespuesta().isEmpty()) {
			mensaje = respuestaBO.getItemsMensajeRespuesta().get(0).getMensaje();
			tipoError = respuestaBO.getItemsMensajeRespuesta().get(0).getTipoError().toString();
		}
		return respuestaFactory.crearRespuestaErrorPersonalizadoSinClase(mensaje, tipoError);
	}

	/**
	 * Crear respuesta ok finalizar plazo fijo.
	 *
	 * @param respuestaBO the respuesta BO
	 * @return the respuesta
	 */
	private Respuesta<FinalizarPlazoFijoView> crearRespuestaOkFinalizarPlazoFijo(
			Respuesta<FinalizarPlazoFijoDTO> respuestaBO) {
		guardarEstadisticaSegunTipoPlazoFijo(EstadisticasConstants.CODIGO_ESTADISTICAS_OK,
				respuestaBO.getRespuesta().getTipoPF(), respuestaBO.getRespuesta().getDivisa(),
				respuestaBO.getRespuesta().getNroCuentaDebito(), respuestaBO.getRespuesta().getImporteCertificado());
		FinalizarPlazoFijoView finalizarView = new FinalizarPlazoFijoView();
		finalizarView.setMensaje(respuestaBO.getRespuesta().getMensaje());
		finalizarView.setNumeroComprobante(respuestaBO.getRespuesta().getNumeroComprobante());
		finalizarView.setFechaConstitucion(respuestaBO.getRespuesta().getFechaConstitucion());
		finalizarView.setMinimoDiasPrecancelar(respuestaBO.getRespuesta().getMinimoDiasPrecancelar());
		finalizarView.setSaldoInicUr(respuestaBO.getRespuesta().getSaldoInicUr());
		finalizarView.setCotizacionCodigoUr(respuestaBO.getRespuesta().getCotizacionCodigoUr());
		return respuestaFactory.crearRespuestaOk(FinalizarPlazoFijoView.class, finalizarView);
	}

	/**
	 * Creates the finalizar plazo fijo DTO.
	 *
	 * @param finalizarPlazoFijoInView the finalizar plazo fijo in view
	 * @return the finalizar plazo fijo DTO
	 * @throws BusinessException the business exception
	 */
	private FinalizarPlazoFijoDTO createFinalizarPlazoFijoDTO(FinalizarPlazoFijoInView finalizarPlazoFijoInView)
			throws BusinessException {
		FinalizarPlazoFijoDTO dto = new FinalizarPlazoFijoDTO();
		try {
			BeanUtils.copyProperties(dto, finalizarPlazoFijoInView);
			// seteo los casos especiales

			dto.setTipoPF(StringUtils.right(finalizarPlazoFijoInView.getSubproducto(), ULTIMOS_DOS));
			// seteo indicador de renovacion
			String accionesAlVencimiento = finalizarPlazoFijoInView.getAccionAlVencimiento();
			if ("RC".equals(accionesAlVencimiento) || "RCI".equals(accionesAlVencimiento)) {
				dto.setIndicadorRenovacion("S");
			} else {
				dto.setIndicadorRenovacion("N");
			}
			// seteo capitaliza intereses
			if ("RCI".equals(accionesAlVencimiento)) {
				dto.setIndicadorCapitalizaIntereses("S");
			} else {
				dto.setIndicadorCapitalizaIntereses("N");
			}

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
	 * Guarda la estadistica correspondiente a la moneda y tipoPF recibido, con el
	 * codigo de error recibido.
	 *
	 * @param codigoError        the codigo error
	 * @param tipoPf             the tipo pf
	 * @param divisa             the divisa
	 * @param cuentaOrigen       the cuenta origen
	 * @param importeCertificado the importe certificado
	 */
	private void guardarEstadisticaSegunTipoPlazoFijo(String codigoError, String tipoPf, String divisa,
			String cuentaOrigen, BigDecimal importeCertificado) {
		Estadistica estadistica = new Estadistica();
		estadistica.setMoneda(DivisaEnum.fromCodigoString(divisa).getSimbolo());
		estadistica.setNroCtaOrigen(cuentaOrigen);
		estadistica.setImporte(importeCertificado);
		if (PlazoFijoEnum.PRECANCELABLE.getCodigo().equals(tipoPf)
				&& (TipoMonedaInversionEnum.ARG.getCodigo2().equalsIgnoreCase(divisa))) {
			if (BANCA_RETAIL.equals(sessionParametros.getTipoBancaPlazoFijo())) {
				estadisticaManager.add(EstadisticasConstants.CONSTITUCION_PLAZO_FIJO_PRECANCELABLE, codigoError);
				return;
			} else if (BANCA_PRIVADA.equals(sessionParametros.getTipoBancaPlazoFijo())) {
				estadisticaManager.add(EstadisticasConstants.CONSTITUCION_PLAZO_FIJO_PRECANCELABLE_BP, codigoError);
				return;
			}
		}

		if (PlazoFijoEnum.TRADICIONAL.getCodigo().equals(StringUtils.right(tipoPf, 1))) {
			if (TipoMonedaInversionEnum.ARG.getCodigo2().equalsIgnoreCase(divisa)) {
				if (BANCA_RETAIL.equals(sessionParametros.getTipoBancaPlazoFijo())) {
					estadisticaManager.add(estadistica, EstadisticasConstants.CONSTITUCION_PLAZO_FIJO_TRADICIONAL_PESOS,
							codigoError);
					return;
				} else if (BANCA_PRIVADA.equals(sessionParametros.getTipoBancaPlazoFijo())) {
					estadisticaManager.add(estadistica,
							EstadisticasConstants.CONSTITUCION_PLAZO_FIJO_TRADICIONAL_PESOS_BP, codigoError);
					return;
				}
			} else {
				if (BANCA_RETAIL.equals(sessionParametros.getTipoBancaPlazoFijo())) {
					estadisticaManager.add(EstadisticasConstants.CONSTITUCION_PLAZO_FIJO_TRADICIONAL_DOLARES,
							codigoError);
					return;
				} else if (BANCA_PRIVADA.equals(sessionParametros.getTipoBancaPlazoFijo())) {
					estadisticaManager.add(EstadisticasConstants.CONSTITUCION_PLAZO_FIJO_TRADICIONAL_DOLARES_BP,
							codigoError);
					return;
				}
			}
		}

		if (PlazoFijoEnum.INTERESANTE_TASA_FIJA.getCodigo().equals(tipoPf)
				&& (TipoMonedaInversionEnum.ARG.getCodigo2().equalsIgnoreCase(divisa))) {
			if (BANCA_RETAIL.equals(sessionParametros.getTipoBancaPlazoFijo())) {
				estadisticaManager.add(EstadisticasConstants.CONSTITUCION_PLAZO_FIJO_INTERESANTE, codigoError);
				return;
			} else if (BANCA_PRIVADA.equals(sessionParametros.getTipoBancaPlazoFijo())) {
				estadisticaManager.add(EstadisticasConstants.CONSTITUCION_PLAZO_FIJO_INTERESANTE_BP, codigoError);
				return;
			}
		}
		if (PlazoFijoEnum.UVA_PRECANCELABLE.getCodigo().equals(tipoPf)
				&& (TipoMonedaInversionEnum.ARG.getCodigo2().equalsIgnoreCase(divisa))) {
			if (BANCA_RETAIL.equals(sessionParametros.getTipoBancaPlazoFijo())) {
				estadisticaManager.add(EstadisticasConstants.CONFIRMAR_PLAZO_FIJO_UVA_PRECANCELABLE_RTL, codigoError);
				return;
			} else if (BANCA_PRIVADA.equals(sessionParametros.getTipoBancaPlazoFijo())) {
				estadisticaManager.add(EstadisticasConstants.CONFIRMAR_PLAZO_FIJO_UVA_PRECANCELABLE_BP, codigoError);
				return;
			}
		}

		if (PlazoFijoEnum.TRADICIONAL_REPATRIACION.getCodigo().equals(tipoPf)) {
			if (BANCA_RETAIL.equals(sessionParametros.getTipoBancaPlazoFijo())) {
				estadisticaManager.add(EstadisticasConstants.FINALIZAR_PF_TRADICIONAL_REPATRIACION_RTL, codigoError);
				return;
			} else if (BANCA_PRIVADA.equals(sessionParametros.getTipoBancaPlazoFijo())) {
				estadisticaManager.add(EstadisticasConstants.FINALIZAR_PF_TRADICIONAL_REPATRIACION_BPRIV, codigoError);
				return;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.
	 * PlazoFijoManager#vaciarCachePlazosFijos()
	 */
	@Override
	public Respuesta<Boolean> vaciarCachePlazosFijos() {
		return plazoFijoBO.vaciarCachePlazosFijos();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.
	 * PlazoFijoManager#vaciarCacheTasas()
	 */
	@Override
	public Respuesta<Boolean> vaciarCacheTasas() {
		return plazoFijoBO.vaciarCacheTasas();
	}

	/**
	 * Crea DTO de entrada para el BO.
	 *
	 * @param inView the in view
	 * @return the simular plazo fijo in DTO
	 */
	private SimularPlazoFijoInDTO createDTORequestCalcularIntereses(CalcularInteresesInView inView) {
		SimularPlazoFijoInDTO inDTO = new SimularPlazoFijoInDTO();
		if (inView != null) {
			inDTO.setNumeroCuentaDebito(inView.getNumeroCuentaDebito());
			inDTO.setPlazo(inView.getPlazo());
			inDTO.setImportePlazoFijo(inView.getImportePlazoFijo());
			inDTO.setDivisa(inView.getDivisa());
			inDTO.setCantidadDias(inView.getCantidadDeDias());
		}
		return inDTO;
	}

	/**
	 * Creates the retorno view calcular intereses.
	 *
	 * @param dto the dto
	 * @return the intereses view
	 */
	private InteresesView createRetornoViewCalcularIntereses(InteresesDTO dto) {
		InteresesView outView = new InteresesView();
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
	 * Crear mapa de la vista calcular intereses.
	 *
	 * @param numeroCuentaDebito the numero cuenta debito
	 * @param importePlazoFijo   the importe plazo fijo
	 * @return the map
	 */
	private Map<String, Object> crearMapaDeLaVistaCalcularIntereses(String numeroCuentaDebito,
			BigDecimal importePlazoFijo) {
		LOGGER.info(MSJ_INFO_CREANDO_HASH_ATRIBUTOS);
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		mapaAtributos.put("numeroCuentaDebito", numeroCuentaDebito);
		mapaAtributos.put("importePlazoFijo", importePlazoFijo);
		LOGGER.info("String mapa vista: " + mapaAtributos.toString());
		return mapaAtributos;
	}

	/**
	 * Validar hash constitucion plazo fijo.
	 *
	 * @param numeroCuentaDebito the numero cuenta debito
	 * @param importePlazoFijo   the importe plazo fijo
	 */
	private void validarHashConstitucionPlazoFijo(String numeroCuentaDebito, BigDecimal importePlazoFijo) {
		String inputHash = HashUtils
				.obtenerHash(crearMapaDeLaVistaCalcularIntereses(numeroCuentaDebito, importePlazoFijo));
		LOGGER.info(MSJ_INFO_VALIDANDO_HASH, sessionParametros.getValidacionHash(), inputHash);
		HashUtils.compareHash(sessionParametros.getValidacionHash(), inputHash);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.
	 * PlazoFijoManager#calcularIntereses(ar.com.santanderrio.obp.servicios.
	 * inversiones.plazofijo.view.CalcularInteresesInView)
	 */
	@Override
	public Respuesta<InteresesView> calcularIntereses(CalcularInteresesInView inView) {

		validarHashConstitucionPlazoFijo(inView.getNumeroCuentaDebito(), inView.getImportePlazoFijo());

		Respuesta<CalcularInteresesOutView> rtaValidacion = super.validate(inView, new CalcularInteresesOutView());
		if (!rtaValidacion.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		LOGGER.info("Invocando al BO para ejecutar la consulta");
		SimularPlazoFijoInDTO dTORequest = createDTORequestCalcularIntereses(inView);
		Respuesta<InteresesDTO> respuestaBO = plazoFijoBO.calcularIntereses(dTORequest, sesionCliente.getCliente());
		InteresesView outView = createRetornoViewCalcularIntereses(respuestaBO.getRespuesta());

		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaOk(InteresesView.class, outView);

		}
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.
	 * PlazoFijoManager#consultarTasas()
	 */
	@Override
	public Respuesta<ConsultaTasasPlazosFijoView> consultarTasas(
			ConsultaTasasPlazosFijoView consultaTasasPlazosFijoView) {
		String bancaSeleccionada = consultaTasasPlazosFijoView.getBancaSeleccionada();
		Respuesta<ConsultaTasasPlazosFijoView> respuestaTasasView = plazoFijoBO
				.consultarTasas(sesionCliente.getCliente(), bancaSeleccionada);
		if (EstadoRespuesta.OK.equals(respuestaTasasView.getEstadoRespuesta())) {
			Respuesta<String> respuestaLegalConsultaTasas = legalBO
					.buscarLegal(CodigoMensajeConstantes.LEGAL_CONSULTA_TASAS);
			Respuesta<String> respuestaLegalConsultaTasasDos = legalBO
					.buscarLegal(CodigoMensajeConstantes.CONFIRMACION_PLAZO_FIJO);
			if (EstadoRespuesta.OK.equals(respuestaLegalConsultaTasas.getEstadoRespuesta())
					&& EstadoRespuesta.OK.equals(respuestaLegalConsultaTasasDos.getEstadoRespuesta())) {
				respuestaTasasView.getRespuesta().setLegales(new ArrayList<String>());
				respuestaTasasView.getRespuesta().getLegales().add(respuestaLegalConsultaTasas.getRespuesta());
				respuestaTasasView.getRespuesta().getLegales().add(respuestaLegalConsultaTasasDos.getRespuesta());
				grabarEstadisticaConsultaTasas(EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				return respuestaTasasView;
			} else {
				grabarEstadisticaConsultaTasas(EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
		}
		grabarEstadisticaConsultaTasas(EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		return respuestaTasasView;
	}

	/**
	 * Graba estadisticas para la consulta de tasas
	 * 
	 * @param codigo the codigo
	 */
	private void grabarEstadisticaConsultaTasas(String codigo) {
		if (BANCA_RETAIL.equals(sessionParametros.getTipoBancaPlazoFijo())) {
			estadisticaManager.add(EstadisticasConstants.CONSULTA_TASAS_PLAZOFIJO, codigo);
		} else if (BANCA_PRIVADA.equals(sessionParametros.getTipoBancaPlazoFijo())) {
			estadisticaManager.add(EstadisticasConstants.CONSULTA_TASAS_PLAZOFIJO_BP, codigo);
		}

		estadisticaManager.add(EstadisticasConstants.CODIGO_PLAZO_FIJO_HABILITADO_CON_TIPOS, codigo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.
	 * PlazoFijoManager#obtenerTenenciasPlazoFijo(ar.com.santanderrio.obp.
	 * servicios.inversiones.plazofijo.view.ObtenerTenenciasPlazoFijoInView)
	 */
	@Override
	public Respuesta<TenenciaPlazoFijoView> obtenerTenenciasPlazoFijo(ObtenerTenenciasPlazoFijoInView inView) {

		Respuesta<TenenciaPlazoFijoView> rtaValidacion = super.validate(inView, new TenenciaPlazoFijoView());
		if (!rtaValidacion.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		LOGGER.info("Invocando al BO para ejecutar la consulta");

		Respuesta<String> respuestaLegal = legalBO.buscarLegal(CodigoMensajeConstantes.OBTENER_TENENCIA_PF_B_PERSONAL);

		if (!EstadoRespuesta.OK.equals(respuestaLegal.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.PLAZO_FIJO_INICIO_BANCA_PERSONAL,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		TenenciaPlazoFijoView outView = new TenenciaPlazoFijoView();

		Respuesta<TenenciaTotalPlazoFijoDTO> respuestaBO = plazoFijoBO
				.obtenerTenenciasPlazoFijo(sesionCliente.getCliente());
		outView = createRetornoViewTenenciasPlazoFijo(respuestaBO.getRespuesta());
		outView.setLegales(respuestaLegal.getRespuesta());

		sessionParametros.setInfoPlazoFijo(guardarDatosPFSesion(outView));

		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			grabarEstadisticasTenenciasPlazoFijo(EstadisticasConstants.PLAZO_FIJO_INICIO_BANCA_PERSONAL,
					EstadisticasConstants.CODIGO_ESTADISTICA_TENENCIA_PLAZO_FIJO_BANCA_PERSONAL,
					respuestaBO.getEstadoRespuesta(), inView);
			return respuestaFactory.crearRespuestaOk(TenenciaPlazoFijoView.class, outView);
		} else if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
			grabarEstadisticasTenenciasPlazoFijo(EstadisticasConstants.PLAZO_FIJO_INICIO_BANCA_PERSONAL,
					EstadisticasConstants.CODIGO_ESTADISTICA_TENENCIA_PLAZO_FIJO_BANCA_PERSONAL,
					respuestaBO.getEstadoRespuesta(), inView);

			return respuestaFactory.crearRespuestaWarning(TenenciaPlazoFijoView.class, outView,
					respuestaBO.getItemsMensajeRespuesta());
		} else {
			grabarEstadisticasTenenciasPlazoFijo(EstadisticasConstants.PLAZO_FIJO_INICIO_BANCA_PERSONAL,
					EstadisticasConstants.CODIGO_ESTADISTICA_TENENCIA_PLAZO_FIJO_BANCA_PERSONAL,
					respuestaBO.getEstadoRespuesta(), inView);
			return respuestaFactory.crearRespuestaWarning(TenenciaPlazoFijoView.class, outView,
					TipoError.WARNING_GRILLA_TENENCIA, CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS, "");
		}
	}

	TenenciaPlazoFijoView guardarDatosPFSesion(TenenciaPlazoFijoView outView) {

		TenenciaPlazoFijoView plazosFijosSesion = new TenenciaPlazoFijoView();

		plazosFijosSesion.setTenenciaPlazoFijoPesos(outView.getTenenciaPlazoFijoPesos());
		plazosFijosSesion.setTenenciaPlazoFijoDolares(outView.getTenenciaPlazoFijoDolares());
		plazosFijosSesion.setTotalesGrilla(outView.getTotalesGrilla());

		return plazosFijosSesion;
	}

	/**
	 * Graba estadisticas para tenencia de PlazoFijo y para InicioBase, segun
	 * resultado.
	 *
	 * @param estadisticaInicioBase         the estadistica inicio base
	 * @param estadisticaTenenciasPlazoFijo the estadistica tenencias plazo fijo
	 * @param estadoRespuestaGrilla         the estado respuesta grilla
	 * @param inview                        the inview
	 */
	private void grabarEstadisticasTenenciasPlazoFijo(String estadisticaInicioBase,
			String estadisticaTenenciasPlazoFijo, EstadoRespuesta estadoRespuestaGrilla,
			ObtenerTenenciasPlazoFijoInView inview) {
		if (EstadoRespuesta.OK.toString().equals(inview.getEstadoTotales())) {
			if (EstadoRespuesta.OK.equals(estadoRespuestaGrilla)
					|| EstadoRespuesta.WARNING.equals(estadoRespuestaGrilla)) {
				estadisticaManager.add(estadisticaInicioBase, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				estadisticaManager.add(estadisticaTenenciasPlazoFijo, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			} else {
				estadisticaManager.add(estadisticaInicioBase, EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL);
				estadisticaManager.add(estadisticaTenenciasPlazoFijo, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}
		} else {
			if (EstadoRespuesta.OK.equals(estadoRespuestaGrilla)
					|| EstadoRespuesta.WARNING.equals(estadoRespuestaGrilla)) {
				estadisticaManager.add(estadisticaInicioBase, EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL);
				estadisticaManager.add(estadisticaTenenciasPlazoFijo, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			} else {
				estadisticaManager.add(estadisticaInicioBase, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				estadisticaManager.add(estadisticaTenenciasPlazoFijo, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}
		}

	}

	/**
	 * Creates the retorno view calcular intereses.
	 *
	 * @param DTO the dto
	 * @return the intereses view
	 */
	private TenenciaPlazoFijoView createRetornoViewTenenciasPlazoFijo(TenenciaTotalPlazoFijoDTO DTO) {
		TenenciaPlazoFijoView outView = new TenenciaPlazoFijoView();
		if (DTO != null) {
			try {
				BeanUtils.copyProperties(outView, DTO);
			} catch (Exception e) {
				LOGGER.error("Error creando View", e);
			}
		}
		return outView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.
	 * PlazoFijoManager#obtenerTenenciasPlazoFijoBPriv(ar.com.santanderrio.obp.
	 * servicios.inversiones.plazofijo.view.ObtenerTenenciasPlazoFijoInView)
	 */
	@Override
	public Respuesta<TenenciaPlazoFijoBprivView> obtenerTenenciasPlazoFijoBPriv(
			ObtenerTenenciasPlazoFijoInView inView) {

		Respuesta<TenenciaPlazoFijoBprivView> rtaValidacion = super.validate(inView, new TenenciaPlazoFijoBprivView());
		if (!rtaValidacion.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		LOGGER.info("Invocando al BO para ejecutar la consulta");

		Respuesta<String> respuestaLegal = legalBO.buscarLegal(CodigoMensajeConstantes.OBTENER_TENENCIA_PF_B_PRIVADA);

		if (!EstadoRespuesta.OK.equals(respuestaLegal.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.PLAZO_FIJO_INICIO_BANCA_PRIVADA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		TenenciaPlazoFijoBprivView outView = new TenenciaPlazoFijoBprivView();
		Respuesta<TenenciaPlazoFijoBprivDTO> respuestaBO = plazoFijoBO.obtenerTenenciasPlazoFijoBpriv(
				sesionCliente.getCliente(), sessionParametros.getCuentasInversionesPFBpriv());
		outView = createRetornoViewTenenciasPlazoFijoBpriv(respuestaBO.getRespuesta());

		outView.setLegales(respuestaLegal.getRespuesta());

		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			grabarEstadisticasTenenciasPlazoFijo(EstadisticasConstants.PLAZO_FIJO_INICIO_BANCA_PRIVADA,
					EstadisticasConstants.CODIGO_ESTADISTICA_TENENCIA_PLAZO_FIJO_BANCA_PRIVADA,
					respuestaBO.getEstadoRespuesta(), inView);
			sessionParametros.setListaPlazosFijosBP(outView.getContenidosTenenciaBpriv());
			return respuestaFactory.crearRespuestaOk(TenenciaPlazoFijoBprivView.class, outView);
		} else if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
			grabarEstadisticasTenenciasPlazoFijo(EstadisticasConstants.PLAZO_FIJO_INICIO_BANCA_PRIVADA,
					EstadisticasConstants.CODIGO_ESTADISTICA_TENENCIA_PLAZO_FIJO_BANCA_PRIVADA,
					respuestaBO.getEstadoRespuesta(), inView);
			return respuestaFactory.crearRespuestaWarning(TenenciaPlazoFijoBprivView.class, outView,
					TipoError.WARNING_PARCIAL_PLAZO_FIJO, CodigoMensajeConstantes.TOTALES_TENENCIA_INCOMPLETOS, "");
		} else {
			grabarEstadisticasTenenciasPlazoFijo(EstadisticasConstants.PLAZO_FIJO_INICIO_BANCA_PRIVADA,
					EstadisticasConstants.CODIGO_ESTADISTICA_TENENCIA_PLAZO_FIJO_BANCA_PRIVADA,
					respuestaBO.getEstadoRespuesta(), inView);
			return respuestaFactory.crearRespuestaWarning(TenenciaPlazoFijoBprivView.class, outView,
					TipoError.WARNING_GRILLA_TENENCIA, CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS, "");
		}

	}

	/**
	 * Creates the retorno view calcular intereses.
	 *
	 * @param DTO the dto
	 * @return the intereses view
	 */
	private TenenciaPlazoFijoBprivView createRetornoViewTenenciasPlazoFijoBpriv(TenenciaPlazoFijoBprivDTO DTO) {
		TenenciaPlazoFijoBprivView outView = new TenenciaPlazoFijoBprivView();
		if (DTO != null) {
			try {
				BeanUtils.copyProperties(outView, DTO);
			} catch (Exception e) {
				LOGGER.error("Error creando View", e);
			}
		}
		return outView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.
	 * PlazoFijoManager#grabarEstadisticaVerDetalle()
	 */
	@Override
	public Respuesta<ConfirmacionPlazoFijoOutView> verDetalle(AccionesAlVencimientoInView inView) {

		if (inView.isErrorParcial()) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		Respuesta<ConfirmacionPlazoFijoOutView> rtaValidacion = super.validate(inView,
				new ConfirmacionPlazoFijoOutView());
		if (!rtaValidacion.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		ConfirmacionPlazoFijoOutView confirmacionView = new ConfirmacionPlazoFijoOutView();
		confirmacionView.setListaLegales(new ArrayList<String>());
		Respuesta<String> respuestaLegal = legalBO.buscarLegal(CodigoMensajeConstantes.PLAZO_FIJO_LEGAL_VER_DETALLE);
		confirmacionView.getListaLegales().add(respuestaLegal.getRespuesta());

		if (PlazoFijoEnum.AJUSTABLE_POR_CER.getCodigo().equals(inView.getCodigoPlazo())) {
			Respuesta<String> respuestaLegalCER = legalBO
					.buscarLegal(CodigoMensajeConstantes.CER_PLAZO_FIJO_LEGAL_CONFIRMACION);
			if (EstadoRespuesta.OK.equals(respuestaLegalCER.getEstadoRespuesta())) {
				confirmacionView.getListaLegales().add(respuestaLegalCER.getRespuesta());
				grabarEstadistica(EstadisticasConstants.VER_DETALLE_TENENCIA_PLAZO_FIJO_BANCA_PERSONAL,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK, sesionCliente.getCliente());
				return respuestaFactory.crearRespuestaOk(ConfirmacionPlazoFijoOutView.class, confirmacionView);
			} else if (!EstadoRespuesta.OK.equals(respuestaLegalCER.getEstadoRespuesta())) {
				grabarEstadistica(EstadisticasConstants.VER_DETALLE_TENENCIA_PLAZO_FIJO_BANCA_PERSONAL,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR, sesionCliente.getCliente());
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
		}
		if (PlazoFijoEnum.UVA.getCodigo().equals(inView.getCodigoPlazo())) {
			Respuesta<String> respuestaLegalUVA = legalBO
					.buscarLegal(CodigoMensajeConstantes.UVA_PLAZO_FIJO_LEGAL_VER_DETALLE);
			if (EstadoRespuesta.OK.equals(respuestaLegalUVA.getEstadoRespuesta())) {
				confirmacionView.getListaLegales().add(respuestaLegalUVA.getRespuesta());
				grabarEstadistica(EstadisticasConstants.VER_DETALLE_TENENCIA_PLAZO_FIJO_BANCA_PERSONAL,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK, sesionCliente.getCliente());
				return respuestaFactory.crearRespuestaOk(ConfirmacionPlazoFijoOutView.class, confirmacionView);
			} else if (!EstadoRespuesta.OK.equals(respuestaLegalUVA.getEstadoRespuesta())) {
				grabarEstadistica(EstadisticasConstants.VER_DETALLE_TENENCIA_PLAZO_FIJO_BANCA_PERSONAL,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR, sesionCliente.getCliente());
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
		}

		if (PlazoFijoEnum.UVA_PRECANCELABLE.getCodigo().equals(inView.getCodigoPlazo())) {
			confirmacionView.getListaLegales().addAll(0, obtenerLegalesUvaPrecancelable());
			Respuesta<SolicitarPrecancelarOutDTO> respuestaBO = plazoFijoBO
					.solicitarPrecancelarPlazoFijo(transformarInView(inView), sesionCliente.getCliente(), true, false);
			if (EstadoRespuesta.ERROR.equals(respuestaBO.getEstadoRespuesta())) {
				grabarEstadistica(
						EstadisticasConstants.VER_DETALLE_TENENCIA_PLAZO_FIJO_BANCA_PERSONAL_UVA_PRECANCELABLE,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR, sesionCliente.getCliente());
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
			cargarDatosDePrecancelacion(confirmacionView, respuestaBO.getRespuesta());
			grabarEstadistica(EstadisticasConstants.VER_DETALLE_TENENCIA_PLAZO_FIJO_BANCA_PERSONAL_UVA_PRECANCELABLE,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK, sesionCliente.getCliente());
			return respuestaFactory.crearRespuestaOk(ConfirmacionPlazoFijoOutView.class, confirmacionView);
		}
		
		if(PlazoFijoEnum.TRADICIONAL_REPATRIACION.getCodigo().equals(inView.getCodigoPlazo())) {
			grabarEstadistica(EstadisticasConstants.VER_DETALLE_PF_REPATRIACION,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK, sesionCliente.getCliente());
		}else {
			grabarEstadistica(EstadisticasConstants.VER_DETALLE_TENENCIA_PLAZO_FIJO_BANCA_PERSONAL,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK, sesionCliente.getCliente());
		}
		
		return respuestaFactory.crearRespuestaOk(ConfirmacionPlazoFijoOutView.class, confirmacionView);
	}

	private void cargarDatosDePrecancelacion(ConfirmacionPlazoFijoOutView confirmacionView,
			SolicitarPrecancelarOutDTO dto) {
		confirmacionView
				.setPlazoMinimoPrecancelacion(ISBANStringUtils.eliminarCeros(dto.getPlazoMinimoPrecancelacion()));
		confirmacionView.setPorcentajeDePenalizacion(
				ISBANStringUtils.agregadorDecimales(dto.getPorcentajePenalizacion().replace(".", ","))
						+ SIGNO_PORCENTAJE);
	}

	private SolicitarPrecancelarInView transformarInView(AccionesAlVencimientoInView inView) {
		SolicitarPrecancelarInView viewPrecancelar = new SolicitarPrecancelarInView();
		viewPrecancelar.setCuentaPlazo(inView.getCuentaPlazo());
		viewPrecancelar.setNumeroSecuencia(inView.getNumeroSecuencia());
		viewPrecancelar.setFechaConstitucion(inView.getFechaConstitucion());
		return viewPrecancelar;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.
	 * PlazoFijoManager#grabarEstadisticaVerDetalleBPriv()
	 */
	@Override
	public Respuesta<ConfirmacionPlazoFijoOutView> verDetalleBPriv(AccionesAlVencimientoInView inView) {

		if (inView.isErrorParcial()) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		Respuesta<ConfirmacionPlazoFijoOutView> rtaValidacion = super.validate(inView,
				new ConfirmacionPlazoFijoOutView());
		if (!rtaValidacion.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		ConfirmacionPlazoFijoOutView confirmacionView = new ConfirmacionPlazoFijoOutView();
		confirmacionView.setListaLegales(new ArrayList<String>());
		Respuesta<String> respuestaLegal = legalBO.buscarLegal(CodigoMensajeConstantes.PLAZO_FIJO_LEGAL_VER_DETALLE);
		confirmacionView.getListaLegales().add(respuestaLegal.getRespuesta());

		if (PlazoFijoEnum.AJUSTABLE_POR_CER.getCodigo().equals(inView.getCodigoPlazo())) {
			Respuesta<String> respuestaLegalCER = legalBO
					.buscarLegal(CodigoMensajeConstantes.CER_PLAZO_FIJO_LEGAL_CONFIRMACION);
			if (EstadoRespuesta.OK.equals(respuestaLegalCER.getEstadoRespuesta())) {
				confirmacionView.getListaLegales().add(respuestaLegalCER.getRespuesta());
				grabarEstadistica(EstadisticasConstants.VER_DETALLE_TENENCIA_PLAZO_FIJO_BANCA_PRIVADA,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK, sesionCliente.getCliente());
				return respuestaFactory.crearRespuestaOk(ConfirmacionPlazoFijoOutView.class, confirmacionView);
			} else if (!EstadoRespuesta.OK.equals(respuestaLegalCER.getEstadoRespuesta())) {
				grabarEstadistica(EstadisticasConstants.VER_DETALLE_TENENCIA_PLAZO_FIJO_BANCA_PRIVADA,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR, sesionCliente.getCliente());
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
		}
		if (PlazoFijoEnum.UVA.getCodigo().equals(inView.getCodigoPlazo())) {
			Respuesta<String> respuestaLegalUVA = legalBO
					.buscarLegal(CodigoMensajeConstantes.UVA_PLAZO_FIJO_LEGAL_VER_DETALLE);
			if (EstadoRespuesta.OK.equals(respuestaLegalUVA.getEstadoRespuesta())) {
				confirmacionView.getListaLegales().add(respuestaLegalUVA.getRespuesta());
				grabarEstadistica(EstadisticasConstants.VER_DETALLE_TENENCIA_PLAZO_FIJO_BANCA_PRIVADA,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK, sesionCliente.getCliente());
				return respuestaFactory.crearRespuestaOk(ConfirmacionPlazoFijoOutView.class, confirmacionView);
			} else if (!EstadoRespuesta.OK.equals(respuestaLegalUVA.getEstadoRespuesta())) {
				grabarEstadistica(EstadisticasConstants.VER_DETALLE_TENENCIA_PLAZO_FIJO_BANCA_PRIVADA,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR, sesionCliente.getCliente());
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
		}

		if (PlazoFijoEnum.UVA_PRECANCELABLE.getCodigo().equals(inView.getCodigoPlazo())) {
			confirmacionView.getListaLegales().addAll(0, obtenerLegalesUvaPrecancelable());
			Respuesta<SolicitarPrecancelarOutDTO> respuestaBO = plazoFijoBO
					.solicitarPrecancelarPlazoFijo(transformarInView(inView), sesionCliente.getCliente(), true, true);
			if (EstadoRespuesta.ERROR.equals(respuestaBO.getEstadoRespuesta())) {
				grabarEstadistica(EstadisticasConstants.VER_DETALLE_TENENCIA_PLAZO_FIJO_BANCA_PRIVADA_UVA_PRECANCELABLE,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR, sesionCliente.getCliente());
				confirmacionView.setPlazoMinimoPrecancelacion("-");
				confirmacionView.setPorcentajeDePenalizacion("-");
				return respuestaFactory.crearRespuestaOk(ConfirmacionPlazoFijoOutView.class, confirmacionView);
			}
			cargarDatosDePrecancelacion(confirmacionView, respuestaBO.getRespuesta());
			grabarEstadistica(EstadisticasConstants.VER_DETALLE_TENENCIA_PLAZO_FIJO_BANCA_PRIVADA_UVA_PRECANCELABLE,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK, sesionCliente.getCliente());
			return respuestaFactory.crearRespuestaOk(ConfirmacionPlazoFijoOutView.class, confirmacionView);
		}

		if(PlazoFijoEnum.TRADICIONAL_REPATRIACION.getCodigo().equals(inView.getCodigoPlazo())) {
			grabarEstadistica(EstadisticasConstants.VER_DETALLE_PF_REPATRIACION_BP,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK, sesionCliente.getCliente());
		}else {
			grabarEstadistica(EstadisticasConstants.VER_DETALLE_TENENCIA_PLAZO_FIJO_BANCA_PRIVADA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK, sesionCliente.getCliente());
		}
		
		return respuestaFactory.crearRespuestaOk(ConfirmacionPlazoFijoOutView.class, confirmacionView);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.
	 * PlazoFijoManager#solicitarPrecancelar(ar.com.santanderrio.obp.servicios.
	 * inversiones.plazofijo.view.SolicitarPrecancelarInView)
	 */
	@Override
	public Respuesta<SolicitarPrecancelarOutView> solicitarPrecancelar(SolicitarPrecancelarInView inview) {
		sessionParametros.setContador(new ContadorIntentos(2));
		Respuesta<SolicitarPrecancelarOutView> rtaValidacion = super.validate(inview,
				new SolicitarPrecancelarOutView());
		if (!rtaValidacion.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		LOGGER.info("Invocando al BO para ejecutar la consulta");

		Respuesta<SolicitarPrecancelarOutDTO> respuestaBO = plazoFijoBO.solicitarPrecancelarPlazoFijo(inview,
				sesionCliente.getCliente(), false, false);

		SolicitarPrecancelarOutView outview = createRetornoViewSolicitarPrecancelar(respuestaBO.getRespuesta());

		Mensaje msg = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.INFORMACION_PLAZO_FIJO_PRECANCELAR);
		String mensaje = msg.getMensaje();
		outview.setMensaje(mensaje);

		Respuesta<String> rtaLegal = legalBO.buscarLegal(CodigoMensajeConstantes.COMPROBANTE_PRECANCELACION);
		if (rtaLegal.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			outview.setLegal(rtaLegal.getRespuesta());
		}

		if (!rtaLegal.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			guardarEstadisticaSolicitarPrecancelar(EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(SolicitarPrecancelarOutView.class, outview);
		}
		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
			return respuestaFactory.crearRespuestaWarning(SolicitarPrecancelarOutView.class, outview,
					respuestaBO.getItemsMensajeRespuesta());
		}
		guardarEstadisticaSolicitarPrecancelar(EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
	}

	/**
	 * Creates the retorno view solicitar precancelar.
	 *
	 * @param DTO the dto
	 * @return the solicitar precancelar out view
	 */
	private SolicitarPrecancelarOutView createRetornoViewSolicitarPrecancelar(SolicitarPrecancelarOutDTO DTO) {
		SolicitarPrecancelarOutView outView = new SolicitarPrecancelarOutView();
		if (DTO != null) {
			try {
				BeanUtils.copyProperties(outView, DTO);
			} catch (Exception e) {
				LOGGER.error("Error creando View", e);
			}
		}
		return outView;
	}

	/**
	 * Guardar estadistica solicitar precancelar.
	 *
	 * @param codigoError the codigo error
	 */
	private void guardarEstadisticaSolicitarPrecancelar(String codigoError) {
		if (BANCA_RETAIL.equals(sessionParametros.getTipoBancaPlazoFijo())) {
			estadisticaManager.add(EstadisticasConstants.SOLICITAR_PRECANCELAR, codigoError);
		} else if (BANCA_PRIVADA.equals(sessionParametros.getTipoBancaPlazoFijo())) {
			estadisticaManager.add(EstadisticasConstants.SOLICITAR_PRECANCELAR_BP, codigoError);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.
	 * PlazoFijoManager#simularPrecancelacion(ar.com.santanderrio.obp.servicios.
	 * inversiones.plazofijo.view.SimularPrecancelarInView)
	 */
	@Override
	public Respuesta<SimulacionPrecancelableOutView> simularPrecancelacion(SimularPrecancelarInView inView) {
		Respuesta<SimulacionPrecancelableOutView> rtaValidacion = super.validate(inView,
				new SimulacionPrecancelableOutView());
		if (!rtaValidacion.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		Respuesta<SimulacionPrecancelableDTO> respuestaBO = plazoFijoBO.precancelar(sesionCliente.getCliente(), inView,
				OPCION_SIMULACION);
		if (EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta())) {
			SimulacionPrecancelableOutView retornoView = crearView(respuestaBO.getRespuesta());
			if (retornoView != null) {
				return respuestaFactory.crearRespuestaOk(retornoView);
			}
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);

		}
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
	}

	/**
	 * Crea y carga el view en base al DTO.
	 *
	 * @param respuestaDTO the respuesta DTO
	 * @return the simulacion precancelable out view
	 */
	private SimulacionPrecancelableOutView crearView(SimulacionPrecancelableDTO respuestaDTO) {
		SimulacionPrecancelableOutView retornoView = new SimulacionPrecancelableOutView();
		try {
			BeanUtils.copyProperties(retornoView, respuestaDTO);
		} catch (IllegalAccessException e) {
			LOGGER.error("Error al simular precancelacion. ", e);
			return null;
		} catch (InvocationTargetException e) {
			LOGGER.error("Error al simular precancelacion. ", e);
			return null;
		}
		retornoView.setInteresAFecha(
				BigDecimal.valueOf(Long.valueOf(retornoView.getInteresAFecha()), DECIMALES_CAPITAL).toString());
		retornoView.setInteresAlVencimiento(
				BigDecimal.valueOf(Long.valueOf(retornoView.getInteresAlVencimiento()), DECIMALES_CAPITAL).toString());
		retornoView.setInteresesPrecancelacion(BigDecimal
				.valueOf(Long.valueOf(retornoView.getInteresesPrecancelacion()), DECIMALES_CAPITAL).toString());
		retornoView.setTotalAPagar(
				BigDecimal.valueOf(Long.valueOf(retornoView.getTotalAPagar()), DECIMALES_CAPITAL).toString());
		retornoView.setPorcentajePenalizacion(BigDecimal
				.valueOf(Long.valueOf(retornoView.getPorcentajePenalizacion()), DECIMALES_PORCENTAJE_PENALIZACION)
				.toString());
		retornoView.setFechaVencimientoPF(
				ISBANStringUtils.formatearFechaConSeparadores(retornoView.getFechaVencimientoPF()));
		return retornoView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.
	 * PlazoFijoManager#finalizarPrecancelacion(ar.com.santanderrio.obp.
	 * servicios.inversiones.plazofijo.view.SimularPrecancelarInView)
	 */
	@Override
	public Respuesta<FinalizarPrecancelableOutView> finalizarPrecancelacion(SimularPrecancelarInView inView) {

		boolean permiteReintentar;
		if (sessionParametros.getContador() != null) {
			permiteReintentar = sessionParametros.getContador().permiteReintentar();
		} else {
			LOGGER.debug("Error, Contador no inicializado!!");
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		Respuesta<FinalizarPrecancelableOutView> rtaValidacion = super.validate(inView,
				new FinalizarPrecancelableOutView());
		if (!rtaValidacion.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		FinalizarPrecancelableOutView retornoView = new FinalizarPrecancelableOutView();
		Respuesta<SimulacionPrecancelableDTO> respuestaBO = plazoFijoBO.precancelar(sesionCliente.getCliente(), inView,
				OPCION_FINALIZAR);

		if (EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta())) {
			try {
				BeanUtils.copyProperties(retornoView, respuestaBO.getRespuesta());
			} catch (IllegalAccessException e) {
				LOGGER.error("Error al finalizar precancelacion. ", e);
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			} catch (InvocationTargetException e) {
				LOGGER.error("Error al finalizar precancelacion. ", e);
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}

			if (BANCA_RETAIL.equals(sessionParametros.getTipoBancaPlazoFijo())) {
				estadisticaManager.add(EstadisticasConstants.CODIGO_FINALIZAR_PRECANCELAR,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			} else if (BANCA_PRIVADA.equals(sessionParametros.getTipoBancaPlazoFijo())) {
				estadisticaManager.add(EstadisticasConstants.CODIGO_FINALIZAR_PRECANCELAR_BP,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			}

			crearMensajeOk(inView, respuestaBO, retornoView);
			Date fechaDesde;
			try {
				fechaDesde = new SimpleDateFormat("ddMMyyyy").parse(retornoView.getFechaAltaPF());
				retornoView.setDiasTranscurridos(String.valueOf(diferenciaDias(fechaDesde, new Date())));
				String fecha = retornoView.getFechaAltaPF();
				retornoView.setFechaAltaPF(ISBANStringUtils.formatearFechaConSeparadores(fecha));

			} catch (ParseException e) {
				LOGGER.error("Error al finalizar precancelacion. ", e);
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
			return respuestaFactory.crearRespuestaOk(retornoView);
		}

		if ((TipoError.ERROR_FINALIZAR_PLAZO_FIJO_SIN_REINTENTO.toString())
				.equals(respuestaBO.getItemsMensajeRespuesta().get(0).getTipoError())) {

			if (BANCA_RETAIL.equals(sessionParametros.getTipoBancaPlazoFijo())) {
				estadisticaManager.add(EstadisticasConstants.CODIGO_FINALIZAR_PRECANCELAR,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			} else if (BANCA_PRIVADA.equals(sessionParametros.getTipoBancaPlazoFijo())) {
				estadisticaManager.add(EstadisticasConstants.CODIGO_FINALIZAR_PRECANCELAR_BP,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}

			crearMensajeError(inView, retornoView);
			return respuestaFactory.crearRespuestaError(FinalizarPrecancelableOutView.class, retornoView.getMensaje(),
					TipoError.ERROR_FINALIZAR_PLAZO_FIJO_SIN_REINTENTO.toString());
		}

		if (BANCA_RETAIL.equals(sessionParametros.getTipoBancaPlazoFijo())) {
			estadisticaManager.add(EstadisticasConstants.CODIGO_FINALIZAR_PRECANCELAR,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		} else if (BANCA_PRIVADA.equals(sessionParametros.getTipoBancaPlazoFijo())) {
			estadisticaManager.add(EstadisticasConstants.CODIGO_FINALIZAR_PRECANCELAR_BP,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}

		return manejarReintentoPrecancelacion(inView, retornoView, permiteReintentar);

	}

	/**
	 * Devuelve la diferencia en dias de dos fechas. Las fechas se reciben en
	 * formato aaMMyyyy
	 *
	 * @param fechaDesde the fecha desde
	 * @param fechaHasta the fecha hasta
	 * @return the int
	 */
	private int diferenciaDias(Date fechaDesde, Date fechaHasta) {
		int dias = (int) ((fechaHasta.getTime() - fechaDesde.getTime()) / 86400000);
		return dias;
	}

	/**
	 * Manejar reintento precancelacion.
	 *
	 * @param inView            the in view
	 * @param retornoView       the retorno view
	 * @param permiteReintentar the permite reintentar
	 * @return the respuesta
	 */
	Respuesta<FinalizarPrecancelableOutView> manejarReintentoPrecancelacion(SimularPrecancelarInView inView,
			FinalizarPrecancelableOutView retornoView, boolean permiteReintentar) {
		Respuesta<FinalizarPrecancelableOutView> respuesta = new Respuesta<FinalizarPrecancelableOutView>();
		crearMensajeError(inView, retornoView);
		if (permiteReintentar) {
			respuesta = respuestaFactory.crearRespuestaErrorPersonalizadoSinClase(retornoView.getMensaje(),
					TipoError.ERROR_FINALIZAR_PLAZO_FIJO_CON_REINTENTO.toString());
		} else {
			respuesta = respuestaFactory.crearRespuestaErrorPersonalizadoSinClase(retornoView.getMensaje(),
					TipoError.ERROR_FINALIZAR_PLAZO_FIJO_SIN_REINTENTO.toString());
		}
		return respuesta;
	}

	/**
	 * Crear mensaje ok.
	 *
	 * @param inView      the in view
	 * @param respuestaBO the respuesta BO
	 * @param retornoView the retorno view
	 */
	private void crearMensajeOk(SimularPrecancelarInView inView, Respuesta<SimulacionPrecancelableDTO> respuestaBO,
			FinalizarPrecancelableOutView retornoView) {
		BigDecimal capital = null;
		try {
			capital = ISBANStringUtils.convertirStrToBigDecimal(respuestaBO.getRespuesta().getCapital(),
					DECIMALES_CAPITAL);
		} catch (ImporteConvertException e) {
			LOGGER.error("Error al convertir String a BigDecimal ", e);
		}

		String mensaje = mensajeBO.obtenerMensajePorCodigo(FEED_BACK_POSITIVO).getMensaje();
		String mensajeFormateado = MessageFormat.format(mensaje, PLAZO_FIJO + inView.getTipoPlazo(),
				obtenerDivisa(inView) + " " + ISBANStringUtils.formatearConComaYDosDecimales(capital.toString()));
		retornoView.setMensaje(mensajeFormateado);
	}

	/**
	 * Obtener divisa.
	 *
	 * @param inView the in view
	 * @return the string
	 */
	private String obtenerDivisa(SimularPrecancelarInView inView) {
		String divisa = null;
		if ("ARS".equals(inView.getMoneda())) {
			divisa = "$";
		} else {
			divisa = "u$s";
		}
		return divisa;
	}

	/**
	 * Crear mensaje error.
	 *
	 * @param inView      the in view
	 * @param retornoView the retorno view
	 */
	private void crearMensajeError(SimularPrecancelarInView inView, FinalizarPrecancelableOutView retornoView) {
		String mensaje = mensajeBO.obtenerMensajePorCodigo(FEED_BACK_NEGATIVO).getMensaje();
		String mensajeFormateado = MessageFormat.format(mensaje, PLAZO_FIJO + inView.getTipoPlazo());
		retornoView.setMensaje(mensajeFormateado);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.
	 * PlazoFijoManager#verComprobantePrecancelacion(ar.com.santanderrio.obp.
	 * servicios.inversiones.plazofijo.view.ComprobantePlazoFijoInView)
	 */
	@Override
	public Respuesta<ComprobantePlazoFijoOutView> verComprobantePrecancelacion(
			ComprobantePlazoFijoInView comprobantePlazoFijoInView) {
		Respuesta<String> rtaLegal = legalBO.buscarLegal(CodigoMensajeConstantes.COMPROBANTE_PRECANCELACION);
		Respuesta<String> rtaLegal2 = legalBO.buscarLegal(CodigoMensajeConstantes.COMPROBANTE_PRECANCELACION_2);
		if (EstadoRespuesta.OK.equals(rtaLegal.getEstadoRespuesta())
				&& EstadoRespuesta.OK.equals(rtaLegal2.getEstadoRespuesta())) {
			ComprobantePlazoFijoOutView comprobanteResponse = new ComprobantePlazoFijoOutView();
			List<String> listaLegales = new ArrayList<String>();
			comprobanteResponse.setLegales(listaLegales);
			listaLegales.add(MessageFormat.format(rtaLegal.getRespuesta(),
					comprobantePlazoFijoInView.getMinimoDiasPrecancelar(), comprobantePlazoFijoInView.getPlazo(),
					comprobantePlazoFijoInView.getPorcentajePenalizacion()));
			listaLegales.add(rtaLegal2.getRespuesta());

			if (BANCA_RETAIL.equals(sessionParametros.getTipoBancaPlazoFijo())) {
				estadisticaManager.add(EstadisticasConstants.VER_COMPROBANTE_PRECANCELAR_PLAZO_FIJO,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			} else if (BANCA_PRIVADA.equals(sessionParametros.getTipoBancaPlazoFijo())) {
				estadisticaManager.add(EstadisticasConstants.VER_COMPROBANTE_PRECANCELAR_PLAZO_FIJO_BP,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			}

			return respuestaFactory.crearRespuestaOk(ComprobantePlazoFijoOutView.class, comprobanteResponse);
		}

		if (BANCA_RETAIL.equals(sessionParametros.getTipoBancaPlazoFijo())) {
			estadisticaManager.add(EstadisticasConstants.VER_COMPROBANTE_PRECANCELAR_PLAZO_FIJO,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		} else if (BANCA_PRIVADA.equals(sessionParametros.getTipoBancaPlazoFijo())) {
			estadisticaManager.add(EstadisticasConstants.VER_COMPROBANTE_PRECANCELAR_PLAZO_FIJO_BP,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}

		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.
	 * PlazoFijoManager#grabarEstadisticaModificarAccion()
	 */
	@Override
	public Respuesta<Void> grabarEstadisticaModificarAccion() {
		sessionParametros.setContador(new ContadorIntentos(2));
		grabarEstadistica(EstadisticasConstants.MODIFICAR_ACCION_PLAZO_FIJO,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK, sesionCliente.getCliente());
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.
	 * PlazoFijoManager#modificarAccion(ar.com.santanderrio.obp.servicios.
	 * inversiones.plazofijo.view.ModificarAccionInView)
	 */
	@Override
	public Respuesta<ModificarAccionOutView> modificarAccion(ModificarAccionInView inView) {
		Respuesta<SimularPlazoFijoOutView> rtaValidacion = super.validate(inView, new SimularPlazoFijoOutView());
		if (!rtaValidacion.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		Respuesta<String> respuestaBO = plazoFijoBO.modificarAccionVencimiento(inView, sesionCliente.getCliente());

		if (EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta())) {
			Mensaje mensajeFeedback = mensajeBO
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.FEEDBACK_OK_MODIFICAR_ACCION_PLAZO_FIJO);
			ModificarAccionOutView respuesta = new ModificarAccionOutView();
			respuesta.setMensaje(MessageFormat.format(mensajeFeedback.getMensaje(), inView.getNombrePlazoFijo(),
					TipoMonedaInversionEnum.fromCodigo2String(inView.getMoneda()).getMonedas()));
			estadisticaManager.add(EstadisticasConstants.CAMBIO_ACCION_AL_VENCIMIENTO_PLAZO_FIJO,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

			return respuestaFactory.crearRespuestaOk(ModificarAccionOutView.class, respuesta);
		}

		Mensaje mensajeFeedback = new Mensaje(
				mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.FEEDBACK_ERROR_MODIFICAR_ACCION_PLAZO_FIJO));
		mensajeFeedback.setMensaje(MessageFormat.format(mensajeFeedback.getMensaje(), inView.getNombrePlazoFijo(),
				TipoMonedaInversionEnum.fromCodigo2String(inView.getMoneda()).getMonedas()));
		estadisticaManager.add(EstadisticasConstants.CAMBIO_ACCION_AL_VENCIMIENTO_PLAZO_FIJO,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);

		if (respuestaBO.getItemsMensajeRespuesta() != null && TipoError.TIMEOUT.getDescripcion().toString()
				.equals(respuestaBO.getItemsMensajeRespuesta().get(0).getTipoError())) {
			return respuestaFactory.crearRespuestaErrorPersonalizadoSinClase(mensajeFeedback.getMensaje(),
					TipoError.ERROR_FINALIZAR_PLAZO_FIJO_SIN_REINTENTO.toString());
		}
		return evaluarReintentos(mensajeFeedback);
	}

	/**
	 * Evaluar reintentos.
	 *
	 * @param mensajeFeedback the mensaje feedback
	 * @return the respuesta
	 */
	private Respuesta<ModificarAccionOutView> evaluarReintentos(Mensaje mensajeFeedback) {
		if (sessionParametros.getContador() != null) {
			if (sessionParametros.getContador().permiteReintentar()) {
				return respuestaFactory.crearRespuestaErrorPersonalizadoSinClase(mensajeFeedback.getMensaje(),
						TipoError.ERROR_FINALIZAR_PLAZO_FIJO_CON_REINTENTO.toString());
			} else {
				return respuestaFactory.crearRespuestaErrorPersonalizadoSinClase(mensajeFeedback.getMensaje(),
						TipoError.ERROR_FINALIZAR_PLAZO_FIJO_SIN_REINTENTO.toString());
			}
		} else {
			LOGGER.debug("Error, Contador no inicializado!!");
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.
	 * PlazoFijoManager#verComprobanteModificarAccion()
	 */
	@Override
	public Respuesta<ComprobantePlazoFijoOutView> verComprobanteModificarAccion(
			VerComprobanteModificacionVencimientoView inView) {
		Respuesta<String> rtaLegal = legalBO
				.buscarLegal(CodigoMensajeConstantes.COMPROBANTE_MODIFICAR_ACCION_AL_VENCIMIENTO);
		if (EstadoRespuesta.OK.equals(rtaLegal.getEstadoRespuesta())) {
			ComprobantePlazoFijoOutView comprobanteResponse = new ComprobantePlazoFijoOutView();
			List<String> listaLegales = new ArrayList<String>();
			comprobanteResponse.setLegales(listaLegales);
			listaLegales.add(rtaLegal.getRespuesta());
			cargarHashModificacionAlVencimiento(inView);
			estadisticaManager.add(EstadisticasConstants.VER_COMPROBANTE_MODIFICAR_ACCION_AL_VENCIMIENTO,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(ComprobantePlazoFijoOutView.class, comprobanteResponse);
		}
		estadisticaManager.add(EstadisticasConstants.VER_COMPROBANTE_MODIFICAR_ACCION_AL_VENCIMIENTO,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.
	 * PlazoFijoManager#obtenerDetalleIntereses(ar.com.santanderrio.obp.
	 * servicios.inversiones.plazofijo.view.DetalleInteresesViewRequest)
	 */
	@Override
	public Respuesta<DetalleCobroInteresesViewResponse> obtenerDetalleIntereses(
			DetalleInteresesViewRequest viewRequest) {
		Respuesta<DetalleCobroInteresesViewResponse> rtaValidacion = super.validate(viewRequest,
				new DetalleCobroInteresesViewResponse());
		if (!rtaValidacion.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		return plazoFijoBO.obtenerDetalleIntereses(viewRequest, sesionCliente.getCliente());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.
	 * PlazoFijoManager#grabarEstadisticaDetallePFVencidos()
	 */
	@Override
	public Respuesta<Void> grabarEstadisticaDetallePFVencidos() {
		grabarEstadistica(EstadisticasConstants.PLAZO_FIJO_DETALLE_VENCIDOS,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK, sesionCliente.getCliente());
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.
	 * PlazoFijoManager#descargarComprobanteSuscripcionPDF(ar.com.santanderrio.
	 * obp.servicios.inversiones.plazofijo.view. ComprobanteConstitucionPlazoFijo)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteSuscripcionPDF(ComprobanteConstitucionPlazoFijo viewRequest) {
		Respuesta<Reporte> reporte;
		Respuesta<ReporteView> respuestaView;
		reporte = reporteBO.obtenerComprobantePDF(viewRequest);
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
			estadisticaManager.add(EstadisticasConstants.COMPROBANTE_CONSTITUCION_PLAZO_FIJO,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			respuestaView = respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
			estadisticaManager.add(EstadisticasConstants.COMPROBANTE_CONSTITUCION_PLAZO_FIJO,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.
	 * PlazoFijoManager#descargarComprobanteModificacionAccionVencimientoPDF(ar.
	 * com.santanderrio.obp.servicios.inversiones.plazofijo.view.
	 * ComprobanteModificacionAccionVencimiento)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteModificacionAccionVencimientoPDF(
			ComprobanteModificacionAccionVencimiento viewRequest) {
		Respuesta<Reporte> reporte;
		Respuesta<ReporteView> respuestaView;
		validarHashModificacionAlVencimiento(viewRequest);
		reporte = reporteBO.obtenerComprobantePDF(viewRequest);
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
			estadisticaManager.add(EstadisticasConstants.COMPROBANTE_MODIFICACION_ACCION_VENCIMIENTO,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			respuestaView = respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
			estadisticaManager.add(EstadisticasConstants.COMPROBANTE_MODIFICACION_ACCION_VENCIMIENTO,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.
	 * PlazoFijoManager#descargarComprobanteCancelacionPrecancelable(ar.com.
	 * santanderrio.obp.servicios.inversiones.plazofijo.view.
	 * ComprobanteCancelacionPrecancelable)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteCancelacionPrecancelable(
			ComprobanteCancelacionPrecancelable viewRequest) {
		Respuesta<Reporte> reporte;
		Respuesta<ReporteView> respuestaView;
		reporte = reporteBO.obtenerComprobantePDF(viewRequest);
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
			estadisticaManager.add(EstadisticasConstants.COMPROBANTE_CANCELACION_PRECANCELABLE,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			respuestaView = respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
			estadisticaManager.add(EstadisticasConstants.COMPROBANTE_CANCELACION_PRECANCELABLE,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaView;
	}

	/**
	 * Validar hash constitucion plazo fijo.
	 *
	 * @param inView the in view
	 */
	private void cargarHashModificacionAlVencimiento(VerComprobanteModificacionVencimientoView inView) {
		String hashView = HashUtils.obtenerHash(crearMapaDeLaVistaAccionAlVencimiento(inView));
		LOGGER.info(MSJ_INFO_GUARDANDO_HASH_EN_SESION);
		sessionParametros.setValidacionHash(hashView);
	}

	/**
	 * Crear mapa de la vista calcular intereses.
	 *
	 * @param inView the in view
	 * @return the map
	 */
	private Map<String, Object> crearMapaDeLaVistaAccionAlVencimiento(
			VerComprobanteModificacionVencimientoView inView) {
		LOGGER.info(MSJ_INFO_CREANDO_HASH_ATRIBUTOS);
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		mapaAtributos.put("tipoPlazo", inView.getTipoPlazo());
		mapaAtributos.put("moneda", inView.getMoneda());
		mapaAtributos.put("fechaVencimiento", inView.getFechaVencimiento());
		mapaAtributos.put("capitalInvertido", inView.getCapitalInvertido());
		mapaAtributos.put("importeTotal", inView.getImporteTotal());
		mapaAtributos.put("accionAlVencimiento", inView.getAccionAlVencimiento());
		LOGGER.info("String mapa vista: " + mapaAtributos.toString());
		return mapaAtributos;
	}

	/**
	 * Validar hash constitucion plazo fijo.
	 *
	 * @param inView the in view
	 */
	private void validarHashModificacionAlVencimiento(ComprobanteModificacionAccionVencimiento inView) {
		String inputHash = HashUtils.obtenerHash(crearMapaDeLaVistaComprobanteAccionAlVencimiento(inView));
		LOGGER.info(MSJ_INFO_VALIDANDO_HASH, sessionParametros.getValidacionHash(), inputHash);
		HashUtils.compareHash(sessionParametros.getValidacionHash(), inputHash);
	}

	/**
	 * Crear mapa de la vista calcular intereses.
	 *
	 * @param inView the in view
	 * @return the map
	 */
	private Map<String, Object> crearMapaDeLaVistaComprobanteAccionAlVencimiento(
			ComprobanteModificacionAccionVencimiento inView) {
		LOGGER.info(MSJ_INFO_CREANDO_HASH_ATRIBUTOS);
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		mapaAtributos.put("tipoPlazo", inView.getTipoPlazoFijo());
		mapaAtributos.put("moneda", inView.getMoneda());
		mapaAtributos.put("fechaVencimiento", inView.getFechaVencimiento());
		mapaAtributos.put("capitalInvertido", inView.getCapitalInvertido());
		mapaAtributos.put("importeTotal", inView.getImporteTotal());
		mapaAtributos.put("accionAlVencimiento", inView.getAccionAlVencimiento());
		LOGGER.info("String mapa vista: " + mapaAtributos.toString());
		return mapaAtributos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.
	 * PlazoFijoManager#vaciarCacheAcciones()
	 */
	@Override
	public Respuesta<Boolean> vaciarCacheAcciones() {
		return plazoFijoBO.vaciarCacheAcciones();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.
	 * PlazoFijoManager#altaComprobante(ar.com.santanderrio.obp.servicios.
	 * inversiones.plazofijo.view.AltaComprobantePlazoFijoView)
	 */
	@Override
	public Respuesta<AltaComprobantePlazoFijoView> altaComprobante(AltaComprobantePlazoFijoView viewRequest) {
		Respuesta<AltaComprobantePlazoFijoView> rtaValidacion = super.validate(viewRequest,
				new AltaComprobantePlazoFijoView());
		if (!rtaValidacion.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		AltaComprobantePlazoFijoDTO altaComprobantePlazoFijoDTO = sessionParametros.getAltaComprobantePlazoFijoDTO();
		altaComprobantePlazoFijoDTO.setCodigoPlazo(viewRequest.getCodigoPlazo());
		Respuesta<AltaComprobantePlazoFijoDTO> respuestaAltaComprobante = plazoFijoBO
				.altaComprobante(altaComprobantePlazoFijoDTO, sesionCliente.getCliente());
		if (EstadoRespuesta.OK.equals(respuestaAltaComprobante.getEstadoRespuesta())) {
			return respuestaFactory.crearRespuestaOk(viewRequest);
		}
		LOGGER.error("No se pudo dar de alta el comprobante en SCOMP. ");
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.
	 * PlazoFijoManager#obtenerOrdenesPorCuenta(ar.com.santanderrio.obp.
	 * servicios.inversiones.plazofijo.view.ObtenerOrdenesViewRequest)
	 */
	@Override
	public Respuesta<OrdenesView> obtenerOrdenesPorCuenta(ObtenerOrdenesViewRequest request) {
		sessionParametros.setContador(new ContadorIntentos(2));
		OrdenesView ordenesView = new OrdenesView();
		if (request.getNumeroCuenta() == null) {
			// PRIMER LLAMADO, CARGA ORDENES Y LISTA DE CUENTAS
			cargarCuentas(ordenesView);
		} else {
			// SEGUNDO LLAMADO, SOLO CARGA ORDENES DE LA CUENTA PEDIDA
			ordenesView.setCuentaSeleccionada(request.getNumeroCuenta());
		}
		Respuesta<OrdenesDTO> respuestaOrdenes = null;
		String codigoMensajeBuscado;
		String codigoEstadisticaAGrabar;
		if (request.getTipoConsulta() == null && (request.getFechaDesde() == null || request.getFechaHasta() == null)) {
			respuestaOrdenes = ordenesBO.buscarOrdenesOperacionesPorCuenta(ordenesView.getCuentaSeleccionada(),
					CodigoSistemaLoadOrdenesEnum.PLAZO_FIJO.getCodigoSistema());
			codigoMensajeBuscado = CodigoMensajeConstantes.PLAZO_FIJO_SIN_ORDENES_Y_OPERACIONES_ULTIMOS_30_DIAS;
			codigoEstadisticaAGrabar = EstadisticasConstants.CONSULTA_ORDENES_OPERACIONES_PLAZO_FIJO_BPRIV;
		} else {
			respuestaOrdenes = ordenesBO.buscarOrdenesOperaciones(crearFiltroBusqueda(request),
					CodigoSistemaLoadOrdenesEnum.PLAZO_FIJO.getCodigoSistema());
			codigoMensajeBuscado = CodigoMensajeConstantes.INVERSIONES_SIN_ORDENES_Y_OPERACIONES;
			codigoEstadisticaAGrabar = EstadisticasConstants.CONSULTA_ORDENES_INVERSIONES;
		}

		if (!EstadoRespuesta.OK.equals(respuestaOrdenes.getEstadoRespuesta())) {
			estadisticaManager.add(codigoEstadisticaAGrabar, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(OrdenesView.class, ordenesView, "", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		if (respuestaOrdenes.getRespuesta().getOrdenes().isEmpty()) {
			estadisticaManager.add(codigoEstadisticaAGrabar, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaWarning(OrdenesView.class, ordenesView, TipoError.LISTAVACIA,
					codigoMensajeBuscado, "");
		}

		estadisticaManager.add(codigoEstadisticaAGrabar, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		ordenesView.setOrdenes(dtoToViewOrdenesOperaciones(respuestaOrdenes.getRespuesta()));

		return respuestaFactory.crearRespuestaOk(OrdenesView.class, ordenesView);
	}

	/**
	 * Crear filtro busqueda.
	 *
	 * @param request the request
	 * @return the filtros ordenes view
	 */
	private FiltrosOrdenesView crearFiltroBusqueda(ObtenerOrdenesViewRequest request) {
		FiltrosOrdenesView filtroCreado = new FiltrosOrdenesView();

		if (request.getFechaDesde() != null && request.getFechaHasta() != null) {
			try {
				Date fechaFormatoDate = null;
				String fechaConGuiones = null;
				fechaFormatoDate = formatterIn.parse(request.getFechaDesde());
				fechaConGuiones = formatterOut.format(fechaFormatoDate);
				filtroCreado.setFechaDesde(fechaConGuiones);

				fechaFormatoDate = formatterIn.parse(request.getFechaHasta());
				fechaConGuiones = formatterOut.format(fechaFormatoDate);
				filtroCreado.setFechaHasta(fechaConGuiones);
				filtroCreado.setCuentaSeleccionada(request.getNumeroCuenta());
			} catch (ParseException e) {
				LOGGER.error("Fecha desde/hasta recibida por parametro para filtrar, invalida. ", e);
			}
		} else {
			filtroCreado.setFechaDesde(formatter.format(
					FechaUtils.obtenerFechaDesde(CodigoSistemaLoadOrdenesEnum.PLAZO_FIJO.getDiasAtrasPorDefecto())));
			filtroCreado.setFechaHasta(formatter.format(new Date()));
		}
		if (request.getTipoConsulta() != null) {
			if (OPERACIONES.equalsIgnoreCase(request.getTipoConsulta())) {
				filtroCreado.setTipoSeleccionado("E");
			} else {
				filtroCreado.setTipoSeleccionado("R");
			}
		}
		return filtroCreado;
	}

	/**
	 * Cargar cuentas.
	 *
	 * @param ordenesView the ordenes view
	 */
	private void cargarCuentas(OrdenesView ordenesView) {
		for (CuentaBancaPrivada parCuentaTitOp : sesionCliente.getCliente().getCuentasBancaPrivada()) {
			CuentaView cuentaView = cuentaEntityToView(parCuentaTitOp.getCuentaOperativa());
			ordenesView.getCuentas().add(cuentaView);
		}
		ordenesView.setCuentaSeleccionada(ordenesView.getCuentas().get(0).getNumero());
	}

	/**
	 * Cuenta entity to view.
	 *
	 * @param cuenta the cuenta
	 * @return the cuenta view
	 */
	private CuentaView cuentaEntityToView(Cuenta cuenta) {
		CuentaView cuentaView = new CuentaView();
		cuentaView.setAlias(cuenta.getAlias());
		cuentaView.setCbu(cuenta.getCbu());
		cuentaView.setDescripcionTipoCuenta(cuenta.getTipoCuentaEnum().getDescripcionConMoneda());
		cuentaView.setIntervinientes(cuenta.getIntervinientes());
		cuentaView.setIsFavorito(cuenta.getIsFavorita());
		cuentaView.setNumero(cuenta.getNroCuentaProducto());
		cuentaView.setNumeroFormateado(ISBANStringUtils.obtenerNumeroCuentaDesdeCBU(cuenta.getCbu()));
		return cuentaView;
	}

	/**
	 * Dto to view ordenes operaciones.
	 *
	 * @param respuesta the respuesta
	 * @return the list
	 */
	private List<OrdenBaseView> dtoToViewOrdenesOperaciones(OrdenesDTO respuesta) {

		List<OrdenBaseView> listaOrdenesView = new ArrayList<OrdenBaseView>();

		for (OrdenBaseDTO ordenBaseDto : respuesta.getOrdenes()) {

			OrdenPlazoFijoDTO ordenPFDto = (OrdenPlazoFijoDTO) ordenBaseDto;
			OrdenPlazoFijoView ordenPFView = new OrdenPlazoFijoView();

			ordenPFView.setAccionAlVencimiento(ordenPFDto.getAccionAlVencimiento());
			ordenPFView.setCapital(ordenPFDto.getCapital());
			ordenPFView.setEstado(ordenPFDto.getEstado());
			ordenPFView.setFecha(ordenPFDto.getFecha());
			ordenPFView.setFechaLiquidacion(ordenPFDto.getFechaLiquidacion());
			ordenPFView.setMonedaLiq(
					TipoMonedaInversionEnum.fromLetraInicialString(ordenPFDto.getMonedaLiq()).getSimbolo());
			ordenPFView.setNumero(ordenPFDto.getNumero());
			ordenPFView.setPlazo(ordenPFDto.getPlazo());
			ordenPFView.setTipoPlazoFijo(ordenPFDto.getTipoPlazoFijo());
			ordenPFView.setTna(ISBANStringUtils.formatearConComaYDosDecimales(ordenPFDto.getTna()));
			ordenPFView.setPuedeConfirmar(ordenPFDto.isPuedeConfirmar());

			OrdenBaseView ordenView = (OrdenBaseView) ordenPFView;
			listaOrdenesView.add(ordenView);
		}
		return listaOrdenesView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.
	 * PlazoFijoManager#grabarEstadisticaDetalleOrdenes()
	 */
	@Override
	public Respuesta<Void> grabarEstadisticaDetalleOrdenes() {
		grabarEstadistica(EstadisticasConstants.PLAZO_FIJO_DETALLE_ORDENES,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK, sesionCliente.getCliente());
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.
	 * PlazoFijoManager#grabarEstadisticaConfirmarOrdenes()
	 */
	@Override
	public Respuesta<Void> grabarEstadisticaConfirmarOrdenes() {
		grabarEstadistica(EstadisticasConstants.PLAZO_FIJO_CONFIRMAR_ORDENES,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK, sesionCliente.getCliente());
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.
	 * PlazoFijoManager#confirmarOrden(ar.com.santanderrio.obp.servicios.
	 * inversiones.ordenes.entity.ConfirmarOrdenInEntity)
	 */
	@Override
	public Respuesta<ConfirmarOrdenPFView> confirmarOrden(ConfirmarOrdenInEntity entity) {

		entity.setCodigoSist(CodigoSistemaLoadOrdenesEnum.PLAZO_FIJO.getCodigoSistema());
		entity.setRegMant(null);
		Respuesta<ConfirmarOrdenPFView> respuestaBO = ordenesBO.confirmarOrden(entity);

		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return efectuarLogicaConfirmarOrdenOk(respuestaBO);
		}
		return efectuarLogicaConfirmarOrdenError(respuestaBO);
	}

	/**
	 * Efectuar logica confirmar orden ok.
	 *
	 * @param respuestaBO the respuesta BO
	 * @return the respuesta
	 */
	private Respuesta<ConfirmarOrdenPFView> efectuarLogicaConfirmarOrdenOk(
			Respuesta<ConfirmarOrdenPFView> respuestaBO) {

		ConfirmarOrdenPFView view = createRetornoView(respuestaBO.getRespuesta());
		Estadistica estadistica = new Estadistica(EstadisticasConstants.CODIGO_CONFIRMAR_ORDEN,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		estadisticaManager.add(estadistica, sessionParametros.getRegistroSession(), sesionCliente.getCliente());
		return respuestaFactory.crearRespuestaOk(ConfirmarOrdenPFView.class, view);
	}

	/**
	 * Efectuar logica confirmar orden error.
	 *
	 * @param respuestaBO the respuesta BO
	 * @return the respuesta
	 */
	private Respuesta<ConfirmarOrdenPFView> efectuarLogicaConfirmarOrdenError(
			Respuesta<ConfirmarOrdenPFView> respuestaBO) {

		Estadistica estadistica = new Estadistica(EstadisticasConstants.CODIGO_CONFIRMAR_ORDEN,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		estadisticaManager.add(estadistica, sessionParametros.getRegistroSession(), sesionCliente.getCliente());
		return respuestaFactory.crearRespuestaError(ConfirmarOrdenPFView.class,
				respuestaBO.getItemsMensajeRespuesta().get(0).getMensaje(),
				respuestaBO.getItemsMensajeRespuesta().get(0).getTag());
	}

	/**
	 * Creates the retorno view.
	 *
	 * @param dto the dto
	 * @return the confirmar orden PF view
	 */
	private ConfirmarOrdenPFView createRetornoView(ConfirmarOrdenPFView dto) {
		ConfirmarOrdenPFView viewReturn = new ConfirmarOrdenPFView();
		if (dto != null) {
			viewReturn.setMensajeConfirmacion(dto.getMensajeConfirmacion());
		}
		return viewReturn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.
	 * PlazoFijoManager#obtenerRendimiento()
	 */
	@Override
	public Respuesta<RendimientoView> obtenerRendimiento() {

		Respuesta<RendimientoDTO> rtaRendimiento = rendimientoBOImpl
				.obtenerRendimientoTenenciaRTL(sesionCliente.getCliente(), TipoProductoEnum.PLAZO_FIJO.getCodigo());
		String codigoEstadistica = EstadisticasConstants.RENDIMIENTO_PLAZO_FIJO;

		RendimientoDTO rendimientoDTO = rtaRendimiento.getRespuesta();
		RendimientoView rendimientoView = createRetornoView(rendimientoDTO);
		rendimientoView.setTooltip(obtenerMensajeRendimiento());

		if (EstadoRespuesta.OK.equals(rtaRendimiento.getEstadoRespuesta())) {
			estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(RendimientoView.class, rendimientoView);
		}
		if (EstadoRespuesta.WARNING.equals(rtaRendimiento.getEstadoRespuesta())) {
			estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL);
			return respuestaFactory.crearRespuestaWarning(rendimientoView, "", TipoError.WARNING_PARCIAL_PLAZO_FIJO,
					"");
		}
		estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
	}

	/**
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.
	 * PlazoFijoManager#obtenerRendimientoBPriv()
	 */
	@Override
	public Respuesta<RendimientoBPrivView> obtenerRendimientoBPriv() {
		Respuesta<RendimientoBPrivDTO> rtaRendimiento = rendimientoBOImpl
				.obtenerRendimientoTenenciaBPriv(sesionCliente.getCliente(), TipoProductoEnum.PLAZO_FIJO.getCodigo());

		String codigoEstadistica = EstadisticasConstants.RENDIMIENTO_PLAZO_FIJO_BP;
		RendimientoBPrivDTO rendimientoBPrivDTO = rtaRendimiento.getRespuesta();
		RendimientoBPrivView rendimientoBPrivView = createRetornoViewBPriv(rendimientoBPrivDTO);
		rendimientoBPrivView.setTooltip(obtenerMensajeRendimiento());

		if (EstadoRespuesta.OK.equals(rtaRendimiento.getEstadoRespuesta())) {
			estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(RendimientoBPrivView.class, rendimientoBPrivView);
		}
		if (EstadoRespuesta.WARNING.equals(rtaRendimiento.getEstadoRespuesta())) {
			estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR_PARCIAL);
			return respuestaFactory.crearRespuestaWarning(rendimientoBPrivView, "",
					TipoError.WARNING_PARCIAL_PLAZO_FIJO, "");
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.
	 * PlazoFijoManager#obtenerDetallePFInteresanteBP(ar.com.santanderrio.obp.
	 * servicios.inversiones.comun.view.DetallePFInteresanteInView)
	 */
	@Override
	public Respuesta<List<FrecuenciaCobroPFInteresanteView>> obtenerDetallePFInteresanteBP(
			DetallePFInteresanteInView detalleIn) {

		Respuesta<List<FrecuenciaCobroPFInteresanteView>> respuesta;

		try {
			List<FrecuenciaCobroPFInteresanteOutEntity> listaFrecuencias = plazoFijoBO
					.obtenerDetallePFInteresanteBP(detalleIn);
			respuesta = armarListaDeFrecuenciasView(listaFrecuencias, detalleIn);
		} catch (BusinessException e) {
			respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		return respuesta;
	}

	/**
	 * Armar lista de frecuencias view.
	 *
	 * @param listaFrecuencias the lista frecuencias
	 * @param detalleIn        the detalle in
	 * @return the respuesta
	 */
	private Respuesta<List<FrecuenciaCobroPFInteresanteView>> armarListaDeFrecuenciasView(
			List<FrecuenciaCobroPFInteresanteOutEntity> listaFrecuencias, DetallePFInteresanteInView detalleIn) {

		List<FrecuenciaCobroPFInteresanteView> listaFrecuenciasView = new ArrayList<FrecuenciaCobroPFInteresanteView>();
		for (FrecuenciaCobroPFInteresanteOutEntity frecuenciaEntity : listaFrecuencias) {
			FrecuenciaCobroPFInteresanteView frecuenciaView = new FrecuenciaCobroPFInteresanteView(frecuenciaEntity,
					detalleIn);
			listaFrecuenciasView.add(frecuenciaView);
		}

		return respuestaFactory.crearRespuestaOk(listaFrecuenciasView);
	}

	@Override
	public Response exportarMovimientos(TipoBancaEnum tipoBanca) {

		Respuesta<Reporte> respuestaReporte;
		if (TipoBancaEnum.BANCA_PERSONAL.equals(tipoBanca)) {
			respuestaReporte = plazoFijoBO.obtenerPlazosFijosReporte(sessionParametros.getInfoPlazoFijo(),
					sesionCliente.getCliente());
		} else {
			respuestaReporte = plazoFijoBO.obtenerPlazosFijosReporteBP(sessionParametros.getListaPlazosFijosBP(),
					sesionCliente.getCliente());
		}
		ResponseBuilder responseBuilder = null;
		Respuesta<ReporteView> respuestaView = new Respuesta<ReporteView>();
		if (EstadoRespuesta.OK.equals(respuestaReporte.getEstadoRespuesta())) {
			ReporteView resumenesView = ReporteView.fromReporte(respuestaReporte.getRespuesta());
			respuestaView.setRespuesta(resumenesView);
			respuestaView.setEstadoRespuesta(EstadoRespuesta.OK);
			responseBuilder = Response.ok((Object) respuestaView.getRespuesta().getByteArray());
			responseBuilder.header("Content-Disposition", "attachment; filename=\"TenenciaPlazosFijos.xls\"");
			estadisticaManager.add(
					TipoBancaEnum.BANCA_PERSONAL.equals(tipoBanca)
							? EstadisticasConstants.DESCARGA_EXCEL_PLAZOS_FIJOS_RETAIL
							: EstadisticasConstants.DESCARGA_EXCEL_PLAZOS_FIJOS_PRIVADA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			estadisticaManager.add(
					TipoBancaEnum.BANCA_PERSONAL.equals(tipoBanca)
							? EstadisticasConstants.DESCARGA_EXCEL_PLAZOS_FIJOS_RETAIL
							: EstadisticasConstants.DESCARGA_EXCEL_PLAZOS_FIJOS_PRIVADA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			respuestaView = respuestaFactory.crearRespuestaErrorPersonalizado(ReporteView.class, MENSAJE_ERROR_EXCEL,
					TipoError.ERROR_DESCARGA_EXCEL.getDescripcion());
			responseBuilder = Response.ok((Object) respuestaView);
		}
		return responseBuilder.build();

	}

	@Override
	public Respuesta<SimularPrecancelarUVAView> simularPrecancelarUVA(SimularPrecancelarInView request) {
		sessionParametros.setContador(new ContadorIntentos(2));
		String estadistica = "BP".equalsIgnoreCase(request.getBanca())
				? EstadisticasConstants.SIMULAR_PRECANCELAR_PF_UVA_BP
				: EstadisticasConstants.SIMULAR_PRECANCELAR_PF_UVA;
		Respuesta<String> legales = legalBO.buscarLegal(CodigoMensajeConstantes.LEGAL_PRECANCELACION_PF_UVA);
		if (!EstadoRespuesta.OK.equals(legales.getEstadoRespuesta())) {
			estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		Respuesta<SimulacionPrecancelableUVADTO> rta = plazoFijoBO.precancelarUVA(sesionCliente.getCliente(), request,
				OPCION_SIMULACION);

		if (EstadoRespuesta.ERROR.equals(rta.getEstadoRespuesta())) {
			estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			if (TipoError.ERROR_FINALIZAR_PLAZO_FIJO_SIN_REINTENTO.toString()
					.equals(rta.getItemsMensajeRespuesta().get(0).getTipoError())) {
				rta.getItemsMensajeRespuesta().get(0).setTipoError(TipoError.ERROR_GENERICO.toString());
			}
			return respuestaFactory.crearRespuestaErrorPersonalizado(SimularPrecancelarUVAView.class,
					rta.getItemsMensajeRespuesta());
		}

		estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return crearRespuestaOkSimularPrecancelacionUVA(rta.getRespuesta(), legales.getRespuesta());
	}

	private Respuesta<SimularPrecancelarUVAView> crearRespuestaOkSimularPrecancelacionUVA(
			SimulacionPrecancelableUVADTO respuesta, String legal) {
		SimularPrecancelarUVAView rta = new SimularPrecancelarUVAView();
		rta.setMensaje(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_INFO_PRECANCELACION_PF_UVA)
				.getMensaje());
		rta.setLegal(legal);
		rta.setFechaSolicitud(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		rta.setFechaAcreditacion(ISBANStringUtils.formatearFechaPrecancelableUVA(respuesta.getFechaPrecancelacion()));
		rta.setIntereses(respuesta.getInteresPrecancelacion());
		rta.setCapitalInicial(formatearDecimalesPrecancelableUVA(respuesta.getCapital(), 2));
		rta.setTasaNominalAnual(formatearDecimalesPrecancelableUVA(respuesta.getPorcentajePenalizacion(), 5));
		rta.setTotalACobrar(formatearDecimalesPrecancelableUVA(respuesta.getTotalAPagar(), 2));
		return respuestaFactory.crearRespuestaOk(rta);
	}

	@Override
	public Respuesta<FinalizarPrecancelarUVAView> finalizarPrecancelarUVA(SimularPrecancelarInView request) {
		boolean permiteReintentar;
		if (sessionParametros.getContador() != null) {
			permiteReintentar = sessionParametros.getContador().permiteReintentar();
		} else {
			LOGGER.debug("Error, Contador no inicializado!!");
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		String estadistica = "BP".equalsIgnoreCase(request.getBanca())
				? EstadisticasConstants.FINALIZAR_PRECANCELAR_PF_UVA_BP
				: EstadisticasConstants.FINALIZAR_PRECANCELAR_PF_UVA;
		Respuesta<String> legales = legalBO
				.buscarLegal(CodigoMensajeConstantes.COMPROBANTE_MODIFICAR_ACCION_AL_VENCIMIENTO);
		if (!EstadoRespuesta.OK.equals(legales.getEstadoRespuesta())) {
			estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		Respuesta<SimulacionPrecancelableUVADTO> rta = plazoFijoBO.precancelarUVA(sesionCliente.getCliente(), request,
				OPCION_FINALIZAR);

		if (EstadoRespuesta.ERROR.equals(rta.getEstadoRespuesta())) {
			estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			if (TipoError.ERROR_FINALIZAR_PLAZO_FIJO_SIN_REINTENTO.toString()
					.equals(rta.getItemsMensajeRespuesta().get(0).getTipoError())) {
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
			return manejarRespuestaErrorFinalizarUVA(rta, permiteReintentar);
		}

		estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return crearRespuestaOkFinalizarPrecancelacionUVA(rta.getRespuesta(), legales.getRespuesta());
	}

	private Respuesta<FinalizarPrecancelarUVAView> manejarRespuestaErrorFinalizarUVA(
			Respuesta<SimulacionPrecancelableUVADTO> rta, boolean permiteReintentar) {
		if (permiteReintentar) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_FINALIZAR_PLAZO_FIJO_CON_REINTENTO,
					CodigoMensajeConstantes.FINALIZAR_PRECANCELACION_PF_UVA_ERROR);
		}
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_FINALIZAR_PLAZO_FIJO_SIN_REINTENTO,
				CodigoMensajeConstantes.FINALIZAR_PRECANCELACION_PF_UVA_ERROR);
	}

	private Respuesta<FinalizarPrecancelarUVAView> crearRespuestaOkFinalizarPrecancelacionUVA(
			SimulacionPrecancelableUVADTO respuesta, String legal) {
		FinalizarPrecancelarUVAView rta = new FinalizarPrecancelarUVAView();
		rta.setFechaSolicitud(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		rta.setFechaAcreditacion(ISBANStringUtils.formatearFechaPrecancelableUVA(respuesta.getFechaPrecancelacion()));
		rta.setIntereses(respuesta.getInteresPrecancelacion());
		rta.setMensaje(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.FINALIZAR_PRECANCELACION_PF_UVA_OK)
				.getMensaje());
		rta.setCapitalInicial(formatearDecimalesPrecancelableUVA(respuesta.getCapital(), 2));
		rta.setTasaNominalAnual(formatearDecimalesPrecancelableUVA(respuesta.getPorcentajePenalizacion(), 5));
		rta.setTotalACobrar(formatearDecimalesPrecancelableUVA(respuesta.getTotalAPagar(), 2));
		rta.setLegal(legal);
		rta.setFechaHora(ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date()));
		rta.setDiasTranscurridos(Integer.valueOf(respuesta.getPlazoRealPF()));
		return respuestaFactory.crearRespuestaOk(rta);
	}

	@Override
	public Respuesta<Void> verComprobantePrecancelarUVA(String banca) {
		String estadistica = "BP".equalsIgnoreCase(banca) ? EstadisticasConstants.VER_COMPROBANTE_PRECANCELAR_PF_UVA_BP
				: EstadisticasConstants.VER_COMPROBANTE_PRECANCELAR_PF_UVA;
		estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(null);
	}

	@Override
	public Respuesta<ReporteView> descargarComprobanteCancelacionPrecancelableUVA(
			ComprobanteCancelacionPrecancelableUVA viewRequest) {
		Respuesta<Reporte> reporte;
		Respuesta<ReporteView> respuestaView;
		reporte = reporteBO.obtenerComprobantePDF(viewRequest);
		String estadistica = "BP".equalsIgnoreCase(viewRequest.getBanca())
				? EstadisticasConstants.DESCARGA_COMPROBANTE_PRECANCELAR_PF_UVA_BP
				: EstadisticasConstants.DESCARGA_COMPROBANTE_PRECANCELAR_PF_UVA;
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
			estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			respuestaView = respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
			estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaView;
	}

	@Override
	public Respuesta<RecomendacionResponseEntity> obtenerRecomendacionManager(RecomendacionInView viewRequest){
		Respuesta<RecomendacionResponseEntity> respuesta;
		
		RecomendacionResponseEntity recomendacionView= plazoFijoBO.obtenerRecomendacion(viewRequest);
		
		if (recomendacionView.getDatos() == null) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
		}else {
			respuesta=respuestaFactory.crearRespuestaOk(RecomendacionResponseEntity.class,recomendacionView);
			}
		
		return respuesta;
	}
	
	
}
