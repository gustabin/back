/*
 * 
 */
package ar.com.santanderrio.obp.servicios.turnosweb.dto;

import java.util.List;

/**
 * The Class HorariosDisponiblesOutDTO.
 *
 * @author IT Resources
 */
public class HorariosDisponiblesOutDTO{
	
	/** The direccion. */
	private String direccion;
	
	/** The descripcion sucursal. */
	private String descripcionSucursal;
	
	/** The sectot. */
	private String sector;

	/** The id suc. */
	private String idSuc;
	
	/** The id turno. */
	private Long idTurno;

	/** The dias disponibles. */
	private List<DiasDisponiblesDTO> diasDisponibles;
	
	
	/**
	 * Gets the direccion.
	 *
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Sets the direccion.
	 *
	 * @param direccion
	 *            the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Gets the sector.
	 *
	 * @return the sector
	 */
	public String getSector() {
		return sector;
	}

	/**
	 * Sets the sector.
	 *
	 * @param sector
	 *            the sector to set
	 */
	public void setSector(String sector) {
		this.sector = sector;
	}

	/**
	 * Gets the id suc.
	 *
	 * @return the idSuc
	 */
	public String getIdSuc() {
		return idSuc;
	}

	/**
	 * Sets the id suc.
	 *
	 * @param idSuc
	 *            the idSuc to set
	 */
	public void setIdSuc(String idSuc) {
		this.idSuc = idSuc;
	}

	/**
	 * Gets the id turno.
	 *
	 * @return the idTurno
	 */
	public Long getIdTurno() {
		return idTurno;
	}

	/**
	 * Sets the id turno.
	 *
	 * @param idTurno
	 *            the idTurno to set
	 */
	public void setIdTurno(Long idTurno) {
		this.idTurno = idTurno;
	}

	/**
	 * Gets the descripcion sucursal.
	 *
	 * @return the descripcionSucursal
	 */
	public String getDescripcionSucursal() {
		return descripcionSucursal;
	}

	/**
	 * Sets the descripcion sucursal.
	 *
	 * @param descripcionSucursal
	 *            the descripcionSucursal to set
	 */
	public void setDescripcionSucursal(String descripcionSucursal) {
		this.descripcionSucursal = descripcionSucursal;
	}

	/**
	 * Gets the dias disponibles.
	 *
	 * @return the diasDisponibles
	 */
	public List<DiasDisponiblesDTO> getDiasDisponibles() {
		return diasDisponibles;
	}

	/**
	 * Sets the dias disponibles.
	 *
	 * @param diasDisponibles
	 *            the diasDisponibles to set
	 */
	public void setDiasDisponibles(List<DiasDisponiblesDTO> diasDisponibles) {
		this.diasDisponibles = diasDisponibles;
	}

	
}
