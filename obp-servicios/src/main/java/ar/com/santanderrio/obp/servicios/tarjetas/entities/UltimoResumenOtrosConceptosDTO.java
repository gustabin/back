/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The Class UltimoResumenOtrosConceptosDTO.
 */
public class UltimoResumenOtrosConceptosDTO {

    /** The fecha. */
    private Date fecha;
    
    /** The descripcion. */
    private String descripcion;

    /** The total pesos. */
    private BigDecimal importePesos;

   
    /**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
    public Date getfecha() {
        return fecha;
    }

    
    /**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the new fecha
	 */
    public void setfecha(Date fecha) {
        this.fecha = fecha;
    }

     /**
		 * Gets the descripcion.
		 *
		 * @return the descripcion
		 */
     public String getDescripcion() {
        return descripcion;
    }

    
    /**
	 * Sets the descripcion.
	 *
	 * @param descripcion
	 *            the new descripcion
	 */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
     
    /**
	 * Gets the importe pesos.
	 *
	 * @return the importe pesos
	 */
    public BigDecimal getImportePesos() {
        return importePesos;
    }

    
    /**
	 * Sets the importe pesos.
	 *
	 * @param importePesos
	 *            the new importe pesos
	 */
    public void setImportePesos(BigDecimal importePesos) {
        this.importePesos = importePesos;
    }

}
