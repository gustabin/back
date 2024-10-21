/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.bo.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.dao.AliasFavoritoProductoDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.AliasFavoritoTarjetaBo;
import ar.com.santanderrio.obp.servicios.tarjetas.util.ManejoDeMensajesTarjeta;

/**
 * The Class AliasFavoritoTarjetaBoImpl.
 */
@Component
public class AliasFavoritoTarjetaBoImpl extends TarjetasBOImpl implements AliasFavoritoTarjetaBo {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AliasFavoritoTarjetaBoImpl.class);

	/** The Constant MAX_ALIAS_LENGTH. */
	private static final int MAX_ALIAS_LENGTH = 18;

	/** The alias favorito producto DAO. */
	@Autowired
	private AliasFavoritoProductoDAO aliasFavoritoProductoDAO;

	/** The mensajes tarjeta. */
	@Autowired
	private ManejoDeMensajesTarjeta mensajesTarjeta;

	/**
	 * Actualiza el alias de la cuenta.
	 * 
	 * @param cuenta
	 *            the cuenta
	 * @param alias
	 *            the alias
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	@Override
	public Respuesta<Void> actualizarAlias(Cuenta cuenta, String alias) throws BusinessException {
		Respuesta<Void> respuestaAlias = new Respuesta<Void>();
		try {
			if (alias.length() > MAX_ALIAS_LENGTH) {
				armarRespuestaAliasConError(respuestaAlias);
				return respuestaAlias;
			}
			Long nup = Long.valueOf(cuenta.getCliente().getNup());
			String nroCtaProducto = cuenta.getNroCuentaProducto();
			aliasFavoritoProductoDAO.actualizaAlias(nup, nroCtaProducto, alias);

			cuenta.setAlias(alias);

			respuestaAlias.setEstadoRespuesta(EstadoRespuesta.OK);
			respuestaAlias.setRespuestaVacia(false);
		} catch (DAOException e) {
			LOGGER.info("Error al actualizar alias.", e);
			armarRespuestaAliasConError(respuestaAlias);
		}
		return respuestaAlias;
	}

	/**
	 * Marca la tarjeta como favorita.
	 * 
	 * @param identificacionCuenta
	 *            the identificacion cuenta
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	@Override
	public Respuesta<Void> marcarFavorita(IdentificacionCuenta identificacionCuenta, Cliente cliente) {
		Respuesta<Void> respuestaFavorito = new Respuesta<Void>();
		try {
			AbstractCuenta cuentaFavorita = getCuentaBO().buscarCuentaPorId(cliente, identificacionCuenta);
			actualizaFavorito(cliente, cuentaFavorita, true);

			List<Cuenta> cuentas = cliente.getCuentas();
			for (Cuenta cuenta : cuentas) {
				if (!coincideCuentaId(cuenta, identificacionCuenta) && TarjetaBOUtils.esTipoCuentaTarjeta(cuenta)) {
					actualizaFavorito(cliente, cuenta, false);
				}
			}
			respuestaFavorito.setEstadoRespuesta(EstadoRespuesta.OK);
		} catch (Exception e) {
			LOGGER.error("Error al marcar la tarjeta como favorita", e);
			armarRespuestaFavoritoConError(respuestaFavorito);
		}
		return respuestaFavorito;
	}

	/**
	 * Arma item de mensaje para la respuesta de favorito con error.
	 *
	 * @param <E>
	 *            the element type
	 * @param respuesta
	 *            the respuesta
	 */
	private <E> void armarRespuestaFavoritoConError(Respuesta<E> respuesta) {
		respuesta.add(mensajesTarjeta.obtenerItemErrorActualizarFavoritoTarjeta());
		respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
	}

	/**
	 * Arma item de mensaje para la respuesta de alias con error.
	 *
	 * @param <E>
	 *            the element type
	 * @param respuesta
	 *            the respuesta
	 */
	private <E> void armarRespuestaAliasConError(Respuesta<E> respuesta) {
		respuesta.add(mensajesTarjeta.obtenerItemErrorActualizarAlias());
		respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
	}

	/**
	 * Actualiza favorito.
	 *
	 * @param cliente
	 *            the cliente
	 * @param cuenta
	 *            the cuenta
	 * @param favorita
	 *            the favorita
	 * @throws BusinessException
	 *             the business exception
	 */
	private void actualizaFavorito(Cliente cliente, AbstractCuenta cuenta, boolean favorita) throws BusinessException {
		try {
			Long nup = Long.valueOf(cliente.getNup());
			String nroCtaProducto = cuenta.getNroCuentaProducto();
			aliasFavoritoProductoDAO.actualizaFavorito(nup, nroCtaProducto, favorita);

			cuenta.setIsFavorita(favorita);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	/**
	 * Coincide cuenta id.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param id
	 *            the id
	 * @return true, if successful
	 */
	private boolean coincideCuentaId(AbstractCuenta cuenta, IdentificacionCuenta id) {
		boolean eqNroCuentaProducto = Long.parseLong(cuenta.getNroCuentaProducto()) == Long
				.parseLong(id.getNroCuentaProducto());
		boolean eqNroSucursal = Long.parseLong(cuenta.getNroSucursal()) == Long.parseLong(id.getNroSucursal());
		return eqNroCuentaProducto && eqNroSucursal;
	}

}