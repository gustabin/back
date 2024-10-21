
package ar.com.santanderrio.obp.generated.webservices.discador;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfTrx5006ConsultaMovimientosCruIterationResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfTrx5006ConsultaMovimientosCruIterationResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Trx5006ConsultaMovimientosCruIterationResponse" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5006}Trx5006ConsultaMovimientosCruIterationResponse" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfTrx5006ConsultaMovimientosCruIterationResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5006", propOrder = {
    "trx5006ConsultaMovimientosCruIterationResponse"
})
public class ArrayOfTrx5006ConsultaMovimientosCruIterationResponse {

    @XmlElement(name = "Trx5006ConsultaMovimientosCruIterationResponse", nillable = true)
    protected List<Trx5006ConsultaMovimientosCruIterationResponse> trx5006ConsultaMovimientosCruIterationResponse;

    /**
     * Gets the value of the trx5006ConsultaMovimientosCruIterationResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trx5006ConsultaMovimientosCruIterationResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrx5006ConsultaMovimientosCruIterationResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Trx5006ConsultaMovimientosCruIterationResponse }
     * 
     * 
     */
    public List<Trx5006ConsultaMovimientosCruIterationResponse> getTrx5006ConsultaMovimientosCruIterationResponse() {
        if (trx5006ConsultaMovimientosCruIterationResponse == null) {
            trx5006ConsultaMovimientosCruIterationResponse = new ArrayList<Trx5006ConsultaMovimientosCruIterationResponse>();
        }
        return this.trx5006ConsultaMovimientosCruIterationResponse;
    }

}
