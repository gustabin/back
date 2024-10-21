/**
 * 
 */
package ar.com.santanderrio.obp.base.lucene;

import java.util.HashMap;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Enum LuceneIndexerType.
 *
 * @author pablo.martin.gore
 */
public enum LuceneIndexerType {

	// ----------------------------------------------------------------------
	// Enums
	/** The medio de pago. */
	// ----------------------------------------------------------------------
	MEDIO_DE_PAGO("medioPagoRubroCompania");

	// ----------------------------------------------------------------------
	// Fields
	/** The Constant map. */
	// ----------------------------------------------------------------------
	private static final Map<String, LuceneIndexerType> map = new HashMap<String, LuceneIndexerType>();

	/** The name. */
	private String name;

	// ----------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------
	static {
		for (LuceneIndexerType type : LuceneIndexerType.values()) {
			map.put(type.name, type);
		}
	}

	// ----------------------------------------------------------------------
	// static method
	// ----------------------------------------------------------------------

	/**
	 * Instantiates a new lucene indexer type.
	 *
	 * @param name
	 *            the name
	 */
	private LuceneIndexerType(String name) {
		this.name = name;
	}

	/**
	 * From string.
	 *
	 * @param name
	 *            the name
	 * @param code
	 *            the code
	 * @return the lucene indexer type
	 */
	public static LuceneIndexerType fromString(String name, String code) {
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