package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class PeriodoActualOutEntity.
 */
public class PeriodoActualOutEntity {

	/** The nup. */
    @Pattern(regexp = "^\\d*$")
    @NotNull
    @JsonProperty("nup")
    private String nup;
    
    /** The total chances periodo. */
    @NotNull
    @JsonProperty("total_chances_periodo")
	private String totalChancesPeriodo;
    
    /** The fecha corte. */
    @NotNull
    @JsonProperty("fecha_corte")
	private String fechaCorte;
    
    /** The codigo error. */
    @Pattern(regexp = "ERROR000")
    @NotNull
    @JsonProperty("codigoError")
    private String codigoError;

    /** The descripcion error. */
    @JsonProperty("descripcionError")
    private String descripcionError;

	/**
	 *Gets nup
	 *
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 *Sets nup
	 *
	 * @param the nup 
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 *Gets totalChancesPeriodo
	 *
	 * @return the totalChancesPeriodo
	 */
	public String getTotalChancesPeriodo() {
		return totalChancesPeriodo;
	}

	/**
	 *Sets totalChancesPeriodo
	 *
	 * @param the totalChancesPeriodo 
	 */
	public void setTotalChancesPeriodo(String totalChancesPeriodo) {
		this.totalChancesPeriodo = totalChancesPeriodo;
	}

	/**
	 *Gets fechaCorte
	 *
	 * @return the fechaCorte
	 */
	public String getFechaCorte() {
		return fechaCorte;
	}

	/**
	 *Sets fechaCorte
	 *
	 * @param the fechaCorte 
	 */
	public void setFechaCorte(String fechaCorte) {
		this.fechaCorte = fechaCorte;
	}

	/**
	 *Gets codigoError
	 *
	 * @return the codigoError
	 */
	public String getCodigoError() {
		return codigoError;
	}

	/**
	 *Sets codigoError
	 *
	 * @param the codigoError 
	 */
	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	/**
	 *Gets descripcionError
	 *
	 * @return the descripcionError
	 */
	public String getDescripcionError() {
		return descripcionError;
	}

	/**
	 *Sets descripcionError
	 *
	 * @param the descripcionError 
	 */
	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}
	
}
