/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class ResumenTarjetaDTO.
 */
public class ResumenTarjetaDTO {

	/** The detalle. */
	private DetalleTarjetaDTO detalle;

	/** The detalles tarjetas. */
	private List<DetalleTarjetaDTO> detallesTarjetas;

	/** The error disponibles. */
	private Boolean errorDisponibles;

	/** The error consumos. */
	private Boolean errorConsumos;

	/** The codigo error. */
	private String codigoError;

	/** The Limites Y disponibles DTO. */
	private LimitesYDisponiblesDTO limitesYDisponiblesDTO;

	/**
	 * Instantiates a new resumen tarjeta DTO.
	 *
	 * @param detalle
	 *            the detalle
	 * @param errorDisponibles
	 *            the error disponibles
	 * @param errorConsumos
	 *            the error consumos
	 * @param codigoError
	 *            the codigo error
	 * @param limitesYDisponiblesDTO
	 *            the limites Y disponibles DTO
	 */
	public ResumenTarjetaDTO(DetalleTarjetaDTO detalle, Boolean errorDisponibles, Boolean errorConsumos,
			String codigoError, LimitesYDisponiblesDTO limitesYDisponiblesDTO) {
		super();
		this.detalle = detalle;
		this.errorDisponibles = errorDisponibles;
		this.errorConsumos = errorConsumos;
		this.codigoError = codigoError;
		this.setLimitesYDisponiblesDTO(limitesYDisponiblesDTO);
	}

	/**
	 * Instantiates a new resumen tarjeta DTO.
	 */
	public ResumenTarjetaDTO() {
		super();
		detallesTarjetas = new ArrayList<DetalleTarjetaDTO>();
	}

	/**
	 * Gets the codigo error.
	 *
	 * @return the codigo error
	 */
	public String getCodigoError() {
		return codigoError;
	}

	/**
	 * Sets the codigo error.
	 *
	 * @param codigoError
	 *            the new codigo error
	 */
	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	/**
	 * Gets the detalle.
	 *
	 * @return the detalle
	 */
	public DetalleTarjetaDTO getDetalle() {
		return detalle;
	}

	/**
	 * Gets the error disponibles.
	 *
	 * @return the error disponibles
	 */
	public Boolean getErrorDisponibles() {
		return errorDisponibles;
	}

	/**
	 * Sets the error disponibles.
	 *
	 * @param errorDisponibles
	 *            the new error disponibles
	 */
	public void setErrorDisponibles(Boolean errorDisponibles) {
		this.errorDisponibles = errorDisponibles;
	}

	/**
	 * Gets the error consumos.
	 *
	 * @return the error consumos
	 */
	public Boolean getErrorConsumos() {
		return errorConsumos;
	}

	/**
	 * Sets the error consumos.
	 *
	 * @param errorConsumos
	 *            the new error consumos
	 */
	public void setErrorConsumos(Boolean errorConsumos) {
		this.errorConsumos = errorConsumos;
	}

	/**
	 * Sets the detalle.
	 *
	 * @param detalle
	 *            the new detalle
	 */
	public void setDetalle(DetalleTarjetaDTO detalle) {
		this.detalle = detalle;
	}

	/**
	 * Gets the detalles tarjetas.
	 *
	 * @return the detalles tarjetas
	 */
	public List<DetalleTarjetaDTO> getDetallesTarjetas() {
		return detallesTarjetas;
	}

	/**
	 * Sets the detalles tarjetas.
	 *
	 * @param detallesTarjetas
	 *            the new detalles tarjetas
	 */
	public void setDetallesTarjetas(List<DetalleTarjetaDTO> detallesTarjetas) {
		this.detallesTarjetas = detallesTarjetas;
	}

	/**
	 * Gets the limites Y disponibles DTO.
	 *
	 * @return the limites Y disponibles DTO
	 */
	public LimitesYDisponiblesDTO getLimitesYDisponiblesDTO() {
		return limitesYDisponiblesDTO;
	}

	/**
	 * Sets the limites Y disponibles DTO.
	 *
	 * @param limitesYDisponiblesDTO
	 *            the new limites Y disponibles DTO
	 */
	public void setLimitesYDisponiblesDTO(LimitesYDisponiblesDTO limitesYDisponiblesDTO) {
		this.limitesYDisponiblesDTO = limitesYDisponiblesDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ResumenTarjetaDTO [detalle=" + detalle + ", detallesTarjetas=" + detallesTarjetas
				+ ", errorDisponibles=" + errorDisponibles + ", errorConsumos=" + errorConsumos + ", codigoError="
				+ codigoError + ", limitesYDisponiblesDTO=" + limitesYDisponiblesDTO + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(codigoError);
		hcb.append(detalle);
		hcb.append(detallesTarjetas);
		hcb.append(errorConsumos);
		hcb.append(errorDisponibles);
		hcb.append(limitesYDisponiblesDTO);
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
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
		ResumenTarjetaDTO other = (ResumenTarjetaDTO) obj;
		return new EqualsBuilder().append(codigoError, other.codigoError).append(detalle, other.detalle)
				.append(detallesTarjetas, other.detallesTarjetas).append(errorConsumos, other.errorConsumos)
				.append(errorDisponibles, other.errorDisponibles)
				.append(limitesYDisponiblesDTO, other.limitesYDisponiblesDTO).isEquals();
	}

}
