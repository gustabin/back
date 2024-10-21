/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.sei.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.pagos.sei.PagoMultipleSEI;
import ar.com.santanderrio.obp.servicios.pagos.web.manager.PagoMultipleManager;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ListaPDFPagosMultiples;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoMultipleListView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoMultipleView;

/**
 * The Class PagoMultipleSEIImpl.
 */
@Component("pagoMultipleSEI")
public class PagoMultipleSEIImpl implements PagoMultipleSEI {

	/** The pago multiple manager. */
	@Autowired
	PagoMultipleManager pagoMultipleManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.sei.PagoMultipleSEI#
	 * solicitarPagoMultiple(ar.com.santanderrio.obp.servicios.pagos.web.view.
	 * PagoMultipleView)
	 */
	@Override
	public Respuesta<PagoMultipleListView> solicitarPagoMultiple(PagoMultipleListView pagoMultipleListView) {
		return pagoMultipleManager.solicitarPagoMultiple(pagoMultipleListView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.sei.PagoMultipleSEI#
	 * ejecutarPagoMultiple(ar.com.santanderrio.obp.servicios.pagos.web.view.
	 * PagoMultipleListView)
	 */
	@Override
	public Respuesta<PagoMultipleListView> ejecutarPagoMultiple(PagoMultipleListView pagoMultipleListView) {
		return pagoMultipleManager.ejecutarPagoMultiple(pagoMultipleListView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.sei.PagoMultipleSEI#
	 * grabarEstadisticaComprobante(ar.com.santanderrio.obp.servicios.pagos.web.
	 * view.PagoMultipleListView)
	 */
	@Override
	public Respuesta<Boolean> grabarEstadisticaComprobantePagoMultiple() {
		return pagoMultipleManager.grabarEstadisticaComprobantePagoMultiple();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.sei.PagoMultipleSEI#
	 * descargarComprobantePDF(ar.com.santanderrio.obp.servicios.pagos.web.view.
	 * PagoMultipleView)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobantePDF(PagoMultipleView pagoMultipleView) {
		return pagoMultipleManager.descargarComprobantePDF(pagoMultipleView);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.nuevopago.sei.NuevoPagoSEI#
	 * grabarEstadisticaRecargaIngresoNumero(ar.com.santanderrio.obp.servicios.
	 * simuladorprestamo.view.PuntoDeAccesoView)
	 */
	@Override
	public Respuesta<Void> continuarPago(PagoMultipleListView pagoMultipleListView) {
		return pagoMultipleManager.continuarPago(pagoMultipleListView);
	}

	@Override
	public Respuesta<ReporteView> imprimirComprobantes() {
		return pagoMultipleManager.imprimirComprobantes();
	}

	@Override
	public Respuesta<ListaPDFPagosMultiples> descargaMultipleComprobantesPDF() {
		return pagoMultipleManager.descargaMultipleComprobantesPDF();
	}
}
