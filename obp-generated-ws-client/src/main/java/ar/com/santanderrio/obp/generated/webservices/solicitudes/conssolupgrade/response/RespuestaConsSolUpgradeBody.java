package ar.com.santanderrio.obp.generated.webservices.solicitudes.conssolupgrade.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "codRes",
    "codSuc",
    "solicitud"
})
public class RespuestaConsSolUpgradeBody {

    protected int codRes;
    @XmlElement(name = "CodSuc", required = true)
    protected String codSuc;
    @XmlElement(name = "Solicitud", required = true)
    protected RespuestaConsSolUpgradeBodySolicitud solicitud;

    public int getCodRes() {
        return codRes;
    }

    public void setCodRes(int value) {
        this.codRes = value;
    }

    public String getCodSuc() {
        return codSuc;
    }

    public void setCodSuc(String codSuc) {
        this.codSuc = codSuc;
    }

    public RespuestaConsSolUpgradeBodySolicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(RespuestaConsSolUpgradeBodySolicitud solicitud) {
        this.solicitud = solicitud;
    }

}