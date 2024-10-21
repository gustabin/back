package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import ar.com.santanderrio.obp.servicios.tarjetas.view.Reintentar;

/**
 * @author florencia.n.martinez
 *
 */
public final class ReintentarMock {

    private ReintentarMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    
    public static Reintentar obtenerSinReintentos() {
        Reintentar reintentar = new Reintentar();
        reintentar.setReintentar("false");
        reintentar.setPuntoDeAcceso("0");
        return reintentar;
    }
    
    public static Reintentar obtenerConReintentos() {
        Reintentar reintentar = new Reintentar();
        reintentar.setReintentar("true");
        reintentar.setPuntoDeAcceso("10");
        return reintentar;
    }
}
