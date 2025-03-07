
package ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfRegimenGanancia complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfRegimenGanancia">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RegimenGanancia" type="{Domain}RegimenGanancia" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfRegimenGanancia", propOrder = {
    "regimenGanancia"
})
public class ArrayOfRegimenGanancia {

    @XmlElement(name = "RegimenGanancia", nillable = true)
    protected List<RegimenGanancia> regimenGanancia;

    /**
     * Gets the value of the regimenGanancia property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the regimenGanancia property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRegimenGanancia().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RegimenGanancia }
     * 
     * 
     */
    public List<RegimenGanancia> getRegimenGanancia() {
        if (regimenGanancia == null) {
            regimenGanancia = new ArrayList<RegimenGanancia>();
        }
        return this.regimenGanancia;
    }

}
