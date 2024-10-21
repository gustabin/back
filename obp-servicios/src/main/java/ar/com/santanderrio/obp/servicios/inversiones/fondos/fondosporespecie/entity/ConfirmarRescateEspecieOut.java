package ar.com.santanderrio.obp.servicios.inversiones.fondos.fondosporespecie.entity;

import java.io.Serializable;

public class ConfirmarRescateEspecieOut implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -2423997013182559430L;
    /** The codigoRespuesta. */
    private String codigoRespuesta;
    /** The nroOrden. */
    private String nroOrden;

    /**
     * @return the codigoRespuesta
     */
    public String getCodigoRespuesta() {
        return codigoRespuesta;
    }

    /**
     * @param codigoRespuesta
     *            the codigoRespuesta to set
     */
    public void setCodigoRespuesta(String codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    /**
     * @return the nroOrden
     */
    public String getNroOrden() {
        return nroOrden;
    }

    /**
     * @param nroOrden
     *            the nroOrden to set
     */
    public void setNroOrden(String nroOrden) {
        this.nroOrden = nroOrden;
    }
}
