/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view;

import java.math.BigDecimal;

/**
 * The Class ProductoGraficoView.
 */
public class ProductoGraficoView {

	/** The producto. */
	private String producto;
	
	/** The valor. */
	private BigDecimal valor;
	
	/** The valor txt. */
	private String valorTxt;
	
	/** The codigo. */
	private String codigo;

	
	/**
	 * Gets the producto.
	 *
	 * @return the producto
	 */
	public String getProducto() {
		return producto;
	}

	/**
	 * Sets the producto.
	 *
	 * @param producto
	 *            the new producto
	 */
	public void setProducto(String producto) {
		this.producto = producto;
	}

	/**
	 * Gets the valor.
	 *
	 * @return the valor
	 */
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * Sets the valor.
	 *
	 * @param valor
	 *            the new valor
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	/**
	 * Gets the valor txt.
	 *
	 * @return the valor txt
	 */
	public String getValorTxt() {
		return valorTxt;
	}

	/**
	 * Sets the valor txt.
	 *
	 * @param valorTxt
	 *            the new valor txt
	 */
	public void setValorTxt(String valorTxt) {
		this.valorTxt = valorTxt;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Sets the codigo.
	 *
	 * @param codigo
	 *            the new codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
}
