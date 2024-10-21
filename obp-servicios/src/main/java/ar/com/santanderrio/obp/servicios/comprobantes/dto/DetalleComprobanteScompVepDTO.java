/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import java.text.SimpleDateFormat;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobantePMCVEPView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobantePMCView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

/**
 * The Class DetalleComprobanteScompVepDTO.
 */
public class DetalleComprobanteScompVepDTO extends DetalleComprobantePMCVEPDTO {

	private static final String FECHA_NO_DISPONIBLE_MOBILE = "**/**/**";

	private static final String FECHA_NO_DISPONIBLE_DESKTOP = "**/**/****";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.dto.
	 * DetalleComprobantePMCVEPDTO#getView(ar.com.santanderrio.obp.servicios.
	 * comprobantes.dto.ComprobanteDTO)
	 */
	@Override
	public DetalleComprobantePMCView getView(ComprobanteDTO comprobante) {
		DetalleComprobantePMCVEPView view = new DetalleComprobantePMCVEPView();
		SimpleDateFormat sdf = new SimpleDateFormat(ISBANStringUtils.FORMATO_FECHA);
		setearAtributosComunesDetallePMC(view, getEmpresa(), sdf.format(comprobante.getFecha()), comprobante);
		setearCuitYTipoCuit(view);
		view.setAnticipo(getAnticipo());
		view.setCuit2(getCuitVEP().getNumero());
		if (getFechaVencimiento() == null) {
			if (comprobante.getEsMobile()) {
				view.setFechaVencimiento(FECHA_NO_DISPONIBLE_MOBILE);
			} else {
				view.setFechaVencimiento(FECHA_NO_DISPONIBLE_DESKTOP);
			}
		} else {
			view.setFechaVencimiento(ISBANStringUtils.formatearFecha(getFechaVencimiento()));
		}
		view.setHora(getHoraDePago());
		view.setIdentificacion(getIdentificacion());
		view.setLabelDinamico(LABEL_DINAMICO);
		view.setNroControl(getNroControl());
		if (!ISBANStringUtils.isEmptyOrNull(getNroComprobante().trim())) {
			view.setNroTransaccion(getNroComprobante());
		}
		view.setNroVEP(getNroVEP());
		view.setPeriodo(getPeriodo());
		return view;
	}

}
