/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.math.BigDecimal;
import java.util.List;

/**
 * The Class MovimientosTarjetaExcel2.
 */
public class MovimientosTarjetaExcel2 {

    /** The tarjetas titular. */
    private List<ConsumoTarjetaDTO> ultimosConsumos;

    /** The tarjetas adicional. */
    private List<ConsumoTarjetaDTO> consumosPendientes;

    /** The total consumos pesos. */
    private BigDecimal totalUltimosConsumosPesos = BigDecimal.ZERO;

    /** The total consumos dolares. */
    private BigDecimal totalUltimosConsumosDolares = BigDecimal.ZERO;

    /** The total consumos pesos. */
    private BigDecimal totalConsumosPendientesPesos = BigDecimal.ZERO;

    /** The total consumos dolares. */
    private BigDecimal totalConsumosPendientesDolares = BigDecimal.ZERO;

    /** The recargable. */
    private Boolean recargable = Boolean.FALSE;

    /**
	 * Instantiates a new movimientos tarjeta excel 2.
	 *
	 * @param ultimoConsumoIn
	 *            the ultimo consumo in
	 * @param consumosPendientesIn
	 *            the consumos pendientes in
	 */
    public MovimientosTarjetaExcel2(List<ConsumoTarjetaDTO> ultimoConsumoIn,
            List<ConsumoTarjetaDTO> consumosPendientesIn) {
        this.ultimosConsumos = ultimoConsumoIn;
        this.consumosPendientes = consumosPendientesIn;

        for (ConsumoTarjetaDTO dto : ultimoConsumoIn) {
            if (dto.getMarca().contains("Recargable")) {
                recargable = true;
            }
            if (dto.getConsumoPesos() != null && dto.getConsumoPesos().toString().length() != 0) {
                totalUltimosConsumosPesos = totalUltimosConsumosPesos.add(dto.getConsumoPesos());
            }

            if (dto.getConsumoDolares() != null && dto.getConsumoDolares().toString().length() != 0) {
                totalUltimosConsumosDolares = totalUltimosConsumosDolares.add(dto.getConsumoDolares());
            }
        }
        for (ConsumoTarjetaDTO dto : consumosPendientesIn) {
            if (dto.getConsumoPesos() != null && dto.getConsumoPesos().toString().length() != 0) {
                totalConsumosPendientesPesos = totalConsumosPendientesPesos.add(dto.getConsumoPesos());
            }

            if (dto.getConsumoDolares() != null && dto.getConsumoDolares().toString().length() != 0) {
                totalConsumosPendientesDolares = totalConsumosPendientesDolares.add(dto.getConsumoDolares());
            }
        }
    }

    /**
	 * Gets the total ultimos consumos pesos.
	 *
	 * @return the total ultimos consumos pesos
	 */
    public BigDecimal getTotalUltimosConsumosPesos() {
        return totalUltimosConsumosPesos;
    }

    /**
	 * Gets the total ultimos consumos dolares.
	 *
	 * @return the total ultimos consumos dolares
	 */
    public BigDecimal getTotalUltimosConsumosDolares() {
        return totalUltimosConsumosDolares;
    }

    /**
	 * Gets the total consumos pendientes pesos.
	 *
	 * @return the total consumos pendientes pesos
	 */
    public BigDecimal getTotalConsumosPendientesPesos() {
        return totalConsumosPendientesPesos;
    }

    /**
	 * Gets the total consumos pendientes dolares.
	 *
	 * @return the total consumos pendientes dolares
	 */
    public BigDecimal getTotalConsumosPendientesDolares() {
        return totalConsumosPendientesDolares;
    }

    /**
	 * Gets the ultimos consumos.
	 *
	 * @return the ultimos consumos
	 */
    public List<ConsumoTarjetaDTO> getUltimosConsumos() {
        return ultimosConsumos;
    }

    /**
	 * Gets the consumos pendientes.
	 *
	 * @return the consumos pendientes
	 */
    public List<ConsumoTarjetaDTO> getConsumosPendientes() {
        return consumosPendientes;
    }

    /**
	 * Gets the tiene ultimos consumos.
	 *
	 * @return the tiene ultimos consumos
	 */
    public Boolean getTieneUltimosConsumos() {
        if (ultimosConsumos == null || ultimosConsumos.isEmpty()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
	 * Gets the tiene consumos pendientes.
	 *
	 * @return the tiene consumos pendientes
	 */
    public Boolean getTieneConsumosPendientes() {
        if (consumosPendientes == null || consumosPendientes.isEmpty()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
	 * Gets the recargable.
	 *
	 * @return the recargable
	 */
    public Boolean getRecargable() {
        return recargable;
    }

    /**
	 * Sets the recargable.
	 *
	 * @param recargable
	 *            the new recargable
	 */
    public void setRecargable(Boolean recargable) {
        this.recargable = recargable;
    }

}