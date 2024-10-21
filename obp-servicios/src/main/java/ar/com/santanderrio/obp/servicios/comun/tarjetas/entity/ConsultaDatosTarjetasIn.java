/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.tarjetas.entity;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class ConsultaDatosTarjetasIn.
 */
public class ConsultaDatosTarjetasIn {

    /** The cliente. */
    private Cliente cliente;

    /** The marca. */
    private String marca;

    /** The tipo cuenta. */
    private String tipoCuenta;

    /** The nro cuenta. */
    private String nroCuenta;
    
    /** clave reEnganche. */
    private String claveReEnganche;
    
    

    /**
	 * Gets the clave re enganche.
	 *
	 * @return the clave re enganche
	 */
    public String getClaveReEnganche() {
        return claveReEnganche;
    }

    /**
	 * Sets the clave re enganche.
	 *
	 * @param claveReEnganche
	 *            the new clave re enganche
	 */
    public void setClaveReEnganche(String claveReEnganche) {
        this.claveReEnganche = claveReEnganche;
    }

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
     *            the new cliente
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Gets the marca.
     *
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Sets the marca.
     *
     * @param marca
     *            the new marca
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Gets the tipo cuenta.
     *
     * @return the tipo cuenta
     */
    public String getTipoCuenta() {
        return tipoCuenta;
    }

    /**
     * Sets the tipo cuenta.
     *
     * @param tipoCuenta
     *            the new tipo cuenta
     */
    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    /**
     * Gets the nro cuenta.
     *
     * @return the nro cuenta
     */
    public String getNroCuenta() {
        return nroCuenta;
    }

    /**
     * Sets the nro cuenta.
     *
     * @param nroCuenta
     *            the new nro cuenta
     */
    public void setNroCuenta(String nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

}
