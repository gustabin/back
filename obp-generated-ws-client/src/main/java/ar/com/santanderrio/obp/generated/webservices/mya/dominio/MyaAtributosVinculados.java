package ar.com.santanderrio.obp.generated.webservices.mya.dominio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author mariano.g.pulera
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class MyaAtributosVinculados {

    @XmlAttribute(name = "id")
    private String attrVincId;
    
    @XmlElement(name = "AttrVincClave")
    private String attrVincClave;
    
    @XmlElement(name = "AttrVincDato")
    private String attrVincDato;

    
    public String getAttrVincId() {
        return attrVincId;
    }

    public void setAttrVincId(String attrVincId) {
        this.attrVincId = attrVincId;
    }

    public String getAttrVincClave() {
        return attrVincClave;
    }

    public void setAttrVincClave(String attrVincClave) {
        this.attrVincClave = attrVincClave;
    }

    public String getAttrVincDato() {
        return attrVincDato;
    }

    public void setAttrVincDato(String attrVincDato) {
        this.attrVincDato = attrVincDato;
    }
         
}