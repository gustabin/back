/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class RendimientoPorCuentaDTO.
 */
public class RendimientoPorCuentaDTO {

	/** The numero de cuenta. */
	private String nroCuenta;

	/** The nroCuentaFormateado. */
	private String nroCuentaFormateado;
	
	/** The informacionParcial. */
	private boolean informacionParcial = false;

	/** The rendimiento pesos. */
	private List<ElementoRendimientoDTO> rendimientoPesos = new ArrayList<ElementoRendimientoDTO>();

	/** The rendimiento dolares. */
	private List<ElementoRendimientoDTO> rendimientoDolares = new ArrayList<ElementoRendimientoDTO>();

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
	 * Sets the informacion parcial.
	 *
	 * @param informacionParcial
	 *            the new informacion parcial
	 */
	public void setInformacionParcial(boolean informacionParcial) {
		this.informacionParcial = informacionParcial;
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
	 * Gets the rendimiento pesos.
	 *
	 * @return the rendimiento pesos
	 */
	public List<ElementoRendimientoDTO> getRendimientoPesos() {
		return rendimientoPesos;
	}

	/**
	 * Sets the rendimiento pesos.
	 *
	 * @param rendimientoPesos
	 *            the new rendimiento pesos
	 */
	public void setRendimientoPesos(List<ElementoRendimientoDTO> rendimientoPesos) {
		this.rendimientoPesos = rendimientoPesos;
	}

	/**
	 * Gets the rendimiento dolares.
	 *
	 * @return the rendimiento dolares
	 */
	public List<ElementoRendimientoDTO> getRendimientoDolares() {
		return rendimientoDolares;
	}

	/**
	 * Sets the rendimiento dolares.
	 *
	 * @param rendimientoDolares
	 *            the new rendimiento dolares
	 */
	public void setRendimientoDolares(List<ElementoRendimientoDTO> rendimientoDolares) {
		this.rendimientoDolares = rendimientoDolares;
	}

}
