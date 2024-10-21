/*
 * 
 */
package ar.com.santanderrio.obp.servicios.mya.entities;

import java.util.List;

/**
 * The Class UpdateMensajesMyaDTOIn.
 *
 * @author B041964
 */
public class UpdateMensajesMyaDTOIn {

	/** The lista update mensajes mya suscripcion. */
	private List<UpdateMensajesMyaSuscripcion> listaUpdateMensajesMyaSuscripcion;

	/**
	 * Gets the lista update mensajes mya suscripcion.
	 *
	 * @return the lista update mensajes mya suscripcion
	 */
	public List<UpdateMensajesMyaSuscripcion> getListaUpdateMensajesMyaSuscripcion() {
		return listaUpdateMensajesMyaSuscripcion;
	}

	/**
	 * Sets the lista update mensajes mya suscripcion.
	 *
	 * @param listaUpdateMensajesMyaSuscripcion
	 *            the new lista update mensajes mya suscripcion
	 */
	public void setListaUpdateMensajesMyaSuscripcion(
			List<UpdateMensajesMyaSuscripcion> listaUpdateMensajesMyaSuscripcion) {
		this.listaUpdateMensajesMyaSuscripcion = listaUpdateMensajesMyaSuscripcion;
	}

}
