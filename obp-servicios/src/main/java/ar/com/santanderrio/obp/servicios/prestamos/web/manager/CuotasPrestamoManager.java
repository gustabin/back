/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultaPrestamo;
import ar.com.santanderrio.obp.servicios.prestamos.view.ProximasCuotasView;

/**
 * Gestiona la logica relacionada a Cuotas de Prestamo.
 * 
 * @author
 *
 */
public interface CuotasPrestamoManager {

    /**
     * Obtener proximas cuotas.
     *
     * @param consultaPrestamo
     *            the consulta prestamo
     * @return the respuesta
     */
    Respuesta<ProximasCuotasView> obtenerProximasCuotas(ConsultaPrestamo consultaPrestamo);

    /**
     * Grabar estadistica detalle proxima cuota.
     *
     * @param consultaPrestamo
     *            the consulta prestamo
     * @return the respuesta
     */
    Respuesta<Void> grabarEstadisticaDetalleProximaCuota(ConsultaPrestamo consultaPrestamo);

    /**
     * Obtener cuotas pagas.
     *
     * @param consultaPrestamo
     *            the consulta prestamo
     * @return the respuesta
     */
    Respuesta<ProximasCuotasView> obtenerCuotasPagas(ConsultaPrestamo consultaPrestamo);

    /**
     * Grabar estadistica detalle cuota paga.
     *
     * @param consultaPrestamo
     *            the consulta prestamo
     * @return the respuesta
     */
    Respuesta<Void> grabarEstadisticaDetalleCuotaPaga(ConsultaPrestamo consultaPrestamo);

    /**
     * Obtener cuotas de cancelado.
     *
     * @param consultaPrestamo
     *            the consulta prestamo
     * @return the respuesta
     */
    Respuesta<ProximasCuotasView> obtenerCuotasDeCancelado(ConsultaPrestamo consultaPrestamo);

    /**
     * Grabar estadistica detalle cuota de cancelado.
     *
     * @return the respuesta
     */
    Respuesta<Void> grabarEstadisticaDetalleCuotaDeCancelado();

    /**
     * Obtener detalle.
     *
     * @param consultaPrestamo
     *            the consulta prestamo
     * @return the respuesta
     */
    Respuesta<ProximasCuotasView> obtenerDetalle(ConsultaPrestamo consultaPrestamo);

    Respuesta<ReporteView> exportarCuotasPagas();

    Respuesta<ReporteView> exportarProximasCuotas();
}
