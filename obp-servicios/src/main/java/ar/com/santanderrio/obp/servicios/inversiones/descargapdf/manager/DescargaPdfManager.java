package ar.com.santanderrio.obp.servicios.inversiones.descargapdf.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaResumenCuentaBP;
import ar.com.santanderrio.obp.servicios.inversiones.descargapdf.entities.ListadoPDF;
import ar.com.santanderrio.obp.servicios.inversiones.descargapdf.entities.TipoPDFEnum;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ListaFechasComprobantes;

public interface DescargaPdfManager {

	Respuesta<ListaFechasComprobantes> obtenerListaComprobantes(TipoPDFEnum tipoPDF);
	
	Respuesta<ListadoPDF> obtenerPDF (ConsultaResumenCuentaBP cuenta, TipoPDFEnum tipoPDF);
	
}
