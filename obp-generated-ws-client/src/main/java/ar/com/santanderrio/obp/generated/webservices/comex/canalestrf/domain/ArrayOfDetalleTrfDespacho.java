
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfDetalleTrfDespacho complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfDetalleTrfDespacho">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DetalleTrfDespacho" type="{Domain}DetalleTrfDespacho" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfDetalleTrfDespacho", propOrder = {
    "detalleTrfDespacho"
})
public class ArrayOfDetalleTrfDespacho {

    @XmlElement(name = "DetalleTrfDespacho", nillable = true)
    protected List<DetalleTrfDespacho> detalleTrfDespacho;

    /**
     * Gets the value of the detalleTrfDespacho property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the detalleTrfDespacho property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDetalleTrfDespacho().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DetalleTrfDespacho }
     * 
     * 
     */
    public List<DetalleTrfDespacho> getDetalleTrfDespacho() {
        if (detalleTrfDespacho == null) {
            detalleTrfDespacho = new ArrayList<DetalleTrfDespacho>();
        }
        return this.detalleTrfDespacho;
    }

}
