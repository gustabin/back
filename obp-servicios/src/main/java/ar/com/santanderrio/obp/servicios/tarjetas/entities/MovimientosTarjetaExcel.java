/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.util.List;

/**
 * The Class MovimientosTarjetaExcel.
 */
public class MovimientosTarjetaExcel {

    /** The ultimos consumos. */
    private UltimosMovimientosTarjetasExcel ultimosConsumos;

    /** The consumos pendientes. */
    private UltimosMovimientosTarjetasExcel consumosPendientes;

    /**
	 * Instantiates a new movimientos tarjeta excel.
	 *
	 * @param ultimosConsumosConfirmados
	 *            the ultimos consumos confirmados
	 * @param ultimosConsumosPendientes
	 *            the ultimos consumos pendientes
	 */
    public MovimientosTarjetaExcel(List<ConsumoTarjetaDTO> ultimosConsumosConfirmados,
            List<ConsumoTarjetaDTO> ultimosConsumosPendientes) {
        ultimosConsumos = new UltimosMovimientosTarjetasExcel(ultimosConsumosConfirmados);
        consumosPendientes = new UltimosMovimientosTarjetasExcel(ultimosConsumosPendientes);
    }

    /**
	 * Gets the ultimos consumos.
	 *
	 * @return the ultimos consumos
	 */
    public UltimosMovimientosTarjetasExcel getUltimosConsumos() {
        return ultimosConsumos;
    }

    /**
	 * Gets the consumos pendientes.
	 *
	 * @return the consumos pendientes
	 */
    public UltimosMovimientosTarjetasExcel getConsumosPendientes() {
        return consumosPendientes;
    }

}
