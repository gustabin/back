/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dto;

import java.math.BigDecimal;

/**
 * The Class SimulacionInDTO.
 */
public class SimulacionInDTO {

	/** The cuenta titulo. */
	private String nroCuentaBancaPrivada;

	/** The moneda. */
	private String moneda;

	/** The importe. */
	private BigDecimal importe;

	/** The codigo fondo. */
	private String codigoFondo;

	/** The codigo fondoDes. */
	private String codigoFondoDes;

	/**
	 * Gets the nro cuenta banca privada.
	 *
	 * @return the nro cuenta banca privada
	 */
	public String getNroCuentaBancaPrivada() {
		return nroCuentaBancaPrivada;
	}

	/**
	 * Sets the nro cuenta banca privada.
	 *
	 * @param nroCuentaBancaPrivada
	 *            the new nro cuenta banca privada
	 */
	public void setNroCuentaBancaPrivada(String nroCuentaBancaPrivada) {
		this.nroCuentaBancaPrivada = nroCuentaBancaPrivada;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the codigo fondo.
	 *
	 * @return the codigo fondo
	 */
	public String getCodigoFondo() {
		return codigoFondo;
	}

	/**
	 * Sets the codigo fondo.
	 *
	 * @param codigoFondo
	 *            the new codigo fondo
	 */
	public void setCodigoFondo(String codigoFondo) {
		this.codigoFondo = codigoFondo;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the new importe
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	/**
	 * Gets the codigo fondo des.
	 *
	 * @return the codigo fondo des
	 */
	public String getCodigoFondoDes() {
		return codigoFondoDes;
	}

	/**
	 * Sets the codigo fondo des.
	 *
	 * @param codigoFondoDes
	 *            the new codigo fondo des
	 */
	public void setCodigoFondoDes(String codigoFondoDes) {
		this.codigoFondoDes = codigoFondoDes;
	}

}
