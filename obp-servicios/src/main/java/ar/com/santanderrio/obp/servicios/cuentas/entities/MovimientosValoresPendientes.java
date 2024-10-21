/**
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

import java.util.List;

/**
 * The Class MovimientosValoresPendientes.
 *
 * @author B039636
 */
public class MovimientosValoresPendientes {

	/** The valores debito. */
	private List<DetalleMovimientoChequesYValoresEntity> valoresDebito;

	/** The valores credito. */
	private List<DetalleMovimientoChequesYValoresEntity> valoresCredito;

	/** The cuenta. */
	private String cuenta;

	/** The tipo cuenta. */
	private String tipoCuenta;

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipo cuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Setter para tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            el nuevo tipo cuenta
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the valores debito.
	 *
	 * @return the valoresDebito
	 */
	public List<DetalleMovimientoChequesYValoresEntity> getValoresDebito() {
		return valoresDebito;
	}

	/**
	 * Setter para valores debito.
	 *
	 * @param valoresDebito
	 *            the valoresDebito to set
	 */
	public void setValoresDebito(List<DetalleMovimientoChequesYValoresEntity> valoresDebito) {
		this.valoresDebito = valoresDebito;
	}

	/**
	 * Gets the valores credito.
	 *
	 * @return the valoresCredito
	 */
	public List<DetalleMovimientoChequesYValoresEntity> getValoresCredito() {
		return valoresCredito;
	}

	/**
	 * Setter para valores credito.
	 *
	 * @param valoresCredito
	 *            the valoresCredito to set
	 */
	public void setValoresCredito(List<DetalleMovimientoChequesYValoresEntity> valoresCredito) {
		this.valoresCredito = valoresCredito;
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
