package ar.com.santanderrio.obp.servicios.login.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.http.HttpHeaders;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.owasp.esapi.codecs.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import ar.com.santanderrio.obp.base.clientes.entities.CredencialCliente;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.servicios.clave.online.entities.AltaSGIClaveInEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.CambioUsuarioEntity;
import ar.com.santanderrio.obp.servicios.clientes.utils.CriptoDec;
import ar.com.santanderrio.obp.servicios.comun.dao.ArchivoDeRecursosDAO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.login.dao.ApiAuthDAO;
import ar.com.santanderrio.obp.servicios.login.entity.ApiAuthErrorResponse;
import ar.com.santanderrio.obp.servicios.login.entity.ApiAuthLoginResponse;
import ar.com.santanderrio.obp.servicios.login.entity.ApiAuthRequest;
import ar.com.santanderrio.obp.servicios.modulos.dao.ModuloPermisoDAO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;

@Component()
public class ApiAuthDAOImpl implements ApiAuthDAO {

    private static final String ERROR_DE_FORMATO_EN_LA_LINEA = "Error de formato en la linea: {0} del archivo {1}.txt";

	private static final String ERROR_STATUS_CODE = "Error al invocar a sm-api-auth. Status: {}";

	private static final String INVOCANDO_A_API_AUTH_X_SAN_CORRELATION_ID = "Invocando a api-auth -X-SAN-CorrelationId: {}";

	private static final String SCOPE_PARAM = "scope";

	private static final String EXCEPTION_SM_API_AUTH = "Exception sm-api-auth";

	private static final String ERROR_AL_INVOCAR_A_SM_API_AUTH = "Error al invocar a sm-api-auth: {}";

	private static final String API_AUTH_LOGOUT_SERVICE = "[api-auth] logout service: {} ";

	private static final String REFRESH_TOKEN_PARAM = "refresh_token";

	private static final String CLIENT_SECRET_PARAM = "client_secret";

	private static final String CLIENT_ID_PARAM = "client_id";

	@Autowired
    private RestWebClient restWebClient;

