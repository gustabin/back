/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto;

import java.util.List;

import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;

/**
 * The Class CuentasTituloHabilitadasCompra.
 */
public class CuentasTituloHabilitadasCompra {

	/** The cuenta titulo. */
	private String cuentaTitulo;

	/** The estado cuenta titulo. */
	private String estadoCuentaTitulo;

	/** The operacion disponible. */
	private String operacionDisponible;

	/** The intervinientes. titular y cotitulares */
	private List<Interviniente> intervinientes;

	/**
	 * Gets the cuenta titulo.
	 *
	 * @return the cuentaTitulo
	 */
	public String getCuentaTitulo() {
		return cuentaTitulo;
	}

	/**
	 * Sets the cuenta titulo.
	 *
	 * @param cuentaTitulo
	 *            the cuentaTitulo to set
	 */
	public void setCuentaTitulo(String cuentaTitulo) {
		this.cuentaTitulo = cuentaTitulo;
	}

	/**
	 * Gets the estado cuenta titulo.
	 *
	 * @return the estadoCuentaTitulo
	 */
	public String getEstadoCuentaTitulo() {
		return estadoCuentaTitulo;
	}

	/**
	 * Sets the estado cuenta titulo.
	 *
	 * @param estadoCuentaTitulo
	 *            the estadoCuentaTitulo to set
	 */
	public void setEstadoCuentaTitulo(String estadoCuentaTitulo) {
		this.estadoCuentaTitulo = estadoCuentaTitulo;
	}

	/**
	 * Gets the operacion disponible.
	 *
	 * @return the operacionDisponible
	 */
	public String getOperacionDisponible() {
		return operacionDisponible;
	}

	/**
	 * Sets the operacion disponible.
	 *
	 * @param operacionDisponible
	 *            the operacionDisponible to set
	 */
	public void setOperacionDisponible(String operacionDisponible) {
		this.operacionDisponible = operacionDisponible;
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
	 *            the intervinientes to set
	 */
	public void setIntervinientes(List<Interviniente> intervinientes) {
		this.intervinientes = intervinientes;
	}

}
