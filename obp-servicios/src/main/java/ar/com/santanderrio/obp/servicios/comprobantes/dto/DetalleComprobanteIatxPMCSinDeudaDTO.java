/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import java.text.SimpleDateFormat;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobantePMCView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

/**
 * The Class DetalleComprobanteIatxPMCSinDeudaDTO.
 */
public class DetalleComprobanteIatxPMCSinDeudaDTO extends DetalleComprobantePagoMisCuentasDTO {
    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteDTO#
     * getView(ar.com.santanderrio.obp.servicios.comprobantes.dto. ComprobanteDTO)
     */
    @Override
    public DetalleComprobantePMCView getView(ComprobanteDTO comprobante) {
        DetalleComprobantePMCView view = new DetalleComprobantePMCView();
        SimpleDateFormat sdf = new SimpleDateFormat(ISBANStringUtils.FORMATO_FECHA);
        setearAtributosComunesDetallePMC(view, getEmpresa(), sdf.format(getFechaDePago()), comprobante);
        view.setIdentificacion(getIdentificacion());
        view.setLabelDinamico(LABEL_DINAMICO);
        view.setHora(getHoraDePago());
        view.setNroControl(getNroControl());
        view.setNroTransaccion(getTransaccion());
        return view;

    }
}
