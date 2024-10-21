package ar.com.santanderrio.obp.generated.webservices.scomp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "idComp"})
public class FiltroDetalleComprobante {

    /** The tpo comp. */
    @XmlElement(name = "id-comp")
    protected String idComp = "2226932";

    
	public String getIdComp() {
		return idComp;
	}

	public void setIdComp(String idComp) {
		this.idComp = idComp;
	}
     
	
}
