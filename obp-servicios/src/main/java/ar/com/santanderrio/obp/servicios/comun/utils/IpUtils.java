/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.utils;

/**
 * The Class IpUtils.
 */
public class IpUtils {

	/** The Constant IP_INDEX_0. */
	private static final int IP_INDEX_0 = 0;

	/** The Constant IP_INDEX_1. */
	private static final int IP_INDEX_1 = 1;

	/** The Constant IP_INDEX_2. */
	private static final int IP_INDEX_2 = 2;

	/** The Constant IP_INDEX_3. */
	private static final int IP_INDEX_3 = 3;

	/** The Constant MAX_HEXA. */
	private static final String MAX_HEXA = "FFFFFFFF";

	/** The Constant IP_PARTS. */
	private static final int IP_PARTS = 4;

	/**
	 * Dotted ip2 hex.
	 *
	 * @param ip
	 *            the ip
	 * @return the string
	 */
	public static String dottedIp2Hex(String ip) {
		String[] t = ip.split("\\.");
		if (t.length != IP_PARTS) {
			return MAX_HEXA;
		}
		Integer[] i = new Integer[IP_PARTS];
		i[IP_INDEX_0] = Integer.valueOf(t[IP_INDEX_0]);
		i[IP_INDEX_1] = Integer.valueOf(t[IP_INDEX_1]);
		i[IP_INDEX_2] = Integer.valueOf(t[IP_INDEX_2]);
		i[IP_INDEX_3] = Integer.valueOf(t[IP_INDEX_3]);
		return String.format("%02X%02X%02X%02X", (Object[]) i);
	}
}
