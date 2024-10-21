/**
 * 
 */
package ar.com.santanderrio.obp.servicios.marcaviajero.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.ws.security.wss4j.WSS4JStaxInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JStaxOutInterceptor;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract;
import ar.com.santanderrio.obp.base.webservice.interceptor.BaseLoggingInterceptor;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.MarcaViajeroService;
import ar.com.santanderrio.obp.servicios.prisma.helper.CryptoHelperDTO;
import ar.com.santanderrio.obp.servicios.prisma.helper.PWCallback;

/**
 * Gestionar todo lo relativo a la conexion del ws a nivel interceptores de cxf.
 * 
 * @author sergio.e.goldentair  
 *
 */
@Component 
public class GestionarMarcaViajero extends GestionarWSAbstract<MarcaViajeroService> {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(GestionarMarcaViajero.class);

	/** The Constant CRYPTO_MERLIN. */
	private static final String CRYPTO_MERLIN = "org.apache.wss4j.common.crypto.Merlin";

	/** The Constant CRYPTO_MERLIN_FILE. */
	private static final String CRYPTO_MERLIN_FILE = "org.apache.wss4j.crypto.merlin.keystore.file";

	/** The Constant CRYPTO_MERLIN_KEYSTORE_ALIAS. */
	private static final String CRYPTO_MERLIN_KEYSTORE_ALIAS = "org.apache.wss4j.crypto.merlin.keystore.alias";

	/** The Constant CRYPTO_MERLIN_KEYSTORE_PSWD. */
	private static final String CRYPTO_MERLIN_KEYSTORE_PSWD = "org.apache.wss4j.crypto.merlin.keystore.password";

	/** The Constant CRYPTO_MERLIN_KEYSTORE_TYPE. */
	private static final String CRYPTO_MERLIN_KEYSTORE_TYPE = "org.apache.wss4j.crypto.merlin.keystore.type";

	/** The Constant CRYPTO_PROVIDER. */
	private static final String CRYPTO_PROVIDER = "org.apache.wss4j.crypto.provider";

	/** The Constant CRYPTO_PROPS. */
	private static final String CRYPTO_PROPS = "crypto_props";

	/** The Constant CRYPTO_PROPS_DEC. */
	private static final String CRYPTO_PROPS_DEC = "crypto_props_dec";

	/** The Constant CRYPTO_PROPS_ENC. */
	private static final String CRYPTO_PROPS_ENC = "crypto_props_enc";

	/** The Constant CRYPTO_SIGN_PROPS. */
	private static final String CRYPTO_SIGN_PROPS = "crypto_sign_props";

	/** The env. */
	@Autowired
	private Environment env;

	/** The credential security factory. */
	@Autowired
	private CredentialSecurityFactory credentialSecurityFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract#
	 * asignarInterceptors(org.apache.cxf.endpoint.Client)
	 */
	@Override
	protected void asignarInterceptors(Client cliente) {
		Credential credential;
		CryptoHelperDTO cryptoHelperDTO = new CryptoHelperDTO();
		cryptoHelperDTO.setAliasIdSeguridad(env.getProperty("KEYSTORE.MARCAVIAJERO.IDSEGURIDAD"));
		cryptoHelperDTO.setKeyStorePath(env.getProperty("KEYSTORE.MARCAVIAJERO.PATH"));
		cryptoHelperDTO.setKeyStoreType(env.getProperty("KEYSTORE.MARCAVIAJERO.TYPE"));
		cryptoHelperDTO.setPrismaPubKeyAlias(env.getProperty("MARCAVIAJEROCBU.ENCUSER"));
		cryptoHelperDTO.setTtlTimestamp(env.getProperty("MARCAVIAJEROCBU.TTL.TIMESTAMP"));

		try {
			deshabilitarBSPCompliant(cliente);
			credential = credentialSecurityFactory.getCredentialById(cryptoHelperDTO.getAliasIdSeguridad());
			String keystoreAlias = credential.getUsuario();
			String keystorePassword = credential.getPassword();

			cliente.getOutInterceptors().clear();
			BaseLoggingInterceptor baseOutLogging = new BaseLoggingInterceptor(Phase.PRE_STREAM);

			baseOutLogging.addBefore(WSS4JStaxOutInterceptor.class.getName());
			cliente.getOutInterceptors().add(baseOutLogging);
			Map<String, Object> outProps = getOutProps(keystoreAlias, keystorePassword, cryptoHelperDTO);
			cliente.getOutInterceptors().add(new WSS4JStaxOutInterceptor(outProps));

			cliente.getInInterceptors().clear();
			BaseLoggingInterceptor baseInLogging = new BaseLoggingInterceptor(Phase.RECEIVE);
			baseInLogging.addAfter(WSS4JStaxInInterceptor.class.getName());
			cliente.getInInterceptors().add(baseInLogging);
			Map<String, Object> inProps = getInProps(keystoreAlias, keystorePassword, cryptoHelperDTO);
			cliente.getInInterceptors().add(new WSS4JStaxInInterceptor(inProps));

		} catch (SQLException e) {
			LOGGER.error("Error al obtener info del jks para el id de seguridad {}.",
					cryptoHelperDTO.getAliasIdSeguridad(), e);
		}
	}

