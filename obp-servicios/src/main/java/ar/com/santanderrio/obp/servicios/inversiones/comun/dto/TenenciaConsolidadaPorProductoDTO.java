/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaPorProductoDTO;

/**
 * The Class TenenciaConsolidadaPorProductoDTO.
 *
 * @author juan.pablo.picate
 */
public class TenenciaConsolidadaPorProductoDTO {

	/** The lista tenencia. */
	List<TenenciaPorProductoDTO> listaTenencia = new ArrayList<TenenciaPorProductoDTO>();

	/** The total tenencia valuada costo pesos. */
	private BigDecimal totalTenenciaValuadaCostoPesos = null;

	/** The total tenencia valuada costo dolares. */
	private BigDecimal totalTenenciaValuadaCostoDolares = null;

	/** The total tenencia valuada hoy pesos. */
	private BigDecimal totalTenenciaValuadaHoyPesos = BigDecimal.valueOf(0);

	/** The total tenencia valuada hoy dolares. */
	private BigDecimal totalTenenciaValuadaHoyDolares = BigDecimal.valueOf(0);

	/** The resultado pesos. */
	private BigDecimal resultadoPesos = null;

	/** The resultado dolares. */
	private BigDecimal resultadoDolares = null;

	/** The tenencia reexpresada pesos. */
	private BigDecimal tenenciaReexpresadaPesos = BigDecimal.valueOf(0);

	/** The tenencia reexpresada dolares. */
	private BigDecimal tenenciaReexpresadaDolares = BigDecimal.valueOf(0);

	/** The total tenencia reexpresada pesos. */
	private BigDecimal totalTenenciaReexpresadaPesos = BigDecimal.valueOf(0);

	/** The total tenencia reexpresada dolares. */
	private BigDecimal totalTenenciaReexpresadaDolares = BigDecimal.valueOf(0);
//
//	/** The mensaje warning. */
//	private String mensajeWarning;

	/** The resultado ok pesos. */
	private boolean resultadoOkPesos;

	/** The resultado ok dolares. */
	private boolean resultadoOkDolares;

	/**
	 * Gets the lista tenencia.
	 *
	 * @return the listaTenencia
	 */
	public List<TenenciaPorProductoDTO> getListaTenencia() {
		return listaTenencia;
	}

	/**
	 * Sets the lista tenencia.
	 *
	 * @param listaTenencia
	 *            the listaTenencia to set
	 */
	public void setListaTenencia(List<TenenciaPorProductoDTO> listaTenencia) {
		this.listaTenencia = listaTenencia;
	}

	/**
	 * Gets the total tenencia valuada costo pesos.
	 *
	 * @return the totalTenenciaValuadaCostoPesos
	 */
	public BigDecimal getTotalTenenciaValuadaCostoPesos() {
		return totalTenenciaValuadaCostoPesos;
	}

	/**
	 * Sets the total tenencia valuada costo pesos.
	 *
	 * @param totalTenenciaValuadaCostoPesos
	 *            the totalTenenciaValuadaCostoPesos to set
	 */
	public void setTotalTenenciaValuadaCostoPesos(BigDecimal totalTenenciaValuadaCostoPesos) {
		this.totalTenenciaValuadaCostoPesos = totalTenenciaValuadaCostoPesos;
	}

	/**
	 * Gets the total tenencia valuada costo dolares.
	 *
	 * @return the totalTenenciaValuada
	 */
	public BigDecimal getTotalTenenciaValuadaCostoDolares() {
		return totalTenenciaValuadaCostoDolares;
	}

	/**
	 * Sets the total tenencia valuada costo dolares.
	 *
	 * @param totalTenenciaValuadaCostoDolares
	 *            the new total tenencia valuada costo dolares
	 */
	public void setTotalTenenciaValuadaCostoDolares(BigDecimal totalTenenciaValuadaCostoDolares) {
		this.totalTenenciaValuadaCostoDolares = totalTenenciaValuadaCostoDolares;
	}

	/**
	 * Gets the total tenencia valuada hoy pesos.
	 *
	 * @return the totalTenenciaValuadaHoyPesos
	 */
	public BigDecimal getTotalTenenciaValuadaHoyPesos() {
		return totalTenenciaValuadaHoyPesos;
	}

	/**
	 * Sets the total tenencia valuada hoy pesos.
	 *
	 * @param totalTenenciaValuadaHoyPesos
	 *            the totalTenenciaValuadaHoyPesos to set
	 */
	public void setTotalTenenciaValuadaHoyPesos(BigDecimal totalTenenciaValuadaHoyPesos) {
		this.totalTenenciaValuadaHoyPesos = totalTenenciaValuadaHoyPesos;
	}

