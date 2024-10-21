
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx201IdentificacionDeLlamadaProductoResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx201IdentificacionDeLlamadaProductoResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response}IdentificacionDeLlamadaProductoResponse">
 *       &lt;sequence>
 *         &lt;element name="GrupoAfinidad201" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx201IdentificacionDeLlamadaProductoResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx201", propOrder = {
    "grupoAfinidad201"
})
public class Trx201IdentificacionDeLlamadaProductoResponse
    extends IdentificacionDeLlamadaProductoResponse
{

    @XmlElementRef(name = "GrupoAfinidad201", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx201", type = JAXBElement.class)
    protected JAXBElement<String> grupoAfinidad201;

    /**
     * Gets the value of the grupoAfinidad201 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGrupoAfinidad201() {
        return grupoAfinidad201;
    }

    /**
     * Sets the value of the grupoAfinidad201 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGrupoAfinidad201(JAXBElement<String> value) {
        this.grupoAfinidad201 = value;
    }

}
