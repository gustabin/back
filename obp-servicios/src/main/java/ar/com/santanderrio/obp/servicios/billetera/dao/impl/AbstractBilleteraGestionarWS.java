/**
 *
 */
package ar.com.santanderrio.obp.servicios.billetera.dao.impl;

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
import org.springframework.beans.factory.annotation.Value;

import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract;
import ar.com.santanderrio.obp.base.webservice.interceptor.BaseLoggingInterceptor;
import ar.com.santanderrio.obp.servicios.prisma.helper.PWCallback;

/**
 * The Class AbstractBilleteraGestionarWS.
 *
 * @param <T>
 *            the generic type
 */
public abstract class AbstractBilleteraGestionarWS<T> extends GestionarWSAbstract<T> {

    /** The Constant CRYPTO_MERLIN. */
    private static final String CRYPTO_MERLIN = "org.apache.wss4j.common.crypto.Merlin";

    /** The Constant CRYPTO_MERLIN_FILE. */
    private static final String CRYPTO_MERLIN_FILE = "org.apache.wss4j.crypto.merlin.keystore.file";

    /** The Constant CRYPTO_MERLIN_KEYSTORE_ALIAS. */
    private static final String CRYPTO_MERLIN_KEYSTORE_ALIAS = "org.apache.wss4j.crypto.merlin.keystore.alias";

    /** The Constant CRYPTO_MERLIN_KEYSTORE_PASSWORD. */
    private static final String CRYPTO_MERLIN_KEYSTORE_PASS = "org.apache.wss4j.crypto.merlin.keystore.password";

    /** The Constant CRYPTO_MERLIN_KEYSTORE_TYPE. */
    private static final String CRYPTO_MERLIN_KEYSTORE_TYPE = "org.apache.wss4j.crypto.merlin.keystore.type";

    /** The Constant CRYPTO_PROPS. */
    private static final String CRYPTO_PROPS = "crypto_props";

    /** The Constant CRYPTO_PROPS_DEC. */
    private static final String CRYPTO_PROPS_DEC = "crypto_props_dec";

    /** The Constant CRYPTO_PROPS_ENC. */
    private static final String CRYPTO_PROPS_ENC = "crypto_props_enc";

    /** The Constant CRYPTO_PROVIDER. */
    private static final String CRYPTO_PROVIDER = "org.apache.wss4j.crypto.provider";

    /** The Constant CRYPTO_SIGN_PROPS. */
    private static final String CRYPTO_SIGN_PROPS = "crypto_sign_props";

    /** The Constant FALSE. */
    private static final String FALSE = "false";

    /** The Constant X509_KEY_IDENTIFIER. */
    private static final String X509_KEY_IDENTIFIER = "X509KeyIdentifier";

