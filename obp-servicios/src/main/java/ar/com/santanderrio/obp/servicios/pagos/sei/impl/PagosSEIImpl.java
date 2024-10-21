/*
 * editado: 23/08/2016 16:00:15 - b039542 - ignacio_valek@itrsa.com.ar
 *
 */
package ar.com.santanderrio.obp.servicios.pagos.sei.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.base.web.view.FeedbackMensajeView;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.pagos.sei.PagosSEI;
import ar.com.santanderrio.obp.servicios.pagos.web.manager.PagosManager;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultaPagosView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoPendienteView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagosPendientesView;

/**
 * The Class PagosSEIImpl.
 */
@Component("pagosSEI")
public class PagosSEIImpl implements PagosSEI {

	/** The pago manager. */
	@Autowired
	private PagosManager pagoManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.pagos.sei.PagosSEI#getPagosPendientes(
	 * ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultaPagosView)
	 */
	@Override
	public Respuesta<PagosPendientesView> getPagosPendientes(ConsultaPagosView consultaPagosView) {
		return pagoManager.getPagosTotales(consultaPagosView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.pagos.sei.PagosSEI#solicitarStopDebit(
	 * ar.com.santanderrio.obp.servicios.pagos.web.view.PagoPendienteView)
	 */
	@Override
	public Respuesta<PagoPendienteView> solicitarStopDebit(PagoPendienteView pagoPendienteView) {
		return pagoManager.solicitarStopDebit(pagoPendienteView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.pagos.sei.PagosSEI#ejecutarStopDebit(ar
	 * .com.santanderrio.obp.servicios.pagos.web.view.PagoPendienteView)
	 */
	@Override
	public Respuesta<PagoPendienteView> ejecutarStopDebit(PagoPendienteView pagoPendienteView) {
		return pagoManager.ejecutarStopDebit(pagoPendienteView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.sei.PagosSEI#
	 * altaInformacionAdicional(ar.com.santanderrio.obp.servicios.pagos.web.view
	 * .PagoPendienteView)
	 */
	@Override
	public Respuesta<Boolean> altaInformacionAdicional(PagoPendienteView pagoPendienteView) {
		return pagoManager.altaDeInformacionAdicional(pagoPendienteView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.sei.PagosSEI#
	 * solicitarAdhesionPagoAuto(ar.com.santanderrio.obp.servicios.pagos.web.
	 * view.PagoPendienteView)
	 */
	@Override
	public Respuesta<PagoPendienteView> solicitarAdhesionPagoAuto(PagoPendienteView pagoPendienteView) {
		return pagoManager.solicitarAdhesionPagoAuto(pagoPendienteView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.sei.PagosSEI#
	 * modificarAdhesionPagoAuto(ar.com.santanderrio.obp.servicios.pagos.web.
	 * view.PagoPendienteView)
	 */
	@Override
	public Respuesta<PagoPendienteView> modificarAdhesionPagoAuto(PagoPendienteView pagoPendienteView) {
		return pagoManager.modificarAdhesionPagoAuto(pagoPendienteView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.sei.PagosSEI#
	 * solicitarBajaAdhesion(ar.com.santanderrio.obp.servicios.pagos.web.view.
	 * PagoPendienteView)
	 */
	@Override
	public Respuesta<PagoPendienteView> solicitarBajaAdhesion(PagoPendienteView pagoPendienteView) {
		return pagoManager.solicitarBajaAdhesion(pagoPendienteView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.pagos.sei.PagosSEI#ejecutarBajaAdhesion
	 * (ar.com.santanderrio.obp.servicios.pagos.web.view.PagoPendienteView)
	 */
	@Override
	public Respuesta<PagoPendienteView> ejecutarBajaAdhesion(PagoPendienteView pagoPendienteView) {
		return pagoManager.ejecutarBajaAdhesion(pagoPendienteView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.sei.PagosSEI#
	 * solicitarEliminacionPagoPuntual(ar.com.santanderrio.obp.servicios.pagos.
	 * web.view.PagoPendienteView)
	 */
	@Override
	public Respuesta<PagoPendienteView> solicitarEliminacionPago(PagoPendienteView pagoPendienteView) {
		return pagoManager.solicitarEliminacionPago(pagoPendienteView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.pagos.sei.PagosSEI#eliminarPagoPuntual(
	 * ar.com.santanderrio.obp.servicios.pagos.web.view.PagoPendienteView)
	 */
	@Override
	public Respuesta<FeedbackMensajeView> eliminarPago(PagoPendienteView pagoPendienteView) {
		return pagoManager.eliminarPago(pagoPendienteView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.pagos.sei.PagosSEI#verDetalle(ar.com.
	 * santanderrio.obp.servicios.pagos.web.view.PagoPendienteView)
	 */
	@Override
	public Respuesta<PagoPendienteView> verDetalle(PagoPendienteView pagoPendienteView) {
		return pagoManager.verDetalle(pagoPendienteView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.sei.PagosSEI#
	 * solicitarAdhesionDebitoAutomatico(ar.com.santanderrio.obp.servicios.pagos
	 * .web.view.PagoPendienteView)
	 */
	@Override
	public Respuesta<PagoPendienteView> solicitarAdhesionDebitoAutomatico(PagoPendienteView pagoPendienteView) {
		return pagoManager.solicitarAdhesionDebitoAutomatico(pagoPendienteView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.sei.PagosSEI#
	 * solicitarAdhesionPagoAutomatico(ar.com.santanderrio.obp.servicios.pagos.
	 * web.view.PagoPendienteView)
	 */
	@Override
	public Respuesta<PagoPendienteView> solicitarAdhesionPagoAutomatico(PagoPendienteView pagoPendienteView) {
		return pagoManager.solicitarAdhesionPagoAutomatico(pagoPendienteView);
	}

	/**
	 * Baja pago programado de tarjeta credito. ver: DTF: 10303 iatx: CNSTJCPAGP
	 *
	 * @author B041299 Manuel.Vargas
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 * @see ar.com.santanderrio.obp.servicios.pagos.sei.PagosSEI#bajaPagoProgramadoDeTarjetaCredito(ar.com.santanderrio.obp.servicios.pagos.web.view.PagoPendienteView)
	 */
	@Override
	public Respuesta<FeedbackMensajeView> bajaPagoProgramadoDeTarjetaCredito(PagoPendienteView pagoPendienteView) {
		return pagoManager.ejecutarBajaPagoProgramadoDeTarjetaCredito(pagoPendienteView);
	}
}
