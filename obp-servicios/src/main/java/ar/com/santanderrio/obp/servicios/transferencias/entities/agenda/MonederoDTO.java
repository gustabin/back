/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.entities.agenda;

/**
 * The Class MonederoDTO.
 */
public class MonederoDTO {

	/** The tipo tarjeta. */
	private String tipoTarjeta;

	/** The numero tarjeta tag. */
	private String numeroTarjetaTag;

	/** The nombre embozado. */
	private String nombreEmbozado;

	/** The estado tarjeta tag. */
	private String estadoTarjetaTag;

	/**
	 * Gets the tipo tarjeta.
	 *
	 * @return the tipo tarjeta
	 */
	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	/**
	 * Sets the tipo tarjeta.
	 *
	 * @param tipoTarjeta
	 *            the new tipo tarjeta
	 */
	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	/**
	 * Gets the numero tarjeta tag.
	 *
	 * @return the numero tarjeta tag
	 */
	public String getNumeroTarjetaTag() {
		return numeroTarjetaTag;
	}

	/**
	 * Sets the numero tarjeta tag.
	 *
	 * @param numeroTarjetaTag
	 *            the new numero tarjeta tag
	 */
	public void setNumeroTarjetaTag(String numeroTarjetaTag) {
		this.numeroTarjetaTag = numeroTarjetaTag;
	}

	/**
	 * Gets the nombre embozado.
	 *
	 * @return the nombre embozado
	 */
	public String getNombreEmbozado() {
		return nombreEmbozado;
	}

	/**
	 * Sets the nombre embozado.
	 *
	 * @param nombreEmbozado
	 *            the new nombre embozado
	 */
	public void setNombreEmbozado(String nombreEmbozado) {
		this.nombreEmbozado = nombreEmbozado;
	}

	/**
	 * Gets the estado tarjeta tag.
	 *
	 * @return the estado tarjeta tag
	 */
	public String getEstadoTarjetaTag() {
		return estadoTarjetaTag;
	}

	/**
	 * Sets the estado tarjeta tag.
	 *
	 * @param estadoTarjetaTag
	 *            the new estado tarjeta tag
	 */
	public void setEstadoTarjetaTag(String estadoTarjetaTag) {
		this.estadoTarjetaTag = estadoTarjetaTag;
	}

}