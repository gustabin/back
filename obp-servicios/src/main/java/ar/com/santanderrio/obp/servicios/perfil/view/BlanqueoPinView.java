package ar.com.santanderrio.obp.servicios.perfil.view;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;

@JsonSerialize(include = Inclusion.NON_NULL)
public class BlanqueoPinView {

	private String numeroTarjeta;
	
	private AutentificacionDTO desafio;
	
	private Boolean reintentar;
	
	private String mensajeBlanqueoExitoso;
	
	private String tipoBlanqueo;
	
	private String mensajeInformativo;
	
		
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public AutentificacionDTO getDesafio() {
		return desafio;
	}

	public void setDesafio(AutentificacionDTO desafio) {
		this.desafio = desafio;
	}

	public Boolean getReintentar() {
		return reintentar;
	}

	public void setReintentar(Boolean reintentar) {
		this.reintentar = reintentar;
	}

	public String getMensajeBlanqueoExitoso() {
		return mensajeBlanqueoExitoso;
	}

	public void setMensajeBlanqueoExitoso(String mensajeBlanqueoExitoso) {
		this.mensajeBlanqueoExitoso = mensajeBlanqueoExitoso;
	}
		
	public String getTipoBlanqueo() {
		return tipoBlanqueo;
	}

	public void setTipoBlanqueo(String tipoBlanqueo) {
		this.tipoBlanqueo = tipoBlanqueo;
	}
	
	public String getMensajeInformativo() {
		return mensajeInformativo;
	}

	public void setMensajeInformativo(String mensajeInformativo) {
		this.mensajeInformativo = mensajeInformativo;
	}
	
}
