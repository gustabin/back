/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.ErrorTarjetasEntity;

/**
 * The Class RetornoTarjetasEntity. Clase que representa el WebService Prisma
 *
 * @author sergio.e.goldentair
 */
public class RetornoTarjetasEntity {

	/** The tarjetas. */
	private TarjetasEntity tarjetas;

	/** The error tarjetas. */
	private ErrorTarjetasEntity errorTarjetas;

	/** The error. */
	private Boolean error = Boolean.FALSE;

	/**
	 * Instantiates a new tarjetas DTO.
	 */
	public RetornoTarjetasEntity() {
		super();
	}

	/**
	 * Gets the tarjetas.
	 *
	 * @return the tarjetas
	 */
	public TarjetasEntity getTarjetas() {
		return tarjetas;
	}

	/**
	 * Sets the tarjetas.
	 *
	 * @param tarjetas
	 *            the tarjetas to set
	 */
	public void setTarjetas(TarjetasEntity tarjetas) {
		this.tarjetas = tarjetas;
	}

	/**
	 * Gets the error tarjetas.
	 *
	 * @return the errorTarjetas
	 */
	public ErrorTarjetasEntity getErrorTarjetas() {
		return errorTarjetas;
	}

	/**
	 * Sets the error tarjetas.
	 *
	 * @param errorTarjetas
	 *            the errorTarjetas to set
	 */
	public void setErrorTarjetas(ErrorTarjetasEntity errorTarjetas) {
		this.errorTarjetas = errorTarjetas;
	}

	/**
	 * Gets the error.
	 *
	 * @return the error
	 */
	public Boolean getError() {
		return error;
	}

	/**
	 * Sets the error.
	 *
	 * @param error
	 *            the error to set
	 */
	public void setError(Boolean error) {
		this.error = error;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(error);
		hcb.append(errorTarjetas);
		hcb.append(tarjetas);
		return hcb.toHashCode();
	}

	/**
	 * Equals.
	 *
	 * @param obj
	 *            the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		RetornoTarjetasEntity other = (RetornoTarjetasEntity) obj;

		EqualsBuilder eb = new EqualsBuilder();
		eb.append(error, other.getError());
		eb.append(errorTarjetas, other.getErrorTarjetas());
		eb.append(tarjetas, other.getTarjetas());
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("RetornoTarjetasEntity [tarjetas=");
		sb.append(tarjetas);
		sb.append(", errorTarjetas=");
		sb.append(errorTarjetas);
		sb.append(", error=");
		sb.append(error);
		sb.append("]");
		return sb.toString();
	}

}
