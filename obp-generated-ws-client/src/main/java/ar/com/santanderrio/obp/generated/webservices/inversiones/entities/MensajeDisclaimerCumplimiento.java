package ar.com.santanderrio.obp.generated.webservices.inversiones.entities;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Mensaje")
@XmlAccessorType(XmlAccessType.FIELD)
public class MensajeDisclaimerCumplimiento {
	/**
	 * Cantidad de disclaimers
	 */
	@XmlElement(name = "CantidadDeDisclaimers")
	private Integer CantidadDeDisclaimers;
	
	@XmlElementWrapper(name = "Disclaimers")
	@XmlElement(name = "DisclaimerCumplimiento")
	private List<Disclaimer> disclaimers;

	
	public Integer getCantidadDeDisclaimers() {
		return CantidadDeDisclaimers;
	}
	public void setCantidadDeDisclaimers(Integer cantidadDeDisclaimers) {
		CantidadDeDisclaimers = cantidadDeDisclaimers;
	}
	public List<Disclaimer> getDisclaimers() {
		return disclaimers;
	}
	public void setDisclaimers(List<Disclaimer> disclaimers) {
		this.disclaimers = disclaimers;
	}
	
}
