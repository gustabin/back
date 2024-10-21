/*
 * 
 */
package ar.com.santanderrio.obp.servicios.billetera.web.view;

import java.util.List;

/**
 * The Class ComprobanteAltaUsuarioBilleteraView.
 */
public class ComprobanteBilleteraView extends DesafioView {

	/** The codigo area. */
	private String codigoArea;

	/** The cuenta. */
	private Integer cuenta;

	/** The email. */
	private String email;

	/** The empresa seleccionada. */
	private String empresaSeleccionada;

	/** The mensaje. */
	private String fecha;

	/** The legales SEO. */
	private String legalesSEO;

	/** The nroComprobante. */
	private String nroComprobante;

	/** The tarjetas ok. */
	private List<MedioDePagoComprobanteView> tarjetasOk;

	/** The telefono. */
	private String telefono;

	/**
	 * Gets the codigo area.
	 *
	 * @return the codigoArea
	 */
	public String getCodigoArea() {
		return codigoArea;
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public Integer getCuenta() {
		return cuenta;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Gets the empresa seleccionada.
	 *
	 * @return the empresaSeleccionada
	 */
	public String getEmpresaSeleccionada() {
		return empresaSeleccionada;
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Gets the legales SEO.
	 *
	 * @return the legalesSEO
	 */
	public String getLegalesSEO() {
		return legalesSEO;
	}

	/**
	 * Gets the nro comprobante.
	 *
	 * @return the nroComprobante
	 */
	public String getNroComprobante() {
		return nroComprobante;
	}

	/**
	 * Gets the tarjetas ok.
	 *
	 * @return the tarjetasOk
	 */
	public List<MedioDePagoComprobanteView> getTarjetasOk() {
		return tarjetasOk;
	}

	/**
	 * Gets the telefono.
	 *
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Sets the codigo area.
	 *
	 * @param codigoArea
	 *            the codigoArea to set
	 */
	public void setCodigoArea(String codigoArea) {
		this.codigoArea = codigoArea;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta
	 *            the cuenta to set
	 */
	public void setCuenta(Integer cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Sets the empresa seleccionada.
	 *
	 * @param empresaSeleccionada
	 *            the empresaSeleccionada to set
	 */
	public void setEmpresaSeleccionada(String empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Sets the legales SEO.
	 *
	 * @param legalesSEO
	 *            the legalesSEO to set
	 */
	public void setLegalesSEO(String legalesSEO) {
		this.legalesSEO = legalesSEO;
	}

	/**
	 * Sets the nro comprobante.
	 *
	 * @param nroComprobante
	 *            the nroComprobante to set
	 */
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	/**
	 * Sets the tarjetas ok.
	 *
	 * @param tarjetasOk
	 *            the tarjetasOk to set
	 */
	public void setTarjetasOk(List<MedioDePagoComprobanteView> tarjetasOk) {
		this.tarjetasOk = tarjetasOk;
	}

	/**
	 * Sets the telefono.
	 *
	 * @param telefono
	 *            the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
