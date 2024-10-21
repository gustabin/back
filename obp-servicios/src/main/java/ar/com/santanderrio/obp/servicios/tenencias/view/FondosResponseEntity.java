package ar.com.santanderrio.obp.servicios.tenencias.view;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.tenencias.entity.FondosRimpEntity;

public class FondosResponseEntity {
	
	/** The Datos */
	@JsonProperty("Datos")
	private List<FondosRimpEntity> datos;
	
	/** The Codigo*/
	@JsonProperty("Codigo")
	private int codigo;
	
	/** The mensaje*/
	@JsonProperty("Mensaje")
	private String mensaje;
	
	/** The MensajeTecnico */
	@JsonProperty("MensajeTecnico")
	private String mensajeTecnico;

	

	public int getCodigo() {
		return codigo;
	}

	public List<FondosRimpEntity> getDatos() {
		return datos;
	}

	public void setDatos(List<FondosRimpEntity> datos) {
		this.datos = datos;
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
	
	

}
