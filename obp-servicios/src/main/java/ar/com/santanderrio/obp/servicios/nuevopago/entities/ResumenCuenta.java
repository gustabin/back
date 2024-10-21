/*
 * 
 */
package ar.com.santanderrio.obp.servicios.nuevopago.entities;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class ResumenCuenta.
 */
public class ResumenCuenta implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The tipo cuenta. */
	private String descripcionTipoCuenta;

	/** The nro cuenta producto. */
	private String numero;

	/** The saldo pesos. */
	private String saldoPesos;

	/** The cuenta principal. */
	private Boolean cuentaPrincipal;

	/** The Tico Cuenta Abreviado. */
	private String tipoCuentaAbreviado;

	/**
	 * Gets the descripcion tipo cuenta.
	 *
	 * @return the descripcion tipo cuenta
	 */
	public String getDescripcionTipoCuenta() {
		return descripcionTipoCuenta;
	}

	/**
	 * Sets the descripcion tipo cuenta.
	 *
	 * @param descripcionTipoCuenta
	 *            the new descripcion tipo cuenta
	 */
	public void setDescripcionTipoCuenta(String descripcionTipoCuenta) {
		this.descripcionTipoCuenta = descripcionTipoCuenta;
	}

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
	 * Getter de saldo pesos.
	 *
	 * @return el saldo pesos
	 */
	public String getSaldoPesos() {
		return saldoPesos;
	}

	/**
	 * Setter de saldo pesos.
	 *
	 * @param saldoPesos
	 *            el nuevo saldo pesos
	 */
	public void setSaldoPesos(String saldoPesos) {
		this.saldoPesos = saldoPesos;
	}

	/**
	 * Getter de cuenta principal.
	 *
	 * @return la nueva cuenta principal
	 */
	public Boolean getCuentaPrincipal() {
		return cuentaPrincipal;
	}

	/**
	 * Setter de cuenta principal.
	 *
	 * @param cuentaPrincipal
	 *            the new cuenta principal
	 */
	public void setCuentaPrincipal(Boolean cuentaPrincipal) {
		this.cuentaPrincipal = cuentaPrincipal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder().append(cuentaPrincipal).append(numero).append(saldoPesos)
				.append(descripcionTipoCuenta);
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
		ResumenCuenta other = (ResumenCuenta) obj;
		EqualsBuilder eb = new EqualsBuilder().append(cuentaPrincipal, other.cuentaPrincipal)
				.append(numero, other.numero).append(saldoPesos, other.saldoPesos)
				.append(descripcionTipoCuenta, other.descripcionTipoCuenta);
		return eb.isEquals();

	}

	/**
	 * Gets the tipo cuenta abreviado.
	 *
	 * @return the tipo cuenta abreviado
	 */
	public String getTipoCuentaAbreviado() {
		return tipoCuentaAbreviado;
	}

	/**
	 * Sets the tipo cuenta abreviado.
	 *
	 * @param tipoCuentaAbreviado
	 *            the new tipo cuenta abreviado
	 */
	public void setTipoCuentaAbreviado(String tipoCuentaAbreviado) {
		this.tipoCuentaAbreviado = tipoCuentaAbreviado;
	}

}
