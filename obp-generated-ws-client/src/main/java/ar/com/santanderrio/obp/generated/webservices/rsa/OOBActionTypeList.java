
package ar.com.santanderrio.obp.generated.webservices.rsa;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OOBActionTypeList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OOBActionTypeList">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.csd.rsa.com}ActionTypeList">
 *       &lt;sequence>
 *         &lt;element name="oobActionType" type="{http://ws.csd.rsa.com}OOBActionType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OOBActionTypeList", propOrder = {
    "oobActionType"
})
public class OOBActionTypeList
    extends ActionTypeList
{

    protected List<OOBActionType> oobActionType;

    /**
     * Gets the value of the oobActionType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the oobActionType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOobActionType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OOBActionType }
     * 
     * 
     */
    public List<OOBActionType> getOobActionType() {
        if (oobActionType == null) {
            oobActionType = new ArrayList<OOBActionType>();
        }
        return this.oobActionType;
    }

}
