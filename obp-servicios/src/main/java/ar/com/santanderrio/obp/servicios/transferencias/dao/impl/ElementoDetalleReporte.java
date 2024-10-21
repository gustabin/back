/*
 * 
 */

package ar.com.santanderrio.obp.servicios.transferencias.dao.impl;

/**
 * The Class ElementoDetalleReporte.
 */
public class ElementoDetalleReporte implements Comparable<ElementoDetalleReporte> {

	/** The descripcion. */
	private String descripcion;

	/** The valor. */
	private String valor;

	/** The sub valor. */
	private String subValor;

	/** The indice. */
	private Integer indice;

	/**
	 * Instantiates a new elemento detalle reporte.
	 *
	 * @param indice
	 *            the indice
	 * @param descripcion
	 *            the descripcion
	 * @param valor
	 *            the valor
	 */
	public ElementoDetalleReporte(int indice, String descripcion, String valor) {
		this(indice, descripcion, valor, null);
	}

	/**
	 * Instantiates a new elemento detalle reporte.
	 *
	 * @param indice
	 *            the indice
	 * @param descripcion
	 *            the descripcion
	 * @param valor
	 *            the valor
	 * @param subValor
	 *            the sub valor
	 */
	public ElementoDetalleReporte(int indice, String descripcion, String valor, String subValor) {
		super();
		this.descripcion = descripcion;
		this.valor = valor;
		this.subValor = subValor;
		this.indice = indice;
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
	 * Gets the valor.
	 *
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * Gets the indice.
	 *
	 * @return the indice
	 */
	public int getIndice() {
		return indice;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(ElementoDetalleReporte arg0) {
		return indice.compareTo(arg0.getIndice());
	}

	/**
	 * Gets the sub valor.
	 *
	 * @return the sub valor
	 */
	public String getSubValor() {
		return subValor;
	}

	/**
	 * Sets the sub valor.
	 *
	 * @param subValor
	 *            the new sub valor
	 */
	public void setSubValor(String subValor) {
		this.subValor = subValor;
	}

}
