package ar.com.santanderrio.obp.generated.webservices.solicitudes.conssolupgrade.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "nombre",
    "version"
})
public class RespuestaConsSolUpgradeHeaderServicio {

    @XmlElement(required = true)
    protected String nombre;
    protected String version;


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getVersion() {
        return version;
    }

    public void setVersion(String value) {
        this.version = value;
    }

}