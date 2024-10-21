
package ar.com.santanderrio.obp.generated.webservices.crm;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfSubRequestCliente complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSubRequestCliente">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SubRequestCliente" type="{http://model.alertas.crm.bancorio.com/}SubRequestCliente" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSubRequestCliente", propOrder = {
    "subRequestCliente"
})
public class ArrayOfSubRequestCliente {

    @XmlElement(name = "SubRequestCliente", nillable = true)
    protected List<SubRequestCliente> subRequestCliente;

    /**
     * Gets the value of the subRequestCliente property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subRequestCliente property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubRequestCliente().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SubRequestCliente }
     * 
     * 
     */
    public List<SubRequestCliente> getSubRequestCliente() {
        if (subRequestCliente == null) {
            subRequestCliente = new ArrayList<SubRequestCliente>();
        }
        return this.subRequestCliente;
    }

}
