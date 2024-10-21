/*
 * 
 */
package ar.com.santanderrio.obp.servicios.citi.entities;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class FechasResumenCitiIn.
 */
public class FechasResumenCitiIn {
    
	/** The sucursal. */
    private String sucursal;
    
	/** The documento. */
    private String documento;
    
	/** The cuenta. */
    private String cuenta;
    
	/** The tarjeta. */
	private String tarjeta;

	/** The fecha cierre desde. */
    @JsonProperty("fecha_de_cierre_de_periodo")
    private String fechaCierreDesde;
 
	/** The fecha cierre haste. */
    @JsonProperty("fecha_de_cierre_de_periodo2")
    private String fechaCierreHaste;

    /**
	 * Gets the tarjeta.
	 *
	 * @return the tarjeta
	 */
	public String getTarjeta() {
		return tarjeta;
	}

	/**
	 * Sets the tarjeta.
	 *
	 * @param tarjeta the new tarjeta
	 */
	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}

	/**
	 * Gets the sucursal.
	 *
     * @return the sucursal
     */
    public String getSucursal() {
        return sucursal;
    }

    /**
	 * Sets the sucursal.
	 *
     * @param sucursal the sucursal to set
     */
    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    /**
	 * Gets the documento.
	 *
     * @return the documento
     */
    public String getDocumento() {
        return documento;
    }

    /**
	 * Sets the documento.
	 *
     * @param documento the documento to set
     */
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /**
	 * Gets the cuenta.
	 *
     * @return the cuenta
     */
    public String getCuenta() {
        return cuenta;
    }

    /**
	 * Sets the cuenta.
	 *
     * @param cuenta the cuenta to set
     */
    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    /**
	 * Gets the fecha cierre desde.
	 *
     * @return the fechaCierreDesde
     */
    public String getFechaCierreDesde() {
        return fechaCierreDesde;
    }

    /**
	 * Sets the fecha cierre desde.
	 *
     * @param fechaCierreDesde the fechaCierreDesde to set
     */
    public void setFechaCierreDesde(String fechaCierreDesde) {
        this.fechaCierreDesde = fechaCierreDesde;
    }

    /**
	 * Gets the fecha cierre haste.
	 *
     * @return the fechaCierreHaste
     */
    public String getFechaCierreHaste() {
        return fechaCierreHaste;
    }

    /**
	 * Sets the fecha cierre haste.
	 *
     * @param fechaCierreHaste the fechaCierreHaste to set
     */
    public void setFechaCierreHaste(String fechaCierreHaste) {
        this.fechaCierreHaste = fechaCierreHaste;
    }

}
