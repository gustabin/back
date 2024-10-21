/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.view;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * The Class TenenciaPorProductoView.
 *
 * @author juan.pablo.picate
 */
public class TenenciaPorProductoView {

	/** The producto. */
	private String producto;

	/** The tenenciaValuadaHoy. */
	private String tenenciaValuadaHoy;

	/** The tenenciaValuadaCosto. */
	private String tenenciaValuadaCosto;

	/** The resultado. */
	private String resultado;

	/** The porcentaje. */
	private int porcentaje;

	/**
	 * Gets the porcentaje.
	 *
	 * @return the porcentaje
	 */
	public int getPorcentaje() {
		return porcentaje;
	}

	/**
	 * Sets the porcentaje.
	 *
	 * @param porcentaje
	 *            the porcentaje to set
	 */
	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}

	/**
	 * Gets the tenencia valuada hoy.
	 *
	 * @return the tenenciaValuadaHoy
	 */
	public String getTenenciaValuadaHoy() {
		return tenenciaValuadaHoy;
	}

	/**
	 * Sets the tenencia valuada hoy.
	 *
	 * @param tenenciaValuadaHoy
	 *            the tenenciaValuadaHoy to set
	 */
	public void setTenenciaValuadaHoy(String tenenciaValuadaHoy) {
		this.tenenciaValuadaHoy = tenenciaValuadaHoy;
	}

	/**
	 * Gets the tenencia valuada costo.
	 *
	 * @return the tenenciaValuadaCosto
	 */
	public String getTenenciaValuadaCosto() {
		return tenenciaValuadaCosto;
	}

	/**
	 * Sets the tenencia valuada costo.
	 *
	 * @param tenenciaValuadaCosto
	 *            the tenenciaValuadaCosto to set
	 */
	public void setTenenciaValuadaCosto(String tenenciaValuadaCosto) {
		this.tenenciaValuadaCosto = tenenciaValuadaCosto;
	}

	/**
	 * Gets the resultado.
	 *
	 * @return the resultado
	 */
	public String getResultado() {
		return resultado;
	}

	/**
	 * Sets the resultado.
	 *
	 * @param resultado
	 *            the resultado to set
	 */
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

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
	 *            the producto to set
	 */
	public void setProducto(String producto) {
		this.producto = producto;
	}

	/**
	 * Obtener el porcentaje de tenencia de cada producto .
	 *
	 * @param valor1
	 *            the valor 1
	 * @param valor2
	 *            the valor 2
	 * @return the double
	 */
	public BigDecimal obtenerPorcentaje(BigDecimal valor1, BigDecimal valor2) {
		if (valor2.compareTo(BigDecimal.valueOf(0)) == 0) {
			return BigDecimal.valueOf(0);
		}

		valor1 = valor1.multiply(BigDecimal.valueOf(100)).divide(valor2, 2, RoundingMode.HALF_UP);

		if (valor1.compareTo(BigDecimal.valueOf(1)) == -1) {
			if (valor1.compareTo(BigDecimal.valueOf(0)) == 1) {
				return BigDecimal.valueOf(1);
			}
		}
		return valor1;
	}
}
