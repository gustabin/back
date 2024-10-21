package ar.com.santanderrio.obp.servicios.prestamos.dao;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.pagos.entities.PrestamoTasaCeroConfigEntity;
import ar.com.santanderrio.obp.servicios.prestamos.dto.ConfirmarPrestamoTasaCeroInDTO;


/**
 * The Interface PrestamoTasaCeroDAO.
 */
@Component
public interface PrestamoTasaCeroDAO {

	/**
	 * Inicio prestamos tasa cero.
	 *
	 * @param cliente the cliente
	 * @return the prestamo tasa cero config entity
	 * @throws DAOException the DAO exception
	 */
	PrestamoTasaCeroConfigEntity inicioPrestamosTasaCero(SesionCliente cliente) throws DAOException;

	/**
	 * Consultar prestamos tasa cero.
	 *
	 * @param nup the nup
	 * @return true, if successful
	 * @throws DAOException the DAO exception
	 */
	boolean consultarPrestamosTasaCero(String nup) throws DAOException;

	/**
	 * Insertar prestamo tasa cero.
	 *
	 * @param cliente the cliente
	 * @return the prestamo tasa cero comprobante entity
	 * @throws DAOException the DAO exception
	 */
	boolean solicitarPrestamosTasaCero(ConfirmarPrestamoTasaCeroInDTO dto, SesionCliente cliente) throws DAOException;

}
