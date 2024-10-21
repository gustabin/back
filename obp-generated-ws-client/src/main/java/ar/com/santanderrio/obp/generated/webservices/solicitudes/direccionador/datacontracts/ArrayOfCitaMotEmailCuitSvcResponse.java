
package ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfCitaMotEmailCuitSvcResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfCitaMotEmailCuitSvcResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CitaMotEmailCuitSvcResponse" type="{http://schemas.datacontract.org/2004/07/BSCH.CRM.Contracts.Direccionador.DataContracts}CitaMotEmailCuitSvcResponse" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfCitaMotEmailCuitSvcResponse", propOrder = {
    "citaMotEmailCuitSvcResponse"
})
public class ArrayOfCitaMotEmailCuitSvcResponse {

    @XmlElement(name = "CitaMotEmailCuitSvcResponse", nillable = true)
    protected List<CitaMotEmailCuitSvcResponse> citaMotEmailCuitSvcResponse;

    /**
     * Gets the value of the citaMotEmailCuitSvcResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the citaMotEmailCuitSvcResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCitaMotEmailCuitSvcResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CitaMotEmailCuitSvcResponse }
     * 
     * 
     */
    public List<CitaMotEmailCuitSvcResponse> getCitaMotEmailCuitSvcResponse() {
        if (citaMotEmailCuitSvcResponse == null) {
            citaMotEmailCuitSvcResponse = new ArrayList<CitaMotEmailCuitSvcResponse>();
        }
        return this.citaMotEmailCuitSvcResponse;
    }

}
