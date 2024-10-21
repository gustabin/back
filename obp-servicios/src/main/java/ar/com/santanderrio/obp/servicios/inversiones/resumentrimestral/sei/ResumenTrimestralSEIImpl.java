package ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.sei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.manager.ResumenTrimestralManager;
import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.view.ResponsePdfView;
import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.view.ResumenPdfInView;
import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.view.ResumenTrimestralView;
import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.view.ResumenesDispInView;


@Component("resumenTrimestralSEI")
public class ResumenTrimestralSEIImpl implements ResumenTrimestralSEI{

	 @Autowired
	 private ResumenTrimestralManager resumenManager;
 
	
	@Override
	public Respuesta<ResumenTrimestralView> obtenerDisponibles(ResumenesDispInView request) {
		
		Respuesta<ResumenTrimestralView> respuesta=resumenManager.obtenerDisponibles(request);
		return respuesta;
		
	}


	@Override
	public Respuesta<ResponsePdfView> obtenerPdf(ResumenPdfInView viewRequest) {
		
		Respuesta<ResponsePdfView> respuesta=resumenManager.obtenerPdf(viewRequest);
		return respuesta;
	}

}
