
package ar.com.santanderrio.obp.generated.webservices.productos;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfGestionCliente complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfGestionCliente">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GestionCliente" type="{http://webService.core.ges.rio.com}GestionCliente" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfGestionCliente", namespace = "http://webService.core.ges.rio.com", propOrder = {
    "gestionCliente"
})
public class ArrayOfGestionCliente {

    @XmlElement(name = "GestionCliente", nillable = true)
    protected List<GestionCliente> gestionCliente;

    /**
     * Gets the value of the gestionCliente property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the gestionCliente property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGestionCliente().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GestionCliente }
     * 
     * 
     */
    public List<GestionCliente> getGestionCliente() {
        if (gestionCliente == null) {
            gestionCliente = new ArrayList<GestionCliente>();
        }
        return this.gestionCliente;
    }

}
