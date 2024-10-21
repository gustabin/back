/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities;

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

	/** Clave segundo llamado. */
	private String claveSegundoLlamado;
	
	
	/**
	 * Gets the clave segundo llamado.
	 *
	 * @return the clave segundo llamado
	 */
	public String getClaveSegundoLlamado() {
        return claveSegundoLlamado;
    }

    /**
	 * Sets the clave segundo llamado.
	 *
	 * @param claveSegundoLlamado
	 *            the new clave segundo llamado
	 */
    public void setClaveSegundoLlamado(String claveSegundoLlamado) {
        this.claveSegundoLlamado = claveSegundoLlamado;
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
