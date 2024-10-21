/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity;

import java.util.List;

import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.OrdenesView;

/**
 * The Class OrdenesDTO. Esta clase contiene todas las propiedades utilizadas
 * para transportar los datos de ordenes y operaciones entre el DAO y el
 * Manager.
 *
 * @author luis.ventocilla
 * @author emilio.watemberg
 * @see {@link OrdenesView}
 * @since Mon 23, 2017
 */
public class OrdenesDTO {

	/** The cuentas. */
	List<Cuenta> cuentas;

	/** The numero cuenta seleccionada (Nro Cuenta Producto). */
	String numeroCuentaSeleccionada = null;

	/** The ordenes. */
	List<OrdenBaseDTO> ordenes;

	/**
	 * Gets the cuentas.
	 *
	 * @return the cuentas
	 */
	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	/**
	 * Sets the cuentas.
	 *
	 * @param cuentas
	 *            the new cuentas
	 */
	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	/**
	 * Gets the ordenes.
	 *
	 * @return the ordenes
	 */
	public List<OrdenBaseDTO> getOrdenes() {
		return ordenes;
	}

	/**
	 * Sets the ordenes.
	 *
	 * @param ordenes
	 *            the new ordenes
	 */
	public void setOrdenes(List<OrdenBaseDTO> ordenes) {
		this.ordenes = ordenes;
	}

	/**
	 * Gets the numero cuenta seleccionada.
	 *
	 * @return the numero cuenta seleccionada
	 */
	public String getNumeroCuentaSeleccionada() {
		return numeroCuentaSeleccionada;
	}

	/**
	 * Sets the numero cuenta seleccionada.
	 *
	 * @param numeroCuentaSeleccionada
	 *            the new numero cuenta seleccionada
	 */
	public void setNumeroCuentaSeleccionada(String numeroCuentaSeleccionada) {
		this.numeroCuentaSeleccionada = numeroCuentaSeleccionada;
	}

}
