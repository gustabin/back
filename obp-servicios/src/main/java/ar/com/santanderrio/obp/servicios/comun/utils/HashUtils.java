/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.servicios.exception.GeneralMapper;
import ar.com.santanderrio.obp.servicios.exception.HashParametrosException;

/**
 * El objetivo de esta clase es poder armar Hash sobre Strings, Objects y
 * Collections para poder lograr la intregridad de los datos recibidos desde el
 * frontend.<br>
 * La condicion que deben cumplir lo que se desee hashear es que sea
 * Serializable.<br>
 * Los datos sensibles que el front envie al servidor en la configuracion se
 * deberan hashear para luego poder ser comparados con los recibidos para el
 * feedback y asi lograr la intregridad de los datos.
 * 
 * @author sergio.e.goldentair
 *
 */
public final class HashUtils {
	/** Logger para HashUtils. */
	private static final Logger LOGGER = LoggerFactory.getLogger(HashUtils.class);
	/** Algoritmo a utilizar MD5. */
	public static final String ALGORITMO_MD5 = "MD5";

	/** The Constant MSJ_INFO_HASH_DIFERENTES. */
	private static final String MSJ_INFO_HASH_DIFERENTES = "El hash almacenado en sesion difiere del pasado como parametro.";

	/**
	 * El constructor que no permita generar instancias de esta clase.
	 */
	private HashUtils() {
		super();
	}

	/**
	 * Generar MD5 del objeto solicitado.<br>
	 * Comportamiento Default.
	 *
	 * @param <T>
	 *            the generic type
	 * @param t
	 *            the t
	 * @return String
	 */
	public static <T> String obtenerHash(T t) {
		return obtenerHash(t, ALGORITMO_MD5);
	}

	/**
	 * Generar el hash del objeto solicitado.
	 *
	 * @param <T>
	 *            the generic type
	 * @param t
	 *            the t
	 * @param algoritmo
	 *            the algoritmo
	 * @return String
	 */
	public static <T> String obtenerHash(T t, String algoritmo) {
		byte[] bites = getBytes(t);
		return hashear(bites, algoritmo);
	}

	/**
	 * Obtener el array de bytes del argumento recibido.
	 *
	 * @param <T>
	 *            the generic type
	 * @param t
	 *            the t
	 * @return byte[]
	 */
	private static <T> byte[] getBytes(T t) {
		byte[] salida = null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(t);
			salida = bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return salida;
	}

	/**
	 * Recibe el array de bytes y el algoritmo que se requiere aplicar y
	 * devuelve el Hash o un string vacio si hubo algun error.
	 *
	 * @param arrayBites
	 *            the array bites
	 * @param algoritmo
	 *            the algoritmo
	 * @return String
	 */
	private static String hashear(byte[] arrayBites, String algoritmo) {
		try {
			MessageDigest m = MessageDigest.getInstance(algoritmo);
			m.update(arrayBites);
			byte[] digest = m.digest();
			return DatatypeConverter.printHexBinary(digest).toUpperCase(Locale.getDefault());
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("No se pudo realizar el hash requerido.", e);
		}
		return "";
	}

	/**
	 * Compara el hash pasado como parametro de sesion con el generado al
	 * momento de confirmar la operacion. En caso que sean distintos, dispara
	 * una runtime excepcion {@link HashParametrosException} que se captura como
	 * un error generico en {@link GeneralMapper}.
	 *
	 * @param hashEnSesion
	 *            the hash en sesion
	 * @param hashInput
	 *            the hash input
	 */
	public static void compareHash(String hashEnSesion, String hashInput) {
		if (!hashEnSesion.equals(hashInput)) {
			throw new HashParametrosException(MSJ_INFO_HASH_DIFERENTES);
		}
	}

}
