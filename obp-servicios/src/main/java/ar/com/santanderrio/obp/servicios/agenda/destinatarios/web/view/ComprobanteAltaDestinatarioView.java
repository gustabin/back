/**
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ComprobanteAltaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ComprobanteAltaCBUDTO;

/**
 * The Class ComprobanteAltaDestinatarioView.
 *
 */
public class ComprobanteAltaDestinatarioView extends AliasRsaView {

	/** The serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The mensaje efectivizacion. */
	private String mensajeEfectivizacion;

	/** The nroComprobante. */
	private String nroComprobante;

	/** The fecha. */
	private String fecha;

	/** The hora. */
	private String hora;

	/** The alias anterior. */
	private String aliasAnterior;

	/**
	 * Instantiates a new comprobante alta destinatario view.
	 */
	public ComprobanteAltaDestinatarioView() {
		super(OperacionesRSAEnum.GESTION_ALIAS);
	}

	/**
	 * Instantiates a new comprobante alta destinatario view.
	 *
	 * @param dto
	 *            the dto
	 */
	public ComprobanteAltaDestinatarioView(ComprobanteAltaDestinatarioDTO dto) {
		super(OperacionesRSAEnum.GESTION_ALIAS);
		this.setMensajeEfectivizacion(dto.getMensajeEfectivizacion());
		this.setNroComprobante(dto.getNroComprobante());
		this.setFecha(dto.getFecha());
		this.setHora(dto.getHora());
	}

	/**
	 * Instantiates a new comprobante alta destinatario view.
	 *
	 * @param dto
	 *            the dto
	 */
	public ComprobanteAltaDestinatarioView(ComprobanteAltaCBUDTO dto) {
		super(OperacionesRSAEnum.GESTION_ALIAS);
		this.setMensajeEfectivizacion(dto.getMensajeEfectivizacion());
		this.setNroComprobante(dto.getNroComprobante());
		this.setFecha(dto.getFecha());
		this.setHora(dto.getHora());
	}

	/**
	 * Gets the mensaje efectivizacion.
	 *
	 * @return the mensajeEfectivizacion
	 */
	public String getMensajeEfectivizacion() {
		return mensajeEfectivizacion;
	}

	/**
	 * Sets the mensaje efectivizacion.
	 *
	 * @param mensajeEfectivizacion
	 *            the mensajeEfectivizacion to set
	 */
	public void setMensajeEfectivizacion(String mensajeEfectivizacion) {
		this.mensajeEfectivizacion = mensajeEfectivizacion;
	}

	/**
	 * Gets the nroComprobante.
	 *
	 * @return the nroComprobante
	 */
	public String getNroComprobante() {
		return nroComprobante;
	}

	/**
	 * Sets the nroComprobante.
	 *
	 * @param comprobante
	 *            the new nro comprobante
	 */
	public void setNroComprobante(String comprobante) {
		this.nroComprobante = comprobante;
	}

	/**
	 * d Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(Date fecha) {
		if (fecha != null) {
			this.fecha = ISBANStringUtils.formatearFecha(fecha);
		} else {
			this.fecha = ISBANStringUtils.GUION_STRING;
		}

	}

	/**
	 * Gets the hora.
	 *
	 * @return the hora
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * Sets the hora.
	 *
	 * @param hora
	 *            the hora to set
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(nroComprobante);
		hcb.append(fecha);
		hcb.append(hora);
		hcb.append(mensajeEfectivizacion);
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
		ComprobanteAltaDestinatarioView other = (ComprobanteAltaDestinatarioView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(nroComprobante, other.getNroComprobante());
		eb.append(fecha, other.getFecha());
		eb.append(hora, other.getHora());
		eb.append(mensajeEfectivizacion, other.getMensajeEfectivizacion());
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
		return new ToStringBuilder(this).append("mensajeEfectivizacion", mensajeEfectivizacion)
				.append("nroComprobante", nroComprobante).append("fecha", fecha).append("hora", hora).toString();
	}

	/**
	 * Gets the alias anterior.
	 *
	 * @return the aliasAnterior
	 */
	public String getAliasAnterior() {
		return aliasAnterior;
	}

	/**
	 * Sets the alias anterior.
	 *
	 * @param aliasAnterior
	 *            the aliasAnterior to set
	 */
	public void setAliasAnterior(String aliasAnterior) {
		this.aliasAnterior = aliasAnterior;
	}

}