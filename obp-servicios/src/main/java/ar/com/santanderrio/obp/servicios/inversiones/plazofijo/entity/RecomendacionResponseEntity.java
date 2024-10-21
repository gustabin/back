package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity;

import org.codehaus.jackson.annotate.JsonProperty;


public class RecomendacionResponseEntity{

	/** The Datos*/
	@JsonProperty("Datos")
	private DatosRecomendacionResponse datos;

	@JsonProperty("Codigo")
	protected int codigo;
	
	@JsonProperty("Mensaje")
	protected String mensaje;
	
	@JsonProperty("MensajeTecnico")
	protected String mensajeTecnico;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getMensajeTecnico() {
		return mensajeTecnico;
	}

	public void setMensajeTecnico(String mensajeTecnico) {
		this.mensajeTecnico = mensajeTecnico;
	}

	
	/** 
	 * Gets the datos
	 * 
	 * @return
	 */
	public DatosRecomendacionResponse getDatos() {
		return datos;
	}
	
	/**
	 * Sets de Datos
	 * 
	 * @param datos
	 */
	public void setDatos(DatosRecomendacionResponse datos) {
		this.datos = datos;
	}
	

}
