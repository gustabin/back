package ar.com.santanderrio.obp.servicios.getnet.entities;

public class ActualizacionConsultaMarcaAdhesionFinal extends ActualizacionConsultaMarcaAdhesionEntityOut {

	private String comprobante;
	
	private String fechaHora;


	public ActualizacionConsultaMarcaAdhesionFinal() {
		super();
	}

	public ActualizacionConsultaMarcaAdhesionFinal(String comprobante, String fechaHora) {
		super();
		this.comprobante = comprobante;
		this.fechaHora = fechaHora;
	}

	public String getComprobante() {
		return comprobante;
	}

	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}

	public String getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}
}
