/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.bo;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.internal.org.apache.commons.codec.binary.Base64;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.iatx.helpers.SessionUsuarioProvider;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ScompBO;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.PlazoFijoUVABuilder;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.excel.dao.ReporteDAO;
import ar.com.santanderrio.obp.servicios.comun.excel.helpers.ExcelBuilderHelper;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaBancaPrivada;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.inversiones.TipoMonedaInversionEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.bo.InversionesAbstractBO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.entity.PerfilInversorRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DetallePFInteresanteInView;
import ar.com.santanderrio.obp.servicios.inversiones.exception.FechaMinimaPrecancelacionException;
import ar.com.santanderrio.obp.servicios.inversiones.exception.ImporteMayorAlMaximoException;
import ar.com.santanderrio.obp.servicios.inversiones.exception.LimiteDePrecancelacionSuperadoException;
import ar.com.santanderrio.obp.servicios.inversiones.exception.PrecancelacionSolicitadaException;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dao.TenenciaValuadaDAO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.CuentaOPEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DatosDetalleFondoRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DatosRespuestaPF;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DetalleFondoRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ResultadoTenenciaValuadaDetallePFOL;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.Segmento;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CuentaTituloView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.bancaprivada.dao.PlazoFijoBPrivDAO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.consulta.dao.ConsultaPlazoFijoDAO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dao.PlazoFijoDAO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.AltaComprobantePlazoFijoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.ComprobantePlazoFijoInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.ComprobantePlazoFijoOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.CondicionesDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.ContenidoTenenciaBprivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.FinalizarPlazoFijoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.InteresesDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.MinimosPlazoFijoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.ObtenerPlazoFijosParaSimularDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.PlazoFijoVencidoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.SimulacionPrecancelableDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.SimulacionPrecancelableUVADTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.SimularPlazoFijoInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.SimularPlazoFijoOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.TenenciaPlazoFijoBprivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.TenenciaTotalPlazoFijoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.AccionAlVencimientoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaInteresantePlazoFijoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaInteresantePlazoFijoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaPrecancelablePlazoFijoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaPrecancelablenPlazoFijoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaTasasPlazoFijoBPrivOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaTiposPlazoFijoBPrivEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaTiposPlazoFijoBPrivOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.DescripcionAccionAlVencimientoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.DetallePFInteresanteInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.DetalleTenenciaValuadaPFEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.FrecuenciaCobroPFInteresanteOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ImposicionPlazoFijoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ImposicionPlazoFijoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.MantenimientoPlazoFijoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.MantenimientoPlazoFijoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.MontoPlazoFijo;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.PFInteresanteEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.PlazoFijoEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.PlazoFijoEnum;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.PrecancelacionPlazoFijoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.RecomendacionResponseEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.RouterApiRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.RouterApiResponseEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.SimulacionPlazoFijoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.SimulacionPlazoFijoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.SimulacionPrecancelableOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.SimulacionPrecancelableUvaOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.TasasPlazoFijoBPrivEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.TenenciaValuadaPlazoFijoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.excel.CuentaTitulosPFExcelGeneral;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.excel.TenenciaPlazoFijoExcel;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.excel.TenenciaPlazoFijoExcelValores;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.excel.TotalesPlazoFijoExcel;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.AccionAlVencimientoView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.AccionesAlVencimientoInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.AccionesAlVencimientoOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ConsultaTasasPlazosFijoView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.DetalleCobroInteresesViewResponse;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.DetalleInteresesViewRequest;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ItemDetalleIntereses;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ModificarAccionInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.RecomendacionInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.SimularPrecancelarInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.SolicitarPrecancelarInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.TasasPorPlazoView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.TenenciaPlazoFijoView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ValoresTasa;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;
import ar.com.santanderrio.obp.servicios.tenencias.dto.SolicitarPrecancelarOutDTO;
import ar.com.santanderrio.obp.servicios.transferencias.exception.SaldoInsuficienteException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TimeOutException;
import isban.commons.cryptography.crypto.InvalidKeyException;



/**
 * The Class PlazoFijoBOImpl.
 *
 * @author juan.pablo.picate
 */
@Component
public class PlazoFijoBOImpl extends InversionesAbstractBO implements PlazoFijoBO {

