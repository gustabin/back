package ar.com.santanderrio.obp.servicios.login.dao;

import java.sql.SQLException;
import java.util.Date;

import ar.com.santanderrio.obp.servicios.oauth2.token.OAuth2AccessToken;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import ar.com.santanderrio.obp.base.clientes.entities.CredencialCliente;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.clave.online.entities.AltaSGIClaveInEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.CambioUsuarioEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.login.dao.impl.ProxyLoginDAOImpl;
import ar.com.santanderrio.obp.servicios.login.entity.ProxyLoginResponse;
import ar.com.santanderrio.obp.servicios.oauth2.connector.OAuthV2Connector;

@RunWith(MockitoJUnitRunner.class)
@TestPropertySource(properties = { "IATX.CICS = FEINIH2", "IATX.GATEPORT = 2300", "PROXYLOGIN.ENC.SEC.ID = ",
		"PROXYLOGIN.IV.SEC.ID", "PROXYLOGIN.URL = ", "PROXYLOGIN.SCOPE = proxy-login.read" })
public class ProxyLoginDAOTest {

	@Autowired
	@InjectMocks
	private ProxyLoginDAO proxyLoginDAO = new ProxyLoginDAOImpl();

	@Mock
	@Qualifier("restLoginTemplate")
	private RestTemplate restTemplate;

	@Mock
	private OAuthV2Connector oAuthV2Connector;

	@Mock
	private CredentialSecurityFactory credentialSecurityFactory;

	private static Credential credential;
	private static OAuth2AccessToken accessToken;

	ResponseEntity<ProxyLoginResponse> responseOk = new ResponseEntity<ProxyLoginResponse>(new ProxyLoginResponse(),
			HttpStatus.CREATED);

	static {
		credential = new Credential();
		credential.setUsuario("1234567890");
		credential.setPassword("1234567890123456");

		accessToken = new OAuth2AccessToken();
		accessToken.setAccessToken("dummy-token");

	}

	@Before
	public void init() {

	}

	//TODO: Add cache layer tests
	@Test
	public void obtenerTokenLoginOkTest() throws SQLException, DAOException {
		CredencialCliente cc = new CredencialCliente();
		cc.setDniOri("12345678");
		cc.setClave("330A330A330A330A330A330A330A330A");
		cc.setFechaNacimiento(new Date());
		cc.setUsuario("348A304A37A304A239A297A349A271A315A315A315A315A315A315A315A315A315A315A315A315A");

		Mockito.when(restTemplate.exchange(Matchers.anyString(), Matchers.any(HttpMethod.class),
				Matchers.any(HttpEntity.class), Mockito.eq(ProxyLoginResponse.class))).thenReturn(responseOk);
		Mockito.when(credentialSecurityFactory.getCredentialById(Matchers.anyString())).thenReturn(credential);
		Mockito.when(oAuthV2Connector.getAccessToken(Matchers.any(Credential.class), Matchers.anyString(), Matchers.anyString())).thenReturn(accessToken);

		ProxyLoginResponse response = proxyLoginDAO.obtenerTokenLogin(cc);
		Assert.assertNotNull(response);
		Assert.assertNull(response.getMessage());
	}

	@Test
	public void obtenerTokenLoginUnauthTest() throws SQLException, DAOException {
		CredencialCliente cc = new CredencialCliente();
		cc.setDniOri("12345678");
		cc.setClave("330A330A330A330A330A330A330A330A");
		cc.setFechaNacimiento(new Date());
		cc.setUsuario("348A304A37A304A239A297A349A271A315A315A315A315A315A315A315A315A315A315A315A315A");
		String responseBody = "{\"code\": \"400\", \"message\": \"10001016\", \"description\": \"No es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.||CLAVE INCORRECTA\"}";
		byte[] body = responseBody.getBytes();
		HttpClientErrorException hscex = new HttpClientErrorException(HttpStatus.BAD_REQUEST, "", body, null);

		Mockito.when(restTemplate.exchange(Matchers.anyString(), Matchers.any(HttpMethod.class),
				Matchers.any(HttpEntity.class), Mockito.eq(ProxyLoginResponse.class))).thenThrow(hscex);
		Mockito.when(credentialSecurityFactory.getCredentialById(Matchers.anyString())).thenReturn(credential);
		Mockito.when(oAuthV2Connector.getAccessToken(Matchers.any(Credential.class), Matchers.anyString(), Matchers.anyString())).thenReturn(accessToken);


		ProxyLoginResponse response = proxyLoginDAO.obtenerTokenLogin(cc);
		Assert.assertNotNull(response);
		Assert.assertEquals("10001016", response.getMessage());
	}

