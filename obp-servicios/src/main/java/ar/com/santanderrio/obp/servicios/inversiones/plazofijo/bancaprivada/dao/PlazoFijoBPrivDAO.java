/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.bancaprivada.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.bancaprivada.entity.ConsultaPlazoFijoBPrivInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.bancaprivada.entity.ConsultaPlazoFijoBPrivOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.CuentaRossiOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.DetallePFInteresanteInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.FrecuenciaCobroPFInteresanteOutEntity;

/**
 * The Interface PlazoFijoBPrivDAO.
 *
 * @author juan.pablo.picate
 */
public interface PlazoFijoBPrivDAO {

	/**
	 * Obtiene todos los Plazos Fijos.
	 *
	 * @param consultarInEntity
	 *            the consultar in entity
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<ConsultaPlazoFijoBPrivOutEntity> obtenerPlazosFijo(ConsultaPlazoFijoBPrivInEntity consultarInEntity)
			throws DAOException;
	
	/**
	 * Obtener detalle PF interesante BP.
	 *
	 * @param detalleIn
	 *            the detalle in
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<FrecuenciaCobroPFInteresanteOutEntity> obtenerDetallePFInteresanteBP(DetallePFInteresanteInEntity detalleIn) throws DAOException;

	CuentaRossiOutEntity getCuentaRossi(String nroCuenta) throws DAOException;

}