	/** The Constant GUION_MEDIO. */
	private static final String GUION_MEDIO = "-";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PlazoFijoBOImpl.class);

	/** The Constant LARGO_COD_SUCURSAL. */
	private static final int LARGO_COD_SUCURSAL = 3;

	/** The Constant MONEDA_DOLARES. */
	private static final String MONEDA_DOLARES = "USD";

	/** The Constant MONEDA_PESOS. */
	private static final String MONEDA_PESOS = "ARS";

	/** The Constant DECIMALES_IMPORTE_MINIMO. */
	private static final int DECIMALES_IMPORTE_MINIMO = 2;

	/** The Constant DECIMALES_PLAZO_FIJO. */
	private static final int DECIMALES_PLAZO_FIJO = 2;

	/** The Constant DECIMALES_PLAZO_FIJO_TNA. */
	private static final int DECIMALES_PLAZO_FIJO_TNA = 5;

	/** The Constatnt SIN_TENENCIA_PLAZO_FIJO_BPRIV. */
	private static final String SIN_TENENCIA_PLAZO_FIJO_BPRIV = "Esta cuenta no registra tenencia";

	/** The largo cantidad dias. */
	private static int LARGO_CANTIDAD_DIAS = 5;

	/** The decimales porcentaje penalizacion. */
	private static int DECIMALES_PORCENTAJE_PENALIZACION = 5;

	/** The Constant LARGO_PLAZO. */
	private static final int LARGO_PLAZO = 5;

	/** The Constant DECIMALES_PLAZO_FIJO_CERO. */
	private static final int DECIMALES_PLAZO_FIJO_CERO = 0;

	/** The Constant NUEVE_INT. */
	private static final int NUEVE_INT = 9;

	/** The Constant CUATRO_INT. */
	private static final int CUATRO_INT = 4;

	/** The Constant LONGITUD_UNO. */
	private static final int LONGITUD_UNO = 1;

	/** The Constant LARGO_IMPORTE_PLAZO_FIJO. */
	private static final int LARGO_IMPORTE_PLAZO_FIJO = 15;

	/** The Constant LARGO_TIPO_CUENTA_DEBITO. */
	private static final int LARGO_TIPO_CUENTA_DEBITO = 2;

	/** The Constant LARGO_NRO_CUENTA_DEBITO. */
	private static final int LARGO_NRO_CUENTA = 7;

	/** The Constant ATIT. */
	private static final String CODIGO_TPF_INTERESANTE = "17";

	/** The Constant LONGITUD_PLAZO. */
	private static final int LONGITUD_PLAZO = 5;

	/** The Constant LONGITUD_SECUENCIA. */
	private static final int LONGITUD_SECUENCIA = 5;

	/** The Constant LONGITUD_SPREAD. */
	private static final int LONGITUD_SPREAD = 9;

	/** The Constant CERO_STRING. */
	private static final String CERO_STRING = "0";

	/** The Constant LARGO_TASA. */
	private static final int LARGO_TASA = 8;

	/** The Constant LARGO_TASA_BP. */
	private static final int LARGO_TASA_BP = 9;

	/** The Constant CODIGO_OK. */
	private static final Integer CODIGO_OK = 0;

	/** The Constant CODIGO_OK. */
	private static final Integer CODIGO_PARCIAL = 1;

	/** The Constant ESTADO_NO_DISPONIBLE. */
	private static final String ESTADO_NO_DISPONIBLE = "X";

	/** The Constant ESTADO_NO_DISPONIBLE_BPRIV. */
	private static final String ESTADO_NO_DISPONIBLE_BPRIV = "L";

	/** The Constant ESTADO_NO_DISPONIBLE_BPRIV. */
	private static final String ESTADO_NO_DISPONIBLE_BPRIV2 = "P";

	/** The Constant FINALIZAR_SUSCRIPCION_FALLO_GENERICO. */
	public static final String PRECANCELABLE_NO_CUMPLE_PLAZO_MINIMO = "10490";

	/** The Constant FINALIZAR_SUSCRIPCION_FALLO_GENERICO. */
	public static final String ERROR_PARCIAL_PLAZO_FIJO = "10485";

	/** The Constant INICIO_CENTRO_CTA_INVERSION. */
	private static final int INICIO_CENTRO_CTA_INVERSION = 4;

	/** The Constant FIN_CENTRO_CTA_INVERSION. */
	private static final int FIN_CENTRO_CTA_INVERSION = 8;

	/** The Constant ULTIMOS_DOCE. */
	private static final int ULTIMOS_DOCE = 12;

	/** The Constant RENOVACION_CAPITAL. */
	private static final String RENOVACION_CAPITAL = "RC";

	/** The Constant RENOVACION_CAPITAL_E_INTERESES. */
	private static final String RENOVACION_CAPITAL_E_INTERESES = "RCI";

	/** The Constant SI. */
	private static final String SI = "S";

	/** The Constant NO. */
	private static final String NO = "N";

	/** The Constant ESPACIO_EN_BLANCO. */
	private static final String ESPACIO_EN_BLANCO = " ";

	/** The Constant DIEZ_ESPACIOS. */
	private static final String DIEZ_ESPACIOS = "          ";

	/** The Constant FORMATO_FECHA. */
	public static final String FORMATO_FECHA = "dd/MM/yyyy";

	/** The Constant SEPARATOR_BARRA_LATERAL. */
	public static final String SEPARATOR_BARRA_LATERAL = "/";

	/** The Constant LARGO_CUENTA_PLAZO. */
	private static final int LARGO_CUENTA_PLAZO = 20;

	/** The Constant PF_VENCIDOS_OTROS. */
	private static final String PF_VENCIDOS_OTROS = "1";

	/** The Constant PF_VENCIDOS_OTROS_2. */
	private static final String PF_VENCIDOS_OTROS_2 = "901";

	/** The Constant VENCIDOS_A_COBRAR. */
	private static final String VENCIDOS_A_COBRAR = "VENCIDOS_A_COBRAR";

	/** The Constant OTROS_VENCIDOS. */
	private static final String OTROS_VENCIDOS = "OTROS_VENCIDOS";

	/** The Constant INICIO_MES. */
	private static final int INICIO_MES = 3;

	/** The Constant CUENTA_UNICA. */
	private static final String CUENTA_UNICA = "2";

	/** The Constant CodProducto correspondiente a PF tipo CER. */
	private static final String PLAZO_FIJO_CER = "30";

	/** The Constant CodProducto correspondiente a PF tipo CER. */
	private static final String PLAZO_FIJO_CER_BPRIV = "AJ";

	/** The Constant DECIMALES_SALDO_INIC_UR. */
	private static final int DECIMALES_SALDO_INIC_UR = 4;

	/** The Constant DECIMALES_COTIZACION_CODIGO_UR. */
	private static final int DECIMALES_COTIZACION_CODIGO_UR = 8;

	/** The Constant LARGO_COTIZACION_CODIGO_UR. */
	private static final int LARGO_COTIZACION_CODIGO_UR = 13;

	/** The Constant LARGO_SALDO_INIC_UR. */
	private static final int LARGO_SALDO_INIC_UR = 17;

	/** The Constant DECIMALES_INTERESES. */
	private static final int DECIMALES_INTERESES = 2;

	private static final int COD_PF_REPATRIACION = 94;

	/** The Constant CODIGO_NO_DISPONIBLE. */
	private static final String CODIGO_NO_DISPONIBLE = "10099906";
	
	private static final String VERSION_110 = "110";
	
	/** Constante Canal RTL */
	private String canalRTL = "04";

	/** Constante SubCanal RTL */
	private String subCanalRTL = "0099";
	
	/** Constante Canal BP */
	private String canalBP = "79";

	/** Constante Subancal BP */
	private String subCanalBP = "0000";

	/** dao. */
	@Autowired
	private PlazoFijoDAO plazoFijoDAO;

	/** The consulta plazo fijo DAO. */
	@Autowired
	private ConsultaPlazoFijoDAO consultaPlazoFijoDAO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The legal bo. */
	@Autowired
	private LegalBO legalBO;

	/** The tenencia valuada. */
	@Autowired
	private TenenciaValuadaDAO tenenciaValuadaDAO;

	/** the Cuenta bo. */
	@Autowired
	private CuentaBO cuentaBO;

	/** The session parametros. */
	@Autowired
	private SesionParametros sessionParametros;

	/** The scomp BO. */
	@Autowired
	private ScompBO scompBO;
	
	@Autowired
	protected SesionCliente sesionCliente;

	/** The credential security factory. */
	@Autowired
	private CredentialSecurityFactory credentialSecurityFactory;

	/** The plazo fijo B priv DAO. */
	@Autowired
	private PlazoFijoBPrivDAO plazoFijoBPrivDAO;

	@Autowired
	private ReporteDAO reporteDAO;
	
	@Autowired
    private ModuloPermisoBO moduloPermisoBO;


	/** The Constant BANCA_RETAIL. */
	private static final String BANCA_RETAIL = "BR";

	/** The Constant BANCA_PERSONAL. */
	private static final String BANCA_PERSONAL = "BANCAPERSONAL";

	/** The Constant BANCA_PRIVADA. */
	private static final String BANCA_PRIVADA = "BANCAPRIVADA";

	/** The Constant BP. */
	private static final String BP = "BP";

	/** The Constant IMPORTE_MINIMO. */
	private static final String IMPORTE_MINIMO = "50000";

	/** The dato firmado tipo. */
	@Value("${INVERSIONES.FIRMA.DATO}")
	private String datoTitulos;
	
    /** The iatx cics. */
    @Value("${IATX.CICS}")
    private String iatxCics; 
    
	/** The trans id. */
	@Value("${IATX.TRANSID}")
	private String transId;
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<SimularPlazoFijoOutDTO> simularPlazoFijo(SimularPlazoFijoInDTO inDTO, Cliente cliente,
			int cantCuentas) {

		Cuenta cuenta = obtenerCuentaDebito(cliente, inDTO.getNumeroCuentaDebito());

		if (cuenta == null) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		boolean pfRepatriacion = esCuentaRepatriacion(cuenta);

		List<PlazoFijoEntity> tiposPlazoFijosHabilitados = null;
		String moneda = obtenerCodigoMoneda(inDTO.getDivisa());
		try {
			tiposPlazoFijosHabilitados = consultaPlazoFijoDAO.obtenerPlazosFijosHabilitadosPorMoneda(moneda);
		} catch (DAOException daoe) {
			LOGGER.error("Error al consultar PlazoFijo DAO con ", cliente, daoe);
			return respuestaFactory.crearRespuestaError(SimularPlazoFijoOutDTO.class, null);
		}

		
		SimulacionPlazoFijoInEntity inEntity = createEntityRequest(inDTO, cuenta);
		List<CondicionesDTO> listaCondiciones = new ArrayList<CondicionesDTO>();
		List<CondicionesDTO> listaCondicionesVenta = new ArrayList<CondicionesDTO>();

		ObtenerPlazoFijosParaSimularDTO tiposPlazoFijosHabilitadosFiltrados;
		try {
			tiposPlazoFijosHabilitadosFiltrados = obtenerPlazoFijosParaSimular(inDTO,
					obtenerPlazoFijosHabilitados(cliente, tiposPlazoFijosHabilitados, pfRepatriacion),
					tiposPlazoFijosHabilitados);
		} catch (DAOException e) {
			LOGGER.error("Error fallo de servicio para pf tradicional repatriación", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		if(pfRepatriacion && !existePfRepatriacion(tiposPlazoFijosHabilitadosFiltrados)) {
			LOGGER.error("El servicio no devuelve pf tradicional repatriación");
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		for (PlazoFijoEntity tipoPF : tiposPlazoFijosHabilitadosFiltrados.getPlazosFijosParaSimular()) {
			if(!pfRepatriacion && String.valueOf(COD_PF_REPATRIACION).equals(tipoPF.getCodigoPlazo())) {
				continue;
			}
			inEntity.setTipoPF(formatearTipoPlazo(tipoPF.getCodigoPlazo()));
			inEntity.setCantidadDias(StringUtils.leftPad(tipoPF.getMinDiasLiqInt(), LARGO_CANTIDAD_DIAS, "0"));
			try {
				SimulacionPlazoFijoOutEntity outDAO = plazoFijoDAO.simularPlazoFijo(inEntity, cliente, pfRepatriacion);
				CondicionesDTO condicionesDTO = createDTOResponse(inEntity, outDAO, tipoPF);
				listaCondiciones.add(condicionesDTO);
			} catch (ImporteMayorAlMaximoException e) {
				LOGGER.error("Error Importe Mayor al Maximo. Codigo del Plazo:" + tipoPF.getCodigoPlazo(), e); 
				// se comenta esta condicion, ya que esta excepcion afecta a cualquier plazo, no solo a Repatriacion
				// if(String.valueOf(COD_PF_REPATRIACION).equals(tipoPF.getCodigoPlazo())) {
					LOGGER.error("Error fallo de servicio para pf {}", tipoPF.getDescripcion(), e);
					return respuestaFactory.crearRespuestaWarning("", TipoError.ERROR_MONTO_EXCEDE_MAXIMO_TRANSACCIONA,
							CodigoMensajeConstantes.PLAZO_FIJO_LIMITE_DIARIO_ALCANZADO);
				// }
			} catch (SaldoInsuficienteException ex) {
				LOGGER.error("Error Saldo Insuficiente. Codigo del Plazo:" + tipoPF.getCodigoPlazo(), ex);
				return errorSaldoInsuficiente(cliente, cantCuentas);
			} catch (DAOException e) {
				if ("CUENTA_INACTIVA".equals(e.getMessage())) {
					return errorCuentaInactiva();
				}
				if ("CUENTA_SIMULACION_NO_SELECT".equals(e.getMessage())) {
					if (PlazoFijoEnum.UVA_PRECANCELABLE.getCodigo().equals(tipoPF.getCodigoPlazo())) {
						continue;
					}
					return errorCuentaSimualcionNoSelect();
				}
				if ("PLAZOS_FIJOS_NO_DISPONIBLE".equals(e.getMessage())) {
					return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, 
							CodigoMensajeConstantes.ERROR_PLAZOS_FIJOS_NO_DISPONIBLE);
				}
				if(String.valueOf(COD_PF_REPATRIACION).equals(tipoPF.getCodigoPlazo())) {
					LOGGER.error("Error fallo de servicio para pf {}", tipoPF.getDescripcion(), e);
					return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
							CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
				}
				LOGGER.error("Error fallo de servicio", e);
			}
		}

//		for (PlazoFijoEntity tipoPFVenta : tiposPlazoFijosHabilitadosFiltrados.getPlazosFijosDeVenta()) {
//			inEntity.setTipoPF(formatearTipoPlazo(tipoPFVenta.getCodigoPlazo()));
//			inEntity.setCantidadDias(StringUtils.leftPad(tipoPFVenta.getMinDiasLiqInt(), LARGO_CANTIDAD_DIAS, "0"));
//			CondicionesDTO condicionesVentaDTO = createVentasDTOResponse(inEntity, tipoPFVenta);
//			listaCondicionesVenta.add(condicionesVentaDTO);
//		}

		String tipoBanca = "";
		if (!StringUtils.isBlank(sessionParametros.getTipoBancaPlazoFijo())) {
			tipoBanca = BANCA_PRIVADA;
		} else {
			tipoBanca = BANCA_RETAIL;
		}

		if (tipoBanca.equals(BANCA_RETAIL)) {
			if (listaCondiciones.isEmpty()) {
				LOGGER.error("Lista vacia de Plazos Fijos a simular.");
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
		}

		SimularPlazoFijoOutDTO responseDTO = new SimularPlazoFijoOutDTO();
		responseDTO.setConsultarCondiciones(listaCondiciones);
		responseDTO.setListaPlazoFijosVentas(listaCondicionesVenta);
		
		
		return respuestaFactory.crearRespuestaOk(SimularPlazoFijoOutDTO.class, responseDTO);
	}

	
	private boolean existePfRepatriacion(ObtenerPlazoFijosParaSimularDTO tiposPlazoFijosHabilitadosFiltrados) {
		for (PlazoFijoEntity tipoPF : tiposPlazoFijosHabilitadosFiltrados.getPlazosFijosParaSimular()) {
			if(String.valueOf(COD_PF_REPATRIACION).equals(tipoPF.getCodigoPlazo())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean esCuentaRepatriacion(Cuenta cuenta) {
		return cuenta.getTipoCuentaEnum().equals(TipoCuenta.CAJA_AHORRO_DOLARES)
				&& cuenta.getProductoAltair().equals("03") && cuenta.getSubproductoAltair().equals("0005");
	}

	/**
	 * Maneja el error a retornar en el caso de un saldo insuficiente de parte de la
	 * simulacion, distinto mensaje si tiene una o mas cuentas debito.
	 *
	 * @param cliente     the cliente
	 * @param cantCuentas the cant cuentas
	 * @return the respuesta
	 */
	private Respuesta<SimularPlazoFijoOutDTO> errorSaldoInsuficiente(Cliente cliente, int cantCuentas) {
		String codigoError = CodigoMensajeConstantes.SALDO_INSUFICIENTE_UNA_CUENTA;
		if (cantCuentas > 1) {
			codigoError = CodigoMensajeConstantes.SALDO_INSUFICIENTE_MULTIPLES_CUENTAS;
		}
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SALDO_INSUFICIENTE, codigoError);
	}

	/**
	 * Error cuenta inactiva.
	 *
	 * @return the respuesta
	 */
	private Respuesta<SimularPlazoFijoOutDTO> errorCuentaInactiva() {
		String codigoErrorCuentaInactiva = "10551";
		String tipoErrorCuentaInactiva = "CUENTA_INACTIVA";
		return respuestaFactory.crearRespuestaError("", tipoErrorCuentaInactiva, codigoErrorCuentaInactiva);
	}

	private Respuesta<SimularPlazoFijoOutDTO> errorCuentaSimualcionNoSelect() {
		String codigoErrorCuentaInactiva = "10552";
		String tipoErrorCuentaInactiva = "CUENTA_SIMULACION_NO_SELECT";
		return respuestaFactory.crearRespuestaError("", tipoErrorCuentaInactiva, codigoErrorCuentaInactiva);
	}

	/**
	 * Obtener cuenta debito.
	 *
	 * @param cliente            the cliente
	 * @param numeroCuentaDebito the numero cuenta debito
	 * @return the cuenta
	 */
	private Cuenta obtenerCuentaDebito(Cliente cliente, String numeroCuentaDebito) {
		Cuenta cuentaRes = null;
		List<Cuenta> cuentas = cliente.getCuentas();
		for (Cuenta cuenta : cuentas) {
			String numeroDeCuentaFormateado = ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuenta);
			if (numeroCuentaDebito.equals(numeroDeCuentaFormateado)) {
				cuentaRes = cuenta;
				return cuentaRes;
			}
		}

		cuentas = cliente.getCuentasPrivadas();
		for (Cuenta cuenta : cuentas) {
			String numeroDeCuentaFormateado = ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuenta);
			if (numeroCuentaDebito.equals(numeroDeCuentaFormateado)) {
				cuentaRes = cuenta;
				return cuentaRes;
			}
		}
		return cuentaRes;
	}

	/**
	 * Obtener codigo moneda.
	 *
	 * @param monedaIn the moneda in
	 * @return the string
	 */
	private String obtenerCodigoMoneda(String monedaIn) {
		String codigoMoneda = "02";
		if ("$".equals(monedaIn)) {
			codigoMoneda = "00";
		}
		return codigoMoneda;
	}

	/**
	 * Formatear tipo plazo.
	 *
	 * @param tipoPlazo the tipo plazo
	 * @return the string
	 */
	private String formatearTipoPlazo(String tipoPlazo) {
		String result = tipoPlazo;
		if (tipoPlazo.length() == LONGITUD_UNO) {
			result = CERO_STRING + tipoPlazo;
		}
		return result;
	}

	/**
	 * Obtner divisa.
	 *
	 * @param divisa the divisa
	 * @return the string
	 */
	private String obtnerDivisa(String divisa) {
		String moneda = "USD";
		if (("$".equals(divisa)) || ("1".equals(divisa))) {
			moneda = "ARS";
		}
		return moneda;

	}

	/**
	 * Obtiene descripcion de la divisa.
	 *
	 * @param divisa the divisa
	 * @return the string
	 */
	private String obtenerMoneda(String divisa) {
		String moneda = "Dolares";
		if ((MONEDA_PESOS.equals(divisa))) {
			moneda = "Pesos";
		}
		return moneda;
	}

	/**
	 * Creates the entity request.
	 *
	 * @param inDTO  the in DTO
	 * @param cuenta the cuenta
	 * @return the simulacion plazo fijo in entity
	 */
	private SimulacionPlazoFijoInEntity createEntityRequest(SimularPlazoFijoInDTO inDTO, Cuenta cuenta) {
		SimulacionPlazoFijoInEntity inEntity = new SimulacionPlazoFijoInEntity();

		if (BANCA_RETAIL.equals(sessionParametros.getTipoBancaPlazoFijo())) {
			String sucursal = cuenta.getNroSucursal().substring(1);
			inEntity.setSucursalCuentaDebito(sucursal);
			inEntity.setNumeroCuentaDebito(cuenta.getNroCuentaProducto().substring(NUEVE_INT));
			inEntity.setNroContratoAltair(StringUtils.right(cuenta.getContratoAltair(), 12));
		} else {
			inEntity.setSucursalCuentaDebito(cuenta.getNroSucursal());
			inEntity.setNumeroCuentaDebito(cuenta.getContratoAltair().substring(CUATRO_INT));
		}

		inEntity.setFechaAlta(new SimpleDateFormat("yyyyMMdd").format(new Date()));
		String plazo = inDTO.getPlazo();
		inEntity.setPlazo(StringUtils.leftPad(plazo, LARGO_PLAZO, "0"));
		inEntity.setImportePlazoFijo(
				ISBANStringUtils.ajustadorBigDecimalIatx(inDTO.getImportePlazoFijo(), LARGO_IMPORTE_PLAZO_FIJO));
		inEntity.setDivisa(obtnerDivisa(inDTO.getDivisa()));
		inEntity.setSucursalRadicacion(cuenta.getNroSucursal());

		String tipoCuenta = cuenta.getTipoCuenta().substring(1);

		if (tipoCuenta.equals(TipoCuenta.CUENTA_UNICA.getCodigo().toString())) {
			if (inDTO.getDivisa().equals(TipoMonedaInversionEnum.USD.getSimbolo())) {
				inEntity.setTipoCuentaDebito(StringUtils.leftPad(TipoCuenta.CUENTA_UNICA_DOLARES.getCodigo().toString(),
						LARGO_TIPO_CUENTA_DEBITO, "0"));
			} else {
				inEntity.setTipoCuentaDebito(StringUtils.leftPad(TipoCuenta.CUENTA_UNICA_PESOS.getCodigo().toString(),
						LARGO_TIPO_CUENTA_DEBITO, "0"));
			}
		} else {
			inEntity.setTipoCuentaDebito(cuenta.getTipoCuenta());
		}
		return inEntity;
	}

	/**
	 * Creates the DTO response.
	 *
	 * @param inEntity the in entity
	 * @param outDAO   the out DAO
	 * @param tipoPF   the tipo PF
	 * @return the condiciones DTO
	 */
	private CondicionesDTO createDTOResponse(SimulacionPlazoFijoInEntity inEntity, SimulacionPlazoFijoOutEntity outDAO,
			PlazoFijoEntity tipoPF) {
		CondicionesDTO condicionesDTO = new CondicionesDTO();

		try {
			BigDecimal intereses = ISBANStringUtils.convertirStrToBigDecimal(outDAO.getInteres(), DECIMALES_PLAZO_FIJO);
			condicionesDTO.setIntereses(intereses);
			BigDecimal importe = ISBANStringUtils.convertirStrToBigDecimal(inEntity.getImportePlazoFijo(),
					DECIMALES_PLAZO_FIJO);
			condicionesDTO.setImporte(importe);
			BigDecimal plazoVencimiento = ISBANStringUtils.convertirStrToBigDecimal(outDAO.getPlazo(),
					DECIMALES_PLAZO_FIJO_CERO);
			condicionesDTO.setPlazoVencimiento(plazoVencimiento);
			BigDecimal tna = ISBANStringUtils.convertirStrToBigDecimal(outDAO.getTasa(), DECIMALES_PLAZO_FIJO_TNA);
			condicionesDTO.setTna(tna, plazoVencimiento);

			
			if("USD".equalsIgnoreCase(inEntity.getDivisa())){
				BigDecimal impuInt = ISBANStringUtils.convertirStrToBigDecimal(outDAO.getImpuestosPF().get(0).getMontoMonedaExtranjera(), DECIMALES_PLAZO_FIJO);
				if (impuInt.compareTo(BigDecimal.ZERO) != 0) {
					BigDecimal interes = ISBANStringUtils.convertirStrToBigDecimal(outDAO.getInteres(),
							DECIMALES_PLAZO_FIJO);
					condicionesDTO.setIni(interes.subtract(impuInt));
				}
			}else {
				BigDecimal impuInt = ISBANStringUtils.convertirStrToBigDecimal(outDAO.getImpuestosPF().get(0).getMontoMonedaLocal(), DECIMALES_PLAZO_FIJO);
				if (impuInt.compareTo(BigDecimal.ZERO) != 0) {
					BigDecimal interes = ISBANStringUtils.convertirStrToBigDecimal(outDAO.getInteres(),
							DECIMALES_PLAZO_FIJO);
					condicionesDTO.setIni(interes.subtract(impuInt));
				}
			}
//			BigDecimal impuInt = ISBANStringUtils.convertirStrToBigDecimal(outDAO.getImpuInt(), DECIMALES_PLAZO_FIJO);
//			if (impuInt.compareTo(BigDecimal.ZERO) != 0) {
//				BigDecimal interes = ISBANStringUtils.convertirStrToBigDecimal(outDAO.getInteres(),
//						DECIMALES_PLAZO_FIJO);
//				condicionesDTO.setIni(interes.subtract(impuInt));
//			}
			BigDecimal saldoInicUr = ISBANStringUtils.convertirStrToBigDecimal(outDAO.getSaldoInicUr(),
					DECIMALES_SALDO_INIC_UR);
			condicionesDTO.setSaldoInicUr(saldoInicUr);
			BigDecimal cotizacionCodigoUr = ISBANStringUtils.convertirStrToBigDecimal(outDAO.getCotizacionCodigoUr(),
					DECIMALES_COTIZACION_CODIGO_UR);
			condicionesDTO.setCotizacionCodigoUr(cotizacionCodigoUr);
		} catch (ImporteConvertException e) {
			LOGGER.error("Error al convertir String a BigDecimal ", e);
		}

		condicionesDTO.setFechaVencimiento(outDAO.getFechaProven());
		condicionesDTO.setNombrePlazoFijo(tipoPF.getNombreLegal());
		condicionesDTO.setDescripcionPF(tipoPF.getDescripcion());
		condicionesDTO.setInformacion(tipoPF.getAyuda());
		condicionesDTO.setTarifa(outDAO.getTarifa());
		condicionesDTO.setTasaVariable(outDAO.getTasaVarMinimoGarantizada());
		condicionesDTO.setCantidadDeDias(tipoPF.getMinDiasLiqInt());
		condicionesDTO.setTipoPF(outDAO.getTipoPF());
		condicionesDTO.setFechaAltaReal(outDAO.getFechaAltaReal());
		condicionesDTO.setImpuestosPF(outDAO.getImpuestosPF());
		condicionesDTO.setInteresantePF(outDAO.getInteresantePF());
		condicionesDTO.setMostrarFecuenciaIntereses(tipoPF.getMostrarFrecIntereses());
		condicionesDTO.setProducto(outDAO.getProducto());
		condicionesDTO.setSubproducto(outDAO.getSubproducto());
		condicionesDTO.setPeriodoLiquidacion(outDAO.getPerLiq());
		condicionesDTO.setFechaMinimaPrecancelar(outDAO.getFechaMinimaPrecancelar());
		condicionesDTO.setPorcentajePenalizacion(outDAO.getPorcentajePenalizacion());
		condicionesDTO.setPrioridad(tipoPF.getPrioridad());

		return condicionesDTO;

	}

//	/**
//	 * Creates the Ventas DTO response.
//	 *
//	 * @param inEntity the in entity
//	 * @param tipoPF   the tipo PF
//	 * @return the condiciones DTO
//	 */
//	private CondicionesDTO createVentasDTOResponse(SimulacionPlazoFijoInEntity inEntity, PlazoFijoEntity tipoPF) {
//		CondicionesDTO condicionesDTO = new CondicionesDTO();
//
//		try {
//			BigDecimal importe = ISBANStringUtils.convertirStrToBigDecimal(tipoPF.getImporteMinimo(),
//					DECIMALES_PLAZO_FIJO);
//			condicionesDTO.setImporte(importe);
//			BigDecimal plazoVencimiento = ISBANStringUtils.convertirStrToBigDecimal(tipoPF.getPlazoTaza(),
//					DECIMALES_PLAZO_FIJO_CERO);
//			condicionesDTO.setPlazoVencimiento(plazoVencimiento);
//		} catch (ImporteConvertException e) {
//			LOGGER.error("Error al convertir String a BigDecimal ", e);
//		}
//		condicionesDTO.setNombrePlazoFijo(tipoPF.getNombreLegal());
//		condicionesDTO.setInformacion(tipoPF.getAyuda());
//		condicionesDTO.setPrioridad(tipoPF.getPrioridad());
//		condicionesDTO.setDescripcionPF(tipoPF.getDescripcion());
//		return condicionesDTO;
//
//	}

	/**
	 * 
	 * Se obtienen todos los plazos habilitados para operar.
	 *
	 * @param cliente     the cliente
	 * @param plazosFijos the plazos fijos
	 * @return the list
	 * @throws DAOException 
	 */

	private List<TasasPlazoFijoBPrivEntity> obtenerPlazoFijosHabilitados(Cliente cliente,
			List<PlazoFijoEntity> plazosFijos, boolean pfRepatriacion) throws DAOException {
		LOGGER.info("Inicio metodo consultarMinimos.");
		List<TasasPlazoFijoBPrivEntity> plazosHabilitados = new ArrayList<TasasPlazoFijoBPrivEntity>();
		try {
			LOGGER.info("Obteniendo Plazos Fijos Disponibles");
			// Obtengo todos los plazos fijos disponibles
			String tipoBanca = "";
			if (!StringUtils.isBlank(sessionParametros.getTipoBancaPlazoFijo())) {
				tipoBanca = sessionParametros.getTipoBancaPlazoFijo();
			} else {
				tipoBanca = BANCA_RETAIL;
			}

			if (pfRepatriacion) {
				vaciarCacheTasas();
				plazosFijos = filtrarPFNoPermitidos(plazosFijos);
			}

			ConsultaTasasPlazoFijoBPrivOutEntity plazosFijosDisponibles = plazoFijoDAO.consultarTasas(cliente,
					tipoBanca, pfRepatriacion);
			plazosHabilitados = plazosFijosDisponibles.getListaTasas();

			LOGGER.info("Filtrando plazos fijos habilitados para operar");
			// Filtro los plazos fijos para que queden solamente los disponibles

			plazosHabilitados = filtrarPlazosFijosHabilitados(plazosFijos, plazosHabilitados);

		} catch (TimeOutException e) {
			LOGGER.error("Error  al consultar DAO, TimeOutException ", e);
			respuestaFactory.crearRespuestaErrorPersonalizadoSinClase("", TipoError.TIMEOUT.toString());
		} catch (DAOException daoe) {
			LOGGER.error("Error al consultar DAO con", cliente);
			if(pfRepatriacion) {
				throw new DAOException();
			}
		}

		return plazosHabilitados;
	}

	private List<PlazoFijoEntity> filtrarPFNoPermitidos(List<PlazoFijoEntity> plazosFijos) {
		List<PlazoFijoEntity> pfRepatriacion = new ArrayList<PlazoFijoEntity>();
		for (PlazoFijoEntity pf : plazosFijos) {
			if (Integer.valueOf(pf.getCodigoPlazo()).equals(COD_PF_REPATRIACION)) {
				pfRepatriacion.add(pf);
				break;
			}
		}
		return pfRepatriacion;
	}

	/**
	 * Obtiene los plazo fijos habilitados para simular.
	 *
	 * @param inDTO                           the in DTO
	 * @param coleccionTasasHabilitadas       the coleccion tasas habilitadas
	 * @param coleccionPlazosFijosHabilitados the coleccion plazos fijos habilitados
	 * @return the list
	 */

	protected ObtenerPlazoFijosParaSimularDTO obtenerPlazoFijosParaSimular(SimularPlazoFijoInDTO inDTO,
			List<TasasPlazoFijoBPrivEntity> coleccionTasasHabilitadas,
			List<PlazoFijoEntity> coleccionPlazosFijosHabilitados) {

		ObtenerPlazoFijosParaSimularDTO obtenerPlazoFijosParaSimularDTO = new ObtenerPlazoFijosParaSimularDTO();
		List<PlazoFijoEntity> plazosFijosParaSimular = new ArrayList<PlazoFijoEntity>();
		//List<PlazoFijoEntity> plazosFijosDeVenta = new ArrayList<PlazoFijoEntity>();

		for (PlazoFijoEntity plazoFijoHabilitado : coleccionPlazosFijosHabilitados) {
			String codigoPlazoHabilitado = StringUtils.leftPad(plazoFijoHabilitado.getCodigoPlazo(), LARGO_PLAZO, "0");

			for (TasasPlazoFijoBPrivEntity tasaHabilitada : coleccionTasasHabilitadas) {
				String codigoTasaHabilitada = StringUtils.leftPad(tasaHabilitada.getTipoPF(), LARGO_PLAZO, "0");
				if (codigoPlazoHabilitado.equals(codigoTasaHabilitada)) {

					BigDecimal importe;

					try {
						importe = ISBANStringUtils.convertirStrToBigDecimal(tasaHabilitada.getImporteMinimo(),
								DECIMALES_PLAZO_FIJO);
						int res = importe.compareTo(inDTO.getImportePlazoFijo());

						int plazoTasa = Integer.parseInt(tasaHabilitada.getPlazo());
						int plazoVista = Integer.parseInt(inDTO.getPlazo());
						if (obtnerDivisa(inDTO.getDivisa()).equals(tasaHabilitada.getMoneda()) && (res <= 0)
								&& (plazoTasa <= plazoVista)) {
							plazosFijosParaSimular.add(plazoFijoHabilitado);
							break;
						}
//						else if (obtnerDivisa(inDTO.getDivisa()).equals(tasaHabilitada.getMoneda())) {
//							plazoFijoHabilitado.setImporteMinimo(tasaHabilitada.getImporteMinimo());
//							plazoFijoHabilitado.setPlazoTaza(tasaHabilitada.getPlazo());
//							plazosFijosDeVenta.add(plazoFijoHabilitado);
//							break;
//						}

					} catch (ImporteConvertException e) {
						LOGGER.info("error ImporteConvertException : " + e);
					}
				}
			}
		}
		//obtenerPlazoFijosParaSimularDTO.setPlazosFijosDeVenta(plazosFijosDeVenta);
		obtenerPlazoFijosParaSimularDTO.setPlazosFijosParaSimular(plazosFijosParaSimular);
		return obtenerPlazoFijosParaSimularDTO;
	}

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

	/**
	 * {@inheritDoc}
	 * 
	 * @throws BusinessException
	 */
	@Override
	public Respuesta<MinimosPlazoFijoDTO> consultarMinimos(Cliente cliente, String tipoBanca) {
		LOGGER.info("Inicio metodo consultarMinimos.");
		try {
			LOGGER.info("Obteniendo Plazos Fijos Disponibles");
			// Obtengo todos los plazos fijos disponibles
			ConsultaTasasPlazoFijoBPrivOutEntity plazosFijosDisponibles = plazoFijoDAO.consultarTasas(cliente,
					tipoBanca, false);
			List<TasasPlazoFijoBPrivEntity> plazosHabilitados = plazosFijosDisponibles.getListaTasas();

			if (tipoBanca.equalsIgnoreCase(BANCA_RETAIL) && existeCuentaRepatriacionRTL(cliente)) {
				ConsultaTasasPlazoFijoBPrivOutEntity plazosFijosDisponiblesRepatriacion = plazoFijoDAO
						.consultarTasas(cliente, tipoBanca, true);
				plazosHabilitados.addAll(plazosFijosDisponiblesRepatriacion.getListaTasas());
			}

			LOGGER.info("Obteniendo Plazos Fijos Habilitados");
			// Obtengo plazos fijos habilitados para operar
			
			List<PlazoFijoEntity> plazosFijos=null;
			
			String version = plazoFijoDAO.determinarVersion(tipoBanca, false);
			
			if(version.equals(VERSION_110)){
				plazosFijos = flujoIatx(cliente, tipoBanca);
			}else {
			
				String flujo = "Simulacion";
				plazosFijos = llamarApiOIatx(cliente, tipoBanca, flujo);
			}

			LOGGER.info("Filtrando plazos fijos habilitados para operar");
			// Filtro los plazos fijos para que queden solamente los disponibles
			plazosHabilitados = filtrarPlazosFijosHabilitados(plazosFijos, plazosHabilitados);

			LOGGER.info("Obteniendo importe y plazo minimos");
			// Obtengo importes y plazos minimos por moneda
			MinimosPlazoFijoDTO minimos = obtenerMinimos(plazosHabilitados);

			Respuesta<MinimosPlazoFijoDTO> respuesta = respuestaFactory.crearRespuestaOk(MinimosPlazoFijoDTO.class,
					minimos);
			return respuesta;
		} catch (DAOException daoe) {
			LOGGER.error("Error al consultar DAO con", cliente);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} catch (BusinessException be) {
			LOGGER.error("Error al convertir Importe o al consultar servicio de IATX CNSPFTIPOS");
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
	}
	
	private List<PlazoFijoEntity> llamarApiOIatx(Cliente cliente, String tipoBanca, String flujo) throws BusinessException, DAOException {
		
		ModuloPermiso moduloPermiso;
		
		List<PlazoFijoEntity> plazosFijos;
		
		try {
			
			if("Simulacion".equals(flujo)) {
				moduloPermiso = moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.UTILIZAR_API_ROUTER_SIMULACION);
			}else {
				moduloPermiso = moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.UTILIZAR_API_ROUTER_TASAS);
			}
			
			
			if(!moduloPermiso.tienePermisoDeVisibilidad()) {
				plazosFijos = flujoRouterApi(tipoBanca);
				
			}else {
				plazosFijos = flujoIatx(cliente, tipoBanca);
				
			}
        } catch (Exception e) {
        	LOGGER.error("Error llamando al servicio consultarTiposApiFTD. Se llama al flujo legacy", e);
        	plazosFijos = flujoIatx(cliente, tipoBanca);
		}
		return plazosFijos;
	}


	private List<PlazoFijoEntity> flujoRouterApi(String tipoBanca) throws BusinessException, DAOException {
		List<PlazoFijoEntity> plazosFijos = null;
		
		try {
			plazosFijos = obtenerPlazoFijosHabilitadosValidados(
					consultaPlazoFijoDAO.obtenerPlazosFijosHabilitados(),
					plazoFijoDAO.consultarTiposRouterApi(tipoBanca));
		} catch (BusinessException e) {
			LOGGER.error("Error llamando al servicio consultarTiposRouterApi. Se llama al flujo legacy", e);
            throw new DAOException();
		} catch (DAOException e) {
			LOGGER.error("Error llamando al servicio consultarTiposRouterApi. Se llama al flujo legacy", e);
            throw new DAOException();
		}
		
		return plazosFijos;
	}


	private List<PlazoFijoEntity> flujoIatx(Cliente cliente, String tipoBanca) throws BusinessException, DAOException {
		List<PlazoFijoEntity> plazosFijos;
		
		plazosFijos = obtenerPlazoFijosHabilitadosValidados(
				consultaPlazoFijoDAO.obtenerPlazosFijosHabilitados(),
				plazoFijoDAO.consultarTipos(cliente, tipoBanca, false));
		
		return plazosFijos;
	}


	private boolean existeCuentaRepatriacionRTL(Cliente cliente) {
		for (Cuenta c : cliente.getCuentas()) {
			if (esCuentaRepatriacion(c)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Recibe todos los tipos de plazo fijo y devuelve solo los habilitados para
	 * operar.
	 *
	 * @param plazosFijos       the plazos fijos
	 * @param plazosHabilitados the plazos habilitados
	 * @return the list
	 */
	protected List<TasasPlazoFijoBPrivEntity> filtrarPlazosFijosHabilitados(List<PlazoFijoEntity> plazosFijos,
			List<TasasPlazoFijoBPrivEntity> plazosHabilitados) {
		List<TasasPlazoFijoBPrivEntity> plazosFijosHabilitados = new ArrayList<TasasPlazoFijoBPrivEntity>();

		for (TasasPlazoFijoBPrivEntity habilitados : plazosHabilitados) {

			for (PlazoFijoEntity plazosGeneral : plazosFijos) {

				long codPlazoGeneral = Long.parseLong(plazosGeneral.getCodigoPlazo());
				long codHabilitados = Long.parseLong(habilitados.getTipoPF());

				if (codPlazoGeneral == codHabilitados) {
					plazosFijosHabilitados.add(habilitados);
					break;
				}
			}
		}
		return plazosFijosHabilitados;
	}

	/**
	 * Recibe los plazos fijos habilitados y devuelve el importe y plazo minimo
	 * posible para USD y ARS.
	 *
	 * @param plazosHabilitados the plazos filtrados
	 * @return the minimos plazo fijo DTO
	 * @throws BusinessException the business exception
	 */
	protected MinimosPlazoFijoDTO obtenerMinimos(List<TasasPlazoFijoBPrivEntity> plazosHabilitados)
			throws BusinessException {
		MinimosPlazoFijoDTO minimosDTO = new MinimosPlazoFijoDTO();

		String tipoBanca = "";

		if (!StringUtils.isBlank(sessionParametros.getTipoBancaPlazoFijo())) {
			tipoBanca = sessionParametros.getTipoBancaPlazoFijo();
		} else {
			tipoBanca = BANCA_RETAIL;
		}

		for (TasasPlazoFijoBPrivEntity habilitados : plazosHabilitados) {
			if (habilitados.getMoneda().equals(MONEDA_PESOS)) {
				BigDecimal importePesos;
				try {
					importePesos = ISBANStringUtils.convertirStrToBigDecimal(habilitados.getImporteMinimo(),
							DECIMALES_IMPORTE_MINIMO);

					if (minimosDTO.getImporteMinimoPesos() == null
							|| importePesos.compareTo(minimosDTO.getImporteMinimoPesos()) == -1) {
						minimosDTO.setImporteMinimoPesos(importePesos);
						if (BANCA_PERSONAL.equals(tipoBanca) || BANCA_RETAIL.equals(tipoBanca)) {
							minimosDTO.setPlazoMinimoPesos(Integer.parseInt(habilitados.getPlazo()));
						}
					}

					// TEMPORAL: Segun ID 7739 en mail del 09/12/2019 para BP
					// solo se setea el Plazo
					if (BP.equals(tipoBanca) || BANCA_PRIVADA.equals(tipoBanca)) {
						if (minimosDTO.getPlazoMinimoPesos() == 0
								|| (Integer.parseInt(habilitados.getPlazo()) < minimosDTO.getPlazoMinimoPesos())) {
							minimosDTO.setPlazoMinimoPesos(Integer.parseInt(habilitados.getPlazo()));
						}
					}
				} catch (ImporteConvertException e) {
					LOGGER.error("Error ImporteConvertException", e);
					throw new BusinessException();
				}

			} else if (habilitados.getMoneda().equals(MONEDA_DOLARES)) {
				BigDecimal importeDolares;
				try {
					importeDolares = ISBANStringUtils.convertirStrToBigDecimal(habilitados.getImporteMinimo(),
							DECIMALES_IMPORTE_MINIMO);
					if (minimosDTO.getImporteMinimoDolares() == null
							|| importeDolares.compareTo(minimosDTO.getImporteMinimoDolares()) == -1) {
						minimosDTO.setImporteMinimoDolares(importeDolares);
						if (BANCA_PERSONAL.equals(tipoBanca) || BANCA_RETAIL.equals(tipoBanca)) {
							minimosDTO.setPlazoMinimoDolares(Integer.parseInt(habilitados.getPlazo()));
						}
					}

					// TEMPORAL: Segun ID 7739 en mail del 09/12/2019 para BP
					// solo se setea el Plazo
					if (BP.equals(tipoBanca) || BANCA_PRIVADA.equals(tipoBanca)) {
						if (minimosDTO.getPlazoMinimoDolares() == 0
								|| (Integer.parseInt(habilitados.getPlazo()) < minimosDTO.getPlazoMinimoDolares())) {
							minimosDTO.setPlazoMinimoDolares(Integer.parseInt(habilitados.getPlazo()));
						}
					}
				} catch (ImporteConvertException e) {
					LOGGER.error("Error ImporteConvertException", e);
					throw new BusinessException();
				}
			}
		}

		// TEMPORAL: Segun ID 7739 en mail del 09/12/2019 para BP se debe setear
		// 1000 como importe minimo en pesos
		try {
			minimosDTO.setImporteMinimoPesos(
					ISBANStringUtils.convertirStrToBigDecimal(IMPORTE_MINIMO, DECIMALES_IMPORTE_MINIMO));
		} catch (ImporteConvertException e) {
			LOGGER.error("Error ImporteConvertException", e);
			throw new BusinessException();
		}
		return minimosDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.bo.PlazoFijoBO#
	 * accionesAlVencimiento(java.lang.String)
	 */
	@Override
	public Respuesta<AccionesAlVencimientoOutView> accionesAlVencimiento(AccionesAlVencimientoInView inView,
			Cliente cliente) {
		Respuesta<AccionesAlVencimientoOutView> respuestaAcciones = new Respuesta<AccionesAlVencimientoOutView>();
		try {
			PlazoFijoEntity plazoFijo = consultaPlazoFijoDAO.obtenerPorCodigo(inView.getCodigoPlazo());
			if (plazoFijo != null) {
				respuestaAcciones.setEstadoRespuesta(EstadoRespuesta.OK);
				if (TipoBancaEnum.BANCA_PRIVADA.getCodigo().equalsIgnoreCase(inView.getBanca())
						&& esCuentaRepatriacion(obtenerCuentaDebito(cliente, inView.getNumeroCuentaDebito()))) {
					filtrarAcciones(plazoFijo.getAccionesAlVencimiento());
				}
				respuestaAcciones.setRespuesta(entityToView(plazoFijo.getAccionesAlVencimiento()));
				return respuestaAcciones;
			}
		} catch (DAOException ex) {
			LOGGER.error("Error obteniendo las acciones al vencimiento. ", ex);
		}
		respuestaAcciones.setEstadoRespuesta(EstadoRespuesta.ERROR);
		return respuestaAcciones;
	}

	private void filtrarAcciones(List<AccionAlVencimientoOutEntity> accionesAlVencimiento) {
		List<AccionAlVencimientoOutEntity> accionesABorrar = new ArrayList<AccionAlVencimientoOutEntity>();
		for (AccionAlVencimientoOutEntity accion : accionesAlVencimiento) {
			if (!accion.getCodigoAccion().equalsIgnoreCase("DC")) {
				accionesABorrar.add(accion);
			}
		}
		accionesAlVencimiento.removeAll(accionesABorrar);
	}

	/**
	 * Entity to view.
	 *
	 * @param accionesAlVencimiento the acciones al vencimiento
	 * @return the acciones al vencimiento out view
	 * @throws DAOException the DAO exception
	 */
	private AccionesAlVencimientoOutView entityToView(List<AccionAlVencimientoOutEntity> accionesAlVencimiento)
			throws DAOException {
		AccionesAlVencimientoOutView accionesView = new AccionesAlVencimientoOutView();
		for (AccionAlVencimientoOutEntity accionAlVencimientoOutEntity : accionesAlVencimiento) {
			AccionAlVencimientoView accionView = new AccionAlVencimientoView();
			try {
				BeanUtils.copyProperties(accionView, accionAlVencimientoOutEntity);
			} catch (IllegalAccessException e) {
				LOGGER.error("Error entityToView", e);
				throw new DAOException();
			} catch (InvocationTargetException e) {
				LOGGER.error("Error entityToView", e);
				throw new DAOException();
			}
			accionesView.getListaAcciones().add(accionView);
		}
		return accionesView;
	}

	/**
	 * Finalizar Constitucion de un Plazo Fijo.
	 *
	 * @param finalizarPlazoFijoDTO the finalizar plazo fijo DTO
	 * @param cliente               the cliente
	 * @return the respuesta
	 */
	@Override
	public Respuesta<FinalizarPlazoFijoDTO> finalizarPlazoFijo(FinalizarPlazoFijoDTO finalizarPlazoFijoDTO,
			Cliente cliente) {
		boolean permiteReintentar;
		if (sessionParametros.getContador() != null) {
			permiteReintentar = sessionParametros.getContador().permiteReintentar();
		} else {
			LOGGER.debug("Error, Contador no inicializado!!");
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		String tipoBanca = "";
		if (!StringUtils.isBlank(sessionParametros.getTipoBancaPlazoFijo())) {
			tipoBanca = sessionParametros.getTipoBancaPlazoFijo();
		} else {
			tipoBanca = BANCA_RETAIL;
		}

		Cuenta cuenta = obtenerCuentaDebito(cliente, finalizarPlazoFijoDTO.getNroCuentaDebito());

		boolean pfRepatriacion = esCuentaRepatriacion(cuenta);

		ImposicionPlazoFijoOutEntity imposicionPlazoFijoOutEntity = null;
		AltaComprobantePlazoFijoDTO altaComprobantePlazoFijoDTO = null;
		try {
			ImposicionPlazoFijoInEntity inEntityDAO = plazoFijoDTOToEntity(finalizarPlazoFijoDTO, cuenta);
			imposicionPlazoFijoOutEntity = plazoFijoDAO.confirmarConstitucion(inEntityDAO, cliente, tipoBanca,
					pfRepatriacion);
			altaComprobantePlazoFijoDTO = entityToAltaPlazoFijoDTO(imposicionPlazoFijoOutEntity, finalizarPlazoFijoDTO);
			sessionParametros.setAltaComprobantePlazoFijoDTO(altaComprobantePlazoFijoDTO);
		} catch (TimeOutException e) {
			LOGGER.error("Error en BO Time out. ", e);
			String mensajeError = armarMensajeError(finalizarPlazoFijoDTO);
			return respuestaFactory.crearRespuestaErrorPersonalizadoSinClase(mensajeError,
					TipoError.ERROR_FINALIZAR_PLAZO_FIJO_SIN_REINTENTO.toString());

		} catch (DAOException e) {
			if (CODIGO_NO_DISPONIBLE.equals(e.getErrorCode())) {
				String mensajeError = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_PLAZOS_FIJOS_NO_DISPONIBLE).getMensaje();
				return respuestaFactory.crearRespuestaErrorPersonalizadoSinClase(mensajeError,
						TipoError.ERROR_FINALIZAR_PLAZO_FIJO_SIN_REINTENTO.toString());
			}
			return crearRespuestaReintentoPlazoFijo(permiteReintentar, e, finalizarPlazoFijoDTO);
		} catch (BusinessException e) {
			return crearRespuestaReintentoPlazoFijo(permiteReintentar, e, finalizarPlazoFijoDTO);
		}
		return crearRespuestaOkFinalizarPlazoFijo(finalizarPlazoFijoDTO, imposicionPlazoFijoOutEntity);
	}

	/**
	 * Entity To AltaPlazoFijoDTO.
	 *
	 * @param imposicionPlazoFijoOutEntity the imposicion plazo fijo out entity
	 * @param finalizarPlazoFijoDTO        the finalizar plazo fijo DTO
	 * @return the alta comprobante plazo fijo DTO
	 */
	private AltaComprobantePlazoFijoDTO entityToAltaPlazoFijoDTO(
			ImposicionPlazoFijoOutEntity imposicionPlazoFijoOutEntity, FinalizarPlazoFijoDTO finalizarPlazoFijoDTO) {
		AltaComprobantePlazoFijoDTO altaComprobantePlazoFijoDTO = new AltaComprobantePlazoFijoDTO();
		try {
			BigDecimal saldoInicUr = ISBANStringUtils
					.convertirStrToBigDecimal(imposicionPlazoFijoOutEntity.getSaldoInicUr(), DECIMALES_SALDO_INIC_UR);
			altaComprobantePlazoFijoDTO.setSaldoInicUr(saldoInicUr.stripTrailingZeros());
			BigDecimal cotizacionCodigoUr = ISBANStringUtils.convertirStrToBigDecimal(
					imposicionPlazoFijoOutEntity.getCotizacionCodigoUr(), DECIMALES_COTIZACION_CODIGO_UR);
			altaComprobantePlazoFijoDTO.setCotizacionCodigoUr(cotizacionCodigoUr.stripTrailingZeros());
			BigDecimal intereses = ISBANStringUtils
					.convertirStrToBigDecimal(imposicionPlazoFijoOutEntity.getIntereses(), DECIMALES_INTERESES);
			altaComprobantePlazoFijoDTO.setIntereses(intereses.stripTrailingZeros());
		} catch (ImporteConvertException e) {
			LOGGER.error("Error al convertir String a BigDecimal ", e);
		}
		altaComprobantePlazoFijoDTO.setFechaAltaReal(imposicionPlazoFijoOutEntity.getFechaAltaReal());
		altaComprobantePlazoFijoDTO
				.setPlazo(ISBANStringUtils.sacarCerosYBlancosIzq(imposicionPlazoFijoOutEntity.getPlazo()));
		altaComprobantePlazoFijoDTO.setFechaVencimiento(imposicionPlazoFijoOutEntity.getFechaVencimiento());
		altaComprobantePlazoFijoDTO.setEntidadCuentaPlazo(
				ISBANStringUtils.sacarCerosYBlancosIzq(imposicionPlazoFijoOutEntity.getEntidadCuentaPlazo()));
		altaComprobantePlazoFijoDTO.setNumeroCuentaPlazo(
				ISBANStringUtils.sacarCerosYBlancosIzq(imposicionPlazoFijoOutEntity.getNumeroCuentaPlazo()));
		altaComprobantePlazoFijoDTO
				.setSecuencia(ISBANStringUtils.sacarCerosYBlancosIzq(imposicionPlazoFijoOutEntity.getSecuencia()));
		altaComprobantePlazoFijoDTO.setImporteCertificado(finalizarPlazoFijoDTO.getImporteCertificado());
		altaComprobantePlazoFijoDTO.setNroCuentaDebito(finalizarPlazoFijoDTO.getNroCuentaDebito());
		altaComprobantePlazoFijoDTO.setMoneda(obtenerMoneda(finalizarPlazoFijoDTO.getDivisa()));
		altaComprobantePlazoFijoDTO.setTasaNominal(finalizarPlazoFijoDTO.getTasa());
		altaComprobantePlazoFijoDTO.setTasaEfectiva(finalizarPlazoFijoDTO.getTasaEfectiva());
		altaComprobantePlazoFijoDTO
				.setDescripcionAccionVencimiento(finalizarPlazoFijoDTO.getDescripcionAccionVencimiento());
		altaComprobantePlazoFijoDTO.setImpuestosPF(imposicionPlazoFijoOutEntity.getImpuestosPF());
		return altaComprobantePlazoFijoDTO;
	}

	/**
	 * Arma el mensaje de error para errores conocidos sin reintento.
	 *
	 * @param finalizarPlazoFijoDTO the finalizar plazo fijo DTO
	 * @return the string
	 */
	private String armarMensajeError(FinalizarPlazoFijoDTO finalizarPlazoFijoDTO) {
		String mensajeError = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_TIME_OUT_PLAZO_FIJO)
				.getMensaje();
		String divisa = null;
		if ("ARS".equals(finalizarPlazoFijoDTO.getDivisa())) {
			divisa = "$";
		} else {
			divisa = "u$s";
		}
		String importeConMoneda = divisa + " "
				+ ISBANStringUtils.formatearSaldoConSigno(finalizarPlazoFijoDTO.getImporteCertificado());
		return MessageFormat.format(mensajeError, finalizarPlazoFijoDTO.getNombrePlazoFijo(), importeConMoneda,
				finalizarPlazoFijoDTO.getPlazo());
	}

	/**
	 * Crear respuesta ok finalizar plazo fijo.
	 *
	 * @param dto                          the dto
	 * @param imposicionPlazoFijoOutEntity the imposicion plazo fijo out entity
	 * @return the respuesta
	 */
	private Respuesta<FinalizarPlazoFijoDTO> crearRespuestaOkFinalizarPlazoFijo(FinalizarPlazoFijoDTO dto,
			ImposicionPlazoFijoOutEntity imposicionPlazoFijoOutEntity) {
		FinalizarPlazoFijoDTO finalizarPlazoFijoDTO = entityToDTO(imposicionPlazoFijoOutEntity, dto);
		return respuestaFactory.crearRespuestaOk(FinalizarPlazoFijoDTO.class, finalizarPlazoFijoDTO);
	}

	/**
	 * Entity to DTO.
	 *
	 * @param imposicionPlazoFijoOutEntity the imposicion plazo fijo out entity
	 * @param finalizarPlazoFijoDTO        the finalizar plazo fijo DTO
	 * @return the finalizar plazo fijo DTO
	 */
	private FinalizarPlazoFijoDTO entityToDTO(ImposicionPlazoFijoOutEntity imposicionPlazoFijoOutEntity,
			FinalizarPlazoFijoDTO finalizarPlazoFijoDTO) {
		String mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.FEEDBACK_POSITIVO_PLAZO_FIJO)
				.getMensaje();
		String nombrePlazoFijo = finalizarPlazoFijoDTO.getNombrePlazoFijo();
		String divisa = null;
		if ("ARS".equals(finalizarPlazoFijoDTO.getDivisa())) {
			divisa = "$";
		} else {
			divisa = "u$s";
		}
		String cantDiasSinCeros = ISBANStringUtils.eliminarCeros(imposicionPlazoFijoOutEntity.getPlazo());
		// ver tema de formato de importe
		String importe = ISBANStringUtils
				.formatearSaldosConCerosYSignos(imposicionPlazoFijoOutEntity.getImporteCertificado());
		String mensajeFormateado = MessageFormat.format(mensaje, nombrePlazoFijo, divisa + " " + importe,
				cantDiasSinCeros);
		finalizarPlazoFijoDTO.setMensaje(mensajeFormateado);
		String nroComprobante = certPlazoFijo(imposicionPlazoFijoOutEntity.getEntidadCuentaPlazo(),
				imposicionPlazoFijoOutEntity.getNumeroCuentaPlazo(), imposicionPlazoFijoOutEntity.getSecuencia());
		finalizarPlazoFijoDTO.setNumeroComprobante(nroComprobante);
		finalizarPlazoFijoDTO.setFechaConstitucion(new SimpleDateFormat("dd/MM/yyyy - HH:mm").format(new Date()));
		finalizarPlazoFijoDTO.setMinimoDiasPrecancelar(
				ISBANStringUtils.eliminarCeros(imposicionPlazoFijoOutEntity.getMinimoDiasPrecancelar()));
		try {
			BigDecimal saldoInicUr = ISBANStringUtils
					.convertirStrToBigDecimal(imposicionPlazoFijoOutEntity.getSaldoInicUr(), DECIMALES_SALDO_INIC_UR);
			finalizarPlazoFijoDTO.setSaldoInicUr(saldoInicUr);
			BigDecimal cotizacionCodigoUr = ISBANStringUtils.convertirStrToBigDecimal(
					imposicionPlazoFijoOutEntity.getCotizacionCodigoUr(), DECIMALES_COTIZACION_CODIGO_UR);
			finalizarPlazoFijoDTO.setCotizacionCodigoUr(cotizacionCodigoUr);
		} catch (ImporteConvertException e) {
			LOGGER.error("Error al convertir String a BigDecimal ", e);
		}
		return finalizarPlazoFijoDTO;
	}

	/**
	 * Cert plazo fijo.
	 *
	 * @param entidad   the entidad
	 * @param ctaPlazo  the cta plazo
	 * @param secuencia the secuencia
	 * @return the string
	 */
	public static String certPlazoFijo(String entidad, String ctaPlazo, String secuencia) {
		if (esTodoCerosOBlanco(entidad)) {
			return secuencia;
		}
		if (secuencia.length() < 9) {
			secuencia = StringUtils.leftPad(secuencia, 9, "0");
		}
		String sec = secuencia.substring(esTodoCerosOBlanco(secuencia.substring(0, 4)) ? 4 : 0);
		return ctaPlazo.substring(3) + GUION_MEDIO + sec;
	}

	/**
	 * Es todos ceros O blanco.
	 *
	 * @param str the str
	 * @return true, if successful
	 */
	public static boolean esTodoCerosOBlanco(String str) {
		boolean retValue = true;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != '0' && str.charAt(i) != ' ') {
				retValue = false;
				break;
			}
		}
		return retValue;

	}

	/**
	 * Plazo fijo DTO to entity.
	 *
	 * @param finalizarPlazoFijoDTO the finalizar plazo fijo DTO
	 * @param cuenta                the cuenta
	 * @return the imposicion plazo fijo in entity
	 * @throws DAOException the DAO exception
	 */
	private ImposicionPlazoFijoInEntity plazoFijoDTOToEntity(FinalizarPlazoFijoDTO finalizarPlazoFijoDTO, Cuenta cuenta)
			throws DAOException {

		ImposicionPlazoFijoInEntity imposicionPlazoFijoInEntity = new ImposicionPlazoFijoInEntity();
		try {
			BeanUtils.copyProperties(imposicionPlazoFijoInEntity, finalizarPlazoFijoDTO);
		} catch (IllegalAccessException e) {
			LOGGER.error("Error al copiar atributos de entity a DTO. ", e);
			throw new DAOException();
		} catch (InvocationTargetException e) {
			LOGGER.error("Error al copiar atributos de entity a DTO. ", e);
			throw new DAOException();
		}

		String tipoCuenta = cuenta.getTipoCuenta().substring(1);
		if (tipoCuenta.equals(TipoCuenta.CUENTA_UNICA.getCodigo().toString())) {
			if (finalizarPlazoFijoDTO.getDivisa().equals(TipoMonedaInversionEnum.USD.getCodigo())) {
				imposicionPlazoFijoInEntity.setTipoCuenta(StringUtils.leftPad(
						TipoCuenta.CUENTA_UNICA_DOLARES.getCodigo().toString(), LARGO_TIPO_CUENTA_DEBITO, "0"));
			} else {
				imposicionPlazoFijoInEntity.setTipoCuenta(StringUtils
						.leftPad(TipoCuenta.CUENTA_UNICA_PESOS.getCodigo().toString(), LARGO_TIPO_CUENTA_DEBITO, "0"));
			}
		} else {
			imposicionPlazoFijoInEntity.setTipoCuenta(cuenta.getTipoCuenta());
		}

		if (BANCA_RETAIL.equals(sessionParametros.getTipoBancaPlazoFijo())) {
			imposicionPlazoFijoInEntity
					.setSucursalCuentaDebito(StringUtils.right(cuenta.getNroSucursal(), LARGO_COD_SUCURSAL));
			imposicionPlazoFijoInEntity
					.setSucursalCuentaDebito2(CERO_STRING + imposicionPlazoFijoInEntity.getSucursalCuentaDebito());
			imposicionPlazoFijoInEntity
					.setNroCuentaDebito(StringUtils.right(cuenta.getNroCuentaProducto(), LARGO_NRO_CUENTA));
			imposicionPlazoFijoInEntity.setTasa(ISBANStringUtils.formatearNumericosParaIatxSeparadoPorPunto(
					finalizarPlazoFijoDTO.getTasa(), LARGO_TASA, DECIMALES_PLAZO_FIJO_TNA));
			imposicionPlazoFijoInEntity
					.setTasaVariable(StringUtils.left(finalizarPlazoFijoDTO.getTasaVariable(), LARGO_TASA));
			imposicionPlazoFijoInEntity.setNroContratoAltair(StringUtils.right(cuenta.getContratoAltair(), 12));
		} else {
			imposicionPlazoFijoInEntity
					.setSucursalCuentaDebito2(StringUtils.right(cuenta.getNroSucursal(), LARGO_COD_SUCURSAL));
			imposicionPlazoFijoInEntity
					.setSucursalCuentaDebito(CERO_STRING + imposicionPlazoFijoInEntity.getSucursalCuentaDebito2());
			imposicionPlazoFijoInEntity.setNroCuentaDebito(cuenta.getContratoAltair().substring(CUATRO_INT));
			imposicionPlazoFijoInEntity.setTasa(ISBANStringUtils.formatearNumericosParaIatxSeparadoPorPunto(
					finalizarPlazoFijoDTO.getTasa(), LARGO_TASA_BP, DECIMALES_PLAZO_FIJO_TNA));
			imposicionPlazoFijoInEntity.setSecuencia(StringUtils.repeat(CERO_STRING, LONGITUD_SECUENCIA));
			imposicionPlazoFijoInEntity
					.setTasaVariable(StringUtils.left(finalizarPlazoFijoDTO.getTasaVariable(), LARGO_TASA_BP));
			imposicionPlazoFijoInEntity.setSpread(StringUtils.repeat(CERO_STRING, LONGITUD_SPREAD));
		}

		imposicionPlazoFijoInEntity
				.setPlazo(StringUtils.leftPad(finalizarPlazoFijoDTO.getPlazo(), LONGITUD_PLAZO, CERO_STRING));
		imposicionPlazoFijoInEntity.setImporteCertificado(ISBANStringUtils
				.ajustadorBigDecimalIatx(finalizarPlazoFijoDTO.getImporteCertificado(), LARGO_IMPORTE_PLAZO_FIJO));
		imposicionPlazoFijoInEntity.setCantidadDias(
				StringUtils.leftPad(finalizarPlazoFijoDTO.getCantidadDias(), LARGO_PLAZO, CERO_STRING));
		imposicionPlazoFijoInEntity.setSaldoInicUr(ISBANStringUtils.formatearNumericosParaIatxSeparadoPorPunto(
				finalizarPlazoFijoDTO.getSaldoInicUr().toString(), LARGO_SALDO_INIC_UR, DECIMALES_SALDO_INIC_UR));
		imposicionPlazoFijoInEntity.setCotizacionCodigoUr(ISBANStringUtils.formatearNumericosParaIatxSeparadoPorPunto(
				finalizarPlazoFijoDTO.getCotizacionCodigoUr().toString(), LARGO_COTIZACION_CODIGO_UR,
				DECIMALES_COTIZACION_CODIGO_UR));
		return imposicionPlazoFijoInEntity;
	}

	/**
	 * Crear respuesta reintento plazo fijo.
	 *
	 * @param permiteReintentar     the permite reintentar
	 * @param e                     the e
	 * @param finalizarPlazoFijoDTO the finalizar plazo fijo DTO
	 * @return the respuesta
	 */
	Respuesta<FinalizarPlazoFijoDTO> crearRespuestaReintentoPlazoFijo(boolean permiteReintentar, Exception e,
			FinalizarPlazoFijoDTO finalizarPlazoFijoDTO) {
		LOGGER.error("Error en PlazoFijoBO metodo finalizarPlazoFijo. ", e);
		String mensajeError = armarMensajeError(finalizarPlazoFijoDTO);
		if (permiteReintentar) {
			return respuestaFactory.crearRespuestaErrorPersonalizadoSinClase(mensajeError,
					TipoError.ERROR_FINALIZAR_PLAZO_FIJO_CON_REINTENTO.toString());
		}
		return respuestaFactory.crearRespuestaErrorPersonalizadoSinClase(mensajeError,
				TipoError.ERROR_FINALIZAR_PLAZO_FIJO_SIN_REINTENTO.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.bo.PlazoFijoBO#
	 * vaciarCachePlazosFijos()
	 */
	@Override
	public Respuesta<Boolean> vaciarCachePlazosFijos() {
		consultaPlazoFijoDAO.vaciarCachePlazosFijos();
		return respuestaFactory.crearRespuestaOk(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.bo.PlazoFijoBO#
	 * vaciarCacheTasas()
	 */
	@Override
	public Respuesta<Boolean> vaciarCacheTasas() {
		plazoFijoDAO.vaciarCacheTasas();
		return respuestaFactory.crearRespuestaOk(true);
	}

	/**
	 * Creates the intereses DTO response.
	 *
	 * @param outDAO the out DAO
	 * @return the intereses DTO
	 */
	private InteresesDTO createInteresesDTOResponse(SimulacionPlazoFijoOutEntity outDAO) {
		InteresesDTO intereses = new InteresesDTO();
		intereses.setInteresantePF(outDAO.getInteresantePF());
		return intereses;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.bo.PlazoFijoBO#
	 * calcularIntereses(ar.com.santanderrio.obp.servicios.inversiones.plazofijo
	 * .dto.SimularPlazoFijoInDTO,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<InteresesDTO> calcularIntereses(SimularPlazoFijoInDTO inDTO, Cliente cliente) {

		Cuenta cuenta = obtenerCuentaDebito(cliente, inDTO.getNumeroCuentaDebito());

		if (cuenta == null) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		SimulacionPlazoFijoInEntity inEntity = createEntityRequest(inDTO, cuenta);
		inEntity.setTipoPF(CODIGO_TPF_INTERESANTE);
		inEntity.setCantidadDias(StringUtils.leftPad(inDTO.getCantidadDias(), LARGO_CANTIDAD_DIAS, "0"));
		try {
			SimulacionPlazoFijoOutEntity outDAO = plazoFijoDAO.simularPlazoFijo(inEntity, cliente, false);
			InteresesDTO interesesDTO = createInteresesDTOResponse(outDAO);
			return respuestaFactory.crearRespuestaOk(InteresesDTO.class, interesesDTO);

		} catch (DAOException e) {
			LOGGER.error("Error fallo de servicio", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.bo.PlazoFijoBO#
	 * verComprobante(ar.com.santanderrio.obp.servicios.inversiones.plazofijo.
	 * dto.ComprobantePlazoFijoInDTO)
	 */
	@Override
	public Respuesta<ComprobantePlazoFijoOutDTO> verComprobante(ComprobantePlazoFijoInDTO comprobantePlazoFijoInDTO) {

		String codigoPlazo = ISBANStringUtils.eliminarCeros(comprobantePlazoFijoInDTO.getCodigoPlazo());
		ComprobantePlazoFijoOutDTO comprobanteDTO = new ComprobantePlazoFijoOutDTO();
		if (PlazoFijoEnum.PRECANCELABLE.getCodigo().equals(codigoPlazo)) {
			return obtenerLegalesPrecancelable(comprobantePlazoFijoInDTO, comprobanteDTO);
		}
		if (PlazoFijoEnum.TRADICIONAL.getCodigo().equals(codigoPlazo) || PlazoFijoEnum.TRADICIONAL_REPATRIACION.getCodigo().equals(codigoPlazo)) {
			return obtenerLegalesTradicional(comprobanteDTO);
		}
		if (PlazoFijoEnum.INTERESANTE_TASA_FIJA.getCodigo().equals(codigoPlazo)) {
			return obtenerLegalesInteresante(comprobanteDTO);
		}
		if (PlazoFijoEnum.UVA.getCodigo().equals(codigoPlazo)) {
			return obtenerLegalesUVA(comprobanteDTO);
		}
		if (PlazoFijoEnum.UVA_PRECANCELABLE.getCodigo().equals(codigoPlazo)) {
			return obtenerLegalesUvaPrecancelable(comprobanteDTO);
		}
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
	}

	private Respuesta<ComprobantePlazoFijoOutDTO> obtenerLegalesUvaPrecancelable(
			ComprobantePlazoFijoOutDTO comprobanteDTO) {
		ArrayList<String> listaLegales = new ArrayList<String>();
		listaLegales.add(
				legalBO.buscarLegal(CodigoMensajeConstantes.UVA_PLAZO_FIJO_PRECANCELABLE_CONFIGURACION).getRespuesta());
		listaLegales.add(legalBO.buscarLegal(CodigoMensajeConstantes.CONFIRMACION_PLAZO_FIJO).getRespuesta());
		for (String legal : listaLegales) {
			if (StringUtils.isBlank(legal)) {
				return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO, "");
			}
		}
		comprobanteDTO.setLegales(listaLegales);
		return respuestaFactory.crearRespuestaOk(ComprobantePlazoFijoOutDTO.class, comprobanteDTO);
	}

	/**
	 * Obtener legales UVA.
	 *
	 * @param comprobanteDTO the comprobante DTO
	 * @return the respuesta
	 */
	private Respuesta<ComprobantePlazoFijoOutDTO> obtenerLegalesUVA(ComprobantePlazoFijoOutDTO comprobanteDTO) {
		Respuesta<String> respuestaLegalGeneralPF = legalBO
				.buscarLegal(CodigoMensajeConstantes.CONFIRMACION_PLAZO_FIJO);
		if (EstadoRespuesta.OK.equals(respuestaLegalGeneralPF.getEstadoRespuesta())) {
			comprobanteDTO.setLegales(new ArrayList<String>());
			comprobanteDTO.getLegales().add(respuestaLegalGeneralPF.getRespuesta());
			Respuesta<String> respuestaLegalUVA = legalBO.buscarLegal(CodigoMensajeConstantes.UVA_VER_COMPROBANTE);
			if (EstadoRespuesta.OK.equals(respuestaLegalUVA.getEstadoRespuesta())) {
				comprobanteDTO.getLegales().add(0, respuestaLegalUVA.getRespuesta());
			}
			return respuestaFactory.crearRespuestaOk(ComprobantePlazoFijoOutDTO.class, comprobanteDTO);
		} else {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
	}

	/**
	 * Obtener legales precancelable.
	 *
	 * @param comprobantePlazoFijoInDTO the comprobante plazo fijo in DTO
	 * @param confirmacionDTO           the confirmacion DTO
	 * @return the respuesta
	 */
	private Respuesta<ComprobantePlazoFijoOutDTO> obtenerLegalesPrecancelable(
			ComprobantePlazoFijoInDTO comprobantePlazoFijoInDTO, ComprobantePlazoFijoOutDTO confirmacionDTO) {
		String porcentaje = "";
		ComprobantePlazoFijoOutDTO comprobantePlazoFijoOutDTO = new ComprobantePlazoFijoOutDTO();
		comprobantePlazoFijoOutDTO.setLegales(new ArrayList<String>());

		Respuesta<String> respuestaLegalPrecancelable = legalBO
				.buscarLegal(CodigoMensajeConstantes.VER_COMPROBANTE_PLAZO_FIJO);

		if (EstadoRespuesta.OK.equals(respuestaLegalPrecancelable.getEstadoRespuesta())) {
			String mensaje = legalBO.buscarLegal(CodigoMensajeConstantes.VER_COMPROBANTE_PLAZO_FIJO).getRespuesta();
			String cantidadMindias = comprobantePlazoFijoInDTO.getMinimoDiasPrecancelar();
			String plazo = comprobantePlazoFijoInDTO.getPlazo();
			try {
				BigDecimal porcentajePenalizacion = ISBANStringUtils.convertirStrToBigDecimal(
						comprobantePlazoFijoInDTO.getPorcentajePenalizacion(), DECIMALES_PORCENTAJE_PENALIZACION);
				porcentaje = String.valueOf(porcentajePenalizacion.doubleValue());

			} catch (ImporteConvertException e) {
				LOGGER.error("Error al convertir String a BigDecimal ", e);
			}
			String mensajeFormateado = MessageFormat.format(mensaje, cantidadMindias, plazo,
					StringUtils.replaceChars(porcentaje, ".", ","));
			Respuesta<String> respuestaLegalTradicional = legalBO
					.buscarLegal(CodigoMensajeConstantes.CONFIRMACION_PLAZO_FIJO);
			Respuesta<String> respuestaLegalDDJJ = legalBO
					.buscarLegal(CodigoMensajeConstantes.CONFIRMACION_PLAZO_FIJO_DDJJ);
			if (EstadoRespuesta.OK.equals(respuestaLegalTradicional.getEstadoRespuesta())) {
				confirmacionDTO.setLegales(new ArrayList<String>());
				confirmacionDTO.getLegales().add(mensajeFormateado);
				confirmacionDTO.getLegales().add(respuestaLegalDDJJ.getRespuesta());
				confirmacionDTO.getLegales().add(respuestaLegalTradicional.getRespuesta());
				return respuestaFactory.crearRespuestaOk(ComprobantePlazoFijoOutDTO.class, confirmacionDTO);

			} else {
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}

		} else {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

	}

	/**
	 * Obtener legales interesante.
	 *
	 * @param confirmacionDTO the confirmacion DTO
	 * @return the respuesta
	 */
	private Respuesta<ComprobantePlazoFijoOutDTO> obtenerLegalesInteresante(
			ComprobantePlazoFijoOutDTO confirmacionDTO) {
		Respuesta<String> respuestaLegalTradicional = legalBO
				.buscarLegal(CodigoMensajeConstantes.CONFIRMACION_PLAZO_FIJO);
		Respuesta<String> respuestaLegalInteresante = legalBO
				.buscarLegal(CodigoMensajeConstantes.INTERESANTE_PLAZO_FIJO_LEGAL_CONFIRMACION);
		Respuesta<String> respuestaLegalDDJJ = legalBO
				.buscarLegal(CodigoMensajeConstantes.CONFIRMACION_PLAZO_FIJO_DDJJ);
		if (EstadoRespuesta.OK.equals(respuestaLegalTradicional.getEstadoRespuesta())
				&& EstadoRespuesta.OK.equals(respuestaLegalInteresante.getEstadoRespuesta())) {
			confirmacionDTO.setLegales(new ArrayList<String>());
			confirmacionDTO.getLegales().add(respuestaLegalDDJJ.getRespuesta());
			confirmacionDTO.getLegales().add(respuestaLegalTradicional.getRespuesta());
			confirmacionDTO.getLegales().add(respuestaLegalInteresante.getRespuesta());
			return respuestaFactory.crearRespuestaOk(ComprobantePlazoFijoOutDTO.class, confirmacionDTO);
		} else {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
	}

	/**
	 * Obtener legales tradicional.
	 *
	 * @param confirmacionDTO the confirmacion DTO
	 * @return the respuesta
	 */
	private Respuesta<ComprobantePlazoFijoOutDTO> obtenerLegalesTradicional(
			ComprobantePlazoFijoOutDTO confirmacionDTO) {
		Respuesta<String> respuestaLegalTradicional = legalBO
				.buscarLegal(CodigoMensajeConstantes.CONFIRMACION_PLAZO_FIJO);
		Respuesta<String> respuestaLegalDDJJ = legalBO
				.buscarLegal(CodigoMensajeConstantes.CONFIRMACION_PLAZO_FIJO_DDJJ);
		if (EstadoRespuesta.OK.equals(respuestaLegalTradicional.getEstadoRespuesta())) {
			confirmacionDTO.setLegales(new ArrayList<String>());
			confirmacionDTO.getLegales().add(respuestaLegalDDJJ.getRespuesta());
			confirmacionDTO.getLegales().add(respuestaLegalTradicional.getRespuesta());
			return respuestaFactory.crearRespuestaOk(ComprobantePlazoFijoOutDTO.class, confirmacionDTO);
		} else {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.bo.PlazoFijoBO#
	 * consultarTasas(ar.com.santanderrio.obp.servicios.clientes.entities. Cliente)
	 */
	@Override
	public Respuesta<ConsultaTasasPlazosFijoView> consultarTasas(Cliente cliente, String bancaSeleccionada) {
		ConsultaTasasPlazoFijoBPrivOutEntity tasas;
		List<PlazoFijoEntity> plazosFijosHabilitados;
		String version = plazoFijoDAO.determinarVersion(bancaSeleccionada, false);
		String flujo= "Tasas";

		try {
			
			tasas = plazoFijoDAO.consultarTasas(cliente, bancaSeleccionada, false);
			
			if(version.equals(VERSION_110)){
				plazosFijosHabilitados = flujoIatx(cliente, bancaSeleccionada);
			}else {
				plazosFijosHabilitados = llamarApiOIatx(cliente, bancaSeleccionada, flujo);
				ordenarPlazosPorPrioridad(plazosFijosHabilitados);
			}
			ConsultaTasasPlazosFijoView tasasFiltradas = filtrarTasasDePlazosNoHabilitados(tasas, plazosFijosHabilitados);
			if (!tasas.getListaTasas().isEmpty()) {
				return respuestaFactory.crearRespuestaOk(ConsultaTasasPlazosFijoView.class, tasasFiltradas);
			}


		} catch (DAOException ex) {
			LOGGER.error("Error al consultar PlazoFijo DAO con ", cliente, ex);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} catch (BusinessException e) {
			LOGGER.error("Error al consultar servicio de IATX CNSPFTIPOS con ", cliente, e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);

	}

	/**
	 * Ordenar plazos por prioridad.
	 *
	 * @param plazosFijosHabilitados the plazos fijos habilitados
	 */
	private void ordenarPlazosPorPrioridad(List<PlazoFijoEntity> plazosFijosHabilitados) {
		Collections.sort(plazosFijosHabilitados, new Comparator<PlazoFijoEntity>() {
			@Override
			public int compare(PlazoFijoEntity plazo1, PlazoFijoEntity plazo2) {
				return Integer.valueOf(plazo1.getPrioridad()).compareTo(Integer.valueOf(plazo2.getPrioridad()));
			}
		});
	}

	/**
	 * Metodo que filtra los plazos fijos habilitados con los que trae el servicio
	 * de IATX CNSPFTIPOS.
	 *
	 * @param plazosFijosHabilitados          the plazos fijos habilitados
	 * @param consultaTiposPlazoFijoOutEntity the consulta tipos plazo fijo out
	 *                                        entity
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	private List<PlazoFijoEntity> obtenerPlazoFijosHabilitadosValidados(List<PlazoFijoEntity> plazosFijosHabilitados,
			ConsultaTiposPlazoFijoBPrivOutEntity consultaTiposPlazoFijoOutEntity) throws BusinessException {
		boolean elemAgregado = false;
		List<PlazoFijoEntity> plazosFijosHabilitadosFinal = new ArrayList<PlazoFijoEntity>();
		for (ConsultaTiposPlazoFijoBPrivEntity consultaTiposPlazoFijoEntity : consultaTiposPlazoFijoOutEntity
				.getTipoPF()) {
			for (PlazoFijoEntity habilitado : plazosFijosHabilitados) {
				if (formatearTipoPlazo(habilitado.getCodigoPlazo()).equals(consultaTiposPlazoFijoEntity.getTipoPF())) {
					plazosFijosHabilitadosFinal.add(habilitado);
					elemAgregado = true;
					break;
				}
			}
			if (!elemAgregado) {
				LOGGER.error("Error, No se encontro el Plazo fijo en la tabla PRC_VER_PLAZOS_FIJOS");
				throw new BusinessException();
			}
			elemAgregado = false;
		}
		return plazosFijosHabilitadosFinal;
	}

	/**
	 * Recibe los plazos fijos habilitados y la lista de tasas Devuelve las tasas de
	 * los plazos fijos habilitados Eliminando las tasas de los PF que no estan
	 * habilitados.
	 *
	 * @param tasas                  the tasas
	 * @param plazosFijosHabilitados the plazos fijos habilitados
	 * @return the consulta tasas plazos fijo view
	 */
	private ConsultaTasasPlazosFijoView filtrarTasasDePlazosNoHabilitados(ConsultaTasasPlazoFijoBPrivOutEntity tasas,
			List<PlazoFijoEntity> plazosFijosHabilitados) {

		ConsultaTasasPlazosFijoView tasasFiltradas = new ConsultaTasasPlazosFijoView();
		List<TasasPorPlazoView> listaTasasPorPlazo = new ArrayList<TasasPorPlazoView>();
		tasasFiltradas.setListaTasasPorPlazo(listaTasasPorPlazo);

		for (PlazoFijoEntity plazoFijoEntity : plazosFijosHabilitados) {

			TasasPorPlazoView tasasPorPlazo = new TasasPorPlazoView();
			List<ValoresTasa> listaValoresTasa = new ArrayList<ValoresTasa>();

			tasasPorPlazo.setListaValoresTasa(listaValoresTasa);
			tasasPorPlazo.setNombrePlazo(plazoFijoEntity.getDescripcion());
			listaTasasPorPlazo.add(tasasPorPlazo);

			for (TasasPlazoFijoBPrivEntity tasa : tasas.getListaTasas()) {
				asignarTasaAPlazo(plazoFijoEntity, listaValoresTasa, tasa);
			}
		}
		return tasasFiltradas;
	}

	/**
	 * Asigna la tasa al plazo fijo si es que corresponde.
	 *
	 * @param plazoFijoEntity  the plazo fijo entity
	 * @param listaValoresTasa the lista valores tasa
	 * @param tasa             the tasa
	 */
	private void asignarTasaAPlazo(PlazoFijoEntity plazoFijoEntity, List<ValoresTasa> listaValoresTasa,
			TasasPlazoFijoBPrivEntity tasa) {
		if (ISBANStringUtils.eliminarCeros(tasa.getTipoPF()).equals(plazoFijoEntity.getCodigoPlazo())) {
			if (ISBANStringUtils.eliminarCeros(TipoMonedaInversionEnum.AMBAS.getCodigoNumerico())
					.equals(plazoFijoEntity.getMoneda())
					|| TipoMonedaInversionEnum.fromCodigoNumericoString("0" + plazoFijoEntity.getMoneda())
							.equals(TipoMonedaInversionEnum.fromCodigo2String(tasa.getMoneda()))) {
				ValoresTasa valoresTasa = new ValoresTasa();
				valoresTasa.setDias(tasa.getPlazo());
				valoresTasa.setPenalizacion(tasa.getPorcentajePenalizacion());
				valoresTasa.setTasaNominalBasica(tasa.getTasaNominalCanal());
				valoresTasa.setMoneda(tasa.getMoneda());
				valoresTasa.setImporte(ISBANStringUtils.eliminarCeros(tasa.getImporte()));
				listaValoresTasa.add(valoresTasa);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.bo.PlazoFijoBO#
	 * obtenerTenenciasPlazoFijo(ar.com.santanderrio.obp.servicios.clientes.
	 * entities.Cliente)
	 */
	@Override
	public Respuesta<TenenciaTotalPlazoFijoDTO> obtenerTenenciasPlazoFijo(Cliente cliente) {

		String segmento = Segmento.BANCA_PERSONAL.getCodigo();

		// Se va a efectuar un refactor, para eliminar cuentasRetail, ya que
		// solo discrimina por NUP en Banca Personal.
		List<Cuenta> cuentasRetail = cliente.getCuentasRetail();

		DetalleTenenciaValuadaPFEntity tenenciaValuada = new DetalleTenenciaValuadaPFEntity();
		List<PlazoFijoEntity> plazosFijos = null;

		try {
			plazosFijos = consultaPlazoFijoDAO.obtenerPlazosFijos();
			tenenciaValuada = tenenciaValuadaDAO
					.obtenerTenenciaValuadaDetallePFOnline(createRequestService(cuentasRetail, cliente, segmento));
		} catch (DAOException e) {
			LOGGER.error("Error consultando tenenciaValuadaDAO", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		TenenciaTotalPlazoFijoDTO tenencias = obtenerTenenciasPlazoFijo(tenenciaValuada, plazosFijos, cliente);
		return obtenerTipoRespuesta(cliente, tenencias, tenenciaValuada.getCodigo());
	}

	
	
	/**
	 * Obtener tipo respuesta.
	 *
	 * @param cliente     the cliente
	 * @param tenencias   the tenencias
	 * @param codigoError the codigo error
	 * @return the respuesta
	 */
	private Respuesta<TenenciaTotalPlazoFijoDTO> obtenerTipoRespuesta(Cliente cliente,
			TenenciaTotalPlazoFijoDTO tenencias, int codigoError) {

		if (tenencias == null) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		if (tenencias.getTenenciaPlazoFijoDolares().isEmpty() && tenencias.getTenenciaPlazoFijoPesos().isEmpty()) {
			if (!cuentaBO.hasCuentasMonetariasActivas(cliente)) {
				return respuestaFactory.crearRespuestaWarning(tenencias, "", TipoError.ERROR_SIN_CUENTAS_DEBITO,
						CodigoMensajeConstantes.ERROR_SIN_CUENTAS_DEBITO);
			} else {
				return respuestaFactory.crearRespuestaWarning(tenencias, "", TipoError.ERROR_SIN_TENENCIA_PLAZO_FIJO,
						CodigoMensajeConstantes.ERROR_SIN_TENENCIA_PLAZO_FIJO);
			}
		}
		if (CODIGO_PARCIAL == codigoError) {
			return respuestaFactory.crearRespuestaWarning(tenencias, "", TipoError.WARNING_PARCIAL_PLAZO_FIJO,
					CodigoMensajeConstantes.TOTALES_TENENCIA_INCOMPLETOS);
		}

		return respuestaFactory.crearRespuestaOk(tenencias);
	}

	/**
	 * Obtener tenencias plazo fijo.
	 *
	 * @param tenenciaValuada the tenencia valuada
	 * @param plazosFijos     the plazos fijos
	 * @param cliente         the cliente
	 * @return the tenencia plazo fijo DTO
	 */
	private TenenciaTotalPlazoFijoDTO obtenerTenenciasPlazoFijo(DetalleTenenciaValuadaPFEntity tenenciaValuada,
			List<PlazoFijoEntity> plazosFijos, Cliente cliente) {

		TenenciaTotalPlazoFijoDTO tenenciasPlazoFijoDTO = new TenenciaTotalPlazoFijoDTO();
		if (CODIGO_OK.equals(Integer.valueOf(tenenciaValuada.getCodigo()))
				|| CODIGO_PARCIAL.equals(Integer.valueOf(tenenciaValuada.getCodigo()))) {
			DatosRespuestaPF datosRespuestaPF = tenenciaValuada.getDatos();

			for (ResultadoTenenciaValuadaDetallePFOL tenenciaValuadaEntityPF : datosRespuestaPF.getResultado()) {
				TenenciaValuadaPlazoFijoDTO tenenciaValuadaPlazoFijoDTO = tenenciaPlazoFijoEntityToDto(
						tenenciaValuadaEntityPF, plazosFijos, cliente, TipoBancaEnum.BANCA_PERSONAL);
				insertarSegunMoneda(tenenciasPlazoFijoDTO, tenenciaValuadaEntityPF, tenenciaValuadaPlazoFijoDTO);

				validarLegalCer(tenenciaValuadaEntityPF, tenenciasPlazoFijoDTO);

			}
		}
		return tenenciasPlazoFijoDTO;
	}

	/**
	 * Validar legal cer.
	 *
	 * @param tenenciaValuadaEntityPF the tenencia valuada entity PF
	 * @param tenenciasPlazoFijoDTO   the tenencias plazo fijo DTO
	 * @return the tenencia total plazo fijo DTO
	 */
	private TenenciaTotalPlazoFijoDTO validarLegalCer(ResultadoTenenciaValuadaDetallePFOL tenenciaValuadaEntityPF,
			TenenciaTotalPlazoFijoDTO tenenciasPlazoFijoDTO) {
		String codPlazoFijo = tenenciaValuadaEntityPF.getCodSubProducto();

		if (StringUtils.isNumeric(tenenciaValuadaEntityPF.getCodSubProducto())) {
			codPlazoFijo = ISBANStringUtils.eliminarCeros(tenenciaValuadaEntityPF.getCodSubProducto());
		}

		if (PLAZO_FIJO_CER.equalsIgnoreCase(codPlazoFijo) || codPlazoFijo.equals(PLAZO_FIJO_CER_BPRIV)) {
			if (tenenciasPlazoFijoDTO.getLegalesCer() == null || "".equals(tenenciasPlazoFijoDTO.getLegalesCer())) {
				Respuesta<String> respuestaLegalCer = legalBO
						.buscarLegal(CodigoMensajeConstantes.PLAZO_FIJO_LEGAL_GRILLA_CER);
				if (EstadoRespuesta.ERROR.equals(respuestaLegalCer.getEstadoRespuesta())) {

					return tenenciasPlazoFijoDTO = new TenenciaTotalPlazoFijoDTO();

				}
				tenenciasPlazoFijoDTO.setLegalesCer(respuestaLegalCer.getRespuesta());
			}
		}
		return tenenciasPlazoFijoDTO;
	}

	/**
	 * Inserta la tenencia en la lista correspondiente segun la moneda.
	 *
	 * @param tenenciaTotalDTO        the tenencia plazo fijo DTO
	 * @param tenenciaValuadaEntityPF the tenencia valuada entity PF
	 * @param tenenciaValuadaDTO      the tenencia valuada plazo fijo entity
	 */
	private void insertarSegunMoneda(TenenciaTotalPlazoFijoDTO tenenciaTotalDTO,
			ResultadoTenenciaValuadaDetallePFOL tenenciaValuadaEntityPF,
			TenenciaValuadaPlazoFijoDTO tenenciaValuadaDTO) {

		if (tenenciaValuadaEntityPF.getEstado().equals(ESTADO_NO_DISPONIBLE)) {
			tenenciaTotalDTO.getPlazosFijosVencidos()
					.add(armarPlazoVencido(tenenciaValuadaEntityPF, tenenciaValuadaDTO));
			return;
		}

		if (tenenciaValuadaEntityPF.getMoneda().equals(MONEDA_PESOS)) {
			tenenciaTotalDTO.getTenenciaPlazoFijoPesos().add(tenenciaValuadaDTO);
			tenenciaTotalDTO.getTotalesGrilla()
					.setCapitalInicialArs(null == tenenciaValuadaDTO.getCapitalInicial() ? null
							: Double.valueOf(tenenciaValuadaDTO.getCapitalInicial()));

			tenenciaTotalDTO.getTotalesGrilla()
					.setTenenciaHoyArs(null == tenenciaValuadaDTO.getTenenciaValuadaHastaHoy() ? null
							: Double.valueOf(tenenciaValuadaDTO.getTenenciaValuadaHastaHoy()));

			tenenciaTotalDTO.getTotalesGrilla().setResultadoArs(null == tenenciaValuadaDTO.getResultado() ? null
					: Double.valueOf(tenenciaValuadaDTO.getResultado()));

			if (esProximoAvencer(tenenciaValuadaDTO.getFechaVencimiento())) {
				HashMap<String, BigDecimal> mapaPesos = (HashMap<String, BigDecimal>) tenenciaTotalDTO
						.getPlazosProximosVencerPesos();
				insertarEnMapa(tenenciaValuadaDTO, mapaPesos);
			}
		} else if (tenenciaValuadaEntityPF.getMoneda().equals(MONEDA_DOLARES)) {
			tenenciaTotalDTO.getTenenciaPlazoFijoDolares().add(tenenciaValuadaDTO);

			tenenciaTotalDTO.getTotalesGrilla()
					.setCapitalInicialUsd(null == tenenciaValuadaDTO.getCapitalInicial() ? null
							: Double.valueOf(tenenciaValuadaDTO.getCapitalInicial()));

			tenenciaTotalDTO.getTotalesGrilla()
					.setTenenciaHoyUsd(null == tenenciaValuadaDTO.getTenenciaValuadaHastaHoy() ? null
							: Double.valueOf(tenenciaValuadaDTO.getTenenciaValuadaHastaHoy()));

			tenenciaTotalDTO.getTotalesGrilla().setResultadoUsd(null == tenenciaValuadaDTO.getResultado() ? null
					: Double.valueOf(tenenciaValuadaDTO.getResultado()));

			if (esProximoAvencer(tenenciaValuadaDTO.getFechaVencimiento())) {
				HashMap<String, BigDecimal> mapaDolares = (HashMap<String, BigDecimal>) tenenciaTotalDTO
						.getPlazosProximosVencerDolares();
				insertarEnMapa(tenenciaValuadaDTO, mapaDolares);
			}
		}
	}

	/**
	 * Inserta el elemento en el mapa recibido y si ya lo contenia le suma el valor.
	 * 
	 * @param tenenciaValuadaPlazoFijoEntity the tenencia valuada plazo fijo entity
	 * @param map                            the map
	 */
	private void insertarEnMapa(TenenciaValuadaPlazoFijoDTO tenenciaValuadaPlazoFijoEntity,
			HashMap<String, BigDecimal> map) {
		String clave = tenenciaValuadaPlazoFijoEntity.getFechaVencimiento().substring(INICIO_MES);
		BigDecimal valor = null;
		if (null != tenenciaValuadaPlazoFijoEntity.getTenenciaValuada()) {
			valor = new BigDecimal(tenenciaValuadaPlazoFijoEntity.getTenenciaValuada()).setScale(2,
					BigDecimal.ROUND_HALF_EVEN);

			if (map.containsKey(clave)) {
				map.put(clave, map.get(clave).add(valor));
			} else {
				map.put(clave, valor);
			}
		}
	}

	/**
	 * Devuelve un DTO del plazo vencido en base al plazo entity recibido.
	 * 
	 * @param tenenciaValuadaEntityPF the tenencia valuada entity PF
	 * @param tenenciaDTO             the tenencia DTO
	 * @return the plazo fijo vencido DTO
	 */
	private PlazoFijoVencidoDTO armarPlazoVencido(ResultadoTenenciaValuadaDetallePFOL tenenciaValuadaEntityPF,
			TenenciaValuadaPlazoFijoDTO tenenciaDTO) {
		PlazoFijoVencidoDTO pfVencido = new PlazoFijoVencidoDTO();
		try {
			BeanUtils.copyProperties(pfVencido, tenenciaValuadaEntityPF);
			pfVencido.setGrupo(obtenerGrupo(tenenciaValuadaEntityPF.getCodSubProducto()));
			pfVencido.setFechaVencimiento(convertirFechaTenencias(pfVencido.getFechaVencimiento()));
			pfVencido.setPrioridad(tenenciaDTO.getPrioridad());
			pfVencido.setNumeroCertificado(validaCertificadoPfVencido(tenenciaValuadaEntityPF.getNumeroCertificado()));
		} catch (IllegalAccessException e) {
			LOGGER.error("Error al armar DTO de plazo fijo vencido. ", e);
		} catch (InvocationTargetException e) {
			LOGGER.error("Error al armar DTO de plazo fijo vencido. ", e);
		}
		return pfVencido;
	}

	/**
	 * Devuelve un DTO del plazo vencido en base al plazo entity recibido.
	 * 
	 * @param tenenciaValuadaEntityPF the tenencia valuada entity PF
	 * @param tenenciaDTO             the tenencia DTO
	 * @return the plazo fijo vencido DTO
	 */
	private PlazoFijoVencidoDTO armarPlazoVencidoBPriv(ResultadoTenenciaValuadaDetallePFOL tenenciaValuadaEntityPF,
			TenenciaValuadaPlazoFijoDTO tenenciaDTO) {
		PlazoFijoVencidoDTO pfVencido = new PlazoFijoVencidoDTO();
		try {
			BeanUtils.copyProperties(pfVencido, tenenciaValuadaEntityPF);
			pfVencido.setGrupo(tenenciaValuadaEntityPF.getCodSubProducto());
			pfVencido.setFechaVencimiento(convertirFechaTenencias(pfVencido.getFechaVencimiento()));
			pfVencido.setPrioridad(tenenciaDTO.getPrioridad());
			pfVencido.setNumeroCertificado(validaCertificadoPfVencido(tenenciaValuadaEntityPF.getNumeroCertificado()));
		} catch (IllegalAccessException e) {
			LOGGER.error("Error al armar DTO de plazo fijo vencido. ", e);
		} catch (InvocationTargetException e) {
			LOGGER.error("Error al armar DTO de plazo fijo vencido. ", e);
		}
		return pfVencido;
	}

	/**
	 * Valida certificado pf vencido.
	 *
	 * @param certificado the certificado
	 * @return the string
	 */
	private String validaCertificadoPfVencido(String certificado) {

		if (certificado != null) {
			certificado = (certificado.trim()).length() == 0 ? null : certificado.trim();
			return certificado;
		}

		return certificado;
	}

	/**
	 * Devuelve el nombre del grupo segun corresponda.
	 * 
	 * @param codSubProducto the cod sub producto
	 * @return the string
	 */
	private String obtenerGrupo(String codSubProducto) {
		codSubProducto = ISBANStringUtils.eliminarCeros(codSubProducto);
		if (PF_VENCIDOS_OTROS.equals(codSubProducto) || PF_VENCIDOS_OTROS_2.equals(codSubProducto)) {
			return OTROS_VENCIDOS;
		} else {
			return VENCIDOS_A_COBRAR;
		}
	}

	/**
	 * retorna true, si la fechaVencimiento es mayo o igual a la fecha actual, y si
	 * el año de fechaVencimiento es igual al actual. sino, retorna false.
	 * 
	 * @param fechaVencimiento the fecha vencimiento
	 * @return true, if successful
	 */
	@SuppressWarnings("deprecation")
	private boolean esProximoAvencer(String fechaVencimiento) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		try {
			Date fechaVencimientoDate = formatter.parse(fechaVencimiento);
			Date fechaActual = new Date();
			Date fechaActualMasUnAnio = new Date();
			fechaActualMasUnAnio.setYear(fechaActualMasUnAnio.getYear() + 1);
			if ((fechaVencimientoDate.compareTo(fechaActual) >= 0)
					&& fechaVencimientoDate.compareTo(fechaActualMasUnAnio) <= 0) {
				return true;
			}
		} catch (ParseException e) {
			LOGGER.error("Error al convertir de String a Date ", e);
		}
		return false;
	}

	/**
	 * Obtener tipo.
	 *
	 * @param plazosFijos             the plazos fijos
	 * @param tenenciaValuadaEntityPF the tenencia valuada entity PF
	 * @param tipoBanca               the tipo banca
	 * @return the plazo fijo entity
	 */
	private PlazoFijoEntity obtenerTipo(List<PlazoFijoEntity> plazosFijos,
			ResultadoTenenciaValuadaDetallePFOL tenenciaValuadaEntityPF, TipoBancaEnum tipoBanca) {
		for (PlazoFijoEntity plazoFijoEntity : plazosFijos) {
			if (TipoBancaEnum.BANCA_PRIVADA.equals(tipoBanca) && plazoFijoEntity.getCodigoBPriv() != null) {
				if (plazoFijoEntity.getCodigoBPriv().equals(tenenciaValuadaEntityPF.getCodSubProducto())) {
					return plazoFijoEntity;
				}
			} else if (TipoBancaEnum.BANCA_PERSONAL.equals(tipoBanca) && plazoFijoEntity.getCodigoPlazo()
					.equals(ISBANStringUtils.eliminarCeros(tenenciaValuadaEntityPF.getCodSubProducto()))) {
				return plazoFijoEntity;
			}
		}

		return new PlazoFijoEntity();
	}

	/**
	 * Obtener prioridad.
	 *
	 * @param plazosFijos             the plazos fijos
	 * @param tenenciaValuadaEntityPF the tenencia valuada entity PF
	 * @param tipoBanca               the tipo banca
	 * @return the string
	 */
	private String obtenerPrioridad(List<PlazoFijoEntity> plazosFijos,
			ResultadoTenenciaValuadaDetallePFOL tenenciaValuadaEntityPF, TipoBancaEnum tipoBanca) {
		String prioridad = "";
		for (PlazoFijoEntity plazoFijoEntity : plazosFijos) {
			if (TipoBancaEnum.BANCA_PRIVADA.equals(tipoBanca) && (plazoFijoEntity.getCodigoBPriv() != null)) {
				if (plazoFijoEntity.getCodigoBPriv().equals(tenenciaValuadaEntityPF.getCodSubProducto())) {
					return plazoFijoEntity.getPrioridad();
				}
			} else {
				if (TipoBancaEnum.BANCA_PERSONAL.equals(tipoBanca) && plazoFijoEntity.getCodigoPlazo()
						.equals(ISBANStringUtils.eliminarCeros(tenenciaValuadaEntityPF.getCodSubProducto()))) {
					return plazoFijoEntity.getPrioridad();
				}
			}
		}
		return prioridad;
	}

	/**
	 * Obtener tenencia valuada pazo fijo.
	 *
	 * @param tenenciaValuadaEntityPF the tenencia valuada entity PF
	 * @param plazosFijos             the plazos fijos
	 * @param cliente                 the cliente
	 * @param tipoBanca               the tipo banca
	 * @return the tenencia valuada plazo fijo entity
	 */
	private TenenciaValuadaPlazoFijoDTO tenenciaPlazoFijoEntityToDto(
			ResultadoTenenciaValuadaDetallePFOL tenenciaValuadaEntityPF, List<PlazoFijoEntity> plazosFijos,
			Cliente cliente, TipoBancaEnum tipoBanca) {

		TenenciaValuadaPlazoFijoDTO tenenciaValuadaPlazoFijoEntity = new TenenciaValuadaPlazoFijoDTO();

		if (tenenciaValuadaEntityPF.getCapitalInicial() != null) {
			BigDecimal capitalInicial = tenenciaValuadaEntityPF.getCapitalInicial();
			tenenciaValuadaPlazoFijoEntity
					.setCapitalInicial(capitalInicial.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
		} else {
			tenenciaValuadaPlazoFijoEntity.setCapitalInicial(null);
		}

		tenenciaValuadaPlazoFijoEntity
				.setFechaVencimiento(convertirFechaTenencias(tenenciaValuadaEntityPF.getFechaVencimiento()));
		tenenciaValuadaPlazoFijoEntity
				.setInteres(tenenciaValuadaEntityPF.getInteres().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());

		if (tenenciaValuadaEntityPF.getResultadoBruto() != null) {
			BigDecimal resultado = new BigDecimal(tenenciaValuadaEntityPF.getResultadoBruto());
			tenenciaValuadaPlazoFijoEntity.setResultado(resultado.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
		} else {
			tenenciaValuadaPlazoFijoEntity.setResultado(null);
		}

		if (tenenciaValuadaEntityPF.getTenenciaValuada() != null) {
			BigDecimal tenenciaValuadaHastaHoy = new BigDecimal(tenenciaValuadaEntityPF.getTenenciaValuada());
			tenenciaValuadaPlazoFijoEntity.setTenenciaValuadaHastaHoy(
					tenenciaValuadaHastaHoy.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
		} else {
			tenenciaValuadaPlazoFijoEntity.setTenenciaValuadaHastaHoy(null);
		}

		PlazoFijoEntity plazoFijo = obtenerTipo(plazosFijos, tenenciaValuadaEntityPF, tipoBanca);
		tenenciaValuadaPlazoFijoEntity.setTipo(plazoFijo.getDescripcion());
		tenenciaValuadaPlazoFijoEntity.setCodigoPlazoFijo(plazoFijo.getCodigoPlazo());
		tenenciaValuadaPlazoFijoEntity.setNombreLegal(plazoFijo.getNombreLegal());

		tenenciaValuadaPlazoFijoEntity.setTipo(plazoFijo.getDescripcion());
		tenenciaValuadaPlazoFijoEntity.setCodigoPlazoFijo(plazoFijo.getCodigoPlazo());

		BigDecimal tna = tenenciaValuadaEntityPF.getTna();
		tenenciaValuadaPlazoFijoEntity.setTna(tna.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
		tenenciaValuadaPlazoFijoEntity.setPrioridad(obtenerPrioridad(plazosFijos, tenenciaValuadaEntityPF, tipoBanca));
		tenenciaValuadaPlazoFijoEntity.setTenenciaValuada(tenenciaValuadaEntityPF.getTenenciaValuada());
		tenenciaValuadaPlazoFijoEntity.setImpuestos(cargarImpuestos(tenenciaValuadaEntityPF));
		tenenciaValuadaPlazoFijoEntity.setIntereses(cargarIntereses(tenenciaValuadaEntityPF));
		tenenciaValuadaPlazoFijoEntity
				.setFechaConstitucion(convertirFechaTenencias(tenenciaValuadaEntityPF.getFechaConstitucion()));

		if (TipoBancaEnum.BANCA_PRIVADA.equals(tipoBanca)) {
			tenenciaValuadaPlazoFijoEntity.setNumeroOrden(tenenciaValuadaEntityPF.getNumeroOrden());
			tenenciaValuadaPlazoFijoEntity.setNombreAccionAlVencimiento(tenenciaValuadaEntityPF.getAccionVencimiento());
			// EN BANCA PRIVADA ES SIEMPRE CUENTA UNICA
			tenenciaValuadaPlazoFijoEntity.setDescripcionTipoCuentaDebito(obtenerDescripcionTipoCuenta(CUENTA_UNICA));
			tenenciaValuadaPlazoFijoEntity
					.setNumeroCuentaDestino(obtenerCuentaAccionAlVencimiento(tenenciaValuadaEntityPF, "BP"));
			tenenciaValuadaPlazoFijoEntity.setTipoCuentaDestino(obtenerDescripcionTipoCuenta(CUENTA_UNICA));
			tenenciaValuadaPlazoFijoEntity.setDescripcionTipoCuentaDestino(obtenerDescripcionTipoCuenta(CUENTA_UNICA));
			
			if(PlazoFijoEnum.TRADICIONAL_REPATRIACION.getCodigo().equals(plazoFijo.getCodigoPlazo())) {
				tenenciaValuadaPlazoFijoEntity.setNombreAccionAlVencimiento("Depósito en Cuenta");
				tenenciaValuadaPlazoFijoEntity.setTipoCuentaDestino("Caja de ahorro en u$s");
				tenenciaValuadaPlazoFijoEntity.setDescripcionTipoCuentaDestino("Caja de ahorro en u$s");
				tenenciaValuadaPlazoFijoEntity.setDescripcionTipoCuentaDebito("Caja de ahorro en u$s");
			}
		}
		if (TipoBancaEnum.BANCA_PERSONAL.equals(tipoBanca)) {
			// TODO Eliminar cuando funcione el servicio
			tenenciaValuadaPlazoFijoEntity.setNumeroComprobante(certPlazoFijo(
					tenenciaValuadaEntityPF.getEntidadCuentaInversion(),
					tenenciaValuadaEntityPF.getNumeroCuentaInversion(), tenenciaValuadaEntityPF.getNumeroSecuencia()));

			try {
				tenenciaValuadaPlazoFijoEntity
						.setNumeroCuentaDestino(obtenerCuentaAccionAlVencimiento(tenenciaValuadaEntityPF, "RTL"));

//				 if (tenenciaValuadaEntityPF.getCuentaAccionVencimiento() != null) {
//				tenenciaValuadaPlazoFijoEntity
//						.setTipoCuentaDestino(obtenerTipoCuenta(tenenciaValuadaEntityPF.getCuentaAccionVencimiento()));
//				 }

				if (StringUtils.isEmpty(tenenciaValuadaEntityPF.getCuentaAccionVencimiento())) {
					tenenciaValuadaPlazoFijoEntity.setTipoCuentaDestino(StringUtils.EMPTY);
				} else {
					tenenciaValuadaPlazoFijoEntity
						.setTipoCuentaDestino(obtenerTipoCuenta(tenenciaValuadaEntityPF.getCuentaAccionVencimiento()));
				}

				tenenciaValuadaPlazoFijoEntity.setDescripcionTipoCuentaDestino(
						obtenerDescripcionTipoCuenta(tenenciaValuadaPlazoFijoEntity.getTipoCuentaDestino()));

				tenenciaValuadaPlazoFijoEntity.setDescripcionTipoCuentaDebito(
						obtenerDescripcionTipoCuenta(obtenerTipoCuenta(tenenciaValuadaEntityPF.getCuentaDebito())));

				List<DescripcionAccionAlVencimientoOutEntity> respuestaNombreAccionAlVencimiento = consultaPlazoFijoDAO
						.obtenerDescripcionAccionAlVencimiento(tenenciaValuadaEntityPF.getAccionVencimiento());
				String nombreAccionAlVencimiento = respuestaNombreAccionAlVencimiento.get(0).getDescripcion();
				tenenciaValuadaPlazoFijoEntity.setNombreAccionAlVencimiento(nombreAccionAlVencimiento);
				tenenciaValuadaPlazoFijoEntity.setAccionesAlVencimiento(plazoFijo.getAccionesAlVencimiento());
			} catch (DAOException e) {
				LOGGER.error("Error al obtener descripcion de accion al vencimiento.  ", e);
			}
		}
		tenenciaValuadaPlazoFijoEntity.setNumeroCuentaDebito(cargarNumeroCuenta(
				tenenciaValuadaEntityPF.getCuentaDebito(), tenenciaValuadaEntityPF.getSucursalDebito().toString()));
		tenenciaValuadaPlazoFijoEntity.setCodigoAccion(tenenciaValuadaEntityPF.getAccionVencimiento());
		tenenciaValuadaPlazoFijoEntity.setSucursalRadicacion(StringUtils
				.leftPad(tenenciaValuadaEntityPF.getSucursalRadicacion().toString(), LARGO_COD_SUCURSAL, CERO_STRING));
		tenenciaValuadaPlazoFijoEntity.setCanal(obtenerCanal(tenenciaValuadaEntityPF.getCanal().toString(), tipoBanca));
		tenenciaValuadaPlazoFijoEntity.setPlazoVigencia(tenenciaValuadaEntityPF.getPlazoVigencia().toString());
		tenenciaValuadaPlazoFijoEntity.setInteresesNetosDeImpuestos(obtenerInteresesNetos(tenenciaValuadaEntityPF));
		tenenciaValuadaPlazoFijoEntity.setFrecuenciaCobroInteres(obtenerFrecuenciaCobroIntereses(
				plazoFijo.getMostrarFrecIntereses(), tenenciaValuadaEntityPF.getFrecuenciaCobroIntereses()));
		tenenciaValuadaPlazoFijoEntity.setCuentaPlazo(
				tenenciaValuadaEntityPF.getEntidadCuentaInversion() + tenenciaValuadaEntityPF.getCentroCuentaInversion()
						+ tenenciaValuadaEntityPF.getNumeroCuentaInversion());

		tenenciaValuadaPlazoFijoEntity.setSecuencia(tenenciaValuadaEntityPF.getNumeroSecuencia());
		tenenciaValuadaPlazoFijoEntity.setMontoCobrar(tenenciaValuadaEntityPF.getMontoCobrar().toString());

		if (!ISBANStringUtils.isEmptyOrNull(plazoFijo.getCodigoPlazo())
				&& (plazoFijo.getCodigoPlazo().equals(PlazoFijoEnum.AJUSTABLE_POR_CER.getCodigo()))
				&& tenenciaValuadaEntityPF.getImpuestoCER() != null
				&& tenenciaValuadaEntityPF.getImpuestoCER().compareTo(new BigDecimal("0.0")) != 0) {
			tenenciaValuadaPlazoFijoEntity.setImpuestoCER(tenenciaValuadaEntityPF.getImpuestoCER().toString());
		} else {
			tenenciaValuadaPlazoFijoEntity.setImpuestoCER(null);
		}

		if ((PlazoFijoEnum.UVA.getCodigo().equals(plazoFijo.getCodigoPlazo())
				|| PlazoFijoEnum.UVA_PRECANCELABLE.getCodigo().equals(plazoFijo.getCodigoPlazo()))
				&& tenenciaValuadaEntityPF.getCapitalInicioExpresadoIndice() != null
				&& tenenciaValuadaEntityPF.getCapitalInicioExpresadoIndice().compareTo(new BigDecimal("0.0")) != 0) {
			BigDecimal capitalInicioExpresadoIndice = tenenciaValuadaEntityPF.getCapitalInicioExpresadoIndice();
			tenenciaValuadaPlazoFijoEntity.setCapitalInicioExpresadoIndice(
					capitalInicioExpresadoIndice.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
		} else {
			tenenciaValuadaPlazoFijoEntity.setCapitalInicioExpresadoIndice(null);
		}

		if (PlazoFijoEnum.DIVA.getCodigo().equals(plazoFijo.getCodigoPlazo())
				&& tenenciaValuadaEntityPF.getInteresDevTasaFijaAcum() != null
				&& tenenciaValuadaEntityPF.getInteresDevTasaFijaAcum().compareTo(new BigDecimal("0.0")) != 0) {
			tenenciaValuadaPlazoFijoEntity.setRetribucionFija(tenenciaValuadaEntityPF.getInteresDevTasaFijaAcum()
					.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
		} else {
			tenenciaValuadaPlazoFijoEntity.setRetribucionFija(null);
		}
		tenenciaValuadaPlazoFijoEntity.setCotizacionCERUVA(tenenciaValuadaEntityPF.getCotizacionCERUVA());
		return tenenciaValuadaPlazoFijoEntity;
	}

	/**
	 * Obtener tipo cuenta.
	 *
	 * @param cuentaAccionVencimiento the cuenta accion vencimiento
	 * @return the string
	 */
	private String obtenerTipoCuenta(String cuentaAccionVencimiento) {
		String tipocuenta = String.valueOf(cuentaAccionVencimiento.charAt(5))
				+ String.valueOf(cuentaAccionVencimiento.charAt(6));

		int tipoCuentaNumerico;
		try {
			tipoCuentaNumerico = Integer.valueOf(tipocuenta);
		} catch (NumberFormatException ex) {
			LOGGER.error("Tipo cuenta debito devuelto por servicio de tenencia no numerico. ", ex);
			return null;
		}
		switch (tipoCuentaNumerico) {
		case 7:
			return "09";
		case 2:
			return "01";
		case 3:
			return "04";
		case 5:
			return "00";
		case 6:
			return "03";
		default:
			LOGGER.error(
					"Tipo cuenta devuelto por servicio de tenencia no coincide con ninguno de los tipos correspondientes. ");
			return null;
		}
	}


	/**
	 * Devuelve la frecuencia de cobro de intereses evaluando previamente si debe
	 * mostrarse o no.
	 *
	 * @param mostrarFrecIntereses     the mostrar frec intereses
	 * @param frecuenciaCobroIntereses the frecuencia cobro intereses
	 * @return the string
	 */
	private String obtenerFrecuenciaCobroIntereses(String mostrarFrecIntereses, BigDecimal frecuenciaCobroIntereses) {
		if (String.valueOf(LONGITUD_UNO).equals(mostrarFrecIntereses)) {
			return frecuenciaCobroIntereses.toString();
		}
		return null;
	}

	/**
	 * Retorna true si el plazo fijo se puede precancelar Caso contrario retorna
	 * false.
	 *
	 * @param plazoMinimoPrecancelacion the plazo minimo precancelacion
	 * @param fechaConstitucion         the fecha constitucion
	 * @return true, if successful
	 */
	private boolean permitePrecancelar(String plazoMinimoPrecancelacion, String fechaConstitucion) {
		if (fechaConstitucion != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			try {
				Date fechaconstitucionDate = formatter.parse(fechaConstitucion);
				Date fechaLimite = DateUtils.addDays(fechaconstitucionDate, Integer.valueOf(plazoMinimoPrecancelacion));
				Date fechaActual = new Date();

				if (fechaLimite.compareTo(fechaActual) <= 0) {
					return true;
				}
			} catch (ParseException e) {
				LOGGER.error("Error al convertir de String a Date ", e);
			}
		}
		return false;
	}

	/**
	 * Cargar numero cuenta.
	 *
	 * @param numeroCuenta the cuenta debito
	 * @param sucursal     the sucursal
	 * @return the string
	 */
	private String cargarNumeroCuenta(String numeroCuenta, String sucursal) {
		if (numeroCuenta != null && !numeroCuenta.trim().isEmpty()) {
			sucursal = StringUtils.leftPad(sucursal, LARGO_COD_SUCURSAL, CERO_STRING);
			sucursal = StringUtils.right(sucursal, LARGO_COD_SUCURSAL);
			numeroCuenta = ISBANStringUtils.formatearNumeroCuenta(StringUtils.right(numeroCuenta, LARGO_NRO_CUENTA));
			return sucursal + GUION_MEDIO + numeroCuenta;
		}
		LOGGER.error("Formato o longitud en numero de cuenta devuelto en la tenencia invalido. ");
		return null;
	}

	/**
	 * Obtener cuenta accion al vencimiento.
	 *
	 * @param tenenciaValuadaEntityPF the tenencia valuada entity PF
	 * @return the string
	 */
	private String obtenerCuentaAccionAlVencimiento(ResultadoTenenciaValuadaDetallePFOL tenenciaValuadaEntityPF,
			String banca) {
		if ("DC".equals(tenenciaValuadaEntityPF.getAccionVencimiento()) || "BP".equalsIgnoreCase(banca)) {
			String sucursal = tenenciaValuadaEntityPF.getSucursalAccionVencimiento().toString();
			sucursal = StringUtils.leftPad(sucursal, LARGO_COD_SUCURSAL, CERO_STRING);
			sucursal = StringUtils.right(sucursal, LARGO_COD_SUCURSAL);
			String cuenta = StringUtils.right(tenenciaValuadaEntityPF.getCuentaAccionVencimiento(), LARGO_NRO_CUENTA);
			cuenta = ISBANStringUtils.formatearNumeroCuenta(cuenta);
			return sucursal + GUION_MEDIO + cuenta;
		}
		return null;
	}

	/**
	 * Obtener intereses netos.
	 *
	 * @param tenenciaValuadaEntityPF the tenencia valuada entity PF
	 * @return the string
	 */
	private String obtenerInteresesNetos(ResultadoTenenciaValuadaDetallePFOL tenenciaValuadaEntityPF) {
		if (tenenciaValuadaEntityPF.getImpuestoCER() != null
				&& tenenciaValuadaEntityPF.getImpuestoCER().compareTo(new BigDecimal(0)) > 0) {
			if (tenenciaValuadaEntityPF.getInteres() != null) {
				return tenenciaValuadaEntityPF.getInteres().subtract(tenenciaValuadaEntityPF.getImpuestoCER())
						.toString();
			}
		}
		return null;
	}

	/**
	 * Obtener descripcion tipo cuenta.
	 *
	 * @param tipoCuenta the tipo cuenta
	 * @return the string
	 */
	private String obtenerDescripcionTipoCuenta(String tipoCuenta) {
		String cc = "Cuenta Corriente";
		String ca = "Caja de Ahorro";
		String cu = "Cuenta única";
		int tipoCuentaNumerico;
		try {
			tipoCuentaNumerico = Integer.valueOf(tipoCuenta);
		} catch (NumberFormatException ex) {
			LOGGER.error("Tipo cuenta debito no numerico. ", ex);
			return null;
		}
		switch (tipoCuentaNumerico) {
		case 0:
			return cc;
		case 3:
			return cc;
		case 1:
			return ca;
		case 4:
			return ca;
		case 2:
			return cu;
		case 9:
			return cu;
		case 10:
			return cu;
		default:
			break;
		}
		return null;
	}

	// /**
	// * Obtiene la cuenta de la sesion.
	// * @param numeroCuentaBuscado
	// * the numero cuenta buscado
	// * @param cliente
	// * the cliente
	// * @return the cuenta
	// */
	// private Cuenta obtenerCuenta(String numeroCuentaBuscado, Cliente cliente)
	// {
	// for (Cuenta cuenta : cliente.getCuentas()) {
	// String numeroCuenta = StringUtils.right(cuenta.getNroCuentaProducto(),
	// LARGO_NRO_CUENTA);
	// numeroCuentaBuscado = StringUtils.right(numeroCuentaBuscado,
	// LARGO_NRO_CUENTA);
	// if (numeroCuentaBuscado.equals(numeroCuenta)) {
	// return cuenta;
	// }
	// }
	// return new Cuenta();
	// }

	/**
	 * Cargar intereses.
	 *
	 * @param tenenciaValuadaEntityPF the tenencia valuada entity PF
	 * @return the list
	 */
	private List<MontoPlazoFijo> cargarIntereses(ResultadoTenenciaValuadaDetallePFOL tenenciaValuadaEntityPF) {
		List<MontoPlazoFijo> listaIntereses = new ArrayList<MontoPlazoFijo>();
		if ("V".equals(tenenciaValuadaEntityPF.getMomentoDeCobro1())) {
			MontoPlazoFijo interes = new MontoPlazoFijo();
			interes.setDescripcion(tenenciaValuadaEntityPF.getDescripcionImpuesto1());
			if (TipoMonedaInversionEnum.ARG.getCodigo2().toString().equals(tenenciaValuadaEntityPF.getMoneda())) {
				interes.setMonto(ISBANStringUtils
						.formatearConComaYDosDecimales(tenenciaValuadaEntityPF.getMontoMonedaLocal1().toString()));
			} else {
				interes.setMonto(ISBANStringUtils
						.formatearConComaYDosDecimales(tenenciaValuadaEntityPF.getMontoMonedaExtranjera1().toString()));
			}
			listaIntereses.add(interes);
		}
		if ("V".equals(tenenciaValuadaEntityPF.getMomentoDeCobro2())) {
			MontoPlazoFijo interes = new MontoPlazoFijo();
			interes.setDescripcion(tenenciaValuadaEntityPF.getDescripcionImpuesto2());
			if (TipoMonedaInversionEnum.ARG.getCodigo2().toString().equals(tenenciaValuadaEntityPF.getMoneda())) {
				interes.setMonto(ISBANStringUtils
						.formatearConComaYDosDecimales(tenenciaValuadaEntityPF.getMontoMonedaLocal2().toString()));
			} else {
				interes.setMonto(ISBANStringUtils
						.formatearConComaYDosDecimales(tenenciaValuadaEntityPF.getMontoMonedaExtranjera2().toString()));
			}
			listaIntereses.add(interes);
		}
		if ("V".equals(tenenciaValuadaEntityPF.getMomentoDeCobro3())) {
			MontoPlazoFijo interes = new MontoPlazoFijo();
			interes.setDescripcion(tenenciaValuadaEntityPF.getDescripcionImpuesto3());
			if (TipoMonedaInversionEnum.ARG.getCodigo2().toString().equals(tenenciaValuadaEntityPF.getMoneda())) {
				interes.setMonto(ISBANStringUtils
						.formatearConComaYDosDecimales(tenenciaValuadaEntityPF.getMontoMonedaLocal3().toString()));
			} else {
				interes.setMonto(ISBANStringUtils
						.formatearConComaYDosDecimales(tenenciaValuadaEntityPF.getMontoMonedaExtranjera3().toString()));
			}
			listaIntereses.add(interes);
		}
		if ("V".equals(tenenciaValuadaEntityPF.getMomentoDeCobro4())) {
			MontoPlazoFijo interes = new MontoPlazoFijo();
			interes.setDescripcion(tenenciaValuadaEntityPF.getDescripcionImpuesto4());
			if (TipoMonedaInversionEnum.ARG.getCodigo2().toString().equals(tenenciaValuadaEntityPF.getMoneda())) {
				interes.setMonto(ISBANStringUtils
						.formatearConComaYDosDecimales(tenenciaValuadaEntityPF.getMontoMonedaLocal4().toString()));
			} else {
				interes.setMonto(ISBANStringUtils
						.formatearConComaYDosDecimales(tenenciaValuadaEntityPF.getMontoMonedaExtranjera4().toString()));
			}
			listaIntereses.add(interes);
		}
		if ("V".equals(tenenciaValuadaEntityPF.getMomentoDeCobro5())) {
			MontoPlazoFijo interes = new MontoPlazoFijo();
			interes.setDescripcion(tenenciaValuadaEntityPF.getDescripcionImpuesto5());
			if (TipoMonedaInversionEnum.ARG.getCodigo2().toString().equals(tenenciaValuadaEntityPF.getMoneda())) {
				interes.setMonto(ISBANStringUtils
						.formatearConComaYDosDecimales(tenenciaValuadaEntityPF.getMontoMonedaLocal5().toString()));
			} else {
				interes.setMonto(ISBANStringUtils
						.formatearConComaYDosDecimales(tenenciaValuadaEntityPF.getMontoMonedaExtranjera5().toString()));
			}
			listaIntereses.add(interes);
		}
		return listaIntereses;
	}

	/**
	 * Cargar impuestos.
	 *
	 * @param tenenciaValuadaEntityPF the tenencia valuada entity PF
	 * @return the list
	 */
	private List<MontoPlazoFijo> cargarImpuestos(ResultadoTenenciaValuadaDetallePFOL tenenciaValuadaEntityPF) {
		List<MontoPlazoFijo> listaImpuestos = new ArrayList<MontoPlazoFijo>();
		if ("A".equals(tenenciaValuadaEntityPF.getMomentoDeCobro1())) {
			MontoPlazoFijo impuesto = new MontoPlazoFijo();
			impuesto.setDescripcion(tenenciaValuadaEntityPF.getDescripcionImpuesto1());
			if (TipoMonedaInversionEnum.ARG.getCodigo2().toString().equals(tenenciaValuadaEntityPF.getMoneda())) {
				impuesto.setMonto(ISBANStringUtils
						.formatearConComaYDosDecimales(tenenciaValuadaEntityPF.getMontoMonedaLocal1().toString()));
			} else {
				impuesto.setMonto(ISBANStringUtils
						.formatearConComaYDosDecimales(tenenciaValuadaEntityPF.getMontoMonedaExtranjera1().toString()));
			}
			listaImpuestos.add(impuesto);
		}
		if ("A".equals(tenenciaValuadaEntityPF.getMomentoDeCobro2())) {
			MontoPlazoFijo impuesto = new MontoPlazoFijo();
			impuesto.setDescripcion(tenenciaValuadaEntityPF.getDescripcionImpuesto2());
			if (TipoMonedaInversionEnum.ARG.getCodigo2().toString().equals(tenenciaValuadaEntityPF.getMoneda())) {
				impuesto.setMonto(ISBANStringUtils
						.formatearConComaYDosDecimales(tenenciaValuadaEntityPF.getMontoMonedaLocal2().toString()));
			} else {
				impuesto.setMonto(ISBANStringUtils
						.formatearConComaYDosDecimales(tenenciaValuadaEntityPF.getMontoMonedaExtranjera2().toString()));
			}
			listaImpuestos.add(impuesto);
		}
		if ("A".equals(tenenciaValuadaEntityPF.getMomentoDeCobro3())) {
			MontoPlazoFijo impuesto = new MontoPlazoFijo();
			impuesto.setDescripcion(tenenciaValuadaEntityPF.getDescripcionImpuesto3());
			if (TipoMonedaInversionEnum.ARG.getCodigo2().toString().equals(tenenciaValuadaEntityPF.getMoneda())) {
				impuesto.setMonto(ISBANStringUtils
						.formatearConComaYDosDecimales(tenenciaValuadaEntityPF.getMontoMonedaLocal3().toString()));
			} else {
				impuesto.setMonto(ISBANStringUtils
						.formatearConComaYDosDecimales(tenenciaValuadaEntityPF.getMontoMonedaExtranjera3().toString()));
			}
			listaImpuestos.add(impuesto);
		}
		if ("A".equals(tenenciaValuadaEntityPF.getMomentoDeCobro4())) {
			MontoPlazoFijo impuesto = new MontoPlazoFijo();
			impuesto.setDescripcion(tenenciaValuadaEntityPF.getDescripcionImpuesto4());
			if (TipoMonedaInversionEnum.ARG.getCodigo2().toString().equals(tenenciaValuadaEntityPF.getMoneda())) {
				impuesto.setMonto(ISBANStringUtils
						.formatearConComaYDosDecimales(tenenciaValuadaEntityPF.getMontoMonedaLocal4().toString()));
			} else {
				impuesto.setMonto(ISBANStringUtils
						.formatearConComaYDosDecimales(tenenciaValuadaEntityPF.getMontoMonedaExtranjera4().toString()));
			}
			listaImpuestos.add(impuesto);
		}
		if ("A".equals(tenenciaValuadaEntityPF.getMomentoDeCobro5())) {
			MontoPlazoFijo impuesto = new MontoPlazoFijo();
			impuesto.setDescripcion(tenenciaValuadaEntityPF.getDescripcionImpuesto5());
			if (TipoMonedaInversionEnum.ARG.getCodigo2().toString().equals(tenenciaValuadaEntityPF.getMoneda())) {
				impuesto.setMonto(ISBANStringUtils
						.formatearConComaYDosDecimales(tenenciaValuadaEntityPF.getMontoMonedaLocal5().toString()));
			} else {
				impuesto.setMonto(ISBANStringUtils
						.formatearConComaYDosDecimales(tenenciaValuadaEntityPF.getMontoMonedaExtranjera5().toString()));
			}
			listaImpuestos.add(impuesto);
		}
		return listaImpuestos;
	}

	/**
	 * Crea el request para el servicio de tenencia Plazo Fijo.
	 *
	 * @param cuentas  the cuentas
	 * @param cliente  the cliente
	 * @param segmento the segmento
	 * @return the detalle fondo request entity
	 */
	public DetalleFondoRequestEntity createRequestService(List<Cuenta> cuentas, Cliente cliente, String segmento) {
		DetalleFondoRequestEntity request = new DetalleFondoRequestEntity();

		List<CuentaOPEntity> listaCuenta = new ArrayList<CuentaOPEntity>();

		for (Cuenta cuentaElemento : cuentas) {
			CuentaOPEntity cuenta = new CuentaOPEntity();
			cuenta.setSucursal(cuentaElemento.getNroSucursal());
			cuenta.setNroCuentaOP(cuentaElemento.getNroCuentaProducto());
			listaCuenta.add(cuenta);
		}

		DatosDetalleFondoRequestEntity datosRequest = createDatosRequest(cliente, segmento, listaCuenta);
		request.setDatos(datosRequest);
		return request;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.bo.PlazoFijoBO#
	 * obtenerTenenciasPlazoFijoBpriv(ar.com.santanderrio.obp.servicios.clientes
	 * .entities.Cliente)
	 */
	@Override
	public Respuesta<TenenciaPlazoFijoBprivDTO> obtenerTenenciasPlazoFijoBpriv(Cliente cliente,
			List<CuentaTituloView> cuentasInversionesPFBpriv) {

		String segmento = Segmento.BANCA_PRIVADA.getCodigo();
		List<CuentaBancaPrivada> cuentasBancaPrivada = cliente.getCuentasBancaPrivada();
		List<Cuenta> cuentasOperativas = obtenerCuentasOperativas(cuentasBancaPrivada);
		List<Cuenta> cuentasOperativasRequest = obtenerCuentasOperativasFormateadas(cuentasOperativas, false);
		List<Cuenta> cuentasOperativasRepatriacion = new ArrayList<Cuenta>();

		DetalleTenenciaValuadaPFEntity tenenciaValuada = new DetalleTenenciaValuadaPFEntity();
		List<PlazoFijoEntity> plazosFijos = null;

		for (CuentaTituloView cta : cuentasInversionesPFBpriv) {
			Cuenta cuenta = obtenerCuentaDebito(cliente, cta.getSucursal() + "-" + cta.getNroCuentaFormateado());
			if (cuenta != null) {
				if (esCuentaRepatriacion(cuenta)) {
					cuentasOperativasRepatriacion.add(cuenta);
				}
			}
		}
		cuentasOperativasRequest.addAll(obtenerCuentasOperativasFormateadas(cuentasOperativasRepatriacion, true));
		try {
			plazosFijos = consultaPlazoFijoDAO.obtenerPlazosFijos();
			tenenciaValuada = tenenciaValuadaDAO.obtenerTenenciaValuadaDetallePFOnline(
					createRequestService(cuentasOperativasRequest, cliente, segmento));
		} catch (DAOException e) {
			LOGGER.error("Error consultando tenenciaValuadaDAO", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		TenenciaPlazoFijoBprivDTO tenencias = obtenerTenenciasPlazoFijoBpriv(tenenciaValuada, plazosFijos,
				cuentasOperativasRequest, cliente);

		if (CODIGO_PARCIAL == tenenciaValuada.getCodigo()) {
			return respuestaFactory.crearRespuestaWarning(tenencias, "", TipoError.WARNING_PARCIAL_PLAZO_FIJO, "");
		}

		return respuestaFactory.crearRespuestaOk(tenencias);

	}

	/**
	 * Obtener cuentas operativas formateadas.
	 *
	 * @param cuentasOperativas the cuentas operativas
	 * @return the list
	 */
	private List<Cuenta> obtenerCuentasOperativasFormateadas(List<Cuenta> cuentasOperativas, boolean pfRepatriacion) {
		List<Cuenta> cuentasOperativasFormateadas = new ArrayList<Cuenta>();

		for (Cuenta cuenta : cuentasOperativas) {

			Cuenta cuenta2 = new Cuenta();
			if (cuenta != null) {
				try {
					BeanUtils.copyProperties(cuenta2, cuenta);
				} catch (Exception e) {
					LOGGER.error("Error copiando Cuenta", e);
				}
			}

			BigDecimal nro = new BigDecimal(cuenta2.getNroCuentaProducto());
			String nroCuentaBPFormateado = "";
			if (pfRepatriacion) {
				nroCuentaBPFormateado = "3" + llenarConCerosIzqOTruncar(nro.toString(), 9);
			} else {
				nroCuentaBPFormateado = "7" + llenarConCerosIzqOTruncar(nro.toString(), 9);
			}
			cuenta2.setNroCuentaProducto(nroCuentaBPFormateado);
			cuentasOperativasFormateadas.add(cuenta2);
		}
		return cuentasOperativasFormateadas;
	}

	/**
	 * Obtener cuentas operativas.
	 *
	 * @param cuentasBancaPrivada the cuentas banca privada
	 * @return the list
	 */
	private List<Cuenta> obtenerCuentasOperativas(List<CuentaBancaPrivada> cuentasBancaPrivada) {
		List<Cuenta> cuentasOperativas = new ArrayList<Cuenta>();
		for (CuentaBancaPrivada cuentaBancaPrivada : cuentasBancaPrivada) {
			if (cuentaBancaPrivada.getCuentaOperativa() != null) {
				cuentasOperativas.add(cuentaBancaPrivada.getCuentaOperativa());
			}
		}
		return cuentasOperativas;
	}

	/**
	 * Obtener tenencias plazo fijo bpriv.
	 *
	 * @param tenenciaValuada   the tenencia valuada
	 * @param plazosFijos       the plazos fijos
	 * @param cuentasOperativas the cuentas operativas
	 * @param cliente           the cliente
	 * @return the tenencia plazo fijo bpriv DTO
	 */
	private TenenciaPlazoFijoBprivDTO obtenerTenenciasPlazoFijoBpriv(DetalleTenenciaValuadaPFEntity tenenciaValuada,
			List<PlazoFijoEntity> plazosFijos, List<Cuenta> cuentasOperativas, Cliente cliente) {

		TenenciaPlazoFijoBprivDTO tenenciaPlazoFijoBprivDTO = new TenenciaPlazoFijoBprivDTO();
		List<ContenidoTenenciaBprivDTO> contenidosTenenciaBpriv = new ArrayList<ContenidoTenenciaBprivDTO>();

		for (Cuenta cuentaOperativa : cuentasOperativas) {
			if (CODIGO_OK.equals(Integer.valueOf(tenenciaValuada.getCodigo()))
					|| CODIGO_PARCIAL.equals(Integer.valueOf(tenenciaValuada.getCodigo()))) {
				DatosRespuestaPF datosRespuestaPF = tenenciaValuada.getDatos();
				List<TenenciaValuadaPlazoFijoDTO> tenenciaPlazoFijoPesos = new ArrayList<TenenciaValuadaPlazoFijoDTO>();
				List<TenenciaValuadaPlazoFijoDTO> tenenciaPlazoFijoDolares = new ArrayList<TenenciaValuadaPlazoFijoDTO>();

				TenenciaTotalPlazoFijoDTO tenenciaPlazoFijoDTO = new TenenciaTotalPlazoFijoDTO();
				for (ResultadoTenenciaValuadaDetallePFOL tenenciaValuadaEntityPF : datosRespuestaPF.getResultado()) {
					cargarTenenciasBpriv(cuentaOperativa, tenenciaValuadaEntityPF, plazosFijos, tenenciaPlazoFijoDTO,
							cliente);

					validarLegalCer(tenenciaValuadaEntityPF, tenenciaPlazoFijoDTO);

				}
				ContenidoTenenciaBprivDTO tenenciaPorCuentaBprivDTO = new ContenidoTenenciaBprivDTO();

				// tenenciaPlazoFijoDTO.setTenenciaPlazoFijoPesos(tenenciaPlazoFijoPesos);
				// tenenciaPlazoFijoDTO.setTenenciaPlazoFijoDolares(tenenciaPlazoFijoDolares);
				tenenciaPorCuentaBprivDTO.setTenenciaPlazoFijoDTO(tenenciaPlazoFijoDTO);

				tenenciaPorCuentaBprivDTO.setIntervinientes(cuentaOperativa.getIntervinientes());
				String totalDolares = obtenerTotalesDolaresBpriv(tenenciaPlazoFijoDTO.getTenenciaPlazoFijoDolares())
						.toString();
				String totalPesos = obtenerTotalesPesosBpriv(tenenciaPlazoFijoDTO.getTenenciaPlazoFijoPesos())
						.toString();
				tenenciaPorCuentaBprivDTO.setTotalDolares(totalDolares);
				tenenciaPorCuentaBprivDTO.setTotalPesos(totalPesos);

				String sucursal = ISBANStringUtils.eliminarCeros(cuentaOperativa.getNroSucursal());
				String numeroCuenta = cuentaOperativa.getNroCuentaProducto().substring(3, 10);
				numeroCuenta = numeroCuenta.substring(0, numeroCuenta.length() - 1) + SEPARATOR_BARRA_LATERAL
						+ numeroCuenta.substring(numeroCuenta.length() - 1);
				tenenciaPorCuentaBprivDTO.setNumeroCuenta(sucursal + GUION_MEDIO + numeroCuenta);

				contenidosTenenciaBpriv.add(tenenciaPorCuentaBprivDTO);

				if (tenenciaPlazoFijoPesos.isEmpty() && tenenciaPlazoFijoDolares.isEmpty()) {
					tenenciaPlazoFijoBprivDTO.setMensajeError(SIN_TENENCIA_PLAZO_FIJO_BPRIV);
				}
			}
		}
		tenenciaPlazoFijoBprivDTO.setContenidosTenenciaBpriv(contenidosTenenciaBpriv);
		return tenenciaPlazoFijoBprivDTO;
	}

	/**
	 * Obtener totales dolares bpriv.
	 *
	 * @param listaDolares the lista dolares
	 * @return the big decimal
	 */
	private BigDecimal obtenerTotalesDolaresBpriv(List<TenenciaValuadaPlazoFijoDTO> listaDolares) {

		BigDecimal totalDolares = BigDecimal.valueOf(0);

		for (TenenciaValuadaPlazoFijoDTO tenenciaValuadaPlazoFijoEntity : listaDolares) {
			if (null != tenenciaValuadaPlazoFijoEntity.getTenenciaValuada()) {
				BigDecimal tenenciaValuadaHastaHoy = new BigDecimal(
						tenenciaValuadaPlazoFijoEntity.getTenenciaValuada());
				totalDolares = totalDolares.add(tenenciaValuadaHastaHoy);
			}
		}
		return totalDolares;
	}

	/**
	 * Obtener totales pesos bpriv.
	 *
	 * @param listaPesos the lista pesos
	 * @return the big decimal
	 */
	private BigDecimal obtenerTotalesPesosBpriv(List<TenenciaValuadaPlazoFijoDTO> listaPesos) {

		BigDecimal totalPesos = BigDecimal.valueOf(0);

		for (TenenciaValuadaPlazoFijoDTO tenenciaValuadaPlazoFijoEntity : listaPesos) {
			if (null != tenenciaValuadaPlazoFijoEntity.getTenenciaValuada()) {
				BigDecimal tenenciaValuadaHastaHoy = new BigDecimal(
						tenenciaValuadaPlazoFijoEntity.getTenenciaValuada());
				totalPesos = totalPesos.add(tenenciaValuadaHastaHoy);
			}
		}
		return totalPesos;
	}

	/**
	 * Cargar tenencias bpriv.
	 *
	 * @param cuentaOperativa         the cuenta operativa
	 * @param tenenciaValuadaEntityPF the tenencia valuada entity PF
	 * @param plazosFijos             the plazos fijos
	 * @param tenenciaTotal           the tenencia total
	 * @param cliente                 the cliente
	 */
	private void cargarTenenciasBpriv(Cuenta cuentaOperativa,
			ResultadoTenenciaValuadaDetallePFOL tenenciaValuadaEntityPF, List<PlazoFijoEntity> plazosFijos,
			TenenciaTotalPlazoFijoDTO tenenciaTotal, Cliente cliente) {

		if (Long.valueOf(tenenciaValuadaEntityPF.getCuentaDebito())
				.equals(Long.valueOf(ISBANStringUtils.eliminarCeros(cuentaOperativa.getNroCuentaProducto())))) {
			TenenciaValuadaPlazoFijoDTO tenenciaValuadaPlazoFijoEntity = tenenciaPlazoFijoEntityToDto(
					tenenciaValuadaEntityPF, plazosFijos, cliente, TipoBancaEnum.BANCA_PRIVADA);

			insertarSegunMonedaBpriv(tenenciaValuadaEntityPF, tenenciaValuadaPlazoFijoEntity, tenenciaTotal);
		}
	}

	/**
	 * Insertat moneda bpriv.
	 *
	 * @param tenenciaValuadaEntityPF the tenencia valuada entity PF
	 * @param tenenciaValuadaDTO      the tenencia valuada plazo fijo entity
	 * @param tenenciaTotalDTO        the tenencia total DTO
	 */
	private void insertarSegunMonedaBpriv(ResultadoTenenciaValuadaDetallePFOL tenenciaValuadaEntityPF,
			TenenciaValuadaPlazoFijoDTO tenenciaValuadaDTO, TenenciaTotalPlazoFijoDTO tenenciaTotalDTO) {
		if (tenenciaValuadaEntityPF.getEstado().equals(ESTADO_NO_DISPONIBLE_BPRIV)
				|| tenenciaValuadaEntityPF.getEstado().equals(ESTADO_NO_DISPONIBLE_BPRIV2)) {
			tenenciaTotalDTO.getPlazosFijosVencidos()
					.add(armarPlazoVencidoBPriv(tenenciaValuadaEntityPF, tenenciaValuadaDTO));
			return;
		}
		if (tenenciaValuadaEntityPF.getMoneda().equals(MONEDA_DOLARES)) {
			tenenciaValuadaDTO.setMoneda(DivisaEnum.DOLAR.getCodigo());
			tenenciaTotalDTO.getTenenciaPlazoFijoDolares().add(tenenciaValuadaDTO);
			if (esProximoAvencer(tenenciaValuadaDTO.getFechaVencimiento())) {
				HashMap<String, BigDecimal> mapaDolares = (HashMap<String, BigDecimal>) tenenciaTotalDTO
						.getPlazosProximosVencerDolares();
				insertarEnMapa(tenenciaValuadaDTO, mapaDolares);
			}
			tenenciaTotalDTO.getTotalesGrilla()
					.setCapitalInicialUsd(null == tenenciaValuadaDTO.getCapitalInicial() ? null
							: Double.valueOf(tenenciaValuadaDTO.getCapitalInicial()));
			tenenciaTotalDTO.getTotalesGrilla()
					.setTenenciaHoyUsd(null == tenenciaValuadaDTO.getTenenciaValuadaHastaHoy() ? null
							: Double.valueOf(tenenciaValuadaDTO.getTenenciaValuadaHastaHoy()));

			tenenciaTotalDTO.getTotalesGrilla().setResultadoUsd(null == tenenciaValuadaDTO.getResultado() ? null
					: Double.valueOf(tenenciaValuadaDTO.getResultado()));

		} else if (tenenciaValuadaEntityPF.getMoneda().equals(MONEDA_PESOS)) {
			tenenciaValuadaDTO.setMoneda(DivisaEnum.PESO.getCodigo());
			tenenciaTotalDTO.getTenenciaPlazoFijoPesos().add(tenenciaValuadaDTO);
			if (esProximoAvencer(tenenciaValuadaDTO.getFechaVencimiento())) {
				HashMap<String, BigDecimal> mapaPesos = (HashMap<String, BigDecimal>) tenenciaTotalDTO
						.getPlazosProximosVencerPesos();

				insertarEnMapa(tenenciaValuadaDTO, mapaPesos);
			}
			tenenciaTotalDTO.getTotalesGrilla()
					.setCapitalInicialArs(null == tenenciaValuadaDTO.getCapitalInicial() ? null
							: Double.valueOf(tenenciaValuadaDTO.getCapitalInicial()));

			tenenciaTotalDTO.getTotalesGrilla()
					.setTenenciaHoyArs(null == tenenciaValuadaDTO.getTenenciaValuadaHastaHoy() ? null
							: Double.valueOf(tenenciaValuadaDTO.getTenenciaValuadaHastaHoy()));

			tenenciaTotalDTO.getTotalesGrilla().setResultadoArs(null == tenenciaValuadaDTO.getResultado() ? null
					: Double.valueOf(tenenciaValuadaDTO.getResultado()));

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.bo.PlazoFijoBO#
	 * solicitarPrecancelarPlazoFijo(ar.com.santanderrio.obp.servicios.
	 * inversiones.plazofijo.view.SolicitarPrecancelarInView,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<SolicitarPrecancelarOutDTO> solicitarPrecancelarPlazoFijo(SolicitarPrecancelarInView inview,
			Cliente cliente, Boolean isUvaPrecancelable, boolean uvaPrecaBPriv) {

		ConsultaPrecancelablenPlazoFijoOutEntity consultaPrecancelablenPlazoFijoOutEntity = new ConsultaPrecancelablenPlazoFijoOutEntity();
		ConsultaPrecancelablePlazoFijoInEntity consultaPrecancelablePlazoFijoInEntity = new ConsultaPrecancelablePlazoFijoInEntity();
		consultaPrecancelablePlazoFijoInEntity.setCuentaPlazo(inview.getCuentaPlazo());
		consultaPrecancelablePlazoFijoInEntity
				.setSecuencia(StringUtils.leftPad(inview.getNumeroSecuencia(), LONGITUD_SECUENCIA, CERO_STRING));

		try {
			consultaPrecancelablenPlazoFijoOutEntity = plazoFijoDAO
					.consultarPrecancelable(consultaPrecancelablePlazoFijoInEntity, cliente, uvaPrecaBPriv);
		} catch (DAOException daoe) {
			LOGGER.error("Error al consultar PlazoFijo DAO con ", cliente, daoe);
			return respuestaFactory.crearRespuestaError(SolicitarPrecancelarOutDTO.class, null);
		}

		SolicitarPrecancelarOutDTO solicitud = obtenerConsultaPrecancelablePlazoFijo(
				consultaPrecancelablenPlazoFijoOutEntity, cliente);
		if (isUvaPrecancelable) {
			return respuestaFactory.crearRespuestaOk(solicitud);
		}
		if (permitePrecancelar(solicitud.getPlazoMinimoPrecancelacion(), inview.getFechaConstitucion())) {
			return respuestaFactory.crearRespuestaOk(solicitud);
		} else {
			return casoWarninig(inview.getFechaConstitucion(), solicitud);
		}

	}

	/**
	 * Manejar caso warninig.
	 *
	 * @param fechaConstitucion the fecha constitucion
	 * @param solicitud         the solicitud
	 * @return the respuesta
	 */
	private Respuesta<SolicitarPrecancelarOutDTO> casoWarninig(String fechaConstitucion,
			SolicitarPrecancelarOutDTO solicitud) {
		String mensaje = mensajeBO.obtenerMensajePorCodigo(PRECANCELABLE_NO_CUMPLE_PLAZO_MINIMO).getMensaje();
		String mensajeError = "";

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date fechaconstitucionDate = formatter.parse(fechaConstitucion);
			Date fechaLimite = DateUtils.addDays(fechaconstitucionDate,
					Integer.valueOf(ISBANStringUtils.eliminarCeros(solicitud.getPlazoMinimoPrecancelacion())));
			String theData = formatter.format(fechaLimite);

			mensajeError = MessageFormat.format(mensaje,
					ISBANStringUtils.eliminarCeros(solicitud.getPlazoMinimoPrecancelacion()), theData);
		} catch (ParseException e) {
			LOGGER.error("Error al convertir de String a Date ", e);
		}
		return respuestaFactory.crearRespuestaWarning(SolicitarPrecancelarOutDTO.class, mensajeError, null);
	}

	/**
	 * Obtener consulta precancelable plazo fijo.
	 *
	 * @param respuestaDAO the respuesta DAO
	 * @param cliente      the cliente
	 * @return the solicitar precancelar out DTO
	 */
	private SolicitarPrecancelarOutDTO obtenerConsultaPrecancelablePlazoFijo(
			ConsultaPrecancelablenPlazoFijoOutEntity respuestaDAO, Cliente cliente) {

		String porcentaje = "";
		SolicitarPrecancelarOutDTO precancelarPlazoFijoOutDTO = new SolicitarPrecancelarOutDTO();
		try {
			BigDecimal porcentajePenalizacion = ISBANStringUtils.convertirStrToBigDecimal(
					respuestaDAO.getPorcentajePenalizacion(), DECIMALES_PORCENTAJE_PENALIZACION);
			porcentaje = String.valueOf(porcentajePenalizacion.doubleValue());

		} catch (ImporteConvertException e) {
			LOGGER.error("Error al convertir String a BigDecimal ", e);
		}
		precancelarPlazoFijoOutDTO.setPorcentajePenalizacion(porcentaje);
		precancelarPlazoFijoOutDTO.setPlazoMinimoPrecancelacion(respuestaDAO.getPlazoMinimoPrecancelacion());
		return precancelarPlazoFijoOutDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.bo.PlazoFijoBO#
	 * precancelar(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.
	 * SimularPrecancelarInView, java.lang.String)
	 */
	@Override
	public Respuesta<SimulacionPrecancelableDTO> precancelar(Cliente cliente, SimularPrecancelarInView inView,
			String opcion) {
		PrecancelacionPlazoFijoInEntity requestEntity = crearRequestPrecancelar(inView, opcion, false);
		SimulacionPrecancelableOutEntity outDAO;
		try {
			outDAO = plazoFijoDAO.simularPrecancelable(requestEntity, cliente);
			if (outDAO != null) {
				SimulacionPrecancelableDTO outDTO = new SimulacionPrecancelableDTO();
				BeanUtils.copyProperties(outDTO, outDAO);
				crearPrecancelarResponse(outDTO, outDAO);
				return respuestaFactory.crearRespuestaOk(SimulacionPrecancelableDTO.class, outDTO);
			}
			LOGGER.error("Error al simular precancelacion. ");
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} catch (TimeOutException e) {
			LOGGER.error("Error al simular precancelacion, TimeOutException ", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_FINALIZAR_PLAZO_FIJO_SIN_REINTENTO, "");

		} catch (DAOException e) {
			LOGGER.error("Error al simular precancelacion. ", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} catch (IllegalAccessException e) {
			LOGGER.error("Error al simular precancelacion. ", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} catch (InvocationTargetException e) {
			LOGGER.error("Error al simular precancelacion. ", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

	}

	/**
	 * Crear precancelar response.
	 *
	 * @param outDTO the out DTO
	 * @param outDAO the out DAO
	 * @return the simulacion precancelable DTO
	 */
	private SimulacionPrecancelableDTO crearPrecancelarResponse(SimulacionPrecancelableDTO outDTO,
			SimulacionPrecancelableOutEntity outDAO) {
		try {
			BigDecimal diferenciaIntAVencimiento = ISBANStringUtils
					.convertirStrToBigDecimal(outDAO.getDiferenciaIntAVencimiento(), DECIMALES_PLAZO_FIJO);

			BigDecimal interesesAcobrar = ISBANStringUtils.convertirStrToBigDecimal(outDAO.getInteresAFecha(),
					DECIMALES_PLAZO_FIJO);

			outDTO.setPenalizacionPrecancelacion(diferenciaIntAVencimiento.toString());
			outDTO.setInteresesACobrar(interesesAcobrar.toString());

		} catch (ImporteConvertException e) {
			LOGGER.error("Error al convertir String a BigDecimal ", e);
		}

		outDTO.setPorcentajePenalizacion(outDAO.getPorcentajePenalizacion());
		outDTO.setInteresesPrecancelacion(outDAO.getInteresesPrecancelacion());
		return outDTO;
	}

	/**
	 * Crear request precancelar.
	 *
	 * @param inView the in view
	 * @param opcion the opcion
	 * @return the precancelacion plazo fijo in entity
	 */
	private PrecancelacionPlazoFijoInEntity crearRequestPrecancelar(SimularPrecancelarInView inView, String opcion,
			boolean isUva) {
		PrecancelacionPlazoFijoInEntity requestEntity = new PrecancelacionPlazoFijoInEntity();
		if (!isUva) {
			String nroCuenta = inView.getNumeroCuentaDestino().replace("/", "");
			requestEntity.setNroCuentaCredito(StringUtils.right(nroCuenta, LARGO_NRO_CUENTA));
			String sucursal = StringUtils.left(inView.getNumeroCuentaDestino(), LARGO_COD_SUCURSAL);
			requestEntity.setSucursalCuentaCredito(sucursal);
			requestEntity.setTipoCuentaCredito(inView.getTipoCuentaDestino());
			requestEntity.setDivisaCuentaCredito(inView.getMoneda());
		}
		requestEntity.setCuentaPlazo(inView.getCuentaPlazo());
		requestEntity.setOpcion(opcion);
		requestEntity.setSecuencia(StringUtils.leftPad(inView.getSecuencia().trim(), LONGITUD_PLAZO, CERO_STRING));
		return requestEntity;
	}

	/**
	 * Modificar accion vencimiento.
	 *
	 * @param inView  the in view
	 * @param cliente the cliente
	 * @return the respuesta
	 */
	public Respuesta<String> modificarAccionVencimiento(ModificarAccionInView inView, Cliente cliente) {
		MantenimientoPlazoFijoInEntity requestEntity = crearRequestModificarAccion(inView);
		try {
			MantenimientoPlazoFijoOutEntity outDAO = plazoFijoDAO.confirmarAccionVencimiento(requestEntity, cliente);
			return respuestaFactory.crearRespuestaOk(String.class, outDAO.getIdentificadorCapitalizaIntereses());
		} catch (TimeOutException e) {
			LOGGER.error("Error al convertir String a BigDecimal ", e);
			return respuestaFactory.crearRespuestaError("", TipoError.TIMEOUT, "");
		} catch (DAOException e) {
			LOGGER.error("Error al convertir String a BigDecimal ", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, "");
		}
	}

	/**
	 * Crear request modificar accion.
	 *
	 * @param inView the in view
	 * @return the mantenimiento plazo fijo in entity
	 */
	private MantenimientoPlazoFijoInEntity crearRequestModificarAccion(ModificarAccionInView inView) {
		MantenimientoPlazoFijoInEntity requestEntity = new MantenimientoPlazoFijoInEntity();
		requestEntity.setCertificado(DIEZ_ESPACIOS + DIEZ_ESPACIOS + DIEZ_ESPACIOS);
		requestEntity.setSucursal(
				StringUtils.substring(inView.getCuentaPlazo(), INICIO_CENTRO_CTA_INVERSION, FIN_CENTRO_CTA_INVERSION));
		requestEntity.setCuenta(StringUtils.right(inView.getCuentaPlazo(), ULTIMOS_DOCE));
		requestEntity.setSecuencia(StringUtils.leftPad(inView.getSecuencia(), LONGITUD_SECUENCIA, CERO_STRING));
		requestEntity.setMoneda(inView.getMoneda());
		requestEntity.setRenovacion(calcularRenovacion(inView.getCodigoAccion()));
		requestEntity.setTasa(CERO_STRING + CERO_STRING + CERO_STRING + CERO_STRING + CERO_STRING + CERO_STRING
				+ CERO_STRING + CERO_STRING);
		requestEntity.setCircular(CERO_STRING + CERO_STRING);
		requestEntity.setFormaPago(ESPACIO_EN_BLANCO);
		requestEntity.setSecuenciaCan(StringUtils.leftPad(inView.getSecuencia(), NUEVE_INT, CERO_STRING));
		requestEntity.setCentroGestor(ESPACIO_EN_BLANCO + ESPACIO_EN_BLANCO + ESPACIO_EN_BLANCO + ESPACIO_EN_BLANCO);
		requestEntity.setBloqueoCta(ESPACIO_EN_BLANCO);
		requestEntity.setNssf(CERO_STRING + CERO_STRING + CERO_STRING + CERO_STRING);
		requestEntity.setTarifa(ESPACIO_EN_BLANCO + ESPACIO_EN_BLANCO + ESPACIO_EN_BLANCO + ESPACIO_EN_BLANCO);
		requestEntity.setIndicadorTransferible(ESPACIO_EN_BLANCO);
		requestEntity.setCuentaCredito(DIEZ_ESPACIOS + DIEZ_ESPACIOS);
		requestEntity.setIndicadorCapitalizaIntereses(calcularCapitalizaInteres(inView.getCodigoAccion()));

		return requestEntity;
	}

	/**
	 * Calcular capitaliza interes.
	 *
	 * @param codigoAccion the codigo accion
	 * @return the string
	 */
	private String calcularCapitalizaInteres(String codigoAccion) {
		if (RENOVACION_CAPITAL_E_INTERESES.equals(codigoAccion)) {
			return SI;
		}
		return NO;
	}

	/**
	 * Calcular renovacion.
	 *
	 * @param codigoAccion the codigo accion
	 * @return the string
	 */
	private String calcularRenovacion(String codigoAccion) {
		if (RENOVACION_CAPITAL.equals(codigoAccion) || RENOVACION_CAPITAL_E_INTERESES.equals(codigoAccion)) {
			return SI;
		}
		return NO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.bo.PlazoFijoBO#
	 * obtenerDetalleIntereses(ar.com.santanderrio.obp.servicios.inversiones.
	 * plazofijo.view.DetalleInteresesViewRequest,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<DetalleCobroInteresesViewResponse> obtenerDetalleIntereses(DetalleInteresesViewRequest viewRequest,
			Cliente cliente) {
		ConsultaInteresantePlazoFijoInEntity consultarDetalleInteresesEntity = createEntityRequest(viewRequest);
		try {
			ConsultaInteresantePlazoFijoOutEntity outDao = plazoFijoDAO
					.consultarInteresante(consultarDetalleInteresesEntity, cliente);
			DetalleCobroInteresesViewResponse listaResponse = crearDetallesResponse(
					outDao.getAgrElementosLiquidacion());
			return respuestaFactory.crearRespuestaOk(DetalleCobroInteresesViewResponse.class, listaResponse);
		} catch (DAOException e) {
			LOGGER.error("Error al consultar la frecuencia de cobro de intereses. ", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
	}

	/**
	 * Crear detalles response.
	 *
	 * @param agrElementosLiquidacion the agr elementos liquidacion
	 * @return the detalle cobro intereses view response
	 */
	private DetalleCobroInteresesViewResponse crearDetallesResponse(List<PFInteresanteEntity> agrElementosLiquidacion) {
		DetalleCobroInteresesViewResponse detalleIntereses = new DetalleCobroInteresesViewResponse();
		List<ItemDetalleIntereses> listaDetalleIntereses = detalleIntereses.getListaDetalles();
		Iterator<PFInteresanteEntity> iterator = agrElementosLiquidacion.iterator();
		while (iterator.hasNext()) {
			PFInteresanteEntity detalle = iterator.next();
			ItemDetalleIntereses itemLista = new ItemDetalleIntereses();
			itemLista.setCapital(ISBANStringUtils.formatearSaldosConCerosYSignos(detalle.getCapital()));
			itemLista.setFechaLiquidacion(
					ISBANStringUtils.formatearFechaConSeparadores(detalle.getFechaProximaLiquidacion()));
			itemLista.setImpuestos(ISBANStringUtils.formatearSaldosConCerosYSignos(detalle.getImpuesto()));
			itemLista.setIntereses(ISBANStringUtils.formatearSaldosConCerosYSignos(detalle.getIntereses()));
			itemLista.setTotal(ISBANStringUtils.formatearSaldosConCerosYSignos(detalle.getTotal()));
			listaDetalleIntereses.add(itemLista);
		}
		return detalleIntereses;
	}

	/**
	 * Creates the entity request.
	 *
	 * @param viewRequest the view request
	 * @return the consulta interesante plazo fijo in entity
	 */
	private ConsultaInteresantePlazoFijoInEntity createEntityRequest(DetalleInteresesViewRequest viewRequest) {
		ConsultaInteresantePlazoFijoInEntity entityRequest = new ConsultaInteresantePlazoFijoInEntity();
		entityRequest
				.setCuentaPlazo(StringUtils.leftPad(viewRequest.getCuentaPlazo(), LARGO_CUENTA_PLAZO, CERO_STRING));
		entityRequest.setSecuencia(StringUtils.leftPad(viewRequest.getSecuencia(), LONGITUD_SECUENCIA, CERO_STRING));
		entityRequest.setDatosRell(ESPACIO_EN_BLANCO);
		entityRequest.setFechaProxLiquidacionRell(ESPACIO_EN_BLANCO);
		entityRequest.setFechaUltLiquidacionRell(ESPACIO_EN_BLANCO);
		entityRequest.setIndicadorMasDatosRell(ESPACIO_EN_BLANCO);
		entityRequest.setPlazoRell(ESPACIO_EN_BLANCO);
		entityRequest.setSignoVencImpuestoRell(ESPACIO_EN_BLANCO);
		entityRequest.setVencImpuestoRell(ESPACIO_EN_BLANCO);

		return entityRequest;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.bo.PlazoFijoBO#
	 * descripcionAccionesAlVencimiento(java.lang.String, java.lang.String)
	 */
	@Override
	public String descripcionAccionesAlVencimiento(String codigoPlazo, String codigoAccion) {
		try {
			PlazoFijoEntity plazoFijo = consultaPlazoFijoDAO.obtenerPorCodigo(codigoPlazo);
			List<AccionAlVencimientoOutEntity> listAcciones = plazoFijo.getAccionesAlVencimiento();
			for (AccionAlVencimientoOutEntity accion : listAcciones) {
				if (accion.getCodigoAccion().equals(codigoAccion)) {
					return accion.getDescripcion();
				}
			}
		} catch (DAOException e) {
			LOGGER.error("Error obteniendo las acciones al vencimiento. ", e);
			return null;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.bo.PlazoFijoBO#
	 * vaciarCacheAcciones()
	 */
	@Override
	public Respuesta<Boolean> vaciarCacheAcciones() {
		consultaPlazoFijoDAO.vaciarCacheAcciones();
		return respuestaFactory.crearRespuestaOk(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.bo.PlazoFijoBO#
	 * altaComprobante(ar.com.santanderrio.obp.servicios.inversiones.plazofijo.
	 * dto.AltaComprobantePlazoFijoDTO,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<AltaComprobantePlazoFijoDTO> altaComprobante(
			AltaComprobantePlazoFijoDTO altaComprobantePlazoFijoDTO, Cliente cliente) {
		String codigoPlazo = ISBANStringUtils.eliminarCeros(altaComprobantePlazoFijoDTO.getCodigoPlazo());
		PlazoFijoUVABuilder plazoFijoUVABuilder = null;
		Respuesta<Void> respuestaAltaComprobante = null;
		if (sessionParametros.getAltaComprobantePlazoFijoDTO() == null) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} else {
			if (PlazoFijoEnum.UVA.getCodigo().equals(codigoPlazo)) {
				plazoFijoUVABuilder = new PlazoFijoUVABuilder(cliente).setFechaNacCliente(cliente.getFechaNacimiento())
						.setAltaComprobantePlazoFijoDTO(altaComprobantePlazoFijoDTO);
				respuestaAltaComprobante = scompBO.altaScompTransferencia(plazoFijoUVABuilder);
				sessionParametros.setAltaComprobantePlazoFijoDTO(null);
			}
		}
		if (respuestaAltaComprobante == null) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		return respuestaFactory.crearRespuestaOk(altaComprobantePlazoFijoDTO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.bo.PlazoFijoBO#
	 * obtenerDetallePFInteresanteBP(ar.com.santanderrio.obp.servicios.
	 * inversiones.comun.view.DetallePFInteresanteInView)
	 */
	@Override
	public List<FrecuenciaCobroPFInteresanteOutEntity> obtenerDetallePFInteresanteBP(
			DetallePFInteresanteInView detalleIn) throws BusinessException {

		Credential credencial;
		List<FrecuenciaCobroPFInteresanteOutEntity> listaFrecuencias;
		try {
			credencial = credentialSecurityFactory.getCredentialBySistema(DataBase.BPRIVRACF.getNombreTarget());
		} catch (SQLException e) {
			throw new BusinessException(e);
		}

		DetallePFInteresanteInEntity detallePFInteresanteInEntity = new DetallePFInteresanteInEntity();

		detallePFInteresanteInEntity.setNumeroOrden(detalleIn.getNumeroOrden());
		detallePFInteresanteInEntity.setUsuarioRacf(credencial.getUsuario());
		detallePFInteresanteInEntity.setPasswordRacf(credencial.getPassword());

		try {
			listaFrecuencias = plazoFijoBPrivDAO.obtenerDetallePFInteresanteBP(detallePFInteresanteInEntity);
		} catch (DAOException e) {
			throw new BusinessException(e);
		}

		return listaFrecuencias;
	}

	@Override
	public Respuesta<Reporte> obtenerPlazosFijosReporte(TenenciaPlazoFijoView tenenciaPFView, Cliente cliente) {

		TenenciaPlazoFijoExcel plazosFijosSesion = new TenenciaPlazoFijoExcel();
		TotalesPlazoFijoExcel totalesGrilla = new TotalesPlazoFijoExcel();

		if (!tenenciaPFView.getTenenciaPlazoFijoPesos().isEmpty()) {
			List<TenenciaPlazoFijoExcelValores> listaPesos = new ArrayList<TenenciaPlazoFijoExcelValores>();
			for (TenenciaValuadaPlazoFijoDTO tenenciaPesosDTO : tenenciaPFView.getTenenciaPlazoFijoPesos()) {
				TenenciaPlazoFijoExcelValores tenenciaValorExcel = armarObjetoPF(tenenciaPesosDTO, DivisaEnum.PESO);
				listaPesos.add(tenenciaValorExcel);
				totalesGrilla.setCapitalInicialArs(
						DivisaEnum.PESO.getSimbolo() + " " + ISBANStringUtils.formatearConComaYDosDecimales(
								tenenciaPFView.getTotalesGrilla().getCapitalInicialArs().toString()));
				totalesGrilla.setTenenciaHoyArs(
						DivisaEnum.PESO.getSimbolo() + " " + ISBANStringUtils.formatearConComaYDosDecimales(
								tenenciaPFView.getTotalesGrilla().getTenenciaHoyArs().toString()));
				totalesGrilla.setResultadoArs(DivisaEnum.PESO.getSimbolo() + " " + ISBANStringUtils
						.formatearConComaYDosDecimales(tenenciaPFView.getTotalesGrilla().getResultadoArs().toString()));
			}

			Collections.sort(listaPesos);
			eliminarTipoSiRepetido(listaPesos);
			plazosFijosSesion.setTenenciaPlazoFijoPesos(listaPesos);
		}

		if (!tenenciaPFView.getTenenciaPlazoFijoDolares().isEmpty()) {
			List<TenenciaPlazoFijoExcelValores> listaDolares = new ArrayList<TenenciaPlazoFijoExcelValores>();
			for (TenenciaValuadaPlazoFijoDTO tenenciaDolaresDTO : tenenciaPFView.getTenenciaPlazoFijoDolares()) {
				TenenciaPlazoFijoExcelValores tenenciaValorExcel = armarObjetoPF(tenenciaDolaresDTO, DivisaEnum.DOLAR);
				listaDolares.add(tenenciaValorExcel);
				totalesGrilla.setCapitalInicialUsd(
						DivisaEnum.DOLAR.getSimbolo() + " " + ISBANStringUtils.formatearConComaYDosDecimales(
								tenenciaPFView.getTotalesGrilla().getCapitalInicialUsd().toString()));
				totalesGrilla.setTenenciaHoyUsd(
						DivisaEnum.DOLAR.getSimbolo() + " " + ISBANStringUtils.formatearConComaYDosDecimales(
								tenenciaPFView.getTotalesGrilla().getTenenciaHoyUsd().toString()));
				totalesGrilla.setResultadoUsd(DivisaEnum.DOLAR.getSimbolo() + " " + ISBANStringUtils
						.formatearConComaYDosDecimales(tenenciaPFView.getTotalesGrilla().getResultadoUsd().toString()));
			}

			Collections.sort(listaDolares);
			eliminarTipoSiRepetido(listaDolares);
			plazosFijosSesion.setTenenciaPlazoFijoDolares(listaDolares);
		}

		plazosFijosSesion.setTotalesGrilla(totalesGrilla);
		return reporteDAO.obtenerReporte(plazosFijosSesion, ExcelBuilderHelper.PLAZOS_FIJOS, cliente, false);
	}

	private TenenciaPlazoFijoExcelValores armarObjetoPF(TenenciaValuadaPlazoFijoDTO tenenciaPFDTO, DivisaEnum moneda) {

		TenenciaPlazoFijoExcelValores tenenciaValorExcel = new TenenciaPlazoFijoExcelValores();
		tenenciaValorExcel.setTipo(tenenciaPFDTO.getTipo());
		tenenciaValorExcel.setFechaVencimiento(tenenciaPFDTO.getFechaVencimiento());
		tenenciaValorExcel.setCapitalInicial(moneda.getSimbolo() + " "
				+ ISBANStringUtils.formatearConComaYDosDecimales(tenenciaPFDTO.getCapitalInicial()));
		tenenciaValorExcel.setTna(tenenciaPFDTO.getTna().replaceAll("\\.", ",") + "%");
		tenenciaValorExcel.setTenenciaValuadaHoy(moneda.getSimbolo() + " "
				+ ISBANStringUtils.formatearConComaYDosDecimales(tenenciaPFDTO.getTenenciaValuadaHastaHoy()));
		tenenciaValorExcel.setResultado(moneda.getSimbolo() + " "
				+ ISBANStringUtils.formatearConComaYDosDecimales(tenenciaPFDTO.getResultado()));
		tenenciaValorExcel.setPrioridad(tenenciaPFDTO.getPrioridad());

		return tenenciaValorExcel;
	}

	private void eliminarTipoSiRepetido(List<TenenciaPlazoFijoExcelValores> listaPF) {

		String tipoPF = StringUtils.EMPTY;
		for (TenenciaPlazoFijoExcelValores plazoFijo : listaPF) {
			if (tipoPF.isEmpty()) {
				tipoPF = plazoFijo.getTipo();
			} else if (tipoPF.equals(plazoFijo.getTipo())) {
				plazoFijo.setTipo(StringUtils.EMPTY);
			} else {
				tipoPF = plazoFijo.getTipo();
			}
		}
	}

	@Override
	public Respuesta<Reporte> obtenerPlazosFijosReporteBP(List<ContenidoTenenciaBprivDTO> listaPlazosFijosBP,
			Cliente cliente) {

		CuentaTitulosPFExcelGeneral cuentaTitulosPFGeneral = new CuentaTitulosPFExcelGeneral();
		List<TenenciaPlazoFijoExcel> listaCuentasTitulosExcel = new ArrayList<TenenciaPlazoFijoExcel>();

		for (ContenidoTenenciaBprivDTO cuentaTituloPFBO : listaPlazosFijosBP) {
			TenenciaPlazoFijoExcel cuentaTituloPF = new TenenciaPlazoFijoExcel();
			cuentaTituloPF.setNumeroCuenta(cuentaTituloPFBO.getNumeroCuenta());
			cuentaTituloPF.setIntervinientes(crearListaIntervinientes(cuentaTituloPFBO.getIntervinientes()));

			if (!cuentaTituloPFBO.getTenenciaPlazoFijoDTO().getTenenciaPlazoFijoPesos().isEmpty()) {
				cuentaTituloPF.setTienePlazosFijosPesos(Boolean.TRUE);
				List<TenenciaPlazoFijoExcelValores> listaPesos = new ArrayList<TenenciaPlazoFijoExcelValores>();
				for (TenenciaValuadaPlazoFijoDTO tenenciaPesosDTO : cuentaTituloPFBO.getTenenciaPlazoFijoDTO()
						.getTenenciaPlazoFijoPesos()) {
					TenenciaPlazoFijoExcelValores tenenciaValorExcel = armarObjetoPF(tenenciaPesosDTO, DivisaEnum.PESO);
					listaPesos.add(tenenciaValorExcel);
				}

				Collections.sort(listaPesos);
				eliminarTipoSiRepetido(listaPesos);
				cuentaTituloPF.setTenenciaPlazoFijoPesos(listaPesos);
			} else {
				List<TenenciaPlazoFijoExcelValores> listaPesos = crearListaVacia();
				cuentaTituloPF.setTenenciaPlazoFijoPesos(listaPesos);
			}

			if (!cuentaTituloPFBO.getTenenciaPlazoFijoDTO().getTenenciaPlazoFijoDolares().isEmpty()) {
				cuentaTituloPF.setTienePlazosFijosDolares(Boolean.TRUE);
				List<TenenciaPlazoFijoExcelValores> listaDolares = new ArrayList<TenenciaPlazoFijoExcelValores>();
				for (TenenciaValuadaPlazoFijoDTO tenenciaDolaresDTO : cuentaTituloPFBO.getTenenciaPlazoFijoDTO()
						.getTenenciaPlazoFijoDolares()) {
					TenenciaPlazoFijoExcelValores tenenciaValorExcel = armarObjetoPF(tenenciaDolaresDTO,
							DivisaEnum.DOLAR);
					listaDolares.add(tenenciaValorExcel);
				}

				Collections.sort(listaDolares);
				eliminarTipoSiRepetido(listaDolares);
				cuentaTituloPF.setTenenciaPlazoFijoDolares(listaDolares);
			} else {
				List<TenenciaPlazoFijoExcelValores> listaDolares = crearListaVacia();
				cuentaTituloPF.setTenenciaPlazoFijoDolares(listaDolares);
			}

			if (cuentaTituloPF.getTienePlazosFijosPesos() || cuentaTituloPF.getTienePlazosFijosDolares()) {
				cuentaTituloPF.setTotalesGrilla(new TotalesPlazoFijoExcel(cuentaTituloPFBO));
				listaCuentasTitulosExcel.add(cuentaTituloPF);
			}

		}

		cuentaTitulosPFGeneral.setListaCuentasTitulosExcel(listaCuentasTitulosExcel);
		return reporteDAO.obtenerReporte(cuentaTitulosPFGeneral, "PlazosFijosBP", cliente, false);
	}

	private String crearListaIntervinientes(List<Interviniente> listaIntervinientes) {

		StringBuilder intervinientes = new StringBuilder();
		for (Interviniente interviniente : listaIntervinientes) {
			intervinientes.append(interviniente.getApellido() + ", " + interviniente.getNombre() + " / ");
		}

		return intervinientes.substring(0, intervinientes.length() - 2);
	}

	private List<TenenciaPlazoFijoExcelValores> crearListaVacia() {

		List<TenenciaPlazoFijoExcelValores> lista = new ArrayList<TenenciaPlazoFijoExcelValores>();
		TenenciaPlazoFijoExcelValores tenenciaValorExcel = new TenenciaPlazoFijoExcelValores();
		tenenciaValorExcel.setTipo("");
		tenenciaValorExcel.setFechaVencimiento("");
		tenenciaValorExcel.setCapitalInicial("");
		tenenciaValorExcel.setTna("");
		tenenciaValorExcel.setTenenciaValuadaHoy("");
		tenenciaValorExcel.setResultado("");
		tenenciaValorExcel.setPrioridad("");
		lista.add(tenenciaValorExcel);

		return lista;
	}

	@Override
	public Respuesta<SimulacionPrecancelableUVADTO> precancelarUVA(Cliente cliente, SimularPrecancelarInView inView,
			String opcion) {
		PrecancelacionPlazoFijoInEntity requestEntity = crearRequestPrecancelar(inView, opcion, true);
		SimulacionPrecancelableUvaOutEntity outDAO;
		try {
			outDAO = plazoFijoDAO.solicitarPrecancelacion(requestEntity, cliente, inView.getBanca());
			SimulacionPrecancelableUVADTO outDTO = new SimulacionPrecancelableUVADTO();
			BeanUtils.copyProperties(outDTO, outDAO);
			crearPrecancelarUVAResponse(outDTO, outDAO);
			return respuestaFactory.crearRespuestaOk(SimulacionPrecancelableUVADTO.class, outDTO);
		} catch (TimeOutException e) {
			LOGGER.error("Error al simular precancelacion, TimeOutException ", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_FINALIZAR_PLAZO_FIJO_SIN_REINTENTO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} catch (FechaMinimaPrecancelacionException e) {
			LOGGER.error("Error al simular precancelacion. ", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_FECHA_MINIMA_PRECANCELACION,
					CodigoMensajeConstantes.ERROR_FECHA_MINIMA_PRECANCELACION,
					ISBANStringUtils.formatearFechaPrecancelableUVA(StringUtils.substring(e.getMessage(), 24, 34)));
		} catch (PrecancelacionSolicitadaException e) {
			LOGGER.error("Error al simular precancelacion. ", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_PRECANCELACION_SOLICITADA_PREVIAMENTE,
					CodigoMensajeConstantes.ERROR_PRECANCELACION_SOLICITADA_PREVIAMENTE,
					ISBANStringUtils.formatearFechaPrecancelableUVA(StringUtils.substring(e.getMessage(), 56, 66)));
		} catch (LimiteDePrecancelacionSuperadoException e) {
			LOGGER.error("Error al simular precancelacion. ", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_FECHA_MAXIMA_PRECANCELACION_SUPERADA,
					CodigoMensajeConstantes.ERROR_FECHA_MAXIMA_PRECANCELACION_SUPERADA);
		} catch (DAOException e) {
			LOGGER.error("Error al simular precancelacion. ", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} catch (IllegalAccessException e) {
			LOGGER.error("Error al simular precancelacion. ", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} catch (InvocationTargetException e) {
			LOGGER.error("Error al simular precancelacion. ", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
	}

	/**
	 * Crear precancelar response.
	 *
	 * @param outDTO the out DTO
	 * @param outDAO the out DAO
	 * @return the simulacion precancelable DTO
	 */
	private SimulacionPrecancelableUVADTO crearPrecancelarUVAResponse(SimulacionPrecancelableUVADTO outDTO,
			SimulacionPrecancelableUvaOutEntity outDAO) {
		try {
			BigDecimal diferenciaIntAVencimiento = ISBANStringUtils
					.convertirStrToBigDecimal(outDAO.getDiferenciaIntVtoPrec(), DECIMALES_PLAZO_FIJO);

			BigDecimal interesesAcobrar = ISBANStringUtils.convertirStrToBigDecimal(outDAO.getInteresPrecancelacion(),
					DECIMALES_PLAZO_FIJO);

			outDTO.setDiferenciaIntVtoPrec(diferenciaIntAVencimiento.toString());
			outDTO.setInteresPrecancelacion(interesesAcobrar.toString());

		} catch (ImporteConvertException e) {
			LOGGER.error("Error al convertir String a BigDecimal ", e);
		}

		outDTO.setPorcentajePenalizacion(outDAO.getPorcentajePenalizacion());

		return outDTO;
	}

	
	public RecomendacionResponseEntity obtenerRecomendacion(RecomendacionInView viewRequest) {
		
		RecomendacionResponseEntity responseRecomendacion = new RecomendacionResponseEntity();
		
		try {
			responseRecomendacion = plazoFijoDAO.consultaRecomendacion(armarRequestRecomendacion(viewRequest));
		} catch (BusinessException e) {
			LOGGER.error("Error al obtener recomendaciones", e);
		}
		
		return responseRecomendacion;
	}
	
	private PerfilInversorRequestEntity armarRequestRecomendacion(RecomendacionInView viewRequest) throws BusinessException {
		PerfilInversorRequestEntity requestRecomendacion = new PerfilInversorRequestEntity();
		requestRecomendacion.setTipoHash("0");
		requestRecomendacion.setFirma(generarFirma(datoTitulos));
		requestRecomendacion.setDato("Prueba");
		requestRecomendacion.setDatosFirmados(0);
		requestRecomendacion.getDatos().setNup(sesionCliente.getCliente().getNup());
		requestRecomendacion.getDatos().setIp(sesionCliente.getIpCliente());
		requestRecomendacion.getDatos().setUsuario(sesionCliente.getCliente().getUsuarioRacf());
		requestRecomendacion.getDatos().setDaysCount(viewRequest.getDaysCount());
		requestRecomendacion.getDatos().setSegment(viewRequest.getSegment());
		
		if (BANCA_RETAIL.equals(sessionParametros.getTipoBancaPlazoFijo())) {
			requestRecomendacion.setCanal(canalRTL);
			requestRecomendacion.setSubCanal(subCanalRTL);
		}else if (BANCA_PRIVADA.equals(sessionParametros.getTipoBancaPlazoFijo())){
			requestRecomendacion.setCanal(canalBP);
			requestRecomendacion.setSubCanal(subCanalBP);
		}
		return requestRecomendacion;
	}
	
	public RouterApiResponseEntity llamarRouterApi(String criptoKey, String canal, String subCanal) throws DAOException {
		
		RouterApiResponseEntity responseRouterApi = new RouterApiResponseEntity();
		
			try {
				responseRouterApi = plazoFijoDAO.invocarRouterApi(armarRequestRouterApi(criptoKey,canal,subCanal));
			} catch (Exception e) {
				LOGGER.error("Error al invocar a Router-Api", e);
				throw new DAOException();
			}
		
		return responseRouterApi;
	}
	
	public RouterApiRequestEntity armarRequestRouterApi(String criptoKey, String canal, String subCanal){
		
		RouterApiRequestEntity requestRouterApi = new RouterApiRequestEntity();
		
		try {
			Cliente cliente = sesionCliente.getCliente();
			String passRacf = cliente.getPasswordRacf();
			String userRacf = cliente.getUsuarioRacf();
			String nup = StringUtils.leftPad(cliente.getNup(), 8, "0");
			String fecha = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			String tipoDoc = cliente.getTipoDocumento();
			String nroDoc = StringUtils.leftPad(cliente.getDni(),11,"0");
			String idUsConc = StringUtils.leftPad(SessionUsuarioProvider.getSessionUsuario(),8,"0");
			String canalDosDigitos = StringUtils.leftPad(canal, 2, "0");
			String subCanalCuatroDigitos = StringUtils.leftPad(subCanal, 4, "0");
			
		
			SecureRandom random = new SecureRandom();
			byte[] vector = new byte[16];
			random.nextBytes(vector);
			
			String pass64 = encriptar(passRacf,criptoKey,vector);
			
			requestRouterApi.setCics(iatxCics);
			requestRouterApi.setTransactionId(transId);
			requestRouterApi.setPassword(pass64);
			
			requestRouterApi.setBody("200000000000Q"+canalDosDigitos+"HTML0"+subCanalCuatroDigitos+"000104"+userRacf+"   XXXXXXXX"+idUsConc+"00000014"+fecha+"00000000        00000000CNSPFTIPOS1200000            "+nup+"    00        00  I"+tipoDoc+nroDoc+"1946111500000        0000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ00000");
			
		}
		catch(Exception e) {
			LOGGER.error("Error al armar RequestRouterApi", e);
		}
		
		return requestRouterApi;
	}
	

    /**
     * Aplica la encriptacion AES a la cadena de texto usando la clave indicada
     * @param datos Cadena a encriptar
     * @param claveSecreta Clave para encriptar
     * @return Información encriptada
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws NoSuchPaddingException
     * @throws InvalidAlgorithmParameterException 
     * @throws java.security.InvalidKeyException 
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException 
     */
	private String encriptar(String str, String secretKey, byte[] iv){
		byte[] enc = null;
		byte[] iv64 = null;
		
		try {
	        IvParameterSpec ivspec = new IvParameterSpec(iv);  //NOSONAR
	        Key secretKeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec , ivspec);
			enc = Base64.encodeBase64(cipher.doFinal(str.getBytes("UTF-8")), false);
	        iv64 = Base64.encodeBase64(iv, false);
		}catch(Exception e) {
			LOGGER.error("Error encriptar credenciales", e);
		}
		
        String ivEncode = new String(iv64);
        String encEncode = new String(enc);
        
		return ivEncode+encEncode;
	}
	
}
