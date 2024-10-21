/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class GraficoFondosRTLOutDTO.
 *
 * @author juan.pablo.picate
 */
public class GraficoFondosRTLOutDTO {

	/** The lista porcentaje. */
	private List<PorcentajeGraficoFondos> listaPorcentaje = new ArrayList<PorcentajeGraficoFondos>();

	/** The porcentaje tenencia pesos. */
	private double porcentajeTenenciaPesos;

	/** The graficos en cero. */
	private boolean graficosEnCero;

	/**
	 * Gets the lista porcentaje.
	 *
	 * @return the listaPorcentaje
	 */
	public List<PorcentajeGraficoFondos> getListaPorcentaje() {
		return listaPorcentaje;
	}

	/**
	 * Sets the lista porcentaje.
	 *
	 * @param listaPorcentaje
	 *            the listaPorcentaje to set
	 */
	public void setListaPorcentaje(List<PorcentajeGraficoFondos> listaPorcentaje) {
		this.listaPorcentaje = listaPorcentaje;
	}

	/**
	 * Gets the porcentaje tenencia pesos.
	 *
	 * @return the porcentajeTenenciaPesos
	 */
	public double getPorcentajeTenenciaPesos() {
		return porcentajeTenenciaPesos;
	}

	/**
	 * Sets the porcentaje tenencia pesos.
	 *
	 * @param porcentajeTenenciaPesos
	 *            the porcentajeTenenciaPesos to set
	 */
	public void setPorcentajeTenenciaPesos(double porcentajeTenenciaPesos) {
		this.porcentajeTenenciaPesos = porcentajeTenenciaPesos;
	}

	/**
	 * Checks if is graficos en cero.
	 *
	 * @return the graficosEnCero
	 */
	public boolean isGraficosEnCero() {
		return graficosEnCero;
	}

	/**
	 * Sets the graficos en cero.
	 *
	 * @param graficosEnCero
	 *            the graficosEnCero to set
	 */
	public void setGraficosEnCero(boolean graficosEnCero) {
		this.graficosEnCero = graficosEnCero;
	}

	/**
	 * Calcular porcentaje reexpresado pesos.
	 *
	 * @param valor1
	 *            the valor 1
	 * @param valor2
	 *            the valor 2
	 * @return the double
	 */
	public double calcularPorcentajeReexpresadoPesos(BigDecimal valor1, BigDecimal valor2) {
		if (BigDecimal.ZERO.equals(valor2)) {
			return 0;
		}
		valor1 = valor1.multiply(new BigDecimal("100")).divide(valor2, RoundingMode.HALF_UP);

		if (valor1.compareTo(BigDecimal.ZERO) > 0 && valor1.compareTo(BigDecimal.ONE) < 0) {
			return 1;
		}
		return valor1.doubleValue();
	}

}
