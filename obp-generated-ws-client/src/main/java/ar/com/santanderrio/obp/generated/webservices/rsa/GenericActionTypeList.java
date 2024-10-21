
package ar.com.santanderrio.obp.generated.webservices.rsa;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GenericActionTypeList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GenericActionTypeList">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.csd.rsa.com}ActionTypeList">
 *       &lt;sequence>
 *         &lt;element name="genericActionTypes" type="{http://ws.csd.rsa.com}GenericActionType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GenericActionTypeList", propOrder = {
    "genericActionTypes"
})
public class GenericActionTypeList
    extends ActionTypeList
{

    protected List<GenericActionType> genericActionTypes;

    /**
     * Gets the value of the genericActionTypes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the genericActionTypes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGenericActionTypes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GenericActionType }
     * 
     * 
     */
    public List<GenericActionType> getGenericActionTypes() {
        if (genericActionTypes == null) {
            genericActionTypes = new ArrayList<GenericActionType>();
        }
        return this.genericActionTypes;
    }

}
