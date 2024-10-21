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
public class MyaProducto {
    @XmlElement(name = "NroProducto")
    private String nroProducto;

    @XmlElementWrapper(name = "ListaMensajes")
    @XmlElement(name = "Mensaje")
    private List<MyaMensaje> listMyaMensaje;

    /**
     * @return the nroProducto
     */
    public String getNroProducto() {
        return nroProducto;
    }

    /**
     * @param nroProducto
     *            the nroProducto to set
     */
    public void setNroProducto(String nroProducto) {
        this.nroProducto = nroProducto;
    }

    /**
     * @return the listMyaMensaje
     */
    public List<MyaMensaje> getListMyaMensaje() {
        return listMyaMensaje;
    }

    /**
     * @param listMyaMensaje
     *            the listMyaMensaje to set
     */
    public void setListMyaMensaje(List<MyaMensaje> listMyaMensaje) {
        this.listMyaMensaje = listMyaMensaje;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(nroProducto);
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

        MyaProducto other = (MyaProducto) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(nroProducto, other.getNroProducto());
        return eb.isEquals();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(nroProducto).toString();
    }

}
