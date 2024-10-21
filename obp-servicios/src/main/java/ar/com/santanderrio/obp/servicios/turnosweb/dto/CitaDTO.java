/*
 * 
 */
package ar.com.santanderrio.obp.servicios.turnosweb.dto;

/**
 * The Class CitaDTO.
 *
 * @author IT Resources
 */
public class CitaDTO {

	/** The fecha. */
	private String fecha;
	
	/** The dia. */
	private String dia;
	
	/** The mes. */
	private String mes;
	
	/** The anio. */
	private Integer anio;

	/** The sucursal. */
	private String sucursal;

	/** The direccion. */
	private String direccion;
	
	/** The descripcion sucursal. */
	private String descripcionSucursal;

	/** The fraccion. */
	private String fraccion;

	/** The sector. */
	private String sector;

	/** The id turno. */
	private Long idTurno;
	
	/** The horario. */
	private String horario;
	
	/** The diaNumero. */
	private Integer diaNumero;

	/** The motivoId. */
	private Integer motivoId;
	
	/** The tipoTurno. */
	private String tipoTurno;

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the sucursal.
	 *
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * Sets the sucursal.
	 *
	 * @param sucursal
	 *            the sucursal to set
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

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
	 * Gets the fraccion.
	 *
	 * @return the fraccion
	 */
	public String getFraccion() {
		return fraccion;
	}

	/**
	 * Sets the fraccion.
	 *
	 * @param fraccion
	 *            the fraccion to set
	 */
	public void setFraccion(String fraccion) {
		this.fraccion = fraccion;
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
	 * Gets the dia.
	 *
	 * @return the dia
	 */
	public String getDia() {
		return dia;
	}

	/**
	 * Sets the dia.
	 *
	 * @param dia
	 *            the dia to set
	 */
	public void setDia(String dia) {
		this.dia = dia;
	}

	/**
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes
	 *            the mes to set
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

	/**
	 * Gets the anio.
	 *
	 * @return the anio
	 */
	public Integer getAnio() {
		return anio;
	}

	/**
	 * Sets the anio.
	 *
	 * @param anio
	 *            the anio to set
	 */
	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	/**
	 * Gets the horario.
	 *
	 * @return the horario
	 */
	public String getHorario() {
		return horario;
	}

	/**
	 * Sets the horario.
	 *
	 * @param horario
	 *            the horario to set
	 */
	public void setHorario(String horario) {
		this.horario = horario;
	}

	/**
	 * Gets the dia numero.
	 *
	 * @return the diaNumero
	 */
	public Integer getDiaNumero() {
		return diaNumero;
	}

	/**
	 * Sets the dia numero.
	 *
	 * @param diaNumero
	 *            the diaNumero to set
	 */
	public void setDiaNumero(Integer diaNumero) {
		this.diaNumero = diaNumero;
	}

	/**
	 * Gets the motivo id.
	 *
	 * @return the motivo id
	 */
	public Integer getMotivoId() {
		return motivoId;
	}

	/**
	 * Sets the motivo id.
	 *
	 * @param motivoId the new motivo id
	 */
	public void setMotivoId(Integer motivoId) {
		this.motivoId = motivoId;
	}

	public String getTipoTurno() {
		return tipoTurno;
	}

	public void setTipoTurno(String tipoTurno) {
		this.tipoTurno = tipoTurno;
	}
}
