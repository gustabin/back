package ar.com.santanderrio.obp.servicios.clave.online.view;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;

@JsonSerialize(include = Inclusion.NON_NULL)
public class TarjetaBanelcoView {

	private String numeroTarjeta;
	
	private String pinTarjeta;
	
	private String mensajeFeedbackOk;
	
	private Integer ciclo;
	
	private AutentificacionDTO desafio;

	
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getPinTarjeta() {
		return pinTarjeta;
	}

	public void setPinTarjeta(String pinTarjeta) {
		this.pinTarjeta = pinTarjeta;
	}

	public String getMensajeFeedbackOk() {
		return mensajeFeedbackOk;
	}

	public void setMensajeFeedbackOk(String mensajeFeedbackOk) {
		this.mensajeFeedbackOk = mensajeFeedbackOk;
	}

	public Integer getCiclo() {
		return ciclo;
	}

	public void setCiclo(Integer ciclo) {
		this.ciclo = ciclo;
	}

	public AutentificacionDTO getDesafio() {
		return desafio;
	}

	public void setDesafio(AutentificacionDTO desafio) {
		this.desafio = desafio;
	}
		
	
}
