/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaUltimosMovimientosView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.DetalleMovimientosView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.EstadoDetalleMovimientoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.MovimientoValoresView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.MovimientoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.MovimientosPendientesDeConfirmacionView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.NumeroCuentaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;

/**
 * The Interface MovimientosSEI.
 */
@Path("/movimientos")
public interface MovimientosSEI {

	/**
	 * Gets the movimientos pendientes.
	 *
	 * @param consultaUltimosMovimientosView
	 *            the consulta ultimos movimientos view
	 * @return the movimientos pendientes
	 */
	@POST
	@Path("/obtenerMovimientosPendientes")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<MovimientosPendientesDeConfirmacionView> getMovimientosPendientes(
			ConsultaUltimosMovimientosView consultaUltimosMovimientosView);

	/**
	 * Gets the movimientos valores pendientes.
	 *
	 * @param consultaUltimosMovimientosView
	 *            the consulta ultimos movimientos view
	 * @return the movimientos valores pendientes
	 */
	@POST
	@Path("/obtenerMovimientosValoresPendientes")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<MovimientoValoresView> getMovimientosValoresPendientes(
			ConsultaUltimosMovimientosView consultaUltimosMovimientosView);

	/**
	 * Gets the movimientos pendientes detalle.
	 *
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @return the movimientos pendientes detalle
	 */
	@POST
	@Path("/obtenerMovimientosPendientesDetalle")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<MovimientosPendientesDeConfirmacionView> getMovimientosPendientesDetalle(NumeroCuentaView numeroCuenta);

	/**
	 * Gets the movimientos valores pendientes detalle.
	 *
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @return the movimientos valores pendientes detalle
	 */
	@POST
	@Path("/obtenerMovimientosValoresPendientesDetalle")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<MovimientosPendientesDeConfirmacionView> getMovimientosValoresPendientesDetalle(
			NumeroCuentaView numeroCuenta);

	/**
	 * Gets the movimientos pendientes reporte.
	 *
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @return the movimientos pendientes reporte
	 */
	@POST
	@Path("/obtenerMovimientosPendientesReporte")
	@Produces({"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8", MediaType.APPLICATION_JSON})
	Response getMovimientosPendientesReporte(NumeroCuentaView numeroCuenta);

	/**
	 * Exportar movimientos.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/exportarMovimientos")
	@Produces({"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8", MediaType.APPLICATION_JSON})
	Response exportarMovimientos();
	
	@POST
    @Path("/exportarMovimientosPDF")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<ReporteView> exportarMovimientosPDF();

	/**
	 * Gets the movimientos valores pendientes reporte.
	 *
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @return the movimientos valores pendientes reporte
	 */
	@POST
	@Path("/obtenerMovimientosValoresPendientesReporte")
	@Produces({"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8", MediaType.APPLICATION_JSON})
	Response getMovimientosValoresPendientesReporte(NumeroCuentaView numeroCuenta);

	/**
	 * Gets the movimientos.
	 *
	 * @param consultaUltimosMovimientosView
	 *            the consulta ultimos movimientos view
	 * @return the movimientos
	 */

	@POST
	@Path("/obtenerMovimientos")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<MovimientoView> getMovimientos(ConsultaUltimosMovimientosView consultaUltimosMovimientosView);

	/**
	 * Gets the movimientos.
	 *
	 * @return the movimientos
	 */

	@POST
	@Path("/obtenerMasMovimientos")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<MovimientoView> obtenerMasMovimientos();

	/**
	 * Obtener detalle movimiento.
	 *
	 * @param id
	 *            the id
	 * @return the respuesta
	 */

	@POST
	@Path("/obtenerDetalleMovimiento")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<DetalleMovimientosView> obtenerDetalleMovimiento(EstadoDetalleMovimientoView id);

}