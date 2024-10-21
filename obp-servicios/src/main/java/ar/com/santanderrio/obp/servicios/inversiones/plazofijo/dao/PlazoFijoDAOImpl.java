/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.core.MediaType;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.inversiones.comun.InversionWSHelper;
import ar.com.santanderrio.obp.servicios.inversiones.comun.entity.PerfilInversorRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.util.CuentasBancaPrivadaUtil;
import ar.com.santanderrio.obp.servicios.inversiones.exception.FechaMinimaPrecancelacionException;
import ar.com.santanderrio.obp.servicios.inversiones.exception.ImporteMayorAlMaximoException;
import ar.com.santanderrio.obp.servicios.inversiones.exception.LimiteDePrecancelacionSuperadoException;
import ar.com.santanderrio.obp.servicios.inversiones.exception.PrecancelacionSolicitadaException;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.bancaprivada.dao.PlazoFijoBPrivDAO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.bo.PlazoFijoBO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaGralPlazoFijoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaGralPlazoFijoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaInteresantePlazoFijoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaInteresantePlazoFijoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaPrecancelablePlazoFijoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaPrecancelablenPlazoFijoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaPrecancelacionImpuestosAltaPlazoFijoEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaPrecancelacionImpuestosVencPlazoFijoEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaTasasPlazoFijoBPrivOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaTasasPlazoFijoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaTiposPlazoFijoBPrivEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaTiposPlazoFijoBPrivOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaTiposPlazoFijoEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaTiposPlazoFijoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultarCondicionesImpuestosEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.CuentaRossiOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ImposicionImpuestosPlazoFijoEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ImposicionPlazoFijoBPrivOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ImposicionPlazoFijoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ImposicionPlazoFijoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.MantenimientoPlazoFijoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.MantenimientoPlazoFijoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.PrecancelacionPlazoFijoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.RecomendacionResponseEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.RouterApiRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.RouterApiResponseEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.SimulacionPlazoFijoBPrivOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.SimulacionPlazoFijoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.SimulacionPlazoFijoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.SimulacionPrecancelableOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.SimulacionPrecancelableUvaOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.TasasPlazoFijoBPrivEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.TasasPlazoFijoEntity;
import ar.com.santanderrio.obp.servicios.oauth.config.APIcConfigConstants;
import ar.com.santanderrio.obp.servicios.transferencias.exception.SaldoInsuficienteException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TimeOutException;

/**
 * The Class PlazoFijoDAOImpl.
 *
 * @author juan.pablo.picate
 */
/**
 * @author juan.pablo.picate
 *
 */
