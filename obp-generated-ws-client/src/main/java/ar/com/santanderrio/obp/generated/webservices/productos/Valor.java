package ar.com.santanderrio.obp.generated.webservices.productos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "valor", namespace = "http://webService.core.ges.rio.com")

public class Valor {

	@XmlElement(nillable = true)
	protected String string;

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

}
