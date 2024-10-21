/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import org.apache.commons.lang.StringUtils;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteTransferenciaView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

/**
 * The Class DetalleComprobanteTransferenciaInmediataOtrosBancosDTO.
 */
public class DetalleComprobanteTransferenciaInmediataOtrosBancosDTO
		extends DetalleComprobanteTransferenciaInmediataDTO {

	private String aliasCBU;

	/**
	 * @return the aliasCBU
	 */
	public String getAliasCBU() {
		return aliasCBU;
	}

	/**
	 * @param aliasCBU
	 *            the aliasCBU to set
	 */
	public void setAliasCBU(String aliasCBU) {
		this.aliasCBU = aliasCBU;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.dto.
	 * DetalleComprobanteTransferenciaInmediataDTO#getView(ar.com.santanderrio.
	 * obp.servicios.comprobantes.dto.ComprobanteDTO)
	 */
	@Override
	public DetalleComprobanteView getView(ComprobanteDTO comprobante) {
		DetalleComprobanteTransferenciaView view = new DetalleComprobanteTransferenciaView();
		setearConceptoYDesconcepto(view, getInformacionAdicional());
		setearCuitYTipoCuit(view);
		view.setFecha(setearHora(comprobante.getFecha(), false));
		setearImportes(view, comprobante);
		setearNumeroTipoYTitulo(view);
		setearNumeroYTipoCuentaDestino(view, "CBU");
		setearNumeroYTipoCuentaOrigen(view, getNroCuentaOrigen(),
				ISBANStringUtils.formatearFraseInicialMayuscula(obtenerTipoConSinMoneda(getTipoCuentaOrigen(), false)));
		view.setBanco(getBanco());
		view.setDestinatario(comprobante.getDestinatario());
		view.setPlazoAcreditacion(getPlazoAcreditacion());
		view.setAvisoTransfMail(getAvisoTransferencia());
		view.setMail(getMail());
		view.setComentarios(getComentarios());
		view.setAliasCBU(StringUtils.isBlank(getAliasCBU()) ? null : getAliasCBU());
		return view;
	}

	@Override
	public String obtenerIdentificacionHistorial() {
		return "CBU " + getNroCuentaDestino();
	}

}
