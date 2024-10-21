package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The Class LineaDetalleConsumoPendienteMock.
 *
 * @author sabrina.cis
 */
public final class LineaDetalleConsumoPendienteMock {

    private LineaDetalleConsumoPendienteMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    
    /**
     * Obtener linea detalle consumo pendiente.
     *
     * @return the linea detalle consumo pendiente
     */
    public static LineaDetalleConsumoTarjetaDTO obtenerLineaDetalleConsumoPendiente() {
        LineaDetalleConsumoTarjetaDTO linea = new LineaDetalleConsumoTarjetaDTO();
        linea.setCodigoEstablecimiento("");
        linea.setConsumoPesos(true);
        linea.setCuota("2");
        linea.setFecha(new Date());
        linea.setDescripcion("");
        linea.setImporteDolares(BigDecimal.valueOf(16, 80));
        linea.setImportePesos(BigDecimal.valueOf(16, 80));
        return linea;
    }
}