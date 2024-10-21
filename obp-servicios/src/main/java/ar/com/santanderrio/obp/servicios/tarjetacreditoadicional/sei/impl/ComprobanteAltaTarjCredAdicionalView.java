/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.sei.impl;

import java.util.List;

import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.TarjetaAdicionalSolicitadaView;

/**
 * The Class ComprobanteAltaTarjCredAdicionalView.
 */
public class ComprobanteAltaTarjCredAdicionalView {

	/** The apellido nombre adicional. */
	private String apellidoNombreAdicional;

	/** The dni adicional. */
	private String dniAdicional;

	/** The tarjetas adicionales solicitadas. */
	private List<TarjetaAdicionalSolicitadaView> tarjetasAdicionalesSolicitadas;

	/** The fecha hora. */
	private String fechaHora;

	/** The legal. */
	private String legal;

	/**
	 * Gets the apellido nombre adicional.
	 *
	 * @return the apellidoNombreAdicional
	 */
	public String getApellidoNombreAdicional() {
		return apellidoNombreAdicional;
	}

	/**
	 * Sets the apellido nombre adicional.
	 *
	 * @param apellidoNombreAdicional
	 *            the apellidoNombreAdicional to set
	 */
	public void setApellidoNombreAdicional(String apellidoNombreAdicional) {
		this.apellidoNombreAdicional = apellidoNombreAdicional;
	}

	/**
	 * Gets the dni adicional.
	 *
	 * @return the dniAdicional
	 */
	public String getDniAdicional() {
		return dniAdicional;
	}

	/**
	 * Sets the dni adicional.
	 *
	 * @param dniAdicional
	 *            the dniAdicional to set
	 */
	public void setDniAdicional(String dniAdicional) {
		this.dniAdicional = dniAdicional;
	}

	/**
	 * Gets the tarjetas adicionales solicitadas.
	 *
	 * @return the tarjetasAdicionalesSolicitadas
	 */
	public List<TarjetaAdicionalSolicitadaView> getTarjetasAdicionalesSolicitadas() {
		return tarjetasAdicionalesSolicitadas;
	}

	/**
	 * Sets the tarjetas adicionales solicitadas.
	 *
	 * @param tarjetasAdicionalesSolicitadas
	 *            the tarjetasAdicionalesSolicitadas to set
	 */
	public void setTarjetasAdicionalesSolicitadas(List<TarjetaAdicionalSolicitadaView> tarjetasAdicionalesSolicitadas) {
		this.tarjetasAdicionalesSolicitadas = tarjetasAdicionalesSolicitadas;
	}

	/**
	 * Gets the fecha hora.
	 *
	 * @return the fechaHora
	 */
	public String getFechaHora() {
		return fechaHora;
	}

	/**
	 * Sets the fecha hora.
	 *
	 * @param fechaHora
	 *            the fechaHora to set
	 */
	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

	/**
	 * Gets the legal.
	 *
	 * @return the legal
	 */
	public String getLegal() {
		return legal;
	}

	/**
	 * Sets the legal.
	 *
	 * @param legal
	 *            the legal to set
	 */
	public void setLegal(String legal) {
		this.legal = legal;
	}

}
