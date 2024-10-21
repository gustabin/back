/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenes.view;

import javax.validation.constraints.NotNull;

/**
 * The Class FinalizarReversarOrdenViewRequest.
 */
public class FinalizarReversarOrdenViewRequest {

	
	/** The tipo operacion. */
	@NotNull
	private String tipoOperacion;
	
	/** The especie descripcion. */
	private String especieDescripcion;
	
	/** The fecha ingreso. */
	private String fechaIngreso;
	
	/** The numero orden. */
	private String numeroOrden;
	
	/** The motivo reversa. */
	private String motivoReversa;
	
	/** The cantidad reversada. */
	private String cantidadReversada;
	

	/**
	 * Gets the tipo operacion.
	 *
	 * @return the tipo operacion
	 */
	public String getTipoOperacion() {
		return tipoOperacion;
	}

	/**
	 * Sets the tipo operacion.
	 *
	 * @param tipoOperacion
	 *            the new tipo operacion
	 */
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

			
	/**
	 * Gets the especie descripcion.
	 *
	 * @return the especie descripcion
	 */
	public String getEspecieDescripcion() {
		return especieDescripcion;
	}

	/**
	 * Sets the especie descripcion.
	 *
	 * @param especieDescripcion
	 *            the new especie descripcion
	 */
	public void setEspecieDescripcion(String especieDescripcion) {
		this.especieDescripcion = especieDescripcion;
	}

	/**
	 * Gets the fecha ingreso.
	 *
	 * @return the fecha ingreso
	 */
	public String getFechaIngreso() {
		return fechaIngreso;
	}

	/**
	 * Sets the fecha ingreso.
	 *
	 * @param fechaIngreso
	 *            the new fecha ingreso
	 */
	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	/**
	 * Gets the numero orden.
	 *
	 * @return the numero orden
	 */
	public String getNumeroOrden() {
		return numeroOrden;
	}

	/**
	 * Sets the numero orden.
	 *
	 * @param numeroOrden
	 *            the new numero orden
	 */
	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	/**
	 * Gets the motivo reversa.
	 *
	 * @return the motivo reversa
	 */
	public String getMotivoReversa() {
		return motivoReversa;
	}

	/**
	 * Sets the motivo reversa.
	 *
	 * @param motivoReversa
	 *            the new motivo reversa
	 */
	public void setMotivoReversa(String motivoReversa) {
		this.motivoReversa = motivoReversa;
	}

	/**
	 * Gets the cantidad reversada.
	 *
	 * @return the cantidad reversada
	 */
	public String getCantidadReversada() {
		return cantidadReversada;
	}

	/**
	 * Sets the cantidad reversada.
	 *
	 * @param cantidadReversada
	 *            the new cantidad reversada
	 */
	public void setCantidadReversada(String cantidadReversada) {
		this.cantidadReversada = cantidadReversada;
	}

	
	
}
