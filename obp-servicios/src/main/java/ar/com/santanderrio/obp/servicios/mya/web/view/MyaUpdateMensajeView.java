/*
 * 
 */
package ar.com.santanderrio.obp.servicios.mya.web.view;

import java.util.List;

import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaMsgMultiple;

/**
 * The Class MyaUpdateMensajeView.
 */
public class MyaUpdateMensajeView {

	/** The datos. */
	private UpdateMensajeMyaView datos;

	/** The lista msg multiples. */
	private List<MyaMsgMultiple> listaMsgMultiples;

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public UpdateMensajeMyaView getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the new datos
	 */
	public void setDatos(UpdateMensajeMyaView datos) {
		this.datos = datos;
	}

	/**
	 * Gets the lista msg multiples.
	 *
	 * @return the lista msg multiples
	 */
	public List<MyaMsgMultiple> getListaMsgMultiples() {
		return listaMsgMultiples;
	}

	/**
	 * Sets the lista msg multiples.
	 *
	 * @param listaMsgMultiples
	 *            the new lista msg multiples
	 */
	public void setListaMsgMultiples(List<MyaMsgMultiple> listaMsgMultiples) {
		this.listaMsgMultiples = listaMsgMultiples;
	}

}