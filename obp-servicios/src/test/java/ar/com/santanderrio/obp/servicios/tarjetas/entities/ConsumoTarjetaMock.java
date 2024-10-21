package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The Class ConsumoTarjetaMock.
 *
 * @author sabrina.cis
 */
public final class ConsumoTarjetaMock {
    
    private ConsumoTarjetaMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    
    /**
     * Obtener consumo tarjeta.
     *
     * @return the consumo tarjeta
     */
    public static ConsumoTarjetaDTO obtenerConsumoTarjeta() {
        ConsumoTarjetaDTO consumo = new ConsumoTarjetaDTO();
        consumo.setConsumoDolares(BigDecimal.valueOf(16.80));
        consumo.setConsumoPesos(BigDecimal.valueOf(16.80));
        consumo.setFechaDesde(new Date());
        consumo.setHasConsumoDolaresCero(false);
        consumo.setHasConsumoPesosCero(false);
        consumo.setHasError(false);
        consumo.setIsAdicional(false);
        consumo.setIsTitular(true);
        consumo.setLineas(obtenerListLineaDetalleConsumoTarjeta());
        consumo.setMarca("VISA");
        consumo.setNombreAdicional(null);
        consumo.setNumero("0000000338501392");
        return consumo;
    }

    /**
     * Obtener list linea detalle consumo tarjeta.
     *
     * @return the list
     */
    private static List<LineaDetalleConsumoTarjetaDTO> obtenerListLineaDetalleConsumoTarjeta() {
        List<LineaDetalleConsumoTarjetaDTO> lineas = new ArrayList<LineaDetalleConsumoTarjetaDTO>();
        lineas.add(LineaDetalleConsumoTarjetaMock.obtenerLineaDetalleConsumoTarjeta());
        return lineas;
    }
}
