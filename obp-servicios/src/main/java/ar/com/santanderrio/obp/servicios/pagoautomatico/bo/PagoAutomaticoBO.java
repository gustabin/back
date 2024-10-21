/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagoautomatico.bo;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.debitoautomatico.entities.ComprobanteAdhesionDebitoAutomatico;
import ar.com.santanderrio.obp.servicios.debitoautomatico.entities.ComprobanteDebitoAutomatico;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.AdhesionPagoAutomatico;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;

/**
 * The Interface PagoAutomaticoBO.
 */
/**
 * @author maximiliano_cuno
 *
 */
public interface PagoAutomaticoBO {

	/**
	 * genera el Reporte de Jasper y arma el objeto de respuesta.
	 *
	 * @param comprobanteDebitoAutomatico
	 *            the comprobante debito automatico
	 * @return the respuesta
	 */
	Respuesta<Reporte> generarComprobanteAdhesion(ComprobanteDebitoAutomatico comprobanteDebitoAutomatico);

	/**
	 * Ejecutar adhesion pago automatico.
	 *
	 * @param cliente
	 *            the cliente
	 * @param adhesionPagoAutomatico
	 *            the adhesion pago automatico
	 * @return the respuesta
	 */
	Respuesta<AdhesionPagoAutomatico> ejecutarAdhesionPagoAutomatico(Cliente cliente,
			AdhesionPagoAutomatico adhesionPagoAutomatico);

	/**
	 * Eliminar pago puntual.
	 *
	 * @param pagoPendiente
	 *            the pago pendiente
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<ResultadoTransaccion> eliminarPagoPuntual(PagoPendiente pagoPendiente, Cliente cliente);

	/**
	 * Ejecutar baja adhesion.
	 *
	 * @param pagoPendiente
	 *            the pago pendiente
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<ResultadoTransaccion> ejecutarBajaAdhesion(PagoPendiente pagoPendiente, Cliente cliente);


	/**
	 * @param comprobanteAdhesionDebitoAutomatico
	 * @return
	 */
	Respuesta<Reporte> generarComprobanteAdhesionPagoAutomatico(ComprobanteAdhesionDebitoAutomatico comprobanteAdhesionDebitoAutomatico);

}
