/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DetallePorCabecera.
 */
public class DetallePorCabecera {
	
	/** The Moneda. */
	@JsonProperty("GUID")
	private String guidError;
	
	/** The Codigo Cabecera. */
	@JsonProperty("CodigoCabecera")
	private String codigoCabecera;
	
	/** The DescripcionCabecera. */
	@JsonProperty("DescripcionCabecera")
	private String descripcionCabecera;
	
	/** The RentabilidadNeta. */
	@JsonProperty("RentabilidadNeta")
	private BigDecimal rentabilidadNeta;
	
	/** The Rendimiento. */
	@JsonProperty("Rendimiento")
	private BigDecimal rendimiento;
	
	/** The DistribucionRent. */
	@JsonProperty("DistribucionRent")
	private BigDecimal distribucionRent;
	
	/** The RentabilidadNeta. */
	@JsonProperty("DistribucionRend")
	private BigDecimal distribucionRend;
	
	/** The GraficovDisponibe Rent. */
	@JsonProperty("GraficoDisponibleRent")
	private String graficoDisponibleRent;
	
	/** The Graficov Disponible Rend. */
	@JsonProperty("GraficoDisponibleRend")
	private String graficoDisponibleRend;

	/**
	 * Gets the guid error.
	 *
	 * @return the guid error
	 */
	public String getGuidError() {
		return guidError;
	}

	/**
	 * Sets the guid error.
	 *
	 * @param guidError
	 *            the new guid error
	 */
	public void setGuidError(String guidError) {
		this.guidError = guidError;
	}

	/**
	 * Gets the codigo cabecera.
	 *
	 * @return the codigo cabecera
	 */
	public String getCodigoCabecera() {
		return codigoCabecera;
	}

	/**
	 * Sets the codigo cabecera.
	 *
	 * @param codigoCabecera
	 *            the new codigo cabecera
	 */
	public void setCodigoCabecera(String codigoCabecera) {
		this.codigoCabecera = codigoCabecera;
	}

	/**
	 * Gets the descripcion cabecera.
	 *
	 * @return the descripcion cabecera
	 */
	public String getDescripcionCabecera() {
		return descripcionCabecera;
	}

	/**
	 * Sets the descripcion cabecera.
	 *
	 * @param descripcionCabecera
	 *            the new descripcion cabecera
	 */
	public void setDescripcionCabecera(String descripcionCabecera) {
		this.descripcionCabecera = descripcionCabecera;
	}

	/**
	 * Gets the rentabilidad neta.
	 *
	 * @return the rentabilidad neta
	 */
	public BigDecimal getRentabilidadNeta() {
		return rentabilidadNeta;
	}

	/**
	 * Sets the rentabilidad neta.
	 *
	 * @param rentabilidadNeta
	 *            the new rentabilidad neta
	 */
	public void setRentabilidadNeta(BigDecimal rentabilidadNeta) {
		this.rentabilidadNeta = rentabilidadNeta;
	}

	/**
	 * Gets the rendimiento.
	 *
	 * @return the rendimiento
	 */
	public BigDecimal getRendimiento() {
		return rendimiento;
	}

	/**
	 * Sets the rendimiento.
	 *
	 * @param rendimiento
	 *            the new rendimiento
	 */
	public void setRendimiento(BigDecimal rendimiento) {
		this.rendimiento = rendimiento;
	}

	/**
	 * Gets the distribucion rent.
	 *
	 * @return the distribucion rent
	 */
	public BigDecimal getDistribucionRent() {
		return distribucionRent;
	}

	/**
	 * Sets the distribucion rent.
	 *
	 * @param distribucionRent
	 *            the new distribucion rent
	 */
	public void setDistribucionRent(BigDecimal distribucionRent) {
		this.distribucionRent = distribucionRent;
	}

	/**
	 * Gets the distribucion rend.
	 *
	 * @return the distribucion rend
	 */
	public BigDecimal getDistribucionRend() {
		return distribucionRend;
	}

	/**
	 * Sets the distribucion rend.
	 *
	 * @param distribucionRend
	 *            the new distribucion rend
	 */
	public void setDistribucionRend(BigDecimal distribucionRend) {
		this.distribucionRend = distribucionRend;
	}

	/**
	 * Gets the grafico disponible rent.
	 *
	 * @return the grafico disponible rent
	 */
	public String getGraficoDisponibleRent() {
		return graficoDisponibleRent;
	}

	/**
	 * Sets the grafico disponible rent.
	 *
	 * @param graficoDisponibleRent
	 *            the new grafico disponible rent
	 */
	public void setGraficoDisponibleRent(String graficoDisponibleRent) {
		this.graficoDisponibleRent = graficoDisponibleRent;
	}

	/**
	 * Gets the grafico disponible rend.
	 *
	 * @return the grafico disponible rend
	 */
	public String getGraficoDisponibleRend() {
		return graficoDisponibleRend;
	}

	/**
	 * Sets the grafico disponible rend.
	 *
	 * @param graficoDisponibleRend
	 *            the new grafico disponible rend
	 */
	public void setGraficoDisponibleRend(String graficoDisponibleRend) {
		this.graficoDisponibleRend = graficoDisponibleRend;
	}

	
}
