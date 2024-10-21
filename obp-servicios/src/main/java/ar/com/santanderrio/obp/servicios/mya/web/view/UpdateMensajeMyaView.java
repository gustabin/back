/*
 * 
 */
package ar.com.santanderrio.obp.servicios.mya.web.view;

import java.util.List;

/**
 * The Class UpdateMensajeMyaView.
 */
public class UpdateMensajeMyaView {

	/** The lista updates suscripciones. */
	private List<UpdateSuscripcionMensajeMyaView> listaUpdatesSuscripciones;

	/** The lista tarjetas. */
	private List<MyaTarjetaView> listaTarjetas;

	/**
	 * Gets the lista updates suscripciones.
	 *
	 * @return the lista updates suscripciones
	 */
	public List<UpdateSuscripcionMensajeMyaView> getListaUpdatesSuscripciones() {
		return listaUpdatesSuscripciones;
	}

	/**
	 * Sets the lista updates suscripciones.
	 *
	 * @param listaUpdatesSuscripciones
	 *            the new lista updates suscripciones
	 */
	public void setListaUpdatesSuscripciones(List<UpdateSuscripcionMensajeMyaView> listaUpdatesSuscripciones) {
		this.listaUpdatesSuscripciones = listaUpdatesSuscripciones;
	}

	/**
	 * Gets the lista tarjetas.
	 *
	 * @return the lista tarjetas
	 */
	public List<MyaTarjetaView> getListaTarjetas() {
		return listaTarjetas;
	}

	/**
	 * Sets the lista tarjetas.
	 *
	 * @param listaTarjetas
	 *            the new lista tarjetas
	 */
	public void setListaTarjetas(List<MyaTarjetaView> listaTarjetas) {
		this.listaTarjetas = listaTarjetas;
	}

}
