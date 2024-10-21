/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.sei;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.DestinatarioView;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.TipoDeCuentaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

/**
 * The Interface TransferenciaSEI.
 */
@Path("/transferencias")
public interface TransferenciaSEI {

	/** The Constant NUEVA_TRANSFERENCIA. */
	String NUEVA_TRANSFERENCIA = "nuevaTransferencia";

	/** The Constant OBTENER_TIPOS_DE_CUENTA. */
	String OBTENER_TIPOS_DE_CUENTA = "obtenerTiposDeCuenta";

	/** The Constant OBTENER_AGENDA_DESTINATARIOS. */
	String OBTENER_AGENDA_DESTINATARIOS = "obtenerAgendaDestinatarios";

	/** The Constant EJECUTAR_NUEVA_TRANSFERENCIA. */
	String EJECUTAR_NUEVA_TRANSFERENCIA = "ejecutarNuevaTransferencia";

	/** The Constant CONSULTAR_TITULARIDAD. */
	String CONSULTAR_TITULARIDAD = "consultarTitularidad";

	/** The Constant OBTENER_INFORMACION_DESTINATARIO. */
	String OBTENER_INFORMACION_DESTINATARIO = "obtenerInformacionDestinatario";

	/** The Constant VALIDAR_CONTRATO_TRANSFERENCIA. */
	String VALIDAR_CONTRATO_TRANSFERENCIA = "/validarContratoTransferencia";

	/** The Constant ACTUALIZAR_SALDO. */
	String ACTUALIZAR_SALDO = "/actualizarSaldo";

	/** The grabar estadisticas acceso ayuda. */
	String GRABAR_ESTADISTICAS_ACCESO_AYUDA = "/grabarEstadisticasAyuda";
	
	/** The grabar estadisticas acceso transferencias. */
	String GRABAR_ESTADISTICAS_ACCESO_TRANSFERENCIAS = "/grabarEstadisticasAcceso";
	
	/** The consultar horarios. */
	String CONSULTAR_HORARIOS = "/consultarHorarios";

	/**
	 * Retorna una nueva transferencia view.
	 *
	 * @param transferencia
	 *            the transferencia
	 * @param mc
         *            the mc
	 * @return the nueva transferencia
	 */
	@POST
	@Path(NUEVA_TRANSFERENCIA)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<TransferenciaView> getNuevaTransferencia(TransferenciaView transferencia,
	        @Context org.apache.cxf.jaxrs.ext.MessageContext mc);

	/**
	 * Obtener tipos de cuenta.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path(OBTENER_TIPOS_DE_CUENTA)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<List<TipoDeCuentaView>> obtenerTiposDeCuenta();

	/**
	 * Ejecutar nueva transferencia.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @return the respuesta
	 */
	@POST
	@Path(EJECUTAR_NUEVA_TRANSFERENCIA)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<TransferenciaView> ejecutarNuevaTransferencia(TransferenciaView transferenciaView,
															@Context org.apache.cxf.jaxrs.ext.MessageContext mc);

	/**
	 * Consulta la titularidad. En el caso que el usuario modifique la cuenta de
	 * origen se debe volver a consultar la titularidad (CNSTITCBU110).
	 *
	 * @param transferencia
	 *            the transferencia
	 * @param mc
	 *            the mc
	 * @return the respuesta
	 */
	@POST
	@Path(CONSULTAR_TITULARIDAD)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<TransferenciaView> consultarTitularidad(TransferenciaView transferencia,
			@Context org.apache.cxf.jaxrs.ext.MessageContext mc);

	/**
	 * Obtener informacion destinatario.
	 *
	 * @param destinatarioView
	 *            the destinatario view
	 * @return the respuesta
	 */
	@POST
	@Path(OBTENER_INFORMACION_DESTINATARIO)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<TransferenciaView> obtenerInformacionDestinatario(DestinatarioView destinatarioView);

	/**
	 * Validar contrato transferencia.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path(VALIDAR_CONTRATO_TRANSFERENCIA)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<Boolean> validarContratoTransferencia();

	/**
	 * Actualizar saldo.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path(ACTUALIZAR_SALDO)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<CuentasView> actualizarSaldo();

	/**
	 * Descargar comprobante PDF.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/descargarComprobantePDF")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ReporteView> descargarComprobantePDF();

	/**
	 * Este metodo se encarga de grabar las estadisticas de acceso al componente
	 * de ayuda.
	 *
	 * @author emilio.watemberg.
	 * @return the respuesta
	 * @since Aug 7, 2017.
	 */
	@POST
	@Path(GRABAR_ESTADISTICAS_ACCESO_AYUDA)
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<TransferenciaView> grabarEstadisticasAccesoAyuda();
	
	/**
	 * Se graba una estadistica cuando se salta de cuentas a transferencias.
	 */
	@POST
	@Path(GRABAR_ESTADISTICAS_ACCESO_TRANSFERENCIAS)
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	void grabarEstadisticasAccesoTransferencias();
	
	/**
	 * Consultar horarios.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path(CONSULTAR_HORARIOS)
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<TransferenciaView> consultarHorarios();
	
}
