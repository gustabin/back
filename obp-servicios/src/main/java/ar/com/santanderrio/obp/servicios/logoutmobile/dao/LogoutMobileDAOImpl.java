/**
 * 
 */
package ar.com.santanderrio.obp.servicios.logoutmobile.dao;

import java.io.IOException;
import java.sql.SQLException;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.logoutmobile.entities.LogoutDatosInEntity;
import ar.com.santanderrio.obp.servicios.logoutmobile.entities.LogoutMobileInEntity;
import ar.com.santanderrio.obp.servicios.logoutmobile.entities.LogoutMobileOutEntity;

/**
 * @author sergio.e.goldentair
 *
 */
@Repository("logoutMobileDAO")
public class LogoutMobileDAOImpl implements LogoutMobileDAO {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(LogoutMobileDAOImpl.class);

	/** The Constant NOMBRE_WS. */
	private static final String LOGOUT_WS = "LOGOUT.MOBILE";

	/** The Constant CANAL mobp. */
	private static final String CANAL_MOBP = "04";

	/** The rest web client. */
	@Autowired
	private RestWebClient restWebClient;

	/** The sign. */
	@Autowired
	private Sign sign;

	/** The credential security factory. */
	@Autowired
	private CredentialSecurityFactory credentialSecurityFactory;

	@Value("${LOGOUT.MOBILE.USER:TBANCO}")
	private String tbancoUser;

	@Value("${LOGOUT.MOBILE.PSW:ATBancoC}")
	private String tbancoPsw;

	/** public key rsa256. */
	@Value("${DB.LOGOUT.MOBILE.ID}")
	private String logoutMobilePubKey;

	@Override
	public LogoutMobileOutEntity logoutMobile(String datoEncriptado) throws DAOException {
		try {
			LogoutMobileInEntity reqLogoutMobile = new LogoutMobileInEntity(datoEncriptado);
			WebClient service = restWebClient.obtenerClienteRest(LOGOUT_WS);
			service.accept(MediaType.APPLICATION_JSON);
			service.type(MediaType.APPLICATION_JSON + ";charset=UTF-8").accept(MediaType.APPLICATION_JSON);
			service.path("/logoutExterno");
			return service.post(reqLogoutMobile, LogoutMobileOutEntity.class);
		} catch (DAOException e) {
			String errorMsg = "Error al generar el cliente rest para notificar cierre de session app mobile";
			LOGGER.error(errorMsg, e);
			throw new DAOException(errorMsg);
		} catch (Exception e) {
			LOGGER.error("Error al solicitar cierre de sesion app mobile no permite continuar", e);
			throw new DAOException(TipoError.ERROR_GENERICO.getDescripcion());
		}
	}

	@Override
	public String getDatoEncriptado(Cliente cliente) throws DAOException {
		LogoutDatosInEntity logoutDatos = new LogoutDatosInEntity(CANAL_MOBP, cliente.getNup(), tbancoUser, tbancoPsw);
		LOGGER.info("Los datos a enviar para cierre de session mobile son: {}", logoutDatos);
		ObjectMapper mapper = new ObjectMapper();
		try {
			String dato = mapper.writeValueAsString(logoutDatos);
			String key = obtenerKey();
			return sign.encryptKeyPair(dato, key);
		} catch (JsonGenerationException e) {
			String errorMsg = "Error al generar json con datos para cierre session app mobile";
			LOGGER.error(errorMsg, e);
			throw new DAOException(errorMsg);
		} catch (JsonMappingException e) {
			String errorMsgMapping = "Error al mapear json con datos para cierre session app mobile";
			LOGGER.error(errorMsgMapping, e);
			throw new DAOException(errorMsgMapping);
		} catch (IOException e) {
			String errorMsgIO = "Error al mapear json con datos para cierre session app mobile";
			LOGGER.error(errorMsgIO, e);
			throw new DAOException(errorMsgIO);
		}
	}

	/**
	 * Obtener la clave con la cual se encriptara RSA256.
	 * 
	 * @return
	 * @throws DAOException
	 */
	private String obtenerKey() throws DAOException {
		try {
			Credential credential = credentialSecurityFactory.getCredentialById(logoutMobilePubKey);
			return credential.getPassword();
		} catch (SQLException e) {
			String errorMsgSQL = "No es posible obtener la entrada de la base de seguridad por error en la base.";
			LOGGER.error(errorMsgSQL, e);
			throw new DAOException(errorMsgSQL);
		} catch (NullPointerException e) {
			String errorMsgNull = "No es posible obtener la entrada de la base de seguridad por no estar el dato.";
			LOGGER.error(errorMsgNull, e);
			throw new DAOException(errorMsgNull);
		}
	}
}
