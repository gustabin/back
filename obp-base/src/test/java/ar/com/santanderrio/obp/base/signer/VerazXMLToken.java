/**
 * 
 */
package ar.com.santanderrio.obp.base.signer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ar.com.santanderrio.obp.base.signer.token.XMLToken;

/**
 * The Class VerazXMLToken.
 *
 * @author sergio.e.goldentair
 */
@XmlRootElement(name = "verazToken")
@XmlAccessorType(XmlAccessType.FIELD)
public class VerazXMLToken extends XMLToken {

    /** The dato. */
    @XmlElement
    private String dato;

    /**
     * Gets the dato.
     *
     * @return the dato
     */
    public String getDato() {
        return dato;
    }

    /**
     * Sets the dato.
     *
     * @param dato
     *            the dato to set
     */
    public void setDato(String dato) {
        this.dato = dato;
    }
}
