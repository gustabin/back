/**
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.bo.impl;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sucursales.bo.ConsultarSucursalesBO;
import ar.com.santanderrio.obp.servicios.comun.sucursales.entities.Sucursal;
import ar.com.santanderrio.obp.servicios.cuentas.bo.MovimientosPendientesDeConfirmacionBO;
import ar.com.santanderrio.obp.servicios.cuentas.dao.MovimientosPendientesDeConfirmacionDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.EstadoOperacionMovimimiento;
import ar.com.santanderrio.obp.servicios.cuentas.entities.MovimientoDeCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.MovimientosPendientesDeConfirmacion;

/**
 * The Class MovimientosPendientesDeConfirmacionBOImpl.
 *
 * @author B039542
 */
@Component
public class MovimientosPendientesDeConfirmacionBOImpl implements MovimientosPendientesDeConfirmacionBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MovimientosPendientesDeConfirmacionBOImpl.class);

	/** The movimientos pendientes de confirmacion dao. */
	@Autowired
	private MovimientosPendientesDeConfirmacionDAO movimientosPendientesDeConfirmacionDAO;

	/** The consultar sucursales bo. */
	@Autowired
	private ConsultarSucursalesBO consultarSucursalesBO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.cuentas.bo.MovimientosPendientesDeConfirmacionBO#
	 * obtenerMovimientosPendientesDeConfirmacionPorCuenta(ar.com.santanderrio.
	 * obp.cuentas.entities.AbstractCuenta)
	 */
	@Override
	public Respuesta<MovimientosPendientesDeConfirmacion> obtenerMovimientosPendientesDeConfirmacionPorCuenta(
			AbstractCuenta cuenta) {
		Respuesta<MovimientosPendientesDeConfirmacion> result = new Respuesta<MovimientosPendientesDeConfirmacion>();
		try {
			List<MovimientoDeCuenta> movimientosDeCuenta = movimientosPendientesDeConfirmacionDAO
					.obtenerMovimientosPendientesDeConfirmacionPorCuenta(cuenta);
			completarDatosDeSucursales(movimientosDeCuenta);
			MovimientosPendientesDeConfirmacion movimientosPendientesDeConfirmacion = generarMovimientosPendientesDeConfirmacion(
					movimientosDeCuenta);
			result.setRespuesta(movimientosPendientesDeConfirmacion);
			result.setEstadoRespuesta(EstadoRespuesta.OK);
			result.setRespuestaVacia(false);
		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
			result.setRespuesta(null);
			result.setRespuestaVacia(true);
			result.setEstadoRespuesta(EstadoRespuesta.ERROR);
			List<ItemMensajeRespuesta> mensajesList = new ArrayList<ItemMensajeRespuesta>();
			ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
			itemMensajeRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
			itemMensajeRespuesta.setMensaje(e.getMessage());
			mensajesList.add(itemMensajeRespuesta);
			result.setItemMensajeRespuesta(mensajesList);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			result.setRespuesta(null);
			result.setRespuestaVacia(true);
			result.setEstadoRespuesta(EstadoRespuesta.ERROR);
			List<ItemMensajeRespuesta> mensajesList = new ArrayList<ItemMensajeRespuesta>();
			ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
			itemMensajeRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
			itemMensajeRespuesta.setMensaje(e.getMessage());
			mensajesList.add(itemMensajeRespuesta);
			result.setItemMensajeRespuesta(mensajesList);
		}
		return result;
	}

	/**
	 * Completar datos de sucursales.
	 *
	 * @param movimientosDeCuenta
	 *            the movimientos de cuenta
	 * @return the list
	 */
	private List<MovimientoDeCuenta> completarDatosDeSucursales(List<MovimientoDeCuenta> movimientosDeCuenta) {
		for (MovimientoDeCuenta movimientoDeCuenta : movimientosDeCuenta) {
			completarDatosDeSucursales(movimientoDeCuenta);
		}
		return movimientosDeCuenta;
	}

	/**
	 * Completar datos de sucursales.
	 *
	 * @param movimientoDeCuenta
	 *            the movimiento de cuenta
	 * @return the movimiento de cuenta
	 */
	private MovimientoDeCuenta completarDatosDeSucursales(MovimientoDeCuenta movimientoDeCuenta) {
		// Sucursal de cuenta
		Sucursal sucursal = movimientoDeCuenta.getSucursal();
		Respuesta<Sucursal> respuestaSucursal = consultarSucursalesBO.consultarSucursalPorId(sucursal.getIdSucursal());
		if (EstadoRespuesta.OK.equals(respuestaSucursal.getEstadoRespuesta())
				&& !respuestaSucursal.isRespuestaVacia()) {
			movimientoDeCuenta.setSucursal(respuestaSucursal.getRespuesta());
		}
		// Sucursal de origen movimiento
		sucursal = movimientoDeCuenta.getSucursalOrigen();
		respuestaSucursal = consultarSucursalesBO.consultarSucursalPorId(sucursal.getIdSucursal());
		if (EstadoRespuesta.OK.equals(respuestaSucursal.getEstadoRespuesta())
				&& !respuestaSucursal.isRespuestaVacia()) {
			movimientoDeCuenta.setSucursalOrigen(respuestaSucursal.getRespuesta());
		}
		return movimientoDeCuenta;
	}

	/**
	 * Generar movimientos pendientes de confirmacion.
	 *
	 * @param movimientosDeCuenta
	 *            the movimientos de cuenta
	 * @return the movimientos pendientes de confirmacion
	 */
	private MovimientosPendientesDeConfirmacion generarMovimientosPendientesDeConfirmacion(
			List<MovimientoDeCuenta> movimientosDeCuenta) {

		MovimientosPendientesDeConfirmacion result = new MovimientosPendientesDeConfirmacion();
		List<MovimientoDeCuenta> resultList = result.getMovimientosPendientesDeConfirmacion();
		EstadoOperacionMovimimiento estado = null;
		for (MovimientoDeCuenta movimientoDeCuenta : movimientosDeCuenta) {
			estado = movimientoDeCuenta.getEstado();
			if (EstadoOperacionMovimimiento.A_CONFIRMAR.equals(estado)
					|| EstadoOperacionMovimimiento.A_CONFIRMAR_CON_CPC.equals(estado)) {
				resultList.add(movimientoDeCuenta);
			}
		}
		ordenarMovimientosPorSucursal(resultList);
		return result;
	}

	/**
	 * Ordenar movimientos por sucursal.
	 *
	 * @param resultList
	 *            the result list
	 */
	private void ordenarMovimientosPorSucursal(List<MovimientoDeCuenta> resultList) {
		Collections.sort(resultList, new MovimientoDeCuentaPorSucursalComparator());
	}

	/**
	 * The Class MovimientoDeCuentaPorSucursalComparator.
	 */
	private static class MovimientoDeCuentaPorSucursalComparator
			implements Comparator<MovimientoDeCuenta>, Serializable {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/** The formatter. */
		private final SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(MovimientoDeCuenta o1, MovimientoDeCuenta o2) {
			Sucursal sucursal1 = o1.getSucursalOrigen();
			Integer idSucursal1 = Integer.valueOf(sucursal1.getIdSucursal());
			Sucursal sucursal2 = o2.getSucursalOrigen();
			Integer idSucursal2 = Integer.valueOf(sucursal2.getIdSucursal());
			int resultadoComparacion = idSucursal1.compareTo(idSucursal2);
			// ordena por sucursal, si es la misma ordena por hora (la fecha es
			// igual)
			if (resultadoComparacion == 0) {
				Date hora1;
				try {
					hora1 = formatter.parse(o1.getHora());
				} catch (ParseException e) {
					// si no hay hora, entonces lo pongo como ultimo
					return 1;
				}
				Date hora2;
				try {
					hora2 = formatter.parse(o2.getHora());
				} catch (ParseException e) {
					// si no hay hora, entonces lo pongo como ultimo
					return -1;
				}
				resultadoComparacion = hora1.compareTo(hora2);
			}
			return resultadoComparacion;
		}

	}
}
