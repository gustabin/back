/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dao;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.inversiones.comun.entity.PerfilInversorRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaGralPlazoFijoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaGralPlazoFijoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaInteresantePlazoFijoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaInteresantePlazoFijoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaPrecancelablePlazoFijoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaPrecancelablenPlazoFijoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaTasasPlazoFijoBPrivOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaTiposPlazoFijoBPrivOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ImposicionPlazoFijoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ImposicionPlazoFijoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.MantenimientoPlazoFijoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.MantenimientoPlazoFijoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.PrecancelacionPlazoFijoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.RecomendacionResponseEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.RouterApiRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.RouterApiResponseEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.SimulacionPlazoFijoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.SimulacionPlazoFijoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.SimulacionPrecancelableOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.SimulacionPrecancelableUvaOutEntity;

/**
 * The Interface PlazoFijoDAO.
 *
 * @author juan.pablo.picate
 */
public interface PlazoFijoDAO {

	/**
	 * Consultar tipos.
	 *
	 * @param cliente
	 *            the cliente
	 * @return tipos plazos fijos.
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaTiposPlazoFijoBPrivOutEntity consultarTipos(Cliente cliente, String bancaSeleccionada, boolean pfRepatriacion) throws DAOException;

	/**
	 * Consultar tasas.
	 *
	 * @param cliente
	 *            the cliente
	 * @param bancaSeleccionada
	 *            the banca seleccionada
	 * @return tasas plazos fijos
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaTasasPlazoFijoBPrivOutEntity consultarTasas(Cliente cliente, String bancaSeleccionada, boolean pfRepatriacion) throws DAOException;

	/**
	 * Consultar tenencia.
	 *
	 * @param entity
	 *            the entity
	 * @param cliente
	 *            the cliente
	 * @return tenencia plazos fijos
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaGralPlazoFijoOutEntity consultarTenencia(ConsultaGralPlazoFijoInEntity entity, Cliente cliente)
			throws DAOException;

	/**
	 * omnsulta de PF interensante.
	 *
	 * @param entity
	 *            the entity
	 * @param cliente
	 *            the cliente
	 * @return plazos fijos interesantes
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaInteresantePlazoFijoOutEntity consultarInteresante(ConsultaInteresantePlazoFijoInEntity entity,
			Cliente cliente) throws DAOException;

	/**
	 * Confirmar accion vencimiento.
	 *
	 * @param entity
	 *            the entity
	 * @param cliente
	 *            the cliente
	 * @return confirmar accion al vencimiento de un plazo fijo
	 * @throws DAOException
	 *             the DAO exception
	 */
	MantenimientoPlazoFijoOutEntity confirmarAccionVencimiento(MantenimientoPlazoFijoInEntity entity, Cliente cliente)
			throws DAOException;

	/**
	 * Confirmar constitucion.
	 *
	 * @param entity
	 *            the entity
	 * @param cliente
	 *            the cliente
	 * @return confirmar la constitucion de un plazo fijo
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 */
	ImposicionPlazoFijoOutEntity confirmarConstitucion(ImposicionPlazoFijoInEntity entity, Cliente cliente,
			String tipoBanca, boolean pfRepatriacion) throws DAOException, BusinessException;

	/**
	 * Simular plazo fijo.
	 *
	 * @param entity
	 *            the entity
	 * @param cliente
	 *            the cliente
	 * @return condiciones de plazo fijo
	 * @throws DAOException
	 *             the DAO exception
	 */
	SimulacionPlazoFijoOutEntity simularPlazoFijo(SimulacionPlazoFijoInEntity entity, Cliente cliente, boolean pfRepatriacion)
			throws DAOException;

	/**
	 * Consultar precancelable.
	 *
	 * @param entity
	 *            the entity
	 * @param cliente
	 *            the cliente
	 * @return plazos fijos precancelables
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaPrecancelablenPlazoFijoOutEntity consultarPrecancelable(ConsultaPrecancelablePlazoFijoInEntity entity,
			Cliente cliente, boolean uvaPrecaBPriv) throws DAOException;

	/**
	 * Simular precancelable.
	 *
	 * @param entity
	 *            the entity
	 * @param cliente
	 *            the cliente
	 * @return simulacion/precancelacion plazos fijos
	 * @throws DAOException
	 *             the DAO exception
	 */
	SimulacionPrecancelableOutEntity simularPrecancelable(PrecancelacionPlazoFijoInEntity entity, Cliente cliente)
			throws DAOException;

	/**
	 * Solicitar precancelacion.
	 *
	 * Se consulta tanto para la simulación como para la solicitud de
	 * precancelación de un plazo fijo
	 *
	 * @param entity
	 *            the entity
	 * @param cliente
	 *            the cliente
	 * @return simulacion/precancelacion plazos fijos
	 * @throws DAOException
	 *             the DAO exception
	 */
	SimulacionPrecancelableUvaOutEntity solicitarPrecancelacion(PrecancelacionPlazoFijoInEntity entity, Cliente cliente, String tipoBanca)
			throws DAOException;

	
	RecomendacionResponseEntity consultaRecomendacion(PerfilInversorRequestEntity request) throws BusinessException;
	
	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Vacia la cache de tasas de plazos fijos.
	 */
	void vaciarCacheTasas();

	RouterApiResponseEntity invocarRouterApi(RouterApiRequestEntity routerApiRequestEntity) throws DAOException;

	ConsultaTiposPlazoFijoBPrivOutEntity consultarTiposRouterApi(String bancaSeleccionada) throws DAOException;

	String determinarVersion(String bancaSeleccionada, boolean repatriacion);

}
