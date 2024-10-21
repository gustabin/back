package ar.com.santanderrio.obp.servicios.tarjetas.web.view;

/**
 * The Class StackTarjetaView.
 * 
 *
 */
public class StackTarjetaView {
	/** The String marca. */
	private String marca;

	/** The String numero. */
	private String numero;

	/** The String numero. */
	private String numeroCuenta;

	/**
	 * Getter de marca.
	 * 
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * Setter de marca.
	 * 
	 * @param marca
	 *            the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * Getter de numero.
	 * 
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Setter de numero.
	 * 
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
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
}
