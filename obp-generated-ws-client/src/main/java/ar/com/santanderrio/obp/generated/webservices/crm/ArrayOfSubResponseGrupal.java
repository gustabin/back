
package ar.com.santanderrio.obp.generated.webservices.crm;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfSubResponseGrupal complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSubResponseGrupal">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SubResponseGrupal" type="{http://model.alertas.crm.bancorio.com/}SubResponseGrupal" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSubResponseGrupal", propOrder = {
    "subResponseGrupal"
})
public class ArrayOfSubResponseGrupal {

    @XmlElement(name = "SubResponseGrupal", nillable = true)
    protected List<SubResponseGrupal> subResponseGrupal;

    /**
     * Gets the value of the subResponseGrupal property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subResponseGrupal property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubResponseGrupal().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SubResponseGrupal }
     * 
     * 
     */
    public List<SubResponseGrupal> getSubResponseGrupal() {
        if (subResponseGrupal == null) {
            subResponseGrupal = new ArrayList<SubResponseGrupal>();
        }
        return this.subResponseGrupal;
    }

}
