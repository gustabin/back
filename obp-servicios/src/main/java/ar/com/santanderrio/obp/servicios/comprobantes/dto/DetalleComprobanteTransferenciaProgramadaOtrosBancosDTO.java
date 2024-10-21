/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteTransferenciaProgramadaView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;

/**
 * The Class DetalleComprobanteTransferenciaProgramadaOtrosBancosDTO.
 */
public class DetalleComprobanteTransferenciaProgramadaOtrosBancosDTO
		extends DetalleComprobanteTransferenciaProgramadaDTO {

	/** The desc cuenta. */
	private String descCuenta;

	/** The caract cuenta. */
	private String caractCuenta;

	/**
	 * Gets the desc cuenta.
	 *
	 * @return the desc cuenta
	 */
	public String getDescCuenta() {
		return descCuenta;
	}

	/**
	 * Sets the desc cuenta.
	 *
	 * @param descCuenta
	 *            the new desc cuenta
	 */
	public void setDescCuenta(String descCuenta) {
		this.descCuenta = descCuenta;
	}

	/**
	 * Gets the caract cuenta.
	 *
	 * @return the caract cuenta
	 */
	public String getCaractCuenta() {
		return caractCuenta;
	}

	/**
	 * Sets the caract cuenta.
	 *
	 * @param caractCuenta
	 *            the new caract cuenta
	 */
	public void setCaractCuenta(String caractCuenta) {
		this.caractCuenta = caractCuenta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.dto.
	 * DetalleComprobanteTransferenciaProgramadaDTO#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hb = new HashCodeBuilder();
		hb.append(nroComprobante);
		return hb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.dto.
	 * DetalleComprobanteTransferenciaProgramadaDTO#equals(java.lang.Object)
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

		if (DetalleComprobanteTransferenciaInmediataOtrosBancosDTO.class.equals(a.getClass())) {
			DetalleComprobanteTransferenciaInmediataOtrosBancosDTO detalleOB = (DetalleComprobanteTransferenciaInmediataOtrosBancosDTO) a;
			eb.append(nroComprobante, detalleOB.getNroComprobante());
			return eb.isEquals();
		}

		if (getClass() != a.getClass()) {
			return false;
		}
		DetalleComprobanteTransferenciaProgramadaOtrosBancosDTO detalle = (DetalleComprobanteTransferenciaProgramadaOtrosBancosDTO) a;
		eb.append(nroComprobante, detalle.getNroComprobante());
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.dto.
	 * DetalleComprobanteTransferenciaProgramadaDTO#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("DetalleComprobanteTransferenciaProgramadaOtrosBancosDTO [descCuenta=");
		sb.append(descCuenta);
		sb.append(", caractCuenta= ");
		sb.append(caractCuenta);
		sb.append("]");
		return sb.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.dto.
	 * DetalleComprobanteTransferenciaProgramadaDTO#getView(ar.com.santanderrio.
	 * obp.servicios.comprobantes.dto.ComprobanteDTO)
	 */
	@Override
	public DetalleComprobanteView getView(ComprobanteDTO comprobante) {
		DetalleComprobanteTransferenciaProgramadaView view = new DetalleComprobanteTransferenciaProgramadaView();
		setearNumeroTipoYTitulo(view);
		setearImportes(view, comprobante);
		setearNumeroYTipoCuentaOrigen(view, comprobante);
		setearNumeroYTipoCuentaDestino(view, "CBU");
		view.setFecha(setearHora(comprobante.getFecha(), false));
		setearCuitYTipoCuit(view);
		setearConceptoYDesconcepto(view, getInformacionAdicional());
		setearCamposComunesTransferenciasProgramadas(view, comprobante.getDestinatario());
		return view;
	}
	
	@Override
    public String obtenerIdentificacionHistorial() {
    	return "CBU " + getNroCuentaDestino();
    }

}
