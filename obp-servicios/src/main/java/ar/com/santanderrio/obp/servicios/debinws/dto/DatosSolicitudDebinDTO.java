/*
 * 
 */
package ar.com.santanderrio.obp.servicios.debinws.dto;

/**
 * The Class DatosSolicitudDebinDTO.
 */
public class DatosSolicitudDebinDTO {
    
    /** titular. */
    private String nombreDestinatario;
    
    /** titular. */
    private String cuitDestinatario;
    
    /** titular. */
    private String cbuDestinatario;
    
    /** alias destinatario. */
    private String aliasDestinatario;
    
    /** banco. */
    private String bancoDestinatario;
    
    /** moneda. */
    private String moneda;
    
    /** concepto. */
    private String concepto;
    
    /** descripcion. */
    private String descripcion;
    
    
    /** fechaHoraComprobante. */
    private String fechaHoraComprobante;
    
    /** nroComprobante. */
    private String nroComprobante;
    
    /** importe. */
    private String importe;
    
    /** tipoCuenta acreditacion. */
    private String tipoCuentaAcreditacion;
    
    /** nroCuenta acreditacion. */
    private String nroCuentaAcreditacion;
    
    /** alias acreditacion. */
    private String aliasAcreditacion;

    /** fecha vencimiento. */
    private String fechaVencimiento;
    
    
    
    
    
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
	 * Gets the nombre destinatario.
	 *
	 * @return the nombreDestinatario
	 */
    public String getNombreDestinatario() {
        return nombreDestinatario;
    }

    /**
	 * Sets the nombre destinatario.
	 *
	 * @param nombreDestinatario
	 *            the nombreDestinatario to set
	 */
    public void setNombreDestinatario(String nombreDestinatario) {
        this.nombreDestinatario = nombreDestinatario;
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
	 * Sets the cuit destinatario.
	 *
	 * @param cuitDestinatario
	 *            the cuitDestinatario to set
	 */
    public void setCuitDestinatario(String cuitDestinatario) {
        this.cuitDestinatario = cuitDestinatario;
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
	 * Gets the banco destinatario.
	 *
	 * @return the bancoDestinatario
	 */
    public String getBancoDestinatario() {
        return bancoDestinatario;
    }

    /**
	 * Sets the banco destinatario.
	 *
	 * @param bancoDestinatario
	 *            the bancoDestinatario to set
	 */
    public void setBancoDestinatario(String bancoDestinatario) {
        this.bancoDestinatario = bancoDestinatario;
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
	 * Gets the fecha hora comprobante.
	 *
	 * @return the fechaHoraComprobante
	 */
    public String getFechaHoraComprobante() {
        return fechaHoraComprobante;
    }

    /**
	 * Sets the fecha hora comprobante.
	 *
	 * @param fechaHoraComprobante
	 *            the fechaHoraComprobante to set
	 */
    public void setFechaHoraComprobante(String fechaHoraComprobante) {
        this.fechaHoraComprobante = fechaHoraComprobante;
    }

    /**
	 * Gets the nro comprobante.
	 *
	 * @return the nroComprobante
	 */
    public String getNroComprobante() {
        return nroComprobante;
    }

    /**
	 * Sets the nro comprobante.
	 *
	 * @param nroComprobante
	 *            the nroComprobante to set
	 */
    public void setNroComprobante(String nroComprobante) {
        this.nroComprobante = nroComprobante;
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
	 * Gets the tipo cuenta acreditacion.
	 *
	 * @return the tipoCuentaAcreditacion
	 */
    public String getTipoCuentaAcreditacion() {
        return tipoCuentaAcreditacion;
    }

    /**
	 * Sets the tipo cuenta acreditacion.
	 *
	 * @param tipoCuentaAcreditacion
	 *            the tipoCuentaAcreditacion to set
	 */
    public void setTipoCuentaAcreditacion(String tipoCuentaAcreditacion) {
        this.tipoCuentaAcreditacion = tipoCuentaAcreditacion;
    }

    /**
	 * Gets the nro cuenta acreditacion.
	 *
	 * @return the nroCuentaAcreditacion
	 */
    public String getNroCuentaAcreditacion() {
        return nroCuentaAcreditacion;
    }

    /**
	 * Sets the nro cuenta acreditacion.
	 *
	 * @param nroCuentaAcreditacion
	 *            the nroCuentaAcreditacion to set
	 */
    public void setNroCuentaAcreditacion(String nroCuentaAcreditacion) {
        this.nroCuentaAcreditacion = nroCuentaAcreditacion;
    }

    /**
	 * Gets the alias acreditacion.
	 *
	 * @return the aliasAcreditacion
	 */
    public String getAliasAcreditacion() {
        return aliasAcreditacion;
    }

    /**
	 * Sets the alias acreditacion.
	 *
	 * @param aliasAcreditacion
	 *            the aliasAcreditacion to set
	 */
    public void setAliasAcreditacion(String aliasAcreditacion) {
        this.aliasAcreditacion = aliasAcreditacion;
    }
    
    
    

}
