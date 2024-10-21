/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.view;

/**
 * The Class ItemView.
 */
public class ItemView {

	/** The label. */
	private String label;

	/** The valor. */
	private String valor;

	/** The valor 2. */
	private String valor2;

	/**
	 * Instantiates a new item view.
	 */
	public ItemView() {
		super();
	}

	/**
	 * Instantiates a new item view.
	 *
	 * @param label
	 *            the label
	 * @param valor
	 *            the valor
	 */
	public ItemView(String label, String valor) {
		this.label = label;
		this.valor = valor;
	}

	/**
	 * Instantiates a new item view.
	 *
	 * @param label
	 *            the label
	 * @param valor
	 *            the valor
	 * @param valor2
	 *            the valor 2
	 */
	public ItemView(String label, String valor, String valor2) {
		this.label = label;
		this.valor = valor;
		this.valor2 = valor2;
	}

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Sets the label.
	 *
	 * @param label
	 *            the new label
	 */
	public void setLabel(String label) {
		this.label = label;
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
	 * Sets the valor.
	 *
	 * @param valor
	 *            the new valor
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/**
	 * Gets the valor 2.
	 *
	 * @return the valor 2
	 */
	public String getValor2() {
		return valor2;
	}

	/**
	 * Sets the valor 2.
	 *
	 * @param valor2
	 *            the new valor 2
	 */
	public void setValor2(String valor2) {
		this.valor2 = valor2;
	}

}
