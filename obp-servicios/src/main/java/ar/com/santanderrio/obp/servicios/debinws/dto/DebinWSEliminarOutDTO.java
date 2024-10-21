/*
 * 
 */
package ar.com.santanderrio.obp.servicios.debinws.dto;

/**
 * The Class DebinWSEliminarOutDTO.
 */
public class DebinWSEliminarOutDTO {

    /** fecha comprobante. */
    private String fechaComprobante;
    
    /** numero comprobante. */
    private String numeroComprobante;

    
    
    /**
	 * Gets the numero comprobante.
	 *
	 * @return the numeroComprobante
	 */
    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    /**
	 * Sets the numero comprobante.
	 *
	 * @param numeroComprobante
	 *            the numeroComprobante to set
	 */
    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    /**
	 * Gets the fecha comprobante.
	 *
	 * @return the fechaComprobante
	 */
    public String getFechaComprobante() {
        return fechaComprobante;
    }

    /**
	 * Sets the fecha comprobante.
	 *
	 * @param fechaComprobante
	 *            the fechaComprobante to set
	 */
    public void setFechaComprobante(String fechaComprobante) {
        this.fechaComprobante = fechaComprobante;
    }
    
    
    
    
}
