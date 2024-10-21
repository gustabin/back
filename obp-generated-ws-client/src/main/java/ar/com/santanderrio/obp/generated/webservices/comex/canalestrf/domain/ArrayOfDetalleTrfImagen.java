
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfDetalleTrfImagen complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfDetalleTrfImagen">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DetalleTrfImagen" type="{Domain}DetalleTrfImagen" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfDetalleTrfImagen", propOrder = {
    "detalleTrfImagen"
})
public class ArrayOfDetalleTrfImagen {

    @XmlElement(name = "DetalleTrfImagen", nillable = true)
    protected List<DetalleTrfImagen> detalleTrfImagen;

    /**
     * Gets the value of the detalleTrfImagen property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the detalleTrfImagen property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDetalleTrfImagen().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DetalleTrfImagen }
     * 
     * 
     */
    public List<DetalleTrfImagen> getDetalleTrfImagen() {
        if (detalleTrfImagen == null) {
            detalleTrfImagen = new ArrayList<DetalleTrfImagen>();
        }
        return this.detalleTrfImagen;
    }

}
