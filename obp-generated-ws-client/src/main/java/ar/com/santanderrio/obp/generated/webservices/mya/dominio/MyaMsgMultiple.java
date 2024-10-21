package ar.com.santanderrio.obp.generated.webservices.mya.dominio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class MyaMsgMultiple {

    @XmlAttribute(name = "id")
    private String idMsgMultiple;
    
    @XmlElement(name = "Descripcion")
    private String descripcion;
    
    @XmlElement(name = "Suscripto")
    private String suscripto;
    
    @XmlElement(name = "Monto")
    private String monto;
    
    @XmlElement(name = "NroTarjeta")
    private String nroTarjeta;
    
    @XmlElement(name = "Cuenta")
    private String cuenta;

    
    public String getIdMsgMultiple() {
        return idMsgMultiple;
    }

    public void setIdMsgMultiple(String idMsgMultiple) {
        this.idMsgMultiple = idMsgMultiple;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSuscripto() {
        return suscripto;
    }

    public void setSuscripto(String suscripto) {
        this.suscripto = suscripto;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getNroTarjeta() {
        return nroTarjeta;
    }

    public void setNroTarjeta(String nroTarjeta) {
        this.nroTarjeta = nroTarjeta;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }    

}