
package ar.com.santanderrio.obp.generated.webservices.discador;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfTrx258ConsultaValoresDebitadosIterationResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfTrx258ConsultaValoresDebitadosIterationResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Trx258ConsultaValoresDebitadosIterationResponse" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx258}Trx258ConsultaValoresDebitadosIterationResponse" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfTrx258ConsultaValoresDebitadosIterationResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx258", propOrder = {
    "trx258ConsultaValoresDebitadosIterationResponse"
})
public class ArrayOfTrx258ConsultaValoresDebitadosIterationResponse {

    @XmlElement(name = "Trx258ConsultaValoresDebitadosIterationResponse", nillable = true)
    protected List<Trx258ConsultaValoresDebitadosIterationResponse> trx258ConsultaValoresDebitadosIterationResponse;

    /**
     * Gets the value of the trx258ConsultaValoresDebitadosIterationResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trx258ConsultaValoresDebitadosIterationResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrx258ConsultaValoresDebitadosIterationResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Trx258ConsultaValoresDebitadosIterationResponse }
     * 
     * 
     */
    public List<Trx258ConsultaValoresDebitadosIterationResponse> getTrx258ConsultaValoresDebitadosIterationResponse() {
        if (trx258ConsultaValoresDebitadosIterationResponse == null) {
            trx258ConsultaValoresDebitadosIterationResponse = new ArrayList<Trx258ConsultaValoresDebitadosIterationResponse>();
        }
        return this.trx258ConsultaValoresDebitadosIterationResponse;
    }

}
