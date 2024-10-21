package ar.com.santanderrio.obp.servicios.prestamos.dao;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPreaprobadoMonoproductoInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPreaprobadoMonoproductoOutEntity;

@Component
public interface PrestamoPreaprobadoMonoproductoDAO {

	/**
	 * Realiza el alta de un prestamo preaprobado monoproducto
	 * @param prestamoPreaprobadoInOutEntity
	 * @param cliente
	 * @return
	 * @throws DAOException
	 */
	PrestamoPreaprobadoMonoproductoOutEntity altaPrestamoPreabrobadoMonoproducto(PrestamoPreaprobadoMonoproductoInEntity prestamoPreaprobadoInOutEntity, Cliente cliente) throws DAOException;
}
