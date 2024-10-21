/*
 * 
 */
package ar.com.santanderrio.obp.servicios.debinws.entities;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class ConsultaDebinEntityIn.
 *
 * @author Silvina_Luque
 */
public class ConsultaDebinEntityIn {
    
    /** cliente. */
    private Cliente cliente;
    
    /** funcion. */
    private String funcion;
    
    /** idDebin. */
    private String idDebin;
    
    /** tipoDebin. */
    private String tipoDebin;
    
    /** claseDebin. */
    private String claseDebin;

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
	 * Gets the funcion.
	 *
	 * @return the funcion
	 */
    public String getFuncion() {
        return funcion;
    }

    /**
	 * Sets the funcion.
	 *
	 * @param funcion
	 *            the new funcion
	 */
    public void setFuncion(String funcion) {
        this.funcion = funcion;
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
	 * Gets the tipo debin.
	 *
	 * @return the tipo debin
	 */
    public String getTipoDebin() {
        return tipoDebin;
    }

    /**
	 * Sets the tipo debin.
	 *
	 * @param tipoDebin
	 *            the new tipo debin
	 */
    public void setTipoDebin(String tipoDebin) {
        this.tipoDebin = tipoDebin;
    }

    /**
	 * Gets the clase debin.
	 *
	 * @return the clase debin
	 */
    public String getClaseDebin() {
        return claseDebin;
    }

    /**
	 * Sets the clase debin.
	 *
	 * @param claseDebin
	 *            the new clase debin
	 */
    public void setClaseDebin(String claseDebin) {
        this.claseDebin = claseDebin;
    }
    
    

}
