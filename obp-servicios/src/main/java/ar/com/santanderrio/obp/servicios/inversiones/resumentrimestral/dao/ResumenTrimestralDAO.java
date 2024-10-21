package ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.dao;

import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.view.ResponsePdfView;
import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.view.ResumenTrimestralView;

public interface ResumenTrimestralDAO {

	ResumenTrimestralView obtenerDisponibles(String nup);

	ResponsePdfView obtenerPdf(String idPdf, String nup);

}
