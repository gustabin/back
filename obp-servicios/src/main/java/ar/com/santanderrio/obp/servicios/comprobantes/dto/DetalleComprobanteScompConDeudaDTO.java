/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobantePMCView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

/**
 * The Class DetalleComprobanteScompConDeudaDTO.
 *
 * @author sabrina.cis
 */
public class DetalleComprobanteScompConDeudaDTO extends DetalleComprobantePMCConDeudaDTO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.dto.
	 * DetalleComprobantePMCConDeudaDTO#getView(ar.com.santanderrio.obp.
	 * servicios.comprobantes.dto.ComprobanteDTO)
	 */
	@Override
	public DetalleComprobantePMCView getView(ComprobanteDTO comprobante) {
		DetalleComprobantePMCView view = new DetalleComprobantePMCView();
		setearAtributosComunesDetallePMC(view, getEmpresa(), ISBANStringUtils.formatearFecha(comprobante.getFecha()),
				comprobante);
		setearLeyendaFactura(view);
		setearFechaVencimiento(view);
		view.setFactura(getFactura());
		view.setHora(getHoraDePago());
		view.setIdentificacion(getIdentificacion());
		view.setLabelDinamico(getLabelDinamico());
		view.setLeyendaEmpresa(getLeyendaEmpresa());
		view.setNroControl(getNroControl());
		if (!ISBANStringUtils.isEmptyOrNull(getNroComprobante().trim())) {
			view.setNroTransaccion(getNroComprobante());
		}
		return view;

	}

}
