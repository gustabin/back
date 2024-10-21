/*
 * 
 */
package ar.com.santanderrio.obp.servicios.turnosweb.view;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class CitaView.
 *
 * @author IT Resources
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class CitaView {
	
	/** The direccion. */
	private String direccion;
	
	/** The dia. */
	private String dia;
	
	/** The dia numero. */
	private Integer diaNumero;
	
	/** The mes. */
	private String mes;
	
	/** The anio. */
	private Integer anio;
	
	/** The horario. */
	private String horario;
	
	/** The sucursal. */
	private String sucursal;
	
	/** The id turno. */
	private Long idTurno;
	
	/** The sector. */
	private String sector;
	
	/** The tipo. */
	private String tipo;
	
	/** The descripcion sucursal. */
	private String descripcionSucursal;

	/** The id dia seleccionado. */
	private String idDiaSeleccionado;
	
	/** The id hora seleccionado. */
	private String idHoraSeleccionado;

	/** The motivo descripcion. */
	private String motivoDescripcion;

	/** The motivo id. */
	private String motivoId;	

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
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo
	 *            the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	 * Gets the id dia seleccionado.
	 *
	 * @return the idDiaSeleccionado
	 */
	public String getIdDiaSeleccionado() {
		return idDiaSeleccionado;
	}

	/**
	 * Sets the id dia seleccionado.
	 *
	 * @param idDiaSeleccionado
	 *            the idDiaSeleccionado to set
	 */
	public void setIdDiaSeleccionado(String idDiaSeleccionado) {
		this.idDiaSeleccionado = idDiaSeleccionado;
	}

	/**
	 * Gets the id hora seleccionado.
	 *
	 * @return the idHoraSeleccionado
	 */
	public String getIdHoraSeleccionado() {
		return idHoraSeleccionado;
	}

	/**
	 * Sets the id hora seleccionado.
	 *
	 * @param idHoraSeleccionado
	 *            the idHoraSeleccionado to set
	 */
	public void setIdHoraSeleccionado(String idHoraSeleccionado) {
		this.idHoraSeleccionado = idHoraSeleccionado;
	}

	/**
	 * Gets the motivo descripcion.
	 *
	 * @return the motivo descripcion
	 */
	public String getMotivoDescripcion() {
		return motivoDescripcion;
	}

	/**
	 * Sets the motivo descripcion.
	 *
	 * @param motivoDescripcion the new motivo descripcion
	 */
	public void setMotivoDescripcion(String motivoDescripcion) {
		this.motivoDescripcion = motivoDescripcion;
	}

	/**
	 * Gets the motivo id.
	 *
	 * @return the motivo id
	 */
	public String getMotivoId() {
		return motivoId;
	}

	/**
	 * Sets the motivo id.
	 *
	 * @param motivoId the new motivo id
	 */
	public void setMotivoId(String motivoId) {
		this.motivoId = motivoId;
	}
}
