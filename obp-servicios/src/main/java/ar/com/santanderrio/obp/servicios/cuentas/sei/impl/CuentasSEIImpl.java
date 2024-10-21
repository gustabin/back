/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.sei.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ComprobanteAltaDestinatarioView;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ComprobanteBajaDestinatarioView;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AliasFavoritoProducto;
import ar.com.santanderrio.obp.servicios.cuentas.sei.CuentasSEI;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.manager.TraspasoAutomaticoManager;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.manager.TraspasoManualManager;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.view.TraspasoAutomaticoView;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.view.TraspasoManualView;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.AliasCuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.ResumenCuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.AdhesionResumenView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaCuentaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CopiarMensajeView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasIntermedioView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.DetalleCBUView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ResumenesMensualesCuentaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.SelectorCuentasView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.StackSelectorCuentasView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

// TODO: Auto-generated Javadoc
/**
 * The Class CuentasSEIImpl.
 */
@Component("cuentasSEI")
public class CuentasSEIImpl implements CuentasSEI {

	/** The Constant USER_AGENT. */
	private static final String USER_AGENT = "User-Agent";

	/** The manager. */
	@Autowired
	private CuentaManager manager;

	/** The alias cuenta. */
	@Autowired
	private AliasCuentaManager aliasCuenta;

	/** The Traspaso Automatico. */
	@Autowired
	private TraspasoAutomaticoManager traspasoAutomaticoManager;

	/** The Traspaso Manual. */
	@Autowired
	private TraspasoManualManager traspasoManualManager;

	/** The resumen cuenta manager. */
	@Autowired
	private ResumenCuentaManager resumenCuentaManager;

	/**
	 * Obtiene las cuentas.
	 *
	 * @param mc
	 *            the mc
	 * @param cuenta
	 *            the cuenta
	 * @return the cuentas
	 */
	@Override
	public Respuesta<SelectorCuentasView> getCuentas(org.apache.cxf.jaxrs.ext.MessageContext mc,
			ConsultaCuentaView cuenta) {
		return manager.getCuentas(mc.getHttpHeaders().getRequestHeaders().get(USER_AGENT).get(0),
				cuenta.getNumeroCuenta());
	}
	
