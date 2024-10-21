/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.bo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.extractos.CuentaTituloFirmada;
import ar.com.santanderrio.obp.generated.webservices.extractos.CuentasFirmadasResponse;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaBancaPrivada;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.extracto.dao.ExtractoDAO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.inversiones.comun.MonedaEspecieEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.bo.InversionesAbstractBO;
import ar.com.santanderrio.obp.servicios.inversiones.custodia.view.CuentaCustodiaView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.bo.FondoBO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.OrdenesTitulosDAO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaSuscripcionSaldoPDCRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaSuscripcionSaldoPDCResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CuentasPDC;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.dto.CondicionesGeneralesCuentasCustodiaOrdenVentaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.dto.ConfiguracionOrdenVentaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.dto.PlazoAcreditacionOrdenVentaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.AperturaEspecieResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.ConsultaAperturaEspecieRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.ConsultaAperturaEspecieResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.ListaAperturaEspecieResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.ConfiguracionOrdenVentaInView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.ConfirmacionVentaTitulosInView;
import ar.com.santanderrio.obp.servicios.login.bo.MyaBO;
import ar.com.santanderrio.obp.servicios.login.dto.MyaDTOIn;
import ar.com.santanderrio.obp.servicios.login.dto.MyaDTOOut;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;

/**
 * The Class ConfiguracionOrdenVentaTituloBOImpl.
 */
