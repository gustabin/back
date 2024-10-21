/**
 *
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class LineaDetalleConsumoTarjetaEntity.
 *
 *
 */
public class LineaDetalleConsumoTarjetaEntity extends LineaDetalleConsumoTarjetaGeneral implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The cuotas canceladas. */
	private String cuotasCanceladas;

	/** The cuotas totales. */
	private String cuotasTotales;

	/** The importe pesos. */
	private String importePesos;

	/** The importe dolares. */
	private String importeDolares;

	/** The consumo credito pesos. */
	private Boolean consumoCreditoPesos;

	/** The consumo credito dolares. */
	private Boolean consumoCreditoDolares;

	/** The fecha. */
	private String fecha;

	/** The date. */
	private Date date;

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the new fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return date != null ? new Date(date.getTime()) : null;
	}

	/**
	 * Sets the date.
	 *
	 * @param date
	 *            the new date
	 */
	public void setDate(Date date) {
		this.date = date != null ? new Date(date.getTime()) : null;
	}

	/**
	 * Gets the importe pesos.
	 *
	 * @return the importe pesos
	 */
	public String getImportePesos() {
		return importePesos;
	}

	/**
	 * Sets the importe pesos.
	 *
	 * @param importePesos
	 *            the new importe pesos
	 */
	public void setImportePesos(String importePesos) {
		this.importePesos = importePesos;
	}

	/**
	 * Gets the importe dolares.
	 *
	 * @return the importe dolares
	 */
	public String getImporteDolares() {
		return importeDolares;
	}

	/**
	 * Sets the importe dolares.
	 *
	 * @param importeDolares
	 *            the new importe dolares
	 */
	public void setImporteDolares(String importeDolares) {
		this.importeDolares = importeDolares;
	}

	/**
	 * Gets the consumo credito pesos.
	 *
	 * @return the consumo credito pesos
	 */
	public Boolean getConsumoCreditoPesos() {
		return consumoCreditoPesos;
	}

	/**
	 * Sets the consumo credito pesos.
	 *
	 * @param consumoCreditoPesos
	 *            the new consumo credito pesos
	 */
	public void setConsumoCreditoPesos(Boolean consumoCreditoPesos) {
		this.consumoCreditoPesos = consumoCreditoPesos;
	}

	/**
	 * Gets the consumo credito dolares.
	 *
	 * @return the consumo credito dolares
	 */
	public Boolean getConsumoCreditoDolares() {
		return consumoCreditoDolares;
	}

	/**
	 * Sets the consumo credito dolares.
	 *
	 * @param consumoCreditoDolares
	 *            the new consumo credito dolares
	 */
	public void setConsumoCreditoDolares(Boolean consumoCreditoDolares) {
		this.consumoCreditoDolares = consumoCreditoDolares;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LineaDetalleConsumoTarjetaEntity [descripcion=" + getDescripcion() + ", cuota=" + getCuota()
				+ ", tieneCuota=" + getTieneCuota() + ", importePesos=" + importePesos + ", importeDolares="
				+ importeDolares + ", consumoCreditoPesos=" + consumoCreditoPesos + ", consumoCreditoDolares="
				+ consumoCreditoDolares + ", fecha=" + fecha + ", date=" + date + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(consumoCreditoDolares);
		hcb.append(consumoCreditoPesos);
		hcb.append(getCuota());
		hcb.append(date);
		hcb.append(getDescripcion());
		hcb.append(fecha);
		hcb.append(importeDolares);
		hcb.append(importePesos);
		hcb.append(getTieneCuota());
		return hcb.hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
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
		LineaDetalleConsumoTarjetaEntity other = (LineaDetalleConsumoTarjetaEntity) obj;

		EqualsBuilder eb = new EqualsBuilder();
		eb.append(consumoCreditoDolares, other.consumoCreditoDolares);
		eb.append(consumoCreditoPesos, other.consumoCreditoPesos);
		eb.append(getCuota(), other.getCuota());
		eb.append(date, other.date);
		eb.append(getDescripcion(), other.getDescripcion());
		eb.append(fecha, other.fecha);
		eb.append(importeDolares, other.importeDolares);
		eb.append(importePesos, other.importePesos);
		eb.append(getTieneCuota(), other.getTieneCuota());
		return eb.isEquals();
	}
}
