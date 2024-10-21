/**
 * 
 */
package ar.com.santanderrio.obp.generated.webservices.inversiones;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author sergio.e.goldentair
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ParametroDatos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.MESAC.ServiceContracts.Parameters", propOrder = {
	    "fecha",
	    "nup"
	})
public class ParametroDatos {
    /**  */
    @XmlElement(name = "Fecha")
    private String fecha;
    /**  */
    @XmlElement(name = "Nup")
    private String nup;

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha
     *            the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the nup
     */
    public String getNup() {
        return nup;
    }

    /**
     * @param nup
     *            the nup to set
     */
    public void setNup(String nup) {
        this.nup = nup;
    }

}