@Component
public class ConfiguracionOrdenVentaTituloBOImpl extends InversionesAbstractBO
		implements ConfiguracionOrdenVentaTituloBO {

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The legal BO. */
	@Autowired
	private LegalBO legalBO;

	/** The ordenes titulos DAO. */
	@Autowired
	private OrdenesTitulosDAO ordenesTitulosDAO;
	
	/** The extracto DAO. */
	@Autowired
	private ExtractoDAO extractoDAO;

	/** The MyA BO. */
	@Autowired
	private MyaBO myaBO;

	/** The Fondo BO. */
	@Autowired
	private FondoBO fondoBO;
	
	@Autowired
	private ModuloPermisoBO moduloPermisoBO;

	/** The Constant TIPO_OPERACION_VENTA. */
	private static final String TIPO_OPERACION_VENTA = "V";

	/** The Constant MONEDA_PESO. */
	private static final String MONEDA_PESO = "ARS";

	/** The Constant MONEDA_DOLAR. */
	private static final String MONEDA_DOLAR = "USD";

	/** The Constant FORMATO_FECHA_REQUEST. */
	private static final String FORMATO_FECHA_REQUEST = "yyyy-MM-dd";

	/** The Constant HORAS. */
	private static final String HORAS = " hs";

	/** The Constant INMEDIATO. */
	private static final String INMEDIATO = "Inmediato";

	/** The Constant SI. */
	private static final String SI = "S";
	
	/** The Constant SI_2. */
	private static final String SI_2 = "SI";

	/** The Constant OK. */
	private static final String OK = "OK";

	/** The Constant CODIGO_TITULO_BLOQUEADO. */
	private static final String CODIGO_TITULO_BLOQUEADO = "NG";

	/** The Constant CODIGO_FUERA_HORARIO. */
	private static final String CODIGO_FUERA_HORARIO = "FH";
	
	/** The Constant BANCA_PERSONAL. */
	private static final String BANCA_PERSONAL = "BR";

	/** The Constant SEGMENTO_BPRIV. */
	private static final String SEGMENTO_BPRIV = "BP";

	/** The Constant SEGMENTO. */
	private static final String SEGMENTO = "RTL";
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfiguracionOrdenVentaTituloBOImpl.class);
	
	/** The Constant CERO. */
	private static final String CERO = "0";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.comun.bo.
	 * InversionesAbstractBO#getTipo()
	 */
	@Override
	protected String getTipo() {
		return null;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.bo.ConfiguracionOrdenVentaTituloBO#obtenerConfiguracionOrdenVenta(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.ConfiguracionOrdenVentaInView)
	 */
	@Override
	public Respuesta<ConfiguracionOrdenVentaDTO> obtenerConfiguracionOrdenVenta(Cliente cliente,
			ConfiguracionOrdenVentaInView datosEntrada) {
		try {
			ConfiguracionOrdenVentaDTO configuracionDTO = iniciarConfiguracionDTOConLegales(datosEntrada);
			ConsultaAperturaEspecieResponse response = obtenerResponseAperturaEspecie(cliente, datosEntrada);
			if (noTieneNingunPlazoOK(response)) {
				return identificarErrorArmarRespuesta(response);
			}
			completarConfiguracionOrdenVentaDTO(configuracionDTO, response, cliente, datosEntrada);
			if(!datosEntrada.getEsBancaPrivada()) {
				consultarPoderDeCompra(configuracionDTO, response, cliente, datosEntrada);
			}
			if (CollectionUtils.isEmpty(configuracionDTO.getPlazos())) {
				return obtenerRespuestaErrorSinCuentasDestino();
			}
			configuracionDTO.setCondicionesGenerales(obtenerCondicionesGeneralesCuentasCustodiaOrdenVentaDTO(cliente));
			return respuestaFactory.crearRespuestaOk(configuracionDTO);
		} catch (BusinessException e) {
			return obtenerRespuestaErrorDeServicio();
		} catch (DAOException e) {
			return obtenerRespuestaErrorDeServicio();
		}
	}

	private void consultarPoderDeCompra(ConfiguracionOrdenVentaDTO configuracionDTO,
			ConsultaAperturaEspecieResponse response, Cliente cliente, ConfiguracionOrdenVentaInView datosEntrada) {
		
		ConsultaSuscripcionSaldoPDCResponse responsePDC = null;
		ConfirmacionVentaTitulosInView inView = new ConfirmacionVentaTitulosInView();
		inView.setEsBancaPrivada(datosEntrada.getEsBancaPrivada());
		inView.setFechaLiquidacion(response.getDatos().getListaAperturaEspecie().getAperturasEspecie().get(0).getFechaLiquidacion());
		String cuentaTitulo = StringUtils.remove(datosEntrada.getNumeroCuentaTitulo(), "Cuenta títulos ");
		//inView.setCuentaTitulo(ISBANStringUtils.eliminarCeros(cuentaTitulo.replace("/", "")));
		inView.setCuentaTitulo(cuentaTitulo.replace("/", ""));
		
		inView.setTipoEspecie(response.getDatos().getListaAperturaEspecie().getTipoEspecie());
		ConsultaSuscripcionSaldoPDCRequest entity = obtenerDatosParaConsultaSuscripcion(cliente, inView);
 
		try {
			responsePDC = ordenesTitulosDAO.consultaSuscripcionSaldoPDC(entity);
			List<CuentasPDC> listaCuentas = responsePDC.getDatos().getListaCuentas();
			for (CuentasPDC cuenta : listaCuentas) {
				if ("ARS".equals(cuenta.getMoneda())) {
					configuracionDTO.setIndicadorPDCPesos(cuenta.getIndicadorPDC());
				} else {
					configuracionDTO.setIndicadorPDCDolares(cuenta.getIndicadorPDC());
				}
			}
			configuracionDTO.setListaCuentasPDC(listaCuentas);
			configuracionDTO.setPoderCompraOk(true);
		} catch (DAOException e) {
			configuracionDTO.setIndicadorPDCDolares(null);
			configuracionDTO.setIndicadorPDCPesos(null);
			LOGGER.error("Error al consultar ordenesTitulos DAO con ", e);
			return;
		}

	}

	/**
	 * Obtener datos para consulta suscripcion.
	 *
	 * @param cliente
	 *            the cliente
	 * @param confirmacionInView
	 *            the confirmacion in view
	 * @return the consulta suscripcion saldo PDC request
	 */
	private ConsultaSuscripcionSaldoPDCRequest obtenerDatosParaConsultaSuscripcion(Cliente cliente,
			ConfirmacionVentaTitulosInView confirmacionInView) {

		ConsultaSuscripcionSaldoPDCRequest request = new ConsultaSuscripcionSaldoPDCRequest();
		setearCanalesRequestMWCanales(request, confirmacionInView.getEsBancaPrivada());
		setearDatosRequestMWCanales(request.getDatos(), cliente, confirmacionInView.getEsBancaPrivada());
		request.setDatosFirmados(0);
		request.getDatos().setFechaLiquidacion(confirmacionInView.getFechaLiquidacion());
		request.getDatos().setCuentaTitulos(confirmacionInView.getCuentaTitulo().replaceAll("\\/", ""));
//		request.getDatos().setCodigoMoneda(PESOS.equals(confirmacionInView.getMonedaOperacion()) ? "ARS" : "USD");
		request.getDatos().setTipoCtaOper(confirmacionInView.getTipoCuenta());
		request.getDatos().setSucCtaOper(confirmacionInView.getSucursalCuenta());
//		request.getDatos().setNumeroCuentaOper(formatearNroCuenta(confirmacionInView.getNumeroCuenta()));
		request.getDatos().setSegmento(confirmacionInView.getEsBancaPrivada() ? SEGMENTO_BPRIV : SEGMENTO);
		request.getDatos().setProductoInversion(confirmacionInView.getTipoEspecie());
		return request;
	}
	/**
	 * Iniciar configuracion DTO con legales.
	 *
	 * @param datosEntrada
	 *            the datos entrada
	 * @return the configuracion orden venta DTO
	 * @throws BusinessException
	 *             the business exception
	 */
	private ConfiguracionOrdenVentaDTO iniciarConfiguracionDTOConLegales(ConfiguracionOrdenVentaInView datosEntrada)
			throws BusinessException {
		ConfiguracionOrdenVentaDTO configuracionDTO = new ConfiguracionOrdenVentaDTO();

		if (!datosEntrada.getEsBancaPrivada()) {
			Respuesta<String> legalPrecioReferencia = legalBO
					.buscarLegal(CodigoMensajeConstantes.LEGALES_PRECIO_REFERENCIA);
			if (EstadoRespuesta.ERROR.equals(legalPrecioReferencia.getEstadoRespuesta())) {
				throw new BusinessException();
			} else {
				configuracionDTO.setLegalesPrecioReferencia(legalPrecioReferencia.getRespuesta());
			}
		}

		Mensaje mensajePrecioLimite = mensajeBO
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.ORDEN_VENTA_TITULOS_TEXTO_PRECIO_LIMITE);
		Respuesta<String> textoOrigenFondos = legalBO
				.buscarLegal(CodigoMensajeConstantes.ORDEN_COMPRA_VENTA_TITULOS_LEGAL_ORIGEN_FONDOS);
		if (StringUtils.isBlank(mensajePrecioLimite.getMensaje())
				|| textoOrigenFondos.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			throw new BusinessException();
		}
		String mensajePrecioLimiteFormateado = mensajePrecioLimite.getMensaje().replaceAll("<p>", "").replaceAll("</p>",
				"");
		configuracionDTO.setMensajePrecioLimite(mensajePrecioLimiteFormateado);
		configuracionDTO.setTextoOrigenFondos(textoOrigenFondos.getRespuesta());
		return configuracionDTO;
	}

	/**
	 * Obtener consulta apertura especie request entity.
	 *
	 * @param cliente
	 *            the cliente
	 * @param datosEntrada
	 *            the datos entrada
	 * @return the consulta apertura especie request entity
	 */
	private ConsultaAperturaEspecieRequestEntity obtenerConsultaAperturaEspecieRequestEntity(Cliente cliente,
			ConfiguracionOrdenVentaInView datosEntrada) {
		ConsultaAperturaEspecieRequestEntity request = new ConsultaAperturaEspecieRequestEntity();
		setearCanalesRequestMWCanales(request, datosEntrada.getEsBancaPrivada());
		setearDatosRequestMWCanales(request.getDatos(), cliente, datosEntrada.getEsBancaPrivada());
		request.setDatosFirmados(0);
		request.getDatos().setSegmento(datosEntrada.getEsBancaPrivada() ? SEGMENTO_BPRIV : SEGMENTO);
		request.getDatos().setCodigoEspecieRossi(datosEntrada.getCodigoRossi());
		request.getDatos().setFecha(formatearFechaActualParaRequest());
		request.getDatos().setCuentaTitulo(formatearCuentaParaRequest(datosEntrada, cliente));
		request.getDatos().setTipoOperacion(TIPO_OPERACION_VENTA);
		return request;
	}

	/**
	 * Formatear fecha actual para request.
	 *
	 * @return the string
	 */
	private String formatearFechaActualParaRequest() {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA_REQUEST);
		return sdf.format(new Date()) + "T00:00:00-03:00";
	}

	/**
	 * Formatear cuenta para request.
	 *
	 * @param datosEntrada
	 *            the datos entrada
	 * @param cliente
	 *            the cliente
	 * @return the integer
	 */
	private Integer formatearCuentaParaRequest(ConfiguracionOrdenVentaInView datosEntrada, Cliente cliente) {
		String numeroSinBarra = StringUtils.remove(datosEntrada.getNumeroCuentaTitulo(), "/");
		if (datosEntrada.getEsBancaPrivada()) {
			String numeroCuentaTitulo = StringUtils.EMPTY;
			numeroSinBarra = StringUtils.remove(numeroSinBarra, "Cuenta Banca Privada ");
			numeroSinBarra = numeroSinBarra.substring(4, numeroSinBarra.length());
			for (CuentaBancaPrivada cuentaBP : cliente.getCuentasBancaPrivada()) {
				if (cuentaBP.getCuentaOperativa().getNroCuentaProducto().contains(numeroSinBarra)) {
					numeroCuentaTitulo = ISBANStringUtils
							.eliminarCeros(cuentaBP.getCuentaTitulo().getNroCuentaProducto());
				}
			}
			return Integer.valueOf(numeroCuentaTitulo);
		} else {
			numeroSinBarra = StringUtils.remove(numeroSinBarra, "Cuenta títulos ");
			return Integer.valueOf(numeroSinBarra);
		}
	}

	/**
	 * Obtener response apertura especie.
	 *
	 * @param cliente
	 *            the cliente
	 * @param datosEntrada
	 *            the datos entrada
	 * @return the consulta apertura especie response
	 * @throws BusinessException
	 *             the business exception
	 */
	private ConsultaAperturaEspecieResponse obtenerResponseAperturaEspecie(Cliente cliente,
			ConfiguracionOrdenVentaInView datosEntrada) throws BusinessException {
		ConsultaAperturaEspecieRequestEntity request = obtenerConsultaAperturaEspecieRequestEntity(cliente,
				datosEntrada);
		ConsultaAperturaEspecieResponse response = new ConsultaAperturaEspecieResponse();
		try {
			response = ordenesTitulosDAO.consultaAperturaEspecie(request);
		} catch (DAOException e) {
			throw new BusinessException();
		}
		if (response.getCodigo() != 0 || response.getDatos().getListaAperturaEspecie() == null) {
			throw new BusinessException();
		}
		return response;
	}

	/**
	 * Identificar error armar respuesta.
	 *
	 * @param response
	 *            the response
	 * @return the respuesta
	 */
	private Respuesta<ConfiguracionOrdenVentaDTO> identificarErrorArmarRespuesta(
			ConsultaAperturaEspecieResponse response) {
		List<AperturaEspecieResponse> aperturaEspecieResponse = response.getDatos().getListaAperturaEspecie()
				.getAperturasEspecie();
		if (buscarErrorPorCodigo(aperturaEspecieResponse, CODIGO_FUERA_HORARIO)) {
			return obtenerRespuestaErrorFueraDeHorario();
		} else if (buscarErrorPorCodigo(aperturaEspecieResponse, CODIGO_TITULO_BLOQUEADO)) {
			return obtenerRespuestaErrorTituloBloqueado();
		}
		return obtenerRespuestaErrorDeServicio();
	}

	/**
	 * No tiene ningun plazo OK.
	 *
	 * @param response
	 *            the response
	 * @return the boolean
	 */
	private Boolean noTieneNingunPlazoOK(ConsultaAperturaEspecieResponse response) {
		List<AperturaEspecieResponse> aperturasEspecie = response.getDatos().getListaAperturaEspecie()
				.getAperturasEspecie();
		for (AperturaEspecieResponse apertura : aperturasEspecie) {
			if (StringUtils.equals(apertura.getCodHabilitacion(), OK)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Buscar error por codigo.
	 *
	 * @param aperturasEspecie
	 *            the aperturas especie
	 * @param codigoError
	 *            the codigo error
	 * @return the boolean
	 */
	private Boolean buscarErrorPorCodigo(List<AperturaEspecieResponse> aperturasEspecie, String codigoError) {
		for (AperturaEspecieResponse apertura : aperturasEspecie) {
			if (!StringUtils.equals(apertura.getCodHabilitacion(), codigoError)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Obtener respuesta error de servicio.
	 *
	 * @return the respuesta
	 */
	public Respuesta<ConfiguracionOrdenVentaDTO> obtenerRespuestaErrorDeServicio() {
		return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_SERVICIO,
				CodigoMensajeConstantes.ORDEN_VENTA_TITULOS_ERROR_SERVICIO);
	}

	/**
	 * Obtener respuesta error fuera de horario.
	 *
	 * @return the respuesta
	 */
	private Respuesta<ConfiguracionOrdenVentaDTO> obtenerRespuestaErrorFueraDeHorario() {
		return respuestaFactory.crearRespuestaError(null, TipoError.FUERADEHORARIO,
				CodigoMensajeConstantes.ORDEN_VENTA_TITULOS_FUERA_HORARIO);
	}

	/**
	 * Obtener respuesta error titulo bloqueado.
	 *
	 * @return the respuesta
	 */
	private Respuesta<ConfiguracionOrdenVentaDTO> obtenerRespuestaErrorTituloBloqueado() {
		return respuestaFactory.crearRespuestaError(null, TipoError.TITULO_BLOQUEADO,
				CodigoMensajeConstantes.ORDEN_VENTA_TITULOS_BLOQUEADO);
	}

	/**
	 * Obtener respuesta error sin cuentas destino.
	 *
	 * @return the respuesta
	 */
	private Respuesta<ConfiguracionOrdenVentaDTO> obtenerRespuestaErrorSinCuentasDestino() {
		return respuestaFactory.crearRespuestaError(null, TipoError.SIN_CUENTAS_VALIDAS,
				CodigoMensajeConstantes.ORDEN_VENTA_TITULOS_SIN_CUENTA_DESTINO);
	}

	/**
	 * Completar configuracion orden venta DTO.
	 *
	 * @param configuracionDTO
	 *            the configuracion DTO
	 * @param response
	 *            the response
	 * @param cliente
	 *            the cliente
	 * @param datosEntrada
	 *            the datos entrada
	 * @throws BusinessException
	 *             the business exception
	 * @throws DAOException 
	 */
	private void completarConfiguracionOrdenVentaDTO(ConfiguracionOrdenVentaDTO configuracionDTO,
			ConsultaAperturaEspecieResponse response, Cliente cliente, ConfiguracionOrdenVentaInView datosEntrada)
			throws BusinessException, DAOException {
		ListaAperturaEspecieResponse listaResponse = response.getDatos().getListaAperturaEspecie();
		List<Cuenta> cuentas = cliente.getCuentas();
		String descripcionMonedaEmision = MonedaEspecieEnum
				.obtenerDescripcionPorCodigo(listaResponse.getEmisionMonedaEspecie());
		if (descripcionMonedaEmision != null) {
			configuracionDTO.setMoneda(descripcionMonedaEmision);
		} else {
			configuracionDTO.setMoneda(listaResponse.getEmisionMonedaEspecie());
		}
		configuracionDTO.setPlazos(
				obtenerListaDePlazos(listaResponse.getAperturasEspecie(), datosEntrada.getEsBancaPrivada(), cuentas));
		configuracionDTO.setLeyendaLegal(listaResponse.getLeyendaLegal());
		configuracionDTO.setLegalCNV(legalBO.obtenerLegal(CodigoMensajeConstantes.LEGAL_CNV_TITULOS_VALORES));
		if (datosEntrada.getEsBancaPrivada()) {
			completarDatosCuentaOperativa(configuracionDTO, datosEntrada.getNumeroCuentaTitulo(), cliente);
		}
	}

	/**
	 * Completar datos cuenta operativa.
	 *
	 * @param configuracionDTO
	 *            the configuracion DTO
	 * @param numeroCuentaTitulo
	 *            the numero cuenta titulo
	 * @param cliente
	 *            the cliente
	 * @throws BusinessException
	 *             the business exception
	 */
	private void completarDatosCuentaOperativa(ConfiguracionOrdenVentaDTO configuracionDTO, String numeroCuentaTitulo,
			Cliente cliente) throws BusinessException {
		Cuenta cuenta = obtenerCuentaBancaPrivada(numeroCuentaTitulo, cliente);
		Respuesta<CuentasAdhesionDebitoView> respuestaSaldoCuentaOperativa = fondoBO.obtenerSaldosCuentaOperativa(
				"7" + StringUtils.right(cuenta.getNroCuentaProducto(), 9), cliente, false);
		if (respuestaSaldoCuentaOperativa.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			throw new BusinessException();
		}
		configuracionDTO.setCuentaOperativa(respuestaSaldoCuentaOperativa.getRespuesta());
	}

	/**
	 * Obtener cuenta banca privada.
	 *
	 * @param numeroCuentaTitulo
	 *            the numero cuenta titulo
	 * @param cliente
	 *            the cliente
	 * @return the cuenta
	 */
	private Cuenta obtenerCuentaBancaPrivada(String numeroCuentaTitulo, Cliente cliente) {

		Cuenta cuenta = new Cuenta();
		String numeroSinBarra = StringUtils.remove(numeroCuentaTitulo, "/");
		numeroSinBarra = StringUtils.remove(numeroSinBarra, "Cuenta Banca Privada ");
		numeroSinBarra = numeroSinBarra.substring(4, numeroSinBarra.length());

		for (Cuenta cuentaPrivada : cliente.getCuentasPrivadas()) {
			if (cuentaPrivada.getNroCuentaProducto().contains(numeroSinBarra)) {
				cuenta = cuentaPrivada;
			}
		}

		return cuenta;
	}

	/**
	 * Rellenar listas cuentas por moneda.
	 *
	 * @param cuentas
	 *            the cuentas
	 * @param cuentasPesos
	 *            the cuentas pesos
	 * @param cuentasDolares
	 *            the cuentas dolares
	 */
	private void rellenarListasCuentasPorMoneda(List<Cuenta> cuentas, List<Cuenta> cuentasPesos,
			List<Cuenta> cuentasDolares) {
		for (Cuenta cuenta : cuentas) {
			if (cuenta.isCuentaPesos()
					|| TipoCuenta.CUENTA_UNICA.getCodigo() == Integer.parseInt(cuenta.getTipoCuenta())) {
				cuentasPesos.add(cuenta);
			}
			if (cuenta.isCuentaDolares()
					|| TipoCuenta.CUENTA_UNICA.getCodigo() == Integer.parseInt(cuenta.getTipoCuenta())) {
				cuentasDolares.add(cuenta);
			}
		}
	}

	/**
	 * Obtener lista de plazos.
	 *
	 * @param aperturasEspecie
	 *            the aperturas especie
	 * @param esBancaPrivada
	 *            the es banca privada
	 * @param cuentas
	 *            the cuentas
	 * @return the list
	 * @throws BusinessException
	 *             the business exception
	 */
	private List<PlazoAcreditacionOrdenVentaDTO> obtenerListaDePlazos(List<AperturaEspecieResponse> aperturasEspecie,
			Boolean esBancaPrivada, List<Cuenta> cuentas) throws BusinessException {
		List<PlazoAcreditacionOrdenVentaDTO> plazos = new ArrayList<PlazoAcreditacionOrdenVentaDTO>();
		Integer contadorId = 1;
		for (AperturaEspecieResponse aperturaEspecie : aperturasEspecie) {
			if (StringUtils.equals(aperturaEspecie.getCodHabilitacion(), OK)) {
				Boolean esPesos = esPlazoAcreditacionEnPesos(aperturaEspecie.getMonedaNegociacion());
				if (validaMonedaCuenta(esBancaPrivada, cuentas, esPesos)) {
					continue;
				}
				PlazoAcreditacionOrdenVentaDTO plazo = obtenerPlazoDTO(contadorId, aperturaEspecie, esPesos);
				plazos.add(plazo);
				contadorId++;
			}
		}
		return plazos;
	}

	/**
	 * Es plazo acreditacion en pesos.
	 *
	 * @param monedaNegociacion
	 *            the moneda negociacion
	 * @return the boolean
	 * @throws BusinessException
	 *             the business exception
	 */
	private Boolean esPlazoAcreditacionEnPesos(String monedaNegociacion) throws BusinessException {
		if (StringUtils.equals(monedaNegociacion, MONEDA_PESO)) {
			return true;
		} else if (StringUtils.equals(monedaNegociacion, MONEDA_DOLAR)) {
			return false;
		} else {
			throw new BusinessException();
		}
	}

	/**
	 * Valida moneda cuenta.
	 *
	 * @param esBancaPrivada
	 *            the es banca privada
	 * @param cuentas
	 *            the cuentas
	 * @param esPesos
	 *            the es pesos
	 * @return the boolean
	 */
	private Boolean validaMonedaCuenta(Boolean esBancaPrivada, List<Cuenta> cuentas, Boolean esPesos) {
		if (!esBancaPrivada) {
			List<Cuenta> cuentasPesos = new ArrayList<Cuenta>();
			List<Cuenta> cuentasDolares = new ArrayList<Cuenta>();
			rellenarListasCuentasPorMoneda(cuentas, cuentasPesos, cuentasDolares);
			Boolean poseeCuentasPesos = CollectionUtils.isNotEmpty(cuentasPesos);
			Boolean poseeCuentasDolares = CollectionUtils.isNotEmpty(cuentasDolares);
			return (esPesos && !poseeCuentasPesos) || (!esPesos && !poseeCuentasDolares);
		}
		return false;
	}

	/**
	 * Obtener plazo DTO.
	 *
	 * @param contadorId
	 *            the contador id
	 * @param aperturaEspecie
	 *            the apertura especie
	 * @param esPesos
	 *            the es pesos
	 * @return the plazo acreditacion orden venta DTO
	 * @throws BusinessException
	 *             the business exception
	 */
	private PlazoAcreditacionOrdenVentaDTO obtenerPlazoDTO(Integer contadorId, AperturaEspecieResponse aperturaEspecie,
			Boolean esPesos) throws BusinessException {
		PlazoAcreditacionOrdenVentaDTO plazo = new PlazoAcreditacionOrdenVentaDTO();
		plazo.setId(contadorId);
		plazo.setNombre(
				aperturaEspecie.getPlazo() == 0 ? INMEDIATO : String.valueOf(aperturaEspecie.getPlazo()) + HORAS);
		plazo.setTenenciaNegociable(aperturaEspecie.getTenenciaNominalNegociable());
		plazo.setPrecioReferencia(aperturaEspecie.getCotizacionEspecie());
		plazo.setFechaReferencia(formatearFecha(aperturaEspecie.getFechaCotizacionEspecie()));
		plazo.setFechaDeLiquidacion(aperturaEspecie.getFechaLiquidacion());
		plazo.setHoraReferencia(aperturaEspecie.getHoraCotizacionEspecie());
		plazo.setNominalMinimoValue(aperturaEspecie.getCantidadMinimaNegociable());
		plazo.setNominalMaximoValue(aperturaEspecie.getCantidadMaximaNegociable());
		plazo.setNominalIncrementoValue(aperturaEspecie.getCantidadMinimoIncremento());
		plazo.setEsPesos(esPesos);
		plazo.setPrecioMinimoValue(aperturaEspecie.getPrecioLimiteMinimo());
		plazo.setPrecioMaximoValue(aperturaEspecie.getPrecioLimiteMaximo());
		plazo.setPrecioIncrementoValue(aperturaEspecie.getPrecioLimiteIncremento());
		plazo.setRequierePrecioLimite(StringUtils.equals(aperturaEspecie.getRequierePrecioLimite(), SI));
		return plazo;
	}

	/**
	 * Formatear fecha.
	 *
	 * @param fechaServicio
	 *            the fecha servicio
	 * @return the string
	 * @throws BusinessException
	 *             the business exception
	 */
	private String formatearFecha(String fechaServicio) throws BusinessException {
		try {
			if (fechaServicio == null) {
				return null;
			}
			SimpleDateFormat sdfIn = new SimpleDateFormat(FORMATO_FECHA_REQUEST);
			Date fechaDate = sdfIn.parse(StringUtils.left(fechaServicio, 10));
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd/MM/yyyy");
			return sdfOut.format(fechaDate);
		} catch (ParseException e) {
			throw new BusinessException();
		}
	}

	/**
	 * Obtener condiciones generales cuentas custodia orden venta DTO.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the condiciones generales cuentas custodia orden venta DTO
	 * @throws BusinessException
	 *             the business exception
	 */
	private CondicionesGeneralesCuentasCustodiaOrdenVentaDTO obtenerCondicionesGeneralesCuentasCustodiaOrdenVentaDTO(
			Cliente cliente) throws BusinessException {
		CondicionesGeneralesCuentasCustodiaOrdenVentaDTO condicionesCC = new CondicionesGeneralesCuentasCustodiaOrdenVentaDTO();
		if (moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.VENTAS_SIN_VALIDAR_FIRMAS).tienePermisoDeVisibilidad()) {
			completarListasCuentasFirmadasSinValidacion(cliente, condicionesCC);
		} else {
			completarListasCuentasFirmadas(cliente, condicionesCC);
		}
		completarConLegales(condicionesCC);
		completarConMail(condicionesCC, cliente);
		return condicionesCC;
	}

	/**
	 * Completar listas cuentas firmadas sin llamar al servicio de validacion de firmas.
	 *
	 * @param cliente
	 *            the cliente
	 * @param condicionesCC
	 *            the condiciones CC
	 * @throws BusinessException
	 *             the business exception
	 */
	private void completarListasCuentasFirmadasSinValidacion(Cliente cliente,
			CondicionesGeneralesCuentasCustodiaOrdenVentaDTO condicionesCC) throws BusinessException {
		List<CuentaCustodiaView> cuentasFirmadas = new ArrayList<CuentaCustodiaView>();
		List<CuentaCustodiaView> cuentasSinFirmar = new ArrayList<CuentaCustodiaView>();
		
		
		for (Cuenta cuentaTituloEntity : cliente.getCuentasRetail()) {
			CuentaCustodiaView cuenta = new CuentaCustodiaView();
			cuenta.setNumeroCuenta(cuentaTituloEntity.getNroCuentaProducto());
			cuenta.setSucursal(StringUtils.leftPad(cuentaTituloEntity.getNroSucursal(), LARGO_COD_SUCURSAL, CERO));
				cuentasFirmadas.add(cuenta);
		}
		condicionesCC.setCuentasFirmadasSinValidacion(cuentasFirmadas);
		condicionesCC.setCuentasSinFirmarSinValidacion(cuentasSinFirmar);
		condicionesCC.setCondicionesAceptadas(CollectionUtils.isEmpty(cuentasSinFirmar));
		condicionesCC.setComprobantesDisponibles(CollectionUtils.isNotEmpty(cuentasFirmadas));
	}
	
	/**
	 * Completar listas cuentas firmadas.
	 *
	 * @param cliente
	 *            the cliente
	 * @param condicionesCC
	 *            the condiciones CC
	 * @throws BusinessException
	 *             the business exception
	 */
	private void completarListasCuentasFirmadas(Cliente cliente,
			CondicionesGeneralesCuentasCustodiaOrdenVentaDTO condicionesCC) throws BusinessException {

		CuentasFirmadasResponse outEntity = obtenerResponseCuentasFirmadas(cliente);

		List<Cuenta> cuentasFirmadas = new ArrayList<Cuenta>();
		List<Cuenta> cuentasSinFirmar = new ArrayList<Cuenta>();
		for (CuentaTituloFirmada cuentaTituloEntity : outEntity.getListaCuentas().getValue().getCuentaTituloFirmada()) {
			Cuenta cuenta = new Cuenta();
			cuenta.setNroCuentaProducto(cuentaTituloEntity.getAtit().getValue());
			cuenta.setNroSucursal(cuentaTituloEntity.getSuc().getValue());
			if (StringUtils.equals(SI_2, cuentaTituloEntity.getFirmada().getValue())) {
				cuentasFirmadas.add(cuenta);
			} else {
				cuentasSinFirmar.add(cuenta);
			}
		}
		condicionesCC.setCuentasFirmadas(cuentasFirmadas);
		condicionesCC.setCuentasSinFirmar(cuentasSinFirmar);
		condicionesCC.setCondicionesAceptadas(CollectionUtils.isEmpty(cuentasSinFirmar));
		condicionesCC.setComprobantesDisponibles(CollectionUtils.isNotEmpty(cuentasFirmadas));
	}

	/**
	 * Obtener response cuentas firmadas.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the cuentas firmadas response
	 * @throws BusinessException
	 *             the business exception
	 */
	private CuentasFirmadasResponse obtenerResponseCuentasFirmadas(Cliente cliente) throws BusinessException {
		try {
			return extractoDAO.firmasCuentasPorNup(cliente.getNup(), BANCA_PERSONAL);
		} catch (DAOException e) {
			throw new BusinessException();
		}
	}

	/**
	 * Completar con legales.
	 *
	 * @param condicionesCC
	 *            the condiciones CC
	 * @throws BusinessException
	 *             the business exception
	 */
	private void completarConLegales(CondicionesGeneralesCuentasCustodiaOrdenVentaDTO condicionesCC)
			throws BusinessException {
		Respuesta<String> legalIntro = legalBO
				.buscarLegal(CodigoMensajeConstantes.ORDEN_VENTA_TITULOS_LEGAL_CONDICIONES_GENERALES);
		Respuesta<String> legalCondiciones = legalBO
				.buscarLegal(CodigoMensajeConstantes.ORDEN_VENTA_TITULOS_LEGAL_CONDICIONES_GENERALES_2);
		Respuesta<String> legal3 = legalBO
				.buscarLegal(CodigoMensajeConstantes.ORDEN_VENTA_TITULOS_LEGAL_CONDICIONES_GENERALES_3);
		if (!legalIntro.getEstadoRespuesta().equals(EstadoRespuesta.OK)
				|| !legalCondiciones.getEstadoRespuesta().equals(EstadoRespuesta.OK)
				|| !legal3.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			throw new BusinessException();
		}
		condicionesCC.setIntro(legalIntro.getRespuesta());
		condicionesCC.setCondiciones(legalCondiciones.getRespuesta());
		condicionesCC.setLegal(legal3.getRespuesta());
	}

	/**
	 * Completar con mail.
	 *
	 * @param condicionesCC
	 *            the condiciones CC
	 * @param cliente
	 *            the cliente
	 * @throws BusinessException
	 *             the business exception
	 */
	private void completarConMail(CondicionesGeneralesCuentasCustodiaOrdenVentaDTO condicionesCC, Cliente cliente)
			throws BusinessException {
		if (!condicionesCC.getCondicionesAceptadas()) {
			Respuesta<String> legalMail = legalBO
					.buscarLegal(CodigoMensajeConstantes.ORDEN_VENTA_TITULOS_LEGAL_CONDICIONES_GENERALES_4);
			MyaDTOOut responseMya = myaBO.consultaWsEstadoCliente(cliente, new MyaDTOIn());
			if (responseMya.getEmail() == null || !legalMail.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
				throw new BusinessException();
			}
			condicionesCC.setLegalMail(legalMail.getRespuesta());
			condicionesCC.setMail(responseMya.getEmail());
		}
	}

}