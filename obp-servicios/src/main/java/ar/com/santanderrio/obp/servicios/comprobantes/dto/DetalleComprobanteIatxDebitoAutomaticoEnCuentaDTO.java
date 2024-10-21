/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.EmpresaAdheridaEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteDebitoAutomaticoCuentaView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

/**
 * The Class DetalleComprobanteIatxDebitoAutomaticoEnCuentaDTO.
 */
public class DetalleComprobanteIatxDebitoAutomaticoEnCuentaDTO extends DetalleComprobanteDTO {

	/** The motivo rechazo. */
	private String motivoRechazo;

	/** The empresa. */
	private EmpresaAdheridaEntity empresa;

	/**
	 * Gets the motivo rechazo.
	 *
	 * @return the motivo rechazo
	 */
	public String getMotivoRechazo() {
		return motivoRechazo;
	}

	/**
	 * Sets the motivo rechazo.
	 *
	 * @param motivoRechazo
	 *            the new motivo rechazo
	 */
	public void setMotivoRechazo(String motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
	}

	/**
	 * @return the empresa
	 */
	public EmpresaAdheridaEntity getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa
	 *            the empresa to set
	 */
	public void setEmpresa(EmpresaAdheridaEntity empresa) {
		this.empresa = empresa;
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
		hb.append(motivoRechazo);
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
		DetalleComprobanteIatxDebitoAutomaticoEnCuentaDTO detalle = (DetalleComprobanteIatxDebitoAutomaticoEnCuentaDTO) a;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(motivoRechazo, detalle.getMotivoRechazo());
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
		sb.append("DetalleComprobanteIatxDebitoAutomaticoEnCuentaDTO [motivoRechazo=");
		sb.append(motivoRechazo);
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
		DetalleComprobanteDebitoAutomaticoCuentaView view = new DetalleComprobanteDebitoAutomaticoCuentaView();
		setearImportes(view, comprobante);
		setearNumeroTipoYTitulo(view);
		setearNumeroYTipoCuentaOrigen(view, getNroCuentaOrigen(),
				ISBANStringUtils.formatearFraseInicialMayuscula(obtenerTipoConSinMoneda(getTipoCuentaOrigen(), false)));
		view.setEmpresa(comprobante.getDestinatario());
		view.setIdentificacion(getIdentificacion());
		SimpleDateFormat sdf = new SimpleDateFormat(ISBANStringUtils.FORMATO_FECHA);
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		view.setFecha(sdf.format(comprobante.getFecha()));
		view.setFechaPago(sdf.format(getFechaDePago()));
		view.setMotivoRechazo(getMotivoRechazo());
		view.setEstado(
				comprobante.getTipoOperacion().equals(TipoOperacionComprobanteEnum.DEBITO_AUTOMATICO_CUENTA_RECHAZADO)
						? "Rechazado"
						: null);
		return view;
	}

}
