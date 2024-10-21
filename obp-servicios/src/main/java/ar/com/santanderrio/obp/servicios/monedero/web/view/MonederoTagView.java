/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.web.view;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * The Class MonederoTag.
 */
public class MonederoTagView {

	/** The numero. */
	private String numero;

	/** The marca. */
	private String marca;

	/** The tipo tarjeta. */
	private String tipoTarjeta;

	/** The importe recarga. */
	private String importeRecarga;

	/** The limite mensual. */
	private String limiteMensual;

	/** The saldo. */
	private String saldo;

	/** The is titular. */
	private Boolean isTitular;

	/** The activo. */
	private Boolean activo;

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero
	 *            the new numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Gets the marca.
	 *
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * Sets the marca.
	 *
	 * @param marca
	 *            the new marca
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * Gets the tipo tarjeta.
	 *
	 * @return the tipo tarjeta
	 */
	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	/**
	 * Sets the tipo tarjeta.
	 *
	 * @param tipoTarjeta
	 *            the new tipo tarjeta
	 */
	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	/**
	 * Gets the importe recarga.
	 *
	 * @return the importe recarga
	 */
	public String getImporteRecarga() {
		return importeRecarga;
	}

	/**
	 * Sets the importe recarga.
	 *
	 * @param importeRecarga
	 *            the new importe recarga
	 */
	public void setImporteRecarga(String importeRecarga) {
		this.importeRecarga = importeRecarga;
	}

	/**
	 * Gets the limite mensual.
	 *
	 * @return the limite mensual
	 */
	public String getLimiteMensual() {
		return limiteMensual;
	}

	/**
	 * Sets the limite mensual.
	 *
	 * @param limiteMensual
	 *            the new limite mensual
	 */
	public void setLimiteMensual(String limiteMensual) {
		this.limiteMensual = limiteMensual;
	}

	/**
	 * Gets the saldo.
	 *
	 * @return the saldo
	 */
	public String getSaldo() {
		return saldo;
	}

	/**
	 * Sets the saldo.
	 *
	 * @param saldo
	 *            the new saldo
	 */
	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	/**
	 * Gets the checks if is titular.
	 *
	 * @return the checks if is titular
	 */
	public Boolean getIsTitular() {
		return isTitular;
	}

	/**
	 * Sets the checks if is titular.
	 *
	 * @param isTitular
	 *            the new checks if is titular
	 */
	public void setIsTitular(Boolean isTitular) {
		this.isTitular = isTitular;
	}

	/**
	 * Gets the activo.
	 *
	 * @return the activo
	 */
	public Boolean getActivo() {
		return activo;
	}

	/**
	 * Sets the activo.
	 *
	 * @param activo
	 *            the new activo
	 */
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(numero);
		hcb.append(marca);
		hcb.append(tipoTarjeta);
		hcb.append(importeRecarga);
		hcb.append(limiteMensual);
		hcb.append(saldo);
		hcb.append(isTitular);
		hcb.append(activo);

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
		MonederoTagView other = (MonederoTagView) obj;

		EqualsBuilder eb = new EqualsBuilder();

		eb.append(numero, other.getNumero());
		eb.append(marca, other.getMarca());
		eb.append(tipoTarjeta, other.getTipoTarjeta());
		eb.append(importeRecarga, other.getImporteRecarga());
		eb.append(limiteMensual, other.getLimiteMensual());
		eb.append(saldo, other.getSaldo());
		eb.append(isTitular, other.getIsTitular());
		eb.append(activo, other.getIsTitular());

		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MonederoTag [numero=" + numero + "marca=" + marca + ", tipoTarjeta=" + tipoTarjeta + ", importeRecarga="
				+ importeRecarga + ", limiteMensual=" + limiteMensual + ", " + "saldo=" + saldo + ", isTitular="
				+ isTitular + ", activo=" + activo + "]";
	}
}