	/**
	 * Gets the total tenencia valuada hoy dolares.
	 *
	 * @return the totalTenenciaValuadaHoyDolares
	 */
	public BigDecimal getTotalTenenciaValuadaHoyDolares() {
		return totalTenenciaValuadaHoyDolares;
	}

	/**
	 * Sets the total tenencia valuada hoy dolares.
	 *
	 * @param totalTenenciaValuadaHoyDolares
	 *            the totalTenenciaValuadaHoyDolares to set
	 */
	public void setTotalTenenciaValuadaHoyDolares(BigDecimal totalTenenciaValuadaHoyDolares) {
		this.totalTenenciaValuadaHoyDolares = totalTenenciaValuadaHoyDolares;
	}

	/**
	 * Gets the resultado pesos.
	 *
	 * @return the resultadoPesos
	 */
	public BigDecimal getResultadoPesos() {
		return resultadoPesos;
	}

	/**
	 * Sets the resultado pesos.
	 *
	 * @param resultadoPesos
	 *            the resultadoPesos to set
	 */
	public void setResultadoPesos(BigDecimal resultadoPesos) {
		this.resultadoPesos = resultadoPesos;
	}

	/**
	 * Gets the resultado dolares.
	 *
	 * @return the resultadoDolares
	 */
	public BigDecimal getResultadoDolares() {
		return resultadoDolares;
	}

	/**
	 * Sets the resultado dolares.
	 *
	 * @param resultadoDolares
	 *            the resultadoDolares to set
	 */
	public void setResultadoDolares(BigDecimal resultadoDolares) {
		this.resultadoDolares = resultadoDolares;
	}

	/**
	 * Gets the tenencia reexpresada pesos.
	 *
	 * @return the tenenciaReexpresadaPesos
	 */
	public BigDecimal getTenenciaReexpresadaPesos() {
		return tenenciaReexpresadaPesos;
	}

	/**
	 * Sets the tenencia reexpresada pesos.
	 *
	 * @param tenenciaReexpresadaPesos
	 *            the tenenciaReexpresadaPesos to set
	 */
	public void setTenenciaReexpresadaPesos(BigDecimal tenenciaReexpresadaPesos) {
		this.tenenciaReexpresadaPesos = tenenciaReexpresadaPesos;
	}

	/**
	 * Gets the tenencia reexpresada dolares.
	 *
	 * @return the tenenciaReexpresadaDolares
	 */
	public BigDecimal getTenenciaReexpresadaDolares() {
		return tenenciaReexpresadaDolares;
	}

	/**
	 * Gets the total tenencia reexpresada pesos.
	 *
	 * @return the totalTenenciaReexpresadaPesos
	 */
	public BigDecimal getTotalTenenciaReexpresadaPesos() {
		return totalTenenciaReexpresadaPesos;
	}

	/**
	 * Sets the total tenencia reexpresada pesos.
	 *
	 * @param totalTenenciaReexpresadaPesos
	 *            the totalTenenciaReexpresadaPesos to set
	 */
	public void setTotalTenenciaReexpresadaPesos(BigDecimal totalTenenciaReexpresadaPesos) {
		this.totalTenenciaReexpresadaPesos = totalTenenciaReexpresadaPesos;
	}

	/**
	 * Gets the total tenencia reexpresada dolares.
	 *
	 * @return the totalTenenciaReexpresadaDolares
	 */
	public BigDecimal getTotalTenenciaReexpresadaDolares() {
		return totalTenenciaReexpresadaDolares;
	}

	/**
	 * Sets the total tenencia reexpresada dolares.
	 *
	 * @param totalTenenciaReexpresadaDolares
	 *            the totalTenenciaReexpresadaDolares to set
	 */
	public void setTotalTenenciaReexpresadaDolares(BigDecimal totalTenenciaReexpresadaDolares) {
		this.totalTenenciaReexpresadaDolares = totalTenenciaReexpresadaDolares;
	}

	/**
	 * Sets the tenencia reexpresada dolares.
	 *
	 * @param tenenciaReexpresadaDolares
	 *            the tenenciaReexpresadaDolares to set
	 */
	public void setTenenciaReexpresadaDolares(BigDecimal tenenciaReexpresadaDolares) {
		this.tenenciaReexpresadaDolares = tenenciaReexpresadaDolares;

	}

	/**
	 * Checks if is resultado ok pesos.
	 *
	 * @return the resultadoOkPesos
	 */
	public boolean isResultadoOkPesos() {
		return resultadoOkPesos;
	}

	/**
	 * Sets the resultado ok pesos.
	 *
	 * @param resultadoOkPesos
	 *            the resultadoOkPesos to set
	 */
	public void setResultadoOkPesos(boolean resultadoOkPesos) {
		this.resultadoOkPesos = resultadoOkPesos;
	}

	/**
	 * Checks if is resultado ok dolares.
	 *
	 * @return the resultadoOkDolares
	 */
	public boolean isResultadoOkDolares() {
		return resultadoOkDolares;
	}

