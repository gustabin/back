package ar.com.santanderrio.obp.servicios.inversiones.descargapdf.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.inversiones.descargapdf.entities.TipoPDFEnum;
import ar.com.santanderrio.obp.servicios.ondemand.entities.WSODException;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenMensualCuenta;
import ar.com.santanderrio.obp.servicios.resumen.entities.ResumenMensualCuenta;

public interface DescargaPdfBO {

	Respuesta<List<ResumenMensualCuenta>> obtenerListaComprobantes(AbstractCuenta cuenta, TipoPDFEnum tipoPDF) throws BusinessException, WSODException;
	
	Respuesta<ReporteResumenMensualCuenta> obtenerPDF(ResumenMensualCuenta resumenSeleccionado, AbstractCuenta cuenta, TipoPDFEnum tipoPDF);

}
