/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;

/**
 * The Class CanalTramoCtaTitulo.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class CanalTramoCtaTitulo {

	/** The cuenta titulo. */
	private long cuentaTitulo;

	/** The cantidad. */
	private double cantidad;

	/** The cantidad maxima. */
	private double cantidadMaxima;
	
	private List<Interviniente> intervinientes;

	/**
	 * Gets the cuenta titulo.
	 *
	 * @return the cuenta titulo
	 */
	public long getCuentaTitulo() {
		return cuentaTitulo;
	}

	/**
	 * Sets the cuenta titulo.
	 *
	 * @param cuentaTitulo
	 *            the new cuenta titulo
	 */
	public void setCuentaTitulo(long cuentaTitulo) {
		this.cuentaTitulo = cuentaTitulo;
	}

	/**
	 * Gets the cantidad.
	 *
	 * @return the cantidad
	 */
	public double getCantidad() {
		return cantidad;
	}

	/**
	 * Sets the cantidad.
	 *
	 * @param cantidad
	 *            the new cantidad
	 */
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * Gets the cantidad maxima.
	 *
	 * @return the cantidad maxima
	 */
	public double getCantidadMaxima() {
		return cantidadMaxima;
	}

	/**
	 * Sets the cantidad maxima.
	 *
	 * @param cantidadMaxima
	 *            the new cantidad maxima
	 */
	public void setCantidadMaxima(double cantidadMaxima) {
		this.cantidadMaxima = cantidadMaxima;
	}

	public List<Interviniente> getIntervinientes() {
		return intervinientes;
	}

	public void setIntervinientes(List<Interviniente> intervinientes) {
		this.intervinientes = intervinientes;
	}
	
}
