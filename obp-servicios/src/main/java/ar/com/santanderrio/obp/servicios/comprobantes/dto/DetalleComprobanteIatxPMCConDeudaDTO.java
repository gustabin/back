/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import java.text.SimpleDateFormat;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobantePMCView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

/**
 * The Class DetalleComprobanteIatxPMCConDeudaDTO.
 */
public class DetalleComprobanteIatxPMCConDeudaDTO extends DetalleComprobantePMCConDeudaDTO {

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
        setearLeyendaFactura(view);
        view.setIdentificacion(getIdentificacion());
        view.setLabelDinamico(LABEL_DINAMICO);
        view.setLeyendaEmpresa(getLeyendaEmpresa());
        view.setHora(getHoraDePago());
        view.setNroControl(getNroControl());
        view.setNroTransaccion(getTransaccion());
        return view;

    }
}
