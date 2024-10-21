/*
 *
 */
package ar.com.santanderrio.obp.servicios.nuevopago.sei.impl;

import ar.com.santanderrio.obp.servicios.cuentas.web.view.*;
import ar.com.santanderrio.obp.servicios.seguros.entities.EmisionOfertaIntegrada;
import ar.com.santanderrio.obp.servicios.seguros.entities.GastoProtegido;
import ar.com.santanderrio.obp.servicios.seguros.entities.Poliza;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuit.entities.Cuit;
import ar.com.santanderrio.obp.servicios.nuevopago.manager.NuevoPagoManager;
import ar.com.santanderrio.obp.servicios.nuevopago.sei.NuevoPagoSEI;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoSelectionView;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoView;
import ar.com.santanderrio.obp.servicios.pagos.entities.NuevoPago;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConfiguracionRecargaView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultaConfiguracionView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoPendienteView;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.view.PuntoDeAccesoView;

/**
 * Created by david.ruiz on 11-Aug-16.
 */
@Component("nuevoPagoSEI")
public class NuevoPagoSEIImpl implements NuevoPagoSEI {

	/** The nuevo pago manager. */
	@Autowired
	private NuevoPagoManager nuevoPagoManager;

	/**
	 * Obtengo las facturas pendientes del cliente.
	 *
	 * @param medioPagoView
	 *            the medio pago view
	 * @return the respuesta
	 */
	@Override
	public Respuesta<MedioPagoSelectionView> obtenerPagos(MedioPagoView medioPagoView) {
		return nuevoPagoManager.obtenerFacturas(medioPagoView);
	}

	/**
	 * Valido el cuit pasado como parametro.
	 *
	 * @param cuit
	 *            the cuit
	 * @return the respuesta
	 */

	@Override
	public Respuesta<String> validarCuit(Cuit cuit) {
		return nuevoPagoManager.validarCuit(cuit.getNumero());
	}

	/**
	 * Obtiene la lista de cuentas para realizar un pago sin factura, ej. VEP,
	 * DOMESTICO
	 *
	 * @param consulta
	 *            the consulta
	 * @return the respuesta
	 */
	@Override
	public Respuesta<MedioPagoSelectionView> obtenerCuentas(ConsultaConfiguracionView consulta) {
		return nuevoPagoManager.obtenerCuentas(consulta);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.nuevopago.sei.NuevoPagoSEI#
	 * obtenerConfiguracionRecarga(ar.com.santanderrio.obp.servicios.pagos.
	 * entities.MedioPagoView)
	 */
	@Override
	public Respuesta<ConfiguracionRecargaView> obtenerConfiguracionRecarga(
			ConsultaConfiguracionView consultaConfiguracionRecargaView) {
		return nuevoPagoManager.obtenerConfiguracionRecarga(consultaConfiguracionRecargaView);
	}

	/**
	 * Realiza el pago de una o mas facturas seleccionadas.
	 *
	 * @param nuevoPago
	 *            the nuevo pago
	 * @return el resultado del pago
	 */
	@Override
	public Respuesta<NuevoPago> confirmarPagoPuntual(NuevoPago nuevoPago) {
		return nuevoPagoManager.pagoPuntual(nuevoPago);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.nuevopago.sei.NuevoPagoSEI#
	 * estadisticaComprobanteNuevoPago()
	 */
	@Override
	public void estadisticaComprobanteNuevoPago(CuentaSeleccionView seleccion) {
		nuevoPagoManager.estadisticaComprobanteNuevoPago(seleccion);
	}

	/**
	 * Solicitu del pago puntual de un evento seleccionado.
	 *
	 * @param pagoPendienteView
	 *            the nuevo pago
	 * @return el resultado del pago
	 */
	@Override
	public void grabarEstadisticaPagoPuntual(PagoPendienteView pagoPendienteView) {
		nuevoPagoManager.grabarEstadisticaPagoPuntual(pagoPendienteView);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.nuevopago.sei.NuevoPagoSEI#
	 * grabarEstadisticaRecargaIngresoNumero(ar.com.santanderrio.obp.servicios.
	 * simuladorprestamo.view.PuntoDeAccesoView)
	 */
	@Override
	public Respuesta<Void> grabarEstadisticaRecargaIngresoNumero(PuntoDeAccesoView puntoDeAcceso) {
		return nuevoPagoManager.grabarEstadisticaRecargaIngresoNumero(puntoDeAcceso);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.nuevopago.sei.NuevoPagoSEI#
	 * verificarFactoresAutenticacion()
	 */
	@Override
	public Respuesta<Boolean> verificarFactoresAutenticacion() {
		return nuevoPagoManager.verificarFactoresAutenticacion();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.nuevopago.sei.NuevoPagoSEI#
	 * descargarComprobantePDF(ar.com.santanderrio.obp.servicios.pagos.entities.
	 * NuevoPago)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobantePDF(NuevoPago nuevoPagoView) {

		return nuevoPagoManager.descargarComprobantePDF(nuevoPagoView);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.nuevopago.sei.NuevoPagoSEI#
	 * grabarEstadisticaRecargaIngresoNumero(ar.com.santanderrio.obp.servicios.
	 * simuladorprestamo.view.PuntoDeAccesoView)
	 */
	@Override
	public Respuesta<Void> continuarPago(NuevoPago nuevoPagoView) {
		return nuevoPagoManager.continuarPago(nuevoPagoView);
	}

	public Respuesta<EmisionGastosProtegidosView> emitirGastosProtegidos(EmisionOfertaIntegrada nuevaEmisioView) {
		return nuevoPagoManager.emitirGastosProtegidos(nuevaEmisioView);
	}

	public Respuesta<GastoProtegidoView> consultarGastosProtegidos(GastoProtegido gastoProtegido) {
		return nuevoPagoManager.consultarGastosProtegidos(gastoProtegido);
	}

	public Respuesta<FlagCompraProtegidaView> getFlagCompraProtegida(){
		return nuevoPagoManager.getFlagCompraProtegida();
	}

	public Respuesta<Poliza> consultarPolizaExistente(String tokenJwt, String ramo){
		return nuevoPagoManager.consultarPolizaExistente(tokenJwt, ramo);
	};


}
