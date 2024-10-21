/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dto;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase para obtener los porcentajes correspondientes a los graficos de fondos
 * para ambas bancas.
 *
 * @author juan.pablo.picate
 */
public class GraficosFondosDTO {

	/** mapa de graficos. */
	private Map<TipoFondoEnum, PorcentajeGraficoFondos> graficosMap;

	/** The total pesos. */
	private BigDecimal totalPesos = new BigDecimal("0");

	/** The total dolares. */
	private BigDecimal totalDolares = new BigDecimal("0");

	/** The tenencia reexp en pesos. */
	private BigDecimal tenenciaReexpEnPesos = new BigDecimal("0");

	/** The graficos en cero. */
	private boolean graficosEnCero = true;

	/**
	 * Constructor del map donde se cargaran los porcentajes para los graficos.
	 * 
	 */
	public GraficosFondosDTO() {
		graficosMap = new HashMap<TipoFondoEnum, PorcentajeGraficoFondos>();
		for (TipoFondoEnum tipoEnum : TipoFondoEnum.values()) {
			graficosMap.put(tipoEnum, new PorcentajeGraficoFondos());
			graficosMap.get(tipoEnum).setTipoFondo(tipoEnum.toString());
		}
	}

	/**
	 * Gets the total pesos.
	 *
	 * @return the totalPesos
	 */
	public BigDecimal getTotalPesos() {
		return totalPesos;
	}

	/**
	 * Sets the total pesos.
	 *
	 * @param totalPesos
	 *            the totalPesos to set
	 */
	public void setTotalPesos(BigDecimal totalPesos) {
		this.totalPesos = totalPesos;
	}

	/**
	 * Gets the total dolares.
	 *
	 * @return the totalDolares
	 */
	public BigDecimal getTotalDolares() {
		return totalDolares;
	}

	/**
	 * Gets the tenencia reexp en pesos.
	 *
	 * @return the tenenciaReexpEnPesos
	 */
	public BigDecimal getTenenciaReexpEnPesos() {
		return tenenciaReexpEnPesos;
	}

	/**
	 * Sets the tenencia reexp en pesos.
	 *
	 * @param tenenciaReexpEnPesos
	 *            the tenenciaReexpEnPesos to set
	 */
	public void setTenenciaReexpEnPesos(BigDecimal tenenciaReexpEnPesos) {
		this.tenenciaReexpEnPesos = tenenciaReexpEnPesos;
	}

	/**
	 * Sets the total dolares.
	 *
	 * @param totalDolares
	 *            the totalDolares to set
	 */
	public void setTotalDolares(BigDecimal totalDolares) {
		this.totalDolares = totalDolares;
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
	 * Adds the total pesos.
	 *
	 * @param valor
	 *            the valor
	 */
	public void addTotalPesos(BigDecimal valor) {
		this.totalPesos = this.totalPesos.add(valor);
	}

	/**
	 * Adds the total dolares.
	 *
	 * @param valor
	 *            the valor
	 */
	public void addTotalDolares(BigDecimal valor) {
		this.totalDolares = this.totalDolares.add(valor);
	}

	/**
	 * Adds the tenencia reexp pesos.
	 *
	 * @param valor
	 *            the valor
	 */
	public void addTenenciaReexpPesos(BigDecimal valor) {
		this.tenenciaReexpEnPesos = this.tenenciaReexpEnPesos.add(valor);
	}

	/**
	 * Carga de los datos para poder calcular los porcentajes de graficos de
	 * fondos.
	 *
	 * @param tenencia
	 *            the tenencia
	 * @return the map
	 */
	public Map<TipoFondoEnum, PorcentajeGraficoFondos> cargarPorcentajes(TenenciaPorFondoDTO tenencia) {
		for (TenenciaFondosSuscritosDTO tenenciaFondo : tenencia.getTenenciaFondosSuscritosPesos()) {
			graficosMap.get(TipoFondoEnum.fromCodigoProductoPesos(tenenciaFondo.getCodigoAgrupador().trim()))
					.addTenenciaValuadaTotalPesos(tenenciaFondo.getValuacion());
			addTotalPesos(tenenciaFondo.getValuacion());
		}

		for (TenenciaFondosSuscritosDTO tenenciaFondo : tenencia.getTenenciaFondosSuscritosDolares()) {
			graficosMap.get(TipoFondoEnum.fromCodigoProductoDolares(tenenciaFondo.getCodigoAgrupador().trim()))
					.addTenenciaValuadaTotalDolares(tenenciaFondo.getValuacion());
			addTotalDolares(tenenciaFondo.getValuacion());
			addTenenciaReexpPesos(tenenciaFondo.getTenenciaValuadaReexp());
		}
		return graficosMap;
	}

	/**
	 * Calculo de porcentajes para los graficos de fondos.
	 *
	 * @return the grafico fondos RTL out DTO
	 */
	public GraficoFondosRTLOutDTO obtenerListaConPorcentajes() {
		GraficoFondosRTLOutDTO graficosFondosRTLOutDTO = new GraficoFondosRTLOutDTO();
		graficosFondosRTLOutDTO.getListaPorcentaje().addAll(graficosMap.values());
		Collections.sort(graficosFondosRTLOutDTO.getListaPorcentaje());
		for (PorcentajeGraficoFondos porcentaje : graficosFondosRTLOutDTO.getListaPorcentaje()) {
			porcentaje.setPorcentajeTenenciaPesos(
					porcentaje.calcularPorcentajeTenencia(porcentaje.getTenenciaValuadaTotalPesos(), totalPesos).intValue());
			porcentaje.setPorcentajeTenenciaDolares(porcentaje
					.calcularPorcentajeTenencia(porcentaje.getTenenciaValuadaTotalDolares(), totalDolares).intValue());
			if (porcentaje.getPorcentajeTenenciaPesos() != 0 || porcentaje.getPorcentajeTenenciaDolares() != 0) {
				this.graficosEnCero = false;
			}
		}
		graficosFondosRTLOutDTO.setPorcentajeTenenciaPesos(graficosFondosRTLOutDTO
				.calcularPorcentajeReexpresadoPesos(totalPesos, (totalPesos.add(tenenciaReexpEnPesos))));
		return graficosFondosRTLOutDTO;
	}
}
