package ar.com.santanderrio.obp.servicios.referidos.view;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * View para inicio de referidos
 * @author A309331
 *
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class ReferidosInicioResponseView {

	/**
	 * URL para formulario de carga
	 */
	private String urlCarga;

	/**
	 * URL para invitación a formulario de carga
	 */
	private String urlInvitacion;

	/**
	 * Firma que contiene el NUP del referente
	 */
	private String firma;
	
	private String titulo;
	
	private String subtitulo;
	
	private String cuerpo;
	
	private List<ComisionPaqueteReferidoView> comisionesPaquetes;
	
	private String boton;
	
	private String legales;
		

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSubtitulo() {
		return subtitulo;
	}

	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public List<ComisionPaqueteReferidoView> getComisionesPaquetes() {
		return comisionesPaquetes;
	}

	public void setComisionesPaquetes(List<ComisionPaqueteReferidoView> comisionesPaquetes) {
		this.comisionesPaquetes = comisionesPaquetes;
	}

	public String getBoton() {
		return boton;
	}

	public void setBoton(String boton) {
		this.boton = boton;
	}

	public String getLegales() {
		return legales;
	}

	public void setLegales(String legales) {
		this.legales = legales;
	}

	public String getUrlCarga() {
		return urlCarga;
	}

	public void setUrlCarga(String urlCarga) {
		this.urlCarga = urlCarga;
	}

	public String getUrlInvitacion() {
		return urlInvitacion;
	}

	public void setUrlInvitacion(String urlInvitacion) {
		this.urlInvitacion = urlInvitacion;
	}

	public String getFirma() {
		return firma;
	}

	public void setFirma(String firma) {
		this.firma = firma;
	}

}
