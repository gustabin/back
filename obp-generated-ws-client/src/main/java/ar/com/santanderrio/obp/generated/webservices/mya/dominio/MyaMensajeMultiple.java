package ar.com.santanderrio.obp.generated.webservices.mya.dominio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author mariano.g.pulera
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class MyaMensajeMultiple {

    @XmlAttribute(name = "id")
    private String idMensaje;
    
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

    
    public String getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(String idMensaje) {
        this.idMensaje = idMensaje;
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