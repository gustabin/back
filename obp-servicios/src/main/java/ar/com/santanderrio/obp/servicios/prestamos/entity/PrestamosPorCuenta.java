package ar.com.santanderrio.obp.servicios.prestamos.entity;

import java.math.BigDecimal;

import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;

/**
 * The Class PrestamosPorCuenta.
 */
public class PrestamosPorCuenta {
	
	/** The prestamo mayor. */
	private BigDecimal prestamoMayor;
	
	/** The prestamos. */
	private PrestamoPermitidoOutEntity prestamos;
	
	/** The cuenta. */
	private Cuenta cuenta;
	
	/** The codigo error. */
	private String codigoError;

	/**
	 * Instantiates a new prestamos por cuenta.
	 */
	public PrestamosPorCuenta() {
		super();
		this.prestamoMayor = BigDecimal.ZERO;
	}

	/**
	 * Gets the prestamo mayor.
	 *
	 * @return the prestamo mayor
	 */
	public BigDecimal getPrestamoMayor() {
		return prestamoMayor;
	}

	/**
	 * Sets the prestamo mayor.
	 *
	 * @param prestamoMayor the new prestamo mayor
	 */
	public void setPrestamoMayor(BigDecimal prestamoMayor) {
		this.prestamoMayor = prestamoMayor;
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
	 * @param cuenta the new cuenta
	 */
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the prestamos.
	 *
	 * @return the prestamos
	 */
	public PrestamoPermitidoOutEntity getPrestamos() {
		return prestamos;
	}

	/**
	 * Sets the prestamos.
	 *
	 * @param prestamos the new prestamos
	 */
	public void setPrestamos(PrestamoPermitidoOutEntity prestamos) {
		this.prestamos = prestamos;
	}

	/**
	 * Gets the codigo error.
	 *
	 * @return the codigo error
	 */
	public String getCodigoError() {
		return codigoError;
	}

	/**
	 * Sets the codigo error.
	 *
	 * @param codigoError the new codigo error
	 */
	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}
	
}
