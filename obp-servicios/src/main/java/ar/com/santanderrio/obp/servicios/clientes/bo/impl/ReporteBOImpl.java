/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.bo.ReporteBO;
import ar.com.santanderrio.obp.servicios.clientes.dao.ReporteDAO;
import ar.com.santanderrio.obp.servicios.clientes.entities.DatosComprobante;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;

/**
 * The Class ReporteBOImpl.
 */
@Component
public class ReporteBOImpl implements ReporteBO {

	/** The reporte DAO. */
	@Autowired
	private ReporteDAO reporteDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.clientes.bo.ReporteBO#
	 * descargarComprobante(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * DatosComprobante)
	 */
	@Override
	public Respuesta<Reporte> descargarComprobante(DatosComprobante datos) {
		Respuesta<Reporte> respuestaReporte = new Respuesta<Reporte>();

		try {
			Reporte reporte = reporteDAO.descargarComprobante(datos);

			respuestaReporte.setEstadoRespuesta(EstadoRespuesta.OK);
			respuestaReporte.setRespuesta(reporte);
		} catch (Exception e) {
			ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
			itemMensajeRespuesta.setMensaje(e.getMessage());
			itemMensajeRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());

			respuestaReporte.setEstadoRespuesta(EstadoRespuesta.ERROR);
			respuestaReporte.add(itemMensajeRespuesta);
		}
		return respuestaReporte;
	}

}
