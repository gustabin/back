package ar.com.santanderrio.obp.servicios.debinrecurrente.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.debinapi.dto.OperationMode;
import ar.com.santanderrio.obp.servicios.debinapi.dto.RecurrenceActionEnum;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dto.*;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.debinrecurrente.entity.ConsultaDebinOutEntity;
import ar.com.santanderrio.obp.servicios.debinrecurrente.entity.CreditoPorContracargoOutEntity;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.AccionRecurrenciaResponseView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.CrearRecurrenciaView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.DatosComprobanteDebinRecurrente;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.RecurrenciaView;
import ar.com.santanderrio.obp.servicios.debinws.dto.ConsultaDetalleDebinWSOutDTO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * The Class DebinRecurrenteDAOImpl.
 */
@Component
public class DebinRecurrenteDAOImpl extends IatxBaseDAO implements DebinRecurrenteDAO  {

	private static final String CONSULTA_DEBIN_VERSION = "110";

	private static final String CONSULTA_DEBIN = "CNSDEBIN__";

	private static final String CREDITO_POR_CONTRACARGO_VERSION = "100";

	private static final String CREDITO_POR_CONTRACARGO = "DEBCREDBN";

	/** The Constant MAX_PAGE_SIZE. */
	private static final int MAX_PAGE_SIZE = 8;
	
	/** The Constant PARAMETER_QUERY_RECURRENCES_BY_BUYER_PAGE_NUMBER. */
	private static final String PARAMETER_QUERY_RECURRENCES_BY_BUYER_PAGE_NUMBER = "pageNumber";

	/** The Constant PARAMETER_QUERY_RECURRENCES_BY_BUYER_PAGE_SIZE. */
	private static final String PARAMETER_QUERY_RECURRENCES_BY_BUYER_PAGE_SIZE = "pageSize";
	
	/** The Constant PARAMETER_RECURRENCE_ID. */
	private static final String PARAMETER_RECURRENCE_ID = "{recurrenceId}";

	/** The Constant PARAMETER_QUERY_SEARCH_BY_NAME. */
	private static final String PARAMETER_QUERY_SEARCH_BY_NAME = "name";

	/** The Constant PARAMETER_QUERY_RECURRENCES_BY_BUYER_CUIT. */
	private static final String PARAMETER_QUERY_RECURRENCES_BY_BUYER_CUIT = "buyerCuit";

	/** The Constant PARAMETER_QUERY_RECURRENCES_BY_BUYER_CBU. */
	private static final String PARAMETER_QUERY_RECURRENCES_BY_BUYER_CBU = "buyerCbu";
	
	/** The Constant PARAMETER_CUIT. */
	private static final String PARAMETER_CUIT = "{cuit}";
	
	/** The Constant PATH_RECURRENCES_BY_BUYER. */
	private static final String PATH_RECURRENCES_BY_BUYER = "/recurrences/recurrencesByBuyer";
	
	/** The Constant PATH_SELLERS_SEARCH_BY_NAME. */
	private static final String PATH_SELLERS_SEARCH_BY_NAME = "/sellers/searchByName";
	
	/** The Constant PATH_SELLER_PROVISIONS. */
	private static final String PATH_SELLER_PROVISIONS = "/sellers/" + PARAMETER_CUIT + "/provisions";

	/** The Constant PATH_RECURRENCES. */
	private static final String PATH_RECURRENCES = "/recurrences";

