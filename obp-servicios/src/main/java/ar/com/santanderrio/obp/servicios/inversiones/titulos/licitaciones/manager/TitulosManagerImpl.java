package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.xml.bind.JAXBException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.comun.JaxbUtils;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.inversiones.EvaluacionDeRiesgoResponse;
import ar.com.santanderrio.obp.generated.webservices.inversiones.entities.Mensaje;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentosEnum;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.contrato.bo.ContratoBO;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.CampoEnum;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.SinonimoEnum;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaBancaPrivada;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.TipoMonedaInversionEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.InversionWSHelper;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoProductoEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dto.RendimientoBPrivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dto.RendimientoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.manager.BaseManager;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RendimientoBPrivView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RendimientoView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.bo.FondoBO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TenenciaTitulosCuentaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TenenciaTitulosOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CuentaTituloView;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.bo.RendimientoBOImpl;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo.TitulosBO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.CondicionesGeneralesCuentaCustodiaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.ConsultaOperacionesDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.ConsultaOrdenesDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.ConsultarOrdenesDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.ConsultarOrdenesOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.ConsultarOrdenesPorCuentaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.FinalizarReversarOrdenDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.LicitacionesVigentesOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.NuevaLicitacionDTOResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.OperacionDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CompraVtaTitulosResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaOperaciones;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConsultaEspeciesResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.EspeciesEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ListarOperaciones;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.NuevaLicitacionDTOResponseBPriv;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerSaldoCuentasCustodiaResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.OperacionesPorCuenta;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.OrdenesPorCuenta;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ReversarOrdenResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.BuscadorEspeciesView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.BusquedaOrdenesCompra;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteAceptacionCNV;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteAceptacionCNVRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteCnvViewResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteLicitacionOutView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteOrdenCompra;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteOrdenCompraView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteReversaLicitacion;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteReversaLicitacionCanje;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteReversaOrdenCompraVenta;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteSuscripcionLicitacion;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteSuscripcionLicitacionCanje;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.CondicionesGeneralesViewRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfiguracionOrdenesCompraRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfiguracionOrdenesCompraResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfigurarLicitacionBPrivOutView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfigurarLicitacionOutView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfigurarLicitacionViewReq;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfirmarOrdenCompraRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfirmarOrdenCompraResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConsultaOperacionesView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConsultaOrdenesEstadisticaRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConsultaOrdenesView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConsultarOrdenesPorCuentaOutView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.CuentaTituloParaLicitarView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.CuentaTituloParaLicitarViewBPriv;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.DetalleTituloInView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.DetalleTitulosBPView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.EspeciesView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.EstadisticasTotalesInView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.FinalizarLicitacionCanjeView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.FinalizarLicitacionRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.FinalizarLicitacionViewResp;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.FinalizarReversarOrdenView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.FirmarCuentasViewRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.LicitacionCanjeRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.LicitacionCanjeResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.LicitacionVigente;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.LicitacionesVIgentesBprivOutView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.LicitacionesVigentesOutView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ListarComprobantesViewRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ListarComprobantesViewResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.NuevaLicitacionViewReq;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.NuevaLicitacionViewResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.NuevaLicitacionViewResponseBPriv;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ObtenerComprobanteViewRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ReversaRequestView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ReversarOrdenViewResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.SimularLicitacionCanjeRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.SimularLicitacionCanjeResponseView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.SimularLicitacionRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.SimularLicitacionViewResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.TenenciaTitulosOutView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenes.entity.TipoOrdenDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenes.view.ConsultarOrdenesViewRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenes.view.EstadisticasOrdenesVentaView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenes.view.FinalizarReversarOrdenViewRequest;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.ReversarCompraVentaRequestView;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.ReversarCompraVentaViewResponse;

/**
 * The Class TitulosManagerImpl.
 *
 * @author juan.pablo.picate
 */
@Component("licitacionesManager")
public class TitulosManagerImpl extends BaseManager implements TitulosManager {

	/** The Constant TIPO_OPERACION_LICITACIONES. */
	private static final String TIPO_OPERACION_LICITACIONES = "L";

	/** The Constant CEDEARS. */
	private static final String CEDEARS = "Cedears";

	/** The Constant CODIGO_CEDEARS. */
	private static final String CODIGO_CEDEARS = "CDR";

	/** The Constant ACCIONES. */
	private static final String ACCIONES = "Acciones";

	/** The Constant CODIGO_ACCIONES. */
	private static final String CODIGO_ACCIONES = "ACC";

	/** The Constant TITULOS_PRIVADOS. */
	private static final String TITULOS_PRIVADOS = "Títulos Privados";

	/** The Constant CODIGO_TITULOS_PRIVADOS. */
	private static final String CODIGO_TITULOS_PRIVADOS = "PRV";

	/** The Constant TITULOS_PUBLICOS. */
	private static final String TITULOS_PUBLICOS = "Títulos Públicos";

	/** The Constant CODIGO_TITULOS_PUBLICOS. */
	private static final String CODIGO_TITULOS_PUBLICOS = "PUB";

	/** The Constant OBTENIENDO_LEGALES. */
	private static final String OBTENIENDO_LEGALES = "Obteniendo Legales";

	/** The Constant COMPRA_ORDENES_SIMULAR. */
	private static final String COMPRA_ORDENES_SIMULAR = "S";

	/** The Constant COMPRA_ORDENES_FEEDBACK. */
	private static final String COMPRA_ORDENES_FEEDBACK = "D";

	/** The Constant CONTRATO_ACEPTADO. */
	private static final String CONTRATO_ACEPTADO = "1";

	/** The Constant OK. */

	private static final String OK = "OK";

	private static final String TIPO_PLIEGO_CANJE = "CANJES";

	/** The licitaciones bo. */
	@Autowired
	TitulosBO licitacionesBO;

	/** The fondo BO. */
	@Autowired
	private FondoBO fondoBO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The cuenta manager. */
	@Autowired
	private CuentaManager cuentaManager;

	/** The session parametros. */
	@Autowired
	private SesionParametros sessionParametros;

	/** The contratosBO. */
	@Autowired
	private ContratoBO contratosBO;

	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The reporte BO. */
	@Autowired
	private ReporteComprobantePDFBO reporteBO;

	/** The rendimiento BO impl. */
	@Autowired
	private RendimientoBOImpl rendimientoBOImpl;

	/** The legal BO. */
	@Autowired
	private LegalBO legalBO;

	/** The Constant BANCA_PERSONAL. */
	private static final String BANCA_PERSONAL = "BR";

	/** The Constant BANCA_PRIVADA. */
	private static final String BANCA_PRIVADA = "BP";

