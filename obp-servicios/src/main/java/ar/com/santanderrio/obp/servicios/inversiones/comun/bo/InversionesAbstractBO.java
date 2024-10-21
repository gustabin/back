/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.bo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import ar.com.santanderrio.base.http.web.util.NetworkUtil;
import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.generated.webservices.inversiones.DatosEvaluacionRiesgo;
import ar.com.santanderrio.obp.generated.webservices.inversiones.EvaluacionDeRiesgoResponse;
import ar.com.santanderrio.obp.generated.webservices.inversiones.entities.Disclaimer;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.bo.EstadisticaBO;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.factory.DatoItemMensaje;
import ar.com.santanderrio.obp.servicios.inversiones.TipoPapel;
import ar.com.santanderrio.obp.servicios.inversiones.comun.InversionWSHelper;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.cuentatitulo.dao.CuentaTituloDAO;
import ar.com.santanderrio.obp.servicios.inversiones.cuentatitulo.entity.CuentaBP;
import ar.com.santanderrio.obp.servicios.inversiones.cuentatitulo.entity.CuentaTituloInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentatitulo.entity.CuentaTituloOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.consultas.dao.ConsultaFondoDAO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentaTituloDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentasConsultaFondoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FondoResumidoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.InicioFondoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SimulacionInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ConsultaFondoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ConsultaTenenciaFCIInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ConsultaTenenciaFDCInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.CuentaOPEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DatosDetalleFondoRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DatosServiciosEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DatosTenenciaValuadaCarteraRequest;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DetalleFondoRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.Segmento;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.SimulacionFondoBancaPrivadaInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.SucursalCuentaEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.TenenciaValuadaCarteraRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.RescateFondoEspecie;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.BaseRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CabeceraOrdenesTitulosEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosObtenerCuentasTitulos;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosObtenerSaldoCuentasCustodia;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.EntityBaseTitulos;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerCuentasTitulos;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerSaldoCuentasCustodia;
import ar.com.santanderrio.obp.servicios.pagos.dao.IntervinientesDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;
import ar.com.santanderrio.obp.servicios.pagos.entities.IntervinientesEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.IntervinientesOutEntity;

/**
 * The Class InversionesBO.
 */
public abstract class InversionesAbstractBO {

	/** The canal titulos ordenes banca privada. */
	@Value("${INVERSIONES.TITULOS.ORDENES.CANAL.BANCAPRIVADA}")
	protected String canalOrdenesBPriv;

	/** The Subcanal titulos ordenes banca privada. */
	@Value("${INVERSIONES.TITULOS.ORDENES.SUBCANAL.BANCAPRIVADA}")
	protected String subCanalOrdenesBPriv;

	/** The canal titulos ordenes banca personal. */
	@Value("${INVERSIONES.TITULOS.ORDENES.CANAL.BANCAPERSONAL}")
	protected String canalOrdenesRTL;

	/** The Subcanal titulos ordenes banca personal. */
	@Value("${INVERSIONES.TITULOS.ORDENES.SUBCANAL.BANCAPERSONAL}")
	protected String subCanalOrdenesRTL;

	/** The Subcanal titulos ordenes banca personal. */
	@Value("${SUBCANALTIPO.CABECERA.BANCAPERSONAL}")
	protected String subCanalTipoCabeceraOrdenesRTL;

	/** The Subcanal titulos ordenes banca personal. */
	@Value("${SUBCANALTIPO.CABECERA.BANCAPRIVADA}")
	protected String subCanalTipoCabeceraOrdenesBPriv;

	/** The dato firmado tipo. */
	@Value("${INVERSIONES.LICITACIONES.DATOFIRMADO}")
	private String dato;

	/** The canal tipo. */
	@Value("${INVERSIONES.CANAL.BANCAPERSONAL}")
	private String canalRTL;

	/** The subcanal tipo. */
	@Value("${INVERSIONES.SUBCANAL.BANCAPERSONAL}")
	private String subcanalRTL;

	/** The segmento tipo. */
	@Value("${INVERSIONES.SEGMENTO.BANCAPERSONAL}")
	private String segmentoRTL;

	/** The Subcanal titulos ordenes banca personal. */
	@Value("${HORARIO.DESDE.OPERAR.FONDOS}")
	protected String horarioDesdeOperarFondos;

	/** The Subcanal titulos ordenes banca personal. */
	@Value("${HORARIO.HASTA.OPERAR.FONDOS}")
	protected String horarioHastaOperarFondos;

	/** The Constant LARGO_COD_FONDO. */
	private static final int LARGO_COD_FONDO = 3;

	/** The Constant MONEDA_PESO. */
	protected static final String MONEDA_PESO = "ARG";

	/** The Constant BPRIVRACF. */
	private static final String BPRIVRACF = "BPRIVRACF";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(InversionesAbstractBO.class);

	/** The Constant LONGITUD_CUENTA. */
	protected static final int LONGITUD_CUENTA = 9;

	/** The Constant PREFIJO_CUENTA_TITULO. */
	protected static final String PREFIJO_CUENTA_TITULO = "7";

	/** The Constant URL_REGLAMENTO_ESTATICA. */
	private static final String URL_REGLAMENTO_ESTATICA = "https://www.santanderrio.com.ar/RenderPDF/Render?name={0}&library=CONTENIDO-SR";

	/** The Constant SIN_REGLAMENTO. */
	private static final String SIN_REGLAMENTO = "x";

	/** The Constant FIN_FECHA. */
	private static final int FIN_FECHA = 8;

	/** The Constant LARGO_COD_SUCURSAL. */
	protected static final int LARGO_COD_SUCURSAL = 3;

	/** The Constant CERO_STRING. */
	private static final String CERO_STRING = "0";

	/** The Constant CANAL. */
	private static final String CANAL = "PLAZOFIJO.CANAL.";

	/** The Constant CANAL_BANCA_PRIVADA. */
	private static final String CANAL_BANCA_PRIVADA = "18";

	/** The Constant CODIGOS_SINCERAMIENTO. */
	private static final ArrayList<String> CODIGOS_SINCERAMIENTO = new ArrayList<String>(
			Arrays.asList("2011", "2016", "2017", "2018", "2019", "2021"));

	/** The Constant ATIT. */
	private static final String ATIT = "ATIT";

	private static final String ACAD = "ACAD";

	/** The Constant CANAL_VERSION. */
	private static final String CANAL_VERSION = "000";

	/** The Constant CANAL_TIPO. */
	private static final String CANAL_TIPO = "04";

	/** The Constant COD_CANAL. */
	protected static final String COD_CANAL = "OBP";

	/** The Constant CANAL_ID. */
	private static final String CANAL_ID = "HTML";

	/** The Constant USUARIO_TIPO. */
	private static final String USUARIO_TIPO = "03";

	/** The Constant PRODUCTO. */
	private static final String PRODUCTO = "LC";

	/** The Constant USUARIO_ATRIB. */
	private static final String USUARIO_ATRIB = "  ";

	/** The Constant TIPO_CLIENTE_ID. */
	private static final String TIPO_CLIENTE_ID = "N";

	/** The Constant TIPO_CLIENTE. */
	private static final String TIPO_CLIENTE = "I";

	/** The Constant SUBCANAL_TIPO. */
	private static final String SUBCANAL_TIPO = "99";

	/** The Constant SUBCANAL_ID. */
	private static final String SUBCANAL_ID = "0001";

	/** The Constant COMPRA_VENTA. */
	private static final String COMPRA_VENTA = "1";

	/** The Constant TIPO_HASH. */
	private static final String TIPO_HASH = "0";

