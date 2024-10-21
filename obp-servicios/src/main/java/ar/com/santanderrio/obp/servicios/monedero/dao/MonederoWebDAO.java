/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.monedero.dto.TagsDTO;
import ar.com.santanderrio.obp.servicios.monedero.dto.TagsTransacDTO;
import ar.com.santanderrio.obp.servicios.monedero.entities.TagEntity;
import ar.com.santanderrio.obp.servicios.monedero.entities.TransaccionEntity;

/**
 * The Interface MonederoWebDAO.
 */
public interface MonederoWebDAO {

	/**
	 * Obtener tags.
	 *
	 * @param dto
	 *            the dto
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<TagEntity> obtenerTags(TagsDTO dto) throws DAOException;

	/**
	 * Obtener transacciones tag.
	 *
	 * @param dto
	 *            the dto
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<TransaccionEntity> obtenerTransaccionesTag(TagsTransacDTO dto) throws DAOException;

}
