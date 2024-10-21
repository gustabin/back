/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagocompras.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoSelectionView;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoView;
import ar.com.santanderrio.obp.servicios.pagos.entities.NuevoPago;

/**
 * The Interface PagoComprasManager.
 */
public interface PagoComprasManager {

    /**
     * Obtener deudas.
     *
     * @param medioPagoView
     *            the medio pago view
     * @return the respuesta
     */
    Respuesta<MedioPagoSelectionView> obtenerDeudas(MedioPagoView medioPagoView);

    /**
     * Obtener configuracion.
     *
     * @param medioPagoView
     *            the medio pago view
     * @return the respuesta
     */
    Respuesta<MedioPagoSelectionView> obtenerConfiguracion(MedioPagoView medioPagoView);

    /**
     * Continuar pago compras.
     *
     * @param pagoCompras
     *            the pago compras
     * @return the respuesta
     */
    Respuesta<Boolean> continuarPagoCompras(NuevoPago pagoCompras);

    /**
     * Ejecutar pago de compras.
     *
     * @param pagoCompras
     *            the pago compras
     * @return the respuesta
     */
    Respuesta<NuevoPago> ejecutarPagoCompras(NuevoPago pagoCompras);

    /**
     * Grabar estadistica comprobante pago compras.
     *
     * @return the respuesta
     */
    Respuesta<Boolean> grabarEstadisticaComprobantePagoCompras();

    /**
     * Descargar comprobante PDF.
     *
     * @return the respuesta
     */
    Respuesta<ReporteView> descargarComprobantePDF();

}
