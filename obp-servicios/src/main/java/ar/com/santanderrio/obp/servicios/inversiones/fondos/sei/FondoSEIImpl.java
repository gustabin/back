
/**
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.sei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaResumenCuentaBP;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DatosComprobante;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RendimientoBPrivView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RendimientoView;
import ar.com.santanderrio.obp.servicios.inversiones.descargapdf.entities.ListadoPDF;
import ar.com.santanderrio.obp.servicios.inversiones.descargapdf.entities.TipoPDFEnum;
import ar.com.santanderrio.obp.servicios.inversiones.descargapdf.manager.DescargaPdfManager;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.FondoManager;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ComprobanteSuscripcionFondo;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ComprobanteSuscripcionView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfigFondoBPrivView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfigFondoView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConsultaHorariosYHonorariosView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CotizacionDeFondosView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CuentasConsultaFondoView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.DetalleDeFondoIn;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.DetalleDeFondoOutView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FondoView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.GraficoFondosBPrivOutView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.GraficoFondosRTLOutView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.MovimientosInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.MovimientosView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SimulacionInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SimulacionSuscripcionOutView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SuscripcionInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SuscripcionOutView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.TenenciasFondoView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.TipoBancaView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ListaFechasComprobantes;

/**
 * The Class FondoSEIImpl.
 *
 * @author
 */
@Component("fondoSEI")
public class FondoSEIImpl implements FondoSEI {

	/** The fondo manager. */
	@Autowired
	private FondoManager fondoManager;
	
	@Autowired
	private DescargaPdfManager descargaPdfManager;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<FondoView> simularSuscripcion(FondoView viewRequest) {
		return fondoManager.simularSuscripcion(viewRequest);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<FondoView> suscribir(FondoView viewRequest) {
		return fondoManager.suscribir(viewRequest);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<CuentasConsultaFondoView> obtenerFondosSuscriptosYDisponibles(
			CuentasConsultaFondoView viewRequest) {
		return fondoManager.obtenerFondosSuscriptosYDisponibles(viewRequest);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<CuentasConsultaFondoView> obtenerFondosSuscriptosYDisponiblesBPriv(
			CuentasConsultaFondoView viewRequest) {
		return fondoManager.obtenerFondosSuscriptosYDisponiblesBPriv(viewRequest);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<ConfigFondoView> obtenerDatosConfig(ConfigFondoView requestView) {
		return fondoManager.obtenerDatosConfig(requestView);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<ConfigFondoView> aceptarContrato(ConfigFondoView requestView) {
		return fondoManager.aceptarContrato(requestView);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void grabarEstadisticaLegalBPersonal(ConfigFondoView requestView) {
		fondoManager.grabarEstadisticaLegalBPersonal(requestView);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void grabarEstadisticaLegalBPriv(ConfigFondoView requestView) {
		fondoManager.grabarEstadisticaLegalBPriv(requestView);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<TenenciasFondoView> obtenerTenencias(TipoBancaView requestView) {
		return fondoManager.obtenerTenencias(requestView);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<FondoView> finalizarSuscribirFondos(FondoView viewRequest) {
		return fondoManager.finalizarSuscribirFondos(viewRequest);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<ConfigFondoBPrivView> obtenerDatosConfigBPriv(ConfigFondoBPrivView requestView) {
		return fondoManager.obtenerDatosConfigBpriv(requestView);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<SimulacionSuscripcionOutView> simularSuscripcionBPriv(SimulacionInView viewRequest) {
		return fondoManager.simularSuscripcionBPriv(viewRequest);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<SuscripcionOutView> finalizarSuscribirFondosBPriv(SuscripcionInView viewRequest) {
		return fondoManager.finalizarSuscribirFondosBPriv(viewRequest);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<ComprobanteSuscripcionView> verComprobanteBPriv(DatosComprobante viewRequest) {
		return fondoManager.verComprobanteBPriv(viewRequest);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<ComprobanteSuscripcionView> verComprobante(DatosComprobante viewRequest) {
		return fondoManager.verComprobante(viewRequest);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<CotizacionDeFondosView> consultarCotizaciones() {
		return fondoManager.consultarCotizaciones();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<ConsultaHorariosYHonorariosView> consultarHorariosYHonorarios() {
		return fondoManager.consultarHorariosYHonorarios();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<DetalleDeFondoOutView> obtenerDetalleDeFondo(DetalleDeFondoIn viewRequest) {
		return fondoManager.obtenerDetalleDeFondo(viewRequest);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<MovimientosView> ultimosMovimientos(MovimientosInView viewRequest) {
		return fondoManager.ultimosMovimientos(viewRequest);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<MovimientosView> busquedaMovimientos(MovimientosInView viewRequest) {
		return fondoManager.busquedaMovimientos(viewRequest);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.sei.FondoSEI#
	 * obtenerGraficosRTL()
	 */
	@Override
	public Respuesta<GraficoFondosRTLOutView> obtenerGraficosRTL() {
		return fondoManager.obtenerGraficosRTL();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.sei.FondoSEI#
	 * obtenerGraficosBPriv()
	 */
	@Override
	public Respuesta<GraficoFondosBPrivOutView> obtenerGraficosBPriv() {
		return fondoManager.obtenerGraficosBPriv();
	}

	/**
	 * Obtiene todos los fondos existente para ver sus ultimos movimientos.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	@Override
	public Respuesta<CuentasConsultaFondoView> obtenerFondosUltimosMovimientos(CuentasConsultaFondoView viewRequest) {
		return fondoManager.obtenerFondosUltimosMovimientos(viewRequest);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.sei.FondoSEI#
	 * descargarComprobanteSuscripcionPDF(ar.com.santanderrio.obp.servicios.
	 * inversiones.fondos.view.ComprobanteSuscripcionFondo)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteSuscripcionPDF(ComprobanteSuscripcionFondo viewRequest) {
		return fondoManager.descargarComprobanteSuscripcionPDF(viewRequest);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.sei.FondoSEI#obtenerRendimiento()
	 */
	@Override
	public Respuesta<RendimientoView> obtenerRendimiento() {
		return fondoManager.obtenerRendimiento();
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.sei.FondoSEI#obtenerRendimientoBPriv()
	 */
	@Override
	public Respuesta<RendimientoBPrivView> obtenerRendimientoBPriv() {
		return fondoManager.obtenerRendimientoBPriv();
	}

	
	@Override
	public Respuesta<ListaFechasComprobantes> obtenerListaComprobantes() {
		return descargaPdfManager.obtenerListaComprobantes(TipoPDFEnum.FONDOS_COMUNES_INVERSION);
	}

	@Override
	public Respuesta<ListadoPDF> descargaComprobantesPDF(ConsultaResumenCuentaBP cuenta) {
		return descargaPdfManager.obtenerPDF(cuenta, TipoPDFEnum.FONDOS_COMUNES_INVERSION);
	}

	@Override
	public Respuesta<Void> grabarEstadisticaGoToAGD() {
		return fondoManager.grabarEstadisticaGoToAGD();
	}
	
	@Override
	public Respuesta<Void> grabarEstadisticaGoToAGDBpriv() {
		return fondoManager.grabarEstadisticaGoToAGDBpriv();
	}

}
