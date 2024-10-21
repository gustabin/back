/*
 * 
 */
package ar.com.santanderrio.obp.servicios.turnosweb.dto;

import java.util.List;

/**
 * The Class DiasDisponiblesDTO.
 *
 * @author IT Resources
 */
public class DiasDisponiblesDTO implements Comparable<DiasDisponiblesDTO>{

	/** The id. */
	private String id;
	
	/** The descripcion. */
	private String descripcion;
	
	/** The fraccion horaria disponible. */
	private List<FraccionHorariaDisponibleDTO> fraccionHorariaDisponible;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the fraccion horaria disponible.
	 *
	 * @return the fraccionHorariaDisponible
	 */
	public List<FraccionHorariaDisponibleDTO> getFraccionHorariaDisponible() {
		return fraccionHorariaDisponible;
	}

	/**
	 * Sets the fraccion horaria disponible.
	 *
	 * @param fraccionHorariaDisponible
	 *            the fraccionHorariaDisponible to set
	 */
	public void setFraccionHorariaDisponible(List<FraccionHorariaDisponibleDTO> fraccionHorariaDisponible) {
		this.fraccionHorariaDisponible = fraccionHorariaDisponible;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(DiasDisponiblesDTO o) {
		return this.id.compareTo(o.id);
	}

	
	
	
}
