/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

/**
 * The Class PagoPendientePrestamo.
 */
public class PagoPendientePrestamo extends PagoPendiente {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The prestamo. */
	private transient Prestamo prestamo;

	/** The pre formalizacion. */
	private PreFormalizacion preFormalizacion;

	/**
	 * Gets the prestamo.
	 *
	 * @return the prestamo
	 */
	public Prestamo getPrestamo() {
		return prestamo;
	}

	/**
	 * Sets the prestamo.
	 *
	 * @param prestamo
	 *            the new prestamo
	 */
	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((prestamo == null) ? 0 : prestamo.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente#equals(
	 * java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		PagoPendientePrestamo other = (PagoPendientePrestamo) obj;
		if (prestamo == null) {
			if (other.prestamo != null) {
				return false;
			}
		} else if (!prestamo.equals(other.prestamo)) {
			return false;
		}
		return true;
	}

	/**
	 * Gets the pre formalizacion.
	 *
	 * @return the pre formalizacion
	 */
	public PreFormalizacion getPreFormalizacion() {
		return preFormalizacion;
	}

	/**
	 * Sets the pre formalizacion.
	 *
	 * @param preFormalizacion
	 *            the new pre formalizacion
	 */
	public void setPreFormalizacion(PreFormalizacion preFormalizacion) {
		this.preFormalizacion = preFormalizacion;
	}

}
