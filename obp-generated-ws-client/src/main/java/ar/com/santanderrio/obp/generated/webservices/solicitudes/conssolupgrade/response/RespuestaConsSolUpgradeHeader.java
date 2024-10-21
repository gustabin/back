package ar.com.santanderrio.obp.generated.webservices.solicitudes.conssolupgrade.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "servicio"
})
public class RespuestaConsSolUpgradeHeader {

    @XmlElement(required = true)
    protected RespuestaConsSolUpgradeHeaderServicio servicio;


    public RespuestaConsSolUpgradeHeaderServicio getServicio() {
        return servicio;
    }


    public void setServicio(RespuestaConsSolUpgradeHeaderServicio servicio) {
        this.servicio = servicio;
    }
    
}