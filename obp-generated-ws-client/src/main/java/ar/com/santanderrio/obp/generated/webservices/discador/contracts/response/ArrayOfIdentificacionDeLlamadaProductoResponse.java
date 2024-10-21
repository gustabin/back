
package ar.com.santanderrio.obp.generated.webservices.discador.contracts.response;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfIdentificacionDeLlamadaProductoResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfIdentificacionDeLlamadaProductoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdentificacionDeLlamadaProductoResponse" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response}IdentificacionDeLlamadaProductoResponse" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfIdentificacionDeLlamadaProductoResponse", propOrder = {
    "identificacionDeLlamadaProductoResponse"
})
public class ArrayOfIdentificacionDeLlamadaProductoResponse {

    @XmlElement(name = "IdentificacionDeLlamadaProductoResponse", nillable = true)
    protected List<IdentificacionDeLlamadaProductoResponse> identificacionDeLlamadaProductoResponse;

    /**
     * Gets the value of the identificacionDeLlamadaProductoResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the identificacionDeLlamadaProductoResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdentificacionDeLlamadaProductoResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IdentificacionDeLlamadaProductoResponse }
     * 
     * 
     */
    public List<IdentificacionDeLlamadaProductoResponse> getIdentificacionDeLlamadaProductoResponse() {
        if (identificacionDeLlamadaProductoResponse == null) {
            identificacionDeLlamadaProductoResponse = new ArrayList<IdentificacionDeLlamadaProductoResponse>();
        }
        return this.identificacionDeLlamadaProductoResponse;
    }

}
