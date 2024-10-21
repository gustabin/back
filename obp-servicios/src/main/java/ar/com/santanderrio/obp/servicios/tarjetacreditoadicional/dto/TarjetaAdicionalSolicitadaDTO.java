/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto;

/**
 * The Class tarjetaAdicionalSolicitadaView.
 */
public class TarjetaAdicionalSolicitadaDTO {

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The nro tarjeta con formato. */
	private String nroTarjetaConFormato;

	/** The cuenta. */
	private String cuenta;

	/** The limite de compra. */
	private String porcentajeLimiteDeCompra;

	/** The solicitud nro. */
	private String solicitudNro;

	/** The con error. */
	private boolean conError;
	
	/** The nombre tarjeta. */
	private String nombreTarjeta;

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
	 * Gets the nro tarjeta con formato.
	 *
	 * @return the nro tarjeta con formato
	 */
	public String getNroTarjetaConFormato() {
		return nroTarjetaConFormato;
	}

	/**
	 * Sets the nro tarjeta con formato.
	 *
	 * @param nroTarjetaConFormato
	 *            the new nro tarjeta con formato
	 */
	public void setNroTarjetaConFormato(String nroTarjetaConFormato) {
		this.nroTarjetaConFormato = nroTarjetaConFormato;
	}

	/**
	 * Checks if is con error.
	 *
	 * @return true, if is con error
	 */
	public boolean isConError() {
		return conError;
	}

	/**
	 * Sets the con error.
	 *
	 * @param conError
	 *            the new con error
	 */
	public void setConError(boolean conError) {
		this.conError = conError;
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
	 * Sets the cuenta.
	 *
	 * @param cuenta
	 *            the cuenta to set
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the porcentaje limite de compra.
	 *
	 * @return the porcentaje limite de compra
	 */
	public String getPorcentajeLimiteDeCompra() {
		return porcentajeLimiteDeCompra;
	}

	/**
	 * Sets the porcentaje limite de compra.
	 *
	 * @param porcentajeLimiteDeCompra
	 *            the new porcentaje limite de compra
	 */
	public void setPorcentajeLimiteDeCompra(String porcentajeLimiteDeCompra) {
		this.porcentajeLimiteDeCompra = porcentajeLimiteDeCompra;
	}

	/**
	 * Gets the solicitud nro.
	 *
	 * @return the solicitudNro
	 */
	public String getSolicitudNro() {
		return solicitudNro;
	}

	/**
	 * Sets the solicitud nro.
	 *
	 * @param solicitudNro
	 *            the solicitudNro to set
	 */
	public void setSolicitudNro(String solicitudNro) {
		this.solicitudNro = solicitudNro;
	}

	/**
	 * Gets the nombre tarjeta.
	 *
	 * @return the nombre tarjeta
	 */
	public String getNombreTarjeta() {
		return nombreTarjeta;
	}

	/**
	 * Sets the nombre tarjeta.
	 *
	 * @param nombreTarjeta
	 *            the new nombre tarjeta
	 */
	public void setNombreTarjeta(String nombreTarjeta) {
		this.nombreTarjeta = nombreTarjeta;
	}

}
