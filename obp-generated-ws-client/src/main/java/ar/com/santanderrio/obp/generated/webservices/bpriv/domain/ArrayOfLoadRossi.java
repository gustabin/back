
package ar.com.santanderrio.obp.generated.webservices.bpriv.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfLoadRossi complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfLoadRossi">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LoadRossi" type="{http://schemas.datacontract.org/2004/07/BancaPrivada.Domain}LoadRossi" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfLoadRossi", propOrder = {
    "loadRossi"
})
public class ArrayOfLoadRossi {

    @XmlElement(name = "LoadRossi", nillable = true)
    protected List<LoadRossi> loadRossi;

    /**
     * Gets the value of the loadRossi property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the loadRossi property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLoadRossi().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LoadRossi }
     * 
     * 
     */
    public List<LoadRossi> getLoadRossi() {
        if (loadRossi == null) {
            loadRossi = new ArrayList<LoadRossi>();
        }
        return this.loadRossi;
    }

}
