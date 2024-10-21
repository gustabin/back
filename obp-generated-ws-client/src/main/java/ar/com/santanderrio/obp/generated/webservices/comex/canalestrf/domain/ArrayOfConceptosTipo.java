
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfConceptosTipo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfConceptosTipo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ConceptosTipo" type="{Domain}ConceptosTipo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfConceptosTipo", propOrder = {
    "conceptosTipo"
})
public class ArrayOfConceptosTipo {

    @XmlElement(name = "ConceptosTipo", nillable = true)
    protected List<ConceptosTipo> conceptosTipo;

    /**
     * Gets the value of the conceptosTipo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the conceptosTipo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConceptosTipo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConceptosTipo }
     * 
     * 
     */
    public List<ConceptosTipo> getConceptosTipo() {
        if (conceptosTipo == null) {
            conceptosTipo = new ArrayList<ConceptosTipo>();
        }
        return this.conceptosTipo;
    }

}
