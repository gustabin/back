
package ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfMotivoSvcResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfMotivoSvcResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MotivoSvcResponse" type="{http://schemas.datacontract.org/2004/07/BSCH.CRM.Contracts.Direccionador.DataContracts}MotivoSvcResponse" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfMotivoSvcResponse", propOrder = {
    "motivoSvcResponse"
})
public class ArrayOfMotivoSvcResponse {

    @XmlElement(name = "MotivoSvcResponse", nillable = true)
    protected List<MotivoSvcResponse> motivoSvcResponse;

    /**
     * Gets the value of the motivoSvcResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the motivoSvcResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMotivoSvcResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MotivoSvcResponse }
     * 
     * 
     */
    public List<MotivoSvcResponse> getMotivoSvcResponse() {
        if (motivoSvcResponse == null) {
            motivoSvcResponse = new ArrayList<MotivoSvcResponse>();
        }
        return this.motivoSvcResponse;
    }

}
