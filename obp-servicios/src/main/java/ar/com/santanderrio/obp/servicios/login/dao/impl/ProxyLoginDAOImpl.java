package ar.com.santanderrio.obp.servicios.login.dao.impl;

import java.security.Key;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.auth0.jwt.internal.org.apache.commons.codec.binary.Base64;
import ar.com.santanderrio.obp.base.clientes.entities.CredencialCliente;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.clave.online.entities.AltaSGIClaveInEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.CambioUsuarioEntity;
import ar.com.santanderrio.obp.servicios.clientes.utils.CriptoDec;
import ar.com.santanderrio.obp.servicios.login.dao.ProxyLoginDAO;
import ar.com.santanderrio.obp.servicios.login.entity.ProxyLoginRequest;
import ar.com.santanderrio.obp.servicios.login.entity.ProxyLoginResponse;
import ar.com.santanderrio.obp.servicios.oauth2.connector.OAuthV2Connector;

/**
 * The Class ProxyLoginDAOImpl.
 */
@Component
public class ProxyLoginDAOImpl implements ProxyLoginDAO {

	/** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ProxyLoginDAOImpl.class);
    /** The Constant AUTHORIZATION_BEARER. */
    private static final String AUTHORIZATION_BEARER = "Bearer ";

    /** The Constant CLAVE_OFF_FIN. */
	private static final int CLAVE_OFF_FIN = 8;

	/** The Constant CLAVE_OFF_INI. */
	private static final int CLAVE_OFF_INI = 4;

	/** The Constant HEADER_AUTHORIZATION. */
	private static final String HEADER_AUTHORIZATION = "Authorization";

	/** The Constant HEADER_CICS. */
	private static final String HEADER_CICS = "X-SAN-Force-Cics-Port";

	/** The Constant HEADER_CHANNEL_ID. */
	private static final String HEADER_CHANNEL_ID = "X-SAN-Iatx-Channel-Id";

	/** The Constant HEADER_CHANNEL_TYPE. */
	private static final String HEADER_CHANNEL_TYPE = "X-SAN-Iatx-Channel-Type";

	/** The Constant HEADER_SUBCHANNEL_ID. */
	private static final String HEADER_SUBCHANNEL_ID = "X-SAN-Iatx-Subchannel-Id";

	/** The Constant HEADER_SUBCHANNEL_TYPE. */
	private static final String HEADER_SUBCHANNEL_TYPE = "X-SAN-Iatx-Subchannel-Type";

	/** The Constant HEADER_CORRELATION_ID. */
	private static final String HEADER_CORRELATION_ID = "X-SAN-CorrelationId";

	/** The Constant CHANNEL_VALUE. */
	private static final String CHANNEL_ID_VALUE = "HTML";

	/** The Constant SUBCHANNEL_VALUE. */
	private static final String SUBCHANNEL_ID_VALUE = "0001";

	/** The Constant CHANNEL_VALUE. */
	private static final String CHANNEL_TYPE_VALUE = "04";

	/** The Constant SUBCHANNEL_VALUE. */
	private static final String SUBCHANNEL_TYPE_VALUE = "99";

	/** The Constant PATH_CREDENTIALS. */
	private static final String PATH_CREDENTIALS = "/user/%s/credentials";

	/** The Constant PATH_PASSWORD. */
	private static final String PATH_PASSWORD = "/user/%s/password";

	/** The Constant PATH_PIN. */
	private static final String PATH_PIN = "/user/%s/pin";

	/** The Constant PATH_TOKEN. */
	private static final String PATH_TOKEN = "/token";

    /** The iatx cics. */
    @Value("${IATX.CICS}")
    private String iatxCics;

    /** The iatx port. */
    @Value("${IATX.GATEPORT}")
    private String iatxPort;

    /** The rest template. */
    @Autowired
    @Qualifier("restLoginTemplate")
    private RestTemplate restTemplate;

	/** The credential security factory. */
    @Autowired
    private CredentialSecurityFactory credentialSecurityFactory;

    /** The oAuthV2Connector. */
    @Autowired
    private OAuthV2Connector oAuthV2Connector;

    /** The proxy login enc sec id. */
    @Value("${PROXYLOGIN.ENC.SEC.ID}")
    private String proxyLoginEncSecId;

    /** The proxy login iv sec id. */
    @Value("${PROXYLOGIN.IV.SEC.ID}")
    private String proxyLoginIvSecId;

    /** The proxy login sec id. */
    @Value("${PROXYLOGIN.OAUTHV2.SEC.ID}")
    private String proxyLoginSecId;

    /** The proxy login url. */
    @Value("${PROXYLOGIN.URL}")
	private String proxyLoginUrl;
    
	@Value("${PROXYLOGIN.OAUTHV2.URL}")
	private String proxyOauthLoginUrl;

