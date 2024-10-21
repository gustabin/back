package ar.com.santanderrio.obp.servicios.cuentas.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.citi.entities.CuentaCitiMigrada;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AliasFavoritoProducto;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaDetalleCuentaOutEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.SaldosConsolidadosCuentaDTO;
import ar.com.santanderrio.obp.servicios.delete.account.model.DeleteAccountRequest;
import ar.com.santanderrio.obp.servicios.delete.account.web.dto.DeleteAccountResponseDTO;

/**
 * The Interface CuentaBO.
 */
public interface CuentaBO {

    /**
     * Obtener cuenta principal.
     *
     * @param cliente
     *            the cliente
     * @return the respuesta
     * @throws BusinessException
     *             the business exception
     */
    Respuesta<ResumenDetalleCuenta> obtenerCuentaPrincipal(Cliente cliente) throws BusinessException;

    /**
     * Obtener cuentas.
     *
     * @param cliente
     *            the cliente
     * @return the respuesta
     * @throws BusinessException
     *             the business exception
     */
    Respuesta<List<ResumenDetalleCuenta>> obtenerCuentas(Cliente cliente) throws BusinessException;

    /**
     * Obtener reporte cbu cuenta.
     *
     * @param id
     *            the id
     * @param cliente
     *            the cliente
     * @param alias
     *            the alias
     * @return the respuesta
     * @throws BusinessException
     *             the business exception
     */
    Respuesta<Reporte> obtenerReporteCBUCuenta(IdentificacionCuenta id, Cliente cliente, String alias)
            throws BusinessException;

    /**
     * Gets the cuenta by id.
     *
     * @param id
     *            the id
     * @param cliente
     *            the cliente
     * @return the cuenta by id
     */
    AbstractCuenta getCuentaById(IdentificacionCuenta id, Cliente cliente);

    /**
     * Actualizar alias.
     *
     * @param identificacionCuenta
     *            the identificacion cuenta
     * @param alias
     *            the alias
     * @param cliente
     *            the cliente
     * @return the respuesta
     * @throws BusinessException
     *             the business exception
     */
    Respuesta<Void> actualizarAlias(IdentificacionCuenta identificacionCuenta, String alias, Cliente cliente)
            throws BusinessException;

    /**
     * Marcar favorita.
     *
     * @param identificacionCuenta
     *            the identificacion cuenta
     * @param cliente
     *            the cliente
     * @return the respuesta
     * @throws BusinessException
     *             the business exception
     */
    Respuesta<Void> marcarFavorita(IdentificacionCuenta identificacionCuenta, Cliente cliente) throws BusinessException;

    /**
     * Cargar alias Y favoritos.
     *
     * @param cliente
     *            the cliente
     * @param isTipoCuentaMonetaria
     *            the is tipo cuenta monetaria
     * @throws BusinessException
     *             the business exception
     */
    void cargarAliasYFavoritos(Cliente cliente, boolean isTipoCuentaMonetaria) throws BusinessException;

    /**
     * Obtener cuenta favorita.
     *
     * @param cliente
     *            the cliente
     * @return the respuesta
     * @throws BusinessException
     *             the business exception
     */
    Respuesta<ResumenDetalleCuenta> obtenerCuentaFavorita(Cliente cliente) throws BusinessException;

    /**
     * Obtener cuenta seleccionada.
     *
     * @param cuentas
     *            the cuentas
     * @return the respuesta
     * @throws BusinessException
     *             the business exception
     */
    Respuesta<Integer> obtenerCuentaSeleccionada(Respuesta<List<ResumenDetalleCuenta>> cuentas)
            throws BusinessException;

    /**
     * Buscar cuenta por id.
     *
     * @param cliente
     *            the cliente
     * @param id
     *            the id
     * @return AbstractCuenta
     */
    AbstractCuenta buscarCuentaPorId(Cliente cliente, IdentificacionCuenta id);

    /**
     * Buscar cuenta por id.
     *
     * @param cliente
     *            the cliente
     * @param id
     *            the id
     * @return the abstract cuenta
     */
    AbstractCuenta buscarCuentaPorId(Cliente cliente, String id);

