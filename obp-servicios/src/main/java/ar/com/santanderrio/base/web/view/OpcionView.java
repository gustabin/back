/*
 * 
 */
package ar.com.santanderrio.base.web.view;

/**
 * The Class OpcionView.
 */
public class OpcionView extends View {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The texto. */
	// texto de la opcion
	private String texto;

	/** The valor. */
	// valor que identifica a la opcion
	private String valor;

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
	 * Gets the valor.
	 *
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * Setter para valor.
	 *
	 * @param valor
	 *            el nuevo valor
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

}
