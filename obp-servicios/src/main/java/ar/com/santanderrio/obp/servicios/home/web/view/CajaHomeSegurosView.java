/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.view;

// TODO: Auto-generated Javadoc
/**
 * The Class CajaHomeCalendarioPagosView.
 */
public class CajaHomeSegurosView extends Caja {

	/** The titulo. */
	private String titulo;

	/** The is calendario pagos. */
	private Boolean isSeguro = Boolean.TRUE;

	/** The id seguro. */
	private int idSeguro;

	/** The encabezado. */
	private String encabezado;

	/** The cantidad. */
	private int cantidad;

	/** The icono. */
	private String icono;
	
	/** The recargar. */
	private boolean recargar; 
	
	/** The texto link. */
	private String textoLink;
	


	/**
	 * Gets the id seguro.
	 *
	 * @return the idSeguro
	 */
	public int getIdSeguro() {
		return idSeguro;
	}

	/**
	 * Sets the id seguro.
	 *
	 * @param idSeguro
	 *            the idSeguro to set
	 */
	public void setIdSeguro(int idSeguro) {
		this.idSeguro = idSeguro;
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
	 *            the encabezado to set
	 */
	public void setEncabezado(String encabezado) {
		this.encabezado = encabezado;
	}

	/**
	 * Gets the cantidad.
	 *
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * Sets the cantidad.
	 *
	 * @param cantidad
	 *            the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * Gets the icono.
	 *
	 * @return the icono
	 */
	public String getIcono() {
		return icono;
	}

	/**
	 * Sets the icono.
	 *
	 * @param icono
	 *            the icono to set
	 */
	public void setIcono(String icono) {
		this.icono = icono;
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
	 * Gets the checks if is seguro.
	 *
	 * @return the checks if is seguro
	 */
	public Boolean getIsSeguro() {
		return isSeguro;
	}

	/**
	 * Sets the checks if is seguro.
	 *
	 * @param isSeguro
	 *            the new checks if is seguro
	 */
	public void setIsSeguro(Boolean isSeguro) {
		this.isSeguro = isSeguro;
	}
	
	/**
	 * Checks if is recargar.
	 *
	 * @return true, if is recargar
	 */
	public boolean isRecargar() {
		return recargar;
	}

	/**
	 * Sets the recargar.
	 *
	 * @param recargar the new recargar
	 */
	public void setRecargar(boolean recargar) {
		this.recargar = recargar;
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
