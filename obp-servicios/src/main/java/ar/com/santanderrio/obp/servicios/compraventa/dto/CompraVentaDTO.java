/*
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.dto;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class CompraVentaDolar.
 */
/**
 * @author florencia.n.martinez
 *
 */
public class CompraVentaDTO {
	/** The Boolean esCompra. */
	private Boolean esCompra = Boolean.TRUE;

	/** The Boolean esVenta. */
	private Boolean esVenta = Boolean.FALSE;

	/** The importe pesos. */
	private BigDecimal importePesos;

	/** The importe dolares. */
	private BigDecimal importeDolares;

	/** The String aliasCtaOrigen. */
	private String aliasCtaOrigen;

	/** The Boolean tieneAliasCtaOrigen. */
	private Boolean tieneAliasCtaOrigen = Boolean.FALSE;

	/** The String nroCuentaOrigen. */
	private String nroCuentaOrigen;

	/** The Boolean mostrarNroCtaOrigen. */
	private Boolean mostrarNroCtaOrigen = Boolean.TRUE;

	/** The saldo cuenta origen. */
	private BigDecimal saldoCuentaOrigen;

	/** The Boolean tieneCtaOrigen. */
	private Boolean tieneCtaOrigen = Boolean.TRUE;

	/** The String aliasCtaDestino. */
	private String aliasCtaDestino;

	/** The Boolean tieneAliasCtaDestino. */
	private Boolean tieneAliasCtaDestino = Boolean.FALSE;

	/** The String nroCuentaDestino. */
	private String nroCuentaDestino;

	/** The Boolean mostrarNroCtaDestino. */
	private Boolean mostrarNroCtaDestino = Boolean.TRUE;

	/** The saldo cuenta destino. */
	private BigDecimal saldoCuentaDestino;

	/** The Boolean tieneCtaDestino. */
	private Boolean tieneCtaDestino = Boolean.TRUE;

	/**
	 * Getter de esCompra.
	 * 
	 * @return the esCompra
	 */
	public Boolean getEsCompra() {
		return esCompra;
	}

	/**
	 * Setter de esCompra.
	 * 
	 * @param esCompra
	 *            the esCompra to set
	 */
	public void setEsCompra(Boolean esCompra) {
		this.esCompra = esCompra;
	}

	/**
	 * Getter de esVenta.
	 * 
	 * @return the esVenta
	 */
	public Boolean getEsVenta() {
		return esVenta;
	}

	/**
	 * Setter de esVenta.
	 * 
	 * @param esVenta
	 *            the esVenta to set
	 */
	public void setEsVenta(Boolean esVenta) {
		this.esVenta = esVenta;
	}

	/**
	 * Getter de aliasCtaOrigen.
	 * 
	 * @return the aliasCtaOrigen
	 */
	public String getAliasCtaOrigen() {
		return aliasCtaOrigen;
	}

	/**
	 * Setter de aliasCtaOrigen.
	 * 
	 * @param aliasCtaOrigen
	 *            the aliasCtaOrigen to set
	 */
	public void setAliasCtaOrigen(String aliasCtaOrigen) {
		this.aliasCtaOrigen = aliasCtaOrigen;
	}

	/**
	 * Getter de tieneAliasCtaOrigen.
	 * 
	 * @return the tieneAliasCtaOrigen
	 */
	public Boolean getTieneAliasCtaOrigen() {
		return tieneAliasCtaOrigen;
	}

	/**
	 * Setter de tieneAliasCtaOrigen.
	 * 
	 * @param tieneAliasCtaOrigen
	 *            the tieneAliasCtaOrigen to set
	 */
	public void setTieneAliasCtaOrigen(Boolean tieneAliasCtaOrigen) {
		this.tieneAliasCtaOrigen = tieneAliasCtaOrigen;
	}

	/**
	 * Getter de nroCuentaOrigen.
	 * 
	 * @return the nroCuentaOrigen
	 */
	public String getNroCuentaOrigen() {
		return nroCuentaOrigen;
	}

	/**
	 * Setter de nroCuentaOrigen.
	 * 
	 * @param nroCuentaOrigen
	 *            the nroCuentaOrigen to set
	 */
	public void setNroCuentaOrigen(String nroCuentaOrigen) {
		this.nroCuentaOrigen = nroCuentaOrigen;
	}

