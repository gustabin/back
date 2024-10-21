/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.generated.webservices.visa.planv.ConfirmacionSolicitudPlanV;
import ar.com.santanderrio.obp.generated.webservices.visa.planv.InformacionPlanV;
import ar.com.santanderrio.obp.generated.webservices.visa.planv.SolicitudPlanV;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosConfirmacionFinanciacionTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.SimulacionPlanVDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.SimulacionPlanVRequestDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.exceptions.SimulacionDAOException;

/**
 * The Interface ConsultaFinanciacionDAO.
 *
 * @author sergio.e.goldentair
 */
public interface ConsultaFinanciacionDAO {

	/**
	 * Obtener el listado de financiacion disponible.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return List<SolicitudPlanV>
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<SolicitudPlanV> obtenerListaFinanciacion(Cuenta cuenta) throws DAOException;

	/**
	 * Obtener informacion plan V.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the informacion plan V
	 * @throws DAOException
	 *             the DAO exception
	 */
	InformacionPlanV obtenerInformacionPlanV(Cuenta cuenta) throws DAOException;

	/**
	 * Simulacion financiacion plan V. El sistema llama la servicio SIMFIPLANV
	 * para ser usados en la comunicación con Visa host to host y en la llamada
	 * al servicio de confirmación. Llamada a iatx.SIMFIPLANV100
	 *
	 * @author Manuel.Vargas B041299
	 * @param simulacionPlanVRequestDTO
	 *            the simulacion plan V request DTO
	 * @param cliente
	 *            the cliente
	 * @return the consulta financiacion DTO
	 * @throws DAOException
	 *             the DAO exception
	 * @throws SimulacionDAOException
	 *             the simulacion DAO exception
	 */
	SimulacionPlanVDTO simulacionFinanciacionPlanV(SimulacionPlanVRequestDTO simulacionPlanVRequestDTO, Cliente cliente)
			throws DAOException, SimulacionDAOException;

	/**
	 * Ejecutar financiacion tarjeta.
	 *
	 * @author Manuel.Vargas B041299
	 * @param datos
	 *            the datos
	 * @return the financiacion tarjeta DTO
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConfirmacionSolicitudPlanV ejecutarFinanciacionTarjeta(DatosConfirmacionFinanciacionTarjetaDTO datos)
			throws DAOException;
}
