/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTOParaDesafio;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.TarjetaSolicitadaDTO;

/**
 * The Class AltaDatosReimpresionTarjetasView.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AltaDatosReimpresionTarjetasView extends RsaDTOParaDesafio {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** The tarjetas solicitadas. */
    private List<TarjetaSolicitadaDTO> tarjetasSolicitadas;

    /** The motivos. */
    private List<MotivoView> motivos;

    /** The domicilios. */
    private List<DomicilioView> domicilios;

    /** The comprobante. */
    private ComprobanteAltaReimpresionView comprobante;

    /** The mensaje. */
    private String mensaje;

    /**
     * Instantiates a new alta datos reimpresion tarjetas view.
     */
    public AltaDatosReimpresionTarjetasView() {
        super(OperacionesRSAEnum.REIMPRESION_TARJETA);
    }

    /**
     * Gets the tarjetas solicitadas.
     *
     * @return the tarjetas solicitadas
     */
    public List<TarjetaSolicitadaDTO> getTarjetasSolicitadas() {
        return tarjetasSolicitadas;
    }

    /**
     * Sets the tarjetas solicitadas.
     *
     * @param tarjetasSolicitadas
     *            the new tarjetas solicitadas
     */
    public void setTarjetasSolicitadas(List<TarjetaSolicitadaDTO> tarjetasSolicitadas) {
        this.tarjetasSolicitadas = tarjetasSolicitadas;
    }

    /**
     * Gets the motivos.
     *
     * @return the motivos
     */
    public List<MotivoView> getMotivos() {
        return motivos;
    }

    /**
     * Sets the motivos.
     *
     * @param motivos
     *            the new motivos
     */
    public void setMotivos(List<MotivoView> motivos) {
        this.motivos = motivos;
    }

    /**
     * Gets the domicilios.
     *
     * @return the domicilios
     */
    public List<DomicilioView> getDomicilios() {
        return domicilios;
    }

    /**
     * Sets the domicilios.
     *
     * @param domicilios
     *            the new domicilios
     */
    public void setDomicilios(List<DomicilioView> domicilios) {
        this.domicilios = domicilios;
    }

    /**
     * Gets the comprobante.
     *
     * @return the comprobante
     */
    public ComprobanteAltaReimpresionView getComprobante() {
        return comprobante;
    }

    /**
     * Sets the comprobante.
     *
     * @param comprobante
     *            the new comprobante
     */
    public void setComprobante(ComprobanteAltaReimpresionView comprobante) {
        this.comprobante = comprobante;
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
     * @param mensaje
     *            the new mensaje
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
