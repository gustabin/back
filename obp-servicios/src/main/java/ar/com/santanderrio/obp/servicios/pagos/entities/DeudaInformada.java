/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

/**
 * The Class DeudaInformada.
 */
public class DeudaInformada extends AbstractDeudaEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The texto en pantalla. */
	private String textoEnPantalla;

	/** The primer pago. */
	private Boolean primerPago;

	/** The modo de alta. */
	private String modoDeAlta;

	/** The descripcion. */
	private String descripcion;

	/** The pago recurrente. */
	private Boolean pagoRecurrente;

	/** The datos adicionales. */
	private String datosAdicionales = "";

	/** The informacion adicional. */
	private String informacionAdicional = "";

	/**
	 * Gets the datos adicionales.
	 *
	 * @return the datos adicionales
	 */
	public String getDatosAdicionales() {
		return datosAdicionales;
	}

	/**
	 * Sets the datos adicionales.
	 *
	 * @param datosAdicionales
	 *            the new datos adicionales
	 */
	public void setDatosAdicionales(String datosAdicionales) {
		this.datosAdicionales = datosAdicionales;
	}

	/**
	 * Gets the informacion adicional.
	 *
	 * @return the informacion adicional
	 */
	public String getInformacionAdicional() {
		return informacionAdicional;
	}

	/**
	 * Sets the informacion adicional.
	 *
	 * @param informacionAdicional
	 *            the new informacion adicional
	 */
	public void setInformacionAdicional(String informacionAdicional) {
		this.informacionAdicional = informacionAdicional;
	}

	/**
	 * Gets the texto en pantalla.
	 *
	 * @return the texto en pantalla
	 */
	public String getTextoEnPantalla() {
		return textoEnPantalla;
	}

	/**
	 * Sets the texto en pantalla.
	 *
	 * @param textoEnPantalla
	 *            the new texto en pantalla
	 */
	public void setTextoEnPantalla(String textoEnPantalla) {
		this.textoEnPantalla = textoEnPantalla;
	}

	/**
	 * Gets the primer pago.
	 *
	 * @return the primer pago
	 */
	public Boolean getPrimerPago() {
		return primerPago;
	}

	/**
	 * Sets the primer pago.
	 *
	 * @param primerPago
	 *            the new primer pago
	 */
	public void setPrimerPago(Boolean primerPago) {
		this.primerPago = primerPago;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion
	 *            the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the pago recurrente.
	 *
	 * @return the pago recurrente
	 */
	public Boolean getPagoRecurrente() {
		return pagoRecurrente;
	}

	/**
	 * Sets the pago recurrente.
	 *
	 * @param pagoRecurrente
	 *            the new pago recurrente
	 */
	public void setPagoRecurrente(Boolean pagoRecurrente) {
		this.pagoRecurrente = pagoRecurrente;
	}

	/**
	 * Gets the modo de alta.
	 *
	 * @return the modo de alta
	 */
	public String getModoDeAlta() {
		return modoDeAlta;
	}

	/**
	 * Sets the modo de alta.
	 *
	 * @param modoDeAlta
	 *            the new modo de alta
	 */
	public void setModoDeAlta(String modoDeAlta) {
		this.modoDeAlta = modoDeAlta;
	}

}
