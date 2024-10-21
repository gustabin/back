/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class CuentaOPEntity.
 *
 * @author marcelo.ruiz
 */
public class CuentaOPEntity {

	/** The nro cuenta OP. */
	@JsonProperty("NumeroCuenta")
	private String nroCuentaOP;

	/** The sucursal. */
	@JsonProperty("Sucursal")
	private String sucursal;

	/**
	 * Gets the nro cuenta OP.
	 *
	 * @return the nro cuenta OP
	 */
	public String getNroCuentaOP() {
		return nroCuentaOP;
	}

	/**
	 * Sets the nro cuenta OP.
	 *
	 * @param nroCuentaOP
	 *            the new nro cuenta OP
	 */
	public void setNroCuentaOP(String nroCuentaOP) {
		this.nroCuentaOP = nroCuentaOP;
	}

	/**
	 * Gets the sucursal.
	 *
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * Sets the sucursal.
	 *
	 * @param sucursal
	 *            the new sucursal
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

}
