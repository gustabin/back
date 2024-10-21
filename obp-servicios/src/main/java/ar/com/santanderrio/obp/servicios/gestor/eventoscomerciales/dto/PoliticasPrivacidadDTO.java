package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto;

public class PoliticasPrivacidadDTO {

	private String nup;
	
	private String dni;
	
	private String apellido;
	
	private String nombre;
	
	private String aceptacion;
	
	private String fechaAceptacion;

	
	public String getNup() {
		return nup;
	}

	public void setNup(String nup) {
		this.nup = nup;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAceptacion() {
		return aceptacion;
	}

	public void setAceptacion(String aceptacion) {
		this.aceptacion = aceptacion;
	}

	public String getFechaAceptacion() {
		return fechaAceptacion;
	}

	public void setFechaAceptacion(String fechaAceptacion) {
		this.fechaAceptacion = fechaAceptacion;
	}
		
}
