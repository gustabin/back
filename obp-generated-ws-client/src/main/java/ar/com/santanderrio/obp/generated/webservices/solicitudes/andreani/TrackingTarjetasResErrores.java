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
    "error"
})
public class TrackingTarjetasResErrores {

    @XmlElement(name = "Error")
    protected List<TrackingTarjetasPiezaError> error;

    public List<TrackingTarjetasPiezaError> getError() {
        return error;
    }

    public void setError(List<TrackingTarjetasPiezaError> error) {
        this.error = error;
    }
    
}
