/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The Class CuentaBancaPrivada.
 */
public class CuentaBancaPrivada {

	/** The cuenta titulo. */
	private Cuenta cuentaTitulo;

	/** The cuenta operativa. */
	private Cuenta cuentaOperativa;

	/**
	 * Gets the cuenta titulo.
	 *
	 * @return the cuenta titulo
	 */
	public Cuenta getCuentaTitulo() {
		return cuentaTitulo;
	}

	/**
	 * Sets the cuenta titulo.
	 *
	 * @param cuentaTitulo
	 *            the new cuenta titulo
	 */
	public void setCuentaTitulo(Cuenta cuentaTitulo) {
		this.cuentaTitulo = cuentaTitulo;
	}

	/**
	 * Gets the cuenta operativa.
	 *
	 * @return the cuenta operativa
	 */
	public Cuenta getCuentaOperativa() {
		return cuentaOperativa;
	}

	/**
	 * Sets the cuenta operativa.
	 *
	 * @param cuentaOperativa
	 *            the new cuenta operativa
	 */
	public void setCuentaOperativa(Cuenta cuentaOperativa) {
		this.cuentaOperativa = cuentaOperativa;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("cuentaTitulo", cuentaTitulo).append("cuentaOperativa", cuentaOperativa)
				.toString();
	}

}
