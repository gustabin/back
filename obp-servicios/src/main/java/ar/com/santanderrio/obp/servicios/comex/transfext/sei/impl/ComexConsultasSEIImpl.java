/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.sei.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comex.transfext.manager.ComexConsultasManager;
import ar.com.santanderrio.obp.servicios.comex.transfext.sei.ComexConsultasSEI;
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
 * The class TransferenciaExteriorSEI.
 *
 * @author B040619
 */
@Component("comexConsultasSEI")
public class ComexConsultasSEIImpl implements ComexConsultasSEI {
	
	/** The comex Consultas Manager. */
	@Autowired
	private ComexConsultasManager comexConsultasManager;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comex.transext.manager.
	 * ComexConsultasSEI#getDatosBeneficiario()
	 */
	@Override
	public Respuesta<DatosBeneficiarioOutView> getDatosBeneficiario() {
		Respuesta<DatosBeneficiarioOutView> respuesta = comexConsultasManager.obtenerDatosBeneficiarios();
		return respuesta;
	}

	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comex.transext.manager.
	 * ComexConsultasSEI#obtenerDatosComex()
	 */
	public Respuesta<TransferenciaComexOutView> obtenerDatosComex() {
		return comexConsultasManager.obtenerDatosComex();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comex.transext.manager.
	 * ComexConsultasSEI#obtenerBancos()
	 */
	@Override
	public Respuesta<ConsultaBancosOutView> obtenerBancos(ConsultaBancosInView consultaBancosInView) {
		return comexConsultasManager.obtenerBancos(consultaBancosInView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comex.transext.manager.
	 * ComexConsultasSEI#descargarComprobante()
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobante(ComprobanteInView documentacion) {
		return comexConsultasManager.descargarComprobante(documentacion);
	}



	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comex.transfext.sei.ComexConsultasSEI#obtenerConfiguracionAdjuntarArchivo()
	 */
	@Override
	public Respuesta<ConfiguracionAdjuntarArchivosOutView> obtenerConfiguracionAdjuntarArchivo() {
		return this.comexConsultasManager.obtenerConfiguracionAdjuntarArchivo();
	}


	/* 
	 * obtiene consulta concepto
	 *
	 * @return respuesta
	 */
	@Override
	public Respuesta<ConsultaConceptoOutView> obtenerConsultaConcepto(int idConcepto) {
		return this.comexConsultasManager.obtenerConsultaConcepto(idConcepto);
	}



	@Override
	public Respuesta<String> obtenerMensajeVinculante() {
		return comexConsultasManager.obtenerMensajeVinculante();
	}



	@Override
	public Respuesta<ReporteView> descargarNormativa() {
		return comexConsultasManager.descargarNormativa();
	}



	@Override
	public Respuesta<Void> estadisticaVinculante(ProcesarTransferenciaComexView view) {
		return comexConsultasManager.estadisticaVinculante(view);
	}



	@Override
	public Respuesta<Void> estadisticaDatosBeneficiario() {
		return comexConsultasManager.estadisticaDatosBeneficiario();
	}



	@Override
	public Respuesta<Void> estadisticaDatosTransferencia() {
		return comexConsultasManager.estadisticaDatosTransferencia();
	}



	@Override
	public Respuesta<Void> estadisticaIngresoAdjuntar() {
		return comexConsultasManager.estadisticaIngresoAdjuntar();
	}



	@Override
	public Respuesta<Void> estadisticaVerComprobante() {
		return comexConsultasManager.estadisticaVerComprobante();
	}
	
}