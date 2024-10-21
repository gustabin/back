package ar.com.santanderrio.obp.servicios.tenencias.entity;


import org.codehaus.jackson.annotate.JsonProperty;

public class ValorCuotaParteRespEntity {
	
	/** The codigo. */
	@JsonProperty("Codigo")
	private int codigo;

	/** The mensaje. */
	@JsonProperty("Mensaje")
	private String mensaje;

	/** The mensaje tecnico. */
	@JsonProperty("MensajeTecnico")
	private Object mensajeTecnico;

	/** The datos. */
	@JsonProperty("Datos")
	private DatosRespuestaCuotaEntity datos;

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

	public Object getMensajeTecnico() {
		return mensajeTecnico;
	}

	public void setMensajeTecnico(Object mensajeTecnico) {
		this.mensajeTecnico = mensajeTecnico;
	}

	public DatosRespuestaCuotaEntity getDatos() {
		return datos;
	}

	public void setDatos(DatosRespuestaCuotaEntity datos) {
		this.datos = datos;
	}
	
	

}
