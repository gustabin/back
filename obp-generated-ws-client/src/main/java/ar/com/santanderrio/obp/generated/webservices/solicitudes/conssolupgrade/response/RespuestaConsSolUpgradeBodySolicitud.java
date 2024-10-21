package ar.com.santanderrio.obp.generated.webservices.solicitudes.conssolupgrade.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "nup",
    "nroSolcitudUpgrade",
    "fechaSolUpgrade",
    "producto",
    "sucursal",
    "mensaje",
    "docInfo"
})
public class RespuestaConsSolUpgradeBodySolicitud {

    @XmlElement(name = "Nup", required = true)
    protected String nup;
    @XmlElement(name = "NroSolcitudUpgrade", required = true)
    protected String nroSolcitudUpgrade;
    @XmlElement(name = "FechaSolUpgrade", required = true)
    protected String fechaSolUpgrade;
    @XmlElement(name = "Producto", required = true)
    protected String producto;
    @XmlElement(name = "Sucursal", required = true)
    protected RespuestaConsSolUpgradeBodySolicitudSucursal sucursal;
    @XmlElement(name = "Mensaje", required = true)
    protected String mensaje;
    @XmlElement(name = "DocInfo", required = true)
    protected String docInfo;


    public String getNup() {
        return nup;
    }

    public void setNup(String value) {
        this.nup = value;
    }

    public String getNroSolcitudUpgrade() {
        return nroSolcitudUpgrade;
    }

    public void setNroSolcitudUpgrade(String value) {
        this.nroSolcitudUpgrade = value;
    }

    public String getFechaSolUpgrade() {
        return fechaSolUpgrade;
    }

    public void setFechaSolUpgrade(String value) {
        this.fechaSolUpgrade = value;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String value) {
        this.producto = value;
    }

    public RespuestaConsSolUpgradeBodySolicitudSucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(RespuestaConsSolUpgradeBodySolicitudSucursal value) {
        this.sucursal = value;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String value) {
        this.mensaje = value;
    }

    public String getDocInfo() {
        return docInfo;
    }

    public void setDocInfo(String value) {
        this.docInfo = value;
    }

}


