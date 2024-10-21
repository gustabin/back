
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfTrx59ConsultaValoresAcreditarIterationResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfTrx59ConsultaValoresAcreditarIterationResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Trx59ConsultaValoresAcreditarIterationResponse" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx059}Trx59ConsultaValoresAcreditarIterationResponse" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfTrx59ConsultaValoresAcreditarIterationResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx059", propOrder = {
    "trx59ConsultaValoresAcreditarIterationResponse"
})
public class ArrayOfTrx59ConsultaValoresAcreditarIterationResponse {

    @XmlElement(name = "Trx59ConsultaValoresAcreditarIterationResponse", nillable = true)
    protected List<Trx59ConsultaValoresAcreditarIterationResponse> trx59ConsultaValoresAcreditarIterationResponse;

    /**
     * Gets the value of the trx59ConsultaValoresAcreditarIterationResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trx59ConsultaValoresAcreditarIterationResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrx59ConsultaValoresAcreditarIterationResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Trx59ConsultaValoresAcreditarIterationResponse }
     * 
     * 
     */
    public List<Trx59ConsultaValoresAcreditarIterationResponse> getTrx59ConsultaValoresAcreditarIterationResponse() {
        if (trx59ConsultaValoresAcreditarIterationResponse == null) {
            trx59ConsultaValoresAcreditarIterationResponse = new ArrayList<Trx59ConsultaValoresAcreditarIterationResponse>();
        }
        return this.trx59ConsultaValoresAcreditarIterationResponse;
    }

}
