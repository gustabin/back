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
public class MyaEmpresaCel {
    @XmlAttribute(name = "id")
    private String empresaCelId;
    @XmlElement(name = "CodigoEmpCel")
    private String codigoEmpresaCel;
    @XmlElement(name = "DescripEmpCel")
    private String descripcionEmpresaCel;

    /**
     * @return the empresaCelId
     */
    public String getEmpresaCelId() {
        return empresaCelId;
    }

    /**
     * @param empresaCelId
     *            the empresaCelId to set
     */
    public void setEmpresaCelId(String empresaCelId) {
        this.empresaCelId = empresaCelId;
    }

    /**
     * @return the codigoEmpresaCel
     */
    public String getCodigoEmpresaCel() {
        return codigoEmpresaCel;
    }

    /**
     * @param codigoEmpresaCel
     *            the codigoEmpresaCel to set
     */
    public void setCodigoEmpresaCel(String codigoEmpresaCel) {
        this.codigoEmpresaCel = codigoEmpresaCel;
    }

    /**
     * @return the descripcionEmpresaCel
     */
    public String getDescripcionEmpresaCel() {
        return descripcionEmpresaCel;
    }

    /**
     * @param descripcionEmpresaCel
     *            the descripcionEmpresaCel to set
     */
    public void setDescripcionEmpresaCel(String descripcionEmpresaCel) {
        this.descripcionEmpresaCel = descripcionEmpresaCel;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(empresaCelId);
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

        MyaEmpresaCel other = (MyaEmpresaCel) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(empresaCelId, other.getEmpresaCelId());
        return eb.isEquals();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(empresaCelId).append(codigoEmpresaCel).append(descripcionEmpresaCel)
                .toString();
    }

}
