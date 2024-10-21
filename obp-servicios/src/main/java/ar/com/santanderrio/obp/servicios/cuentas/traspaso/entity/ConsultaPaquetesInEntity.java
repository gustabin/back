/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity;

import java.util.List;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class ConsultaPaquetesInEntity.
 */
public class ConsultaPaquetesInEntity extends InformacionCuentaPaqueteInEntity{
	/**
     * 
     */
    private static final long serialVersionUID = 6599396798291409089L;

    /** The cliente. */
	private Cliente cliente;

	/** The cantidad de cuentas. */
	private String cantidadDeCuentas;
	
	/** The cuentas filtradas. */
	private List<InformacionCuentaPaqueteInEntity> cuentasFiltradas;

	/**
	 * Gets the cantidad de cuentas.
	 *
	 * @return the cantidad de cuentas
	 */
	public String getCantidadDeCuentas() {
		return cantidadDeCuentas;
	}

	/**
	 * Sets the cantidad de cuentas.
	 *
	 * @param cantidadDeCuentas
	 *            the new cantidad de cuentas
	 */
	public void setCantidadDeCuentas(String cantidadDeCuentas) {
		this.cantidadDeCuentas = cantidadDeCuentas;
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
	 * Gets the cuentas filtradas.
	 *
	 * @return the cuentas filtradas
	 */
    public List<InformacionCuentaPaqueteInEntity> getCuentasFiltradas() {
        return cuentasFiltradas;
    }

    /**
	 * Sets the cuentas filtradas.
	 *
	 * @param cuentasFiltradas
	 *            the new cuentas filtradas
	 */
    public void setCuentasFiltradas(List<InformacionCuentaPaqueteInEntity> cuentasFiltradas) {
        this.cuentasFiltradas = cuentasFiltradas;
    }

	
	
}