	/** The Constant DATOS_FIRMADO. */
	private static final String DATOS_FIRMADO = "0";

	/** The date formatter. */
	private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHH:mm:ss");

	/** The date formatter para fecha baja interviniente. */
	private final SimpleDateFormat dateFormatterFechaBajaRel = new SimpleDateFormat("ddMMyyyy");

	/** The consulta fondo DAO. */
	@Autowired
	protected ConsultaFondoDAO consultaFondoDAO;

	/** The credential security. */
	@Autowired
	private CredentialSecurityFactory credentialSecurity;

	/** The intervinientes DAO. */
	@Autowired
	private IntervinientesDAO intervinientesDAO;

	/** The cuenta titulo DAO. */
	@Autowired
	private CuentaTituloDAO cuentaTituloDAO;

	/** The session parametros. */
	@Autowired
	private SesionParametros sessionParametros;

	/** The estadistica BO. */
	@Autowired
	private EstadisticaBO estadisticaBO;

	/** The inversion WS helper. */
	@Autowired
	private InversionWSHelper inversionWSHelper;

	/** The rescate Fondo Especie. */
	@Autowired
	private RescateFondoEspecie rescateFondoEspecie;

	/**
	 * Metodo a ser implementado por toda subclase para retornar el codigo de
	 * operacion .
	 *
	 * @return the tipo
	 */
	protected abstract String getTipo();

	/**
	 * Crea el objeto de entrada al DAO de suscripcion a partir del DTO recibido, en
	 * codifo de operacion es retornado por cada clase concreta que extienda de esta
	 * por parametro.
	 *
	 * @param requestDTO the request DTO
	 * @param cliente    the cliente
	 * @param credential the credential
	 * @return the simular suscripcion B priv in
	 * @throws BusinessException the business exception
	 */
	protected SimulacionFondoBancaPrivadaInEntity crearDAOin(SimulacionInDTO requestDTO, Cliente cliente, Credential credential)
			throws BusinessException {
		SimulacionFondoBancaPrivadaInEntity rta = new SimulacionFondoBancaPrivadaInEntity();

		rta.setCodigoFondo(requestDTO.getCodigoFondo());
		rta.setCodigoOperacion(getTipo());
		rta.setCuentaTitulo(PREFIJO_CUENTA_TITULO + llenarConCerosIzqOTruncar(
				requestDTO.getNroCuentaBancaPrivada().replaceAll("/", ""), LONGITUD_CUENTA));
		rta.setCapital(requestDTO.getImporte());
		rta.setUssRacf(credential.getUsuario());
		rta.setPassRacf(credential.getPassword());
		rta.setCliente(cliente);

		try {
			ConsultaFondoOutEntity rtaConsultaFondo = consultaFondoDAO
					.obtenerPorCodigo(StringUtils.right(requestDTO.getCodigoFondo(), LARGO_COD_FONDO));
			rta.setEspecie(rtaConsultaFondo.getEspecie());

			if (DivisaEnum.PESO.getCodigo().equals(rtaConsultaFondo.getMoneda())) {
				rta.setMoneda(MONEDA_PESO);
			} else {
				rta.setMoneda(DivisaEnum.DOLAR.getCodigo());
			}
		} catch (DAOException e) {
			LOGGER.error("Error recuperando especie del fondo", e);
			throw new BusinessException(e);
		}
		return rta;
	}

	/**
	 * Armar url reglamento.
	 *
	 * @param codigoFondo the codigo fondo
	 * @return the string
	 * @throws BusinessException the business exception
	 */
	protected String armarUrlReglamento(String codigoFondo) throws BusinessException {
		ConsultaFondoOutEntity respuestaConsulta;
		String urlRetorno = null;
		try {
			respuestaConsulta = consultaFondoDAO.obtenerPorCodigo(StringUtils.right(codigoFondo, LARGO_COD_FONDO));
			urlRetorno = respuestaConsulta.getLinkRegla();
			if (urlRetorno.equalsIgnoreCase(SIN_REGLAMENTO)) {
				urlRetorno = "";
			} else {
				urlRetorno = MessageFormat.format(URL_REGLAMENTO_ESTATICA, urlRetorno);
			}
			return urlRetorno;
		} catch (DAOException e) {
			LOGGER.error("Error consultando fondo para obtener URL del reglamento", e);
			throw new BusinessException();
		}
	}

	/**
	 * Obtiene las credenciales RACF para banca privada.
	 *
	 * @return the RACF credential
	 * @throws BusinessException the business exception
	 */
	protected Credential getRACFCredential() throws BusinessException {
		Credential credential = null;
		try {
			credential = credentialSecurity.getCredentialBySistema(BPRIVRACF);

		} catch (SQLException e) {
			LOGGER.error("Error llamando a simulacion de suscripcion", e);
			throw new BusinessException(e);
		}
		return credential;
	}

	/**
	 * Devuelve el fondoDTO correspondiente al codigo enviado Si el fondo no esta
	 * habilitado para suscribir lo devuelve igual.
	 *
	 * @param codigoFondo the codigo fondo
	 * @return the fondo resumido DTO
	 * @throws DAOException the DAO exception
	 */
	protected FondoResumidoDTO obtenerFondoPorCodigo(String codigoFondo) throws DAOException {
		FondoResumidoDTO fondoResumidoDTO = null;
		fondoResumidoDTO = entityToDTO(consultaFondoDAO.obtenerPorCodigo(codigoFondo));
		return fondoResumidoDTO;
	}

	/**
	 * Devuelve el fondoDTO correspondiente al codigo enviado solo si el fondo esta
	 * habilitado para transferencia. Si no esta habilitado retorna null
	 *
	 * @param codigoFondo the codigo fondo
	 * @return the fondo resumido DTO
	 * @throws DAOException the DAO exception
	 */
	protected FondoResumidoDTO obtenerFondoPorCodigoHabiliTR(String codigoFondo) throws DAOException {
		FondoResumidoDTO fondoResumidoDTO = null;
		ConsultaFondoOutEntity fondo = consultaFondoDAO.obtenerPorCodigo(codigoFondo);
		if ("1".equals(fondo.getHabilitarTransferencia())) {
			fondoResumidoDTO = entityToDTO(fondo);
		}
		return fondoResumidoDTO;
	}

	/**
	 * Devuelve el fondoDTO correspondiente al codigo enviado solo si el fondo esta
	 * habilitado para rescate. Si no esta habilitado retorna null
	 *
	 * @param codigoFondo the codigo fondo
	 * @return the fondo resumido DTO
	 * @throws DAOException the DAO exception
	 */
	protected FondoResumidoDTO obtenerFondoPorCodigoHabiliResc(String codigoFondo) throws DAOException {
		FondoResumidoDTO fondoResumidoDTO = null;
		ConsultaFondoOutEntity fondo = consultaFondoDAO.obtenerPorCodigo(codigoFondo);
		if ("1".equals(fondo.getHabilitarRescate())) {
			fondoResumidoDTO = entityToDTO(fondo);
		}
		return fondoResumidoDTO;
	}

