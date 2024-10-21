/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * The Class PorcentajeGraficoFondos.
 *
 * @author juan.pablo.picate
 */
public class PorcentajeGraficoFondos implements Comparable<PorcentajeGraficoFondos> {

	/** The tipo fondo. */
	private String tipoFondo;

	/** The tenencia valuada total pesos. */
	private BigDecimal tenenciaValuadaTotalPesos = new BigDecimal("0");

	/** The tenencia valuada total dolares. */
	private BigDecimal tenenciaValuadaTotalDolares = new BigDecimal("0");

	/** The porcentaje tenencia pesos. */
	private int porcentajeTenenciaPesos;

	/** The porcentaje tenencia dolares. */
	private int porcentajeTenenciaDolares;

	/**
	 * Gets the tipo fondo.
	 *
	 * @return the tipoFondo
	 */
	public String getTipoFondo() {
		return tipoFondo;
	}

	/**
	 * Sets the tipo fondo.
	 *
	 * @param tipoFondo
	 *            the tipoFondo to set
	 */
	public void setTipoFondo(String tipoFondo) {
		this.tipoFondo = tipoFondo;
	}

	/**
	 * Gets the porcentaje tenencia pesos.
	 *
	 * @return the porcentajeTenenciaPesos
	 */
	public int getPorcentajeTenenciaPesos() {
		return porcentajeTenenciaPesos;
	}

	/**
	 * Sets the porcentaje tenencia pesos.
	 *
	 * @param porcentajeTenenciaPesos
	 *            the porcentajeTenenciaPesos to set
	 */
	public void setPorcentajeTenenciaPesos(int porcentajeTenenciaPesos) {
		this.porcentajeTenenciaPesos = porcentajeTenenciaPesos;
	}

	/**
	 * Gets the porcentaje tenencia dolares.
	 *
	 * @return the porcentajeTenenciaDolares
	 */
	public int getPorcentajeTenenciaDolares() {
		return porcentajeTenenciaDolares;
	}

	/**
	 * Sets the porcentaje tenencia dolares.
	 *
	 * @param porcentajeTenenciaDolares
	 *            the porcentajeTenenciaDolares to set
	 */
	public void setPorcentajeTenenciaDolares(int porcentajeTenenciaDolares) {
		this.porcentajeTenenciaDolares = porcentajeTenenciaDolares;
	}

	/**
	 * Gets the tenencia valuada total pesos.
	 *
	 * @return the tenenciaValuadaTotalPesos
	 */
	public BigDecimal getTenenciaValuadaTotalPesos() {
		return tenenciaValuadaTotalPesos;
	}

	/**
	 * Sets the tenencia valuada total pesos.
	 *
	 * @param tenenciaValuadaTotalPesos
	 *            the tenenciaValuadaTotalPesos to set
	 */
	public void setTenenciaValuadaTotalPesos(BigDecimal tenenciaValuadaTotalPesos) {
		this.tenenciaValuadaTotalPesos = tenenciaValuadaTotalPesos;
	}

	/**
	 * Adds the tenencia valuada total pesos.
	 *
	 * @param valor
	 *            the valor
	 */
	public void addTenenciaValuadaTotalPesos(BigDecimal valor) {
		this.tenenciaValuadaTotalPesos = this.tenenciaValuadaTotalPesos.add(valor);
	}

	/**
	 * Gets the tenencia valuada total dolares.
	 *
	 * @return the tenenciaValuadaTotalDolares
	 */
	public BigDecimal getTenenciaValuadaTotalDolares() {
		return tenenciaValuadaTotalDolares;
	}

	/**
	 * Sets the tenencia valuada total dolares.
	 *
	 * @param tenenciaValuadaTotalDolares
	 *            the tenenciaValuadaTotalDolares to set
	 */
	public void setTenenciaValuadaTotalDolares(BigDecimal tenenciaValuadaTotalDolares) {
		this.tenenciaValuadaTotalDolares = tenenciaValuadaTotalDolares;
	}

	/**
	 * Adds the tenencia valuada total dolares.
	 *
	 * @param valor
	 *            the valor
	 */
	public void addTenenciaValuadaTotalDolares(BigDecimal valor) {
		this.tenenciaValuadaTotalDolares = this.tenenciaValuadaTotalDolares.add(valor);
	}

	/**
	 * Calcular porcentaje tenencia.
	 *
	 * @param valor1
	 *            the valor 1
	 * @param valor2
	 *            the valor 2
	 * @return the BigDecimal
	 */
	public BigDecimal calcularPorcentajeTenencia(BigDecimal valor1, BigDecimal valor2) {
	    if(BigDecimal.ZERO.equals(valor2)){
	        return valor2;
	    }
		BigDecimal resultado = valor1.multiply(new BigDecimal("100")).divide(valor2, RoundingMode.HALF_UP);
		if (resultado.compareTo(BigDecimal.ZERO) > 0 && resultado.compareTo(BigDecimal.ONE) < 0) {
			return BigDecimal.ONE;
		}
		return resultado;
	}

	/**
	 * Gets the ordinal.
	 *
	 * @return the ordinal
	 */
	public int getOrdinal() {
		if ("MONEY_MARKET".equals(this.getTipoFondo())) {
			return 1;
		}
		if ("RENTA_FIJA".equals(this.getTipoFondo())) {
			return 2;
		}
		if ("RENTA_VARIABLE".equals(this.getTipoFondo())) {
			return 3;
		}
		if ("RENTA_MIXTA".equals(this.getTipoFondo())) {
			return 4;
		}
		if ("OTROS".equals(this.getTipoFondo())) {
			return 5;
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(PorcentajeGraficoFondos porcentajeGraficoFondos) {
		if (this.getOrdinal() < porcentajeGraficoFondos.getOrdinal()) {
			return -1;
		} else {
			return 1;
		}
	}

}
