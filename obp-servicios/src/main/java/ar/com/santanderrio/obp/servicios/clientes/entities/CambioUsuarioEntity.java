/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.entities;

/**
 * The Class CambioUsuarioView.
 */
public class CambioUsuarioEntity {

	/** The str old usr. */
	private String strOldUsr;

	/** The str new usr. */
	private String strNewUsr;

	/** The str old pin. */
	private String strOldPin;

	/** The str new pin. */
	private String strNewPin;

	/** The cliente. */
	private Cliente cliente;

	/**
	 * Gets the str old usr.
	 *
	 * @return the str old usr
	 */
	public String getStrOldUsr() {
		return strOldUsr;
	}

	/**
	 * Sets the str old usr.
	 *
	 * @param strOldUsr
	 *            the new str old usr
	 */
	public void setStrOldUsr(String strOldUsr) {
		this.strOldUsr = strOldUsr;
	}

	/**
	 * Gets the str new usr.
	 *
	 * @return the str new usr
	 */
	public String getStrNewUsr() {
		return strNewUsr;
	}

	/**
	 * Sets the str new usr.
	 *
	 * @param strNewUsr
	 *            the new str new usr
	 */
	public void setStrNewUsr(String strNewUsr) {
		this.strNewUsr = strNewUsr;
	}

	/**
	 * Gets the str old pin.
	 *
	 * @return the str old pin
	 */
	public String getStrOldPin() {
		return strOldPin;
	}

	/**
	 * Sets the str old pin.
	 *
	 * @param strOldPin
	 *            the new str old pin
	 */
	public void setStrOldPin(String strOldPin) {
		this.strOldPin = strOldPin;
	}

	/**
	 * Gets the str new pin.
	 *
	 * @return the str new pin
	 */
	public String getStrNewPin() {
		return strNewPin;
	}

	/**
	 * Sets the str new pin.
	 *
	 * @param strNewPin
	 *            the new str new pin
	 */
	public void setStrNewPin(String strNewPin) {
		this.strNewPin = strNewPin;
	}

	/**
	 * Gets the cliente.
	 *
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Sets the cliente.
	 *
	 * @param cliente
	 *            the new cliente
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
