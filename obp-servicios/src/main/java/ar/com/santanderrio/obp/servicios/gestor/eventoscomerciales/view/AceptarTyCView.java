package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class AceptarTyCView {

	private String titulo;
	
	private String mensajeInformativo1;
	
	private String mensajeInformativo2;
	
	private ContratosPaqueteTyC contratosTyC;
	
	private String nombrePaquete;
	
	private String mensajeFeedbackOK;
	
	private String textoTycDatosPersonales;
	
	private Boolean esStackCompartirDatos = Boolean.FALSE;
		
	private String linkPoliticasPrivacidad;
	
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensajeInformativo1() {
		return mensajeInformativo1;
	}

	public void setMensajeInformativo1(String mensajeInformativo1) {
		this.mensajeInformativo1 = mensajeInformativo1;
	}

	public String getMensajeInformativo2() {
		return mensajeInformativo2;
	}

	public void setMensajeInformativo2(String mensajeInformativo2) {
		this.mensajeInformativo2 = mensajeInformativo2;
	}

	public ContratosPaqueteTyC getContratosTyC() {
		return contratosTyC;
	}

	public void setContratosTyC(ContratosPaqueteTyC contratosTyC) {
		this.contratosTyC = contratosTyC;
	}

	public String getNombrePaquete() {
		return nombrePaquete;
	}

	public void setNombrePaquete(String nombrePaquete) {
		this.nombrePaquete = nombrePaquete;
	}

	public String getMensajeFeedbackOK() {
		return mensajeFeedbackOK;
	}

	public void setMensajeFeedbackOK(String mensajeFeedbackOK) {
		this.mensajeFeedbackOK = mensajeFeedbackOK;
	}

	public String getTextoTycDatosPersonales() {
		return textoTycDatosPersonales;
	}

	public void setTextoTycDatosPersonales(String textoTycDatosPersonales) {
		this.textoTycDatosPersonales = textoTycDatosPersonales;
	}

	public Boolean getEsStackCompartirDatos() {
		return esStackCompartirDatos;
	}

	public void setEsStackCompartirDatos(Boolean esStackCompartirDatos) {
		this.esStackCompartirDatos = esStackCompartirDatos;
	}

	public String getLinkPoliticasPrivacidad() {
		return linkPoliticasPrivacidad;
	}

	public void setLinkPoliticasPrivacidad(String linkPoliticasPrivacidad) {
		this.linkPoliticasPrivacidad = linkPoliticasPrivacidad;
	}
						
}