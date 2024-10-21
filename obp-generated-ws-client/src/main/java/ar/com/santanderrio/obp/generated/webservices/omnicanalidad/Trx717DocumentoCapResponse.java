
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx717DocumentoCapResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx717DocumentoCapResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}ResponseBase">
 *       &lt;sequence>
 *         &lt;element name="DocumentosCap" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response}ArrayOfStDocumentoCap" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx717DocumentoCapResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx717", propOrder = {
    "documentosCap"
})
public class Trx717DocumentoCapResponse
    extends ResponseBase
{

    @XmlElementRef(name = "DocumentosCap", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx717", type = JAXBElement.class)
    protected JAXBElement<ArrayOfStDocumentoCap> documentosCap;

    /**
     * Gets the value of the documentosCap property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfStDocumentoCap }{@code >}
     *     
     */
    public JAXBElement<ArrayOfStDocumentoCap> getDocumentosCap() {
        return documentosCap;
    }

    /**
     * Sets the value of the documentosCap property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfStDocumentoCap }{@code >}
     *     
     */
    public void setDocumentosCap(JAXBElement<ArrayOfStDocumentoCap> value) {
        this.documentosCap = value;
    }

}
