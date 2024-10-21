/**
 * 
 */
package ar.com.santanderrio.obp.generated.webservices.mya.dominio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author sergio.e.goldentair
 *
 */
@XmlRootElement(name = "Mensaje")
@XmlAccessorType(XmlAccessType.FIELD)
public class MyaMensajeError {
    @XmlElement(name = "CodMensaje")
    private String codMensaje;
    @XmlElement(name = "TextoMensaje")
    private String textoMensaje;

    /**
     * @return the codMensaje
     */
    public String getCodMensaje() {
        return codMensaje;
    }

    /**
     * @param codMensaje
     *            the codMensaje to set
     */
    public void setCodMensaje(String codMensaje) {
        this.codMensaje = codMensaje;
    }

    /**
     * @return the textoMensaje
     */
    public String getTextoMensaje() {
        return textoMensaje;
    }

    /**
     * @param textoMensaje
     *            the textoMensaje to set
     */
    public void setTextoMensaje(String textoMensaje) {
        this.textoMensaje = textoMensaje;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(codMensaje);
        hcb.append(textoMensaje);
        return hcb.toHashCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        MyaMensajeError other = (MyaMensajeError) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(codMensaje, other.getCodMensaje());
        eb.append(textoMensaje, other.getTextoMensaje());
        return eb.isEquals();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(codMensaje).append(textoMensaje).toString();
    }

}
