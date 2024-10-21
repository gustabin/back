/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.service;

import java.util.List;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.Service;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaUltimosMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleMovimientoEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleUltimosMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenMensualCuenta;
import ar.com.santanderrio.obp.servicios.resumen.entities.ResumenMensualCuenta;

/**
 * The Interface CuentasService.
 */
public interface CuentasService extends Service {

	/**
	 * Obtener cuentas.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 * @throws ServiceException
	 *             the service exception
	 */
	Respuesta<List<ResumenDetalleCuenta>> obtenerCuentas(Cliente cliente) throws ServiceException;

	/**
	 * Obtener cuenta.
	 *
	 * @param cliente
	 *            the cliente
	 * @param nroCuenta
	 *            the nro cuenta
	 * @return the respuesta
	 * @throws ServiceException
	 *             the service exception
	 */
	Respuesta<ResumenDetalleCuenta> obtenerCuenta(Cliente cliente, String nroCuenta) throws ServiceException;

	/**
	 * Obtener cuenta privada.
	 *
	 * @param cliente
	 *            the cliente
	 * @param nroCuenta
	 *            the nro cuenta
	 * @return the respuesta
	 * @throws ServiceException
	 *             the service exception
	 */
	Respuesta<ResumenDetalleCuenta> obtenerCuentaPrivada(Cliente cliente, String nroCuenta) throws ServiceException;

	/**
	 * Gets the cuenta by id.
	 *
	 * @param identificacionCuenta
	 *            the identificacion cuenta
	 * @param cliente
	 *            the cliente
	 * @return the cuenta by id
	 */
	AbstractCuenta getCuentaById(IdentificacionCuenta identificacionCuenta, Cliente cliente);

	/**
	 * Obtener ultimos movimientos.
	 *
	 * @param consultaUltimosMovimientos
	 *            the consulta ultimos movimientos
	 * @return the respuesta
	 * @throws ServiceException
	 *             the service exception
	 */
	Respuesta<DetalleUltimosMovimientos> obtenerUltimosMovimientos(
			ConsultaUltimosMovimientos consultaUltimosMovimientos) throws ServiceException;

	/**
	 * Obtener cuenta principal.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 * @throws ServiceException
	 *             the service exception
	 */
	Respuesta<ResumenDetalleCuenta> obtenerCuentaPrincipal(Cliente cliente) throws ServiceException;

	/**
	 * Obtener reporte cbu cuenta.
	 *
	 * @param identificacionCuenta
	 *            the identificacion cuenta
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 * @throws ServiceException
	 *             the service exception
	 */
	Respuesta<Reporte> obtenerReporteCBUCuenta(IdentificacionCuenta identificacionCuenta, Cliente cliente)
			throws ServiceException;

    /**
     * Exportar ultimos movimientos.
     *
     * @param movimientos
     *            the movimientos
     * @param filtro
     *            the filtro
     * @param dispositivo
     *            the dispositivo
     * @return the respuesta
     * @throws ServiceException
     *             the service exception
     */
    @Deprecated
    Respuesta<Reporte> exportarUltimosMovimientos(List<DetalleMovimientoEntity> movimientos,
            ConsultaUltimosMovimientos filtro, String dispositivo) throws ServiceException;

	/**
	 * Completar filtro.
	 *
	 * @param filtro
	 *            the filtro
	 * @throws ServiceException
	 *             the service exception
	 */
	void completarFiltro(ConsultaUltimosMovimientos filtro) throws ServiceException;

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
	 * @throws ServiceException
	 *             the service exception
	 */
	Respuesta<Void> actualizarAlias(IdentificacionCuenta identificacionCuenta, String alias, Cliente cliente)
			throws ServiceException;

	/**
	 * Marcar favorita.
	 *
	 * @param identificacionCuenta
	 *            the identificacion cuenta
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 * @throws ServiceException
	 *             the service exception
	 */
	Respuesta<Void> marcarFavorita(IdentificacionCuenta identificacionCuenta, Cliente cliente) throws ServiceException;

