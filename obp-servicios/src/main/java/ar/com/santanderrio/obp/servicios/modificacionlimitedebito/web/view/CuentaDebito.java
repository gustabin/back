/*
 * 
 */
package ar.com.santanderrio.obp.servicios.modificacionlimitedebito.web.view;

/**
 * The Class CuentaDebito.
 */
public class CuentaDebito {

	/** The id cuenta. */
	private String idCuenta;

	/** The numero tarjeta. */
	private String numeroTarjeta;

	/** The alias. */
	private String alias;

	/**
	 * Instantiates a new cuenta debito.
	 *
	 * @param idCuenta
	 *            the id cuenta
	 * @param numeroTarjeta
	 *            the numero tarjeta
	 * @param alias
	 *            the alias
	 */
	public CuentaDebito(String idCuenta, String numeroTarjeta, String alias) {
		super();
		this.idCuenta = idCuenta;
		this.numeroTarjeta = numeroTarjeta;
		this.alias = alias;
	}

	/**
	 * Gets the id cuenta.
	 *
	 * @return the id cuenta
	 */
	public String getIdCuenta() {
		return idCuenta;
	}

	/**
	 * Sets the id cuenta.
	 *
	 * @param idCuenta
	 *            the new id cuenta
	 */
	public void setIdCuenta(String idCuenta) {
		this.idCuenta = idCuenta;
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

}
