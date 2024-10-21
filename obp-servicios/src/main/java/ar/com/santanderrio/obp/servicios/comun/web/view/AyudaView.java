/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.web.view;

/**
 * Template HTML de ayuda en transferencias agendamiento auto/recordatorio.
 *
 * @author B041299
 */
public class AyudaView {

	/** The titulo. */
	private String titulo;

	/** The templatem (HTML), o mensaje. */
	private String template;

	/**
	 * Instantiates a new ayuda view.
	 *
	 * @param titulo
	 *            the titulo
	 * @param ayudaTransferenciaAuto
	 *            the ayuda transferencia auto
	 */
	public AyudaView(String titulo, String ayudaTransferenciaAuto) {
		setTitulo(titulo);
		setTemplate(ayudaTransferenciaAuto);
	}

	/**
	 * Gets the template.
	 *
	 * @return the mensaje
	 */
	public String getTemplate() {
		return template;
	}

	/**
	 * Sets the template.
	 *
	 * @param template
	 *            the new template
	 */
	public void setTemplate(String template) {
		this.template = template;
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

}
