/*
 * 
 */
package ar.com.santanderrio.obp.servicios.debinws.dto;


import java.util.Date;



/**
 * ConsultaDebinesInDTO.
 *
 * @author Silvina_Luque
 */
public class ConsultaDebinWSInDTO {
    
    /** Cuit usuario sesion. */
    private String cuit;
    
    /** canal E. */
    private String canal;
    
    /** dirIP. */
    private String dirIP;
    
    /** nroDocumento. */
    private String nroDocumento;
    
    /** tipoDocumento. */
    private String tipoDocumento;
    
    /** consultaComprador. */
    private Boolean consultaComprador = true;
    
    /** cuitConsulta. */
    private String cuitConsulta;
    
    /** fechaDesde. */
    private Date fechaDesde;
    
    /** fechaHasta. */
    private Date fechaHasta;
    
    /** nroPagina. */
    private int nroPagina;
    
    /** tipo. */
    private String tipo;

    /**  primer estado busqueda */
    private EstadoDebinWSBusquedaDTO primerEstadoBusqueda;
    
    /**  primer estado busqueda */
    private EstadoDebinWSBusquedaDTO segundoEstadoBusqueda;
    
    /** The aplica filtro. */
    private Boolean aplicaFiltro = Boolean.FALSE;

    private String idRecurrencia;
    
        /**
     * @return the primerEstadoBusqueda
     */
    public EstadoDebinWSBusquedaDTO getPrimerEstadoBusqueda() {
        return primerEstadoBusqueda;
    }

    /**
     * @param primerEstadoBusqueda the primerEstadoBusqueda to set
     */
    public void setPrimerEstadoBusqueda(EstadoDebinWSBusquedaDTO primerEstadoBusqueda) {
        this.primerEstadoBusqueda = primerEstadoBusqueda;
    }

    /**
     * @param segundoEstadoBusqueda the segundoEstadoBusqueda to set
     */
    public void setSegundoEstadoBusqueda(EstadoDebinWSBusquedaDTO segundoEstadoBusqueda) {
        this.segundoEstadoBusqueda = segundoEstadoBusqueda;
    }



    /**
     * @return the segundoEstadoBusqueda
     */
    public EstadoDebinWSBusquedaDTO getSegundoEstadoBusqueda() {
        return segundoEstadoBusqueda;
    }

    
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
	 * Gets the consulta comprador.
	 *
	 * @return the consulta comprador
	 */
    public Boolean getConsultaComprador() {
        return consultaComprador;
    }

    /**
	 * Sets the consulta comprador.
	 *
	 * @param consultaComprador
	 *            the new consulta comprador
	 */
    public void setConsultaComprador(Boolean consultaComprador) {
        this.consultaComprador = consultaComprador;
    }

    /**
	 * Gets the cuit consulta.
	 *
	 * @return the cuit consulta
	 */
    public String getCuitConsulta() {
        return cuitConsulta;
    }

    /**
	 * Sets the cuit consulta.
	 *
	 * @param cuitConsulta
	 *            the new cuit consulta
	 */
    public void setCuitConsulta(String cuitConsulta) {
        this.cuitConsulta = cuitConsulta;
    }

    /**
	 * Gets the fecha desde.
	 *
	 * @return the fecha desde
	 */
    public Date getFechaDesde() {
        return fechaDesde;
    }

    /**
	 * Sets the fecha desde.
	 *
	 * @param fechaDesde
	 *            the new fecha desde
	 */
    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    /**
	 * Gets the fecha hasta.
	 *
	 * @return the fecha hasta
	 */
    public Date getFechaHasta() {
        return fechaHasta;
    }

    /**
	 * Sets the fecha hasta.
	 *
	 * @param fechaHasta
	 *            the new fecha hasta
	 */
    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    /**
	 * Gets the nro pagina.
	 *
	 * @return the nro pagina
	 */
    public int getNroPagina() {
        return nroPagina;
    }

    /**
	 * Sets the nro pagina.
	 *
	 * @param nroPagina
	 *            the new nro pagina
	 */
    public void setNroPagina(int nroPagina) {
        this.nroPagina = nroPagina;
    }

    /**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
    public String getTipo() {
        return tipo;
    }

    /**
	 * Sets the tipo.
	 *
	 * @param tipo
	 *            the new tipo
	 */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

	/**
	 * @return the aplicaFiltro
	 */
	public Boolean getAplicaFiltro() {
		return aplicaFiltro;
	}

	/**
	 * @param aplicaFiltro the aplicaFiltro to set
	 */
	public void setAplicaFiltro(Boolean aplicaFiltro) {
		this.aplicaFiltro = aplicaFiltro;
	}

    /**
     * @return the idRecurrencia
     */
    public String getIdRecurrencia() { return idRecurrencia; }

    /**
     * @param idRecurrencia the aplicaFiltro to set
     */
    public void setIdRecurrencia(String idRecurrencia) { this.idRecurrencia = idRecurrencia; }

}
