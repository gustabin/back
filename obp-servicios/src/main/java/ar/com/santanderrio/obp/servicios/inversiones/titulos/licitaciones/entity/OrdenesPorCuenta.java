/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenes.entity.TipoOrdenDTO;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;

/**
 * The Class OrdenesPorCuenta.
 */
public class OrdenesPorCuenta {
	
	/** numero de cuenta. */
	private String cuentaTitulo;
	
	/** The cuenta operativa C suc. */
	private String cuentaOperativaCSuc;
	
	/** The ordenes. */
	private List<TipoOrdenDTO> ordenes = new ArrayList<TipoOrdenDTO>();
	
	/** intervinientes de la cuenta. */
	private List<Interviniente> intervinientes = new ArrayList<Interviniente>();

	/**
	 * Gets the cuenta titulo.
	 *
	 * @return the cuenta titulo
	 */
	public String getCuentaTitulo() {
		return cuentaTitulo;
	}

	/**
	 * Sets the cuenta titulo.
	 *
	 * @param cuentaTitulo
	 *            the new cuenta titulo
	 */
	public void setCuentaTitulo(String cuentaTitulo) {
		this.cuentaTitulo = cuentaTitulo;
	}
	
	/**
	 * Gets the cuenta operativa C suc.
	 *
	 * @return the cuenta operativa C suc
	 */
	public String getCuentaOperativaCSuc() {
		return cuentaOperativaCSuc;
	}

	/**
	 * Sets the cuenta operativa C suc.
	 *
	 * @param cuentaOperativaCSuc
	 *            the new cuenta operativa C suc
	 */
	public void setCuentaOperativaCSuc(String cuentaOperativaCSuc) {
		this.cuentaOperativaCSuc = cuentaOperativaCSuc;
	}


	/**
	 * Gets the ordenes.
	 *
	 * @return the ordenes
	 */
	public List<TipoOrdenDTO> getOrdenes() {
		return ordenes;
	}

	/**
	 * Sets the ordenes.
	 *
	 * @param ordenes
	 *            the new ordenes
	 */
	public void setOrdenes(List<TipoOrdenDTO> ordenes) {
		this.ordenes = ordenes;
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
