/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.movimientosbancaprivada.bo;

import java.util.Date;
import java.util.List;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaUltimosMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaUltimosMovimientosView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ItemMovimiento;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.MovimientosCuentaBPOutEntity;

/**
 * The Interface MovimientosBancaPrivadaBO.
 */
public interface MovimientosBancaPrivadaBO {

    /**
     * Obtener movimientos.
     *
     * @param consultaUltimosMovimientosView
     *            the consulta ultimos movimientos view
     * @param cliente
     *            the cliente
     * @param Cuenta
     *            the cuenta
     * @return the movimientos cuenta BP out entity
     * @throws BusinessException
     *             the business exception
     */
    MovimientosCuentaBPOutEntity obtenerMovimientos(ConsultaUltimosMovimientosView consultaUltimosMovimientosView,
            Cliente cliente, Cuenta cuenta) throws BusinessException;
    
    
    /**
     * Obtener Cuenta Cliente.
     *
     * @param cliente
     *            the cliente
     * @param consultaUltimosMovimientosView
     *            the consulta ultimos movimientos view
     * @return the Cuenta
     */
    Cuenta obtenerCuentaCliente(Cliente cliente,
            ConsultaUltimosMovimientosView consultaUltimosMovimientosView);

    /**
     * Aplicar filtros.
     *
     * @param movimientosCuentaBPOutEntity
     *            the movimientos cuenta BP out entity
     * @param consultaUltimosMovimientosView
     *            the consulta ultimos movimientos view
     * @return the movimientos cuenta BP out entity
     */
    MovimientosCuentaBPOutEntity aplicarFiltros(MovimientosCuentaBPOutEntity movimientosCuentaBPOutEntity,
            ConsultaUltimosMovimientosView consultaUltimosMovimientosView);

    /**
     * Limpiar cache.
     *
     * @param cliente
     *            the cliente
     */
    void limpiarCache(Cliente cliente);

    /**
     * Exportar ultimos movimientos.
     *
     * @param listaMovimientos
     *            the lista movimientos
     * @param dispositivo
     *            the dispositivo
     * @param cliente
     *            the cliente
     * @param consultaUltimosMovimientos
     *            the consulta ultimos movimientos
     * @return the respuesta
     */
    Respuesta<Reporte> exportarUltimosMovimientos(List<ItemMovimiento> listaMovimientos, String dispositivo,
            Cliente cliente, ConsultaUltimosMovimientos consultaUltimosMovimientos);

    /**
     * Obtener fecha hoy.
     *
     * @param isUltimaHora
     *            the is ultima hora
     * @return the date
     */
    Date obtenerFechaHoy(Boolean isUltimaHora);

    /**
     * Restar dias.
     *
     * @param fecha
     *            the fecha
     * @param cantidadDias
     *            the cantidad dias
     * @return the date
     */
    Date restarDias(Date fecha, int cantidadDias);

    DetalleComprobanteView exportarUltimosMovimientosPDF(List<ItemMovimiento> listaMovimientos, String dispositivo,
            ConsultaUltimosMovimientos filtro);
}
