/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.generated.webservices.inversiones.ConfirmacionOrdenResponse;
import ar.com.santanderrio.obp.generated.webservices.inversiones.DatosEvaluacionRiesgo;
import ar.com.santanderrio.obp.generated.webservices.inversiones.EvaluacionDeRiesgoResponse;
import ar.com.santanderrio.obp.generated.webservices.inversiones.ParametroDatosConfirmacionOrden;
import ar.com.santanderrio.obp.servicios.inversiones.comun.entity.PerfilInversorRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.comun.entity.PerfilInversorResponse;

/**
 * DAO.
 *
 * @author marcelo.ruiz
 */
public interface InversionDAO {

	/**
	 * Evaluacion de riesgo.
	 *
	 * @param datosEvaluacionRiesgo
	 *            the datos evaluacion riesgo
	 * @return the evaluacion de riesgo response
	 * @throws DAOException
	 *             the DAO exception
	 */
	EvaluacionDeRiesgoResponse evaluacionDeRiesgo(DatosEvaluacionRiesgo datosEvaluacionRiesgo) throws DAOException;

	/**
	 * Confirmacion orden.
	 *
	 * @param confirmacionOrden
	 *            the confirmacion orden
	 * @return the confirmacion orden response
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConfirmacionOrdenResponse confirmacionOrden(ParametroDatosConfirmacionOrden confirmacionOrden) throws DAOException;

	/**
	 * Consulta perfil de inversor.
	 *
	 * @param datosConsultaPerfilInversor
	 *            the datos consulta perfil inversor
	 * @return the consulta perfil inversor response
	 * @throws DAOException
	 *             the DAO exception
	 */
	PerfilInversorResponse consultaPerfilInversor(PerfilInversorRequestEntity request) throws DAOException;

}
