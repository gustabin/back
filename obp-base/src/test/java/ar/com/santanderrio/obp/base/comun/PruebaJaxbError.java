/**
 * 
 */
package ar.com.santanderrio.obp.base.comun;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * FALTA la anotacion @XmlAccessorType para que funcione el parseo.
 * 
 * @author sergio.e.goldentair
 *
 */
@XmlRootElement(name = "prueba")
public class PruebaJaxbError {

    /** The nombre. */
    @XmlElement
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
        return "PruebaJaxbError [nombre=" + nombre + "]";
    }
}
