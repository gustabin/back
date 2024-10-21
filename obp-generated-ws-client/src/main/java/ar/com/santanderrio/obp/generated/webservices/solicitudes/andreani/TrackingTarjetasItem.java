package ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "nroPieza",
    "nroIdComponente",
    "lista"
})
public class TrackingTarjetasItem {

    @XmlElement(name = "Nro_Pieza")
    protected String nroPieza;
    @XmlElement(name = "Nro_Id_Componente")
    protected String nroIdComponente;
    @XmlElement(name = "Lista", required = true)
    protected TrackingTarjetasLista lista;

    /**
     * Gets the value of the nroPieza property.
     * 
     */
    public String getNroPieza() {
        return nroPieza;
    }

    /**
     * Sets the value of the nroPieza property.
     * 
     */
    public void setNroPieza(String value) {
        this.nroPieza = value;
    }

    /**
     * Gets the value of the nroIdComponente property.
     * 
     */
    public String getNroIdComponente() {
        return nroIdComponente;
    }

    /**
     * Sets the value of the nroIdComponente property.
     *  
     */
    public void setNroIdComponente(String value) {
        this.nroIdComponente = value;
    }

    /**
     * Gets the value of the lista property.
     * 
     * @return
     *     possible object is
     *     {@link TrackingTarjetasLista }
     *     
     */
    public TrackingTarjetasLista getLista() {
        return lista;
    }

    /**
     * Sets the value of the lista property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrackingTarjetasLista }
     *     
     */
    public void setLista(TrackingTarjetasLista value) {
        this.lista = value;
    }

}
