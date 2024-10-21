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
public class MyaCuenta {
    @XmlElement(name = "TipoCta")
    private String tipoCuenta;
    @XmlElement(name = "CodigoAplicacion")
    private String codigoAplicacion;
    @XmlElement(name = "CodigoPaquete")
    private String codigoPaquete;
    @XmlElement(name = "DepositoEfectivo")
    private String depositoEfectivo;

    /**
     * @return the tipoCuenta
     */
    public String getTipoCuenta() {
        return tipoCuenta;
    }

    /**
     * @param tipoCuenta
     *            the tipoCuenta to set
     */
    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    /**
     * @return the codigoAplicacion
     */
    public String getCodigoAplicacion() {
        return codigoAplicacion;
    }

    /**
     * @param codigoAplicacion
     *            the codigoAplicacion to set
     */
    public void setCodigoAplicacion(String codigoAplicacion) {
        this.codigoAplicacion = codigoAplicacion;
    }

    /**
     * @return the codigoPaquete
     */
    public String getCodigoPaquete() {
        return codigoPaquete;
    }

    /**
     * @param codigoPaquete
     *            the codigoPaquete to set
     */
    public void setCodigoPaquete(String codigoPaquete) {
        this.codigoPaquete = codigoPaquete;
    }

    /**
     * @return the depositoEfectivo
     */
    public String getDepositoEfectivo() {
        return depositoEfectivo;
    }

    /**
     * @param depositoEfectivo
     *            the depositoEfectivo to set
     */
    public void setDepositoEfectivo(String depositoEfectivo) {
        this.depositoEfectivo = depositoEfectivo;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(tipoCuenta);
        hcb.append(codigoAplicacion);
        hcb.append(codigoPaquete);
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
        MyaCuenta other = (MyaCuenta) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(tipoCuenta, other.getTipoCuenta());
        eb.append(codigoAplicacion, other.getCodigoAplicacion());
        eb.append(codigoPaquete, other.getCodigoPaquete());
        return eb.isEquals();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(tipoCuenta).append(codigoAplicacion).append(codigoPaquete)
                .append(depositoEfectivo).toString();
    }

}
