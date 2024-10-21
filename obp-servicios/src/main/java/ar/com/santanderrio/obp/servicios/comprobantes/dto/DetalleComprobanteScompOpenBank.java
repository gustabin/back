package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobantePMCView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

import java.text.SimpleDateFormat;

public class DetalleComprobanteScompOpenBank extends DetalleComprobantePagoMisCuentasDTO {

    @Override
    public DetalleComprobanteView getView(ComprobanteDTO comprobante) {
        DetalleComprobantePMCView view = new DetalleComprobantePMCView();
        SimpleDateFormat sdf = new SimpleDateFormat(ISBANStringUtils.FORMATO_FECHA);
        setearAtributosComunesDetallePMC(view, getEmpresa(), sdf.format(comprobante.getFecha()), comprobante);
        view.setHora(getHoraDePago());
        view.setIdentificacion(getIdentificacion());
        view.setLabelDinamico(getLabelDinamico());
        view.setNroControl(getNroControl());
        view.setNroTransaccion(getNroTransaccion());
        return view;
    }

}
