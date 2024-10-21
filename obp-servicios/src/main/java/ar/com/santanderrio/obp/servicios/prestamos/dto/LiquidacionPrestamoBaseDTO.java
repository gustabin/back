package ar.com.santanderrio.obp.servicios.prestamos.dto;

import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnore;

public class LiquidacionPrestamoBaseDTO {

	protected String estado;

	@JsonIgnore
	protected Map<String, Object> metadata;

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Map<String, Object> getMetadata() {
		return metadata;
	}

	public void setMetadata(Map<String, Object> metadata) {
		this.metadata = metadata;
	}
}
