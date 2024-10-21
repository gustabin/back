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
public class MyaHeaderRequest {
    /** Servicio que se esta invocando. **/
    @XmlElement(name = "Servicio")
    private MyaServiciosEnum servicio;
    /** Canal **/
    @XmlElement(name = "Canal")
    private String canal;
    /** SubCanal **/
    @XmlElement(name = "SubCanal")
    private String subCanal;
    /** Nup del cliente. **/
    @XmlElement(name = "NUP")
    private String nup;
    @XmlElement(name = "Legajo")
    private String legajo;
    @XmlElement(name = "Nivel")
    private String nivel;

    /**
     * @return the servicio
     */
    public MyaServiciosEnum getServicio() {
        return servicio;
    }

    /**
     * @param servicio
     *            the servicio to set
     */
    public void setServicio(MyaServiciosEnum servicio) {
        this.servicio = servicio;
    }

    /**
     * @return the canal
     */
    public String getCanal() {
        return canal;
    }

    /**
     * @param canal
     *            the canal to set
     */
    public void setCanal(String canal) {
        this.canal = canal;
    }

    /**
     * @return the subCanal
     */
    public String getSubCanal() {
        return subCanal;
    }

    /**
     * @param subCanal
     *            the subCanal to set
     */
    public void setSubCanal(String subCanal) {
        this.subCanal = subCanal;
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

    /**
     * @return the legajo
     */
    public String getLegajo() {
        return legajo;
    }

    /**
     * @param legajo
     *            the legajo to set
     */
    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }
    
    /**
     * @return the nivel
     */
    public String getNivel() {
        return nivel;
    }

    /**
     * @param nivel
     *            the nivel to set
     */
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}