	/**
	 * Deshabilitar BSP compliant.
	 *
	 * @param cliente
	 *            the cliente
	 */
	private void deshabilitarBSPCompliant(Client cliente) {
		LOGGER.info("Requiere deshabilitar las validaciones BSP.");
		cliente.getEndpoint().getEndpointInfo().setProperty("ws-security.is-bsp-compliant", Boolean.FALSE.toString());
	}

	/**
	 * Obtiene propiedades para configurar el interceptor de entrada.
	 *
	 * @param privKeyAlias
	 *            Alias clave privada
	 * @param keyPassword
	 *            Password clave privada
	 * @param cryptoHelperDTO
	 *            the crypto helper DTO
	 * @return Map conteniendo las propiedades a cargar
	 */
	private Map<String, Object> getInProps(String privKeyAlias, String keyPassword, CryptoHelperDTO cryptoHelperDTO) {
		Properties cryptoDecSignProperties = new Properties();
		Properties cryptoPropertiesDec = new Properties();
		cryptoDecSignProperties.put(CRYPTO_MERLIN_KEYSTORE_ALIAS, cryptoHelperDTO.getPrismaPubKeyAlias());
		cryptoDecSignProperties.put(CRYPTO_MERLIN_KEYSTORE_PSWD, keyPassword);
		cryptoDecSignProperties.put(CRYPTO_MERLIN_FILE, cryptoHelperDTO.getKeyStorePath());
		cryptoDecSignProperties.put(CRYPTO_MERLIN_KEYSTORE_TYPE, cryptoHelperDTO.getKeyStoreType());
		cryptoDecSignProperties.put(CRYPTO_PROVIDER, CRYPTO_MERLIN);
		cryptoPropertiesDec.put(CRYPTO_MERLIN_KEYSTORE_ALIAS, privKeyAlias);
		cryptoPropertiesDec.put(CRYPTO_MERLIN_KEYSTORE_PSWD, keyPassword);
		cryptoPropertiesDec.put(CRYPTO_MERLIN_FILE, cryptoHelperDTO.getKeyStorePath());
		cryptoPropertiesDec.put(CRYPTO_MERLIN_KEYSTORE_TYPE, cryptoHelperDTO.getKeyStoreType());
		cryptoPropertiesDec.put(CRYPTO_PROVIDER, CRYPTO_MERLIN);
		// Props para input interceptor
		Map<String, Object> inProps = new HashMap<String, Object>();
		inProps.put(CRYPTO_SIGN_PROPS, cryptoDecSignProperties);
		// Sign (privkey receiver)
		inProps.put(WSHandlerConstants.USER, privKeyAlias);
		inProps.put(WSHandlerConstants.ALLOW_RSA15_KEY_TRANSPORT_ALGORITHM, "true");
		inProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, PWCallback.class.getName());
		inProps.put(WSHandlerConstants.SIG_PROP_REF_ID, CRYPTO_SIGN_PROPS);
		inProps.put(WSHandlerConstants.SIGNATURE_PARTS,
				"{Element}{http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd}Timestamp;{Element}{http://schemas.xmlsoap.org/soap/envelope/}Body");
		inProps.put(WSHandlerConstants.ENABLE_SIGNATURE_CONFIRMATION, "false");
		inProps.put(WSHandlerConstants.SIG_KEY_ID, "DirectReference");
		inProps.put(WSHandlerConstants.SIG_ALGO, "http://www.w3.org/2000/09/xmldsig#rsa-sha1");
		// Decr (pubkey sender)
		inProps.put(CRYPTO_PROPS_DEC, cryptoPropertiesDec);
		inProps.put(WSHandlerConstants.DEC_PROP_REF_ID, CRYPTO_PROPS_DEC);
		inProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, PWCallback.class.getName());
		inProps.put(WSHandlerConstants.ENCRYPTION_USER, cryptoHelperDTO.getPrismaPubKeyAlias());
		inProps.put(WSHandlerConstants.ACTION,
				WSHandlerConstants.TIMESTAMP + " " + WSHandlerConstants.SIGNATURE + " " + WSHandlerConstants.ENCRYPT);
		return inProps;
	}

	/**
	 * Obtiene propiedades para configurar el interceptor de salida.
	 *
	 * @param privKeyAlias
	 *            Alias clave privada
	 * @param keyPassword
	 *            Password clave privada
	 * @param cryptoHelperDTO
	 *            the crypto helper DTO
	 * @return Map conteniendo las propiedades a cargar
	 */
	private Map<String, Object> getOutProps(String privKeyAlias, String keyPassword, CryptoHelperDTO cryptoHelperDTO) {
		Properties cryptoProperties = new Properties();
		Properties cryptoPropertiesEnc = new Properties();
		cryptoProperties.put(CRYPTO_MERLIN_KEYSTORE_ALIAS, privKeyAlias);
		cryptoProperties.put(CRYPTO_MERLIN_KEYSTORE_PSWD, keyPassword);
		cryptoProperties.put(CRYPTO_MERLIN_FILE, cryptoHelperDTO.getKeyStorePath());
		cryptoProperties.put(CRYPTO_MERLIN_KEYSTORE_TYPE, cryptoHelperDTO.getKeyStoreType());
		cryptoProperties.put(CRYPTO_PROVIDER, CRYPTO_MERLIN);
		cryptoPropertiesEnc.put(CRYPTO_MERLIN_KEYSTORE_ALIAS, cryptoHelperDTO.getPrismaPubKeyAlias());
		cryptoPropertiesEnc.put(CRYPTO_MERLIN_KEYSTORE_PSWD, keyPassword);
		cryptoPropertiesEnc.put(CRYPTO_MERLIN_FILE, cryptoHelperDTO.getKeyStorePath());
		cryptoPropertiesEnc.put(CRYPTO_MERLIN_KEYSTORE_TYPE, cryptoHelperDTO.getKeyStoreType());
		cryptoPropertiesEnc.put(CRYPTO_PROVIDER, CRYPTO_MERLIN);
		// Props para out interceptor
		Map<String, Object> outProps = new HashMap<String, Object>();
		outProps.put(CRYPTO_PROPS, cryptoProperties);
		outProps.put(CRYPTO_PROPS_ENC, cryptoPropertiesEnc);
		outProps.put(WSHandlerConstants.ACTION,
				WSHandlerConstants.TIMESTAMP + " " + WSHandlerConstants.SIGNATURE + " " + WSHandlerConstants.ENCRYPT);
		// Sign (privkey sender)
		outProps.put(WSHandlerConstants.USER, privKeyAlias);
		outProps.put(WSHandlerConstants.ALLOW_RSA15_KEY_TRANSPORT_ALGORITHM, "true");
		outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, PWCallback.class.getName());
		outProps.put(WSHandlerConstants.SIG_PROP_REF_ID, CRYPTO_PROPS);
		outProps.put(WSHandlerConstants.SIGNATURE_PARTS,
				"{Element}{http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd}Timestamp;{Element}{http://schemas.xmlsoap.org/soap/envelope/}Body");
		outProps.put(WSHandlerConstants.ENABLE_SIGNATURE_CONFIRMATION, "false");
		outProps.put(WSHandlerConstants.TTL_TIMESTAMP, cryptoHelperDTO.getTtlTimestamp());
		outProps.put(WSHandlerConstants.SIG_KEY_ID, "DirectReference");
		outProps.put(WSHandlerConstants.SIG_ALGO, "http://www.w3.org/2000/09/xmldsig#rsa-sha1");
		// Encr (pubkey receiver)
		outProps.put(WSHandlerConstants.ENC_PROP_REF_ID, CRYPTO_PROPS_ENC);
		outProps.put(WSHandlerConstants.ENCRYPTION_USER, cryptoHelperDTO.getPrismaPubKeyAlias());
		outProps.put(WSHandlerConstants.ENC_SYM_ALGO, "http://www.w3.org/2001/04/xmlenc#aes128-cbc");
		outProps.put(WSHandlerConstants.ENC_KEY_TRANSPORT, "http://www.w3.org/2001/04/xmlenc#rsa-oaep-mgf1p");
		outProps.put(WSHandlerConstants.ENC_KEY_ID, "IssuerSerial");
		outProps.put(WSHandlerConstants.ENCRYPTION_PARTS, "{Content}{http://schemas.xmlsoap.org/soap/envelope/}Body");
		return outProps;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract#getCodigoWS()
	 */
	@Override
	public String getCodigoWS() {
		return "MARCAVIAJERO";
	}

}
