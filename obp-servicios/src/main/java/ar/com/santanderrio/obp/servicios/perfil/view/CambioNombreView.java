package ar.com.santanderrio.obp.servicios.perfil.view;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class CambioNombreView {

	private String mensajeConfigLegal;
	
	private List<String> mensajesConfig;
	
	private String mensajeFeedbackOk;
	
	private String nombreElegido;
	
	private String textoLink;
	
	private String link;

	
	public String getMensajeConfigLegal() {
		return mensajeConfigLegal;
	}

	public void setMensajeConfigLegal(String mensajeConfigLegal) {
		this.mensajeConfigLegal = mensajeConfigLegal;
	}

	public List<String> getMensajesConfig() {
		return mensajesConfig;
	}

	public void setMensajesConfig(List<String> mensajesConfig) {
		this.mensajesConfig = mensajesConfig;
	}

	public String getMensajeFeedbackOk() {
		return mensajeFeedbackOk;
	}

	public void setMensajeFeedbackOk(String mensajeFeedbackOk) {
		this.mensajeFeedbackOk = mensajeFeedbackOk;
	}

	public String getNombreElegido() {
		return nombreElegido;
	}

	public void setNombreElegido(String nombreElegido) {
		this.nombreElegido = nombreElegido;
	}

	public String getTextoLink() {
		return textoLink;
	}

	public void setTextoLink(String textoLink) {
		this.textoLink = textoLink;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
		
}