
package ar.com.santanderrio.obp.generated.webservices.crm;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfSubResponseCliente complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSubResponseCliente">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SubResponseCliente" type="{http://model.alertas.crm.bancorio.com/}SubResponseCliente" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSubResponseCliente", propOrder = {
    "subResponseCliente"
})
public class ArrayOfSubResponseCliente {

    @XmlElement(name = "SubResponseCliente", nillable = true)
    protected List<SubResponseCliente> subResponseCliente;

    /**
     * Gets the value of the subResponseCliente property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subResponseCliente property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubResponseCliente().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SubResponseCliente }
     * 
     * 
     */
    public List<SubResponseCliente> getSubResponseCliente() {
        if (subResponseCliente == null) {
            subResponseCliente = new ArrayList<SubResponseCliente>();
        }
        return this.subResponseCliente;
    }

}
