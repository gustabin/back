
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfTrx602IdentificacionDelClienteProductResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfTrx602IdentificacionDelClienteProductResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Trx602IdentificacionDelClienteProductResponse" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx602}Trx602IdentificacionDelClienteProductResponse" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfTrx602IdentificacionDelClienteProductResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx602", propOrder = {
    "trx602IdentificacionDelClienteProductResponse"
})
public class ArrayOfTrx602IdentificacionDelClienteProductResponse {

    @XmlElement(name = "Trx602IdentificacionDelClienteProductResponse", nillable = true)
    protected List<Trx602IdentificacionDelClienteProductResponse> trx602IdentificacionDelClienteProductResponse;

    /**
     * Gets the value of the trx602IdentificacionDelClienteProductResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trx602IdentificacionDelClienteProductResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrx602IdentificacionDelClienteProductResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Trx602IdentificacionDelClienteProductResponse }
     * 
     * 
     */
    public List<Trx602IdentificacionDelClienteProductResponse> getTrx602IdentificacionDelClienteProductResponse() {
        if (trx602IdentificacionDelClienteProductResponse == null) {
            trx602IdentificacionDelClienteProductResponse = new ArrayList<Trx602IdentificacionDelClienteProductResponse>();
        }
        return this.trx602IdentificacionDelClienteProductResponse;
    }

}