	/**
	 * Entity to DTO.
	 *
	 * @param consultaFondoOutEntity the consulta fondo out entity
	 * @return the fondo resumido DTO
	 */
	private FondoResumidoDTO entityToDTO(ConsultaFondoOutEntity consultaFondoOutEntity) {
		FondoResumidoDTO fondoResumidoDTO = new FondoResumidoDTO();

		fondoResumidoDTO.setImporteMaximo(consultaFondoOutEntity.getLimiteMaximoRescate());
		fondoResumidoDTO.setImporteMinimo(consultaFondoOutEntity.getLimiteMinimoRescate());
		fondoResumidoDTO.setMoneda(DivisaEnum.fromCodigoString(consultaFondoOutEntity.getMoneda()).getSimbolo());
		fondoResumidoDTO.setNombreFondo(consultaFondoOutEntity.getNombreFondo());
		fondoResumidoDTO.setCodigoFondo(consultaFondoOutEntity.getCodigoFondo());
		fondoResumidoDTO.setGrupo(consultaFondoOutEntity.getAgrupadorSuscripcion());
		fondoResumidoDTO.setOrdenPorGrupo(consultaFondoOutEntity.getCodigoAgrupador());
		fondoResumidoDTO.setSubOrdenPorGrupo(consultaFondoOutEntity.getOrdenAgrupacion());
		fondoResumidoDTO.setIdMensajeGastos(consultaFondoOutEntity.getIdMensajeGastos());
		fondoResumidoDTO.setHabilitarSuscripcion(consultaFondoOutEntity.getHabilitarSuscripcion());
		fondoResumidoDTO.setHabilitarRescate(consultaFondoOutEntity.getHabilitarRescate());
		fondoResumidoDTO.setTipoBanca(consultaFondoOutEntity.getTipoBanca());
		fondoResumidoDTO.setPlazoEfectivo(consultaFondoOutEntity.getPlazoEfectivo());

		fondoResumidoDTO.setIdLegalesInformacion(consultaFondoOutEntity.getIdLegalesInformacion());
		fondoResumidoDTO.setIdLegales3(consultaFondoOutEntity.getIdLegales3());
		fondoResumidoDTO.setDescripcionAdicional(consultaFondoOutEntity.getDescripcionAdicional());
		fondoResumidoDTO.setHonorariosEntrada(consultaFondoOutEntity.getHonorariosEntrada());
		fondoResumidoDTO.setHonorariosSalida(consultaFondoOutEntity.getHonorariosSalida());
		fondoResumidoDTO.setCodigoAgrupador(consultaFondoOutEntity.getCodigoAgrupador());
		fondoResumidoDTO.setOrdenAgrupacion(consultaFondoOutEntity.getOrdenAgrupacion());
		fondoResumidoDTO.setExCiti(consultaFondoOutEntity.getExCiti());
		fondoResumidoDTO.setNombreFondo(consultaFondoOutEntity.getNombreFondo());
		return fondoResumidoDTO;
	}

	/**
	 * Llenar con ceros izq O truncar.
	 *
	 * @param s    the s
	 * @param size the size
	 * @return the string
	 */
	protected String llenarConCerosIzqOTruncar(String s, int size) {
		int l = s.length();
		if (l >= size) {
			return s.substring(l - size, l);
		}
		StringBuffer ceros = new StringBuffer();
		for (int n = 0; n < size - l; ++n) {
			ceros.append("0");
		}
		return ceros + s;
	}

	/**
	 * Recupera la lista de intervinientes de una cuenta.
	 *
	 * @param cuenta the cuenta
	 * @return the list
	 */
	public List<Interviniente> obtenerIntervinientes(Cuenta cuenta) {
		List<Interviniente> listaIntervinientes = new ArrayList<Interviniente>();
		try {
			IntervinientesOutEntity rta = intervinientesDAO.obtenerIntervinientes(cuenta);
			for (IntervinientesEntity intervinienteEntity : rta.getListaRepeticiones()) {
				if (intervinienteActivo(intervinienteEntity)) {
					Interviniente interviniente = new Interviniente();
					interviniente.setApellido(obtenerDatoEntity(intervinienteEntity.getApellido()));
					interviniente.setNombre(obtenerDatoEntity(intervinienteEntity.getNombre()));
					interviniente.setOrdenParticipacion(intervinienteEntity.getOrdenParticipacion());
					listaIntervinientes.add(interviniente);
				}

			}
		} catch (DAOException e) {
			LOGGER.error("Error recuperando datos de los intervinientes en la cuenta: ", cuenta, e);
		}
		return listaIntervinientes;
	}

	/**
	 * Limpiar nombre y apellido.
	 * 
	 * @param datp
	 * @return
	 */
	private String obtenerDatoEntity(String datoInterviniente) {
		return StringUtils.isBlank(datoInterviniente) ? " "
				: ISBANStringUtils.formateoStringPrimeraLetraMayuscula(datoInterviniente.trim());
	}

	/**
	 * Devuelve true o false segun si el interviniente esta activo o no
	 * respectivamente.
	 *
	 * @param intervinienteEntity the interviniente entity
	 * @return true, if successful
	 */
	private boolean intervinienteActivo(IntervinientesEntity intervinienteEntity) {
		try {
			Date fechaBajaRel = dateFormatterFechaBajaRel.parse(intervinienteEntity.getFechaBajaRel());
			return fechaBajaRel.after(new Date());
		} catch (ParseException e) {
			return true;
		}
	}

	/**
	 * Cargar intervinientes.
	 *
	 * @param cuentasCliente the cuentas cliente
	 * @return the list
	 */
	protected List<Cuenta> cargarIntervinientes(List<Cuenta> cuentasCliente) {
		for (Cuenta cuenta : cuentasCliente) {
			if (cuenta.getIntervinientes() == null) {
				List<Interviniente> listaIntervinientes = obtenerIntervinientes(cuenta);
				if (!listaIntervinientes.isEmpty()) {
					cuenta.setIntervinientes(listaIntervinientes);
				}
			}
		}
		return cuentasCliente;
	}

	/**
	 * Relaciona las cuentas titulo con su operativa correspondiente y devuelve una
	 * lista con dicha relacion.
	 *
	 * @param cliente the cliente
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	public List<CuentaTituloDTO> obtenerRelacionOperativaTitulo(Cliente cliente) throws BusinessException {
		CuentaTituloInEntity inEntity = new CuentaTituloInEntity();
		inEntity.setCliente(cliente);
		CuentaTituloOutEntity outEntityLoadAtits;
		try {
			outEntityLoadAtits = cuentaTituloDAO.obtenerCuentasTitulo(inEntity);
		} catch (DAOException ex) {
			LOGGER.error("Error al cargar las cuentas titulo. ", ex);
			throw new BusinessException();
		}

		List<CuentaTituloDTO> listaReturn = new ArrayList<CuentaTituloDTO>();

		for (CuentaBP relacionOperativaTitulo : outEntityLoadAtits.relacionesOperativaTitulo()) {
			CuentaTituloDTO cuentaTitulo = new CuentaTituloDTO();
			cuentaTitulo.setNroCuentaFormateado(relacionOperativaTitulo.getCuentaTit());
			cuentaTitulo.setCuentaOperativa(relacionOperativaTitulo.getCuentaOp());
			cuentaTitulo = obtenerNroCuentaOperativaFormateado(cuentaTitulo, cliente);
			if (cuentaTitulo != null) {
				listaReturn.add(cuentaTitulo);
			}
		}
		return listaReturn;
	}

	/**
	 * toma el nro de cuenta con el 700 delante, por ej 7003547236 y retorna el nro
	 * formateado de la forma 250-354880/2 tambien setea la sucursal de la cuenta
	 * titulo.
	 *
	 * @param cuentaTitulo the cuenta titulo
	 * @param cliente      the cliente
	 * @return the cuenta titulo DTO
	 */
	private CuentaTituloDTO obtenerNroCuentaOperativaFormateado(CuentaTituloDTO cuentaTitulo, Cliente cliente) {
		LOGGER.info("Buscando cuenta nro " + cuentaTitulo.getCuentaOperativa());
		Iterator<Cuenta> ite = cliente.getCuentasPrivadas().iterator();
		while (ite.hasNext()) {
			Cuenta c = ite.next();
			BigDecimal nro = new BigDecimal(c.getNroCuentaProducto());
//			String nroCuentaBPFormateado = "7" + llenarConCerosIzqOTruncar(nro.toString(), 9);
			String nroCuentaBPFormateado = Integer.parseInt(c.getProductoAltair()) + llenarConCerosIzqOTruncar(nro.toString(), 9);
			if (nroCuentaBPFormateado.equals(cuentaTitulo.getCuentaOperativa())) {
				cuentaTitulo.setCuentaOperativaSinFormatear(cuentaTitulo.getCuentaOperativa());
				cuentaTitulo.setCuentaOperativa(ISBANStringUtils.formatearNumeroCuenta(c.getNroCuentaProducto()));
				BigInteger sucursal = new BigInteger(c.getNroSucursal());
				cuentaTitulo.setSucursal(sucursal.toString());
				// guardo la cuenta en formato 7003547236 para mas adelante
				cuentaTitulo.setIntervinientes(c.getIntervinientes());
				return cuentaTitulo;
			}
		}
		return null;
	}

