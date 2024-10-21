/*
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.manager;

import java.util.List;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.*;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view.DomicilioView;

/**
 * The Interface GestorEventosComercialesManager.
 */
public interface GestorEventosComercialesManager {

	/**
	 * Obtiene la cantidad de notificaciones sin leer y totales.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<NotificacionesComercialesView> obtenerCantidadNotificaciones(Cliente cliente);

	/**
	 * Obtiene notificaciones.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<NotificacionesComercialesView> obtenerNotificaciones(Cliente cliente);
	
	
	/**
	 * Borrar notificacion.
	 *
	 * @param cliente the cliente
	 * @param oferta the oferta
	 * @return the respuesta
	 */
	Respuesta<OfertaComercialView> borrarNotificacion(Cliente cliente, OfertaComercialView oferta);
	

	/**
	 * Informa el interes que tiene un cliente en la notificacion.
	 *
	 * @param idNotificacion
	 *            the id notificacion
	 * @return the respuesta
	 */
	Respuesta<Void> informarInteresEnNotificacion(String idNotificacion);

	/**
	 * Informar interes en notificaciones.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<Boolean> actualizarNotificaciones(Cliente cliente);

	/**
	 * Obtener carrusel.
	 *
	 * @return the respuesta
	 */
	Respuesta<OfertasComercialesView> obtenerCarrusel();

	
	Respuesta<Void> informarFeedbackOferta(OfertaComercialView oferta, String action);

	/**
	 * Obtener ofertas por seccion.
	 *
	 * @return the respuesta
	 */
	Respuesta<OfertasComercialesView> obtenerOfertasPorSeccion();
	
	/**
	 * Registrar encuesta.
	 *
	 * @param respuestasEncuesta
	 *            the respuestas encuesta
	 * @return the respuesta
	 */
	Respuesta<Void> registrarEncuesta(List<RespuestaEncuestaView> respuestasEncuesta);

	/**
	 * Informar Gestion AC.
	 *
	 * @param domicilioDeReemplazo
	 *            the domicilio de reemplazo
	 * @return the respuesta
	 */
	Respuesta<Void> informarGestionAC(DomicilioView domicilioDeReemplazo);

	/**
	 * Obetener notificaciones perfil.
	 *
	 * @param cliente the cliente
	 * @return the respuesta
	 */
	Respuesta<NotificacionesComercialesView> obetenerNotificacionesPerfil(Cliente cliente);

	/**
	 * Obtener premiaciones.
	 *
	 * @param fechaCorte
	 * 		the fecha corte
	 * @return the respuesta
	 */
	Respuesta<ChanceView> obtenerPremiaciones(String fechaCorte);

	/**
	 * Actualizar cuenta favorita.
	 *
	 * @return the respuesta
	 */
	Respuesta<NumeroCuentaFavoritaView> actualizarCuentaFavorita();
	
	/**
	 * Actualizar tarjeta favorita.
	 *
	 * @return the respuesta
	 */
	Respuesta<NumeroTarjetaFavoritaView> actualizarTarjetaFavorita();
	
	/**
	 * Cargar contratos transferencia.
	 */
	void cargarContratosTransferencia();

	/**
	 * Confirmar promesa pago.
	 *
	 * @return the respuesta
	 */
	Respuesta<ConfirmarPromesaPagoView> confirmarPromesaPago();

	/**
	 * Finalizar promesa pago.
	 *
	 * @param inView the in view
	 * @return the respuesta
	 */
	Respuesta<FinalizarPromesaPagoOutView> finalizarPromesaPago(FinalizarPromesaPagoInView inView);
	
	/**
	 * Grabar estadistica ayuda promesa pago.
	 */
	void grabarEstadisticaAyudaPromesaPago();
	
	/**
	 * Grabar estadistica ayuda descubierto PP.
	 */
	void grabarEstadisticaAyudaDescubiertoPP();
	
	/**
	 * Grabar estadistica configuracion promesa pago.
	 */
	void grabarEstadisticaConfiguracionPromesaPago();
	
	/**
	 * Obtener ofertas financiacion.
	 */
	Respuesta<OfertasFinanciacionOutView> obtenerOfertasFinanciacion();

	/**
	 * Solicitar financiacion.
	 *
	 * @param solicitarFinanciacionInView the solicitar financiacion in view
	 * @return the respuesta
	 */
	Respuesta<String> solicitarFinanciacion(SolicitarFinanciacionInView solicitarFinanciacionInView);

	/**
	 * Mostrar contratos ty C.
	 *
	 * @return the respuesta
	 */
	Respuesta<AceptarTyCView> mostrarContratosTyC();
	
	/**
	 * Aceptar contratos ty C.
	 *
	 * @return the respuesta
	 */
	Respuesta<AceptarTyCView> aceptarContratosTyC();
	
	/**
	 * Grabar estadistica carrusel mostrar contratos ty C.
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> grabarEstadisticaCarruselMostrarContratosTyC();
	
	/**
	 * Grabar estadistica recordar mas tarde contratos ty C.
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> grabarEstadisticaRecordarMasTardeContratosTyC();
	

	Respuesta<ContactabilidadView> informarAceptacionContacto(String idOferta);
	
	Respuesta<Void> cerrarStackPoliticasPrivacidad();
	
	Respuesta<AceptarTyCView> grabarEstadisticaVerTycPoliticasPrivacidad();
	
	Respuesta<TarjetaMastercardBlackView> iniciarFlujoMastercardBlack();
	
	Respuesta<TarjetaMastercardBlackView> solicitarMastercardBlack();

	Respuesta<TyCDuoView> aceptaTyCDUO();
		
}
