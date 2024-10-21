
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfTrx089DebitoAutomaticoResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfTrx089DebitoAutomaticoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Trx089DebitoAutomaticoResponse" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx089}Trx089DebitoAutomaticoResponse" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfTrx089DebitoAutomaticoResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx089", propOrder = {
    "trx089DebitoAutomaticoResponse"
})
public class ArrayOfTrx089DebitoAutomaticoResponse {

    @XmlElement(name = "Trx089DebitoAutomaticoResponse", nillable = true)
    protected List<Trx089DebitoAutomaticoResponse> trx089DebitoAutomaticoResponse;

    /**
     * Gets the value of the trx089DebitoAutomaticoResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trx089DebitoAutomaticoResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrx089DebitoAutomaticoResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Trx089DebitoAutomaticoResponse }
     * 
     * 
     */
    public List<Trx089DebitoAutomaticoResponse> getTrx089DebitoAutomaticoResponse() {
        if (trx089DebitoAutomaticoResponse == null) {
            trx089DebitoAutomaticoResponse = new ArrayList<Trx089DebitoAutomaticoResponse>();
        }
        return this.trx089DebitoAutomaticoResponse;
    }

}
