package ar.com.santanderrio.obp.servicios.tenencias.entity;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Datos de respuesta del servicio
 * PLTenenciaValuadaService/ConsultaHabilitacionCompraVentaBonos
 * @author A309331
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = Inclusion.NON_NULL)
public class DatosRespuestaHabilitaCompraVentaUSDEntity {

	@JsonProperty("Datos")
	private DatosRespuestaBaseEntity datos;

	@JsonProperty("Codigo")
	private int codigo;
	
	@JsonProperty("Mensaje")
	private String mensaje;
	
	@JsonProperty("MensajeTecnico")
	private String mensajeTecnico;

	public DatosRespuestaBaseEntity getDatos() {
		return datos;
	}

	public void setDatos(DatosRespuestaBaseEntity datos) {
		this.datos = datos;
	}

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

}