	/**
	 * Formatea las cuotapartes a 4 decimales siempre, por ejemplo 100000000.5
	 * -->100.000.000,5000
	 *
	 * @param cuotaPartes the cuota partes
	 * @return the string
	 */
	protected String formatearCuotapartes(String cuotaPartes) {

		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
		otherSymbols.setDecimalSeparator(',');
		otherSymbols.setGroupingSeparator('.');
		DecimalFormat format = new DecimalFormat("###,###,###.####", otherSymbols);
		format.setDecimalSeparatorAlwaysShown(true);
		format.setMinimumFractionDigits(4);

		BigDecimal decimal3 = new BigDecimal(cuotaPartes);
		return format.format(decimal3);
	}

	/**
	 * Formatea las cuotapartes a 5 decimales siempre, por ejemplo 100000000.5
	 * -->100.000.000,50000
	 *
	 * @param valorALaFecha the valor A la fecha
	 * @return the string
	 */
	protected String formatearValorALaFecha(String valorALaFecha) {

		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
		otherSymbols.setDecimalSeparator(',');
		otherSymbols.setGroupingSeparator('.');
		DecimalFormat format = new DecimalFormat("###,###,###.#####", otherSymbols);
		format.setDecimalSeparatorAlwaysShown(true);
		format.setMinimumFractionDigits(4);

		String valorALaFechaAux = valorALaFecha.substring(0, valorALaFecha.length() - 5);
		valorALaFecha = valorALaFecha.substring(valorALaFecha.length() - 5);
		valorALaFechaAux = valorALaFechaAux.concat(".").concat(valorALaFecha);
		BigDecimal decimal3 = new BigDecimal(valorALaFechaAux);
		return format.format(decimal3);
	}

	/**
	 * Carga atributos del entity de fondo a fondoDTO.
	 *
	 * @param consultaFondos the consulta fondos
	 * @return the fondo resumido DTO
	 */
	protected FondoResumidoDTO cargarFondoDTO(ConsultaFondoOutEntity consultaFondos) {
		FondoResumidoDTO fondoResumidoDTO = new FondoResumidoDTO();
		fondoResumidoDTO.setImporteMaximo(consultaFondos.getLimiteMaximoSuscripcion());
		fondoResumidoDTO.setImporteMinimo(consultaFondos.getLimiteMinimoSuscripcion());
		fondoResumidoDTO.setMoneda(DivisaEnum.fromCodigoString(consultaFondos.getMoneda()).getSimbolo());
		fondoResumidoDTO.setNombreFondo(consultaFondos.getNombreFondo());
		fondoResumidoDTO.setCodigoFondo(consultaFondos.getCodigoFondo());
		fondoResumidoDTO.setGrupo(consultaFondos.getAgrupadorSuscripcion());
		fondoResumidoDTO.setOrdenPorGrupo(consultaFondos.getCodigoAgrupador());
		fondoResumidoDTO.setSubOrdenPorGrupo(consultaFondos.getOrdenAgrupacion());
		fondoResumidoDTO.setIdMensajeGastos(consultaFondos.getIdMensajeGastos());
		fondoResumidoDTO.setHabilitarSuscripcion(consultaFondos.getHabilitarSuscripcion());
		fondoResumidoDTO.setTipoBanca(consultaFondos.getTipoBanca());
		fondoResumidoDTO.setHorario(consultaFondos.getHorario());
		fondoResumidoDTO.setDescripcionLarga(consultaFondos.getDescripcionLarga());
		fondoResumidoDTO.setCodigoAgrupador(consultaFondos.getCodigoAgrupador());
		fondoResumidoDTO.setOrdenAgrupacion(consultaFondos.getOrdenAgrupacion());
		fondoResumidoDTO.setExCiti(consultaFondos.getExCiti());
		fondoResumidoDTO.setFondoNuevo(rescateFondoEspecie.getFondosMap().containsKey(consultaFondos.getCodigoFondo()));
		if(consultaFondos.isRepatriacion()) {
			fondoResumidoDTO.setRepatriacion(true);
		}
		return fondoResumidoDTO;
	}

	/**
	 * Predicado obtener fondos habilitados.
	 *
	 * @return the predicate
	 */
	protected Predicate predicadoObtenerFondosHabilitados() {

		return new Predicate() {
			@Override
			public boolean evaluate(Object object) {
				ConsultaFondoOutEntity fondo = (ConsultaFondoOutEntity) object;
				return "1".equals(fondo.getHabilitarTransferencia())
						&& (fondo.getTipoBanca().equalsIgnoreCase(TipoBancaEnum.BANCA_PERSONAL.getCodigo())
								|| fondo.getTipoBanca().equalsIgnoreCase(TipoBancaEnum.AMBAS_BANCAS.getCodigo()));
			}
		};
	}

	/**
	 * Generer request entity.
	 *
	 * @param requestDTO the request DTO
	 * @param exCiti     the ex citi
	 * @return the consulta tenencia FCI in entity
	 */
	protected ConsultaTenenciaFCIInEntity genererRequestEntity(CuentasConsultaFondoDTO requestDTO, boolean exCiti) {
		// OBTENGO FONDOS SUSCRIPTOS
		ConsultaTenenciaFCIInEntity requestEntity = new ConsultaTenenciaFCIInEntity();
		String fechaYHora = dateFormatter.format(new Date());
		// prueba de llamar el servicio original con el papel para fdc - arroja error
		requestEntity.setTipoPapelEnum(exCiti ? TipoPapel.FDC : TipoPapel.FCI);
		requestEntity.setFechaPeriodo(fechaYHora.substring(0, FIN_FECHA));
		requestEntity.setHoraPeriodo(fechaYHora.substring(FIN_FECHA));

		List<SucursalCuentaEntity> sucursalCuentaList = new ArrayList<SucursalCuentaEntity>();
		for (CuentaTituloDTO cuentaTituloDTO : requestDTO.getCuentasTitulo()) {
			SucursalCuentaEntity sucursalCuenta = new SucursalCuentaEntity(cuentaTituloDTO.getSucursal(),
					cuentaTituloDTO.getNroCuenta());
			sucursalCuentaList.add(sucursalCuenta);
		}
		requestEntity.setSucursalCuentaList(sucursalCuentaList);
		return requestEntity;
	}

