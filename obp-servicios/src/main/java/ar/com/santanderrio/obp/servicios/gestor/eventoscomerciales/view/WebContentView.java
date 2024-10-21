/*
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class WebContentView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class WebContentView {

    /** The titulo. */
    private String titulo;

    /** The descripcion. */
    private String descripcion;

    /** The link. */
    private String link;
    
    /** The boton. */
    private String boton;
    
    /** The url. */
    private String dataUrl;

    /** The tipo acceso. */
    private String tipoAcceso;
    
    /** data */
    private String data;
    
    @JsonSerialize(include = Inclusion.NON_NULL)
    private Boolean esDeeplink = Boolean.FALSE;

    private Object microfront;

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
	 *            the new titulo
	 */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
    public String getDescripcion() {
        return descripcion;
    }

    /**
	 * Sets the descripcion.
	 *
	 * @param descripcion
	 *            the new descripcion
	 */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
	 * Gets the link.
	 *
	 * @return the link
	 */
    public String getLink() {
        return link;
    }

    /**
	 * Sets the link.
	 *
	 * @param link
	 *            the new link
	 */
    public void setLink(String link) {
        this.link = link;
    }

    /**
	 * Gets the boton.
	 *
	 * @return the boton
	 */
    public String getBoton() {
        return boton;
    }

    /**
	 * Sets the boton.
	 *
	 * @param boton
	 *            the new boton
	 */
    public void setBoton(String boton) {
        this.boton = boton;
    }

	public String getDataUrl() {
		return dataUrl;
	}

	public void setDataUrl(String dataUrl) {
		this.dataUrl = dataUrl;
	}

	public String getTipoAcceso() {
		return tipoAcceso;
	}

	public void setTipoAcceso(String tipoAcceso) {
		this.tipoAcceso = tipoAcceso;
	}
    
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Boolean getEsDeeplink() {
		return esDeeplink;
	}

	public void setEsDeeplink(Boolean esDeeplink) {
		this.esDeeplink = esDeeplink;
	}

	public Object getMicrofront() {
		return this.microfront;
	}

	public void setMicrofront(Object microfront) {
		this.microfront = microfront;
	}
    
}
