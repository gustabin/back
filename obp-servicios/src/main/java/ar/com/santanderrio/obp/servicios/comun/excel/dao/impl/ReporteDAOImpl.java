/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.excel.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.excel.dao.ReporteDAO;
import ar.com.santanderrio.obp.servicios.comun.excel.helpers.ExcelBuilderHelper;
import ar.com.santanderrio.obp.servicios.comun.excel.helpers.ExcelBuilderHelper2;
import ar.com.santanderrio.obp.servicios.tenencias.helpers.TeneciasExcelBuilderHelper;

/**
 * The Class ReporteDAOImpl.
 *
 * @author B039636
 */
@Component
public class ReporteDAOImpl implements ReporteDAO {

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ReporteDAOImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.comun.excel.dao.ReporteDAO#obtenerReporte(java.
	 * lang. Object, java.lang.String,
	 * ar.com.santanderrio.obp.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<Reporte> obtenerReporte(Object body, String proceso, Cliente cliente, Boolean isHelperBasico) {
		ExcelBuilderHelper excelBuilderHelper = null;
		ExcelBuilderHelper2 excelBuilderHelper2 = null;
		if (isHelperBasico) {
			excelBuilderHelper = ContextHolder.getContext().getBean(ExcelBuilderHelper.class);
		} else {
			excelBuilderHelper2 = ContextHolder.getContext().getBean(ExcelBuilderHelper2.class);
		}

		Respuesta<Reporte> respuesta = new Respuesta<Reporte>();
		Reporte reporte = new Reporte();
		try {
			if (isHelperBasico) {
				reporte.setBytes(excelBuilderHelper.hacerExcel(cliente, proceso, body));
			} else {
				reporte.setBytes(excelBuilderHelper2.hacerExcel(cliente, proceso, body));
			}
			reporte.setTipoArchivo(TipoArchivoEnum.EXCEL);
			reporte.setNombre(proceso + ".xls");
			respuesta.setRespuesta(reporte);
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			respuesta.setRespuestaVacia(false);
		} catch (Exception e) {
			LOGGER.error("Error al obtener el reporte del cliente {}.", cliente, e);
			respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
			respuesta.setRespuestaVacia(true);
		}
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.excel.dao.ReporteDAO#
	 * obtenerReporte(java.lang.Object, java.util.List,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<Reporte> obtenerReporte(Object body, List<String> proceso, Cliente cliente) {
		TeneciasExcelBuilderHelper excelBuilderHelper = ContextHolder.getContext()
				.getBean(TeneciasExcelBuilderHelper.class);

		Respuesta<Reporte> respuesta = new Respuesta<Reporte>();
		Reporte reporte = new Reporte();
		try {
			reporte.setBytes(excelBuilderHelper.hacerExcel(cliente, body));
			reporte.setTipoArchivo(TipoArchivoEnum.EXCEL);
			reporte.setNombre(proceso.get(0) + ".xls");

			respuesta.setRespuesta(reporte);
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			respuesta.setRespuestaVacia(false);
		} catch (Exception e) {
			LOGGER.error("Error al obtener el reporte del cliente {}.", cliente, e);
			respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
			respuesta.setRespuestaVacia(true);
		}
		return respuesta;
	}

}
