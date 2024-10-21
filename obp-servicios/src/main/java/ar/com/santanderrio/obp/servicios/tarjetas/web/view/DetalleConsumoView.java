/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.view;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 
 * The Class DetalleConsumoView.
 * 
 *
 */

public class DetalleConsumoView {

	/** The nombre estableciminento. */
	private String nombreEstableciminento;

	/** The cantidad de cuotas. */
	private String cantidadDeCuotas;

	/** The tiene cuota. */
	private Boolean tieneCuota;

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

	/** The codigo establecimiento. */
	private String codigoEstablecimiento;

	/** The numero referencia. */
	private String numeroReferencia;

	/** The numero tarjeta. */
	private String numeroTarjeta;

	/** The has consumo pesos cero. */
	private Boolean hasConsumoPesosCero;

	/** The has consumo dolares cero. */
	private Boolean hasConsumoDolaresCero;

	/** The consumo pesos. */
	private Boolean consumoPesos;

	/** The consumo dolares. */
	private Boolean consumoDolares;

	/**
	 * Gets the consumo pesos.
	 *
	 * @return the consumo pesos
	 */
	public Boolean getConsumoPesos() {
		return consumoPesos;
	}

	/**
	 * Sets the consumo pesos.
	 *
	 * @param consumoPesos
	 *            the new consumo pesos
	 */
	public void setConsumoPesos(Boolean consumoPesos) {
		this.consumoPesos = consumoPesos;
	}

	/**
	 * Gets the consumo dolares.
	 *
	 * @return the consumo dolares
	 */
	public Boolean getConsumoDolares() {
		return consumoDolares;
	}

