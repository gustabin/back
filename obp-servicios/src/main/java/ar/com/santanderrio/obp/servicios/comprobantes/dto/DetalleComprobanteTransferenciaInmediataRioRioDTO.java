/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteTransferenciaView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

/**
 * The Class DetalleComprobanteTransferenciaInmediataRioRioDTO.
 */
public class DetalleComprobanteTransferenciaInmediataRioRioDTO extends DetalleComprobanteTransferenciaInmediataDTO {
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.dto.
	 * DetalleComprobanteTransferenciaDTO#getView(ar.com.santanderrio.obp.
	 * servicios.comprobantes.dto.ComprobanteDTO) // scompRio, scompRioTBanco
	 */
	@Override
	public DetalleComprobanteView getView(ComprobanteDTO comprobante) {
		DetalleComprobanteTransferenciaView view = new DetalleComprobanteTransferenciaView();
		setearConceptoYDesconcepto(view, getDesConcepto());
		view.setFecha(setearHora(comprobante.getFecha(), false));
		setearImportes(view, comprobante);
		setearNumeroTipoYTitulo(view);
		setearNumeroYTipoCuentaOrigen(view, getNroCuentaOrigen(),
				ISBANStringUtils.formatearFraseInicialMayuscula(obtenerTipoConSinMoneda(getTipoCuentaOrigen(), false)));
		setearNumeroYTipoCuentaDestino(view);
		view.setAvisoTransfMail(getAvisoTransferencia());
		view.setBanco(getBanco());
		view.setComentarios(getComentarios());
		view.setDestinatario(comprobante.getDestinatario());
		view.setMail(getMail());
		view.setPlazoAcreditacion(getPlazoAcreditacion());
		return view;
	}

}
