package ar.com.santanderrio.obp.servicios.inversiones.maps.entity;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.ControlMaps;

public class AltaAdhesionMapsDatosRequest  implements RequestMap {

	@JsonProperty("Id")
	private String id;
	
	@JsonProperty("IdServicio")
	private String idServicio;
	
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	@JsonProperty("Titulo")
	private String titulo;

	@JsonProperty("Nup")
	private String nup;
	
	@JsonProperty("Segmento")
	private String segmento;
	
	@JsonProperty("Items")
	private List<ControlMaps> items;
	
	@JsonProperty("Estado")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String estado;
	
	@JsonProperty("Operacion")
	private String operacion;
	
	@JsonProperty("Moneda")
	private String moneda;
	
	@JsonProperty("CuentaTitulos")
	private String cuentaTitulos;
	
	@JsonProperty("CuentaOperativa")
	private String cuentaOperativa;
	
	public String getCuentaOperativa() {
		return cuentaOperativa;
	}

	public void setCuentaOperativa(String cuentaOperativa) {
		this.cuentaOperativa = cuentaOperativa;
	}

	@JsonProperty("CodFondo")
	private String codFondo;
	

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getNup() {
		return nup;
	}

	public void setNup(String nup) {
		this.nup = nup;
	}

	public String getSegmento() {
		return segmento;
	}

	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	public List<ControlMaps> getItems() {
		return items;
	}

	public void setItems(List<ControlMaps> items) {
		this.items = items;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
