package ar.com.santanderrio.obp.generated.webservices.solicitudes.conssolupgrade.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "codRes",
    "mensaje"
})
public class RespuestaConsSolUpgradeBodyError {

    protected int codRes;
    @XmlElement(required = true)
    protected String mensaje;

    public int getCodRes() {
        return codRes;
    }

    public void setCodRes(int value) {
        this.codRes = value;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String value) {
        this.mensaje = value;
    }

}

