/*
 * 
 */
package ar.com.santanderrio.obp.servicios.mya.web.view;

/**
 * The Class MyaTarjetaView.
 */
public class MyaTarjetaView {

	/** The nro tarjeta enmascarado. */
	private String nroTarjetaEnmascarado;

	/** The monto. */
	private String monto;

	/** The suscripcion. */
	private Boolean suscripcion;

	/**
	 * Gets the monto.
	 *
	 * @return the monto
	 */
	public String getMonto() {
		return monto;
	}

	/**
	 * Sets the monto.
	 *
	 * @param monto
	 *            the new monto
	 */
	public void setMonto(String monto) {
		this.monto = monto;
	}

	/**
	 * Gets the suscripcion.
	 *
	 * @return the suscripcion
	 */
	public Boolean getSuscripcion() {
		return suscripcion;
	}

	/**
	 * Sets the suscripcion.
	 *
	 * @param suscripcion
	 *            the new suscripcion
	 */
	public void setSuscripcion(Boolean suscripcion) {
		this.suscripcion = suscripcion;
	}

	/**
	 * Gets the nro tarjeta enmascarado.
	 *
	 * @return the nro tarjeta enmascarado
	 */
	public String getNroTarjetaEnmascarado() {
		return nroTarjetaEnmascarado;
	}

	/**
	 * Sets the nro tarjeta enmascarado.
	 *
	 * @param nroTarjetaEnmascarado
	 *            the new nro tarjeta enmascarado
	 */
	public void setNroTarjetaEnmascarado(String nroTarjetaEnmascarado) {
		this.nroTarjetaEnmascarado = nroTarjetaEnmascarado;
	}

}
