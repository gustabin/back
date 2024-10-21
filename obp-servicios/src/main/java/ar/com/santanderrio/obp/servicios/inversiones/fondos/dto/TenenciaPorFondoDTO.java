/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class TenenciaPorFondoDTO.
 */
public class TenenciaPorFondoDTO {

	/** The tenencia fondos suscritos pesos. */
	private List<TenenciaFondosSuscritosDTO> tenenciaFondosSuscritosPesos = new ArrayList<TenenciaFondosSuscritosDTO>();

	/** The total resultado. */
	private BigDecimal totalResultadoPesos = new BigDecimal("0");

	/** The total valuacion. */
	private BigDecimal totalValuacionPesos = new BigDecimal("0");

	/** The tenencia fondos suscritos dolares. */
	private List<TenenciaFondosSuscritosDTO> tenenciaFondosSuscritosDolares = new ArrayList<TenenciaFondosSuscritosDTO>();

	/** The total resultado. */
	private BigDecimal totalResultadoDolares = new BigDecimal("0");

	/** The total valuacion. */
	private BigDecimal totalValuacionDolares = new BigDecimal("0");

	/**
	 * Gets the tenencia fondos suscritos pesos.
	 *
	 * @return the tenenciaFondosSuscritosPesos
	 */
	public List<TenenciaFondosSuscritosDTO> getTenenciaFondosSuscritosPesos() {
		return tenenciaFondosSuscritosPesos;
	}

	/**
	 * Sets the tenencia fondos suscritos pesos.
	 *
	 * @param tenenciaFondosSuscritosPesos
	 *            the tenenciaFondosSuscritosPesos to set
	 */
	public void setTenenciaFondosSuscritosPesos(List<TenenciaFondosSuscritosDTO> tenenciaFondosSuscritosPesos) {
		this.tenenciaFondosSuscritosPesos = tenenciaFondosSuscritosPesos;
	}

	/**
	 * Gets the tenencia fondos suscritos dolares.
	 *
	 * @return the tenenciaFondosSuscritosDolares
	 */
	public List<TenenciaFondosSuscritosDTO> getTenenciaFondosSuscritosDolares() {
		return tenenciaFondosSuscritosDolares;
	}

	/**
	 * Sets the tenencia fondos suscritos dolares.
	 *
	 * @param tenenciaFondosSuscritosDolares
	 *            the tenenciaFondosSuscritosDolares to set
	 */
	public void setTenenciaFondosSuscritosDolares(List<TenenciaFondosSuscritosDTO> tenenciaFondosSuscritosDolares) {
		this.tenenciaFondosSuscritosDolares = tenenciaFondosSuscritosDolares;
	}

	/**
	 * Checks if is empty.
	 *
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		return this.tenenciaFondosSuscritosDolares.isEmpty() && this.tenenciaFondosSuscritosPesos.isEmpty();
	}

	/**
	 * Gets the total valuacion pesos.
	 *
	 * @return the total valuacion pesos
	 */
	public BigDecimal getTotalValuacionPesos() {
		return totalValuacionPesos;
	}

	/**
	 * Sets the total valuacion pesos.
	 *
	 * @param totalValuacionPesos
	 *            the new total valuacion pesos
	 */
	public void setTotalValuacionPesos(BigDecimal totalValuacionPesos) {
		this.totalValuacionPesos = totalValuacionPesos;
	}

	/**
	 * Gets the total valuacion dolares.
	 *
	 * @return the total valuacion dolares
	 */
	public BigDecimal getTotalValuacionDolares() {
		return totalValuacionDolares;
	}

	/**
	 * Sets the total valuacion dolares.
	 *
	 * @param totalValuacionDolares
	 *            the new total valuacion dolares
	 */
	public void setTotalValuacionDolares(BigDecimal totalValuacionDolares) {
		this.totalValuacionDolares = totalValuacionDolares;
	}

	/**
	 * Gets the total resultado pesos.
	 *
	 * @return the total resultado pesos
	 */
	public BigDecimal getTotalResultadoPesos() {
		return totalResultadoPesos;
	}

	/**
	 * Sets the total resultado pesos.
	 *
	 * @param totalResultadoPesos
	 *            the new total resultado pesos
	 */
	public void setTotalResultadoPesos(BigDecimal totalResultadoPesos) {
		this.totalResultadoPesos = totalResultadoPesos;
	}

	/**
	 * Gets the total resultado dolares.
	 *
	 * @return the total resultado dolares
	 */
	public BigDecimal getTotalResultadoDolares() {
		return totalResultadoDolares;
	}

	/**
	 * Sets the total resultado dolares.
	 *
	 * @param totalResultadoDolares
	 *            the new total resultado dolares
	 */
	public void setTotalResultadoDolares(BigDecimal totalResultadoDolares) {
		this.totalResultadoDolares = totalResultadoDolares;
	}

	/**
	 * Adds the resultado pesos.
	 *
	 * @param resultado
	 *            the resultado
	 */
	public void addResultadoPesos(BigDecimal resultado) {
		this.totalResultadoPesos = this.totalResultadoPesos.add(resultado);
	}

	/**
	 * Adds the valuacion pesos.
	 *
	 * @param valuacion
	 *            the valuacion
	 */
	public void addValuacionPesos(BigDecimal valuacion) {
		this.totalValuacionPesos = this.totalValuacionPesos.add(valuacion);
	}

	/**
	 * Adds the resultado dolares.
	 *
	 * @param resultado
	 *            the resultado
	 */
	public void addResultadoDolares(BigDecimal resultado) {
		this.totalResultadoDolares = this.totalResultadoDolares.add(resultado);
	}

	/**
	 * Adds the valuacion dolares.
	 *
	 * @param valuacion
	 *            the valuacion
	 */
	public void addValuacionDolares(BigDecimal valuacion) {
		this.totalValuacionDolares = this.totalValuacionDolares.add(valuacion);
	}

}
