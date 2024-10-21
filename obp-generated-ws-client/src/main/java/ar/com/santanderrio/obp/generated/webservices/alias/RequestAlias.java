
package ar.com.santanderrio.obp.generated.webservices.alias;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * <p>Java class for requestAlias complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="requestAlias">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservices.api.transferencias.banelco.com/}request">
 *       &lt;sequence>
 *         &lt;element name="alias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cbu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cuit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reasigna" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="terminalDTO" type="{http://webservices.api.transferencias.banelco.com/}terminalDTO" minOccurs="0"/>
 *         &lt;element name="tipoCta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoPersona" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usuarioDTO" type="{http://webservices.api.transferencias.banelco.com/}usuarioDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "requestAlias", propOrder = {
    "alias",
    "cbu",
    "cuit",
    "reasigna",
    "terminalDTO",
    "tipoCta",
    "tipoPersona",
    "usuarioDTO"
})
public class RequestAlias
    extends Request
{

    protected String alias;
    protected String cbu;
    protected String cuit;
    protected String reasigna;
    protected TerminalDTO terminalDTO;
    protected String tipoCta;
    protected String tipoPersona;
    protected UsuarioDTO usuarioDTO;

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
     * Gets the value of the cuit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuit() {
        return cuit;
    }

    /**
     * Sets the value of the cuit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuit(String value) {
        this.cuit = value;
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

    /**
     * Gets the value of the terminalDTO property.
     * 
     * @return
     *     possible object is
     *     {@link TerminalDTO }
     *     
     */
    public TerminalDTO getTerminalDTO() {
        return terminalDTO;
    }

    /**
     * Sets the value of the terminalDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link TerminalDTO }
     *     
     */
    public void setTerminalDTO(TerminalDTO value) {
        this.terminalDTO = value;
    }

    /**
     * Gets the value of the tipoCta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoCta() {
        return tipoCta;
    }

    /**
     * Sets the value of the tipoCta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoCta(String value) {
        this.tipoCta = value;
    }

    /**
     * Gets the value of the tipoPersona property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoPersona() {
        return tipoPersona;
    }

    /**
     * Sets the value of the tipoPersona property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoPersona(String value) {
        this.tipoPersona = value;
    }

    /**
     * Gets the value of the usuarioDTO property.
     * 
     * @return
     *     possible object is
     *     {@link UsuarioDTO }
     *     
     */
    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }

    /**
     * Sets the value of the usuarioDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link UsuarioDTO }
     *     
     */
    public void setUsuarioDTO(UsuarioDTO value) {
        this.usuarioDTO = value;
    }

    @Override
    public String toString() {
    	ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
        return new ToStringBuilder(this).append("alias", alias).append("cbu", cbu)
        		.append("cuit", cuit).append("reasigna", reasigna).append("terminalDTO", terminalDTO)
        		.append("tipoCta", tipoCta).append("tipoPersona",tipoPersona).append("usuarioDTO", usuarioDTO).toString();
    }
}
