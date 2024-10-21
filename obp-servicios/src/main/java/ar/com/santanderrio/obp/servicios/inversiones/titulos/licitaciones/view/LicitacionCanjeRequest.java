package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

public class LicitacionCanjeRequest {
	
	private String ctaTitulos;
	
	private String especie;
	
	private String moneda;
	
	private long codigoPliego;
	
	private boolean primerIngreso;

	public String getCtaTitulos() {
		return ctaTitulos;
	}

	public void setCtaTitulos(String ctaTitulos) {
		this.ctaTitulos = ctaTitulos;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public boolean isPrimerIngreso() {
		return primerIngreso;
	}

	public void setPrimerIngreso(boolean primerIngreso) {
		this.primerIngreso = primerIngreso;
	}

	public long getCodigoPliego() {
		return codigoPliego;
	}

	public void setCodigoPliego(long codigoPliego) {
		this.codigoPliego = codigoPliego;
	}
	
}
