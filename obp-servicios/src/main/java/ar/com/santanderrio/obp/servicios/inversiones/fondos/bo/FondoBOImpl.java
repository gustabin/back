package ar.com.santanderrio.obp.servicios.inversiones.fondos.bo;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.generated.webservices.extractos.MovFondos;
import ar.com.santanderrio.obp.generated.webservices.extractos.MovFondosResponse;
import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.api.common.config.ErrorResponse;
import ar.com.santanderrio.obp.servicios.api.funds.FundsApi;
import ar.com.santanderrio.obp.servicios.api.funds.entities.holdings.HoldingsData;
import ar.com.santanderrio.obp.servicios.api.funds.entities.holdings.Holdings;
import ar.com.santanderrio.obp.servicios.api.funds.entities.holdings.HoldingsResponse;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.contrato.bo.ContratoBO;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.CampoEnum;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.SinonimoEnum;
import ar.com.santanderrio.obp.servicios.comun.excel.dao.ReporteDAO;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.extracto.dao.ExtractoDAO;
import ar.com.santanderrio.obp.servicios.factory.DatoItemMensaje;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.TipoMonedaInversionEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.EnumFondosDisponiblesTipoOperacion;
import ar.com.santanderrio.obp.servicios.inversiones.comun.InversionWSHelper;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.bo.InversionesAbstractBO;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.dao.CuentaSaldoDAO;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.dao.RtaLoadSaldo;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.CuentaSaldoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.CuentaSaldoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.custodia.dao.CustodiaDAO;
import ar.com.santanderrio.obp.servicios.inversiones.custodia.entity.CustodiaInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.custodia.entity.CustodiaOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.custodia.entity.Tenencia;
import ar.com.santanderrio.obp.servicios.inversiones.exception.CargaTenenciaException;
import ar.com.santanderrio.obp.servicios.inversiones.exception.FondoSinMovimientosException;
import ar.com.santanderrio.obp.servicios.inversiones.exception.ImporteMayorAlMaximoException;
import ar.com.santanderrio.obp.servicios.inversiones.exception.ImporteMenorAlMinimoException;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.bancaprivada.dao.FondoBPrivDAO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dao.FondoDAO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dao.TenenciaValuadaDAO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.*;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.*;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.excel.FciCuentaTitulosExcel;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.excel.FciExcel;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.excel.FciGeneralExcel;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.excel.FciTotalesCuenta;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.FondoEspecie;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.RescateFondoEspecie;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.prototype.FondosTenenciasPrototype;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.*;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;
import ar.com.santanderrio.obp.servicios.tarjetas.exceptions.SimulacionDAOException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The Class FondoBOImpl.
 *
 * @author
 */
@Component("fondoBO")
public class FondoBOImpl extends InversionesAbstractBO implements FondoBO {

	/** The Constant ESTADISTICA_SUSCRIPCION_FONDO_BPRIV. */
	private static final String ESTADISTICA_SUSCRIPCION_FONDO_BPRIV = "18005";

	/** The Constant PREFIJO_COD_CLIENTE. */
	private static final String PREFIJO_COD_CLIENTE = "001";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(FondoBOImpl.class);

	/** The Constant ESTADISTICA_OK. */
	private static final String ESTADISTICA_OK = "1";

	/** The Constant ESTADISTICA_NOK. */
	private static final String ESTADISTICA_NOK = "2";

	/** The Constant ESTADISTICA_PARCIAL. */
	private static final String ESTADISTICA_PARCIAL = "9";

	/** The Constant SUSCRIPCION_CORRECTA. */
	private static final String SUSCRIPCION_CORRECTA = "10416";

	/** The Constant FINALIZAR_SUSCRIPCION_FALLO_GENERICO. */
	public static final String FINALIZAR_SUSCRIPCION_FALLO_GENERICO = "10417";

	/** The Constant BANCA_RETAIL. */
	private static final String BANCA_RETAIL = "BR";

	/** The Constant AMBAS_BANCAS. */
	private static final String AMBAS_BANCAS = "RP";

	/** The Constant LARGO_COD_FONDO. */
	private static final int LARGO_COD_FONDO = 3;

	/** The Constant FALLA_TENENCIA_FONDOS. */
	private static final String FALLA_TENENCIA_FONDOS = "10420";

	/** The Constant SUSCRIPCION_FONDO. */
	private static final String SUSCRIPCION_FONDO = "18001";

	/** The Constant OK_CODIGO_RETORNO. */
	private static final String OK_CODIGO_RETORNO = "00000000";

	/** The Constant LARGO_IMPORTE_BRUTO. */
	private static final int LARGO_IMPORTE_BRUTO = 14;

	/** The Constant LARGO_CUENTA_DEBITO. */
	private static final int LARGO_CUENTA_DEBITO = 2;

	/** The Constant LARGO_CUENTA_TITULO. */
	private static final int LARGO_CUENTA_TITULO = 8;

	/** The Constant LARGO_TIPO_MONEDA. */
	private static final int LARGO_TIPO_MONEDA = 2;

	/** The Constant CERO_STRING. */
	private static final String CERO_STRING = "0";

	/** The Constant CUATRO_ESPACIOS. */
	private static final String CUATRO_ESPACIOS = "    ";

	/** The Constant CUATRO_CONSTANTE. */
	private static final String CUATRO_CONSTANTE = "4";

	/** The Constant CERO_DIEZ_CONSTANTE. */
	private static final String CERO_DIEZ_CONSTANTE = "0000000000";

	/** The Constant CERO_CATORCE_CONTANTE. */
	private static final String CERO_CATORCE_CONTANTE = "00000000000000";

	/** The Constant LEGAL_TERMINOS_COND_SUSCRIPCION. */
	private static final String LEGAL_TERMINOS_COND_SUSCRIPCION = "10002";

	/** The Constant MENSAJE_LEGALES_CONFIRMACION_BANCA_PERSONAL. */
	private static final String MENSAJE_LEGALES_CONFIRMACION_BANCA_PERSONAL = "50015";

	/** The Constant MENSAJE_LEGALES_REGLAMENTO_GESTION. */
	private static final String MENSAJE_LEGALES_REGLAMENTO_GESTION = "10014";

	/** The Constant TIPO_BANCA_PERSONAL. */
	private static final String TIPO_BANCA_PERSONAL = "BR";

	/**
	 * The Constant MONEDA_PESOS. como devuelve el servicio PLtenenciaValuada
	 */
	private static final String MONEDA_PESOS = "Pesos";

	/**
	 * The Constant MONEDA_DOLARES como devuelve el servicio PLtenenciaValuada.
	 */
	private static final String MONEDA_DOLARES = "Dolares";

	/** The Constant ARS. */
	private static final String ARS = "ARS";

	/** The Constant CODIGO_OK. */
	private static final Integer CODIGO_OK = 0;

	/** The Constant CODIGO_OK. */
	private static final Integer CODIGO_WARNING = 1;

	/** The Constant CODIGO_AGENTE_CONSTANTE. */
	private static final String CODIGO_AGENTE_CONSTANTE = "001";

	/**
	 * The Constant limite de caractes que tomara para el parseo de Valor
	 * Coutaparte.
	 */
	private static final int LIMITE_MAXIMO_CARACTERES_VC = 15;

	/** The Constant ERROR_CUENTA_SIN_TENENCIA. */
	public static final String ERROR_CUENTA_SIN_TENENCIA = "10474";

	/** The Constant MENSAJE_CUENTA_BLOQUEADA. */
	public static final String MENSAJE_CUENTA_BLOQUEADA = "10476";

	/** The constant CUENTA_NO_BLOQUEADA. */
	public static final String CUENTA_NO_BLOQUEADA = "0";

	/** The constant OPERACION_BLOQUEADA. */
	public static final String OPERACION_BLOQUEADA = "0";

	/** The Constant FONDO_EXCITI. */
	private static final String FONDO_EXCITI = "1";

	/** The Constant ASTERISCO. */
	private static final String ASTERISCO = " (*)";

	/** The Constant DOS_ASTERISCOS. */
	private static final String DOS_ASTERISCOS = " (**)";

	/** The Constant CODIGO_FONDO_54. */
	private static final String CODIGO_FONDO_54 = "054";

	/** The Constant CODIGO_FONDO_41. */
	private static final String CODIGO_FONDO_41 = "041";

	/** The Constant CODIGO_FONDO_42. */
	private static final String CODIGO_FONDO_42 = "042";

	/** The Constant LARGO_COD_FONDO_FCI. */
	private static final int LARGO_COD_FONDO_FCI = 3;

	/** The Constant LARGO_COD_FONDO_FDC. */
	private static final int LARGO_COD_FONDO_FDC = 4;
	public static final String ID_SUPERFONDO_EQUILIBRADO = "100";
	public static final String RENDIMIENTO_NEUTRAL = "000000+";

	/** The Constants for holdings - tipo cuenta **/
	private static final String ALTAIR_PRODUCT_03 = "03";
	private static final String TIPO_CUENTA_PREFIJO_300 = "300";
	private static final String TIPO_CUENTA_PREFIJO_700 = "700";
	private static final String SUSCRIPCION = "suscripcion";
	private static final String ERROR = "ERROR";

	/** The Constants for segments **/
	private static final String BP = "BP";
	private static final String RTL = "RTL";

	/** The tenencia map. */
	Map<String, TenenciaPorFondoDTO> tenenciaMap = new HashMap<String, TenenciaPorFondoDTO>();

	/** maximo dias para busqueda de movimientos por importe. */
	@Value("${INVERSIONES.MOVIMIENTOS.LIMITE}")
	private int maxDias;

	/** URL cartera para factsheet fondos. */
	@Value("${INVERSIONES.FONDOS.CARTERA.URL}")
	private String urlCartera;

	/** dao. */
	@Autowired
	private FondoDAO fondoDAO;

	/** The extracto DAO. */
	@Autowired
	private ExtractoDAO extractoDAO;

	/** The custodia DAO. */
	@Autowired
	private CustodiaDAO custodiaDAO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The contratosBO. */
	@Autowired
	private ContratoBO contratosBO;

	/** The mensaje bo. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The session parametros. */
	@Autowired
	private SesionParametros sessionParametros;

	/** The cuenta saldo DAO. */
	@Autowired
	private CuentaSaldoDAO cuentaSaldoDAO;

	/** The fondo B priv DAO. */
	@Autowired
	private FondoBPrivDAO fondoBPrivDAO;

	/** obtener los legales. */
	@Autowired
	private LegalBO legalBO;

	/** The tenencia valuada. */
	@Autowired
	private TenenciaValuadaDAO tenenciaValuadaDAO;

	/** The mensaje manager. */
	@Autowired
	private MensajeManager mensajeManager;

	@Autowired
	private ReporteDAO reporteDAO;

	/** The rescate Fondo Especie. */
	@Autowired
	private RescateFondoEspecie rescateFondoEspecie;
	
	@Autowired
	private InversionWSHelper inversionWSHelper;

	@Autowired
	private FondosTenenciasPrototype fondosTenenciasPrototype;

	/** The ModuloPermiso BO. */
	@Autowired
	private ModuloPermisoBO moduloPermisoBO;

