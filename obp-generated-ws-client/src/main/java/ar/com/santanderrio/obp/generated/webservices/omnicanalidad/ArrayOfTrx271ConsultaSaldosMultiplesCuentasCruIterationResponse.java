
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfTrx271ConsultaSaldosMultiplesCuentasCruIterationResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfTrx271ConsultaSaldosMultiplesCuentasCruIterationResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Trx271ConsultaSaldosMultiplesCuentasCruIterationResponse" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx271}Trx271ConsultaSaldosMultiplesCuentasCruIterationResponse" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfTrx271ConsultaSaldosMultiplesCuentasCruIterationResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx271", propOrder = {
    "trx271ConsultaSaldosMultiplesCuentasCruIterationResponse"
})
public class ArrayOfTrx271ConsultaSaldosMultiplesCuentasCruIterationResponse {

    @XmlElement(name = "Trx271ConsultaSaldosMultiplesCuentasCruIterationResponse", nillable = true)
    protected List<Trx271ConsultaSaldosMultiplesCuentasCruIterationResponse> trx271ConsultaSaldosMultiplesCuentasCruIterationResponse;

    /**
     * Gets the value of the trx271ConsultaSaldosMultiplesCuentasCruIterationResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trx271ConsultaSaldosMultiplesCuentasCruIterationResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrx271ConsultaSaldosMultiplesCuentasCruIterationResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Trx271ConsultaSaldosMultiplesCuentasCruIterationResponse }
     * 
     * 
     */
    public List<Trx271ConsultaSaldosMultiplesCuentasCruIterationResponse> getTrx271ConsultaSaldosMultiplesCuentasCruIterationResponse() {
        if (trx271ConsultaSaldosMultiplesCuentasCruIterationResponse == null) {
            trx271ConsultaSaldosMultiplesCuentasCruIterationResponse = new ArrayList<Trx271ConsultaSaldosMultiplesCuentasCruIterationResponse>();
        }
        return this.trx271ConsultaSaldosMultiplesCuentasCruIterationResponse;
    }

}