	/**
	 * Obtener cuenta favorita.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 * @throws ServiceException
	 *             the service exception
	 */
	Respuesta<ResumenDetalleCuenta> obtenerCuentaFavorita(Cliente cliente) throws ServiceException;

	/**
	 * Obtener lista resumen.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 * @throws ServiceException
	 *             the service exception
	 */
	Respuesta<List<ResumenMensualCuenta>> obtenerListaResumen(AbstractCuenta cuenta) throws ServiceException;

	/**
	 * Obtener resumen descarga multiple.
	 *
	 * @param fechaResumenes
	 *            the fecha resumenes
	 * @param nroCuenta
	 *            the nro cuenta
	 * @param cantidadADescargar
	 *            the cantidad A descargar
	 * @return the respuesta
	 * @throws ServiceException
	 *             the service exception
	 */
	Respuesta<ReporteResumenMensualCuenta> obtenerResumenDescargaMultiple(List<ResumenMensualCuenta> fechaResumenes,
			AbstractCuenta nroCuenta, int cantidadADescargar) throws ServiceException;

	/**
	 * Obtener resumenes PDF.
	 *
	 * @param fechaResumenes
	 *            the fecha resumenes
	 * @param nroCuenta
	 *            the nro cuenta
	 * @return the respuesta
	 */
	Respuesta<ReporteResumenMensualCuenta> obtenerResumenesPDF(List<ResumenMensualCuenta> fechaResumenes,
			AbstractCuenta nroCuenta);

	/**
	 * Obtener cuenta seleccionada.
	 *
	 * @param cuentas
	 *            the cuentas
	 * @return the respuesta
	 * @throws ServiceException
	 *             the service exception
	 */
	Respuesta<Integer> obtenerCuentaSeleccionada(Respuesta<List<ResumenDetalleCuenta>> cuentas) throws ServiceException;

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
	 * @throws ServiceException
	 *             the service exception
	 */
	Respuesta<Cliente> verificarCuenta(Cliente cliente, String tipoCuenta, String sucursalCuenta, String nroCuenta)
			throws ServiceException;

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
	 * @throws ServiceException
	 *             the service exception
	 */
	Respuesta<List<ResumenDetalleCuenta>> getCuentasSaldo(Cliente cliente) throws ServiceException;

	/**
	 * Gets the cuentas saldo por moneda.
	 *
	 * @param cliente
	 *            the cliente
	 * @param tipoMoneda
	 *            the tipo moneda
	 * @return the cuentas saldo por moneda
	 * @throws ServiceException
	 *             the service exception
	 */
	Respuesta<List<ResumenDetalleCuenta>> getCuentasSaldoPorMoneda(Cliente cliente, String tipoMoneda)
			throws ServiceException;

	/**
	 * Obtener mensaje informativo.
	 *
	 * @param nroSucursal
	 *            the nro sucursal
	 * @param nroCuenta
	 *            the nro cuenta
	 * @return the string
	 */
	String obtenerMensajeInformativo(String nroSucursal, String nroCuenta);

	/**
	 * Obtener mensaje informativo descarga resumenes.
	 *
	 * @return the string
	 */
	String obtenerMensajeInformativoDescargaResumenes();

	/**
	 * Obtener mensaje no hay movimientos.
	 *
	 * @param consultaUltimosMovimientos
	 *            the consulta ultimos movimientos
	 * @return the string
	 */
	String obtenerMensajeNoHayMovimientos(ConsultaUltimosMovimientos consultaUltimosMovimientos);

	/**
	 * Obtener mensaje de copiar CBU.
	 *
	 * @return the string
	 */
	Respuesta<String> obtenerMensajeCopiarCBU();

	/**
	 * Obtener marca resumen online.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
	Respuesta<Boolean> obtenerMarcaResumenOnline(AbstractCuenta cuenta);

}
