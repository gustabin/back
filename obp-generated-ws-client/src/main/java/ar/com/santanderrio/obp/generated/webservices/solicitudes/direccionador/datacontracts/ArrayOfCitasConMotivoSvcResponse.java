
package ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfCitasConMotivoSvcResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfCitasConMotivoSvcResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CitasConMotivoSvcResponse" type="{http://schemas.datacontract.org/2004/07/BSCH.CRM.Contracts.Direccionador.DataContracts}CitasConMotivoSvcResponse" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfCitasConMotivoSvcResponse", propOrder = {
    "citasConMotivoSvcResponse"
})
public class ArrayOfCitasConMotivoSvcResponse {

    @XmlElement(name = "CitasConMotivoSvcResponse", nillable = true)
    protected List<CitasConMotivoSvcResponse> citasConMotivoSvcResponse;

    /**
     * Gets the value of the citasConMotivoSvcResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the citasConMotivoSvcResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCitasConMotivoSvcResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CitasConMotivoSvcResponse }
     * 
     * 
     */
    public List<CitasConMotivoSvcResponse> getCitasConMotivoSvcResponse() {
        if (citasConMotivoSvcResponse == null) {
            citasConMotivoSvcResponse = new ArrayList<CitasConMotivoSvcResponse>();
        }
        return this.citasConMotivoSvcResponse;
    }

}
