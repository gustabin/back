
package ar.com.santanderrio.obp.generated.webservices.discador.contracts.response;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfStPagoCap complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfStPagoCap">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StPagoCap" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response}StPagoCap" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfStPagoCap", propOrder = {
    "stPagoCap"
})
public class ArrayOfStPagoCap {

    @XmlElement(name = "StPagoCap", nillable = true)
    protected List<StPagoCap> stPagoCap;

    /**
     * Gets the value of the stPagoCap property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the stPagoCap property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStPagoCap().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StPagoCap }
     * 
     * 
     */
    public List<StPagoCap> getStPagoCap() {
        if (stPagoCap == null) {
            stPagoCap = new ArrayList<StPagoCap>();
        }
        return this.stPagoCap;
    }

}
