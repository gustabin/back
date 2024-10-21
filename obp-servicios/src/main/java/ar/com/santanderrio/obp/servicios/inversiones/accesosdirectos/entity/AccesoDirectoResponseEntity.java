package ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.entity;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.tenencias.entity.DatosHabilitaCompraVentaUSDEntity;

public class AccesoDirectoResponseEntity {
	
	@JsonProperty("Codigo")
	private int codigo;

	@JsonProperty("Mensaje")
	private String mensaje;

	@JsonProperty("MensajeTecnico")
	private String mensajeTecnico;

	@JsonProperty("Datos")
	private DatosAccesoDirectoResponseEntity datos;


	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return the mensajeTecnico
	 */
	public String getMensajeTecnico() {
		return mensajeTecnico;
	}

	/**
	 * @param mensajeTecnico the mensajeTecnico to set
	 */
	public void setMensajeTecnico(String mensajeTecnico) {
		this.mensajeTecnico = mensajeTecnico;
	}

	/**
	 * @return the datos
	 */
	public DatosAccesoDirectoResponseEntity getDatos() {
		return datos;
	}

	/**
	 * @param datos the datos to set
	 */
	public void setDatos(DatosAccesoDirectoResponseEntity datos) {
		this.datos = datos;
	}
	

}
