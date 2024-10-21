/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import java.text.SimpleDateFormat;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteDebitoAutomaticoTarjetaView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

/**
 * The Class DetalleComprobanteDebitoAutomaticoTarjetaDTO.
 */
public class DetalleComprobanteDebitoAutomaticoTarjetaDTO extends DetalleComprobanteDTO {

	/** The descripcion. */
	private String descripcionDebito;

	/** The descripcion. */
	private String nombreTarjeta;

	/**
	 * Gets the descripcion debito.
	 *
	 * @return the descripcion Debito
	 */
	public String getDescripcionDebito() {
		return descripcionDebito;
	}

	/**
	 * Sets the descripcion debito.
	 *
	 * @param descripcionDebito
	 *            the new descripcion debito
	 */
	public void setDescripcionDebito(String descripcionDebito) {
		this.descripcionDebito = descripcionDebito;
	}

	/**
	 * Gets the nombre tarjeta.
	 *
	 * @return the nombreTarjeta
	 */
	public String getNombreTarjeta() {
		return nombreTarjeta;
	}

	/**
	 * Sets the nombre tarjeta.
	 *
	 * @param nombreTarjeta
	 *            the nombreTarjeta to set
	 */
	public void setNombreTarjeta(String nombreTarjeta) {
		this.nombreTarjeta = nombreTarjeta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteDTO#
	 * hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hb = new HashCodeBuilder();
		hb.append(descripcionDebito);
		hb.append(nombreTarjeta);
		return hb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteDTO#
	 * equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object a) {
		if (this == a) {
			return true;
		}

		if (a == null) {
			return false;
		}

		if (getClass() != a.getClass()) {
			return false;
		}
		DetalleComprobanteDebitoAutomaticoTarjetaDTO detalle = (DetalleComprobanteDebitoAutomaticoTarjetaDTO) a;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(descripcionDebito, detalle.getDescripcionDebito());
		eb.append(nombreTarjeta, detalle.getNombreTarjeta());
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteDTO#
	 * toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("DetalleComprobanteDebitoAutomaticoTarjetaDTO [descripcionDebito=");
		sb.append(descripcionDebito);
		sb.append(", nombreTarjeta= ");
		sb.append(nombreTarjeta);
		sb.append("]");
		return sb.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteDTO#
	 * getView(ar.com.santanderrio.obp.servicios.comprobantes.dto. ComprobanteDTO)
	 */
	@Override
	public DetalleComprobanteView getView(ComprobanteDTO comprobante) {
		DetalleComprobanteDebitoAutomaticoTarjetaView view = new DetalleComprobanteDebitoAutomaticoTarjetaView();
		setearImportes(view, comprobante);
		setearNumeroTipoYTitulo(view);
		view.setDescripcion(getDescripcionDebito());
		view.setEmpresa(comprobante.getDestinatario());
		SimpleDateFormat sdf = new SimpleDateFormat(ISBANStringUtils.FORMATO_FECHA);
		view.setFechaOperacion(sdf.format(comprobante.getFecha()));
		if (comprobante.getCtaMedioDePagoPesos() != null) {
			view.setMedioDePago(comprobante.getCtaMedioDePagoPesos());
		} else {
			view.setMedioDePago(comprobante.getCtaMedioDePagoDolares());
		}
		view.setTitular(getNombreTarjeta());
		return view;
	}

	@Override
	public String obtenerIdentificacionHistorial() {
		return null;
	}

}