    /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractBilleteraGestionarWS.class);

    /** Billetera ID SEGURIDAD. */
    @Value("${KEYSTORE.BILLETERA.IDSEGURIDAD}")
    private String billeteraIdSeguridad;

    /** The credential security factory. */
    @Autowired
    private CredentialSecurityFactory credentialSecurityFactory;

    /** Keystore Billetera path. */
    @Value("${KEYSTORE.BILLETERA.PATH}")
    private String keyStorePath;

    /** Keystore Billetera type. */
    @Value("${KEYSTORE.BILLETERA.TYPE}")
    private String keyStoreType;

    /** Prisma Key Billetera. */
    @Value("${BILLETERA.ENC_USER}")
    private String prismaPubKeyAlias;

    /** Billetera ttl timestamp. */
    @Value("${BILLETERA.TTL_TIMESTAMP}")
    private String ttlTimestamp;

    /**
     * Falla en InboundWSSecurityContextImpl.handleBSPRule al validar BSP
     * compliant la regla R5402 del bloque firmado ds:Signature.
     *
     * <pre>
     * <code>
     *  <ds:KeyInfo Id="KeyId-A301BDD9CFA548072E14933875497443605">
     *     <wsse:SecurityTokenReference xmlns:wsu=
    "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd" wsu:Id
    ="STRId-A301BDD9CFA548072E14933875497443606">
     *       <wsse:KeyIdentifier EncodingType=
    "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary" ValueType
    =
    "http://docs.oasis-open.org/wss/oasis-wss-soap-message-security-1.1#ThumbprintSHA1">Yba8L1NMauFUdRpMHm6zn+0HA3A=</wsse:KeyIdentifier>
     *     </wsse:SecurityTokenReference>
     *  </ds:KeyInfo>
     * </code>
     * </pre>
     *
     * @param cliente
     *            the cliente
     */
    private void deshabilitarBSPCompliant(Client cliente) {
        LOGGER.info("Requiere deshabilitar las validaciones BSP.");
        cliente.getEndpoint().getEndpointInfo().setProperty("ws-security.is-bsp-compliant", FALSE);
    }

    /**
     * Obtiene propiedades para configurar el interceptor de entrada.
     *
     * @param privKeyAlias
     *            Alias clave privada
     * @param keyPassword
     *            Password clave privada
     * @return Map conteniendo las propiedades a cargar
     */
    private Map<String, Object> getInProps(String privKeyAlias, String keyPassword) {
        Properties cryptoDecSignProperties = new Properties();
        Properties cryptoPropertiesDec = new Properties();
        cryptoDecSignProperties.put(CRYPTO_MERLIN_KEYSTORE_ALIAS, prismaPubKeyAlias);
        cryptoDecSignProperties.put(CRYPTO_MERLIN_KEYSTORE_PASS, keyPassword);
        cryptoDecSignProperties.put(CRYPTO_MERLIN_FILE, keyStorePath);
        cryptoDecSignProperties.put(CRYPTO_MERLIN_KEYSTORE_TYPE, keyStoreType);
        cryptoDecSignProperties.put(CRYPTO_PROVIDER, CRYPTO_MERLIN);
        cryptoPropertiesDec.put(CRYPTO_MERLIN_KEYSTORE_ALIAS, privKeyAlias);
        cryptoPropertiesDec.put(CRYPTO_MERLIN_KEYSTORE_PASS, keyPassword);
        cryptoPropertiesDec.put(CRYPTO_MERLIN_FILE, keyStorePath);
        cryptoPropertiesDec.put(CRYPTO_MERLIN_KEYSTORE_TYPE, keyStoreType);
        cryptoPropertiesDec.put(CRYPTO_PROVIDER, CRYPTO_MERLIN);

        Map<String, Object> inProps = new HashMap<String, Object>();
        inProps.put(CRYPTO_SIGN_PROPS, cryptoDecSignProperties);

        inProps.put(WSHandlerConstants.USER, privKeyAlias);

        inProps.put(WSHandlerConstants.ALLOW_RSA15_KEY_TRANSPORT_ALGORITHM, "true");
        inProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, PWCallback.class.getName());
        inProps.put(WSHandlerConstants.SIG_PROP_REF_ID, CRYPTO_SIGN_PROPS);
        inProps.put(WSHandlerConstants.SIGNATURE_PARTS,
                "{Content}{http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd}Timestamp;{Content}{http://schemas.xmlsoap.org/soap/envelope/}Body");
        inProps.put(WSHandlerConstants.ENABLE_SIGNATURE_CONFIRMATION, FALSE);
        inProps.put(WSHandlerConstants.SIG_KEY_ID, X509_KEY_IDENTIFIER);
        inProps.put(WSHandlerConstants.SIG_ALGO, "http://www.w3.org/2000/09/xmldsig#rsa-sha1");

        inProps.put(CRYPTO_PROPS_DEC, cryptoPropertiesDec);
        inProps.put(WSHandlerConstants.DEC_PROP_REF_ID, CRYPTO_PROPS_DEC);
        inProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, PWCallback.class.getName());
        inProps.put(WSHandlerConstants.ENCRYPTION_USER, prismaPubKeyAlias);
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
     * @return Map conteniendo las propiedades a cargar
     */
    private Map<String, Object> getOutProps(String privKeyAlias, String keyPassword) {
        Properties cryptoProperties = new Properties();
        Properties cryptoPropertiesEnc = new Properties();
        cryptoProperties.put(CRYPTO_MERLIN_KEYSTORE_ALIAS, privKeyAlias);
        cryptoProperties.put(CRYPTO_MERLIN_KEYSTORE_PASS, keyPassword);
        cryptoProperties.put(CRYPTO_MERLIN_FILE, keyStorePath);
        cryptoProperties.put(CRYPTO_MERLIN_KEYSTORE_TYPE, keyStoreType);
        cryptoProperties.put(CRYPTO_PROVIDER, CRYPTO_MERLIN);
        cryptoPropertiesEnc.put(CRYPTO_MERLIN_KEYSTORE_ALIAS, prismaPubKeyAlias);
        cryptoPropertiesEnc.put(CRYPTO_MERLIN_KEYSTORE_PASS, keyPassword);
        cryptoPropertiesEnc.put(CRYPTO_MERLIN_FILE, keyStorePath);
        cryptoPropertiesEnc.put(CRYPTO_MERLIN_KEYSTORE_TYPE, keyStoreType);
        cryptoPropertiesEnc.put(CRYPTO_PROVIDER, CRYPTO_MERLIN);

        Map<String, Object> outProps = new HashMap<String, Object>();
        outProps.put(CRYPTO_PROPS, cryptoProperties);
        outProps.put(CRYPTO_PROPS_ENC, cryptoPropertiesEnc);
        outProps.put(WSHandlerConstants.ACTION,
                WSHandlerConstants.TIMESTAMP + " " + WSHandlerConstants.SIGNATURE + " " + WSHandlerConstants.ENCRYPT);

        outProps.put(WSHandlerConstants.USER, privKeyAlias);
        outProps.put(WSHandlerConstants.ALLOW_RSA15_KEY_TRANSPORT_ALGORITHM, "true");
        outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, PWCallback.class.getName());
        outProps.put(WSHandlerConstants.SIG_PROP_REF_ID, CRYPTO_PROPS);
        outProps.put(WSHandlerConstants.SIGNATURE_PARTS,
                "{Content}{http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd}Timestamp;{Content}{http://schemas.xmlsoap.org/soap/envelope/}Body");
        outProps.put(WSHandlerConstants.ENABLE_SIGNATURE_CONFIRMATION, FALSE);
        outProps.put(WSHandlerConstants.TTL_TIMESTAMP, ttlTimestamp);
        outProps.put(WSHandlerConstants.SIG_KEY_ID, X509_KEY_IDENTIFIER);
        outProps.put(WSHandlerConstants.SIG_ALGO, "http://www.w3.org/2000/09/xmldsig#rsa-sha1");

        outProps.put(WSHandlerConstants.ENC_PROP_REF_ID, CRYPTO_PROPS_ENC);
        outProps.put(WSHandlerConstants.ENCRYPTION_USER, prismaPubKeyAlias);
        outProps.put(WSHandlerConstants.ENC_SYM_ALGO, "http://www.w3.org/2001/04/xmlenc#aes128-cbc");
        outProps.put(WSHandlerConstants.ENC_KEY_TRANSPORT, "http://www.w3.org/2001/04/xmlenc#rsa-oaep-mgf1p");
        outProps.put(WSHandlerConstants.ENC_KEY_ID, X509_KEY_IDENTIFIER);
        outProps.put(WSHandlerConstants.ENCRYPTION_PARTS, "{Content}{http://schemas.xmlsoap.org/soap/envelope/}Body");
        return outProps;
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract#
     * asignarInterceptors(org.apache.cxf.endpoint.Client)
     */
    @Override
    protected void asignarInterceptors(Client cliente) {
        deshabilitarBSPCompliant(cliente);
        try {
            Credential credential = credentialSecurityFactory.getCredentialById(billeteraIdSeguridad);
            String keystoreAlias = credential.getUsuario();
            String keystorePassword = credential.getPassword();

            cliente.getOutInterceptors().clear();
            BaseLoggingInterceptor baseOutLogging = new BaseLoggingInterceptor(Phase.PRE_STREAM);
            // OriginalWss4jInterceptor baseOutLogging = new
            // OriginalWss4jInterceptor(Phase.POST_PROTOCOL);
            baseOutLogging.addBefore(WSS4JStaxOutInterceptor.class.getName());
            cliente.getOutInterceptors().add(baseOutLogging);
            Map<String, Object> outProps = getOutProps(keystoreAlias, keystorePassword);
            cliente.getOutInterceptors().add(new WSS4JStaxOutInterceptor(outProps));
            // cliente.getOutInterceptors().add(new SAAJOutInterceptor());

            cliente.getInInterceptors().clear();
            BaseLoggingInterceptor baseInLogging = new BaseLoggingInterceptor(Phase.RECEIVE);
            // OriginalWss4jInterceptor baseInLogging = new
            // OriginalWss4jInterceptor(Phase.POST_PROTOCOL);
            baseInLogging.addAfter(WSS4JStaxInInterceptor.class.getName());
            cliente.getInInterceptors().add(baseInLogging);
            // cliente.getInInterceptors().add(new SaajInWss4jInterceptor());
            Map<String, Object> inProps = getInProps(keystoreAlias, keystorePassword);
            cliente.getInInterceptors().add(new WSS4JStaxInInterceptor(inProps));
        } catch (SQLException e) {
            LOGGER.error("Error al obtener info del jks para el id de seguridad {}: {}", billeteraIdSeguridad, e);
        }

    }

}
