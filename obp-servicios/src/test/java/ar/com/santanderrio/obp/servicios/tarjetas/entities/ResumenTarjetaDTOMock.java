package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class ResumenTarjetaDTOMock.
 *
 * @author sabrina.cis
 */
public final class ResumenTarjetaDTOMock {
    
    private ResumenTarjetaDTOMock() {
        throw new IllegalAccessError("Clase para testing");
    }

    /**
     * Obtener resumen tarjeta DTO.
     *
     * @return the resumen tarjeta DTO
     */
    public static ResumenTarjetaDTO obtenerResumenTarjetaDTO() {
        DetalleTarjetaDTO detalle = DetalleTarjetaDTOMock.obtenerDetalle();
        ResumenTarjetaDTO rdto = new ResumenTarjetaDTO();
        rdto.setCodigoError(null);
        rdto.setDetalle(detalle);
        List<DetalleTarjetaDTO> detallesTarjetas = new ArrayList<DetalleTarjetaDTO>();
        detallesTarjetas.add(detalle);
        rdto.setDetallesTarjetas(detallesTarjetas);
        rdto.setErrorConsumos(false);
        rdto.setErrorDisponibles(false);
        return rdto;
    }
}
