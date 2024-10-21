/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.entities;

import ar.com.santanderrio.obp.base.entities.Entity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class Consulta.
 */
public class StatusAgendamiento extends Entity {
	/** Is Agendado */
	private Boolean agendado;

	/** Is sePuedeAgendarCuentaBP */
	private Boolean sePuedeAgendarCuentaBP;

	public StatusAgendamiento(Boolean agendado, Boolean sePuedeAgendarCuentaBP){
		this.agendado = agendado;
		this.sePuedeAgendarCuentaBP = sePuedeAgendarCuentaBP;
	}
	/**
	 * Gets the agendado value.
	 *
	 * @return the agendado
	 */
	public Boolean isAgendado() {
		return this.agendado;
	}

	/**
	 * Sets the agendado value.
	 *
	 * @param agendado
	 *            the idDef to set
	 */
	public void setAgendado(Boolean agendado) {
		this.agendado = agendado;
	}

	/**
	 * Gets the sePuedeAgendarCuentaBP value.
	 *
	 * @return the sePuedeAgendarCuentaBP
	 */
	public Boolean isSePuedeAgendarCuentaBP() {
		return this.sePuedeAgendarCuentaBP;
	}

	/**
	 * Sets the sePuedeAgendarCuentaBP value.
	 *
	 * @param sePuedeAgendarCuentaBP
	 *            the idDef to set
	 */
	public void setSePuedeAgendarCuentaBP(Boolean sePuedeAgendarCuentaBP) {
		this.sePuedeAgendarCuentaBP = sePuedeAgendarCuentaBP;
	}
}
