/*
 * 
 */
package ar.com.santanderrio.obp.servicios.ofertacontextual.web.view;

import java.net.URL;

import ar.com.santanderrio.base.web.view.OpcionView;
import ar.com.santanderrio.base.web.view.View;

/**
 * The Class OfertaContextual.
 */
public class OfertaContextual extends View {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The texto. */
	private String texto;

	/** The link oferta. */
	private OpcionView linkOferta;

	/** The offset link. */
	private int offsetLink;

	/** The url imagen. */
	private URL urlImagen;

	/**
	 * Gets the texto.
	 *
	 * @return the texto
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * Setter para texto.
	 *
	 * @param texto
	 *            el nuevo texto
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}

	/**
	 * Gets the link oferta.
	 *
	 * @return the link oferta
	 */
	public OpcionView getLinkOferta() {
		return linkOferta;
	}

	/**
	 * Setter para link oferta.
	 *
	 * @param linkOferta
	 *            el nuevo link oferta
	 */
	public void setLinkOferta(OpcionView linkOferta) {
		this.linkOferta = linkOferta;
	}

	/**
	 * Gets the offset link.
	 *
	 * @return the offset link
	 */
	public int getOffsetLink() {
		return offsetLink;
	}

	/**
	 * Setter para offset link.
	 *
	 * @param offsetLink
	 *            el nuevo offset link
	 */
	public void setOffsetLink(int offsetLink) {
		this.offsetLink = offsetLink;
	}

	/**
	 * Gets the url imagen.
	 *
	 * @return the url imagen
	 */
	public URL getUrlImagen() {
		return urlImagen;
	}

	/**
	 * Setter para url imagen.
	 *
	 * @param urlImagen
	 *            el nuevo url imagen
	 */
	public void setUrlImagen(URL urlImagen) {
		this.urlImagen = urlImagen;
	}

}
