package ar.com.santanderrio.obp.generated.webservices.inversiones.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Mensaje")
@XmlAccessorType(XmlAccessType.FIELD)
public class Mensaje {
	/**
	 * Cantidad de disclaimers
	 */
	@XmlElement(name = "CantidadDeDisclaimers")
	private Integer CantidadDeDisclaimers;
	
	@XmlElement(name = "Disclaimers")
	private Disclaimers  Disclaimers;
	
	public Integer getCantidadDeDisclaimers() {
		return CantidadDeDisclaimers;
	}
	public void setCantidadDeDisclaimers(Integer cantidadDeDisclaimers) {
		CantidadDeDisclaimers = cantidadDeDisclaimers;
	}
	public Disclaimers getDisclaimer() {
		return Disclaimers;
	}
	public void setDisclaimer(Disclaimers disclaimers) {
		this.Disclaimers = disclaimers;
	}
	
	/**
	 * concatena los mensajes del mensaje pasado por param
	 * @param mensaje
	 */
	public void add(Mensaje mensaje){
		CantidadDeDisclaimers = CantidadDeDisclaimers + mensaje.getCantidadDeDisclaimers();
		if (Disclaimers.getDisclaimers() == null){
			Disclaimers = mensaje.getDisclaimer();
		}else if (mensaje.getDisclaimer().getDisclaimers() != null){
			Disclaimers.getDisclaimers().addAll(mensaje.getDisclaimer().getDisclaimers());
		}
	}
	
}