	/**
	 * Sets the consumo dolares.
	 *
	 * @param consumoDolares
	 *            the new consumo dolares
	 */
	public void setConsumoDolares(Boolean consumoDolares) {
		this.consumoDolares = consumoDolares;
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
	 * Gets the nombre estableciminento.
	 *
	 * @return the nombre estableciminento
	 */
	public String getNombreEstableciminento() {
		return nombreEstableciminento;
	}

	/**
	 * Sets the nombre estableciminento.
	 *
	 * @param nombreEstableciminento
	 *            the new nombre estableciminento
	 */
	public void setNombreEstableciminento(String nombreEstableciminento) {
		this.nombreEstableciminento = nombreEstableciminento;
	}

	/**
	 * Gets the cantidad de cuotas.
	 *
	 * @return the cantidad de cuotas
	 */
	public String getCantidadDeCuotas() {
		return cantidadDeCuotas;
	}

	/**
	 * Sets the cantidad de cuotas.
	 *
	 * @param cantidadDeCuotas
	 *            the new cantidad de cuotas
	 */
	public void setCantidadDeCuotas(String cantidadDeCuotas) {
		this.cantidadDeCuotas = cantidadDeCuotas;
	}

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
	 * Gets the numero referencia.
	 *
	 * @return the numero referencia
	 */
	public String getNumeroReferencia() {
		return numeroReferencia;
	}

	/**
	 * Sets the numero referencia.
	 *
	 * @param numeroReferencia
	 *            the new numero referencia
	 */
	public void setNumeroReferencia(String numeroReferencia) {
		this.numeroReferencia = numeroReferencia;
	}

	/**
	 * Gets the checks for consumo pesos cero.
	 *
	 * @return the checks for consumo pesos cero
	 */
	public Boolean getHasConsumoPesosCero() {
		return hasConsumoPesosCero;
	}

	/**
	 * Sets the checks for consumo pesos cero.
	 *
	 * @param hasConsumoPesosCero
	 *            the new checks for consumo pesos cero
	 */
	public void setHasConsumoPesosCero(Boolean hasConsumoPesosCero) {
		this.hasConsumoPesosCero = hasConsumoPesosCero;
	}

	/**
	 * Gets the checks for consumo dolares cero.
	 *
	 * @return the checks for consumo dolares cero
	 */
	public Boolean getHasConsumoDolaresCero() {
		return hasConsumoDolaresCero;
	}

	/**
	 * Sets the checks for consumo dolares cero.
	 *
	 * @param hasConsumoDolaresCero
	 *            the new checks for consumo dolares cero
	 */
	public void setHasConsumoDolaresCero(Boolean hasConsumoDolaresCero) {
		this.hasConsumoDolaresCero = hasConsumoDolaresCero;
	}

	/**
	 * Gets the consumo credito pesos.
	 *
	 * @return the consumoCreditoPesos
	 */
	public Boolean getConsumoCreditoPesos() {
		return consumoCreditoPesos;
	}

	/**
	 * Sets the consumo credito pesos.
	 *
	 * @param consumoCreditoPesos
	 *            the consumoCreditoPesos to set
	 */
	public void setConsumoCreditoPesos(Boolean consumoCreditoPesos) {
		this.consumoCreditoPesos = consumoCreditoPesos;
	}

	/**
	 * Gets the consumo credito dolares.
	 *
	 * @return the consumoCreditoDolares
	 */
	public Boolean getConsumoCreditoDolares() {
		return consumoCreditoDolares;
	}

	/**
	 * Sets the consumo credito dolares.
	 *
	 * @param consumoCreditoDolares
	 *            the consumoCreditoDolares to set
	 */
	public void setConsumoCreditoDolares(Boolean consumoCreditoDolares) {
		this.consumoCreditoDolares = consumoCreditoDolares;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DetalleConsumoView [consumoPesos=" + consumoPesos + ", consumoDolares=" + consumoDolares
				+ ", importePesos=" + importePesos + ", importeDolares=" + importeDolares + ", nombreEstableciminento="
				+ nombreEstableciminento + ", cantidadDeCuotas=" + cantidadDeCuotas + ", fecha=" + fecha
				+ ", codigoEstablecimiento=" + codigoEstablecimiento + ", numeroTarjeta=" + numeroTarjeta
				+ ", numeroReferencia=" + numeroReferencia + ", hasConsumoPesosCero=" + hasConsumoPesosCero
				+ ", hasConsumoDolaresCero=" + hasConsumoDolaresCero + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(cantidadDeCuotas);
		hcb.append(codigoEstablecimiento);
		hcb.append(consumoDolares);
		hcb.append(consumoPesos);
		hcb.append(fecha);
		hcb.append(hasConsumoDolaresCero);
		hcb.append(hasConsumoPesosCero);
		hcb.append(importeDolares);
		hcb.append(importePesos);
		hcb.append(nombreEstableciminento);
		hcb.append(numeroReferencia);
		hcb.append(numeroTarjeta);
		return hcb.toHashCode();
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
		DetalleConsumoView other = (DetalleConsumoView) obj;

		EqualsBuilder eb = new EqualsBuilder();
		eb.append(cantidadDeCuotas, other.cantidadDeCuotas);
		eb.append(codigoEstablecimiento, other.codigoEstablecimiento);
		eb.append(consumoDolares, other.consumoDolares);
		eb.append(consumoPesos, other.consumoPesos);
		eb.append(fecha, other.fecha);
		eb.append(hasConsumoDolaresCero, other.hasConsumoDolaresCero);
		eb.append(hasConsumoPesosCero, other.hasConsumoPesosCero);
		eb.append(importeDolares, other.importeDolares);
		eb.append(fecha, other.fecha);
		eb.append(hasConsumoDolaresCero, other.hasConsumoDolaresCero);
		eb.append(hasConsumoPesosCero, other.hasConsumoPesosCero);
		eb.append(importeDolares, other.importeDolares);
		eb.append(importePesos, other.importePesos);
		eb.append(nombreEstableciminento, other.nombreEstableciminento);
		eb.append(numeroReferencia, other.numeroReferencia);
		eb.append(numeroTarjeta, other.numeroTarjeta);
		return eb.isEquals();
	}
}
