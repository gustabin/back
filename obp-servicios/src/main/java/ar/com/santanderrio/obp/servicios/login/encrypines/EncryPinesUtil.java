/**
 * 
 */
package ar.com.santanderrio.obp.servicios.login.encrypines;

import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.exception.RobotException;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import isban.commons.cryptography.crypto.CryptoSchema;
import isban.commons.cryptography.crypto.CryptoSchemaRegistry;
import isban.commons.cryptography.crypto.Decryptor;
import isban.commons.cryptography.crypto.DecryptorFactory;
import isban.commons.cryptography.crypto.EncryptionException;
import isban.commons.cryptography.crypto.Encryptor;
import isban.commons.cryptography.crypto.EncryptorFactory;
import isban.commons.cryptography.crypto.InvalidKeyException;

/**
 * The Class EncryPinesUtil.
 *
 * @author sergio.e.goldentair
 */
@Component
public class EncryPinesUtil implements EncryPines {
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(EncryPinesUtil.class);

    /** The credential security factory. */
    @Autowired
    private CredentialSecurityFactory credentialSecurityFactory;

    /** The ModuloPermiso BO. */
    @Autowired
    private ModuloPermisoBO moduloPermisoBO;

    /** Enc Pines 3Des ID. */
    @Value("${DB.ENCPINES_3DES.ID}")
    private String encpines3des;
    /** Enc Pines Privada ID. */
    @Value("${DB.ENCPINES_RSA_PRIV.ID}")
    private String encpinesPriv;
    /** Enc Pines Publica ID. */
    @Value("${DB.ENCPINES_RSA_PUB.ID}")
    private String encpinesPub;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.login.encrypines.EncryPines#
     * obtenerCadena3Des(java.lang.String)
     */
    @Override
    public String obtenerCadena3Des(String dato) {
        long inicio = System.currentTimeMillis();
        boolean habilitaLogicaEnc = moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.LOGICA_ENC_LOGIN)
                .tienePermisoDeVisibilidad();
        try {
            LOGGER.debug("Dato de entrada {}.", dato);
            try {
                if (habilitaLogicaEnc && StringUtils.isNotBlank(dato)) {
                    String desKey = obtenerEnc(encpines3des);
                    return encryptText(desKey, dato);
                } else {
                    LOGGER.info(
                            "No esta habilitado la encripcion de pines, el dato se propaga al backend como se recibio.");
                }
            } catch (InvalidKeyException e) {
                LOGGER.error("No es posible Encriptar la cadena recibida {} por tener una clave incorrecta.", dato);
            } catch (EncryptionException e) {
                LOGGER.error("No es posible Encriptar la cadena recibida {}.", dato);
            }
        } finally {
            LOGGER.debug("El tiempo de procesamiento para de 3des es {}ms, con permiso {}.",
                    System.currentTimeMillis() - inicio, habilitaLogicaEnc);
        }
        LOGGER.debug("Dato de salida {}.", dato);
        return dato;
    }

    /**
	 * Obtener la entrada de seguridad, si no se puede recuperar se registra en
	 * la tabla de errores y se retorna la cadena vacia.
	 *
	 * @param clavSeg
	 *            the clav seg
	 * @return the string
	 */
    private String obtenerEnc(String clavSeg) {
        try {
            Credential credential = credentialSecurityFactory.getCredentialById(clavSeg);
            return credential.getPassword();
        } catch (SQLException e) {
            LOGGER.error("No es posible obtener la entrada {} de la base de seguridad por error en la base.", clavSeg);
        } catch (NullPointerException e) {
            LOGGER.error("No es posible obtener la entrada {} de la base de seguridad por no estar el dato.", clavSeg);
        }
        LOGGER.debug("Retorna clave vacia para el valor {} consultado.", clavSeg);
        return StringUtils.EMPTY;
    }

    /**
	 * Encriptar los datos recibidos desde el front para enviar al backend.
	 *
	 * @param desKey
	 *            the des key
	 * @param cryptoMsg
	 *            the crypto msg
	 * @return the string
	 * @throws EncryptionException
	 *             the encryption exception
	 */
    private String encryptText(String desKey, String cryptoMsg) throws EncryptionException {
        Encryptor encryptor = EncryptorFactory.instanceWithHexKey(CryptoSchema.SCHEMA_3DES192_CBC, desKey);
        return encryptor.encryptText(cryptoMsg);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.login.encrypines.EncryPines#obtenerEncPub()
     */
    @Override
    public String obtenerEncPub() {
        long inicio = System.currentTimeMillis();
        boolean habilitaLogicaEnc = moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.LOGICA_ENC_LOGIN)
                .tienePermisoDeVisibilidad();
        try {
            if (habilitaLogicaEnc) {
                return obtenerEnc(encpinesPub);
            }
        } finally {
            LOGGER.debug("El tiempo de procesamiento para obtener la clave publica es {}ms, con permiso {}.",
                    System.currentTimeMillis() - inicio, habilitaLogicaEnc);
        }
        LOGGER.debug("Se retorna una cadena vacia al consultar la clave publica.");
        return StringUtils.EMPTY;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.login.encrypines.EncryPines#
     * obtenerSoloCrypto(java.lang.String)
     */
    @Override
    public String obtenerSoloCrypto(String dato) {
        long inicio = System.currentTimeMillis();
        boolean habilitaLogicaEnc = moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.LOGICA_ENC_LOGIN)
                .tienePermisoDeVisibilidad();
        try {
            if (habilitaLogicaEnc && StringUtils.isNotBlank(dato)) {
                String desKey = obtenerEnc(encpinesPriv);
                try {
                    Decryptor decryptor = DecryptorFactory.instanceWithBase64Key(
                            CryptoSchemaRegistry.getSchema(CryptoSchema.SCHEMA_HYB_RSA_AES128_CBC_NAME), desKey);
                    return decryptor.decryptText(dato);
                } catch (InvalidKeyException e) {
                    LOGGER.error("No es posible obtener una instancia para desencriptar y obtener el dato del crypto.");
                    throw new RobotException(e);
                } catch (EncryptionException e) {
                    LOGGER.error("No es posible obtener desencriptar el dato entrante de front.");
                    throw new RobotException(e);
                }
            }
        } finally {
            LOGGER.debug(
                    "El tiempo de procesamiento para obtener la trama de entrada original (aplica priv) es {}ms, con permiso {}.",
                    System.currentTimeMillis() - inicio, habilitaLogicaEnc);
        }
        LOGGER.debug("Se va operar con la trama {}", dato);
        return dato;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.login.encrypines.EncryPines#
     * obtenerLoginView(java.lang.String, java.lang.Class)
     */
    @Override
    public <T> T obtenerViewFromJson(String cryptoInput, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
            return objectMapper.readValue(cryptoInput, clazz);
        } catch (Exception e) {
            throw new RobotException(e);
        }
    }

}