	/** The scope. */
	@Value("${PROXYLOGIN.SCOPE}")
	private String scope;

	@Override
	public ProxyLoginResponse obtenerTokenLogin(CredencialCliente cc) throws DAOException {
		return obtenerToken(cc.getDniOri(), cc.getClave(), null, 
				cc.getUsuario(), null, cc.getFechaNacimiento());
	}

	@Override
	public ProxyLoginResponse obtenerToken(CredencialCliente cc) throws DAOException {
		return obtenerToken(cc.getDniOri(), cc.getClave(), cc.getClaveNueva(), 
				cc.getUsuario(), cc.getUsuarioNuevo(), cc.getFechaNacimiento());
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
	 * @return the proxy login response
	 * @throws DAOException the DAO exception
	 */
	private ProxyLoginResponse obtenerToken(String dni, String crClave, String crClaveNueva, 
			String crUsuario, String crUsuarioNuevo, Date fechaNacimiento) throws DAOException {
		MultiValueMap<String, String> map;
		try {
			map = generarRequestMap(dni, crClave, crClaveNueva, crUsuario, crUsuarioNuevo, fechaNacimiento, null);
		} catch (Exception e) {
			throw new DAOException("Error al invocar a ProxyLogin api: " + e);
		}
		String url = this.buildUrl(PATH_TOKEN);
		return callProxyLogin(url, HttpMethod.POST, this.getFormRequestEntity(map));
	}

	/**
	 * Map error response.
	 *
	 * @param hscex the hscex
	 * @return the proxy login response
	 */
	private ProxyLoginResponse mapErrorResponse(HttpStatusCodeException hscex) {
		ObjectMapper objectMapper = new ObjectMapper();
		ProxyLoginResponse response = null;
		try {
			response = objectMapper.readValue(hscex.getResponseBodyAsString(), ProxyLoginResponse.class);
		} catch (Exception e) {
			LOGGER.info("Error al invocar a ProxyLogin api. No se pudo mapear el error");
		}
		return response;
	}

	/**
	 * Generar request map.
	 *
	 * @param documentNumber the document number
	 * @param crPin the cr pin
	 * @param crNewPin the cr new pin
	 * @param crPassword the cr password
	 * @param crNewPassword the cr new password
	 * @param birthDate the birth date
	 * @param isAlta the is alta
	 * @return the multi value map
	 * @throws Exception the exception
	 */
	private MultiValueMap<String, String> generarRequestMap(String documentNumber, String crPin, String crNewPin, 
    		String crPassword, String crNewPassword, Date birthDate, Boolean isAlta) throws Exception {	
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("documentNumber", documentNumber);
        if (birthDate != null) {
        	map.add("birthDate", new SimpleDateFormat("yyyy-MM-dd").format(birthDate));
        }
        map.add("password", encriptar(CriptoDec.decrypt(crPassword).trim()));
        if (crNewPassword != null) {
        	map.add("newPassword", encriptar(CriptoDec.decrypt(crNewPassword).trim()));
        }
        map.add("pin", encriptar(CriptoDec.decrypt(crPin).substring(CLAVE_OFF_INI, CLAVE_OFF_FIN)));
        if (crNewPin != null) {
        	map.add("newPin", encriptar(CriptoDec.decrypt(crNewPin).substring(CLAVE_OFF_INI, CLAVE_OFF_FIN)));
        }
        if (isAlta != null) {
        	map.add("isAlta", String.valueOf(isAlta));
        }
        return map;
    }

	/**
	 * Gets the form request entity.
	 *
	 * @param request the request
	 * @return the form request entity
	 * @throws DAOException 
	 */
	private HttpEntity<MultiValueMap<String, String>> getFormRequestEntity(MultiValueMap<String, String> request) throws DAOException {
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Arrays.asList(org.springframework.http.MediaType.APPLICATION_JSON));
		headers.set(HEADER_CHANNEL_ID, CHANNEL_ID_VALUE);
		headers.set(HEADER_SUBCHANNEL_ID, SUBCHANNEL_ID_VALUE);
		headers.set(HEADER_CHANNEL_TYPE, CHANNEL_TYPE_VALUE);
		headers.set(HEADER_SUBCHANNEL_TYPE, SUBCHANNEL_TYPE_VALUE);
		headers.set(HEADER_CICS, iatxCics + ":" + iatxPort);
		headers.set(HEADER_AUTHORIZATION, AUTHORIZATION_BEARER + 
				oAuthV2Connector.getAccessToken(obtenerCredenciales(), scope, proxyOauthLoginUrl).getAccessToken());
		String correlationId = UUID.randomUUID().toString();
		headers.set(HEADER_CORRELATION_ID, correlationId);
		LOGGER.info("Invocando a proxy-login-api - X-SAN-CorrelationId: " + correlationId);
        return new HttpEntity<MultiValueMap<String, String>>(request, headers);
	}

