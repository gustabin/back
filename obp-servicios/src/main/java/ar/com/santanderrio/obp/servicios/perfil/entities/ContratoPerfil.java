/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.entities;

/**
 * The class ContratoPerfil.
 */
public class ContratoPerfil {

    /** The titulo. */
    private String titulo;

    /** The url. */
    private String url;

    /**
	 * Instantiates a new contrato perfil.
	 */
    public ContratoPerfil() {
        super();
    }

    /**
	 * Instantiates a new contrato perfil.
	 *
	 * @param titulo
	 *            the titulo
	 * @param url
	 *            the url
	 */
    public ContratoPerfil(String titulo, String url) {
        this.titulo = titulo;
        this.url = url;
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
	 * Gets the url.
	 *
	 * @return the url
	 */
    public String getUrl() {
        return url;
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
	 * Sets the url.
	 *
	 * @param url
	 *            the url to set
	 */
    public void setUrl(String url) {
        this.url = url;
    }

}
