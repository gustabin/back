/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobantePagoComprasView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

/**
 * The Class DetalleComprobantePagoDeComprasDTO.
 */
public class DetalleComprobantePagoDeComprasDTO extends DetalleComprobanteDTO {

    /** The label dinamico. */
    private String labelDinamico;

    /** The nro boleta. */
    private String nroBoleta;

    /** The nro orden. */
    private String nroOrden;
    
    private String idEmpresa;

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteDTO#getView(ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobanteDTO)
     */
    @Override
    public DetalleComprobanteView getView(ComprobanteDTO dataGrilla) {
        DetalleComprobantePagoComprasView view = new DetalleComprobantePagoComprasView();
        view.setTituloComprobante(CabeceraComprobantesEnum.COMPROBANTE_DE_PAGO.getDetalle());
        view.setTipoComprobante(getTipoComprobante().getTag());
        view.setDestinatario(dataGrilla.getDestinatario());
        view.setEmpresa(dataGrilla.getDestinatario());
        if (dataGrilla.getImportePesos() != null) {
            view.setImportePesos(ISBANStringUtils.formatearSaldoSinAbs(dataGrilla.getImportePesos()));
            view.setNroCuentaOrigen(dataGrilla.getCtaMedioDePagoPesos());
            view.setTipoCuentaOrigen(dataGrilla.getTipoCtaMedioDePagoPesos().getDescripcion());
        } else if (dataGrilla.getImporteDolares() != null) {
            view.setImporteDolares(ISBANStringUtils.formatearSaldoSinAbs(dataGrilla.getImporteDolares()));
            view.setNroCuentaOrigen(dataGrilla.getCtaMedioDePagoDolares());
            view.setTipoCuentaOrigen(dataGrilla.getTipoCtaMedioDePagoDolares().getDescripcion());
        }
        view.setFecha(setearHora(dataGrilla.getFecha(), false));
        view.setNroComprobante(getNroComprobante());
        view.setLabelDinamico(this.labelDinamico);
        view.setIdentificacion(getIdentificacion());
        view.setNroBoleta(this.nroBoleta);
        view.setNroOrden(this.nroOrden);
        return view;

    }

    /**
	 * Gets the nro boleta.
	 *
	 * @return the nro boleta
	 */
    public String getNroBoleta() {
        return nroBoleta;
    }

    /**
	 * Sets the nro boleta.
	 *
	 * @param nroBoleta
	 *            the new nro boleta
	 */
    public void setNroBoleta(String nroBoleta) {
        this.nroBoleta = nroBoleta;
    }

    /**
	 * Gets the nro orden.
	 *
	 * @return the nro orden
	 */
    public String getNroOrden() {
        return nroOrden;
    }

    /**
	 * Sets the nro orden.
	 *
	 * @param nroOrden
	 *            the new nro orden
	 */
    public void setNroOrden(String nroOrden) {
        this.nroOrden = nroOrden;
    }

    /**
	 * Gets the label dinamico.
	 *
	 * @return the label dinamico
	 */
    public String getLabelDinamico() {
        return labelDinamico;
    }

    /**
	 * Sets the label dinamico.
	 *
	 * @param labelDinamico
	 *            the new label dinamico
	 */
    public void setLabelDinamico(String labelDinamico) {
        this.labelDinamico = labelDinamico;
    }

	/**
	 * @return the idEmpresa
	 */
	public String getIdEmpresa() {
		return idEmpresa;
	}

	/**
	 * @param idEmpresa the idEmpresa to set
	 */
	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
	

}
