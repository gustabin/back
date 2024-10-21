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
public class MyaDestinosPermitidos {
    @XmlElement(name = "DPMail")
    private String dpMail;
    @XmlElement(name = "DPCelular")
    private String dpCelular;
    @XmlElement(name = "DPAgenda")
    private String dpAgenda;

    /**
     * @return the dpMail
     */
    public String getDpMail() {
        return dpMail;
    }

    /**
     * @param dpMail
     *            the dpMail to set
     */
    public void setDpMail(String dpMail) {
        this.dpMail = dpMail;
    }

    /**
     * @return the dpCelular
     */
    public String getDpCelular() {
        return dpCelular;
    }

    /**
     * @param dpCelular
     *            the dpCelular to set
     */
    public void setDpCelular(String dpCelular) {
        this.dpCelular = dpCelular;
    }

    /**
     * @return the dpAgenda
     */
    public String getDpAgenda() {
        return dpAgenda;
    }

    /**
     * @param dpAgenda
     *            the dpAgenda to set
     */
    public void setDpAgenda(String dpAgenda) {
        this.dpAgenda = dpAgenda;
    }
}
