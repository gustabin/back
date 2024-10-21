/**
 * 
 */
package ar.com.santanderrio.obp.generated.webservices.mya.dominio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author sergio.e.goldentair
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class MyaDiasAvisoPrevio {
    @XmlAttribute(name = "id")
    private String dapId;
    @XmlElement(name = "CodigoDAP")
    private String codigoDAP;
    @XmlElement(name = "DescripDAP")
    private String descripcionDAP;

    /**
     * @return the dapId
     */
    public String getDapId() {
        return dapId;
    }

    /**
     * @param dapId
     *            the dapId to set
     */
    public void setDapId(String dapId) {
        this.dapId = dapId;
    }

    /**
     * @return the codigoDAP
     */
    public String getCodigoDAP() {
        return codigoDAP;
    }

    /**
     * @param codigoDAP
     *            the codigoDAP to set
     */
    public void setCodigoDAP(String codigoDAP) {
        this.codigoDAP = codigoDAP;
    }

    /**
     * @return the descripcionDAP
     */
    public String getDescripcionDAP() {
        return descripcionDAP;
    }

    /**
     * @param descripcionDAP
     *            the descripcionDAP to set
     */
    public void setDescripcionDAP(String descripcionDAP) {
        this.descripcionDAP = descripcionDAP;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(dapId);
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

        MyaDiasAvisoPrevio other = (MyaDiasAvisoPrevio) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(dapId, other.getDapId());
        return eb.isEquals();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(dapId).append(codigoDAP).append(descripcionDAP).toString();
    }

}
