/**
 * 
 */
package ar.com.santanderrio.obp.generated.webservices.scomp.dominio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * The Class Comprobante.
 *
 * @author sergio.e.goldentair
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({ ComprobanteTrfcci.class, ComprobantePaghabcci.class, ComprobanteTrfcta.class, ComprobanteTrfcta7x24.class,
        ComprobanteTarjRecargOk.class, ComprobantePagoCompraOk.class, ComprobanteTransfInmRioRio.class,
        ComprobanteTransfInmRioOB.class, ComprobantePMCConDeuda.class, ComprobantePMCSinDeuda.class,
        ComprobantePMCVEP.class, ComprobantePMCAfip.class, ComprobanteFinanciacionResumen.class, ComprobanteCompraVentaDolar.class})
public abstract class Comprobante {

    /** The canal. */
    @XmlElement(name = "Canal")
    protected String canal;

    /** The sub canal. */
    @XmlElement(name = "SubCanal")
    protected String subCanal;

    /** The tpo comprobante. */
    @XmlElement(name = "TpoComprobante")
    protected String tpoComprobante;

    /** The sub tpo comprobante. */
    @XmlElement(name = "SubTpoComprobante")
    protected String subTpoComprobante;

    /**
     * Gets the canal.
     *
     * @return the canal
     */
    public String getCanal() {
        return canal;
    }

    /**
     * Sets the canal.
     *
     * @param canal
     *            the canal to set
     */
    public void setCanal(String canal) {
        this.canal = canal;
    }

    /**
     * Gets the value of the subCanal property.
     *
     * @return the sub canal
     */
    public String getSubCanal() {
        return subCanal;
    }

    /**
     * Sets the value of the subCanal property.
     *
     * @param value
     *            the new sub canal
     */
    public void setSubCanal(String value) {
        this.subCanal = value;
    }

    /**
     * Gets the value of the tpoComprobante property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getTpoComprobante() {
        return tpoComprobante;
    }

    /**
     * Sets the value of the tpoComprobante property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setTpoComprobante(String value) {
        this.tpoComprobante = value;
    }

    /**
     * Gets the value of the subTpoComprobante property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getSubTpoComprobante() {
        return subTpoComprobante;
    }

    /**
     * Sets the value of the subTpoComprobante property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setSubTpoComprobante(String value) {
        this.subTpoComprobante = value;
    }

}
