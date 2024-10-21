/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.AdjuntarArchivosDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ArchivoTransferenciaView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaDetalleTrfOBPInView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaDetalleTrfOBPOutView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaOperacionesInView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaOperacionesOutView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ProcesarTransferenciaComexView;

/**
 * The Interface ComexCanalesManager.
 *
 * @author IT Resources
 */
public interface ComexCanalesManager {

	/**
	 * Consulta detalle trf.
	 *
	 * @param consultaDetalleTrfOBPInView
	 *            the consulta detalle trf OBP in view
	 * @return the respuesta
	 */
	Respuesta<ConsultaDetalleTrfOBPOutView> consultaDetalleTrf(ConsultaDetalleTrfOBPInView consultaDetalleTrfOBPInView);

	/**
	 * Consulta operaciones.
	 *
	 * @param consultaOperacionesInView
	 *            the consulta operaciones in view
	 * @return the respuesta
	 */
	Respuesta<ConsultaOperacionesOutView> consultaOperaciones(ConsultaOperacionesInView consultaOperacionesInView);

	/**
	 * Consulta operaciones mostrar mas.
	 *
	 * @return the respuesta
	 */
	Respuesta<ConsultaOperacionesOutView> consultaOperacionesMostrarMas();

	/**
	 * Procesar transferencia guardar.
	 *
	 * @param procesarTransferenciaComexView
	 *            the procesar transferencia comex view
	 * @return the respuesta
	 */
	Respuesta<ProcesarTransferenciaComexView> procesarTransferenciaGuardar(ProcesarTransferenciaComexView procesarTransferenciaComexView);

	/**
	 * Procesar transferencia baja.
	 *
	 * @param procesarTransferenciaComexView
	 *            the procesar transferencia comex view
	 * @return the respuesta
	 */
	Respuesta<ProcesarTransferenciaComexView> procesarTransferenciaBaja(ProcesarTransferenciaComexView procesarTransferenciaComexView);

	/**
	 * Procesar transferencia alta.
	 *
	 * @param procesarTransferenciaComexView
	 *            the procesar transferencia comex view
	 * @return the respuesta
	 */
	Respuesta<ProcesarTransferenciaComexView> procesarTransferenciaAlta(ProcesarTransferenciaComexView procesarTransferenciaComexView);

	/**
	 * Adjuntar archivo.
	 *
	 * @param procesarTransferenciaComexView
	 *            the procesar transferencia comex view
	 * @return the respuesta
	 */
	Respuesta<ArchivoTransferenciaView> adjuntarArchivo(ProcesarTransferenciaComexView procesarTransferenciaComexView);


	
	/**
	 * Borrar archivo.
	 *
	 * @param archivoTransferenciaView
	 *            the archivo transferencia view
	 * @return the respuesta
	 */
	Respuesta<Void> borrarArchivo(ArchivoTransferenciaView archivoTransferenciaView);

	

	/**
	 * Obtener archivo ws comex.
	 *
	 * @param archivoTransferenciaView
	 *            the archivo transferencia view
	 * @return the respuesta
	 */

	Respuesta<AdjuntarArchivosDTO> obtenerArchivoWsComex(ArchivoTransferenciaView archivoTransferenciaView);

}