    /**
     * Obtener cuenta por id.
     *
     * @param cliente
     *            the cliente
     * @param id
     *            the id
     * @return the cuenta
     */
    Cuenta obtenerCuentaPorId(Cliente cliente, String id);

    /**
     * Buscar cuenta by nro tarjeta recargable.
     *
     * @param cuentas
     *            the cuentas
     * @param nroTarjeta
     *            the nro tarjeta
     * @return the cuenta
     * @throws BusinessException
     *             the business exception
     */
    Cuenta buscarCuentaByNroTarjetaRecargable(List<Cuenta> cuentas, String nroTarjeta) throws BusinessException;

    /**
     * Verificar cuenta.
     *
     * @param cliente
     *            the cliente
     * @param tipoCuenta
     *            the tipo cuenta
     * @param sucursalCuenta
     *            the sucursal cuenta
     * @param nroCuenta
     *            the nro cuenta
     * @return the respuesta
     * @throws BusinessException
     *             the business exception
     */
    Respuesta<Cliente> verificarCuenta(Cliente cliente, String tipoCuenta, String sucursalCuenta, String nroCuenta)
            throws BusinessException;

    /**
     * Obtener cuentas banelco.
     *
     * @param cliente
     *            the cliente
     * @return the list
     * @throws BusinessException
     *             the business exception
     */
    List<Cuenta> obtenerCuentasBanelcoPesos(Cliente cliente) throws BusinessException;

    /**
     * Obtener cuenta.
     *
     * @param cliente
     *            the cliente
     * @param nroCuenta
     *            the nro cuenta
     * @return the respuesta
     * @throws BusinessException
     *             the business exception
     */
    Respuesta<ResumenDetalleCuenta> obtenerCuenta(Cliente cliente, String nroCuenta) throws BusinessException;

    /**
     * Obtener cuenta privada.
     *
     * @param cliente
     *            the cliente
     * @param nroCuenta
     *            the nro cuenta
     * @return the respuesta
     * @throws BusinessException
     *             the business exception
     */
    Respuesta<ResumenDetalleCuenta> obtenerCuentaPrivada(Cliente cliente, String nroCuenta) throws BusinessException;

    /**
     * Checks for cuentas monetarias activas en pesos.
     *
     * @param cliente
     *            the cliente
     * @return the boolean
     */
    Boolean hasCuentasMonetariasActivasEnPesos(Cliente cliente);

    /**
     * Gets the cuentas saldo.
     *
     * @param cliente
     *            the cliente
     * @return the cuentas saldo
     * @throws BusinessException
     *             the business exception
     */
    Respuesta<List<ResumenDetalleCuenta>> getCuentasSaldo(Cliente cliente) throws BusinessException;

    /**
     * Gets the cuentas saldo por cliente.
     *
     * @param cliente
     *            the cliente
     * @return the cuentas saldo por cliente
     * @throws BusinessException
     *             the business exception
     */
    Respuesta<List<Cuenta>> getCuentasSaldoPorCliente(Cliente cliente) throws BusinessException;

    /**
     * Gets the cuentas saldo por tipo Moneda.
     *
     * @param cliente
     *            the cliente
     * @param tipoMoneda
     *            the tipo moneda
     * @return the cuentas saldo por moneda
     * @throws BusinessException
     *             the business exception
     */
    Respuesta<List<ResumenDetalleCuenta>> getCuentasSaldoPorMoneda(Cliente cliente, String tipoMoneda)
            throws BusinessException;

    /**
     * Obtiene el mensaje de copiar CBU.
     *
     * @return mensaje de copiar CBU
     */
    Respuesta<String> obtenerMensajeCopiarCBU();

    /**
     * Obtener saldo consolidado cliente.
     *
     * @param cliente
     *            the cliente
     * @return the respuesta
     */
    Respuesta<SaldosConsolidadosCuentaDTO> obtenerSaldoConsolidadoCliente(Cliente cliente);

    /**
     * Obtener saldo consolidado cliente.
     *
     * @param listaResumenDetalleCuenta
     *            the lista resumen detalle cuenta
     * @return the respuesta
     */
    Respuesta<SaldosConsolidadosCuentaDTO> obtenerSaldoConsolidadoCliente(
            List<ResumenDetalleCuenta> listaResumenDetalleCuenta);

