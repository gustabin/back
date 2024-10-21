/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.view;

/**
 * The Class CajaHomePrestamosView.
 */
public class CajaHomePrestamosView extends Caja {

	/** The titulo. */
	private String titulo;

	/** The is prestamos. */
	private Boolean isPrestamos;

	/** The encabezado. */
	private String encabezado;

	/** The is hipotecario. */
	private Boolean isHipotecario;

	/** The is prendario. */
	private Boolean isPrendario;

	/** The is personal. */
	private Boolean isPersonal;

	/** The cantidad. */
	private Integer cantidad;
	
	/** The texto link. */
	private String textoLink;
	
	/** The linea crediticia. */
	private String lineaCrediticia;

	/**
	 * Gets the checks if is prestamos.
	 *
	 * @return the isPrestamo
	 */
	public Boolean getIsPrestamos() {
		return isPrestamos;
	}

	/**
	 * Sets the checks if is prestamos.
	 *
	 * @param isPrestamos
	 *            the new checks if is prestamos
	 */
	public void setIsPrestamos(Boolean isPrestamos) {
		this.isPrestamos = isPrestamos;
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
	 * Gets the checks if is hipotecario.
	 *
	 * @return the isHipotecario
	 */
	public Boolean getIsHipotecario() {
		return isHipotecario;
	}

	/**
	 * Sets the checks if is hipotecario.
	 *
	 * @param isHipotecario
	 *            the isHipotecario to set
	 */
	public void setIsHipotecario(Boolean isHipotecario) {
		this.isHipotecario = isHipotecario;
	}

	/**
	 * Gets the checks if is prendario.
	 *
	 * @return the isPrendario
	 */
	public Boolean getIsPrendario() {
		return isPrendario;
	}

	/**
	 * Sets the checks if is prendario.
	 *
	 * @param isPrendario
	 *            the isPrendario to set
	 */
	public void setIsPrendario(Boolean isPrendario) {
		this.isPrendario = isPrendario;
	}

	/**
	 * Gets the checks if is personal.
	 *
	 * @return the isPersonal
	 */
	public Boolean getIsPersonal() {
		return isPersonal;
	}

	/**
	 * Sets the checks if is personal.
	 *
	 * @param isPersonal
	 *            the isPersonal to set
	 */
	public void setIsPersonal(Boolean isPersonal) {
		this.isPersonal = isPersonal;
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
	public Integer getCantidad() {
		return cantidad;
	}

	/**
	 * Sets the cantidad.
	 *
	 * @param cantidad
	 *            the cantidad to set
	 */
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
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

	public String getLineaCrediticia() {
		return lineaCrediticia;
	}

	public void setLineaCrediticia(String lineaCrediticia) {
		this.lineaCrediticia = lineaCrediticia;
	}
    
}
