package ar.com.santanderrio.obp.servicios.seguros.entities;

import ar.com.santanderrio.obp.servicios.seguros.dto.FlagCompraProtegidaDTO;

import java.io.Serializable;
import java.util.List;

public class FlagCompraProtegidaResponse implements Serializable
{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1160042353847004789L;

    /** The codigo respuesta. */
    private String codigoRespuesta;

    /** The mensaje. */
    private String mensaje;

    /** The respuesta. */
    private Boolean respuesta;

    public String getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(String codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Boolean getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Boolean respuesta) {
        this.respuesta = respuesta;
    }
}
