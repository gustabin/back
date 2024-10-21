/**
 * 
 */
package ar.com.santanderrio.obp.generated.webservices.mya.dominio;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author sergio.e.goldentair
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class MyaProductoReq {
    @XmlElement(name = "NumeroProducto")
    private String numeroProducto;
    @XmlElementWrapper(name = "ListaDeMensajes")
    @XmlElement(name = "NroMensaje")
    private List<MyaNroMensaje> listMyaNroMensaje;

    /**
     * @return the numeroProducto
     */
    public String getNumeroProducto() {
        return numeroProducto;
    }

    /**
     * @param numeroProducto
     *            the numeroProducto to set
     */
    public void setNumeroProducto(String numeroProducto) {
        this.numeroProducto = numeroProducto;
    }

    /**
     * @return the listMyaNroMensaje
     */
    public List<MyaNroMensaje> getListMyaNroMensaje() {
        return listMyaNroMensaje;
    }

    /**
     * @param listMyaNroMensaje
     *            the listMyaNroMensaje to set
     */
    public void setListMyaNroMensaje(List<MyaNroMensaje> listMyaNroMensaje) {
        this.listMyaNroMensaje = listMyaNroMensaje;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(numeroProducto);
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

        MyaProductoReq other = (MyaProductoReq) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(numeroProducto, other.getNumeroProducto());
        return eb.isEquals();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(numeroProducto).toString();
    }

}