	@Test
	public void obtenerTokenOkTest() throws SQLException, DAOException {
		CredencialCliente cc = new CredencialCliente();
		cc.setDniOri("12345678");
		cc.setClave("330A330A330A330A330A330A330A330A");
		cc.setClaveNueva("330A330A330A330A330A330A330A330A");
		cc.setFechaNacimiento(new Date());
		cc.setUsuario("348A304A37A304A239A297A349A271A315A315A315A315A315A315A315A315A315A315A315A315A");
		cc.setUsuarioNuevo("348A304A37A304A239A297A349A271A315A315A315A315A315A315A315A315A315A315A315A315A");

		Mockito.when(restTemplate.exchange(Matchers.anyString(), Matchers.any(HttpMethod.class),
				Matchers.any(HttpEntity.class), Mockito.eq(ProxyLoginResponse.class))).thenReturn(responseOk);
		Mockito.when(credentialSecurityFactory.getCredentialById(Matchers.anyString())).thenReturn(credential);
		Mockito.when(oAuthV2Connector.getAccessToken(Matchers.any(Credential.class), Matchers.anyString(), Matchers.anyString())).thenReturn(accessToken);

		ProxyLoginResponse response = proxyLoginDAO.obtenerToken(cc);
		Assert.assertNotNull(response);
		Assert.assertNull(response.getMessage());
	}

	@Test
	public void obtenerTokenUnauthTest() throws SQLException, DAOException {
		CredencialCliente cc = new CredencialCliente();
		cc.setDniOri("12345678");
		cc.setClave("330A330A330A330A330A330A330A330A");
		cc.setClaveNueva("330A330A330A330A330A330A330A330A");
		cc.setFechaNacimiento(new Date());
		cc.setUsuario("348A304A37A304A239A297A349A271A315A315A315A315A315A315A315A315A315A315A315A315A");
		cc.setUsuarioNuevo("348A304A37A304A239A297A349A271A315A315A315A315A315A315A315A315A315A315A315A315A");
		String responseBody = "{\"code\": \"400\", \"message\": \"10001016\", \"description\": \"No es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.||CLAVE INCORRECTA\"}";
		byte[] body = responseBody.getBytes();
		HttpClientErrorException hscex = new HttpClientErrorException(HttpStatus.BAD_REQUEST, "", body, null);

		Mockito.when(restTemplate.exchange(Matchers.anyString(), Matchers.any(HttpMethod.class),
				Matchers.any(HttpEntity.class), Mockito.eq(ProxyLoginResponse.class))).thenThrow(hscex);
		Mockito.when(credentialSecurityFactory.getCredentialById(Matchers.anyString())).thenReturn(credential);
		Mockito.when(oAuthV2Connector.getAccessToken(Matchers.any(Credential.class), Matchers.anyString(), Matchers.anyString())).thenReturn(accessToken);

		ProxyLoginResponse response = proxyLoginDAO.obtenerToken(cc);
		Assert.assertNotNull(response);
		Assert.assertEquals("10001016", response.getMessage());
	}

