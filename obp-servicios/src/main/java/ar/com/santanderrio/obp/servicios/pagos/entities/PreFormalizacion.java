/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.base.entities.Entity;

/**
 * The Class PreFormalizacion.
 */
public class PreFormalizacion extends Entity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The plazo. */
	private String plazo;

	/** The codigo destino prestamo. */
	private String codigoDestinoPrestamo;

	/** The prestamo debito adherido. */
	private CuentaPrestamoDebitoAdherido prestamoDebitoAdherido = new CuentaPrestamoDebitoAdherido();

	/**
	 * Gets the plazo.
	 *
	 * @return the plazo
	 */
	public String getPlazo() {
		return plazo;
	}

	/**
	 * Sets the plazo.
	 *
	 * @param plazo
	 *            the new plazo
	 */
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	/**
	 * Gets the prestamo debito adherido.
	 *
	 * @return the prestamo debito adherido
	 */
	public CuentaPrestamoDebitoAdherido getPrestamoDebitoAdherido() {
		return prestamoDebitoAdherido;
	}

	/**
	 * Sets the prestamo debito adherido.
	 *
	 * @param prestamoDebitoAdherido
	 *            the new prestamo debito adherido
	 */
	public void setPrestamoDebitoAdherido(CuentaPrestamoDebitoAdherido prestamoDebitoAdherido) {
		this.prestamoDebitoAdherido = prestamoDebitoAdherido;
	}

	/**
	 * Gets the codigo destino prestamo.
	 *
	 * @return the codigo destino prestamo
	 */
	public String getCodigoDestinoPrestamo() {
		return codigoDestinoPrestamo;
	}

	/**
	 * Sets the codigo destino prestamo.
	 *
	 * @param codigoDestinoPrestamo
	 *            the new codigo destino prestamo
	 */
	public void setCodigoDestinoPrestamo(String codigoDestinoPrestamo) {
		this.codigoDestinoPrestamo = codigoDestinoPrestamo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.base.entities.Entity#hashCode()
	 */
	public int hashCode() {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.entities.Entity#equals(java.lang.Object)
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
		return true;
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this).append("plazo", plazo).append("codigoDestinoPrestamo", codigoDestinoPrestamo)
				.append("prestamoDebitoAdherido", prestamoDebitoAdherido).toString();
	}
}
