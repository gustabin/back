
package ar.com.santanderrio.obp.generated.webservices.segmento;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfInfoMiEquipo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfInfoMiEquipo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InfoMiEquipo" type="{http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData}InfoMiEquipo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfInfoMiEquipo", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", propOrder = {
    "infoMiEquipo"
})
public class ArrayOfInfoMiEquipo {

    @XmlElement(name = "InfoMiEquipo", nillable = true)
    protected List<InfoMiEquipo> infoMiEquipo;

    /**
     * Gets the value of the infoMiEquipo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the infoMiEquipo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInfoMiEquipo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InfoMiEquipo }
     * 
     * 
     */
    public List<InfoMiEquipo> getInfoMiEquipo() {
        if (infoMiEquipo == null) {
            infoMiEquipo = new ArrayList<InfoMiEquipo>();
        }
        return this.infoMiEquipo;
    }

}
