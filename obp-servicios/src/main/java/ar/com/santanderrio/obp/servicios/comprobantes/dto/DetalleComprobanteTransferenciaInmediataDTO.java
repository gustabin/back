/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class DetalleComprobanteTransferenciaInmediataDTO.
 */
public class DetalleComprobanteTransferenciaInmediataDTO extends DetalleComprobanteTransferenciaDTO {

	/** The recordatorio. */
	private String recordatorio;

	/**
	 * Gets the recordatorio.
	 *
	 * @return the recordatorio
	 */
	public String getRecordatorio() {
		return recordatorio;
	}

	/**
	 * Sets the recordatorio.
	 *
	 * @param recordatorio
	 *            the new recordatorio
	 */
	public void setRecordatorio(String recordatorio) {
		this.recordatorio = recordatorio;
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
		if (StringUtils.defaultString(nroComprobante).equals("")) {
			hb.append("");
		} else {
			hb.append(nroComprobante);
		}
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
		if (DetalleComprobanteTransferenciaProgramadaOtrosBancosDTO.class.equals(a.getClass())
				|| DetalleComprobanteTransferenciaProgramadaDTO.class.equals(a.getClass())
				|| DetalleComprobanteTransferenciaProgramadaOtrosBancosDTO.class.equals(a.getClass())) {
			DetalleComprobanteTransferenciaProgramadaDTO detalleOB = (DetalleComprobanteTransferenciaProgramadaDTO) a;
			eb.append(Long.valueOf(nroComprobante), Long.valueOf(detalleOB.getNroComprobante()));
			return eb.isEquals();
		}

		if (getClass() != a.getClass()) {
			return false;
		}
		DetalleComprobanteTransferenciaInmediataDTO detalle = (DetalleComprobanteTransferenciaInmediataDTO) a;
		eb.append(Long.parseLong(nroComprobante), Long.parseLong(detalle.getNroComprobante()));
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
		sb.append("DetalleComprobanteTransferenciaInmediataDTO [recordatorio=");
		sb.append(recordatorio);
		sb.append(conseguirDatosDetalle());
		sb.append("]");
		return sb.toString();
	}

}
