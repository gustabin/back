
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfConsNif complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfConsNif">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ConsNif" type="{Domain}ConsNif" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfConsNif", propOrder = {
    "consNif"
})
public class ArrayOfConsNif {

    @XmlElement(name = "ConsNif", nillable = true)
    protected List<ConsNif> consNif;

    /**
     * Gets the value of the consNif property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the consNif property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConsNif().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConsNif }
     * 
     * 
     */
    public List<ConsNif> getConsNif() {
        if (consNif == null) {
            consNif = new ArrayList<ConsNif>();
        }
        return this.consNif;
    }

}
