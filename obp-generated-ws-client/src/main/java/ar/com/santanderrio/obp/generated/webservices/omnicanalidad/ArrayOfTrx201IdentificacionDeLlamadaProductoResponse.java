
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfTrx201IdentificacionDeLlamadaProductoResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfTrx201IdentificacionDeLlamadaProductoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Trx201IdentificacionDeLlamadaProductoResponse" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx201}Trx201IdentificacionDeLlamadaProductoResponse" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfTrx201IdentificacionDeLlamadaProductoResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx201", propOrder = {
    "trx201IdentificacionDeLlamadaProductoResponse"
})
public class ArrayOfTrx201IdentificacionDeLlamadaProductoResponse {

    @XmlElement(name = "Trx201IdentificacionDeLlamadaProductoResponse", nillable = true)
    protected List<Trx201IdentificacionDeLlamadaProductoResponse> trx201IdentificacionDeLlamadaProductoResponse;

    /**
     * Gets the value of the trx201IdentificacionDeLlamadaProductoResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trx201IdentificacionDeLlamadaProductoResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrx201IdentificacionDeLlamadaProductoResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Trx201IdentificacionDeLlamadaProductoResponse }
     * 
     * 
     */
    public List<Trx201IdentificacionDeLlamadaProductoResponse> getTrx201IdentificacionDeLlamadaProductoResponse() {
        if (trx201IdentificacionDeLlamadaProductoResponse == null) {
            trx201IdentificacionDeLlamadaProductoResponse = new ArrayList<Trx201IdentificacionDeLlamadaProductoResponse>();
        }
        return this.trx201IdentificacionDeLlamadaProductoResponse;
    }

}
