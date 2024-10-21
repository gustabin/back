/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.OperacionDTO;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;

/**
 * The Class OperacionesPorCuenta.
 */
public class OperacionesPorCuenta {

	/** numero de cuenta. */
	private String nroCuenta;
	
	/** The operaciones. */
	private List<OperacionDTO> operaciones = new ArrayList<OperacionDTO>();
	
	/** intervinientes de la cuenta. */
	private List<Interviniente> intervinientes = new ArrayList<Interviniente>();
	
		
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
	 * Gets the operaciones.
	 *
	 * @return the operaciones
	 */
	public List<OperacionDTO> getOperaciones() {
		return operaciones; 
	}

	/**
	 * Sets the operaciones.
	 *
	 * @param operaciones
	 *            the new operaciones
	 */
	public void setOperaciones(List<OperacionDTO> operaciones) {
		this.operaciones = operaciones;
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
	
	
	
	
}
