/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagocompras.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.DeudaPagoComprasView;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.PagoComprasMultiple;

/**
 * The Interface PagoComprasManager.
 */
public interface PagoComprasMultipleManager {

    void grabarEstadisticaPagoComprasMultiple();

    Respuesta<Boolean> continuarPagoComprasMultiple(PagoComprasMultiple pagoCompras);

    Respuesta<PagoComprasMultiple> ejecutarPagoComprasMultiple(PagoComprasMultiple pagoCompras);

    Respuesta<ReporteView> descargarComprobantePDF(DeudaPagoComprasView deuda);

    Respuesta<Boolean> grabarEstadisticaComprobantePagoCompras();

}
