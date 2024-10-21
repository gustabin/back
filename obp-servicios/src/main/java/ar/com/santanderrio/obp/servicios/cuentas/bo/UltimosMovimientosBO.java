/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.bo;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaUltimosMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleUltimosMovimientos;

/**
 * The Interface UltimosMovimientosBO.
 */
public interface UltimosMovimientosBO {

    /**
     * Obtener ultimos movimientos.
     *
     * @param consultaUltimosMovimientos
     *            the consulta ultimos movimientos
     * @param filtroSession
     *            the filtro session
     * @return the respuesta
     */
    Respuesta<DetalleUltimosMovimientos> obtenerUltimosMovimientos(
            ConsultaUltimosMovimientos consultaUltimosMovimientos, ConsultaUltimosMovimientos filtroSession);

    /**
     * Exportar ultimos movimientos.
     *
     * @param consultaUltimosMovimientos
     *            the consulta ultimos movimientos
     * @param dispositivo
     *            the dispositivo
     * @return the respuesta
     */
    Respuesta<Reporte> exportarUltimosMovimientos(ConsultaUltimosMovimientos consultaUltimosMovimientos,
            String dispositivo);

    /**
     * Completar filtro.
     *
     * @param filtro
     *            the filtro
     * @throws BusinessException
     *             the business exception
     */
    void completarFiltro(ConsultaUltimosMovimientos filtro) throws BusinessException;

    /**
     * Obtener Mensaje Informativo.
     *
     * @param nroSucursal
     *            the nroSucursal
     * @param nroCuenta
     *            the nroCuenta
     * @return the string
     */
    String obtenerMensajeInformativo(String nroSucursal, String nroCuenta);

    /**
     * Obtener mensaje no hay movimientos.
     *
     * @param consultaUltimosMovimientos
     *            the consulta ultimos movimientos
     * @return the string
     */
    String obtenerMensajeNoHayMovimientos(ConsultaUltimosMovimientos consultaUltimosMovimientos);

    /**
     * Validar filtros.
     *
     * @param filtroMovimientos
     *            the filtro movimientos
     * @param filtro
     *            the filtro
     * @return the respuesta
     */
    Respuesta<ConsultaUltimosMovimientos> validarFiltros(ConsultaUltimosMovimientos filtroMovimientos,
            ConsultaUltimosMovimientos filtro, Boolean primerIngreso);

    DetalleComprobanteView exportarUltimosMovimientosPDF(ConsultaUltimosMovimientos filtro, String dispositivo);
}