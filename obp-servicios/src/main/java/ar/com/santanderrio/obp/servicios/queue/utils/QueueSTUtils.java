package ar.com.santanderrio.obp.servicios.queue.utils;

import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.queue.entities.QueueSTOperations;
import ar.com.santanderrio.obp.servicios.queue.entities.TurnDTO;
import ar.com.santanderrio.obp.servicios.queue.entities.TurnStatesEnum;

/**
 * The Class QueueSTUtils.
 */
public class QueueSTUtils {

	/**
	 * Instantiates a new queue ST utils.
	 */
	private QueueSTUtils() {
	}

	/**
	 * Verificar horario queue.
	 *
	 * @param operation the operation
	 * @return true, if successful
	 */
	public static boolean verificarHorarioQueue(QueueSTOperations operation) {
		boolean horarioValido = false;
		String queueHoraDesde = ContextHolder.getContext().getEnvironment().getProperty(operation.getParamDesde());
		String queueHoraHasta = ContextHolder.getContext().getEnvironment().getProperty(operation.getParamHasta());
		if (!ISBANStringUtils.isNullOrBlank(queueHoraDesde) && !ISBANStringUtils.isNullOrBlank(queueHoraHasta)) {
			try {
				horarioValido = ISBANStringUtils.compararHoras(queueHoraDesde, queueHoraHasta);
			} catch (Exception e) {
			}
		}
		return horarioValido;
	}

	/**
	 * Es turno habilitado.
	 *
	 * @param turnDTO the turn DTO
	 * @return true, if successful
	 */
	public static boolean esTurnoHabilitado(TurnDTO turnDTO) {
		return turnDTO == null || (TurnStatesEnum.TURN_STATUS_READY.getCodigo().equals(turnDTO.getStatus()) ||
				Long.parseLong(turnDTO.getRemainingTime()) <= 0);
	}

}
