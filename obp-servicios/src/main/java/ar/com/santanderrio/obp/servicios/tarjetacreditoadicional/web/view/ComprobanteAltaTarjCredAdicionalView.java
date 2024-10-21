/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.ComprobanteAltaTarjCredAdicionalDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.TarjetaAdicionalSolicitadaDTO;

/**
 * The Class ComprobanteAltaTarjCredAdicionalView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class ComprobanteAltaTarjCredAdicionalView {

    /** The estado respuesta. */
    private EstadoRespuesta estadoRespuesta;

    /** The apellido. */
    private String apellido;

    /** The nombre. */
    private String nombre;

    /** The dni adicional. */
    private String dniAdicional;

    /** The tarjetas adicionales solicitadas. */
    private List<TarjetaAdicionalSolicitadaView> tarjetasAdicionalesSolicitadas;

    /** The fecha hora. */
    private String fechaHora;

    /** The legal. */
    private String legal;

    /**
	 * Instantiates a new comprobante alta tarj cred adicional view.
	 */
    public ComprobanteAltaTarjCredAdicionalView() {
        super();
    }

    /**
	 * Instantiates a new comprobante alta tarj cred adicional view.
	 *
	 * @param respuesta
	 *            the respuesta
	 */
    public ComprobanteAltaTarjCredAdicionalView(ComprobanteAltaTarjCredAdicionalDTO respuesta) {
        this.estadoRespuesta = respuesta.getEstadoRespuesta();
        this.apellido = respuesta.getApellido();
        this.nombre = respuesta.getNombre();
        this.dniAdicional = respuesta.getDniAdicional();
        this.fechaHora = respuesta.getFechaHora();
        this.legal = respuesta.getLegal();

        this.tarjetasAdicionalesSolicitadas = new ArrayList<TarjetaAdicionalSolicitadaView>();
        for (TarjetaAdicionalSolicitadaDTO tarjeta : respuesta.getTarjetasAdicionalesSolicitadas()) {
            this.tarjetasAdicionalesSolicitadas.add(new TarjetaAdicionalSolicitadaView(tarjeta));
        }
    }

    /**
     * Gets the estado respuesta.
     *
     * @return the estado respuesta
     */
    public EstadoRespuesta getEstadoRespuesta() {
        return estadoRespuesta;
    }

    /**
     * Sets the estado respuesta.
     *
     * @param estadoRespuesta
     *            the new estado respuesta
     */
    public void setEstadoRespuesta(EstadoRespuesta estadoRespuesta) {
        this.estadoRespuesta = estadoRespuesta;
    }

    /**
     * Gets the apellido.
     *
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Sets the apellido.
     *
     * @param apellido
     *            the new apellido
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Gets the nombre.
     *
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the nombre.
     *
     * @param nombre
     *            the new nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets the dni adicional.
     *
     * @return the dniAdicional
     */
    public String getDniAdicional() {
        return dniAdicional;
    }

    /**
     * Sets the dni adicional.
     *
     * @param dniAdicional
     *            the dniAdicional to set
     */
    public void setDniAdicional(String dniAdicional) {
        this.dniAdicional = dniAdicional;
    }

    /**
     * Gets the tarjetas adicionales solicitadas.
     *
     * @return the tarjetasAdicionalesSolicitadas
     */
    public List<TarjetaAdicionalSolicitadaView> getTarjetasAdicionalesSolicitadas() {
        return tarjetasAdicionalesSolicitadas;
    }

    /**
     * Sets the tarjetas adicionales solicitadas.
     *
     * @param tarjetasAdicionalesSolicitadas
     *            the tarjetasAdicionalesSolicitadas to set
     */
    public void setTarjetasAdicionalesSolicitadas(List<TarjetaAdicionalSolicitadaView> tarjetasAdicionalesSolicitadas) {
        this.tarjetasAdicionalesSolicitadas = tarjetasAdicionalesSolicitadas;
    }

    /**
     * Gets the fecha hora.
     *
     * @return the fechaHora
     */
    public String getFechaHora() {
        return fechaHora;
    }

    /**
     * Sets the fecha hora.
     *
     * @param fechaHora
     *            the fechaHora to set
     */
    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * Gets the legal.
     *
     * @return the legal
     */
    public String getLegal() {
        return legal;
    }

    /**
     * Sets the legal.
     *
     * @param legal
     *            the legal to set
     */
    public void setLegal(String legal) {
        this.legal = legal;
    }

}
