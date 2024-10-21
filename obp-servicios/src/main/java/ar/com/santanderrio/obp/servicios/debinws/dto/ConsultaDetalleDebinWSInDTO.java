/*
 * 
 */
package ar.com.santanderrio.obp.servicios.debinws.dto;

/**
 * The Class ConsultaDetalleDebinWSInDTO.
 *
 * @author Silvina_Luque
 */
public class ConsultaDetalleDebinWSInDTO {
    
    /** debinId. */
    private String idDebin;

    /** canal. */
    private String canal;
    
    /** cuit. */
    private String cuit;
    
    /** direccion ip. */
    private String dirIP;
    
    /** nroDocumento. */
    private String nroDocumento;
    
    /** tipoDocuemento. */
    private String tipoDocumento;

    /** consulta desde recibidos. */
    private boolean consultaDesdeRecibidos;
    
    
    /**
	 * Gets the cuit.
	 *
	 * @return the cuit
	 */
    public String getCuit() {
        return cuit;
    }



    /**
	 * Sets the cuit.
	 *
	 * @param cuit
	 *            the new cuit
	 */
    public void setCuit(String cuit) {
        this.cuit = cuit;
    }



    /**
	 * Gets the dir IP.
	 *
	 * @return the dir IP
	 */
    public String getDirIP() {
        return dirIP;
    }



    /**
	 * Sets the dir IP.
	 *
	 * @param dirIP
	 *            the new dir IP
	 */
    public void setDirIP(String dirIP) {
        this.dirIP = dirIP;
    }



    /**
	 * Gets the nro documento.
	 *
	 * @return the nro documento
	 */
    public String getNroDocumento() {
        return nroDocumento;
    }



    /**
	 * Sets the nro documento.
	 *
	 * @param nroDocumento
	 *            the new nro documento
	 */
    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }



    /**
	 * Gets the tipo documento.
	 *
	 * @return the tipo documento
	 */
    public String getTipoDocumento() {
        return tipoDocumento;
    }



    /**
	 * Sets the tipo documento.
	 *
	 * @param tipoDocumento
	 *            the new tipo documento
	 */
    public void setTipoDocumento(String tipoDocumento) {
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
	 *            the new canal
	 */
    public void setCanal(String canal) {
        this.canal = canal;
    }



    /**
	 * Gets the id debin.
	 *
	 * @return the id debin
	 */
    public String getIdDebin() {
        return idDebin;
    }
    
    

    /**
	 * Sets the id debin.
	 *
	 * @param idDebin
	 *            the new id debin
	 */
    public void setIdDebin(String idDebin) {
        this.idDebin = idDebin;
    }



	/**
	 * @return the consultaDesdeRecibidos
	 */
	public boolean isConsultaDesdeRecibidos() {
		return consultaDesdeRecibidos;
	}



	/**
	 * @param consultaDesdeRecibidos the consultaDesdeRecibidos to set
	 */
	public void setConsultaDesdeRecibidos(boolean consultaDesdeRecibidos) {
		this.consultaDesdeRecibidos = consultaDesdeRecibidos;
	}
    
    
    
    
}
