/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dto;

import java.util.List;

/**
 * The Class GraficosFondosPorCuentaBPriv.
 *
 * @author juan.pablo.picate
 */
public class GraficosFondosPorCuentaBPriv {

	/** The nro cuenta titulo. */
	private String nroCuentaTitulo;

	/** The lista porcentaje. */
	private List<PorcentajeGraficoFondos> listaPorcentaje;

	/** The porcentaje tenencia pesos. */
	private double porcentajeTenenciaPesos;

	/** The graficos en cero. */
	private boolean graficosEnCero = true;

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
	 * Gets the nro cuenta titulo.
	 *
	 * @return the nroCuentaTitulo
	 */
	public String getNroCuentaTitulo() {
		return nroCuentaTitulo;
	}

	/**
	 * Sets the nro cuenta titulo.
	 *
	 * @param nroCuentaTitulo
	 *            the nroCuentaTitulo to set
	 */
	public void setNroCuentaTitulo(String nroCuentaTitulo) {
		this.nroCuentaTitulo = nroCuentaTitulo;
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

}
