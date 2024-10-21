package ar.com.santanderrio.obp.generated.webservices.mya.dominio;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * @author mariano.g.pulera
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class MyaDAPPer {
    
    @XmlAttribute(name = "id")
    private String idDapPer;
    
    @XmlElementWrapper(name = "msgMultiple")
    @XmlElement(name = "msgMultiple")
    private List<MyaMensajeMultiple> listMyaMensajeMultiple;

    
    public String getIdDapPer() {
        return idDapPer;
    }

    public void setIdDapPer(String idDapPer) {
        this.idDapPer = idDapPer;
    }

    public List<MyaMensajeMultiple> getListMyaMensajeMultiple() {
        return listMyaMensajeMultiple;
    }

    public void setListMyaMensajeMultiple(List<MyaMensajeMultiple> listMyaMensajeMultiple) {
        this.listMyaMensajeMultiple = listMyaMensajeMultiple;
    }
    
}