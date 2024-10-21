
package ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfPaisesDobleConv complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfPaisesDobleConv">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PaisesDobleConv" type="{Domain}PaisesDobleConv" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfPaisesDobleConv", propOrder = {
    "paisesDobleConv"
})
public class ArrayOfPaisesDobleConv {

    @XmlElement(name = "PaisesDobleConv", nillable = true)
    protected List<PaisesDobleConv> paisesDobleConv;

    /**
     * Gets the value of the paisesDobleConv property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the paisesDobleConv property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPaisesDobleConv().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PaisesDobleConv }
     * 
     * 
     */
    public List<PaisesDobleConv> getPaisesDobleConv() {
        if (paisesDobleConv == null) {
            paisesDobleConv = new ArrayList<PaisesDobleConv>();
        }
        return this.paisesDobleConv;
    }

}
