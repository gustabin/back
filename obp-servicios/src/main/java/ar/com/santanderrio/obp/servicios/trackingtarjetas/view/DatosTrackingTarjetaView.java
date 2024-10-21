/*
 * 
 */
package ar.com.santanderrio.obp.servicios.trackingtarjetas.view;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class DatosTrackingTarjetaView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class DatosTrackingTarjetaView {

    /** The numero estado. */
    private String numeroEstado; // "1"

    /** The descripcion estado. */
    private String descripcionEstado; // "Pendiente de envío"

    /** The detalle fecha. */
    private String detalleFecha; // "26/01/2017"

    /** The mensaje estado. */
    private String mensajeEstado; // "Su tarjeta se encuentra en circuito de distribucion..."

    /** The codigo producto. */
    private String codigoProducto;

    /** The producto. */
    private String producto;

    /** The subtitulo producto. */
    private String subtituloProducto;

    /** The mostrar link reimpresion. */
    private boolean mostrarLinkReimpresion;

    /** The url andreani. */
    private String urlAndreani;

    /**
     * Gets the url andreani.
     *
     * @return the url andreani
     */
    public String getUrlAndreani() {
        return urlAndreani;
    }

    /**
     * Sets the url andreani.
     *
     * @param urlAndreani
     *            the new url andreani
     */
    public void setUrlAndreani(String urlAndreani) {
        this.urlAndreani = urlAndreani;
    }

    /**
     * Gets the codigo producto.
     *
     * @return the codigo producto
     */
    public String getCodigoProducto() {
        return codigoProducto;
    }

    /**
     * Sets the codigo producto.
     *
     * @param codigoProducto
     *            the new codigo producto
     */
    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    /**
     * Checks if is mostrar link reimpresion.
     *
     * @return true, if is mostrar link reimpresion
     */
    public boolean isMostrarLinkReimpresion() {
        return mostrarLinkReimpresion;
    }

    /**
     * Sets the mostrar link reimpresion.
     *
     * @param mostrarLinkReimpresion
     *            the new mostrar link reimpresion
     */
    public void setMostrarLinkReimpresion(boolean mostrarLinkReimpresion) {
        this.mostrarLinkReimpresion = mostrarLinkReimpresion;
    }

    /**
     * Gets the numero estado.
     *
     * @return the numero estado
     */
    public String getNumeroEstado() {
        return numeroEstado;
    }

    /**
     * Sets the numero estado.
     *
     * @param numeroEstado
     *            the new numero estado
     */
    public void setNumeroEstado(String numeroEstado) {
        this.numeroEstado = numeroEstado;
    }

    /**
     * Gets the descripcion estado.
     *
     * @return the descripcion estado
     */
    public String getDescripcionEstado() {
        return descripcionEstado;
    }

    /**
     * Sets the descripcion estado.
     *
     * @param descripcionEstado
     *            the new descripcion estado
     */
    public void setDescripcionEstado(String descripcionEstado) {
        this.descripcionEstado = descripcionEstado;
    }

    /**
     * Gets the detalle fecha.
     *
     * @return the detalle fecha
     */
    public String getDetalleFecha() {
        return detalleFecha;
    }

    /**
     * Sets the detalle fecha.
     *
     * @param detalleFecha
     *            the new detalle fecha
     */
    public void setDetalleFecha(String detalleFecha) {
        this.detalleFecha = detalleFecha;
    }

    /**
     * Gets the mensaje estado.
     *
     * @return the mensaje estado
     */
    public String getMensajeEstado() {
        return mensajeEstado;
    }

    /**
     * Sets the mensaje estado.
     *
     * @param mensajeEstado
     *            the new mensaje estado
     */
    public void setMensajeEstado(String mensajeEstado) {
        this.mensajeEstado = mensajeEstado;
    }

    /**
     * Gets the producto.
     *
     * @return the producto
     */
    public String getProducto() {
        return producto;
    }

    /**
     * Sets the producto.
     *
     * @param producto
     *            the new producto
     */
    public void setProducto(String producto) {
        this.producto = producto;
    }

    /**
     * Gets the subtitulo producto.
     *
     * @return the subtitulo producto
     */
    public String getSubtituloProducto() {
        return subtituloProducto;
    }

    /**
     * Sets the subtitulo producto.
     *
     * @param subtituloProducto
     *            the new subtitulo producto
     */
    public void setSubtituloProducto(String subtituloProducto) {
        this.subtituloProducto = subtituloProducto;
    }
}
