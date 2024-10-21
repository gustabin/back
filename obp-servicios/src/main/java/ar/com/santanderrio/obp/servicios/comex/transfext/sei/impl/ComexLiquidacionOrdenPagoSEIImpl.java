package ar.com.santanderrio.obp.servicios.comex.transfext.sei.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comex.transfext.manager.ComexLiquidacionOrdenPagoManager;
import ar.com.santanderrio.obp.servicios.comex.transfext.sei.ComexLiquidacionOrdenPagoSEI;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ArchivoTransferenciaView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.DocumentacionAdjuntaView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.FeedbackProcesarOrdenPago;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.InicioLiquidacionOrdenPagoView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ProcesarOrdenPagoInView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;

@Component
public class ComexLiquidacionOrdenPagoSEIImpl implements ComexLiquidacionOrdenPagoSEI {

	@Autowired
	private ComexLiquidacionOrdenPagoManager liquidacionOrdenPagoManager;

	@Override
	public Respuesta<InicioLiquidacionOrdenPagoView> inicioLiquidacionOrdenPago() {
		return liquidacionOrdenPagoManager.inicioLiquidacionOrdenPagoView();
	}

	@Override
	public Respuesta<ArchivoTransferenciaView> adjuntarArchivo(DocumentacionAdjuntaView documentacionAdjuntaView) {
		return liquidacionOrdenPagoManager.adjuntarArchivo(documentacionAdjuntaView);
	}

	@Override
	public Respuesta<FeedbackProcesarOrdenPago> procesarOrden(ProcesarOrdenPagoInView procesarOrdenPagoInView) {
		return liquidacionOrdenPagoManager.procesarOrden(procesarOrdenPagoInView);
	}

	@Override
	public Respuesta<Void> borrarArchivo(ArchivoTransferenciaView documentacionAdjuntaView) {
		return liquidacionOrdenPagoManager.borrarArchivo(documentacionAdjuntaView);
	}

	@Override
	public Respuesta<Void> configuracionOrdenPagoExt() {
		return liquidacionOrdenPagoManager.configuracionOrdenPagoExt();
	}

	@Override
	public Respuesta<ReporteView> descargarComprobantePDF(ProcesarOrdenPagoInView procesarOrdenPagoViewIn) {
		return liquidacionOrdenPagoManager.descargarComprobantePDF(procesarOrdenPagoViewIn.getEsDescarga());
	}
	
	@Override
	public Respuesta<ReporteView> descargarNormativa() {
		return liquidacionOrdenPagoManager.descargarNormativa();
	}

	@Override
	public Respuesta<Void> verComprobante() {
		return liquidacionOrdenPagoManager.verComprobante();
	}

	@Override
	public Respuesta<Void> estadisticaAdjuntar() {
		return liquidacionOrdenPagoManager.estadisticaAdjuntar();
	}

	@Override
	public Respuesta<String> obtenerMensajeVinculante() {
		return liquidacionOrdenPagoManager.obtenerMensajeVinculante();
	}

}
