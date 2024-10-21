package ar.com.santanderrio.obp.servicios.transferencias.web.view;

import java.util.List;

import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.DestinatarioView;

public class DestinatariosFrecuentesView {

	private List<DestinatarioView> destinatariosFavoritos;
	private List<DestinatarioView> destinatariosNoFavoritos;
	private String mensajeAltaFavorito;
	private String mensajeBajaFavorito;

	/**
	 * @return the destinatariosFavoritos
	 */
	public List<DestinatarioView> getDestinatariosFavoritos() {
		return destinatariosFavoritos;
	}

	/**
	 * @param destinatariosFavoritos
	 *            the destinatariosFavoritos to set
	 */
	public void setDestinatariosFavoritos(List<DestinatarioView> destinatariosFavoritos) {
		this.destinatariosFavoritos = destinatariosFavoritos;
	}

	/**
	 * @return the destinatariosNoFavoritos
	 */
	public List<DestinatarioView> getDestinatariosNoFavoritos() {
		return destinatariosNoFavoritos;
	}

	/**
	 * @param destinatariosNoFavoritos
	 *            the destinatariosNoFavoritos to set
	 */
	public void setDestinatariosNoFavoritos(List<DestinatarioView> destinatariosNoFavoritos) {
		this.destinatariosNoFavoritos = destinatariosNoFavoritos;
	}

	/**
	 * @return the mensajeAltaFavorito
	 */
	public String getMensajeAltaFavorito() {
		return mensajeAltaFavorito;
	}

	/**
	 * @param mensajeAltaFavorito
	 *            the mensajeAltaFavorito to set
	 */
	public void setMensajeAltaFavorito(String mensajeAltaFavorito) {
		this.mensajeAltaFavorito = mensajeAltaFavorito;
	}

	/**
	 * @return the mensajeBajaFavorito
	 */
	public String getMensajeBajaFavorito() {
		return mensajeBajaFavorito;
	}

	/**
	 * @param mensajeBajaFavorito
	 *            the mensajeBajaFavorito to set
	 */
	public void setMensajeBajaFavorito(String mensajeBajaFavorito) {
		this.mensajeBajaFavorito = mensajeBajaFavorito;
	}

}