	@Test
	public void setPinOkTest() throws SQLException, DAOException {
		CredencialCliente cc = new CredencialCliente();
		CambioUsuarioEntity entity = new CambioUsuarioEntity();
		entity.setStrOldPin("330A330A330A330A330A330A330A330A");
		entity.setStrNewPin("330A330A330A330A330A330A330A330A");
		entity.setCliente(new Cliente());

		Mockito.when(restTemplate.exchange(Matchers.anyString(), Matchers.any(HttpMethod.class),
				Matchers.any(HttpEntity.class), Mockito.eq(ProxyLoginResponse.class))).thenReturn(responseOk);
		Mockito.when(credentialSecurityFactory.getCredentialById(Matchers.anyString())).thenReturn(credential);

		ProxyLoginResponse response = proxyLoginDAO.setPin(cc, entity);
		Assert.assertNotNull(response);
		Assert.assertNull(response.getMessage());
	}

	@Test
	public void setPinErrorTest() throws SQLException, DAOException {
		CredencialCliente cc = new CredencialCliente();
		CambioUsuarioEntity entity = new CambioUsuarioEntity();
		entity.setStrOldPin("330A330A330A330A330A330A330A330A");
		entity.setStrNewPin("330A330A330A330A330A330A330A330A");
		entity.setCliente(new Cliente());
		String responseBody = "{\"code\": \"400\", \"message\": \"10001028\", \"description\": \"No es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.||CLAVE NUEVA TRIVIAL O HISTORICO\"}";
		byte[] body = responseBody.getBytes();
		HttpClientErrorException hscex = new HttpClientErrorException(HttpStatus.BAD_REQUEST, "", body, null);

		Mockito.when(restTemplate.exchange(Matchers.anyString(), Matchers.any(HttpMethod.class),
				Matchers.any(HttpEntity.class), Mockito.eq(ProxyLoginResponse.class))).thenThrow(hscex);
		Mockito.when(credentialSecurityFactory.getCredentialById(Matchers.anyString())).thenReturn(credential);

		ProxyLoginResponse response = proxyLoginDAO.setPin(cc, entity);
		Assert.assertNotNull(response);
		Assert.assertEquals("10001028", response.getMessage());
	}

	@Test
	public void setPasswordOkTest() throws SQLException, DAOException {
		CredencialCliente cc = new CredencialCliente();
		CambioUsuarioEntity entity = new CambioUsuarioEntity();
		entity.setStrOldUsr("330A330A330A330A330A330A330A330A");
		entity.setStrNewUsr("330A330A330A330A330A330A330A330A");
		entity.setCliente(new Cliente());

		Mockito.when(restTemplate.exchange(Matchers.anyString(), Matchers.any(HttpMethod.class),
				Matchers.any(HttpEntity.class), Mockito.eq(ProxyLoginResponse.class))).thenReturn(responseOk);
		Mockito.when(credentialSecurityFactory.getCredentialById(Matchers.anyString())).thenReturn(credential);

		ProxyLoginResponse response = proxyLoginDAO.setPassword(cc, entity);
		Assert.assertNotNull(response);
		Assert.assertNull(response.getMessage());
	}

	@Test
	public void setPasswordErrorTest() throws SQLException, DAOException {
		CredencialCliente cc = new CredencialCliente();
		CambioUsuarioEntity entity = new CambioUsuarioEntity();
		entity.setStrOldUsr("330A330A330A330A330A330A330A330A");
		entity.setStrNewUsr("330A330A330A330A330A330A330A330A");
		entity.setCliente(new Cliente());
		String responseBody = "{\"code\": \"400\", \"message\": \"10001026\", \"description\": \"No es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.||NUEVO USUARIO TRIVIAL O HISTORICO\"}";
		byte[] body = responseBody.getBytes();
		HttpClientErrorException hscex = new HttpClientErrorException(HttpStatus.BAD_REQUEST, "", body, null);

		Mockito.when(restTemplate.exchange(Matchers.anyString(), Matchers.any(HttpMethod.class),
				Matchers.any(HttpEntity.class), Mockito.eq(ProxyLoginResponse.class))).thenThrow(hscex);
		Mockito.when(credentialSecurityFactory.getCredentialById(Matchers.anyString())).thenReturn(credential);

		ProxyLoginResponse response = proxyLoginDAO.setPassword(cc, entity);
		Assert.assertNotNull(response);
		Assert.assertEquals("10001026", response.getMessage());
	}

