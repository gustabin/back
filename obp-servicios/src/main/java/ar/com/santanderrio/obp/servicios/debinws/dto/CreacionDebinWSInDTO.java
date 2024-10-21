/*
 * 
 */
package ar.com.santanderrio.obp.servicios.debinws.dto;



/**
 * The Class CreacionDebinWSInDTO.
 */
public class CreacionDebinWSInDTO {
    
    /** nro documente. */
    private String nroDocumento;
    
    /** tipoDocumento. */
    private String tipoDocumento;

    /** canal. */
    private String canal;
    
    /** cuit destinatario. */
    private String cuitDestinatario;
    
    /** permite preautorizaciones. */
    private boolean permitePreautorizado;
    
    
    /** categoria limite. */
    private int categoriaLimite;
    
    /** titulas destinatario. */
    private String titularDestinatario;
    
    /** cbu destinatario. */
    private String cbuDestinatario;
    
    /** alias destinatario. */
    private String aliasDestinatario;
    
    /** concepto. */ 
    private String concepto;
    
    /** fecha vecimiento. */
    private String fechaVencimiento;
    
    /** importe. */
    private String importe;
    
    /** moneda. */
    private String moneda;
    
    /** descripciom. */
    private String descripcion;

    /** cbu vendedor. */
    private String cbuOrigen;
    
    
    
    /**
	 * Gets the cbu origen.
	 *
	 * @return the cbuOrigen
	 */
    public String getCbuOrigen() {
        return cbuOrigen;
    }

    /**
	 * Sets the cbu origen.
	 *
	 * @param cbuOrigen
	 *            the cbuOrigen to set
	 */
    public void setCbuOrigen(String cbuOrigen) {
        this.cbuOrigen = cbuOrigen;
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
	 *            the descripcion to set
	 */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
    public String getMoneda() {
        return moneda;
    }

    /**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the moneda to set
	 */
    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    /**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
    public String getImporte() {
        return importe;
    }

    /**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the importe to set
	 */
    public void setImporte(String importe) {
        this.importe = importe;
    }

    /**
	 * Gets the fecha vencimiento.
	 *
	 * @return the fechaVencimiento
	 */
    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
	 * Sets the fecha vencimiento.
	 *
	 * @param fechaVencimiento
	 *            the fechaVencimiento to set
	 */
    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
	 * Gets the titular destinatario.
	 *
	 * @return the titularDestinatario
	 */
    public String getTitularDestinatario() {
        return titularDestinatario;
    }

    /**
	 * Sets the titular destinatario.
	 *
	 * @param titularDestinatario
	 *            the titularDestinatario to set
	 */
    public void setTitularDestinatario(String titularDestinatario) {
        this.titularDestinatario = titularDestinatario;
    }

    /**
	 * Gets the cbu destinatario.
	 *
	 * @return the cbuDestinatario
	 */
    public String getCbuDestinatario() {
        return cbuDestinatario;
    }

    /**
	 * Sets the cbu destinatario.
	 *
	 * @param cbuDestinatario
	 *            the cbuDestinatario to set
	 */
    public void setCbuDestinatario(String cbuDestinatario) {
        this.cbuDestinatario = cbuDestinatario;
    }

    /**
	 * Gets the alias destinatario.
	 *
	 * @return the aliasDestinatario
	 */
    public String getAliasDestinatario() {
        return aliasDestinatario;
    }

    /**
	 * Sets the alias destinatario.
	 *
	 * @param aliasDestinatario
	 *            the aliasDestinatario to set
	 */
    public void setAliasDestinatario(String aliasDestinatario) {
        this.aliasDestinatario = aliasDestinatario;
    }

    /**
	 * Gets the concepto.
	 *
	 * @return the concepto
	 */
    public String getConcepto() {
        return concepto;
    }

    /**
	 * Sets the concepto.
	 *
	 * @param concepto
	 *            the concepto to set
	 */
    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    /**
	 * Checks if is permite preautorizado.
	 *
	 * @return the permitePreautorizado
	 */
    public boolean isPermitePreautorizado() {
        return permitePreautorizado;
    }

    /**
	 * Sets the permite preautorizado.
	 *
	 * @param permitePreautorizado
	 *            the permitePreautorizado to set
	 */
    public void setPermitePreautorizado(boolean permitePreautorizado) {
        this.permitePreautorizado = permitePreautorizado;
    }

    /**
	 * Gets the categoria limite.
	 *
	 * @return the categoriaLimite
	 */
    public int getCategoriaLimite() {
        return categoriaLimite;
    }

    /**
	 * Sets the categoria limite.
	 *
	 * @param categoriaLimite
	 *            the categoriaLimite to set
	 */
    public void setCategoriaLimite(int categoriaLimite) {
        this.categoriaLimite = categoriaLimite;
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
	 * Gets the cuit destinatario.
	 *
	 * @return the cuitDestinatario
	 */
    public String getCuitDestinatario() {
        return cuitDestinatario;
    }

    /**
	 * Sets the cuit destinatario.
	 *
	 * @param cuitDestinatario
	 *            the cuitDestinatario to set
	 */
    public void setCuitDestinatario(String cuitDestinatario) {
        this.cuitDestinatario = cuitDestinatario;
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
    
    
    

}
