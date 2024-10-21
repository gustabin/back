package ar.com.santanderrio.obp.servicios.inversiones.maps.view;

public class DetalleSuscripcionView {
	
	private long idServicio;
	
	private String banca;
	
	private boolean activas;

	public long getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(long idServicio) {
		this.idServicio = idServicio;
	}

	public String getBanca() {
		return banca;
	}

	public void setBanca(String banca) {
		this.banca = banca;
	}

	public boolean isActivas() {
		return activas;
	}

	public void setActivas(boolean activas) {
		this.activas = activas;
	}
	
}
