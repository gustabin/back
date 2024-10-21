package ar.com.santanderrio.obp.servicios.extraccionefectivo.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.extraccionefectivo.view.DatosClienteExtraccionEfectivoView;
import ar.com.santanderrio.obp.servicios.extraccionefectivo.view.ExtraccionEfectivoView;

public interface ExtraccionEfectivoManager {

	Respuesta<DatosClienteExtraccionEfectivoView> obtenerConfiguracion();

	Respuesta<ExtraccionEfectivoView> ejecutarSolicitud(ExtraccionEfectivoView solicitarEfectivoView);

	Respuesta<Void> grabarEstadisticaVisualizacionComprobante();
	
	Respuesta<ReporteView> generarComprobantePDF();
	
}
