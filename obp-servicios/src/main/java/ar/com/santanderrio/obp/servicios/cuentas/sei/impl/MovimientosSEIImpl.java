/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.sei.impl;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.sei.MovimientosSEI;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.MovimientosManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaUltimosMovimientosView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.DetalleMovimientosView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.EstadoDetalleMovimientoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.MovimientoValoresView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.MovimientoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.MovimientosPendientesDeConfirmacionView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.NumeroCuentaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;

/**
 * The Class MovimientosController.
 */
@Controller
@RequestMapping("/movimientos")
public class MovimientosSEIImpl implements MovimientosSEI {

    /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(MovimientosSEIImpl.class);

    /** The manager. */
    @Autowired
    private MovimientosManager manager;

    /** The session client. */
    @Autowired
    private SesionCliente sessionClient;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.cuentas.sei.impl.MovimientosController#
     * getMovimientosPendientes(ar.com.santanderrio.obp.cuentas.web.view.
     * ConsultaUltimosMovimientosView)
     */
    @Override
    public Respuesta<MovimientosPendientesDeConfirmacionView> getMovimientosPendientes(
            ConsultaUltimosMovimientosView consultaUltimosMovimientosView) {
        LOGGER.info("Inicio de getMovimientosPendientes ");
        return manager.getMovimientosPendientes(consultaUltimosMovimientosView);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.cuentas.sei.impl.MovimientosController#
     * getMovimientosValoresPendientes(ar.com.santanderrio.obp.cuentas.web.view.
     * ConsultaUltimosMovimientosView)
     */
    @Override
    public Respuesta<MovimientoValoresView> getMovimientosValoresPendientes(
            ConsultaUltimosMovimientosView consultaUltimosMovimientosView) {
        LOGGER.info("Inicio de MovimientosController ");
        return manager.getMovimientosValores(consultaUltimosMovimientosView);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.cuentas.sei.impl.MovimientosController#
     * getMovimientosPendientesDetalle(java.lang.String)
     */
    @Override
    public Respuesta<MovimientosPendientesDeConfirmacionView> getMovimientosPendientesDetalle(
            NumeroCuentaView numeroCuentaView) {
        LOGGER.info("Inicio de getMovimientosPendientesDetalle ");
        return manager.getMovimientosPendientesDetalle(numeroCuentaView.getNumero(), sessionClient.getCliente());
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.cuentas.sei.impl.MovimientosController#
     * getMovimientosValoresPendientesDetalle(java.lang.String)
     */
    @Override
    public Respuesta<MovimientosPendientesDeConfirmacionView> getMovimientosValoresPendientesDetalle(
            NumeroCuentaView numeroCuentaView) {
        return manager.getMovimientosValoresPendientesDetalle(numeroCuentaView.getNumero(), sessionClient.getCliente());
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cuentas.sei.MovimientosSEI#
     * exportarMovimientos()
     */
    @Override
    public Response exportarMovimientos() {

        return manager.exportarMovimientos();
    }
    @Override
    public Respuesta<ReporteView> exportarMovimientosPDF() {
        return manager.exportarMovimientosPDF();
    }
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cuentas.sei.MovimientosSEI#
     * getMovimientosPendientesReporte(ar.com.santanderrio.obp.servicios.cuentas
     * .web.view.NumeroCuentaView)
     */
    @Override
    public Response getMovimientosPendientesReporte(NumeroCuentaView numeroCuentaView) {
        return manager.getMovimientosPendientesReporte(numeroCuentaView.getNumero());

    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cuentas.sei.MovimientosSEI#
     * getMovimientosValoresPendientesReporte(ar.com.santanderrio.obp.servicios.
     * cuentas.web.view.NumeroCuentaView)
     */
    @Override
    public Response getMovimientosValoresPendientesReporte(NumeroCuentaView numeroCuentaView) {
        return manager.getMovimientosValoresPendientesReporte(numeroCuentaView.getNumero());
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cuentas.sei.MovimientosSEI#
     * getMovimientos(ar.com.santanderrio.obp.servicios.cuentas.web.view.
     * ConsultaUltimosMovimientosView)
     */
    @Override
    public Respuesta<MovimientoView> getMovimientos(ConsultaUltimosMovimientosView consultaUltimosMovimientosView) {
        return manager.getMovimientos(consultaUltimosMovimientosView);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cuentas.sei.MovimientosSEI#
     * obtenerMasMovimientos()
     */
    @Override
    public Respuesta<MovimientoView> obtenerMasMovimientos() {
        return manager.obtenerMasMovimientos();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cuentas.sei.MovimientosSEI#
     * obtenerDetalleMovimiento(ar.com.santanderrio.obp.servicios.cuentas.web.
     * view.IdMovimientosView)
     */
    @Override
    public Respuesta<DetalleMovimientosView> obtenerDetalleMovimiento(
            EstadoDetalleMovimientoView detalleMovimientoView) {
        return manager.getDetalleMovimiento(detalleMovimientoView);
    }

}
