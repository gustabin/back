/**
 * 
 */
package ar.com.santanderrio.obp.generated.webservices.mya.dominio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author sergio.e.goldentair
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class MyaBodyRequest {
    /** */
    @XmlElement
    private MyaBodyDatosRequest datos;

    /**
     * @return the datos
     */
    public MyaBodyDatosRequest getDatos() {
        return datos;
    }

    /**
     * @param datos
     *            the datos to set
     */
    public void setDatos(MyaBodyDatosRequest datos) {
        this.datos = datos;
    }

}
