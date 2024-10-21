/**
 * 
 */
package ar.com.santanderrio.obp.base.jwt;

import java.util.HashMap;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Enum JWTClaimEnumeration.
 *
 * @author pablo.martin.gore
 */
public enum JWTClaimEnumeration {

	// ----------------------------------------------------------------------
	// Enums
	/** The user dni. */
	// ----------------------------------------------------------------------
	USER_DNI("dni"),

	/** The user nup. */
	USER_NUP("nup"),

	/** The server host. */
	SERVER_HOST("host"),

	/** The server uuid. */
	SERVER_UUID("jti"),

	/** The id. */
	ID("id"),

	/** The jwt exp. */
	JWT_EXP("exp");

	// ----------------------------------------------------------------------
	// Fields
	/** The Constant map. */
	// ----------------------------------------------------------------------
	private static final Map<String, JWTClaimEnumeration> map = new HashMap<String, JWTClaimEnumeration>();

	/** The name. */
	private String name;

	// ----------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------
	static {
		for (JWTClaimEnumeration type : JWTClaimEnumeration.values()) {
			map.put(type.name, type);
		}
	}

	// ----------------------------------------------------------------------
	// static method
	// ----------------------------------------------------------------------

	/**
	 * Instantiates a new JWT claim enumeration.
	 *
	 * @param name
	 *            the name
	 */
	private JWTClaimEnumeration(String name) {
		this.name = name;
	}

	/**
	 * From string.
	 *
	 * @param name
	 *            the name
	 * @param code
	 *            the code
	 * @return the JWT claim enumeration
	 */
	public static JWTClaimEnumeration fromString(String name, String code) {
		if (map.containsKey(name)) {
			return map.get(name);
		}
		return null;
	}

	// ----------------------------------------------------------------------
	// public method
	// ----------------------------------------------------------------------

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

}