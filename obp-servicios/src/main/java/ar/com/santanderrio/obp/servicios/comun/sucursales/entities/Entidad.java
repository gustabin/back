/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.sucursales.entities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class Entidad.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Entidad {
    /** The id entidad. */
    @XmlAttribute(name = "cod")
    private String idEntidad;

    /** The sucursales. */
    @XmlElement(name = "cod_oficina")
    private List<Sucursal> sucursales;

    /**
     * Gets the id entidad.
     *
     * @return the id entidad
     */
    public String getIdEntidad() {
        return idEntidad;
    }

    /**
     * Setter para id entidad.
     *
     * @param idEntidad
     *            el nuevo id entidad
     */
    public void setIdEntidad(String idEntidad) {
        this.idEntidad = idEntidad;
    }

    /**
     * Gets the sucursales.
     *
     * @return the sucursales
     */
    public List<Sucursal> getSucursales() {
        if (sucursales == null) {
            sucursales = new ArrayList<Sucursal>();
        }
        return sucursales;
    }

    /**
     * Setter para sucursales.
     *
     * @param sucursales
     *            el nuevo sucursales
     */
    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(idEntidad);
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
        Entidad other = (Entidad) obj;
        if (idEntidad == null) {
            if (other.idEntidad != null) {
                return false;
            }
        } else if (!idEntidad.equals(other.idEntidad)) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Entidad [" + (idEntidad != null ? "idEntidad=" + idEntidad : "") + "]";
    }

}
