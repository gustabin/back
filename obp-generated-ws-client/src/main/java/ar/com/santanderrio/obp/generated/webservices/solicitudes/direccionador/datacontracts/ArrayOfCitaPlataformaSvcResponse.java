
package ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfCitaPlataformaSvcResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfCitaPlataformaSvcResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CitaPlataformaSvcResponse" type="{http://schemas.datacontract.org/2004/07/BSCH.CRM.Contracts.Direccionador.DataContracts}CitaPlataformaSvcResponse" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfCitaPlataformaSvcResponse", propOrder = {
    "citaPlataformaSvcResponse"
})
public class ArrayOfCitaPlataformaSvcResponse {

    @XmlElement(name = "CitaPlataformaSvcResponse", nillable = true)
    protected List<CitaPlataformaSvcResponse> citaPlataformaSvcResponse;

    /**
     * Gets the value of the citaPlataformaSvcResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the citaPlataformaSvcResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCitaPlataformaSvcResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CitaPlataformaSvcResponse }
     * 
     * 
     */
    public List<CitaPlataformaSvcResponse> getCitaPlataformaSvcResponse() {
        if (citaPlataformaSvcResponse == null) {
            citaPlataformaSvcResponse = new ArrayList<CitaPlataformaSvcResponse>();
        }
        return this.citaPlataformaSvcResponse;
    }

}