    /**
     * Obtiene los saldos consolidados actualizados del cliente.
     *
     * @param cliente
     *            the cliente
     * @return the respuesta
     */
    Respuesta<SaldosConsolidadosCuentaDTO> obtenerSaldosConsolidadosActualizados(Cliente cliente);

    /**
     * Checks for cuentas monetarias activas.
     *
     * @param cliente
     *            the cliente
     * @return the boolean
     */
    Boolean hasCuentasMonetariasActivas(Cliente cliente);

    /**
     * Checks for cuentas monetarias cerradas.
     *
     * @param cliente
     *            the cliente
     * @return the boolean
     */
    Boolean hasCuentasMonetariasCerradas(Cliente cliente);

    /**
     * Obtiene cuentas con saldos actualizado con servicio de cuentas dom, solo se
     * consultara por las cuentas recibidas por parametro.
     *
     * @param cliente
     *            the cliente
     * @param cuentas
     *            the cuentas
     * @return the cuentas saldo
     * @throws BusinessException
     *             the business exception
     */
    Respuesta<List<ResumenDetalleCuenta>> getCuentasSaldo(Cliente cliente, List<Cuenta> cuentas)
            throws BusinessException;

    /**
     * Obtener cuenta principal cliente.
     *
     * @param cliente
     *            the cliente
     * @return the cuenta
     */
    Cuenta obtenerCuentaPrincipalCliente(Cliente cliente);

    /**
     * Obtener saldo actualizado.
     *
     * @param cuenta
     *            the cuenta
     * @return the cuenta
     * @throws BusinessException
     *             the business exception
     */
    Cuenta obtenerSaldoActualizado(Cuenta cuenta) throws BusinessException;

    /**
     * Obtener info cuentas.
     *
     * @param cliente
     *            the cliente
     * @return the respuesta
     * @throws BusinessException
     *             the business exception
     */
    Respuesta<List<ResumenDetalleCuenta>> obtenerInfoCuentas(Cliente cliente) throws BusinessException;

    /**
     * Obtener cuentas.
     *
     * @param cuentasCliente
     *            the cuentas cliente
     * @param cuentasMonetarias
     *            the cuentas monetarias
     * @param cliente
     *            the cliente
     * @throws BusinessException
     *             the business exception
     */
    void obtenerCuentas(List<Cuenta> cuentasCliente, List<Cuenta> cuentasMonetarias, Cliente cliente)
            throws BusinessException;

    /**
	 * Obtener cuentas debito.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the list
	 * @throws BusinessException
	 *             the business exception
	 */
    List<Cuenta> obtenerCuentasDebito(Cliente cliente) throws BusinessException;

    /**
     * Verificar cuenta 2.
     *
     * @param cliente
     *            the cliente
     * @param tipoCuenta
     *            the tipo cuenta
     * @param sucursalCuenta
     *            the sucursal cuenta
     * @param nroCuenta
     *            the nro cuenta
     * @return the respuesta
     */
    Respuesta<Cliente> verificarCuenta2(Cliente cliente, String tipoCuenta, String sucursalCuenta, String nroCuenta);

    /**
	 * Obtener cuenta citi.
	 *
	 * @param cliente
	 *            the cliente
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
    Respuesta<CuentaCitiMigrada> obtenerCuentaCiti(AbstractCuenta cuenta, Cliente cliente);

    /**
	 * Tiene una sola cuenta.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the boolean
	 */
    Boolean tieneUnaSolaCuenta(Cliente cliente);

    ConsultaDetalleCuentaOutEntity obtenerDetalleCuenta(Cuenta cuenta) throws DAOException;

    List<AliasFavoritoProducto> obtenerAliasYFavoritos(String nup);

    Respuesta<DeleteAccountResponseDTO> deleteCuenta(DeleteAccountRequest request, Cuenta cuenta) throws BusinessException;

    Boolean tieneUnaSolaCuentaIncluyendoBancaPrivada(Cliente cliente);
}
