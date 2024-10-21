package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The Class LineaDetalleConsumoTarjetaMock.
 *
 * @author sabrina.cis
 */
public final class LineaDetalleConsumoTarjetaMock {

    private LineaDetalleConsumoTarjetaMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    
    /**
     * Obtener linea detalle consumo tarjeta.
     *
     * @return the linea detalle consumo tarjeta
     */
    public static LineaDetalleConsumoTarjetaDTO obtenerLineaDetalleConsumoTarjeta() {
        LineaDetalleConsumoTarjetaDTO linea = new LineaDetalleConsumoTarjetaDTO();
        linea.setCodigoEstablecimiento("");
        linea.setConsumoDolares(true);
        linea.setConsumoPesos(true);
        linea.setCuota("");
        linea.setCuotasCanceladas(new Integer(0));
        linea.setCuotasTotales(new Integer(0));
        linea.setDescripcion("");
        linea.setFecha(new Date());
        linea.setImporteDolares(BigDecimal.valueOf(16.80));
        linea.setImportePesos(BigDecimal.valueOf(16.80));
        linea.setNroReferencia("");
        linea.setTieneCuota(true);
        return linea;
    }

}
