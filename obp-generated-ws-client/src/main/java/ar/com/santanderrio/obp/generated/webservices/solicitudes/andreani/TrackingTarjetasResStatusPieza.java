/**
 * 
 */
package ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author julian.ariel.karp
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "piezaError"
})
public class TrackingTarjetasResStatusPieza {

    @XmlElement(name = "error")
    protected List<TrackingTarjetasPiezaError> piezaError;

    public List<TrackingTarjetasPiezaError> getPiezaError() {
        return piezaError;
    }

    public void setPiezaError(List<TrackingTarjetasPiezaError> piezaError) {
        this.piezaError = piezaError;
    }
    
}
