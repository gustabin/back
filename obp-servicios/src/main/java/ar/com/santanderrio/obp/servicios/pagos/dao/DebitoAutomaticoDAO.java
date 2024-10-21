/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.AdhesionDebitoAutomatico;

/**
 * The Interface DebitoAutomaticoDAO.
 */
public interface DebitoAutomaticoDAO {

	/**
	 * Adherir.
	 *
	 * @param cliente
	 *            the cliente
	 * @param adhesionDebitoAutomatico
	 *            the adhesion debito automatico
	 * @return the respuesta
	 * @throws DAOException
	 *             the DAO exception
	 */
	Respuesta<AdhesionDebitoAutomatico> adherir(Cliente cliente, AdhesionDebitoAutomatico adhesionDebitoAutomatico)
			throws DAOException;

	/**
	 * Invocar servicio confirmar.
	 *
	 * @param cliente
	 *            the cliente
	 * @param adhesionDebitoAutomatico
	 *            the adhesion debito automatico
	 * @param request
	 *            the request
	 * @return the respuesta
	 * @throws DAOException
	 *             the DAO exception
	 */
	Respuesta<AdhesionDebitoAutomatico> invocarServicioConfirmar(Cliente cliente,
			AdhesionDebitoAutomatico adhesionDebitoAutomatico, IatxRequest request) throws DAOException;

	/**
	 * Baja adhesion.
	 *
	 * @param cliente
	 *            the cliente
	 * @param adhesion
	 *            the adhesion
	 * @return the resultado transaccion
	 * @throws DAOException
	 *             the DAO exception
	 */
	ResultadoTransaccion bajaAdhesion(Cliente cliente, AdhesionDebitoAutomatico adhesion) throws DAOException;

}
