
package ar.com.santanderrio.obp.generated.webservices.alias;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for responseAlias complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="responseAlias">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservices.api.transferencias.banelco.com/}response">
 *       &lt;sequence>
 *         &lt;element name="alias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cbu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="error" type="{http://webservices.api.transferencias.banelco.com/}error" minOccurs="0"/>
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reasigna" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "responseAlias", propOrder = {
    "alias",
    "cbu",
    "error",
    "estado",
    "reasigna"
})
public class ResponseAlias
    extends Response
{

    protected String alias;
    protected String cbu;
    protected Error error;
    protected String estado;
    protected String reasigna;

    /**
     * Gets the value of the alias property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets the value of the alias property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlias(String value) {
        this.alias = value;
    }

    /**
     * Gets the value of the cbu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCbu() {
        return cbu;
    }

    /**
     * Sets the value of the cbu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCbu(String value) {
        this.cbu = value;
    }

    /**
     * Gets the value of the error property.
     * 
     * @return
     *     possible object is
     *     {@link Error }
     *     
     */
    public Error getError() {
        return error;
    }

    /**
     * Sets the value of the error property.
     * 
     * @param value
     *     allowed object is
     *     {@link Error }
     *     
     */
    public void setError(Error value) {
        this.error = value;
    }

    /**
     * Gets the value of the estado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Sets the value of the estado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstado(String value) {
        this.estado = value;
    }

    /**
     * Gets the value of the reasigna property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReasigna() {
        return reasigna;
    }

    /**
     * Sets the value of the reasigna property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReasigna(String value) {
        this.reasigna = value;
    }

    @Override
    public String toString() {
    	StringBuilder builder = new StringBuilder();
    	builder.append("ResponseAlias [alias=");
    	builder.append(alias);
    	builder.append(", cbu=");
    	builder.append(cbu);
    	builder.append(", error=");    	
    	if (error == null) {
    		builder.append("No fue obtenido del XML");
    	} else {
    		builder.append(error.getCodigo());
    		builder.append(" " + error.getMensaje());
    	}
    	builder.append(", estado=");
    	builder.append(estado);
    	builder.append(", reasigna=");
    	builder.append(reasigna);
    	builder.append("]");
    	
        return builder.toString();
    }
    

}
