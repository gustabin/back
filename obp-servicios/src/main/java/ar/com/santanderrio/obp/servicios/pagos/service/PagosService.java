/*
 * editado: 19/08/2016 10:46:38 - B039542 - ignacio_valek@itrsa.com.ar
 *
 */
package ar.com.santanderrio.obp.servicios.pagos.service;

import java.math.BigDecimal;
import java.util.List;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.Service;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.AdhesionDebitoAutomatico;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;
import ar.com.santanderrio.obp.servicios.pagos.entities.TipoDeModificacion;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosTarjeta;

/**
 * The Interface PagosService.
 */
public interface PagosService extends Service {

	/**
	 * Consulta los pagos pendientes para un cliente.
	 *
	 * @param cliente
	 *            the cliente
	 * @return lista de pagos pendientes
	 */
	Respuesta<List<PagoPendiente>> obtenerPagoPendiente(Cliente cliente);

	/**
	 * Gets the prestamos pendientes.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the prestamos pendientes
	 */
	Respuesta<List<PagoPendiente>> getPrestamosPendientes(Cliente cliente);

	/**
	 * Gets the tarjetas pendientes.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the tarjetas pendientes
	 */
	Respuesta<List<PagoPendiente>> getTarjetasPendientes(Cliente cliente);

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
	 * Gets the datos tarjetas con adicionales.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the datos tarjetas con adicionales
	 * @throws ServiceException
	 *             the service exception
	 */
	List<DatosTarjeta> getDatosTarjetasConAdicionales(Cliente cliente) throws ServiceException;

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
	 * @throws ServiceException
	 *             the service exception
	 */
	Boolean tienePagosProgramados(Cliente cliente, DatosTarjeta datosTarjeta, String fechaLimite)
			throws ServiceException;

	/**
	 * Ejecutar baja adhesion.
	 *
	 * @param cliente
	 *            the cliente
	 * @param servicioAdherido
	 *            the servicio adherido
	 * @return the respuesta
	 * @throws ServiceException
	 *             the service exception
	 */
	Respuesta<ResultadoTransaccion> ejecutarBajaAdhesion(Cliente cliente, PagoPendiente servicioAdherido)
			throws ServiceException;

	/**
	 * Ejecutar eliminar pago puntual.
	 *
	 * @param pagoPendiente
	 *            the pago pendiente
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<ResultadoTransaccion> ejecutarEliminarPagoPuntual(PagoPendiente pagoPendiente, Cliente cliente);

	/**
	 * Ejecutar baja adhesion debito automatico. 9816. *
	 *
	 * @param cliente
	 *            the cliente
	 * @param adhesionDebitoAutomatico
	 *            the adhesion debito automatico
	 * @return the respuesta
	 */
	Respuesta<ResultadoTransaccion> ejecutarBajaAdhesionDebitoAutomatico(Cliente cliente,
			AdhesionDebitoAutomatico adhesionDebitoAutomatico);

	/**
	 * Modificar adhesion pago auto de pago mis cuentas.
	 *
	 * @param cliente
	 *            the cliente
	 * @param pagoPendienteSesion
	 *            the pago pendiente sesion
	 * @param cuenta
	 *            the cuenta
	 * @param nuevoTope
	 *            the nuevo tope
	 * @param modAmbos
	 *            the mod ambos
	 * @param cuentaDelServicio
	 *            the cuenta del servicio
	 * @return the respuesta
	 */
	Respuesta<ResultadoTransaccion> modificarAdhesionPagoAutoDePagoMisCuentas(Cliente cliente,
			PagoPendiente pagoPendienteSesion, Cuenta cuenta, BigDecimal nuevoTope, TipoDeModificacion modAmbos,
			String cuentaDelServicio);
}
