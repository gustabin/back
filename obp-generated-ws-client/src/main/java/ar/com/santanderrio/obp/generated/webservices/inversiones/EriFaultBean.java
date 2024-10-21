/**
 * 
 */
package ar.com.santanderrio.obp.generated.webservices.inversiones;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Elemento contendio en la exception.
 * 
 * @author sergio.e.goldentair
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class EriFaultBean {

    /** The mensaje. */
    @XmlElement(name = "Message", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.MESAC.Entity.ERI")
    protected String mensaje;

    /** The codigo. */
    @XmlElement(name = "Code", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.MESAC.Entity.ERI")
    protected String codigo;

    /**
     * Constructor EriFaultBean.
     */
    public EriFaultBean() {
        super();
    }

    /**
     * Gets the mensaje.
     *
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Sets the mensaje.
     *
     * @param value
     *            the new mensaje
     */
    public void setMensaje(String value) {
        this.mensaje = value;
    }
}
