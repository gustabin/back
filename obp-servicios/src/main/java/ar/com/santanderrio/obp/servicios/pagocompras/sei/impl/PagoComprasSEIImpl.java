/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagocompras.sei.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.DeudaPagoComprasView;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.PagoComprasMultiple;
import ar.com.santanderrio.obp.servicios.pagocompras.manager.PagoComprasManager;
import ar.com.santanderrio.obp.servicios.pagocompras.manager.PagoComprasMultipleManager;
import ar.com.santanderrio.obp.servicios.pagocompras.sei.PagoComprasSEI;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoSelectionView;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoView;
import ar.com.santanderrio.obp.servicios.pagos.entities.NuevoPago;

/**
 * The Class PagoComprasSEIImpl.
 */
@Component
public class PagoComprasSEIImpl implements PagoComprasSEI {

    /** The pago compras manager. */
    @Autowired
    PagoComprasManager pagoComprasManager;
    
    @Autowired
    PagoComprasMultipleManager pagoComprasMultipleManager;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagocompras.sei.PagoComprasSEI#
     * obtenerDeudas(ar.com.santanderrio.obp.servicios.pagos.entities.
     * MedioPagoView)
     */
    @Override
    public Respuesta<MedioPagoSelectionView> obtenerDeudas(MedioPagoView medioPagoView) {
        return pagoComprasManager.obtenerDeudas(medioPagoView);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagocompras.sei.PagoComprasSEI#
     * obtenerConfiguracion(ar.com.santanderrio.obp.servicios.pagos.entities.
     * MedioPagoView)
     */
    @Override
    public Respuesta<MedioPagoSelectionView> obtenerConfiguracion(MedioPagoView medioPagoView) {
        return pagoComprasManager.obtenerConfiguracion(medioPagoView);
    }

    /**
     * Continuar pago de compras.
     *
     * @param pagoCompras
     *            the pago compras
     * @return the respuesta
     */
    @Override
    public Respuesta<Boolean> continuarPagoCompras(NuevoPago pagoCompras) {
        return pagoComprasManager.continuarPagoCompras(pagoCompras);
    }

    /**
     * Ejecutar pago de compras.
     *
     * @param pagoCompras
     *            the pago compras
     * @return the respuesta
     */
    @Override
    public Respuesta<NuevoPago> ejecutarPagoCompras(NuevoPago pagoCompras) {
        return pagoComprasManager.ejecutarPagoCompras(pagoCompras);
    }

    /**
     * Graba la estadistica correspondiente al comprobante de pago de compras.
     *
     * @return the respuesta
     */
    @Override
    public Respuesta<Boolean> grabarEstadisticaComprobantePagoCompras() {
        return pagoComprasManager.grabarEstadisticaComprobantePagoCompras();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagocompras.sei.PagoComprasSEI#
     * descargarComprobantePDF()
     */
    @Override
    public Respuesta<ReporteView> descargarComprobantePDF() {
        return pagoComprasManager.descargarComprobantePDF();
    }

    @Override
    public void grabarEstadisticaPagoComprasMultiple() {
        pagoComprasMultipleManager.grabarEstadisticaPagoComprasMultiple();
        
    }

    @Override
    public Respuesta<Boolean> continuarPagoComprasMultiple(PagoComprasMultiple pagoCompras) {
        return pagoComprasMultipleManager.continuarPagoComprasMultiple(pagoCompras);
    }

    @Override
    public Respuesta<PagoComprasMultiple> ejecutarPagoComprasMultiple(PagoComprasMultiple pagoCompras) {
        return pagoComprasMultipleManager.ejecutarPagoComprasMultiple(pagoCompras);
    }
    
    @Override
    public Respuesta<ReporteView> descargarComprobanteSeleccionadoPDF(DeudaPagoComprasView deuda) {
        return pagoComprasMultipleManager.descargarComprobantePDF(deuda);
    }
    
    @Override
    public Respuesta<Boolean> grabarEstadisticaComprobantePagoComprasMultiple() {
        return pagoComprasMultipleManager.grabarEstadisticaComprobantePagoCompras();
    }
}
