package ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "item"
})
public class TrackingTarjetasLista {

    @XmlElement(required = true)
    protected TrackingTarjetasItem2 item;

    /**
     * Gets the value of the item2 property.
     * 
     * @return
     *     possible object is
     *     {@link TrackingTarjetasItem2 }
     *     
     */
    public TrackingTarjetasItem2 getItem() {
        return item;
    }

    /**
     * Sets the value of the item2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrackingTarjetasItem2 }
     *     
     */
    public void setItem(TrackingTarjetasItem2 value) {
        this.item = value;
    }

}
