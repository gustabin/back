package ar.com.santanderrio.obp.base.security.sign.impl;

import java.io.IOException;
import java.security.Security;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.cms.CMSTypedData;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.SignerInformationStore;
import org.bouncycastle.cms.SignerInformationVerifier;
import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder;
import org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.RSAEngine;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.util.PrivateKeyFactory;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import org.bouncycastle.util.Store;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.security.keystore.KSStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreHelper;
import ar.com.santanderrio.obp.base.security.sign.Sign;

/**
 * Clase para generacion y verificacion de SIGNATURES PKCS7 en formatos DER, DER
 * + BASE64 y PEM.<br>
 *
 * Para la verificacion de firmas se utiliza la clave p�blica del CERTIFICADO,
 * independientemente que se haya especificado la misma por separado dentro del
 * archivo .PEM.
 *
 * @author DFC
 * @version 1.0
 */
@Component("sign")
public class SignImpl implements Sign {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SignImpl.class);

	/** The key store helper. */
	@Autowired
	private KeyStoreHelper keyStoreHelper;

	/**
	 * Instantiates a new sign impl.
	 */
	public SignImpl() {
		super();
	}

	/**
	 * Genera la firma DER PKCS7 (incluye el contenido firmado).
	 *
	 * @param data     the data
	 * @param keystore the keystore
	 * @return the byte[]
	 * @throws DAOException the DAO exception
	 */
	@Override
	public byte[] buildSignature(byte[] data, String keystore) throws DAOException {
		return this.buildSignature(data, keystore, Sign.FIRMA_NO_INCLUYE_CERT);
	}

	/**
	 * Genera la firma DER PKCS7 (incluye el contenido firmado).
	 * 
	 * Se modifica la imple heredada de obp was7 para que corra con BC para jdk 6.
	 *
	 * @param data        the data
	 * @param keystore    the keystore
	 * @param incluirCert the incluir cert
	 * @return the byte[]
	 * @throws DAOException the DAO exception
	 */
	@SuppressWarnings("unchecked")
	public byte[] buildSignature(byte[] data, String keystore, boolean incluirCert) throws DAOException {
		CMSTypedData content = new CMSProcessableByteArray(data);
		CMSSignedDataGenerator signGen = new CMSSignedDataGenerator();

		KSStore kSStore = keyStoreHelper.readKeyStore(keystore);
		byte[] retorno = null;

		try {
			ContentSigner sha1Signer = new JcaContentSignerBuilder("SHA1withRSA")
			        .setProvider(BouncyCastleProvider.PROVIDER_NAME).build(kSStore.getPrivateKey());
			signGen.addSignerInfoGenerator(new JcaSignerInfoGeneratorBuilder(
			        new JcaDigestCalculatorProviderBuilder().setProvider(BouncyCastleProvider.PROVIDER_NAME).build())
			                .build(sha1Signer, (X509Certificate) kSStore.getCerts().get(0)));

			if (incluirCert) {
				List<X509Certificate> certList = new ArrayList<X509Certificate>();
				certList.add((X509Certificate) kSStore.getCerts().get(0));
				Store<X509Certificate> certs = new JcaCertStore(certList);
				signGen.addCertificates(certs);
			}

			CMSSignedData signedData = signGen.generate(content, true);
			retorno = signedData.getEncoded();
		} catch (IOException e) {
			LOGGER.error("Error de entrada/salida al obtener el byte[].", e);
			throw new DAOException(e);
		} catch (CertificateEncodingException e) {
			LOGGER.error("Error al intentar encodear el certificado.", e);
			throw new DAOException(e);
		} catch (OperatorCreationException e) {
			LOGGER.error("Error de creacion del certificado.", e);
			throw new DAOException(e);
		} catch (Exception e) {
			LOGGER.error("Error general al utilizar el certificado.", e);
			throw new DAOException(e);
		}
		return retorno;
	}

	/**
	 * Genera la firma DER PKCS7 + BASE64 (incluye el contenido firmado).
	 *
	 * @param data     the data
	 * @param keystore the keystore
	 * @return the byte[]
	 * @throws DAOException the DAO exception
	 */
	public byte[] buildB64Signature(byte[] data, String keystore) throws DAOException {
		return this.buildB64Signature(data, keystore, Sign.FIRMA_NO_INCLUYE_CERT);
	}

	/**
	 * Genera la firma DER PKCS7 + BASE64 (incluye el contenido firmado).
	 *
	 * @param data        the data
	 * @param keystore    the keystore
	 * @param incluirCert the incluir cert
	 * @return the byte[]
	 * @throws DAOException the DAO exception
	 */
	public byte[] buildB64Signature(byte[] data, String keystore, boolean incluirCert) throws DAOException {
		return Base64.encode(this.buildSignature(data, keystore, incluirCert));
	}

	/**
	 * Genera la firma PEM PKCS7 (incluye el contenido firmado).
	 *
	 * @param data     the data
	 * @param keystore the keystore
	 * @return the string
	 * @throws DAOException the DAO exception
	 */
	public String buildPemSignature(byte[] data, String keystore) throws DAOException {
		return this.buildPemSignature(data, keystore, Sign.FIRMA_NO_INCLUYE_CERT);
	}

	/**
	 * Genera la firma PEM PKCS7 (incluye el contenido firmado).
	 *
	 * @param data        the data
	 * @param keystore    the keystore
	 * @param incluirCert the incluir cert
	 * @return the string
	 * @throws DAOException the DAO exception
	 */
	public String buildPemSignature(byte[] data, String keystore, boolean incluirCert) throws DAOException {
		byte[] derSignature = this.buildSignature(data, keystore, incluirCert);
		return keyStoreHelper.derToPemPKCS7(derSignature);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.security.sign.Sign#desencriptadorPKCS7Incluido(
	 * java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public String desencriptadorPKCS7Incluido(String datoEncriptado, String keystore, boolean requiereValidarCert) {
		try {
			byte[] data = new byte[(int) datoEncriptado.length()];
			data = datoEncriptado.getBytes();
			CMSSignedData sd = new CMSSignedData(Base64.decode(data));
			if (requiereValidarCert && !isValidToken(sd, datoEncriptado, keystore)) {
				LOGGER.error("El token {} entrante no es valido", datoEncriptado);
				return StringUtils.EMPTY;
			}
			CMSProcessableByteArray cpb = (CMSProcessableByteArray) sd.getSignedContent();
			return new String((byte[]) cpb.getContent());
		} catch (CMSException e) {
			LOGGER.error("No fue posible desencriptar el texto {}", datoEncriptado);
		}
		return StringUtils.EMPTY;
	}

	/**
	 * Valida que sea un token correcto respecto al keystore utilizado.
	 *
	 * @param sd             the sd
	 * @param datoEncriptado the dato encriptado
	 * @param keystore       the keystore
	 * @return true, if is valid token
	 * @throws CMSException the CMS exception
	 */
	private boolean isValidToken(CMSSignedData sd, String datoEncriptado, String keystore) throws CMSException {
		SignerInformationStore signers = sd.getSignerInfos();
		Iterator<SignerInformation> sit = signers.getSigners().iterator();
		try {
			SignerInformation signer = null;
			while (sit.hasNext()) {
				signer = (SignerInformation) sit.next();
			}
			if (signer == null) {
				LOGGER.error("No signers");
				return false;
			}
			X509Certificate cert = (X509Certificate) keyStoreHelper.readKeyStore(keystore).getCerts().get(0);
			SignerInformationVerifier siv = new JcaSimpleSignerInfoVerifierBuilder().setProvider("BC").build(cert);
			return signer.verify(siv);
		} catch (DAOException e) {
			LOGGER.error("No fue posible obtener el certificado {} para desencriptar el texto {}", keystore,
			        datoEncriptado);
		} catch (OperatorCreationException e) {
			LOGGER.error("No fue posible obtener el verificador del certificado {} para desencriptar el texto {}",
			        keystore, datoEncriptado);
		}
		return false;
	}

	@Override
	public String encryptKeyPair(String inputData, String key) {
		LOGGER.debug("Para encriptar se utilizara la key publica {}", key);
		String encryptedData = null;
		try {
 			AsymmetricKeyParameter publicKey = PublicKeyFactory.createKey(Base64.decode(key));
			AsymmetricBlockCipher e = new RSAEngine();
			e = new org.bouncycastle.crypto.encodings.PKCS1Encoding(e);
			e.init(true, publicKey);

			byte[] messageBytes = inputData.getBytes();
			byte[] hexEncodedCipher = e.processBlock(messageBytes, 0, messageBytes.length);
			encryptedData = getHexString(hexEncodedCipher);
			LOGGER.info("Valor encriptado {}\r\n", encryptedData);
		} catch (Exception e) {
			LOGGER.info("No se pudo encriptar el input utilizado {}", inputData, e);
		}
		return encryptedData;
	}

	/**
	 * Desencriptado utilizado para el caso particular de Login App. 
	 * @param input
	 * @param key
	 * @return
	 */
	@Override
	public String desencryptKeyPairForApp(String input, String key) {

		LOGGER.info("Iniciando proceso de desencriptado proveniente de la APP");
		LOGGER.info("KEY: {}", key);
		
		String desEncryptedData = null; 
	    AsymmetricKeyParameter privateKey;
		try {
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			privateKey = (AsymmetricKeyParameter) PrivateKeyFactory.createKey(Base64.decode(key));
		    AsymmetricBlockCipher blockCipher = new RSAEngine();
		    blockCipher = new org.bouncycastle.crypto.encodings.PKCS1Encoding(blockCipher);
		    blockCipher.init(false, privateKey);
		
		    byte[] messageBytes = hexStringToByteArray(input);
		    byte[] hexEncodedCipher = blockCipher.processBlock(messageBytes, 0, messageBytes.length);
		
		    desEncryptedData = new String(hexEncodedCipher, "UTF-8");
		} catch (IOException e) {
			LOGGER.info("No se pudo desencriptar el input utilizado {}", input, e);
		} catch (InvalidCipherTextException e) {
			LOGGER.info("No se pudo desencriptar el input utilizado {}", input, e);
		}
		LOGGER.info("Proceso de desencriptado proveniente de la APP finalizado.");
		return desEncryptedData;
	}
	
	 private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
	
	private static String getHexString(byte[] b) throws Exception {
		String result = StringUtils.EMPTY;
		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}
}