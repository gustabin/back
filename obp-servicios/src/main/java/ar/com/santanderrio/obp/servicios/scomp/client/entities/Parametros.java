/**
 * 
 */
package ar.com.santanderrio.obp.servicios.scomp.client.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class Parametros.
 *
 * @author sergio.e.goldentair
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "formatoRespuesta" })
public class Parametros {

    /** The formato respuesta. */
    @XmlElement(name = "formato-respuesta", required = true)
    protected String formatoRespuesta;

    /**
     * Gets the formato respuesta.
     *
     * @return the formatoRespuesta
     */
    public String getFormatoRespuesta() {
        return formatoRespuesta;
    }

    /**
     * Sets the formato respuesta.
     *
     * @param formatoRespuesta
     *            the formatoRespuesta to set
     */
    public void setFormatoRespuesta(String formatoRespuesta) {
        this.formatoRespuesta = formatoRespuesta;
    }

}
