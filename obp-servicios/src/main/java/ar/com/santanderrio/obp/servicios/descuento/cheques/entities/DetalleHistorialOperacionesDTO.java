/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.entities;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * The Class DetalleHistorialOperacionesDTO.
 */
@JsonIgnoreProperties
public class DetalleHistorialOperacionesDTO extends DetalleOperacionesPrecargadasDTO{


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4243114029282558548L;

	/** The estado. */
	private String estado;
	
	/** The desc estado. */
	private String descEstado;
	
	/**
	 * Instantiates a new detalle historial operaciones DTO.
	 */
	public DetalleHistorialOperacionesDTO(){
		super();
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
