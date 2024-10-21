/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

import java.math.BigDecimal;
import java.util.Date;

import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

/**
 * The Class DeudaPagoAutomatico.
 */
public class DeudaPagoAutomaticoEntity extends AbstractDeudaEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The fecha stop debit. */
	private Date fechaStopDebit;

	/** The tipo cuenta. */
	private TipoCuenta tipoCuenta;

	/** The identificacion cuenta. */
	private IdentificacionCuenta identificacionCuenta;

	/** The tope. */
	private BigDecimal tope;

	/**
	 * Gets the fecha stop debit.
	 *
	 * @return the fecha stop debit
	 */
	public Date getFechaStopDebit() {
		Date vuelta = null;
		if (this.fechaStopDebit != null) {
			vuelta = new Date(this.fechaStopDebit.getTime());
		}
		return vuelta;
	}

	/**
	 * Sets the fecha stop debit.
	 *
	 * @param fechaStopDebit
	 *            the new fecha stop debit
	 */
	public void setFechaStopDebit(Date fechaStopDebit) {
		Date inputFechaStopDebit = null;
		if (fechaStopDebit != null) {
			inputFechaStopDebit = new Date(fechaStopDebit.getTime());
		}
		this.fechaStopDebit = inputFechaStopDebit;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipo cuenta
	 */
	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the new tipo cuenta
	 */
	public void setTipoCuenta(TipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the identificacion cuenta.
	 *
	 * @return the identificacion cuenta
	 */
	public IdentificacionCuenta getIdentificacionCuenta() {
		return identificacionCuenta;
	}

	/**
	 * Sets the identificacion cuenta.
	 *
	 * @param identificacionCuenta
	 *            the new identificacion cuenta
	 */
	public void setIdentificacionCuenta(IdentificacionCuenta identificacionCuenta) {
		this.identificacionCuenta = identificacionCuenta;
	}

	/**
	 * Gets the tope.
	 *
	 * @return the tope
	 */
	public BigDecimal getTope() {
		return tope;
	}

	/**
	 * Sets the tope.
	 *
	 * @param tope
	 *            the new tope
	 */
	public void setTope(BigDecimal tope) {
		this.tope = tope;
	}

}
