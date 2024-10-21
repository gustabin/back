package ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "firma",
    "canalTipo",
    "canalID",
    "subcanalTipo",
    "subcanalID",
    "nup",
    "tipoPersona",
    "tipoID",
    "idCliente",
    "fechaNac",
    "datos"
})
@XmlRootElement(name = "in")
public class TrackingTarjetasIn {

    @XmlElement(name = "Firma")
    protected String firma;
    @XmlElement(name = "CanalTipo")
    protected String canalTipo;
    @XmlElement(name = "CanalID")
    protected String canalID;
    @XmlElement(name = "SubcanalTipo")
    protected String subcanalTipo;
    @XmlElement(name = "SubcanalID")
    protected String subcanalID;
    @XmlElement(name = "NUP")
    protected String nup;
    @XmlElement(name = "TipoPersona")
    protected String tipoPersona;
    @XmlElement(name = "TipoID")
    protected String tipoID;
    @XmlElement(name = "IDCliente")
    protected String idCliente;
    @XmlElement(name = "FechaNac")
    protected String fechaNac;
    @XmlElement(name = "Datos", required = true)
    protected TrackingTarjetasDatos datos;

    /**
     * Gets the value of the firma property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirma() {
        return firma;
    }

    /**
     * Sets the value of the firma property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirma(String value) {
        this.firma = value;
    }

    /**
     * Gets the value of the canalTipo property.
     * 
     */
    public String getCanalTipo() {
        return canalTipo;
    }

    /**
     * Sets the value of the canalTipo property.
     * 
     */
    public void setCanalTipo(String value) {
        this.canalTipo = value;
    }

    /**
     * Gets the value of the canalID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCanalID() {
        return canalID;
    }

    /**
     * Sets the value of the canalID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCanalID(String value) {
        this.canalID = value;
    }

    /**
     * Gets the value of the subcanalTipo property.
     * 
     */
    public String getSubcanalTipo() {
        return subcanalTipo;
    }

    /**
     * Sets the value of the subcanalTipo property.
     * 
     */
    public void setSubcanalTipo(String value) {
        this.subcanalTipo = value;
    }

    /**
     * Gets the value of the subcanalID property.
     * 
     */
    public String getSubcanalID() {
        return subcanalID;
    }

    /**
     * Sets the value of the subcanalID property.
     * 
     */
    public void setSubcanalID(String value) {
        this.subcanalID = value;
    }

    /**
     * Gets the value of the nup property.
     * 
     */
    public String getNUP() {
        return nup;
    }

    /**
     * Sets the value of the nup property.
     * 
     */
    public void setNUP(String value) {
        this.nup = value;
    }

    /**
     * Gets the value of the tipoPersona property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoPersona() {
        return tipoPersona;
    }

    /**
     * Sets the value of the tipoPersona property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoPersona(String value) {
        this.tipoPersona = value;
    }

    /**
     * Gets the value of the tipoID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoID() {
        return tipoID;
    }

    /**
     * Sets the value of the tipoID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoID(String value) {
        this.tipoID = value;
    }

    /**
     * Gets the value of the idCliente property.
     * 
     */
    public String getIDCliente() {
        return idCliente;
    }

    /**
     * Sets the value of the idCliente property.
     * 
     */
    public void setIDCliente(String value) {
        this.idCliente = value;
    }

    /**
     * Gets the value of the fechaNac property.
     * 
     */
    public String getFechaNac() {
        return fechaNac;
    }

    /**
     * Sets the value of the fechaNac property.
     * 
     */
    public void setFechaNac(String value) {
        this.fechaNac = value;
    }

    /**
     * Gets the value of the datos property.
     * 
     * @return
     *     possible object is
     *     {@link TrackingTarjetasDatos }
     *     
     */
    public TrackingTarjetasDatos getDatos() {
        return datos;
    }

    /**
     * Sets the value of the datos property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrackingTarjetasDatos }
     *     
     */
    public void setDatos(TrackingTarjetasDatos value) {
        this.datos = value;
    }

}
