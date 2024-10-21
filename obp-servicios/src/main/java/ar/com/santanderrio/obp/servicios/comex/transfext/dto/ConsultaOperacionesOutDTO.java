/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.dto;

import java.util.List;

/**
 * The Class ConsultaOperacionesInDTO.
 *
 * @author IT Resources
 */
public class ConsultaOperacionesOutDTO {
	
	/** The cantidad transferencias guardadas. */
	private Integer cantidadTransferenciasGuardadas;
	
	/** The cantidad transferencias pendientes. */
	private Integer cantidadTransferenciasPendientes;
	
	/** The cantidad transferencias rechazadas. */
	private Integer cantidadTransferenciasRechazadas;
	
	/** The operaciones. */
	private List<ConsultaOperacionesDTO> operaciones;

	/**
	 * Gets the cantidad transferencias guardadas.
	 *
	 * @return the cantidadTransferenciasGuardadas
	 */
	public Integer getCantidadTransferenciasGuardadas() {
		return cantidadTransferenciasGuardadas;
	}

	/**
	 * Sets the cantidad transferencias guardadas.
	 *
	 * @param cantidadTransferenciasGuardadas
	 *            the cantidadTransferenciasGuardadas to set
	 */
	public void setCantidadTransferenciasGuardadas(Integer cantidadTransferenciasGuardadas) {
		this.cantidadTransferenciasGuardadas = cantidadTransferenciasGuardadas;
	}

	/**
	 * Gets the cantidad transferencias pendientes.
	 *
	 * @return the cantidadTransferenciasPendientes
	 */
	public Integer getCantidadTransferenciasPendientes() {
		return cantidadTransferenciasPendientes;
	}

	/**
	 * Sets the cantidad transferencias pendientes.
	 *
	 * @param cantidadTransferenciasPendientes
	 *            the cantidadTransferenciasPendientes to set
	 */
	public void setCantidadTransferenciasPendientes(Integer cantidadTransferenciasPendientes) {
		this.cantidadTransferenciasPendientes = cantidadTransferenciasPendientes;
	}

	/**
	 * Gets the cantidad transferencias rechazadas.
	 *
	 * @return the cantidadTransferenciasRechazadas
	 */
	public Integer getCantidadTransferenciasRechazadas() {
		return cantidadTransferenciasRechazadas;
	}

	/**
	 * Sets the cantidad transferencias rechazadas.
	 *
	 * @param cantidadTransferenciasRechazadas
	 *            the cantidadTransferenciasRechazadas to set
	 */
	public void setCantidadTransferenciasRechazadas(Integer cantidadTransferenciasRechazadas) {
		this.cantidadTransferenciasRechazadas = cantidadTransferenciasRechazadas;
	}

	/**
	 * Gets the operaciones.
	 *
	 * @return the operaciones
	 */
	public List<ConsultaOperacionesDTO> getOperaciones() {
		return operaciones;
	}

	/**
	 * Sets the operaciones.
	 *
	 * @param operaciones
	 *            the operaciones to set
	 */
	public void setOperaciones(List<ConsultaOperacionesDTO> operaciones) {
		this.operaciones = operaciones;
	}
	
	

}
