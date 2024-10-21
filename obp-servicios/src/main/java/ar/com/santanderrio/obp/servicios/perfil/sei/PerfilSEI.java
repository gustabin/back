/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.sei;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.ext.MessageContext;

import ar.com.santanderrio.base.web.view.FeedbackMensajeView;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.blanqueopin.web.view.BlanqueoPinInicioView;
import ar.com.santanderrio.obp.servicios.bonificacionesvigentes.view.BonificacionVigenteView;
import ar.com.santanderrio.obp.servicios.clientes.web.view.CambioClaveUsuarioView;
import ar.com.santanderrio.obp.servicios.clientes.web.view.CambioDatosContactoResponse;
import ar.com.santanderrio.obp.servicios.clientes.web.view.CambioDatosContactoView;
import ar.com.santanderrio.obp.servicios.clientes.web.view.CambioUsuarioView;
import ar.com.santanderrio.obp.servicios.clientes.web.view.ComprobanteClaveUsuarioView;
import ar.com.santanderrio.obp.servicios.clientes.web.view.MarcaAPNHResponse;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.mya.web.view.ConfirmarMailViewIn;
import ar.com.santanderrio.obp.servicios.mya.web.view.DescargaContratoViewIn;
import ar.com.santanderrio.obp.servicios.mya.web.view.InicioGeneralSuscripcionMyAView;
import ar.com.santanderrio.obp.servicios.mya.web.view.InicioProductoMyAViewIn;
import ar.com.santanderrio.obp.servicios.mya.web.view.InicioProductoSuscripcionMyAView;
import ar.com.santanderrio.obp.servicios.mya.web.view.MyaUpdateMensajeView;
import ar.com.santanderrio.obp.servicios.perfil.entities.ContratosPerfil;
import ar.com.santanderrio.obp.servicios.perfil.view.BlanqueoPinView;
import ar.com.santanderrio.obp.servicios.perfil.view.CambioNombreView;
import ar.com.santanderrio.obp.servicios.perfil.view.ConfirmarMailView;
import ar.com.santanderrio.obp.servicios.perfil.view.ConsultaPerfil;
import ar.com.santanderrio.obp.servicios.perfil.view.LogoutAppView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.CambioDomicilioView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.ComprobanteCambioDomicilioView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.ComprobantePreguntasSeguridadView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.PerfilDetalleDeudorView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.PerfilView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.PreguntasSeguridadView;

/**
 * The Interface PerfilSEI.
 */
@Path("/perfil")
public interface PerfilSEI {

	/**
	 * Obtener comprobante stop debit.
	 *
	 * @author alejandro.vigano
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerPerfil")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<PerfilView> obtenerPerfil();

	/**
	 * Obtener detalle deudores.
	 *
	 * @author B043069
	 * @param consultaPerfil
	 *            the consulta perfil
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerDetalleDeudor")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<PerfilDetalleDeudorView> obtenerDetalleDeudor(ConsultaPerfil consultaPerfil);

	/**
	 * Gets the marca APNH.
	 *
	 * @return the marca APNH
	 */
	@POST
	@Path("/marcaAPNH")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<MarcaAPNHResponse> getMarcaAPNH();

	/**
	 * Cambio clave usuario.
	 *
	 * @param mc
	 *            contexto
	 * @param cambioUsuarioView
	 *            the cambio usuario view
	 * @return the respuesta
	 */
	@POST
	@Path("/cambioClaveUsuario")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<CambioClaveUsuarioView> cambioClaveUsuario(@Context MessageContext mc,
			CambioClaveUsuarioView cambioUsuarioView);

	/**
	 * Cambio usuario.
	 *
	 * @param mc
	 *            contexto
	 * @param cambioUsuarioView
	 *            the cambio usuario view
	 * @return the respuesta
	 */
	@POST
	@Path("/cambioUsuario")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<CambioUsuarioView> cambioUsuario(@Context MessageContext mc, CambioUsuarioView cambioUsuarioView);

	/**
	 * Gets the datos contacto.
	 *
	 * @return the datos contacto
	 */
	@POST
	@Path("/getDatosContacto")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<CambioDatosContactoResponse> getDatosContacto();

	/**
	 * Cambio datos contacto.
	 *
	 * @param cambioDatosContacto
	 *            the cambio datos contacto
	 * @return the respuesta
	 */
	@POST
	@Path("/cambioDatosMail")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<CambioDatosContactoView> cambioDatosMail(CambioDatosContactoView cambioDatosContacto);

	/**
	 * Cambio datos celular.
	 *
	 * @param cambioDatosContacto
	 *            the cambio datos contacto
	 * @return the respuesta
	 */
	@POST
	@Path("/cambioDatosCelular")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<CambioDatosContactoView> cambioDatosCelular(CambioDatosContactoView cambioDatosContacto);

	/**
	 * Gets the comprobante clave usuario.
	 *
	 * @return the comprobante clave usuario
	 */
	@POST
	@Path("/perfilClaveUsuarioComprobante")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<ComprobanteClaveUsuarioView> getComprobanteClaveUsuario();

	/**
	 * Descargar comprobante.
	 *
	 * @param tipoComprobante
	 *            the tipo comprobante
	 * @return the respuesta
	 */
	@POST
	@Path("/descargarComprobante")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> descargarComprobante(String tipoComprobante);

