
package ar.com.santanderrio.obp.generated.webservices.discador;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.response.Result;


/**
 * <p>Java class for Trx7001AddTokenResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx7001AddTokenResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}ResponseBase">
 *       &lt;sequence>
 *         &lt;element name="Result" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response}Result" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx7001AddTokenResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7001", propOrder = {
    "rest"
})
public class Trx7001AddTokenResponse
    extends ResponseBase
{

    @XmlElementRef(name = "Result", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7001", type = JAXBElement.class)
    protected List<JAXBElement<Result>> rest;

    /**
     * Gets the rest of the content model. 
     * 
     * <p>
     * You are getting this "catch-all" property because of the following reason: 
     * The field name "Result" is used by two different parts of a schema. See: 
     * line 0 of http://srvtran.ar.bsch:8100/ClickToCall.svc?xsd=xsd79
     * line 0 of http://srvtran.ar.bsch:8100/ClickToCall.svc?xsd=xsd2
     * <p>
     * To get rid of this property, apply a property customization to one 
     * of both of the following declarations to change their names: 
     * Gets the value of the rest property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rest property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRest().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link Result }{@code >}
     * 
     * 
     */
    public List<JAXBElement<Result>> getRest() {
        if (rest == null) {
            rest = new ArrayList<JAXBElement<Result>>();
        }
        return this.rest;
    }

}