	/** The funds client*/
	@Autowired
	private FundsApi fundsApi;

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	protected String getTipo() {
		return EnumFondosDisponiblesTipoOperacion.SU.getCodigo();
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<FondoDTO> simularSuscripcion(FondoDTO requestDTO, Cliente cliente) {

		try {
			SuscripcionFondoInEntity entity = generarRequestCNSSUSFCI(requestDTO, cliente);
			SuscripcionFondoOutEntity respuestaDAO = fondoDAO.suscribir(entity);
			if (OK_CODIGO_RETORNO.equals(respuestaDAO.getCodigoRetornoExtendido())) {
				return respuestaOKSimularSuscripcion(requestDTO);
			}

		} catch (FueraDeHorarioException e) {
			LOGGER.error("Error Fuera de horario. ", e);
			return respuestaFactory.crearRespuestaWarning(new FondoDTO(), "", TipoError.FUERADEHORARIO,
					CodigoMensajeConstantes.CODIGO_MENSAJE_FUERA_HORARIO_FONDOS);
		} catch (BusinessException e) {
			LOGGER.error("Error convirtiendo view en DTO. ", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} catch (ImporteMayorAlMaximoException e) {
			LOGGER.error("Error Importe Mayor al Maximo. ", e);
			return respuestaFactory.crearRespuestaWarning("", TipoError.ERROR_LIMITE_MAXIMO, "");
		} catch (ImporteMenorAlMinimoException e) {
			LOGGER.error("Error Importe Mayor al Maximo. ", e);
			return respuestaFactory.crearRespuestaWarning("", TipoError.ERROR_LIMITE_MINIMO, "");
		} catch (ServicioDeshabilitadoException e) {
			LOGGER.error("Error Servicio Deshabilitado. ", e);
			return respuestaFactory.crearRespuestaError("", TipoError.SERVICIO_FONDOS_DESHABILITADO,
					CodigoMensajeConstantes.ERROR_SERVICIO_FONDOS_DESHABILITADO);
		}

		catch (DAOException e) {
			LOGGER.error("Error en fondoDAO metodo suscribir. ", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
	}

	/**
	 * manejo la respuesta OK, del servicio simular suscripcion.
	 *
	 * @param requestDTO the request DTO
	 * @return the respuesta
	 * @throws BusinessException the business exception
	 */
	private Respuesta<FondoDTO> respuestaOKSimularSuscripcion(FondoDTO requestDTO) throws BusinessException {
		FondoDTO dtoResponse = new FondoDTO();
		dtoResponse.setUrlReglamento(armarUrlReglamento(requestDTO.getCodigoFondo()));
		Respuesta<String> respuestaTerminosYCondiciones = legalBO.buscarLegal(LEGAL_TERMINOS_COND_SUSCRIPCION);
		Respuesta<String> respuestaLegales = legalBO.buscarLegal(MENSAJE_LEGALES_CONFIRMACION_BANCA_PERSONAL);
		if (respuestaLegales.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)
				|| respuestaTerminosYCondiciones.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} else if (respuestaLegales.getEstadoRespuesta().equals(EstadoRespuesta.OK)
				&& respuestaTerminosYCondiciones.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			dtoResponse.setLegales(respuestaLegales.getRespuesta());
			dtoResponse.setTerminosYCondiciones(respuestaTerminosYCondiciones.getRespuesta());
		}
		return respuestaFactory.crearRespuestaOk(FondoDTO.class, dtoResponse);
	}

	
	@Override
	public String obtenerReglamento(String codFondo) throws BusinessException {
		String urlReglamento = armarUrlReglamento(codFondo);
		
		return urlReglamento;
	}

	@Override
	public Boolean validarHorarioDeFondo(String horarioFondo) {
		return isFondoFueraHorario(horarioFondo);
	}

	@Override
	public List<DatoItemMensaje> crearItemsWarningFueraHorarioAgendarFondo() {
		return crearItemsFueraHorarioAgendarFondo();
	}

	/**
	 * Generar request CNSSUSFCI.
	 *
	 * @param requestDTO the request DTO
	 * @param cliente    the cliente
	 * @return the suscripcion fondo in entity
	 * @throws BusinessException the business exception
	 */
	private SuscripcionFondoInEntity generarRequestCNSSUSFCI(FondoDTO requestDTO, Cliente cliente)
			throws BusinessException {
		try {
			SuscripcionFondoInEntity requestEntity = new SuscripcionFondoInEntity();
			requestEntity.setCliente(cliente);
			requestEntity.setCodigoFondo(requestDTO.getCodigoFondo());
			requestEntity.setCodigoCliente(PREFIJO_COD_CLIENTE
					+ StringUtils.leftPad(requestDTO.getCuentaTitulo().replace("/", ""), LARGO_CUENTA_TITULO, "0"));
			String importeBruto = StringUtils.replaceChars(requestDTO.getImporte().trim(), ".", "");
			importeBruto = StringUtils.replaceChars(importeBruto, ",", "");
			requestEntity.setImporteBruto(StringUtils.leftPad(importeBruto, LARGO_IMPORTE_BRUTO, "0"));

			TipoMonedaInversionEnum moneda = TipoMonedaInversionEnum.fromSimboloString(requestDTO.getMoneda());
			requestEntity.setMoneda(moneda.getCodigoNumerico().substring(1));

			requestEntity.setMoneda(
					TipoMonedaInversionEnum.fromSimboloString(requestDTO.getMoneda()).getCodigoNumerico().substring(1));

			if (requestDTO.getTipoCtaDeb().equals(TipoCuenta.CUENTA_UNICA.getAbreviatura())) {
				if (requestDTO.getMoneda().equals(TipoMonedaInversionEnum.USD.getSimbolo())) {
					requestEntity.setTipoCuentaDebito(StringUtils
							.leftPad(TipoCuenta.CUENTA_UNICA_DOLARES.getCodigo().toString(), LARGO_CUENTA_DEBITO, "0"));
				} else {
					requestEntity.setTipoCuentaDebito(StringUtils
							.leftPad(TipoCuenta.CUENTA_UNICA_PESOS.getCodigo().toString(), LARGO_CUENTA_DEBITO, "0"));
				}
			} else {
				requestEntity.setTipoCuentaDebito(StringUtils.leftPad(
						TipoCuenta.fromAbreviatura(requestDTO.getTipoCtaDeb()).getCodigo().toString(),
						LARGO_CUENTA_DEBITO, "0"));
			}
			requestEntity.setSucursalCuentaDebito(requestDTO.getSucursalCtaDeb());
			requestEntity.setNumeroCuentaDebito(requestDTO.getNumeroCtaDeb().replace("/", ""));
			return requestEntity;
		} catch (Exception ex) {
			LOGGER.error("Error generando entity request para CNSSUSFCI. ", ex);
			throw new BusinessException();
		}
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<FondoDTO> suscribir(FondoDTO dtoRequest, Cliente cliente) {

		SuscripcionFondoInEntity inDAO = new SuscripcionFondoInEntity();
		inDAO.setCliente(cliente);
		Respuesta<FondoDTO> respuesta = null;
		try {
			fondoDAO.suscribir(inDAO);
			respuesta = respuestaFactory.crearRespuestaOk(FondoDTO.class, new FondoDTO());
		} catch (DAOException daoe) {
			respuesta = respuestaFactory.crearRespuestaError(FondoDTO.class, null);
			LOGGER.error("Error al consultar DAO con", inDAO, daoe);
		}
		return respuesta;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<CuentasConsultaFondoDTO> obtenerFondosSuscriptosYDisponibles(CuentasConsultaFondoDTO requestDTO,
			Cliente cliente, boolean fondoDisponibles, boolean esUltimosMovimientos) {
		
		Date fecha = new Date();
		LOGGER.info("CACHE_DIAS_NO_HABILES: Consulta al metodo");
		boolean esFeriado = inversionWSHelper.esFeriado(fecha);
		
		if ((!validarHorarioFondos() || esFinDeSemana() || esFeriado) && !esUltimosMovimientos) {
			LOGGER.error("Los fondos no se encuentran dentro del horario de inicio ({} hs.) y fin ({} hs.) de operatoria.",
					horarioDesdeOperarFondos, horarioHastaOperarFondos);
			return respuestaFactory.crearRespuestaWarning(crearItemsFueraHorarioAgendarFondo(), new CuentasConsultaFondoDTO());
		}
		

		//INYECTO CUENTAS DE REPATRIACION EN LAS CUENTAS TITULO DEL CLIENTE
		
		requestDTO=inyectarCuentasTitRep(requestDTO,cliente);
		
		// OBTENGO FONDOS TOTALES DISPONIBLES
		List<FondoResumidoDTO> fondosResumidosDTO;
		try {
			fondosResumidosDTO = obtenerFondosDisponibles(requestDTO.getTipoBanca(), fondoDisponibles,
					cliente.getIsExCiti());
		} catch (DAOException e1) {
			grabarEstadistica(SUSCRIPCION_FONDO, ESTADISTICA_NOK, cliente);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		CuentasConsultaFondoDTO returnDTO = new CuentasConsultaFondoDTO();
		returnDTO.setFondosTotales(fondosResumidosDTO);

		// SETEO TIPO BANCA Y MENSAJE INFORMACION
		returnDTO.setTipoBanca(requestDTO.getTipoBanca());
		Mensaje mensajeInformacion = mensajeBO
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.TRANSFERENCIA_INFORMACION_GO_TO);
		returnDTO.setMensajeInformacion(mensajeInformacion.getMensaje());
		Mensaje textoBuscador = mensajeBO
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_BUSCADOR_NO_ENCONTRADO);
		returnDTO.setTextoBuscador(textoBuscador.getMensaje());

		ConsultaTenenciaFCIInEntity requestEntity = genererRequestEntity(requestDTO, false);

		try {
			fondosTenenciasPrototype.setEstadisticaConsultaTenenciaFCI(cliente);
			ConsultaTenenciaFCIOutEntity retornoFCIDAO = fondoDAO.consultaTenenciaFCI(cliente, requestEntity);

			List<ConsultaTenenciaFCIEntity> listaTenencia = new ArrayList<ConsultaTenenciaFCIEntity>();
			listaTenencia.addAll(retornoFCIDAO.getListaTenencia());

			returnDTO.setCuentasTitulo(entityToDTO(listaTenencia, fondosResumidosDTO, requestDTO));
		} catch (DAOException e) {
			fondosTenenciasPrototype.cleanCacheConsultaTenenciaFCI(cliente);
			returnDTO.setCuentasTitulo(requestDTO.getCuentasTitulo());
			grabarEstadistica(SUSCRIPCION_FONDO, ESTADISTICA_PARCIAL, cliente);
			return respuestaFactory.crearRespuestaWarning(returnDTO, "", TipoError.WARNING, FALLA_TENENCIA_FONDOS);
		} catch (BusinessException e) {
			fondosTenenciasPrototype.cleanCacheConsultaTenenciaFCI(cliente);
			returnDTO.setCuentasTitulo(requestDTO.getCuentasTitulo());
			grabarEstadistica(SUSCRIPCION_FONDO, ESTADISTICA_PARCIAL, cliente);
			return respuestaFactory.crearRespuestaWarning(returnDTO, "", TipoError.WARNING, FALLA_TENENCIA_FONDOS);
		}
		grabarEstadistica(SUSCRIPCION_FONDO, ESTADISTICA_OK, cliente);
		return respuestaFactory.crearRespuestaOk(CuentasConsultaFondoDTO.class, returnDTO);
	}

	private InicioFondoDTO inyectarCuentasTitRepRTL(InicioFondoDTO inicioFondoDTO, Cliente cliente,String tipoBanca) {
		List<Cuenta> ctasTitRep=new ArrayList<Cuenta>();
		
		if(tipoBanca.equals("BR")) {
			
			ctasTitRep=cliente.getCuentasTitRtlRepatriacion();
			List<CuentaTituloDTO> ListaDTO=inicioFondoDTO.getCuentasBancaPersonal();
			
			for(Cuenta ctaTitRep : ctasTitRep) {
				CuentaTituloDTO ctaDto=new CuentaTituloDTO();
				ctaDto.setIntervinientes(ctaTitRep.getIntervinientes());
				ctaDto.setNroCuenta(ctaTitRep.getNroCuentaProducto());
				int numeroCta=Integer.parseInt(ctaTitRep.getNroCuentaProducto());
				String nroCuenta=numeroCta+"";
				ctaDto.setNroCuentaFormateado(nroCuenta.substring(0, nroCuenta.length() - 1) + "/"
			            + nroCuenta.substring(nroCuenta.length() - 1, nroCuenta.length()));
				ctaDto.setRepatriacion(true);
				ctaDto.setSucursal("000");
				
				String sucOp=ctaTitRep.getSucursalCtaOpRep();
				String ctaOP=ctaTitRep.getCuentaOPRepatriacion()+"";
				
				String ctaFormateada = ISBANStringUtils.formateadorConCerosIzq(ctaOP, 7);
				ctaDto.setCuentaOperativaRep(sucOp+"-"+ctaFormateada.substring(0,(ctaFormateada.length()-1))+
						"/"+ctaFormateada.substring(ctaFormateada.length()-1,ctaFormateada.length()));
				
				
				ListaDTO.add(ctaDto);
			}
			inicioFondoDTO.setCuentasBancaPersonal(ListaDTO);
			
		}
		
		return inicioFondoDTO;
		
		
	}
	
	private CuentasConsultaFondoDTO inyectarCuentasTitRep(CuentasConsultaFondoDTO requestDTO, Cliente cliente) {
	
		List<Cuenta> ctasTitRep=new ArrayList<Cuenta>();	
		if(requestDTO.getTipoBanca().equals("BR")) {
			ctasTitRep=cliente.getCuentasTitRtlRepatriacion();
		}else {
			ctasTitRep=cliente.getCuentasTitBPRepatriacion();
		}
		
		List<CuentaTituloDTO> ListaDTO=requestDTO.getCuentasTitulo();
		
		for(Cuenta ctaTitRep : ctasTitRep) {
			CuentaTituloDTO ctaDto=new CuentaTituloDTO();
			ctaDto.setIntervinientes(ctaTitRep.getIntervinientes());
			ctaDto.setNroCuenta(ctaTitRep.getNroCuentaProducto());
			int nroCta=Integer.parseInt(ctaTitRep.getNroCuentaProducto());
			String nroCuenta=nroCta+"";
			ctaDto.setNroCuentaFormateado(nroCuenta.substring(0, nroCuenta.length() - 1) + "/"
		            + nroCuenta.substring(nroCuenta.length() - 1, nroCuenta.length()));
			ctaDto.setRepatriacion(true);
			
			String nroCuentaOp=ctaTitRep.getCuentaOPRepatriacion()+"";
			String sucursalOp=ctaTitRep.getSucursalCtaOpRep();
			
			String ctaFormateada = ISBANStringUtils.formateadorConCerosIzq(nroCuentaOp, 7);
			
			ctaDto.setCuentaOperativa(sucursalOp+"-"+ctaFormateada.substring(0,ctaFormateada.length()-1)+
					"/"+ctaFormateada.substring(ctaFormateada.length()-1,ctaFormateada.length()));

						
			ctaDto.setSucursal("000");
			ListaDTO.add(ctaDto);
		}
		
		requestDTO.setCuentasTitulo(ListaDTO);
		return requestDTO;
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<CuentasConsultaFondoDTO> obtenerFondosSuscriptosYDisponiblesBPriv(
			CuentasConsultaFondoDTO requestDTO, Cliente cliente, boolean fondoDisponibles) {
		
		Date fecha = new Date();
		LOGGER.info("CACHE_DIAS_NO_HABILES: Consulta al metodo");
		boolean esFeriado = inversionWSHelper.esFeriado(fecha);
		
		if (!validarHorarioFondos() || esFinDeSemana() || esFeriado) {
			LOGGER.error("Los fondos no se encuentran dentro del horario de inicio ({} hs.) y fin ({} hs.) de operatoria.",
					horarioDesdeOperarFondos, horarioHastaOperarFondos);
			return respuestaFactory.crearRespuestaWarning(crearItemsFueraHorarioAgendarFondo(), new CuentasConsultaFondoDTO());
		}
		// OBTENGO FONDOS DISPONIBLES
		List<FondoResumidoDTO> fondosResumidosDTO;
		try {
			fondosResumidosDTO = obtenerFondosDisponibles(requestDTO.getTipoBanca(), fondoDisponibles,
					cliente.getIsExCiti());
		} catch (DAOException e1) {
			grabarEstadistica(ESTADISTICA_SUSCRIPCION_FONDO_BPRIV, ESTADISTICA_NOK, cliente);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		// SETEO TIPO BANCA Y MENSAJE INFORMACION
		CuentasConsultaFondoDTO returnDTO = new CuentasConsultaFondoDTO();
		returnDTO.setTipoBanca(requestDTO.getTipoBanca());
		Mensaje mensajeInformacion = mensajeBO
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.TRANSFERENCIA_INFORMACION_GO_TO);
		returnDTO.setMensajeInformacion(mensajeInformacion.getMensaje());
		Mensaje textoBuscador = mensajeBO
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_BUSCADOR_NO_ENCONTRADO);
		returnDTO.setTextoBuscador(textoBuscador.getMensaje());

		// OBTENGO FONDOS SUSCRIPTOS
		try {
			returnDTO.setCuentasTitulo(obtenerRelacionOperativaTitulo(cliente));
			returnDTO=setRepatriacion(returnDTO,cliente);
			Predicate filtroSuscripcion = new Predicate() {
				@Override
				public boolean evaluate(Object object) {
					ConsultaFondoOutEntity fondo = (ConsultaFondoOutEntity) object;
					return "1".equals(fondo.getHabilitarSuscripcion());
				}
			};
			cargarTenencia(returnDTO.getCuentasTitulo(), filtroSuscripcion);
			returnDTO.setFondosTotales(fondosResumidosDTO);
		} catch (CargaTenenciaException e) {
			returnDTO.setFondosTotales(fondosResumidosDTO);
			grabarEstadistica(ESTADISTICA_SUSCRIPCION_FONDO_BPRIV, ESTADISTICA_PARCIAL, cliente);
			return respuestaFactory.crearRespuestaWarning(returnDTO, "", TipoError.WARNING, FALLA_TENENCIA_FONDOS);
		} catch (BusinessException e) {
			grabarEstadistica(ESTADISTICA_SUSCRIPCION_FONDO_BPRIV, ESTADISTICA_NOK, cliente);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		grabarEstadistica(ESTADISTICA_SUSCRIPCION_FONDO_BPRIV, ESTADISTICA_OK, cliente);
		return respuestaFactory.crearRespuestaOk(CuentasConsultaFondoDTO.class, returnDTO);
	}

	private CuentasConsultaFondoDTO setRepatriacion(CuentasConsultaFondoDTO returnDTO, Cliente cliente) {
		
		List<Cuenta> ctasTitRep=cliente.getCuentasTitBPRepatriacion();
		List<CuentaTituloDTO> cuentasDTO=returnDTO.getCuentasTitulo();
		for(Cuenta ctaTitRep : ctasTitRep) {
				
			for(CuentaTituloDTO ctaBP: cuentasDTO) {
				if(ctaTitRep.getCuentaOPRepatriacion()==Integer.parseInt(ctaBP.getCuentaOperativa().replaceAll("/", ""))) {
					ctaBP.setRepatriacion(true);
				}
			}
			
		}
		
		return returnDTO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void cargarTenencia(List<CuentaTituloDTO> list, Predicate condicion) throws CargaTenenciaException {

		for (CuentaTituloDTO cuentaTitulo : list) {

			List<FondoResumidoDTO> fondosSuscriptos = new ArrayList<FondoResumidoDTO>();
			CustodiaInEntity custodiaInEntity = new CustodiaInEntity();

			custodiaInEntity.setCuenta(cuentaTitulo.getCuentaOperativaSinFormatear());
			CustodiaOutEntity outLoadCustodiaOl;
			try {
				outLoadCustodiaOl = custodiaDAO.verTenenciaOl(custodiaInEntity);
				for (Tenencia tenencia : outLoadCustodiaOl.getListaTenencia()) {
					FondoResumidoDTO fondoSuscripto = new FondoResumidoDTO();
					ConsultaFondoOutEntity respuestaFondo = consultaFondoDAO.obtenerPorCodigo(
							StringUtils.leftPad(tenencia.getEspecieCodigo().toString(), LARGO_COD_FONDO, CERO_STRING));
					if (condicion.evaluate(respuestaFondo)) {
						fondoSuscripto = entityToDTO(respuestaFondo);
						fondoSuscripto
								.setMoneda(TipoMonedaInversionEnum
										.fromCodigoNumericoString(StringUtils.leftPad(
												tenencia.getMonedaTipo().toString(), LARGO_TIPO_MONEDA, CERO_STRING))
										.getSimbolo());
						fondoSuscripto.setSaldo(
								ISBANStringUtils.formatearConComaYDosDecimales(tenencia.getSaldoBruto().toString()));
						fondoSuscripto.setCodigoFondo(respuestaFondo.getCodigoFondo());
						fondoSuscripto.setPlazoEfectivo(respuestaFondo.getPlazoEfectivo());
						fondoSuscripto.setFondoNuevo(
								rescateFondoEspecie.getFondosMap().containsKey(respuestaFondo.getCodigoFondo()));
						if (fondoSuscripto.isFondoNuevo()) {
							FondoEspecie fondoEspecie = rescateFondoEspecie.getFondosMap()
									.get(respuestaFondo.getCodigoFondo());
							LOGGER.info("Se opera con el fondo obtenido del yaml {}", fondoEspecie);
							fondoSuscripto.setSoloEspecie(fondoEspecie.getOperacion());
						} else {
							LOGGER.debug("El fondo consultado en el yaml {} no es nuevo",
									respuestaFondo.getCodigoFondo());
						}
						fondosSuscriptos.add(fondoSuscripto);
					}
				}
				Collections.sort(fondosSuscriptos);
				cuentaTitulo.setFondosSuscriptos(fondosSuscriptos);
			} catch (DAOException e) {
				LOGGER.error("Error al obtener la tenencia de los fondos. ", e);
				throw new CargaTenenciaException();
			} catch (BusinessException e) {
				LOGGER.error("Error al obtener la tenencia de los fondos. ", e);
				throw new CargaTenenciaException();
			}
		}
		return;
	}

	/**
	 * Obtiene el listado de fondos habilitados para la suscripcion y graba
	 * estadistica NO OK en caso de falla.
	 *
	 * @param tipoBanca        the tipo banca
	 * @param fondoDisponibles the fondo disponibles
	 * @param isClienteExCiti  the isClienteExCiti
	 * @return the list
	 * @throws DAOException the DAO exception
	 */
	private List<FondoResumidoDTO> obtenerFondosDisponibles(String tipoBanca, boolean fondoDisponibles,
			Boolean isClienteExCiti) throws DAOException {
		List<FondoResumidoDTO> fondosResumidosDTO = null;
		fondosResumidosDTO = entityToDTOs(consultaFondoDAO.obtenerPorBanca(tipoBanca), fondoDisponibles,
				isClienteExCiti);
		return fondosResumidosDTO;
	}

	/**
	 * Entity to DT os.
	 *
	 * @param consultasFondos  the consultas fondos
	 * @param fondoDisponibles the fondo disponibles
	 * @param isClienteExCiti  the isClienteExCiti
	 * @return the list
	 */
	private List<FondoResumidoDTO> entityToDTOs(List<ConsultaFondoOutEntity> consultasFondos, boolean fondoDisponibles,
			Boolean isClienteExCiti) {
		List<FondoResumidoDTO> fondosResumidosDTO = new ArrayList<FondoResumidoDTO>();
		if (fondoDisponibles) {
			for (ConsultaFondoOutEntity consultaFondos : consultasFondos) {
				if ("1".equals(consultaFondos.getHabilitarSuscripcion())
						&& aplicaFondo(consultaFondos, isClienteExCiti)) {
					FondoResumidoDTO fondoResumidoDTO = cargarFondoDTO(consultaFondos);
					fondosResumidosDTO.add(fondoResumidoDTO);
				}
			}
		} else {
			for (ConsultaFondoOutEntity consultaFondos : consultasFondos) {
				if (aplicaFondo(consultaFondos, isClienteExCiti)) {
					FondoResumidoDTO fondoResumidoDTO = cargarFondoDTO(consultaFondos);
					fondosResumidosDTO.add(fondoResumidoDTO);
				}
			}
		}
		return fondosResumidosDTO;
	}

	/**
	 * Aplica fondo.
	 *
	 * @param consultaFondos  the consulta fondos
	 * @param isClienteExCiti the isClienteExCiti
	 * @return true, if successful
	 */
	private boolean aplicaFondo(ConsultaFondoOutEntity consultaFondos, Boolean isClienteExCiti) {
		return (consultaFondos.getTipoBanca().equalsIgnoreCase(BANCA_RETAIL)
				|| consultaFondos.getTipoBanca().equalsIgnoreCase(AMBAS_BANCAS))
				&& ((isClienteExCiti != null && isClienteExCiti.booleanValue())
						|| !FONDO_EXCITI.equalsIgnoreCase(consultaFondos.getExCiti()));
	}

	/**
	 * Metodo que devuelve un DTO con las cuentas titulos, extrayendolas del
	 * requestDTO y las completa con los fondos asociados a cada una de ellas que
	 * vinieron del dao de CNSTENVAL_.
	 *
	 * @param listaTenencia      the respuesta DAO
	 * @param fondosResumidosDTO the fondos resumidos DTO
	 * @param requestDTO         the request DTO
	 * @return the cuentas consulta fondo DTO
	 * @throws BusinessException the business exception
	 */
	private List<CuentaTituloDTO> entityToDTO(List<ConsultaTenenciaFCIEntity> listaTenencia,
			List<FondoResumidoDTO> fondosResumidosDTO, CuentasConsultaFondoDTO requestDTO) throws BusinessException {

		CuentaTituloDTO cuentaTitDTO = null;
		// RECORRO TODOS LOS FONDOS DEVUELTOS
		for (ConsultaTenenciaFCIEntity tenencia : listaTenencia) {

			// ASIGNO EL FONDO ACTUAL A LA "cuentaTitDTO" CORRESPONDIENTE
			cuentaTitDTO = requestDTO.getCuentaByNumero(tenencia.getNroCuenta());

			if (cuentaTitDTO != null) {

				if (cuentaTitDTO.getFondosSuscriptos() == null) {
					cuentaTitDTO.setFondosSuscriptos(new ArrayList<FondoResumidoDTO>());
				}

				FondoResumidoDTO fondoResumido = new FondoResumidoDTO();
				for (FondoResumidoDTO fondo : fondosResumidosDTO) {
					if (StringUtils.right(tenencia.getEspecieCodigo(), LARGO_COD_FONDO)
							.equals(fondo.getCodigoFondo())) {
						if ("1".equals(fondo.getHabilitarSuscripcion())
								&& (fondo.getTipoBanca().equalsIgnoreCase(BANCA_RETAIL)
										|| fondo.getTipoBanca().equalsIgnoreCase(AMBAS_BANCAS))) {
							fondoResumido = fondo.clone();
							fondoResumido.setMoneda(TipoMonedaInversionEnum
									.fromCodigoNumericoString(tenencia.getMonedaTipo()).getSimbolo());
							fondoResumido.setSaldo(
									ISBANStringUtils.formatearSaldosConCerosYSignos(tenencia.getTeneciaValuada()));
							fondoResumido.setFondoNuevo(
									rescateFondoEspecie.getFondosMap().containsKey(fondo.getCodigoFondo()));
							cuentaTitDTO.getFondosSuscriptos().add(fondoResumido);
						}
					}
				}
				Collections.sort(cuentaTitDTO.getFondosSuscriptos());
			}
		}
		return requestDTO.getCuentasTitulo();
	}

	/**
	 * Entity to DTO.
	 *
	 * @param consultaFondos the consultar fondos
	 * @return the fondo resumido DTO
	 * @throws BusinessException the business exception
	 */
	private FondoResumidoDTO entityToDTO(ConsultaFondoOutEntity consultaFondos) throws BusinessException {

		FondoResumidoDTO fondoResumidoDTO = new FondoResumidoDTO();
		fondoResumidoDTO.setImporteMaximo(consultaFondos.getLimiteMaximoSuscripcion());
		fondoResumidoDTO.setImporteMinimo(consultaFondos.getLimiteMinimoSuscripcion());
		fondoResumidoDTO.setNombreFondo(consultaFondos.getNombreFondo());
		fondoResumidoDTO.setIdMensajeGastos(consultaFondos.getIdMensajeGastos());
		fondoResumidoDTO.setCodigoFondo(consultaFondos.getCodigoFondo());
		fondoResumidoDTO.setCodigoAgrupador(consultaFondos.getCodigoAgrupador());
		fondoResumidoDTO.setOrdenAgrupacion(consultaFondos.getOrdenAgrupacion());

		return fondoResumidoDTO;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<Boolean> obtenerMarcaContrato(Cliente cliente) throws BusinessException {
		LOGGER.info("Obteniendo marca de aceptacion para suscripcion a un fondo, Paso 2: ");
		String resultado = null;
		try {
			resultado = contratosBO.buscarContratoAceptadoOld(cliente.getFechaNacimiento(), cliente.getDni(),
					CampoEnum.CPI_FCI_C, SinonimoEnum.NO_REPETIDO);
		} catch (DAOException e) {
			LOGGER.error("Error consultando store contrato ", e);
			return respuestaFactory.crearRespuestaError(Boolean.class, "Error de BD", null);
		}
		return respuestaFactory.crearRespuestaOk(Boolean.class, new Boolean("1".equals(resultado)));
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<Boolean> aceptarContrato(Cliente cliente) throws BusinessException {
		LOGGER.info("aceptacion para suscripcion a un fondo, Paso 2: ");
		String resultado = null;
		try {
			resultado = contratosBO.confirmarAceptacionContratoOld(cliente.getFechaNacimiento(), cliente.getDni(),
					CampoEnum.CPI_FCI_C, SinonimoEnum.NO_REPETIDO);
		} catch (DAOException e) {
			LOGGER.error("Error consultando store contrato ", e);
			throw new BusinessException(e);
		}
		return respuestaFactory.crearRespuestaOk(Boolean.class, new Boolean("OK".equals(resultado)));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<TenenciasFondoDTO> obtenerTenencias(TipoBancaDTO tipoBanca, Cliente cliente) {
		TenenciasFondoDTO tenencias = new TenenciasFondoDTO();
		List<CuentaTituloDTO> cuentasDTO = new ArrayList<CuentaTituloDTO>();
		InicioFondoDTO inicioFondoDTO = generarCuentasDTO(cliente);
		inicioFondoDTO=inyectarCuentasTitRepRTL(inicioFondoDTO,cliente,tipoBanca.getTipoBanca());
		String segmento = null;

		if (TIPO_BANCA_PERSONAL.equals(tipoBanca.getTipoBanca())) {
			cuentasDTO = generarCuentasRTLTenencias(inicioFondoDTO.getCuentasBancaPersonal());
			segmento = Segmento.BANCA_PERSONAL.getCodigo();
		} else {
			try {
				cuentasDTO = obtenerRelacionOperativaTitulo(cliente);
				cuentasDTO = inyectarCuentasTitRepBP(cuentasDTO,cliente);
			} catch (BusinessException e) {
				LOGGER.error("Error al cargar las cuentas titulo. ", e);
			}
			segmento = Segmento.BANCA_PRIVADA.getCodigo();
		}

		List<ConsultaFondoOutEntity> fondos = new ArrayList<ConsultaFondoOutEntity>();
		DetalleTenenciaValuadaEntity tenenciaValuada = new DetalleTenenciaValuadaEntity();
		Boolean holdingsByBff = false;

		try {
			fondos = consultaFondoDAO.obtenerFondos();
		} catch (DAOException e){
			LOGGER.error("Error en consultaFondoDao al obtener fondos", e);
		}

		holdingsByBff = fundsApi.verifyAccessToGetHolding(cliente);

		if (holdingsByBff.equals(Boolean.TRUE)) {
			try {
				LOGGER.info("Se procede a consultar FONDOS-TENENCIA-BFF para tipo de cliente {}", segmento);
				tenenciaValuada = obtenerTenenciaValuadaHoldingBff(cliente, segmento);
			} catch (ApiException e) {
				grabarEstadistica(EstadisticasConstants.CONSUMO_FONDOS_TENENCIA_BFF, ESTADISTICA_PARCIAL, cliente);
				holdingsByBff = false;
			}
		}

		if (holdingsByBff.equals(Boolean.FALSE)) {
			LOGGER.info("Se procede a consultar el servicio de PyL como contingencia para {}", segmento);
			try {
				DetalleFondoRequestEntity detalleFondoRequestEntity =
						createRequestServiceTenencias(cuentasDTO, cliente, segmento);
				// Obtener tenencia valuada por banca (RTL - BP)
				tenenciaValuada = tenenciaValuadaDetalleFondoOnlinePorBanca(tipoBanca, detalleFondoRequestEntity);
			} catch (DAOException e) {
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
		}

		try {
			tenencias = obtenerCuentasConTenencias(cuentasDTO, fondos, tenenciaValuada);
		} catch (DAOException e) {
			LOGGER.error("Error Fondo no existe en la tabla", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		tenencias = obtenerMensajes(tenencias);
		sessionParametros.setTenenciasFondoDTO(tenencias);

		if (tenenciaValuada.getCodigo() == 0) {
			return respuestaFactory.crearRespuestaOk(tenencias);
		}

		return respuestaFactory.crearRespuestaWarning(tenencias, new ArrayList<ItemMensajeRespuesta>());
	}

	private DetalleTenenciaValuadaEntity tenenciaValuadaDetalleFondoOnlinePorBanca(
			TipoBancaDTO tipoBanca,
			DetalleFondoRequestEntity detalleFondoRequestEntity) throws DAOException {
		DetalleTenenciaValuadaEntity tenenciaValuada;
		if (TIPO_BANCA_PERSONAL.equals(tipoBanca.getTipoBanca())) {
			tenenciaValuada = tenenciaValuadaDAO.obtenerTenenciaValuadaDetalleFondoOnline(
					detalleFondoRequestEntity);
		} else {
			tenenciaValuada = tenenciaValuadaDAO.obtenerTenenciaValuadaDetalleFondoOnlineBP(
					detalleFondoRequestEntity);
		}

		return tenenciaValuada;
	}

	private List<CuentaTituloDTO> inyectarCuentasTitRepBP(List<CuentaTituloDTO> cuentasDTO, Cliente cliente) {
		
		List<Cuenta> ctasTitRep=cliente.getCuentasTitBPRepatriacion();
		
		for(Cuenta ctaTitRep : ctasTitRep) {
				
			for(CuentaTituloDTO ctaBP: cuentasDTO) {
				if(ctaTitRep.getCuentaOPRepatriacion()==Integer.parseInt(ctaBP.getCuentaOperativa().replaceAll("/", ""))) {
					ctaBP.setRepatriacion(true);
				}
			}
			
		}

		return cuentasDTO;
	}

	/**
	 * Setear datos entrada suscripcion fondo.
	 *
	 * @param requestDTO the request DTO
	 * @return the comprobante suscripcion fondo in entity
	 * @throws BusinessException the business exception
	 */
	private ComprobanteSuscripcionFondoInEntity setearDatosEntradaSuscripcionFondo(FondoDTO requestDTO)
			throws BusinessException {
		try {
			ComprobanteSuscripcionFondoInEntity requestEntity = new ComprobanteSuscripcionFondoInEntity();
			requestEntity.setTerminalSafe(CUATRO_ESPACIOS);
			requestEntity.setCodigoFondo(requestDTO.getCodigoFondo());
			requestEntity.setCodigoCliente(PREFIJO_COD_CLIENTE
					+ StringUtils.leftPad(requestDTO.getCuentaTitulo().replace("/", ""), LARGO_CUENTA_TITULO, "0"));
			String importeBruto = StringUtils.replaceChars(requestDTO.getImporte(), ".", "");
			importeBruto = StringUtils.replaceChars(importeBruto, ",", "");
			requestEntity.setImporteBruto(StringUtils.leftPad(importeBruto, LARGO_IMPORTE_BRUTO, "0"));
			if (requestDTO.getTipoCtaDeb().equals(TipoCuenta.CUENTA_UNICA.getAbreviatura())) {
				if (requestDTO.getMoneda().equals(TipoMonedaInversionEnum.USD.getSimbolo())) {
					requestEntity.setTipoCuentaDebito(StringUtils
							.leftPad(TipoCuenta.CUENTA_UNICA_DOLARES.getCodigo().toString(), LARGO_CUENTA_DEBITO, "0"));
				} else {
					requestEntity.setTipoCuentaDebito(StringUtils
							.leftPad(TipoCuenta.CUENTA_UNICA_PESOS.getCodigo().toString(), LARGO_CUENTA_DEBITO, "0"));
				}
			} else {
				requestEntity.setTipoCuentaDebito(StringUtils.leftPad(
						TipoCuenta.fromAbreviatura(requestDTO.getTipoCtaDeb()).getCodigo().toString(),
						LARGO_CUENTA_DEBITO, "0"));
			}
			requestEntity.setSucursalCuentaDebito(requestDTO.getSucursalCtaDeb());
			requestEntity.setNumeroCuentaDebito(requestDTO.getNumeroCtaDeb().replace("/", ""));
			requestEntity.setCodigoCustodiaActual(CUATRO_CONSTANTE);
			requestEntity.setDiaClearingCheques(CERO_STRING);
			TipoMonedaInversionEnum moneda = TipoMonedaInversionEnum.fromSimboloString(requestDTO.getMoneda());
			requestEntity.setMoneda(moneda.getCodigoNumerico().substring(1));
			requestEntity.setNroCertificReversar(CERO_DIEZ_CONSTANTE);
			requestEntity.setMontoReversar(CERO_CATORCE_CONTANTE);
			return requestEntity;

		} catch (Exception ex) {
			LOGGER.error("Error generando entity request para SuscripcionFondo. ", ex);
			throw new BusinessException();
		}
	}

	/**
	 * Cargar datos.
	 *
	 * @param requestDTO   the request DTO
	 * @param respuestaDAO the respuesta DAO
	 * @return the fondo DTO
	 */
	public FondoDTO cargarDatos(FondoDTO requestDTO, SuscripcionFondoOutEntity respuestaDAO) {
		FondoDTO dtoResponse = new FondoDTO();
		String moneda = requestDTO.getMoneda();
		String saldo = requestDTO.getImporte();
		dtoResponse.setImporte(" " + moneda + " " + saldo);
		String mensaje = mensajeBO.obtenerMensajePorCodigo(SUSCRIPCION_CORRECTA).getMensaje();
		String mensajeSuscripcion = MessageFormat.format(mensaje, requestDTO.getNombreFondo(),
				dtoResponse.getImporte());
		dtoResponse.setMensajeSuscripcion(mensajeSuscripcion);
		dtoResponse.setNumeroCertificado(respuestaDAO.getNumeroCertificado().toString());
		dtoResponse.setFechaHora(ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date()));
		return dtoResponse;

	}

	/**
	 * Manejar reintento.
	 *
	 * @param requestDTO        the request DTO
	 * @param permiteReintentar the permite reintentar
	 * @param e                 the e
	 * @return the respuesta
	 */
	Respuesta<FondoDTO> manejarReintento(FondoDTO requestDTO, boolean permiteReintentar, DAOException e) {
		Respuesta<FondoDTO> respuesta;
		LOGGER.error("Error en fondoDAO metodo finalizarSuscribirFondos. ", e);
		String mensaje = mensajeBO.obtenerMensajePorCodigo(FINALIZAR_SUSCRIPCION_FALLO_GENERICO).getMensaje();
		String mensajeError = MessageFormat.format(mensaje, requestDTO.getNombreFondo());
		if (permiteReintentar) {
			respuesta = respuestaFactory.crearRespuestaError(FondoDTO.class, mensajeError,
					TipoError.ERROR_FINALIZAR_SUSCRIPCION_FONDO_CON_REINTENTO.toString());

		} else {
			respuesta = respuestaFactory.crearRespuestaError(FondoDTO.class, mensajeError,
					TipoError.ERROR_FINALIZAR_SUSCRIPCION_FONDO_SIN_REINTENTO.toString());
		}
		return respuesta;
	}

	/**
	 * Manejar caso feliz.
	 *
	 * @param requestDTO   the request DTO
	 * @param respuestaDAO the respuesta DAO
	 * @return the respuesta
	 */
	Respuesta<FondoDTO> manejarCasoFeliz(FondoDTO requestDTO, SuscripcionFondoOutEntity respuestaDAO) {
		FondoDTO dtoResponse = cargarDatos(requestDTO, respuestaDAO);
		return respuestaFactory.crearRespuestaOk(FondoDTO.class, dtoResponse);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<FondoDTO> finalizarSuscribirFondos(FondoDTO requestDTO, Cliente cliente) {
		boolean permiteReintentar;
		String segmento = Segmento.BANCA_PERSONAL.getCodigo();

		if (sessionParametros.getContador() != null) {
			permiteReintentar = sessionParametros.getContador().permiteReintentar();
		} else {
			LOGGER.debug("Error, Contador no inicializado!!");
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		try {
			fondosTenenciasPrototype.cleanCacheObtenerTenenciaValuadaDetalleFondoOnline(cliente, segmento);
			ComprobanteSuscripcionFondoInEntity entity = setearDatosEntradaSuscripcionFondo(requestDTO);
			SuscripcionFondoOutEntity respuestaDAO = fondoDAO.comprobanteSuscripcionFondo(cliente, entity);
			if (OK_CODIGO_RETORNO.equals(respuestaDAO.getCodigoRetornoExtendido())) {
				cleanCacheHoldingBff(requestDTO.getCuentaTitulo(), cliente, segmento);
				return manejarCasoFeliz(requestDTO, respuestaDAO);
			}
		} catch (FueraDeHorarioException e) {
			LOGGER.error("Error Fuera de horario. ", e);
			return respuestaFactory.crearRespuestaWarning(new FondoDTO(), "", TipoError.FUERADEHORARIO,
					CodigoMensajeConstantes.CODIGO_MENSAJE_FUERA_HORARIO_FONDOS);
		} catch (SaldoInsuficienteException ex) {
			LOGGER.error("Error en BO saldo insuficiente. ", ex);
			return respuestaFactory.crearRespuestaWarning("", TipoError.ERROR_SALDO_INSUFICIENTE,
					CodigoMensajeConstantes.FINALIZAR_SUSCRIPCION_SALDO_INSUFICIENTE);
		} catch (TimeOutException exc) {
			LOGGER.error("Error en BO timeOut. ", exc);
			String mensaje = mensajeBO.obtenerMensajePorCodigo(FINALIZAR_SUSCRIPCION_FALLO_GENERICO).getMensaje();
			String mensajeError = MessageFormat.format(mensaje, requestDTO.getNombreFondo());
			return respuestaFactory.crearRespuestaError(FondoDTO.class, mensajeError,
					TipoError.ERROR_FINALIZAR_SUSCRIPCION_FONDO_SIN_REINTENTO.toString());

		} catch (CuentaSinOperarException e) {
			LOGGER.error("Error en BO 180 dias cuentasinOperar. ", e);
			return manejarReintento(requestDTO, permiteReintentar, e);

		} catch (DAOException e) {
			return manejarReintento(requestDTO, permiteReintentar, e);
		} catch (BusinessException e) {
			LOGGER.error("Error convirtiendo view en DTO. ", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);

	}

	/**
	 * Recupero el saldo de la cuenta opertiva y luego la busco en las cuentas que
	 * me retorno identificacion de cliente para obtener el tipo de cuenta.
	 * 
	 * requiereSaldo es true devuelvo saldo con guiones cuando el servico falle, de
	 * lo contrario cuando es false devuelve un error generado por intentar iterar
	 * una lista null que posteriormente devuelve como error de servicio.
	 *
	 * @param nroCuenta     the nro cuenta
	 * @param cliente       the cliente
	 * @param requiereSaldo the requiere saldo
	 * @return the respuesta
	 */
	@Override
	public Respuesta<CuentasAdhesionDebitoView> obtenerSaldosCuentaOperativa(String nroCuenta, Cliente cliente,
			boolean requiereSaldo) {
		CuentaSaldoInEntity cuentaSaldoInEntity = new CuentaSaldoInEntity();
		CuentasAdhesionDebitoView cuentasAdhesionDebitoView = new CuentasAdhesionDebitoView();
		cuentaSaldoInEntity.setCuenta(nroCuenta);
		cuentaSaldoInEntity.setPass(cliente.getPasswordRacf());
		cuentaSaldoInEntity.setUsuario(cliente.getUsuarioRacf());
		CuentaSaldoOutEntity rta = cuentaSaldoDAO.verSaldos(cuentaSaldoInEntity);
		if (rta == null) {
			LOGGER.error("Error obteniendo el saldo de la cuenta operativa");
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		// siempre es una porq consulto el saldo de una cuenta
		RtaLoadSaldo saldoCuenta = new RtaLoadSaldo(null, null, null, null, null, "-", "-");
		if (requiereSaldo == true) {
			if (rta.getRespuesta() != null)
				saldoCuenta = rta.getRespuesta().get(0);
		} else {
			saldoCuenta = rta.getRespuesta().get(0);
		}
		CuentaTituloDTO cuentaTituloDTO = new CuentaTituloDTO();
		cuentaTituloDTO.setCuentaOperativa(nroCuenta);
		Iterator<Cuenta> ite = cliente.getCuentasPrivadas().iterator();
		while (ite.hasNext()) {
			Cuenta c = ite.next();
			BigDecimal nro = new BigDecimal(c.getNroCuentaProducto());
//			String nroCuentaBPFormateado = "7" + llenarConCerosIzqOTruncar(nro.toString(), 9);
			String nroCuentaBPFormateado = Integer.parseInt(c.getProductoAltair()) + llenarConCerosIzqOTruncar(nro.toString(), 9);
			if (nroCuentaBPFormateado.equals(cuentaTituloDTO.getCuentaOperativa())) {
				cuentaTituloDTO.setCuentaOperativaSinFormatear(cuentaTituloDTO.getCuentaOperativa());
				cuentaTituloDTO.setCuentaOperativa(ISBANStringUtils.formatearNumeroCuenta(c.getNroCuentaProducto()));
				BigInteger sucursal = new BigInteger(c.getNroSucursal());
				cuentaTituloDTO.setSucursal(sucursal.toString());
				cuentasAdhesionDebitoView.setAbreviaturaTipoCuenta(c.getTipoCuentaEnum().getAbreviatura());
				cuentasAdhesionDebitoView.setDescripcionTipoCuenta(c.getTipoCuentaEnum().getDescripcion());
				cuentasAdhesionDebitoView.setAlias(c.getAlias());
				break;
			}
		}

		cuentasAdhesionDebitoView.setNumero(cuentaTituloDTO.getSucursal() + "-" + cuentaTituloDTO.getCuentaOperativa());
		cuentasAdhesionDebitoView.setSaldoPesos("-".equals(saldoCuenta.getcAhorroPesos()) ? "-"
				: ISBANStringUtils.formatearConComaYDosDecimales(saldoCuenta.getcAhorroPesos()));
		cuentasAdhesionDebitoView.setSaldoDolares("-".equals(saldoCuenta.getcAhorroDolares()) ? "-"
				: ISBANStringUtils.formatearConComaYDosDecimales(saldoCuenta.getcAhorroDolares()));
		return respuestaFactory.crearRespuestaOk(CuentasAdhesionDebitoView.class, cuentasAdhesionDebitoView);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<SimularSuscripcionOutDTO> simularSuscripcionBPriv(SimulacionInDTO requestDTO, Cliente cliente) {
		SimulacionFondoBancaPrivadaOutEntity out = new SimulacionFondoBancaPrivadaOutEntity();
		SimularSuscripcionOutDTO respuesta = new SimularSuscripcionOutDTO();
		try {
			out = fondoBPrivDAO.simularSuscripcionBPriv(crearDAOin(requestDTO, cliente, getRACFCredential()));
			respuesta = createDTOout(out);
			respuesta.setUrlReglamento(armarUrlReglamento(requestDTO.getCodigoFondo()));
			Respuesta<String> respuestaLegales = legalBO.buscarLegal(MENSAJE_LEGALES_REGLAMENTO_GESTION);
			Respuesta<String> respuestaTerminosYCondiciones = legalBO.buscarLegal(LEGAL_TERMINOS_COND_SUSCRIPCION);
			if (respuestaLegales.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)
					|| respuestaTerminosYCondiciones.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.COMUNICATE_ASESOR_BANCA_PRIVADA);
			}
			respuesta.setTerminosYCondiciones(respuestaTerminosYCondiciones.getRespuesta());
			respuesta.setLegales(respuestaLegales.getRespuesta());
		} catch (SimulacionDAOException e) {
			LOGGER.error("Error llamando a simulacion de suscripcion", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} catch (DAOException e) {
			LOGGER.error("Error llamando a simulacion de suscripcion", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.COMUNICATE_ASESOR_BANCA_PRIVADA);
		} catch (BusinessException e) {
			LOGGER.error("Error llamando a simulacion de suscripcion", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		return respuestaFactory.crearRespuestaOk(SimularSuscripcionOutDTO.class, respuesta);
	}

	/**
	 * Crea el DTO de respuesta a partir del resutado del DAO.
	 *
	 * @param out the out
	 * @return the simulacion out DTO
	 */
	private SimularSuscripcionOutDTO createDTOout(SimulacionFondoBancaPrivadaOutEntity out) {
		SimularSuscripcionOutDTO simulacionOut = new SimularSuscripcionOutDTO();
		simulacionOut.setDentroDelPerfil(out.getDentroDelPerfil());
		simulacionOut.setDisclaimer(out.getDisclaimer());
		return simulacionOut;
	}

	/**
	 * Cargar datos B priv.
	 *
	 * @param daoIn        the dao in
	 * @param respuestaDAO the respuesta DAO
	 * @param request      the request
	 * @return the suscripcion out DTO
	 */
	public SuscripcionOutDTO cargarDatosBPriv(EjecucionFondoBancaPrivadaInEntity daoIn, EjecucionFondoBancaPrivadaOutEntity respuestaDAO,
											  SuscripcionInDTO request) {

		SuscripcionOutDTO dtoResponse = new SuscripcionOutDTO();
		String moneda = daoIn.getMoneda();
		if ("ARG".equals(moneda)) {
			moneda = "$";
		} else {
			moneda = "u$s";
		}
		String saldo = ISBANStringUtils.formatearConComaYDosDecimales(request.getImporte().toString());
		dtoResponse.setImporte(" " + moneda + " " + saldo);

		String mensaje = mensajeBO.obtenerMensajePorCodigo(SUSCRIPCION_CORRECTA).getMensaje();
		String mensajeSuscripcion = MessageFormat.format(mensaje, daoIn.getNombreFondo(),

				dtoResponse.getImporte());
		dtoResponse.setMensajeSuscripcion(mensajeSuscripcion);
		dtoResponse.setNroCertificado(respuestaDAO.getNroCertificado());
		dtoResponse.setFechaHora(ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date()));
		dtoResponse.setCuotaPartes(respuestaDAO.getCuotaPartes());
		return dtoResponse;

	}

	/**
	 * Manejar caso feliz B priv.
	 *
	 * @param daoIn        the dao in
	 * @param respuestaDAO the respuesta DAO
	 * @param requestDTO   the request DTO
	 * @return the respuesta
	 */
	Respuesta<SuscripcionOutDTO> manejarCasoFelizBPriv(EjecucionFondoBancaPrivadaInEntity daoIn,
													   EjecucionFondoBancaPrivadaOutEntity respuestaDAO, SuscripcionInDTO requestDTO) {
		SuscripcionOutDTO dtoResponse = cargarDatosBPriv(daoIn, respuestaDAO, requestDTO);
		return respuestaFactory.crearRespuestaOk(SuscripcionOutDTO.class, dtoResponse);
	}

	/**
	 * Manejar reintento B priv.
	 *
	 * @param daoIn             the dao in
	 * @param permiteReintentar the permite reintentar
	 * @param e                 the e
	 * @return the respuesta
	 */
	Respuesta<SuscripcionOutDTO> manejarReintentoBPriv(EjecucionFondoBancaPrivadaInEntity daoIn, boolean permiteReintentar,
													   DAOException e) {
		Respuesta<SuscripcionOutDTO> respuesta;
		LOGGER.error("Error en fondoDAO metodo finalizarSuscribirFondosBPriv. ", e);
		String mensaje = mensajeBO.obtenerMensajePorCodigo(FINALIZAR_SUSCRIPCION_FALLO_GENERICO).getMensaje();
		String mensajeError = MessageFormat.format(mensaje, daoIn.getNombreFondo());
		if (permiteReintentar) {
			respuesta = respuestaFactory.crearRespuestaError(SuscripcionOutDTO.class, mensajeError,
					TipoError.ERROR_FINALIZAR_SUSCRIPCION_FONDO_CON_REINTENTO.toString());

		} else {
			respuesta = respuestaFactory.crearRespuestaError(SuscripcionOutDTO.class, mensajeError,
					TipoError.ERROR_FINALIZAR_SUSCRIPCION_FONDO_SIN_REINTENTO.toString());
		}
		return respuesta;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<SuscripcionOutDTO> finalizarSuscribirFondosBPriv(SuscripcionInDTO request, Cliente cliente) {
		boolean permiteReintentar;
		String segmento = Segmento.BANCA_PRIVADA.getCodigo();

		if (sessionParametros.getContador() != null) {
			permiteReintentar = sessionParametros.getContador().permiteReintentar();
		} else {
			LOGGER.debug("Error, Contador no inicializado!!");
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		EjecucionFondoBancaPrivadaInEntity daoIn = new EjecucionFondoBancaPrivadaInEntity();
		EjecucionFondoBancaPrivadaOutEntity daoOut = null;
		try {
			fondosTenenciasPrototype.cleanCacheObtenerTenenciaValuadaDetalleFondoOnline(cliente, segmento);
			daoIn = createDAOIn(request, getRACFCredential());
			daoOut = fondoBPrivDAO.suscribir(daoIn);
		} catch (FueraDeHorarioException e) {
			LOGGER.error("Error Fuera de horario. ", e);
			return respuestaFactory.crearRespuestaWarning(new SuscripcionOutDTO(), "", TipoError.FUERADEHORARIO,
					CodigoMensajeConstantes.CODIGO_MENSAJE_FUERA_HORARIO_FONDOS);
		} catch (SaldoInsuficienteException ex) {
			LOGGER.error("Error en BO saldo insuficiente. ", ex);
			return respuestaFactory.crearRespuestaWarning("", TipoError.ERROR_SALDO_INSUFICIENTE,
					CodigoMensajeConstantes.FINALIZAR_SUSCRIPCION_SALDO_INSUFICIENTE);
		} catch (TimeOutException exc) {
			LOGGER.error("Error en BO timeOut. ", exc);
			String mensaje = mensajeBO.obtenerMensajePorCodigo(FINALIZAR_SUSCRIPCION_FALLO_GENERICO).getMensaje();
			String mensajeError = MessageFormat.format(mensaje, daoIn.getNombreFondo());
			return respuestaFactory.crearRespuestaError(SuscripcionOutDTO.class, mensajeError,
					TipoError.ERROR_FINALIZAR_SUSCRIPCION_FONDO_SIN_REINTENTO.toString());
		} catch (ImporteLimiteException ex) {
			LOGGER.error("Error en BO error de limite menor. ", ex);
			return respuestaFactory.crearRespuestaWarning("", TipoError.ERROR_LIMITE_MINIMO, "");
		} catch (DAOException e) {
			LOGGER.error("Error realizando la Ejecucion de Fondos", e);
			return manejarReintentoBPriv(daoIn, permiteReintentar, e);
		} catch (BusinessException e) {
			LOGGER.error("Error creando entidad para invocar dao Ejecucion de Fondos", e);
		}

		cleanCacheHoldingBff(request.getNroCuentaBancaPrivada(), cliente, segmento);

		return manejarCasoFelizBPriv(daoIn, daoOut, request);
	}

	/**
	 * Crea el DAOIn para el llamado a ejecucion de fondos.
	 *
	 * @param request    the request
	 * @param credential the credential
	 * @return the ejecucion fondo in entity
	 * @throws BusinessException the business exception
	 */
	private EjecucionFondoBancaPrivadaInEntity createDAOIn(SuscripcionInDTO request, Credential credential)
			throws BusinessException {
		EjecucionFondoBancaPrivadaInEntity daoIn = new EjecucionFondoBancaPrivadaInEntity();
		daoIn.setTipo(EnumFondosDisponiblesTipoOperacion.SU.getCodigo());
		daoIn.setCapital(request.getImporte());
		daoIn.setIsPerfilInversion(request.getDentroDelPerfil());
		daoIn.setNroCuenta(request.getNroCuentaBancaPrivada());
		daoIn.setPasswordRacf(credential.getPassword());
		daoIn.setUsuarioRacf(credential.getUsuario());

		ConsultaFondoOutEntity fondo;
		try {
			fondo = consultaFondoDAO.obtenerPorCodigo(request.getCodigoFondo());
			daoIn.setEspecie(fondo.getEspecie());
			daoIn.setNombreFondo(fondo.getNombreFondo());
			daoIn.setMoneda(fondo.getMoneda());
			if (DivisaEnum.PESO.getCodigo().equals(fondo.getMoneda())) {
				daoIn.setMoneda(MONEDA_PESO);
			} else {
				daoIn.setMoneda(DivisaEnum.DOLAR.getCodigo());
			}
		} catch (DAOException e) {
			LOGGER.error("Error recuperando fondo", e);
			throw new BusinessException(e);
		}

		return daoIn;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<Boolean> vaciarCache() {
		consultaFondoDAO.vaciarCache();
		return respuestaFactory.crearRespuestaOk(true);
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<CotizacionDeFondosView> consultarCotizaciones() {
		Respuesta<CotizacionDeFondosView> respuesta = new Respuesta<CotizacionDeFondosView>();
		try {
			FondoOutEntity respuestaDAO = fondoDAO.consultarCotizaciones();
			List<ConsultaFondoOutEntity> fondosTotales = consultaFondoDAO.obtenerFondos();
			CotizacionDeFondosView respuestaView = crearView(respuestaDAO, fondosTotales);

			Respuesta<String> respuestaMensajeLegal = legalBO
					.buscarLegal(CodigoMensajeConstantes.CODIGO_MENSAJE_LEGAL_DETALLE_FCI);
			if (EstadoRespuesta.OK.equals(respuestaMensajeLegal.getEstadoRespuesta())) {
				respuestaView.setLegales(respuestaMensajeLegal.getRespuesta());
			}

			Respuesta<String> respuestaMensajeLegalSuperAhorroPesos = legalBO
					.buscarLegal(CodigoMensajeConstantes.CODIGO_MENSAJE_LEGAL_DETALLE_SUPER_AHORRO_PESOS);
			if (EstadoRespuesta.OK.equals(respuestaMensajeLegalSuperAhorroPesos.getEstadoRespuesta())) {
				respuestaView.setLegalSuperAhorroPesos(respuestaMensajeLegalSuperAhorroPesos.getRespuesta());
			}

			// Se determina si el usuario posee cuentas con fondos ex citi con tenencia, así
			// seteando los legales para fondos DELTA y COMPASS
			respuestaView.setIsExCiti(tieneFondosExCitiConTenencia());

			if (respuestaView.getIsExCiti()) {
				respuestaView.setUrlDelta(mensajeManager
						.obtenerMensajePorCodigo(CodigoMensajeConstantes.INVERSIONES_TENENCIA_URL_DELTA).getMensaje());
				respuestaView.setUrlCompass(
						mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.INVERSIONES_TENENCIA_URL_COMPASS)
								.getMensaje());
				sessionParametros.setTenenciasFondoDTO(null);
			}
			respuesta.setRespuesta(respuestaView);
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
			respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		}
		return respuesta;
	}

	/**
	 * Tiene fondos ex citi con tenencia.
	 *
	 * @return the boolean
	 */
	private Boolean tieneFondosExCitiConTenencia() {

		boolean tieneFondosExCitiConTenencia = false;
		TenenciasFondoDTO fondosConTenencia = sessionParametros.getTenenciasFondoDTO();

		if (fondosConTenencia != null) {
			List<CuentaConTenenciaDTO> listaCuentas = fondosConTenencia.getListaCuentas();
			for (CuentaConTenenciaDTO cuentaConTenencia : listaCuentas) {
				if (tieneFondosExCitiConTenencia) {
					break;
				}
				List<TenenciaFondosSuscritosDTO> tenenciaFondosSuscritos = new ArrayList<TenenciaFondosSuscritosDTO>();
				if (cuentaConTenencia.getRespuesta().getRespuesta() != null) {
					tenenciaFondosSuscritos
							.addAll(cuentaConTenencia.getRespuesta().getRespuesta().getTenenciaFondosSuscritosPesos());
					tenenciaFondosSuscritos.addAll(
							cuentaConTenencia.getRespuesta().getRespuesta().getTenenciaFondosSuscritosDolares());
					for (TenenciaFondosSuscritosDTO fondoConTenencia : tenenciaFondosSuscritos) {
						if (FONDO_EXCITI.equalsIgnoreCase(fondoConTenencia.getExCiti())) {
							tieneFondosExCitiConTenencia = true;
							break;
						}
					}
				}
			}
		}
		return tieneFondosExCitiConTenencia;
	}

	/**
	 * Crear view.
	 *
	 * @param respuestaIn   the respuesta in
	 * @param fondosTotales the fondos totales
	 * @return the cotizacion de fondos view
	 * @throws BusinessException the business exception
	 */
	private CotizacionDeFondosView crearView(FondoOutEntity respuestaIn, List<ConsultaFondoOutEntity> fondosTotales)
			throws BusinessException {
		CotizacionDeFondosView respuestaOut = new CotizacionDeFondosView();
		respuestaOut.setFondos(new ArrayList<DatosCotizacionFondoView>());
		try {
			// RECORRO LOS FONDOS CON LOS DATOS DE LA COTIZACION
			for (FondoEntity cotizacionFondo : respuestaIn.getfondos()) {
				// RECORRO LOS FONDOS CON LOS DATOS DEL STORE VER_FONDOS PARA
				// LUEGO MACHEAR
				for (ConsultaFondoOutEntity datosFondo : fondosTotales) {
					if (cotizacionFondo.getTipo_Fondo().equals(datosFondo.getCodigoFondo())
							&& (!(rescateFondoEspecie.getFondosMap().containsKey(datosFondo.getCodigoFondo())))) {
						// JUNTO LOS DATOS DE LA COTIZACION CON LOS PROPIOS DEL
						// FONDO Y ARMO LA LISTA A DEVOLVER
						DatosCotizacionFondoView fondoCompleto = new DatosCotizacionFondoView();

						BeanUtils.copyProperties(fondoCompleto, datosFondo);
						// se formatea los honorarios
						fondoCompleto.setHonorariosAdmin(formaterarHonorarios(datosFondo.getHonorariosAdmin()));
						fondoCompleto.setHonorariosEntrada(formaterarHonorarios(datosFondo.getHonorariosEntrada()));
						fondoCompleto.setHonorariosSalida(formaterarHonorarios(datosFondo.getHonorariosSalida()));

						fondoCompleto.setAdministrarFondos(formaterarHonorarios(datosFondo.getHonorariosAdmin()));
						fondoCompleto.setsGTE(formaterarHonorarios(datosFondo.getsGTE()));
						fondoCompleto.setsDEP(formaterarHonorarios(datosFondo.getsDEP()));
						if (!"x".equals(datosFondo.getLinkCartera().trim())) {
							fondoCompleto.setUrlCartera(MessageFormat.format(urlCartera, datosFondo.getLinkCartera()));
						}
						BeanUtils.copyProperties(fondoCompleto, cotizacionFondo);
						fondoCompleto.setValor_a_la_fecha(
								formatearValorCuotaparteInfoFondos(cotizacionFondo.getValor_a_la_fecha()));
						respuestaOut.getFondos().add(fondoCompleto);
					}
				}
			}
		} catch (IllegalAccessException e) {
			LOGGER.error(e.getMessage(), e);
			throw new BusinessException();
		} catch (InvocationTargetException e) {
			LOGGER.error(e.getMessage(), e);
			throw new BusinessException();
		}
		return respuestaOut;
	}

	/**
	 * Metodo para formatear el valor a mostar en informacion de fondos obtenido del
	 * CNSCOTIFCI_120 Se divide por 1000, pero se respeta que los ultimos 2 digitos
	 * del valor informado son la parte decimal.
	 * 
	 * @param valor
	 */
	private String formatearValorCuotaparteInfoFondos(String valor) {
		BigDecimal bg = new BigDecimal(valor).divide(new BigDecimal("100000"));
		return ISBANStringUtils.formatearConComaYVariosDecimalesBis(String.valueOf(bg), 5);
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<ConsultaHorariosYHonorariosView> obtenerFondosHorariosYHonorarios() {
		try {

			Predicate filtroFondoHorario = crearPredicateHorario();

			Predicate filtroFondoHonorario = crearPredicateHonorario();

			List<ConsultaFondoOutEntity> listaFondos = consultaFondoDAO.obtenerPorBanca(TIPO_BANCA_PERSONAL);

			List<ConsultaFondoOutEntity> listaFondosHorarios = new ArrayList<ConsultaFondoOutEntity>();
			listaFondosHorarios.addAll(listaFondos);

			List<ConsultaFondoOutEntity> listaFondosHonorarios = new ArrayList<ConsultaFondoOutEntity>();
			listaFondosHonorarios.addAll(listaFondos);

			// Aplico los filtros para horarios y honorarios.
			CollectionUtils.filter(listaFondosHorarios, filtroFondoHorario);
			CollectionUtils.filter(listaFondosHonorarios, filtroFondoHonorario);

			ConsultaHorariosYHonorariosView consultaHorariosYHonorariosView = new ConsultaHorariosYHonorariosView();
			List<FondoHorarioHonorario> listaHorariosView = consultaHorariosYHonorariosView.getFondoHorario();
			List<FondoHorarioHonorario> listaHonorariosView = consultaHorariosYHonorariosView.getFondoHonorario();

			// Lleno lista horarios
			cargarListaHorarios(listaFondosHorarios, listaHorariosView);

			// Lleno lista honorarios
			cargarListaHonorarios(listaFondosHonorarios, listaHonorariosView);

			// obtengo mensajes
			Respuesta<String> respuestaPrimerLegalesHonorarios = legalBO
					.buscarLegal(CodigoMensajeConstantes.PRIMER_LEGALES_HONORARIOS);
			Respuesta<String> respuestaSegundoLegalesHonorarios = legalBO
					.buscarLegal(CodigoMensajeConstantes.SEGUNDO_LEGALES_HONORARIOS);
			Respuesta<String> respuestaLegalesHorarios = legalBO.buscarLegal(CodigoMensajeConstantes.LEGALES_HORARIOS);

			if (EstadoRespuesta.OK.equals(respuestaPrimerLegalesHonorarios.getEstadoRespuesta())
					&& EstadoRespuesta.OK.equals(respuestaSegundoLegalesHonorarios.getEstadoRespuesta())
					&& EstadoRespuesta.OK.equals(respuestaLegalesHorarios.getEstadoRespuesta())) {
				cargarLegales(consultaHorariosYHonorariosView, respuestaPrimerLegalesHonorarios,
						respuestaSegundoLegalesHonorarios, respuestaLegalesHorarios);
				// Armo respuesta Ok
				return respuestaFactory.crearRespuestaOk(ConsultaHorariosYHonorariosView.class,
						consultaHorariosYHonorariosView);
			} else {
				return armarRespuestaErrorHorariosYHonorarios();
			}

		} catch (DAOException e) {
			return armarRespuestaErrorHorariosYHonorarios();
		}
	}

	/**
	 * Cargar legales.
	 *
	 * @param consultaHorariosYHonorariosView   the consulta horarios Y honorarios
	 *                                          view
	 * @param respuestaPrimerLegalesHonorarios  the respuesta primer legales
	 *                                          honorarios
	 * @param respuestaSegundoLegalesHonorarios the respuesta segundo legales
	 *                                          honorarios
	 * @param respuestaLegalesHorarios          the respuesta legales horarios
	 */
	private void cargarLegales(ConsultaHorariosYHonorariosView consultaHorariosYHonorariosView,
			Respuesta<String> respuestaPrimerLegalesHonorarios, Respuesta<String> respuestaSegundoLegalesHonorarios,
			Respuesta<String> respuestaLegalesHorarios) {
		consultaHorariosYHonorariosView.setPrimerLegalesHonorarios(respuestaPrimerLegalesHonorarios.getRespuesta());
		consultaHorariosYHonorariosView.setSegundoLegalesHonorarios(respuestaSegundoLegalesHonorarios.getRespuesta());
		consultaHorariosYHonorariosView.setLegalesHorario(respuestaLegalesHorarios.getRespuesta());
	}

	/**
	 * Cargar lista honorarios.
	 *
	 * @param listaFondosHonorarios the lista fondos honorarios
	 * @param listaHonorariosView   the lista honorarios view
	 */
	private void cargarListaHonorarios(List<ConsultaFondoOutEntity> listaFondosHonorarios,
			List<FondoHorarioHonorario> listaHonorariosView) {
		for (ConsultaFondoOutEntity fondoHonorario : listaFondosHonorarios) {
			FondoHorarioHonorario fondoHorarioHonorario = new FondoHorarioHonorario();
			fondoHorarioHonorario.setComisAdmin(formaterarHonorarios(fondoHonorario.getHonorariosAdmin()));
			fondoHorarioHonorario.setEntrada(formaterarHonorarios(fondoHonorario.getHonorariosEntrada()));
			fondoHorarioHonorario.setSalida(formaterarHonorarios(fondoHonorario.getHonorariosSalida()));
			fondoHorarioHonorario.setsGTE(formaterarHonorarios(fondoHonorario.getsGTE()));
			fondoHorarioHonorario.setsDEP(formaterarHonorarios(fondoHonorario.getsDEP()));
			fondoHorarioHonorario.setNombreFondo(obtenerNombreFondoHonorario(fondoHonorario));
			listaHonorariosView.add(fondoHorarioHonorario);
		}
	}

	/**
	 * Obtener nombre fondo honorario.
	 *
	 * @param fondoHonorario the fondo honorario
	 * @return the string
	 */
	private String obtenerNombreFondoHonorario(ConsultaFondoOutEntity fondoHonorario) {
		String nombreFondo = fondoHonorario.getNombreFondo();
		if (StringUtils.equals(fondoHonorario.getCodigoFondo(), CODIGO_FONDO_42)) {
			return nombreFondo + ASTERISCO;
		} else if (StringUtils.equals(fondoHonorario.getCodigoFondo(), CODIGO_FONDO_41)
				|| StringUtils.equals(fondoHonorario.getCodigoFondo(), CODIGO_FONDO_54)) {
			return nombreFondo + DOS_ASTERISCOS;
		}
		return nombreFondo;
	}

	/**
	 * Cargar lista horarios.
	 *
	 * @param listaFondosHorarios the lista fondos horarios
	 * @param listaHorariosView   the lista horarios view
	 */
	private void cargarListaHorarios(List<ConsultaFondoOutEntity> listaFondosHorarios,
			List<FondoHorarioHonorario> listaHorariosView) {
		for (ConsultaFondoOutEntity fondoHorario : listaFondosHorarios) {
			FondoHorarioHonorario fondoHorarioHonorario = new FondoHorarioHonorario();
			fondoHorarioHonorario.setNombreFondo(fondoHorario.getNombreFondo());
			fondoHorarioHonorario.setHorario(fondoHorario.getHorario());
			listaHorariosView.add(fondoHorarioHonorario);
		}
	}

	/**
	 * Crear predicate honorario.
	 *
	 * @return the predicate
	 */
	private Predicate crearPredicateHonorario() {
		return new Predicate() {
			@Override
			public boolean evaluate(Object object) {
				ConsultaFondoOutEntity fondo = (ConsultaFondoOutEntity) object;
				if ((fondo.getHonorariosAdmin() != null) && (fondo.getHonorariosEntrada() != null)
						&& (fondo.getHonorariosSalida() != null)) {
					return true;
				}
				return false;
			}
		};
	}

	/**
	 * Crear predicate horario.
	 *
	 * @return the predicate
	 */
	private Predicate crearPredicateHorario() {
		return new Predicate() {
			@Override
			public boolean evaluate(Object object) {
				ConsultaFondoOutEntity fondo = (ConsultaFondoOutEntity) object;
				if (fondo.getHorario() != null) {
					return true;
				}
				return false;
			}
		};
	}

	/**
	 * Formaterar honorarios. si llega a venir un honorario en null, en front se
	 * muestra como sin informacion
	 *
	 * @param honorarios the honorarios
	 * @return the string
	 */
	private String formaterarHonorarios(String honorarios) {
		if (honorarios == null) {
			return honorarios;
		}
		if (0 == honorarios.indexOf(".")) {
			return "0" + honorarios;
		} else {
			return honorarios;
		}
	}

	/**
	 * Armar respuesta error horarios Y honorarios.
	 *
	 * @return the respuesta
	 */
	private Respuesta<ConsultaHorariosYHonorariosView> armarRespuestaErrorHorariosYHonorarios() {
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_HORARIOS_HONORARIOS,
				CodigoMensajeConstantes.ERROR_CARGA_HORARIOS_HONORARIOS);
	}

	/**
	 * Obtener cuentas con tenencias.
	 *
	 * @param cuentas         the cuentas banca personal
	 * @param fondos          the fondos RTL
	 * @param tenenciaValuada the tenencia valuada
	 * @return the tenencias fondo DTO
	 * @throws DAOException the DAO exception
	 */
	protected TenenciasFondoDTO obtenerCuentasConTenencias(List<CuentaTituloDTO> cuentas,
			List<ConsultaFondoOutEntity> fondos, DetalleTenenciaValuadaEntity tenenciaValuada) throws DAOException {
		TenenciasFondoDTO tenencia = new TenenciasFondoDTO();
		List<CuentaConTenenciaDTO> listaCuentas = new ArrayList<CuentaConTenenciaDTO>();

		if (CODIGO_OK.equals(Integer.valueOf(tenenciaValuada.getCodigo()))
				|| CODIGO_WARNING.equals(Integer.valueOf(tenenciaValuada.getCodigo()))) {
			try {
				tenenciaMap = obtenerTenenciaFondos(cuentas, fondos, tenenciaValuada.getDatos());
			} catch (DAOException e) {
				throw new DAOException();
			}
		}

		for (CuentaTituloDTO cuentaTitulo : cuentas) {
			CuentaConTenenciaDTO cuenta = new CuentaConTenenciaDTO();
			cuenta.setNumeroCuentaTitulo(cuentaTitulo.getCuentaOperativa());
			cuenta.setIntervinientes(cuentaTitulo.getIntervinientes());
			cuenta.setSucursal(cuentaTitulo.getSucursal());
			cuenta.setRepatriacion(cuentaTitulo.isRepatriacion());
			cuenta.setCuentaOperativa(cuentaTitulo.getCuentaOperativaRep());
			// cuenta.setCuentaBloqueada(cuentaTitulo.isCuentaBloqueada());
			Respuesta<TenenciaPorFondoDTO> resp = null;
			if (!CODIGO_OK.equals(Integer.valueOf(tenenciaValuada.getCodigo()))
					&& !CODIGO_WARNING.equals(Integer.valueOf(tenenciaValuada.getCodigo()))) {
				resp = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_TENENCIA_FONDOS_NUEVOS);
			} else if (tenenciaMap.get(cuentaTitulo.getCuentaOperativaSinFormatear()).isEmpty()) {
				resp = respuestaFactory.crearRespuestaError("", TipoError.ERROR_CUENTA_SIN_TENENCIA,
						CodigoMensajeConstantes.ERROR_CUENTA_SIN_TENENCIA);
			} else {
				resp = respuestaFactory.crearRespuestaOk(TenenciaPorFondoDTO.class,
						tenenciaMap.get(cuentaTitulo.getCuentaOperativaSinFormatear()));
			}
			cuenta.setRespuesta(resp);
			listaCuentas.add(cuenta);
		}
		tenencia.setListaCuentas(listaCuentas);
		return tenencia;
	}

	/**
	 * Obtener las tenencias en pesos y dolares de los fondos.
	 *
	 * @param cuentasBancaPersonal the cuentas banca personal
	 * @param datosFondos          the fondos RTL
	 * @param tenenciaFondos       the datos
	 * @return the map
	 * @throws DAOException the DAO exception
	 */
	protected Map<String, TenenciaPorFondoDTO> obtenerTenenciaFondos(List<CuentaTituloDTO> cuentasBancaPersonal,
			List<ConsultaFondoOutEntity> datosFondos, DatosRespuesta tenenciaFondos) throws DAOException {
		tenenciaMap.clear();
		for (CuentaTituloDTO cuenta : cuentasBancaPersonal) {
			tenenciaMap.put(cuenta.getCuentaOperativaSinFormatear(), new TenenciaPorFondoDTO());
			List<TenenciaFondosSuscritosDTO> tenenciaFondosSuscritosPesos = new ArrayList<TenenciaFondosSuscritosDTO>();
			List<TenenciaFondosSuscritosDTO> tenenciaFondosSuscritosDolares = new ArrayList<TenenciaFondosSuscritosDTO>();

			for (ResultadoTenenciaValuadaDetalleFondoOL fondoConTenencia : tenenciaFondos.getResultado()) {
				for (ConsultaFondoOutEntity datosFondo : datosFondos) {
					cargarMapTenencias(tenenciaFondosSuscritosPesos, tenenciaFondosSuscritosDolares, fondoConTenencia,
							datosFondo, cuenta);
				}
			}
		}
		return tenenciaMap;
	}

	// /**
	// * Devuleve si una CuentaTituloDTO esta bloqueada.
	// *
	// * @param cuenta
	// * the cuenta
	// * @param fondoConTenencia
	// * the fondo con tenencia
	// * @return the cuenta titulo DTO
	// */
	// private CuentaTituloDTO isCuentaBloqueada(CuentaTituloDTO cuenta,
	// ResultadoTenenciaValuadaDetalleFondoOL fondoConTenencia) {
	// if (fondoConTenencia.getFechaBloqueo() != null) {
	// if (!("0").equalsIgnoreCase(fondoConTenencia.getFechaBloqueo()))
	// cuenta.setCuentaBloqueada(true);
	// }
	// return cuenta;
	//
	// }
	// String bloqueo =
	// convertirFechaTenencias(fondoConTenencia.getFechaBloqueo());
	// if (Long.valueOf(fondoConTenencia.getNumeroCuenta())
	// .equals(Long.valueOf(cuenta.getCuentaOperativaSinFormatear()))) {
	// if (bloqueo != null) {
	// cuenta.setCuentaBloqueada(true);
	// }
	// }
	// return cuenta;
	// }

	/**
	 * Cargar map tenencias con las tenencias en pesos y dolares y los totales
	 * correspondientes por cuenta.
	 *
	 * @param tenenciaFondosSuscritosPesos   the tenencia fondos suscritos pesos
	 * @param tenenciaFondosSuscritosDolares the tenencia fondos suscritos dolares
	 * @param fondoConTenencia               the dato
	 * @param datosFondo                     the fondo 1
	 * @param cuenta                         the cuenta
	 */
	protected void cargarMapTenencias(List<TenenciaFondosSuscritosDTO> tenenciaFondosSuscritosPesos,
			List<TenenciaFondosSuscritosDTO> tenenciaFondosSuscritosDolares,
			ResultadoTenenciaValuadaDetalleFondoOL fondoConTenencia, ConsultaFondoOutEntity datosFondo,
			CuentaTituloDTO cuenta) {
		if (Long.valueOf(fondoConTenencia.getNumeroCuenta())
				.equals(Long.valueOf(cuenta.getCuentaOperativaSinFormatear()))
				&& fondoConTenencia.getMoneda().equals(MONEDA_PESOS)
				&& fondoConTenencia.getCodigoFondo() == Integer.valueOf(datosFondo.getCodigoFondo()).intValue()) {
			TenenciaFondosSuscritosDTO fondoPesos = new TenenciaFondosSuscritosDTO();
			boolean exCiti = FONDO_EXCITI.equalsIgnoreCase(datosFondo.getExCiti());
			fondoPesos.setCodigoFondo(datosFondo.getCodigoFondo());
			fondoPesos.setExCiti(datosFondo.getExCiti());
			fondoPesos.setNombreFondo(datosFondo.getNombreFondo());
			fondoPesos.setCodigoAgrupador(datosFondo.getCodigoAgrupador());
			fondoPesos.setOrdenAgrupacion(datosFondo.getOrdenAgrupacion());
			fondoPesos.setTenenciaValuadaAlCosto(fondoConTenencia.getTenenciaValuadaCompra());
			// if (cuenta.isCuentaBloqueada()) {
			// fondoPesos.setHabilitarRescate(OPERACION_BLOQUEADA);
			// fondoPesos.setHabilitarSuscripcion(OPERACION_BLOQUEADA);
			// fondoPesos.setHabilitarTransferencia(OPERACION_BLOQUEADA);
			// } else {
			fondoPesos.setHabilitarRescate(datosFondo.getHabilitarRescate());
			fondoPesos.setHabilitarSuscripcion(exCiti ? OPERACION_BLOQUEADA : datosFondo.getHabilitarSuscripcion());
			fondoPesos.setHabilitarTransferencia(exCiti ? OPERACION_BLOQUEADA : datosFondo.getHabilitarTransferencia());
			// }
			fondoPesos.setCantidadCuotapartes(fondoConTenencia.getCantidadCuotapartes());
			fondoPesos.setValorCuotaparte(fondoConTenencia.getValorCuotaparte());
			fondoPesos.setValuacion(fondoConTenencia.getTenenciaValuada());
			if (fondoConTenencia.getResultadoBruto() != null) {
				fondoPesos.setResultado(fondoConTenencia.getResultadoBruto());
			} else {
				fondoPesos.setResultado(null);
			}
			fondoPesos.setTenenciaValuadaReexp(fondoConTenencia.getTenenciaValuadaReexp());
			if (!"x".equals(datosFondo.getLinkCartera().trim())) {
				fondoPesos.setUrlCartera(MessageFormat.format(urlCartera, datosFondo.getLinkCartera()));
			}
			if (exCiti) {
				fondoPesos.setUrlCartera(null);
			}

			fondoPesos.setPrecioCompra(fondoConTenencia.getpPPC());
			if (rescateFondoEspecie.getFondosMap().containsKey(datosFondo.getCodigoFondo())) {
				fondoPesos.setFondoNuevo(true);
				fondoPesos.setTipoFondo(datosFondo.getAgrupadorSuscripcion() + " (**)");
			} else {
				fondoPesos.setTipoFondo(datosFondo.getAgrupadorSuscripcion());
			}

			tenenciaFondosSuscritosPesos.add(fondoPesos);
			Collections.sort(tenenciaFondosSuscritosPesos);
			tenenciaMap.get(cuenta.getCuentaOperativaSinFormatear())
					.setTenenciaFondosSuscritosPesos(tenenciaFondosSuscritosPesos);
			if (!fondoPesos.isFondoNuevo()) {
				if (fondoConTenencia.getResultadoBruto() != null) {
					tenenciaMap.get(cuenta.getCuentaOperativaSinFormatear())
							.addResultadoPesos(fondoConTenencia.getResultadoBruto());
				}
				if (fondoConTenencia.getTenenciaValuada() != null) {
					tenenciaMap.get(cuenta.getCuentaOperativaSinFormatear())
							.addValuacionPesos(fondoConTenencia.getTenenciaValuada());
				}
			}
		} else if (Long.valueOf(fondoConTenencia.getNumeroCuenta())
				.equals(Long.valueOf(cuenta.getCuentaOperativaSinFormatear()))
				&& fondoConTenencia.getMoneda().equals(MONEDA_DOLARES)
				&& fondoConTenencia.getCodigoFondo() == Integer.valueOf(datosFondo.getCodigoFondo()).intValue()) {
			TenenciaFondosSuscritosDTO fondoDolares = new TenenciaFondosSuscritosDTO();
			boolean exCiti = FONDO_EXCITI.equalsIgnoreCase(datosFondo.getExCiti());
			fondoDolares.setExCiti(datosFondo.getExCiti());
			fondoDolares.setCodigoFondo(datosFondo.getCodigoFondo());
			fondoDolares.setNombreFondo(datosFondo.getNombreFondo());
			fondoDolares.setTipoFondo(datosFondo.getAgrupadorSuscripcion());
			fondoDolares.setCodigoAgrupador(datosFondo.getCodigoAgrupador());
			fondoDolares.setOrdenAgrupacion(datosFondo.getOrdenAgrupacion());
			fondoDolares.setTenenciaValuadaAlCosto(fondoConTenencia.getTenenciaValuadaCompra());
			// if (cuenta.isCuentaBloqueada()) {
			// fondoDolares.setHabilitarRescate(OPERACION_BLOQUEADA);
			// fondoDolares.setHabilitarSuscripcion(OPERACION_BLOQUEADA);
			// fondoDolares.setHabilitarTransferencia(OPERACION_BLOQUEADA);
			// } else {
			fondoDolares.setHabilitarRescate(datosFondo.getHabilitarRescate());
			fondoDolares.setHabilitarSuscripcion(exCiti ? OPERACION_BLOQUEADA : datosFondo.getHabilitarSuscripcion());
			fondoDolares
					.setHabilitarTransferencia(exCiti ? OPERACION_BLOQUEADA : datosFondo.getHabilitarTransferencia());
			// }
			fondoDolares.setCantidadCuotapartes(fondoConTenencia.getCantidadCuotapartes());
			fondoDolares.setValorCuotaparte(fondoConTenencia.getValorCuotaparte());
			fondoDolares.setValuacion(fondoConTenencia.getTenenciaValuada());
			if (fondoConTenencia.getResultadoBruto() != null) {
				fondoDolares.setResultado(fondoConTenencia.getResultadoBruto());
			} else {
				fondoDolares.setResultado(null);
			}
			fondoDolares.setTenenciaValuadaReexp(fondoConTenencia.getTenenciaValuadaReexp());
			if (!"x".equals(datosFondo.getLinkCartera().trim())) {
				fondoDolares.setUrlCartera(MessageFormat.format(urlCartera, datosFondo.getLinkCartera()));
			}
			fondoDolares.setPrecioCompra(fondoConTenencia.getpPPC());
			if (rescateFondoEspecie.getFondosMap().containsKey(datosFondo.getCodigoFondo())) {
				fondoDolares.setFondoNuevo(true);
				fondoDolares.setTipoFondo(datosFondo.getAgrupadorSuscripcion() + " (**)");
			} else {
				fondoDolares.setTipoFondo(datosFondo.getAgrupadorSuscripcion());
			}
			tenenciaFondosSuscritosDolares.add(fondoDolares);
			Collections.sort(tenenciaFondosSuscritosDolares);
			tenenciaMap.get(cuenta.getCuentaOperativaSinFormatear())
					.setTenenciaFondosSuscritosDolares(tenenciaFondosSuscritosDolares);
			if (!fondoDolares.isFondoNuevo()) {
				if (fondoConTenencia.getResultadoBruto() != null) {
					tenenciaMap.get(cuenta.getCuentaOperativaSinFormatear())
							.addResultadoDolares(fondoConTenencia.getResultadoBruto());
				}
				if (fondoConTenencia.getTenenciaValuada() != null) {
					tenenciaMap.get(cuenta.getCuentaOperativaSinFormatear())
							.addValuacionDolares(fondoConTenencia.getTenenciaValuada());
				}
			}
		}
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<MovimientosFondoOutEntity> consultarMovimientos(Cliente cliente, MovimientosInView viewRequest) {
		MovimientosFondoInEntity movimientoInEntity = createInEntity(viewRequest);
		MovimientosFondoOutEntity movimientos = new MovimientosFondoOutEntity();

		try {
			movimientos = fondoDAO.consultarMovimientos(cliente, movimientoInEntity);

			if (viewRequest.getImporteMinimo() != null && viewRequest.getImporteMaximo() != null) {
				List<MovimientoFondoEntity> listMovimientos = filtrarMovimientosPorImporte(movimientos.getMovimientos(),
						viewRequest);
				movimientos.setMovimientos(listMovimientos);
			}

		} catch (FondoSinMovimientosException e) {
			LOGGER.info("Fondo sin movimientos");
			return respuestaFactory.crearRespuestaError("", TipoError.SIN_MOVIMIENTOS,
					CodigoMensajeConstantes.ERROR_FONDO_SIN_MOVIMIENTOS);
		} catch (DAOException e) {
			LOGGER.error("Error en el servicio", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_FALLO_SERVICIOS);
		} catch (ImporteConvertException e) {
			LOGGER.error("Error al convertir el importe", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_FALLO_SERVICIOS);
		}
		return respuestaFactory.crearRespuestaOk(MovimientosFondoOutEntity.class, movimientos);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.bo.FondoBO#
	 * consultarMovimientosCiti(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente,
	 * ar.com.santanderrio.obp.servicios.inversiones.fondos.view.MovimientosInView)
	 */
	@Override
	public Respuesta<MovimientosFondoOutEntity> consultarMovimientosCiti(Cliente cliente,
			MovimientosInView viewRequest) {
		MovFondosResponse movimientosFondoCiti = new MovFondosResponse();
		MovimientosFondoOutEntity movimientos = new MovimientosFondoOutEntity();

		try {
			MovimientosFondoCitiInEntity movimientoCitiInEntity = createCitiInEntity(viewRequest);
			movimientosFondoCiti = extractoDAO.consultaMovimientosOly(movimientoCitiInEntity.getCuentaTitulo(),
					movimientoCitiInEntity.getCodigoFondo(), movimientoCitiInEntity.getFechaDesde(),
					movimientoCitiInEntity.getFechaHasta(), cliente.getNup());
			movimientos.setMovimientos(procesarRespuestaMovimientosFondoCiti(
					movimientosFondoCiti.getListaMovimientos().getValue().getMovFondos()));

			if (viewRequest.getImporteMinimo() != null && viewRequest.getImporteMaximo() != null) {
				List<MovimientoFondoEntity> listMovimientos = filtrarMovimientosPorImporte(movimientos.getMovimientos(),
						viewRequest);
				movimientos.setMovimientos(listMovimientos);
			}

		} catch (FondoSinMovimientosException e) {
			LOGGER.info("Fondo sin movimientos");
			return respuestaFactory.crearRespuestaError("", TipoError.SIN_MOVIMIENTOS,
					CodigoMensajeConstantes.ERROR_FONDO_SIN_MOVIMIENTOS);
		} catch (DAOException e) {
			LOGGER.error("Error en el servicio", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_FALLO_SERVICIOS);
		} catch (ImporteConvertException e) {
			LOGGER.error("Error al convertir el importe", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_FALLO_SERVICIOS);
		} catch (DatatypeConfigurationException e) {
			LOGGER.error("Error al convertir la fecha", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_FALLO_SERVICIOS);
		} catch (ParseException e) {
			LOGGER.error("Error al parsear la fecha", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_FALLO_SERVICIOS);
		}
		return respuestaFactory.crearRespuestaOk(MovimientosFondoOutEntity.class, movimientos);

	}

	/**
	 * Procesar respuesta movimientos fondo citi.
	 *
	 * @param listMovimientos the list movimientos
	 * @return the list
	 * @throws FondoSinMovimientosException the fondo sin movimientos exception
	 */
	private List<MovimientoFondoEntity> procesarRespuestaMovimientosFondoCiti(List<MovFondos> listMovimientos)
			throws FondoSinMovimientosException {

		List<MovimientoFondoEntity> respuestaListaMovimientos = new ArrayList<MovimientoFondoEntity>();
		for (MovFondos movimiento : listMovimientos) {
			MovimientoFondoEntity movimientoTemp = new MovimientoFondoEntity();

			movimientoTemp.setFechaDeConversion(movimiento.getFECHALIQ().toString());
			movimientoTemp.setDescripcionMovimiento(movimiento.getCONCEPTO().toString());
			movimientoTemp.setImporte(movimiento.getIMPORTE().toString());
			movimientoTemp.setNumeroCertificado(movimiento.getNRLIQUIDACION().toString());
			movimientoTemp.setCantidadCuotaPartes(movimiento.getCUOTASPARTES().getValue());
			movimientoTemp.setValorCuota(movimiento.getCOTIZACION().toString());

			respuestaListaMovimientos.add(movimientoTemp);
		}
		if (respuestaListaMovimientos.isEmpty()) {
			throw new FondoSinMovimientosException();
		}
		return respuestaListaMovimientos;
	}

	/**
	 * Crea entity de entrada para llamar al DAO consultarMovimientos.
	 *
	 * @param viewRequest the view request
	 * @return the movimientos fondo in entity
	 */
	MovimientosFondoInEntity createInEntity(MovimientosInView viewRequest) {
		MovimientosFondoInEntity movimientoFondoInEntity = new MovimientosFondoInEntity(maxDias);
		String cuentaTitulo = viewRequest.getCuentaTitulo().replace("/", "");
		movimientoFondoInEntity.setCodigoFondo(viewRequest.getCodigoFondo());
		movimientoFondoInEntity
				.setCodigoCliente(CODIGO_AGENTE_CONSTANTE.concat(StringUtils.leftPad(cuentaTitulo, 8, "0")));
		if (viewRequest.getFechaDesde() != null && viewRequest.getFechaHasta() != null) {
			movimientoFondoInEntity.setFechaDesde(viewRequest.getFechaDesde().trim());
			movimientoFondoInEntity.setFechaHasta(viewRequest.getFechaHasta().trim());
		}
		return movimientoFondoInEntity;
	}

	/**
	 * Creates the citi in entity.
	 *
	 * @param viewRequest the view request
	 * @return the movimientos fondo citi in entity
	 * @throws DatatypeConfigurationException the datatype configuration exception
	 * @throws ParseException                 the parse exception
	 */
	private MovimientosFondoCitiInEntity createCitiInEntity(MovimientosInView viewRequest)
			throws DatatypeConfigurationException, ParseException {
		MovimientosFondoCitiInEntity inEntity = new MovimientosFondoCitiInEntity();
		String cuentaTitulo = viewRequest.getCuentaTitulo().replace("/", "");
		inEntity.setCodigoFondo(viewRequest.getCodigoFondo());
		inEntity.setCuentaTitulo(CODIGO_AGENTE_CONSTANTE.concat(StringUtils.leftPad(cuentaTitulo, 8, "0")));
		if (viewRequest.getFechaDesde() != null && viewRequest.getFechaHasta() != null) {

			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
			GregorianCalendar gcd = new GregorianCalendar();
			gcd.setTime(sdf.parse(viewRequest.getFechaDesde().trim()));
			XMLGregorianCalendar desde = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcd);
			GregorianCalendar gch = new GregorianCalendar();
			gch.setTime(sdf.parse(viewRequest.getFechaHasta().trim()));
			XMLGregorianCalendar hasta = DatatypeFactory.newInstance().newXMLGregorianCalendar(gch);
			inEntity.setFechaDesde(desde);
			inEntity.setFechaHasta(hasta);
		}
		return inEntity;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<DetalleDeFondoOutView> obtenerDetalleDeFondo(DetalleDeFondoIn viewRequest, Cliente cliente,
			TenenciaFondosSuscritosDTO fondoSeleccionado) {

		DetalleDeFondoOutView detalleDeFondo = new DetalleDeFondoOutView();
		try {

			Respuesta<CotizacionDeFondosView> cotizacionDeFondosViewRespuesta = this.consultarCotizaciones();

			if(EstadoRespuesta.ERROR.equals(cotizacionDeFondosViewRespuesta.getEstadoRespuesta())){
				throw new DAOException("Error en el servicio");
			} else {
				CotizacionDeFondosView cotizacionFondos = cotizacionDeFondosViewRespuesta.getRespuesta();

				for(DatosCotizacionFondoView datosCotizacion : cotizacionFondos.getFondos()) {
					if(viewRequest.getCodigoFondo().equalsIgnoreCase(datosCotizacion.getCodigoFondo())) {
						String variacionCotizacion = ISBANStringUtils
								.formatearSaldosConCerosYSignos(datosCotizacion.getVariacion_Inicio());

						String variacionTreintaDias = ISBANStringUtils
								.formatearSaldosConCerosYSignos(datosCotizacion.getUltimos_30_dias());

						String variacionNoventaDias = ISBANStringUtils
								.formatearSaldosConCerosYSignos(datosCotizacion.getUltimos_90_dias());

						String variacionUltimoAnio = ISBANStringUtils
								.formatearSaldosConCerosYSignos(datosCotizacion.getRenta_12_meses());

						detalleDeFondo.setCotizacion(variacionCotizacion);
						detalleDeFondo.setCotizacionTreintaDias(variacionTreintaDias);
						detalleDeFondo.setCotizacionNoventaDias(variacionNoventaDias);
						detalleDeFondo.setCotizacionUltimoAnio(variacionUltimoAnio);

						detalleDeFondo.setStatusCotizacion(datosCotizacion.getVariacion_Inicio());
						detalleDeFondo.setStatusCotizacionTreintaDias(datosCotizacion.getUltimos_30_dias());
						detalleDeFondo.setStatusCotizacionNoventaDias(datosCotizacion.getUltimos_90_dias());
						detalleDeFondo.setStatusCotizacionUltimoAnio(datosCotizacion.getRenta_12_meses());

						detalleDeFondo.setDescripcion(datosCotizacion.getDescripcion());
						detalleDeFondo.setDescripcionLarga(datosCotizacion.getDescripcionLarga());

						detalleDeFondo.setCartera(datosCotizacion.getLinkCartera());
						detalleDeFondo.setUrlCartera(datosCotizacion.getUrlCartera());
					}
				}

				int longitudCodigo = fondoSeleccionado.getExCiti() != null
						&& FONDO_EXCITI.equalsIgnoreCase(fondoSeleccionado.getExCiti()) ? LARGO_COD_FONDO_FDC
						: LARGO_COD_FONDO_FCI;
				ConsultaFondoOutEntity detalleHonorarios = consultaFondoDAO
						.obtenerPorCodigo(StringUtils.right(viewRequest.getCodigoFondo(), longitudCodigo));
				/* Honorarios */
				detalleDeFondo.setEntrada(detalleHonorarios.getHonorariosEntrada() != null
						? ISBANStringUtils.formatearConComaYDosDecimales(detalleHonorarios.getHonorariosEntrada())
						: null);
				detalleDeFondo.setSalida(detalleHonorarios.getHonorariosSalida() != null
						? ISBANStringUtils.formatearConComaYDosDecimales(detalleHonorarios.getHonorariosSalida())
						: null);
				detalleDeFondo.setAdministrarFondos(detalleHonorarios.getHonorariosAdmin() != null
						? ISBANStringUtils.formatearConComaYDosDecimales(detalleHonorarios.getHonorariosAdmin())
						: null);
				detalleDeFondo.setsGTE(detalleHonorarios.getsGTE() != null
						? ISBANStringUtils.formatearConComaYDosDecimales(detalleHonorarios.getsGTE())
						: null);
				detalleDeFondo.setsDEP(detalleHonorarios.getsDEP() != null
						? ISBANStringUtils.formatearConComaYDosDecimales(detalleHonorarios.getsDEP())
						: null);

				detalleDeFondo.setPlazoDePagos(detalleHonorarios.getPlazoEfectivo());
				detalleDeFondo.setHorarios(detalleHonorarios.getHorario());
			}

		} catch (DAOException e) {
			LOGGER.error("Error en el servicio", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		return respuestaFactory.crearRespuestaOk(DetalleDeFondoOutView.class, detalleDeFondo);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BigDecimal convertirStrToBigDecimalVC(String imp, int cantDecimales) throws ImporteConvertException {
		String importe = imp.substring(0, LIMITE_MAXIMO_CARACTERES_VC);
		if (importe == null || "".equalsIgnoreCase(importe)) {
			throw new ImporteConvertException();
		}

		BigDecimal importeDecimal = null;
		StringBuffer result = new StringBuffer();
		for (int i = importe.length(); i > 0; i--) {
			result.append(importe.charAt(i - 1));
			if (result.length() == cantDecimales) {
				result = result.append(".");
			}
		}

		try {
			importeDecimal = new BigDecimal(StringUtils.reverse(result.toString()));
		} catch (NumberFormatException e) {
			throw new ImporteConvertException(e);
		}
		return importeDecimal;
	}

	/**
	 * Filtra los movimientos devueltos por IATX por importe.
	 *
	 * @param listMovimientos   the list movimientos
	 * @param movimientosInView the movimientos in view
	 * @return the list
	 * @throws ImporteConvertException      the importe convert exception
	 * @throws FondoSinMovimientosException the fondo sin movimientos exception
	 */
	private List<MovimientoFondoEntity> filtrarMovimientosPorImporte(List<MovimientoFondoEntity> listMovimientos,
			MovimientosInView movimientosInView) throws ImporteConvertException, FondoSinMovimientosException {
		List<MovimientoFondoEntity> listMovimientosFiltrados = new ArrayList<MovimientoFondoEntity>();
		BigDecimal importeMinimo = ISBANStringUtils.convertirStrToBigDecimal(movimientosInView.getImporteMinimo(), 0);
		BigDecimal importeMaximo = ISBANStringUtils.convertirStrToBigDecimal(movimientosInView.getImporteMaximo(), 0);
		for (MovimientoFondoEntity movimiento : listMovimientos) {
			BigDecimal importeMovimiento = ISBANStringUtils.convertirStrToBigDecimal(movimiento.getImporte(), 2);

			if (importeMovimiento.compareTo(importeMinimo) == 1 || importeMovimiento.compareTo(importeMinimo) == 0) {
				if (importeMovimiento.compareTo(importeMaximo) == -1
						|| importeMovimiento.compareTo(importeMaximo) == 0) {
					listMovimientosFiltrados.add(movimiento);
				}
			}
		}
		if (listMovimientosFiltrados.isEmpty()) {
			throw new FondoSinMovimientosException();
		}
		return listMovimientosFiltrados;
	}

	/**
	 * Setea los mensajes al objeto TenenciasFondoDTO de respuesta.
	 *
	 * @param tenenciaDTO the tenencia DTO
	 * @return the tenencias fondo DTO
	 */
	private TenenciasFondoDTO obtenerMensajes(TenenciasFondoDTO tenenciaDTO) {
		tenenciaDTO.setMensajeSinTenenciasMobile(
				mensajeBO.obtenerMensajePorCodigo(ERROR_CUENTA_SIN_TENENCIA).getMensaje());
		tenenciaDTO.setMensajeCuentaBloqueada(mensajeBO.obtenerMensajePorCodigo(MENSAJE_CUENTA_BLOQUEADA).getMensaje());
		return tenenciaDTO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<GraficoFondosRTLOutDTO> obtenerGraficosRTL(Cliente cliente) {
		TenenciasFondoDTO tenencias = new TenenciasFondoDTO();
		if (sessionParametros.getTenenciasFondoDTO() != null) {
			tenencias = sessionParametros.getTenenciasFondoDTO();
		} else {
			Respuesta<GraficoFondosRTLOutDTO> respError = new Respuesta<GraficoFondosRTLOutDTO>();
			respError.setEstadoRespuesta(EstadoRespuesta.ERROR);
			return respError;
		}

		GraficosFondosDTO mapGraficos = new GraficosFondosDTO();

		for (CuentaConTenenciaDTO tenencia : tenencias.getListaCuentas()) {
			if (tenencia.getRespuesta().getRespuesta() != null) {
				mapGraficos.cargarPorcentajes(tenencia.getRespuesta().getRespuesta());
			}
		}

		GraficoFondosRTLOutDTO respuesta = mapGraficos.obtenerListaConPorcentajes();
		respuesta.setListaPorcentaje(porcentajeFondosOK(respuesta.getListaPorcentaje()));
		respuesta.setGraficosEnCero(mapGraficos.isGraficosEnCero());
		return respuestaFactory.crearRespuestaOk(GraficoFondosRTLOutDTO.class, respuesta);
	}

	/**
	 * Me fijo si los porcentajes redondeados dan 100 en total, si no dan 100 ajusto
	 * el valor del major porcentaje para que de 100% el total.
	 *
	 * @param listaTenenciaProductos the lista tenencia productos
	 * @return lista de tenencias con los porcentajes actualizados
	 */
	public List<PorcentajeGraficoFondos> porcentajeFondosOK(List<PorcentajeGraficoFondos> listaTenenciaProductos) {
		int sumaTotalPesos = 0;
		int sumaTotalDolares = 0;
		PorcentajeGraficoFondos porcentajeMajorPesos = listaTenenciaProductos.get(0);
		PorcentajeGraficoFondos porcentajeMajorDolares = listaTenenciaProductos.get(0);
		for (PorcentajeGraficoFondos tenencia : listaTenenciaProductos) {
			sumaTotalPesos = sumaTotalPesos + tenencia.getPorcentajeTenenciaPesos();
			sumaTotalDolares = sumaTotalDolares + tenencia.getPorcentajeTenenciaDolares();

			if (tenencia.getPorcentajeTenenciaPesos() > porcentajeMajorPesos.getPorcentajeTenenciaPesos()) {
				porcentajeMajorPesos = tenencia;
			}
			if (tenencia.getPorcentajeTenenciaDolares() > porcentajeMajorDolares.getPorcentajeTenenciaDolares()) {
				porcentajeMajorDolares = tenencia;
			}
		}
		if (sumaTotalPesos != 100 && sumaTotalPesos != 0) {
			int dif = 100 - sumaTotalPesos;
			porcentajeMajorPesos.setPorcentajeTenenciaPesos(porcentajeMajorPesos.getPorcentajeTenenciaPesos() + dif);
		}

		if (sumaTotalDolares != 100 && sumaTotalDolares != 0) {
			int dif = 100 - sumaTotalDolares;
			porcentajeMajorDolares
					.setPorcentajeTenenciaDolares(porcentajeMajorDolares.getPorcentajeTenenciaDolares() + dif);
		}
		return listaTenenciaProductos;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<GraficoFondosBPrivOutDTO> obtenerGraficosBpriv(Cliente cliente) {
		TenenciasFondoDTO tenencias = new TenenciasFondoDTO();

		if (sessionParametros.getTenenciasFondoDTO() != null) {
			tenencias = sessionParametros.getTenenciasFondoDTO();
		} else {
			Respuesta<GraficoFondosBPrivOutDTO> respError = new Respuesta<GraficoFondosBPrivOutDTO>();
			respError.setEstadoRespuesta(EstadoRespuesta.ERROR);
			return respError;
		}

		List<GraficosFondosPorCuentaBPriv> listGraficosPorCuenta = new ArrayList<GraficosFondosPorCuentaBPriv>();

		for (CuentaConTenenciaDTO cuenta : tenencias.getListaCuentas()) {
			GraficosFondosPorCuentaBPriv graficosFondosPorCuentaBPriv = new GraficosFondosPorCuentaBPriv();
			graficosFondosPorCuentaBPriv.setNroCuentaTitulo(cuenta.getNumeroCuentaTitulo());
			if (cuenta.getRespuesta().getRespuesta() != null) {
				GraficosFondosDTO mapGraficos = new GraficosFondosDTO();
				mapGraficos.cargarPorcentajes(cuenta.getRespuesta().getRespuesta());
				GraficoFondosRTLOutDTO respuesta = mapGraficos.obtenerListaConPorcentajes();
				graficosFondosPorCuentaBPriv.setListaPorcentaje(porcentajeFondosOK(respuesta.getListaPorcentaje()));
				graficosFondosPorCuentaBPriv.setPorcentajeTenenciaPesos(respuesta.getPorcentajeTenenciaPesos());
				graficosFondosPorCuentaBPriv.setGraficosEnCero(mapGraficos.isGraficosEnCero());
			}

			listGraficosPorCuenta.add(graficosFondosPorCuentaBPriv);
		}

		GraficoFondosBPrivOutDTO graficoFondosBPrivOutDTO = new GraficoFondosBPrivOutDTO();
		graficoFondosBPrivOutDTO.setListGraficosPorCuenta(listGraficosPorCuenta);
		return respuestaFactory.crearRespuestaOk(GraficoFondosBPrivOutDTO.class, graficoFondosBPrivOutDTO);
	}

	@Override
	public Respuesta<Reporte> obtenerFondosReporte(List<CuentaConTenenciaDTO> listaCuentasFondos,
			TipoBancaEnum tipoBanca, Cliente cliente) {

		FciGeneralExcel fondosExcel = new FciGeneralExcel();
		List<FciCuentaTitulosExcel> listaCuentasTitulos = new ArrayList<FciCuentaTitulosExcel>();

		for (CuentaConTenenciaDTO cuentaDTO : listaCuentasFondos) {
			if (cuentaDTO.getRespuesta().getRespuesta() != null) {
				FciCuentaTitulosExcel cuentaTitulos = new FciCuentaTitulosExcel();
				cuentaTitulos.setNumeroCuenta(
						TipoBancaEnum.BANCA_PERSONAL.equals(tipoBanca) ? cuentaDTO.getNumeroCuentaTitulo()
								: cuentaDTO.getSucursal() + "-" + cuentaDTO.getNumeroCuentaTitulo());
				cuentaTitulos.setIntervinientes(crearListaIntervinientes(cuentaDTO.getIntervinientes()));

				List<FciExcel> listaFciPesos = new ArrayList<FciExcel>();
				List<FciExcel> listaFciDolares = new ArrayList<FciExcel>();

				if (!cuentaDTO.getRespuesta().getRespuesta().getTenenciaFondosSuscritosPesos().isEmpty()) {
					cuentaTitulos.setTieneFciPesos(Boolean.TRUE);
					for (TenenciaFondosSuscritosDTO fondosDTO : cuentaDTO.getRespuesta().getRespuesta()
							.getTenenciaFondosSuscritosPesos()) {
						listaFciPesos.add(new FciExcel(DivisaEnum.PESO.getSimbolo(), fondosDTO, false,
								rescateFondoEspecie.getFondosMap()));
					}
					eliminarTipoSiRepetido(listaFciPesos);
					cuentaTitulos.setFciPesos(listaFciPesos);
				} else {
					listaFciPesos.add(new FciExcel(null, null, true, null));
					cuentaTitulos.setFciPesos(listaFciPesos);
				}

				if (!cuentaDTO.getRespuesta().getRespuesta().getTenenciaFondosSuscritosDolares().isEmpty()) {
					cuentaTitulos.setTieneFciDolares(Boolean.TRUE);
					for (TenenciaFondosSuscritosDTO fondosDTO : cuentaDTO.getRespuesta().getRespuesta()
							.getTenenciaFondosSuscritosDolares()) {
						listaFciDolares.add(new FciExcel(DivisaEnum.DOLAR.getSimbolo(), fondosDTO, false,
								rescateFondoEspecie.getFondosMap()));
					}
					eliminarTipoSiRepetido(listaFciDolares);
					cuentaTitulos.setFciDolares(listaFciDolares);
				} else {
					listaFciDolares.add(new FciExcel(null, null, true, null));
					cuentaTitulos.setFciDolares(listaFciDolares);
				}

				cuentaTitulos.setTotales(new FciTotalesCuenta(cuentaDTO.getRespuesta().getRespuesta()));
				listaCuentasTitulos.add(cuentaTitulos);
			}
		}
		fondosExcel.setListaCuentasTitulos(listaCuentasTitulos);

		return reporteDAO.obtenerReporte(fondosExcel, "fondosComunesInversion", cliente, false);
	}

	private String crearListaIntervinientes(List<Interviniente> listaIntervinientes) {

		StringBuilder intervinientes = new StringBuilder();
		for (Interviniente interviniente : listaIntervinientes) {
			intervinientes.append(interviniente.getApellido() + ", " + interviniente.getNombre() + " / ");
		}

		return intervinientes.substring(0, intervinientes.length() - 2);
	}

	private void eliminarTipoSiRepetido(List<FciExcel> listaFci) {

		String tipoFCI = StringUtils.EMPTY;
		for (FciExcel fondoComunInversion : listaFci) {
			if (tipoFCI.isEmpty()) {
				tipoFCI = fondoComunInversion.getTipo();
			} else if (tipoFCI.equals(fondoComunInversion.getTipo())) {
				fondoComunInversion.setTipo(StringUtils.EMPTY);
			} else {
				tipoFCI = fondoComunInversion.getTipo();
			}
		}
	}

	public DetalleTenenciaValuadaEntity obtenerTenenciaValuadaHoldingBff(Cliente cliente, String segmento) {
		DetalleTenenciaValuadaEntity tenenciaValuada = new DetalleTenenciaValuadaEntity();
		DatosRespuesta datos = new DatosRespuesta();
		try {
			HoldingsResponse tenenciaValuadaBff = fundsApi.getHoldingBff(cliente);
			// Respuesta vacia, se fuerza la ejecucion de PyL
			if (tenenciaValuadaBff == null || tenenciaValuadaBff.getData() == null) {
				throw new ApiException(new ErrorResponse()
						.code(ERROR)
						.message("Respueta sin tenencia para el nup " + cliente.getNup() + ", segmento " + segmento));
			}
			List<HoldingsData> holdings = tenenciaValuadaBff.getData().getHoldings();
			List<ResultadoTenenciaValuadaDetalleFondoOL> resultado = new ArrayList<ResultadoTenenciaValuadaDetalleFondoOL>();

			// For custody accounts
			for (HoldingsData holding : holdings) {
				String custodyAccount = holding.getCustodyAccount();
				Boolean isBp = holding.getIsBp();
				Integer branch = holding.getBranch();
				String altairProduct = holding.getAltairProduct();
				List<Holdings> holdingsData = holding.getHoldings();

				// For holdings
				if (holdingsData != null && !holdingsData.isEmpty()) {
					for (Holdings data : holdingsData) {
						ResultadoTenenciaValuadaDetalleFondoOL resultadoTenenciaValuadaDetalleFondoOL =
								setResultadoTenenciaValuadaDetalleFondoOL(isBp, altairProduct, branch, custodyAccount,
								data, holding);

						if (verificarSegmento(isBp, segmento).equals(Boolean.TRUE)) {
							resultado.add(resultadoTenenciaValuadaDetalleFondoOL);
						}
					}
				}
				tenenciaValuada.setCodigo(0);
				tenenciaValuada.setMensaje("OK");
				tenenciaValuada.setMensajeTecnico("Se pudieron valuar todos los activos de este producto de inversión");
				datos.setResultado(resultado);
				tenenciaValuada.setDatos(datos);
			}
		} catch (ApiException e){
			// Eliminar cache de sesión en el caso de execepcion
			fondosTenenciasPrototype.cleanCacheObpNupHoldingFondosTenenciaBff(cliente);

			String msg = e.getErrorResponse().getMessage();
			LOGGER.error(msg, e);
			throw new ApiException(new ErrorResponse()
					.code(ERROR)
					.message("No se pudieron valuar los activos de este producto de inversion"));
		} catch (Exception e) {
			throw new ApiException(new ErrorResponse()
					.code(ERROR)
					.message("Error al obtener las tenencias desde FONDOS-TENENCIA-BFF - Exception"));
		}

		return tenenciaValuada;
	}

	private String formatearMoneda(String codigoMoneda) {
		return codigoMoneda.equalsIgnoreCase(ARS) ? MONEDA_PESOS : MONEDA_DOLARES;
	}

	/**
	 * Se obtiene el prefijo de la cuenta en base al altair product code
	 */
	private String getTipoCuenta(String altairProduct) {
		if (altairProduct.equalsIgnoreCase(ALTAIR_PRODUCT_03)) {
			return TIPO_CUENTA_PREFIJO_300;
		}

		return TIPO_CUENTA_PREFIJO_700;
	}

	private ResultadoTenenciaValuadaDetalleFondoOL setResultadoTenenciaValuadaDetalleFondoOL(Boolean isBp,
																							 String altairProduct,
																							 Integer branch,
																							 String custodyAccount,
																							 Holdings data,
																							 HoldingsData holding) {
		ResultadoTenenciaValuadaDetalleFondoOL resultadoTenenciaValuadaDetalleFondoOL =
				new ResultadoTenenciaValuadaDetalleFondoOL();

		// Asignar cuenta titulo para RTL o cuenta operativa para BP
		if (Boolean.TRUE.equals(isBp)) {
			String tipoCuenta = getTipoCuenta(altairProduct);
			resultadoTenenciaValuadaDetalleFondoOL.setNumeroCuenta(
					tipoCuenta + Integer.parseInt(holding.getOperativeAccount()));
		} else {
			resultadoTenenciaValuadaDetalleFondoOL.setNumeroCuenta(custodyAccount);
		}
		resultadoTenenciaValuadaDetalleFondoOL.setSucursalCuenta(branch);
		resultadoTenenciaValuadaDetalleFondoOL.setCodigoFondo(Integer.parseInt(data.getFund().getFundCode()));
		resultadoTenenciaValuadaDetalleFondoOL.setTenenciaValuada(
				BigDecimal.valueOf(Double.parseDouble(data.getBalance().getAmount())));
		resultadoTenenciaValuadaDetalleFondoOL.setCantidadCuotapartes(
				new BigDecimal(data.getBalance().getShares())
						.setScale(4, RoundingMode.HALF_DOWN));
		resultadoTenenciaValuadaDetalleFondoOL.setValorCuotaparte(
				new BigDecimal(data.getBalance().getPrice())
						.setScale(6, RoundingMode.HALF_DOWN));

		resultadoTenenciaValuadaDetalleFondoOL.setMoneda(
				formatearMoneda(data.getBalance().getCurrency()));
		resultadoTenenciaValuadaDetalleFondoOL.setDescripcionFondo(data.getFund().getName());

		if (data.getPerformance() != null) {
			resultadoTenenciaValuadaDetalleFondoOL.setResultadoBruto(
					BigDecimal.valueOf(data.getPerformance().getResult())
							.setScale(2, RoundingMode.HALF_DOWN));
			resultadoTenenciaValuadaDetalleFondoOL.setTenenciaValuadaCompra(
					BigDecimal.valueOf(data.getPerformance().getCostHoldingValue())
							.setScale(2, RoundingMode.HALF_DOWN));
			resultadoTenenciaValuadaDetalleFondoOL.setpPPC(
					BigDecimal.valueOf(data.getPerformance().getPppFifo()));
		}

		return resultadoTenenciaValuadaDetalleFondoOL;
	}

	private Boolean verificarSegmento(Boolean isBp, String segmento) {
		return ((Boolean.TRUE.equals(isBp) && segmento.equalsIgnoreCase(BP)) ||
				(Boolean.FALSE.equals(isBp) && segmento.equalsIgnoreCase(RTL)));
	}

	private void cleanCacheHoldingBff(String cuentaTitulo, Cliente cliente, String segmento) {
		Boolean holdingsByBff = fundsApi.verifyAccessToGetHolding(cliente);
		if (holdingsByBff.equals(Boolean.TRUE)) {
			fundsApi.cleanCacheFunds(cuentaTitulo, cliente, SUSCRIPCION, segmento);
			fondosTenenciasPrototype.cleanCacheObpNupHoldingFondosTenenciaBff(cliente);
			try {
				fundsApi.getHoldingBff(cliente);
			} catch (ApiException e) {
				LOGGER.error("Error al obtener las tenencias desde FONDOS-TENENCIA-BFF", e);
			} catch (Exception e) {
				LOGGER.error("Error al obtener las tenencias desde FONDOS-TENENCIA-BFF - Exeption");
			}
		}
	}

}
