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
public class MyaFrecuencia {
    @XmlAttribute(name = "id")
    private String frecuenciaId;
    @XmlElement(name = "CodigoFrecuencia")
    private String codigoFrecuencia;
    @XmlElement(name = "DescripFrecuencia")
    private String descripcionFrecuencia;

    /**
     * @return the frecuenciaId
     */
    public String getFrecuenciaId() {
        return frecuenciaId;
    }

    /**
     * @param frecuenciaId
     *            the frecuenciaId to set
     */
    public void setFrecuenciaId(String frecuenciaId) {
        this.frecuenciaId = frecuenciaId;
    }

    /**
     * @return the codigoFrecuencia
     */
    public String getCodigoFrecuencia() {
        return codigoFrecuencia;
    }

    /**
     * @param codigoFrecuencia
     *            the codigoFrecuencia to set
     */
    public void setCodigoFrecuencia(String codigoFrecuencia) {
        this.codigoFrecuencia = codigoFrecuencia;
    }

    /**
     * @return the descripcionFrecuencia
     */
    public String getDescripcionFrecuencia() {
        return descripcionFrecuencia;
    }

    /**
     * @param descripcionFrecuencia
     *            the descripcionFrecuencia to set
     */
    public void setDescripcionFrecuencia(String descripcionFrecuencia) {
        this.descripcionFrecuencia = descripcionFrecuencia;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(frecuenciaId);
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

        MyaFrecuencia other = (MyaFrecuencia) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(frecuenciaId, other.getFrecuenciaId());
        return eb.isEquals();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(frecuenciaId).append(codigoFrecuencia).append(descripcionFrecuencia)
                .toString();
    }

}
