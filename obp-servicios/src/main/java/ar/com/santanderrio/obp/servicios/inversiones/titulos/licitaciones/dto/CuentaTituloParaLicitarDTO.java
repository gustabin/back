/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;

/**
 * DTO con las licitaciones habilitadas para una cuenta titulo.
 */
public class CuentaTituloParaLicitarDTO {

	/** nroCuenta. */
	private String nroCuenta;

	/** intervinientes de la cuenta. */
	private List<Interviniente> intervinientes = new ArrayList<Interviniente>();

	/** lista de licitaciones vigentes para el nro cuenta. */
	List<LicitacionVigenteDTO> licitacionesVigentesList = new ArrayList<LicitacionVigenteDTO>();

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

	/**
	 * Gets the intervinientes.
	 *
	 * @return the intervinientes
	 */
	public List<Interviniente> getIntervinientes() {
		return intervinientes;
	}

	/**
	 * Sets the intervinientes.
	 *
	 * @param intervinientes
	 *            the new intervinientes
	 */
	public void setIntervinientes(List<Interviniente> intervinientes) {
		this.intervinientes = intervinientes;
	}

	/**
	 * Gets the licitaciones vigentes list.
	 *
	 * @return the licitaciones vigentes list
	 */
	public List<LicitacionVigenteDTO> getLicitacionesVigentesList() {
		return licitacionesVigentesList;
	}

	/**
	 * Sets the licitaciones vigentes list.
	 *
	 * @param licitacionesVigentesList
	 *            the new licitaciones vigentes list
	 */
	public void setLicitacionesVigentesList(List<LicitacionVigenteDTO> licitacionesVigentesList) {
		this.licitacionesVigentesList = licitacionesVigentesList;
	}

}