	/**
	 * Generer request entity.
	 *
	 * @param requestDTO the request DTO
	 * @return the consulta tenencia FDC in entity
	 */
	protected ConsultaTenenciaFDCInEntity generarRequestExcityEntity(CuentasConsultaFondoDTO requestDTO) {

		ConsultaTenenciaFDCInEntity requestEntity = new ConsultaTenenciaFDCInEntity();
		String fechaYHora = dateFormatter.format(new Date());

		requestEntity.setTipoPapelEnum(TipoPapel.FDC);
		requestEntity.setFechaPeriodo(fechaYHora.substring(0, FIN_FECHA));
		requestEntity.setHoraPeriodo(fechaYHora.substring(FIN_FECHA));

		List<SucursalCuentaEntity> sucursalCuentaList = new ArrayList<SucursalCuentaEntity>();
		for (CuentaTituloDTO cuentaTituloDTO : requestDTO.getCuentasTitulo()) {
			SucursalCuentaEntity sucursalCuenta = new SucursalCuentaEntity(cuentaTituloDTO.getSucursal(),
					cuentaTituloDTO.getNroCuenta());
			sucursalCuentaList.add(sucursalCuenta);
		}
		requestEntity.setSucursalCuentaList(sucursalCuentaList);
		return requestEntity;
	}

	/**
	 * Crea el objeto Datos servicio para el llamado al servicio PLtenenciavaluada.
	 *
	 * @param cliente the cliente
	 * @return the datos servicios entity
	 */
	protected DatosServiciosEntity createDatosServicio(Cliente cliente) {
		DatosServiciosEntity datosServicios = new DatosServiciosEntity();
		datosServicios.setUsuarioTipo(cliente.getTipoRacf());
		datosServicios.setUsuarioId(cliente.getUsuarioRacf());
		datosServicios.setUsuarioAtrib("AA"); // @TODO: poner el valor que
												// corresponda
		datosServicios.setUsuarioPwd(cliente.getPasswordRacf());
		datosServicios.setTipoPersona(cliente.getTipoPersona());
		datosServicios.setTipoId(cliente.getTipoDocumento());
		datosServicios.setIdCliente(cliente.getDni());
		datosServicios.setFechaNac(cliente.getFechaNacimiento());

		datosServicios.setIp(NetworkUtil.getHostAddress());
		datosServicios.setUsuario(System.getProperty("user.name"));
		return datosServicios;
	}

	/**
	 * Vincula las cuentas titulos con las operativas para un cliente de banca
	 * privada esto lo hace llamando al SP load_atits.
	 *
	 * @param cliente the cliente
	 * @throws BusinessException the business exception
	 */
	protected void vincularCuentasBriv(Cliente cliente) throws BusinessException {
		List<CuentaTituloDTO> relacionCuentas = new ArrayList<CuentaTituloDTO>();
		try {
			relacionCuentas = obtenerRelacionOperativaTitulo(cliente);
		} catch (BusinessException e) {
			LOGGER.error("Error llamando al SP load_atits", e);
			throw new BusinessException();
		}
		List<Cuenta> cuentasBPriv = cliente.getCuentasPrivadas();
		Iterator<Cuenta> iterator = cuentasBPriv.iterator();
		while (iterator.hasNext()) {
			Cuenta cuenta = iterator.next();
			if (!cuenta.getTipoCuenta().equals(Cuenta.TIPOCTA_TITULO)) {
//				String nroCuentaFormateado = "7" + llenarConCerosIzqOTruncar(cuenta.getNroCuentaProducto(), 9);
				String nroCuentaFormateado = Integer.parseInt(cuenta.getProductoAltair()) + llenarConCerosIzqOTruncar(cuenta.getNroCuentaProducto(), 9);
				Iterator<CuentaTituloDTO> iteratorBP = relacionCuentas.iterator();
				cuenta.setNroCuentaTit(buscarNroCtaTit(iteratorBP, nroCuentaFormateado));
			}
		}

	}

	/**
	 * Busca el numero de cuenta buscado dentro del iterator donde buscar, y
	 * devuelve el numero de cuenta formateado si es que lo encontro, sino null.
	 *
	 * @param iteratorDondeBuscar the iterator donde buscar
	 * @param nroCuentaBuscado    the nro cuenta buscado
	 * @return the string
	 */
	private String buscarNroCtaTit(Iterator<CuentaTituloDTO> iteratorDondeBuscar, String nroCuentaBuscado) {
		while (iteratorDondeBuscar.hasNext()) {
			CuentaTituloDTO cuentaBP = iteratorDondeBuscar.next();
			if (cuentaBP.getCuentaOperativaSinFormatear().equals(nroCuentaBuscado)) {
				return cuentaBP.getNroCuentaFormateado();
			}
		}
		return null;
	}

	/**
	 * Genero los objetos DTO de las listas de cuentas de banca privada o retail.
	 *
	 * @param cliente the cliente
	 * @return the inicio fondo DTO
	 */
	protected InicioFondoDTO generarCuentasDTO(Cliente cliente) {
		InicioFondoDTO rta = new InicioFondoDTO();
		List<CuentaTituloDTO> bancaPrivada = new ArrayList<CuentaTituloDTO>();
		List<CuentaTituloDTO> bancaPersonal = new ArrayList<CuentaTituloDTO>();
		for (Cuenta c : cliente.getCuentasPrivadas()) {
			if (!CODIGOS_SINCERAMIENTO.contains(c.getCodigoPaquete())
					&& c.getCodigoAplicacion().equalsIgnoreCase(ATIT)) {
				bancaPrivada.add(generarDTO(c));
			}
		}
		for (Cuenta c : cliente.getCuentasRetail()) {
			if (!CODIGOS_SINCERAMIENTO.contains(c.getCodigoPaquete())
					&& c.getCodigoAplicacion().equalsIgnoreCase(ATIT)) {
				bancaPersonal.add(generarDTO(c));
			}
		}
		rta.setCuentasBancaPersonal(bancaPersonal);
		rta.setCuentasBancaPrivada(bancaPrivada);
		return rta;
	}

	/**
	 * Genero los objetos DTO de las listas de cuentas de banca privada o retail.
	 *
	 * @param cliente the cliente
	 * @return the inicio fondo DTO
	 */
	protected InicioFondoDTO generarCuentasDTOPlazosFijos(Cliente cliente) {
		InicioFondoDTO rta = new InicioFondoDTO();
		List<CuentaTituloDTO> bancaPrivada = new ArrayList<CuentaTituloDTO>();
		List<CuentaTituloDTO> bancaPersonal = new ArrayList<CuentaTituloDTO>();
		for (Cuenta c : cliente.getCuentasPrivadas()) {
			if (!CODIGOS_SINCERAMIENTO.contains(c.getCodigoPaquete()) && (c.getCodigoAplicacion().equalsIgnoreCase(ATIT)
					|| esCuentaValidaGrillaPlazosFijos(c))) {
				bancaPrivada.add(generarDTO(c));
			}
		}
		for (Cuenta c : cliente.getCuentasRetail()) {
			if (!CODIGOS_SINCERAMIENTO.contains(c.getCodigoPaquete())
					&& c.getCodigoAplicacion().equalsIgnoreCase(ATIT)) {
				bancaPersonal.add(generarDTO(c));
			}
		}
		rta.setCuentasBancaPersonal(bancaPersonal);
		rta.setCuentasBancaPrivada(bancaPrivada);
		return rta;
	}

	/**
	 * Una cuenta es valida en banca privada para grilla de plazos fijos si
	 * corresponde a una cuentra repatriacion
	 * 
	 * @param c
	 * @return
	 */
	private boolean esCuentaValidaGrillaPlazosFijos(Cuenta c) {
		return c.getCodigoAplicacion().equalsIgnoreCase(ACAD) && c.getTipoCuenta().equalsIgnoreCase("04")
				&& c.getProductoAltair().equalsIgnoreCase("03") && c.getSubproductoAltair().equalsIgnoreCase("0005");
	}

