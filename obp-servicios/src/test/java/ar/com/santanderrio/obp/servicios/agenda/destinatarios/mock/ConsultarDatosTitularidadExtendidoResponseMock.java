package ar.com.santanderrio.obp.servicios.agenda.destinatarios.mock;

import ar.com.santanderrio.obp.generated.webservices.alias.ConsultarDatosTitularidadExtendidoResponse;
import ar.com.santanderrio.obp.generated.webservices.alias.TitularidadExtendido;

/**
 * The Class ConsultarDatosTitularidadExtendidoResponseMock.
 *
 * @author florencia.n.martinez
 */
public final class ConsultarDatosTitularidadExtendidoResponseMock {

    private ConsultarDatosTitularidadExtendidoResponseMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    
    /**
     * Completar info rio.
     *
     * @return the consultar datos titularidad extendido response
     */
    public static ConsultarDatosTitularidadExtendidoResponse completarInfoRio(
            TitularidadExtendido titularidadExtendido) {
        ConsultarDatosTitularidadExtendidoResponse response = new ConsultarDatosTitularidadExtendidoResponse();
        response.setTitularidadExtendido(titularidadExtendido);
        return response;
    }

    /**
     * Completar info error.
     *
     * @param codigo
     *            the codigo
     * @param mensaje
     *            the mensaje
     * @return the consultar datos titularidad extendido response
     */
    public static ConsultarDatosTitularidadExtendidoResponse completarInfoError(String codigo, String mensaje) {
        ConsultarDatosTitularidadExtendidoResponse response = new ConsultarDatosTitularidadExtendidoResponse();
        response.setCodigo(codigo);
        response.setMensaje(mensaje);
        return response;
    }

}
