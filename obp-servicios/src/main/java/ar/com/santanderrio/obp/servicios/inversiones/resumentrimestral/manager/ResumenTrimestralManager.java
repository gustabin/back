package ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.view.ResponsePdfView;
import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.view.ResumenPdfInView;
import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.view.ResumenTrimestralView;
import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.view.ResumenesDispInView;

public interface ResumenTrimestralManager {
	
	Respuesta<ResumenTrimestralView> obtenerDisponibles(ResumenesDispInView request);

	Respuesta<ResponsePdfView> obtenerPdf(ResumenPdfInView viewRequest);

}
