/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteTransferenciaProgramadaView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

/**
 * The Class DetalleComprobanteTransferenciaProgramadaPAGHABONDTO.
 */
public class DetalleComprobanteTransferenciaProgramadaPAGHABONDTO extends DetalleComprobanteTransferenciaProgramadaDTO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.dto.
	 * DetalleComprobanteTransferenciaDTO#getView(ar.com.santanderrio.obp.
	 * servicios.comprobantes.dto.ComprobanteDTO)
	 */
	@Override
	public DetalleComprobanteView getView(ComprobanteDTO comprobante) {
		DetalleComprobanteTransferenciaProgramadaView view = new DetalleComprobanteTransferenciaProgramadaView();
		setearNumeroTipoYTitulo(view);
		setearImportes(view, comprobante);
		setearNumeroYTipoCuentaOrigen(view, comprobante);
		setearNumeroYTipoCuentaDestino(view,
				ISBANStringUtils.formatearFraseInicialMayuscula(getTipoCuentaDestino().getDescripcion()));
		view.setFecha(setearHora(comprobante.getFecha(), false));
		setearConceptoYDesconcepto(view, null);
		view.setBanco(getBanco());
		view.setDestinatario(comprobante.getDestinatario());
		return view;
	}

}
