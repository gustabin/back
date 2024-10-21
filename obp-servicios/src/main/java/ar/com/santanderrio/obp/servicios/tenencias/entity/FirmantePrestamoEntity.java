/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.entity;

/**
 * Firmante Prestamo.
 */
public class FirmantePrestamoEntity extends FirmanteEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The pecodpro. */
	private String pecodpro;

	/** The pecodsub. */
	private String pecodsub;

	/**
	 * Gets the pecodpro.
	 *
	 * @return the pecodpro
	 */
	public String getPecodpro() {
		return pecodpro;
	}

	/**
	 * Sets the pecodpro.
	 *
	 * @param pecodpro
	 *            the pecodpro to set
	 */
	public void setPecodpro(String pecodpro) {
		this.pecodpro = pecodpro;
	}

	/**
	 * Gets the pecodsub.
	 *
	 * @return the pecodsub
	 */
	public String getPecodsub() {
		return pecodsub;
	}

	/**
	 * Sets the pecodsub.
	 *
	 * @param pecodsub
	 *            the pecodsub to set
	 */
	public void setPecodsub(String pecodsub) {
		this.pecodsub = pecodsub;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FirmantePrestamo [pecodpro=" + pecodpro + ", pecodsub=" + pecodsub + "]";
	}

}
