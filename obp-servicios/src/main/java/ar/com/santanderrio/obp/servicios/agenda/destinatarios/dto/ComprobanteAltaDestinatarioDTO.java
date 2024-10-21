/**
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioOutEntity;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.comprobante.entities.BasicComprobante;

/**
 * The Class ComprobanteAltaDestinatarioDTO.
 *
 * @author florencia.n.martinez
 */
public class ComprobanteAltaDestinatarioDTO extends BasicComprobante {

	/** The tiene error destinatario agendado. */
	private Boolean tieneErrorDestinatarioAgendado = Boolean.FALSE;

	/** The tiene error cuenta invalida. */
	private Boolean tieneErrorCuentaInvalida = Boolean.FALSE;

	/** The tiene error. */
	private Boolean tieneError = Boolean.FALSE;

	/** The tiene error cuenta inexistente santander. */
	private Boolean tieneErrorCuentaInexistenteSantander = Boolean.FALSE;

	/**
	 * Instantiates a new comprobante alta destinatario DTO.
	 *
	 * @param outEntity
	 *            the out entity
	 */
	public ComprobanteAltaDestinatarioDTO(AgendaDestinatarioOutEntity outEntity) {
		setFecha(obtenerFecha(outEntity));
		setHora(obtenerHora(outEntity));
		setNroComprobante(obtenerNroComprobante(outEntity));
	}

	/**
	 * Instantiates a new comprobante alta destinatario DTO.
	 */
	public ComprobanteAltaDestinatarioDTO() {
		super();
	}

	/**
	 * Obtiene la fecha.
	 *
	 * @param outEntity
	 *            the out entity
	 * @return tshe date
	 */
	private Date obtenerFecha(AgendaDestinatarioOutEntity outEntity) {
		if (outEntity.getFecha() != null) {
			return ISBANStringUtils.formatearFecha(ISBANStringUtils.formatearFechaSinHora(outEntity.getFecha()));
		}
		return null;
	}

	/**
	 * Obtiene el numero de comprobante.
	 *
	 * @param outEntity
	 *            the out entity
	 * @return the string
	 */
	private String obtenerNroComprobante(AgendaDestinatarioOutEntity outEntity) {
		if (outEntity.getNroComprobante() != null) {
			return outEntity.getNroComprobante();
		}
		return null;
	}

	/**
	 * Obtiene la hora.
	 *
	 * @param outEntity
	 *            the out entity
	 * @return the string
	 */
	private String obtenerHora(AgendaDestinatarioOutEntity outEntity) {
		if (outEntity.getHora() != null) {
			return outEntity.getHora();
		}
		return null;
	}

	/**
	 * Gets the tiene error destinatario agendado.
	 *
	 * @return the tieneErrorDestinatarioAgendado
	 */
	public Boolean getTieneErrorDestinatarioAgendado() {
		return tieneErrorDestinatarioAgendado;
	}

	/**
	 * Sets the tiene error destinatario agendado.
	 *
	 * @param tieneErrorDestinatarioAgendado
	 *            the tieneErrorDestinatarioAgendado to set
	 */
	public void setTieneErrorDestinatarioAgendado(Boolean tieneErrorDestinatarioAgendado) {
		this.tieneErrorDestinatarioAgendado = tieneErrorDestinatarioAgendado;
	}

	/**
	 * Gets the tiene error cuenta invalida.
	 *
	 * @return the tieneErrorCuentaInvalida
	 */
	public Boolean getTieneErrorCuentaInvalida() {
		return tieneErrorCuentaInvalida;
	}

	/**
	 * Sets the tiene error cuenta invalida.
	 *
	 * @param tieneErrorCuentaInvalida
	 *            the tieneErrorCuentaInvalida to set
	 */
	public void setTieneErrorCuentaInvalida(Boolean tieneErrorCuentaInvalida) {
		this.tieneErrorCuentaInvalida = tieneErrorCuentaInvalida;
	}

	/**
	 * Gets the tiene error.
	 *
	 * @return the tieneError
	 */
	public Boolean getTieneError() {
		return tieneError;
	}

	/**
	 * Sets the tiene error.
	 *
	 * @param tieneError
	 *            the tieneError to set
	 */
	public void setTieneError(Boolean tieneError) {
		this.tieneError = tieneError;
	}

	/**
	 * Gets the tiene error cuenta inexistente santander.
	 *
	 * @return the tiene error cuenta inexistente santander
	 */
	public Boolean getTieneErrorCuentaInexistenteSantander() {
		return tieneErrorCuentaInexistenteSantander;
	}

	/**
	 * Sets the tiene error cuenta inexistente santander.
	 *
	 * @param tieneErrorCuentaInexistenteSantander
	 *            the new tiene error cuenta inexistente santander
	 */
	public void setTieneErrorCuentaInexistenteSantander(Boolean tieneErrorCuentaInexistenteSantander) {
		this.tieneErrorCuentaInexistenteSantander = tieneErrorCuentaInexistenteSantander;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(getFecha());
		hcb.append(getHora());
		hcb.append(getMensajeEfectivizacion());
		hcb.append(getNroComprobante());
		hcb.append(tieneError);
		hcb.append(tieneErrorCuentaInvalida);
		hcb.append(tieneErrorDestinatarioAgendado);
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
		ComprobanteAltaDestinatarioDTO other = (ComprobanteAltaDestinatarioDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(getNroComprobante(), other.getNroComprobante());
		eb.append(getFecha(), other.getFecha());
		eb.append(getHora(), other.getHora());
		eb.append(getMensajeEfectivizacion(), other.getMensajeEfectivizacion());
		eb.append(tieneError, other.getTieneError());
		eb.append(tieneErrorCuentaInvalida, other.getTieneErrorCuentaInvalida());
		eb.append(tieneErrorDestinatarioAgendado, other.getTieneErrorDestinatarioAgendado());
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this).append("mensajeEfectivizacion", getMensajeEfectivizacion())
				.append("fecha", getFecha()).append("hora", getHora()).append("nroComprobante", getNroComprobante())
				.append("tieneError", tieneError).append("tieneErrorCuentaInvalida", tieneErrorCuentaInvalida)
				.append("tieneErrorDestinatarioAgendado", tieneErrorDestinatarioAgendado).toString();
	}

}