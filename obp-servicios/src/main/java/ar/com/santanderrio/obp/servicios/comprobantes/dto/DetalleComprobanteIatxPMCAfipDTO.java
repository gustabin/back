/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import java.text.SimpleDateFormat;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobantePMCAfipView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

/**
 * The Class DetalleComprobanteIatxPMCAfipDTO.
 */
public class DetalleComprobanteIatxPMCAfipDTO extends DetalleComprobantePagoMisCuentasDTO {
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comprobantes.dto.
     * DetalleComprobanteTransferenciaInmediataDTO#getView(ar.com.santanderrio.
     * obp.servicios.comprobantes.dto.ComprobanteDTO)
     */
    @Override
    public DetalleComprobanteView getView(ComprobanteDTO comprobante) {
        DetalleComprobantePMCAfipView view = new DetalleComprobantePMCAfipView();
        SimpleDateFormat sdf = new SimpleDateFormat(ISBANStringUtils.FORMATO_FECHA);
        setearAtributosComunesDetallePMC(view, getEmpresa(), sdf.format(getFechaDePago()), comprobante);
        //setearCuitYTipoCuit(view);
        view.setIdentificacion(getIdentificacion());
        view.setLabelDinamico(LABEL_DINAMICO);
        view.setLeyendaEmpresa(getLeyendaEmpresa());
        setearLeyendaFactura(view);
        view.setHora(getHoraDePago());
        view.setNroControl(getNroControl());
        view.setNroTransaccion(getTransaccion());
        return view;
    }

}
