/**
 * 
 */
package ar.com.santanderrio.obp.servicios.scomp.client.entities;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import ar.com.santanderrio.obp.servicios.scomp.client.domain.Comprobante;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteAltaPlazoFijoUVA;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteCompraVentaDolar;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteFinanciacionResumen;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobantePMCAfip;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobantePMCConDeuda;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobantePMCSinDeuda;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobantePMCVEP;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobantePagarDebin;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteTransfInmRioOB;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteTransfInmRioRio;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ScomServicioNombreEnum;

/**
 * The Class AltaComprobanteRequest.
 *
 * @author sergio.e.goldentair
 */
@XmlRootElement(name = "cambio")
@XmlType(name = "", propOrder = { "nombre", "version", "canal", "subcanal", "tipoComprobante", "subTipoComprobante",
        "comprobante" })
@XmlAccessorType(XmlAccessType.FIELD)
public class AltaComprobanteRequest implements Serializable {
    /** The Serial id. */
    private static final long serialVersionUID = 6950352802909496945L;
    /** Nombre del comprobante. */
    @XmlElement(required = true)
    protected ScomServicioNombreEnum nombre;
    /** Version del comprobante. */
    @XmlElement
    protected String version;
    /** Canal del comprobante. */
    @XmlElement
    protected String canal;
    /** SubCanal del comprobante. */
    @XmlElement
    protected String subcanal;
    /** TipoComprobante del comprobante. */
    @XmlElement
    protected String tipoComprobante;
    /** SubTipoComprobante del comprobante. */
    @XmlElement
    protected String subTipoComprobante;
    /** Comprobante. */
    @XmlElements({ @XmlElement(name = "comprobante", type = ComprobanteTransfInmRioRio.class),
            @XmlElement(name = "comprobante", type = ComprobanteTransfInmRioOB.class),
            @XmlElement(name = "comprobante", type = ComprobantePMCConDeuda.class),
            @XmlElement(name = "comprobante", type = ComprobantePMCSinDeuda.class),
            @XmlElement(name = "comprobante", type = ComprobantePMCVEP.class),
            @XmlElement(name = "comprobante", type = ComprobantePMCAfip.class),
            @XmlElement(name = "comprobante", type = ComprobanteFinanciacionResumen.class),
            @XmlElement(name = "comprobante", type = ComprobanteCompraVentaDolar.class),
            @XmlElement(name = "comprobante", type = ComprobanteAltaPlazoFijoUVA.class),
            @XmlElement(name = "comprobante", type = ComprobantePagarDebin.class)})
    protected Comprobante comprobante;

    /**
     * Gets the nombre.
     *
     * @return the nombre
     */
    public ScomServicioNombreEnum getNombre() {
        return nombre;
    }

    /**
     * Sets the nombre.
     *
     * @param nombre
     *            the nombre to set
     */
    public void setNombre(ScomServicioNombreEnum nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets the version.
     *
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the version.
     *
     * @param version
     *            the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

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
     * Gets the subcanal.
     *
     * @return the subcanal
     */
    public String getSubcanal() {
        return subcanal;
    }

    /**
     * Sets the subcanal.
     *
     * @param subcanal
     *            the subcanal to set
     */
    public void setSubcanal(String subcanal) {
        this.subcanal = subcanal;
    }

    /**
     * Gets the tipo comprobante.
     *
     * @return the tipoComprobante
     */
    public String getTipoComprobante() {
        return tipoComprobante;
    }

    /**
     * Sets the tipo comprobante.
     *
     * @param tipoComprobante
     *            the tipoComprobante to set
     */
    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    /**
     * Gets the sub tipo comprobante.
     *
     * @return the subTipoComprobante
     */
    public String getSubTipoComprobante() {
        return subTipoComprobante;
    }

    /**
     * Sets the sub tipo comprobante.
     *
     * @param subTipoComprobante
     *            the subTipoComprobante to set
     */
    public void setSubTipoComprobante(String subTipoComprobante) {
        this.subTipoComprobante = subTipoComprobante;
    }

    /**
     * Gets the comprobante.
     *
     * @return the comprobante
     */
    public Comprobante getComprobante() {
        return comprobante;
    }

    /**
     * Sets the comprobante.
     *
     * @param comprobante
     *            the comprobante to set
     */
    public void setComprobante(Comprobante comprobante) {
        this.comprobante = comprobante;
    }
}