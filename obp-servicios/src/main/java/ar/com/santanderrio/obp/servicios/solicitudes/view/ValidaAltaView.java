package ar.com.santanderrio.obp.servicios.solicitudes.view;

public class ValidaAltaView {
	
	
	private String tokenEncuesta;
	
	private String fechaVencimiento;
	
	private boolean bloqueado;
	
	private String motivo;
    
	private String riesgo;
	
	private String fechaPerfilado;
	
	private String url;
	
	private String urlBase;
	
	private boolean hacerEncuesta;
	
	private String mensaje;

	public String getTokenEncuesta() {
		return tokenEncuesta;
	}

	public void setTokenEncuesta(String tokenEncuesta) {
		this.tokenEncuesta = tokenEncuesta;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public boolean isBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getRiesgo() {
		return riesgo;
	}

	public void setRiesgo(String riesgo) {
		this.riesgo = riesgo;
	}

	public String getFechaPerfilado() {
		return fechaPerfilado;
	}

	public void setFechaPerfilado(String fechaPerfilado) {
		this.fechaPerfilado = fechaPerfilado;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isHacerEncuesta() {
		return hacerEncuesta;
	}

	public void setHacerEncuesta(boolean hacerEncuesta) {
		this.hacerEncuesta = hacerEncuesta;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getUrlBase() {
		return urlBase;
	}

	public void setUrlBase(String urlBase) {
		this.urlBase = urlBase;
	}
	
	
		
}
