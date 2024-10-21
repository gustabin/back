/*
 * 
 */
package ar.com.santanderrio.obp.servicios.turnosweb.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.AltaModificacionInDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.AltaModificacionOutDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.CitaOutDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.ComprobanteTurnoInDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.ComprobanteTurnoRemotoDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.HorariosDisponiblesInDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.HorariosDisponiblesOutDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.MotivoTurnoDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.MotivosInDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.SucursalesOutDTO;

/**
 * The Interface TurnosWebBO.
 *
 * @author IT Resources
 */
public interface TurnosWebBO {

	/**
	 * Obtiene las citas solicitas.
	 *
	 * @param nup
	 *            the nup
	 * @return the respuesta
	 */
	Respuesta<CitaOutDTO> consultarCitas(String nup);
	
	/**
	 * Obtiene las sucursales.
	 *
	 * @return the respuesta
	 */
	Respuesta<SucursalesOutDTO> consultaSucursales();
	
	/**
	 * Obtiene los horarios disponibles.
	 *
	 * @param horariosDisponiblesInDTO
	 *            the horarios disponibles in DTO
	 * @return the respuesta
	 */
	Respuesta<HorariosDisponiblesOutDTO> consultaHorariosDisponibles(HorariosDisponiblesInDTO horariosDisponiblesInDTO);

	/**
	 * Da de baja un turno.
	 *
	 * @param idTurno
	 *            the id turno
	 * @return the respuesta
	 */
	Respuesta<Void> bajaTurno(Long idTurno);
		
	/**
	 * Da de alta o modifica una cita.
	 *
	 * @param altaModificacionInDTO
	 *            the alta modificacion in DTO
	 * @return the respuesta
	 */
	Respuesta<AltaModificacionOutDTO> altaModificacionCita(AltaModificacionInDTO altaModificacionInDTO);

	/**
	 * Genera el comprobante PDF.
	 *
	 * @param comprobanteTurnoInDTO
	 *            the comprobante turno in DTO
	 * @return the respuesta
	 */
	Respuesta<Reporte> generarComprobanteTurnoWeb(ComprobanteTurnoInDTO comprobanteTurnoInDTO);
	
	/**
	 * Genera el comprobante PDF.
	 *
	 * @param comprobanteTurnoInDTO
	 *            the comprobante turno in DTO
	 * @return the respuesta
	 */
	Respuesta<Reporte> generarComprobanteTurnoWebRemoto(ComprobanteTurnoRemotoDTO comprobanteTurnoRemotoDTO);

	/**
	 * Obtener motivos turno.
	 *
	 * @param motivosInDTO the motivos in DTO
	 * @return the respuesta
	 */
	Respuesta<List<MotivoTurnoDTO>> obtenerMotivosTurno(MotivosInDTO motivosInDTO);

	/**
	 * Obtiene los clientes que pueden Realizar Autogestion.
	 *
	 * @param String nup
	 * @return 
	 */
	Boolean consultaNupClienteAutogestion(String nup);
}
