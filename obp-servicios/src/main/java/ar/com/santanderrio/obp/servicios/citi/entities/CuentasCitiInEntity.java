/*
 * 
 */
package ar.com.santanderrio.obp.servicios.citi.entities;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class DetalleCuenta.
 */
public class CuentasCitiInEntity { 
    /** The Constant CONSULTA_CUENTA_CITI_ACCESO. */
    private static final String CONSULTA_CUENTA_CITI_ACCESO = "3";
    
    /** The Constant CONSULTA_CUENTA_CITI_CUENTA. */
    private static final String CONSULTA_CUENTA_CITI_CUENTA = "0000000000";
    
    /** The Constant CONSULTA_CUENTA_CITI_ENTIDAD. */
    private static final String CONSULTA_CUENTA_CITI_ENTIDAD = "0072";
    
    /** The Constant CONSULTA_CUENTA_CITI_APLICACION. */
    private static final String CONSULTA_CUENTA_CITI_APLICACION = "    ";
    
    /** The Constant CONSULTA_CUENTA_CITI_SUCURSAL_RIO. */
    private static final String CONSULTA_CUENTA_CITI_SUCURSAL_RIO = "000";
    
    /** The Constant CONSULTA_CUENTA_CITI_CUENTA_RIO. */
    private static final String CONSULTA_CUENTA_CITI_CUENTA_RIO = "000000000000";
    
    /** The Constant CONSULTA_CUENTA_CITI_CBU_CUENTA_CITI. */
    private static final String CONSULTA_CUENTA_CITI_CBU_CUENTA_CITI = "                      ";
    
    /**
     * Instantiates a new cuentas citi in entity.
     */
    public CuentasCitiInEntity() {
        super();
        this.acceso = CONSULTA_CUENTA_CITI_ACCESO;
        this.cuentaCiti = CONSULTA_CUENTA_CITI_CUENTA;
        this.entidad = CONSULTA_CUENTA_CITI_ENTIDAD;
        this.aplicacion = CONSULTA_CUENTA_CITI_APLICACION; 
        this.sucursalRio = CONSULTA_CUENTA_CITI_SUCURSAL_RIO;
        this.cuentaRio = CONSULTA_CUENTA_CITI_CUENTA_RIO;
        this.cbuCuentaCiti = CONSULTA_CUENTA_CITI_CBU_CUENTA_CITI;
    }

    /** The cliente. */
    private Cliente cliente;
    
	/** The acceso. */
	private String acceso;

	/** The cuenta citi. */
	private String cuentaCiti;
	
	/** The entidad. */
	private String entidad;

	/** The sucursal. */
	private String sucursal;

	/** The cuenta altair. */
	private String cuentaAltair;
	
    /** The divisa. */
    private String divisa;
    
    /** The aplicacion. */
    private String aplicacion;
    
    /** The sucursal rio. */
    private String sucursalRio;
    
    /** The cuenta rio. */
    private String cuentaRio;
    
    /** The cbu cuenta citi. */
    private String cbuCuentaCiti;
    
    /** The is cuenta unica. */
    private Boolean isCuentaUnica;

    /**
     * Gets the cliente.
     *
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Sets the cliente.
     *
     * @param cliente the new cliente
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Gets the acceso.
     *
     * @return the acceso
     */
    public String getAcceso() {
        return acceso;
    }

    /**
     * Sets the acceso.
     *
     * @param acceso the new acceso
     */
    public void setAcceso(String acceso) {
        this.acceso = acceso;
    }

    /**
     * Gets the cuenta citi.
     *
     * @return the cuenta citi
     */
    public String getCuentaCiti() {
        return cuentaCiti;
    }

    /**
     * Sets the cuenta citi.
     *
     * @param cuentaCiti the new cuenta citi
     */
    public void setCuentaCiti(String cuentaCiti) {
        this.cuentaCiti = cuentaCiti;
    }

    /**
     * Gets the entidad.
     *
     * @return the entidad
     */
    public String getEntidad() {
        return entidad;
    }

    /**
     * Sets the entidad.
     *
     * @param entidad the new entidad
     */
    public void setEntidad(String entidad) {
        this.entidad = entidad;
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
     * @param sucursal the new sucursal
     */
    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    /**
     * Gets the cuenta altair.
     *
     * @return the cuenta altair
     */
    public String getCuentaAltair() {
        return cuentaAltair;
    }

    /**
     * Sets the cuenta altair.
     *
     * @param cuentaAltair the new cuenta altair
     */
    public void setCuentaAltair(String cuentaAltair) {
        this.cuentaAltair = cuentaAltair;
    }

    /**
     * Gets the divisa.
     *
     * @return the divisa
     */
    public String getDivisa() {
        return divisa;
    }

    /**
     * Sets the divisa.
     *
     * @param divisa the new divisa
     */
    public void setDivisa(String divisa) {
        this.divisa = divisa;
    }

    /**
     * Gets the aplicacion.
     *
     * @return the aplicacion
     */
    public String getAplicacion() {
        return aplicacion;
    }

    /**
     * Sets the aplicacion.
     *
     * @param aplicacion the new aplicacion
     */
    public void setAplicacion(String aplicacion) {
        this.aplicacion = aplicacion;
    }

    /**
     * Gets the sucursal rio.
     *
     * @return the sucursal rio
     */
    public String getSucursalRio() {
        return sucursalRio;
    }

    /**
     * Sets the sucursal rio.
     *
     * @param sucursalRio the new sucursal rio
     */
    public void setSucursalRio(String sucursalRio) {
        this.sucursalRio = sucursalRio;
    }

    /**
     * Gets the cuenta rio.
     *
     * @return the cuenta rio
     */
    public String getCuentaRio() {
        return cuentaRio;
    }

    /**
     * Sets the cuenta rio.
     *
     * @param cuentaRio the new cuenta rio
     */
    public void setCuentaRio(String cuentaRio) {
        this.cuentaRio = cuentaRio;
    }

    /**
     * Gets the cbu cuenta citi.
     *
     * @return the cbu cuenta citi
     */
    public String getCbuCuentaCiti() {
        return cbuCuentaCiti;
    }

    /**
     * Sets the cbu cuenta citi.
     *
     * @param cbuCuentaCiti the new cbu cuenta citi
     */
    public void setCbuCuentaCiti(String cbuCuentaCiti) {
        this.cbuCuentaCiti = cbuCuentaCiti;
    }

	/**
	 * @return the isCuentaUnica
	 */
	public Boolean getIsCuentaUnica() {
		return isCuentaUnica;
	}

	/**
	 * @param isCuentaUnica the isCuentaUnica to set
	 */
	public void setIsCuentaUnica(Boolean isCuentaUnica) {
		this.isCuentaUnica = isCuentaUnica;
	}
    
    

    
}
