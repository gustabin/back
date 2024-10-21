/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ComprobanteInView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConfiguracionAdjuntarArchivosOutView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaBancosInView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaBancosOutView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaConceptoOutView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.DatosBeneficiarioOutView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ProcesarTransferenciaComexView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ReporteView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.TransferenciaComexOutView;


/**
 * The Interface TransferenciaExteriorSEI.
 *
 * @author B040619
 */
@Path("/transferenciaExterior")
public interface ComexConsultasSEI {
	
	
	/** The Constant NUEVA_TRANSFERENCIA. */
	String CONFIGURACION = "/configuracion";

	/** The Constant OBTENER_DATOS_BENEFICIARIO. */
	String OBTENER_DATOS_BENEFICIARIO = "/obtenerDatosBeneficiario";
		
	/** The Constant OBTENER_BANCOS. */
	String OBTENER_BANCOS ="/obtenerBancos";
	
	/** The Constant DESCARGAR_COMPROBANTE. */
	String DESCARGAR_COMPROBANTE ="/descargarComprobante";
	
	/** The Constant ADJUNTAR_ARCHIVOS_CONFIGURACION. */
	String ADJUNTAR_ARCHIVOS_CONFIGURACION ="/configuracionAdjuntarArchivos";
	
	/** The Constant OBTENER_CONSULTA_CONCEPTO. */
	String OBTENER_CONSULTA_CONCEPTO ="/obtenerConsultaConcepto";
	
	/** The Constant OBTENER_MSJ_VINCULANTE. */
	String OBTENER_MSJ_VINCULANTE = "/obtenerMensajeVinculante";
	
	/** The Constant DESCARGAR_NORMATIVA. */
	String DESCARGAR_NORMATIVA = "/descargarNormativa";	
	
	/** The Constant ESTADISTICA_VINCULANTE. */
	String ESTADISTICA_VINCULANTE = "/estadisticaVinculante";	

	/** The Constant ESTADISTICA_DATOS_BENEFICIARIO. */
	String ESTADISTICA_DATOS_BENEFICIARIO = "/estadisticaDatosBeneficiario";	
	
	/** The Constant ESTADISTICA_DATOS_TRANSFERENCIA. */
	String ESTADISTICA_DATOS_TRANSFERENCIA = "/estadisticaDatosTransferencia";	

	/** The Constant ESTADISTICA_INGRESO_ADJUNTAR. */
	String ESTADISTICA_INGRESO_ADJUNTAR = "/estadisticaIngresoAdjuntar";	

	/** The Constant DESCARGAR_COMPROBANTE. */
	String ESTADISTICA_VER_COMPROBANTE ="/estadisticaVerComprobante";
	
    /**
	 * Obtener datos de beneficiario.
	 *
	 * @return Respuesta
	 */
	@POST
	@Path(OBTENER_DATOS_BENEFICIARIO)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON})
	Respuesta<DatosBeneficiarioOutView> getDatosBeneficiario();
	
	/**
	 * Obtiene datos iniciales.
	 * 
	 * @return respuesta
	 */
	@POST
	@Path(CONFIGURACION)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<TransferenciaComexOutView> obtenerDatosComex();

	/**
	 * ObtenerBancos.
	 *
	 * @param consultaBancosInView
	 *            the consulta bancos in view
	 * @return respuesta
	 */
	@POST
	@Path(OBTENER_BANCOS)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConsultaBancosOutView> obtenerBancos(ConsultaBancosInView consultaBancosInView);
	
	/**
	 * descargar Comprobante.
	 *
	 * @param documentacion
	 *            the documentacion
	 * @return respuesta
	 */
	@POST
	@Path(DESCARGAR_COMPROBANTE)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> descargarComprobante(ComprobanteInView documentacion);
	
	/**
	 * obtener informacion archivo.
	 *
	 * @return respuesta
	 */
	@POST
	@Path(ADJUNTAR_ARCHIVOS_CONFIGURACION)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConfiguracionAdjuntarArchivosOutView> obtenerConfiguracionAdjuntarArchivo();
	
	/**
	 * obtiene consulta concepto
	 *
	 * @return respuesta
	 */
	@POST
	@Path(OBTENER_CONSULTA_CONCEPTO)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConsultaConceptoOutView> obtenerConsultaConcepto(int idConcepto);
	
	/**
	 * obtiene mensaje de ayuda - pregunta de vinculante
	 *
	 * @return respuesta
	 */
	@POST
	@Path(OBTENER_MSJ_VINCULANTE)
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<String> obtenerMensajeVinculante();

	
	/**
	 * descarga de pdf - normativa vinculante
	 *
	 * @return respuesta
	 */
	@POST
	@Path(DESCARGAR_NORMATIVA)
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ReporteView> descargarNormativa();
	
	/**
	 * graba estadistica vinculante si/no + acceso a confirmacion
	 *
	 * @return respuesta
	 */
	@POST
	@Path(ESTADISTICA_VINCULANTE)
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<Void> estadisticaVinculante(ProcesarTransferenciaComexView view);

	/**
	 * graba estadistica guardar datos beneficiario
	 *
	 * @return respuesta
	 */
	@POST
	@Path(ESTADISTICA_DATOS_BENEFICIARIO)
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<Void> estadisticaDatosBeneficiario();

	/**
	 * graba estadistica guardar datos transferencia
	 *
	 * @return respuesta
	 */
	@POST
	@Path(ESTADISTICA_DATOS_TRANSFERENCIA)
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<Void> estadisticaDatosTransferencia();
	
	/**
	 * graba estadistica al ingresar a adjuntar
	 *
	 * @return respuesta
	 */
	@POST
	@Path(ESTADISTICA_INGRESO_ADJUNTAR)
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<Void> estadisticaIngresoAdjuntar();

	
	@POST
	@Path(ESTADISTICA_VER_COMPROBANTE)
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<Void> estadisticaVerComprobante();
	

}