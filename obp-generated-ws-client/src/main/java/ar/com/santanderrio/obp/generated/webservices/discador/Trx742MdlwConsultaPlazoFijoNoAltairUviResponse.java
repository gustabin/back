
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.response.ArrayOfResultCertificadoUvi;


/**
 * <p>Java class for Trx742MdlwConsultaPlazoFijoNoAltairUviResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx742MdlwConsultaPlazoFijoNoAltairUviResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}TrxHeaderResponseBase">
 *       &lt;sequence>
 *         &lt;element name="CantPlazoFijo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Certificado" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response}ArrayOfResultCertificadoUvi" minOccurs="0"/>
 *         &lt;element name="IndicadorRellamada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx742MdlwConsultaPlazoFijoNoAltairUviResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx742", propOrder = {
    "cantPlazoFijo",
    "certificado",
    "indicadorRellamada"
})
public class Trx742MdlwConsultaPlazoFijoNoAltairUviResponse
    extends TrxHeaderResponseBase
{

    @XmlElementRef(name = "CantPlazoFijo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx742", type = JAXBElement.class)
    protected JAXBElement<String> cantPlazoFijo;
    @XmlElementRef(name = "Certificado", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx742", type = JAXBElement.class)
    protected JAXBElement<ArrayOfResultCertificadoUvi> certificado;
    @XmlElementRef(name = "IndicadorRellamada", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx742", type = JAXBElement.class)
    protected JAXBElement<String> indicadorRellamada;

    /**
     * Gets the value of the cantPlazoFijo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCantPlazoFijo() {
        return cantPlazoFijo;
    }

    /**
     * Sets the value of the cantPlazoFijo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCantPlazoFijo(JAXBElement<String> value) {
        this.cantPlazoFijo = value;
    }

    /**
     * Gets the value of the certificado property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfResultCertificadoUvi }{@code >}
     *     
     */
    public JAXBElement<ArrayOfResultCertificadoUvi> getCertificado() {
        return certificado;
    }

    /**
     * Sets the value of the certificado property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfResultCertificadoUvi }{@code >}
     *     
     */
    public void setCertificado(JAXBElement<ArrayOfResultCertificadoUvi> value) {
        this.certificado = value;
    }

    /**
     * Gets the value of the indicadorRellamada property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndicadorRellamada() {
        return indicadorRellamada;
    }

    /**
     * Sets the value of the indicadorRellamada property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndicadorRellamada(JAXBElement<String> value) {
        this.indicadorRellamada = value;
    }

}
