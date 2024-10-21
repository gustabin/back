
package ar.com.santanderrio.obp.generated.webservices.rsa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="queryReturn" type="{http://ws.csd.rsa.com}QueryResponse"/>
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
    "queryReturn"
})
@XmlRootElement(name = "queryResponse")
public class QueryResponse {

    @XmlElement(required = true)
    protected QueryResponse2 queryReturn;

    /**
     * Gets the value of the queryReturn property.
     * 
     * @return
     *     possible object is
     *     {@link QueryResponse2 }
     *     
     */
    public QueryResponse2 getQueryReturn() {
        return queryReturn;
    }

    /**
     * Sets the value of the queryReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryResponse2 }
     *     
     */
    public void setQueryReturn(QueryResponse2 value) {
        this.queryReturn = value;
    }

}
