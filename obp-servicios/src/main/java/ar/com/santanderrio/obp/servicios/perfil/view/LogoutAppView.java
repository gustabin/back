package ar.com.santanderrio.obp.servicios.perfil.view;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class LogoutAppView {

	private String mensajeLegal;
	
	private String mensajeFeedback;

	
	public String getMensajeLegal() {
		return mensajeLegal;
	}

	public void setMensajeLegal(String mensajeLegal) {
		this.mensajeLegal = mensajeLegal;
	}

	public String getMensajeFeedback() {
		return mensajeFeedback;
	}

	public void setMensajeFeedback(String mensajeFeedback) {
		this.mensajeFeedback = mensajeFeedback;
	}	
		
}
