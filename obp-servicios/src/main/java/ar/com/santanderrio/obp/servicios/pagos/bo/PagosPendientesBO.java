/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.bo;

import java.math.BigDecimal;
import java.util.List;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.bo.BusinessObject;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.pagos.entities.CuentaPagoMisCuentas;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;
import ar.com.santanderrio.obp.servicios.pagos.entities.TipoDeModificacion;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.view.TarjetasAdhesionDebitoView;

/**
 * The Interface PagosPendientesBO.
 */
public interface PagosPendientesBO extends BusinessObject {

	/**
	 * Consulta las deudas informadas para un cliente.
	 *
	 * @param cliente
	 *            the cliente
	 * @return respuesta con lista de deudas informadase
	 */
	Respuesta<List<PagoPendiente>> obtenerPagosPendientes(Cliente cliente);

	/**
	 * Obtener cuentas habilitadas.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<List<CuentaPagoMisCuentas>> obtenerCuentasHabilitadas(Cliente cliente);

	/**
	 * Gets the pagos pendientes prestamo.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the pagos pendientes prestamo
	 */
	Respuesta<List<PagoPendiente>> getPagosPendientesPrestamo(Cliente cliente);

	/**
	 * Gets the pagos pendientes tarjetas.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the pagos pendientes tarjetas
	 */
	Respuesta<List<PagoPendiente>> getPagosPendientesTarjetas(Cliente cliente);

	/**
	 * Obtener recargas agendadas tarjetas recargables.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<List<PagoPendiente>> obtenerRecargasAgendadasTarjetasRecargables(Cliente cliente);

	/**
	 * Gets the datos tarjetas. Obtiene solo las tarjetas titulares.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the datos tarjetas
	 * @throws BusinessException
	 *             the business exception
	 */
	List<DatosTarjeta> getDatosTarjetas(Cliente cliente) throws BusinessException;

	/**
	 * Gets the datos tarjetas con adicionales. Obtiene todas las tarjetas,
	 * titulares y adicionales.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the datos tarjetas con adicionales
	 * @throws BusinessException
	 *             the business exception
	 */
	List<DatosTarjeta> getDatosTarjetasConAdicionales(Cliente cliente) throws BusinessException;

	/**
	 * Tiene pagos programados.
	 *
	 * @param cliente
	 *            the cliente
	 * @param datosTarjeta
	 *            the datos tarjeta
	 * @param fechaLimite
	 *            the fecha limite
	 * @return the boolean
	 * @throws BusinessException
	 *             the business exception
	 */
	Boolean tienePagosProgramados(Cliente cliente, DatosTarjeta datosTarjeta, String fechaLimite)
			throws BusinessException;

	/**
	 * Modificar adhesion.
	 *
	 * @param cliente
	 *            the cliente
	 * @param servicioAdherido
	 *            the servicio adherido
	 * @param cuentaDebito
	 *            the cuenta debito
	 * @param limiteAdhesion
	 *            the limite adhesion
	 * @param tipoDeModificacion
	 *            the tipo de modificacion
	 * @return the respuesta
	 */
	Respuesta<ResultadoTransaccion> modificarAdhesion(Cliente cliente, PagoPendiente servicioAdherido,
			Cuenta cuentaDebito, BigDecimal limiteAdhesion, TipoDeModificacion tipoDeModificacion);

	/**
	 * Consulta los débitos automáticos de un cliente.
	 *
	 * @param cliente
	 *            the cliente
	 * @return respuesta con lista de los débitos automáticos
	 */
	Respuesta<List<PagoPendiente>> obtenerDebitosAutomaticos(Cliente cliente);

	/**
	 * Obtener datos tarjetas pago puntual.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the list
	 * @throws BusinessException
	 *             the business exception
	 */
	List<TarjetasAdhesionDebitoView> obtenerDatosTarjetasPagoPuntual(Cliente cliente) throws BusinessException;

	/**
	 * Modificar adhesion pago automatico de pago mis cuentas. DTF 10216, 9770,
	 * 10217
	 *
	 * @param cliente
	 *            the cliente
	 * @param servicioAdherido
	 *            the servicio adherido
	 * @param cuentaDebito
	 *            the cuenta debito
	 * @param limiteAdhesion
	 *            the limite adhesion
	 * @param tipoDeModificacion
	 *            the tipo de modificacion
	 * @param cuentaDelServicio
	 *            the cuenta del servicio
	 * @return the respuesta
	 */
	Respuesta<ResultadoTransaccion> modificarAdhesionPagoAutomaticoDePagoMisCuentas(Cliente cliente,
			PagoPendiente servicioAdherido, Cuenta cuentaDebito, BigDecimal limiteAdhesion,
			TipoDeModificacion tipoDeModificacion, String cuentaDelServicio);

	/**
	 * Obtiene la fecha de vencimiento de una tarjeta.
	 *
	 * @author mariano.g.pulera
	 * @param cliente
	 *            the cliente
	 * @param cuenta
	 *            the cuenta
	 * @return the string
	 * @throws BusinessException
	 *             the business exception
	 * @throws DAOException
	 *             the DAO exception
	 */
	String obtenerFechaVencimientoTarjeta(Cliente cliente, Cuenta cuenta) throws BusinessException, DAOException;

	/**
	 * Obtener datos una tarjeta.
	 *
	 * @param cliente
	 *            the cliente
	 * @param cuenta
	 *            the cuenta
	 * @return the datos tarjeta
	 * @throws BusinessException
	 *             the business exception
	 */
	DatosTarjeta obtenerDatosUnaTarjeta(Cliente cliente, Cuenta cuenta) throws BusinessException;

	/**
	 * Gets the datos iniciales pago tarjetas.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the datos iniciales pago tarjetas
	 * @throws BusinessException
	 *             the business exception
	 */
	List<DatosTarjeta> getDatosInicialesPagoTarjetas(Cliente cliente) throws BusinessException;
}
