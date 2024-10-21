
package ar.com.santanderrio.obp.generated.webservices.discador;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfTrx5024CnsTransfObanResponseRepetitionItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfTrx5024CnsTransfObanResponseRepetitionItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Trx5024CnsTransfObanResponseRepetitionItem" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5024}Trx5024CnsTransfObanResponseRepetitionItem" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfTrx5024CnsTransfObanResponseRepetitionItem", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5024", propOrder = {
    "trx5024CnsTransfObanResponseRepetitionItem"
})
public class ArrayOfTrx5024CnsTransfObanResponseRepetitionItem {

    @XmlElement(name = "Trx5024CnsTransfObanResponseRepetitionItem", nillable = true)
    protected List<Trx5024CnsTransfObanResponseRepetitionItem> trx5024CnsTransfObanResponseRepetitionItem;

    /**
     * Gets the value of the trx5024CnsTransfObanResponseRepetitionItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trx5024CnsTransfObanResponseRepetitionItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrx5024CnsTransfObanResponseRepetitionItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Trx5024CnsTransfObanResponseRepetitionItem }
     * 
     * 
     */
    public List<Trx5024CnsTransfObanResponseRepetitionItem> getTrx5024CnsTransfObanResponseRepetitionItem() {
        if (trx5024CnsTransfObanResponseRepetitionItem == null) {
            trx5024CnsTransfObanResponseRepetitionItem = new ArrayList<Trx5024CnsTransfObanResponseRepetitionItem>();
        }
        return this.trx5024CnsTransfObanResponseRepetitionItem;
    }

}
