/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.manager;

import java.util.List;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AliasFavoritoProducto;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaCuentaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CopiarMensajeView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasIntermedioView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.DetalleCBUView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.SelectorCuentasView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.StackSelectorCuentasView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

// TODO: Auto-generated Javadoc
/**
 * The Interface CuentaManager.
 */
public interface CuentaManager {

	/**
	 * Gets the cuentas.
	 *
	 * @param userAgent
	 *            the user agent
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @return the cuentas
	 */
	Respuesta<SelectorCuentasView> getCuentas(String userAgent, String numeroCuenta);

	/**
	 * Gets the cuentas.
	 *
	 * @param userAgent
	 *            the user agent
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @return the cuentas
	 */
	Respuesta<StackSelectorCuentasView> getListaCuentasCliente();

	
	/**
	 * Guardar estadistica mis productos.
	 *
	 * @param codigoTransaccion
	 *            the codigo transaccion
	 */
	void guardarEstadisticaMisProductos(String codigoTransaccion);

	/**
	 * Obtener reporte cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
	Respuesta<ReporteView> obtenerReporteCuenta(ConsultaCuentaView cuenta);

	/**
	 * Actualizar alias.
	 *
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @param alias
	 *            the alias
	 * @return the respuesta
	 */
	Respuesta<Void> actualizarAlias(String numeroCuenta, String alias);

	/**
	 * Marcar favorita.
	 *
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @return the respuesta
	 */
	Respuesta<Void> marcarFavorita(String numeroCuenta);

	/**
	 * Obtener cuenta by id.
	 *
	 * @param nroCuenta
	 *            the nro cuenta
	 * @return the abstract cuenta
	 */
	AbstractCuenta obtenerCuentaById(String nroCuenta);

	/**
	 * Obtener cuenta por id.
	 *
	 * @author emilio.watemberg
	 * @param nroCuenta
	 *            the nro cuenta
	 * @return the respuesta
	 */
	Respuesta<AbstractCuenta> obtenerCuentaPorId(String nroCuenta);

	/**
	 * Verificar cuenta.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @param versionVerificarCuenta
	 *            the version verificar cuenta
	 * @return the respuesta
	 */
	Respuesta<Cliente> verificarCuenta(TransferenciaView transferenciaView, int versionVerificarCuenta);

	/**
	 * Gets the cuenta.
	 *
	 * @param nroCuenta
	 *            the nro cuenta
	 * @return the cuenta
	 */
	Respuesta<CuentasView> getCuenta(String nroCuenta);

	/**
	 * Checks for cuentas monetarias activas en pesos.
	 *
	 * @return the boolean
	 */
	Boolean hasCuentasMonetariasActivasEnPesos();

	/**
	 * Gets the cuentas saldo.
	 *
	 * @return the cuentas saldo
	 */
	Respuesta<CuentasView> getCuentasSaldo();

	/**
	 * Convertir cuentas view.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	Respuesta<CuentasView> convertirCuentasView(Respuesta<List<ResumenDetalleCuenta>> respuesta)
			throws BusinessException;

	/**
	 * Gets the cuentas activas.
	 *
	 * @return the cuentas activas
	 */
	Respuesta<CuentasView> getCuentasActivas();

	/**
	 * Agregar saldo errorneo en cuentas activas.
	 *
	 * @param respuestaCuentas
	 *            the respuesta cuentas
	 * @param cuentasTemp
	 *            the cuentas temp
	 * @return the respuesta
	 */
	Respuesta<CuentasView> agregarSaldoErrorneoEnCuentasActivas(Respuesta<CuentasView> respuestaCuentas,
			List<CuentasAdhesionDebitoView> cuentasTemp);

	/**
	 * Obtener detalle CBU.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param userAgent
	 *            the user agent
	 * @return the respuesta
	 */
	Respuesta<DetalleCBUView> obtenerDetalleCBU(ConsultaCuentaView cuenta, String userAgent);

	/**
	 * Compartir CBU.
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> compartirCBU();

	/**
	 * Obtener mensaje copiar CBU.
	 *
	 * @return the respuesta
	 */
	Respuesta<CopiarMensajeView> obtenerMensajeCopiarCBU();

	/**
	 * Copiar CBU.
	 *
	 * @return the respuesta
	 */
	Respuesta<CopiarMensajeView> copiarCBU();

	/**
	 * Gets the cuentas saldo por tipo moneda.
	 *
	 * @param tipoMoneda
	 *            the tipo moneda
	 * @return the cuentas saldo por moneda
	 */
	Respuesta<CuentasView> getCuentasSaldoPorMoneda(String tipoMoneda);

	/**
	 * Retorna la lista de cuentas cepo del cliente.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the cuentas cepo
	 */
	List<Cuenta> getCuentasCepo(Cliente cliente);

	/**
	 * Obtener inicio cuentas.
	 *
	 * @param userAgent
	 *            the user agent
	 * @return the respuesta
	 */
	Respuesta<CuentasIntermedioView> obtenerInicioCuentas(String userAgent);

	/**
	 * Grabar estadistica obtener cuentas home.
	 */
	void grabarEstadisticaObtenerCuentasHome();

	List<Cuenta> getCuentasCepoBP(Cliente cliente);
	
	Respuesta<List<AliasFavoritoProducto>> obtenerAliasYFavoritos();

}
