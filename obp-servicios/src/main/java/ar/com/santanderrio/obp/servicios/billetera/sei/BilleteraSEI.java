/*
 * 
 */
package ar.com.santanderrio.obp.servicios.billetera.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.billetera.web.view.BilleteraView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.ComprobanteBilleteraView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.CrearUsuarioBilleteraView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.IngresoBilleteraInView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.PreConfirmarBilleteraView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.RecuperoClaveBilleteraView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.ValidarUsuarioBilleteraInView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.ValidarUsuarioBilleteraView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;

/**
 * The Interface BilleteraSEI.
 */
@Path("/billetera")
public interface BilleteraSEI {

	/**
	 * Procesa confirmacion de alta de usuario de Billetera.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return View asociado
	 */
	@POST
	@Path("/confirmarAltaUsuario")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<BilleteraView> confirmarAltaUsuario(BilleteraView viewRequest);

	/**
	 * Procesa confirmacion de configuracion de Billetera.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return View asociado
	 */
	@POST
	@Path("/confirmarConfiguracion")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<BilleteraView> confirmarConfiguracion(BilleteraView viewRequest);

	/**
	 * Creacion de usuario de Billetera.
	 *
	 * @return View asociado
	 */
	@POST
	@Path("/crearUsuario")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<CrearUsuarioBilleteraView> crearUsuario();

	/**
	 * Descarga de comprobante de adhesion a Billetera.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return View asociado
	 */
	@POST
	@Path("/descargaComprobanteAdhesion")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> descargaComprobanteAdhesion(ComprobanteBilleteraView viewRequest);

	/**
	 * Descarga de comprobante de configuracion a Billetera.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return View asociado
	 */
	@POST
	@Path("/descargaComprobanteConfiguracion")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> descargaComprobanteConfiguracion(ComprobanteBilleteraView viewRequest);

	/**
	 * Inicia proceso de confirmacion de alta de usuario de Billetera.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return View asociado
	 */
	@POST
	@Path("/preConfirmarAltaUsuario")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<PreConfirmarBilleteraView> preConfirmarAltaUsuario(PreConfirmarBilleteraView viewRequest);

	/**
	 * Inicia proceso de confirmacion de configuracion.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return View asociado
	 */
	@POST
	@Path("/preConfirmarConfiguracion")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<PreConfirmarBilleteraView> preConfirmarConfiguracion(PreConfirmarBilleteraView viewRequest);

	/**
	 * Primer ingreso a Billetera.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return View asociado
	 */
	@POST
	@Path("/primerIngreso")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ValidarUsuarioBilleteraView> primerIngreso(IngresoBilleteraInView viewRequest);

	/**
	 * Recupero de clave Billetera.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return View asociado
	 */
	@POST
	@Path("/recuperoClaveBilletera")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<RecuperoClaveBilleteraView> recuperoClaveBilletera(RecuperoClaveBilleteraView viewRequest);

	/**
	 * Verifica el estado del usuario de Billetera.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return View asociado
	 */
	@POST
	@Path("/validarUsuario")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ValidarUsuarioBilleteraView> validarUsuario(ValidarUsuarioBilleteraInView viewRequest);

}
