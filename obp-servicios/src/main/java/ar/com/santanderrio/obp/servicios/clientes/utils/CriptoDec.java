package ar.com.santanderrio.obp.servicios.clientes.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class CriptoDec.
 */
public class CriptoDec {

	/** The Constant MIDDLE_ANNOYING_CHARACTER. */
	private static final char MIDDLE_ANNOYING_CHARACTER = 'A';

	/** The Constant N. */
	private static final int N = 391;

	/** The Constant END_CHAR. */
	private static final int END_CHAR = 255;

	/** The Constant START_CHAR. */
	private static final int START_CHAR = 32;

	/** The decrypt map. */
	private static Map<Integer, Integer> decryptMap = new HashMap<Integer, Integer>();

	static {
		loadDecryptMap();
	}

	/**
	 * Decrypt.
	 *
	 * @param encstring the encstring
	 * @return the string
	 */
	public static String decrypt(final String encstring) {
		String tcstring = "";
		String[] splitted = encstring.split(String.valueOf(MIDDLE_ANNOYING_CHARACTER));
		for (int i = 0; i < splitted.length; i++) {
			char ch = (char) decryptMap.get(Integer.valueOf(splitted[i])).intValue();
			tcstring += ch;
		}
		return tcstring;
	}

	/**
	 * Load decrypt map.
	 */
	public static void loadDecryptMap() {
		for (int i = START_CHAR; i <= END_CHAR; i++) {
			int key = (i * ((i * i) % N)) % N;
			decryptMap.put(Integer.valueOf(key), Integer.valueOf(i));
		}
	}

}