	/**
	 * Crea el CuentaTituloDTO a partir de la cuenta pasada por parametro.
	 *
	 * @param cuenta the cuenta
	 * @return the cuenta titulo DTO
	 */
	private CuentaTituloDTO generarDTO(Cuenta cuenta) {
		CuentaTituloDTO cuentaTituloDTO = new CuentaTituloDTO();
		cuentaTituloDTO.setNroCuentaFormateado(ISBANStringUtils.formatearNumeroCuenta(cuenta.getNroCuentaProducto()));
		cuentaTituloDTO.setNroCuenta(cuenta.getNroCuentaProducto());
		cuentaTituloDTO.setSucursal(StringUtils.right(cuenta.getNroSucursal(), LARGO_COD_SUCURSAL));
		cuentaTituloDTO.setIntervinientes(cuenta.getIntervinientes());
		return cuentaTituloDTO;
	}

	/**
	 * Grabar estadistica enviando ademas el registro sesion.
	 *
	 * @param codigoEstadistica the codigo estadistica
	 * @param codigoError       the codigo error
	 * @param cliente           the cliente
	 */
	protected void grabarEstadistica(String codigoEstadistica, String codigoError, Cliente cliente) {
		Estadistica estadistica = new Estadistica();
		estadistica.setCodigoTransaccion(codigoEstadistica);
		estadistica.setCodigoError(codigoError);
		try {
			estadisticaBO.add(estadistica, sessionParametros.getRegistroSession(), cliente);
		} catch (BusinessException e) {
			LOGGER.error("Error al dar de alta la estadistica {}", estadistica, e);
		}
	}

	/**
	 * Crea el request para los servicios de tenencia
	 * ObtenerTenenciaValuadaDetalleFondoOnline y
	 * TenenciaValuadaCuentaProductoOnline.
	 *
	 * @param cuentasBancaPersonal the cuentas banca personal
	 * @param cliente              the cliente
	 * @param segmento             the segmento
	 * @return the detalle fondo request entity
	 */
	protected DetalleFondoRequestEntity createRequestServiceTenencias(List<CuentaTituloDTO> cuentasBancaPersonal,
			Cliente cliente, String segmento) {
		DetalleFondoRequestEntity request = new DetalleFondoRequestEntity();

		List<CuentaOPEntity> listaCuenta = new ArrayList<CuentaOPEntity>();

		if (Segmento.BANCA_PERSONAL.getCodigo().equals(segmento)) {
			for (CuentaTituloDTO cuentaBR : cuentasBancaPersonal) {
				CuentaOPEntity cuenta = new CuentaOPEntity();
				cuenta.setSucursal(cuentaBR.getSucursal());
				cuenta.setNroCuentaOP(cuentaBR.getNroCuenta());
				listaCuenta.add(cuenta);
			}
		} else {
			for (CuentaTituloDTO cuentaBP : cuentasBancaPersonal) {
				CuentaOPEntity cuenta = new CuentaOPEntity();
				cuenta.setSucursal(cuentaBP.getSucursal());
				cuenta.setNroCuentaOP(cuentaBP.getCuentaOperativaSinFormatear());
				listaCuenta.add(cuenta);
			}
		}

		DatosDetalleFondoRequestEntity datosRequest = createDatosRequest(cliente, segmento, listaCuenta);
		request.setDatos(datosRequest);
		return request;
	}

	/**
	 * Crea el objeto datos request para los llamados a servicios de tenencia.
	 *
	 * @param cliente     the cliente
	 * @param segmento    the segmento
	 * @param listaCuenta the lista cuenta
	 * @return the datos detalle fondo request entity
	 */
	protected DatosDetalleFondoRequestEntity createDatosRequest(Cliente cliente, String segmento,
			List<CuentaOPEntity> listaCuenta) {
		DatosDetalleFondoRequestEntity datosRequest = new DatosDetalleFondoRequestEntity();
		datosRequest.setListaCuentas(listaCuenta);
		datosRequest.setNup(cliente.getNup());
		datosRequest.setSegmento(segmento);
		datosRequest.setIp(NetworkUtil.getHostAddress());
		// datosRequest.setUsuario(System.getProperty("user.name"));
		datosRequest.setUsuario("system");
		DatosServiciosEntity datosServicios = createDatosServicio(cliente);
		datosRequest.setDatosServicios(datosServicios);
		return datosRequest;
	}

	/**
	 * Crea el request para el servicio de TenenciaValuadaCartera.
	 *
	 * @param cuentasBancaPersonal the cuentas banca personal
	 * @param cliente              the cliente
	 * @param segmento             the segmento
	 * @return the tenencia valuada cartera request entity
	 */
	protected TenenciaValuadaCarteraRequestEntity createRequestServiceTenenciasTC(
			List<CuentaTituloDTO> cuentasBancaPersonal, Cliente cliente, String segmento) {
		TenenciaValuadaCarteraRequestEntity request = new TenenciaValuadaCarteraRequestEntity();
		List<CuentaOPEntity> listaCuentaBR = new ArrayList<CuentaOPEntity>();
		List<CuentaOPEntity> listaCuentaBP = new ArrayList<CuentaOPEntity>();

		if (Segmento.BANCA_PERSONAL.getCodigo().equals(segmento)) {
			for (CuentaTituloDTO cuentaBR : cuentasBancaPersonal) {
				CuentaOPEntity cuenta = new CuentaOPEntity();
				cuenta.setSucursal(cuentaBR.getSucursal());
				cuenta.setNroCuentaOP(cuentaBR.getNroCuenta());
				listaCuentaBR.add(cuenta);
			}
		} else {
			for (CuentaTituloDTO cuentaBP : cuentasBancaPersonal) {
				CuentaOPEntity cuenta = new CuentaOPEntity();
				cuenta.setSucursal(cuentaBP.getSucursal());
				cuenta.setNroCuentaOP(cuentaBP.getCuentaOperativaSinFormatear());
				listaCuentaBP.add(cuenta);
			}
		}
		DatosTenenciaValuadaCarteraRequest datosRequest = new DatosTenenciaValuadaCarteraRequest();
		datosRequest.setListaCuentasBP(listaCuentaBP);
		datosRequest.setListaCuentasRTL(listaCuentaBR);
		datosRequest.setNup(cliente.getNup());
		datosRequest.setIp(NetworkUtil.getHostAddress());
		// datosRequest.setUsuario(System.getProperty("user.name"));
		datosRequest.setUsuario("system");
		datosRequest.setCodigoProducto("TOD");

		DatosServiciosEntity datosServicios = createDatosServicio(cliente);
		datosRequest.setDatosServicios(datosServicios);
		request.setDatos(datosRequest);
		return request;
	}

	/**
	 * Genera las CuentaTituloDTO banca personal para llamar al servicio de
	 * tenencias.
	 *
	 * @param cuentas the cuentas
	 * @return the list
	 */
	protected List<CuentaTituloDTO> generarCuentasRTLTenencias(List<CuentaTituloDTO> cuentas) {
		List<CuentaTituloDTO> listCuentasDTO = new ArrayList<CuentaTituloDTO>();
		for (CuentaTituloDTO cuenta : cuentas) {
			CuentaTituloDTO cuentaTitDTO = new CuentaTituloDTO();
			cuentaTitDTO.setCuentaOperativa(cuenta.getNroCuentaFormateado());
			if(cuenta.isRepatriacion()) {
				cuentaTitDTO.setCuentaOperativaRep(cuenta.getCuentaOperativaRep());
			}
			cuentaTitDTO.setNroCuenta(cuenta.getNroCuenta());
			cuentaTitDTO.setSucursal(cuenta.getSucursal());
			cuentaTitDTO.setCuentaOperativaSinFormatear(cuenta.getNroCuenta());
			cuentaTitDTO.setIntervinientes(cuenta.getIntervinientes());
			cuentaTitDTO.setRepatriacion(cuenta.isRepatriacion());
			listCuentasDTO.add(cuentaTitDTO);
		}
		return listCuentasDTO;
	}