	/** The Constant PATH_RECURRECE_STATUS_SETTINGS. */
	private static final String PATH_RECURRECE_STATUS_SETTINGS = "/recurrences/" + PARAMETER_RECURRENCE_ID;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DebinRecurrenteDAOImpl.class);
	
    /** The Constant NOMBRE_WS. */
    private static final String NOMBRE_WS = "DEBINREC";
    
	/** The logo cabecera. */
	@Value("classpath:/report/comprobantes/cabecera_comprobante.png")
	private Resource logoCabecera;
	
	/** The logo pie. */
	@Value("classpath:/report/comprobantes/cierre de comprobante.png")
	private Resource logoPie;
	
	/** The archivo jasper debin recurrente. */
	@Value("classpath:/report/debinRecurrente/comprobanteDebinRecurrente.jasper")
	private Resource archivoJasperDebinRecurrente;

	private static final int OK_CODIGO_RETORNO = 0;

	private static final Object COD_OP_PRI = "PRI";

	private static final Object COD_OP_ITB = "ITB";

	private static final String S_7735 = "7735";

	private static final String S_7758 = "7758";
	
	public static final String CLASE_DEBIN = "15";

    /** The sesion cliente. */
    @Autowired
    protected SesionParametros sesionParametros;

    /** The rest web client. */
    @Autowired
    private RestWebClient restWebClient;

	/** The respuesta factory. */
	@Autowired
	RespuestaFactory respuestaFactory;

	@Autowired
	private IatxComm iatxComm;

	@Autowired
	private CredentialSecurityFactory credentialSecurityFactory;

	@Value("${DEBINAPI.SEC_ID}")
	private String secId;

	@Autowired
	SesionCliente sesionCliente;

	/**
	 * Sets the property.
	 */
	@PostConstruct
	public void setProperty() {
		System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
	}
	
	/**
	 * Obtener empresas.
	 *
	 * @param empresa the empresa
	 * @return the list
	 * @throws Exception the exception
	 */
	@Override
	public List<SellerWithCategoryDTO> obtenerEmpresas(String empresa) throws Exception{
		return callServiceEmpresas(empresa);
	}
	
	/**
	 * Obtener servicios.
	 *
	 * @param cuit the cuit
	 * @return the seller with provision DTO
	 * @throws Exception the exception
	 */
	@Override
	public SellerWithProvisionDTO obtenerServicios(String cuit) throws Exception{
		return callServiceServicios(cuit);
	}
	
	/**
	 * Crear recurrencia.
	 *
	 * @param recurrencia the recurrencia
	 * @return the recurrence DTO
	 * @throws Exception the exception
	 */
	@Override
	public RecurrenceDTO crearRecurrencia(CrearRecurrenciaView recurrencia) throws Exception {
		RecurrenceCreationDTO recurrenceCreationDTO = buildRecurrenceCreationDTO(recurrencia);
		return callRecurrenceService(recurrenceCreationDTO);
	}

	/**
	 * Aplicar accion a recurrencia.
	 *
	 * @param recurrencia the recurrencia
	 * @return the recurrence DTO
	 * @throws Exception the exception
	 */

	@Override
	public AccionRecurrenciaResponseView applyActionToRecurrence(RecurrenciaView recurrencia, RecurrenceActionEnum action,
																	String successfulMessage) throws DAOException {

		RecurrenceUpdateDTO recurrenceUpdateDTO = buildRecurrenceUpdateDTO(recurrencia, action);
		RecurrenceDTO callModifyRecurrenceService = callModifyRecurrenceService(recurrencia.getId(), recurrenceUpdateDTO);
		if(callModifyRecurrenceService != null) {
			return new AccionRecurrenciaResponseView(successfulMessage);
		} else {
			throw new DAOException("No se pudo parsear correctamente el response");
		}

	}

	public RecurrenceUpdateDTO buildRecurrenceUpdateDTO(RecurrenciaView recurrencia, RecurrenceActionEnum action){

		RecurrenceUpdateDTO recurrenceUpdateDTO = new RecurrenceUpdateDTO();
		recurrenceUpdateDTO.setAuthorCuit(sesionCliente.getCliente().getNumeroCUILCUIT().replaceAll("-", StringUtils.EMPTY));
		recurrenceUpdateDTO.setRecurrenceAction(action.toString());
		recurrenceUpdateDTO.setOperationMode(OperationMode.COMPRADOR);

		return recurrenceUpdateDTO;
	}

	@Override 
	public ConsultaDebinOutEntity consultaDebin(Cliente cliente, String idDebin) throws DAOException {
		
		try {
			IatxRequest request = buildConsultaDebinIatxRequest(cliente, idDebin);
			IatxResponse iatxResponse = null;
			iatxResponse = iatxComm.exec(request);
			if (iatxResponse.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
				return buildDatosDebin(iatxResponse);
			} else {
				throw new DAOException("Error " + iatxResponse.getErrorCode());
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException("Error inesperado");
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException("Error inesperado");
		}
	}
	
	@Override
	public CreditoPorContracargoOutEntity creditoPorContracargo(Cliente cliente, String canal, String idContracargo) throws DAOException {
		try {
			IatxRequest request = buildCreditoPorContracargoAtxRequest(cliente, canal, idContracargo);
			IatxResponse iatxResponse = null;
			iatxResponse = iatxComm.exec(request);
			if (iatxResponse.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
				return buildDatosContracargo(iatxResponse);
			} else {
				throw new DAOException("Error " + iatxResponse.getErrorCode());
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException("Error inesperado");
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException("Error inesperado");
		}
	}

	private CreditoPorContracargoOutEntity buildDatosContracargo(IatxResponse iatxResponse) {
		CreditoPorContracargoOutEntity debinEntity = new CreditoPorContracargoOutEntity();
		
		int errorCode = iatxResponse.getErrorCode();
		if (OK_CODIGO_RETORNO == errorCode) {
			debinEntity = processTrama(iatxResponse.getTrama(),
					CreditoPorContracargoOutEntity.class);
		}
		return debinEntity;
	}

	private ConsultaDebinOutEntity buildDatosDebin(IatxResponse iatxResponse){
		
		ConsultaDebinOutEntity debinEntity = new ConsultaDebinOutEntity();
		
		int errorCode = iatxResponse.getErrorCode();
		if (OK_CODIGO_RETORNO == errorCode) {
			debinEntity = processTrama(iatxResponse.getTrama(),
					ConsultaDebinOutEntity.class);
		}
		return debinEntity;
	}
	
	
	/**
	 * Si el servicio API respode OK, devuelvo la entity. Si el cliente no tiene recurrencias dicha lista es vacia.
	 * 
	 * Ante un 424 del servicio -> 
	 * 				- Si la causa es 503 de Coelsa: exception, ya que puede que a su vez coelsa este caido o tenga problemas. No puedo continuar. Logueo problema puntual del servicio.
	 * 				- Si es otro caso, puede que no este bien un dato de input. Devolvemos null, pero no es bloqueante. Es Warning.
	 * Si es cualquier otro error -> exception. Puede darse 404, 503, etc. Propios de la API de recurrencia y no de Coelsa. Todos ellos son bloqueantes y no pueden continuar. 
	 *
	 * @param cbu the cbu
	 * @param formatterdCuit the formatterd cuit
	 * @param numeroPagina the numero pagina
	 * @return the buyer recurrence list request DTO
	 * @throws DAOException the DAO exception
	 */
	private BuyerRecurrenceListRequestDTO callRecurrencesByBuyer(String cbu, String formatterdCuit, String numeroPagina) throws DAOException {
		
		String cuit = StringUtils.remove(formatterdCuit, "-");
		WebClient client = restWebClient.obtenerClienteRest(NOMBRE_WS);
		Response response = client.type(MediaType.APPLICATION_JSON + ";charset=UTF-8")
		.accept(MediaType.APPLICATION_JSON)
		.header(HttpHeaders.AUTHORIZATION, getBasicAuthHeader())
		.path(PATH_RECURRENCES_BY_BUYER)
		.query(PARAMETER_QUERY_RECURRENCES_BY_BUYER_CUIT, cuit)
		.query(PARAMETER_QUERY_RECURRENCES_BY_BUYER_CBU, cbu)
		.query(PARAMETER_QUERY_RECURRENCES_BY_BUYER_PAGE_SIZE, MAX_PAGE_SIZE)
		.query(PARAMETER_QUERY_RECURRENCES_BY_BUYER_PAGE_NUMBER, numeroPagina)
		.get();
		
		int statusCode = response.getStatus();
		if(statusCode == HttpStatus.SC_OK) {
			LOGGER.info("Respuesta satisfactoria de la llamada DEBIN API {}", PATH_RECURRENCES_BY_BUYER);
			return response.readEntity(BuyerRecurrenceListRequestDTO.class);
		} else if (statusCode == HttpStatus.SC_FAILED_DEPENDENCY) {
			ProviderErrorDTO errorDTO = response.readEntity(ProviderErrorDTO.class);
			if(errorDTO.getRootCode() == HttpStatus.SC_SERVICE_UNAVAILABLE) {
				LOGGER.error("Error en el servicio {} error:{}", PATH_RECURRENCES_BY_BUYER + " Coelsa no disponible.");
				throw new DAOException("Error en el servicio DEBIN API " + PATH_RECURRENCES_BY_BUYER);
			} else {
				return null;
			}
		} else {
			LOGGER.error("Error en el servicio {}. StatusCode: {}", PATH_RECURRENCES_BY_BUYER, statusCode);
			throw new DAOException("Error en el servicio DEBIN API " + PATH_RECURRENCES_BY_BUYER + " StatusCode: " + statusCode);
		}
	}

	/**
	 * Call modify recurrence service.
	 *
	 * @param recurrenceId the recurrence id
	 * @param recurrenceUpdateDTO the recurrencia
	 * @return the recurrence DTO
	 * @throws DAOException the DAO exception
	 */
	private RecurrenceDTO callModifyRecurrenceService(String recurrenceId, RecurrenceUpdateDTO recurrenceUpdateDTO) throws DAOException {
		WebClient client = restWebClient.obtenerClienteRest(NOMBRE_WS);
		String path = PATH_RECURRECE_STATUS_SETTINGS.replace(PARAMETER_RECURRENCE_ID, recurrenceId);
		Response response = client.type(MediaType.APPLICATION_JSON + ";charset=UTF-8")
				.accept(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, getBasicAuthHeader())
				.path(path)
				.put(recurrenceUpdateDTO);

		int statusCode = response.getStatus();
		if(statusCode == HttpStatus.SC_OK) {
			LOGGER.info("Respuesta satisfactoria de la llamada API DEBIN {}", path);
			return response.readEntity(RecurrenceDTO.class);
		} else if( statusCode == HttpStatus.SC_FAILED_DEPENDENCY) {
			ProviderErrorDTO errorDTO = response.readEntity(ProviderErrorDTO.class);
			ErrorDTO coelsaError = errorDTO.getRootDetail().getErrors().get(0);

			LOGGER.error("Error en el servicio de debin api {} - code {} - {}", PATH_RECURRENCES, coelsaError.getCode(), coelsaError.getMessage());
			throw new DAOException("Error en el servicio DEBIN API " + PATH_RECURRENCES + "Error coelsa: " +
					coelsaError.getCode() + " - " + coelsaError.getMessage());

		} else if(statusCode == HttpStatus.SC_NOT_FOUND) {
			LOGGER.error("No se ha encontrado recurrencia. ID: {}",recurrenceId);
			throw new DAOException("Error en el servicio DEBIN API " + path);
		}
		else {
			LOGGER.error("Error en el servicio {}", path);
			throw new DAOException("Error en el servicio DEBIN API " + path);
		}
	}

	/**
	 * Call recurrence service.
	 *
	 * @param recurrencia the recurrencia
	 * @return the recurrence DTO
	 * @throws DAOException the DAO exception
	 */
	private RecurrenceDTO callRecurrenceService(RecurrenceCreationDTO recurrencia) throws DAOException {
		WebClient client = restWebClient.obtenerClienteRest(NOMBRE_WS);
		Response post = client.type(MediaType.APPLICATION_JSON + ";charset=UTF-8")
				.accept(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, getBasicAuthHeader())
				.path(PATH_RECURRENCES)
				.post(recurrencia);
		
		int statusCode = post.getStatusInfo().getStatusCode();
		if(statusCode == HttpStatus.SC_OK) {
			LOGGER.info("Respuesta satisfactoria de la llamada API DEBIN {}", PATH_RECURRENCES);
			return post.readEntity(RecurrenceDTO.class);
		} else if( statusCode == HttpStatus.SC_FAILED_DEPENDENCY) {
			ProviderErrorDTO errorDTO = post.readEntity(ProviderErrorDTO.class);
			ErrorDTO coelsaError = errorDTO.getRootDetail().getErrors().get(0);
			if(coelsaError.getCode().equals("CO-5013")) {
				return null;
			} else {
				LOGGER.error("Error en el servicio de debin api {} - code {} - {}", PATH_RECURRENCES, coelsaError.getCode(), coelsaError.getMessage());
				throw new DAOException("Error en el servicio DEBIN API " + PATH_RECURRENCES + "Error coelsa: " + 
				coelsaError.getCode() + " - " + coelsaError.getMessage());
			}
		} else {
			LOGGER.error("Error en el servicio {}", PATH_RECURRENCES);
			throw new DAOException("Error en el servicio DEBIN API " + PATH_RECURRENCES);
		}
	}

	/**
	 * Call service empresas.
	 *
	 * @param empresa the empresa
	 * @return the list
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	private List<SellerWithCategoryDTO> callServiceEmpresas(String empresa) throws Exception {
		WebClient client = restWebClient.obtenerClienteRest(NOMBRE_WS);
		List<SellerWithCategoryDTO> response = (List<SellerWithCategoryDTO>) client.type(MediaType.APPLICATION_JSON + ";charset=UTF-8")
		.accept(MediaType.APPLICATION_JSON)
		.header(HttpHeaders.AUTHORIZATION, getBasicAuthHeader())
		.path(PATH_SELLERS_SEARCH_BY_NAME)
		.query(PARAMETER_QUERY_SEARCH_BY_NAME, empresa)
		.getCollection(SellerWithCategoryDTO.class);
		
		if(client.getResponse().getStatus() == HttpStatus.SC_OK) {
			LOGGER.info("Respuesta satisfactoria de la llamada DEBIN API {}", PARAMETER_QUERY_SEARCH_BY_NAME);
			return response;
		} else {
			LOGGER.error("Error en el servicio {}", PATH_SELLERS_SEARCH_BY_NAME);
			throw new DAOException("Error en el servicio DEBIN API " + PATH_SELLERS_SEARCH_BY_NAME);
		}
	}
	
	/**
	 * Call service servicios.
	 *
	 * @param cuit the cuit
	 * @return the seller with provision DTO
	 * @throws DAOException the DAO exception
	 */
	private SellerWithProvisionDTO callServiceServicios(String cuit) throws DAOException {
		
		System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
		restWebClient.obtenerClienteRest(NOMBRE_WS);
		
		String path = PATH_SELLER_PROVISIONS.replace(PARAMETER_CUIT, cuit);
		WebClient client = restWebClient.obtenerClienteRest(NOMBRE_WS);
		SellerWithProvisionDTO response = client.type(MediaType.APPLICATION_JSON + ";charset=UTF-8")
		.accept(MediaType.APPLICATION_JSON)
		.header(HttpHeaders.AUTHORIZATION, getBasicAuthHeader())
		.path(path)
		.get(SellerWithProvisionDTO.class);
		
		int status = client.getResponse().getStatus();
		if(status == HttpStatus.SC_OK) {
			return response;
		} else if(status == HttpStatus.SC_NOT_FOUND) {
			LOGGER.info("No se ha encontrados datos para cuit: {}", cuit);
			throw new DAOException("Error en el servicio DEBIN API " + path + " - No se ha encontrados datos para cuit: " + cuit);
		} else {
			LOGGER.error("Error en el servicio DEBIN API {}", path);
			throw new DAOException("Error en el servicio DEBIN API" + path);
		}
	}	
	
	/**
	 * Builds the recurrence creation DTO.
	 *
	 * @param recurrencia the recurrencia
	 * @return the recurrence creation DTO
	 */
	private RecurrenceCreationDTO buildRecurrenceCreationDTO(CrearRecurrenciaView recurrencia) {
		RecurrenceCreationDTO dto = new RecurrenceCreationDTO();
		dto.setAuthorCuit(recurrencia.getCuitComprador());
		RecurrenceBuyerDTO buyer = new RecurrenceBuyerDTO();
		RecurrenceDebinDTO debin = new RecurrenceDebinDTO();
		RecurrenceSellerDTO seller = new RecurrenceSellerDTO();
		buyer.setCbu(recurrencia.getCbuComprador());
		buyer.setCuit(recurrencia.getCuitComprador());
		seller.setCuit(recurrencia.getCuitVendedor());
		debin.setConcept(recurrencia.getConcepto());
		debin.setCurrency(recurrencia.getMoneda());
		debin.setDetail(recurrencia.getDetalle());
		debin.setProvision(recurrencia.getProvision());
		debin.setReference(recurrencia.getReferencia());
		dto.setBuyer(buyer);
		dto.setDebin(debin);
		dto.setSeller(seller);
		return dto;
	}

	private IatxRequest buildConsultaDebinIatxRequest(Cliente cliente, String idDebin ) {
		IatxRequest requestIatx = new IatxRequest(CONSULTA_DEBIN, CONSULTA_DEBIN_VERSION);
		IatxRequestData iatxRequestData = new IatxRequestData(cliente);

		// FUNCION N2
		iatxRequestData.addBodyValue("01");
		
		// ID-DEBIN A22
		iatxRequestData.addBodyValue(StringUtils.leftPad(idDebin,22));
		
		// TPO-DEBIN A1
		iatxRequestData.addBodyValue("D");
		
		// CUIT N11
		iatxRequestData.addBodyValue(ISBANStringUtils.repeat("", 11));

		// FECHA-NEGOCIO A26
		iatxRequestData.addBodyValue(ISBANStringUtils.repeat("", 26));
		
		// HORA-NEGOCIO A26
		iatxRequestData.addBodyValue(ISBANStringUtils.repeat("", 26));
		
		// FECHA-OPERACIÓN A26
		iatxRequestData.addBodyValue(ISBANStringUtils.repeat("", 26));
		
		// HORA-OPERACION A26
		iatxRequestData.addBodyValue(ISBANStringUtils.repeat("", 26));
		
		// CLASE-DEBIN A2
		iatxRequestData.addBodyValue("02");
		
		requestIatx.setData(iatxRequestData);
		
		return requestIatx;
	}

	private IatxRequest buildCreditoPorContracargoAtxRequest(Cliente cliente, String canal, String idContracargo) {
		IatxRequest requestIatx = new IatxRequest(CREDITO_POR_CONTRACARGO, CREDITO_POR_CONTRACARGO_VERSION);
		IatxRequestData iatxRequestData = new IatxRequestData(cliente);

		ConsultaDetalleDebinWSOutDTO detalle = sesionParametros.getDetalleDebin();

		// TIPO_CTA_COMPRADOR N2
		iatxRequestData.addBodyValue(detalle.getComprador().getTipo());

		// Sucursal_Comprador N3
		iatxRequestData.addBodyValue(detalle.getComprador().getSucursal());

		// Numero_cuenta_Comprador N7
		iatxRequestData.addBodyValue(StringUtils.leftPad(detalle.getComprador().getNumero(), 7, "0"));

		// Importe N14(12,2)
		iatxRequestData.addBodyValue(detalle.getImporteSolicitado());

		// Fecha_Hora_Operación A26
//		iatxRequestData.addBodyValue(detalle.getFechaSolicitud());

		// Fecha_Hora_Negocio_Operacion A26
		iatxRequestData.addBodyValue(detalle.getFechaComprobante());

		// Divisa A3
		iatxRequestData.addBodyValue(detalle.getMoneda());

		// ID_Debin A22
		iatxRequestData.addBodyValue(StringUtils.leftPad(detalle.getIdDebin(), 22));

		// Tipo_Debin A1
		iatxRequestData.addBodyValue("C");

		// CBU_Debito N22
		iatxRequestData.addBodyValue(detalle.getVendedor().getCbu());

		// CUIT_Debito N11
		iatxRequestData.addBodyValue(detalle.getVendedor().getCuit());

		// Nombre_Debito A100
		iatxRequestData.addBodyValue(StringUtils.leftPad(detalle.getVendedor().getNombreTitular(), 100));

		String codigoOperacion = StringUtils.EMPTY;
		if (canal.equals(COD_OP_PRI)) {
			codigoOperacion = S_7735;
		} else if (canal.equals(COD_OP_ITB)) {
			codigoOperacion = S_7758;
		}

		// Codigo_operacion A4
		iatxRequestData.addBodyValue(StringUtils.leftPad(codigoOperacion, 4));

		// Motivo_contracargo A100
		iatxRequestData.addBodyValue(ISBANStringUtils.repeat(StringUtils.EMPTY, 100));

		// Id_contracargo A22
		iatxRequestData.addBodyValue(StringUtils.leftPad(idContracargo, 22));

		// Nuevo_estado_solicitud A2
		iatxRequestData.addBodyValue(ISBANStringUtils.repeat(StringUtils.EMPTY, 2));

		// Codigo_ErrorPRIBCTX A5
		iatxRequestData.addBodyValue(ISBANStringUtils.repeat(StringUtils.EMPTY, 5));

		// Descripcion_ERRORPRIBCTX A100
		iatxRequestData.addBodyValue(ISBANStringUtils.repeat(StringUtils.EMPTY, 100));

		// Clase_Debin N2
		iatxRequestData.addBodyValue(CLASE_DEBIN);

		//MISMO_TITULAR N1
		iatxRequestData.addBodyValue("0");

		// CBU_Credito A22
		iatxRequestData.addBodyValue(detalle.getComprador().getCbu());

		requestIatx.setData(iatxRequestData);

		return requestIatx;
	}

	/**
	 * Generar comprobante PDF.
	 *
	 * @param datosComprobante the datos comprobante
	 * @return the reporte
	 */
	@Override
	public Reporte generarComprobantePDF(DatosComprobanteDebinRecurrente datosComprobante) {		
		
		try {		
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(archivoJasperDebinRecurrente.getInputStream());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
			HashMap<String, Object> parameters = datosComprobante.obtenerParametrosPDF();

			parameters.put("LOGO_CABECERA", logoCabecera.getFile().getPath());
			parameters.put("LOGO_PIE", logoPie.getFile().getPath());
		
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
			// se crea el archivo PDF
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			byte[] byteArray = outStream.toByteArray();
			Reporte reporte = new Reporte();
			reporte.setTipoArchivo(TipoArchivoEnum.PDF);
			reporte.setBytes(byteArray);
			reporte.setNombre("comprobanteDebinRecurrente.pdf");
			return reporte;
		} catch (JRException ex) {
			throw new ISBANRuntimeException(ex);
		} catch (IOException e) {
			throw new ISBANRuntimeException(e);
		}
	}

	private String getBasicAuthHeader() throws DAOException{

		Credential credential = obtenerCredencial();
		return "basic " + Base64Utils.encodeToString((credential.getUsuario() + ":" + credential.getPassword()).getBytes());

	}

	private Credential obtenerCredencial() throws DAOException {
		Credential credential;
		try {
			credential = credentialSecurityFactory.getCredentialById(secId);
		} catch (SQLException e) {
			throw new DAOException("Error al obtener credenciales");
		}
		return credential;
	}

}
