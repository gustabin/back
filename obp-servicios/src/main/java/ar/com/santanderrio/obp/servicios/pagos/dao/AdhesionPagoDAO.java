/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao;

import java.math.BigDecimal;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;
import ar.com.santanderrio.obp.servicios.pagos.entities.TipoDeModificacion;

/**
 * The Interface AdhesionPagoDAO.
 */
public interface AdhesionPagoDAO {

	/**
	 * Modifica los datos de adhesión para un débito automático.
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
	 * @return resultado de la transacción
	 */
	ResultadoTransaccion modificarAdhesionPago(Cliente cliente, PagoPendiente servicioAdherido, Cuenta cuentaDebito,
			BigDecimal limiteAdhesion, TipoDeModificacion tipoDeModificacion);

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
	 * @return the resultado transaccion
	 * @throws DAOException
	 *             the DAO exception
	 */
	ResultadoTransaccion modificarAdhesionPagoAutomaticoDePagoMisCuentas(Cliente cliente,
			PagoPendiente servicioAdherido, Cuenta cuentaDebito, BigDecimal limiteAdhesion,
			TipoDeModificacion tipoDeModificacion, String cuentaDelServicio) throws DAOException;

}
