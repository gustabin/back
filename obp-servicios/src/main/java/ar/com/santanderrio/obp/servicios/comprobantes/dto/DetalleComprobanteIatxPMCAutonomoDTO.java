/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import java.text.SimpleDateFormat;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobantePMCVEPView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobantePMCView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

/**
 * The Class DetalleComprobanteIatxPMCAutonomoDTO.
 *
 */
public class DetalleComprobanteIatxPMCAutonomoDTO extends DetalleComprobantePMCDTO {

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteDTO#
     * getView(ar.com.santanderrio.obp.servicios.comprobantes.dto. ComprobanteDTO)
     */
    @Override
    public DetalleComprobantePMCView getView(ComprobanteDTO comprobante) {
        DetalleComprobantePMCVEPView view = new DetalleComprobantePMCVEPView();
        SimpleDateFormat sdf = new SimpleDateFormat(ISBANStringUtils.FORMATO_FECHA);
        setearAtributosComunesDetallePMC(view, getEmpresa(), sdf.format(getFechaDePago()), comprobante);
        setearLeyendaFactura(view);
        view.setHora(getHoraDePago());
        view.setIdentificacion(getIdentificacion());
        view.setLabelDinamico(LABEL_DINAMICO);
        view.setLeyendaEmpresa(getLeyendaEmpresa());
        view.setNroControl(getNroControl());
        view.setNroTransaccion(getTransaccion());
        return view;
    }

}
