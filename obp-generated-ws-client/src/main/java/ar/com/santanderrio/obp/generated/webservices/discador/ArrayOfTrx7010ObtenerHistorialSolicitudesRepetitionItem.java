
package ar.com.santanderrio.obp.generated.webservices.discador;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfTrx7010ObtenerHistorialSolicitudesRepetitionItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfTrx7010ObtenerHistorialSolicitudesRepetitionItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Trx7010ObtenerHistorialSolicitudesRepetitionItem" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7010}Trx7010ObtenerHistorialSolicitudesRepetitionItem" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfTrx7010ObtenerHistorialSolicitudesRepetitionItem", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7010", propOrder = {
    "trx7010ObtenerHistorialSolicitudesRepetitionItem"
})
public class ArrayOfTrx7010ObtenerHistorialSolicitudesRepetitionItem {

    @XmlElement(name = "Trx7010ObtenerHistorialSolicitudesRepetitionItem", nillable = true)
    protected List<Trx7010ObtenerHistorialSolicitudesRepetitionItem> trx7010ObtenerHistorialSolicitudesRepetitionItem;

    /**
     * Gets the value of the trx7010ObtenerHistorialSolicitudesRepetitionItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trx7010ObtenerHistorialSolicitudesRepetitionItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrx7010ObtenerHistorialSolicitudesRepetitionItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Trx7010ObtenerHistorialSolicitudesRepetitionItem }
     * 
     * 
     */
    public List<Trx7010ObtenerHistorialSolicitudesRepetitionItem> getTrx7010ObtenerHistorialSolicitudesRepetitionItem() {
        if (trx7010ObtenerHistorialSolicitudesRepetitionItem == null) {
            trx7010ObtenerHistorialSolicitudesRepetitionItem = new ArrayList<Trx7010ObtenerHistorialSolicitudesRepetitionItem>();
        }
        return this.trx7010ObtenerHistorialSolicitudesRepetitionItem;
    }

}
