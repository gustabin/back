package ar.com.santanderrio.obp.base.reporte.entities;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteBase.
 */
/*
 * Se creo esta clase para evitar fallo de findbugs por atributo transient en
 * clase Serializable
 */
public abstract class ReporteBase {

	/** The bytes. */
	private transient byte[] bytes;

	/**
	 * Gets the bytes.
	 *
	 * @return the bytes
	 */
	public byte[] getBytes() {
		byte[] returnValue = null;
		if (bytes != null) {
			returnValue = bytes.clone();
		}
		return returnValue;
	}

	/**
	 * Setter para bytes.
	 *
	 * @param bytes
	 *            the bytes to set
	 */
	public void setBytes(byte[] bytes) {
		this.bytes = bytes.clone();
	}
}
