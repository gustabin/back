/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.sei.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.AdjuntarArchivosDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.manager.ComexCanalesManager;
import ar.com.santanderrio.obp.servicios.comex.transfext.sei.ComexCanalesSEI;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ArchivoTransferenciaView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaDetalleTrfOBPInView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaDetalleTrfOBPOutView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaOperacionesInView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaOperacionesOutView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ProcesarTransferenciaComexView;

/**
 * The Class ComexCanalesSEIImpl.
 *
 * @author IT Resources
 */
@Component("comexCanalesSEI")
public class ComexCanalesSEIImpl implements ComexCanalesSEI {

	/** The comex canales manager. */
	@Autowired
	private ComexCanalesManager comexCanalesManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comex.transfExt.sei.ComexCanalesSEI#
	 * consultaDetalleTrf(ar.com.santanderrio.obp.servicios.comex.transfExt.view.
	 * ConsultaDetalleTrfOBPInView)
	 */
	@Override
	public Respuesta<ConsultaDetalleTrfOBPOutView> consultaDetalleTrf(ConsultaDetalleTrfOBPInView consultaDetalleTrfOBPInView) {
		return comexCanalesManager.consultaDetalleTrf(consultaDetalleTrfOBPInView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comex.transfext.sei.ComexCanalesSEI#
	 * consultaOperaciones(ar.com.santanderrio.obp.servicios.comex.transfext.view.
	 * ConsultaOperacionesInView)
	 */
	@Override
	public Respuesta<ConsultaOperacionesOutView> consultaOperaciones(ConsultaOperacionesInView consultaOperacionesInView) {
		return comexCanalesManager.consultaOperaciones(consultaOperacionesInView);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comex.transfext.sei.ComexCanalesSEI#consultaOperacionesMostrarMas(ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaOperacionesInView)
	 */
	@Override
	public Respuesta<ConsultaOperacionesOutView> consultaOperacionesMostrarMas(ConsultaOperacionesInView consultaOperacionesInView) {
		return comexCanalesManager.consultaOperacionesMostrarMas();
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comex.transfext.sei.ComexCanalesSEI#procesarTransferenciaGuardar(ar.com.santanderrio.obp.servicios.comex.transfext.view.ProcesarTransferenciaComexView)
	 */
	@Override
	public Respuesta<ProcesarTransferenciaComexView> procesarTransferenciaGuardar(ProcesarTransferenciaComexView procesarTransferenciaComexView) {
		return comexCanalesManager.procesarTransferenciaGuardar(procesarTransferenciaComexView);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comex.transfext.sei.ComexCanalesSEI#procesarTransferenciaBaja(ar.com.santanderrio.obp.servicios.comex.transfext.view.ProcesarTransferenciaComexView)
	 */
	@Override
	public Respuesta<ProcesarTransferenciaComexView> procesarTransferenciaBaja(ProcesarTransferenciaComexView procesarTransferenciaComexView) {
		return comexCanalesManager.procesarTransferenciaBaja(procesarTransferenciaComexView);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comex.transfext.sei.ComexCanalesSEI#procesarTransferenciaAlta(ar.com.santanderrio.obp.servicios.comex.transfext.view.ProcesarTransferenciaComexView)
	 */
	@Override
	public Respuesta<ProcesarTransferenciaComexView> procesarTransferenciaAlta(ProcesarTransferenciaComexView procesarTransferenciaComexView) {
		return comexCanalesManager.procesarTransferenciaAlta(procesarTransferenciaComexView);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comex.transfext.sei.ComexCanalesSEI#adjuntarArchivo(ar.com.santanderrio.obp.servicios.comex.transfext.view.ProcesarTransferenciaComexView)
	 */
	@Override
	public Respuesta<ArchivoTransferenciaView> adjuntarArchivo(ProcesarTransferenciaComexView procesarTransferenciaComexView) {
		return this.comexCanalesManager.adjuntarArchivo(procesarTransferenciaComexView);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comex.transfext.sei.ComexCanalesSEI#borrarArchivo(ar.com.santanderrio.obp.servicios.comex.transfext.view.ArchivoTransferenciaView)
	 */
	@Override
	public Respuesta<Void> borrarArchivo(ArchivoTransferenciaView archivoTransferenciaView) {
		return this.comexCanalesManager.borrarArchivo(archivoTransferenciaView);
	}

	

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comex.transfext.sei.ComexCanalesSEI#obtenerArchivoWsComex(ar.com.santanderrio.obp.servicios.comex.transfext.view.ArchivoTransferenciaView)
	 */
	@Override
	public Respuesta<AdjuntarArchivosDTO> obtenerArchivoWsComex(ArchivoTransferenciaView archivoTransferenciaView) {
		return this.comexCanalesManager.obtenerArchivoWsComex(archivoTransferenciaView);
	}

}
