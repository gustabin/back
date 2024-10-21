/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.dto;

import java.math.BigDecimal;

/**
 * The Class IndiceDTO.
 */
public class IndiceDTO {

	/** The nombre. */
	private String nombre;
	
	/** The ultimo. */
	private BigDecimal ultimo;
	
	/** The valor. */
	private BigDecimal valor;

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the ultimo.
	 *
	 * @return the ultimo
	 */
	public BigDecimal getUltimo() {
		return ultimo;
	}

	/**
	 * Sets the ultimo.
	 *
	 * @param ultimo
	 *            the ultimo to set
	 */
	public void setUltimo(BigDecimal ultimo) {
		this.ultimo = ultimo;
	}

	/**
	 * Gets the valor.
	 *
	 * @return the valor
	 */
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * Sets the valor.
	 *
	 * @param valor
	 *            the valor to set
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
