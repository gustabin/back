/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import java.text.SimpleDateFormat;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobantePMCView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

/**
 * The Class DetalleComprobanteScompSinDeudaDTO.
 */
public class DetalleComprobanteScompSinDeudaDTO extends DetalleComprobantePagoMisCuentasDTO {

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comprobantes.dto.
     * DetalleComprobanteTransferenciaInmediataDTO#getView(ar.com.santanderrio.
     * obp.servicios.comprobantes.dto.ComprobanteDTO)
     */
    @Override
    public DetalleComprobanteView getView(ComprobanteDTO comprobante) {
        DetalleComprobantePMCView view = new DetalleComprobantePMCView();
        SimpleDateFormat sdf = new SimpleDateFormat(ISBANStringUtils.FORMATO_FECHA);
        setearAtributosComunesDetallePMC(view, getEmpresa(), sdf.format(comprobante.getFecha()), comprobante);
        view.setHora(getHoraDePago());
        view.setIdentificacion(getIdentificacion());
        view.setLabelDinamico(getLabelDinamico());
        view.setNroControl(getNroControl());
        if (!ISBANStringUtils.isEmptyOrNull(getNroComprobante().trim())) {
            view.setNroTransaccion(getNroComprobante());
        }
        return view;
    }

}
