package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class ContactabilidadView {

	private String idOferta;
	
	private String mensajeOk;

		
	public String getIdOferta() {
		return idOferta;
	}

	public void setIdOferta(String idOferta) {
		this.idOferta = idOferta;
	}

	public String getMensajeOk() {
		return mensajeOk;
	}

	public void setMensajeOk(String mensajeOk) {
		this.mensajeOk = mensajeOk;
	}

}
