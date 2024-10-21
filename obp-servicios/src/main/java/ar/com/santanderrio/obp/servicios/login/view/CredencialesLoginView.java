/*
 * 
 */
package ar.com.santanderrio.obp.servicios.login.view;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class CredencialesLoginView.
 *
 * @author B041964 View para recibir datos que manda el front para actualizar
 *         usuario o clave del cliente
 */
@XmlRootElement(name = "loginView", namespace = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
public class CredencialesLoginView implements Serializable {

    /** The serialVersionUID. */
    private static final long serialVersionUID = 6285514787448984417L;

    /** The claveActual. */
    private String claveActual;

    /** The claveNueva. */
    private String claveNueva;

    /** The usuarioActual. */
    private String usuarioActual;

    /** The usuarioNuevo. */
    private String usuarioNuevo;

    /** The usuario nuevo formateado. */
    private String usuarioNuevoFormateado;

    /** The clave nueva formateada. */
    private String claveNuevaFormateada;

    /** The tipo teclado. */
    private String tipoTeclado;

    /** The usuarioNuevo. */
    private String dni;

    /** * The isFromClaveOnline *. */
    private boolean isFromClaveOnline;

    /**
     * Gets the clave actual.
     *
     * @return the clave actual
     */
    public String getClaveActual() {
        return claveActual;
    }

    /**
     * Sets the clave actual.
     *
     * @param claveActual
     *            the new clave actual
     */
    public void setClaveActual(String claveActual) {
        this.claveActual = claveActual;
    }

    /**
     * Gets the clave nueva.
     *
     * @return the clave nueva
     */
    public String getClaveNueva() {
        return claveNueva;
    }

    /**
     * Sets the clave nueva.
     *
     * @param claveNueva
     *            the new clave nueva
     */
    public void setClaveNueva(String claveNueva) {
        this.claveNueva = claveNueva;
    }

    /**
     * Gets the usuario actual.
     *
     * @return the usuario actual
     */
    public String getUsuarioActual() {
        return usuarioActual;
    }

    /**
     * Sets the usuario actual.
     *
     * @param usuarioActual
     *            the new usuario actual
     */
    public void setUsuarioActual(String usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    /**
     * Gets the usuario nuevo.
     *
     * @return the usuario nuevo
     */
    public String getUsuarioNuevo() {
        return usuarioNuevo;
    }

    /**
     * Sets the usuario nuevo.
     *
     * @param usuarioNuevo
     *            the new usuario nuevo
     */
    public void setUsuarioNuevo(String usuarioNuevo) {
        this.usuarioNuevo = usuarioNuevo;
    }

    /**
     * * Gets the dni .
     *
     * @return the dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * * Sets the dni .
     *
     * @param dni
     *            the new dni
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * gets the isFromClaveOnline.
     *
     * @return true, if is from clave online
     */
    public boolean isFromClaveOnline() {
        return isFromClaveOnline;
    }

    /**
     * sets the isFromClaveOnline.
     *
     * @param isFromClaveOnline
     *            the new from clave online
     */
    public void setIsFromClaveOnline(boolean isFromClaveOnline) {
        this.isFromClaveOnline = isFromClaveOnline;
    }

    /**
     * sets the fromClaveOnline.
     *
     * @param isFromClaveOnline
     *            the new from clave online
     */
    public void setFromClaveOnline(boolean isFromClaveOnline) {
        this.setIsFromClaveOnline(isFromClaveOnline);
    }

    /**
     * Gets the usuario nuevo formateado.
     *
     * @return the usuario nuevo formateado
     */
    public String getUsuarioNuevoFormateado() {
        return usuarioNuevoFormateado;
    }

    /**
     * Sets the usuario nuevo formateado.
     *
     * @param usuarioNuevoFormateado
     *            the new usuario nuevo formateado
     */
    public void setUsuarioNuevoFormateado(String usuarioNuevoFormateado) {
        this.usuarioNuevoFormateado = usuarioNuevoFormateado;
    }

    /**
     * Gets the clave nueva formateada.
     *
     * @return the clave nueva formateada
     */
    public String getClaveNuevaFormateada() {
        return claveNuevaFormateada;
    }

    /**
     * Sets the clave nueva formateada.
     *
     * @param claveNuevaFormateada
     *            the new clave nueva formateada
     */
    public void setClaveNuevaFormateada(String claveNuevaFormateada) {
        this.claveNuevaFormateada = claveNuevaFormateada;
    }

    /**
     * Gets the tipo teclado.
     *
     * @return the tipoTeclado
     */
    public String getTipoTeclado() {
        return tipoTeclado;
    }

    /**
     * Sets the tipo teclado.
     *
     * @param tipoTeclado
     *            the tipoTeclado to set
     */
    public void setTipoTeclado(String tipoTeclado) {
        this.tipoTeclado = tipoTeclado;
    }

}
