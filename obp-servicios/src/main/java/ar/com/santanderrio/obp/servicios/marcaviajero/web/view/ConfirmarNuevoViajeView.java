/*
 * 
 */
package ar.com.santanderrio.obp.servicios.marcaviajero.web.view;

import java.util.List;

/**
 * The Class ConfirmarNuevoViajeView.
 */
public class ConfirmarNuevoViajeView {

	/** The fecha desde. */
	private String fechaDesde;

	/** The fecha hasta. */
	private String fechaHasta;

	/** The email. */
	private String email;

	/** The destinos seleccionados. */
	private List<DestinoView> destinosSeleccionados;

	/** The tarjetas seleccionadas. */
	private List<NuevoViajeTarjetaView> tarjetasSeleccionadas;

	/**
	 * Gets the fecha desde.
	 *
	 * @return the fecha desde
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * Sets the fecha desde.
	 *
	 * @param fechaDesde
	 *            the new fecha desde
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * Gets the fecha hasta.
	 *
	 * @return the fecha hasta
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * Sets the fecha hasta.
	 *
	 * @param fechaHasta
	 *            the new fecha hasta
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
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
	 * Sets the email.
	 *
	 * @param email
	 *            the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the destinos seleccionados.
	 *
	 * @return the destinos seleccionados
	 */
	public List<DestinoView> getDestinosSeleccionados() {
		return destinosSeleccionados;
	}

	/**
	 * Sets the destinos seleccionados.
	 *
	 * @param destinosSeleccionados
	 *            the new destinos seleccionados
	 */
	public void setDestinosSeleccionados(List<DestinoView> destinosSeleccionados) {
		this.destinosSeleccionados = destinosSeleccionados;
	}

	/**
	 * Gets the tarjetas seleccionadas.
	 *
	 * @return the tarjetas seleccionadas
	 */
	public List<NuevoViajeTarjetaView> getTarjetasSeleccionadas() {
		return tarjetasSeleccionadas;
	}

	/**
	 * Sets the tarjetas seleccionadas.
	 *
	 * @param tarjetasSeleccionadas
	 *            the new tarjetas seleccionadas
	 */
	public void setTarjetasSeleccionadas(List<NuevoViajeTarjetaView> tarjetasSeleccionadas) {
		this.tarjetasSeleccionadas = tarjetasSeleccionadas;
	}

}