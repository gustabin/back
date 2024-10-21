package ar.com.santanderrio.obp.servicios.base.mensaje.entities;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;

/**
 * The Class MensajeMock.
 *
 * @author florencia.n.martinez
 */
public final class MensajeMock {
    
    private MensajeMock() {
        throw new IllegalAccessError("Clase para testing");
    }

    /**
     * Completa la informacion del objeto Mensaje.
     *
     * @param codigo
     *            the codigo
     * @param mensaje
     *            the mensaje
     * @return the mensaje
     */
    public static Mensaje completarInfoMensaje(String codigo, String mensaje) {
        Mensaje msj = new Mensaje();
        msj.setCodigo(codigo);
        msj.setMensaje(mensaje);
        return msj;
    }
}