	/**
	 * Getter de mostrarNroCtaOrigen.
	 * 
	 * @return the mostrarNroCtaOrigen
	 */
	public Boolean getMostrarNroCtaOrigen() {
		return mostrarNroCtaOrigen;
	}

	/**
	 * Setter de mostrarNroCtaOrigen.
	 * 
	 * @param mostrarNroCtaOrigen
	 *            the mostrarNroCtaOrigen to set
	 */
	public void setMostrarNroCtaOrigen(Boolean mostrarNroCtaOrigen) {
		this.mostrarNroCtaOrigen = mostrarNroCtaOrigen;
	}

	/**
	 * Getter de tieneCtaOrigen.
	 * 
	 * @return the tieneCtaOrigen
	 */
	public Boolean getTieneCtaOrigen() {
		return tieneCtaOrigen;
	}

	/**
	 * Setter de tieneCtaOrigen.
	 * 
	 * @param tieneCtaOrigen
	 *            the tieneCtaOrigen to set
	 */
	public void setTieneCtaOrigen(Boolean tieneCtaOrigen) {
		this.tieneCtaOrigen = tieneCtaOrigen;
	}

	/**
	 * Getter de aliasCtaDestino.
	 * 
	 * @return the aliasCtaDestino
	 */
	public String getAliasCtaDestino() {
		return aliasCtaDestino;
	}

	/**
	 * Setter de aliasCtaDestino.
	 * 
	 * @param aliasCtaDestino
	 *            the aliasCtaDestino to set
	 */
	public void setAliasCtaDestino(String aliasCtaDestino) {
		this.aliasCtaDestino = aliasCtaDestino;
	}

	/**
	 * Getter de tieneAliasCtaDestino.
	 * 
	 * @return the tieneAliasCtaDestino
	 */
	public Boolean getTieneAliasCtaDestino() {
		return tieneAliasCtaDestino;
	}

	/**
	 * Setter de tieneAliasCtaDestino.
	 * 
	 * @param tieneAliasCtaDestino
	 *            the tieneAliasCtaDestino to set
	 */
	public void setTieneAliasCtaDestino(Boolean tieneAliasCtaDestino) {
		this.tieneAliasCtaDestino = tieneAliasCtaDestino;
	}

	/**
	 * Getter de nroCuentaDestino.
	 * 
	 * @return the nroCuentaDestino
	 */
	public String getNroCuentaDestino() {
		return nroCuentaDestino;
	}

	/**
	 * Setter de nroCuentaDestino.
	 * 
	 * @param nroCuentaDestino
	 *            the nroCuentaDestino to set
	 */
	public void setNroCuentaDestino(String nroCuentaDestino) {
		this.nroCuentaDestino = nroCuentaDestino;
	}

	/**
	 * Getter de mostrarNroCtaDestino.
	 * 
	 * @return the mostrarNroCtaDestino
	 */
	public Boolean getMostrarNroCtaDestino() {
		return mostrarNroCtaDestino;
	}

	/**
	 * Setter de mostrarNroCtaDestino.
	 * 
	 * @param mostrarNroCtaDestino
	 *            the mostrarNroCtaDestino to set
	 */
	public void setMostrarNroCtaDestino(Boolean mostrarNroCtaDestino) {
		this.mostrarNroCtaDestino = mostrarNroCtaDestino;
	}

	/**
	 * Getter de tieneCtaDestino.
	 * 
	 * @return the tieneCtaDestino
	 */
	public Boolean getTieneCtaDestino() {
		return tieneCtaDestino;
	}

	/**
	 * Setter de tieneCtaDestino.
	 * 
	 * @param tieneCtaDestino
	 *            the tieneCtaDestino to set
	 */
	public void setTieneCtaDestino(Boolean tieneCtaDestino) {
		this.tieneCtaDestino = tieneCtaDestino;
	}

	/**
	 * Gets the importe pesos.
	 *
	 * @return the importePesos
	 */
	public BigDecimal getImportePesos() {
		return importePesos;
	}

	/**
	 * Sets the importe pesos.
	 *
	 * @param importePesos
	 *            the importePesos to set
	 */
	public void setImportePesos(BigDecimal importePesos) {
		this.importePesos = importePesos;
	}

	/**
	 * Gets the importe dolares.
	 *
	 * @return the importeDolares
	 */
	public BigDecimal getImporteDolares() {
		return importeDolares;
	}

