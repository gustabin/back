/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity;

import java.math.BigDecimal;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DetallePorAgrupacionEntity.
 */
public class DetallePorAgrupacionEntity {

	/** The codigo agrupacion. */
	@JsonProperty("CodigoAgrupacion")
	private String codigoAgrupacion;
	
	/** The descripcion agrupacion. */
	@JsonProperty("DescripcionAgrupacion")
	private String descripcionAgrupacion;
	
	/** The rentabilidad neta. */
	@JsonProperty("RentabilidadNeta")
	private BigDecimal rentabilidadNeta;
	
	/** The rendimiento. */
	@JsonProperty("Rendimiento")
	private BigDecimal rendimiento;
	
	/** The tna. */
	@JsonProperty("TNA")
	private BigDecimal tna;
	
	/** The resultado instrumento. */
	@JsonProperty("ResultadoInstrumento")
	private List<DetallePorInstrumentoEntity> resultadoInstrumento;

	
	/**
	 * Gets the codigo agrupacion.
	 *
	 * @return the codigo agrupacion
	 */
	public String getCodigoAgrupacion() {
		return codigoAgrupacion;
	}

	/**
	 * Sets the codigo agrupacion.
	 *
	 * @param codigoAgrupacion
	 *            the new codigo agrupacion
	 */
	public void setCodigoAgrupacion(String codigoAgrupacion) {
		this.codigoAgrupacion = codigoAgrupacion;
	}

	/**
	 * Gets the descripcion agrupacion.
	 *
	 * @return the descripcion agrupacion
	 */
	public String getDescripcionAgrupacion() {
		return descripcionAgrupacion;
	}

	/**
	 * Sets the descripcion agrupacion.
	 *
	 * @param descripcionAgrupacion
	 *            the new descripcion agrupacion
	 */
	public void setDescripcionAgrupacion(String descripcionAgrupacion) {
		this.descripcionAgrupacion = descripcionAgrupacion;
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
	 * Gets the tna.
	 *
	 * @return the tna
	 */
	public BigDecimal getTna() {
		return tna;
	}

	/**
	 * Sets the tna.
	 *
	 * @param tna
	 *            the new tna
	 */
	public void setTna(BigDecimal tna) {
		this.tna = tna;
	}

	/**
	 * Gets the resultado instrumento.
	 *
	 * @return the resultado instrumento
	 */
	public List<DetallePorInstrumentoEntity> getResultadoInstrumento() {
		return resultadoInstrumento;
	}

	/**
	 * Sets the resultado instrumento.
	 *
	 * @param resultadoInstrumento
	 *            the new resultado instrumento
	 */
	public void setResultadoInstrumento(List<DetallePorInstrumentoEntity> resultadoInstrumento) {
		this.resultadoInstrumento = resultadoInstrumento;
	}	
		
}
