
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfTrx15ConsultaCotizacionDivisaIterationResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfTrx15ConsultaCotizacionDivisaIterationResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Trx15ConsultaCotizacionDivisaIterationResponse" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx015}Trx15ConsultaCotizacionDivisaIterationResponse" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfTrx15ConsultaCotizacionDivisaIterationResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx015", propOrder = {
    "trx15ConsultaCotizacionDivisaIterationResponse"
})
public class ArrayOfTrx15ConsultaCotizacionDivisaIterationResponse {

    @XmlElement(name = "Trx15ConsultaCotizacionDivisaIterationResponse", nillable = true)
    protected List<Trx15ConsultaCotizacionDivisaIterationResponse> trx15ConsultaCotizacionDivisaIterationResponse;

    /**
     * Gets the value of the trx15ConsultaCotizacionDivisaIterationResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trx15ConsultaCotizacionDivisaIterationResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrx15ConsultaCotizacionDivisaIterationResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Trx15ConsultaCotizacionDivisaIterationResponse }
     * 
     * 
     */
    public List<Trx15ConsultaCotizacionDivisaIterationResponse> getTrx15ConsultaCotizacionDivisaIterationResponse() {
        if (trx15ConsultaCotizacionDivisaIterationResponse == null) {
            trx15ConsultaCotizacionDivisaIterationResponse = new ArrayList<Trx15ConsultaCotizacionDivisaIterationResponse>();
        }
        return this.trx15ConsultaCotizacionDivisaIterationResponse;
    }

}
