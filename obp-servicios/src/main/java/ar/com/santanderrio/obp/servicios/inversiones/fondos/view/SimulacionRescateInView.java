/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.view;

import javax.validation.constraints.Pattern;

import com.sun.istack.NotNull;

/**
 * Entrada al endpoint de simulacion de rescate de fondo.
 *
 * @author marcelo.ruiz
 */

public class SimulacionRescateInView {

	/** The codigo fondo. */
	@NotNull
	private String codigoFondo;

	/** The nro cuenta titulos. */
	@NotNull
	private String nroCuentaTitulos;

	/** The importe neto. */
	@NotNull
	private String importeNeto;

	/** The tipo cuenta. */
	@NotNull
	@Pattern(regexp = "[A-Z]*")
	private String tipoCuenta;

	/** The sucursal cuenta. */
	@NotNull
	@Pattern(regexp = "[0-9]{3}")
	private String sucursalCuenta;

	/** The numero cuenta. */
	@NotNull
	@Pattern(regexp = "[0-9]*/[0-9]")
	private String numeroCuenta;

	/** The moneda. */
	@NotNull
	@Pattern(regexp = "(u\\$s)|(\\$)")
	private String moneda;
	
	private boolean esEspecie;

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
	 * Gets the importe neto.
	 *
	 * @return the importe neto
	 */
	public String getImporteNeto() {
		return importeNeto;
	}

	/**
	 * Sets the importe neto.
	 *
	 * @param importeNeto
	 *            the new importe neto
	 */
	public void setImporteNeto(String importeNeto) {
		this.importeNeto = importeNeto;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipo cuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the new tipo cuenta
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the sucursal cuenta.
	 *
	 * @return the sucursal cuenta
	 */
	public String getSucursalCuenta() {
		return sucursalCuenta;
	}

	/**
	 * Sets the sucursal cuenta.
	 *
	 * @param sucursalCuenta
	 *            the new sucursal cuenta
	 */
	public void setSucursalCuenta(String sucursalCuenta) {
		this.sucursalCuenta = sucursalCuenta;
	}

	/**
	 * Gets the numero cuenta.
	 *
	 * @return the numero cuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Sets the numero cuenta.
	 *
	 * @param numeroCuenta
	 *            the new numero cuenta
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
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
	 * Gets the nro cuenta titulos.
	 *
	 * @return the nro cuenta titulos
	 */
	public String getNroCuentaTitulos() {
		return nroCuentaTitulos;
	}

	/**
	 * Sets the nro cuenta titulos.
	 *
	 * @param nroCuentaTitulos
	 *            the new nro cuenta titulos
	 */
	public void setNroCuentaTitulos(String nroCuentaTitulos) {
		this.nroCuentaTitulos = nroCuentaTitulos;
	}

	public boolean isEsEspecie() {
		return esEspecie;
	}

	public void setEsEspecie(boolean esEspecie) {
		this.esEspecie = esEspecie;
	}
	
}
