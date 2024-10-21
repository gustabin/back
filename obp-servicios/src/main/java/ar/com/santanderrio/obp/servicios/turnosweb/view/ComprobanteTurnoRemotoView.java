package ar.com.santanderrio.obp.servicios.turnosweb.view;

public class ComprobanteTurnoRemotoView {
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
	
	/** The tipo turno. */
	private String tipoTurno;

	public Long getIdTurno() {
		return idTurno;
	}

	public String getDia() {
		return dia;
	}

	public String getHora() {
		return hora;
	}

	public String getSucursal() {
		return sucursal;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getFechaAlta() {
		return fechaAlta;
	}

	public String getSector() {
		return sector;
	}

	public DatosMYAView getDatosMYA() {
		return datosMYA;
	}

	public String getMotivoId() {
		return motivoId;
	}

	public String getTipoTurno() {
		return tipoTurno;
	}

	public void setIdTurno(Long idTurno) {
		this.idTurno = idTurno;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public void setDatosMYA(DatosMYAView datosMYA) {
		this.datosMYA = datosMYA;
	}

	public void setMotivoId(String motivoId) {
		this.motivoId = motivoId;
	}

	public void setTipoTurno(String tipoTurno) {
		this.tipoTurno = tipoTurno;
	}		
}
