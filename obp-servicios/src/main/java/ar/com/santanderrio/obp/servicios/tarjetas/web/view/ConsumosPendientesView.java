/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsumoTarjetaDTO;

/**
 * The Class ConsumosPendientesView.
 */
public class ConsumosPendientesView {

	/** The consumo pendientes. */
	private List<ConsumoPendienteView> consumosPendientes = new ArrayList<ConsumoPendienteView>();

	/** The cantidad consumos. */
	private String cantidadConsumos;

	/** The consumo pendiente. */
	private ConsumoPendienteView consumoPendiente;

	/** The estado respuesta consumos pendientes. */
	private EstadoRespuesta estadoRespuestaConsumosPendientes;

	/**
	 * Instantiates a new consumos pendientes view.
	 *
	 * @param consumosPendientes
	 *            the consumos pendientes
	 * @param estadoRespuesta
	 *            the estado respuesta
	 */
	public ConsumosPendientesView(List<ConsumoTarjetaDTO> consumosPendientes, EstadoRespuesta estadoRespuesta) {
		super();
		this.setConsumosPendientes(new ArrayList<ConsumoPendienteView>());
		if (consumosPendientes != null && !consumosPendientes.isEmpty()) {
			for (ConsumoTarjetaDTO dto : consumosPendientes) {
				ConsumoPendienteView consumoPendienteView = new ConsumoPendienteView(dto);
				this.getConsumosPendientes().add(consumoPendienteView);
			}
		}
		this.setEstadoRespuestaConsumosPendientes(estadoRespuesta);
	}

	/**
	 * Instantiates a new consumos pendientes view.
	 *
	 * @param consumosPendientes
	 *            the consumos pendientes
	 * @param cantidadConsumos
	 *            the cantidad consumos
	 * @param consumoPendiente
	 *            the consumo pendiente
	 */
	public ConsumosPendientesView(List<ConsumoTarjetaDTO> consumosPendientes, String cantidadConsumos,
			ConsumoPendienteView consumoPendiente) {
		super();
		List<ConsumoPendienteView> consumoPendienteViewList = new ArrayList<ConsumoPendienteView>();
		for (ConsumoTarjetaDTO dto : consumosPendientes) {
			ConsumoPendienteView consumoPendienteView = new ConsumoPendienteView(dto);
			consumoPendienteViewList.add(consumoPendienteView);
		}
		this.setConsumosPendientes(consumoPendienteViewList);
		this.cantidadConsumos = cantidadConsumos;
		this.consumoPendiente = consumoPendiente;
	}

	/**
	 * Instantiates a new consumos pendientes view.
	 */
	public ConsumosPendientesView() {
		super();
	}

	/**
	 * Gets the consumo pendientes.
	 *
	 * @return the consumo pendientes
	 */
	public List<ConsumoPendienteView> getConsumosPendientes() {
		return consumosPendientes;
	}

	/**
	 * Sets the consumo pendientes.
	 *
	 * @param consumosPendientes
	 *            the new consumo pendientes
	 */
	public void setConsumosPendientes(List<ConsumoPendienteView> consumosPendientes) {
		this.consumosPendientes = consumosPendientes;
	}

	/**
	 * Gets the cantidad consumos.
	 *
	 * @return the cantidad consumos
	 */
	public String getCantidadConsumos() {
		return cantidadConsumos;
	}

	/**
	 * Sets the cantidad consumos.
	 *
	 * @param cantidadConsumos
	 *            the new cantidad consumos
	 */
	public void setCantidadConsumos(String cantidadConsumos) {
		this.cantidadConsumos = cantidadConsumos;
	}

	/**
	 * Gets the consumo pendiente.
	 *
	 * @return the consumo pendiente
	 */
	public ConsumoPendienteView getConsumoPendiente() {
		return consumoPendiente;
	}

	/**
	 * Sets the consumo pendiente.
	 *
	 * @param consumoPendiente
	 *            the new consumo pendiente
	 */
	public void setConsumoPendiente(ConsumoPendienteView consumoPendiente) {
		this.consumoPendiente = consumoPendiente;
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "ConsumosPendientesView [consumosPendientes=" + consumosPendientes + ", cantidadConsumos="
				+ cantidadConsumos + ", consumoPendiente=" + consumoPendiente + "]";
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(cantidadConsumos);
		hcb.append(consumoPendiente);
		return hcb.toHashCode();
	}

	/**
	 * Equals.
	 *
	 * @param obj
	 *            the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ConsumosPendientesView other = (ConsumosPendientesView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(cantidadConsumos, other.cantidadConsumos);
		eb.append(consumoPendiente, other.consumoPendiente);
		return eb.isEquals();
	}

	/**
	 * Gets the estado respuesta consumos pendientes.
	 *
	 * @return the estado respuesta consumos pendientes
	 */
	public EstadoRespuesta getEstadoRespuestaConsumosPendientes() {
		return estadoRespuestaConsumosPendientes;
	}

	/**
	 * Sets the estado respuesta consumos pendientes.
	 *
	 * @param estadoRespuestaConsumosPendientes
	 *            the new estado respuesta consumos pendientes
	 */
	public void setEstadoRespuestaConsumosPendientes(EstadoRespuesta estadoRespuestaConsumosPendientes) {
		this.estadoRespuestaConsumosPendientes = estadoRespuestaConsumosPendientes;
	}

}