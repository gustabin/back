/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.ConsultaCuotaPagaInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.ConsultaCuotaPagaOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.DatoClienteCuotaPagaInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.DatoClienteCuotaPagaOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.exception.SinCuotasPagasException;

/**
 * The Interface PrestamoCuotaPagaDAO.
 * 
 * @author florencia.n.martinez
 */
public interface PrestamoCuotaPagaDAO {

	/**
	 * Obtiene las cuotas pagas de un prestamo.
	 *
	 * @param cliente
	 *            the cliente
	 * @param inEntity
	 *            the in entity
	 * @return the consulta cuota paga out entity
	 * @throws DAOException
	 *             the DAO exception
	 * @throws SinCuotasPagasException
	 *             the sin cuotas pagas exception
	 */
	ConsultaCuotaPagaOutEntity obtenerCuotasPagasPrestamo(Cliente cliente, ConsultaCuotaPagaInEntity inEntity)
			throws DAOException, SinCuotasPagasException;

	/**
	 * Obtener datos cliente cuotas pagas prestamo.
	 *
	 * @param cliente
	 *            the cliente
	 * @param inEntity
	 *            the in entity
	 * @return the dato cliente cuota paga out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	DatoClienteCuotaPagaOutEntity obtenerDatosClienteCuotasPagasPrestamo(Cliente cliente,
			DatoClienteCuotaPagaInEntity inEntity) throws DAOException;

}
