package ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * @author julian.ariel.karp
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "cod",
    "desc",
    "sistema"
})
public class TrackingTarjetasPiezaError {
    @XmlAttribute(name = "cod")
    protected String cod;
    @XmlAttribute(name = "desc")
    protected String desc;
    @XmlAttribute(name = "sistema")
    protected String sistema;
    
    public String getCod() {
        return cod;
    }
    public void setCod(String cod) {
        this.cod = cod;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getSistema() {
        return sistema;
    }
    public void setSistema(String sistema) {
        this.sistema = sistema;
    }
    
}