	@Test
	public void updatePinPasswordOk() throws SQLException, DAOException {
		CredencialCliente cc = new CredencialCliente();
		cc.setDniOri("12345678");
		cc.setClave("330A330A330A330A330A330A330A330A");
		cc.setClaveNueva("330A330A330A330A330A330A330A330A");
		cc.setFechaNacimiento(new Date());
		cc.setUsuario("348A304A37A304A239A297A349A271A315A315A315A315A315A315A315A315A315A315A315A315A");
		cc.setUsuarioNuevo("348A304A37A304A239A297A349A271A315A315A315A315A315A315A315A315A315A315A315A315A");

		Mockito.when(restTemplate.exchange(Matchers.anyString(), Matchers.any(HttpMethod.class),
				Matchers.any(HttpEntity.class), Mockito.eq(ProxyLoginResponse.class))).thenReturn(responseOk);
		Mockito.when(credentialSecurityFactory.getCredentialById(Matchers.anyString())).thenReturn(credential);
		Mockito.when(oAuthV2Connector.getAccessToken(Matchers.any(Credential.class), Matchers.anyString(), Matchers.anyString())).thenReturn(accessToken);

		ProxyLoginResponse response = proxyLoginDAO.updatePinPassword(cc);
		Assert.assertNotNull(response);
		Assert.assertNull(response.getMessage());
	}

	@Test
	public void updatePinPasswordError() throws SQLException, DAOException {
		CredencialCliente cc = new CredencialCliente();
		cc.setDniOri("12345678");
		cc.setClave("330A330A330A330A330A330A330A330A");
		cc.setClaveNueva("330A330A330A330A330A330A330A330A");
		cc.setFechaNacimiento(new Date());
		cc.setUsuario("348A304A37A304A239A297A349A271A315A315A315A315A315A315A315A315A315A315A315A315A");
		cc.setUsuarioNuevo("348A304A37A304A239A297A349A271A315A315A315A315A315A315A315A315A315A315A315A315A");
		String responseBody = "{\"code\": \"400\", \"message\": \"10001026\", \"description\": \"No es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.||NUEVO USUARIO TRIVIAL O HISTORICO\"}";
		byte[] body = responseBody.getBytes();
		HttpClientErrorException hscex = new HttpClientErrorException(HttpStatus.BAD_REQUEST, "", body, null);

		Mockito.when(restTemplate.exchange(Matchers.anyString(), Matchers.any(HttpMethod.class),
				Matchers.any(HttpEntity.class), Mockito.eq(ProxyLoginResponse.class))).thenThrow(hscex);
		Mockito.when(credentialSecurityFactory.getCredentialById(Matchers.anyString())).thenReturn(credential);
		Mockito.when(oAuthV2Connector.getAccessToken(Matchers.any(Credential.class), Matchers.anyString(), Matchers.anyString())).thenReturn(accessToken);

		ProxyLoginResponse response = proxyLoginDAO.updatePinPassword(cc);
		Assert.assertNotNull(response);
		Assert.assertEquals("10001026", response.getMessage());
	}

	@Test
	public void setPinAndPasswordOk() throws SQLException, DAOException {
		CredencialCliente cc = new CredencialCliente();
		CambioUsuarioEntity entity = new CambioUsuarioEntity();
		entity.setStrOldPin("330A330A330A330A330A330A330A330A");
		entity.setStrNewPin("330A330A330A330A330A330A330A330A");
		entity.setStrOldUsr("330A330A330A330A330A330A330A330A");
		entity.setStrNewUsr("330A330A330A330A330A330A330A330A");
		entity.setCliente(new Cliente());

		Mockito.when(restTemplate.exchange(Matchers.anyString(), Matchers.any(HttpMethod.class),
				Matchers.any(HttpEntity.class), Mockito.eq(ProxyLoginResponse.class))).thenReturn(responseOk);
		Mockito.when(credentialSecurityFactory.getCredentialById(Matchers.anyString())).thenReturn(credential);

		ProxyLoginResponse response = proxyLoginDAO.setPinAndPassword(cc, entity);
		Assert.assertNotNull(response);
		Assert.assertNull(response.getMessage());
	}

