/**
 *
 */
package ar.com.santanderrio.obp.base.security.keystore.impl;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import org.apache.cxf.common.util.StringUtils;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.security.keystore.KSStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreException;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class KeyStoreHelperImpl.
 *
 * @author sergio.e.goldentair
 */
@Service("keyStoreHelper")
public class KeyStoreHelperImpl implements KeyStoreHelper {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(KeyStoreHelperImpl.class);

	/**
	 * indice para la base 64.
	 */
	private static final Integer BASE_64 = 64;

	/** The Constant PKCS7_END. */
	private static final String PKCS7_END = "-----END PKCS7";

	/** The app encoding. */
	@Value("${APP.ENCODING}")
	private String appEncoding;

	/** The key store factory. */
	@Autowired
	private KeyStoreFactory keyStoreFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.base.security.keystore.KeyStoreHelper#
	 * readKeyStore(java.lang.String)
	 */
	@Override
	public KSStore readKeyStore(String jks) throws DAOException {
		KSStore kSStore = new KSStore();
		FileInputStream fis = null;
		try {
			kSStore.setKeyStore(keyStoreFactory.createKeyStore(jks));
			KeyStore ks = KeyStore.getInstance(kSStore.getKeyStore().getKeystoreType());
			fis = new FileInputStream(kSStore.getKeyStore().getKeystorePath());
			ks.load(fis, kSStore.getKeyStore().getKeystorePassword().toCharArray());
			fis.close();
			X509Certificate cer = (X509Certificate) ks.getCertificate(kSStore.getKeyStore().getKeystoreAlias());
			kSStore.getCerts().add(cer);

			// recupera certificado
			if (ks.isKeyEntry(kSStore.getKeyStore().getKeystoreAlias())) {
				// recupera clave privada
				kSStore.setPrivateKey((PrivateKey) ks.getKey(kSStore.getKeyStore().getKeystoreAlias(),
						kSStore.getKeyStore().getKeystorePassword().toCharArray()));
			}
		} catch (KeyStoreException e) {
			LOGGER.error("Error al leer el jks {}.", jks, e);
			throw new DAOException(e);
		} catch (java.security.KeyStoreException e) {
			LOGGER.error("Error al obtener el keystore {}.", jks, e);
			throw new DAOException(e);
		} catch (FileNotFoundException e) {
			LOGGER.error("Error al buscar el keystore {} en el path {}.", jks, kSStore.getKeyStore().getKeystorePath(),
					e);
			throw new DAOException(e);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("Error con el algoritmo.", e);
			throw new DAOException(e);
		} catch (CertificateException e) {
			LOGGER.error("Error con el certificado utilizado.", e);
			throw new DAOException(e);
		} catch (IOException e) {
			LOGGER.error("Error al leer/escribir el keystore.", e);
			throw new DAOException(e);
		} catch (UnrecoverableKeyException e) {
			LOGGER.error("Error al recuperar la key.", e);
			throw new DAOException(e);
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				LOGGER.error("Error al cerrar el keystore.", e);
			}
		}
		return kSStore;
	}

	/**
	 * DER PKSCS7 -> PEM PKCS7.
	 *
	 * @param derObj
	 *            the der obj
	 * @return the string
	 * @throws DAOException
	 *             the DAO exception
	 */
	public String derToPemPKCS7(byte[] derObj) throws DAOException {
		// Codificaci�n a base 64
		byte[] bytes = Base64.encode(derObj);

		// ByteArrayOutputStream para construir el string de salida
		ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length);

		try {
			// Encabazado PEM para el PKCS7
			baos.write("-----BEGIN PKCS7-----\n".getBytes(appEncoding));

			// Cuerpo del PKCS7
			int i = 0;
			int iterMax = (bytes.length - bytes.length % BASE_64) / BASE_64;

			for (int j = 0; j < iterMax; j++, i += BASE_64) {
				baos.write(bytes, i, BASE_64);
				baos.write("\n".getBytes(appEncoding));
			}

			if (i < bytes.length) {
				baos.write(bytes, i, (bytes.length - i));
				baos.write("\n".getBytes(appEncoding));
			}

			// Encabazado PEM para el PKCS7
			baos.write("-----END PKCS7-----\n".getBytes(appEncoding));
			return new String(baos.toByteArray(), appEncoding);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Error con el encoding {} seleccionado para obtener el certificado derToPemPKCS7.",
					appEncoding, e);
			throw new DAOException(e);
		} catch (IOException e) {
			LOGGER.error("Error de lectura/escritura en derToPemPKCS7.", appEncoding, e);
			throw new DAOException(e);
		}
	}

	/**
	 * PEM PKSCS7 -> DER PKCS7.
	 *
	 * @param pemObj
	 *            the pem obj
	 * @return the byte[]
	 * @throws DAOException
	 *             the DAO exception
	 */
	public byte[] pemToDerPKCS7(String pemObj) throws DAOException {
		// Verifica que pemSignature no sea nulo
		if (pemObj == null) {
			LOGGER.info("lanzo DAOExcpetion ya que pemSignature es nulo.");
			throw new DAOException("ERROR formato PEM: sin informaci�n [null]");
		}

		// BufferedReader para el string de entrada
		BufferedReader sbr = new BufferedReader(new StringReader(pemObj));

		// Verificaci�n header PEM para PKCS7
		try {
			String nextSbr = sbr.readLine();
			if (StringUtils.isEmpty(nextSbr) || !nextSbr.startsWith("-----BEGIN PKCS7")) {
				LOGGER.info("lanzo DAOExcpetion ya que el header PEM para el PKCS7 no esta disponible.");
				throw new DAOException("ERROR formato PEM: Header no v�lido");
			}
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			String auxStr = sbr.readLine();
			while ((auxStr != null) && (!auxStr.startsWith(PKCS7_END))) {
				bos.write(Base64.decode(auxStr.trim().getBytes(appEncoding)));
			}
			// Verificaci�n de existencia del pie PEM para el PKCS7
			if (auxStr == null) {
				LOGGER.info("lanzo DAOExcpetion ya que el pie PEM para el PKCS7 no esta disponible.");
				throw new DAOException("ERROR formato PEM: Footer no encontrado");
			}
			return bos.toByteArray();
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Error con el encoding {} seleccionado en pemToDerPKCS7.", appEncoding, e);
			throw new DAOException(e);
		} catch (IOException e) {
			LOGGER.error("Error al recorrer el buffer en pemToDerPKCS7.", e);
			throw new DAOException(e);
		}
	}

}
