/*
 * editado: 19/08/2016 10:41:04 - B039542 - ignacio_valek@itrsa.com.ar
 *
 */
package ar.com.santanderrio.obp.servicios.pagos.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.debitoautomatico.bo.DebitoAutomaticoBO;
import ar.com.santanderrio.obp.servicios.pagoautomatico.bo.PagoAutomaticoBO;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.AdhesionDebitoAutomatico;
import ar.com.santanderrio.obp.servicios.pagos.bo.PagosPendientesBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;
import ar.com.santanderrio.obp.servicios.pagos.entities.TipoDeModificacion;
import ar.com.santanderrio.obp.servicios.pagos.service.PagosService;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosTarjeta;

/**
 * The Class PagosServiceImpl.
 */
@Component
public class PagosServiceImpl implements PagosService {

	/** The Constant PAGOS_SERVICE_GET_TARJETAS_PENDIENTES. */
	private static final String PAGOS_SERVICE_GET_TARJETAS_PENDIENTES = "PagosService#getTarjetasPendientes()";

	/** The pagos pendientes BO. */
	@Autowired
	private PagosPendientesBO pagosPendientesBO;

	/** The pago automatico BO. */
	@Autowired
	PagoAutomaticoBO pagoAutomaticoBO;

	/** The debito automatico BO. */
	@Autowired
	DebitoAutomaticoBO debitoAutomaticoBO;

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PagosService.class);

	/** The Constant DESCRIPCION_LOG. */
	private static final String DESCRIPCION_LOG = "Descripci�n: {}.";

	/** The Constant ERROR_LOG. */
	private static final String ERROR_LOG = "Ha ocurrido un error en servicio {}. " + DESCRIPCION_LOG;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.service.PagosService#
	 * obtenerPagoPendiente(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente)
	 */
	@Override
	public Respuesta<List<PagoPendiente>> obtenerPagoPendiente(Cliente cliente) {
		return pagosPendientesBO.obtenerPagosPendientes(cliente);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.service.PagosService#
	 * getPrestamosPendientes(ar.com.santanderrio.obp.servicios.clientes.
	 * entities.Cliente)
	 */
	@Override
	public Respuesta<List<PagoPendiente>> getPrestamosPendientes(Cliente cliente) {
		return pagosPendientesBO.getPagosPendientesPrestamo(cliente);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.service.PagosService#
	 * getTarjetasPendientes(ar.com.santanderrio.obp.servicios.clientes.entities
	 * .Cliente)
	 */
	@Override
	public Respuesta<List<PagoPendiente>> getTarjetasPendientes(Cliente cliente) {
		return pagosPendientesBO.getPagosPendientesTarjetas(cliente);
	}

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
	 * @see ar.com.santanderrio.obp.servicios.pagos.service.PagosService#
	 *      modificarAdhesion(ar.com.santanderrio.obp.servicios.clientes.entities.
	 *      Cliente,
	 *      ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente,
	 *      ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta,
	 *      java.math.BigDecimal,
	 *      ar.com.santanderrio.obp.servicios.pagos.entities.TipoDeModificacion)
	 */
	@Override
	public Respuesta<ResultadoTransaccion> modificarAdhesion(Cliente cliente, PagoPendiente servicioAdherido,
			Cuenta cuentaDebito, BigDecimal limiteAdhesion, TipoDeModificacion tipoDeModificacion) {
		return pagosPendientesBO.modificarAdhesion(cliente, servicioAdherido, cuentaDebito, limiteAdhesion,
				tipoDeModificacion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.service.PagosService#
	 * getDatosTarjetasConAdicionales(ar.com.santanderrio.obp.servicios.clientes
	 * .entities.Cliente)
	 */
	@Override
	public List<DatosTarjeta> getDatosTarjetasConAdicionales(Cliente cliente) throws ServiceException {
		try {
			return pagosPendientesBO.getDatosTarjetasConAdicionales(cliente);
		} catch (BusinessException e) {
			LOGGER.error(ERROR_LOG, PAGOS_SERVICE_GET_TARJETAS_PENDIENTES, e.getMessage(), e);
			throw new ServiceException(e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.service.PagosService#
	 * tienePagosProgramados(ar.com.santanderrio.obp.servicios.clientes.entities
	 * .Cliente,
	 * ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosTarjeta,
	 * java.lang.String)
	 */
	@Override
	public Boolean tienePagosProgramados(Cliente cliente, DatosTarjeta datosTarjeta, String fechaLimite)
			throws ServiceException {
		try {
			return pagosPendientesBO.tienePagosProgramados(cliente, datosTarjeta, fechaLimite);
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.service.PagosService#
	 * ejecutarBajaAdhesion(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente, ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente)
	 */
	@Override
	public Respuesta<ResultadoTransaccion> ejecutarBajaAdhesion(Cliente cliente, PagoPendiente pagoPendiente)
			throws ServiceException {

		return pagoAutomaticoBO.ejecutarBajaAdhesion(pagoPendiente, cliente);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.service.PagosService#
	 * ejecutarEliminarPagoPuntual(ar.com.santanderrio.obp.servicios.pagos.
	 * entities.PagoPendiente,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<ResultadoTransaccion> ejecutarEliminarPagoPuntual(PagoPendiente pagoPendiente, Cliente cliente) {
		return pagoAutomaticoBO.eliminarPagoPuntual(pagoPendiente, cliente);

	}

	/**
	 * Baja de adhesion a debito automatico. 9816.
	 *
	 * @param cliente
	 *            the cliente
	 * @param adhesionDebitoAutomatico
	 *            the adhesion debito automatico
	 * @return the respuesta
	 * @see ar.com.santanderrio.obp.servicios.pagos.service.PagosService#ejecutarBajaAdhesionDebitoAutomatico(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente,
	 *      ar.com.santanderrio.obp.servicios.pagoautomatico.entities.AdhesionDebitoAutomatico)
	 */
	@Override
	public Respuesta<ResultadoTransaccion> ejecutarBajaAdhesionDebitoAutomatico(Cliente cliente,
			AdhesionDebitoAutomatico adhesionDebitoAutomatico) {
		return debitoAutomaticoBO.bajaAdhesion(adhesionDebitoAutomatico, cliente);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.service.PagosService#
	 * modificarAdhesionPagoAutoDePagoMisCuentas(ar.com.santanderrio.obp.
	 * servicios.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente,
	 * ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta,
	 * java.math.BigDecimal,
	 * ar.com.santanderrio.obp.servicios.pagos.entities.TipoDeModificacion,
	 * java.lang.String)
	 */
	@Override
	public Respuesta<ResultadoTransaccion> modificarAdhesionPagoAutoDePagoMisCuentas(Cliente cliente,
			PagoPendiente pagoPendienteSesion, Cuenta cuenta, BigDecimal nuevoTope, TipoDeModificacion modAmbos,
			String cuentaDelServicio) {
		return pagosPendientesBO.modificarAdhesionPagoAutomaticoDePagoMisCuentas(cliente, pagoPendienteSesion, cuenta,
				nuevoTope, modAmbos, cuentaDelServicio);
	}

}