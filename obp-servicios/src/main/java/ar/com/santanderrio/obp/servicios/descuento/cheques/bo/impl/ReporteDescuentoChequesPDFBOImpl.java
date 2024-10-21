/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.bo.impl;

import java.util.ArrayList;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesBOEnum;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.descuento.cheques.bo.ReporteDescuentoChequesPDFBO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.dao.ReporteDescuentoChequesPDFDAO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.AltaChequesViewOut;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.DetalleOperacionesPrecargadasView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class ReporteDescuentoChequesPDFBOImpl.
 */
@Component
public class ReporteDescuentoChequesPDFBOImpl implements ReporteDescuentoChequesPDFBO{
	
	/** The RespuestaFactory. */
	@Autowired
	private RespuestaFactory respuestaFactory;
	
	/** The RespuestaFactory. */
	@Autowired
	private ReporteDescuentoChequesPDFDAO reporteDao;
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ReporteDescuentoChequesPDFBOImpl.class);

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.bo.ReporteDescuentoChequesPDFBO#obtenerOperacionPDF(ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.DetalleOperacionesPrecargadasView)
	 */
	@Override
	public Respuesta<Reporte> obtenerOperacionPDF(DetalleOperacionesPrecargadasView detalleIn) {
		try {
			Reporte reporte = reporteDao.obtenerOperacionPDF(detalleIn);
			return respuestaFactory.crearRespuestaOk(reporte);
		} catch (DAOException e) {
			LOGGER.error(ComprobantesBOEnum.MENSAJE_ERROR_REPORTE_PDF.getDetalle(), e);
			ItemMensajeRespuesta item = new ItemMensajeRespuesta();
			item.setMensaje(ComprobantesBOEnum.MENSAJE_ERROR_REPORTE_PDF.getDetalle());
			item.setTipoError(TipoError.ERROR_DESCARGA_COMPROBANTE.getDescripcion());
			return respuestaFactory.crearRespuestaError(Reporte.class,
					new ArrayList<ItemMensajeRespuesta>(Arrays.asList(item)));
		}
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.bo.ReporteDescuentoChequesPDFBO#obtenerOperacionPDF(ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.AltaChequesViewOut)
	 */
	@Override
	public Respuesta<Reporte> obtenerOperacionPDF(AltaChequesViewOut chequesView) {
		try {
			Reporte reporte = reporteDao.obtenerOperacionPDF(chequesView);
			return respuestaFactory.crearRespuestaOk(reporte);
		} catch (DAOException e) {
			LOGGER.error(ComprobantesBOEnum.MENSAJE_ERROR_REPORTE_PDF.getDetalle(), e);
			ItemMensajeRespuesta item = new ItemMensajeRespuesta();
			item.setMensaje(ComprobantesBOEnum.MENSAJE_ERROR_REPORTE_PDF.getDetalle());
			item.setTipoError(TipoError.ERROR_DESCARGA_COMPROBANTE.getDescripcion());
			return respuestaFactory.crearRespuestaError(Reporte.class,
					new ArrayList<ItemMensajeRespuesta>(Arrays.asList(item)));
		}
	}

}
