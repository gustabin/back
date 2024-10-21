
package ar.com.santanderrio.obp.generated.webservices.discador;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfTrx802IdentificacionDelClienteProductResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfTrx802IdentificacionDelClienteProductResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Trx802IdentificacionDelClienteProductResponse" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx802}Trx802IdentificacionDelClienteProductResponse" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfTrx802IdentificacionDelClienteProductResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx802", propOrder = {
    "trx802IdentificacionDelClienteProductResponse"
})
public class ArrayOfTrx802IdentificacionDelClienteProductResponse {

    @XmlElement(name = "Trx802IdentificacionDelClienteProductResponse", nillable = true)
    protected List<Trx802IdentificacionDelClienteProductResponse> trx802IdentificacionDelClienteProductResponse;

    /**
     * Gets the value of the trx802IdentificacionDelClienteProductResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trx802IdentificacionDelClienteProductResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrx802IdentificacionDelClienteProductResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Trx802IdentificacionDelClienteProductResponse }
     * 
     * 
     */
    public List<Trx802IdentificacionDelClienteProductResponse> getTrx802IdentificacionDelClienteProductResponse() {
        if (trx802IdentificacionDelClienteProductResponse == null) {
            trx802IdentificacionDelClienteProductResponse = new ArrayList<Trx802IdentificacionDelClienteProductResponse>();
        }
        return this.trx802IdentificacionDelClienteProductResponse;
    }

}
