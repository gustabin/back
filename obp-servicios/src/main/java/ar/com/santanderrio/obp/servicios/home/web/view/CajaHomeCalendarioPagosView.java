/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.view;

/**
 * The Class CajaHomeCalendarioPagosView.
 */
public class CajaHomeCalendarioPagosView extends Caja {

	/** The titulo. */
	private String titulo;

	/** The is calendario pagos. */
	private Boolean isCalendarioPagos;

	/** The is encabezado. */
	private String encabezado;
	
	/** The texto link. */
	private String textoLink;

	/**
	 * Gets the checks if is calendario pagos.
	 *
	 * @return the isCalendarioPagos
	 */
	public Boolean getIsCalendarioPagos() {
		return isCalendarioPagos;
	}

	/**
	 * Sets the checks if is calendario pagos.
	 *
	 * @param isCalendarioPagos
	 *            the isCalendarioPagos to set
	 */
	public void setIsCalendarioPagos(Boolean isCalendarioPagos) {
		this.isCalendarioPagos = isCalendarioPagos;
	}

	/**
	 * Gets the titulo.
	 *
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Sets the titulo.
	 *
	 * @param titulo
	 *            the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Gets the encabezado.
	 *
	 * @return the encabezado
	 */
	public String getEncabezado() {
		return encabezado;
	}

	/**
	 * Sets the encabezado.
	 *
	 * @param encabezado
	 *            the new encabezado
	 */
	public void setEncabezado(String encabezado) {
		this.encabezado = encabezado;
	}

    /**
	 * Gets the texto link.
	 *
	 * @return the texto link
	 */
    public String getTextoLink() {
        return textoLink;
    }

    /**
	 * Sets the texto link.
	 *
	 * @param textoLink
	 *            the new texto link
	 */
    public void setTextoLink(String textoLink) {
        this.textoLink = textoLink;
    }

}
