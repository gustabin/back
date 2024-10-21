/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.bo;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;

/**
 * The Interface AliasFavoritoTarjetaBo.
 */
public interface AliasFavoritoTarjetaBo {

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
	 * Actualizar alias.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param alias
	 *            the alias
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	Respuesta<Void> actualizarAlias(Cuenta cuenta, String alias) throws BusinessException;

}