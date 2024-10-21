/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * The Class GraficosBancaPersonalDTO.
 */
public class GraficosBancaPersonalDTO {

	/** mapa de graficos. */
	private Map<TipoFondoEnum, PorcentajeGraficoFondos> graficosMap;

	/** The total pesos. */
	private BigDecimal totalPesos = new BigDecimal("0");

	/** The total dolares. */
	private BigDecimal totalDolares = new BigDecimal("0");

	/**
	 * 
	 *  
	 * 
	 */
	public GraficosBancaPersonalDTO() {
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
	 * Sets the total dolares.
	 *
	 * @param totalDolares
	 *            the totalDolares to set
	 */
	public void setTotalDolares(BigDecimal totalDolares) {
		this.totalDolares = totalDolares;
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
	 * Cargar porcentajes.
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
		}
		return graficosMap;
	}

	/**
	 * Obtener lista con porcentajes.
	 *
	 * @return the grafico fondos RTL out DTO
	 */
	public GraficoFondosRTLOutDTO obtenerListaConPorcentajes() {
		GraficoFondosRTLOutDTO graficosFondosRTLOutDTO = new GraficoFondosRTLOutDTO();
		graficosFondosRTLOutDTO.getListaPorcentaje().addAll(graficosMap.values());
		for (PorcentajeGraficoFondos porcentaje : graficosFondosRTLOutDTO.getListaPorcentaje()) {
			porcentaje.setPorcentajeTenenciaPesos(
					porcentaje.calcularPorcentajeTenencia(porcentaje.getTenenciaValuadaTotalPesos(), totalPesos).intValue());
			porcentaje.setPorcentajeTenenciaDolares(porcentaje
					.calcularPorcentajeTenencia(porcentaje.getTenenciaValuadaTotalDolares(), totalDolares).intValue());
		}
		return graficosFondosRTLOutDTO;
	}

}
