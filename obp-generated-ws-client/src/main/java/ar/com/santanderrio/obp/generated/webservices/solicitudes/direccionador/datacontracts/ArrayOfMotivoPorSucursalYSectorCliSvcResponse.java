
package ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfMotivoPorSucursalYSectorCliSvcResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfMotivoPorSucursalYSectorCliSvcResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MotivoPorSucursalYSectorCliSvcResponse" type="{http://schemas.datacontract.org/2004/07/BSCH.CRM.Contracts.Direccionador.DataContracts}MotivoPorSucursalYSectorCliSvcResponse" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfMotivoPorSucursalYSectorCliSvcResponse", propOrder = {
    "motivoPorSucursalYSectorCliSvcResponse"
})
public class ArrayOfMotivoPorSucursalYSectorCliSvcResponse {

    @XmlElement(name = "MotivoPorSucursalYSectorCliSvcResponse", nillable = true)
    protected List<MotivoPorSucursalYSectorCliSvcResponse> motivoPorSucursalYSectorCliSvcResponse;

    /**
     * Gets the value of the motivoPorSucursalYSectorCliSvcResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the motivoPorSucursalYSectorCliSvcResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMotivoPorSucursalYSectorCliSvcResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MotivoPorSucursalYSectorCliSvcResponse }
     * 
     * 
     */
    public List<MotivoPorSucursalYSectorCliSvcResponse> getMotivoPorSucursalYSectorCliSvcResponse() {
        if (motivoPorSucursalYSectorCliSvcResponse == null) {
            motivoPorSucursalYSectorCliSvcResponse = new ArrayList<MotivoPorSucursalYSectorCliSvcResponse>();
        }
        return this.motivoPorSucursalYSectorCliSvcResponse;
    }

}
