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
public class DetalleComprobanteIatxPMCDefaultDTO extends DetalleComprobantePMCConDeudaDTO {

    /**
     * Instantiates a new detalle comprobante iatx PMC default DTO.
     */
    public DetalleComprobanteIatxPMCDefaultDTO() {
        super();
        this.setTipoPMC(ComprobantesBOEnum.PMC_SIN_DEUDA.getId());
        this.setTipoComprobante(TipoDetalleComprobanteEnum.IATX_PMC_PAGO_PUNTUAL);
        this.setTituloComprobante(CabeceraComprobantesEnum.COMPROBANTE_DE_PAGO.getDetalle());
    }

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
        setearImportes(view, comprobante);
        setearNumeroTipoYTitulo(view);
        setearNumeroYTipoCuentaOrigen(view, comprobante);
        SimpleDateFormat sdf = new SimpleDateFormat(ISBANStringUtils.FORMATO_FECHA);
        view.setFechaOperacion(sdf.format(comprobante.getFecha()));
        view.setHora(comprobante.getHoraOperacion());
        return view;
    }
}