	/**
	 * Sets the importe dolares.
	 *
	 * @param importeDolares
	 *            the importeDolares to set
	 */
	public void setImporteDolares(BigDecimal importeDolares) {
		this.importeDolares = importeDolares;
	}

	/**
	 * Gets the saldo cuenta origen.
	 *
	 * @return the saldoCuentaOrigen
	 */
	public BigDecimal getSaldoCuentaOrigen() {
		return saldoCuentaOrigen;
	}

	/**
	 * Sets the saldo cuenta origen.
	 *
	 * @param saldoCuentaOrigen
	 *            the saldoCuentaOrigen to set
	 */
	public void setSaldoCuentaOrigen(BigDecimal saldoCuentaOrigen) {
		this.saldoCuentaOrigen = saldoCuentaOrigen;
	}

	/**
	 * Gets the saldo cuenta destino.
	 *
	 * @return the saldoCuentaDestino
	 */
	public BigDecimal getSaldoCuentaDestino() {
		return saldoCuentaDestino;
	}

	/**
	 * Sets the saldo cuenta destino.
	 *
	 * @param saldoCuentaDestino
	 *            the saldoCuentaDestino to set
	 */
	public void setSaldoCuentaDestino(BigDecimal saldoCuentaDestino) {
		this.saldoCuentaDestino = saldoCuentaDestino;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(aliasCtaDestino);
		hcb.append(aliasCtaOrigen);
		hcb.append(esCompra);
		hcb.append(esVenta);
		hcb.append(importeDolares);
		hcb.append(importePesos);
		hcb.append(mostrarNroCtaDestino);
		hcb.append(mostrarNroCtaOrigen);
		hcb.append(nroCuentaDestino);
		hcb.append(nroCuentaOrigen);
		hcb.append(saldoCuentaDestino);
		hcb.append(saldoCuentaOrigen);
		hcb.append(tieneAliasCtaDestino);
		hcb.append(tieneAliasCtaOrigen);
		hcb.append(tieneCtaDestino);
		hcb.append(tieneCtaOrigen);
		return hcb.hashCode();
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
		CompraVentaDTO other = (CompraVentaDTO) obj;

		EqualsBuilder eb = new EqualsBuilder();
		eb.append(aliasCtaDestino, other.getAliasCtaDestino());
		eb.append(aliasCtaOrigen, other.getAliasCtaOrigen());
		eb.append(esCompra, other.getEsCompra());
		eb.append(esVenta, other.getEsVenta());
		eb.append(importeDolares, other.getImporteDolares());
		eb.append(importePesos, other.getImportePesos());
		eb.append(mostrarNroCtaDestino, other.getMostrarNroCtaDestino());
		eb.append(mostrarNroCtaOrigen, other.getMostrarNroCtaOrigen());
		eb.append(nroCuentaDestino, other.getNroCuentaDestino());
		eb.append(nroCuentaOrigen, other.getNroCuentaOrigen());
		eb.append(saldoCuentaDestino, other.getSaldoCuentaDestino());
		eb.append(saldoCuentaOrigen, other.getSaldoCuentaOrigen());
		eb.append(tieneAliasCtaDestino, other.getTieneAliasCtaDestino());
		eb.append(tieneAliasCtaOrigen, other.getTieneAliasCtaOrigen());
		eb.append(tieneCtaDestino, other.getTieneCtaDestino());
		eb.append(tieneCtaOrigen, other.getTieneCtaOrigen());
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
		return new ToStringBuilder(this).append("esCompra", esCompra).append("esVenta", esVenta)
				.append("importePesos", importePesos).append("importeDolares", importeDolares)
				.append("aliasCtaOrigen", aliasCtaOrigen).append("tieneAliasCtaOrigen", tieneAliasCtaOrigen)
				.append("nroCuentaOrigen", nroCuentaOrigen).append("mostrarNroCtaOrigen", mostrarNroCtaOrigen)
				.append("saldoCuentaOrigen", saldoCuentaOrigen).append("tieneCtaOrigen", tieneCtaOrigen)
				.append("aliasCtaDestino", aliasCtaDestino).append("tieneAliasCtaDestino", tieneAliasCtaDestino)
				.append("nroCuentaDestino", nroCuentaDestino).append("mostrarNroCtaDestino", mostrarNroCtaDestino)
				.append("saldoCuentaDestino", saldoCuentaDestino).append("tieneCtaDestino", tieneCtaDestino).toString();
	}

}