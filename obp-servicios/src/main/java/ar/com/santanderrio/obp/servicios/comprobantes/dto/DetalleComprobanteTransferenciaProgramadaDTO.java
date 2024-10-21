/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteTransferenciaProgramadaView;

/**
 * The Class DetalleComprobanteTransferenciaProgramadaDTO.
 */
public class DetalleComprobanteTransferenciaProgramadaDTO extends DetalleComprobanteTransferenciaDTO {

	/** The estado. */
	private String estado;

	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado
	 *            the new estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.dto.
	 * DetalleComprobanteTransferenciaDTO#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hb = new HashCodeBuilder();
		hb.append(StringUtils.defaultString(nroComprobante));
		return hb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.dto.
	 * DetalleComprobanteTransferenciaDTO#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object a) {
		EqualsBuilder eb = new EqualsBuilder();
		if (this == a) {
			return true;
		}

		if (a == null) {
			return false;
		}
		if (DetalleComprobanteTransferenciaInmediataDTO.class.equals(a.getClass())
				|| DetalleComprobanteTransferenciaInmediataRioRioDTO.class.equals(a.getClass())
				|| DetalleComprobanteTransferenciaInmediataOtrosBancosDTO.class.equals(a.getClass())) {
			DetalleComprobanteTransferenciaDTO detalle = (DetalleComprobanteTransferenciaDTO) a;
			eb.append(nroComprobante, detalle.getNroComprobante());
			return eb.isEquals();
		}

		if (getClass() != a.getClass()) {
			return false;
		}
		DetalleComprobanteTransferenciaProgramadaDTO detalle = (DetalleComprobanteTransferenciaProgramadaDTO) a;
		eb.append(nroComprobante, detalle.getNroComprobante());
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.dto.
	 * DetalleComprobanteTransferenciaDTO#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("DetalleComprobanteTransferenciaProgramadaDTO [estado=");
		sb.append(estado);
		sb.append("]");
		return sb.toString();
	}

	/**
	 * Setear campos comunes transferencias programadas.
	 *
	 * @param view
	 *            the view
	 * @param destinatario
	 *            the destinatario
	 */
	public void setearCamposComunesTransferenciasProgramadas(DetalleComprobanteTransferenciaProgramadaView view,
			String destinatario) {
		view.setAvisoTransfMail(getAvisoTransferencia());
		view.setBanco(getBanco());
		view.setComentarios(getComentarios());
		view.setDestinatario(destinatario);
		view.setEstado(getEstado());
		view.setMail(getMail());
		view.setPlazoAcreditacion(getPlazoAcreditacion());
	}

}
