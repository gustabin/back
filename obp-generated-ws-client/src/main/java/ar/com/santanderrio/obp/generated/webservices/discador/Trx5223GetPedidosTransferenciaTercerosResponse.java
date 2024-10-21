
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx5223GetPedidosTransferenciaTercerosResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx5223GetPedidosTransferenciaTercerosResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}ResponseBase">
 *       &lt;sequence>
 *         &lt;element name="PedidosTranseferencias" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5223}ArrayOfTrx5223GetPedidosTransferenciaTercerosRepetitionItem" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx5223GetPedidosTransferenciaTercerosResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5223", propOrder = {
    "pedidosTranseferencias"
})
public class Trx5223GetPedidosTransferenciaTercerosResponse
    extends ResponseBase
{

    @XmlElementRef(name = "PedidosTranseferencias", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5223", type = JAXBElement.class)
    protected JAXBElement<ArrayOfTrx5223GetPedidosTransferenciaTercerosRepetitionItem> pedidosTranseferencias;

    /**
     * Gets the value of the pedidosTranseferencias property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfTrx5223GetPedidosTransferenciaTercerosRepetitionItem }{@code >}
     *     
     */
    public JAXBElement<ArrayOfTrx5223GetPedidosTransferenciaTercerosRepetitionItem> getPedidosTranseferencias() {
        return pedidosTranseferencias;
    }

    /**
     * Sets the value of the pedidosTranseferencias property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfTrx5223GetPedidosTransferenciaTercerosRepetitionItem }{@code >}
     *     
     */
    public void setPedidosTranseferencias(JAXBElement<ArrayOfTrx5223GetPedidosTransferenciaTercerosRepetitionItem> value) {
        this.pedidosTranseferencias = value;
    }

}