@Component
@TargetSystem(DataBase.BPRIV)
public class PlazoFijoDAOImpl extends IatxBaseDAO implements PlazoFijoDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PlazoFijoDAOImpl.class);

	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;

	/** The Constant TIMEOUT_EXCEPTION. */
	private static final String TIMEOUT_EXCEPTION = "iatx.exceptions.IatxConnectException: Se envió la transacción al CICS pero no se recibió respuesta.õ";

	/** The constant SUPERA_LIMITE_MAXIMO. */
	private static final int SUPERA_LIMITE_MAXIMO = 10000004;

	/** The constant SALDO_CUENTA_DEBITO_INSUFICIENTE. */
	private static final int SALDO_CUENTA_DEBITO_INSUFICIENTE = 10002122;

	/** The constant SALDO_CUENTA_DEBITO_INSUFICIENTE2. */
	private static final int SALDO_CUENTA_DEBITO_INSUFICIENTE2 = 10000515;

	/** The Constant CUENTA_INACTIVA_PARA_PLAZO_FIJO. */
	private static final int CUENTA_INACTIVA_PARA_PLAZO_FIJO = 10000117;

	/** The Constant CUENTA_INACTIVA_PARA_PLAZO_FIJO. */
	private final int ERROR_FECHA_MINIMA_PRECANCELACION_INVALIDA = 10003000;

	/** The Constant CUENTA_INACTIVA_PARA_PLAZO_FIJO. */
	private final int ERROR_PRECANCELACION_PREVIAMENTE_SOLICITADA = 10003001;

	/** The Constant CUENTA_INACTIVA_PARA_PLAZO_FIJO. */
	private final int ERROR_SUPERA_FECHA_LIMITE_PRECANCELACION = 10003002;

	/** The Constant ENTIDAD_CUENTA_DEBITO. */
	private static final String ENTIDAD_CUENTA_DEBITO = "0072";

	/** The Constant CARACTER_N. */
	private static final String CARACTER_N = "N";

	/** The Constant CARACTER_C. */
	private static final String CARACTER_C = "C";
	
	/** The Constant CARACTER_SIGNO_MAS. */
	private static final String CARACTER_SIGNO_MAS = "+";

	/** The Constant CANT_CAMPOS_INTERES. */
	private static final int CANT_CAMPOS_INTERES = 15;

	private static final int CUENTA_SIMULACION_NO_SELECT = 10002001;
	
	private final String TIPO_PF_REPATRIACION = "94";
	
	@Value("${ROUTER.API.IDSEGURIDAD}")
	private String routerApiID;
	
	@Value("${ROUTER.API.CRIPTOKEY.IDSEGURIDAD}")
	private String routerApiCriptoKeyID;
	
	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** The plazo fijo B priv DAO. */
	@Autowired
	private PlazoFijoBPrivDAO plazoFijoBPrivDAO;

	/** The Constant BANCA_PERSONAL. */
	private static final String BANCA_PERSONAL = "BANCAPERSONAL";

	/** The Constant BANCA_PRIVADA. */
	private static final String BANCA_PRIVADA = "BANCAPRIVADA";

	/** The Constant BANCA_RETAIL. */
	private static final String BANCA_RETAIL = "BR";

	/** The Constant BP. */
	private static final String BP = "BP";

	/** The Constant CERO_STRING. */
	private static final String CERO_STRING = "0";

	/** The Constant TRES_CEROS_STRING. */
	private static final String TRES_CEROS_STRING = "000";
	
	/** The Constant TRES_CEROS_STRING. */
	private static final String STRING_SUC_BP = "250";


	/** The Constant NUMERO_TREINTA. */
	private static final int NUMERO_TREINTA = 30;

	/** The Constant NUMERO_ONCE. */
	private static final int NUMERO_ONCE = 11;

	/** The Constant NUMERO_CUATRO. */
	private static final int NUMERO_CUATRO = 4;
	
	/** The Constant NUMERO_NUEVE. */
	private static final int NUMERO_NUEVE = 9;


	/** The Constant CODIGO_NO_DISPONIBLE. */
	private static final int CODIGO_NO_DISPONIBLE = 10099906;

	/** the banca privada canal. */
	@Value("${BANCA.PRIVADA.CANAL}")
	private String bancaPrivadaCanal;

	/** the banca privada subcanal. */
	@Value("${BANCA.PRIVADA.SUBCANAL}")
	private String bancaPrivadaSubcanal;
	
	/** The Canal Banca Personal */
	@Value("${INVERSIONES.SUBCANAL.BANCAPERSONAL}")
	protected String subCanalBancaPersonal;
	
	/** The Subcanal banca personal. */
	@Value("${INVERSIONES.CANAL.BANCAPERSONAL}")
	protected String canalBancaPersonal;
	
    /** The credential security factory. */
    @Autowired
    private CredentialSecurityFactory credentialSecurityFactory;

	
    /** The rest web client. */
    @Autowired
    private RestWebClient restWebClient;

    /** The Constant NOMBRE_WS. */
    private static final String NOMBRE_WS = "RECOMENDACION.PF";

	private static final String NOMBRE_WS_ROUTER_API = "ROUTER.API";

	private static final String HEADER_AUTHORIZATION = "AUTHORIZATION";

	private static final String VERSION_110 = "110";

	private static final String VERSION_120 = "120";
	
	private static final String VERSION_160 = "160";

	
	/** BO. */
	@Autowired
	private PlazoFijoBO plazoFijoBO;
    
	@Autowired
	private InversionWSHelper inversionWSHelper;
	
	/** The dato firmado tipo. */
	@Value("${INVERSIONES.LICITACIONES.DATOFIRMADO}")
	private String dato;
	
	/**
	 * Se usa el entity de Banca Privada porque INCLUYE al entity de Retail y se
	 * evita armar dos metodos {@inheritDoc}
	 */
	@Override
	public ConsultaTiposPlazoFijoBPrivOutEntity consultarTipos(Cliente cliente, String bancaSeleccionada,
			boolean pfRepatriacion) throws DAOException {
		String banca = bancaSeleccionada.toUpperCase();
		String servicio = "CNSPFTIPOS";
		String version = determinarVersion(banca, pfRepatriacion);
		
		IatxRequest iatxRequest = new IatxRequest(servicio, version);
		ConsultaTiposPlazoFijoBPrivOutEntity consultaTiposPlazoFijoBPrivOutEntity = new ConsultaTiposPlazoFijoBPrivOutEntity();
		ConsultaTiposPlazoFijoOutEntity consultaTiposPlazoFijoOutEntity = new ConsultaTiposPlazoFijoOutEntity();

		
		try {
			IatxRequestData iatxRequestData = generateRequestDataCNSPFTIPO(cliente);
			if (BP.equals(banca) || BANCA_PRIVADA.equals(banca)) {
				iatxRequestData.setCanalTipo(bancaPrivadaCanal);
				iatxRequestData.setSubCanalTipo(bancaPrivadaSubcanal);
			}

			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				if (BP.equals(banca) || BANCA_PRIVADA.equals(banca) || pfRepatriacion) {
					consultaTiposPlazoFijoBPrivOutEntity = processTrama(iatxResponse.getTrama(),
							ConsultaTiposPlazoFijoBPrivOutEntity.class);
				} else if (BANCA_RETAIL.equals(banca) || BANCA_PERSONAL.equals(banca)) {
					consultaTiposPlazoFijoOutEntity = processTrama(iatxResponse.getTrama(),
							ConsultaTiposPlazoFijoOutEntity.class);
					consultaTiposPlazoFijoBPrivOutEntity = trasladarEntityConsultaTipos(
							consultaTiposPlazoFijoOutEntity);
				}

			} else {
				throw new DAOException();
			}
		} catch (IatxException e) {
			handleIatxException(e);
		}
		
		return consultaTiposPlazoFijoBPrivOutEntity;
	}
	
	public String determinarVersion(String banca, boolean pfRepatriacion) {
		String bancaUp = banca.toUpperCase();
		String version = VERSION_110;
		if (BP.equals(bancaUp) || BANCA_PRIVADA.equals(bancaUp) || pfRepatriacion) {
			version = VERSION_120;
		}
		return version;
	}

	public ConsultaTiposPlazoFijoBPrivOutEntity consultarTiposRouterApi(String bancaSeleccionada) throws DAOException {
		String banca = bancaSeleccionada.toUpperCase();	
		String canal  = canalBancaPersonal;
		String subCanal = subCanalBancaPersonal;
		
		Credential criptoKeyRouterApi = obtenerCriptoKeyRouterApi();
		String criptoKey = criptoKeyRouterApi.getPassword();
		
		if (BP.equals(banca) || BANCA_PRIVADA.equals(banca)) {
			canal = bancaPrivadaCanal;
			subCanal = bancaPrivadaSubcanal;
		}
		
		ConsultaTiposPlazoFijoBPrivOutEntity consultaTiposPlazoFijoBPrivOutEntity = new ConsultaTiposPlazoFijoBPrivOutEntity();
        
		RouterApiResponseEntity routerApiResponse;
		try {
			routerApiResponse = plazoFijoBO.llamarRouterApi(criptoKey,canal,subCanal);

			consultaTiposPlazoFijoBPrivOutEntity = processTrama(routerApiResponse.getResult(),
					ConsultaTiposPlazoFijoBPrivOutEntity.class);
		} catch (Exception e) {
			LOGGER.error("Error llamando a Router-Api", e);
			throw new DAOException();
		}
               
		return consultaTiposPlazoFijoBPrivOutEntity;
    }


	/**
	 * El entity de Banca Privada INCLUYE los campos de entity de Retail. Se
	 * traslada para no generar dos metodos
	 * 
	 * @param consultaTiposPlazoFijoOutEntity
	 * @return consultaTiposPlazoFijoBPrivOutEntityTrasladado
	 */
	private ConsultaTiposPlazoFijoBPrivOutEntity trasladarEntityConsultaTipos(
			ConsultaTiposPlazoFijoOutEntity consultaTiposPlazoFijoOutEntity) {

		ConsultaTiposPlazoFijoBPrivOutEntity consultaTiposPlazoFijoBPrivOutEntityTrasladado = new ConsultaTiposPlazoFijoBPrivOutEntity();
		List<ConsultaTiposPlazoFijoBPrivEntity> consultaTiposPlazoFijoBPrivOutEntityList = new ArrayList<ConsultaTiposPlazoFijoBPrivEntity>();

		consultaTiposPlazoFijoBPrivOutEntityTrasladado
				.setCodigoRetornoExtendido(consultaTiposPlazoFijoOutEntity.getCodigoRetornoExtendido());
		consultaTiposPlazoFijoBPrivOutEntityTrasladado.setCantTipoPF(consultaTiposPlazoFijoOutEntity.getCantTipoPF());

		for (ConsultaTiposPlazoFijoEntity consultaTiposPlazoFijoEntity : consultaTiposPlazoFijoOutEntity.getTipoPF()) {

			ConsultaTiposPlazoFijoBPrivEntity consultaTiposPlazoFijoBPrivEntity = new ConsultaTiposPlazoFijoBPrivEntity();

			consultaTiposPlazoFijoBPrivEntity.setDescripcionTipoPF(consultaTiposPlazoFijoEntity.getDescripcionTipoPF());
			consultaTiposPlazoFijoBPrivEntity.setTipoPF(consultaTiposPlazoFijoEntity.getTipoPF());
			consultaTiposPlazoFijoBPrivEntity
					.setIndicadorCorralito(consultaTiposPlazoFijoEntity.getIndicadorCorralito());

			consultaTiposPlazoFijoBPrivOutEntityList.add(consultaTiposPlazoFijoBPrivEntity);
		}

		consultaTiposPlazoFijoBPrivOutEntityTrasladado.setTipoPF(consultaTiposPlazoFijoBPrivOutEntityList);

		return consultaTiposPlazoFijoBPrivOutEntityTrasladado;
	}

	/**
	 * Manejo las exepciones de Iatx.
	 *
	 * @param e the e
	 * @throws TimeOutException the time out exception
	 * @throws DAOException     the DAO exception
	 */
	private void handleIatxException(IatxException e) throws TimeOutException, DAOException {
		if (TIMEOUT_EXCEPTION.equals(e.getMessage())) {
			LOGGER.error(e.getMessage(), e);
			throw new TimeOutException(e.getMessage());
		} else {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConsultaGralPlazoFijoOutEntity consultarTenencia(ConsultaGralPlazoFijoInEntity entity, Cliente cliente)
			throws DAOException {
		String servicio = "CNSPFGRAL_";
		String version = "130";
		IatxRequest iatxRequest = new IatxRequest(servicio, version);
		ConsultaGralPlazoFijoOutEntity consultaGralPlazoFijoOutEntity = new ConsultaGralPlazoFijoOutEntity();

		try {
			IatxRequestData iatxRequestData = generateRequestDataCNSPFGRAL(entity, cliente);
			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				consultaGralPlazoFijoOutEntity = processTrama(iatxResponse.getTrama(),
						ConsultaGralPlazoFijoOutEntity.class);
			} else {

			}
		} catch (IatxException e) {
			handleIatxException(e);
		}
		return consultaGralPlazoFijoOutEntity;
	}

	/**
	 * {@inheritDoc}
	 */
	@Cacheable(cacheNames = { CacheConstants.Names.CACHE_TASAS_PLAZOS_FIJOS })
	@Override
	public ConsultaTasasPlazoFijoBPrivOutEntity consultarTasas(Cliente cliente, String bancaSeleccionada,
			boolean pfRepatriacion) throws DAOException {
		String servicio = "CNSTASPFCN";
		String version = "120";
		String banca = bancaSeleccionada.toUpperCase();
		if (!StringUtils.isBlank(banca)) {
			if (BP.equals(banca) || BANCA_PRIVADA.equals(banca) || pfRepatriacion) {
				version = "150";
			}
		}
		IatxRequest iatxRequest = new IatxRequest(servicio, version);
		ConsultaTasasPlazoFijoOutEntity consultaTasasPlazoFijoOutEntity = new ConsultaTasasPlazoFijoOutEntity();
		ConsultaTasasPlazoFijoBPrivOutEntity consultaTasasPlazoFijoBPrivOutEntity = new ConsultaTasasPlazoFijoBPrivOutEntity();

		try {
			IatxRequestData iatxRequestData = generateRequestDataCNSTASPFCN(cliente);
			if (BP.equals(banca) || BANCA_PRIVADA.equals(banca)) {
				iatxRequestData.setCanalTipo(bancaPrivadaCanal);
				iatxRequestData.setSubCanalTipo(bancaPrivadaSubcanal);
			}
			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				if (BP.equals(banca) || BANCA_PRIVADA.equals(banca) || pfRepatriacion) {
					consultaTasasPlazoFijoBPrivOutEntity = processTrama(iatxResponse.getTrama(),
							ConsultaTasasPlazoFijoBPrivOutEntity.class);
				} else if (BANCA_RETAIL.equals(banca) || BANCA_PERSONAL.equals(banca)) {
					consultaTasasPlazoFijoOutEntity = processTrama(iatxResponse.getTrama(),
							ConsultaTasasPlazoFijoOutEntity.class);
					consultaTasasPlazoFijoBPrivOutEntity = trasladarEntityConsultaTasas(
							consultaTasasPlazoFijoOutEntity);
				}
			} else {
				LOGGER.error("Codigo de error distinto de OK");
				throw new DAOException();
			}
		} catch (IatxException e) {
			handleIatxException(e);
		}
		return consultaTasasPlazoFijoBPrivOutEntity;
	}

	private ConsultaTasasPlazoFijoBPrivOutEntity trasladarEntityConsultaTasas(
			ConsultaTasasPlazoFijoOutEntity consultaTasasPlazoFijoOutEntity) {

		ConsultaTasasPlazoFijoBPrivOutEntity consultaTasasPlazoFijoBPrivOutEntityTrasladado = new ConsultaTasasPlazoFijoBPrivOutEntity();
		List<TasasPlazoFijoBPrivEntity> consultaTasasPlazoFijoBPrivOutEntityList = new ArrayList<TasasPlazoFijoBPrivEntity>();

		consultaTasasPlazoFijoBPrivOutEntityTrasladado
				.setCodigoRetornoExtendido(consultaTasasPlazoFijoOutEntity.getCodigoRetornoExtendido());
		consultaTasasPlazoFijoBPrivOutEntityTrasladado.setCantTipoPF(consultaTasasPlazoFijoOutEntity.getCantTipoPF());

		for (TasasPlazoFijoEntity consultaTasasPlazoFijoOutEntityAux : consultaTasasPlazoFijoOutEntity
				.getListaTasas()) {

			TasasPlazoFijoBPrivEntity tasasPlazoFijoBPrivEntity = new TasasPlazoFijoBPrivEntity(
					consultaTasasPlazoFijoOutEntityAux.getMoneda(), consultaTasasPlazoFijoOutEntityAux.getPlazo(),
					consultaTasasPlazoFijoOutEntityAux.getImporte(),
					consultaTasasPlazoFijoOutEntityAux.getTasaNominalBaja(),
					consultaTasasPlazoFijoOutEntityAux.getTasaNominalCanal(),
					consultaTasasPlazoFijoOutEntityAux.getTipoPF(),
					consultaTasasPlazoFijoOutEntityAux.getDescripcionSubproducto(),
					consultaTasasPlazoFijoOutEntityAux.getPorcentajePenalizacion(),
					consultaTasasPlazoFijoOutEntityAux.getImporteMinimo());

			consultaTasasPlazoFijoBPrivOutEntityList.add(tasasPlazoFijoBPrivEntity);

		}

		consultaTasasPlazoFijoBPrivOutEntityTrasladado.setListaTasas(consultaTasasPlazoFijoBPrivOutEntityList);

		return consultaTasasPlazoFijoBPrivOutEntityTrasladado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dao.PlazoFijoDAO#
	 * vaciarCacheTasas()
	 */
	@CacheEvict(value = CacheConstants.Names.CACHE_TASAS_PLAZOS_FIJOS, allEntries = true)
	@Override
	public void vaciarCacheTasas() {
		LOGGER.info("Se limpia la cache de fondos.");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConsultaInteresantePlazoFijoOutEntity consultarInteresante(ConsultaInteresantePlazoFijoInEntity entity,
			Cliente cliente) throws DAOException {
		String servicio = "CNSPFINTE_";
		String version = "100";
		IatxRequest iatxRequest = new IatxRequest(servicio, version);
		ConsultaInteresantePlazoFijoOutEntity consultaInteresantePlazoFijoOutEntity = new ConsultaInteresantePlazoFijoOutEntity();
		try {
			IatxRequestData iatxRequestData = generateRequestDataCNSPFINTE(entity, cliente);
			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				consultaInteresantePlazoFijoOutEntity = processTrama(iatxResponse.getTrama(),
						ConsultaInteresantePlazoFijoOutEntity.class);
			} else {
				LOGGER.error("Error de IATX desconocido invocando a " + servicio + version + errorCode);
				throw new DAOException();
			}
		} catch (IatxException e) {
			handleIatxException(e);
		}
		return consultaInteresantePlazoFijoOutEntity;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MantenimientoPlazoFijoOutEntity confirmarAccionVencimiento(MantenimientoPlazoFijoInEntity entity,
			Cliente cliente) throws DAOException {
		String servicio = "CMBPFCNLS_";
		String version = "100";
		IatxRequest iatxRequest = new IatxRequest(servicio, version);
		MantenimientoPlazoFijoOutEntity mantenimientoPlazoFijoOutEntity = new MantenimientoPlazoFijoOutEntity();
		try {
			IatxRequestData iatxRequestData = generateRequestDataCMBPFCNLS(entity, cliente);
			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				mantenimientoPlazoFijoOutEntity = processTrama(iatxResponse.getTrama(),
						MantenimientoPlazoFijoOutEntity.class);
			} else {
				LOGGER.error("Error de IATX desconocido invocando a " + servicio + version + errorCode);
				throw new DAOException();
			}
		} catch (IatxException e) {
			handleIatxException(e);
		}
		return mantenimientoPlazoFijoOutEntity;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ImposicionPlazoFijoOutEntity confirmarConstitucion(ImposicionPlazoFijoInEntity entity, Cliente cliente,
			String tipoBanca, boolean pfRepatriacion) throws DAOException {
		String servicio = "ALTPFCNLS_";
		String version = VERSION_160;
		IatxRequestData iatxRequestData = new IatxRequestData();

		IatxRequest iatxRequest = new IatxRequest(servicio, version);
		ImposicionPlazoFijoOutEntity imposicionPlazoFijoOutEntity = new ImposicionPlazoFijoOutEntity();
		ImposicionPlazoFijoBPrivOutEntity imposicionPlazoFijoBPrivOutEntity = new ImposicionPlazoFijoBPrivOutEntity();

		try {
			iatxRequestData = generateRequestDataALTPFCNLS(entity, cliente);
			if (BP.equals(tipoBanca) || BANCA_PRIVADA.equals(tipoBanca)) {
				iatxRequestData.setCanalTipo(bancaPrivadaCanal);
				iatxRequestData.setSubCanalTipo(bancaPrivadaSubcanal);
			}
			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				imposicionPlazoFijoBPrivOutEntity = processTrama(iatxResponse.getTrama(),
						ImposicionPlazoFijoBPrivOutEntity.class);
				imposicionPlazoFijoOutEntity = trasladarEntityConfirmar(imposicionPlazoFijoBPrivOutEntity);
				CollectionUtils.filter(imposicionPlazoFijoOutEntity.getImpuestosPF(), predicadoImpuestosVacios2());
			} else {
				LOGGER.error("Error no conocido de IATX: " + errorCode);
				throw new DAOException("", String.valueOf(errorCode));
			}
		} catch (IatxException e) {
			handleIatxException(e);
		}
		return imposicionPlazoFijoOutEntity;
	}

	private ImposicionPlazoFijoOutEntity trasladarEntityConfirmar(
			ImposicionPlazoFijoBPrivOutEntity imposicionPlazoFijoBPrivOutEntity) {

		ImposicionPlazoFijoOutEntity imposicionPlazoFijoBPrivOutEntityTrasladada = new ImposicionPlazoFijoOutEntity(
				imposicionPlazoFijoBPrivOutEntity.getHeaderTrama(),
				imposicionPlazoFijoBPrivOutEntity.getCodigoRetornoExtendido(),
				imposicionPlazoFijoBPrivOutEntity.getEntidadCuentaPlazo(),
				imposicionPlazoFijoBPrivOutEntity.getSucursalCuentaPlazo(),
				imposicionPlazoFijoBPrivOutEntity.getNumeroCuentaPlazo(),
				imposicionPlazoFijoBPrivOutEntity.getSecuencia(),
				imposicionPlazoFijoBPrivOutEntity.getFechaVencimiento(),
				imposicionPlazoFijoBPrivOutEntity.getPeriodoLiquidacion(),
				imposicionPlazoFijoBPrivOutEntity.getImporteCertificado(),
				imposicionPlazoFijoBPrivOutEntity.getIntereses(), imposicionPlazoFijoBPrivOutEntity.getPlazo(),
				imposicionPlazoFijoBPrivOutEntity.getImporteCertificado(),
				imposicionPlazoFijoBPrivOutEntity.getTotalImpuestosCobrados(),
				imposicionPlazoFijoBPrivOutEntity.getTotalImpuestosAlVencimiento(),
				imposicionPlazoFijoBPrivOutEntity.getFechaAltaReal(),
				imposicionPlazoFijoBPrivOutEntity.getIndicadorPrecancelable(),
				imposicionPlazoFijoBPrivOutEntity.getMinimoDiasPrecancelar(),
				imposicionPlazoFijoBPrivOutEntity.getFechaMinimaPrecancelar(),
				imposicionPlazoFijoBPrivOutEntity.getPorcentajePenalizacion(),
				imposicionPlazoFijoBPrivOutEntity.getTasasPrimerTramo(),
				imposicionPlazoFijoBPrivOutEntity.getTasaVariableMinimoGarantizada(),
				imposicionPlazoFijoBPrivOutEntity.getCodigoUr(), imposicionPlazoFijoBPrivOutEntity.getSaldoInicUr(),
				imposicionPlazoFijoBPrivOutEntity.getCotizacionCodigoUr(),
				imposicionPlazoFijoBPrivOutEntity.getCantImpuestos(),
				imposicionPlazoFijoBPrivOutEntity.getImpuestosPF(),
				imposicionPlazoFijoBPrivOutEntity.getCantidadRepeticionesInteresante(),
				imposicionPlazoFijoBPrivOutEntity.getInteresantePF());

		return imposicionPlazoFijoBPrivOutEntityTrasladada;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SimulacionPlazoFijoOutEntity simularPlazoFijo(SimulacionPlazoFijoInEntity entity, Cliente cliente,
			boolean pfRepatriacion) throws DAOException {
		String servicio = "CNSSIMPFCN";
		String version = "170";

		SimulacionPlazoFijoOutEntity consultarCondicionesOutEntity = new SimulacionPlazoFijoOutEntity();
		SimulacionPlazoFijoBPrivOutEntity imposicionPlazoFijoBPrivOutEntity = new SimulacionPlazoFijoBPrivOutEntity();
		IatxRequest iatxRequest = new IatxRequest(servicio, version);
		try {

			IatxRequestData iatxRequestData = generateRequestDataCNSSIMPFCN(entity, cliente);
			if (CuentasBancaPrivadaUtil.isCuentaBP(entity.getSucursalCuentaDebito())) {
				iatxRequestData.setCanalTipo(bancaPrivadaCanal);
				iatxRequestData.setSubCanalTipo(bancaPrivadaSubcanal);
			}
			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {

				imposicionPlazoFijoBPrivOutEntity = processTrama(iatxResponse.getTrama(),
							SimulacionPlazoFijoBPrivOutEntity.class);
					consultarCondicionesOutEntity = trasladarEntity(imposicionPlazoFijoBPrivOutEntity);

				CollectionUtils.filter(consultarCondicionesOutEntity.getImpuestosPF(), predicadoImpuestosVacios());
			} else {
				manejarErroresIatxCNSSIMPFCN(errorCode);
			}
		} catch (IatxException e) {
			handleIatxException(e);
		}
		return consultarCondicionesOutEntity;
	}

	private SimulacionPlazoFijoOutEntity trasladarEntity(
			SimulacionPlazoFijoBPrivOutEntity imposicionPlazoFijoBPrivOutEntity) {
		SimulacionPlazoFijoOutEntity simulacionPlazoFijoTrasladadaOutEntity = new SimulacionPlazoFijoOutEntity(
				imposicionPlazoFijoBPrivOutEntity.getHeaderTrama(),
				imposicionPlazoFijoBPrivOutEntity.getCodigoRetornoExtendido(),
				imposicionPlazoFijoBPrivOutEntity.getProducto(), imposicionPlazoFijoBPrivOutEntity.getDescProducto(),
				imposicionPlazoFijoBPrivOutEntity.getSubproducto(),
				imposicionPlazoFijoBPrivOutEntity.getDescSubproducto(), imposicionPlazoFijoBPrivOutEntity.getTarifa(),
				imposicionPlazoFijoBPrivOutEntity.getPlazo(), imposicionPlazoFijoBPrivOutEntity.getFechaProven(),
				imposicionPlazoFijoBPrivOutEntity.getPerLiq(), imposicionPlazoFijoBPrivOutEntity.getImporte(),
				imposicionPlazoFijoBPrivOutEntity.getTasa(),
				StringUtils.left(imposicionPlazoFijoBPrivOutEntity.getInteres(), CANT_CAMPOS_INTERES),
				imposicionPlazoFijoBPrivOutEntity.getImporteADebitar(), imposicionPlazoFijoBPrivOutEntity.getImpuCap(),
				imposicionPlazoFijoBPrivOutEntity.getImpuInt(), imposicionPlazoFijoBPrivOutEntity.getTasaRenovacion(),
				imposicionPlazoFijoBPrivOutEntity.getFechaAltaReal(), imposicionPlazoFijoBPrivOutEntity.getTipoPF(),
				imposicionPlazoFijoBPrivOutEntity.getIndicadorPrecancelable(),
				imposicionPlazoFijoBPrivOutEntity.getMinimoDiasPrecancelar(),
				imposicionPlazoFijoBPrivOutEntity.getFechaMinimaPrecancelar(),
				imposicionPlazoFijoBPrivOutEntity.getPorcentajePenalizacion(),
				imposicionPlazoFijoBPrivOutEntity.getTasaPrimerTramo(),
				imposicionPlazoFijoBPrivOutEntity.getTasaVarMinimoGarantizada(),
				imposicionPlazoFijoBPrivOutEntity.getCodigoUr(), imposicionPlazoFijoBPrivOutEntity.getSaldoInicUr(),
				imposicionPlazoFijoBPrivOutEntity.getCotizacionCodigoUr(),
				imposicionPlazoFijoBPrivOutEntity.getCantImpuestos(),
				imposicionPlazoFijoBPrivOutEntity.getImpuestosPF(),
				imposicionPlazoFijoBPrivOutEntity.getCantidadRepeticionesInteresante(),
				imposicionPlazoFijoBPrivOutEntity.getInteresantePF());

		return simulacionPlazoFijoTrasladadaOutEntity;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConsultaPrecancelablenPlazoFijoOutEntity consultarPrecancelable(
			ConsultaPrecancelablePlazoFijoInEntity entity, Cliente cliente, boolean uvaPrecaBPriv) throws DAOException {
		String servicio = "CNSPFPRECA";
		String version = "110";
		IatxRequest iatxRequest = new IatxRequest(servicio, version);
		ConsultaPrecancelablenPlazoFijoOutEntity consultaPrecancelacionPlazoFijoOutEntity = new ConsultaPrecancelablenPlazoFijoOutEntity();
		try {
			IatxRequestData iatxRequestData = generateRequestDataCNSPFPRECA(entity, cliente);
			if (uvaPrecaBPriv) {
				iatxRequestData.setCanalTipo(bancaPrivadaCanal);
				iatxRequestData.setSubCanalTipo(bancaPrivadaSubcanal);
			}
			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				consultaPrecancelacionPlazoFijoOutEntity = processTrama(iatxResponse.getTrama(),
						ConsultaPrecancelablenPlazoFijoOutEntity.class);
				CollectionUtils.filter(consultaPrecancelacionPlazoFijoOutEntity.getImpuestosAltaPF(),
						predicadoImpuestosVacios3());
				CollectionUtils.filter(consultaPrecancelacionPlazoFijoOutEntity.getImpuestosVencPF(),
						predicadoImpuestosVacios4());
			} else {
				throw new DAOException();
			}
		} catch (IatxException e) {
			handleIatxException(e);
		}
		return consultaPrecancelacionPlazoFijoOutEntity;
	}

	/**
	 * Simula la Pre-Cancelacion de un Plazo Fijo, No la simulacion de un plazo fijo
	 * PreCancelable (Cuidado!!) simulacion/precancelacion plazos fijos
	 * {@inheritDoc}
	 */
	@Override
	public SimulacionPrecancelableOutEntity simularPrecancelable(PrecancelacionPlazoFijoInEntity entity,
			Cliente cliente) throws DAOException {
		String servicio = "PRECASIMCN";
		IatxRequestData iatxRequestData = new IatxRequestData();
		String version = "110";
		SimulacionPrecancelableOutEntity precancelacionPlazoFijoOutEntity = new SimulacionPrecancelableOutEntity();
		try {
			if (CuentasBancaPrivadaUtil.isCuentaBP(entity.getSucursalCuentaCredito())) {
				servicio = "PRECASIM__";
				version = "100";
				iatxRequestData.setCanalTipo(bancaPrivadaCanal);
				iatxRequestData.setSubCanalTipo(bancaPrivadaSubcanal);
			}
			iatxRequestData = generateRequestDataPRECASIMCN(entity, cliente);
			IatxRequest iatxRequest = new IatxRequest(servicio, version);
			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				precancelacionPlazoFijoOutEntity = processTrama(iatxResponse.getTrama(),
						SimulacionPrecancelableOutEntity.class);
			} else {
				LOGGER.error("Codigo de retorno de IATX desconocido: " + errorCode);
				throw new DAOException();
			}
		} catch (IatxException e) {
			handleIatxException(e);
		}
		return precancelacionPlazoFijoOutEntity;
	}

	/**
	 * Generate request data CNSPFTIPO.
	 *
	 * @param cliente the cliente
	 * @return el objeto para llamar al servicio IATX
	 */
	private IatxRequestData generateRequestDataCNSPFTIPO(Cliente cliente) {
		IatxRequestData iatxRequestData = new IatxRequestData(cliente);
		return iatxRequestData;
	}

	/**
	 * Generate request data CNSTASPFCN.
	 *
	 * @param cliente the cliente
	 * @return el objeto para llamar al servicio IATX
	 */
	private IatxRequestData generateRequestDataCNSTASPFCN(Cliente cliente) {
		IatxRequestData iatxRequestData = new IatxRequestData(cliente);
		return iatxRequestData;
	}

	/**
	 * Generate request data CNSPFINTE.
	 *
	 * @param entity  the entity
	 * @param cliente the cliente
	 * @return el objeto para llamar al servicio IATX
	 */
	private IatxRequestData generateRequestDataCNSPFINTE(ConsultaInteresantePlazoFijoInEntity entity, Cliente cliente) {
		IatxRequestData iatxRequestData = new IatxRequestData(cliente);
		iatxRequestData.addBodyValue(entity.getCuentaPlazo());
		iatxRequestData.addBodyValue(entity.getSecuencia());
		iatxRequestData.addBodyValue(entity.getVencImpuestoRell());
		iatxRequestData.addBodyValue(entity.getSignoVencImpuestoRell());
		iatxRequestData.addBodyValue(entity.getFechaUltLiquidacionRell());
		iatxRequestData.addBodyValue(entity.getFechaProxLiquidacionRell());
		iatxRequestData.addBodyValue(entity.getPlazoRell());
		iatxRequestData.addBodyValue(entity.getIndicadorMasDatosRell());
		iatxRequestData.addBodyValue(entity.getDatosRell());
		return iatxRequestData;
	}

	/**
	 * Generate request data CNSPFGRAL.
	 *
	 * @param entity  the entity
	 * @param cliente the cliente
	 * @return el objeto para llamar al servicio IATX
	 */
	private IatxRequestData generateRequestDataCNSPFGRAL(ConsultaGralPlazoFijoInEntity entity, Cliente cliente) {
		IatxRequestData iatxRequestData = new IatxRequestData(cliente);
		iatxRequestData.addBodyValue(entity.getSucursalCuenta());
		iatxRequestData.addBodyValue(entity.getSucursalCuenta());
		iatxRequestData.addBodyValue(entity.getEjecutivo());
		iatxRequestData.addBodyValue(entity.getIndicadorEstadoPF());
		iatxRequestData.addBodyValue(entity.getCustodia());
		iatxRequestData.addBodyValue(entity.getTipoFecha());
		iatxRequestData.addBodyValue(entity.getFechaDesde());
		iatxRequestData.addBodyValue(entity.getFechaHasta());
		iatxRequestData.addBodyValue(entity.getFechaContable());
		iatxRequestData.addBodyValue(entity.getIndicadorEstado2());
		iatxRequestData.addBodyValue(entity.getCantidadRegistrosAConsultar());
		iatxRequestData.addBodyValue(entity.getCuentaRellamada());
		iatxRequestData.addBodyValue(entity.getNroSecuenciaRellamada());
		iatxRequestData.addBodyValue(entity.getNroSecuencia());
		return iatxRequestData;
	}

	/**
	 * Generate request data CMBPFCNLS.
	 *
	 * @param entity  the entity
	 * @param cliente the cliente
	 * @return el objeto para llamar al servicio IATX
	 */
	private IatxRequestData generateRequestDataCMBPFCNLS(MantenimientoPlazoFijoInEntity entity, Cliente cliente) {
		IatxRequestData iatxRequestData = new IatxRequestData(cliente);
		iatxRequestData.addBodyValue(entity.getCertificado());
		iatxRequestData.addBodyValue(entity.getSucursal());
		iatxRequestData.addBodyValue(entity.getCuenta());
		iatxRequestData.addBodyValue(entity.getSecuencia());
		iatxRequestData.addBodyValue(entity.getMoneda());
		iatxRequestData.addBodyValue(entity.getRenovacion());
		iatxRequestData.addBodyValue(entity.getTasa());
		iatxRequestData.addBodyValue(entity.getCircular());
		iatxRequestData.addBodyValue(entity.getFormaPago());
		iatxRequestData.addBodyValue(entity.getSecuenciaCan());
		iatxRequestData.addBodyValue(entity.getCentroGestor());
		iatxRequestData.addBodyValue(entity.getBloqueoCta());
		iatxRequestData.addBodyValue(entity.getNssf());
		iatxRequestData.addBodyValue(entity.getTarifa());
		iatxRequestData.addBodyValue(entity.getIndicadorTransferible());
		iatxRequestData.addBodyValue(entity.getCuentaCredito());
		iatxRequestData.addBodyValue(entity.getIndicadorCapitalizaIntereses());
		return iatxRequestData;
	}

	/**
	 * Generate request data ALTPFCNLS.
	 *
	 * @param entity  the entity
	 * @param cliente the cliente
	 * @return el objeto para llamar al servicio IATX
	 */
	private IatxRequestData generateRequestDataALTPFCNLS(ImposicionPlazoFijoInEntity entity, Cliente cliente) {
		IatxRequestData iatxRequestData = new IatxRequestData(cliente);

		Boolean esBancaPrivada;
		Boolean esPfRepatriacion = TIPO_PF_REPATRIACION.equals(entity.getTipoPF());

		if (CuentasBancaPrivadaUtil.isCuentaBP(entity.getSucursalCuentaDebito())) {
			esBancaPrivada = true;
		} else {
			esBancaPrivada = false;
		}

		iatxRequestData.addBodyValue(ENTIDAD_CUENTA_DEBITO);
		iatxRequestData.addBodyValue(ISBANStringUtils.formateadorConCerosIzq(entity.getSucursalCuentaDebito(), NUMERO_CUATRO));
		
		if (Boolean.TRUE.equals(esBancaPrivada)) {
			iatxRequestData.addBodyValue(entity.getNroCuentaDebito());
		}else {
			iatxRequestData.addBodyValue(entity.getNroContratoAltair());
		}
		
		iatxRequestData.addBodyValue(entity.getDivisa());
		iatxRequestData.addBodyValue(ENTIDAD_CUENTA_DEBITO);
		iatxRequestData.addBodyValue(ISBANStringUtils.formateadorConCerosIzq(entity.getSucursalCuentaDebito(), NUMERO_CUATRO));
		
		if (Boolean.TRUE.equals(esBancaPrivada)) {
			iatxRequestData.addBodyValue(entity.getNroCuentaDebito());
		}else {
			iatxRequestData.addBodyValue(entity.getNroContratoAltair());
		}
		
		iatxRequestData.addBodyValue(entity.getDivisa());
		iatxRequestData.addBodyValue(entity.getProducto());
		iatxRequestData.addBodyValue(entity.getSubproducto());
		iatxRequestData.addBodyValue(entity.getEntidadCuentaPlazo());
		iatxRequestData.addBodyValue(entity.getSucursalCuentaPlazo());
		iatxRequestData.addBodyValue(entity.getNroCuentaPlazo());
		iatxRequestData.addBodyValue(entity.getFechaAlta());
		iatxRequestData.addBodyValue(entity.getPlazo());
		iatxRequestData.addBodyValue(entity.getImporteCertificado());
		iatxRequestData.addBodyValue(entity.getDivisa());
		iatxRequestData.addBodyValue(StringUtils.leftPad(entity.getTasa(), NUMERO_NUEVE, CERO_STRING));
		iatxRequestData.addBodyValue(entity.getCodigoTarifa());
		iatxRequestData.addBodyValue(entity.getIndicadorRenovacion());
		iatxRequestData.addBodyValue(entity.getIndicadorCapitalizaIntereses());
		iatxRequestData.addBodyValue(entity.getPeriodoLiquidacion());
		iatxRequestData.addBodyValue(StringUtils.right(entity.getSecuencia(), 5));
		iatxRequestData.addBodyValue(entity.getTipoPF());
		iatxRequestData.addBodyValue(entity.getCantidadDias());
		iatxRequestData.addBodyValue(StringUtils.leftPad(entity.getTasaVariable(), NUMERO_NUEVE, CERO_STRING));
		iatxRequestData.addBodyValue(CARACTER_SIGNO_MAS);
		iatxRequestData.addBodyValue(StringUtils.leftPad(entity.getSpread(), NUMERO_NUEVE, CERO_STRING));
		iatxRequestData.addBodyValue(CARACTER_SIGNO_MAS);
		iatxRequestData.addBodyValue(entity.getTipoDiaLiqVariable());
		iatxRequestData.addBodyValue(entity.getNumDiaLiqVariable());
		iatxRequestData.addBodyValue(entity.getSaldoInicUr());
		iatxRequestData.addBodyValue(entity.getCotizacionCodigoUr());

		if (esBancaPrivada) {
			if(esPfRepatriacion) {
				//Cuando es un pf repatriacion no se invoca al servicio rossi.
				//Se harcodea suc 250 y cno en 0 y 30 campos vacios para completar longitud
				iatxRequestData.addBodyValue(CERO_STRING + STRING_SUC_BP)	;
				iatxRequestData.addBodyValue(StringUtils.leftPad("", NUMERO_ONCE, CERO_STRING));
				iatxRequestData.addBodyValue(CERO_STRING + STRING_SUC_BP);
				iatxRequestData.addBodyValue(StringUtils.leftPad("", NUMERO_TREINTA, " "));
			}else {
				CuentaRossiOutEntity cuentaRossiOutEntity = new CuentaRossiOutEntity();
				try {
					cuentaRossiOutEntity = plazoFijoBPrivDAO.getCuentaRossi(entity.getNroCuentaDebito());

					iatxRequestData.addBodyValue(CERO_STRING + cuentaRossiOutEntity.getSucursalRossi());
					iatxRequestData.addBodyValue(TRES_CEROS_STRING + cuentaRossiOutEntity.getCNO());
					iatxRequestData.addBodyValue(CERO_STRING + cuentaRossiOutEntity.getSucursalRadicacion());
					iatxRequestData.addBodyValue(StringUtils.left(cuentaRossiOutEntity.getDescripcion(), NUMERO_TREINTA));
				} catch (DAOException e) {
					LOGGER.error("Error obteniendo la cuenta Rossi", e);
				}	
			}

		} else {

			iatxRequestData.addBodyValue(StringUtils.leftPad("", NUMERO_CUATRO, " "));
			iatxRequestData.addBodyValue(StringUtils.leftPad("", NUMERO_ONCE, " "));
			iatxRequestData.addBodyValue(StringUtils.leftPad("", NUMERO_CUATRO, " "));
			iatxRequestData.addBodyValue(StringUtils.leftPad("", NUMERO_TREINTA, " "));
		}

		return iatxRequestData;
	}

	/**
	 * Generate request data CNSSIMPFCN.
	 *
	 * @param entity  the entity
	 * @param cliente the cliente
	 * @return el objeto para llamar al servicio IATX
	 */
	private IatxRequestData generateRequestDataCNSSIMPFCN(SimulacionPlazoFijoInEntity entity, Cliente cliente) {
		IatxRequestData iatxRequestData = new IatxRequestData(cliente);

		Boolean esBancaPrivada;

		if (CuentasBancaPrivadaUtil.isCuentaBP(entity.getSucursalCuentaDebito())) {
			esBancaPrivada = true;
		} else {
			esBancaPrivada = false;
		}


		iatxRequestData.addBodyValue(ENTIDAD_CUENTA_DEBITO);


		iatxRequestData.addBodyValue(!esBancaPrivada
				? ISBANStringUtils.formateadorConCerosIzq(entity.getSucursalCuentaDebito(), 4)
				: entity.getSucursalCuentaDebito());
		iatxRequestData.addBodyValue(
				!esBancaPrivada ? entity.getNroContratoAltair() : entity.getNumeroCuentaDebito());

		if (esBancaPrivada) {
			iatxRequestData.addBodyValue(entity.getDivisa());
			iatxRequestData.addBodyValue(ENTIDAD_CUENTA_DEBITO);
			iatxRequestData.addBodyValue(entity.getSucursalCuentaDebito());
			iatxRequestData.addBodyValue(entity.getNumeroCuentaDebito());
			iatxRequestData.addBodyValue(entity.getDivisa());
		} else {
			iatxRequestData.addBodyValue(entity.getDivisa());
			iatxRequestData.addBodyValue(ENTIDAD_CUENTA_DEBITO);
			iatxRequestData.addBodyValue(ISBANStringUtils.formateadorConCerosIzq(entity.getSucursalCuentaDebito(), 4));
			iatxRequestData.addBodyValue(entity.getNroContratoAltair());
			iatxRequestData.addBodyValue(entity.getDivisa());
		}

		iatxRequestData.addBodyValue(entity.getFechaAlta());
		iatxRequestData.addBodyValue(ISBANStringUtils.formateadorConCerosIzq(entity.getPlazo(), 5));
		iatxRequestData.addBodyValue(entity.getImportePlazoFijo());
		iatxRequestData.addBodyValue(entity.getDivisa());
		iatxRequestData.addBodyValue(entity.getTasa());


		iatxRequestData.addBodyValue(entity.getTipoPF());
		iatxRequestData.addBodyValue(entity.getCantidadDias());
		iatxRequestData.addBodyValue(entity.getTasaVariable());

		iatxRequestData.addBodyValue(CARACTER_SIGNO_MAS);


		iatxRequestData.addBodyValue(entity.getSpread());
		iatxRequestData.addBodyValue(entity.getSpreadSigno());

		iatxRequestData.addBodyValue(esBancaPrivada ? bancaPrivadaCanal : entity.getCanal());

		iatxRequestData.addBodyValue(entity.getSimulacionOriginal());

		iatxRequestData.addBodyValue(CARACTER_N);
		iatxRequestData.addBodyValue(CARACTER_N);
		

		return iatxRequestData;
	}

	/**
	 * Generate request data CNSPFPRECA.
	 *
	 * @param entity  the entity
	 * @param cliente the cliente
	 * @return el objeto para llamar al servicio IATX
	 */
	private IatxRequestData generateRequestDataCNSPFPRECA(ConsultaPrecancelablePlazoFijoInEntity entity,
			Cliente cliente) {
		IatxRequestData iatxRequestData = new IatxRequestData(cliente);
		iatxRequestData.addBodyValue(entity.getCuentaPlazo());
		iatxRequestData.addBodyValue(entity.getSecuencia().trim());
		return iatxRequestData;
	}

	/**
	 * Generate request data PRECASIMCN.
	 *
	 * @param entity  the entity
	 * @param cliente the cliente
	 * @return el objeto para llamar al servicio IATX
	 */
	private IatxRequestData generateRequestDataPRECASIMCN(PrecancelacionPlazoFijoInEntity entity, Cliente cliente) {
		IatxRequestData iatxRequestData = new IatxRequestData(cliente);

		Boolean esBancaPrivada;

		if (CuentasBancaPrivadaUtil.isCuentaBP(entity.getSucursalCuentaCredito())) {
			esBancaPrivada = true;
		} else {
			esBancaPrivada = false;
		}

		iatxRequestData.addBodyValue(entity.getOpcion());
		iatxRequestData.addBodyValue(entity.getCuentaPlazo());
		iatxRequestData.addBodyValue(entity.getSecuencia());

		if (esBancaPrivada) {
			iatxRequestData.addBodyValue(ISBANStringUtils.ESPACIO_STRING);
			iatxRequestData.addBodyValue(CARACTER_C);
		} else {
			iatxRequestData.addBodyValue(entity.getTipoCuentaCredito());
			iatxRequestData.addBodyValue(entity.getSucursalCuentaCredito());
			iatxRequestData.addBodyValue(entity.getNroCuentaCredito());
			iatxRequestData.addBodyValue(entity.getDivisaCuentaCredito());
		}

		return iatxRequestData;
	}

	/**
	 * remueve los campos vacios dentro de una lista.
	 *
	 * @return the predicate
	 */
	public Predicate predicadoImpuestosVacios() {
		return new Predicate() {
			@Override
			public boolean evaluate(Object object) {
				ConsultarCondicionesImpuestosEntity impuestos = (ConsultarCondicionesImpuestosEntity) object;
				return !impuestos.getMontoMonedaLocal().trim().isEmpty();
			}
		};
	}

	/**
	 * remueve los campos vacios dentro de una lista.
	 *
	 * @return the predicate
	 */
	public Predicate predicadoImpuestosVacios2() {
		return new Predicate() {
			@Override
			public boolean evaluate(Object object) {
				ImposicionImpuestosPlazoFijoEntity impuestos = (ImposicionImpuestosPlazoFijoEntity) object;
				return !impuestos.getTipoImpuesto().trim().isEmpty();
			}
		};
	}

	/**
	 * remueve los campos vacios dentro de una lista.
	 *
	 * @return the predicate
	 */
	public Predicate predicadoImpuestosVacios3() {
		return new Predicate() {
			@Override
			public boolean evaluate(Object object) {
				ConsultaPrecancelacionImpuestosAltaPlazoFijoEntity impuestos = (ConsultaPrecancelacionImpuestosAltaPlazoFijoEntity) object;
				return !impuestos.getAltaImpCod().trim().isEmpty();
			}
		};
	}

	/**
	 * remueve los campos vacios dentro de una lista.
	 *
	 * @return the predicate
	 */
	public Predicate predicadoImpuestosVacios4() {
		return new Predicate() {
			@Override
			public boolean evaluate(Object object) {
				ConsultaPrecancelacionImpuestosVencPlazoFijoEntity impuestos = (ConsultaPrecancelacionImpuestosVencPlazoFijoEntity) object;
				return !impuestos.getVencImpCod().trim().isEmpty();
			}
		};
	}

	// TODO completar manejo de errores para todos los servicios.
	/**
	 * Manejo de errores.
	 *
	 * @param errorCode the error code
	 * @throws DAOException the DAO exception
	 */

	private void manejarErroresIatxCNSSIMPFCN(int errorCode) throws DAOException {
		if (SUPERA_LIMITE_MAXIMO == errorCode) {
			LOGGER.debug("Supera el límite máximo permitido.");
			throw new ImporteMayorAlMaximoException();
		}
		if (SALDO_CUENTA_DEBITO_INSUFICIENTE == errorCode) {
			LOGGER.debug("El saldo de la cuenta debito es insuficiente para realizar la transaccion.");
			throw new SaldoInsuficienteException();
		}
		if (SALDO_CUENTA_DEBITO_INSUFICIENTE2 == errorCode) {
			LOGGER.debug("El saldo de la cuenta debito es insuficiente para realizar la transaccion.");
			throw new SaldoInsuficienteException();
		}

		if (CUENTA_INACTIVA_PARA_PLAZO_FIJO == errorCode) {
			throw new DAOException("CUENTA_INACTIVA");
		}

		if (CUENTA_SIMULACION_NO_SELECT == errorCode) {
			LOGGER.debug("Simulacion con cuenta no select.");
			throw new DAOException("CUENTA_SIMULACION_NO_SELECT");
		}
		if (CODIGO_NO_DISPONIBLE == errorCode) {
			LOGGER.debug("Servicio no disponible.");
			throw new DAOException("PLAZOS_FIJOS_NO_DISPONIBLE");
		}

		LOGGER.debug("Codigo de error no esperado de iatx en servicio CNSPFTIPO. ");
		throw new DAOException();
	}

	@Override
	public SimulacionPrecancelableUvaOutEntity solicitarPrecancelacion(PrecancelacionPlazoFijoInEntity entity,
			Cliente cliente, String tipoBanca) throws DAOException {
		String servicio = "PRECASIM__";
		String version = "100";
		SimulacionPrecancelableUvaOutEntity precancelacionPlazoFijoOutEntity = new SimulacionPrecancelableUvaOutEntity();
		try {
			IatxRequestData iatxRequestData = generarRequestDataPRECASIM(entity, cliente);
			if (BP.equalsIgnoreCase(tipoBanca) || BANCA_PRIVADA.equalsIgnoreCase(tipoBanca)) {
				iatxRequestData.setCanalTipo(bancaPrivadaCanal);
				iatxRequestData.setSubCanalTipo(bancaPrivadaSubcanal);
			}
			IatxRequest iatxRequest = new IatxRequest(servicio, version);
			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				precancelacionPlazoFijoOutEntity = processTrama(iatxResponse.getTrama(),
						SimulacionPrecancelableUvaOutEntity.class);
			} else {
				LOGGER.error("Codigo de retorno de IATX distinto de 0: " + errorCode);
				procesarErrorPRECASIM(errorCode, iatxResponse.getErrorMessage());
			}
		} catch (IatxException e) {
			handleIatxException(e);
		}
		return precancelacionPlazoFijoOutEntity;
	}

	private void procesarErrorPRECASIM(int errorCode, String mensaje) throws DAOException {
		switch (errorCode) {
		case ERROR_FECHA_MINIMA_PRECANCELACION_INVALIDA:
			throw new FechaMinimaPrecancelacionException(mensaje);
		case ERROR_PRECANCELACION_PREVIAMENTE_SOLICITADA:
			throw new PrecancelacionSolicitadaException(mensaje);
		case ERROR_SUPERA_FECHA_LIMITE_PRECANCELACION:
			throw new LimiteDePrecancelacionSuperadoException(mensaje);
		default:
			throw new DAOException();
		}
	}

	private IatxRequestData generarRequestDataPRECASIM(PrecancelacionPlazoFijoInEntity entity, Cliente cliente) {
		IatxRequestData iatxRequestData = new IatxRequestData(cliente);
		iatxRequestData.addBodyValue(entity.getOpcion());
		iatxRequestData.addBodyValue(entity.getCuentaPlazo());
		iatxRequestData.addBodyValue(entity.getSecuencia());
		iatxRequestData.addBodyValue(ISBANStringUtils.ESPACIO_STRING);
		iatxRequestData.addBodyValue(CARACTER_C);
		return iatxRequestData;
	}
	

	public RecomendacionResponseEntity consultaRecomendacion(PerfilInversorRequestEntity request) throws BusinessException {
        request.setFirma(generarFirma(dato));
        request.setDato(dato);
        RecomendacionResponseEntity response = new RecomendacionResponseEntity();
        try {
            WebClient service = crearLlamadaAWebService("/GetProductRecomendation/");
            response = service.post(request, RecomendacionResponseEntity.class);
            if (response == null || response.getCodigo() != 0 || response.getDatos() == null) {
                throw new DAOException();
            }
        } catch (Exception e) {
            LOGGER.error("Error llamando al servicio GetProductRecomendation", e);
            throw new BusinessException();
        }
		return response;
	}
	
	public RouterApiResponseEntity invocarRouterApi(RouterApiRequestEntity request) throws DAOException {

		RouterApiResponseEntity response = new RouterApiResponseEntity();
        try {
            WebClient service = crearLlamadaAWebServiceRouterApi("/service-processes");
            response = service.post(request, RouterApiResponseEntity.class);

        } catch (Exception e) {
            LOGGER.error("Error llamando al servicio Router-Api", e);
            throw new DAOException();
        }
		return response;
	}
	
    private WebClient crearLlamadaAWebService(String pathDeConsulta) throws DAOException {

        WebClient service = restWebClient.obtenerClienteRest(NOMBRE_WS);
        service.accept(MediaType.APPLICATION_JSON);
        service.type(MediaType.APPLICATION_JSON + ";charset=UTF-8").accept(MediaType.APPLICATION_JSON);
        service.path(pathDeConsulta);

        return service;
    }
    
    private WebClient crearLlamadaAWebServiceRouterApi(String pathDeConsulta) throws DAOException, BusinessException {
 
		WebClient service;
		try {
			Credential credencialesRouterApi = obtenerCredencial();
			String pass = credencialesRouterApi.getPassword();
			String correlationID = UUID.randomUUID().toString();
	        String logCorrelationId = "API-ROUTER CorrelationId :" + correlationID;
	        LOGGER.info(logCorrelationId);
			
			service = restWebClient.obtenerClienteRest(NOMBRE_WS_ROUTER_API);
			service.accept(MediaType.APPLICATION_JSON);
			service.type(MediaType.APPLICATION_JSON + ";charset=UTF-8").accept(MediaType.APPLICATION_JSON);
			service.header(HEADER_AUTHORIZATION, "Bearer " + pass);
			service.header(APIcConfigConstants.SAN_CORRELATIONID,correlationID);
			service.path(pathDeConsulta);
			
			
		} catch (DAOException e) {
            LOGGER.error("Error al crearLlamadaAWebServiceRouterApi", e);
            throw new DAOException();
		}

        return service;
    }
    
    private Credential obtenerCredencial() throws DAOException {
        Credential credential;
        try {
            credential = credentialSecurityFactory.getCredentialById(routerApiID);
        } catch (SQLException e) {
            throw new DAOException("Error al obtener credenciales");
        }
        return credential;
    }
    
    private Credential obtenerCriptoKeyRouterApi() throws DAOException {
        Credential credential;
        try {
            credential = credentialSecurityFactory.getCredentialById(routerApiCriptoKeyID);
        } catch (SQLException e) {
            throw new DAOException("Error al obtener credenciales");
        }
        return credential;
    }
    
	public String generarFirma(String dato) {
		String firma = "";
		try {
			firma = inversionWSHelper.getDatosFirmados(dato);
		} catch (Exception e) {
			LOGGER.error("Error creando firma");
		}
		return firma;
	}
	
}