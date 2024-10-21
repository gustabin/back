/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class UltimosMovimientosTarjetasExcel.
 */
public class UltimosMovimientosTarjetasExcel {

	/** The tarjetas titular. */
	private List<ConsumoTarjetaDTO> tarjetasTitular;

	/** The tarjetas adicional. */
	private List<ConsumoTarjetaDTO> tarjetasAdicional;

	/** The total consumos pesos. */
	private BigDecimal totalConsumosPesos = BigDecimal.ZERO;

	/** The total consumos dolares. */
	private BigDecimal totalConsumosDolares = BigDecimal.ZERO;

	/**
	 * Instantiates a new ultimos movimientos tarjetas excel.
	 *
	 * @param consumoTarjetas
	 *            the consumo tarjetas
	 */
	public UltimosMovimientosTarjetasExcel(List<ConsumoTarjetaDTO> consumoTarjetas) {
		this.tarjetasTitular = new ArrayList<ConsumoTarjetaDTO>();
		this.tarjetasAdicional = new ArrayList<ConsumoTarjetaDTO>();

		for (ConsumoTarjetaDTO dto : consumoTarjetas) {
			if (dto.getIsTitular()) {
				this.tarjetasTitular.add(dto);
			}
			if (dto.getIsAdicional()) {
				this.tarjetasAdicional.add(dto);
			}

			if (dto.getConsumoPesos() != null && dto.getConsumoPesos().toString().length() != 0) {
				totalConsumosPesos = totalConsumosPesos.add(dto.getConsumoPesos());
			}

			if (dto.getConsumoDolares() != null && dto.getConsumoDolares().toString().length() != 0) {
				totalConsumosDolares = totalConsumosDolares.add(dto.getConsumoDolares());
			}
		}
	}

	/**
	 * Gets the total consumos pesos.
	 *
	 * @return the totalConsumosPesos
	 */
	public BigDecimal getTotalConsumosPesos() {
		return totalConsumosPesos;
	}

	/**
	 * Gets the total consumos dolares.
	 *
	 * @return the totalConsumosDolares
	 */
	public BigDecimal getTotalConsumosDolares() {
		return totalConsumosDolares;
	}

	/**
	 * Gets the tarjetas titular.
	 *
	 * @return the tarjetasTitular
	 */
	public List<ConsumoTarjetaDTO> getTarjetasTitular() {
		return tarjetasTitular;
	}

	/**
	 * Gets the tarjetas adicional.
	 *
	 * @return the tarjetasAdicional
	 */
	public List<ConsumoTarjetaDTO> getTarjetasAdicional() {
		return tarjetasAdicional;
	}

	/**
	 * Gets the tiene algun adicional.
	 *
	 * @return the tiene algun adicional
	 */
	public Boolean getTieneAlgunAdicional() {
		return !this.tarjetasAdicional.isEmpty() ? Boolean.TRUE : Boolean.FALSE;
	}

	/**
	 * Gets the tiene solo titular.
	 *
	 * @return the tiene solo titular
	 */
	public Boolean getTieneSoloTitular() {
		return !this.getTieneAlgunAdicional();
	}
}
