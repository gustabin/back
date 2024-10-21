/**
 * 
 */
package ar.com.santanderrio.obp.generated.webservices.mya.dominio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author sergio.e.goldentair
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class MyaAlias {
    @XmlElement(name = "Nombre")
    private String aliasNombre;
    @XmlElement(name = "DestinoDescripcion")
    private String aliasDestinoDesc;
    @XmlElement(name = "DestinoEmpresaCel")
    private MyaDestinoEmpCelEnum aliasDestinoEmp;
    @XmlElement(name = "Titular")
    private String aliasTitular;

    /**
     * @return the aliasNombre
     */
    public String getAliasNombre() {
        return aliasNombre;
    }

    /**
     * @param aliasNombre
     *            the aliasNombre to set
     */
    public void setAliasNombre(String aliasNombre) {
        this.aliasNombre = aliasNombre;
    }

    /**
     * @return the aliasDestinoDesc
     */
    public String getAliasDestinoDesc() {
        return aliasDestinoDesc;
    }

    /**
     * @param aliasDestinoDesc
     *            the aliasDestinoDesc to set
     */
    public void setAliasDestinoDesc(String aliasDestinoDesc) {
        this.aliasDestinoDesc = aliasDestinoDesc;
    }

    /**
     * @return the aliasDestinoEmp
     */
    public MyaDestinoEmpCelEnum getAliasDestinoEmp() {
        return aliasDestinoEmp;
    }

    /**
     * @param aliasDestinoEmp
     *            the aliasDestinoEmp to set
     */
    public void setAliasDestinoEmp(MyaDestinoEmpCelEnum aliasDestinoEmp) {
        this.aliasDestinoEmp = aliasDestinoEmp;
    }

    /**
     * @return the aliasTitular
     */
    public String getAliasTitular() {
        return aliasTitular;
    }

    /**
     * @param aliasTitular
     *            the aliasTitular to set
     */
    public void setAliasTitular(String aliasTitular) {
        this.aliasTitular = aliasTitular;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(aliasNombre);
        hcb.append(aliasDestinoEmp);
        hcb.append(aliasDestinoDesc);
        hcb.append(aliasTitular);
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

        MyaAlias other = (MyaAlias) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(aliasNombre, other.getAliasNombre());
        eb.append(aliasDestinoEmp, other.getAliasDestinoEmp());
        eb.append(aliasDestinoDesc, other.getAliasDestinoDesc());
        eb.append(aliasTitular, other.getAliasTitular());
        return eb.isEquals();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(aliasNombre).append(aliasDestinoEmp).append(aliasDestinoDesc)
                .append(aliasTitular).toString();
    }

}
