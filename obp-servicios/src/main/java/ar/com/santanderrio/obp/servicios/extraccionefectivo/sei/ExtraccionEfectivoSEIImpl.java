package ar.com.santanderrio.obp.servicios.extraccionefectivo.sei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.extraccionefectivo.manager.ExtraccionEfectivoManager;
import ar.com.santanderrio.obp.servicios.extraccionefectivo.view.DatosClienteExtraccionEfectivoView;
import ar.com.santanderrio.obp.servicios.extraccionefectivo.view.ExtraccionEfectivoView;

@Component
public class ExtraccionEfectivoSEIImpl implements ExtraccionEfectivoSEI {

	@Autowired
	ExtraccionEfectivoManager extraccionEfectivoManager;
	
	@Override
	public Respuesta<DatosClienteExtraccionEfectivoView> configuracionExtraccionEfectivo() {
		return extraccionEfectivoManager.obtenerConfiguracion();
	}

	@Override
	public Respuesta<ExtraccionEfectivoView> ejecutarSolicitud(ExtraccionEfectivoView solicitarEfectivoView) {
		return extraccionEfectivoManager.ejecutarSolicitud(solicitarEfectivoView);
	}

	@Override
	public Respuesta<Void> grabarEstadisticaVisualizacionComprobante() {
		return extraccionEfectivoManager.grabarEstadisticaVisualizacionComprobante();
	}

	@Override
	public Respuesta<ReporteView> generarComprobantePDF() {
		return extraccionEfectivoManager.generarComprobantePDF();
	}

}
