/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.sei.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comprobantes.sei.ComprobantesSEI;
import ar.com.santanderrio.obp.servicios.comprobantes.web.manager.ComprobantesManager;
import ar.com.santanderrio.obp.servicios.comprobantes.web.manager.DetalleComprobantesManager;
import ar.com.santanderrio.obp.servicios.comprobantes.web.manager.HistorialComprobantesManager;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.ComprobantesView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.ComprobantesViewIn;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.HistorialComprobantesView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.TransaccionViewIn;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;

/**
 * SEI de modulo de comprobantes.
 */
@Component
public class ComprobantesSEIImpl implements ComprobantesSEI {

    /** The comprobantes manager. */
    @Autowired
    private ComprobantesManager comprobantesManager;

    /** The detalle comprobantes manager. */
    @Autowired
    private DetalleComprobantesManager detalleComprobantesManager;

    @Autowired
    private HistorialComprobantesManager historialManager;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comprobantes.sei.ComprobantesSEI#
     * obtenerComprobantes()
     */
    @Override
    public Respuesta<ComprobantesView> obtenerComprobantes(ComprobantesViewIn viewIn) {
        return comprobantesManager.obtenerComprobantes(viewIn);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comprobantes.sei.ComprobantesSEI#
     * obtenerDetalleComprobantes(ar.com.santanderrio.obp.servicios.comprobantes
     * .web.view.ComprobantesViewIn)
     */
    @Override
    public Respuesta<DetalleComprobanteView> obtenerDetalleComprobantes(ComprobantesViewIn idComprobante) {
        return detalleComprobantesManager.obtenerDetalleComprobantes(idComprobante.getIdComprobante(), true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comprobantes.sei.ComprobantesSEI#
     * descargarComprobantePDF()
     */
    @Override
    public Respuesta<ReporteView> descargarComprobantePDF() {
        return detalleComprobantesManager.descargaComprobanteGrilla();
    }

    @Override
    public Respuesta<HistorialComprobantesView> consultaHistorial(ComprobantesViewIn consultaHistorial) {
        return historialManager.consultaHistorial(consultaHistorial.getIdComprobante());
    }

    @Override
    public Respuesta<DetalleComprobanteView> obtenerDetalleHistorial(ComprobantesViewIn idComprobante) {
        return detalleComprobantesManager.obtenerDetalleComprobantes(idComprobante.getIdComprobante(), false);
    }
    
    @Override
    public Respuesta<TransaccionViewIn> solapaPorDefecto() {
        return comprobantesManager.solapaPorDefecto();
    }

	@Override
	public Respuesta<ReporteView> descargaHistorial(ComprobantesViewIn viewIn) {
		return detalleComprobantesManager.descargaHistorial(viewIn.getIdComprobante());
	}


}
