/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.view;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class RendimientoPorCuenta.
 */
public class RendimientoPorCuenta {

	/** The numero de cuenta. */
	private String nroCuenta;

	/** The nroCuentaFormateado. */
	private String nroCuentaFormateado;
	
	/** The informacionParcial. */
	private boolean informacionParcial = false;

	/** The rendimiento pesos. */
	private List<ElementoRendimientoView> rendimientoPesos = new ArrayList<ElementoRendimientoView>();

	/** The rendimiento dolares. */
	private List<ElementoRendimientoView> rendimientoDolares = new ArrayList<ElementoRendimientoView>();

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
	 * Gets the nro cuenta formateado.
	 *
	 * @return the nro cuenta formateado
	 */
	public String getNroCuentaFormateado() {
		return nroCuentaFormateado;
	}

	/**
	 * Sets the nro cuenta formateado.
	 *
	 * @param nroCuentaFormateado
	 *            the new nro cuenta formateado
	 */
	public void setNroCuentaFormateado(String nroCuentaFormateado) {
		this.nroCuentaFormateado = nroCuentaFormateado;
	}

	/**
	 * Checks if is informacion parcial.
	 *
	 * @return true, if is informacion parcial
	 */
	public boolean isInformacionParcial() {
		return informacionParcial;
	}

	/**
	 * Sets the informacion parcial.
	 *
	 * @param informacionParcial
	 *            the new informacion parcial
	 */
	public void setInformacionParcial(boolean informacionParcial) {
		this.informacionParcial = informacionParcial;
	}

	/**
	 * Gets the rendimiento pesos.
	 *
	 * @return the rendimiento pesos
	 */
	public List<ElementoRendimientoView> getRendimientoPesos() {
		return rendimientoPesos;
	}

	/**
	 * Sets the rendimiento pesos.
	 *
	 * @param rendimientoPesos
	 *            the new rendimiento pesos
	 */
	public void setRendimientoPesos(List<ElementoRendimientoView> rendimientoPesos) {
		this.rendimientoPesos = rendimientoPesos;
	}

	/**
	 * Gets the rendimiento dolares.
	 *
	 * @return the rendimiento dolares
	 */
	public List<ElementoRendimientoView> getRendimientoDolares() {
		return rendimientoDolares;
	}

	/**
	 * Sets the rendimiento dolares.
	 *
	 * @param rendimientoDolares
	 *            the new rendimiento dolares
	 */
	public void setRendimientoDolares(List<ElementoRendimientoView> rendimientoDolares) {
		this.rendimientoDolares = rendimientoDolares;
	}

}
