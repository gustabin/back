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
public class MyaDestinoVinculado {
    @XmlElement(name = "DestVincTipo")
    private String destVincTipo;
    @XmlElement(name = "DestVincSecuencia")
    private String destVincSecuencia;

    /**
     * @return the destVincTipo
     */
    public String getDestVincTipo() {
        return destVincTipo;
    }

    /**
     * @param destVincTipo
     *            the destVincTipo to set
     */
    public void setDestVincTipo(String destVincTipo) {
        this.destVincTipo = destVincTipo;
    }

    /**
     * @return the destVincSecuencia
     */
    public String getDestVincSecuencia() {
        return destVincSecuencia;
    }

    /**
     * @param destVincSecuencia
     *            the destVincSecuencia to set
     */
    public void setDestVincSecuencia(String destVincSecuencia) {
        this.destVincSecuencia = destVincSecuencia;
    }

}
