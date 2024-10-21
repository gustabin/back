/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.pagos.entities.DebitoAutomatico;

/**
 * The Class CuentaAdheridaDebitoAutomatico.
 */
public class CuentaAdheridaDebitoAutomatico {

	/** The cuenta. */
	private Cuenta cuenta;

	/** The stop debit. */
	private boolean stopDebit;

	/** The tipo stop debit. */
	private String tipoStopDebit;

	/**
	 * Instantiates a new cuenta adherida debito automatico.
	 */
	public CuentaAdheridaDebitoAutomatico() {
		super();
	}

	/**
	 * Instantiates a new cuenta adherida debito automatico.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param debitoAutomatico
	 *            the debito automatico
	 */
	public CuentaAdheridaDebitoAutomatico(Cuenta cuenta, DebitoAutomatico debitoAutomatico) {
		this.cuenta = cuenta;
		this.stopDebit = debitoAutomatico.tieneStopDebit();
		this.tipoStopDebit = debitoAutomatico.getTipoStopDebit();
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public Cuenta getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta
	 *            the new cuenta
	 */
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Checks if is stop debit.
	 *
	 * @return true, if is stop debit
	 */
	public boolean isStopDebit() {
		return stopDebit;
	}

	/**
	 * Sets the stop debit.
	 *
	 * @param stopDebit
	 *            the new stop debit
	 */
	public void setStopDebit(boolean stopDebit) {
		this.stopDebit = stopDebit;
	}

	/**
	 * Gets the tipo stop debit.
	 *
	 * @return the tipo stop debit
	 */
	public String getTipoStopDebit() {
		return tipoStopDebit;
	}

	/**
	 * Sets the tipo stop debit.
	 *
	 * @param tipoStopDebit
	 *            the new tipo stop debit
	 */
	public void setTipoStopDebit(String tipoStopDebit) {
		this.tipoStopDebit = tipoStopDebit;
	}

}
