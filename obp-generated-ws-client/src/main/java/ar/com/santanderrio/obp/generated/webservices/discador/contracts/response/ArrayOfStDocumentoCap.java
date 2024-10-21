
package ar.com.santanderrio.obp.generated.webservices.discador.contracts.response;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfStDocumentoCap complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfStDocumentoCap">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StDocumentoCap" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response}StDocumentoCap" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfStDocumentoCap", propOrder = {
    "stDocumentoCap"
})
public class ArrayOfStDocumentoCap {

    @XmlElement(name = "StDocumentoCap", nillable = true)
    protected List<StDocumentoCap> stDocumentoCap;

    /**
     * Gets the value of the stDocumentoCap property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the stDocumentoCap property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStDocumentoCap().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StDocumentoCap }
     * 
     * 
     */
    public List<StDocumentoCap> getStDocumentoCap() {
        if (stDocumentoCap == null) {
            stDocumentoCap = new ArrayList<StDocumentoCap>();
        }
        return this.stDocumentoCap;
    }

}
