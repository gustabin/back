package ar.com.santanderrio.obp.servicios.tarjetas.entities;

/**
 * The Class LimitesYDisponiblesDTOMock.
 *
 * @author sabrina.cis
 */
public final class LimitesYDisponiblesDTOMock {
    
    private LimitesYDisponiblesDTOMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    
    /**
     * Obtener tarjeta activa.
     *
     * @return the limites Y disponibles DTO
     */
    public static LimitesYDisponiblesDTO obtenerLimitesYDisponiblesDTO() {
        return new LimitesYDisponiblesDTO();
    }

}
