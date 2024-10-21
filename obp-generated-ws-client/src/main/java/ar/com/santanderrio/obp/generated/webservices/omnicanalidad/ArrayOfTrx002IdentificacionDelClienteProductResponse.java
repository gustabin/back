
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfTrx002IdentificacionDelClienteProductResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfTrx002IdentificacionDelClienteProductResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Trx002IdentificacionDelClienteProductResponse" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx002}Trx002IdentificacionDelClienteProductResponse" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfTrx002IdentificacionDelClienteProductResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx002", propOrder = {
    "trx002IdentificacionDelClienteProductResponse"
})
public class ArrayOfTrx002IdentificacionDelClienteProductResponse {

    @XmlElement(name = "Trx002IdentificacionDelClienteProductResponse", nillable = true)
    protected List<Trx002IdentificacionDelClienteProductResponse> trx002IdentificacionDelClienteProductResponse;

    /**
     * Gets the value of the trx002IdentificacionDelClienteProductResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trx002IdentificacionDelClienteProductResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrx002IdentificacionDelClienteProductResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Trx002IdentificacionDelClienteProductResponse }
     * 
     * 
     */
    public List<Trx002IdentificacionDelClienteProductResponse> getTrx002IdentificacionDelClienteProductResponse() {
        if (trx002IdentificacionDelClienteProductResponse == null) {
            trx002IdentificacionDelClienteProductResponse = new ArrayList<Trx002IdentificacionDelClienteProductResponse>();
        }
        return this.trx002IdentificacionDelClienteProductResponse;
    }

}
