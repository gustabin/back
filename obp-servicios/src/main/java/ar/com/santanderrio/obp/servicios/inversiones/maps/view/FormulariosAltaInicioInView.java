package ar.com.santanderrio.obp.servicios.inversiones.maps.view;

import org.codehaus.jackson.annotate.JsonProperty;

public class FormulariosAltaInicioInView {

	@JsonProperty("Segmento")
	private String banca;

	@JsonProperty("IdServicio")
	private String idServicio;
	
	@JsonProperty("Titulo")
	private String titulo;
	
	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getCuentaTitulos() {
		return cuentaTitulos;
	}

	public void setCuentaTitulos(String cuentaTitulos) {
		this.cuentaTitulos = cuentaTitulos;
	}

	public String getCodFondo() {
		return codFondo;
	}

	public void setCodFondo(String codFondo) {
		this.codFondo = codFondo;
	}

	@JsonProperty("Operacion")
	private String operacion;
	
	@JsonProperty("Moneda")
	private String moneda;
	
	@JsonProperty("CuentaTitulos")
	private String cuentaTitulos;
	
	@JsonProperty("CuentaOperativa")
	private String cuentaOperativa;
	
	@JsonProperty("CodFondo")
	private String codFondo;

	
	public String getBanca() {
		return banca;
	}

	public void setBanca(String banca) {
		this.banca = banca;
	}

	public String getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCuentaOperativa() {
		return cuentaOperativa;
	}

	public void setCuentaOperativa(String cuentaOperativa) {
		this.cuentaOperativa = cuentaOperativa;
	}

	
	
	
}
