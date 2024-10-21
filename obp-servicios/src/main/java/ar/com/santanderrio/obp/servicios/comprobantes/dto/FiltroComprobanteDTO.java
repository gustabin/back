/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class FiltroComprobanteDTO.
 */
public class FiltroComprobanteDTO {

	/** The transacciones. */
	List<TransaccionDTO> transacciones = new ArrayList<TransaccionDTO>();

	/** The filtro activo. */
	private Boolean filtroActivo;

	/** The cliente. */
	private Cliente cliente;

	/** The excedido limite parcial. */
	private Boolean excedidoLimiteParcial;

	/** The fecha error. */
	private Boolean fechaError;

	/** The comprobantes excedidos. */
	private Boolean comprobantesExcedidos;

	/**
	 * Instantiates a new filtro comprobante DTO.
	 */
	public FiltroComprobanteDTO() {
		super();
		this.filtroActivo = false;
	}

	/**
	 * Instantiates a new filtro comprobante DTO.
	 * 
	 * @param cliente
	 *            the cliente
	 * @throws ParseException
	 *             the parse exception
	 */
	public FiltroComprobanteDTO(Cliente cliente) throws ParseException {
		super();
		this.cliente = cliente;
		this.filtroActivo = false;
	}

	/**
	 * Gets the cliente.
	 * 
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Sets the cliente.
	 * 
	 * @param cliente
	 *            the new cliente
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Gets the transacciones.
	 * 
	 * @return the transacciones
	 */
	public List<TransaccionDTO> getTransacciones() {
		return transacciones;
	}

	/**
	 * Sets the transacciones.
	 * 
	 * @param transacciones
	 *            the new transacciones
	 */
	public void setTransacciones(List<TransaccionDTO> transacciones) {
		this.transacciones = transacciones;
	}

	/**
	 * Gets the filtro activo.
	 * 
	 * @return the filtro activo
	 */
	public Boolean getFiltroActivo() {
		return filtroActivo;
	}

	/**
	 * Sets the filtro activo.
	 * 
	 * @param filtroActivo
	 *            the new filtro activo
	 */
	public void setFiltroActivo(Boolean filtroActivo) {
		this.filtroActivo = filtroActivo;
	}

	/**
	 * Gets the excedido limite parcial.
	 * 
	 * @return the excedido limite parcial
	 */
	public Boolean getExcedidoLimiteParcial() {
		return excedidoLimiteParcial;
	}

	/**
	 * Sets the excedido limite parcial.
	 * 
	 * @param excedidoLimiteParcial
	 *            the new excedido limite parcial
	 */
	public void setExcedidoLimiteParcial(Boolean excedidoLimiteParcial) {
		this.excedidoLimiteParcial = excedidoLimiteParcial;
	}

	/**
	 * Gets the fecha error.
	 * 
	 * @return the fecha error
	 */
	public Boolean getFechaError() {
		return fechaError;
	}

	/**
	 * Sets the fecha error.
	 * 
	 * @param fechaError
	 *            the new fecha error
	 */
	public void setFechaError(Boolean fechaError) {
		this.fechaError = fechaError;
	}

	/**
	 * Gets the comprobantes excedidos.
	 * 
	 * @return the comprobantes excedidos
	 */
	public Boolean getComprobantesExcedidos() {
		return comprobantesExcedidos;
	}

	/**
	 * Sets the comprobantes excedidos.
	 * 
	 * @param comprobantesExcedidos
	 *            the new comprobantes excedidos
	 */
	public void setComprobantesExcedidos(Boolean comprobantesExcedidos) {
		this.comprobantesExcedidos = comprobantesExcedidos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(transacciones);
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FiltroComprobanteDTO other = (FiltroComprobanteDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(transacciones, other.getTransacciones());
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(transacciones);
		sb.append(filtroActivo);
		return sb.toString();
	}

}