	@Test
	public void setPinAndPasswordError() throws SQLException, DAOException {
		CredencialCliente cc = new CredencialCliente();
		CambioUsuarioEntity entity = new CambioUsuarioEntity();
		entity.setStrOldPin("330A330A330A330A330A330A330A330A");
		entity.setStrNewPin("330A330A330A330A330A330A330A330A");
		entity.setStrOldUsr("330A330A330A330A330A330A330A330A");
		entity.setStrNewUsr("330A330A330A330A330A330A330A330A");
		entity.setCliente(new Cliente());
		String responseBody = "{\"code\": \"400\", \"message\": \"10001026\", \"description\": \"No es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.||NUEVO USUARIO TRIVIAL O HISTORICO\"}";
		byte[] body = responseBody.getBytes();
		HttpClientErrorException hscex = new HttpClientErrorException(HttpStatus.BAD_REQUEST, "", body, null);

		Mockito.when(restTemplate.exchange(Matchers.anyString(), Matchers.any(HttpMethod.class),
				Matchers.any(HttpEntity.class), Mockito.eq(ProxyLoginResponse.class))).thenThrow(hscex);
		Mockito.when(credentialSecurityFactory.getCredentialById(Matchers.anyString())).thenReturn(credential);

		ProxyLoginResponse response = proxyLoginDAO.setPinAndPassword(cc, entity);
		Assert.assertNotNull(response);
		Assert.assertEquals("10001026", response.getMessage());
	}

	@Test
	public void setCredentialsOkTest() throws SQLException, DAOException {
		AltaSGIClaveInEntity altaSGIClaveIn = new AltaSGIClaveInEntity();
		altaSGIClaveIn.setClaveEncriptado("330A330A330A330A330A330A330A330A");
		altaSGIClaveIn.setUsuarioEncriptado("330A330A330A330A330A330A330A330A");
		altaSGIClaveIn.setIdSesion("1234567890");
		altaSGIClaveIn.setNup("12345678");

		Mockito.when(restTemplate.exchange(Matchers.anyString(), Matchers.any(HttpMethod.class),
				Matchers.any(HttpEntity.class), Mockito.eq(ProxyLoginResponse.class))).thenReturn(responseOk);
		Mockito.when(credentialSecurityFactory.getCredentialById(Matchers.anyString())).thenReturn(credential);

		ProxyLoginResponse response = proxyLoginDAO.setCredentials(altaSGIClaveIn);
		Assert.assertNotNull(response);
		Assert.assertNull(response.getMessage());
	}

	@Test
	public void setCredentialsErrorTest() throws SQLException, DAOException {
		AltaSGIClaveInEntity altaSGIClaveIn = new AltaSGIClaveInEntity();
		altaSGIClaveIn.setClaveEncriptado("330A330A330A330A330A330A330A330A");
		altaSGIClaveIn.setUsuarioEncriptado("330A330A330A330A330A330A330A330A");
		altaSGIClaveIn.setIdSesion("1234567890");
		altaSGIClaveIn.setNup("12345678");
		String responseBody = "{\"code\": \"400\", \"message\": \"10010044\", \"description\": \"No es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.||USUARIO REPETIDO\"}";
		byte[] body = responseBody.getBytes();
		HttpClientErrorException hscex = new HttpClientErrorException(HttpStatus.BAD_REQUEST, "", body, null);

		Mockito.when(restTemplate.exchange(Matchers.anyString(), Matchers.any(HttpMethod.class),
				Matchers.any(HttpEntity.class), Mockito.eq(ProxyLoginResponse.class))).thenThrow(hscex);
		Mockito.when(credentialSecurityFactory.getCredentialById(Matchers.anyString())).thenReturn(credential);

		ProxyLoginResponse response = proxyLoginDAO.setCredentials(altaSGIClaveIn);
		Assert.assertNotNull(response);
		Assert.assertEquals("10010044", response.getMessage());
	}
}