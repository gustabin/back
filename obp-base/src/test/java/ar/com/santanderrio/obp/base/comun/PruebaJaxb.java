/**
 * 
 */
package ar.com.santanderrio.obp.base.comun;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class PruebaJaxb.
 *
 * @author sergio.e.goldentair
 */
@XmlRootElement(name = "prueba")
@XmlAccessorType(XmlAccessType.FIELD)
public class PruebaJaxb {

    /** The nombre. */
    @XmlElement(name = "nombre")
    private String nombre;

    /**
     * Gets the nombre.
     *
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the nombre.
     *
     * @param nombre
     *            the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PruebaJaxb [nombre=" + nombre + "]";
    }
}
