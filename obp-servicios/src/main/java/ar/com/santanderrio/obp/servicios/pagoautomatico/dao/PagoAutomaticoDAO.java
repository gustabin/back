/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagoautomatico.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.debitoautomatico.entities.ComprobanteAdhesionDebitoAutomatico;
import ar.com.santanderrio.obp.servicios.debitoautomatico.entities.ComprobanteDebitoAutomatico;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.AdhesionPagoAutomatico;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;

/**
 * The Interface PagoAutomaticoDAO.
 */
public interface PagoAutomaticoDAO {

	/**
	 * Generar comprobante adhesion.
	 *
	 * @param comprobanteDebitoAutomatico
	 *            the comprobante debito automatico
	 * @return the reporte
	 */
	Reporte generarComprobanteAdhesion(ComprobanteDebitoAutomatico comprobanteDebitoAutomatico);

	/**
	 * Eliminar pago puntual.
	 *
	 * @param pagoPendiente
	 *            the pago pendiente
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 * @throws DAOException
	 *             the DAO exception
	 */
	Respuesta<ResultadoTransaccion> eliminarPagoPuntual(PagoPendiente pagoPendiente, Cliente cliente)
			throws DAOException;

	/**
	 * Ejecutar baja adhesion.
	 *
	 * @param pagoPendiente
	 *            the pago pendiente
	 * @param cliente
	 *            the cliente
	 * @return the resultado transaccion
	 * @throws DAOException
	 *             the DAO exception
	 */
	ResultadoTransaccion ejecutarBajaAdhesion(PagoPendiente pagoPendiente, Cliente cliente) throws DAOException;

	/**
	 * Ejecutar Adhesion a pago mis cuentas.
	 *
	 * @param cliente
	 *            the cliente
	 * @param adhesionPagoAutomatico
	 *            the adhesion pago automatico
	 * @return the resultado transaccion
	 * @throws DAOException
	 *             the DAO exception
	 */
	String adhierePagoMisCuentas(Cliente cliente, AdhesionPagoAutomatico adhesionPagoAutomatico) throws DAOException;

	/**
	 * Ejecutar Adhesion a pago automatico.
	 *
	 * @param cliente
	 *            the cliente
	 * @param adhesionPagoAutomatico
	 *            the adhesion pago automatico
	 * @return Integer
	 * @throws DAOException
	 *             the DAO exception
	 */
	ResultadoTransaccion adhierePagoAutomatico(Cliente cliente, AdhesionPagoAutomatico adhesionPagoAutomatico)
			throws DAOException;

	/**
	 * Ejecutar baja a pago mis cuentas.
	 *
	 * @param cliente
	 *            the cliente
	 * @param adhesionPagoAutomatico
	 *            the adhesion pago automatico
	 * @return the resultado transaccion
	 * @throws DAOException
	 *             the DAO exception
	 */
	IatxResponse bajaPagoMisCuentas(Cliente cliente, AdhesionPagoAutomatico adhesionPagoAutomatico) throws DAOException;

	
	
	Reporte generarComprobanteAdhesionPagoAutomatico(ComprobanteAdhesionDebitoAutomatico comprobanteAdhesionDebitoAutomatico);

	
}
