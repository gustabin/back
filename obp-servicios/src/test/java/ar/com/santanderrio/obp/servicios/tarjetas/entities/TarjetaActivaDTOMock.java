package ar.com.santanderrio.obp.servicios.tarjetas.entities;

/**
 * The Class TarjetaActivaDTOMock.
 *
 * @author sabrina.cis
 */
public final class TarjetaActivaDTOMock {

    private TarjetaActivaDTOMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    
    /**
     * Obtener tarjeta activa.
     *
     * @return the tarjeta activa DTO
     */
    public static LimitesYDisponiblesDTO obtenerTarjetaActiva() {
        LimitesYDisponiblesDTO ta = new LimitesYDisponiblesDTO();
        ta.setNroTarjeta("0000000338501392");
        return ta;
    }

}