	/**
	 * obtenerInfoAdicionalDomTel.
	 *
	 * @param cambioDomicilioView
	 *            the cambio domicilio view
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerInfoAdicionalDomTel")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<FeedbackMensajeView> obtenerInfoAdicionalDomTel(CambioDomicilioView cambioDomicilioView);

	/**
	 * guardarCambioDomicilio.
	 *
	 * @param cambioDomicilioView
	 *            the cambio domicilio view
	 * @return the respuesta
	 */
	@POST
	@Path("/guardarCambioDomicilio")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<CambioDomicilioView> guardarCambioDomicilio(CambioDomicilioView cambioDomicilioView);

	/**
	 * normalizarDomicilio.
	 *
	 * @param cambioDomicilioView
	 *            the cambio domicilio view
	 * @return the respuesta
	 */
	@POST
	@Path("/normalizarDomicilio")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<List<CambioDomicilioView>> normalizarDomicilio(CambioDomicilioView cambioDomicilioView);

	/**
	 * verComprobante CambioDomicilio.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/verComprobanteCambioDomicilio")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ComprobanteCambioDomicilioView> verComprobanteCambioDomicilio();

	/**
	 * Descargar comprobante cambio domicilio.
	 *
	 * @return Reporte Comprobante
	 */
	@POST
	@Path("/descargarComprobanteCambioDomicilio")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> descargarComprobanteCambioDomicilio();

	/**
	 * Consultar preguntas seguridad.
	 *
	 * @return PreguntasSeguridadView
	 */
	@POST
	@Path("/consultarPreguntasSeguridad")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<PreguntasSeguridadView> consultarPreguntasSeguridad();

	/**
	 * Guardar preguntas seguridad.
	 *
	 * @param preguntasSeguridadView
	 *            the preguntas seguridad view
	 * @return Respuesta
	 */
	@POST
	@Path("/guardarPreguntasSeguridad")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<PreguntasSeguridadView> guardarPreguntasSeguridad(PreguntasSeguridadView preguntasSeguridadView);

	/**
	 * Ver comprobante de preguntas de seguridad.
	 *
	 * @return Reporte Comprobante
	 */
	@POST
	@Path("/verComprobantePreguntasSeguridad")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ComprobantePreguntasSeguridadView> verComprobantePreguntasSeguridad();

	/**
	 * Descargar comprobante preguntas seguridad.
	 *
	 * @return Reporte Comprobante
	 */
	@POST
	@Path("/descargarComprobantePreguntasSeguridad")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> descargarComprobantePreguntasSeguridad();

	/**
	 * Ingreso a suscripcion MyA.
	 *
	 * @return Respuesta<InicioGeneralSuscripcionMyAView>
	 */
	@POST
	@Path("/inicioMensajesAvisos")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<InicioGeneralSuscripcionMyAView> inicioGeneralSuscripcionMyA();

	/**
	 * Pantalla de configuracion de notificacion MyA de un producto en particular.
	 *
	 * @param producto
	 *            the producto
	 * @return Respuesta<InicioProductoSuscripcionMyAView>
	 */
	@POST
	@Path("/inicioProductoMensajesAvisos")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<InicioProductoSuscripcionMyAView> inicioProductoMyA(InicioProductoMyAViewIn producto);

	/**
	 * Update mensajes.
	 *
	 * @param myaUpdateMensajeView
	 *            the mya update mensaje view
	 * @return the respuesta
	 */
	@POST
	@Path("/updateMensajesMya")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> updateMensajes(MyaUpdateMensajeView myaUpdateMensajeView);

	/**
	 * Consulta de contratos.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/consultaContratos")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<ContratosPerfil> consultaContratos();

	/**
	 * Grabado de estadistica de descarga de contratos.
	 *
	 * @param viewIn
	 *            the view in
	 * @return the respuesta
	 */
	@POST
	@Path("/estadisticaDescargaContrato")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> estadisticaDescargaContrato(DescargaContratoViewIn viewIn);

	/**
	 * Inicio de blanqueo de pin
	 * @return BlanqueoPinInicioView con lista de tarjetas y mensajes de bbdd
	 */
	@POST
	@Path("/blanqueoPin")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<BlanqueoPinInicioView> inicioBlanqueoPin();

	@POST
	@Path("/blanqueoPinRSA")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<BlanqueoPinView> blanqueoPinRSA(BlanqueoPinView blanqueoPin);

	@POST
	@Path("/logoutAppConfiguracion")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<LogoutAppView> logoutAppConfiguracion();

	@POST
	@Path("/logoutAppFeedback")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<LogoutAppView> logoutAppFeedback();
	
	@POST
	@Path("/confirmarEmail")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConfirmarMailView> confirmarEmail(ConfirmarMailViewIn confirmarMailIn);
	
	@POST
	@Path("/obtenerBonificaciones")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<List<BonificacionVigenteView>> obtenerBonificaciones();
	
	@POST
	@Path("/grabarEstadisticaVerBonificaciones")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<Void> grabarEstadisticaVerBonificaciones();
	
	@POST
	@Path("/cambioNombreConfig")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<CambioNombreView> cambioNombreConfig();
	
	@POST
	@Path("/cambioNombreFeedback")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<CambioNombreView> cambioNombreFeedback(CambioNombreView cambioNombreView);

	@POST
	@Path("/descargarDatosPersonalesPDF")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> descargarDatosPersonalesPDF(ReporteView reporteView);

}
