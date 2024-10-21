/*
 * 
 */
package ar.com.santanderrio.obp.servicios.debinws.dto;

/**
 * The Class CuentasAdheridasInDTO.
 */
public class CuentasAdheridasInDTO {
    
    /** canal. */
    private String canal;
    
    /** nroDocumento. */
    private String nroDocumento;
    
    /** tipoDocumento. */
    private String tipoDocumento;
    
    

    /**
	 * 
	 */
	public CuentasAdheridasInDTO() {
	}

	/**
	 * @param canal
	 * @param nroDocumento
	 * @param tipoDocumento
	 */
	public CuentasAdheridasInDTO(String canal, String nroDocumento, String tipoDocumento) {
		this.canal = canal;
		this.nroDocumento = nroDocumento;
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * Gets the canal.
	 *
	 * @return the canal
	 */
    public String getCanal() {
        return canal;
    }

    /**
	 * Sets the canal.
	 *
	 * @param canal
	 *            the canal to set
	 */
    public void setCanal(String canal) {
        this.canal = canal;
    }

    /**
	 * Gets the nro documento.
	 *
	 * @return the nroDocumento
	 */
    public String getNroDocumento() {
        return nroDocumento;
    }

    /**
	 * Sets the nro documento.
	 *
	 * @param nroDocumento
	 *            the nroDocumento to set
	 */
    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    /**
	 * Gets the tipo documento.
	 *
	 * @return the tipoDocumento
	 */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**
	 * Sets the tipo documento.
	 *
	 * @param tipoDocumento
	 *            the tipoDocumento to set
	 */
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    
    

}
