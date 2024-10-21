package ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "codRetorno",
    "errores",
    "piezas"
})
@XmlRootElement(name = "out")
public class TrackingTarjetasOut {
    @XmlElement(name = "codRetorno")
    protected String codRetorno;
    @XmlElement(name = "Errores")
    protected TrackingTarjetasResErrores errores;
    @XmlElement(name = "Piezas")
    protected TrackingTarjetasPiezas piezas;

    /**
     * Gets the value of the codRetorno property.
     * 
     */
    public String getCodRetorno() {
        return codRetorno;
    }

    /**
     * Sets the value of the codRetorno property.
     * 
     */
    public void setCodRetorno(String value) {
        this.codRetorno = value;
    }

    public TrackingTarjetasResErrores getErrores() {
        return errores;
    }

    public void setErrores(TrackingTarjetasResErrores errores) {
        this.errores = errores;
    }

    /**
     * Gets the value of the piezas property.
     * 
     * @return
     *     possible object is
     *     {@link TrackingTarjetasPiezas }
     *     
     */
    public TrackingTarjetasPiezas getPiezas() {
        return piezas;
    }

    /**
     * Sets the value of the piezas property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrackingTarjetasPiezas }
     *     
     */
    public void setPiezas(TrackingTarjetasPiezas value) {
        this.piezas = value;
    }

}
