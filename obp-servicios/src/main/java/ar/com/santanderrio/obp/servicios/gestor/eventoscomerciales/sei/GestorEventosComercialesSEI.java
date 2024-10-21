/*
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.sei;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.base.web.view.View;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.*;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view.DomicilioView;

/**
 * The Interface GestorEventosComercialesSEI.
 */
@Path("/gestorEventosComerciales")
public interface GestorEventosComercialesSEI {

	/**
	 * Obtener cantidad notificaciones.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerCantNotificaciones")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<NotificacionesComercialesView> obtenerCantidadNotificaciones();

	/**
	 * Obtener notificaciones.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerNotificaciones")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<NotificacionesComercialesView> obtenerNotificaciones();
	
	/**
	 * Borrar notificacion.
	 *
	 * @param oferta the oferta
	 * @return the respuesta
	 */
	@POST
	@Path("/borrarNotificacion")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<OfertaComercialView> borrarNotificacion(OfertaComercialView oferta);
	
	
	
	/**
	 * Confirmar Promesa de pago.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/confirmarPromesaPago")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConfirmarPromesaPagoView> confirmarPromesaPago();
	
	
	/**
	 * Finalizar Promesa de pago.
	 *
	 * @param inView the in view
	 * @return the respuesta
	 */
	@POST
	@Path("/finalizarPromesaPago")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<FinalizarPromesaPagoOutView> finalizarPromesaPago(FinalizarPromesaPagoInView inView);
	
	

	/**
	 * Actualizar notificaciones.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/actualizarNotificaciones")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<Boolean> actualizarNotificaciones();

	/**
	 * Informa el interes en notificacion.
	 *
	 * @param view
	 *            the view
	 * @return the respuesta
	 */
	@POST
	@Path("/informarInteresNotificacion")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> informarInteresEnNotificacion(View view);

	/**
	 * Obtener carrusel.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerCarrusel")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<OfertasComercialesView> obtenerCarrusel();

	/**
	 * Informar interes oferta.
	 *
	 * @param oferta
	 *            the oferta
	 * @return the respuesta
	 */
	@POST
	@Path("/informarInteresOferta")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> informarInteresOferta(OfertaComercialView oferta);



	@POST
	@Path("/informarVistaOferta")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> informarVistaOferta(OfertaComercialView oferta);
	
	/**
	 * Obtener ofertas por seccion.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerOfertasPorSeccion")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<OfertasComercialesView> obtenerOfertasPorSeccion();

	/**
	 * Informa que el cliente rechazo la oferta.
	 *
	 * @param oferta
	 *            the oferta
	 * @return the respuesta
	 */
	@POST
	@Path("/informarRechazoOferta")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> informarRechazoOferta(OfertaComercialView oferta);



	/**
	 * Registrar encuesta.
	 *
	 * @param respuestasEncuesta
	 *            the respuestas encuesta
	 * @return the respuesta
	 */
	@POST
	@Path("/registrarEncuesta")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> registrarEncuesta(List<RespuestaEncuestaView> respuestasEncuesta);
	
	/**
	 * Informar Gestion AC.
	 *
	 * @param domicilioDeReemplazo
	 *            the domicilio de reemplazo
	 * @return the respuesta
	 */
	@POST
	@Path("/informarGestionAC")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> informarGestionAC(DomicilioView domicilioDeReemplazo);

	 /** Obetener notificaciones perfil.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/obetenerNotificacionesPerfil")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<NotificacionesComercialesView> obetenerNotificacionesPerfil();
	
	/**
	 *  Obtener premiaciones.
	 *
	 * @param chance the chance
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerPremiaciones")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<ChanceView> obtenerPremiaciones(ChanceView chance);
	
	/**
	 * Actualizar cuenta favorita.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/actualizarCuentaFavorita")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<NumeroCuentaFavoritaView> actualizarCuentaFavorita();

	/**
	 * Actualizar tarjeta favorita.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/actualizarTarjetaFavorita")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<NumeroTarjetaFavoritaView> actualizarTarjetaFavorita();

	/**
	 * Cargar contratos transferencia.
	 */
	@POST
	@Path("/cargarContratosTransferencia")
	@Produces(value = { MediaType.APPLICATION_JSON })
	void cargarContratosTransferencia();
	
	/**
	 * Grabar estadistica ayuda promesa pago.
	 */
	@POST
	@Path("/grabarEstadisticaAyudaPromesaPago")
	@Produces(value = { MediaType.APPLICATION_JSON })
	void grabarEstadisticaAyudaPromesaPago();
	
	/**
	 * Grabar estadistica ayuda descubierto PP.
	 */
	@POST
	@Path("/grabarEstadisticaAyudaDescubiertoPP")
	@Produces(value = { MediaType.APPLICATION_JSON })
	void grabarEstadisticaAyudaDescubiertoPP();
	
	/**
	 * Grabar estadistica configuracion promesa pago.
	 */
	@POST
	@Path("/grabarEstadisticaConfiguracionPromesaPago")
	@Produces(value = { MediaType.APPLICATION_JSON })
	void grabarEstadisticaConfiguracionPromesaPago();
	
	/**
	 * Obtener ofertas financiacion.
	 */
	@POST
	@Path("/obtenerOfertasFinanciacion")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<OfertasFinanciacionOutView> obtenerOfertasFinanciacion();

	/**
	 * Solicitar financiacion.
	 *
	 * @param solicitarFinanciacionInView the solicitar financiacion in view
	 * @return the respuesta
	 */
	@POST
	@Path("/solicitarFinanciacion")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<String> solicitarFinanciacion(SolicitarFinanciacionInView solicitarFinanciacionInView);

	/**
	 * Mostrar contratos ty C.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/mostrarContratosTyC")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<AceptarTyCView> mostrarContratosTyC();

	/**
	 * Aceptar contratos ty C.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/aceptarContratosTyC")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<AceptarTyCView> aceptarContratosTyC();

	/**
	 * Grabar estadistica recordar mas tarde contratos ty C.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/grabarEstadisticaRecordarMasTardeContratosTyC")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> grabarEstadisticaRecordarMasTardeContratosTyC();
	
	/**
	 * Grabar estadistica carrusel mostrar contratos ty C.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/grabarEstadisticaCarruselMostrarContratosTyC")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> grabarEstadisticaCarruselMostrarContratosTyC();
	
	@POST
	@Path("/aceptarContacto")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<ContactabilidadView> informarAceptacionContacto(ContactabilidadView contactabilidadView);
	
	@POST
	@Path("/cerrarStackPoliticasPrivacidad")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> cerrarStackPoliticasPrivacidad();
	
	@POST
	@Path("/grabarEstadisticaVerTycPoliticasPrivacidad")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<AceptarTyCView> grabarEstadisticaVerTycPoliticasPrivacidad();
	
	@POST
	@Path("/iniciarFlujoMastercardBlack")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<TarjetaMastercardBlackView> iniciarFlujoMastercardBlack();
	
	@POST
	@Path("/solicitarMastercardBlack")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<TarjetaMastercardBlackView> solicitarMastercardBlack();

	@POST
	@Path("/aceptaTyCDUO")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<TyCDuoView> aceptaTyCDUO();

}
