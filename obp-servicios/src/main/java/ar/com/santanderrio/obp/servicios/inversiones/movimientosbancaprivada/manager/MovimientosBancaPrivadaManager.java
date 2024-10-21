/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.movimientosbancaprivada.manager;

import javax.ws.rs.core.Response;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaUltimosMovimientosView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.MovimientoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;

/**
 * The Interface MovimientosBancaPrivadaManager.
 */
public interface MovimientosBancaPrivadaManager {

    /**
     * Obtener movimientos primera vez.
     *
     * @param consultaUltimosMovimientosView
     *            the consulta ultimos movimientos view
     * @return the respuesta
     */
    Respuesta<MovimientoView> obtenerMovimientosPrimeraVez(
            ConsultaUltimosMovimientosView consultaUltimosMovimientosView);

    /**
     * Obtener mas movimientos.
     *
     * @return the respuesta
     */
    Respuesta<MovimientoView> obtenerMasMovimientos();

    /**
     * Exportar movimientos.
     *
     * @return the response
     */
    Response exportarMovimientos();

    Respuesta<ReporteView> exportarMovimientosPDF();
}
