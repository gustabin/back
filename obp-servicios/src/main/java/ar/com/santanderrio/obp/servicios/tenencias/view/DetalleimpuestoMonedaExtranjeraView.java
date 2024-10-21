/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.view;

/**
 * The Class DetalleimpuestoMonedaExtranjeraView.
 */
public class DetalleimpuestoMonedaExtranjeraView extends DetalleImpuestoView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The origen. */
	private String origen;

	/**
	 * Gets the origen.
	 *
	 * @return the origen
	 */
	public String getOrigen() {
		return origen;
	}

	/**
	 * Sets the origen.
	 *
	 * @param origen
	 *            the origen to set
	 */
	public void setOrigen(String origen) {
		this.origen = origen;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DetalleimpuestoMonedaExtranjeraView [origen=" + origen + "]";
	}

}
