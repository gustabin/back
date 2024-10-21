package ar.com.santanderrio.obp.servicios.tenencias.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Respuesta Base
 * @author A309331
 *
 */
public class DatosRespuestaBaseEntity {

	@JsonProperty("Codigo")
	private int codigo;

	@JsonProperty("Leyenda")
	private String leyenda;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getLeyenda() {
		return leyenda;
	}

	public void setLeyenda(String leyenda) {
		this.leyenda = leyenda;
	}

}
