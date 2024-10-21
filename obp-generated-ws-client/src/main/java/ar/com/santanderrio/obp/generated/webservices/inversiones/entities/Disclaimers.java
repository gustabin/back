package ar.com.santanderrio.obp.generated.webservices.inversiones.entities;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Disclaimers")
@XmlAccessorType(XmlAccessType.FIELD)
public class Disclaimers {

	/**
	 * Lista de disclaimers
	 */
	@XmlElement(name = "Disclaimer")
	private List<Disclaimer> disclaimers;

	public List<Disclaimer> getDisclaimers() {
		return disclaimers;
	}

	public void setDisclaimers(List<Disclaimer> disclaimers) {
		this.disclaimers = disclaimers;
	}
	
	
}
