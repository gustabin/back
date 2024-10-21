/*
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.sei.impl;

import java.util.List;

import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.base.web.view.View;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.manager.GestorEventosComercialesManager;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.sei.GestorEventosComercialesSEI;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view.DomicilioView;

/**
 * The Class GestorEventosComercialesSEIImpl.
 */
@Component("gestorEventosComercialesSEI")
public class GestorEventosComercialesSEIImpl implements GestorEventosComercialesSEI {

	/** The sesion cliente. */
	@Autowired
	public SesionCliente sesionCliente;

	/** The gestor evento comercial manager. */
	@Autowired
	private GestorEventosComercialesManager gestorEventoComercialManager;

	/**
	 * Obtener cantidad notificaciones.
	 *
	 * @return the respuesta
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.sei.
	 * GestorEventosComercialesSEI#obtenerCantidadNotificaciones()
	 */
	@Override
	public Respuesta<NotificacionesComercialesView> obtenerCantidadNotificaciones() {
		return gestorEventoComercialManager.obtenerCantidadNotificaciones(sesionCliente.getCliente());
	}

	/**
	 * Obtener notificaciones.
	 *
	 * @return the respuesta
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.sei.
	 * GestorEventosComercialesSEI#obtenerNotificaciones()
	 */
	@Override
	public Respuesta<NotificacionesComercialesView> obtenerNotificaciones() {
		return gestorEventoComercialManager.obtenerNotificaciones(sesionCliente.getCliente());
	}
	
	/**
	 * Borrar notificacion.
	 *
	 * @param oferta the oferta
	 * @return the respuesta
	 */
	@Override
	public Respuesta<OfertaComercialView> borrarNotificacion(OfertaComercialView oferta) {
		return gestorEventoComercialManager.borrarNotificacion(sesionCliente.getCliente(), oferta);
	}
	
	
	/**
	 * Confirmar promesa pago.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ConfirmarPromesaPagoView> confirmarPromesaPago() {
		return gestorEventoComercialManager.confirmarPromesaPago();
	}
	
	/**
	 * Finalizar promesa pago.
	 *
	 * @param inView the in view
	 * @return the respuesta
	 */
	@Override
	public Respuesta<FinalizarPromesaPagoOutView> finalizarPromesaPago(FinalizarPromesaPagoInView inView) {		
		return gestorEventoComercialManager.finalizarPromesaPago(inView);
	}
	
	
	/**
	 * Actualizar notificaciones.
	 *
	 * @return the respuesta
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.sei.
	 * GestorEventosComercialesSEI#actualizarNotificaciones()
	 */
	@Override
	public Respuesta<Boolean> actualizarNotificaciones() {
		return gestorEventoComercialManager.actualizarNotificaciones(sesionCliente.getCliente());
	}

	/**
	 * Informa el interes en notificacion.
	 *
	 * @param view
	 *            the view
	 * @return the respuesta
	 */
	@Override
	public Respuesta<Void> informarInteresEnNotificacion(View view) {
		return gestorEventoComercialManager.informarInteresEnNotificacion(view.getId());
	}

	/**
	 * Obtener carrusel.
	 *
	 * @return the respuesta
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.sei.
	 * GestorEventosComercialesSEI#obtenerCarrusel()
	 */
	@Override
	public Respuesta<OfertasComercialesView> obtenerCarrusel() {
		return gestorEventoComercialManager.obtenerCarrusel();
	}

	/**
	 * Informar interes oferta.
	 *
	 * @param oferta the oferta
	 * @return the respuesta
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.sei.
	 * GestorEventosComercialesSEI#informarInteresOferta(ar.com.santanderrio.obp
	 * .servicios.gestor.eventoscomerciales.view.OfertaCarruselView)
	 */
	@Override
	public Respuesta<Void> informarInteresOferta(OfertaComercialView oferta) {
		return gestorEventoComercialManager.informarFeedbackOferta(oferta,"CLICKED");
	}
	@Override
	public Respuesta<Void> informarVistaOferta(OfertaComercialView oferta) {
		return gestorEventoComercialManager.informarFeedbackOferta(oferta,"VIEWED");
	}
	/**
	 * Obtener ofertas por seccion.
	 *
	 * @return the respuesta
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.sei.
	 * GestorEventosComercialesSEI#obtenerOfertasPorSeccion()
	 */
	@Override
	public Respuesta<OfertasComercialesView> obtenerOfertasPorSeccion() {
		return gestorEventoComercialManager.obtenerOfertasPorSeccion();
	}

	/**
	 * Informa que el cliente rechazo la oferta.
	 *
	 * @param oferta
	 *            the oferta
	 * @return the respuesta
	 */
	@Override
	public Respuesta<Void> informarRechazoOferta(OfertaComercialView oferta) {
		return gestorEventoComercialManager.informarFeedbackOferta(oferta,"REJECTED");
	}

	/**
	 * Registrar encuesta.
	 *
	 * @param respuestasEncuesta
	 *            the respuestas encuesta
	 * @return the respuesta
	 */
	@Override
	public Respuesta<Void> registrarEncuesta(List<RespuestaEncuestaView> respuestasEncuesta) {
		return gestorEventoComercialManager.registrarEncuesta(respuestasEncuesta);
	}
	
