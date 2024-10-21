package ar.com.santanderrio.obp.servicios.solicitudes.view;

import java.util.Date;

public class SolicitudCtaTitView {
	
	private boolean bloqueado;

	private Date fechaVencimiento;
	
	private String tokenEncuesta;

	public boolean isBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getTokenEncuesta() {
		return tokenEncuesta;
	}

	public void setTokenEncuesta(String tokenEncuesta) {
		this.tokenEncuesta = tokenEncuesta;
	}
	
	
}
