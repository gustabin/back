
package ar.com.santanderrio.obp.generated.webservices.segmento;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfConsolidateDataResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfConsolidateDataResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ConsolidateDataResponse" type="{http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData}ConsolidateDataResponse" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfConsolidateDataResponse", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", propOrder = {
    "consolidateDataResponse"
})
public class ArrayOfConsolidateDataResponse {

    @XmlElement(name = "ConsolidateDataResponse", nillable = true)
    protected List<ConsolidateDataResponse> consolidateDataResponse;

    /**
     * Gets the value of the consolidateDataResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the consolidateDataResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConsolidateDataResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConsolidateDataResponse }
     * 
     * 
     */
    public List<ConsolidateDataResponse> getConsolidateDataResponse() {
        if (consolidateDataResponse == null) {
            consolidateDataResponse = new ArrayList<ConsolidateDataResponse>();
        }
        return this.consolidateDataResponse;
    }

}
