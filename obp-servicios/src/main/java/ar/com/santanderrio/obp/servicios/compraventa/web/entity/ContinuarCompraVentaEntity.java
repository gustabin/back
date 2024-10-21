/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.web.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class ContinuarCompraVentaEntity.
 *
 * @author sabrina.cis
 */
public class ContinuarCompraVentaEntity {

	/** The is dolar. */
	private Boolean isDolar;

	/** The numero cuenta origen. */
	private String numeroCuentaOrigen;

	/** The numero cuenta destino. */
	private String numeroCuentaDestino;

	/** The cotizacion. */
	private String cotizacion;

	/** The importe. */
	private double importe;
	
	/** The importe cache. */
	private String montoCache;

	/**
	 * Gets the cotizacion.
	 *
	 * @return the cotizacion
	 */
	public String getCotizacion() {
		return cotizacion;
	}

	/**
	 * Sets the cotizacion.
	 *
	 * @param cotizacion
	 *            the cotizacion to set
	 */
	public void setCotizacion(String cotizacion) {
		this.cotizacion = cotizacion;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public double getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the importe to set
	 */
	public void setImporte(double importe) {
		this.importe = importe;
	}

	/**
	 * Gets the monto cache.
	 *
	 * @return the monto cache
	 */
	public String getMontoCache() {
		return montoCache;
	}

	/**
	 * Sets the monto cache.
	 *
	 * @param montoCache
	 *            the new monto cache
	 */
	public void setMontoCache(String montoCache) {
		this.montoCache = montoCache;
	}

	/**
	 * Gets the checks if is dolar.
	 *
	 * @return the isDolar
	 */
	public Boolean getIsDolar() {
		return isDolar;
	}

	/**
	 * Sets the checks if is dolar.
	 *
	 * @param isDolar
	 *            the isDolar to set
	 */
	public void setIsDolar(Boolean isDolar) {
		this.isDolar = isDolar;
	}

	/**
	 * Gets the numero cuenta origen.
	 *
	 * @return the numeroCuentaOrigen
	 */
	public String getNumeroCuentaOrigen() {
		return numeroCuentaOrigen;
	}

	/**
	 * Sets the numero cuenta origen.
	 *
	 * @param numeroCuentaOrigen
	 *            the numeroCuentaOrigen to set
	 */
	public void setNumeroCuentaOrigen(String numeroCuentaOrigen) {
		this.numeroCuentaOrigen = numeroCuentaOrigen;
	}

	/**
	 * Gets the numero cuenta destino.
	 *
	 * @return the numeroCuentaDestino
	 */
	public String getNumeroCuentaDestino() {
		return numeroCuentaDestino;
	}

	/**
	 * Sets the numero cuenta destino.
	 *
	 * @param numeroCuentaDestino
	 *            the numeroCuentaDestino to set
	 */
	public void setNumeroCuentaDestino(String numeroCuentaDestino) {
		this.numeroCuentaDestino = numeroCuentaDestino;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(isDolar);
		hcb.append(numeroCuentaOrigen);
		hcb.append(numeroCuentaDestino);
		hcb.append(cotizacion);
		hcb.append(importe);
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
		ContinuarCompraVentaEntity other = (ContinuarCompraVentaEntity) obj;

		EqualsBuilder eb = new EqualsBuilder();
		eb.append(isDolar, other.getIsDolar());
		eb.append(numeroCuentaOrigen, other.getNumeroCuentaOrigen());
		eb.append(numeroCuentaDestino, other.getNumeroCuentaDestino());
		eb.append(cotizacion, other.getCotizacion());
		eb.append(importe, other.getImporte());
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ContinuarCompraVenta [isDolar=" + isDolar + ", numeroCuentaOrigen=" + numeroCuentaOrigen
				+ ", numeroCuentaDestino=" + numeroCuentaDestino + ", cotizacion=" + cotizacion + ", importe=" + importe
				+ "]";
	}

}
