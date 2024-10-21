/*
 * 
 */
package ar.com.santanderrio.obp.servicios.citi.entities;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class FechasResumenCitiOut.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class FechasResumenCitiOut {
    

    /** The codigo error. */
    @Pattern(regexp = "ERROR000")
    @NotNull
    @JsonProperty("codigoError")
    private String codigoError;

    /** The descripcion error. */
    @JsonProperty("descripcionError")
    private String descripcionError;

    /** The notificaciones. */
    private List<ResumenFechaOutEntity> fechasResumen;

    /**
	 * Gets the codigo error.
	 *
	 * @return the codigoError
	 */
    public String getCodigoError() {
        return codigoError;
    }

    /**
	 * Sets the codigo error.
	 *
	 * @param codigoError
	 *            the codigoError to set
	 */
    public void setCodigoError(String codigoError) {
        this.codigoError = codigoError;
    }

    /**
	 * Gets the descripcion error.
	 *
	 * @return the descripcionError
	 */
    public String getDescripcionError() {
        return descripcionError;
    }

    /**
	 * Sets the descripcion error.
	 *
	 * @param descripcionError
	 *            the descripcionError to set
	 */
    public void setDescripcionError(String descripcionError) {
        this.descripcionError = descripcionError;
    }

    /**
	 * Gets the fechas resumen.
	 *
	 * @return the fechasResumen
	 */
    public List<ResumenFechaOutEntity> getFechasResumen() {
        return fechasResumen;
    }

    /**
	 * Sets the fechas resumen.
	 *
	 * @param fechasResumen
	 *            the fechasResumen to set
	 */
    public void setFechasResumen(List<ResumenFechaOutEntity> fechasResumen) {
        this.fechasResumen = fechasResumen;
    }

}
