/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.view;

/**
 * The Class Cuenta.
 */
public class ConsultaCuentaView {

	/** The numero cuenta. */
	private String numeroCuenta;

	/** The alias. */
	private String alias;

	/** The cuit. */
	private String cuit;

	/** The fechas. */
	private String[] fechas;

	/** The nro sucursal. */
	private String nroSucursal;

	/** cantidadADescargar. */
	private int cantidadADescargar;

	/** The cbu. */
	private String cbu;

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
	 * Gets the alias.
	 *
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * Sets the alias.
	 *
	 * @param alias
	 *            the new alias
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * Gets the fechas.
	 *
	 * @return the fechas
	 */
	public String[] getFechas() {
		return fechas;
	}

	/**
	 * Sets the fechas.
	 *
	 * @param fechas
	 *            the new fechas
	 */
	public void setFechas(String[] fechas) {
		this.fechas = fechas;
	}

	/**
	 * Gets the nro sucursal.
	 *
	 * @return the nro sucursal
	 */
	public String getNroSucursal() {
		return nroSucursal;
	}

	/**
	 * Sets the nro sucursal.
	 *
	 * @param nroSucursal
	 *            the new nro sucursal
	 */
	public void setNroSucursal(String nroSucursal) {
		this.nroSucursal = nroSucursal;
	}

	/**
	 * Gets the cantidad A descargar.
	 *
	 * @return the cantidad A descargar
	 */
	public int getCantidadADescargar() {
		return cantidadADescargar;
	}

	/**
	 * Sets the cantidad A descargar.
	 *
	 * @param cantidadADescargar
	 *            the new cantidad A descargar
	 */
	public void setCantidadADescargar(int cantidadADescargar) {
		this.cantidadADescargar = cantidadADescargar;
	}

	/**
	 * Gets the cbu.
	 *
	 * @return the cbu
	 */
	public String getCbu() {
		return cbu;
	}

	/**
	 * Sets the cbu.
	 *
	 * @param cbu
	 *            the new cbu
	 */
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	/**
	 * Gets the cuit.
	 *
	 * @return the cuit
	 */
	public String getCuit() {
		return cuit;
	}

	/**
	 * Sets the cuit.
	 *
	 * @param cuit
	 *            the new cuit
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

}
