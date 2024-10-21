/*
 * 
 */
package ar.com.santanderrio.obp.servicios.debinws.entities;


import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class ConsultaCBUEntityIn.
 */
public class ConsultaCBUEntityIn {

    
    /** The cliente. */
    private Cliente cliente;
    
    /** The tipo cuenta. */
    private String tipoCuenta;
    
    /** The nro sucursal. */
    private String nroSucursal;
    
    /** The nro cuenta. */
    private String nroCuenta;
    
    /** The cbu destino. */
    private String cbuDestino;
    
    /** The nro tarjeta. */
    private String nroTarjeta;
    
    /** The direccion IP. */
    private String direccionIP;

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
	 * @param cliente
	 *            the cliente to set
	 */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipoCuenta
	 */
    public String getTipoCuenta() {
        return tipoCuenta;
    }

    /**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the tipoCuenta to set
	 */
    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    /**
	 * Gets the nro sucursal.
	 *
	 * @return the nroSucursal
	 */
    public String getNroSucursal() {
        return nroSucursal;
    }

    /**
	 * Sets the nro sucursal.
	 *
	 * @param nroSucursal
	 *            the nroSucursal to set
	 */
    public void setNroSucursal(String nroSucursal) {
        this.nroSucursal = nroSucursal;
    }

    /**
	 * Gets the nro cuenta.
	 *
	 * @return the nroCuenta
	 */
    public String getNroCuenta() {
        return nroCuenta;
    }

    /**
	 * Sets the nro cuenta.
	 *
	 * @param nroCuenta
	 *            the nroCuenta to set
	 */
    public void setNroCuenta(String nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

    /**
	 * Gets the cbu destino.
	 *
	 * @return the cbuDestino
	 */
    public String getCbuDestino() {
        return cbuDestino;
    }

    /**
	 * Sets the cbu destino.
	 *
	 * @param cbuDestino
	 *            the cbuDestino to set
	 */
    public void setCbuDestino(String cbuDestino) {
        this.cbuDestino = cbuDestino;
    }

    /**
	 * Gets the nro tarjeta.
	 *
	 * @return the nroTarjeta
	 */
    public String getNroTarjeta() {
        return nroTarjeta;
    }

    /**
	 * Sets the nro tarjeta.
	 *
	 * @param nroTarjeta
	 *            the nroTarjeta to set
	 */
    public void setNroTarjeta(String nroTarjeta) {
        this.nroTarjeta = nroTarjeta;
    }

    /**
	 * Gets the direccion IP.
	 *
	 * @return the direccionIP
	 */
    public String getDireccionIP() {
        return direccionIP;
    }

    /**
	 * Sets the direccion IP.
	 *
	 * @param direccionIP
	 *            the direccionIP to set
	 */
    public void setDireccionIP(String direccionIP) {
        this.direccionIP = direccionIP;
    }
    

}
