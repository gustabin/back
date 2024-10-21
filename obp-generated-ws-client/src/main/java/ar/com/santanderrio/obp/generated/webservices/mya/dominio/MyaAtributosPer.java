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
public class MyaAtributosPer {

    @XmlAttribute(name = "id")
    private String idAtributo;
    
    @XmlElement(name = "AttrPerClave")
    private String attrPerClave;
    
    @XmlElement(name = "AttrPerDescrip")
    private String attrPerDescrip;
    
    @XmlElement(name = "AttrPerDefault")
    private String attrPerDefault;

    
    public String getIdAtributo() {
        return idAtributo;
    }

    public void setIdAtributo(String idAtributo) {
        this.idAtributo = idAtributo;
    }

    public String getAttrPerClave() {
        return attrPerClave;
    }

    public void setAttrPerClave(String attrPerClave) {
        this.attrPerClave = attrPerClave;
    }

    public String getAttrPerDescrip() {
        return attrPerDescrip;
    }

    public void setAttrPerDescrip(String attrPerDescrip) {
        this.attrPerDescrip = attrPerDescrip;
    }

    public String getAttrPerDefault() {
        return attrPerDefault;
    }

    public void setAttrPerDefault(String attrPerDefault) {
        this.attrPerDefault = attrPerDefault;
    }
    
}