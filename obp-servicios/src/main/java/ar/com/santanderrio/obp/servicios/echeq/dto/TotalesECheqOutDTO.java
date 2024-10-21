package ar.com.santanderrio.obp.servicios.echeq.dto;

public class TotalesECheqOutDTO {

	/** The total emitidos. */
	private String totalEmitidos;
	
	/** The emitidos pendientes de aceptacion. */
	private String emitidosPendientesDeAceptacion;
	
	/** The total recibidos. */
	private String totalRecibidos;
	
	/** The recibidos pendientes de aceptacion. */
	private String recibidosPendientesDeAceptacion;
	
	/** The en custodia. */
	private String enCustodia;
	
	/** The moneda simbolo. */
	private String monedaSimbolo;

	public String getTotalEmitidos() {
		return totalEmitidos;
	}

	public void setTotalEmitidos(String totalEmitidos) {
		this.totalEmitidos = totalEmitidos;
	}

	public String getEmitidosPendientesDeAceptacion() {
		return emitidosPendientesDeAceptacion;
	}

	public void setEmitidosPendientesDeAceptacion(String emitidosPendientesDeAceptacion) {
		this.emitidosPendientesDeAceptacion = emitidosPendientesDeAceptacion;
	}

	public String getTotalRecibidos() {
		return totalRecibidos;
	}

	public void setTotalRecibidos(String totalRecibidos) {
		this.totalRecibidos = totalRecibidos;
	}

	public String getRecibidosPendientesDeAceptacion() {
		return recibidosPendientesDeAceptacion;
	}

	public void setRecibidosPendientesDeAceptacion(String recibidosPendientesDeAceptacion) {
		this.recibidosPendientesDeAceptacion = recibidosPendientesDeAceptacion;
	}

	public String getEnCustodia() {
		return enCustodia;
	}

	public void setEnCustodia(String enCustodia) {
		this.enCustodia = enCustodia;
	}

	public String getMonedaSimbolo() {
		return monedaSimbolo;
	}

	public void setMonedaSimbolo(String monedaSimbolo) {
		this.monedaSimbolo = monedaSimbolo;
	}

	
}
