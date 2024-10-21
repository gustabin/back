package ar.com.santanderrio.obp.generated.webservices.solicitudes.conssolupgrade.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "nroSucursal",
    "domSucursal",
    "horaSucDesde",
    "horaSucHasta"
})
public class RespuestaConsSolUpgradeBodySolicitudSucursal {

    @XmlElement(name = "NroSucursal", required = true)
    protected String nroSucursal;
    @XmlElement(name = "DomSucursal", required = true)
    protected String domSucursal;
    @XmlElement(name = "HoraSucDesde", required = true)
    protected String horaSucDesde;
    @XmlElement(name = "HoraSucHasta", required = true)
    protected String horaSucHasta;

    public String getNroSucursal() {
        return nroSucursal;
    }

    public void setNroSucursal(String value) {
        this.nroSucursal = value;
    }

    public String getDomSucursal() {
        return domSucursal;
    }

    public void setDomSucursal(String value) {
        this.domSucursal = value;
    }

    public String getHoraSucDesde() {
        return horaSucDesde;
    }

    public void setHoraSucDesde(String value) {
        this.horaSucDesde = value;
    }

    public String getHoraSucHasta() {
        return horaSucHasta;
    }

    public void setHoraSucHasta(String value) {
        this.horaSucHasta = value;
    }

}
