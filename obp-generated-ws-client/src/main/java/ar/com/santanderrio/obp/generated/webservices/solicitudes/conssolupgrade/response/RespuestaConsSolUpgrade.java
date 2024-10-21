package ar.com.santanderrio.obp.generated.webservices.solicitudes.conssolupgrade.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "header",
    "body"
})
@XmlRootElement(name = "xml")
public class RespuestaConsSolUpgrade {

    @XmlElement(required = true)
    protected RespuestaConsSolUpgradeHeader header;
    @XmlElement(required = true)
    protected RespuestaConsSolUpgradeBody body;

    public RespuestaConsSolUpgradeHeader getHeader() {
        return header;
    }

    public void setHeader(RespuestaConsSolUpgradeHeader respuestaConSolUpgradeHeader) {
        this.header = respuestaConSolUpgradeHeader;
    }

    public RespuestaConsSolUpgradeBody getBody() {
        return body;
    }

    public void setBody(RespuestaConsSolUpgradeBody respuestaConSolUpgradeBody) {
        this.body = respuestaConSolUpgradeBody;
    }
    
}