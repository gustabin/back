package ar.com.santanderrio.obp.servicios.clientes.entities;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class CtasTitEntity {

	@JsonProperty("IdCuentaCustodia")
	private int numeroCtaTitulo;
	
	@JsonProperty("CuentasOperativas")
	private List<CtasOpEntity> cuentasOp;
	
	@JsonProperty("Estado")
	private String estado;
	
	@JsonProperty("Alias")
	private String alias;
	
	@JsonProperty("Descripcion")
	private String descripcion;
	
	@JsonProperty("Segmento")
	private String segmento;
	
	@JsonProperty("Titulares")
	private List<TitularesEntity> titulares;

	public int getNumeroCtaTitulo() {
		return numeroCtaTitulo;
	}

	public void setNumeroCtaTitulo(int numeroCtaTitulo) {
		this.numeroCtaTitulo = numeroCtaTitulo;
	}

	public List<CtasOpEntity> getCuentasOp() {
		return cuentasOp;
	}

	public void setCuentasOp(List<CtasOpEntity> cuentasOp) {
		this.cuentasOp = cuentasOp;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getSegmento() {
		return segmento;
	}

	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	public List<TitularesEntity> getTitulares() {
		return titulares;
	}

	public void setTitulares(List<TitularesEntity> titulares) {
		this.titulares = titulares;
	}
	
	
}


