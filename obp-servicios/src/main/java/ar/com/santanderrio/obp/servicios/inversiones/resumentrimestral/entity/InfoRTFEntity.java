package ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.entity;

import org.codehaus.jackson.annotate.JsonProperty;

public class InfoRTFEntity {
	
	@JsonProperty("Id")
	private String id;
	
	@JsonProperty("Cuenta")
	private String cuenta;
	
	@JsonProperty("Descripcion")
	private String descripcion;
	
	@JsonProperty("Nombre")
	private String nombre;
	
	@JsonProperty("Visto")
	private boolean visto;
	
	@JsonProperty("Archivo")
	private String archivo;
	
	private boolean tieneMov;
	
	private int idPeriodo;
	
	@JsonProperty("Anio")
	private int anio;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isVisto() {
		return visto;
	}

	public void setVisto(boolean visto) {
		this.visto = visto;
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public boolean isTieneMov() {
		return tieneMov;
	}

	public void setTieneMov(boolean tieneMov) {
		this.tieneMov = tieneMov;
	}

	
	public int getIdPeriodo() {
		return idPeriodo;
	}

	public void setIdPeriodo(int idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

}
