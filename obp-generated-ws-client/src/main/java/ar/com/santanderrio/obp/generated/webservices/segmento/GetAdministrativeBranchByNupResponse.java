
package ar.com.santanderrio.obp.generated.webservices.segmento;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetAdministrativeBranchByNupResult" type="{http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData}GetAdministrativeBranchByNupResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getAdministrativeBranchByNupResult"
})
@XmlRootElement(name = "GetAdministrativeBranchByNupResponse")
public class GetAdministrativeBranchByNupResponse {

    @XmlElementRef(name = "GetAdministrativeBranchByNupResult", namespace = "BSR.CRMBE.API.Services", type = JAXBElement.class)
    protected JAXBElement<GetAdministrativeBranchByNupResponse2> getAdministrativeBranchByNupResult;

    /**
     * Gets the value of the getAdministrativeBranchByNupResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link GetAdministrativeBranchByNupResponse2 }{@code >}
     *     
     */
    public JAXBElement<GetAdministrativeBranchByNupResponse2> getGetAdministrativeBranchByNupResult() {
        return getAdministrativeBranchByNupResult;
    }

    /**
     * Sets the value of the getAdministrativeBranchByNupResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link GetAdministrativeBranchByNupResponse2 }{@code >}
     *     
     */
    public void setGetAdministrativeBranchByNupResult(JAXBElement<GetAdministrativeBranchByNupResponse2> value) {
        this.getAdministrativeBranchByNupResult = value;
    }

}
