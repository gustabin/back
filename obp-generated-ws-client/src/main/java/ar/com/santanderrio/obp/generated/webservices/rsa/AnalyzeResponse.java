
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
 *         &lt;element name="analyzeReturn" type="{http://ws.csd.rsa.com}AnalyzeResponse"/>
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
    "analyzeReturn"
})
@XmlRootElement(name = "analyzeResponse")
public class AnalyzeResponse {

    @XmlElement(required = true)
    protected AnalyzeResponse2 analyzeReturn;

    /**
     * Gets the value of the analyzeReturn property.
     * 
     * @return
     *     possible object is
     *     {@link AnalyzeResponse2 }
     *     
     */
    public AnalyzeResponse2 getAnalyzeReturn() {
        return analyzeReturn;
    }

    /**
     * Sets the value of the analyzeReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnalyzeResponse2 }
     *     
     */
    public void setAnalyzeReturn(AnalyzeResponse2 value) {
        this.analyzeReturn = value;
    }

}
