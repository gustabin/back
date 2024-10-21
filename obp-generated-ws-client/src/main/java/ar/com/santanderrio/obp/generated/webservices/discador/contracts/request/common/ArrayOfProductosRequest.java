
package ar.com.santanderrio.obp.generated.webservices.discador.contracts.request.common;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfProductosRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfProductosRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProductosRequest" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Request}ProductosRequest" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfProductosRequest", propOrder = {
    "productosRequest"
})
public class ArrayOfProductosRequest {

    @XmlElement(name = "ProductosRequest", nillable = true)
    protected List<ProductosRequest> productosRequest;

    /**
     * Gets the value of the productosRequest property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productosRequest property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductosRequest().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductosRequest }
     * 
     * 
     */
    public List<ProductosRequest> getProductosRequest() {
        if (productosRequest == null) {
            productosRequest = new ArrayList<ProductosRequest>();
        }
        return this.productosRequest;
    }

}
