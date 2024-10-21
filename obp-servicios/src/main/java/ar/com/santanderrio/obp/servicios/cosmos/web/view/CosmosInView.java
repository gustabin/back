package ar.com.santanderrio.obp.servicios.cosmos.web.view;

import javax.xml.bind.annotation.XmlRootElement;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class CosmosInView {

	/** The tipo operacion inicial. */
	private String tipoReclamo;

	public String getTipoReclamo() {
		return tipoReclamo;
	}

	public void setTipoReclamo(String tipoReclamo) {
		this.tipoReclamo = tipoReclamo;
	}
	
}