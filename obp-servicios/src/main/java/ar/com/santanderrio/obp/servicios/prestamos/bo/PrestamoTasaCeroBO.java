package ar.com.santanderrio.obp.servicios.prestamos.bo;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.pagos.entities.PrestamoTasaCeroConfigEntity;
import ar.com.santanderrio.obp.servicios.prestamos.dto.ConfirmarPrestamoTasaCeroInDTO;

/**
 * The Interface PrestamoTasaCeroBO.
 */
public interface PrestamoTasaCeroBO {

	/**
	 * Inicio prestamos tasa cero.
	 *
	 * @param cliente the cliente
	 * @return the respuesta
	 * @throws DAOException the DAO exception
	 */
	Respuesta<PrestamoTasaCeroConfigEntity> inicioPrestamosTasaCero(SesionCliente cliente) throws DAOException;
	
	/**
	 * Solicitar prestamos tasa cero.
	 *
	 * @param dto the dto
	 * @param cliente the cliente
	 * @return true, if successful
	 * @throws DAOException the DAO exception
	 */
	boolean solicitarPrestamosTasaCero(ConfirmarPrestamoTasaCeroInDTO dto, SesionCliente cliente) throws DAOException;

}
