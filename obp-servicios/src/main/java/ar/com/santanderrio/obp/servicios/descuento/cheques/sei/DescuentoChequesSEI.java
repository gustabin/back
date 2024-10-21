/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.sei;

import javax.activity.InvalidActivityException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.AltaChequesViewIn;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.AltaChequesViewOut;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.DatosCesionView;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.DetalleHistorialOperacionesView;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.DetalleOperacionesPrecargadasView;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.HistorialOperacionesView;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.OperacionesPrecargadasView;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.TasasIndicativasView;

/**
 * The Interface DescuentoChequesSEI.
 *
 * @author dante.omar.olmedo
 */
@Path("/descuentoCheques")
public interface DescuentoChequesSEI {

	/**
	 * Obtiene montos de cabecera y mensajes del flujo.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerCabecera")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<DatosCesionView> obtenerCabeceraInicio();

	/**
	 * Consultar tasas indicativas.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/consultaTasas")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<TasasIndicativasView> consultarTasasIndicativas();

	/**
	 * Consultar operaciones precargadas.
	 *
	 * @param operacionIn the operacion in
	 * @return the respuesta
	 */
	@POST
	@Path("/consultaOperacionesPrecargadas")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<OperacionesPrecargadasView> consultarOperacionesPrecargadas(OperacionesPrecargadasView operacionIn);

	/**
	 * Obtener detalle operaciones precargadas.
	 *
	 * @param operacionIn the operacion in
	 * @return the respuesta
	 * @throws InvalidActivityException the invalid activity exception
	 */
	@POST
	@Path("/consultaDetalleOperacionesPrecargadas")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Respuesta<DetalleOperacionesPrecargadasView> obtenerDetalleOperacionesPrecargadas(
			DetalleOperacionesPrecargadasView operacionIn) throws InvalidActivityException;

	/**
	 * Eliminar operacion.
	 *
	 * @param detalleIn the detalle in
	 * @return the respuesta
	 */
	@POST
	@Path("/eliminarOperacion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<Void> eliminarOperacion(DetalleOperacionesPrecargadasView detalleIn);
	
	/**
	 * Descargar operacion PDF.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/descargarOperacionPDF")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ReporteView> descargarOperacionPDF();
	
	/**
	 * Consultar historial operaciones.
	 *
	 * @param operacionIn the operacion in
	 * @return the respuesta
	 */
	@POST
	@Path("/consultaHistorialOperaciones")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<HistorialOperacionesView> consultarHistorialOperaciones(HistorialOperacionesView operacionIn);

	/**
	 * Obtener detalle historial operaciones.
	 *
	 * @param operacionIn the operacion in
	 * @return the respuesta
	 * @throws InvalidActivityException the invalid activity exception
	 */
	@POST
	@Path("/consultaDetalleHistorialOperaciones")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<DetalleHistorialOperacionesView> obtenerDetalleHistorialOperaciones(
			DetalleOperacionesPrecargadasView operacionIn) throws InvalidActivityException;
	
	/**
	 * Graba la estadistica de inicio solicitud.
	 *
	 * @param view
	 *            the view
	 */
	@POST
	@Path("/grabarInicioSolicitud")
	void grabarInicioSolicitud(DatosCesionView view);
	
	/**
	 * Realiza la simulacion de alta de cheques.
	 *
	 * @param chequesView
	 *            los cheques cargados en la vista
	 * @return la simulacion con el detalle de los cheques
	 */
	@POST
	@Path("/simularAltaCheques")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<AltaChequesViewOut> simularAltaCheques(AltaChequesViewIn chequesView);
	
	/**
	 * Realiza la efectivizacion de alta de cheques.
	 *
	 * @return el mensaje de feedback positivo con los datos del detalle.
	 */
	@POST
	@Path("/efectuarAltaCheques")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<AltaChequesViewOut> efectuarAltaCheques();

	/**
	 * Realiza la simulacion de alta de cheques.
	 *
	 * @param chequesView
	 *            los cheques cargados en la vista
	 * @return la simulacion con el detalle de los cheques
	 */
	@POST
	@Path("/visualizacionAltaCheques")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<AltaChequesViewOut> visualizarAltaCheques(AltaChequesViewOut chequesView);
	
	/**
	 * Descargar operacion PDF.
	 *
	 * @param chequesView
	 *            the cheques view
	 * @return the respuesta
	 */
	@POST
	@Path("/descargarAltaChequesPDF")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ReporteView> descargarAltaChequesPDF(AltaChequesViewOut chequesView);
	
	/**
	 * Simula las tazas de descuento al día de hoy.
	 *
	 * @param chequesView
	 *            the cheques view
	 * @return the respuesta
	 * @throws InvalidActivityException
	 *             the invalid activity exception
	 */
	@POST
	@Path("/simularTasas")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<AltaChequesViewOut> simulacionDeTasas(AltaChequesViewOut chequesView) throws InvalidActivityException;
	
	/**
	 * Efectuar simulacion tasas.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/efectuarTasas")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<AltaChequesViewOut> efectuarSimulacionTasas();
	
	
	
}
