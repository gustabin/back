/*
 * 
 */
package ar.com.santanderrio.obp.servicios.factory;

import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;

/**
 * The Class DatoItemMensaje.
 */
public class DatoItemMensaje {

	/**
	 * Instantiates a new dato item mensaje.
	 */
	public DatoItemMensaje() {
		super();
	}

	/**
	 * Instantiates a new dato item mensaje.
	 *
	 * @param codigoMensaje
	 *            the codigo mensaje
	 * @param tipoError
	 *            the tipo error
	 * @param tag
	 *            the tag
	 */
	public DatoItemMensaje(String codigoMensaje, TipoError tipoError, String tag) {
		super();
		this.codigoMensaje = codigoMensaje;
		this.tipoError = tipoError;
		this.tag = tag;
	}

	/** The codigo mensaje. */
	private String codigoMensaje;

	/** The tipo error. */
	private TipoError tipoError;

	/** The tag. */
	private String tag;

	/**
	 * Gets the tag.
	 *
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * Sets the tag.
	 *
	 * @param tag
	 *            the new tag
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * Gets the codigo mensaje.
	 *
	 * @return the codigo mensaje
	 */
	public String getCodigoMensaje() {
		return codigoMensaje;
	}

	/**
	 * Sets the codigo mensaje.
	 *
	 * @param codigoMensaje
	 *            the new codigo mensaje
	 */
	public void setCodigoMensaje(String codigoMensaje) {
		this.codigoMensaje = codigoMensaje;
	}

	/**
	 * Gets the tipo error.
	 *
	 * @return the tipo error
	 */
	public TipoError getTipoError() {
		return tipoError;
	}

	/**
	 * Sets the tipo error.
	 *
	 * @param tipoError
	 *            the new tipo error
	 */
	public void setTipoError(TipoError tipoError) {
		this.tipoError = tipoError;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DatoItemMensaje [codigoMensaje=" + codigoMensaje + ", tipoError=" + tipoError + ", tag=" + tag + "]";
	}

}
