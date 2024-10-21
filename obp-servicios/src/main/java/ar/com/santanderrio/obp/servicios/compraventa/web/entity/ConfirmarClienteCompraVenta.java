/*
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.web.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class ConfirmarClienteCompraVenta.
 */
public class ConfirmarClienteCompraVenta {
	/** The numero cuenta origen. */
	private String numeroCuentaOrigen;

	/** The numero cuenta destino. */
	private String numeroCuentaDestino;

	/** The importe debito. */
	private String importeDebito;

	/** The importe credito. */
	private String importeCredito;

	/** The cotizacion. */
	private String cotizacion;

	/** The is dolar. */
	private Boolean isDolar;

	/** The nup num doc. */
	private String nupNumDoc;

	/** The nup tipo. */
	private String nupTipo;
	
	/** The monto cache. */
	private String montoCache;

	/**
	 * Gets the nup tipo.
	 *
	 * @return the nup tipo
	 */
	public String getNupTipo() {
		return nupTipo;
	}

	/**
	 * Sets the nup tipo.
	 *
	 * @param nupTipo
	 *            the new nup tipo
	 */
	public void setNupTipo(String nupTipo) {
		this.nupTipo = nupTipo;
	}

	/**
	 * Gets the numero cuenta origen.
	 *
	 * @return the numero cuenta origen
	 */
	public String getNumeroCuentaOrigen() {
		return numeroCuentaOrigen;
	}

	/**
	 * Sets the numero cuenta origen.
	 *
	 * @param numeroCuentaOrigen
	 *            the new numero cuenta origen
	 */
	public void setNumeroCuentaOrigen(String numeroCuentaOrigen) {
		this.numeroCuentaOrigen = numeroCuentaOrigen;
	}

	/**
	 * Gets the numero cuenta destino.
	 *
	 * @return the numero cuenta destino
	 */
	public String getNumeroCuentaDestino() {
		return numeroCuentaDestino;
	}

	/**
	 * Sets the numero cuenta destino.
	 *
	 * @param numeroCuentaDestino
	 *            the new numero cuenta destino
	 */
	public void setNumeroCuentaDestino(String numeroCuentaDestino) {
		this.numeroCuentaDestino = numeroCuentaDestino;
	}

	/**
	 * Gets the importe debito.
	 *
	 * @return the importe debito
	 */
	public String getImporteDebito() {
		return importeDebito;
	}

	/**
	 * Sets the importe debito.
	 *
	 * @param importeDebito
	 *            the new importe debito
	 */
	public void setImporteDebito(String importeDebito) {
		this.importeDebito = importeDebito;
	}

	/**
	 * Gets the importe credito.
	 *
	 * @return the importe credito
	 */
	public String getImporteCredito() {
		return importeCredito;
	}

	/**
	 * Sets the importe credito.
	 *
	 * @param importeCredito
	 *            the new importe credito
	 */
	public void setImporteCredito(String importeCredito) {
		this.importeCredito = importeCredito;
	}

	/**
	 * Gets the checks if is dolar.
	 *
	 * @return the checks if is dolar
	 */
	public Boolean getIsDolar() {
		return isDolar;
	}

	/**
	 * Sets the checks if is dolar.
	 *
	 * @param isDolar
	 *            the new checks if is dolar
	 */
	public void setIsDolar(Boolean isDolar) {
		this.isDolar = isDolar;
	}

	/**
	 * Gets the nup num doc.
	 *
	 * @return the nup num doc
	 */
	public String getNupNumDoc() {
		return nupNumDoc;
	}

	/**
	 * Sets the nup num doc.
	 *
	 * @param nupNumDoc
	 *            the new nup num doc
	 */
	public void setNupNumDoc(String nupNumDoc) {
		this.nupNumDoc = nupNumDoc;
	}

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
	 *            the new cotizacion
	 */
	public void setCotizacion(String cotizacion) {
		this.cotizacion = cotizacion;
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
	 * Hash builder.
	 *
	 * @return the hash code builder
	 */
	protected HashCodeBuilder hashBuilder() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(isDolar);
		hcb.append(numeroCuentaOrigen);
		hcb.append(numeroCuentaDestino);
		hcb.append(importeDebito);
		hcb.append(importeCredito);
		hcb.append(cotizacion);
		hcb.append(nupNumDoc);
		hcb.append(nupTipo);
		return hcb;
	}

	/**
	 * Equals builder.
	 *
	 * @param other1
	 *            the other 1
	 * @param other2
	 *            the other 2
	 * @return the equals builder
	 */
	protected EqualsBuilder equalsBuilder(ConfirmarClienteCompraEntity other1, ConfirmarClienteVentaEntity other2) {
		EqualsBuilder eb = new EqualsBuilder();
		if (other1 == null) {
			ConfirmarClienteVentaEntity other = other2;
			eb.append(numeroCuentaOrigen, other.getNumeroCuentaOrigen());
			eb.append(numeroCuentaDestino, other.getNumeroCuentaDestino());
			eb.append(importeDebito, other.getImporteDebito());
			eb.append(importeCredito, other.getImporteCredito());
			eb.append(isDolar, other.getIsDolar());
			eb.append(cotizacion, other.getCotizacion());
			eb.append(nupNumDoc, other.getNupNumDoc());
			eb.append(nupTipo, other.getNupTipo());
		} else {
			ConfirmarClienteCompraEntity other = other1;
			eb.append(numeroCuentaOrigen, other.getNumeroCuentaOrigen());
			eb.append(numeroCuentaDestino, other.getNumeroCuentaDestino());
			eb.append(importeDebito, other.getImporteDebito());
			eb.append(importeCredito, other.getImporteCredito());
			eb.append(isDolar, other.getIsDolar());
			eb.append(cotizacion, other.getCotizacion());
			eb.append(nupNumDoc, other.getNupNumDoc());
			eb.append(nupTipo, other.getNupTipo());
		}
		return eb;
	}

	/**
	 * To string compra venta.
	 *
	 * @return the string
	 */
	protected String toStringCompraVenta() {
		String toRet = "numeroCuentaOrigen=" + numeroCuentaOrigen + ", numeroCuentaDestino=" + numeroCuentaDestino
				+ ", importeDebito=" + importeDebito + ", importeCredito=" + importeCredito + ", cotizacion="
				+ cotizacion + ", isDolar=" + isDolar + ", nupNumDoc=" + nupNumDoc + ", nupTipo=" + nupTipo;
		return toRet;
	}

}
