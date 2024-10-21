
package ar.com.santanderrio.obp.generated.webservices.discador;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfTrx278ConsultaModalidadTransferenciaIterationResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfTrx278ConsultaModalidadTransferenciaIterationResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Trx278ConsultaModalidadTransferenciaIterationResponse" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx278}Trx278ConsultaModalidadTransferenciaIterationResponse" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfTrx278ConsultaModalidadTransferenciaIterationResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx278", propOrder = {
    "trx278ConsultaModalidadTransferenciaIterationResponse"
})
public class ArrayOfTrx278ConsultaModalidadTransferenciaIterationResponse {

    @XmlElement(name = "Trx278ConsultaModalidadTransferenciaIterationResponse", nillable = true)
    protected List<Trx278ConsultaModalidadTransferenciaIterationResponse> trx278ConsultaModalidadTransferenciaIterationResponse;

    /**
     * Gets the value of the trx278ConsultaModalidadTransferenciaIterationResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trx278ConsultaModalidadTransferenciaIterationResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrx278ConsultaModalidadTransferenciaIterationResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Trx278ConsultaModalidadTransferenciaIterationResponse }
     * 
     * 
     */
    public List<Trx278ConsultaModalidadTransferenciaIterationResponse> getTrx278ConsultaModalidadTransferenciaIterationResponse() {
        if (trx278ConsultaModalidadTransferenciaIterationResponse == null) {
            trx278ConsultaModalidadTransferenciaIterationResponse = new ArrayList<Trx278ConsultaModalidadTransferenciaIterationResponse>();
        }
        return this.trx278ConsultaModalidadTransferenciaIterationResponse;
    }

}
