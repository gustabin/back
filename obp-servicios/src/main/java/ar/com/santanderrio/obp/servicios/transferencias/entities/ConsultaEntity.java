/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.entities;

import ar.com.santanderrio.obp.base.entities.Entity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class Consulta.
 */
public class ConsultaEntity extends Entity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The cliente. */
	private Cliente cliente;

	/** The id def. */
	private String idDef;

	/** The id datos rec. */
	private String idDatosRec;

	/** The id evento. */
	private String idEvento;

	/**
	 * Gets the id def.
	 *
	 * @return the idDef
	 */
	public String getIdDef() {
		return idDef;
	}

	/**
	 * Sets the id def.
	 *
	 * @param idDef
	 *            the idDef to set
	 */
	public void setIdDef(String idDef) {
		this.idDef = idDef;
	}

	/**
	 * Gets the id datos rec.
	 *
	 * @return the idDatosRec
	 */
	public String getIdDatosRec() {
		return idDatosRec;
	}

	/**
	 * Sets the id datos rec.
	 *
	 * @param idDatosRec
	 *            the idDatosRec to set
	 */
	public void setIdDatosRec(String idDatosRec) {
		this.idDatosRec = idDatosRec;
	}

	/**
	 * Gets the id evento.
	 *
	 * @return the idEvento
	 */
	public String getIdEvento() {
		return idEvento;
	}

	/**
	 * Sets the id evento.
	 *
	 * @param idEvento
	 *            the idEvento to set
	 */
	public void setIdEvento(String idEvento) {
		this.idEvento = idEvento;
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
	 *            the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
