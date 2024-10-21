/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.dto;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;

/**
 * Objeto utilizado junto a Respuesta<T> desde el BO al Manager.
 * 
 * @author
 *
 */
public class CalificacionCrediticiaDTO {

	/** The linea total crediticia. */
	private BigDecimal lineaTotalCrediticia;

	/** The linea actual disponible. */
	private BigDecimal lineaActualDisponible;
	/**
	 * cuenta a la cual pertenece la claficicacion
	 */
	private Cuenta cuenta;

	/**
	 * Gets the linea total crediticia.
	 *
	 * @return the linea total crediticia
	 */
	public BigDecimal getLineaTotalCrediticia() {
		return lineaTotalCrediticia;
	}

	/**
	 * Sets the linea total crediticia.
	 *
	 * @param lineaTotalCrediticia
	 *            the new linea total crediticia
	 */
	public void setLineaTotalCrediticia(BigDecimal lineaTotalCrediticia) {
		this.lineaTotalCrediticia = lineaTotalCrediticia;
	}

	/**
	 * Gets the linea actual disponible.
	 *
	 * @return the linea actual disponible
	 */
	public BigDecimal getLineaActualDisponible() {
		return lineaActualDisponible;
	}

	/**
	 * Sets the linea actual disponible.
	 *
	 * @param lineaActualDisponible
	 *            the new linea actual disponible
	 */
	public void setLineaActualDisponible(BigDecimal lineaActualDisponible) {
		this.lineaActualDisponible = lineaActualDisponible;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				// agregar los append con los atributos que correspondan
				// .append(extra)
				.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}

		CalificacionCrediticiaDTO other = (CalificacionCrediticiaDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		return eb
				// agregar los appends que corresponda segun los atributos
				// cargados, Ej: .append(extra, other.getExtra())
				.isEquals();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				// agregar los appends con los atributos que corresponda, ej:
				// .append("Extra", extra)
				.toString();
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	
	
}