	/**
	 * Obtener credenciales.
	 *
	 * @return the credential
	 * @throws DAOException the DAO exception
	 */
	private Credential obtenerCredenciales() throws DAOException {
		Credential credential;
		try {
			credential = credentialSecurityFactory.getCredentialById(proxyLoginSecId);
		} catch (SQLException e) {
			throw new DAOException("Error al obtener credenciales");
		}
		return credential;
	}

	private boolean isServiceError(HttpStatus statusCode) {
		return !(HttpStatus.OK.equals(statusCode) || HttpStatus.CREATED.equals(statusCode)
				|| HttpStatus.BAD_REQUEST.equals(statusCode));
	}

	/**
	 * Encriptar.
	 *
	 * @param str the str
	 * @return the string
	 * @throws Exception the exception
	 */
	private String encriptar(String str) throws Exception {
		try {
			Credential credential = credentialSecurityFactory.getCredentialById(proxyLoginEncSecId);
			Credential credentialIv = credentialSecurityFactory.getCredentialById(proxyLoginIvSecId);
			return encriptar(str, credential.getPassword(), credentialIv.getPassword().getBytes("UTF-8"));
		} catch (Exception e) {
			LOGGER.error("Error al encriptar: {}", e);
    		throw new DAOException(e);
    	}
	}

