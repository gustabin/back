
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfTrx083TasaPlazoFijoResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfTrx083TasaPlazoFijoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Trx083TasaPlazoFijoResponse" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx083}Trx083TasaPlazoFijoResponse" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfTrx083TasaPlazoFijoResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx083", propOrder = {
    "trx083TasaPlazoFijoResponse"
})
public class ArrayOfTrx083TasaPlazoFijoResponse {

    @XmlElement(name = "Trx083TasaPlazoFijoResponse", nillable = true)
    protected List<Trx083TasaPlazoFijoResponse> trx083TasaPlazoFijoResponse;

    /**
     * Gets the value of the trx083TasaPlazoFijoResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trx083TasaPlazoFijoResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrx083TasaPlazoFijoResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Trx083TasaPlazoFijoResponse }
     * 
     * 
     */
    public List<Trx083TasaPlazoFijoResponse> getTrx083TasaPlazoFijoResponse() {
        if (trx083TasaPlazoFijoResponse == null) {
            trx083TasaPlazoFijoResponse = new ArrayList<Trx083TasaPlazoFijoResponse>();
        }
        return this.trx083TasaPlazoFijoResponse;
    }

}
