/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.sei.impl;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ComprobanteTransferenciaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConfirmarTransferenciaInEntity;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConfirmarTransferenciaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaCuentaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaResumenCuentaBP;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaUltimosMovimientosView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CopiarMensajeView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasIntermedioView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.DatosComprobanteTransferencia;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.DetalleMovimientosView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.IntervinienteView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.IntervinienteinEntity;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.MovimientoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ResumenesMensualesCuentaView;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.manager.CuentasBancaPrivadaManager;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.sei.CuentasBancaPrivadaSEI;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.view.ComprobanteNuevaTransferencia;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.view.DetalleCuentaCBUBancaPrivadaView;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.view.NuevaTransferenciaView;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.view.SelectorCuentasBancaPrivadaView;
import ar.com.santanderrio.obp.servicios.inversiones.descargapdf.entities.ListadoPDF;
import ar.com.santanderrio.obp.servicios.inversiones.descargapdf.entities.TipoPDFEnum;
import ar.com.santanderrio.obp.servicios.inversiones.descargapdf.manager.DescargaPdfManager;
import ar.com.santanderrio.obp.servicios.inversiones.movimientosbancaprivada.manager.MovimientosBancaPrivadaManager;

/**
 * The Class CuentasBancaPrivadaSEIImpl.
 */
@Component
public class CuentasBancaPrivadaSEIImpl implements CuentasBancaPrivadaSEI {

	/** The cuentas banca privada manager. */
	@Autowired
	private CuentasBancaPrivadaManager cuentasBancaPrivadaManager;

	/** The movimientos banca privada manager. */
	@Autowired
	private MovimientosBancaPrivadaManager movimientosBancaPrivadaManager;
	
	@Autowired
	private DescargaPdfManager descargaPdfManager;
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.sei.CuentasBancaPrivadaSEI#obtenerInicio()
	 */
	@Override
	public Respuesta<CuentasIntermedioView> obtenerInicio() {
		return cuentasBancaPrivadaManager.obtenerInicioCuentas();
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.sei.CuentasBancaPrivadaSEI#modificarApodo(ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaCuentaView)
	 */
	@Override
	public Respuesta<Void> modificarApodo(ConsultaCuentaView cuenta) {
		return cuentasBancaPrivadaManager.modificarApodo(cuenta.getNumeroCuenta(), cuenta.getAlias());
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.sei.CuentasBancaPrivadaSEI#verDetalleCBU(ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaCuentaView)
	 */
	@Override
	public Respuesta<DetalleCuentaCBUBancaPrivadaView> verDetalleCBU(ConsultaCuentaView cuenta) {
		return cuentasBancaPrivadaManager.verDetalleCBU(cuenta.getNumeroCuenta());
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.sei.CuentasBancaPrivadaSEI#obtenerCuentas(ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaCuentaView)
	 */
	@Override
	public Respuesta<SelectorCuentasBancaPrivadaView> obtenerCuentas(ConsultaCuentaView cuenta) {
		return cuentasBancaPrivadaManager.obtenerCuentas(cuenta);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.sei.CuentasBancaPrivadaSEI#obtenerMovimientos(ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaUltimosMovimientosView)
	 */
	@Override
	public Respuesta<MovimientoView> obtenerMovimientos(ConsultaUltimosMovimientosView consultaUltimosMovimientosView) {
		return movimientosBancaPrivadaManager.obtenerMovimientosPrimeraVez(consultaUltimosMovimientosView);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.sei.CuentasBancaPrivadaSEI#descargarComprobanteCBUCuenta()
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteCBUCuenta() {
		return cuentasBancaPrivadaManager.descargarComprobanteCBUCuenta();
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.sei.CuentasBancaPrivadaSEI#copiarCBU()
	 */
	@Override
	public Respuesta<CopiarMensajeView> copiarCBU() {
		return cuentasBancaPrivadaManager.copiarCBU();
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.sei.CuentasBancaPrivadaSEI#compartirCBU()
	 */
	@Override
	public Respuesta<Void> compartirCBU() {
		return cuentasBancaPrivadaManager.compartirCBU();
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.sei.CuentasBancaPrivadaSEI#obtenerMasMovimientos()
	 */
	@Override
	public Respuesta<MovimientoView> obtenerMasMovimientos() {
		return movimientosBancaPrivadaManager.obtenerMasMovimientos();
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.sei.CuentasBancaPrivadaSEI#nuevaTransferencia()
	 */
	@Override
	public Respuesta<NuevaTransferenciaView> nuevaTransferencia() {
		return cuentasBancaPrivadaManager.nuevaTransferencia();
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.sei.CuentasBancaPrivadaSEI#obtenerInterviniente(ar.com.santanderrio.obp.servicios.cuentas.web.view.IntervinienteinEntity)
	 */
	public Respuesta<IntervinienteView> obtenerInterviniente(IntervinienteinEntity entity) {
		return cuentasBancaPrivadaManager.obtenerInterviniente(entity);

	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.sei.CuentasBancaPrivadaSEI#verDetalleMovimiento()
	 */
	@Override
	public Respuesta<DetalleMovimientosView> verDetalleMovimiento() {
		return cuentasBancaPrivadaManager.verDetalleMovimiento();
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.sei.CuentasBancaPrivadaSEI#confirmarTransferencia(ar.com.santanderrio.obp.servicios.cuentas.web.view.ConfirmarTransferenciaInEntity)
	 */
	@Override
	public Respuesta<ConfirmarTransferenciaView> confirmarTransferencia(ConfirmarTransferenciaInEntity entity) {
		return cuentasBancaPrivadaManager.confirmarTransferencia(entity);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.sei.CuentasBancaPrivadaSEI#verComprobanteTransferencia(ar.com.santanderrio.obp.servicios.cuentas.web.view.DatosComprobanteTransferencia)
	 */
	@Override
	public Respuesta<ComprobanteTransferenciaView> verComprobanteTransferencia(
			DatosComprobanteTransferencia viewRequest) {
		return cuentasBancaPrivadaManager.verComprobanteTransferencia(viewRequest);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.sei.CuentasBancaPrivadaSEI#descargarComprobantePDFNuevaTransferencia(ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.view.ComprobanteNuevaTransferencia)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobantePDFNuevaTransferencia(ComprobanteNuevaTransferencia viewRequest) {
		return cuentasBancaPrivadaManager.descargarComprobantePDFNuevaTransferencia(viewRequest);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.sei.CuentasBancaPrivadaSEI#exportarMovimientos()
	 */
	@Override
	public Response exportarMovimientos() {
		return movimientosBancaPrivadaManager.exportarMovimientos();
	}
	
	@Override
    public Respuesta<ReporteView> exportarMovimientosPDF() {
        return movimientosBancaPrivadaManager.exportarMovimientosPDF();
    }

	@Override
	public Respuesta<ResumenesMensualesCuentaView> obtenerListaResumen(ConsultaResumenCuentaBP cuenta) {
		return cuentasBancaPrivadaManager.obtenerListaResumen(cuenta.getNumeroCuenta());
	}

	@Override
	public Respuesta<ListadoPDF> obtenerResumenesPDF(ConsultaResumenCuentaBP cuenta) {
		return descargaPdfManager.obtenerPDF(cuenta, TipoPDFEnum.CUENTAS_BANCA_PRIVADA);
	}

}