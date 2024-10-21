/**
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ComprobanteSuscripcionFondoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ConsultaTenenciaFCIInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ConsultaTenenciaFCIOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ConsultaTenenciaFDCInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.FondoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.MovimientosFondoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.MovimientosFondoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.SuscripcionFondoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.SuscripcionFondoOutEntity;

/**
 * The Interface FondoDAO.
 *
 * @author
 */
public interface FondoDAO {

	/**
	 * Suscribir.
	 *
	 * @param entity
	 *            the entity
	 * @return the suscripcion fondo out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	SuscripcionFondoOutEntity suscribir(SuscripcionFondoInEntity entity) throws DAOException;

	/**
	 * Consultar cotizaciones.
	 *
	 * @return the fondo out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	FondoOutEntity consultarCotizaciones() throws DAOException;

	/**
	 * Consulta de tenencia de FCI.
	 *
	 * @param cliente
	 *            the cliente
	 * @param entity
	 *            the entity
	 * @return the consulta tenencia FCI out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaTenenciaFCIOutEntity consultaTenenciaFCI(Cliente cliente, ConsultaTenenciaFCIInEntity entity)
			throws DAOException;

	/**
	 * Consulta de tenencia de FDC.
	 *
	 * @param cliente
	 *            the cliente
	 * @param entity
	 *            the entity
	 * @return the consulta tenencia FDC out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaTenenciaFCIOutEntity consultaTenenciaFDC(Cliente cliente, ConsultaTenenciaFDCInEntity entity)
            throws DAOException;
	
	/**
	 * Consultar movimientos.
	 *
	 * @param cliente
	 *            the cliente
	 * @param fondoAConsultar
	 *            the fondo A consultar
	 * @return the movimientos fondo out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	MovimientosFondoOutEntity consultarMovimientos(Cliente cliente, MovimientosFondoInEntity fondoAConsultar)
			throws DAOException;

	/**
	 * Obtiene el comprobante de suscripcion de un fondo, este servicio debe
	 * invocarse luego de aceptar la confirmacion del alta de una suscripcion de
	 * un fondo.
	 *
	 * @param cliente
	 *            the cliente
	 * @param entity
	 *            the entity
	 * @return the suscripcion fondo out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	SuscripcionFondoOutEntity comprobanteSuscripcionFondo(Cliente cliente, ComprobanteSuscripcionFondoInEntity entity)
			throws DAOException;

}
