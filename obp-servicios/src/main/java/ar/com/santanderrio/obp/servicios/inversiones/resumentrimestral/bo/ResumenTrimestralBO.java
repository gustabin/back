package ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.bo;

import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.view.ResponsePdfView;
import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.view.ResumenTrimestralView;

public interface ResumenTrimestralBO {
	
	String obtenerMensajeAyudaRTF();
	
	String obtenerMensajeSinRTF();

	ResumenTrimestralView obtenerDisponibles(String nup, String segmento);

	ResponsePdfView obtenerPdf(String idPdf,String nup);

}
