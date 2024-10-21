/*
 * 
 */
package ar.com.santanderrio.obp.servicios.debinws.dto;

/**
 * The Class RechazarDebinWSOutDTO.
 */
public class RechazarDebinWSOutDTO {

    /** the mensaje. */
    private String mensaje;

    /** the numero comprobante. */
    private String numeroComprobante;
    
    /** the fecha comprobante. */
    private String fechaComprobante;
    

    /**
	 * Gets the mensaje.
	 *
	 * @return the mensaje
	 */
    public String getMensaje() {
        return mensaje;
    }

    /**
	 * Sets the mensaje.
	 *
	 * @param mensaje
	 *            the mensaje to set
	 */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

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
	 * @return the fechaComprobante
	 */
	public String getFechaComprobante() {
		return fechaComprobante;
	}

	/**
	 * @param fechaComprobante the fechaComprobante to set
	 */
	public void setFechaComprobante(String fechaComprobante) {
		this.fechaComprobante = fechaComprobante;
	}

    
    
}
