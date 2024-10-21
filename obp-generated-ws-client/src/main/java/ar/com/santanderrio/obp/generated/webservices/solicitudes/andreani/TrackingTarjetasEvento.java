package ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "fecha",
    "idEstado",
    "estado",
    "idMotivo",
    "motivo",
    "sucursal"
})
public class TrackingTarjetasEvento {

    @XmlElement(name = "Fecha")
    protected String fecha;
    @XmlElement(name = "IdEstado")
    protected String idEstado;
    @XmlElement(name = "Estado")
    protected String estado;
    @XmlElement(name = "IdMotivo")
    protected String idMotivo;
    @XmlElement(name = "Motivo")
    protected String motivo;
    @XmlElement(name = "Sucursal")
    protected String sucursal;

    /**
     * Gets the value of the fecha property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Sets the value of the fecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecha(String value) {
        this.fecha = value;
    }

    /**
     * Gets the value of the idEstado property.
     * 
     */
    public String getIdEstado() {
        return idEstado;
    }

    /**
     * Sets the value of the idEstado property.
     * 
     */
    public void setIdEstado(String value) {
        this.idEstado = value;
    }

    /**
     * Gets the value of the estado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Sets the value of the estado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstado(String value) {
        this.estado = value;
    }

    /**
     * Gets the value of the idMotivo property.
     * 
     */
    public String getIdMotivo() {
        return idMotivo;
    }

    /**
     * Sets the value of the idMotivo property.
     * 
     */
    public void setIdMotivo(String value) {
        this.idMotivo = value;
    }

    /**
     * Gets the value of the motivo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * Sets the value of the motivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotivo(String value) {
        this.motivo = value;
    }

    /**
     * Gets the value of the sucursal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSucursal() {
        return sucursal;
    }

    /**
     * Sets the value of the sucursal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSucursal(String value) {
        this.sucursal = value;
    }

}