	/**
	 * Encriptar.
	 *
	 * @param str the str
	 * @param secretKey the secret key
	 * @param iv the iv
	 * @return the string
	 * @throws Exception the exception
	 */
	private String encriptar(String str, String secretKey, byte[] iv) throws Exception {
        IvParameterSpec ivspec = new IvParameterSpec(iv);  //NOSONAR
        Key secretKeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec , ivspec);
        byte[] enc = Base64.encodeBase64(cipher.doFinal(str.getBytes("UTF-8")), false);
		return new String(enc);
	}

	@Override
	public ProxyLoginResponse setPin(CredencialCliente cc, CambioUsuarioEntity entity) throws DAOException {
		ProxyLoginRequest request = null;
		String url = null;
		try {
			request = obtenerRequest(entity.getStrOldPin(), entity.getStrNewPin(), null, null, null);
			url = this.buildUrl(String.format(PATH_PIN, entity.getCliente().getNup()));
		} catch (Exception e) {
			throw new DAOException("Error al invocar a ProxyLogin api: " + e);
		}
		return callProxyLogin(url, HttpMethod.PUT, this.getRequestEntity(request));
	}

	@Override
	public ProxyLoginResponse setPassword(CredencialCliente cc, CambioUsuarioEntity entity) throws DAOException {
		ProxyLoginRequest request = null;
		String url = null;
		try {
			request = obtenerRequest(null, null, entity.getStrOldUsr(), entity.getStrNewUsr(), null);
			url = this.buildUrl(String.format(PATH_PASSWORD, entity.getCliente().getNup()));
		} catch (Exception e) {
			throw new DAOException("Error al invocar a ProxyLogin api: " + e);
		}
		return callProxyLogin(url, HttpMethod.PUT, this.getRequestEntity(request));
	}

	@Override
	public ProxyLoginResponse setPinAndPassword(CredencialCliente cc, CambioUsuarioEntity entity) throws DAOException {
		ProxyLoginRequest request = null;
		String url = null;
		try {
			request = obtenerRequest(entity.getStrOldPin(), entity.getStrNewPin(), entity.getStrOldUsr(), 
					entity.getStrNewUsr(), null);
			url = this.buildUrl(String.format(PATH_CREDENTIALS, entity.getCliente().getNup()));
		} catch (Exception e) {
			throw new DAOException("Error al invocar a ProxyLogin api: " + e);
		}
		return callProxyLogin(url, HttpMethod.PUT, this.getRequestEntity(request));
	}

	@Override
	public ProxyLoginResponse setCredentials(AltaSGIClaveInEntity altaSGIClaveIn) throws DAOException {
		ProxyLoginRequest request = null;
		String url = null;
		try {
			request = obtenerRequest(null, altaSGIClaveIn.getClaveEncriptado(), null, 
					altaSGIClaveIn.getUsuarioEncriptado(), altaSGIClaveIn.getIdSesion());
			url = this.buildUrl(String.format(PATH_CREDENTIALS, altaSGIClaveIn.getNup()));
		} catch (Exception e) {
			throw new DAOException("Error al invocar a ProxyLogin api: " + e);
		}
		return callProxyLogin(url, HttpMethod.PUT, this.getRequestEntity(request));
	}

	@Override
	public ProxyLoginResponse updatePinPassword(CredencialCliente cc) throws DAOException {
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
	 * @return the proxy login response
	 * @throws DAOException the DAO exception
	 */
	private ProxyLoginResponse updatePinPassword(String dni, String crClave, String crClaveNueva, 
			String crUsuario, String crUsuarioNuevo, Date fechaNacimiento) throws DAOException {
		MultiValueMap<String, String> map;
		try {
			map = generarRequestMap(dni, crClave, crClaveNueva, crUsuario, crUsuarioNuevo, fechaNacimiento, Boolean.TRUE);
		} catch (Exception e) {
			throw new DAOException("Error al invocar a ProxyLogin api: " + e);
		}
		String url = this.buildUrl(PATH_TOKEN);
		return callProxyLogin(url, HttpMethod.POST, this.getFormRequestEntity(map));
	}

	/**
	 * Obtener request.
	 *
	 * @param crPin the cr pin
	 * @param crNewPin the cr new pin
	 * @param crPassword the cr password
	 * @param crNewPassword the cr new password
	 * @param sessionId the session id
	 * @return the proxy login request
	 * @throws Exception the exception
	 */
	private ProxyLoginRequest obtenerRequest(String crPin, String crNewPin, String crPassword, String crNewPassword, String sessionId) throws Exception {
		ProxyLoginRequest request = new ProxyLoginRequest();
		if (crPin != null) {
			request.setActualPin(encriptar(CriptoDec.decrypt(crPin).substring(CLAVE_OFF_INI, CLAVE_OFF_FIN)));
		}
		if (crNewPin != null) {
			request.setNewPin(encriptar(CriptoDec.decrypt(crNewPin).substring(CLAVE_OFF_INI, CLAVE_OFF_FIN)));
		}
		if (crPassword != null) {
			request.setActualPassword(encriptar(CriptoDec.decrypt(crPassword).trim()));
		}
		if (crNewPassword != null) {
			request.setNewPassword(encriptar(CriptoDec.decrypt(crNewPassword).trim()));
		}
		if (sessionId != null) {
			request.setSessionId(sessionId);
		}
		return request;
	}

	/**
	 * Call proxy login.
	 *
	 * @param <T> the generic type
	 * @param url the url
	 * @param method the method
	 * @param request the request
	 * @return the proxy login response
	 * @throws DAOException the DAO exception
	 */
	private <T> ProxyLoginResponse callProxyLogin(String url, HttpMethod method, HttpEntity<T> request)
			throws DAOException {
		ProxyLoginResponse response = null;
		try {
			ResponseEntity<ProxyLoginResponse> resp = restTemplate.exchange(url,
					method, request, ProxyLoginResponse.class);
			response = resp.getBody();
		} catch (HttpStatusCodeException hscex) {
			HttpStatus statusCode = hscex.getStatusCode();
			if (isServiceError(statusCode)) {
				LOGGER.info("Error al invocar a ProxyLogin api. Status: {}", statusCode.value());
				throw new DAOException("Error al invocar a ProxyLogin api");
			}
			response = new ProxyLoginResponse();
			BeanUtils.copyProperties(mapErrorResponse(hscex), response);
		} catch (Exception e) {
			LOGGER.info("Error al invocar a ProxyLogin api: {}", e);
			throw new DAOException("Error al invocar a ProxyLogin api");
		}
		if (response == null) {
			throw new DAOException("Error al invocar a ProxyLogin api");
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
		urlBuilder.append(proxyLoginUrl).append(endpoint);
		return urlBuilder.toString();
	}

	/**
	 * Gets the request entity.
	 *
	 * @param request the request
	 * @return the request entity
	 * @throws DAOException 
	 */
	private HttpEntity<ProxyLoginRequest> getRequestEntity(ProxyLoginRequest request) throws DAOException {
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(org.springframework.http.MediaType.APPLICATION_JSON));
		headers.set(HEADER_CHANNEL_ID, CHANNEL_ID_VALUE);
		headers.set(HEADER_SUBCHANNEL_ID, SUBCHANNEL_ID_VALUE);
		headers.set(HEADER_CHANNEL_TYPE, CHANNEL_TYPE_VALUE);
		headers.set(HEADER_SUBCHANNEL_TYPE, SUBCHANNEL_TYPE_VALUE);
		headers.set(HEADER_CICS, iatxCics + ":" + iatxPort);
		headers.set(HEADER_AUTHORIZATION, AUTHORIZATION_BEARER + 
				oAuthV2Connector.getAccessToken(obtenerCredenciales(), scope, proxyOauthLoginUrl));
		String correlationId = UUID.randomUUID().toString();
		headers.set(HEADER_CORRELATION_ID, correlationId);
		LOGGER.info("Invocando a proxy-login-api - X-SAN-CorrelationId: " + correlationId);
        return new HttpEntity<ProxyLoginRequest>(request, headers);
	}

}