	/** The Constant OCURRIO_UN_ERROR_AL_CONSULTAR_LAS_CUENTAS_SALDOS. */
	private static final String OCURRIO_UN_ERROR_AL_CONSULTAR_LAS_CUENTAS_SALDOS = "Ocurrió un error al consultar las cuentas saldos.";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TitulosManagerImpl.class);

	/** The Constant TIPO_PESOS. */
	private static final String TIPO_PESOS = "$";

	/** The Constant TIPO_USD. */
	private static final String TIPO_USD = "u$s";

	/** The Constant CODIGO_LEGAL_TENENCIAS_TITULOS. */
	private static final String CODIGO_LEGAL_TENENCIAS_TITULOS = "10034";

	/** The Constant CODIGO_LEGAL_TENENCIAS_TITULOS. */
	private static final String CODIGO_LEGAL_TITULOS_NUEVOS = "10070";

	/** The Constant CODIGO_LEGAL_LICITACIONES_VIGENTES. */
	private static final String CODIGO_LEGAL_LICITACIONES_VIGENTES = "10039";

	/** The Constant OPERACIONES_DE_COMPRA. */
	private static final String OPERACIONES_DE_COMPRA = "C";

	/** The Constant BANCA_PRIVADA. */
	private static final String OPERACIONES_DE_VENTA = "V";

	/** The Constant CNV_LICITACIONES. */
	private static final String CNV_LICITACIONES = "SL";

	/** The Constant CNV_COMPRA_ORDENES. */
	private static final String CNV_COMPRA_ORDENES = "OC";

	/** The Constant CNV_COMPRA_ORDENES. */
	private static final String CNV_LICITACIONES_CANJE = "CA";

	/** The deshabilitar BP. */
	@Value("${DESHABILITAR.OPERACIONES.BANCAPRIVADA}")
	private Boolean deshabilitarBP;

	/** The Constant ESTADO_PENDIENTE. */
	private static final String ESTADO_PENDIENTE = "P";

	private static final String MENSAJE_ERROR_EXCEL = "Ocurrió un error y no se pudo realizar la descarga. Por favor, volvé a intentar";
	
	@Autowired
	private InversionWSHelper inversionWSHelper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<ConsultarOrdenesPorCuentaOutView> consultarLicitacionesRTL() {
		sessionParametros.setContador(new ContadorIntentos(2));
		Respuesta<ConsultarOrdenesOutDTO> respuestaBO = licitacionesBO.consultarLicitaciones(sesionCliente.getCliente(),
				TipoBancaEnum.BANCA_PERSONAL, Boolean.FALSE);

		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			estadisticaManager.add(EstadisticasConstants.ESTADISTICA_CONSULTAR_ORDENES_LICITACIONES,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		ConsultarOrdenesPorCuentaOutView response = createViewRTL(respuestaBO.getRespuesta().getList(),
				sesionCliente.getCliente());
		estadisticaManager.add(EstadisticasConstants.ESTADISTICA_CONSULTAR_ORDENES_LICITACIONES,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(ConsultarOrdenesPorCuentaOutView.class, response);
	}

	/**
	 * Creates the view RTL.
	 *
	 * @param list    the list
	 * @param cliente the cliente
	 * @return the consultar ordenes por cuenta out view
	 */
	private ConsultarOrdenesPorCuentaOutView createViewRTL(List<ConsultarOrdenesDTO> list, Cliente cliente) {

		List<ConsultarOrdenesPorCuentaDTO> listaResponse = new ArrayList<ConsultarOrdenesPorCuentaDTO>();

		List<Cuenta> cuentasOperativas = cliente.getCuentasRetail();

		for (Cuenta cuenta : cuentasOperativas) {
			String nroCuennta = ISBANStringUtils.formatearNumeroCuenta(cuenta.getNroCuentaProducto());
			List<ConsultarOrdenesDTO> listaCuenta = new ArrayList<ConsultarOrdenesDTO>();
			ConsultarOrdenesPorCuentaDTO response = new ConsultarOrdenesPorCuentaDTO();
			for (ConsultarOrdenesDTO consultarOrdenesDTO : list) {
				if (consultarOrdenesDTO.getCuentaTitulo().equalsIgnoreCase(nroCuennta)) {
					listaCuenta.add(consultarOrdenesDTO);
				}
			}
			response.setCuentaTitulo(nroCuennta);
			response.setIntervinientes(cuenta.getIntervinientes());
			response.setOrdenes(listaCuenta);
			listaResponse.add(response);
		}

		ConsultarOrdenesPorCuentaOutView resp = new ConsultarOrdenesPorCuentaOutView();
		resp.setCuentas(listaResponse);
		resp.setMensajeSinOrdenes(
				mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_FONDO_SIN_MOVIMIENTOS_MAYOR_30_DIAS)
						.getMensaje());
		return resp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.
	 * manager.TitulosManager#consultarOrdenesBPriv()
	 */
	@Override
	public Respuesta<ConsultarOrdenesPorCuentaOutView> consultarLicitacionesBPriv() {
		sessionParametros.setContador(new ContadorIntentos(2));
		Respuesta<ConsultarOrdenesOutDTO> respuestaBO = licitacionesBO.consultarLicitaciones(sesionCliente.getCliente(),
				TipoBancaEnum.BANCA_PRIVADA, Boolean.FALSE);

		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			estadisticaManager.add(EstadisticasConstants.ESTADISTICA_CONSULTAR_ORDENES_LICITACIONES_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		ConsultarOrdenesPorCuentaOutView response = createViewBP(respuestaBO.getRespuesta().getList(),
				sesionCliente.getCliente());
		estadisticaManager.add(EstadisticasConstants.ESTADISTICA_CONSULTAR_ORDENES_LICITACIONES_BPRIV,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		response.setMensajeSinOrdenes(
				mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_FONDO_SIN_MOVIMIENTOS_MAYOR_30_DIAS)
						.getMensaje());
		return respuestaFactory.crearRespuestaOk(ConsultarOrdenesPorCuentaOutView.class, response);
	}

	/**
	 * Creates the view BP.
	 *
	 * @param list    the list
	 * @param cliente the cliente
	 * @return the consultar ordenes por cuenta out view
	 */
	private ConsultarOrdenesPorCuentaOutView createViewBP(List<ConsultarOrdenesDTO> list, Cliente cliente) {

		List<ConsultarOrdenesPorCuentaDTO> listaResponse = new ArrayList<ConsultarOrdenesPorCuentaDTO>();

		List<CuentaBancaPrivada> cuentasOperativas = cliente.getCuentasBancaPrivada();

		for (CuentaBancaPrivada cuenta : cuentasOperativas) {
			Cuenta cuentaOperativa = cuenta.getCuentaOperativa();
			String nroCuenta = ISBANStringUtils.eliminarCeros(cuentaOperativa.getNroSucursal()) + "-"
					+ ISBANStringUtils.formatearNumeroCuenta(cuentaOperativa.getNroCuentaProducto());
			List<ConsultarOrdenesDTO> listaCuenta = new ArrayList<ConsultarOrdenesDTO>();
			ConsultarOrdenesPorCuentaDTO response = new ConsultarOrdenesPorCuentaDTO();
			for (ConsultarOrdenesDTO consultarOrdenesDTO : list) {
				if (consultarOrdenesDTO.getCuentaDebito().equalsIgnoreCase(nroCuenta)) {
					listaCuenta.add(consultarOrdenesDTO);
				}
			}
			response.setIntervinientes(cuenta.getCuentaTitulo().getIntervinientes());
			response.setCuentaTitulo(nroCuenta);
			Collections.sort(listaCuenta);
			response.setOrdenes(listaCuenta);
			listaResponse.add(response);
		}

		ConsultarOrdenesPorCuentaOutView resp = new ConsultarOrdenesPorCuentaOutView();
		resp.setCuentas(listaResponse);
		return resp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.
	 * manager.TitulosManager#licitacionesVigentes()
	 */
	@Override
	public Respuesta<LicitacionesVigentesOutView> licitacionesVigentes() {

		LOGGER.info(OBTENIENDO_LEGALES);
		Respuesta<String> respuestaLegales = legalBO.buscarLegal(CODIGO_LEGAL_LICITACIONES_VIGENTES);

		if (!EstadoRespuesta.OK.equals(respuestaLegales.getEstadoRespuesta())) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_LEGALES_LICITACIONES_VIGENTES,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		Respuesta<CuentasView> respuestaCuentaPesos = cuentaManager.getCuentasSaldoPorMoneda(TIPO_PESOS);
		Respuesta<CuentasView> respuestaCuentaDolares = cuentaManager.getCuentasSaldoPorMoneda(TIPO_USD);

		if (respuestaCuentaPesos.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)
				|| respuestaCuentaDolares.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			LOGGER.info(OCURRIO_UN_ERROR_AL_CONSULTAR_LAS_CUENTAS_SALDOS);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_GENERICO);
		}

		List<CuentasAdhesionDebitoView> cuentasDebitoPesos = respuestaCuentaPesos.getRespuesta().getCuentas();
		List<CuentasAdhesionDebitoView> cuentasDebitoDolares = respuestaCuentaDolares.getRespuesta().getCuentas();

		Respuesta<LicitacionesVigentesOutDTO> licitacionesDTO = licitacionesBO
				.licitacionesVigentes(sesionCliente.getCliente(), cuentasDebitoPesos, cuentasDebitoDolares);
		Respuesta<LicitacionesVigentesOutView> rtaLicitacionesVigentes;
		LicitacionesVigentesOutView licitacionesVigentesOutView = new LicitacionesVigentesOutView();

		rtaLicitacionesVigentes = Respuesta.copy(LicitacionesVigentesOutView.class, licitacionesDTO);

		if (licitacionesDTO.getRespuesta() != null) {
			licitacionesVigentesOutView
					.setLicitacionesVigentesList(licitacionesDTO.getRespuesta().getLicitacionesVigentesList());
		}
		licitacionesVigentesOutView.setLegales(respuestaLegales.getRespuesta());
		rtaLicitacionesVigentes.setRespuesta(licitacionesVigentesOutView);

		return rtaLicitacionesVigentes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#licitacionesVigentesBPriv(ar.com.santanderrio.obp.servicios.
	 * inversiones.titulos.licitaciones.view.NuevaLicitacionViewReq)
	 */
	@Override
	public Respuesta<LicitacionesVIgentesBprivOutView> licitacionesVigentesBPriv(NuevaLicitacionViewReq request) {

		LOGGER.info(OBTENIENDO_LEGALES);
		Respuesta<String> respuestaLegales = legalBO.buscarLegal(CODIGO_LEGAL_LICITACIONES_VIGENTES);

		if (!EstadoRespuesta.OK.equals(respuestaLegales.getEstadoRespuesta())) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_LEGALES_LICITACIONES_VIGENTES,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		Respuesta<LicitacionesVIgentesBprivOutView> respuestaBO = licitacionesBO
				.licitacionesVigentesBPriv(sesionCliente.getCliente(), request);
		if (EstadoRespuesta.ERROR.equals(respuestaBO.getEstadoRespuesta())) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_LEGALES_LICITACIONES_VIGENTES,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		if (EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta())) {
			respuestaBO.getRespuesta().setLegales(respuestaLegales.getRespuesta());
		}
		return respuestaBO;
	}

	/**
	 * Crea un objeto con las licitaciones vigentes para banca privada.
	 *
	 * @param licitacionesDTO the licitaciones DTO
	 * @param legales         the legales
	 * @return the licitaciones vigentes out view
	 */
	LicitacionesVigentesOutView dtoToViewLicitacionesBPriv(LicitacionesVigentesOutDTO licitacionesDTO, String legales) {
		LicitacionesVigentesOutView licitaciones = new LicitacionesVigentesOutView();
		licitaciones.getLicitacionesVigentesList().addAll(licitacionesDTO.getLicitacionesVigentesList());
		licitaciones.getLicitacionesVigentesList().addAll(licitacionesDTO.getLicitacionesVigentesCustodiaList());
		licitaciones.setLegales(legales);
		return licitaciones;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.
	 * manager.TitulosManager#nuevaLicitacion(ar.com.santanderrio.obp.servicios.
	 * inversiones.titulos.licitaciones.view.NuevaLicitacionViewReq)
	 */
	@Override
	public Respuesta<NuevaLicitacionViewResponse> nuevaLicitacion(NuevaLicitacionViewReq request) {
		Respuesta<NuevaLicitacionViewResponse> viewResponse = obtenerNuevaLicitacionViewResponse(request);
		if (EstadoRespuesta.OK.equals(viewResponse.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.NUEVA_LICITACION,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			estadisticaManager.add(EstadisticasConstants.NUEVA_LICITACION,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return viewResponse;
	}

	/**
	 * Obtener nueva licitacion view response.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	private Respuesta<NuevaLicitacionViewResponse> obtenerNuevaLicitacionViewResponse(NuevaLicitacionViewReq request) {
		Respuesta<CuentasView> respuestaCuentaPesos = cuentaManager.getCuentasSaldoPorMoneda(TIPO_PESOS);
		Respuesta<CuentasView> respuestaCuentaDolares = cuentaManager.getCuentasSaldoPorMoneda(TIPO_USD);

		if (respuestaCuentaPesos.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)
				|| respuestaCuentaDolares.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			LOGGER.info(OCURRIO_UN_ERROR_AL_CONSULTAR_LAS_CUENTAS_SALDOS);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_GENERICO);
		}

		List<CuentasAdhesionDebitoView> cuentasDebitoPesos = respuestaCuentaPesos.getRespuesta().getCuentas();
		List<CuentasAdhesionDebitoView> cuentasDebitoDolares = respuestaCuentaDolares.getRespuesta().getCuentas();

		sessionParametros.setContador(new ContadorIntentos(2));
		Respuesta<NuevaLicitacionDTOResponse> respuesta = licitacionesBO.nuevaLicitacion(sesionCliente.getCliente(),
				request, cuentasDebitoPesos, cuentasDebitoDolares);

		Respuesta<NuevaLicitacionViewResponse> nuevaLicitacionViewResponse = new Respuesta<NuevaLicitacionViewResponse>();

		if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
			NuevaLicitacionViewResponse listaView = new NuevaLicitacionViewResponse();
			listaView.setCuentasTitulo(respuesta.getRespuesta().getCuentasTitulo());
			nuevaLicitacionViewResponse.setRespuesta(listaView);
			nuevaLicitacionViewResponse.setEstadoRespuesta(EstadoRespuesta.OK);
		}
		if (EstadoRespuesta.WARNING.equals(respuesta.getEstadoRespuesta())) {

			nuevaLicitacionViewResponse.setItemMensajeRespuesta(respuesta.getItemsMensajeRespuesta());
			nuevaLicitacionViewResponse.setEstadoRespuesta(respuesta.getEstadoRespuesta());
			return nuevaLicitacionViewResponse;
		}

		if (EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())) {
			nuevaLicitacionViewResponse = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		return nuevaLicitacionViewResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#nuevaLicitacionBPriv(ar.com.santanderrio.obp.servicios.
	 * inversiones.titulos.licitaciones.view.NuevaLicitacionViewReq)
	 */
	@Override
	public Respuesta<NuevaLicitacionViewResponseBPriv> nuevaLicitacionBPriv(NuevaLicitacionViewReq request) {
		Respuesta<NuevaLicitacionViewResponseBPriv> nuevaLicitacionViewResponse = obtenerNuevaLicitacionViewResponseBPriv(
				request);
		if (EstadoRespuesta.OK.equals(nuevaLicitacionViewResponse.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.NUEVA_LICITACION_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			estadisticaManager.add(EstadisticasConstants.NUEVA_LICITACION_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return nuevaLicitacionViewResponse;
	}

	/**
	 * Obtener nueva licitacion view response B priv.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	private Respuesta<NuevaLicitacionViewResponseBPriv> obtenerNuevaLicitacionViewResponseBPriv(
			NuevaLicitacionViewReq request) {
		sessionParametros.setContador(new ContadorIntentos(2));
		Respuesta<NuevaLicitacionDTOResponseBPriv> respuesta = new Respuesta<NuevaLicitacionDTOResponseBPriv>();
		Respuesta<NuevaLicitacionViewResponseBPriv> nuevaLicitacionViewResponse = new Respuesta<NuevaLicitacionViewResponseBPriv>();
		try {
			respuesta = licitacionesBO.nuevaLicitacionBPriv(sesionCliente.getCliente(), request, true);
		} catch (BusinessException e1) {
			LOGGER.error("");
			nuevaLicitacionViewResponse = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
			NuevaLicitacionViewResponseBPriv listaView = new NuevaLicitacionViewResponseBPriv();
			listaView.setCuentasTitulo(respuesta.getRespuesta().getCuentasTitulo());
			nuevaLicitacionViewResponse.setRespuesta(listaView);
			nuevaLicitacionViewResponse.setEstadoRespuesta(EstadoRespuesta.OK);
		}
		if (EstadoRespuesta.WARNING.equals(respuesta.getEstadoRespuesta())) {

			nuevaLicitacionViewResponse.setItemMensajeRespuesta(respuesta.getItemsMensajeRespuesta());
			nuevaLicitacionViewResponse.setEstadoRespuesta(respuesta.getEstadoRespuesta());
			return nuevaLicitacionViewResponse;
		}

		if (EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())) {
			nuevaLicitacionViewResponse = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		return nuevaLicitacionViewResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.
	 * manager.TitulosManager#grabarEstadisticaReversa()
	 */
	@Override
	public void grabarEstadisticaReversa(ConsultaOrdenesEstadisticaRequest request) {

		String estadistica;
		if (TIPO_PLIEGO_CANJE.equalsIgnoreCase(request.getTipoPliego())) {
			estadistica = EstadisticasConstants.CONSULTAR_REVERSA_ORDENES_LICITACIONES_CANJE;
		} else {
			estadistica = EstadisticasConstants.ESTADISTICA_CONSULTAR_REVERSA_ORDENES_LICITACIONES;
		}
		estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.
	 * manager.TitulosManager#grabarEstadisticaDetalleOrden()
	 */
	@Override
	public void grabarEstadisticaDetalleOrden(ConsultaOrdenesEstadisticaRequest request) {
		String estadistica;
		if (TIPO_PLIEGO_CANJE.equalsIgnoreCase(request.getTipoPliego())) {
			estadistica = EstadisticasConstants.VER_DETALLE_CONSULTA_ORDENES_LICITACIONES_CANJE;
		} else {
			estadistica = EstadisticasConstants.ESTADISTICA_VER_DETALLE_ORDENES_LICITACIONES;
		}
		estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.
	 * manager.TitulosManager#reversarOrden(ar.com.santanderrio.obp.servicios.
	 * inversiones.titulos.licitaciones.view.ReversaRequestView)
	 */
	@Override
	public Respuesta<ReversarOrdenViewResponse> reversarOrdenLicitacion(ReversaRequestView request) {
		Respuesta<ReversarOrdenResponse> response = licitacionesBO.reversarOrdenLicitacion(request,
				sesionCliente.getCliente());

		if (EstadoRespuesta.OK.equals(response.getEstadoRespuesta())) {
			ReversarOrdenViewResponse responseView = createReversaView(request.getDescripcion(), request.isCanje());
			if (BANCA_PERSONAL.equalsIgnoreCase(request.getBanca())) {
				estadisticaManager.add(EstadisticasConstants.ESTADISTICA_REVERSA_ORDENES_LICITACIONES,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			} else {
				estadisticaManager.add(EstadisticasConstants.ESTADISTICA_REVERSA_ORDENES_LICITACIONES_BPRIV,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			}
			return respuestaFactory.crearRespuestaOk(ReversarOrdenViewResponse.class, responseView);
		} else if (EstadoRespuesta.ERROR.equals(response.getEstadoRespuesta())) {
			if (BANCA_PERSONAL.equalsIgnoreCase(request.getBanca())) {
				estadisticaManager.add(EstadisticasConstants.ESTADISTICA_REVERSA_ORDENES_LICITACIONES,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			} else {
				estadisticaManager.add(EstadisticasConstants.ESTADISTICA_REVERSA_ORDENES_LICITACIONES_BPRIV,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_REINTENTOS_AGOTADOS,
					CodigoMensajeConstantes.ERROR_REVERSA_ORDEN_LICITACION, request.getDescripcion());
		} else {
			if (BANCA_PERSONAL.equalsIgnoreCase(request.getBanca())) {
				estadisticaManager.add(EstadisticasConstants.ESTADISTICA_REVERSA_ORDENES_LICITACIONES,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			} else {
				estadisticaManager.add(EstadisticasConstants.ESTADISTICA_REVERSA_ORDENES_LICITACIONES_BPRIV,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_REINTENTAR,
					CodigoMensajeConstantes.ERROR_REVERSA_ORDEN_LICITACION, request.getDescripcion());
		}
	}

	/**
	 * Creates the reversa view.
	 *
	 * @param descripcion the descripcion
	 * @return the reversar orden view response
	 */
	private ReversarOrdenViewResponse createReversaView(String descripcion, boolean isCanje) {
		ReversarOrdenViewResponse response = new ReversarOrdenViewResponse();

		if (isCanje) {
			response.setMensaje(mensajeBO
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.FINALIZAR_REVERSA_LICITACION_CANJE_OK, descripcion)
					.getMensaje());
		} else {
			response.setMensaje(
					mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.REVERSA_ORDEN_LICITACION_OK, descripcion)
							.getMensaje());
		}
		response.setFechaReversa(new SimpleDateFormat("dd/MM/yyyy - HH:mm").format(new Date()));

		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#ReversarOrdenCompraVenta(ar.com.santanderrio.obp.servicios.
	 * titulos.operaciones.view.ReversarCompraVentaRequestView)
	 */
	@Override
	public Respuesta<ReversarCompraVentaViewResponse> ReversarOrdenCompraVenta(ReversarCompraVentaRequestView request) {
		// sessionParametros.setContador(new ContadorIntentos(2));
		Respuesta<ReversarCompraVentaViewResponse> reversarCompraVentaViewResponse = new Respuesta<ReversarCompraVentaViewResponse>();
		LOGGER.info(OBTENIENDO_LEGALES);
		Respuesta<String> respuestaLegales = legalBO
				.buscarLegal(CodigoMensajeConstantes.CODIGO_LEGAL_REVERSAR_COMPRA_VENTA);

		if (BANCA_PRIVADA.equalsIgnoreCase(request.getTipoBanca())) {
			if (!EstadoRespuesta.OK.equals(respuestaLegales.getEstadoRespuesta())) {
				if (OPERACIONES_DE_VENTA.equalsIgnoreCase(request.getTipoOperacion())) {
					estadisticaManager.add(EstadisticasConstants.REVERSAR_ORDEN_VENTA_BPRIV,
							EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
					return respuestaFactory.crearRespuestaError("",
							TipoError.ERROR_LEGALES_REVERSAR_ORDENES_COMPRA_VENTA,
							CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
				} else {
					estadisticaManager.add(EstadisticasConstants.REVERSAR_ORDEN_COMPRA_BPRIV,
							EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
					return respuestaFactory.crearRespuestaError("",
							TipoError.ERROR_LEGALES_REVERSAR_ORDENES_COMPRA_VENTA,
							CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
				}

			} else {
				if (OPERACIONES_DE_VENTA.equalsIgnoreCase(request.getTipoOperacion())) {
					estadisticaManager.add(EstadisticasConstants.REVERSAR_ORDEN_VENTA_BPRIV,
							EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
					ReversarCompraVentaViewResponse responseView = new ReversarCompraVentaViewResponse();
					responseView.setLegales(respuestaLegales.getRespuesta());
					reversarCompraVentaViewResponse.setRespuesta(responseView);
					reversarCompraVentaViewResponse.setEstadoRespuesta(EstadoRespuesta.OK);
					return reversarCompraVentaViewResponse;
				} else {
					estadisticaManager.add(EstadisticasConstants.REVERSAR_ORDEN_COMPRA_BPRIV,
							EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
					ReversarCompraVentaViewResponse responseView = new ReversarCompraVentaViewResponse();
					responseView.setLegales(respuestaLegales.getRespuesta());
					reversarCompraVentaViewResponse.setRespuesta(responseView);
					reversarCompraVentaViewResponse.setEstadoRespuesta(EstadoRespuesta.OK);
					return reversarCompraVentaViewResponse;
				}

			}
			// Banca Personal
		} else {
			if (!EstadoRespuesta.OK.equals(respuestaLegales.getEstadoRespuesta())) {
				if (OPERACIONES_DE_VENTA.equalsIgnoreCase(request.getTipoOperacion())) {
					estadisticaManager.add(EstadisticasConstants.REVERSAR_ORDEN_VENTA,
							EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
					return respuestaFactory.crearRespuestaError("",
							TipoError.ERROR_LEGALES_REVERSAR_ORDENES_COMPRA_VENTA,
							CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
				} else {
					estadisticaManager.add(EstadisticasConstants.REVERSAR_ORDEN_COMPRA,
							EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
					return respuestaFactory.crearRespuestaError("",
							TipoError.ERROR_LEGALES_REVERSAR_ORDENES_COMPRA_VENTA,
							CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
				}

			} else {
				if (OPERACIONES_DE_VENTA.equalsIgnoreCase(request.getTipoOperacion())) {
					estadisticaManager.add(EstadisticasConstants.REVERSAR_ORDEN_VENTA,
							EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
					ReversarCompraVentaViewResponse responseView = new ReversarCompraVentaViewResponse();
					responseView.setLegales(respuestaLegales.getRespuesta());
					reversarCompraVentaViewResponse.setRespuesta(responseView);
					reversarCompraVentaViewResponse.setEstadoRespuesta(EstadoRespuesta.OK);
					return reversarCompraVentaViewResponse;
				} else {
					estadisticaManager.add(EstadisticasConstants.REVERSAR_ORDEN_COMPRA,
							EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
					ReversarCompraVentaViewResponse responseView = new ReversarCompraVentaViewResponse();
					responseView.setLegales(respuestaLegales.getRespuesta());
					reversarCompraVentaViewResponse.setRespuesta(responseView);
					reversarCompraVentaViewResponse.setEstadoRespuesta(EstadoRespuesta.OK);
					return reversarCompraVentaViewResponse;
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#finalizarReversarOrden(ar.com.santanderrio.obp.servicios.
	 * inversiones.titulos.ordenes.view.FinalizarReversarOrdenViewRequest)
	 */
	@Override
	public Respuesta<FinalizarReversarOrdenView> finalizarReversarOrden(FinalizarReversarOrdenViewRequest viewRequest) {
		Respuesta<FinalizarReversarOrdenDTO> respuestaBO = licitacionesBO.finalizarReversarOrden(viewRequest,
				sesionCliente.getCliente(), TipoBancaEnum.BANCA_PERSONAL);
		FinalizarReversarOrdenView outView = createRetornoViewFinalizarReversarOrden(respuestaBO.getRespuesta());
		if (EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta())) {
			if (OPERACIONES_DE_VENTA.equalsIgnoreCase(viewRequest.getTipoOperacion())) {
				estadisticaManager.add(EstadisticasConstants.FINALIZAR_REVERSAR_ORDENES_VENTA,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				return respuestaFactory.crearRespuestaOk(FinalizarReversarOrdenView.class, outView);
			} else {
				estadisticaManager.add(EstadisticasConstants.FINALIZAR_REVERSAR_ORDENES_COMPRA,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				return respuestaFactory.crearRespuestaOk(FinalizarReversarOrdenView.class, outView);
			}
		} else {
			if (OPERACIONES_DE_VENTA.equalsIgnoreCase(viewRequest.getTipoOperacion())) {
				estadisticaManager.add(EstadisticasConstants.FINALIZAR_REVERSAR_ORDENES_VENTA,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				return respuestaFactory.crearRespuestaError(FinalizarReversarOrdenView.class,
						respuestaBO.getItemsMensajeRespuesta().get(0).getMensaje(),
						respuestaBO.getItemsMensajeRespuesta().get(0).getTag());
			} else {
				estadisticaManager.add(EstadisticasConstants.FINALIZAR_REVERSAR_ORDENES_COMPRA,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				return respuestaFactory.crearRespuestaError(FinalizarReversarOrdenView.class,
						respuestaBO.getItemsMensajeRespuesta().get(0).getMensaje(),
						respuestaBO.getItemsMensajeRespuesta().get(0).getTag());
			}
		}
	}

	/**
	 * Creates the retorno view finalizar reversar orden.
	 *
	 * @param DTO the dto
	 * @return the finalizar reversar orden view
	 */
	private FinalizarReversarOrdenView createRetornoViewFinalizarReversarOrden(FinalizarReversarOrdenDTO DTO) {
		FinalizarReversarOrdenView outView = new FinalizarReversarOrdenView();
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
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#finalizarReversarOrdenBPriv(ar.com.santanderrio.obp.servicios.
	 * inversiones.titulos.ordenes.view.FinalizarReversarOrdenViewRequest)
	 */
	@Override
	public Respuesta<FinalizarReversarOrdenView> finalizarReversarOrdenBPriv(
			FinalizarReversarOrdenViewRequest viewRequest) {
		Respuesta<FinalizarReversarOrdenDTO> respuestaBO = licitacionesBO.finalizarReversarOrden(viewRequest,
				sesionCliente.getCliente(), TipoBancaEnum.BANCA_PRIVADA);
		FinalizarReversarOrdenView outView = createRetornoViewFinalizarReversarOrden(respuestaBO.getRespuesta());
		if (EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta())) {
			if (OPERACIONES_DE_VENTA.equalsIgnoreCase(viewRequest.getTipoOperacion())) {
				estadisticaManager.add(EstadisticasConstants.FINALIZAR_REVERSAR_ORDENES_VENTA_BPRIV,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				return respuestaFactory.crearRespuestaOk(FinalizarReversarOrdenView.class, outView);
			} else {
				estadisticaManager.add(EstadisticasConstants.FINALIZAR_REVERSAR_ORDENES_COMPRA_BPRIV,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				return respuestaFactory.crearRespuestaOk(FinalizarReversarOrdenView.class, outView);
			}
		} else {
			if (OPERACIONES_DE_VENTA.equalsIgnoreCase(viewRequest.getTipoOperacion())) {
				estadisticaManager.add(EstadisticasConstants.FINALIZAR_REVERSAR_ORDENES_VENTA_BPRIV,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				return respuestaFactory.crearRespuestaError(FinalizarReversarOrdenView.class,
						respuestaBO.getItemsMensajeRespuesta().get(0).getMensaje(), "");

			} else {
				estadisticaManager.add(EstadisticasConstants.FINALIZAR_REVERSAR_ORDENES_COMPRA_BPRIV,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				return respuestaFactory.crearRespuestaError(FinalizarReversarOrdenView.class,
						respuestaBO.getItemsMensajeRespuesta().get(0).getMensaje(), "");
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.
	 * manager.TitulosManager#simularLicitacion(ar.com.santanderrio.obp.
	 * servicios.inversiones.titulos.licitaciones.view.SimularLicitacionRequest)
	 */
	@Override
	public Respuesta<SimularLicitacionViewResponse> simularLicitacion(SimularLicitacionRequest request) {
		Respuesta<SimularLicitacionViewResponse> rtaValidacion = super.validate(request,
				new SimularLicitacionViewResponse());
		if (!rtaValidacion.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		return licitacionesBO.simularLicitacion(sesionCliente.getCliente(), request, BANCA_PERSONAL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.
	 * manager.TitulosManager#configurarLicitacion(ar.com.santanderrio.obp.
	 * servicios.inversiones.titulos.licitaciones.view. ConfigurarLicitacionViewReq)
	 */
	@Override
	public Respuesta<ConfigurarLicitacionOutView> configurarLicitacion(ConfigurarLicitacionViewReq request) {
		ConfigurarLicitacionOutView configurarLicitacionOutView = new ConfigurarLicitacionOutView();
		try {
			configurarLicitacionOutView.setCuentasDebitoDolares(obtenerCuentasSaldoActualizado(TIPO_USD));
			configurarLicitacionOutView.setCuentasDebitoPesos(obtenerCuentasSaldoActualizado(TIPO_PESOS));
		} catch (BusinessException e) {
			LOGGER.info(OCURRIO_UN_ERROR_AL_CONSULTAR_LAS_CUENTAS_SALDOS);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_GENERICO);
		}

		configurarLicitacionOutView.setTramoAyuda(
				mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.LICITACION_MENSAJE_AYUDA).getMensaje());

		Respuesta<ConfigurarLicitacionOutView> respuestaCondiciones = licitacionesBO
				.establecerCondicionesAceptadas(sesionCliente.getCliente().getNup(), BANCA_PERSONAL);
		if (!EstadoRespuesta.OK.equals(respuestaCondiciones.getEstadoRespuesta())) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_GENERICO);
		}
		configurarLicitacionOutView.setCuentasCustodia(respuestaCondiciones.getRespuesta().getCuentasCustodia());
		configurarLicitacionOutView.setCondicionesGeneralesAceptadas(
				respuestaCondiciones.getRespuesta().isCondicionesGeneralesAceptadas());
		configurarLicitacionOutView
				.setComprobantesCnvDisponibles(respuestaCondiciones.getRespuesta().isComprobantesCnvDisponibles());
		configurarLicitacionOutView
				.setCuentasCustodiaTotales(respuestaCondiciones.getRespuesta().getCuentasCustodiaTotales());

		if (("S").equals(request.getRenovacion())) {
			Respuesta<ConfigurarLicitacionOutView> respuestaReinvertir = licitacionesBO
					.configurarLicitacionReinvertir(sesionCliente.getCliente(), request, BANCA_PERSONAL);

			if (!EstadoRespuesta.OK.equals(respuestaReinvertir.getEstadoRespuesta())) {
				return respuestaFactory.crearRespuestaWarning(configurarLicitacionOutView, "",
						TipoError.WARNING_SUSCRIPCION_LICITACION, CodigoMensajeConstantes.ERROR_SUSCRIPCION_LICITACION);
			}
			configurarLicitacionOutView
					.setListaTenenciaRenovable(respuestaReinvertir.getRespuesta().getListaTenenciaRenovable());
		}

		return respuestaFactory.crearRespuestaOk(ConfigurarLicitacionOutView.class, configurarLicitacionOutView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.
	 * manager.TitulosManager#obtenerTenenciasTitulosRTL(ar.com.santanderrio.obp
	 * .servicios.inversiones.titulos.licitaciones.view. EstadisticasTotalesInView)
	 */
	@Override
	public Respuesta<TenenciaTitulosOutView> obtenerTenenciasTitulosRTL(EstadisticasTotalesInView request) {

		LOGGER.info("Obteniendo Legales");
		Respuesta<String> respuestaLegales = legalBO.buscarLegal(CODIGO_LEGAL_TENENCIAS_TITULOS);
		Respuesta<TenenciaTitulosOutDTO> respBO;
		if (EstadoRespuesta.OK.equals(respuestaLegales.getEstadoRespuesta())){
			respBO = licitacionesBO.obtenerTenenciaTitulosRTL(sesionCliente.getCliente());
			String estadisticaGrilla;
			if (EstadoRespuesta.ERROR.equals(respBO.getEstadoRespuesta())) {
				estadisticaGrilla = EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR;
				estadisticaTenencia(request.getCodigoEstadistica(), estadisticaGrilla);

				LOGGER.info("Error al consultar servicio de tenencias");
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
			estadisticaGrilla = EstadisticasConstants.CODIGO_ESTADISTICAS_OK;
			estadisticaTenencia(request.getCodigoEstadistica(), estadisticaGrilla);

			TenenciaTitulosOutView response = createViewResponse(respBO.getRespuesta(), respBO.getEstadoRespuesta());
			response.setNuevaApertura(inversionWSHelper.enriFlag());
			response.setLegales(respuestaLegales.getRespuesta());
			sessionParametros.setListaTenenciasPorCuenta(respBO.getRespuesta().getList());
			response.setDescripcionEstadoTenencia(mensajeBO
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.TENENCIA_VALUADA_DESCRIPCION).getMensaje());
			if (EstadoRespuesta.WARNING.equals(respBO.getEstadoRespuesta())) {
				return respuestaFactory.crearRespuestaWarning(response, respBO.getItemsMensajeRespuesta());
			}
			return respuestaFactory.crearRespuestaOk(TenenciaTitulosOutView.class, response);
		} else {
			estadisticaManager.add(EstadisticasConstants.LEGALES_LICITACIONES_TITULOS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
	}

	/**
	 * Estadistica tenencia.
	 *
	 * @param codigoTotales the codigo totales
	 * @param codigoGrilla  the codigo grilla
	 */
	private void estadisticaTenencia(String codigoTotales, String codigoGrilla) {

		if (EstadisticasConstants.CODIGO_ESTADISTICAS_OK.equalsIgnoreCase(codigoGrilla)) {
			estadisticaManager.add(EstadisticasConstants.GRILLA_LICITACIONES_TITULOS, codigoGrilla);
			estadisticaManager.add(EstadisticasConstants.TOTALES_TENENCIA_TITULOS_VALORES, codigoTotales);
			estadisticaManager.add(EstadisticasConstants.LEGALES_LICITACIONES_TITULOS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK.equalsIgnoreCase(codigoTotales)
							? EstadisticasConstants.CODIGO_ESTADISTICAS_OK
							: EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL);
		} else {
			estadisticaManager.add(EstadisticasConstants.GRILLA_LICITACIONES_TITULOS, codigoGrilla);
			estadisticaManager.add(EstadisticasConstants.TOTALES_TENENCIA_TITULOS_VALORES, codigoTotales);
			estadisticaManager.add(EstadisticasConstants.LEGALES_LICITACIONES_TITULOS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK.equalsIgnoreCase(codigoTotales)
							? EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL
							: EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);

		}
	}

	/**
	 * Creates the view response.
	 *
	 * @param tenenciaDTO     the tenencia DTO
	 * @param estadoRespuesta the estado respuesta
	 * @return the tenencia titulos out view
	 */
	private TenenciaTitulosOutView createViewResponse(TenenciaTitulosOutDTO tenenciaDTO,
			EstadoRespuesta estadoRespuesta) {
		TenenciaTitulosOutView response = new TenenciaTitulosOutView();
		response.setListTenencias(tenenciaDTO.getList());
		if (EstadoRespuesta.OK.equals(estadoRespuesta)) {
			response.setNoHayTenencias(revisarSiNoHayTenencias(tenenciaDTO.getList()));
			response.setGraficos(tenenciaDTO.getGraficos());
		}
		response.setMensajeCuentaSinTenencias(
				mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_SIN_TENENCIA_TITULOS).getMensaje());
		response.setMensajeFiltroEspecies(
				mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_FILTRO_ESPECIES).getMensaje());
		response.setMensajeCuentaBloqueada(mensajeBO
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_CUENTA_TITULOS_BLOQUEADA).getMensaje());
		response.setMensajeWarningTotales(
				mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.TENENCIAS_WARNING_VALOR_NULL).getMensaje());
		return response;
	}

	private Boolean revisarSiNoHayTenencias(List<TenenciaTitulosCuentaDTO> listaCuentasDTO) {

		Boolean noHayTenencias = Boolean.FALSE;

		for (TenenciaTitulosCuentaDTO cuentaDTO : listaCuentasDTO) {
			if (cuentaDTO.getListaDolares().isEmpty() && cuentaDTO.getListaPesos().isEmpty()) {
				noHayTenencias = Boolean.TRUE;
			} else {
				noHayTenencias = Boolean.FALSE;
			}
		}

		return noHayTenencias;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.
	 * manager.TitulosManager#finalizarLicitacion(ar.com.santanderrio.obp.
	 * servicios.inversiones.titulos.licitaciones.view. FinalizarLicitacionRequest)
	 */
	@Override
	public Respuesta<FinalizarLicitacionViewResp> finalizarLicitacion(FinalizarLicitacionRequest request) {

		Respuesta<SimularLicitacionViewResponse> rtaValidacion = super.validate(request,
				new SimularLicitacionViewResponse());
		if (!rtaValidacion.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		sessionParametros.setIsLicitacionBPriv(false);
		Respuesta<FinalizarLicitacionViewResp> respuesta = licitacionesBO.finalizarLicitacion(request,
				sesionCliente.getCliente(), BANCA_PERSONAL);
		if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.FINALIZAR_SUSCRIPCION_LICITACION,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			estadisticaManager.add(EstadisticasConstants.FINALIZAR_SUSCRIPCION_LICITACION,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.
	 * manager.TitulosManager#finalizarLicitacion(ar.com.santanderrio.obp.
	 * servicios.inversiones.titulos.licitaciones.view. FinalizarLicitacionRequest)
	 */
	@Override
	public Respuesta<FinalizarLicitacionViewResp> finalizarLicitacionBPriv(FinalizarLicitacionRequest request) {

		Respuesta<SimularLicitacionViewResponse> rtaValidacion = super.validate(request,
				new SimularLicitacionViewResponse());
		if (!rtaValidacion.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		Respuesta<FinalizarLicitacionViewResp> respuesta = licitacionesBO.finalizarLicitacion(request,
				sesionCliente.getCliente(), BANCA_PRIVADA);
		sessionParametros.setIsLicitacionBPriv(true);
		if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.FINALIZAR_SUSCRIPCION_LICITACION_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			estadisticaManager.add(EstadisticasConstants.FINALIZAR_SUSCRIPCION_LICITACION_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.
	 * manager.TitulosManager#verComprobanteLicitacion()
	 */
	@Override
	public Respuesta<ComprobanteLicitacionOutView> verComprobanteLicitacion() {

		ComprobanteLicitacionOutView comprobanteResponse = new ComprobanteLicitacionOutView();
		comprobanteResponse.setFechaHora(ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date()));
		if (sessionParametros.getIsLicitacionBPriv()) {
			estadisticaManager.add(EstadisticasConstants.VER_COMPROBANTE_LICITACION_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			estadisticaManager.add(EstadisticasConstants.VER_COMPROBANTE_LICITACION,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}
		return respuestaFactory.crearRespuestaOk(ComprobanteLicitacionOutView.class, comprobanteResponse);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.
	 * manager.TitulosManager#verComprobanteLicitacion()
	 */
	@Override
	public Respuesta<ComprobanteLicitacionOutView> verComprobanteLicitacionBPriv() {

		ComprobanteLicitacionOutView comprobanteResponse = new ComprobanteLicitacionOutView();
		comprobanteResponse.setFechaHora(ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date()));
		estadisticaManager.add(EstadisticasConstants.VER_COMPROBANTE_LICITACION_BPRIV,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(ComprobanteLicitacionOutView.class, comprobanteResponse);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.
	 * manager.TitulosManager#grabarEstadisticaComprobanteReversa()
	 */
	@Override
	public void grabarEstadisticaComprobanteReversa(ConsultaOrdenesEstadisticaRequest request) {
		String estadistica;
		if (TIPO_PLIEGO_CANJE.equalsIgnoreCase(request.getTipoPliego())) {
			estadistica = EstadisticasConstants.VER_COMPROBANTE_FINALIZAR_REVERSA_CANJE;
		} else {
			estadistica = EstadisticasConstants.ESTADISTICA_REVERSA_COMPROBANTE;
		}
		estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.
	 * manager.TitulosManager#descargarComprobanteSuscripcionLicitacionPDF(ar.
	 * com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.
	 * ComprobanteSuscripcionLicitacion)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteSuscripcionLicitacionPDF(
			ComprobanteSuscripcionLicitacion viewRequest) {
		Respuesta<Reporte> reporte;
		Respuesta<ReporteView> respuestaView;
		// validarHashSuscripcionComprobante(viewRequest);
		reporte = reporteBO.obtenerComprobantePDF(viewRequest);
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
			grabarEstadisticaSuscripcionComprobante(viewRequest.getBanca(),
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			respuestaView = respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
			grabarEstadisticaSuscripcionComprobante(viewRequest.getBanca(),
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaView;
	}

	/**
	 * Grabar estadistica suscripcion comprobante.
	 *
	 * @param banca  the banca
	 * @param estado the estado
	 */
	private void grabarEstadisticaSuscripcionComprobante(String banca, String estado) {
		if ("BP".equals(banca)) {
			estadisticaManager.add(EstadisticasConstants.COMPROBANTE_SUSCRIPCION_LICITACION_BPRIV, estado);
		} else {
			estadisticaManager.add(EstadisticasConstants.COMPROBANTE_SUSCRIPCION_LICITACION, estado);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#consultarOperacionesText(ar.com.santanderrio.obp.servicios.
	 * inversiones.titulos.licitaciones.entity.ConsultaOperaciones)
	 */
	@Override
	public Respuesta<ConsultaOperacionesView> consultarOperacionesText(ConsultaOperaciones viewRequest) {
		Respuesta<ConsultaOperacionesDTO> respuestaBO = licitacionesBO.consultarOperacionesText(viewRequest);
		ConsultaOperacionesView outView = createRetornoViewConsultaOperaciones(respuestaBO.getRespuesta());

		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			estadisticaManager.add(EstadisticasConstants.ESTADISTICA_CONSULTAR_OPERACIONES_TITULOS_COMPRA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			estadisticaManager.add(EstadisticasConstants.ESTADISTICA_CONSULTAR_OPERACIONES_TITULOS_VENTA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
			return respuestaFactory.crearRespuestaWarning(ConsultaOperacionesView.class, outView,
					respuestaBO.getItemsMensajeRespuesta());
		}
		return respuestaFactory.crearRespuestaOk(ConsultaOperacionesView.class, outView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#consultarOperacionesLicitacion(ar.com.santanderrio.obp.
	 * servicios.inversiones.comun.TipoBancaEnum)
	 */
	@Override
	public Respuesta<ConsultaOperacionesView> consultarOperacionesLicitacion(TipoBancaEnum tipoBancaEnum) {

		Cliente cliente = sesionCliente.getCliente();
		Respuesta<ConsultarOrdenesOutDTO> respuestaConsultarOrdenesOutDTO = licitacionesBO
				.consultarLicitaciones(cliente, tipoBancaEnum, Boolean.TRUE);

		String codigoEstadistica = null;
		if (TipoBancaEnum.BANCA_PERSONAL.equals(tipoBancaEnum)) {
			codigoEstadistica = EstadisticasConstants.TITULOS_CONSULTAR_LICITACIONES;
		} else {
			codigoEstadistica = EstadisticasConstants.TITULOS_CONSULTAR_LICITACIONES_PRIVADA;
		}
		if (!EstadoRespuesta.OK.equals(respuestaConsultarOrdenesOutDTO.getEstadoRespuesta())) {
			estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		ConsultarOrdenesOutDTO consultarOrdenesOutDTO = respuestaConsultarOrdenesOutDTO.getRespuesta();
		ConsultaOperacionesView consultaOperacionesView = mapearConsultaOperacionesDTOView(consultarOrdenesOutDTO,
				tipoBancaEnum, cliente);

		estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(consultaOperacionesView);
	}

	/**
	 * Mapear consulta operaciones DTO view.
	 *
	 * @param consultarOrdenesOutDTO the consultar ordenes out DTO
	 * @param tipoBancaEnum          the tipo banca enum
	 * @param cliente                the cliente
	 * @return the consulta operaciones view
	 */
	private ConsultaOperacionesView mapearConsultaOperacionesDTOView(ConsultarOrdenesOutDTO consultarOrdenesOutDTO,
			TipoBancaEnum tipoBancaEnum, Cliente cliente) {

		ConsultaOperacionesView consultaOperacionesView = new ConsultaOperacionesView();
		List<OperacionesPorCuenta> cuentasTituloConOperaciones = new ArrayList<OperacionesPorCuenta>();
		List<ConsultarOrdenesDTO> listConsultarOrdenesDTO = consultarOrdenesOutDTO.getList();
		Map<String, List<OperacionDTO>> mapaOperacionPorCuenta = new HashMap<String, List<OperacionDTO>>();
		String cuentaAMostrar = null;
		List<Cuenta> cuentasTitulo = cliente.getCuentasRetail();

		if (CollectionUtils.isNotEmpty(listConsultarOrdenesDTO)) {
			for (ConsultarOrdenesDTO consultarOrdenesDTO : listConsultarOrdenesDTO) {
				OperacionDTO operacionDTO = mapearOperacionDTOConsultarOrdenesDTO(consultarOrdenesDTO);

				if (TipoBancaEnum.BANCA_PERSONAL.equals(tipoBancaEnum)) {
					cuentaAMostrar = consultarOrdenesDTO.getCuentaTitulo();
				} else {
					cuentaAMostrar = consultarOrdenesDTO.getSucursal() + "-" + consultarOrdenesDTO.getCuentaOperativa();
				}
				List<OperacionDTO> operacionPorCuenta = mapaOperacionPorCuenta.get(cuentaAMostrar);
				if (CollectionUtils.isNotEmpty(operacionPorCuenta)) {
					operacionPorCuenta.add(operacionDTO);
				} else {
					List<OperacionDTO> listOperacionDTO = new ArrayList<OperacionDTO>();
					listOperacionDTO.add(operacionDTO);
					mapaOperacionPorCuenta.put(cuentaAMostrar, listOperacionDTO);
				}
			}
		}

		for (Cuenta cuenta : cuentasTitulo) {
			OperacionesPorCuenta operacionesPorCuenta = new OperacionesPorCuenta();
			String nroCuenta = ISBANStringUtils.formatearNumeroCuenta(cuenta.getNroCuentaProducto());
			operacionesPorCuenta.setNroCuenta(nroCuenta);
			operacionesPorCuenta.setOperaciones(
					mapaOperacionPorCuenta.get(nroCuenta) != null ? mapaOperacionPorCuenta.get(nroCuenta)
							: new ArrayList<OperacionDTO>());
			CuentaTituloView c = obtenerCuentaTitulo(cliente, nroCuenta);
			if (c != null) {
				operacionesPorCuenta.setIntervinientes(c.getIntervinientes());
			}
			cuentasTituloConOperaciones.add(operacionesPorCuenta);
		}

		consultaOperacionesView.setCuentasTitulo(cuentasTituloConOperaciones);

		return consultaOperacionesView;
	}

	/**
	 * Obtener cuenta titulo.
	 *
	 * @param cliente           the cliente
	 * @param nroCuentaProducto the nro cuenta producto
	 * @return the cuenta titulo view
	 */
	private CuentaTituloView obtenerCuentaTitulo(Cliente cliente, String nroCuentaProducto) {
		if (nroCuentaProducto == null) {
			return null;
		}
		for (CuentaTituloView cuenta : cliente.getCuentasTituloRTL()) {
			if (ISBANStringUtils.formatearNumeroCuenta(cuenta.getNroCuenta()).contains(nroCuentaProducto)) {
				return cuenta;
			}
		}
		return null;
	}

	/**
	 * Mapear operacion DTO consultar ordenes DTO.
	 *
	 * @param consultarOrdenesDTO the consultar ordenes DTO
	 * @return the operacion DTO
	 */
	private OperacionDTO mapearOperacionDTOConsultarOrdenesDTO(ConsultarOrdenesDTO consultarOrdenesDTO) {
		OperacionDTO operacionDTO = new OperacionDTO();
		operacionDTO.setEspecie(consultarOrdenesDTO.getDescripcion());
		operacionDTO.setTipoEspecie(obtenerTipoEspecie(consultarOrdenesDTO.getCodInstrumento()));
		operacionDTO.setFechaOrden(consultarOrdenesDTO.getFecha());
		operacionDTO.setOrdenId(consultarOrdenesDTO.getNumeroOrden());
		operacionDTO.setEstado(consultarOrdenesDTO.getEstado());
		operacionDTO.setCodMoneda(consultarOrdenesDTO.getMoneda());
		operacionDTO.setCantidad(consultarOrdenesDTO.getCantidadAdjudicada());
		String precio = obtenerPrecio(consultarOrdenesDTO.getPrecioAdjudicada());
		operacionDTO.setPrecio(precio);
		if (StringUtils.isNotBlank(precio)) {
			operacionDTO.setSigno(obtenerSigno(consultarOrdenesDTO.getMoneda()));
		}
		operacionDTO.setTipoOperacion(TIPO_OPERACION_LICITACIONES);
		operacionDTO.setMonedaEspecie(consultarOrdenesDTO.getMonedaEspecie());
		operacionDTO.setCantidadNominales(consultarOrdenesDTO.getCantidadNominal());
		operacionDTO.setTramo(consultarOrdenesDTO.getTramo());
		operacionDTO.setPrecioLabel(consultarOrdenesDTO.getTipoPrecioLabel() + " límite");
		operacionDTO.setPrecioLimite(consultarOrdenesDTO.getPrecio());
		operacionDTO.setImporte(consultarOrdenesDTO.getImporteDebitar());
		operacionDTO.setCuentaTitulos(consultarOrdenesDTO.getCuentaTitulo());
		operacionDTO.setCuentaDebito(consultarOrdenesDTO.getCuentaDebito());
		operacionDTO.setTipoCuentaOperativa(consultarOrdenesDTO.getTipoCuentaDebito());
		operacionDTO.setPrecioAdjudicadoLabel(consultarOrdenesDTO.getTipoPrecioAdjudicadaLabel());
		operacionDTO.setFechaDebito(consultarOrdenesDTO.getFechaDebProv());
		operacionDTO.setFechaHoraCierre(consultarOrdenesDTO.getFechaCierre());
		operacionDTO.setFechaAdjudicacion(consultarOrdenesDTO.getFechaAdjudicacion());
		operacionDTO.setFechaLiquidacion(consultarOrdenesDTO.getFechaLiquidacion());
		operacionDTO.setComisiones(obtenerImporte(consultarOrdenesDTO.getComision()));
		operacionDTO.setImpuestos(obtenerImporte(consultarOrdenesDTO.getImpuesto()));

		return operacionDTO;
	}

	/**
	 * Obtener importe.
	 *
	 * @param importe the importe
	 * @return the string
	 */
	private String obtenerImporte(Double importe) {
		if (importe == null || importe.equals(0D)) {
			return null;
		}
		DecimalFormat df = new DecimalFormat("##,###,##0.00#####");
		df.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ITALY));
		return df.format(importe);
	}

	/**
	 * Obtener signo.
	 *
	 * @param moneda the moneda
	 * @return the string
	 */
	private String obtenerSigno(String moneda) {
		if (StringUtils.isNotBlank(moneda)) {
			return TipoMonedaInversionEnum.fromCodigo2String(moneda).getSimbolo();
		}
		return null;
	}

	/**
	 * Obtener precio.
	 *
	 * @param precioAdjudicada the precio adjudicada
	 * @return the string
	 */
	private String obtenerPrecio(String precioAdjudicada) {
		if (StringUtils.isNotBlank(precioAdjudicada)) {
			String precio = precioAdjudicada.replaceAll("\\$", "");
			return precio.trim();
		}
		return null;
	}

	/**
	 * Obtener tipo especie.
	 *
	 * @param codInstrumento the cod instrumento
	 * @return the string
	 */
	private String obtenerTipoEspecie(String codInstrumento) {

		if (CODIGO_TITULOS_PUBLICOS.equals(codInstrumento)) {
			return TITULOS_PUBLICOS;
		}
		if (CODIGO_TITULOS_PRIVADOS.equals(codInstrumento)) {
			return TITULOS_PRIVADOS;
		}
		if (CODIGO_ACCIONES.equals(codInstrumento)) {
			return ACCIONES;
		}
		if (CODIGO_CEDEARS.equals(codInstrumento)) {
			return CEDEARS;
		}
		return null;
	}

	/**
	 * Creates the retorno view consulta operaciones.
	 *
	 * @param DTO the dto
	 * @return the consulta operaciones view
	 */
	private ConsultaOperacionesView createRetornoViewConsultaOperaciones(ConsultaOperacionesDTO DTO) {
		ConsultaOperacionesView outView = new ConsultaOperacionesView();
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
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#condicionesGeneralesCuentaCustodia(ar.com.santanderrio.obp.
	 * servicios.inversiones.titulos.licitaciones.view.
	 * CondicionesGeneralesViewRequest)
	 */
	@Override
	public Respuesta<CondicionesGeneralesCuentaCustodiaDTO> condicionesGeneralesCuentaCustodia(
			CondicionesGeneralesViewRequest viewRequest) {

		Respuesta<CondicionesGeneralesViewRequest> rtaValidacion = super.validate(viewRequest,
				new CondicionesGeneralesViewRequest());
		if (!rtaValidacion.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		Respuesta<CondicionesGeneralesCuentaCustodiaDTO> respuestaBO = licitacionesBO
				.condicionesGeneralesCuentaCustodia(sesionCliente.getCliente(), viewRequest.isCondicionesAceptadas());
		return respuestaBO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#firmarCuentas(ar.com.santanderrio.obp.servicios.inversiones.
	 * titulos.licitaciones.view.FirmarCuentasViewRequest)
	 */
	@Override
	public Respuesta<String> firmarCuentas(FirmarCuentasViewRequest viewRequest) {

		Respuesta<FirmarCuentasViewRequest> rtaValidacion = super.validate(viewRequest, new FirmarCuentasViewRequest());
		if (!rtaValidacion.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		Respuesta<String> respuestaBo = licitacionesBO.firmarCuentas(sesionCliente.getCliente(), viewRequest);

		if (EstadoRespuesta.OK.equals(respuestaBo.getEstadoRespuesta())) {
			if (viewRequest.isEsCanje()) {
				estadisticaManager.add(EstadisticasConstants.CONDICIONES_ACEPTADAS_CANJE,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			} else {
				estadisticaManager.add(EstadisticasConstants.FIRMA_CONDICIONES_GENERALES_CUENTA_CUSTODIA,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			}

			List<ItemMensajeRespuesta> itemMensajeRta = new ArrayList<ItemMensajeRespuesta>();
			ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
			itemMensajeRespuesta.setMensaje(mensajeBO
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.ACEPTAR_CONDICIONES_GENERALES_CUENTA_CUSTODIA_OK)
					.getMensaje());
			itemMensajeRta.add(itemMensajeRespuesta);
			respuestaBo.setItemMensajeRespuesta(itemMensajeRta);
		} else {
			if (viewRequest.isEsCanje()) {
				estadisticaManager.add(EstadisticasConstants.CONDICIONES_ACEPTADAS_CANJE,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			} else {
				estadisticaManager.add(EstadisticasConstants.FIRMA_CONDICIONES_GENERALES_CUENTA_CUSTODIA,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);

			}
		}
		return respuestaBo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#firmarCuentasBPriv(ar.com.santanderrio.obp.servicios.
	 * inversiones.titulos.licitaciones.view.FirmarCuentasViewRequest)
	 */
	@Override
	public Respuesta<String> firmarCuentasBPriv(FirmarCuentasViewRequest viewRequest) {

		Respuesta<FirmarCuentasViewRequest> rtaValidacion = super.validate(viewRequest, new FirmarCuentasViewRequest());
		if (!rtaValidacion.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		Respuesta<String> respuestaBo = licitacionesBO.firmarCuentas(sesionCliente.getCliente(), viewRequest);

		if (EstadoRespuesta.OK.equals(respuestaBo.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.TERMINOS_Y_CONDIONES_ALTA_CUENTA_TITULO,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			estadisticaManager.add(EstadisticasConstants.TERMINOS_Y_CONDIONES_ALTA_CUENTA_TITULO,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		
		if (EstadoRespuesta.OK.equals(respuestaBo.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.FIRMA_CONDICIONES_GENERALES_CUENTA_CUSTODIA_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			estadisticaManager.add(EstadisticasConstants.FIRMA_CONDICIONES_GENERALES_CUENTA_CUSTODIA_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaBo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#descargarComprobanteReversaLicitacionPDF(ar.com.santanderrio.
	 * obp.servicios.inversiones.titulos.licitaciones.view.
	 * ComprobanteReversaLicitacion)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteReversaLicitacionPDF(ComprobanteReversaLicitacion viewRequest) {
		Respuesta<Reporte> reporte;
		Respuesta<ReporteView> respuestaView;
		// validarHashSuscripcionComprobante(viewRequest);
		reporte = reporteBO.obtenerComprobantePDF(viewRequest);
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
			estadisticaManager.add(EstadisticasConstants.COMPROBANTE_REVERSA_LICITACION,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			respuestaView = respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
			estadisticaManager.add(EstadisticasConstants.COMPROBANTE_REVERSA_LICITACION,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#descargarComprobanteReversaLicitacionPDFBPriv(ar.com.
	 * santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.
	 * ComprobanteReversaLicitacion)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteReversaLicitacionPDFBPriv(
			ComprobanteReversaLicitacion viewRequest) {
		Respuesta<Reporte> reporte;
		Respuesta<ReporteView> respuestaView;
		// validarHashSuscripcionComprobante(viewRequest);
		reporte = reporteBO.obtenerComprobantePDF(viewRequest);
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
			estadisticaManager.add(EstadisticasConstants.COMPROBANTE_REVERSA_LICITACION_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			respuestaView = respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
			estadisticaManager.add(EstadisticasConstants.COMPROBANTE_REVERSA_LICITACION_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#grabarEstadisticaDetalleOperaciones(ar.com.santanderrio.obp.
	 * servicios.inversiones.titulos.licitaciones.entity.ListarOperaciones)
	 */
	@Override
	public void grabarEstadisticaDetalleOperaciones(ListarOperaciones viewRequest) {
		if (OPERACIONES_DE_COMPRA.equals(viewRequest.getTipo())) {
			estadisticaManager.add(EstadisticasConstants.ESTADISTICA_DETALLE_CONSULTAR_OPERACIONES_COMPRA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}

		if (OPERACIONES_DE_VENTA.equals(viewRequest.getTipo())) {
			estadisticaManager.add(EstadisticasConstants.ESTADISTICA_DETALLE_CONSULTAR_OPERACIONES_VENTA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#grabarEstadisticaListarOperaciones(ar.com.santanderrio.obp.
	 * servicios.inversiones.titulos.licitaciones.entity.ListarOperaciones)
	 */
	@Override
	public void grabarEstadisticaListarOperaciones(ListarOperaciones viewRequest) {

		if (OPERACIONES_DE_COMPRA.equals(viewRequest.getTipo())) {
			estadisticaManager.add(EstadisticasConstants.ESTADISTICA_CONSULTAR_OPERACIONES_TITULOS_COMPRA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}

		if (OPERACIONES_DE_VENTA.equals(viewRequest.getTipo())) {
			estadisticaManager.add(EstadisticasConstants.ESTADISTICA_CONSULTAR_OPERACIONES_TITULOS_VENTA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#descargarComprobanteAceptacionCNV(ar.com.santanderrio.obp.
	 * servicios.inversiones.titulos.licitaciones.view.
	 * ComprobanteAceptacionCNVRequest)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteAceptacionCNV(ComprobanteAceptacionCNVRequest request) {
		Respuesta<Reporte> reporte;
		Respuesta<ReporteView> respuestaView;
		// validarHashSuscripcionComprobante(viewRequest);
		ComprobanteAceptacionCNV comprobante = new ComprobanteAceptacionCNV();
		comprobante.setComprobante(sessionParametros.getComprobanteCNVLicitaciones());
		reporte = reporteBO.obtenerComprobantePDF(comprobante);
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
			estadisticasCnv(request.getTipoBanca(), request.getOperacion(), "OK");
		} else {
			respuestaView = respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
			estadisticasCnv(request.getTipoBanca(), request.getOperacion(), "ERROR");
		}
		return respuestaView;
	}

	/**
	 * Estadisticas cnv.
	 *
	 * @param tipoBanca the tipo banca
	 * @param operacion the operacion
	 * @param resultado the resultado
	 */
	private void estadisticasCnv(String tipoBanca, String operacion, String resultado) {

		if (CNV_LICITACIONES.equalsIgnoreCase(operacion)) {

			estadisticasCnvLicitaciones(tipoBanca, resultado);

		} else if (CNV_COMPRA_ORDENES.equalsIgnoreCase(operacion)) {

			estadisticasCnvCompraOrdenes(tipoBanca, resultado);
		} else if (CNV_LICITACIONES_CANJE.equalsIgnoreCase(operacion)) {
			estadisticasCnvLicitacionesCanje(tipoBanca, resultado);
		}

	}

	private void estadisticasCnvLicitacionesCanje(String tipoBanca, String resultado) {
		String estadistica;
		if ("OK".equalsIgnoreCase(resultado)) {
			estadistica = EstadisticasConstants.CODIGO_ESTADISTICAS_OK;
		} else {
			estadistica = EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR;
		}

		estadisticaManager.add(EstadisticasConstants.CNV_LICITACIONES_CANJE, estadistica);
		// Falta agregar logica para cuando descargue banca privada
	}

	/**
	 * Estadisticas cnv licitaciones.
	 *
	 * @param tipoBanca the tipo banca
	 * @param resultado the resultado
	 */
	private void estadisticasCnvLicitaciones(String tipoBanca, String resultado) {

		if (BANCA_PERSONAL.equalsIgnoreCase(tipoBanca)) {

			if ("OK".equalsIgnoreCase(resultado)) {

				estadisticaManager.add(EstadisticasConstants.COMPROBANTE_ACEPTACION_CNV_BR_SUSCRIPCION_LICITACIONES,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			} else {
				estadisticaManager.add(EstadisticasConstants.COMPROBANTE_ACEPTACION_CNV_BR_SUSCRIPCION_LICITACIONES,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}

		} else {

			if ("OK".equalsIgnoreCase(resultado)) {
				estadisticaManager.add(EstadisticasConstants.COMPROBANTE_ACEPTACION_CNV_BP_SUSCRIPCION_LICITACIONES,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			} else {
				estadisticaManager.add(EstadisticasConstants.COMPROBANTE_ACEPTACION_CNV_BP_SUSCRIPCION_LICITACIONES,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}
		}

	}

	/**
	 * Estadisticas cnv compra ordenes.
	 *
	 * @param tipoBanca the tipo banca
	 * @param resultado the resultado
	 */
	private void estadisticasCnvCompraOrdenes(String tipoBanca, String resultado) {

		if (BANCA_PERSONAL.equalsIgnoreCase(tipoBanca)) {

			if ("OK".equalsIgnoreCase(resultado)) {

				estadisticaManager.add(EstadisticasConstants.COMPROBANTE_ACEPTACION_CNV_BR_ORDEN_COMPRA,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			} else {
				estadisticaManager.add(EstadisticasConstants.COMPROBANTE_ACEPTACION_CNV_BR_ORDEN_COMPRA,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}

		} else {

			if ("OK".equalsIgnoreCase(resultado)) {
				estadisticaManager.add(EstadisticasConstants.COMPROBANTE_ACEPTACION_CNV_BP_ORDEN_COMPRA,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			} else {
				estadisticaManager.add(EstadisticasConstants.COMPROBANTE_ACEPTACION_CNV_BP_ORDEN_COMPRA,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#listarComprobantesCNV(ar.com.santanderrio.obp.servicios.
	 * inversiones.titulos.licitaciones.view.ListarComprobantesViewRequest)
	 */
	@Override
	public Respuesta<ListarComprobantesViewResponse> listarComprobantesCNV(ListarComprobantesViewRequest viewRequest) {
		Respuesta<ListarComprobantesViewResponse> respuestaView = new Respuesta<ListarComprobantesViewResponse>();
		respuestaView = licitacionesBO.listarComprobantesCNV(viewRequest);
		if (EstadoRespuesta.OK.equals(respuestaView.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.ACEPTACION_COMPROBANTES_CNV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else if (EstadoRespuesta.WARNING.equals(respuestaView.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.ACEPTACION_COMPROBANTES_CNV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_WARNING);
		} else if (EstadoRespuesta.ERROR.equals(respuestaView.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.ACEPTACION_COMPROBANTES_CNV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		
		return respuestaView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#obtenerComprobanteCNV(ar.com.santanderrio.obp.servicios.
	 * inversiones.titulos.licitaciones.view.ObtenerComprobanteViewRequest)
	 */
	@Override
	public Respuesta<ComprobanteCnvViewResponse> obtenerComprobanteCNV(ObtenerComprobanteViewRequest viewRequest) {
		Respuesta<ComprobanteCnvViewResponse> viewResponse = new Respuesta<ComprobanteCnvViewResponse>();
		viewResponse = licitacionesBO.obtenerComprobanteCNV(viewRequest.getIdComprobante());
		if (!EstadoRespuesta.OK.equals(viewResponse.getEstadoRespuesta())) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		sessionParametros.setComprobanteCNVLicitaciones(viewResponse.getRespuesta().getHtmlResponse());
		return respuestaFactory.crearRespuestaOk(ComprobanteCnvViewResponse.class, viewResponse.getRespuesta());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#consultarCondicionesGeneralesCC()
	 */
	@Override
	public Respuesta<ConfigurarLicitacionOutView> consultarCondicionesGeneralesCC() {
		Respuesta<ConfigurarLicitacionOutView> respuestaCondiciones = licitacionesBO
				.establecerCondicionesAceptadas(sesionCliente.getCliente().getNup(), BANCA_PERSONAL);
		if (!EstadoRespuesta.OK.equals(respuestaCondiciones.getEstadoRespuesta())) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_GENERICO);
		}
		ConfigurarLicitacionOutView configurarLicitacionOutView = new ConfigurarLicitacionOutView();
		configurarLicitacionOutView.setCuentasCustodia(respuestaCondiciones.getRespuesta().getCuentasCustodia());
		configurarLicitacionOutView.setCondicionesGeneralesAceptadas(
				respuestaCondiciones.getRespuesta().isCondicionesGeneralesAceptadas());

		return respuestaFactory.crearRespuestaOk(ConfigurarLicitacionOutView.class, configurarLicitacionOutView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#grabarEstadisticaDetalleOrdenBpriv()
	 */
	@Override
	public void grabarEstadisticaDetalleOrdenBpriv(ConsultaOrdenesEstadisticaRequest request) {

		String estadistica;
		if (TIPO_PLIEGO_CANJE.equalsIgnoreCase(request.getTipoPliego())) {
			estadistica = EstadisticasConstants.VER_DETALLE_CONSULTA_ORDENES_CANJE_BPRIV;
		} else {
			estadistica = EstadisticasConstants.ESTADISTICA_VER_DETALLE_ORDENES_LICITACIONES_BPriv;
		}
		estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#simularLicitacionBPriv(ar.com.santanderrio.obp.servicios.
	 * inversiones.titulos.licitaciones.view.SimularLicitacionRequest)
	 */
	@Override
	public Respuesta<SimularLicitacionViewResponse> simularLicitacionBPriv(SimularLicitacionRequest request) {
		Respuesta<SimularLicitacionViewResponse> rtaValidacion = super.validate(request,
				new SimularLicitacionViewResponse());
		if (!rtaValidacion.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		return licitacionesBO.simularLicitacion(sesionCliente.getCliente(), request, BANCA_PRIVADA);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#grabarEstadisticaReversaBpriv()
	 */
	@Override
	public void grabarEstadisticaReversaBpriv(ConsultaOrdenesEstadisticaRequest request) {
		
		String estadistica;
		if (TIPO_PLIEGO_CANJE.equalsIgnoreCase(request.getTipoPliego())) {
			estadistica = EstadisticasConstants.CONSULTAR_REVERSA_ORDENES_LICITACIONES_CANJE_BPRIV;
		} else {
			estadistica = EstadisticasConstants.ESTADISTICA_CONSULTAR_REVERSA_ORDENES_LICITACIONES_BPRIV;
		}
		estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#grabarEstadisticaComprobanteReversaBpriv()
	 */
	@Override
	public void grabarEstadisticaComprobanteReversaBpriv(ConsultaOrdenesEstadisticaRequest request) {
		String estadistica;
		if (TIPO_PLIEGO_CANJE.equalsIgnoreCase(request.getTipoPliego())) {
			estadistica = EstadisticasConstants.VER_COMPROBANTE_FINALIZAR_REVERSA_CANJE_BPRIV;
		} else {
			estadistica = EstadisticasConstants.ESTADISTICA_REVERSA_COMPROBANTE;
		}
		estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#configurarLicitacionBPriv(ar.com.santanderrio.obp.servicios.
	 * inversiones.titulos.licitaciones.view.ConfigurarLicitacionViewReq)
	 */
	@Override
	public Respuesta<ConfigurarLicitacionBPrivOutView> configurarLicitacionBPriv(ConfigurarLicitacionViewReq request) {
		CuentasAdhesionDebitoView cuenta = new CuentasAdhesionDebitoView();
		ConfigurarLicitacionBPrivOutView respuesta = new ConfigurarLicitacionBPrivOutView();
		if ("A".equals(request.getTipoCuentaEspecie()) || "O".equals(request.getTipoCuentaEspecie())) {
			Respuesta<CuentasAdhesionDebitoView> rtaCuenta = fondoBO
					.obtenerSaldosCuentaOperativa(request.getCuentaTitulo(), sesionCliente.getCliente(), false);
			if (EstadoRespuesta.ERROR.equals(rtaCuenta.getEstadoRespuesta())) {
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SALDO_CUENTA_OPERATIVA,
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
			cuenta = rtaCuenta.getRespuesta();
			respuesta.setCuentaOperativa(cuenta);
		}
		if ("A".equals(request.getTipoCuentaEspecie()) || "C".equals(request.getTipoCuentaEspecie())) {
			try {
				for (String moneda : request.getMonedas()) {
					ObtenerSaldoCuentasCustodiaResponse response = licitacionesBO
							.obtenerSaldoCuentasCustodiaBPriv(sesionCliente.getCliente(), moneda);
					respuesta.getCuentasCustodia().addAll(response.getDatos());
					sesionCliente.getCliente().setCuentasCustodia(respuesta.getCuentasCustodia());
				}
			} catch (BusinessException e) {
				if (cuenta == null) {
					return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SALDO_CUENTA_OPERATIVA,
							CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
				}
				LOGGER.error("Ocurrio un error obteniendo las cuentas custodia");
			}
		}

		respuesta.setTramoAyuda(
				mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.LICITACION_MENSAJE_AYUDA).getMensaje());

		Respuesta<ConfigurarLicitacionOutView> respuestaCondiciones = licitacionesBO
				.establecerCondicionesAceptadas(sesionCliente.getCliente().getNup(), BANCA_PRIVADA);
		if (!EstadoRespuesta.OK.equals(respuestaCondiciones.getEstadoRespuesta())) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_GENERICO);
		}

		respuesta.setCuentasCustodiaFirmar(respuestaCondiciones.getRespuesta().getCuentasCustodia());
		respuesta.setCondicionesGeneralesAceptadas(
				respuestaCondiciones.getRespuesta().isCondicionesGeneralesAceptadas());
		respuesta.setComprobantesCnvDisponibles(respuestaCondiciones.getRespuesta().isComprobantesCnvDisponibles());
		respuesta.setCuentasCustodiaTotales(respuestaCondiciones.getRespuesta().getCuentasCustodiaTotales());

		if (isCuentaOperativaVacia(respuesta) && respuesta.getCuentasCustodia().isEmpty()) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SALDO_CUENTA_OPERATIVA,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		if (("S").equals(request.getRenovacion()) && !"M".equals(request.getTipoOferta())) {
			// if(request.getCuentaTitulo().equals(sesionCliente.getCliente().getCuentasCustodia())){
			// respuesta.setListaTenenciaRenovable(null);
			// }else{
			Respuesta<ConfigurarLicitacionOutView> respuestaReinvertir = licitacionesBO
					.configurarLicitacionReinvertir(sesionCliente.getCliente(), request, BANCA_PRIVADA);

			if (!EstadoRespuesta.OK.equals(respuestaReinvertir.getEstadoRespuesta())) {
				return respuestaFactory.crearRespuestaWarning(respuesta, "", TipoError.WARNING_SUSCRIPCION_LICITACION,
						CodigoMensajeConstantes.ERROR_SUSCRIPCION_LICITACION);
			}
			respuesta.setListaTenenciaRenovable(respuestaReinvertir.getRespuesta().getListaTenenciaRenovable());
			// }

		}
		return respuestaFactory.crearRespuestaOk(ConfigurarLicitacionBPrivOutView.class, respuesta);
	}

	/**
	 * Checks if is cuenta operativa vacia.
	 *
	 * @param cuenta the cuenta
	 * @return true, if is cuenta operativa vacia
	 */
	private boolean isCuentaOperativaVacia(ConfigurarLicitacionBPrivOutView cuenta) {
		if (cuenta.getCuentaOperativa() == null) {
			return false;
		}
		return "-".equals(cuenta.getCuentaOperativa().getSaldoPesos())
				&& "-".equals(cuenta.getCuentaOperativa().getSaldoDolares());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#consultarOrdenes(ar.com.santanderrio.obp.servicios.inversiones
	 * .titulos.ordenes.view.ConsultarOrdenesViewRequest)
	 */
	@Override
	public Respuesta<ConsultaOrdenesView> consultarOrdenes(ConsultarOrdenesViewRequest viewRequest) {

		sessionParametros.setContador(new ContadorIntentos(2));
		Respuesta<String> legales = legalBO.buscarLegal(CodigoMensajeConstantes.LEGALES_CONSULTA_ORDENES);

		if (legales.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			armarRespuestaErrorConsultaOrdenes();
		}

		Respuesta<ConsultaOrdenesDTO> respuestaBO = licitacionesBO.consultarOrdenes(viewRequest,
				sesionCliente.getCliente(), TipoBancaEnum.BANCA_PERSONAL);
		ConsultaOrdenesView outView = createRetornoViewConsultaOrdenes(respuestaBO.getRespuesta());

		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			return armarRespuestaErrorConsultaOrdenes();
		}

		outView.setLegales(legales.getRespuesta());
		estadisticaManager.add(EstadisticasConstants.CONSULTA_ORDENES_COMPRA_TITULOS,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(ConsultaOrdenesView.class, outView);

	}

	/**
	 * Armar respuesta error consulta ordenes.
	 *
	 * @return the respuesta
	 */
	private Respuesta<ConsultaOrdenesView> armarRespuestaErrorConsultaOrdenes() {
		estadisticaManager.add(EstadisticasConstants.CONSULTA_ORDENES_COMPRA_TITULOS,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#consultarOrdenesBPriv(ar.com.santanderrio.obp.servicios.
	 * inversiones.titulos.ordenes.view.ConsultarOrdenesViewRequest)
	 */
	@Override
	public Respuesta<ConsultaOrdenesView> consultarOrdenesBPriv(ConsultarOrdenesViewRequest viewRequest) {
		sessionParametros.setContador(new ContadorIntentos(2));
		Respuesta<ConsultaOrdenesDTO> respuestaBO = licitacionesBO.consultarOrdenes(viewRequest,
				sesionCliente.getCliente(), TipoBancaEnum.BANCA_PRIVADA);
		ConsultaOrdenesView outView = createRetornoViewConsultaOrdenes(respuestaBO.getRespuesta());
		revisarSiDeshabilitarReversaBP(outView);
		outView=filtraRepatriacionBP(outView, sesionCliente.getCliente());

		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			estadisticaManager.add(EstadisticasConstants.CONSULTA_ORDENES_COMPRA_TITULOS_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		estadisticaManager.add(EstadisticasConstants.CONSULTA_ORDENES_COMPRA_TITULOS_BPRIV,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(ConsultaOrdenesView.class, outView);

	}

	/**
	 * Revisar si deshabilitar reversa BP.
	 *
	 * @param outView the out view
	 */
	private void revisarSiDeshabilitarReversaBP(ConsultaOrdenesView outView) {

		for (OrdenesPorCuenta cuenta : outView.getCuentas()) {
			for (TipoOrdenDTO orden : cuenta.getOrdenes()) {
				if (deshabilitarBP != null && deshabilitarBP && ESTADO_PENDIENTE.equals(orden.getEstado())) {
					orden.setDeshabilitarBP(true);
				}
			}
		}
	}

	/**
	 * Creates the retorno view consulta ordenes.
	 *
	 * @param DTO the dto
	 * @return the consulta ordenes view
	 */
	private ConsultaOrdenesView createRetornoViewConsultaOrdenes(ConsultaOrdenesDTO DTO) {
		ConsultaOrdenesView outView = new ConsultaOrdenesView();
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
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#grabarEstadisticaOrdenesVenta(ar.com.santanderrio.obp.
	 * servicios.inversiones.titulos.ordenes.view.EstadisticasOrdenesVentaView)
	 */
	@Override
	public Respuesta<Void> grabarEstadisticaOrdenesVenta(EstadisticasOrdenesVentaView estadistica) {
		if (OK.equalsIgnoreCase(estadistica.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.CONSULTA_ORDENES_VENTA_TITULOS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			estadisticaManager.add(EstadisticasConstants.CONSULTA_ORDENES_VENTA_TITULOS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#grabaEstadisticaVerDetalleOrdenCompra()
	 */
	@Override
	public Respuesta<Void> grabaEstadisticaVerDetalleOrdenCompra() {
		estadisticaManager.add(EstadisticasConstants.VER_DETALLE_ORDENES_COMPRA,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#grabaEstadisticaVerDetalleOrdenVenta()
	 */
	@Override
	public Respuesta<Void> grabaEstadisticaVerDetalleOrdenVenta() {
		estadisticaManager.add(EstadisticasConstants.VER_DETALLE_ORDENES_VENTA,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#grabaEstadisticaBuscadorOrdenVenta()
	 */
	@Override
	public Respuesta<Void> grabaEstadisticaBuscadorOrdenVenta() {
		estadisticaManager.add(EstadisticasConstants.BUSCADOR_ORDENES_VENTA,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#grabaEstadisticaBuscadorOrdenCompra()
	 */
	@Override
	public Respuesta<Void> grabaEstadisticaBuscadorOrdenCompra() {
		estadisticaManager.add(EstadisticasConstants.BUSCADOR_ORDENES_COMPRA,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#grabaEstadisticaBuscadorOrdenVentaBPriv()
	 */
	@Override
	public Respuesta<Void> grabaEstadisticaBuscadorOrdenVentaBPriv() {
		estadisticaManager.add(EstadisticasConstants.BUSCADOR_ORDENES_VENTA_BPRIV,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#grabaEstadisticaBuscadorOrdenCompraBPriv()
	 */
	@Override
	public Respuesta<Void> grabaEstadisticaBuscadorOrdenCompraBPriv() {
		estadisticaManager.add(EstadisticasConstants.BUSCADOR_ORDENES_COMPRA_BPRIV,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#grabarEstadisticaOrdenesVentaBPriv(ar.com.santanderrio.obp.
	 * servicios.inversiones.titulos.ordenes.view.EstadisticasOrdenesVentaView)
	 */
	@Override
	public Respuesta<Void> grabarEstadisticaOrdenesVentaBPriv(EstadisticasOrdenesVentaView estadistica) {
		if (OK.equalsIgnoreCase(estadistica.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.CONSULTA_ORDENES_VENTA_TITULOS_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			estadisticaManager.add(EstadisticasConstants.CONSULTA_ORDENES_VENTA_TITULOS_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#grabaEstadisticaVerDetalleOrdenCompraBPriv()
	 */
	@Override
	public Respuesta<Void> grabaEstadisticaVerDetalleOrdenCompraBPriv() {
		estadisticaManager.add(EstadisticasConstants.VER_DETALLE_ORDENES_COMPRA_BPRIV,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#grabaEstadisticaVerDetalleOrdenVentaBPriv()
	 */
	@Override
	public Respuesta<Void> grabaEstadisticaVerDetalleOrdenVentaBPriv() {
		estadisticaManager.add(EstadisticasConstants.VER_DETALLE_ORDENES_VENTA_BPRIV,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#estadisticaDetalleLicitacionRTL()
	 */
	@Override
	public Respuesta<Void> estadisticaDetalleLicitacionRTL() {
		estadisticaManager.add(EstadisticasConstants.TITULOS_CONSULTAR_DETALLE_OPERACION_LICITACION_RTL,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#obtenerTenenciasTitulosBP(ar.com.santanderrio.obp.servicios.
	 * inversiones.titulos.licitaciones.view.EstadisticasTotalesInView)
	 */
	@Override
	public Respuesta<TenenciaTitulosOutView> obtenerTenenciasTitulosBP(EstadisticasTotalesInView request) {

		Respuesta<String> respuestaLegales = legalBO.buscarLegal(CODIGO_LEGAL_TENENCIAS_TITULOS);
		Respuesta<String> respuestaLegales2 = legalBO.buscarLegal(CODIGO_LEGAL_TITULOS_NUEVOS);
		String estadisticaGrilla = EstadisticasConstants.CODIGO_ESTADISTICAS_OK;
		if (EstadoRespuesta.ERROR.equals(respuestaLegales.getEstadoRespuesta())
				|| EstadoRespuesta.ERROR.equals(respuestaLegales2.getEstadoRespuesta())) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		Respuesta<TenenciaTitulosOutDTO> respBO = licitacionesBO.obtenerTenenciaTitulosBP(sesionCliente.getCliente());

		if (EstadoRespuesta.ERROR.equals(respBO.getEstadoRespuesta())) {
			estadisticaGrilla = EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR;
			grabarEstadisticasTenenciasBP(request.getCodigoEstadistica(), estadisticaGrilla);
			LOGGER.info("Error obteniendo tenencias de titulos BP");
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		grabarEstadisticasTenenciasBP(request.getCodigoEstadistica(), estadisticaGrilla);
		TenenciaTitulosOutView response = createViewResponse(respBO.getRespuesta(), respBO.getEstadoRespuesta());
		response.setLegales(respuestaLegales.getRespuesta());
		response.setLegalTitulosNuevos(respuestaLegales2.getRespuesta());
		sessionParametros.setListaTenenciasPorCuentaBP(respBO.getRespuesta().getList());
		response=filtraRepatriacionBP(response,sesionCliente.getCliente());
		response.setDescripcionEstadoTenencia(
				mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.TENENCIA_VALUADA_DESCRIPCION).getMensaje());
		if (EstadoRespuesta.WARNING.equals(respBO.getEstadoRespuesta())) {
			// respBO.getItemsMensajeRespuesta().get(0).setMensaje(mensajeBO.obtenerMensajePorCodigo("1234").get);
			return respuestaFactory.crearRespuestaWarning(response, respBO.getItemsMensajeRespuesta());
		}
		return respuestaFactory.crearRespuestaOk(TenenciaTitulosOutView.class, response);
	}

	private TenenciaTitulosOutView filtraRepatriacionBP(TenenciaTitulosOutView response, Cliente cliente) {
		
		List<Cuenta> ctasTitBPRep=cliente.getCuentasTitBPRepatriacion();
		List<TenenciaTitulosCuentaDTO> ctasDTO=new ArrayList<TenenciaTitulosCuentaDTO>(response.getListTenencias());
		
		for(Cuenta ctaTitRep : ctasTitBPRep) {
			for(TenenciaTitulosCuentaDTO ctaDto : ctasDTO) {
				if(Integer.parseInt(ctaDto.getNumeroCuentaOperativa().replaceAll("/",""))==(ctaTitRep.getCuentaOPRepatriacion())){
					response.getListTenencias().remove(ctaDto);
				}
			}
		}
		
		return response;
	}
	
	private ConsultaOrdenesView filtraRepatriacionBP(ConsultaOrdenesView response, Cliente cliente) {
		
		List<Cuenta> ctasTitBPRep=cliente.getCuentasTitBPRepatriacion();
		List<OrdenesPorCuenta> listaCtasOrd=new ArrayList<OrdenesPorCuenta>(response.getCuentas());   
		
		
		for(Cuenta ctaTitRep : ctasTitBPRep) {
			for(OrdenesPorCuenta ctaDto : listaCtasOrd) {
				
				if(Integer.parseInt(ctaDto.getCuentaTitulo().replaceAll("/", ""))==Integer.parseInt(ctaTitRep.getNroCuentaProducto())){
					response.getCuentas().remove(ctaDto);
				}
			}
		}
		
		return response;
	}

	/**
	 * Grabar estadisticas tenencias BP.
	 *
	 * @param codigoTotales the codigo totales
	 * @param codigoGrilla  the codigo grilla
	 */
	private void grabarEstadisticasTenenciasBP(String codigoTotales, String codigoGrilla) {

		if (EstadisticasConstants.CODIGO_ESTADISTICAS_OK.equalsIgnoreCase(codigoGrilla)) {
			estadisticaManager.add(EstadisticasConstants.INICIO_TENENCIAS_GRILLA, codigoGrilla);
			estadisticaManager.add(EstadisticasConstants.INICIO_TENENCIAS_TOTALES, codigoTotales);
			estadisticaManager.add(EstadisticasConstants.INICIO_TENENCIAS_PAGINA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK.equalsIgnoreCase(codigoTotales)
							? EstadisticasConstants.CODIGO_ESTADISTICAS_OK
							: EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL);
		} else {
			estadisticaManager.add(EstadisticasConstants.INICIO_TENENCIAS_GRILLA, codigoGrilla);
			estadisticaManager.add(EstadisticasConstants.INICIO_TENENCIAS_TOTALES, codigoTotales);
			estadisticaManager.add(EstadisticasConstants.INICIO_TENENCIAS_PAGINA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK.equalsIgnoreCase(codigoTotales)
							? EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL
							: EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#busquedaOrdenesCompra(ar.com.santanderrio.obp.servicios.
	 * inversiones.titulos.licitaciones.view.BusquedaOrdenesCompra,
	 * ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum)
	 */
	@Override
	public Respuesta<BuscadorEspeciesView> busquedaOrdenesCompra(BusquedaOrdenesCompra request,
			TipoBancaEnum tipoBancaEnum) {

		Respuesta<DatosConsultaEspeciesResponse> respuestaBO = licitacionesBO.busquedaOrdenesCompra(request,
				sesionCliente.getCliente(), tipoBancaEnum);
		if (EstadoRespuesta.ERROR.equals(respuestaBO.getEstadoRespuesta())) {
			grabarEstadisticasBusquedaOrdenesCompra(tipoBancaEnum, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_INICIO_ORDENES_COMPRA);
		} else {
			grabarEstadisticasBusquedaOrdenesCompra(tipoBancaEnum, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return crearRespuestaBuscadorOK(respuestaBO.getRespuesta().getListaEspecies(), request.getIdSesion());
		}
	}

	/**
	 * Grabar estadisticas busqueda ordenes compra.
	 *
	 * @param tipoBancaEnum the tipo banca enum
	 * @param estado        the estado
	 */
	private void grabarEstadisticasBusquedaOrdenesCompra(TipoBancaEnum tipoBancaEnum, String estado) {
		if (TipoBancaEnum.BANCA_PERSONAL.equals(tipoBancaEnum)) {
			estadisticaManager.add(EstadisticasConstants.TITUOS_BUSQUEDA_ORDENES_COMPRA, estado);
		} else {
			estadisticaManager.add(EstadisticasConstants.TITUOS_BUSQUEDA_ORDENES_COMPRA_BPRIV, estado);
		}

	}

	/**
	 * Crear respuesta buscador OK.
	 *
	 * @param lista    the lista
	 * @param idSesion the id sesion
	 * @return the respuesta
	 */
	private Respuesta<BuscadorEspeciesView> crearRespuestaBuscadorOK(List<EspeciesEntity> lista, String idSesion) {
		BuscadorEspeciesView buscador = new BuscadorEspeciesView();
		for (EspeciesEntity especie : lista) {
			EspeciesView especieView = new EspeciesView();
			especieView.setCodigoAmigable(especie.getCodigoAmigable());
			especieView.setCodigoEspecieRossi(especie.getCodigoEspecieRossi());
			especieView.setDescripcionEspecie(especie.getDescripcionEspecie());
			especieView.setInstrumento(especie.getInstrumento());
			especieView.setTipoEspecie(especie.getTipoEspecie());
			buscador.getListaEspecies().add(especieView);
		}
		buscador.setMensaje(
				mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.WARNING_SIN_ESPECIES_BUSQUEDA).getMensaje());
		buscador.setIdSesion(idSesion);
		Respuesta<BuscadorEspeciesView> respuesta = respuestaFactory.crearRespuestaOk(BuscadorEspeciesView.class,
				buscador);

		if (buscador.getListaEspecies().isEmpty()) {
			respuesta.setRespuestaVacia(true);
		}
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#configuracionOrdenesCompra(ar.com.santanderrio.obp.servicios.
	 * inversiones.titulos.licitaciones.view.ConfiguracionOrdenesCompraRequest,
	 * ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum)
	 */
	@Override
	public Respuesta<ConfiguracionOrdenesCompraResponse> configuracionOrdenesCompra(
			ConfiguracionOrdenesCompraRequest request, TipoBancaEnum tipoBancaEnum) {

		ContadorIntentos contador = new ContadorIntentos();
		contador.setIdView(ContadorIntentosEnum.CONFIRMACION_ORDEN_COMPRA, 2, "");
		contador.setEstado(false);
		sessionParametros.setContador(contador);

		return logicaConfiguracionOrdenesCompra(request, tipoBancaEnum);

	}

	@Override
	public Respuesta<ConfiguracionOrdenesCompraResponse> configuracionOrdenesCompraRellamado(
			ConfiguracionOrdenesCompraRequest request, TipoBancaEnum tipoBancaEnum) {

		return logicaConfiguracionOrdenesCompra(request, tipoBancaEnum);

	}

	private Respuesta<ConfiguracionOrdenesCompraResponse> logicaConfiguracionOrdenesCompra(
			ConfiguracionOrdenesCompraRequest request, TipoBancaEnum tipoBancaEnum) {

		Respuesta<ConfiguracionOrdenesCompraResponse> response = new Respuesta<ConfiguracionOrdenesCompraResponse>();

		if (TipoBancaEnum.BANCA_PERSONAL.equals(tipoBancaEnum)) {
			Respuesta<CuentasView> respuestaPesos = cuentaManager.getCuentasSaldoPorMoneda(TIPO_PESOS);
			Respuesta<CuentasView> respuestaDolares = cuentaManager.getCuentasSaldoPorMoneda(TIPO_USD);

			response = licitacionesBO.configuracionOrdenesCompra(request, sesionCliente.getCliente(),
					respuestaPesos.getRespuesta().getCuentas(), respuestaDolares.getRespuesta().getCuentas(),
					tipoBancaEnum);
		} else {
			String cuentaOperativaSinFormatear = obtenerCuentaOperativaSinFormatear(
					request.getCuenta().replace("/", ""), sesionCliente.getCliente());
			Respuesta<CuentasAdhesionDebitoView> cuentaConSaldo = fondoBO
					.obtenerSaldosCuentaOperativa(cuentaOperativaSinFormatear, sesionCliente.getCliente(), true);
			if (EstadoRespuesta.ERROR.equals(cuentaConSaldo.getEstadoRespuesta())) {
				estadisticaManager.add(EstadisticasConstants.TITUOS_CONFIGURACION_ORDENES_COMPRA_BPRIV,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_INICIO_ORDENES_COMPRA);
			}
			response = licitacionesBO.configuracionOrdenesCompraBPriv(request, sesionCliente.getCliente(),
					cuentaConSaldo.getRespuesta(), tipoBancaEnum);
		}

		if (EstadoRespuesta.ERROR.equals(response.getEstadoRespuesta())) {
			estadisticaManager.add(
					TipoBancaEnum.BANCA_PERSONAL.equals(tipoBancaEnum)
							? EstadisticasConstants.TITUOS_CONFIGURACION_ORDENES_COMPRA
							: EstadisticasConstants.TITUOS_CONFIGURACION_ORDENES_COMPRA_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			if (TipoError.ERROR_RESOLUCION_30_2017.equals(response.getItemsMensajeRespuesta().get(0).getTipoError())) {
				return response;
			}
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_INICIO_ORDENES_COMPRA);
		}

		response.getRespuesta().setMensajePrecioLimite(
				mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_PRECIO_LIMITE).getMensaje());

		if (response.getRespuesta().getAperturaEspecie().getPlazos().isEmpty()) {
			if (response.getRespuesta().getAperturaEspecie().isEspeciePesos() != response.getRespuesta()
					.getAperturaEspecie().isEspecieDolares()) {
				estadisticaManager.add(
						TipoBancaEnum.BANCA_PERSONAL.equals(tipoBancaEnum)
								? EstadisticasConstants.TITUOS_CONFIGURACION_ORDENES_COMPRA
								: EstadisticasConstants.TITUOS_CONFIGURACION_ORDENES_COMPRA_BPRIV,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_MONEDA_LIQUIDACION,
						CodigoMensajeConstantes.ERROR_NO_POSEE_MONEDA_PARA_LA_LIQUIDACION);
			}
			if (response.getRespuesta().getAperturaEspecie().isFueraHorario()) {
				estadisticaManager.add(
						TipoBancaEnum.BANCA_PERSONAL.equals(tipoBancaEnum)
								? EstadisticasConstants.TITUOS_CONFIGURACION_ORDENES_COMPRA
								: EstadisticasConstants.TITUOS_CONFIGURACION_ORDENES_COMPRA_BPRIV,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				return respuestaFactory.crearRespuestaError("", TipoError.FUERA_DE_HORARIO,
						CodigoMensajeConstantes.ERROR_ORDENES_COMPRA_FUERA_HORARIO);
			} else {
				estadisticaManager.add(
						TipoBancaEnum.BANCA_PERSONAL.equals(tipoBancaEnum)
								? EstadisticasConstants.TITUOS_CONFIGURACION_ORDENES_COMPRA
								: EstadisticasConstants.TITUOS_CONFIGURACION_ORDENES_COMPRA_BPRIV,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_ORDEN_NO_NEGOCIABLE,
						CodigoMensajeConstantes.ERROR_ORDENES_COMPRA_NO_NEGOCIABLE);
			}
		}

		response.getRespuesta().setContratoAceptado(buscarContratoAceptado(request, tipoBancaEnum));
		response = revisarSiCorrespondenLegalesPrecioReferencia(tipoBancaEnum, response);
		if (EstadoRespuesta.ERROR.equals(response.getEstadoRespuesta())) {
			return response;
		}

		String legales = obtenerLegalesContrato(request.getTipoEspecie(), tipoBancaEnum);
		if (legales == null) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		response.getRespuesta().setLegalContrato(legales);

		Respuesta<ConfigurarLicitacionOutView> respuestaCondiciones = licitacionesBO
				.establecerCondicionesAceptadas(sesionCliente.getCliente().getNup(), BANCA_PERSONAL);
		if (!EstadoRespuesta.OK.equals(respuestaCondiciones.getEstadoRespuesta())) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_GENERICO);
		}
		response.getRespuesta().setCondicionesGeneralesAceptadas(
				respuestaCondiciones.getRespuesta().isCondicionesGeneralesAceptadas());
		response.getRespuesta()
				.setComprobantesCnvDisponibles(respuestaCondiciones.getRespuesta().isComprobantesCnvDisponibles());
		response.getRespuesta().setCuentasCustodia(respuestaCondiciones.getRespuesta().getCuentasCustodia());
		response.getRespuesta()
				.setCuentasCustodiaTotales(respuestaCondiciones.getRespuesta().getCuentasCustodiaTotales());

		sessionParametros.setListaAperturaEspecie(response.getRespuesta().getAperturaEspecie());
		if (!response.getRespuesta().isPoderCompraOK()) {
			estadisticaManager.add(
					TipoBancaEnum.BANCA_PERSONAL.equals(tipoBancaEnum)
							? EstadisticasConstants.TITUOS_CONFIGURACION_ORDENES_COMPRA
							: EstadisticasConstants.TITUOS_CONFIGURACION_ORDENES_COMPRA_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR_PARCIAL);
			return respuestaFactory.crearRespuestaWarning(response.getRespuesta(), "", TipoError.WARNING_PODER_COMPRA,
					CodigoMensajeConstantes.ERROR_PODER_COMPRA);
		}

		ContadorIntentos contadorPDC = new ContadorIntentos();
		contadorPDC.setIdView(ContadorIntentosEnum.ADHESION_PODER_COMPRA, 2, "");
		contadorPDC.setEstado(false);
		sessionParametros.getContador().setContadorSiguiente(contadorPDC);

		estadisticaManager.add(
				TipoBancaEnum.BANCA_PERSONAL.equals(tipoBancaEnum)
						? EstadisticasConstants.TITUOS_CONFIGURACION_ORDENES_COMPRA
						: EstadisticasConstants.TITUOS_CONFIGURACION_ORDENES_COMPRA_BPRIV,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		
		response=inyectarRepatriacion(response,sesionCliente.getCliente(),tipoBancaEnum.getCodigo());
		return respuestaFactory.crearRespuestaOk(ConfiguracionOrdenesCompraResponse.class, response.getRespuesta());

	}

	private Respuesta<ConfiguracionOrdenesCompraResponse> inyectarRepatriacion(
			Respuesta<ConfiguracionOrdenesCompraResponse> response,Cliente cliente, String segmento) {
		if(segmento.equals(TipoBancaEnum.BANCA_PERSONAL.getCodigo())) {
			
			List<CuentasAdhesionDebitoView> cuentasDebitoDolares = response.getRespuesta().getCuentasDebitoDolares();
//			List<CuentasAdhesionDebitoView> cuentasDebito =response.getRespuesta().getCuentasDebitoPesos();
//			cuentasDebito.addAll(cuentasDebitoDolares);
			
			for(Cuenta cta : cliente.getCuentas()) {
			
				for(CuentasAdhesionDebitoView ctaDeb: cuentasDebitoDolares) {
					if(cta.getCbu().equals(ctaDeb.getCbu())) {
						ctaDeb.setRepatriacion(cta.isRepatriacion());
					}
				}
			}
			
		}
		 
		return response;
	}

	/**
	 * Revisar si corresponden legales precio referencia.
	 *
	 * @param tipoBancaEnum the tipo banca enum
	 * @param respuesta     the respuesta
	 * @return the respuesta
	 */
	private Respuesta<ConfiguracionOrdenesCompraResponse> revisarSiCorrespondenLegalesPrecioReferencia(
			TipoBancaEnum tipoBancaEnum, Respuesta<ConfiguracionOrdenesCompraResponse> respuesta) {

		if (tipoBancaEnum.equals(TipoBancaEnum.BANCA_PERSONAL)) {
			Respuesta<String> legalesPrecioReferencia = legalBO
					.buscarLegal(CodigoMensajeConstantes.LEGALES_PRECIO_REFERENCIA);
			if (EstadoRespuesta.ERROR.equals(legalesPrecioReferencia.getEstadoRespuesta())) {
				respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			} else {
				respuesta.getRespuesta().setLegalesPrecioReferencia(legalesPrecioReferencia.getRespuesta());
			}
		}
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#grabarEstadisticaInicioOrdenesCompra(ar.com.santanderrio.obp.
	 * servicios.inversiones.comun.TipoBancaEnum)
	 */
	@Override
	public Respuesta<Void> grabarEstadisticaInicioOrdenesCompra(TipoBancaEnum tipoBancaEnum) {
		if (TipoBancaEnum.BANCA_PERSONAL.equals(tipoBancaEnum)) {
			estadisticaManager.add(EstadisticasConstants.TITUOS_INICIO_ORDENES_COMPRA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			estadisticaManager.add(EstadisticasConstants.TITUOS_INICIO_ORDENES_COMPRA_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

	/**
	 * Buscar contrato aceptado.
	 *
	 * @param request       the request
	 * @param tipoBancaEnum the tipo banca enum
	 * @return true, if successful
	 */
	private boolean buscarContratoAceptado(ConfiguracionOrdenesCompraRequest request, TipoBancaEnum tipoBancaEnum) {
		Respuesta<String> respuestaContratoAceptado = new Respuesta<String>();
		respuestaContratoAceptado = contratosBO.buscarContratoAceptado(sesionCliente.getCliente().getFechaNacimiento(),
				sesionCliente.getCliente().getDni(),
				request.getTipoEspecie().equals("SHS") ? CampoEnum.CPI_ACC_C : CampoEnum.CPI_BON_C,
				SinonimoEnum.NO_REPETIDO);
		if (respuestaContratoAceptado.getEstadoRespuesta().equals(EstadoRespuesta.OK)
				&& respuestaContratoAceptado.getRespuesta() != null) {
			grabarEstadisticaBusquedaContrato(tipoBancaEnum, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaContratoAceptado.getRespuesta().equals(CONTRATO_ACEPTADO);
		} else {
			grabarEstadisticaBusquedaContrato(tipoBancaEnum, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return false;
	}

	/**
	 * Grabar estadistica busqueda contrato.
	 *
	 * @param tipoBancaEnum the tipo banca enum
	 * @param estado        the estado
	 */
	private void grabarEstadisticaBusquedaContrato(TipoBancaEnum tipoBancaEnum, String estado) {
		if (TipoBancaEnum.BANCA_PERSONAL.equals(tipoBancaEnum)) {
			estadisticaManager.add(EstadisticasConstants.BUSQUEDA_CONTRATO_INICIATIVA_PROPIA, estado);
		} else {
			estadisticaManager.add(EstadisticasConstants.BUSQUEDA_CONTRATO_INICIATIVA_PROPIA_BPRIV, estado);
		}
	}

	/**
	 * Obtener legales contrato.
	 *
	 * @param tipoEspecie   the tipo especie
	 * @param tipoBancaEnum the tipo banca enum
	 * @return the string
	 */
	private String obtenerLegalesContrato(String tipoEspecie, TipoBancaEnum tipoBancaEnum) {
		Respuesta<String> respuestaLegales = new Respuesta<String>();
		if (TipoBancaEnum.BANCA_PERSONAL.equals(tipoBancaEnum)) {
			if (tipoEspecie.equals("SHS")) {
				respuestaLegales = legalBO.buscarLegal(CodigoMensajeConstantes.LEGAL_CONTRATO_COMPRA_SHS);
			} else {
				respuestaLegales = legalBO.buscarLegal(CodigoMensajeConstantes.LEGAL_CONTRATO_COMPRA_BON);
			}
		} else {
			if (tipoEspecie.equals("SHS")) {
				respuestaLegales = legalBO.buscarLegal(CodigoMensajeConstantes.LEGAL_CONTRATO_COMPRA_SHS_BPRIV);
			} else {
				respuestaLegales = legalBO.buscarLegal(CodigoMensajeConstantes.LEGAL_CONTRATO_COMPRA_BON_BPRIV);
			}
		}
		return respuestaLegales.getEstadoRespuesta().equals(EstadoRespuesta.OK) ? respuestaLegales.getRespuesta()
				: null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#simularOrdenCompra(ar.com.santanderrio.obp.servicios.
	 * inversiones.titulos.licitaciones.view.ConfirmarOrdenCompraRequest,
	 * ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum)
	 */
	@Override
	public Respuesta<ConfirmarOrdenCompraResponse> simularOrdenCompra(ConfirmarOrdenCompraRequest request,
			TipoBancaEnum tipoBancaEnum) {
		// Estadistica de la resolucion 30 2017 recuperada en el paso 1
		if (tipoBancaEnum.equals(TipoBancaEnum.BANCA_PERSONAL)) {
			estadisticaManager.add(EstadisticasConstants.RESOLUCION_30_2017,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			estadisticaManager.add(EstadisticasConstants.RESOLUCION_30_2017_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}

		if (sessionParametros.getContador() != null) {
			sessionParametros.getContador().gestionarContador(ContadorIntentosEnum.CONFIRMACION_ORDEN_COMPRA, false)
					.setEstado(true);
		}

		Respuesta<CompraVtaTitulosResponse> respuesta = new Respuesta<CompraVtaTitulosResponse>();

		respuesta = licitacionesBO.confirmarOrdenCompra(request, sesionCliente.getCliente(), COMPRA_ORDENES_SIMULAR,
				tipoBancaEnum);

		if (!EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {

			grabarEstadisticaSimularOrdenCompra(tipoBancaEnum, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(ConfirmarOrdenCompraResponse.class,
					respuesta.getItemsMensajeRespuesta());
		}

		if (!request.isAceptarContrato()) {
			if (!aceptarContrato(request.getTipoEspecie(), tipoBancaEnum).equals(OK)) {
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_ACEPTACION_CONTRATO);
			}
		}

		Respuesta<ConfirmarOrdenCompraResponse> response = crearRespuestaSimularOrdenCompra(respuesta.getRespuesta(),
				tipoBancaEnum);

		grabarEstadisticaSimularOrdenCompra(tipoBancaEnum, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return response;
	}

	/**
	 * Grabar estadistica simular orden compra.
	 *
	 * @param tipoBancaEnum the tipo banca enum
	 * @param estado        the estado
	 */
	private void grabarEstadisticaSimularOrdenCompra(TipoBancaEnum tipoBancaEnum, String estado) {
		if (TipoBancaEnum.BANCA_PERSONAL.equals(tipoBancaEnum)) {
			estadisticaManager.add(EstadisticasConstants.TITUOS_SIMULACION_ORDENES_COMPRA, estado);
		} else {
			estadisticaManager.add(EstadisticasConstants.TITUOS_SIMULACION_ORDENES_COMPRA_BPRIV, estado);
		}
	}

	/**
	 * Aceptar contrato.
	 *
	 * @param tipoEspecie   the tipo especie
	 * @param tipoBancaEnum the tipo banca enum
	 * @return the string
	 */
	private String aceptarContrato(String tipoEspecie, TipoBancaEnum tipoBancaEnum) {
		Respuesta<String> respuestaAceptacion = contratosBO.confirmarAceptacionContrato(
				sesionCliente.getCliente().getFechaNacimiento(), sesionCliente.getCliente().getDni(),
				tipoEspecie.equals("SHS") ? CampoEnum.CPI_ACC_C : CampoEnum.CPI_BON_C, SinonimoEnum.NO_REPETIDO);
		if (respuestaAceptacion.getEstadoRespuesta().equals(EstadoRespuesta.OK)
				&& respuestaAceptacion.getRespuesta().equals(OK)) {
			grabarEstadisticaAceptarContrato(tipoBancaEnum, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaAceptacion.getRespuesta();
		} else {
			grabarEstadisticaAceptarContrato(tipoBancaEnum, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return "0";
	}

	/**
	 * Grabar estadistica aceptar contrato.
	 *
	 * @param tipoBancaEnum the tipo banca enum
	 * @param estado        the estado
	 */
	private void grabarEstadisticaAceptarContrato(TipoBancaEnum tipoBancaEnum, String estado) {
		if (TipoBancaEnum.BANCA_PERSONAL.equals(tipoBancaEnum)) {
			estadisticaManager.add(EstadisticasConstants.ACEPTACION_CONTRATO_INICIATIVA_PROPIA, estado);
		} else {
			estadisticaManager.add(EstadisticasConstants.ACEPTACION_CONTRATO_INICIATIVA_PROPIA_BPRIV, estado);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#confirmarOrdenCompra(ar.com.santanderrio.obp.servicios.
	 * inversiones.titulos.licitaciones.view.ConfirmarOrdenCompraRequest,
	 * ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum)
	 */
	@Override
	public Respuesta<ConfirmarOrdenCompraResponse> confirmarOrdenCompra(ConfirmarOrdenCompraRequest request,
			TipoBancaEnum tipoBancaEnum) {

		boolean permiteReintentar;
		ContadorIntentos contador = sessionParametros.getContador();
		if (sessionParametros.getContador() != null) {
			permiteReintentar = contador.gestionarContador(ContadorIntentosEnum.CONFIRMACION_ORDEN_COMPRA)
					.permiteReintentar();
		} else {
			LOGGER.debug("Error, Contador no inicializado!!");
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		Respuesta<CompraVtaTitulosResponse> respuestaBO = new Respuesta<CompraVtaTitulosResponse>();

		respuestaBO = licitacionesBO.confirmarOrdenCompra(request, sesionCliente.getCliente(), COMPRA_ORDENES_FEEDBACK,
				tipoBancaEnum);

		if (EstadoRespuesta.ERROR.equals(respuestaBO.getEstadoRespuesta())) {
			grabarEstadisticaConfirmarOrdenCompra(tipoBancaEnum, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return obtenerRespuestaErrorConfirmarOrdenCompra(request, permiteReintentar,
					respuestaBO.getItemsMensajeRespuesta().get(0));
		}

		grabarEstadisticaConfirmarOrdenCompra(tipoBancaEnum, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		ConfirmarOrdenCompraResponse respuesta = new ConfirmarOrdenCompraResponse();
		respuesta.setIdSesion(request.getIdSesion());
		String mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.FEEDBACK_OK_ORDEN_COMPRA)
				.getMensaje();
		String mensajeFeedBack = MessageFormat.format(mensaje, request.getDescripcionEspecie(),
				request.getCodigoAmigable());
		respuesta.setMensajeFeedBack(mensajeFeedBack);
		respuesta = respuestaCompraTitulosToDTO(respuestaBO.getRespuesta(), respuesta);
		return respuestaFactory.crearRespuestaOk(ConfirmarOrdenCompraResponse.class, respuesta);
	}

	/**
	 * Obtener respuesta error confirmar orden compra.
	 *
	 * @param request           the request
	 * @param permiteReintentar the permite reintentar
	 * @param item              the item
	 * @return the respuesta
	 */
	private Respuesta<ConfirmarOrdenCompraResponse> obtenerRespuestaErrorConfirmarOrdenCompra(
			ConfirmarOrdenCompraRequest request, boolean permiteReintentar, ItemMensajeRespuesta item) {
		String mensajeFeedBack;
		Respuesta<ConfirmarOrdenCompraResponse> respuesta;
		String detalleTipoError = null;
		if (item.getTipoError().equals(TipoError.ERROR_GENERICO.getDescripcion())
				|| item.getTipoError().equals(TipoError.SE_ENCUENTRA_FUERA_DEL_TUNEL.getDescripcion())
				|| item.getTipoError().equals(TipoError.CUENTA_TITULOS_BLOQUEADA.getDescripcion())
				|| item.getTipoError().equals(TipoError.TENENCIA_BLOQUEADA.getDescripcion())
				|| item.getTipoError().equals(TipoError.SUPERA_TIEMPO_DE_ESPERA.getDescripcion())
				|| item.getTipoError()
						.equals(TipoError.NO_CUMPLE_CONDICIONES_VALIDACIONES_DATOS_INGRESADOS.getDescripcion())
				|| item.getTipoError().equals(TipoError.OTROS_COMPRA_VENTA_CUENTA_TITULOS.getDescripcion())) {
			String mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.FEEDBACK_ERROR_ORDEN_COMPRA)
					.getMensaje();
			mensajeFeedBack = MessageFormat.format(mensaje, request.getDescripcionEspecie(),
					request.getCodigoAmigable());
		} else {
			mensajeFeedBack = item.getMensaje();
		}

		detalleTipoError = item.getTipoError();

		Boolean correspondeReintento = revisarSiCorrespondeReintentar(item);
		if (permiteReintentar && correspondeReintento) {
			respuesta = respuestaFactory.crearRespuestaErrorPersonalizado(ConfirmarOrdenCompraResponse.class,
					mensajeFeedBack, TipoError.ERROR_FINALIZAR_ORDEN_COMPRA_CON_REINTENTO.getDescripcion());
		} else {
			respuesta = respuestaFactory.crearRespuestaErrorPersonalizado(ConfirmarOrdenCompraResponse.class,
					mensajeFeedBack, TipoError.ERROR_FINALIZAR_ORDEN_COMPRA_SIN_REINTENTO.getDescripcion());
		}
		respuesta.getItemsMensajeRespuesta().get(0).setDetalleTipoError(detalleTipoError);
		return respuesta;
	}

	private Boolean revisarSiCorrespondeReintentar(ItemMensajeRespuesta item) {

		return TipoError.CUENTA_NO_FIRMADA.getDescripcion().equals(item.getTipoError())
				|| TipoError.CAMBIOS_SIGNIFICATIVOS_PRECIO_REFERENCIA.getDescripcion().equals(item.getTipoError())
				|| TipoError.SUPERA_TIEMPO_DE_ESPERA.getDescripcion().equals(item.getTipoError())
				|| TipoError.NO_CUMPLE_CONDICIONES_VALIDACIONES_DATOS_INGRESADOS.getDescripcion()
						.equals(item.getTipoError());
	}

	/**
	 * Grabar estadistica confirmar orden compra.
	 *
	 * @param tipoBancaEnum the tipo banca enum
	 * @param estado        the estado
	 */
	private void grabarEstadisticaConfirmarOrdenCompra(TipoBancaEnum tipoBancaEnum, String estado) {
		if (TipoBancaEnum.BANCA_PERSONAL.equals(tipoBancaEnum)) {
			estadisticaManager.add(EstadisticasConstants.TITUOS_CONFIRMACION_ORDENES_COMPRA, estado);
		} else {
			estadisticaManager.add(EstadisticasConstants.TITUOS_CONFIRMACION_ORDENES_COMPRA_BPRIV, estado);
		}
	}

	/**
	 * Respuesta compra titulos to DTO.
	 *
	 * @param datos    the datos
	 * @param response the response
	 * @return the confirmar orden compra response
	 */
	private ConfirmarOrdenCompraResponse respuestaCompraTitulosToDTO(CompraVtaTitulosResponse datos,
			ConfirmarOrdenCompraResponse response) {
		response.setCantidad(datos.getDatos().getCantidad());
		if (null != datos.getDatos().getComision() && !ISBANStringUtils.esCeroDouble(datos.getDatos().getComision())) {
			response.setComision(datos.getDatos().getComision());
		} else {
			response.setComision(null);
		}

		response.setCotizacion(datos.getDatos().getCotizacion());
		response.setCotizacionLimite(datos.getDatos().getCotizacionLimite());
		if (null != datos.getDatos().getDerechos() && !ISBANStringUtils.esCeroDouble(datos.getDatos().getDerechos())) {
			response.setDerechos(datos.getDatos().getDerechos());
		} else {
			response.setDerechos(null);
		}

		response.setFechaDebitoCuenta(datos.getDatos().getFechaDebitoCuenta() != null
				? convertirFechaConfirmarOrden(datos.getDatos().getFechaDebitoCuenta())
				: null);
		response.setFechaLiquidacion(datos.getDatos().getFechaLiquidacion() != null
				? convertirFechaConfirmarOrden(datos.getDatos().getFechaLiquidacion())
				: null);
		response.setImporteDebitoCredito(datos.getDatos().getImporteDebitoCredito());
		response.setImportePoderCompra(datos.getDatos().getImportePoderCompra());
		if (null != datos.getDatos().getImpuestos()
				&& !ISBANStringUtils.esCeroDouble(datos.getDatos().getImpuestos())) {
			response.setImpuestos(datos.getDatos().getImpuestos());
		} else {
			response.setImpuestos(null);
		}

		if (null != datos.getDatos().getIva() && !ISBANStringUtils.esCeroDouble(datos.getDatos().getIva())) {
			response.setIva(datos.getDatos().getIva());
		} else {
			response.setIva(null);
		}

		response.setLegales(datos.getDatos().getLegales());
		response.setComprobante(datos.getDatos().getNumeroOperacion());
		response.setNumeroOrden(datos.getDatos().getNumeroOrden());
		response.setDatosComisiones(datos.getDatos().getDatosConsultaComisionResponse());
		
		return response;
	}

	/**
	 * Convertir fecha confirmar orden.
	 *
	 * @param fecha the fecha
	 * @return the string
	 */
	// Formatear fecha del servicio para enviar a front en el formato correcto
	private String convertirFechaConfirmarOrden(String fecha) {
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date;
			date = format.parse(fecha);
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			return df.format(date);
		} catch (ParseException e) {
			LOGGER.error("Error al convertir la fecha", e);
		}
		return null;
	}

	/**
	 * Crear respuesta simular orden compra.
	 *
	 * @param respServicio  the resp servicio
	 * @param tipoBancaEnum the tipo banca enum
	 * @return the respuesta
	 */
	private Respuesta<ConfirmarOrdenCompraResponse> crearRespuestaSimularOrdenCompra(
			CompraVtaTitulosResponse respServicio, TipoBancaEnum tipoBancaEnum) {
		ConfirmarOrdenCompraResponse respuesta = respuestaCompraTitulosToDTO(respServicio,
				new ConfirmarOrdenCompraResponse());
		respuesta.setIdCumplimiento(respServicio.getDatos().getRespuestaSimulacionERI().getIdEvaluacion());
		// Manejar ERI
		if (respServicio.getDatos().getCodigoRespERI() == 0) {
			try {
				EvaluacionDeRiesgoResponse evaluacionDeRiesgoResponse = new EvaluacionDeRiesgoResponse();
				// CARGO CABECERA Y PIE SI CORRESPONDE
				if (respServicio.getDatos().getRespuestaSimulacionERI() != null
						&& !(0 == (respServicio.getDatos().getRespuestaSimulacionERI().getTipoDisclaimer()))) {
					Mensaje mensajeDisclaimer = new Mensaje();
					mensajeDisclaimer.setCantidadDeDisclaimers(0);
					if (!StringUtils
							.isBlank(respServicio.getDatos().getRespuestaSimulacionERI().getDisclaimer().toString())) {
						mensajeDisclaimer = JaxbUtils.transformarXmlAObject(
								respServicio.getDatos().getRespuestaSimulacionERI().getDisclaimer().toString(),
								Mensaje.class);
						if (!StringUtils.isBlank(respServicio.getDatos().getRespuestaSimulacionERI()
								.getDisclaimerCumplimiento().toString())) {
							mensajeDisclaimer.add(
									JaxbUtils.transformarXmlAObject(respServicio.getDatos().getRespuestaSimulacionERI()
											.getDisclaimerCumplimiento().toString(), Mensaje.class));
						}
					}
					evaluacionDeRiesgoResponse.setMensaje(mensajeDisclaimer);
					if (mensajeDisclaimer.getCantidadDeDisclaimers() > 0) {
						respuesta.setCabecera(respServicio.getDatos().getRespuestaSimulacionERI().getCabecera());
						respuesta.setPie(respServicio.getDatos().getRespuestaSimulacionERI().getPie());
					}
				}
				if (respServicio.getDatos().getRespuestaSimulacionERI().getTipoDisclaimer() == 2) {
					List<ItemMensajeRespuesta> itemsMensajeRespuesta = cargarMensajesPopup(evaluacionDeRiesgoResponse,
							TipoError.RIESGO_BLOQUEANTE);
					return respuestaFactory.crearRespuestaWarning(ConfirmarOrdenCompraResponse.class, respuesta,
							itemsMensajeRespuesta);

				} else if (respServicio.getDatos().getRespuestaSimulacionERI().getTipoDisclaimer() == 1) {
					List<ItemMensajeRespuesta> itemsMensajeRespuesta = cargarMensajesPopup(evaluacionDeRiesgoResponse,
							TipoError.RIESGO_MEDIO);
					respuesta.setIdCumplimiento(respServicio.getDatos().getRespuestaSimulacionERI().getIdEvaluacion());
					return respuestaFactory.crearRespuestaWarning(ConfirmarOrdenCompraResponse.class, respuesta,
							itemsMensajeRespuesta);
				}
			} catch (JAXBException e) {
				LOGGER.error("Error recuperando disclaimer. {}.", e);
			}
		} else {
			List<ItemMensajeRespuesta> itemsMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
			ItemMensajeRespuesta item = new ItemMensajeRespuesta(mensajeBO
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.SERVICIO_EVALUACION_RIESGO_CAIDO).getMensaje());
			item.setTipoError(TipoError.SERVICIO_ERI_CAIDO.toString());
			itemsMensajeRespuesta.add(item);
			return respuestaFactory.crearRespuestaWarning(ConfirmarOrdenCompraResponse.class, respuesta,
					itemsMensajeRespuesta);
		}
		return respuestaFactory.crearRespuestaOk(ConfirmarOrdenCompraResponse.class, respuesta);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#descargarComprobanteOrdenCompraPDF(ar.com.santanderrio.obp.
	 * servicios.inversiones.titulos.licitaciones.view.ComprobanteOrdenCompra,
	 * ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteOrdenCompraPDF(ComprobanteOrdenCompra viewRequest,
			TipoBancaEnum tipoBancaEnum) {
		Respuesta<Reporte> reporte;
		Respuesta<ReporteView> respuestaView;
		// validarHashSuscripcionComprobante(viewRequest);
		reporte = reporteBO.obtenerComprobantePDF(viewRequest);
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
			grabarEstadisticasDescargaOrdenCompra(tipoBancaEnum, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			respuestaView = respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
			grabarEstadisticasDescargaOrdenCompra(tipoBancaEnum, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaView;
	}

	/**
	 * Grabar estadisticas descarga orden compra.
	 *
	 * @param tipoBancaEnum the tipo banca enum
	 * @param codigo        the codigo
	 */
	private void grabarEstadisticasDescargaOrdenCompra(TipoBancaEnum tipoBancaEnum, String codigo) {
		if (TipoBancaEnum.BANCA_PERSONAL.equals(tipoBancaEnum)) {
			estadisticaManager.add(EstadisticasConstants.DESCARGA_PDF_ORDEN_COMPRA, codigo);
		} else {
			estadisticaManager.add(EstadisticasConstants.DESCARGA_PDF_ORDEN_COMPRA_BPRIV, codigo);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#verComprobanteOrdenCompra(ar.com.santanderrio.obp.servicios.
	 * inversiones.comun.TipoBancaEnum)
	 */
	@Override
	public Respuesta<ComprobanteOrdenCompraView> verComprobanteOrdenCompra(TipoBancaEnum tipoBancaEnum) {
		ComprobanteOrdenCompraView comprobanteResponse = new ComprobanteOrdenCompraView();
		comprobanteResponse.setFechaHora(ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date()));
		if (TipoBancaEnum.BANCA_PERSONAL.equals(tipoBancaEnum)) {
			estadisticaManager.add(EstadisticasConstants.VER_COMPROBANTE_ORDEN_COMPRA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			estadisticaManager.add(EstadisticasConstants.VER_COMPROBANTE_ORDEN_COMPRA_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}
		return respuestaFactory.crearRespuestaOk(ComprobanteOrdenCompraView.class, comprobanteResponse);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#firmarCuentasOrdenCompras(ar.com.santanderrio.obp.servicios.
	 * inversiones.titulos.licitaciones.view.FirmarCuentasViewRequest)
	 */
	@Override
	public Respuesta<String> firmarCuentasOrdenCompras(FirmarCuentasViewRequest request) {
		Respuesta<FirmarCuentasViewRequest> rtaValidacion = super.validate(request, new FirmarCuentasViewRequest());
		if (!rtaValidacion.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		Respuesta<String> respuestaBo = licitacionesBO.firmarCuentas(sesionCliente.getCliente(), request);

		if (EstadoRespuesta.OK.equals(respuestaBo.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.FIRMA_CONDICIONES_GENERALES_CUENTA_CUSTODIA_ORDEN_COMPRA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			List<ItemMensajeRespuesta> itemMensajeRta = new ArrayList<ItemMensajeRespuesta>();
			ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
			itemMensajeRespuesta.setMensaje(mensajeBO
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.ACEPTAR_CONDICIONES_GENERALES_CUENTA_CUSTODIA_OK)
					.getMensaje());
			itemMensajeRta.add(itemMensajeRespuesta);
			respuestaBo.setItemMensajeRespuesta(itemMensajeRta);
		} else {
			estadisticaManager.add(EstadisticasConstants.FIRMA_CONDICIONES_GENERALES_CUENTA_CUSTODIA_ORDEN_COMPRA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaBo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#firmarCuentasOrdenComprasBpriv(ar.com.santanderrio.obp.
	 * servicios.inversiones.titulos.licitaciones.view.FirmarCuentasViewRequest)
	 */
	@Override
	public Respuesta<String> firmarCuentasOrdenComprasBpriv(FirmarCuentasViewRequest request) {
		Respuesta<FirmarCuentasViewRequest> rtaValidacion = super.validate(request, new FirmarCuentasViewRequest());
		if (!rtaValidacion.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		Respuesta<String> respuestaBo = licitacionesBO.firmarCuentas(sesionCliente.getCliente(), request);

		if (EstadoRespuesta.OK.equals(respuestaBo.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.FIRMA_CONDICIONES_GENERALES_CUENTA_CUSTODIA_ORDEN_COMPRA_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			List<ItemMensajeRespuesta> itemMensajeRta = new ArrayList<ItemMensajeRespuesta>();
			ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
			itemMensajeRespuesta.setMensaje(mensajeBO
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.ACEPTAR_CONDICIONES_GENERALES_CUENTA_CUSTODIA_OK)
					.getMensaje());
			itemMensajeRta.add(itemMensajeRespuesta);
			respuestaBo.setItemMensajeRespuesta(itemMensajeRta);
		} else {
			estadisticaManager.add(EstadisticasConstants.FIRMA_CONDICIONES_GENERALES_CUENTA_CUSTODIA_ORDEN_COMPRA_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaBo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#verComprobanteReversa(ar.com.santanderrio.obp.servicios.
	 * titulos.operaciones.view.ReversarCompraVentaRequestView)
	 */
	@Override
	public Respuesta<Void> verComprobanteReversa(ReversarCompraVentaRequestView request) {
		String codigoEstadisticaSegunOperacion;
		if (BANCA_PRIVADA.equalsIgnoreCase(request.getTipoBanca())) {
			if (OPERACIONES_DE_VENTA.equalsIgnoreCase(request.getTipoOperacion())) {
				codigoEstadisticaSegunOperacion = EstadisticasConstants.COMPROBANTE_REVERSA_ORDEN_VENTA_BPRIV;
			} else {
				codigoEstadisticaSegunOperacion = EstadisticasConstants.COMPROBANTE_REVERSA_ORDEN_COMPRA_BPRIV;
			}
			// BANCA PERSONAL
		} else if (OPERACIONES_DE_VENTA.equalsIgnoreCase(request.getTipoOperacion())) {
			codigoEstadisticaSegunOperacion = EstadisticasConstants.COMPROBANTE_REVERSA_ORDEN_VENTA_BPERS;
		} else {
			codigoEstadisticaSegunOperacion = EstadisticasConstants.COMPROBANTE_REVERSA_ORDEN_COMPRA_BPERS;
		}
		estadisticaManager.add(codigoEstadisticaSegunOperacion, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#detalleTituloRTL(ar.com.santanderrio.obp.servicios.inversiones
	 * .titulos.licitaciones.view.DetalleTituloInView)
	 */
	@Override
	public Respuesta<DetalleTitulosBPView> detalleTituloRTL(DetalleTituloInView view) {
		if (view.isGrillaParcial()) {
			LOGGER.info("Error parcial en grilla");
			estadisticaManager.add(EstadisticasConstants.TITULOS_DETALLE_RTL,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_LEGALES_LICITACIONES_VIGENTES,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		LOGGER.info(OBTENIENDO_LEGALES);
		Respuesta<String> respuestaLegales1 = legalBO.buscarLegal(CodigoMensajeConstantes.TITULOS_DETALLE_BP_LEGALES_1);
		if (!EstadoRespuesta.OK.equals(respuestaLegales1.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.TITULOS_DETALLE_RTL,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_LEGALES_LICITACIONES_VIGENTES,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		Respuesta<String> respuestaLegales2 = legalBO.buscarLegal(CodigoMensajeConstantes.TITULOS_DETALLE_BP_LEGALES_2);
		if (!EstadoRespuesta.OK.equals(respuestaLegales2.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.TITULOS_DETALLE_RTL,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_LEGALES_LICITACIONES_VIGENTES,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		DetalleTitulosBPView detalleTitulosBPView = new DetalleTitulosBPView();
		detalleTitulosBPView.setLegal1(respuestaLegales1.getRespuesta());
		detalleTitulosBPView.setLegal2(respuestaLegales2.getRespuesta());

		estadisticaManager.add(EstadisticasConstants.TITULOS_DETALLE_RTL, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(detalleTitulosBPView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#detalleTituloBPriv(ar.com.santanderrio.obp.servicios.
	 * inversiones.titulos.licitaciones.view.DetalleTituloInView)
	 */
	@Override
	public Respuesta<DetalleTitulosBPView> detalleTituloBPriv(DetalleTituloInView view) {
		LOGGER.info("Error parcial en grilla");
		if (view.isGrillaParcial()) {
			estadisticaManager.add(EstadisticasConstants.TITULOS_DETALLE_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_LEGALES_LICITACIONES_VIGENTES,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		LOGGER.info(OBTENIENDO_LEGALES);
		Respuesta<String> respuestaLegales1 = legalBO.buscarLegal(CodigoMensajeConstantes.TITULOS_DETALLE_BP_LEGALES_1);
		if (!EstadoRespuesta.OK.equals(respuestaLegales1.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.TITULOS_DETALLE_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_LEGALES_LICITACIONES_VIGENTES,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		Respuesta<String> respuestaLegales2 = legalBO.buscarLegal(CodigoMensajeConstantes.TITULOS_DETALLE_BP_LEGALES_2);
		if (!EstadoRespuesta.OK.equals(respuestaLegales2.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.TITULOS_DETALLE_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_LEGALES_LICITACIONES_VIGENTES,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		DetalleTitulosBPView detalleTitulosBPView = new DetalleTitulosBPView();
		detalleTitulosBPView.setLegal1(respuestaLegales1.getRespuesta());
		detalleTitulosBPView.setLegal2(respuestaLegales2.getRespuesta());

		estadisticaManager.add(EstadisticasConstants.TITULOS_DETALLE_BPRIV,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(detalleTitulosBPView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#licitacionesVigentesGoToLicitar(ar.com.santanderrio.obp.
	 * servicios.inversiones.titulos.licitaciones.view.ConfigurarLicitacionViewReq)
	 */
	@Override
	public Respuesta<ConfigurarLicitacionOutView> licitacionesVigentesGoToLicitar(ConfigurarLicitacionViewReq request) {
		List<CuentaTituloParaLicitarView> cuentasValidas = new ArrayList<CuentaTituloParaLicitarView>();
		Boolean esPrimerIngreso = StringUtils.isBlank(request.getCuentaTitulo());
		if (esPrimerIngreso) {
			NuevaLicitacionViewReq nuevaLicitacionViewRequest = new NuevaLicitacionViewReq();
			nuevaLicitacionViewRequest.setCuentasTitulo(request.getCuentasTitulo());
			Respuesta<NuevaLicitacionViewResponse> nuevaLicitacionViewResponse = obtenerNuevaLicitacionViewResponse(
					nuevaLicitacionViewRequest);
			if (!nuevaLicitacionViewResponse.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
			cuentasValidas = filtrarCuentasValidasParaEspecieSeleccionada(
					nuevaLicitacionViewResponse.getRespuesta().getCuentasTitulo(), request.getCodigoEspecie());
			if (CollectionUtils.isEmpty(cuentasValidas)) {
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
			request.setCuentaTitulo(StringUtils.remove(cuentasValidas.get(0).getNroCuenta(), "/"));
		}
		Respuesta<ConfigurarLicitacionOutView> configurarLicitacionOutView = configurarLicitacion(request);
		if (!configurarLicitacionOutView.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			configurarLicitacionOutView.getRespuesta().setCuentasTitulo(cuentasValidas);
		}
		if (esPrimerIngreso) {
			estadisticaManager.add(EstadisticasConstants.LICITACIONESVIGENTES_GOTO_LICITAR,
					configurarLicitacionOutView.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)
							? EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR
							: EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}
		configurarLicitacionOutView= inyectarRepatriacionLic(configurarLicitacionOutView, sesionCliente.getCliente(), BANCA_PERSONAL);
		return configurarLicitacionOutView;
	}

	private Respuesta<ConfigurarLicitacionOutView> inyectarRepatriacionLic(
			Respuesta<ConfigurarLicitacionOutView> configurarLicitacionOutView, Cliente cliente, String bancaPersonal) {
		// TODO Auto-generated method stub
		List<CuentasAdhesionDebitoView> ctasDebitoDolares = configurarLicitacionOutView.getRespuesta().getCuentasDebitoDolares();
		
		List<CuentasAdhesionDebitoView> ctasDolares = new ArrayList<CuentasAdhesionDebitoView>(configurarLicitacionOutView.getRespuesta().getCuentasDebitoDolares());
		
		List<Cuenta> ctasDebitoCliente = cliente.getCuentas();
		
		for(CuentasAdhesionDebitoView ctaDebito : ctasDolares) {
			for(Cuenta cta : ctasDebitoCliente) {
				if(ctaDebito.getCbu().equals(cta.getCbu()) && cta.isRepatriacion()) {
					//ctaDebito.setRepatriacion(true);
					ctasDebitoDolares.remove(ctaDebito);
				}
			}
		}
		
		return configurarLicitacionOutView;
	}

	/**
	 * Filtrar cuentas validas para especie seleccionada.
	 *
	 * @param cuentasTitulo the cuentas titulo
	 * @param codigoEspecie the codigo especie
	 * @return the list
	 */
	private List<CuentaTituloParaLicitarView> filtrarCuentasValidasParaEspecieSeleccionada(
			List<CuentaTituloParaLicitarView> cuentasTitulo, String codigoEspecie) {
		List<CuentaTituloParaLicitarView> cuentasValidas = new ArrayList<CuentaTituloParaLicitarView>();
		for (CuentaTituloParaLicitarView cuenta : cuentasTitulo) {
			for (LicitacionVigente licitacion : cuenta.getLicitacionesVigentesList()) {
				if (StringUtils.equals(codigoEspecie, licitacion.getCodigoEspecie())) {
					cuentasValidas.add(cuenta);
					break;
				}
			}
		}
		return cuentasValidas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#licitacionesVigentesGoToLicitarBPriv(ar.com.santanderrio.obp.
	 * servicios.inversiones.titulos.licitaciones.view.ConfigurarLicitacionViewReq)
	 */
	@Override
	public Respuesta<ConfigurarLicitacionBPrivOutView> licitacionesVigentesGoToLicitarBPriv(
			ConfigurarLicitacionViewReq request) {
		List<CuentaTituloParaLicitarViewBPriv> cuentasValidas = new ArrayList<CuentaTituloParaLicitarViewBPriv>();
		Boolean esPrimerIngreso = StringUtils.isBlank(request.getCuentaTitulo());
		if (esPrimerIngreso) {
			NuevaLicitacionViewReq nuevaLicitacionViewReq = new NuevaLicitacionViewReq();
			nuevaLicitacionViewReq.setCuentasTitulo(request.getCuentasTitulo());
			Respuesta<NuevaLicitacionViewResponseBPriv> nuevaLicitacionViewResponseBPriv = obtenerNuevaLicitacionViewResponseBPriv(
					nuevaLicitacionViewReq);
			if (!nuevaLicitacionViewResponseBPriv.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
			cuentasValidas = filtrarCuentasValidasParaEspecieSeleccionadaBpriv(
					nuevaLicitacionViewResponseBPriv.getRespuesta().getCuentasTitulo(), request.getCodigoEspecie());
			if (CollectionUtils.isEmpty(cuentasValidas)) {
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
			request.setCuentaTitulo(cuentasValidas.get(0).getNumeroCuentaOperativa());
		}
		Respuesta<ConfigurarLicitacionBPrivOutView> configurarLicitacionBPrivOutView = configurarLicitacionBPriv(
				request);
		if (!configurarLicitacionBPrivOutView.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			configurarLicitacionBPrivOutView.getRespuesta().setCuentasTitulo(cuentasValidas);
		}
		if (esPrimerIngreso) {
			estadisticaManager.add(EstadisticasConstants.LICITACIONESVIGENTES_GOTO_LICITAR_BPRIV,
					configurarLicitacionBPrivOutView.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)
							? EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR
							: EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}
		return configurarLicitacionBPrivOutView;
	}

	/**
	 * Filtrar cuentas validas para especie seleccionada bpriv.
	 *
	 * @param cuentasTitulo the cuentas titulo
	 * @param codigoEspecie the codigo especie
	 * @return the list
	 */
	private List<CuentaTituloParaLicitarViewBPriv> filtrarCuentasValidasParaEspecieSeleccionadaBpriv(
			List<CuentaTituloParaLicitarViewBPriv> cuentasTitulo, String codigoEspecie) {
		List<CuentaTituloParaLicitarViewBPriv> cuentasValidas = new ArrayList<CuentaTituloParaLicitarViewBPriv>();
		for (CuentaTituloParaLicitarViewBPriv cuenta : cuentasTitulo) {
			for (LicitacionVigente licitacion : cuenta.getLicitacionesVigentesList()) {
				if (StringUtils.equals(codigoEspecie, licitacion.getCodigoEspecie())) {
					cuentasValidas.add(cuenta);
					break;
				}
			}
		}
		return cuentasValidas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#configuracionOrdenesCompraGrilla()
	 */
	@Override
	public void configuracionOrdenesCompraGrilla() {
		estadisticaManager.add(EstadisticasConstants.TITUOS_CONFIGURACION_ORDENES_COMPRA_GRILLA,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#obtenerRendimiento()
	 */
	@Override
	public Respuesta<RendimientoView> obtenerRendimiento() {
		Respuesta<RendimientoDTO> rtaRendimiento = rendimientoBOImpl.obtenerRendimientoTenenciaRTL(
				sesionCliente.getCliente(), TipoProductoEnum.TITULOS_VALORES.getCodigo());
		String codigoEstadistica = EstadisticasConstants.RENDIMIENTO_TITULOS;

		RendimientoDTO rendimientoDTO = rtaRendimiento.getRespuesta();
		RendimientoView rendimientoView = createRetornoView(rendimientoDTO);
		rendimientoView.setTooltip(obtenerMensajeRendimiento());

		if (EstadoRespuesta.OK.equals(rtaRendimiento.getEstadoRespuesta())) {
			estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(RendimientoView.class, rendimientoView);
		}
		if (EstadoRespuesta.WARNING.equals(rtaRendimiento.getEstadoRespuesta())) {
			estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL);
			return respuestaFactory.crearRespuestaWarning(rendimientoView, "", TipoError.WARNING_PARCIAL_TITULOS_VAORES,
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
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#obtenerRendimientoBPriv()
	 */
	@Override
	public Respuesta<RendimientoBPrivView> obtenerRendimientoBPriv() {
		Respuesta<RendimientoBPrivDTO> rtaRendimiento = rendimientoBOImpl.obtenerRendimientoTenenciaBPriv(
				sesionCliente.getCliente(), TipoProductoEnum.TITULOS_VALORES.getCodigo());
		String codigoEstadistica = EstadisticasConstants.RENDIMIENTO_TITULOS_BP;
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
					TipoError.WARNING_PARCIAL_TITULOS_VAORES, "");
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
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#descargarComprobanteReversaOrdenCompraVenta(ar.com.
	 * santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.
	 * ComprobanteReversaOrdenCompraVenta,
	 * ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteReversaOrdenCompraVenta(
			ComprobanteReversaOrdenCompraVenta viewRequest, TipoBancaEnum tipoBancaEnum) {
		Respuesta<Reporte> reporte;
		Respuesta<ReporteView> respuestaView;
		reporte = reporteBO.obtenerComprobantePDF(viewRequest);
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
			grabarEstadisticaReversarOrdenPorBanca(EstadisticasConstants.CODIGO_ESTADISTICAS_OK, tipoBancaEnum);
		} else {
			respuestaView = respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());

		}
		return respuestaView;
	}

	/**
	 * Grabar estadistica reversar orden por banca.
	 *
	 * @param estado        the estado
	 * @param tipoBancaEnum the tipo banca enum
	 */
	private void grabarEstadisticaReversarOrdenPorBanca(String estado, TipoBancaEnum tipoBancaEnum) {
		if (TipoBancaEnum.BANCA_PERSONAL.equals(tipoBancaEnum)) {
			estadisticaManager.add(EstadisticasConstants.DESCARGA_PDF_ORDEN_COMPRA_VENTA, estado);
		} else {
			estadisticaManager.add(EstadisticasConstants.DESCARGA_PDF_ORDEN_COMPRA_VENTA_BPRIV, estado);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#actualizarPoderDeCompra(ar.com.santanderrio.obp.servicios.
	 * inversiones.titulos.licitaciones.view.ConfiguracionOrdenesCompraRequest)
	 */
	@Override
	public Respuesta<ConfiguracionOrdenesCompraResponse> actualizarPoderDeCompra(
			ConfiguracionOrdenesCompraRequest request) {
		ConfiguracionOrdenesCompraResponse response = new ConfiguracionOrdenesCompraResponse();
		Respuesta<CuentasView> respuestaPesos = cuentaManager.getCuentasSaldoPorMoneda(TIPO_PESOS);
		Respuesta<CuentasView> respuestaDolares = cuentaManager.getCuentasSaldoPorMoneda(TIPO_USD);
		response.setCuentasDebitoPesos(respuestaPesos.getRespuesta().getCuentas());
		response.setCuentasDebitoDolares(respuestaDolares.getRespuesta().getCuentas());
		response.setAperturaEspecie(sessionParametros.getListaAperturaEspecie());
		response = licitacionesBO.obtenerPoderCompra(response, request, sesionCliente.getCliente(),
				TipoBancaEnum.BANCA_PERSONAL);
		Respuesta<ConfiguracionOrdenesCompraResponse> respuestaPDC = new Respuesta<ConfiguracionOrdenesCompraResponse>();
		if (!response.isPoderCompraOK()) {
			respuestaPDC = respuestaFactory.crearRespuestaWarning(response, "", TipoError.WARNING_PODER_COMPRA,
					CodigoMensajeConstantes.ERROR_PODER_COMPRA);
		} else {
			respuestaPDC = respuestaFactory.crearRespuestaOk(response);
		}
		return completarLegalesOrdenCompra(request, respuestaPDC);
	}

	/**
	 * Completar legales orden compra.
	 *
	 * @param request      the request
	 * @param respuestaPDC the respuesta PDC
	 * @return the respuesta
	 */
	private Respuesta<ConfiguracionOrdenesCompraResponse> completarLegalesOrdenCompra(
			ConfiguracionOrdenesCompraRequest request, Respuesta<ConfiguracionOrdenesCompraResponse> respuestaPDC) {
		String legales = obtenerLegalesContrato(request.getTipoEspecie(), TipoBancaEnum.BANCA_PERSONAL);
		if (legales == null) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		respuestaPDC.getRespuesta().setLegalContrato(legales);

		respuestaPDC = revisarSiCorrespondenLegalesPrecioReferencia(TipoBancaEnum.BANCA_PERSONAL, respuestaPDC);
		if (EstadoRespuesta.ERROR.equals(respuestaPDC.getEstadoRespuesta())) {
			return respuestaPDC;
		}
		Respuesta<String> resolucion = legalBO
				.buscarLegal(CodigoMensajeConstantes.ORDEN_COMPRA_VENTA_TITULOS_LEGAL_ORIGEN_FONDOS);
		if (EstadoRespuesta.ERROR.equals(resolucion.getEstadoRespuesta())) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		String mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.PRECIO_REFERENCIA_ORDEN_COMPRA)
				.getMensaje();
		respuestaPDC.getRespuesta().setLegalesResolucion(resolucion.getRespuesta());
		respuestaPDC.getRespuesta().setPrecioReferencia(mensaje);

		respuestaPDC.getRespuesta().setMensajePrecioLimite(
				mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_PRECIO_LIMITE).getMensaje());
		return respuestaPDC;
	}

	@Override
	public Response exportarGrillaTenencias(TipoBancaEnum tipoBanca) {

		Respuesta<Reporte> respuestaReporte;

		if (TipoBancaEnum.BANCA_PERSONAL.equals(tipoBanca)) {
			respuestaReporte = licitacionesBO.obtenerTenenciasReporte(sessionParametros.getListaTenenciasPorCuenta(),
					tipoBanca, sesionCliente.getCliente());
		} else {
			respuestaReporte = licitacionesBO.obtenerTenenciasReporte(sessionParametros.getListaTenenciasPorCuentaBP(),
					tipoBanca, sesionCliente.getCliente());
		}
		ResponseBuilder responseBuilder = null;
		Respuesta<ReporteView> respuestaView = new Respuesta<ReporteView>();
		if (EstadoRespuesta.OK.equals(respuestaReporte.getEstadoRespuesta())) {
			ReporteView resumenesView = ReporteView.fromReporte(respuestaReporte.getRespuesta());
			respuestaView.setRespuesta(resumenesView);
			respuestaView.setEstadoRespuesta(EstadoRespuesta.OK);
			responseBuilder = Response.ok((Object) respuestaView.getRespuesta().getByteArray());
			responseBuilder.header("Content-Disposition", "attachment; filename=\"TenenciaTítulosValores.xls\"");
			estadisticaManager.add(
					TipoBancaEnum.BANCA_PERSONAL.equals(tipoBanca)
							? EstadisticasConstants.DESCARGA_EXCEL_TITULOS_VALORES_RETAIL
							: EstadisticasConstants.DESCARGA_EXCEL_TITULOS_VALORES_PRIVADA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			estadisticaManager.add(
					TipoBancaEnum.BANCA_PERSONAL.equals(tipoBanca)
							? EstadisticasConstants.DESCARGA_EXCEL_TITULOS_VALORES_RETAIL
							: EstadisticasConstants.DESCARGA_EXCEL_TITULOS_VALORES_PRIVADA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			respuestaView = respuestaFactory.crearRespuestaErrorPersonalizado(ReporteView.class, MENSAJE_ERROR_EXCEL,
					TipoError.ERROR_DESCARGA_EXCEL.getDescripcion());
			responseBuilder = Response.ok((Object) respuestaView);
		}
		return responseBuilder.build();

	}

	@Override
	public Respuesta<LicitacionCanjeResponse> obtenerEspeciesCanje(LicitacionCanjeRequest request) {

		Respuesta<LicitacionCanjeResponse> rta = licitacionesBO.obtenerEspeciesCanje(request,
				sesionCliente.getCliente(), TipoBancaEnum.BANCA_PERSONAL.getCodigo());

		if (EstadoRespuesta.OK.equals(rta.getEstadoRespuesta())) {
			try {
				rta.getRespuesta().setCuentasDebitoPesos(obtenerCuentasSaldoActualizado(TIPO_PESOS));
				rta.getRespuesta().setCuentasDebitoDolares(obtenerCuentasSaldoActualizado(TIPO_USD));
			} catch (BusinessException e) {
				estadisticaManager.add(EstadisticasConstants.CONFIGURACION_LICITACION_POR_CANJE,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				LOGGER.info(OCURRIO_UN_ERROR_AL_CONSULTAR_LAS_CUENTAS_SALDOS);
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
			if (request.isPrimerIngreso()) {
				sessionParametros.setContador(new ContadorIntentos(2));
			}
			estadisticaManager.add(EstadisticasConstants.CONFIGURACION_LICITACION_POR_CANJE,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return rta;
		}

		estadisticaManager.add(EstadisticasConstants.CONFIGURACION_LICITACION_POR_CANJE,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
	}

	@Override
	public Respuesta<LicitacionCanjeResponse> obtenerEspeciesCanjeBPriv(LicitacionCanjeRequest request) {

		Respuesta<LicitacionCanjeResponse> rta = licitacionesBO.obtenerEspeciesCanje(request,
				sesionCliente.getCliente(), TipoBancaEnum.BANCA_PRIVADA.getCodigo());

		if (EstadoRespuesta.OK.equals(rta.getEstadoRespuesta())) {
			Respuesta<CuentasAdhesionDebitoView> rtaCuenta = fondoBO.obtenerSaldosCuentaOperativa(
					request.getCtaTitulos().replaceAll("/", ""), sesionCliente.getCliente(), false);
			if (EstadoRespuesta.ERROR.equals(rtaCuenta.getEstadoRespuesta())) {
				estadisticaManager.add(EstadisticasConstants.CONFIGURACION_LICITACION_POR_CANJE_BPRIV,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SALDO_CUENTA_OPERATIVA,
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
			if (request.isPrimerIngreso()) {
				sessionParametros.setContador(new ContadorIntentos(2));
			}
			estadisticaManager.add(EstadisticasConstants.CONFIGURACION_LICITACION_POR_CANJE_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			rta.getRespuesta().setCuentaOperativa(rtaCuenta.getRespuesta());
			return rta;
		}

		estadisticaManager.add(EstadisticasConstants.CONFIGURACION_LICITACION_POR_CANJE_BPRIV,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
	}

	List<CuentasAdhesionDebitoView> obtenerCuentasSaldoActualizado(String moneda) throws BusinessException {

		Respuesta<CuentasView> rta = cuentaManager.getCuentasSaldoPorMoneda(moneda);

		if (rta.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			throw new BusinessException();
		}

		return rta.getRespuesta().getCuentas();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.
	 * manager.TitulosManager#simularLicitacion(ar.com.santanderrio.obp.
	 * servicios.inversiones.titulos.licitaciones.view.SimularLicitacionRequest)
	 */
	@Override
	public Respuesta<SimularLicitacionCanjeResponseView> simularLicitacionCanje(SimularLicitacionCanjeRequest request) {
		Respuesta<SimularLicitacionCanjeResponseView> rta = licitacionesBO
				.simularLicitacionCanje(sesionCliente.getCliente(), request);
		if (EstadoRespuesta.ERROR.equals(rta.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.SIMULAR_LICITACION_CANJE,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		estadisticaManager.add(EstadisticasConstants.SIMULAR_LICITACION_CANJE,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return rta;
	}

	@Override
	public Respuesta<FinalizarLicitacionCanjeView> finalizarLicitacionCanje(FinalizarLicitacionRequest request) {

		Respuesta<FinalizarLicitacionCanjeView> respuesta = licitacionesBO.finalizarLicitacionCanje(request,
				sesionCliente.getCliente(), BANCA_PERSONAL);
		if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.FEEDBACK_LICITACION_CANJE,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			estadisticaManager.add(EstadisticasConstants.FEEDBACK_LICITACION_CANJE,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuesta;
	}

	@Override
	public Respuesta<ComprobanteLicitacionOutView> verComprobanteCanje(TipoBancaEnum tipoBancaEnum) {
		ComprobanteLicitacionOutView comprobanteResponse = new ComprobanteLicitacionOutView();
		comprobanteResponse.setFechaHora(ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date()));
		if(TipoBancaEnum.BANCA_PERSONAL.equals(tipoBancaEnum)) {
			estadisticaManager.add(EstadisticasConstants.VER_COMPROBANTE_CANJE,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}else {
			estadisticaManager.add(EstadisticasConstants.VER_COMPROBANTE_CANJE_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}
		return respuestaFactory.crearRespuestaOk(ComprobanteLicitacionOutView.class, comprobanteResponse);
	}

	@Override
	public Respuesta<SimularLicitacionCanjeResponseView> simularLicitacionCanjeBPriv(
			SimularLicitacionCanjeRequest request) {
		Respuesta<SimularLicitacionCanjeResponseView> rta = licitacionesBO
				.simularLicitacionCanjeBPriv(sesionCliente.getCliente(), request);
		if (EstadoRespuesta.ERROR.equals(rta.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.SIMULAR_LICITACION_CANJE_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}else {
			estadisticaManager.add(EstadisticasConstants.SIMULAR_LICITACION_CANJE_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}
		return rta;
	}


	public Respuesta<ReporteView> descargarComprobanteSuscripcionLicitacionCanjePDF(ComprobanteSuscripcionLicitacionCanje request, TipoBancaEnum tipoBanca) {
		  Respuesta<Reporte> reporte;
	      Respuesta<ReporteView> respuestaView;
	      reporte = reporteBO.obtenerComprobantePDF(request);
	      if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
				ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
				respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
				grabarEstadisticaSuscripcionComprobanteCanje(tipoBanca, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			} else {
				respuestaView = respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
				grabarEstadisticaSuscripcionComprobanteCanje(tipoBanca, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}
			return respuestaView;
	}

	@Override
	public Respuesta<ReversarOrdenViewResponse> reversarOrdenLicitacionCanje(ReversaRequestView request) {
		request.setCanje(true);
		Respuesta<ReversarOrdenResponse> response = licitacionesBO.reversarOrdenLicitacion(request,
				sesionCliente.getCliente());

		if (EstadoRespuesta.OK.equals(response.getEstadoRespuesta())) {
			ReversarOrdenViewResponse responseView = createReversaView(request.getDescripcion(), request.isCanje());
			estadisticaManager.add(EstadisticasConstants.FINALIZAR_REVERSA_ORDENES_LICITACIONES_CANJE,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(ReversarOrdenViewResponse.class, responseView);
		} else if (EstadoRespuesta.ERROR.equals(response.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.FINALIZAR_REVERSA_ORDENES_LICITACIONES_CANJE,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_REINTENTOS_AGOTADOS,
					CodigoMensajeConstantes.FINALIZAR_REVERSA_LICITACION_CANJE_ERROR, request.getDescripcion());
		} else {
			estadisticaManager.add(EstadisticasConstants.FINALIZAR_REVERSA_ORDENES_LICITACIONES_CANJE,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_REINTENTAR,
					CodigoMensajeConstantes.FINALIZAR_REVERSA_LICITACION_CANJE_ERROR, request.getDescripcion());
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#descargarComprobanteReversaLicitacionPDF(ar.com.santanderrio.
	 * obp.servicios.inversiones.titulos.licitaciones.view.
	 * ComprobanteReversaLicitacion)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteReversaLicitacionCanjePDF(ComprobanteReversaLicitacionCanje viewRequest) {
		Respuesta<Reporte> reporte;
		Respuesta<ReporteView> respuestaView;
		// validarHashSuscripcionComprobante(viewRequest);
		reporte = reporteBO.obtenerComprobantePDF(viewRequest);
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
			estadisticaManager.add(EstadisticasConstants.DESCARGAR_COMPROBANTE_FINALIZAR_REVERSA_CANJE,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			respuestaView = respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
			estadisticaManager.add(EstadisticasConstants.DESCARGAR_COMPROBANTE_FINALIZAR_REVERSA_CANJE,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaView;
	}
	
	@Override
	public Respuesta<FinalizarLicitacionCanjeView> finalizarLicitacionCanjeBPriv(FinalizarLicitacionRequest request) {

		Respuesta<FinalizarLicitacionCanjeView> respuesta = licitacionesBO.finalizarLicitacionCanje(request,
				sesionCliente.getCliente(), BANCA_PRIVADA);
		if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.FINALIZAR_LICITACION_CANJE_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			estadisticaManager.add(EstadisticasConstants.FINALIZAR_LICITACION_CANJE_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuesta;
	}
	
	/**
	 * Grabar estadistica suscripcion comprobante.
	 *
	 * @param banca  the banca
	 * @param estado the estado
	 */
	private void grabarEstadisticaSuscripcionComprobanteCanje(TipoBancaEnum tipoBanca, String estado) {
		if (TipoBancaEnum.BANCA_PRIVADA.equals(tipoBanca)) {
			estadisticaManager.add(EstadisticasConstants.DESCARGAR_COMPROBANTE_CANJE_BPRIV, estado);
		} else {
			estadisticaManager.add(EstadisticasConstants.DESCARGAR_COMPROBANTE_FINALIZAR_SUSCRIPCION_CANJE, estado);
		}
	}
	
	@Override
	public Respuesta<ReversarOrdenViewResponse> reversarOrdenLicitacionCanjeBPriv(ReversaRequestView request) {
		request.setCanje(true);
		Respuesta<ReversarOrdenResponse> response = licitacionesBO.reversarOrdenLicitacion(request,
				sesionCliente.getCliente());

		if (EstadoRespuesta.OK.equals(response.getEstadoRespuesta())) {
			ReversarOrdenViewResponse responseView = createReversaView(request.getDescripcion(), request.isCanje());
			estadisticaManager.add(EstadisticasConstants.FINALIZAR_REVERSA_ORDENES_LICITACIONES_CANJE_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(ReversarOrdenViewResponse.class, responseView);
		} else if (EstadoRespuesta.ERROR.equals(response.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.FINALIZAR_REVERSA_ORDENES_LICITACIONES_CANJE_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_REINTENTOS_AGOTADOS,
					CodigoMensajeConstantes.FINALIZAR_REVERSA_LICITACION_CANJE_ERROR, request.getDescripcion());
		} else {
			estadisticaManager.add(EstadisticasConstants.FINALIZAR_REVERSA_ORDENES_LICITACIONES_CANJE_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_REINTENTAR,
					CodigoMensajeConstantes.FINALIZAR_REVERSA_LICITACION_CANJE_ERROR, request.getDescripcion());
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.
	 * TitulosManager#descargarComprobanteReversaLicitacionPDF(ar.com.santanderrio.
	 * obp.servicios.inversiones.titulos.licitaciones.view.
	 * ComprobanteReversaLicitacion)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteReversaLicitacionCanjePDFBPriv(ComprobanteReversaLicitacionCanje viewRequest) {
		Respuesta<Reporte> reporte;
		Respuesta<ReporteView> respuestaView;
		// validarHashSuscripcionComprobante(viewRequest);
		reporte = reporteBO.obtenerComprobantePDF(viewRequest);
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
			estadisticaManager.add(EstadisticasConstants.DESCARGAR_COMPROBANTE_FINALIZAR_REVERSA_CANJE_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			respuestaView = respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
			estadisticaManager.add(EstadisticasConstants.DESCARGAR_COMPROBANTE_FINALIZAR_REVERSA_CANJE_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaView;
	}
}
