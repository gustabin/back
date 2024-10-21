package ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "pieza"
})
public class TrackingTarjetasPiezas {

    @XmlElement(name = "Pieza")
    protected List<TrackingTarjetasPieza> pieza;

    public List<TrackingTarjetasPieza> getPieza() {
        return pieza;
    }

    public void setPieza(List<TrackingTarjetasPieza> pieza) {
        this.pieza = pieza;
    }

}