	// retorna fechas dd/MM/yyyy.

	/**
	 * Convertir fecha tenencias.
	 *
	 * @param fechaInicial the fecha inicial
	 * @return the string
	 */
	protected String convertirFechaTenencias(String fechaInicial) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date fecha = formatter.parse(fechaInicial);
			SimpleDateFormat formatterOut = new SimpleDateFormat("dd/MM/yyyy");
			return formatterOut.format(fecha);

		} catch (ParseException e) {
			LOGGER.error("Error al convertir de String a Date ", e);
		}
		return null;
	}

	/**
	 * genera la firma para llamar a los servicios de P&L.
	 *
	 * @param dato the dato
	 * @return the string
	 * @throws BusinessException the business exception
	 */
	protected String generarFirma(String dato) throws BusinessException {
		String firma = "";
		try {
			firma = inversionWSHelper.getDatosFirmados(dato);
		} catch (Exception e) {
			LOGGER.error("Error creando firma");
			throw new BusinessException();
		}
		return firma;
	}

	/**
	 * Devuelve un ItemMensajeRespuesta con los disclaimers cargados y el tipo error
	 * recibido.
	 *
	 * @param evaluacionDeRiesgoResponse the evaluacion de riesgo response
	 * @param tipoError                  the tipo error
	 * @return the list
	 */
	protected List<ItemMensajeRespuesta> cargarMensajesPopup(EvaluacionDeRiesgoResponse evaluacionDeRiesgoResponse,
			TipoError tipoError) {
		List<ItemMensajeRespuesta> itemsMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
		if (evaluacionDeRiesgoResponse.getMensaje() != null
				&& evaluacionDeRiesgoResponse.getMensaje().getDisclaimer() != null
				&& evaluacionDeRiesgoResponse.getMensaje().getDisclaimer().getDisclaimers() != null) {
			for (Disclaimer disclaimer : evaluacionDeRiesgoResponse.getMensaje().getDisclaimer().getDisclaimers()) {
				ItemMensajeRespuesta mensajeRespuesta = new ItemMensajeRespuesta(disclaimer.getText());
				mensajeRespuesta.setExtra(disclaimer.getTitulo());
				mensajeRespuesta.setTipoError(tipoError.toString());
				itemsMensajeRespuesta.add(mensajeRespuesta);
			}
		} else {
			itemsMensajeRespuesta.add(new ItemMensajeRespuesta());
		}
		return itemsMensajeRespuesta;
	}

	/**
	 * Carga los datos para el request de evaluacion de riesgo.
	 *
	 * @param cliente the cliente
	 * @return the datos evaluacion riesgo
	 */
	public DatosEvaluacionRiesgo cargarDatosERI(Cliente cliente) {
		DatosEvaluacionRiesgo parametroDatos = new DatosEvaluacionRiesgo();

		parametroDatos.setCodCanal(COD_CANAL);
		parametroDatos.setCanalId(CANAL_ID);
		parametroDatos.setCanalTipo(CANAL_TIPO);
		parametroDatos.setCanalVersion(CANAL_VERSION);
		parametroDatos.setSubcanalId(SUBCANAL_ID);
		parametroDatos.setSubcanalTipo(SUBCANAL_TIPO);
		parametroDatos.setTipoCliente(TIPO_CLIENTE);
		parametroDatos.setTipoClienteId(TIPO_CLIENTE_ID);
		parametroDatos.setUsuarioAtrib(USUARIO_ATRIB);
		parametroDatos.setUsuarioTipo(USUARIO_TIPO);
		parametroDatos.setProducto(PRODUCTO);
		parametroDatos.setCompraVenta(COMPRA_VENTA);

		parametroDatos.setNup(cliente.getNup());
		parametroDatos.setUsuarioPwd(cliente.getPasswordRacf());
		parametroDatos.setUsuarioId(cliente.getUsuarioRacf());
		parametroDatos.setIdCliente(cliente.getDni());
		parametroDatos.setFechaNac(cliente.getFechaNacimiento());

		return parametroDatos;
	}

	/**
	 * convierte fecha de formato Ej. /Date(1395284400000)/ a dd/MM/yyyy
	 *
	 * @param fechaInicial the fecha inicial
	 * @return the string
	 */
	protected String convertirFechaTenenciasLicitaciones(String fechaInicial) {
		if (fechaInicial == null) {
			return null;
		}
		String json = fechaInicial;
		json = json.replace("/Date(", "");
		json = json.replace(")/", "");
		long time;
		try {
			time = Long.parseLong(json);
		} catch (NumberFormatException e) {
			return null;
		}
		return new SimpleDateFormat("dd/MM/yyyy").format(new Date(time));
	}

	/**
	 * convierte fecha de formato Ej. /Date(1395284400000)/ a dd/MM/yyyy
	 *
	 * @param fechaInicial the fecha inicial
	 * @return the string
	 */
	protected String convertirFechaHoraTenenciasLicitaciones(String fechaInicial) {
		if (fechaInicial == null) {
			return null;
		}
		String json = fechaInicial;
		json = json.replace("/Date(", "");
		json = json.replace(")/", "");
		long time;
		try {
			time = Long.parseLong(json);
		} catch (NumberFormatException e) {
			return null;
		}
		return new SimpleDateFormat("dd/MM/yyyy - HH:mm").format(new Date(time)) + " hs.";
	}

	/**
	 * Arma el request para invocar al metodo obtenerCuentasTitulos de
	 * LicitacionesDAO.
	 *
	 * @param cliente the cliente
	 * @return the obtener cuentas titulos
	 * @throws BusinessException the business exception
	 */
	protected ObtenerCuentasTitulos crearRequestNuevaLicitacion(Cliente cliente) throws BusinessException {
		ObtenerCuentasTitulos request = new ObtenerCuentasTitulos();
		cargarDatosGenericosRequest(request);
		request.setDatos(new DatosObtenerCuentasTitulos());
		request.getDatos().setCanal(canalRTL);
		request.getDatos().setIp(NetworkUtil.getHostAddress());
		request.getDatos().setNup(cliente.getNup());
		request.getDatos().setSubcanal(subcanalRTL);
		request.getDatos().setSegmento(segmentoRTL);
		request.getDatos().setUsuario(cliente.getUsuarioRacf());
		return request;
	}

	/**
	 * Arma el request para invocar al metodo obtenerCuentasTitulos de
	 * LicitacionesDAO.
	 *
	 * @param cliente the cliente
	 * @return the obtener cuentas titulos
	 * @throws BusinessException the business exception
	 */
	protected ObtenerCuentasTitulos crearRequestNuevaLicitacionBPriv(Cliente cliente) throws BusinessException {
		ObtenerCuentasTitulos request = new ObtenerCuentasTitulos();
		cargarDatosGenericosRequest(request);
		request.setDatos(new DatosObtenerCuentasTitulos());
		request.getDatos().setCanal("79");
		request.getDatos().setIp(NetworkUtil.getHostAddress());
		request.getDatos().setNup(cliente.getNup());
		request.getDatos().setSubcanal("00");
		request.getDatos().setSegmento("BP");
		request.getDatos().setUsuario(cliente.getUsuarioRacf());
		return request;
	}

	/**
	 * Arma el request para invocar al metodo obtenerCuentasTitulos de
	 * LicitacionesDAO.
	 *
	 * @param cliente the cliente
	 * @param moneda  the moneda
	 * @return the obtener cuentas titulos
	 * @throws BusinessException the business exception
	 */
	protected ObtenerSaldoCuentasCustodia crearRequestSaldoCuentasCustodia(Cliente cliente, String moneda)
			throws BusinessException {
		ObtenerSaldoCuentasCustodia request = new ObtenerSaldoCuentasCustodia();
		cargarDatosGenericosRequest(request);
		request.setDatos(new DatosObtenerSaldoCuentasCustodia());
		request.getDatos().setMoneda(moneda);
		request.getDatos().setCanal("79");
		request.getDatos().setIp(NetworkUtil.getHostAddress());
		request.getDatos().setNup(cliente.getNup());
		request.getDatos().setSubcanal("00");
		request.getDatos().setSegmento("BP");
		request.getDatos().setUsuario(cliente.getUsuarioRacf());
		return request;
	}

	/**
	 * Carga los datos genericos para todas las invocaciones a los WS.
	 *
	 * @param requestReturn the request return
	 * @throws BusinessException the business exception
	 */
	protected void cargarDatosGenericosRequest(BaseRequestEntity requestReturn) throws BusinessException {
		requestReturn.setFirma(generarFirma(dato));
		requestReturn.setDato(dato);
		requestReturn.setTipoHash(TIPO_HASH);
		requestReturn.setDatosFirmado(DATOS_FIRMADO);
		return;
	}

	/**
	 * Obtener canal.
	 *
	 * @param codigoCanal the codigo canal
	 * @param tipoBanca   the tipo banca
	 * @return the string
	 */
	protected String obtenerCanal(String codigoCanal, TipoBancaEnum tipoBanca) {
		String propertyBuscada;
		if (TipoBancaEnum.BANCA_PRIVADA.equals(tipoBanca)) {
			propertyBuscada = CANAL + CANAL_BANCA_PRIVADA;
		} else {

			if (!codigoCanal.equals(CERO_STRING)) {
				propertyBuscada = CANAL + StringUtils.leftPad(codigoCanal, 2, CERO_STRING);
			} else {
				propertyBuscada = CANAL + codigoCanal;
			}

		}
		String nombreCanal = ContextHolder.getContext().getEnvironment().getProperty(propertyBuscada);
		return nombreCanal;
	}

	/**
	 * Metodo para invertir el separador decimal de "," a "." para el uso de algunos
	 * servicios
	 *
	 * @param valor the valor
	 * @return the string
	 */
	protected String ajustarSeparadorDecimal(String valor) {
		valor = valor.replace(",", ".");
		return valor;
	}

	/**
	 * Setear canales request MW canales.
	 *
	 * @param request        the request
	 * @param esBancaPrivada the es banca privada
	 */
	protected void setearCanalesRequestMWCanales(RequestBaseFirmado request, Boolean esBancaPrivada) {
		request.setCanal(esBancaPrivada ? canalOrdenesBPriv : canalOrdenesRTL);
		request.setSubCanal(esBancaPrivada ? subCanalOrdenesBPriv : subCanalOrdenesRTL);
	}

	/**
	 * Setear datos request MW canales.
	 *
	 * @param datos          the datos
	 * @param cliente        the cliente
	 * @param esBancaPrivada the es banca privada
	 */
	protected void setearDatosRequestMWCanales(EntityBaseTitulos datos, Cliente cliente, Boolean esBancaPrivada) {
		datos.setCabecera(CabeceraOrdenesTitulosEntity.generarCabeceraRequest(cliente));
		datos.getCabecera().setCanalTipo(esBancaPrivada ? canalOrdenesBPriv : canalOrdenesRTL);
		datos.getCabecera()
				.setSubCanalTipo(esBancaPrivada ? subCanalTipoCabeceraOrdenesBPriv : subCanalTipoCabeceraOrdenesRTL);
		datos.setIp(NetworkUtil.getHostAddress());
		datos.setUsuario(cliente.getUsuarioRacf());
	}

	protected List<DatoItemMensaje> crearItemsFueraHorarioAgendarFondo() {
		List<DatoItemMensaje> list = new ArrayList<DatoItemMensaje>();
			list.add(new DatoItemMensaje(CodigoMensajeConstantes.CODIGO_SUSCRIPCION_RESCATE_FUERA_HORARIO_FONDO_MAPS,
					TipoError.FUERADEHORARIO, ""));
			list.add(new DatoItemMensaje(CodigoMensajeConstantes.CODIGO_SUSCRIPCION_RESCATE_FUERA_HORARIO_MAPS2,
					TipoError.FUERADEHORARIO, ""));
			list.add(new DatoItemMensaje(CodigoMensajeConstantes.CODIGO_SUSCRIPCION_FH_MAPS_REPATRAIACION,
					TipoError.FUERADEHORARIO, ""));
			list.add(new DatoItemMensaje(CodigoMensajeConstantes.CODIGO_MENSAJE_FUERA_HORARIO_FONDOS_MENU,
					TipoError.FUERADEHORARIO, ""));
			list.add(new DatoItemMensaje(CodigoMensajeConstantes.CODIGO_RESCATE_FUERA_HORARIO_MAPS,
					TipoError.FUERADEHORARIO, ""));
			list.add(new DatoItemMensaje(CodigoMensajeConstantes.CODIGO_MENSAJE_SUSCRIPCION_FONDO_FUERA_HORARIO,
				TipoError.FUERADEHORARIO, ""));
			list.add(new DatoItemMensaje(CodigoMensajeConstantes.CODIGO_MENSAJE_RESCATE_FONDO_FUERA_HORARIO,
				TipoError.FUERADEHORARIO, ""));
		return list;
	}

	protected boolean validarHorarioFondos() {
		DateTimeFormatter df = DateTimeFormat.forPattern("HH:mm");

		DateTime horarioBancarioInicio = df.parseLocalTime(horarioDesdeOperarFondos).toDateTimeToday();
		DateTime horarioBancarioFinal = df.parseLocalTime(horarioHastaOperarFondos).toDateTimeToday();

		Interval intervaloFueraHorario = new Interval(horarioBancarioInicio, horarioBancarioFinal);
		return intervaloFueraHorario.contains(new DateTime());
	}

	protected boolean isFondoFueraHorario(String horarioFondo) {
		final String regexHours = "^(\\d{2}:\\d{2}).*(\\d{2}:\\d{2}).*";
		final Pattern pattern = Pattern.compile(regexHours);
		final Matcher matcher = pattern.matcher(horarioFondo);

		DateTime horarioDeAperturaFondo = null;
		DateTime horarioDeCierreFondo = null;

		while(matcher.find()){
			DateTimeFormatter df = DateTimeFormat.forPattern("HH:mm");
			horarioDeAperturaFondo = df.parseLocalTime(matcher.group(1)).toDateTimeToday();
			horarioDeCierreFondo = df.parseLocalTime(matcher.group(2)).toDateTimeToday();
		}

		Interval intervaloFueraHorario = new Interval(horarioDeAperturaFondo, horarioDeCierreFondo);
		return intervaloFueraHorario.contains(new DateTime());
	}

	protected boolean esFinDeSemana() {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(new Date());
		return c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
	}
}