    /** The Constant NOMBRE_WS. */
    private static final String NOMBRE_WS = "APIAUTH";

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiAuthDAOImpl.class);

    private static final String PATH_LOGIN = "/auth/login";

    private static final String PATH_LOGIN_WITH_NEW_PASSWORD = "/auth/loginWithNewPassword";

    private static final String PATH_REFRESH = "/auth/refresh";

    private static final String PATH_LOGOUT = "/auth/logout";

    private static final String BEARER = "Bearer ";
    
    private static final String DATE_PATTERN = "yyyy-MM-dd ";

	private final SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_PATTERN);
	
	@Autowired
	private SesionParametros sesionParametros;

    @Value("${APIAUTH.URL}")
    private String apiAuthUrl;
    @Value("${APIAUTH.CLIENT_ID}")
    private String clientId;
    @Value("${APIAUTH.CLIENT_SECRET}")
    private String clientSecret;
    @Value("${APIAUTH.SCOPE}")
    private String scope;
    
    @Autowired
    private RestTemplate restApiAuthTemplate;

    @Autowired
	private ArchivoDeRecursosDAO archivoResource;
    
    @Autowired
    private ModuloPermisoDAO moduloPermisoDAO;

    /**
     * @param pathDeConsulta
     * @return
     * @throws DAOException
     */
    private WebClient crearLlamadaAWebService(String pathDeConsulta) throws DAOException {

        WebClient service = restWebClient.obtenerClienteRest(NOMBRE_WS);
        service.accept(MediaType.APPLICATION_JSON);
        service.type(MediaType.APPLICATION_JSON + ";charset=UTF-8").accept(MediaType.APPLICATION_JSON);
        service.path(pathDeConsulta);
        return service;
    }

    @Override
    public ApiAuthLoginResponse refreshToken(String jwt, String refreshToken) throws DAOException {
        HashMap<String, String> request;
        request = generarRequestMap(refreshToken);
        LOGGER.info("[api-auth] Invocando a api-auth");
        WebClient service = crearLlamadaAWebService(PATH_REFRESH);
        service.header(HttpHeaders.AUTHORIZATION, BEARER + jwt);

        ApiAuthLoginResponse response = new ApiAuthLoginResponse();
        Response respuesta = service.post(request);
        Object entity = respuesta.getEntity();
        InputStream is = InputStream.class.cast(entity);
        if (is != null) {

            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
            try {
                response = mapper.readValue(IOUtils.toString(is, "UTF-8"), ApiAuthLoginResponse.class);
            } catch (JsonParseException e) {
                throw new DAOException();
            } catch (JsonMappingException e) {
                throw new DAOException();
            } catch (IOException e) {
                throw new DAOException();
            } catch (Exception e) {
                throw new DAOException();
            }
        }
        return response;
    }

    private HashMap<String, String> generarRequestMap(String jwt) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(CLIENT_ID_PARAM, clientId);
        map.put(CLIENT_SECRET_PARAM, clientSecret);
        map.put(REFRESH_TOKEN_PARAM, jwt);
        return map;
    }

    @Override
    public void logout(String token) throws DAOException {
        HashMap<String, String> request;
        request = generarRequestMap();
        LOGGER.info("[api-auth] Invocando a api-auth");
        WebClient service = crearLlamadaAWebService(PATH_LOGOUT);
        service.header(HttpHeaders.AUTHORIZATION, BEARER + token);
        try {
            Response respuesta = service.post(request);
            if (respuesta.getStatus() == 204) {
                LOGGER.info(API_AUTH_LOGOUT_SERVICE, respuesta.getStatus());
                return;
            }
            LOGGER.error(API_AUTH_LOGOUT_SERVICE, respuesta.getStatus());
        } catch (Exception e) {
            LOGGER.error(API_AUTH_LOGOUT_SERVICE);
        }
        throw new DAOException(API_AUTH_LOGOUT_SERVICE);
    }

    private HashMap<String, String> generarRequestMap() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(CLIENT_ID_PARAM, clientId);
        map.put(CLIENT_SECRET_PARAM, clientSecret);
        return map;
    }
    
    
    private static String decrypt(String encryptedHexContent, String secretKey) throws DAOException {
    	
    	try {
            IvParameterSpec ivSpec = new IvParameterSpec(new byte[16]);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);
			byte[] decryptedContent = cipher.doFinal(Hex.decode(encryptedHexContent));
			return new String(decryptedContent).trim();
		} catch (GeneralSecurityException e) {
				LOGGER.error("Decrypt Exception: ", e);
				throw new DAOException();
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Unsupported Encoding", e);
			throw new DAOException();
		}
        
    }

    public static String decrypt(String encryptedHexContent) throws  DAOException{
        return decrypt(encryptedHexContent,"012345678901234567890123");
    }
    
	@Override
	public ApiAuthLoginResponse obtenerTokenLogin(CredencialCliente in, String csid) throws DAOException {
		return obtenerToken(in.getDniOri(), CriptoDec.decrypt(in.getClave()), null, 
				CriptoDec.decrypt(in.getUsuario()), null, in.getFechaNacimiento(), csid);
	}

	@Override
	public ApiAuthLoginResponse obtenerToken(CredencialCliente cc) throws DAOException {
		return obtenerToken(cc.getDniOri(), CriptoDec.decrypt(cc.getClave()), CriptoDec.decrypt(cc.getClaveNueva()), 
				CriptoDec.decrypt(cc.getUsuario()), CriptoDec.decrypt(cc.getUsuarioNuevo()), cc.getFechaNacimiento());
	}

	/**
	 * Obtener token.
	 *
	 * @param dni the dni
	 * @param crClave the clave
	 * @param crClaveNueva the clave nueva
	 * @param crUsuario the usuario
	 * @param crUsuarioNuevo the usuario nuevo
	 * @param fechaNacimiento the fecha nacimiento
	 * @return the Api Auth Login Response
	 * @throws DAOException the DAO exception
	 */
	private ApiAuthLoginResponse obtenerToken(String dni, String crClave, String crClaveNueva, 
			String crUsuario, String crUsuarioNuevo, Date fechaNacimiento) throws DAOException {
		MultiValueMap<String, String> map;
		try {
			map = generarRequestMap(dni, crClave, crClaveNueva, crUsuario, crUsuarioNuevo, fechaNacimiento);
		} catch (Exception e) {
			throw new DAOException(ERROR_AL_INVOCAR_A_SM_API_AUTH, e.getMessage());
		}
		String url = this.buildUrl(PATH_LOGIN);
		return callApiAuth(url, HttpMethod.POST, this.getFormRequestEntity(map));
	}

	private ApiAuthLoginResponse obtenerToken(String dni, String crClave, String crClaveNueva, String crUsuario,
			String crUsuarioNuevo, Date fechaNacimiento, String csid) throws DAOException {
		MultiValueMap<String, String> map;
		try {
			map = generarRequestMap(dni, crClave, crClaveNueva, crUsuario, crUsuarioNuevo, fechaNacimiento);
			Map<String, ModuloPermiso> modulosPermisos = moduloPermisoDAO.obtenerModulosPermisos();
			ModuloPermiso moduloPermiso = modulosPermisos.get(AccionController.ACTIVAR_BIOCATCH.getDescripcion());
			if (moduloPermiso != null && moduloPermiso.tienePermisoDeVisibilidad()) {
				if(StringUtils.isNotBlank(csid)) {
					map.add("customerSessionId", csid);
				} else {
					LOGGER.info("CSID no valido: {}",csid);
				}
			}
		} catch (Exception e) {
			throw new DAOException(ERROR_AL_INVOCAR_A_SM_API_AUTH, e.getMessage());
		}
		String url = this.buildUrl(PATH_LOGIN);
		return callApiAuth(url, HttpMethod.POST, this.getFormRequestEntity(map));
	}

	/**
	 * Map error response.
	 *
	 * @param hscex the hscex
	 * @return the Api Auth Login Response
	 */
	private ApiAuthErrorResponse mapErrorResponse(HttpStatusCodeException hscex) {
		ObjectMapper objectMapper = new ObjectMapper();
		ApiAuthErrorResponse response = null;
		try {
			response = objectMapper.readValue(hscex.getResponseBodyAsString(), ApiAuthErrorResponse.class);
		} catch (Exception e) {
			LOGGER.info("Error al invocar a sm-api-auth. No se pudo mapear el error");
		}
		return response;
	}

	/**
	 * Generar request map.
	 *
	 * @param documentNumber the document number
	 * @param crPin the cr pin
	 * @param crNewPin the cr new pin
	 * @param crUserName the cr password
	 * @param crNewUserName the cr new password
	 * @param birthDate the birth date
	 * @return the multi value map
	 * @throws Exception the exception
	 */
	private MultiValueMap<String, String> generarRequestMap(String documentNumber, String crPin, String crNewPin, 
    		String crUserName, String crNewUserName, Date birthDate) {	
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("documentNumber", documentNumber.replaceFirst("^0+(?!$)", ""));
        if (birthDate != null) {
        	map.add("birthDate", dateFormatter.format(birthDate).trim());
        }
        map.add("password", StringUtils.right(crPin, 4));
        if (crNewUserName != null) {
        	map.add("newPassword", StringUtils.right(crNewPin, 4));
        }
        map.add("username", crUserName);
        if (crNewPin != null) {
        	map.add("newUserName", crNewUserName);
        }
        map.add(CLIENT_ID_PARAM, clientId);
        map.add(CLIENT_SECRET_PARAM, clientSecret);
        map.add(SCOPE_PARAM, scope);
        return map;
    }

	/**
	 * Gets the form request entity.
	 *
	 * @param request the request
	 * @return the form request entity
	 * @throws DAOException 
	 */
	private HttpEntity<MultiValueMap<String, String>> getFormRequestEntity(MultiValueMap<String, String> request) {
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType(APPLICATION_FORM_URLENCODED);
        headers.setAccept(Arrays.asList(org.springframework.http.MediaType.APPLICATION_JSON));
        headers.add("x-client-ip", sesionParametros.getRsaGenericRequestData().getRemoteIp());
        headers.add("user-agent", sesionParametros.getRsaGenericRequestData().getUserAgent());
		String correlationId = UUID.randomUUID().toString();
		LOGGER.info(INVOCANDO_A_API_AUTH_X_SAN_CORRELATION_ID, correlationId);
        return new HttpEntity<MultiValueMap<String, String>>(request, headers);
	}

	private boolean isServiceError(HttpStatus statusCode) {
		return !(HttpStatus.OK.equals(statusCode) || HttpStatus.CREATED.equals(statusCode)
				|| HttpStatus.BAD_REQUEST.equals(statusCode) || HttpStatus.UNAUTHORIZED.equals(statusCode)
				|| HttpStatus.PRECONDITION_REQUIRED.equals(statusCode) || HttpStatus.CONFLICT.equals(statusCode)
				|| HttpStatus.NOT_FOUND.equals(statusCode) || HttpStatus.FORBIDDEN.equals(statusCode)
				|| HttpStatus.FAILED_DEPENDENCY.equals(statusCode)  ||Integer.valueOf(451).equals(statusCode.value()));
	}


	@Override
	public ApiAuthLoginResponse setPin(CredencialCliente cc, CambioUsuarioEntity entity) throws DAOException {
		ApiAuthRequest request = null;
		String url = null;
		try {
			request = obtenerRequest(entity.getStrOldPin(), entity.getStrNewPin(), null, null);
			url = this.buildUrl(String.format(PATH_LOGIN_WITH_NEW_PASSWORD, entity.getCliente().getNup()));
		} catch (Exception e) {
			throw new DAOException(EXCEPTION_SM_API_AUTH);
		}
		return callApiAuth(url, HttpMethod.PUT, this.getRequestEntity(request));
	}

	@Override
	public ApiAuthLoginResponse setPassword(CredencialCliente cc, CambioUsuarioEntity entity) throws DAOException {
		ApiAuthRequest request = null;
		String url = null;
		try {
			request = obtenerRequest(null, null, entity.getStrOldUsr(), entity.getStrNewUsr());
			url = this.buildUrl(String.format(PATH_LOGIN_WITH_NEW_PASSWORD, entity.getCliente().getNup()));
		} catch (Exception e) {
			throw new DAOException(EXCEPTION_SM_API_AUTH);
		}
		return callApiAuth(url, HttpMethod.PUT, this.getRequestEntity(request));
	}

	@Override
	public ApiAuthLoginResponse setPinAndPassword(CredencialCliente cc, CambioUsuarioEntity entity) throws DAOException {
		ApiAuthRequest request = null;
		String url = null;
		try {
			request = obtenerRequest(entity.getStrOldPin(), entity.getStrNewPin(), entity.getStrOldUsr(), 
					entity.getStrNewUsr());
			url = this.buildUrl(String.format(PATH_LOGIN_WITH_NEW_PASSWORD, entity.getCliente().getNup()));
		} catch (Exception e) {
			throw new DAOException(EXCEPTION_SM_API_AUTH);
		}
		return callApiAuth(url, HttpMethod.PUT, this.getRequestEntity(request));
	}

	@Override
	public ApiAuthLoginResponse setCredentials(AltaSGIClaveInEntity altaSGIClaveIn) throws DAOException {
		ApiAuthRequest request = null;
		String url = null;
		try {
			request = obtenerRequest(null, altaSGIClaveIn.getClaveEncriptado(), null, 
					altaSGIClaveIn.getUsuarioEncriptado());
			url = this.buildUrl(String.format(PATH_LOGIN_WITH_NEW_PASSWORD, altaSGIClaveIn.getNup()));
		} catch (Exception e) {
			throw new DAOException(EXCEPTION_SM_API_AUTH);
		}
		return callApiAuth(url, HttpMethod.PUT, this.getRequestEntity(request));
	}

	@Override
	public ApiAuthLoginResponse updatePinPassword(CredencialCliente cc) throws DAOException {
		return updatePinPassword(cc.getDniOri(), cc.getClave(), cc.getClaveNueva(), 
				cc.getUsuario(), cc.getUsuarioNuevo(), cc.getFechaNacimiento());
	}

	/**
	 * Update pin password.
	 *
	 * @param dni the dni
	 * @param crClave the cr clave
	 * @param crClaveNueva the cr clave nueva
	 * @param crUsuario the cr usuario
	 * @param crUsuarioNuevo the cr usuario nuevo
	 * @param fechaNacimiento the fecha nacimiento
	 * @return the Api Auth login response
	 * @throws DAOException the DAO exception
	 */
	private ApiAuthLoginResponse updatePinPassword(String dni, String crClave, String crClaveNueva, 
			String crUsuario, String crUsuarioNuevo, Date fechaNacimiento) throws DAOException {
		MultiValueMap<String, String> map;
		try {
			map = generarRequestMap(dni, crClave, crClaveNueva, crUsuario, crUsuarioNuevo, fechaNacimiento);
		} catch (Exception e) {
			throw new DAOException(EXCEPTION_SM_API_AUTH);
		}
		String url = this.buildUrl(PATH_LOGIN_WITH_NEW_PASSWORD);
		return callApiAuth(url, HttpMethod.POST, this.getFormRequestEntity(map));
	}

	/**
	 * Obtener request.
	 *
	 * @param crPin the cr pin
	 * @param crNewPin the cr new pin
	 * @param crPassword the cr password
	 * @param crNewPassword the cr new password
	 * @param sessionId the session id
	 * @return the Api Auth login request
	 * @throws Exception the exception
	 */
	private ApiAuthRequest obtenerRequest(String crPin, String crNewPin, String crPassword, String crNewPassword) {
		ApiAuthRequest request = new ApiAuthRequest();
		if (crPin != null) {
			request.setUsername(crPin);
		}
		if (crNewPin != null) {
			request.setNewUserName(crNewPin);
		}
		if (crPassword != null) {
			request.setPassword(crPassword);
		}
		if (crNewPassword != null) {
			request.setNewPassword(crNewPassword);
		}
		return request;
	}

	/**
	 * Call Api Auth login.
	 *
	 * @param <T> the generic type
	 * @param url the url
	 * @param method the method
	 * @param request the request
	 * @return the api auth login response
	 * @throws DAOException the DAO exception
	 */
	private <T> ApiAuthLoginResponse callApiAuth(String url, HttpMethod method, HttpEntity<T> request)
			throws DAOException {
		ApiAuthLoginResponse response = null;
		try {
			ResponseEntity<ApiAuthLoginResponse> resp = restApiAuthTemplate.exchange(url,
					method, request, ApiAuthLoginResponse.class);
			response = resp.getBody();
		} catch (HttpStatusCodeException hscex) {
			HttpStatus statusCode = hscex.getStatusCode();
			if (isServiceError(statusCode)) {
				LOGGER.info(ERROR_STATUS_CODE, statusCode.value());
				throw new DAOException(EXCEPTION_SM_API_AUTH);
			}
			response = new ApiAuthLoginResponse();
			BeanUtils.copyProperties(mapErrorResponse(hscex), response);
		} catch (org.springframework.web.client.UnknownHttpStatusCodeException e) {
			if (e.getMessage().contains("[451]")) {
				response = new ApiAuthLoginResponse();
				response.setCode("10000003");
				response.setDetail("Login sesion concurrente ApiAuth");
				response.setStatus("451");
			} else {
				LOGGER.info(ERROR_AL_INVOCAR_A_SM_API_AUTH, e.getMessage());
				throw new DAOException(EXCEPTION_SM_API_AUTH);
			}
		} catch (Exception e) {
			LOGGER.info(ERROR_AL_INVOCAR_A_SM_API_AUTH, e.getMessage());
			throw new DAOException(EXCEPTION_SM_API_AUTH);
		}
		if (response == null) {
			throw new DAOException(EXCEPTION_SM_API_AUTH);
		}

		return response;
	}

	/**
	 * Builds the url.
	 *
	 * @param endpoint the endpoint
	 * @return the string
	 */
	private String buildUrl(String endpoint) {
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(apiAuthUrl).append(endpoint);
		return urlBuilder.toString();
	}

	/**
	 * Gets the request entity.
	 *
	 * @param request the request
	 * @return the request entity
	 * @throws DAOException 
	 */
	private HttpEntity<ApiAuthRequest> getRequestEntity(ApiAuthRequest request) {
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(org.springframework.http.MediaType.APPLICATION_JSON));
		String correlationId = UUID.randomUUID().toString();
		LOGGER.info(INVOCANDO_A_API_AUTH_X_SAN_CORRELATION_ID,  correlationId);
        return new HttpEntity<ApiAuthRequest>(request, headers);
	}

	@Cacheable(cacheNames = { CacheConstants.Names.CACHE_API_AUTH_CLIENTS_IDS})
	@Override
	public List<String> getApiAuthClients(String archivo) {
		
		List<String> listDocumentos = new ArrayList<String>();
		int linea = 0;
		int largoDatosMinimo = 5;
		try {
			for (String lineaTexto : archivoResource.leerArchivo("apiAuthClients.txt")) {
				++linea;
				if (largoDatosMinimo < lineaTexto.length()) {
					listDocumentos.add(lineaTexto);
				} else {
					LOGGER.error(ERROR_DE_FORMATO_EN_LA_LINEA, linea, archivo);
				}
			}
		} catch (DAOException e) {
			LOGGER.error("Error al leer: {}", archivo);
		}
		return listDocumentos;
	}

	
	@CacheEvict(value = CacheConstants.Names.CACHE_API_AUTH_CLIENTS_IDS, allEntries = true)
	@Override
	public void limpiarCacheApiAuth() {
		LOGGER.info("Se limpia la cache documentos.");
	}
}
