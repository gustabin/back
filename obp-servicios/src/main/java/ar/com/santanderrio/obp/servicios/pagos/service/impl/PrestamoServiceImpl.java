/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.pagos.bo.ComprobantePrestamoReporte;
import ar.com.santanderrio.obp.servicios.pagos.bo.PagoPrestamoBO;
import ar.com.santanderrio.obp.servicios.pagos.bo.ReporteComprobanteBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.ComprobantePrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendientePrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;
import ar.com.santanderrio.obp.servicios.pagos.service.PrestamoService;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoBO;
import ar.com.santanderrio.obp.servicios.prestamos.bo.SimuladorPrestamoBO;

/**
 * The Class PrestamoServiceImpl.
 */
@Component
public class PrestamoServiceImpl implements PrestamoService {

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PrestamoServiceImpl.class);

	/** The pago prestamo BO. */
	@Autowired
	private PagoPrestamoBO pagoPrestamoBO;

	/** The prestamo BO. */
	@Autowired
	private PrestamoBO prestamoBO;

	/** The reporte comprobante BO. */
	@Autowired
	private ReporteComprobanteBO reporteComprobanteBO;

	/** The simulador prestamo bo. */
	@Autowired
	private SimuladorPrestamoBO simuladorPrestamoBo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.pagos.service.PrestamoService#pagar(ar.
	 * com.santanderrio.obp.servicios.pagos.entities.PagoPrestamo, int)
	 */
	@Override
	public Respuesta<ComprobantePrestamo> pagar(PagoPrestamo pagoPrestamo, int cantidadCuentasDebito) {

		return pagoPrestamoBO.pagar(pagoPrestamo, cantidadCuentasDebito);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.service.PrestamoService#
	 * obtenerComprobantePrestamo(ar.com.santanderrio.obp.servicios.pagos.
	 * entities.PagoPendientePrestamo,
	 * ar.com.santanderrio.obp.servicios.pagos.entities.ComprobantePrestamo)
	 */
	@Override
	public Respuesta<Reporte> obtenerComprobantePrestamo(PagoPendientePrestamo pagoPendientePrestamo,
			ComprobantePrestamo comprobantePrestamo) throws ServiceException {
		Respuesta<Reporte> reporteRespuesta = null;
		try {
			reporteRespuesta = reporteComprobanteBO.obtenerReporte(pagoPendientePrestamo, comprobantePrestamo);
		} catch (BusinessException e) {
			throw new ServiceException(e);
		}
		return reporteRespuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.service.PrestamoService#
	 * obtenerReporteDetallePrestamo(ar.com.santanderrio.obp.servicios.pagos.bo.
	 * ComprobantePrestamoReporte)
	 */
	@Override
	public Respuesta<Reporte> obtenerReporteDetallePrestamo(ComprobantePrestamoReporte comprobantePrestamoReporte)
			throws ServiceException {
		Respuesta<Reporte> reporteRespuesta = null;
		try {
			reporteRespuesta = reporteComprobanteBO.obtenerReporteDetallePrestamo(comprobantePrestamoReporte);
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}

		return reporteRespuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.pagos.service.PrestamoService#
	 * obtenerMensajePagoOk(ar.com.santanderrio.obp.pagos.entities.Prestamo)
	 */
	@Override
	public String obtenerMensajePagoOk(Prestamo prestamo, ComprobantePrestamo comprobantePrestamo) {
		return pagoPrestamoBO.obtenerMensajePagoOk(prestamo, comprobantePrestamo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.service.PrestamoService#
	 * validarHorario()
	 */
	@Override
	public boolean validarHorario() {
		return simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion();
	}

}