	/**
	 * Informar Gestion AC.
	 *
	 * @param domicilioDeReemplazo
	 *            the domicilio de reemplazo
	 * @return the respuesta
	 */
	@Override
	public Respuesta<Void> informarGestionAC(DomicilioView domicilioDeReemplazo) {
		return gestorEventoComercialManager.informarGestionAC(domicilioDeReemplazo);
	}

	
	/**
	 * obetener Notificaciones Perfil.
	 *
	 * @return the RespuestaNotificacionesPerfilView
	 */
	@Override
	public Respuesta<NotificacionesComercialesView> obetenerNotificacionesPerfil() {
		return gestorEventoComercialManager.obetenerNotificacionesPerfil(sesionCliente.getCliente());
	}

	/**
	 * Obtener premiaciones.
	 *
	 * @param chance the chance
	 * @return the RespuestaChanceView
	 */
	@Override
	public Respuesta<ChanceView> obtenerPremiaciones(ChanceView chance) {
		return gestorEventoComercialManager.obtenerPremiaciones(chance.getFechaCorte());
	}

	/**
	 * Actualizar cuenta favorita.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<NumeroCuentaFavoritaView> actualizarCuentaFavorita() {
		return gestorEventoComercialManager.actualizarCuentaFavorita();
	}

	/**
	 * Actualizar tarjeta favorita.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<NumeroTarjetaFavoritaView> actualizarTarjetaFavorita() {
		return gestorEventoComercialManager.actualizarTarjetaFavorita();
	}

	/**
	 * Cargar contratos transferencia.
	 */
	@Override
	public void cargarContratosTransferencia() {
		gestorEventoComercialManager.cargarContratosTransferencia();
	}

	/**
	 * Grabar estadistica ayuda promesa pago.
	 */
	@Override
	public void grabarEstadisticaAyudaPromesaPago() {
		gestorEventoComercialManager.grabarEstadisticaAyudaPromesaPago();	
	}

	/**
	 * Grabar estadistica ayuda descubierto PP.
	 */
	@Override
	public void grabarEstadisticaAyudaDescubiertoPP() {
		gestorEventoComercialManager.grabarEstadisticaAyudaDescubiertoPP();		
	}

	/**
	 * Grabar estadistica configuracion promesa pago.
	 */
	@Override
	public void grabarEstadisticaConfiguracionPromesaPago() {
		gestorEventoComercialManager.grabarEstadisticaConfiguracionPromesaPago();		
	}

	/**
	 * Mostrar contratos ty C.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<AceptarTyCView> mostrarContratosTyC() {
		return gestorEventoComercialManager.mostrarContratosTyC();	
	}

	/**
	 * Aceptar contratos ty C.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<AceptarTyCView> aceptarContratosTyC() {
		return gestorEventoComercialManager.aceptarContratosTyC();	

	}

	/**
	 * Obtener ofertas financiacion.
	 */
	@Override
	public Respuesta<OfertasFinanciacionOutView> obtenerOfertasFinanciacion() {
		return gestorEventoComercialManager.obtenerOfertasFinanciacion();
	}

	/**
	 * Solicitar financiacion.
	 *
	 * @param solicitarFinanciacionInView the solicitar financiacion in view
	 * @return the respuesta
	 */
	@Override
	public Respuesta<String> solicitarFinanciacion(SolicitarFinanciacionInView solicitarFinanciacionInView) {
		return gestorEventoComercialManager.solicitarFinanciacion(solicitarFinanciacionInView);
	}

	/**
	 * Grabar estadistica carrusel mostrar contratos ty C.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<Void> grabarEstadisticaCarruselMostrarContratosTyC() {
		return gestorEventoComercialManager.grabarEstadisticaCarruselMostrarContratosTyC();	
	}

	/**
	 * Grabar estadistica recordar mas tarde contratos ty C.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<Void> grabarEstadisticaRecordarMasTardeContratosTyC() {
		return gestorEventoComercialManager.grabarEstadisticaRecordarMasTardeContratosTyC();	
	}

	@Override
	public Respuesta<ContactabilidadView> informarAceptacionContacto(ContactabilidadView contactabilidadView) {
		return gestorEventoComercialManager.informarAceptacionContacto(contactabilidadView.getIdOferta());
	}

	@Override
	public Respuesta<Void> cerrarStackPoliticasPrivacidad() {
		return gestorEventoComercialManager.cerrarStackPoliticasPrivacidad();
	}

	@Override
	public Respuesta<AceptarTyCView> grabarEstadisticaVerTycPoliticasPrivacidad() {
		return gestorEventoComercialManager.grabarEstadisticaVerTycPoliticasPrivacidad();	
	}

	@Override
	public Respuesta<TarjetaMastercardBlackView> iniciarFlujoMastercardBlack() {
		return gestorEventoComercialManager.iniciarFlujoMastercardBlack();
	}

	@Override
	public Respuesta<TarjetaMastercardBlackView> solicitarMastercardBlack() {
		return gestorEventoComercialManager.solicitarMastercardBlack();
	}

	@Override
	public Respuesta<TyCDuoView> aceptaTyCDUO() {
		return gestorEventoComercialManager.aceptaTyCDUO();
	}
		
}
