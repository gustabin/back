
package ar.com.santanderrio.obp.generated.webservices.productos;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfGestion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfGestion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Gestion" type="{http://webService.core.ges.rio.com}Gestion" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfGestion", namespace = "http://webService.core.ges.rio.com", propOrder = {
    "gestion"
})
public class ArrayOfGestion {

    @XmlElement(name = "Gestion", nillable = true)
    protected List<Gestion> gestion;

    /**
     * Gets the value of the gestion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the gestion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGestion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Gestion }
     * 
     * 
     */
    public List<Gestion> getGestion() {
        if (gestion == null) {
            gestion = new ArrayList<Gestion>();
        }
        return this.gestion;
    }

}
