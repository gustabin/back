/**
 * 
 */
package ar.com.santanderrio.obp.generated.webservices.mya.dominio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author sergio.e.goldentair
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class MyaFrecPermitida {
    @XmlAttribute(name = "id")
    private String idFrecPermitida;
    @XmlValue
    private String frecuenciaPermitida;

    /**
     * @return the idFrecPermitida
     */
    public String getIdFrecPermitida() {
        return idFrecPermitida;
    }

    /**
     * @param idFrecPermitida
     *            the idFrecPermitida to set
     */
    public void setIdFrecPermitida(String idFrecPermitida) {
        this.idFrecPermitida = idFrecPermitida;
    }

    /**
     * @return the frecuenciaPermitida
     */
    public String getFrecuenciaPermitida() {
        return frecuenciaPermitida;
    }

    /**
     * @param frecuenciaPermitida
     *            the frecuenciaPermitida to set
     */
    public void setFrecuenciaPermitida(String frecuenciaPermitida) {
        this.frecuenciaPermitida = frecuenciaPermitida;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(idFrecPermitida);
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

        MyaFrecPermitida other = (MyaFrecPermitida) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(idFrecPermitida, other.getIdFrecPermitida());
        return eb.isEquals();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(idFrecPermitida).toString();
    }
}
