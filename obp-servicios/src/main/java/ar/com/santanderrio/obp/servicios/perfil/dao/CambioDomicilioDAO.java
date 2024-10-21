/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.perfil.entities.CambioDomicilioInEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.CambioDomicilioOutEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaDatosDomicilioInEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaDatosDomicilioOutEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaDatosTelefonoInEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaDatosTelefonoOutEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaDomiciliosInEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaDomiciliosOutEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.DatosComprobanteEntity;

/**
 * interface CambioDomicilioDAO.
 * 
 */
public interface CambioDomicilioDAO {

	/**
	 * Consulta de domicilios registrados del cliente.
	 *
	 * @param consultaDomiciliosInEntity
	 *            the consulta domicilios in entity
	 * @return ConsultaDomiciliosOutEntity
	 * @throws DAOException
	 *             the DAO exception
	 */
	Respuesta<ConsultaDomiciliosOutEntity> consultaDomiciliosRegistrados(ConsultaDomiciliosInEntity consultaDomiciliosInEntity)
			throws DAOException;

	/**
	 * Consulta datos del telefono adicionales.
	 *
	 * @param consultaDatosTelefonoInEntity
	 *            the consulta datos telefono in entity
	 * @return the consulta datos telefono out entity
	 * @throws DAOException
	 *             the DAO exception
	 * @returnConsultaDatosTelefonoOutEntity
	 */
	ConsultaDatosTelefonoOutEntity consultaDatosTelefono(ConsultaDatosTelefonoInEntity consultaDatosTelefonoInEntity)
			throws DAOException;

	/**
	 * Consulta de datos adicionales de un domicilio.
	 *
	 * @param consultaDomicilioInEntity
	 *            the consulta domicilio in entity
	 * @return ConsultaDatosDomicilioOutEntity
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaDatosDomicilioOutEntity consultaDatosDomicilio(ConsultaDatosDomicilioInEntity consultaDomicilioInEntity)
			throws DAOException;

	/**
	 * Guarda la inforamcion de un cambio de domicilio.
	 *
	 * @param cambioDomicilioInEntity
	 *            the cambio domicilio in entity
	 * @return CambioDomicilioOutEntity
	 * @throws DAOException
	 *             the DAO exception
	 */
	CambioDomicilioOutEntity guardarCambioDomicilio(CambioDomicilioInEntity cambioDomicilioInEntity)
			throws DAOException;

	/**
	 * Reporte del comprobante de cambio de domicilio.
	 *
	 * @param datos
	 *            the datos
	 * @return Reporte
	 */
	Reporte descargarComprobante(DatosComprobanteEntity datos);
}
