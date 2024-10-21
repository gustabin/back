package ar.com.santanderrio.obp.generated.webservices.inversiones.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Modelo el elemanto Disclaimer que viene como rta del 
 * WS
 * @author marcelo.ruiz
 *
 */
@XmlRootElement(name = "Disclaimer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Disclaimer {
	/**
	 * Titulo del Disclaimer
	 */
	@XmlElement(name = "Titulo")
	private String titulo;
	/**
	 * Texto del disclaimer
	 */
	@XmlElement(name = "Text")
	private String text;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}
