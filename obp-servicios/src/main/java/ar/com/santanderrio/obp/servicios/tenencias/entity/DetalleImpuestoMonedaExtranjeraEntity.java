/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.entity;

/**
 * The Class DetalleImpuestoMonedaExtranjeraEntity.
 */
public class DetalleImpuestoMonedaExtranjeraEntity extends DetalleImpuestoEntity {

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
		return "DetalleImpuestoMonedaExtranjeraEntity [origen=" + origen + "]";
	}

}
