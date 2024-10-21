/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class LineaDetalleConsumoPendienteDTO.
 */
public class LineaDetalleConsumoPendienteDTO {

	/** The descripcion. */
	private String descripcion;

	/** The cuota. */
	private String cuota;

	/** The importe pesos. */
	private BigDecimal importePesos;

	/** The importe dolares. */
	private BigDecimal importeDolares;

	/** The fecha. */
	private Date fecha;

	/** The codigo establecimiento. */
	private String codigoEstablecimiento;

	/** The is consumo pesos. */
	private Boolean isConsumoPesos;

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion
	 *            the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the cuota.
	 *
	 * @return the cuota
	 */
	public String getCuota() {
		return cuota;
	}

	/**
	 * Sets the cuota.
	 *
	 * @param cuota
	 *            the new cuota
	 */
	public void setCuota(String cuota) {
		this.cuota = cuota;
	}

	/**
	 * Gets the codigo establecimiento.
	 *
	 * @return the codigo establecimiento
	 */
	public String getCodigoEstablecimiento() {
		return codigoEstablecimiento;
	}

	/**
	 * Sets the codigo establecimiento.
	 *
	 * @param codigoEstablecimiento
	 *            the new codigo establecimiento
	 */
	public void setCodigoEstablecimiento(String codigoEstablecimiento) {
		this.codigoEstablecimiento = codigoEstablecimiento;
	}

	/**
	 * Gets the consumo pesos.
	 *
	 * @return the consumo pesos
	 */
	public Boolean getConsumoPesos() {
		return isConsumoPesos;
	}

	/**
	 * Sets the consumo pesos.
	 *
	 * @param consumoPesos
	 *            the new consumo pesos
	 */
	public void setConsumoPesos(Boolean consumoPesos) {
		this.isConsumoPesos = consumoPesos;
	}

	/**
	 * Gets the importe pesos.
	 *
	 * @return the importe pesos
	 */
	public BigDecimal getImportePesos() {
		return importePesos;
	}

	/**
	 * Sets the importe pesos.
	 *
	 * @param importePesos
	 *            the new importe pesos
	 */
	public void setImportePesos(BigDecimal importePesos) {
		this.importePesos = importePesos;
	}

	/**
	 * Gets the importe dolares.
	 *
	 * @return the importe dolares
	 */
	public BigDecimal getImporteDolares() {
		return importeDolares;
	}

	/**
	 * Sets the importe dolares.
	 *
	 * @param importeDolares
	 *            the new importe dolares
	 */
	public void setImporteDolares(BigDecimal importeDolares) {
		this.importeDolares = importeDolares;
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the new fecha
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the checks if is consumo pesos.
	 *
	 * @return the checks if is consumo pesos
	 */
	public Boolean getIsConsumoPesos() {
		return isConsumoPesos;
	}

	/**
	 * Sets the checks if is consumo pesos.
	 *
	 * @param isConsumoPesos
	 *            the new checks if is consumo pesos
	 */
	public void setIsConsumoPesos(Boolean isConsumoPesos) {
		this.isConsumoPesos = isConsumoPesos;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(codigoEstablecimiento);
		hcb.append(isConsumoPesos);
		hcb.append(cuota);
		hcb.append(descripcion);
		hcb.append(fecha);
		hcb.append(importeDolares);
		hcb.append(importePesos);
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
		LineaDetalleConsumoPendienteDTO other = (LineaDetalleConsumoPendienteDTO) obj;
		return new EqualsBuilder().append(codigoEstablecimiento, other.codigoEstablecimiento)
				.append(isConsumoPesos, other.isConsumoPesos).append(cuota, other.cuota)
				.append(descripcion, other.descripcion).append(fecha, other.fecha)
				.append(importeDolares, other.importeDolares).append(importePesos, other.importePesos).isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this).append("descripcion", descripcion).append("cuota", cuota)
				.append("importePesos", importePesos).append("importeDolares", importeDolares).append("fecha", fecha)
				.append("codigoEstablecimiento", codigoEstablecimiento).append("isConsumoPesos", isConsumoPesos)
				.toString();
	}

}