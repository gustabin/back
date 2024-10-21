
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx002IdentificacionDelClienteProductResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx002IdentificacionDelClienteProductResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}TrxIdentificacionClienteProductResponse">
 *       &lt;sequence>
 *         &lt;element name="grupo_afinidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx002IdentificacionDelClienteProductResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx002", propOrder = {
    "grupoAfinidad"
})
public class Trx002IdentificacionDelClienteProductResponse
    extends TrxIdentificacionClienteProductResponse
{

    @XmlElementRef(name = "grupo_afinidad", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx002", type = JAXBElement.class)
    protected JAXBElement<String> grupoAfinidad;

    /**
     * Gets the value of the grupoAfinidad property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGrupoAfinidad() {
        return grupoAfinidad;
    }

    /**
     * Sets the value of the grupoAfinidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGrupoAfinidad(JAXBElement<String> value) {
        this.grupoAfinidad = value;
    }

}
