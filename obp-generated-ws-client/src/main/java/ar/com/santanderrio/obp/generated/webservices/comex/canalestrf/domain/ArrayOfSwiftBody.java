
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfSwiftBody complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSwiftBody">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SwiftBody" type="{Domain}SwiftBody" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSwiftBody", propOrder = {
    "swiftBody"
})
public class ArrayOfSwiftBody {

    @XmlElement(name = "SwiftBody", nillable = true)
    protected List<SwiftBody> swiftBody;

    /**
     * Gets the value of the swiftBody property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the swiftBody property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSwiftBody().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SwiftBody }
     * 
     * 
     */
    public List<SwiftBody> getSwiftBody() {
        if (swiftBody == null) {
            swiftBody = new ArrayList<SwiftBody>();
        }
        return this.swiftBody;
    }

}
