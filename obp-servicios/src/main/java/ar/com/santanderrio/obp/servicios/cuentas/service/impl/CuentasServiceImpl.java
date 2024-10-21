/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.ResumenMensualCuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.UltimosMovimientosBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaUltimosMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleMovimientoEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleUltimosMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.service.CuentasService;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenMensualCuenta;
import ar.com.santanderrio.obp.servicios.resumen.entities.ResumenMensualCuenta;

/**
 * The Class CuentasServiceImpl.
 */
@Component
public class CuentasServiceImpl implements CuentasService {

	/** The Constant ERROR_AL_OBTENER_CUENTA. */
	private static final String ERROR_AL_OBTENER_CUENTA = "Error al obtener cuenta.";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CuentasServiceImpl.class);

	/** The cuenta bo. */
	@Autowired
	private CuentaBO cuentaBO;

	/** The ultimos movimientos bo. */
	@Autowired
	private UltimosMovimientosBO ultimosMovimientosBO;

	/** The resumen mensual cuenta BO. */
	@Autowired
	private ResumenMensualCuentaBO resumenMensualCuentaBO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.cuentas.service.CuentasService#obtenerCuentas(ar.
	 * com.santanderrio.obp.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<List<ResumenDetalleCuenta>> obtenerCuentas(Cliente cliente) throws ServiceException {
		try {
			return cuentaBO.obtenerCuentas(cliente);
		} catch (BusinessException e) {
			LOGGER.error("Error al obtener las cuentas del cliente {}.", cliente, e);
			throw new ServiceException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.cuentas.service.CuentasService#getCuentaById(ar.
	 * com.santanderrio.obp.cuentas.entities.IdentificacionCuenta,
	 * ar.com.santanderrio.obp.clientes.entities.Cliente)
	 */
	@Override
	public AbstractCuenta getCuentaById(IdentificacionCuenta identificacionCuenta, Cliente cliente) {
		return cuentaBO.getCuentaById(identificacionCuenta, cliente);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.cuentas.service.CuentasService#
	 * obtenerUltimosMovimientos(ar.com.santanderrio.obp.cuentas.entities.
	 * ConsultaUltimosMovimientos)
	 */
	@Override
	public Respuesta<DetalleUltimosMovimientos> obtenerUltimosMovimientos(
			ConsultaUltimosMovimientos consultaUltimosMovimientos) throws ServiceException {

		return ultimosMovimientosBO.obtenerUltimosMovimientos(consultaUltimosMovimientos, null);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.cuentas.service.CuentasService#
	 * obtenerCuentaPrincipal(ar.com.santanderrio.obp.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<ResumenDetalleCuenta> obtenerCuentaPrincipal(Cliente cliente) throws ServiceException {
		try {
			return cuentaBO.obtenerCuentaPrincipal(cliente);
		} catch (BusinessException e) {
			LOGGER.error("Error al obtener cuenta principal del cliente {}.", e);
			throw new ServiceException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.service.CuentasService#
	 * obtenerCuentaFavorita(ar.com.santanderrio.obp.servicios.clientes.entities
	 * .Cliente)
	 */
	@Override
	public Respuesta<ResumenDetalleCuenta> obtenerCuentaFavorita(Cliente cliente) throws ServiceException {
		try {
			return cuentaBO.obtenerCuentaFavorita(cliente);
		} catch (BusinessException e) {
			LOGGER.error("Error al obtener la cuenta favorita", e);
			throw new ServiceException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.cuentas.service.CuentasService#
	 * obtenerReporteCBUCuenta(ar.com.santanderrio.obp.cuentas.entities.
	 * IdentificacionCuenta, ar.com.santanderrio.obp.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<Reporte> obtenerReporteCBUCuenta(IdentificacionCuenta identificacionCuenta, Cliente cliente)
			throws ServiceException {
		try {
			return cuentaBO.obtenerReporteCBUCuenta(identificacionCuenta, cliente, "");
		} catch (BusinessException e) {
			LOGGER.error("Error al obtener reporte de cbu para el cliente {}.", cliente, e);
			throw new ServiceException(e);
		}
	}

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.cuentas.service.CuentasService#
     * exportarUltimosMovimientos(java.util.List,
     * ar.com.santanderrio.obp.cuentas.entities.ConsultaUltimosMovimientos)
     */
    
    @Override
    public Respuesta<Reporte> exportarUltimosMovimientos(List<DetalleMovimientoEntity> movimientos,
            ConsultaUltimosMovimientos filtro, String dispositivo) throws ServiceException {
        
            return ultimosMovimientosBO.exportarUltimosMovimientos(filtro, dispositivo);
        
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.cuentas.service.CuentasService#completarFiltro(ar
	 * .com.santanderrio.obp.cuentas.entities.ConsultaUltimosMovimientos)
	 */
	@Override
	public void completarFiltro(ConsultaUltimosMovimientos filtro) throws ServiceException {
		try {
			ultimosMovimientosBO.completarFiltro(filtro);
		} catch (BusinessException e) {
			throw new ServiceException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.service.CuentasService#
	 * actualizarAlias(ar.com.santanderrio.obp.servicios.cuentas.entities.
	 * IdentificacionCuenta, java.lang.String,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<Void> actualizarAlias(IdentificacionCuenta identificacionCuenta, String alias, Cliente cliente)
			throws ServiceException {
		try {
			return cuentaBO.actualizarAlias(identificacionCuenta, alias, cliente);
		} catch (BusinessException e) {
			LOGGER.error("Error al actualizar Alias", e);
			throw new ServiceException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.service.CuentasService#
	 * marcarFavorita(ar.com.santanderrio.obp.servicios.cuentas.entities.
	 * IdentificacionCuenta,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<Void> marcarFavorita(IdentificacionCuenta identificacionCuenta, Cliente cliente)
			throws ServiceException {
		try {
			return cuentaBO.marcarFavorita(identificacionCuenta, cliente);
		} catch (BusinessException e) {
			LOGGER.error("Error al marcarFavorita", e);
			throw new ServiceException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.service.CuentasService#
	 * obtenerCuentaSeleccionada(ar.com.santanderrio.obp.base.respuesta.entities
	 * .Respuesta)
	 */
	@Override
	public Respuesta<Integer> obtenerCuentaSeleccionada(Respuesta<List<ResumenDetalleCuenta>> cuentas)
			throws ServiceException {
		try {
			return cuentaBO.obtenerCuentaSeleccionada(cuentas);
		} catch (BusinessException e) {
			LOGGER.error("Error al marcarFavorita", e);
			throw new ServiceException(e);
		}
	}

	/**
	 * Gets the cuenta bo.
	 *
	 * @return the cuenta bo
	 */
	public CuentaBO getCuentaBO() {
		return cuentaBO;
	}

	/**
	 * Setter para cuenta bo.
	 *
	 * @param cuentaBO
	 *            el nuevo cuenta bo
	 */
	public void setCuentaBO(CuentaBO cuentaBO) {
		this.cuentaBO = cuentaBO;
	}

	/**
	 * Gets the ultimos movimientos bo.
	 *
	 * @return the ultimos movimientos bo
	 */
	public UltimosMovimientosBO getUltimosMovimientosBO() {
		return ultimosMovimientosBO;
	}

	/**
	 * Setter para ultimos movimientos bo.
	 *
	 * @param ultimosMovimientosBO
	 *            el nuevo ultimos movimientos bo
	 */
	public void setUltimosMovimientosBO(UltimosMovimientosBO ultimosMovimientosBO) {
		this.ultimosMovimientosBO = ultimosMovimientosBO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.service.CuentasService#
	 * obtenerListaResumen(ar.com.santanderrio.obp.servicios.cuentas.entities.
	 * AbstractCuenta)
	 */
	@Override
	public Respuesta<List<ResumenMensualCuenta>> obtenerListaResumen(AbstractCuenta cuenta) throws ServiceException {
		try {
			return resumenMensualCuentaBO.obtenerListaResumen(cuenta);
		} catch (BusinessException e) {
			LOGGER.error("Error al obtener la lista de resumen", e);
			throw new ServiceException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.service.CuentasService#
	 * obtenerResumenDescargaMultiple(java.util.List,
	 * ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta)
	 */
	@Override
	public Respuesta<ReporteResumenMensualCuenta> obtenerResumenDescargaMultiple(
			List<ResumenMensualCuenta> resumenMensualCuenta, AbstractCuenta cuenta, int cantidadADescargar)
			throws ServiceException {
		try {
			return resumenMensualCuentaBO.obtenerResumenDescargaMultiple(resumenMensualCuenta, cuenta,
					cantidadADescargar);
		} catch (BusinessException e) {
			LOGGER.error("Error al obtener los resumenes", e);
			throw new ServiceException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.service.CuentasService#
	 * obtenerResumenesPDF(java.util.List,
	 * ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta)
	 */
	@Override
	public Respuesta<ReporteResumenMensualCuenta> obtenerResumenesPDF(List<ResumenMensualCuenta> resumenMensualCuenta,
			AbstractCuenta cuenta) {
		return resumenMensualCuentaBO.obtenerResumenesPDF(resumenMensualCuenta, cuenta);
	}

	/**
	 * Metodo verificarCuenta. Ref: 14435
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
	@Override
	public Respuesta<Cliente> verificarCuenta(Cliente cliente, String tipoCuenta, String sucursalCuenta,
			String nroCuenta) throws ServiceException {
		try {
			return cuentaBO.verificarCuenta(cliente, tipoCuenta, sucursalCuenta, nroCuenta);
		} catch (BusinessException e) {
			LOGGER.error(ERROR_AL_OBTENER_CUENTA, e);
			throw new ServiceException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.service.CuentasService#
	 * obtenerCuenta(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente, java.lang.String)
	 */
	@Override
	public Respuesta<ResumenDetalleCuenta> obtenerCuenta(Cliente cliente, String nroCuenta) throws ServiceException {
		try {
			return cuentaBO.obtenerCuenta(cliente, nroCuenta);
		} catch (BusinessException e) {
			LOGGER.error(ERROR_AL_OBTENER_CUENTA, e);
			throw new ServiceException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.service.CuentasService#
	 * obtenerCuenta(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente, java.lang.String)
	 */
	@Override
	public Respuesta<ResumenDetalleCuenta> obtenerCuentaPrivada(Cliente cliente, String nroCuenta)
			throws ServiceException {
		try {
			return cuentaBO.obtenerCuentaPrivada(cliente, nroCuenta);
		} catch (BusinessException e) {
			LOGGER.error(ERROR_AL_OBTENER_CUENTA, e);
			throw new ServiceException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.service.CuentasService#
	 * hasCuentasMonetariasActivasEnPesos(ar.com.santanderrio.obp.servicios.
	 * clientes.entities.Cliente)
	 */
	@Override
	public Boolean hasCuentasMonetariasActivasEnPesos(Cliente cliente) {
		return cuentaBO.hasCuentasMonetariasActivasEnPesos(cliente);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.service.CuentasService#
	 * getCuentasSaldo(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente)
	 */
	@Override
	public Respuesta<List<ResumenDetalleCuenta>> getCuentasSaldo(Cliente cliente) throws ServiceException {
		try {
			return cuentaBO.getCuentasSaldo(cliente);
		} catch (BusinessException e) {
			LOGGER.error(ERROR_AL_OBTENER_CUENTA, e);
			throw new ServiceException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.service.CuentasService#
	 * getCuentasSaldo(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente)
	 */
	@Override
	public Respuesta<List<ResumenDetalleCuenta>> getCuentasSaldoPorMoneda(Cliente cliente, String tipoMoneda)
			throws ServiceException {
		try {
			return cuentaBO.getCuentasSaldoPorMoneda(cliente, tipoMoneda);
		} catch (BusinessException e) {
			LOGGER.error(ERROR_AL_OBTENER_CUENTA, e);
			throw new ServiceException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.service.CuentasService#
	 * obtenerMensajeInformativo(java.lang.String, java.lang.String)
	 */
	@Override
	public String obtenerMensajeInformativo(String nroSucursal, String nroCuenta) {
		return ultimosMovimientosBO.obtenerMensajeInformativo(nroSucursal, nroCuenta);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.service.CuentasService#
	 * obtenerMensajeNoHayMovimientos(ar.com.santanderrio.obp.servicios.cuentas.
	 * entities.ConsultaUltimosMovimientos)
	 */
	@Override
	public String obtenerMensajeNoHayMovimientos(ConsultaUltimosMovimientos consultaUltimosMovimientos) {
		return ultimosMovimientosBO.obtenerMensajeNoHayMovimientos(consultaUltimosMovimientos);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.service.CuentasService#
	 * obtenerMensajeInformativoDescargaResumenes()
	 */
	@Override
	public String obtenerMensajeInformativoDescargaResumenes() {
		return resumenMensualCuentaBO.obtenerMensajeInformativoDescargaResumenes();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.service.CuentasService#
	 * obtenerMensajeCopiarCBU()
	 */
	@Override
	public Respuesta<String> obtenerMensajeCopiarCBU() {
		return cuentaBO.obtenerMensajeCopiarCBU();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.service.CuentasService#
	 * obtenerMarcaResumenOnline(ar.com.santanderrio.obp.servicios.cuentas.
	 * entities.AbstractCuenta)
	 */
	@Override
	public Respuesta<Boolean> obtenerMarcaResumenOnline(AbstractCuenta cuenta) {
		return resumenMensualCuentaBO.obtenerMarcaResumenOnline(cuenta);
	}

}
