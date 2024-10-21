package ar.com.santanderrio.obp.servicios.debinrecurrente.view;

public class AccionRecurrenciaResponseView {

	private String mensaje;

	public AccionRecurrenciaResponseView(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}
