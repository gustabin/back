/*
 * 
 */
package ar.com.santanderrio.obp.servicios.turnosweb.view;

/**
 * The Class CitaView.
 *
 * @author IT Resources
 */
public class ComprobanteTurnoInView {
	
	/** The id turno. */
	private Long idTurno;
	
	/** The dia. */
	private String dia;
	
	/** The hora. */
	private String hora;
	
	/** The sucursal. */
	private String sucursal;
	
	/** The direccion. */
	private String direccion;
	
	/** The fecha alta. */
	private String fechaAlta;
	
	/** The sector. */
	private String sector;
	
	/** The datos mya. */
	private DatosMYAView datosMYA;

	/** The motivo id. */
	private String motivoId;

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
	 * Gets the hora.
	 *
	 * @return the hora
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * Sets the hora.
	 *
	 * @param hora
	 *            the hora to set
	 */
	public void setHora(String hora) {
		this.hora = hora;
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
	 * Gets the fecha alta.
	 *
	 * @return the fechaAlta
	 */
	public String getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * Sets the fecha alta.
	 *
	 * @param fechaAlta
	 *            the fechaAlta to set
	 */
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
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
	 * Gets the datos MYA.
	 *
	 * @return the datosMYA
	 */
	public DatosMYAView getDatosMYA() {
		return datosMYA;
	}

	/**
	 * Sets the datos MYA.
	 *
	 * @param datosMYA
	 *            the datosMYA to set
	 */
	public void setDatosMYA(DatosMYAView datosMYA) {
		this.datosMYA = datosMYA;
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
