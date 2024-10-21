package ar.com.santanderrio.obp.servicios.prestamos.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.prestamos.dto.EncolamientoRequestDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.EncolamientoResponseDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.LiquidacionResponseDTO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.TipoOfertaEnum;

/**
 * DAO para consumir los servicios de prestamos-bff
 *
 */
public interface PrestamosBffDAO {

	/**
	 * Encola un prestamo a traves de prestamos-bff
	 * 
	 * @param encolamientoRequestDTO Datos del prestamo a encolar
	 * @return encolamientoRespondeDTO Datos de la respuesta del servicio
	 * @throws DAOException
	 */
	EncolamientoResponseDTO encolarPrestamo(EncolamientoRequestDTO encolamientoRequestDTO) throws DAOException;

	/**
	 * Liquida un prestamo que se encuentra encolado a traves de prestamos-bff
	 * 
	 * @param tipoOferta tipo de oferta del prestamo a liquidar
	 * @param nup        Numero unico de persona a quien liquidarle el prestamo
	 *                   encolado
	 * @return encolamientoRespondeDTO Datos de la respuesta del servicio
	 * @throws DAOException
	 */
	LiquidacionResponseDTO liquidarPrestamo(TipoOfertaEnum tipoOferta, String nup) throws DAOException;

}
