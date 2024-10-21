
package ar.com.santanderrio.obp.generated.webservices.bpriv.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfLoadAtits complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfLoadAtits">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LoadAtits" type="{http://schemas.datacontract.org/2004/07/BancaPrivada.Domain}LoadAtits" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfLoadAtits", propOrder = {
    "loadAtits"
})
public class ArrayOfLoadAtits {

    @XmlElement(name = "LoadAtits", nillable = true)
    protected List<LoadAtits> loadAtits;

    /**
     * Gets the value of the loadAtits property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the loadAtits property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLoadAtits().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LoadAtits }
     * 
     * 
     */
    public List<LoadAtits> getLoadAtits() {
        if (loadAtits == null) {
            loadAtits = new ArrayList<LoadAtits>();
        }
        return this.loadAtits;
    }

}
