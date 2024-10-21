/*
 * 
 */
package ar.com.santanderrio.base.web.view;

/**
 * The Class EnlaceView.
 */
public class EnlaceView extends View {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The titulo. */
	private String titulo;

	/** The link. */
	private String link;

	/**
	 * Instantiates a new enlace view.
	 */
	public EnlaceView() {
		super();
	}

	/**
	 * Instantiates a new enlace view.
	 *
	 * @param titulo
	 *            the titulo
	 * @param link
	 *            the link
	 */
	public EnlaceView(String titulo, String link) {
		this.titulo = titulo;
		this.link = link;
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
	 * Setter para titulo.
	 *
	 * @param titulo
	 *            el nuevo titulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
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
	 * Setter para link.
	 *
	 * @param link
	 *            el nuevo link
	 */
	public void setLink(String link) {
		this.link = link;
	}
}
