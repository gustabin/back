package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class TarjetaMastercardBlackView {

	private String titulo;
	
	private String descripcion;
	
	private String legal;
	
	private String feedbackOk;
	
	private String feedbackConsejoActualizar;

	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getLegal() {
		return legal;
	}

	public void setLegal(String legal) {
		this.legal = legal;
	}

	public String getFeedbackOk() {
		return feedbackOk;
	}

	public void setFeedbackOk(String feedbackOk) {
		this.feedbackOk = feedbackOk;
	}

	public String getFeedbackConsejoActualizar() {
		return feedbackConsejoActualizar;
	}

	public void setFeedbackConsejoActualizar(String feedbackConsejoActualizar) {
		this.feedbackConsejoActualizar = feedbackConsejoActualizar;
	}
	
}
