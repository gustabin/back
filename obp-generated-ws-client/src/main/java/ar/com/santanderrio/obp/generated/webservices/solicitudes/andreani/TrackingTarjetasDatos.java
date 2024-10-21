package ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "item"
})
public class TrackingTarjetasDatos {

    @XmlElement(required = true)
    protected List<TrackingTarjetasItem> item;

    public List<TrackingTarjetasItem> getItem() {
        return item;
    }

    public void setItem(List<TrackingTarjetasItem> item) {
        this.item = item;
    }

}
