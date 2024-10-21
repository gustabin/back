/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.sei.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.pagos.sei.PrestamoSEI;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ComprobanteView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConfiguracionPagoCuotaPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConfirmacionPagoPrestamoView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultaPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.web.view.DetallePrestamoView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.RespuestaPagoPrestamoView;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.PrestamoManager;

/**
 * The Class PrestamoSEIImpl.
 */
@Component("prestamoSEI")
public class PrestamoSEIImpl implements PrestamoSEI {

	/** The prestamo manager. */
	@Autowired
	private PrestamoManager prestamoManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.sei.PrestamoSEI#
	 * getDetallePrestamos(ar.com.santanderrio.obp.servicios.pagos.web.view.
	 * ConsultaPrestamo)
	 */
	@Override
	public Respuesta<DetallePrestamoView> getDetallePrestamos(ConsultaPrestamo consultaPrestamo) {
		return prestamoManager.getDetallePrestamos(consultaPrestamo, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.sei.PrestamoSEI#
	 * obtenerComprobante(ar.com.santanderrio.obp.servicios.pagos.web.view.
	 * ConsultaPrestamo)
	 */
	@Override
	public Respuesta<ComprobanteView> obtenerComprobante(ConsultaPrestamo consultaPrestamo) {
		return prestamoManager.obtenerComprobante(consultaPrestamo.getNumeroPrestamo());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.sei.PrestamoSEI#
	 * obtenerConfiguracionPagoCuotaPrestamo(ar.com.santanderrio.obp.servicios.
	 * pagos.web.view.ConsultaPrestamo)
	 */
	@Override
	public Respuesta<ConfiguracionPagoCuotaPrestamo> obtenerConfiguracionPagoCuotaPrestamo(
			ConsultaPrestamo consultaPrestamo) {
		return prestamoManager.obtenerConfiguracionPagoCuotaPrestamo(consultaPrestamo, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.sei.PrestamoSEI#
	 * obtenerConfirmacion(ar.com.santanderrio.obp.servicios.pagos.web.view.
	 * ConsultaPrestamo)
	 */
	@Override
	public Respuesta<ConfirmacionPagoPrestamoView> obtenerConfirmacion(ConsultaPrestamo consultaPrestamo) {
		return prestamoManager.obtenerConfirmacion(consultaPrestamo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.pagos.sei.PrestamoSEI#pagar(ar.com.
	 * santanderrio.obp.servicios.pagos.web.view.ConsultaPrestamo)
	 */
	@Override
	public Respuesta<RespuestaPagoPrestamoView> pagar(ConsultaPrestamo consultaPrestamo) {
		return prestamoManager.efectuarPago(consultaPrestamo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.sei.PrestamoSEI#
	 * obtenerReporteComprobante(ar.com.santanderrio.obp.servicios.pagos.web.
	 * view.ConsultaPrestamo)
	 */
	@Override
	public Respuesta<ReporteView> obtenerReporteComprobante(ConsultaPrestamo consultaPrestamo) {
		return prestamoManager.obtenerReporteComprobante(consultaPrestamo.getNumeroPrestamo());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.sei.PrestamoSEI#
	 * obtenerReporteDetallePrestamo(ar.com.santanderrio.obp.servicios.pagos.web
	 * .view.ConsultaPrestamo)
	 */
	@Override
	public Respuesta<ReporteView> obtenerReporteDetallePrestamo(ConsultaPrestamo consultaPrestamo) {
		return prestamoManager.getReporteDetallePrestamo(consultaPrestamo.getNumeroPrestamo(), consultaPrestamo.getIsUva());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.sei.PrestamoSEI#
	 * notificarDetalleImporteCuota()
	 */
	@Override
	public void notificarDetalleImporteCuota(String tipo) {
		prestamoManager.notificarDetalleImporteCuota(tipo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.sei.PrestamoSEI#
	 * notificarDetalleTasasCuota()
	 */
	@Override
	public void notificarDetalleTasasCuota(String tipo) {
		prestamoManager.notificarDetalleTasasCuota(tipo);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.pagos.sei.PrestamoSEI#descargarPdfTasas()
	 */
	@Override
	public Respuesta<ReporteView> descargarPdfTasas() {
		return prestamoManager.descargarPdfTasas();
	}

}
