/**
 * 
 */
package ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.dto;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.entities.SolicitudAdhesionDebitoTarjetaEntity;

/**
 * The Class DebitoAutomaticoTarjetaDTO.
 *
 * @author florencia.n.martinez
 */
public class DebitoAutomaticoTarjetaDTO {

	/** The mensaje feedback. */
	private String mensajeFeedback;

	/** The comprobante. */
	private String comprobante;

	/** The fecha. */
	private Date fecha;

	/**
	 * Instantiates a new debito automatico tarjeta DTO.
	 */
	public DebitoAutomaticoTarjetaDTO() {
		super();
	}

	/**
	 * Instantiates a new debito automatico tarjeta DTO.
	 *
	 * @param solicitudAdhesion
	 *            the solicitud adhesion
	 * @param mensajeFeedback
	 *            the mensaje feedback
	 */
	public DebitoAutomaticoTarjetaDTO(SolicitudAdhesionDebitoTarjetaEntity solicitudAdhesion, String mensajeFeedback) {
		this.comprobante = String.valueOf(solicitudAdhesion.getIdSolicitud());
		this.fecha = new Date();
		this.mensajeFeedback = mensajeFeedback;
	}

	/**
	 * Gets the mensaje feedback.
	 *
	 * @return the mensajeFeedback
	 */
	public String getMensajeFeedback() {
		return mensajeFeedback;
	}

	/**
	 * Sets the mensaje feedback.
	 *
	 * @param mensajeFeedback
	 *            the mensajeFeedback to set
	 */
	public void setMensajeFeedback(String mensajeFeedback) {
		this.mensajeFeedback = mensajeFeedback;
	}

	/**
	 * Gets the comprobante.
	 *
	 * @return the comprobante
	 */
	public String getComprobante() {
		return comprobante;
	}

	/**
	 * Sets the comprobante.
	 *
	 * @param comprobante
	 *            the comprobante to set
	 */
	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(comprobante);
		hcb.append(fecha);
		hcb.append(mensajeFeedback);
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DebitoAutomaticoTarjetaDTO other = (DebitoAutomaticoTarjetaDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(comprobante, other.getComprobante());
		eb.append(fecha, other.getFecha());
		eb.append(mensajeFeedback, other.getMensajeFeedback());
		return eb.isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this).append("mensajeFeedback", mensajeFeedback).append("comprobante", comprobante)
				.append("fecha", fecha).toString();
	}

}
