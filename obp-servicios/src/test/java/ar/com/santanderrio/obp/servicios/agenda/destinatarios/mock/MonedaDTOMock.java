package ar.com.santanderrio.obp.servicios.agenda.destinatarios.mock;

import ar.com.santanderrio.obp.generated.webservices.alias.MonedaDTO;

/**
 * The Class MonedaDTOMock.
 *
 * @author florencia.n.martinez
 */
public final class MonedaDTOMock {

    private MonedaDTOMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    
    /**
     * Completar info moneda pesos.
     *
     * @return the moneda DTO
     */
    public static MonedaDTO completarInfoMonedaPesos() {
        MonedaDTO moneda = new MonedaDTO();
        moneda.setCodigo("1");
        moneda.setDescripcion("$");
        return moneda;
    }
    
    /**
     * Completar info moneda dolares.
     *
     * @return the moneda DTO
     */
    public static MonedaDTO completarInfoMonedaDolares() {
        MonedaDTO moneda = new MonedaDTO();
        moneda.setCodigo("2");
        moneda.setDescripcion("u$s");
        return moneda;
    }
}
