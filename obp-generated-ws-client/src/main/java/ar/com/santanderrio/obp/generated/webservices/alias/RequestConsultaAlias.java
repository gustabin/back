
package ar.com.santanderrio.obp.generated.webservices.alias;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * <p>Java class for requestConsultaAlias complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="requestConsultaAlias">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservices.api.transferencias.banelco.com/}request">
 *       &lt;sequence>
 *         &lt;element name="alias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="terminalDTO" type="{http://webservices.api.transferencias.banelco.com/}terminalDTO" minOccurs="0"/>
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
@XmlType(name = "requestConsultaAlias", propOrder = {
    "alias",
    "terminalDTO",
    "usuarioDTO"
})
public class RequestConsultaAlias
    extends Request
{

    protected String alias;
    protected TerminalDTO terminalDTO;
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
        return new ToStringBuilder(this).append("alias", alias).append("terminalDTO", terminalDTO)
        		.append("usuarioDTO", usuarioDTO).toString();
    }

}
