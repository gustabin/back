package ar.com.santanderrio.obp.servicios.perfil.dto;

public class CambioNombreDTO {

	private String nombre;
	
	private String mensajeOK;
	
	private String numeroComprobante;

	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMensajeOK() {
		return mensajeOK;
	}

	public void setMensajeOK(String mensajeOK) {
		this.mensajeOK = mensajeOK;
	}

	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}
	
}