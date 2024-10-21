
package ar.com.santanderrio.obp.generated.webservices.bpriv.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfMovimiento3ros complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfMovimiento3ros">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Movimiento3ros" type="{http://schemas.datacontract.org/2004/07/BancaPrivada.Domain}Movimiento3ros" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfMovimiento3ros", propOrder = {
    "movimiento3Ros"
})
public class ArrayOfMovimiento3Ros {

    @XmlElement(name = "Movimiento3ros", nillable = true)
    protected List<Movimiento3Ros> movimiento3Ros;

    /**
     * Gets the value of the movimiento3Ros property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the movimiento3Ros property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMovimiento3Ros().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Movimiento3Ros }
     * 
     * 
     */
    public List<Movimiento3Ros> getMovimiento3Ros() {
        if (movimiento3Ros == null) {
            movimiento3Ros = new ArrayList<Movimiento3Ros>();
        }
        return this.movimiento3Ros;
    }

}
