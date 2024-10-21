/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagoautomatico.sei.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.debitoautomatico.entities.ComprobanteAdhesionDebitoAutomatico;
import ar.com.santanderrio.obp.servicios.debitoautomatico.entities.ComprobanteDebitoAutomatico;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.AdhesionPagoAutomatico;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.TerminosCondiciones;
import ar.com.santanderrio.obp.servicios.pagoautomatico.manager.PagoAutomaticoManager;
import ar.com.santanderrio.obp.servicios.pagoautomatico.sei.PagoAutomaticoSEI;
import ar.com.santanderrio.obp.servicios.pagoautomatico.view.AdhesionDebitoAutomaticoEnCuentaView;
import ar.com.santanderrio.obp.servicios.pagoautomatico.view.AdhesionDebitoAutomaticoEnTarjetaView;
import ar.com.santanderrio.obp.servicios.pagoautomatico.view.CodigoEmpresaView;
import ar.com.santanderrio.obp.servicios.pagoautomatico.view.HashDebitoAutomaticoTarjetaView;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoSelectionView;

/**
 * The Class NuevoPagoAutomaticoSEIImpl.
 */
@Component("nuevoPagoAutomaticoSEI")
public class PagoAutomaticoSEIImpl implements PagoAutomaticoSEI {

	/** The nuevo pago automatico manager. */
	@Autowired
	private PagoAutomaticoManager pagoAutomaticoManager;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.nuevopagoautomatico.sei.
	 * NuevoPagoAutomaticoSEI#obtenerCuentas()
	 */
	@Override
	public Respuesta<MedioPagoSelectionView> obtenerCuentas() {
		return pagoAutomaticoManager.obtenerCuentas(sesionCliente.getCliente());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.pagoautomatico.sei.PagoAutomaticoSEI#
	 * confirmarAdhesionPagoAutomatico(ar.com.santanderrio.obp.servicios.
	 * pagoautomatico.entities.AdhesionPagoAutomatico)
	 */
	@Override
	public Respuesta<AdhesionPagoAutomatico> confirmarAdhesionPagoAutomatico(
			AdhesionPagoAutomatico adhesionPagoAutomatico) {
		return pagoAutomaticoManager.confirmarAdhesionPagoAutomatico(adhesionPagoAutomatico);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.nuevopagoautomatico.sei.
	 * NuevoPagoAutomaticoSEI#cargarTerminosCondiciones()
	 */
	@Override
	public Respuesta<TerminosCondiciones> cargarTerminosCondiciones() {
		return pagoAutomaticoManager.cargarTerminosCondiciones();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.nuevopagoautomatico.sei.
	 * NuevoPagoAutomaticoSEI#adherirDebito(ar.com.santanderrio.obp.servicios.
	 * nuevopagoautomatico.view.
	 * AdhesionDebitoAutAdhesionDebitoAutomaticoViewomaticoView)
	 */
	@Override
	public Respuesta<AdhesionDebitoAutomaticoEnCuentaView> adherirDebito(
			AdhesionDebitoAutomaticoEnCuentaView adhesionDebitoAutomaticoView) {
		return pagoAutomaticoManager.adherirDebitoEnCuenta(sesionParametros.getRegistroSession(),
				adhesionDebitoAutomaticoView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.nuevopagoautomatico.sei.
	 * NuevoPagoAutomaticoSEI#generarComprobanteAdhesion(ar.com.santanderrio.obp
	 * .servicios.debitoautomatico.entities.ComprobanteDebitoAutomatico)
	 */
	@Override
	public Respuesta<ReporteView> generarComprobanteAdhesion(ComprobanteDebitoAutomatico comprobanteDebitoAutomatico) {
		return pagoAutomaticoManager.generarComprobanteAdhesion(comprobanteDebitoAutomatico);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.nuevopagoautomatico.sei.
	 * NuevoPagoAutomaticoSEI#estadisticaComprobanteNuevoPagoAutomatico()
	 */
	@Override
	public void estadisticaComprobanteNuevoPagoAutomatico() {
		pagoAutomaticoManager.estadisticaComprobanteNuevoPagoAutomatico();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.nuevopagoautomatico.sei.
	 * NuevoPagoAutomaticoSEI#obtenerDatosAdhesionDebitoAutomaticoEnTarjeta(ar.
	 * com.
	 * santanderrio.obp.servicios.nuevopagoautomatico.view.CodigoEmpresaView)
	 */
	@Override
	public Respuesta<AdhesionDebitoAutomaticoEnTarjetaView> obtenerDatosAdhesionDebitoAutomaticoEnTarjeta(
			CodigoEmpresaView codigoEmpresa) {
		return pagoAutomaticoManager.obtenerDatosAdhesionDebitoAutomaticoEnTarjeta(codigoEmpresa.getNombreFantasia());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.nuevopagoautomatico.sei.
	 * NuevoPagoAutomaticoSEI#generarHashDeSeguridad(ar.com.santanderrio.obp.
	 * servicios.nuevopagoautomatico.view.HashDebitoAutomaticoTarjetaView)
	 */
	@Override
	public Boolean generarHashDeSeguridad(HashDebitoAutomaticoTarjetaView datosParaHash) {
		return pagoAutomaticoManager.generarHashSeguridad(datosParaHash);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.pagoautomatico.sei.PagoAutomaticoSEI#
	 * validarAutenticacionPagoAutomatico()
	 */
	@Override
	public Respuesta<Boolean> validarAutenticacionPagoAutomatico() {
		return pagoAutomaticoManager.validarAutenticacionPagoAutomatico();
	}

	@Override
	public Respuesta<ReporteView> generarComprobanteAdhesionPagoAutomatico(
			ComprobanteAdhesionDebitoAutomatico comprobanteAdhesionDebitoAutomatico) {
		return pagoAutomaticoManager.generarComprobanteAdhesionPagoAutomatico(comprobanteAdhesionDebitoAutomatico);
	}
}