	/**
	 * Obtiene las cuentas.
	 *
	 * @return the list of cuentas
	 */
	@Override
	public Respuesta<StackSelectorCuentasView> getListaCuentasCliente() {
		return manager.getListaCuentasCliente();
	}	

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.sei.CuentasSEI#getCBU(org.
	 * apache.cxf.jaxrs.ext.MessageContext,
	 * ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaCuentaView)
	 */

	/**
	 * Copiar cbu.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<CopiarMensajeView> copiarCBU() {
		return manager.copiarCBU();
	}

	/**
	 * Imprimir.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ReporteView> imprimir(ConsultaCuentaView cuenta) {
		return manager.obtenerReporteCuenta(cuenta);
	}

	/**
	 * Metodo que actualiza el alias de una cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
	@Override
	public Respuesta<Void> actualizarAlias(ConsultaCuentaView cuenta) {
		return manager.actualizarAlias(cuenta.getNumeroCuenta(), cuenta.getAlias());
	}

	/**
	 * Metodo que pone la marca favorita.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
	@Override
	public Respuesta<Void> marcarFavorita(ConsultaCuentaView cuenta) {
		return manager.marcarFavorita(cuenta.getNumeroCuenta());
	}

	/**
	 * Metodo que obtiene una lisgta con el resument de la cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ResumenesMensualesCuentaView> obtenerListaResumen(ConsultaCuentaView cuenta) {
		return resumenCuentaManager.obtenerListaResumen(cuenta.getNumeroCuenta());
	}

	/**
	 * Toma solo la primera fecha|id y retorna el pdf asociado.
	 *
	 * @param consultaCuentaView
	 *            the consulta cuenta view
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ReporteView> obtenerResumenesPDF(ConsultaCuentaView consultaCuentaView) {
		return resumenCuentaManager.obtenerResumenesPDF(consultaCuentaView);
	}

	/**
	 * Obtiene los resumenes en un archivo zip.
	 * 
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ReporteView> obtenerResumenesMultiple(ConsultaCuentaView cuenta) {
		return resumenCuentaManager.obtenerResumenDescargaMultiple(Arrays.asList(cuenta.getFechas()),
				cuenta.getNumeroCuenta(), cuenta.getCantidadADescargar());
	}

	/**
	 * Verifica cuenta.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @return the cuentas
	 */
	@Override
	public Respuesta<Cliente> verificarCuenta(TransferenciaView transferenciaView) {
		return manager.verificarCuenta(transferenciaView, 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.cuentas.sei.CuentasSEI#getCuentasSaldo( )
	 */
	@Override
	public Respuesta<CuentasView> getCuentasSaldo() {
		return manager.getCuentasSaldo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.sei.CuentasSEI#
	 * obtenerDetalleCBU(ar.com.santanderrio.obp.servicios.cuentas.web.view.
	 * ConsultaCuentaView)
	 */
	@Override
	public Respuesta<DetalleCBUView> obtenerDetalleCBU(ConsultaCuentaView cuenta,
			org.apache.cxf.jaxrs.ext.MessageContext mc) {
		return manager.obtenerDetalleCBU(cuenta, mc.getHttpHeaders().getRequestHeaders().get(USER_AGENT).get(0));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.sei.CuentasSEI#compartirCBU()
	 */
	@Override
	public Respuesta<Void> compartirCBU() {
		return manager.compartirCBU();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.cuentas.sei.CuentasSEI#obtenerAliasCBU(
	 * ar.com.santanderrio.obp.servicios.cuentas.web.view.DetalleCBUView,
	 * org.apache.cxf.jaxrs.ext.MessageContext)
	 */
	@Override
	public Respuesta<DetalleCBUView> obtenerAliasCBU(DetalleCBUView cuenta,
			org.apache.cxf.jaxrs.ext.MessageContext mc) {
		return aliasCuenta.obtenerAliasCBU(cuenta.getCbu(),
				mc.getHttpHeaders().getRequestHeaders().get(USER_AGENT).get(0));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.sei.CuentasSEI#
	 * configuracionAltaAliasCBU(ar.com.santanderrio.obp.servicios.cuentas.web.
	 * view.DetalleCBUView)
	 */
	@Override
	public Respuesta<DetalleCBUView> configuracionAltaAliasCBU(DetalleCBUView detalle) {
		return aliasCuenta.continuarAliasCBU(detalle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.sei.CuentasSEI#
	 * grabarEstadisticaAyudaAliasCBU(ar.com.santanderrio.obp.servicios.cuentas.
	 * web.view.DetalleCBUView)
	 */
	@Override
	public void grabarEstadisticaAyudaAliasCBU(DetalleCBUView detalle) {
		aliasCuenta.grabadoEstadisticaAyuda(detalle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.sei.CuentasSEI#
	 * confirmacionAliasCBU(ar.com.santanderrio.obp.servicios.cuentas.web.view.
	 * ComprobanteAltaDestinatarioView, org.apache.cxf.jaxrs.ext.MessageContext)
	 */
	@Override
	public Respuesta<ComprobanteAltaDestinatarioView> confirmacionAliasCBU(ComprobanteAltaDestinatarioView view,
			org.apache.cxf.jaxrs.ext.MessageContext mc) {
		if (view.getAliasAnterior() == null) {
			return aliasCuenta.confirmacionCrearAliasCBU(view,
					mc.getHttpHeaders().getRequestHeaders().get(USER_AGENT).get(0));
		}
		return aliasCuenta.confirmacionEditarAliasCBU(view,
				mc.getHttpHeaders().getRequestHeaders().get(USER_AGENT).get(0));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.sei.CuentasSEI#
	 * comprobanteAliasCBU(ar.com.santanderrio.obp.servicios.cuentas.web.view.
	 * DetalleCBUView)
	 */
	@Override
	public void comprobanteAliasCBU(DetalleCBUView view) {
		aliasCuenta.comprobanteAliasCBU(view);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.sei.CuentasSEI#
	 * confirmacionBajaAliasCBU(ar.com.santanderrio.obp.servicios.cuentas.web.
	 * view.ComprobanteBajaDestinatarioView,
	 * org.apache.cxf.jaxrs.ext.MessageContext)
	 */
	@Override
	public Respuesta<ComprobanteBajaDestinatarioView> confirmacionBajaAliasCBU(ComprobanteBajaDestinatarioView view,
			org.apache.cxf.jaxrs.ext.MessageContext mc) {

		return aliasCuenta.confirmacionBajaAliasCBU(view,
				mc.getHttpHeaders().getRequestHeaders().get(USER_AGENT).get(0));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.cuentas.sei.CuentasSEI#eliminarAliasCBU
	 * (ar.com.santanderrio.obp.servicios.cuentas.web.view.DetalleCBUView)
	 */
	@Override
	public Respuesta<DetalleCBUView> eliminarAliasCBU(DetalleCBUView view) {

		return aliasCuenta.eliminarAliasCBU(view);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.sei.CuentasSEI#
	 * comprobanteEliminarAliasCBU(ar.com.santanderrio.obp.servicios.cuentas.web
	 * .view.DetalleCBUView)
	 */
	@Override
	public void comprobanteEliminarAliasCBU(DetalleCBUView view) {
		aliasCuenta.grabarEstadisticaComprobanteEliminarAliasCBU(view.getNumeroCuenta());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.sei.CuentasSEI#
	 * solicitudTraspaso(ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.
	 * view.TraspasoAutomaticoView)
	 */
	@Override
	public Respuesta<TraspasoAutomaticoView> solicitudTraspaso(TraspasoAutomaticoView view) {

		return traspasoAutomaticoManager.solicitarTraspasoAutomatico(view);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.sei.CuentasSEI#
	 * confirmarTraspasoAutomatico(ar.com.santanderrio.obp.servicios.cuentas.
	 * traspaso.web.view.TraspasoAutomaticoView)
	 */
	@Override
	public Respuesta<TraspasoAutomaticoView> confirmarTraspasoAutomatico(TraspasoAutomaticoView view) {

		return traspasoAutomaticoManager.confirmarTraspasoAutomatico(view);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.sei.CuentasSEI#
	 * solicitudTraspasoManual(ar.com.santanderrio.obp.servicios.cuentas.
	 * traspaso.web.view.TraspasoManualView)
	 */
	@Override
	public Respuesta<TraspasoManualView> solicitudTraspasoManual(TraspasoManualView view) {
		return traspasoManualManager.configurarTraspasoManual(view);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.sei.CuentasSEI#
	 * configuracionTraspasoManual(ar.com.santanderrio.obp.servicios.cuentas.
	 * traspaso.web.view.TraspasoManualView)
	 */
	@Override
	public Respuesta<TraspasoManualView> configuracionTraspasoManual(TraspasoManualView view) {
		return traspasoManualManager.confirmarTraspasoManual(view);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.sei.CuentasSEI#
	 * confirmacionTraspasoManual(ar.com.santanderrio.obp.servicios.cuentas.
	 * traspaso.web.view.TraspasoManualView)
	 */
	@Override
	public Respuesta<TraspasoManualView> confirmacionTraspasoManual(TraspasoManualView view) {
		return traspasoManualManager.realizarTraspasoManual(view);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.sei.CuentasSEI#
	 * comprobanteTraspasoManual()
	 */
	@Override
	public void comprobanteTraspasoManual() {
		traspasoManualManager.obtenerComprobante();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.sei.CuentasSEI#
	 * confirmarAdhesionResumen(ar.com.santanderrio.obp.servicios.cuentas.web.
	 * view.AdhesionResumenView)
	 */
	@Override
	public Respuesta<AdhesionResumenView> confirmarAdhesionResumen(AdhesionResumenView view) {
		return resumenCuentaManager.confirmarAdhesionResumen(view);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.sei.CuentasSEI#
	 * comprobanteAdhesionResumen(ar.com.santanderrio.obp.servicios.cuentas.web.
	 * view.AdhesionResumenView)
	 */
	@Override
	public void comprobanteAdhesionResumen(AdhesionResumenView view) {
		resumenCuentaManager.comprobanteAdhesionResumen(view);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.sei.CuentasSEI#
	 * obtenerInicioCuentas(org.apache.cxf.jaxrs.ext.MessageContext)
	 */
	@Override
	public Respuesta<CuentasIntermedioView> obtenerInicioCuentas(MessageContext mc) {
		return manager.obtenerInicioCuentas(mc.getHttpHeaders().getRequestHeaders().get(USER_AGENT).get(0));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.sei.CuentasSEI#
	 * obtenerTableroCuentasHome()
	 */
	@Override
	public void obtenerTableroCuentasHome() {
		manager.grabarEstadisticaObtenerCuentasHome();
	}

	@Override
	public Respuesta<List<AliasFavoritoProducto>> obtenerAliasYFavoritos() {
	    return manager.obtenerAliasYFavoritos();
	}

}
