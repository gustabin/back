/**
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.view;

import java.util.List;

/**
 * The Class MovimientoValoresView.
 *
 * @author B039636
 */
public class MovimientoValoresView {

	/** The valores debito. */
	private List<ItemMovimiento> valoresDebito;

	/** The valores credito. */
	private List<ItemMovimiento> valoresCredito;

	/** The cuenta. */
	private String cuenta;

	/**
	 * Gets the valores debito.
	 *
	 * @return the valoresDebito
	 */
	public List<ItemMovimiento> getValoresDebito() {
		return valoresDebito;
	}

	/**
	 * Setter para valores debito.
	 *
	 * @param valoresDebito
	 *            the valoresDebito to set
	 */
	public void setValoresDebito(List<ItemMovimiento> valoresDebito) {
		this.valoresDebito = valoresDebito;
	}

	/**
	 * Gets the valores credito.
	 *
	 * @return the valoresCredito
	 */
	public List<ItemMovimiento> getValoresCredito() {
		return valoresCredito;
	}

	/**
	 * Setter para valores credito.
	 *
	 * @param valoresCredito
	 *            the valoresCredito to set
	 */
	public void setValoresCredito(List<ItemMovimiento> valoresCredito) {
		this.valoresCredito = valoresCredito;

	}

	/**
	 * Gets the cantidad valores debito.
	 *
	 * @return the cantidadValoresDebito
	 */
	public int getCantidadValoresDebito() {
		return this.getValoresDebito().size();
	}

	/**
	 * Gets the cantidad valores credito.
	 *
	 * @return the cantidadValoresCredito
	 */
	public int getCantidadValoresCredito() {
		return this.getValoresCredito().size();
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta;
	}

	/**
	 * Setter para cuenta.
	 *
	 * @param cuenta
	 *            the cuenta to set
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

}
