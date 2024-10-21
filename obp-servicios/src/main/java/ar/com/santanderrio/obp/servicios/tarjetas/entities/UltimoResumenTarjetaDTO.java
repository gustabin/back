/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class UltimoResumenTarjetaDTO.
 */
public class UltimoResumenTarjetaDTO {

	/** The numero tarjeta. */
	private String numeroTarjeta;

	/** The total pesos. */
	private BigDecimal totalPesos;

	/** The total dolares. */
	private BigDecimal totalDolares;

	/** The lineas. */
	private List<LineaUltimoResumenTarjetaDTO> lineas;

	/**
	 * Gets the numero tarjeta.
	 *
	 * @return the numero tarjeta
	 */
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	/**
	 * Sets the numero tarjeta.
	 *
	 * @param numeroTarjeta
	 *            the new numero tarjeta
	 */
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	/**
	 * Gets the total pesos.
	 *
	 * @return the totalPesos
	 */
	public BigDecimal getTotalPesos() {
		return totalPesos;
	}

	/**
	 * Sets the total pesos.
	 *
	 * @param totalPesos
	 *            the totalPesos to set
	 */
	public void setTotalPesos(BigDecimal totalPesos) {
		this.totalPesos = totalPesos;
	}

	/**
	 * Gets the total dolares.
	 *
	 * @return the totalDolares
	 */
	public BigDecimal getTotalDolares() {
		return totalDolares;
	}

	/**
	 * Sets the total dolares.
	 *
	 * @param totalDolares
	 *            the totalDolares to set
	 */
	public void setTotalDolares(BigDecimal totalDolares) {
		this.totalDolares = totalDolares;
	}

	/**
	 * Gets the lineas.
	 *
	 * @return the lineas
	 */
	public List<LineaUltimoResumenTarjetaDTO> getLineas() {
		return lineas;
	}

	/**
	 * Sets the lineas.
	 *
	 * @param lineas
	 *            the new lineas
	 */
	public void setLineas(List<LineaUltimoResumenTarjetaDTO> lineas) {
		this.lineas = lineas;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(numeroTarjeta);
		hcb.append(totalPesos);
		hcb.append(totalDolares);
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
		UltimoResumenTarjetaDTO other = (UltimoResumenTarjetaDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(numeroTarjeta, other.getNumeroTarjeta());
		eb.append(totalPesos, other.getTotalPesos());
		eb.append(totalDolares, other.getTotalDolares());

		return eb.isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this).append("numeroTarjeta", numeroTarjeta).append("totalPesos", totalPesos)
				.append("totalDolares", totalDolares).append("lineas", lineas).toString();
	}

}
