
package ar.com.santanderrio.obp.generated.webservices.productos;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOf_1587703434_32493182_nillable_InfoRequeridaWS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOf_1587703434_32493182_nillable_InfoRequeridaWS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InfoRequeridaWS" type="{http://webService.core.ges.rio.com}InfoRequeridaWS" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOf_1587703434_32493182_nillable_InfoRequeridaWS", propOrder = {
    "infoRequeridaWS"
})
public class ArrayOf158770343432493182NillableInfoRequeridaWS {

    @XmlElement(name = "InfoRequeridaWS", nillable = true)
    protected List<InfoRequeridaWS> infoRequeridaWS;

    /**
     * Gets the value of the infoRequeridaWS property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the infoRequeridaWS property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInfoRequeridaWS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InfoRequeridaWS }
     * 
     * 
     */
    public List<InfoRequeridaWS> getInfoRequeridaWS() {
        if (infoRequeridaWS == null) {
            infoRequeridaWS = new ArrayList<InfoRequeridaWS>();
        }
        return this.infoRequeridaWS;
    }

}
