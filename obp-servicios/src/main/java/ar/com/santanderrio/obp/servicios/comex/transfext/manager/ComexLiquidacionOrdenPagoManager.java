package ar.com.santanderrio.obp.servicios.comex.transfext.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ArchivoTransferenciaView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.DocumentacionAdjuntaView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.FeedbackProcesarOrdenPago;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.InicioLiquidacionOrdenPagoView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ProcesarOrdenPagoInView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;

public interface ComexLiquidacionOrdenPagoManager {

	/**
	 * Configuracion liquidacion orden de pago
	 * 
	 * @return
	 */
	Respuesta<InicioLiquidacionOrdenPagoView> inicioLiquidacionOrdenPagoView();

	/**
	 * Limpia cache de conceptos
	 * 
	 * @return
	 */
	Respuesta<Boolean> limpiarConceptos();

	/**
	 * Adjunta archivos a la solicitud de a uno
	 * 
	 * @return
	 */
	Respuesta<ArchivoTransferenciaView> adjuntarArchivo(DocumentacionAdjuntaView documentacionAdjuntaView);

	/**
	 * Procesar orden de pago
	 * 
	 * @param procesarOrdenPagoInView
	 * @return
	 */
	Respuesta<FeedbackProcesarOrdenPago> procesarOrden(ProcesarOrdenPagoInView procesarOrdenPagoInView);

	/**
	 * Elimina un archivo de la solicitud
	 * 
	 * @return
	 */
	Respuesta<Void> borrarArchivo(ArchivoTransferenciaView documentacionAdjuntaView);

	/**
	 * Graba estadística pantalla configuración orden pago exterior
	 * 
	 * @return
	 */
	Respuesta<Void> configuracionOrdenPagoExt();

	/**
	 * Descarga comprobante
	 * 
	 * @return
	 */
	Respuesta<ReporteView> descargarComprobantePDF(Boolean esDescarga);

	/**
	 * Graba estadistica ver comprobante
	 * @return
	 */
	Respuesta<Void> verComprobante();
	
	/**
	 * Descarga comprobante
	 * 
	 * @return
	 */
	Respuesta<ReporteView> descargarNormativa();
	
	/**
	 * Graba estadistica al ingresar al stack para adjuntar archivos
	 * @return
	 */
	Respuesta<Void> estadisticaAdjuntar();

	Respuesta<String> obtenerMensajeVinculante();

}
