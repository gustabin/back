
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfTrx5223GetPedidosTransferenciaTercerosRepetitionItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfTrx5223GetPedidosTransferenciaTercerosRepetitionItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Trx5223GetPedidosTransferenciaTercerosRepetitionItem" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5223}Trx5223GetPedidosTransferenciaTercerosRepetitionItem" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfTrx5223GetPedidosTransferenciaTercerosRepetitionItem", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5223", propOrder = {
    "trx5223GetPedidosTransferenciaTercerosRepetitionItem"
})
public class ArrayOfTrx5223GetPedidosTransferenciaTercerosRepetitionItem {

    @XmlElement(name = "Trx5223GetPedidosTransferenciaTercerosRepetitionItem", nillable = true)
    protected List<Trx5223GetPedidosTransferenciaTercerosRepetitionItem> trx5223GetPedidosTransferenciaTercerosRepetitionItem;

    /**
     * Gets the value of the trx5223GetPedidosTransferenciaTercerosRepetitionItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trx5223GetPedidosTransferenciaTercerosRepetitionItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrx5223GetPedidosTransferenciaTercerosRepetitionItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Trx5223GetPedidosTransferenciaTercerosRepetitionItem }
     * 
     * 
     */
    public List<Trx5223GetPedidosTransferenciaTercerosRepetitionItem> getTrx5223GetPedidosTransferenciaTercerosRepetitionItem() {
        if (trx5223GetPedidosTransferenciaTercerosRepetitionItem == null) {
            trx5223GetPedidosTransferenciaTercerosRepetitionItem = new ArrayList<Trx5223GetPedidosTransferenciaTercerosRepetitionItem>();
        }
        return this.trx5223GetPedidosTransferenciaTercerosRepetitionItem;
    }

}