	/**
	 * Sets the resultado ok dolares.
	 *
	 * @param resultadoOkDolares
	 *            the resultadoOkDolares to set
	 */
	public void setResultadoOkDolares(boolean resultadoOkDolares) {
		this.resultadoOkDolares = resultadoOkDolares;
	}

	/**
	 * Adds the total tenencia valuada costo pesos.
	 * Si valor es null, no lo sumo. Si todas las veces que me invocaron nunca sume,
	 * el total queda en null.
	 * @param valor
	 *            the valor
	 */
	public void addTotalTenenciaValuadaCostoPesos(BigDecimal valor) {
		if(valor != null){
			if(this.totalTenenciaValuadaCostoPesos == null){
				this.totalTenenciaValuadaCostoPesos = BigDecimal.valueOf(0);
			}
			this.totalTenenciaValuadaCostoPesos = this.totalTenenciaValuadaCostoPesos.add(valor);
		}
	}

	/**
	 * Adds the total tenencia valuada costo dolares.
	 *
	 * @param valor
	 *            the valor
	 */
	public void addTotalTenenciaValuadaCostoDolares(BigDecimal valor) {
		if(valor != null){
			if(this.totalTenenciaValuadaCostoDolares == null){
				this.totalTenenciaValuadaCostoDolares = BigDecimal.valueOf(0);
			}
			this.totalTenenciaValuadaCostoDolares = this.totalTenenciaValuadaCostoDolares.add(valor);
		}
	}

	/**
	 * Adds the total tenencia valuada hoy pesos.
	 *
	 * @param valor
	 *            the valor
	 */
	public void addTotalTenenciaValuadaHoyPesos(BigDecimal valor) {
		this.totalTenenciaValuadaHoyPesos = this.totalTenenciaValuadaHoyPesos.add(valor);
	}

	/**
	 * Adds the total tenencia valuada hoy dolares.
	 *
	 * @param valor
	 *            the valor
	 */
	public void addTotalTenenciaValuadaHoyDolares(BigDecimal valor) {
		this.totalTenenciaValuadaHoyDolares = this.totalTenenciaValuadaHoyDolares.add(valor);
	}

	/**
	 * Adds the resultado pesos.
	 *
	 * @param valor
	 *            the valor
	 */
	public void addResultadoPesos(BigDecimal valor) {
		if(valor != null){
			if(this.resultadoPesos == null){
				this.resultadoPesos = BigDecimal.valueOf(0);
			}
			this.resultadoPesos = this.resultadoPesos.add(valor);
		}
	}

	/**
	 * Adds the resultado dolares.
	 *
	 * @param valor
	 *            the valor
	 */
	public void addResultadoDolares(BigDecimal valor) {
		if(valor != null){
			if(this.resultadoDolares == null){
				this.resultadoDolares = BigDecimal.valueOf(0);
			}
			this.resultadoDolares = this.resultadoDolares.add(valor);
		}
	}

	/**
	 * Adds the tenencia reexpresada pesos.
	 *
	 * @param valor
	 *            the valor
	 */
	public void addTenenciaReexpresadaPesos(BigDecimal valor) {
		this.tenenciaReexpresadaPesos = this.tenenciaReexpresadaPesos.add(valor);
	}

	/**
	 * Adds the tenencia reexpresada dolares.
	 *
	 * @param valor
	 *            the valor
	 */
	public void addTenenciaReexpresadaDolares(BigDecimal valor) {
		this.tenenciaReexpresadaDolares = this.tenenciaReexpresadaDolares.add(valor);
	}

	/**
	 * Suma total tenencia reexpresada pesos.
	 *
	 * @param valor1
	 *            the valor 1
	 * @param valor2
	 *            the valor 2
	 */
	public void sumaTotalTenenciaReexpresadaPesos(BigDecimal valor1, BigDecimal valor2) {
		this.totalTenenciaReexpresadaPesos = valor1.add(valor2);
	}

	/**
	 * Suma total tenencia reexpresada dolares.
	 *
	 * @param valor1
	 *            the valor 1
	 * @param valor2
	 *            the valor 2
	 */
	public void sumaTotalTenenciaReexpresadaDolares(BigDecimal valor1, BigDecimal valor2) {
		this.totalTenenciaReexpresadaDolares = valor1.add(valor2);
	}

//	/**
//	 * Gets the mensaje warning.
//	 *
//	 * @return the mensajeWarning
//	 */
//	public String getMensajeWarning() {
//		return mensajeWarning;
//	}
//
//	/**
//	 * Sets the mensaje warning.
//	 *
//	 * @param mensajeWarning
//	 *            the mensajeWarning to set
//	 */
//	public void setMensajeWarning(String mensajeWarning) {
//		this.mensajeWarning = mensajeWarning;
//	}

}
