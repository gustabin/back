package ar.com.santanderrio.obp.servicios.seguros.entities;

import ar.com.santanderrio.obp.servicios.seguros.dto.CompraProtegidaDTO;

import java.io.Serializable;
import java.util.List;

public class CompraProtegidaResponse implements Serializable
{

        /** The Constant serialVersionUID. */
        private static final long serialVersionUID = 1160042353847004189L;

        /** The codigo respuesta. */
        private String codigoRespuesta;

        /** The mensaje. */
        private String mensaje;

        /** The respuesta. */
        private List<CompraProtegidaDTO> respuesta;

        /**
         * Gets the codigo respuesta.
         *
         * @return the codigo respuesta
         */
        public String getCodigoRespuesta() {
        return codigoRespuesta;
    }

        /**
         * Sets the codigo respuesta.
         *
         * @param codigoRespuesta
         *            the new codigo respuesta
         */
        public void setCodigoRespuesta(String codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
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

    public List<CompraProtegidaDTO> getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(List<CompraProtegidaDTO> respuesta) {
        this.respuesta = respuesta;
    }
}
