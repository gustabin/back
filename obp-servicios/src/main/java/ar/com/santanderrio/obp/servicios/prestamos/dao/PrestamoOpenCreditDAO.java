/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.dao;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.ConsultaCuotaPagaInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.ConsultaCuotaPagaOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.ConsultaPrestamoOpenCreditInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.ConsultaPrestamoOpenCreditOutEntity;

/**
 * OLYMPUS PrestamoOpenCreditDAO.
 *
 * @author Silvina_Luque
 */
@Component
public interface PrestamoOpenCreditDAO {

	/**
	 * CNSPERACTI120 OLYMPUS.
	 *
	 * @param consultaPrestamoOpenCreditInEntity
	 *            the consulta prestamo open credit in entity
	 * @return the consulta prestamo open credit out entity
	 * @throws DAOException
	 *             the DAO exception
	 */

	ConsultaPrestamoOpenCreditOutEntity consultarPrestamo(
			ConsultaPrestamoOpenCreditInEntity consultaPrestamoOpenCreditInEntity) throws DAOException;

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
	 */
	ConsultaCuotaPagaOutEntity obtenerCuotasPagasPrestamo(Cliente cliente, ConsultaCuotaPagaInEntity inEntity)
			throws DAOException;

}
