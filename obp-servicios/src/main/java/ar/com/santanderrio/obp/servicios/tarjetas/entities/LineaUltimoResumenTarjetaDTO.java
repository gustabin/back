/*
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
 * The Class LineaUltimoResumenTarjetaDTO.
 */
public class LineaUltimoResumenTarjetaDTO {

	/** The date. */
	private Date fecha;

	/** The descripcion. */
	private String descripcion;

	/** The importe pesos. */
	private BigDecimal importePesos;

	/** The importe dolares. */
	private BigDecimal importeDolares;

	/** The comprobante. */
	private String comprobante;

	/** The cuota. */
	private String cuota;

	/** The tiene cuota. */
	private Boolean tieneCuota;

	/** The cuotas canceladas. */
	private String cuotasCanceladas;

	/** The cuotas totales. */
	private String cuotasTotales;

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
	 *            the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
	 * Gets the comprobante.
	 *
	 * @return the comprobante
	 */
	public String getComprobante() {
		return comprobante;
	}

	/**
	 * Sets the comprobante.
	 *
	 * @param comprobante
	 *            the comprobante to set
	 */
	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
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
	 *            the cuota to set
	 */
	public void setCuota(String cuota) {
		this.cuota = cuota;
	}

	/**
	 * Gets the tiene cuota.
	 *
	 * @return the tieneCuota
	 */
	public Boolean getTieneCuota() {
		return tieneCuota;
	}

	/**
	 * Sets the tiene cuota.
	 *
	 * @param tieneCuota
	 *            the tieneCuota to set
	 */
	public void setTieneCuota(Boolean tieneCuota) {
		this.tieneCuota = tieneCuota;
	}

	/**
	 * Gets the cuotas canceladas.
	 *
	 * @return the cuotasCanceladas
	 */
	public String getCuotasCanceladas() {
		return cuotasCanceladas;
	}

	/**
	 * Sets the cuotas canceladas.
	 *
	 * @param cuotasCanceladas
	 *            the cuotasCanceladas to set
	 */
	public void setCuotasCanceladas(String cuotasCanceladas) {
		this.cuotasCanceladas = cuotasCanceladas;
	}

	/**
	 * Gets the cuotas totales.
	 *
	 * @return the cuotasTotales
	 */
	public String getCuotasTotales() {
		return cuotasTotales;
	}

	/**
	 * Sets the cuotas totales.
	 *
	 * @param cuotasTotales
	 *            the cuotasTotales to set
	 */
	public void setCuotasTotales(String cuotasTotales) {
		this.cuotasTotales = cuotasTotales;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(comprobante);
		hcb.append(fecha);
		hcb.append(descripcion);
		hcb.append(importePesos);
		hcb.append(importeDolares);
		hcb.append(tieneCuota);
		hcb.append(cuota);
		hcb.append(cuotasCanceladas);
		hcb.append(cuotasTotales);
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
		LineaUltimoResumenTarjetaDTO other = (LineaUltimoResumenTarjetaDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(comprobante, other.getComprobante());
		eb.append(fecha, other.getFecha());
		eb.append(descripcion, other.getDescripcion());
		eb.append(importePesos, other.getImportePesos());
		eb.append(importeDolares, other.getImporteDolares());
		eb.append(cuota, other.getCuota());
		eb.append(cuotasCanceladas, other.getCuotasCanceladas());
		eb.append(cuotasTotales, other.getCuotasTotales());
		eb.append(tieneCuota, other.getTieneCuota());
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
		return new ToStringBuilder(this).append("fecha", fecha).append("descripcion", descripcion)
				.append("importePesos", importePesos).append("importeDolares", importeDolares)
				.append("comprobante", comprobante).append("tieneCuota", tieneCuota).append("cuota", cuota)
				.append("cuotasCanceladas", cuotasCanceladas).append("cuotasTotales", cuotasTotales).toString();
	}

}