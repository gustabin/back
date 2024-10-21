package ar.com.santanderrio.obp.servicios.turnosweb.dto;

public class ComprobanteTurnoRemotoDTO {
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
	
	/** The area celular. */
	private String areaCelular;
	
	/** The numero celular. */
	private String numeroCelular;
	
	/** The empresa celular. */
	private String empresaCelular;
	
	/** The email. */
	private String email;

	/** The motivo descripcion. */
	private String motivoDescripcion;
	
	/** The motivo descripcion. */
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

	public String getAreaCelular() {
		return areaCelular;
	}

	public String getNumeroCelular() {
		return numeroCelular;
	}

	public String getEmpresaCelular() {
		return empresaCelular;
	}

	public String getEmail() {
		return email;
	}

	public String getMotivoDescripcion() {
		return motivoDescripcion;
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

	public void setAreaCelular(String areaCelular) {
		this.areaCelular = areaCelular;
	}

	public void setNumeroCelular(String numeroCelular) {
		this.numeroCelular = numeroCelular;
	}

	public void setEmpresaCelular(String empresaCelular) {
		this.empresaCelular = empresaCelular;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setMotivoDescripcion(String motivoDescripcion) {
		this.motivoDescripcion = motivoDescripcion;
	}

	public void setTipoTurno(String tipoTurno) {
		this.tipoTurno = tipoTurno;
	}		
}
