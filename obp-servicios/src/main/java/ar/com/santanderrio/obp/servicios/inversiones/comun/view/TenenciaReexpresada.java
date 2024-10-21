/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.view;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * The Class TenenciaReexpresada.
 *
 * @author juan.pablo.picate
 */
public class TenenciaReexpresada {

	/** The tenencia. */
	private String tenencia;

	/** The tenenciaExpresadaOtraMoneda. */
	private String tenenciaExpresadaOtraMoneda;

	/** The tenenciaEnMoneda. */
	private String tenenciaEnMoneda;

	/** The totalTenenciaReexpresada. */
	private String totalTenenciaReexpresada;

	/** The moneda. */
	private String moneda;

	/** The Porcentaje. */
	private int porcentaje;

	/**
	 * Gets the tenencia.
	 *
	 * @return the tenencia
	 */
	public String getTenencia() {
		return tenencia;
	}

	/**
	 * Sets the tenencia.
	 *
	 * @param tenencia
	 *            the tenencia to set
	 */
	public void setTenencia(String tenencia) {
		this.tenencia = tenencia;
	}

	/**
	 * Gets the tenencia expresada otra moneda.
	 *
	 * @return the tenenciaExpresadaOtraMoneda
	 */
	public String getTenenciaExpresadaOtraMoneda() {
		return tenenciaExpresadaOtraMoneda;
	}

	/**
	 * Sets the tenencia expresada otra moneda.
	 *
	 * @param tenenciaExpresadaOtraMoneda
	 *            the tenenciaExpresadaOtraMoneda to set
	 */
	public void setTenenciaExpresadaOtraMoneda(String tenenciaExpresadaOtraMoneda) {
		this.tenenciaExpresadaOtraMoneda = tenenciaExpresadaOtraMoneda;
	}

	/**
	 * Gets the tenencia en moneda.
	 *
	 * @return the tenenciaEnMoneda
	 */
	public String getTenenciaEnMoneda() {
		return tenenciaEnMoneda;
	}

	/**
	 * Sets the tenencia en moneda.
	 *
	 * @param tenenciaEnMoneda
	 *            the tenenciaEnMoneda to set
	 */
	public void setTenenciaEnMoneda(String tenenciaEnMoneda) {
		this.tenenciaEnMoneda = tenenciaEnMoneda;
	}

	/**
	 * Gets the total tenencia reexpresada.
	 *
	 * @return the totalTenenciaReexpresada
	 */
	public String getTotalTenenciaReexpresada() {
		return totalTenenciaReexpresada;
	}

	/**
	 * Sets the total tenencia reexpresada.
	 *
	 * @param totalTenenciaReexpresada
	 *            the totalTenenciaReexpresada to set
	 */
	public void setTotalTenenciaReexpresada(String totalTenenciaReexpresada) {
		this.totalTenenciaReexpresada = totalTenenciaReexpresada;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

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
	 * Obtener porcentaje.
	 *
	 * @param valor1
	 *            the valor 1
	 * @param valor2
	 *            the valor 2
	 * @return the big decimal
	 */
	public BigDecimal obtenerPorcentaje(BigDecimal valor1, BigDecimal valor2) {
		if (valor2.compareTo(BigDecimal.valueOf(0)) == 0) {
			return BigDecimal.valueOf(0);
		}
		return valor1.multiply(BigDecimal.valueOf(100)).divide(valor2, 2, RoundingMode.HALF_UP);
	}
}
