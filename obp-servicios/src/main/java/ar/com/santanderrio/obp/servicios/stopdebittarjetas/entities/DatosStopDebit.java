/*
 * 
 */
package ar.com.santanderrio.obp.servicios.stopdebittarjetas.entities;

/**
 * Clase DatosStopDebit.
 */
public class DatosStopDebit {

	/** Variable sucursalCuenta. */
	private String sucursalCuenta;

	/** Variable nroCuenta. */
	private String nroCuenta;

	/** Variable tipoCuenta. */
	private String tipoCuenta;

	/** Variable codigoServicio. */
	private String codigoServicio;

	/** Variable nroPartida. */
	private String nroPartida;

	/**
	 * Devuelve la sucursal de la cuenta.
	 *
	 * @return la sucursal de cuenta
	 */
	public String getSucursalCuenta() {
		return sucursalCuenta;
	}

	/**
	 * Setea la sucursal de la cuenta.
	 *
	 * @param sucursalCuenta
	 *            la nueva sucursal de cuenta
	 */
	public void setSucursalCuenta(String sucursalCuenta) {
		this.sucursalCuenta = sucursalCuenta;
	}

	/**
	 * Devuelve el numero de cuenta.
	 *
	 * @return el numero cuenta
	 */
	public String getNroCuenta() {
		return nroCuenta;
	}

	/**
	 * Setea el numero de cuenta.
	 *
	 * @param nroCuenta
	 *            el nuevo numero de cuenta
	 */
	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipo cuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Setea el tipo de cuenta.
	 *
	 * @param tipoCuenta
	 *            el nuevo tipo de cuenta
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Devuelve el codigo de servicio.
	 *
	 * @return el codigo de servicio
	 */
	public String getCodigoServicio() {
		return codigoServicio;
	}

	/**
	 * Setea el codigo de servicio.
	 *
	 * @param codigoServicio
	 *            el nuevo codigo de servicio
	 */
	public void setCodigoServicio(String codigoServicio) {
		this.codigoServicio = codigoServicio;
	}

	/**
	 * Devuelve el numero de partida.
	 *
	 * @return el numero de partida
	 */
	public String getNroPartida() {
		return nroPartida;
	}

	/**
	 * Setea el numero de partida.
	 *
	 * @param nroPartida
	 *            el nuevo numero de partida
	 */
	public void setNroPartida(String nroPartida) {
		this.nroPartida = nroPartida;
	}

}
