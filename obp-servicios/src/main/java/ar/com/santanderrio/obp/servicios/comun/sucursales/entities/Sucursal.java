/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.sucursales.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class Sucursal.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Sucursal {
    /** The id sucursal. */
    @XmlAttribute(name = "id")
    private String idSucursal;

    /** The descripcion. */
    @XmlElement(name = "desc_completa")
    private String descripcion;

    /** The direccion. */
    @XmlElement(name = "direccion")
    private String direccion;

    /** The tipo de oficina. */
    @XmlElement(name = "tipo_oficina")
    private String tipoDeOficina;

    /**
     * Gets the id sucursal.
     *
     * @return the id sucursal
     */
    public String getIdSucursal() {
        return idSucursal;
    }

    /**
     * Setter para id sucursal.
     *
     * @param idSucursal
     *            el nuevo id sucursal
     */
    public void setIdSucursal(String idSucursal) {
        this.idSucursal = idSucursal;
    }

    /**
     * Gets the descripcion.
     *
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Setter para descripcion.
     *
     * @param descripcion
     *            el nuevo descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Gets the direccion.
     *
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Setter para direccion.
     *
     * @param direccion
     *            el nuevo direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Gets the tipo de oficina.
     *
     * @return the tipo de oficina
     */
    public String getTipoDeOficina() {
        return tipoDeOficina;
    }

    /**
     * Setter para tipo de oficina.
     *
     * @param tipoDeOficina
     *            el nuevo tipo de oficina
     */
    public void setTipoDeOficina(String tipoDeOficina) {
        this.tipoDeOficina = tipoDeOficina;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(descripcion);
        hcb.append(direccion);
        hcb.append(idSucursal);
        hcb.append(tipoDeOficina);
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
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Sucursal other = (Sucursal) obj;

        EqualsBuilder eb = new EqualsBuilder().append(descripcion, other.descripcion).append(direccion, other.direccion)
                .append(idSucursal, other.idSucursal).append(tipoDeOficina, other.tipoDeOficina);
        return eb.isEquals();

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Sucursal [" + (idSucursal != null ? "idSucursal=" + idSucursal + ", " : "")
                + (descripcion != null ? "descripcion=" + descripcion + ", " : "")
                + (direccion != null ? "direccion=" + direccion + ", " : "")
                + (tipoDeOficina != null ? "tipoDeOficina=" + tipoDeOficina : "") + "]";
    }

}
