
package ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfCondicionVenta complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfCondicionVenta">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CondicionVenta" type="{Domain}CondicionVenta" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfCondicionVenta", propOrder = {
    "condicionVenta"
})
public class ArrayOfCondicionVenta {

    @XmlElement(name = "CondicionVenta", nillable = true)
    protected List<CondicionVenta> condicionVenta;

    /**
     * Gets the value of the condicionVenta property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the condicionVenta property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCondicionVenta().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CondicionVenta }
     * 
     * 
     */
    public List<CondicionVenta> getCondicionVenta() {
        if (condicionVenta == null) {
            condicionVenta = new ArrayList<CondicionVenta>();
        }
        return this.condicionVenta;
    }

}
