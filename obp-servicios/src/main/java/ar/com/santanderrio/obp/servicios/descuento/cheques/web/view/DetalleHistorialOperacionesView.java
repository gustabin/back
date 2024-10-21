/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.web.view;

import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.DetalleHistorialOperacionesDTO;

/**
 * The Class DetalleHistorialOperacionesView.
 */
public class DetalleHistorialOperacionesView extends DetalleOperacionesPrecargadasView{
	
	/** The estado. */
	private String estado;
	
	/** The desc estado. */
	private String descEstado;
	
	/**
	 * Instantiates a new detalle historial operaciones view.
	 */
	public DetalleHistorialOperacionesView(){
		super();
	}

	/**
	 * Instantiates a new detalle historial operaciones view.
	 *
	 * @param respuesta
	 *            the respuesta
	 */
	public DetalleHistorialOperacionesView(DetalleHistorialOperacionesDTO respuesta) {
		super(respuesta);
		estado = respuesta.getEstado();
		descEstado = respuesta.getDescEstado();
	}

	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado
	 *            the new estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Gets the desc estado.
	 *
	 * @return the desc estado
	 */
	public String getDescEstado() {
		return descEstado;
	}

	/**
	 * Sets the desc estado.
	 *
	 * @param descEstado
	 *            the new desc estado
	 */
	public void setDescEstado(String descEstado) {
		this.descEstado = descEstado;
	}
	

}
