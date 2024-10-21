/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import java.text.SimpleDateFormat;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobantePMCAfipView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

/**
 * The Class DetalleComprobanteScompAfipDTO.
 *
 */
public class DetalleComprobanteScompAfipDTO extends DetalleComprobantePagoMisCuentasDTO {

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteDTO#
     * getView(ar.com.santanderrio.obp.servicios.comprobantes.dto. ComprobanteDTO)
     */
    @Override
    public DetalleComprobanteView getView(ComprobanteDTO comprobante) {
        DetalleComprobantePMCAfipView view = new DetalleComprobantePMCAfipView();
        SimpleDateFormat sdf = new SimpleDateFormat(ISBANStringUtils.FORMATO_FECHA);
        setearAtributosComunesDetallePMC(view, getEmpresa(), sdf.format(comprobante.getFecha()), comprobante);
        setearCuitYTipoCuit(view);
        setearElementoYTipoAdicional(view);
        setearLeyendaFactura(view);
        setearFechaVencimiento(view);
        view.setHora(getHoraDePago());
        view.setIdentificacion(getCuitVEP().getNumero());
        view.setLabelDinamico(labelDinamico);
        view.setLeyendaEmpresa(getLeyendaEmpresa());
        view.setNroControl(getNroControl());
        if (!ISBANStringUtils.isEmptyOrNull(getNroComprobante().trim())) {
            view.setNroTransaccion(getNroComprobante());
        }
        view.setTipoIdentificacion(getLabelDinamico());
        return view;
    }
    

}
