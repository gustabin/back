
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx258ConsultaValoresDebitadosResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx258ConsultaValoresDebitadosResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}TrxHeaderResponseBase">
 *       &lt;sequence>
 *         &lt;element name="Cantidad_Valores_Debitados" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Total_Valores_debitados_ARS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Total_Valores_debitados_USS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ValoresAcreditar" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx258}ArrayOfTrx258ConsultaValoresDebitadosIterationResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx258ConsultaValoresDebitadosResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx258", propOrder = {
    "cantidadValoresDebitados",
    "totalValoresDebitadosARS",
    "totalValoresDebitadosUSS",
    "valoresAcreditar"
})
public class Trx258ConsultaValoresDebitadosResponse
    extends TrxHeaderResponseBase
{

    @XmlElementRef(name = "Cantidad_Valores_Debitados", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx258", type = JAXBElement.class)
    protected JAXBElement<String> cantidadValoresDebitados;
    @XmlElementRef(name = "Total_Valores_debitados_ARS", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx258", type = JAXBElement.class)
    protected JAXBElement<String> totalValoresDebitadosARS;
    @XmlElementRef(name = "Total_Valores_debitados_USS", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx258", type = JAXBElement.class)
    protected JAXBElement<String> totalValoresDebitadosUSS;
    @XmlElementRef(name = "ValoresAcreditar", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx258", type = JAXBElement.class)
    protected JAXBElement<ArrayOfTrx258ConsultaValoresDebitadosIterationResponse> valoresAcreditar;

    /**
     * Gets the value of the cantidadValoresDebitados property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCantidadValoresDebitados() {
        return cantidadValoresDebitados;
    }

    /**
     * Sets the value of the cantidadValoresDebitados property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCantidadValoresDebitados(JAXBElement<String> value) {
        this.cantidadValoresDebitados = value;
    }

    /**
     * Gets the value of the totalValoresDebitadosARS property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTotalValoresDebitadosARS() {
        return totalValoresDebitadosARS;
    }

    /**
     * Sets the value of the totalValoresDebitadosARS property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTotalValoresDebitadosARS(JAXBElement<String> value) {
        this.totalValoresDebitadosARS = value;
    }

    /**
     * Gets the value of the totalValoresDebitadosUSS property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTotalValoresDebitadosUSS() {
        return totalValoresDebitadosUSS;
    }

    /**
     * Sets the value of the totalValoresDebitadosUSS property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTotalValoresDebitadosUSS(JAXBElement<String> value) {
        this.totalValoresDebitadosUSS = value;
    }

    /**
     * Gets the value of the valoresAcreditar property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfTrx258ConsultaValoresDebitadosIterationResponse }{@code >}
     *     
     */
    public JAXBElement<ArrayOfTrx258ConsultaValoresDebitadosIterationResponse> getValoresAcreditar() {
        return valoresAcreditar;
    }

    /**
     * Sets the value of the valoresAcreditar property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfTrx258ConsultaValoresDebitadosIterationResponse }{@code >}
     *     
     */
    public void setValoresAcreditar(JAXBElement<ArrayOfTrx258ConsultaValoresDebitadosIterationResponse> value) {
        this.valoresAcreditar = value;
    }

}
