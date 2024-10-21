package ar.com.santanderrio.obp.base.security.sign;

import ar.com.santanderrio.obp.base.dao.DAOException;

/**
 * Interfaz para generacion y verificacion de SIGNATURES PKCS7 en formatos DER,
 * DER + BASE64 y PEM.<br>
 *
 * Para la verificacion de firmas se utiliza la clave publica del CERTIFICADO,
 * independientemente que se haya especificado la misma por separado dentro del
 * archivo .PEM.
 *
 * @author DFC
 * @version 1.0
 */
public interface Sign {

	/** The firma incluye cert. */
	boolean FIRMA_INCLUYE_CERT = true;

	/** The firma no incluye cert. */
	boolean FIRMA_NO_INCLUYE_CERT = false;

	/**
	 * Genera la firma DER PKCS7 (incluye el contenido firmado).
	 *
	 * @param data     the data
	 * @param keystore the keystore
	 * @return the byte[]
	 * @throws DAOException the DAO exception
	 */
	byte[] buildSignature(byte[] data, String keystore) throws DAOException;

	/**
	 * Genera la firma DER PKCS7 (incluye el contenido firmado).
	 *
	 * @param data        the data
	 * @param keystore    the keystore
	 * @param incluirCert the incluir cert
	 * @return the byte[]
	 * @throws DAOException the DAO exception
	 */
	byte[] buildSignature(byte[] data, String keystore, boolean incluirCert) throws DAOException;

	/**
	 * Genera la firma DER PKCS7 + BASE64 (incluye el contenido firmado).
	 *
	 * @param data     the data
	 * @param keystore the keystore
	 * @return the byte[]
	 * @throws DAOException the DAO exception
	 */
	byte[] buildB64Signature(byte[] data, String keystore) throws DAOException;

	/**
	 * Genera la firma DER PKCS7 + BASE64 (incluye el contenido firmado).
	 *
	 * @param data        the data
	 * @param keystore    the keystore
	 * @param incluirCert the incluir cert
	 * @return the byte[]
	 * @throws DAOException the DAO exception
	 */
	byte[] buildB64Signature(byte[] data, String keystore, boolean incluirCert) throws DAOException;

	/**
	 * Genera la firma PEM PKCS7 (incluye el contenido firmado).
	 *
	 * @param data     the data
	 * @param keystore the keystore
	 * @return the string
	 * @throws DAOException the DAO exception
	 */
	String buildPemSignature(byte[] data, String keystore) throws DAOException;

	/**
	 * Genera la firma PEM PKCS7 (incluye el contenido firmado).
	 *
	 * @param data        the data
	 * @param keystore    the keystore
	 * @param incluirCert the incluir cert
	 * @return the string
	 * @throws DAOException the DAO exception
	 */
	String buildPemSignature(byte[] data, String keystore, boolean incluirCert) throws DAOException;

	/**
	 * Recibe un token y retorna la cadena sin encriptar.
	 *
	 * @param datoEncriptado      the dato encriptado
	 * @param keystore            the keystore
	 * @param requiereValidarCert the requiere validar cert
	 * @return the string
	 */
	String desencriptadorPKCS7Incluido(String datoEncriptado, String keystore, boolean requiereValidarCert);

	/**
	 * Obtener el string encriptado en RSA256 a partir de un key publico.
	 * 
	 * @param inputData
	 * @param key
	 * @return
	 */
	String encryptKeyPair(String inputData, String key);

	/**
	 * Reciben un token encriptado y devuelve un key para login de la App
	 * @param input
	 * @param key
	 * @return
	 */
	String desencryptKeyPairForApp(String input, String key);
}
