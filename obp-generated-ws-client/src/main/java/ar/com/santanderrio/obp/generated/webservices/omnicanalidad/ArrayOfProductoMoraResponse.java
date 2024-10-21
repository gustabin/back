
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfProductoMoraResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfProductoMoraResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProductoMoraResponse" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response}ProductoMoraResponse" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfProductoMoraResponse", propOrder = {
    "productoMoraResponse"
})
public class ArrayOfProductoMoraResponse {

    @XmlElement(name = "ProductoMoraResponse", nillable = true)
    protected List<ProductoMoraResponse> productoMoraResponse;

    /**
     * Gets the value of the productoMoraResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productoMoraResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductoMoraResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductoMoraResponse }
     * 
     * 
     */
    public List<ProductoMoraResponse> getProductoMoraResponse() {
        if (productoMoraResponse == null) {
            productoMoraResponse = new ArrayList<ProductoMoraResponse>();
        }
        return this.productoMoraResponse;
    }

}
