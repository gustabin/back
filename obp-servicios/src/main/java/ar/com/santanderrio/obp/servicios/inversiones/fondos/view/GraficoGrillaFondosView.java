/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.view;

import java.util.List;

/**
 * The Class GraficoGrillaFondosView.
 */
public class GraficoGrillaFondosView {

	/** The grafico distribucion. */
	List<GraficoTenenciaPorFondosView> graficoDistribucion;

	/** The grafico moneda. */
	List<GraficoTenenciaFondosPorMonedaView> graficoMoneda;

	/**
	 * Gets the grafico distribucion.
	 *
	 * @return the grafico distribucion
	 */
	public List<GraficoTenenciaPorFondosView> getGraficoDistribucion() {
		return graficoDistribucion;
	}

	/**
	 * Sets the grafico distribucion.
	 *
	 * @param graficoDistribucion
	 *            the new grafico distribucion
	 */
	public void setGraficoDistribucion(List<GraficoTenenciaPorFondosView> graficoDistribucion) {
		this.graficoDistribucion = graficoDistribucion;
	}

	/**
	 * Gets the grafico moneda.
	 *
	 * @return the grafico moneda
	 */
	public List<GraficoTenenciaFondosPorMonedaView> getGraficoMoneda() {
		return graficoMoneda;
	}

	/**
	 * Sets the grafico moneda.
	 *
	 * @param graficoMoneda
	 *            the new grafico moneda
	 */
	public void setGraficoMoneda(List<GraficoTenenciaFondosPorMonedaView> graficoMoneda) {
		this.graficoMoneda = graficoMoneda;
	}

}
