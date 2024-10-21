/*
 * 
 */
package ar.com.santanderrio.obp.servicios.turnosweb.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetAltaCitaConMotivoSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetBajaTurnoSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaCitaConMotivoSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaHorariosDisponiblesSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaSucursalesSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetModificacionCitaSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetMotivosPorSucursalYSectorCliNoCliSvcResponse;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.turnosweb.entity.AltaModificacionCitaInEntity;
import ar.com.santanderrio.obp.servicios.turnosweb.entity.ComprobanteTurnoInEntity;
import ar.com.santanderrio.obp.servicios.turnosweb.entity.HorariosDisponiblesInEntity;
import ar.com.santanderrio.obp.servicios.turnosweb.entity.MotivosInEntity;

/**
 * The Interface TurnosWebDAO.
 *
 * @author IT Resources
 */
public interface TurnosWebDAO {

	/**
	 * Consulta al webservice del direccionador para obtener las citas.
	 *
	 * @param nup
	 *            the nup
	 * @return the gets the consulta cita svc result
	 * @throws DAOException
	 *             the DAO exception
	 */
	GetConsultaCitaConMotivoSvcResponse consultarCitas(String nup) throws DAOException;
	
	/**
	 * Consulta al webservice del direccionador para obtener los horarios
	 * disponibles.
	 *
	 * @param horariosDisponiblesInEntity
	 *            the horarios disponibles in entity
	 * @return the gets the consulta horarios disponibles svc response 2
	 * @throws DAOException
	 *             the DAO exception
	 */
	GetConsultaHorariosDisponiblesSvcResponse consultaHorarioDisponibles(HorariosDisponiblesInEntity horariosDisponiblesInEntity) throws DAOException;

	/**
	 * Consulta al webservice del direccionador para obtener las sucursales.
	 *
	 * @return the gets the consulta sucursales svc response 2
	 * @throws DAOException
	 *             the DAO exception
	 */
	GetConsultaSucursalesSvcResponse consultaSucursales() throws DAOException;
	
	/**
	 * Consulta al webservice del direccionador para dar de alta una cita.
	 *
	 * @param altaModificacionCitaInEntity
	 *            the alta modificacion cita in entity
	 * @return the gets the alta cita svc response
	 * @throws DAOException
	 *             the DAO exception
	 */
	GetAltaCitaConMotivoSvcResponse altaCita(AltaModificacionCitaInEntity altaModificacionCitaInEntity) throws DAOException;
	
	/**
	 * Consulta al webservice del direccionador para dar de baja un turno.
	 *
	 * @param idTurno
	 *            the id turno
	 * @return the gets the baja turno svc response 2
	 * @throws DAOException
	 *             the DAO exception
	 */
	GetBajaTurnoSvcResponse bajaTurno(Long idTurno) throws DAOException;
	
	/**
	 * Consulta al webservice del direccionador para obtener modificar una cita.
	 *
	 * @param altaModificacionCitaInEntity
	 *            the alta modificacion cita in entity
	 * @return the gets the modificacion cita svc response
	 * @throws DAOException
	 *             the DAO exception
	 */
	GetModificacionCitaSvcResponse modificacionCita(AltaModificacionCitaInEntity altaModificacionCitaInEntity) throws DAOException;

	/**
	 * Generar comprobante.
	 *
	 * @param comprobanteTurnoInEntity the comprobante turno in entity
	 * @param cliente the cliente
	 * @return the reporte
	 */
	Reporte generarComprobante(ComprobanteTurnoInEntity comprobanteTurnoInEntity, Cliente cliente);

	/**
	 * Generar comprobante.
	 *
	 * @param comprobanteTurnoInEntity the comprobante turno in entity
	 * @param cliente the cliente
	 * @return the reporte
	 */
	Reporte generarComprobanteRemoto(ComprobanteTurnoInEntity comprobanteTurnoInEntity, Cliente cliente);
	
	/**
	 * Obtener motivos turno.
	 *
	 * @param motivosInEntity the motivos in entity
	 * @return the gets the motivos por sucursal Y sector cli no cli svc response
	 * @throws DAOException the DAO exception
	 */
	GetMotivosPorSucursalYSectorCliNoCliSvcResponse obtenerMotivosTurno(MotivosInEntity motivosInEntity) throws DAOException;

}
