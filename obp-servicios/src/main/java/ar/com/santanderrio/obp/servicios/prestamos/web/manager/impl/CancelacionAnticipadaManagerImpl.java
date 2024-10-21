package ar.com.santanderrio.obp.servicios.prestamos.web.manager.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoBO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.CancelacionAnticipadaRequestDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.CancelacionAnticipadaResponseDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.GenericRestResponseDto;
import ar.com.santanderrio.obp.servicios.prestamos.entity.GenericRestException;
import ar.com.santanderrio.obp.servicios.prestamos.utils.CancelacionAnticipadaMapper;
import ar.com.santanderrio.obp.servicios.prestamos.utils.PrestamosUtils;
import ar.com.santanderrio.obp.servicios.prestamos.view.CancelacionAnticipadaInView;
import ar.com.santanderrio.obp.servicios.prestamos.view.CancelacionAnticipadaOutView;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.CancelacionAnticipadaManager;

@Component
public class CancelacionAnticipadaManagerImpl implements CancelacionAnticipadaManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(CancelacionAnticipadaManagerImpl.class);

	@Autowired
	private PrestamoBO prestamoBO;

	@Autowired
	private CancelacionAnticipadaMapper cancelacionAnticipadaMapper;

	@Autowired
	private EstadisticaManager estadisticaManager;

	@Autowired
	private RespuestaFactory respuestaFactory;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Respuesta<CancelacionAnticipadaOutView> cancelarAnticipadamente(String numeroPrestamo,
			CancelacionAnticipadaInView cancelacionAnticipadaInView) {
		CancelacionAnticipadaRequestDTO request = new CancelacionAnticipadaRequestDTO(
				cancelacionAnticipadaInView.getSucursalPrestamo(), cancelacionAnticipadaInView.getNroPrestamo(),
				cancelacionAnticipadaInView.getMoneda(), cancelacionAnticipadaInView.getSucursalCuenta(),
				cancelacionAnticipadaInView.getNroCuenta());
		try {
			LOGGER.info("Se va a cancelar anticipadamente el prestamo {} - SID {} - CID {}",
					numeroPrestamo, PrestamosUtils.getSessionId(), PrestamosUtils.getCorrelationId());
			CancelacionAnticipadaResponseDTO response = prestamoBO.cancelarAnticipadamente(request);
			CancelacionAnticipadaOutView out = cancelacionAnticipadaMapper.from(response);
			estadisticaManager.add(EstadisticasConstants.CANCELACION_PRESTAMO_CONFIRMACION,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(out);
		} catch (GenericRestException e) {
			LOGGER.error("Error al cancelar anticipadamente el prestamo {}: code {} - SID {} - CID {}", numeroPrestamo,
					e.getResponseErrorDTO().getCode(), PrestamosUtils.getSessionId(),
					PrestamosUtils.getCorrelationId());
			estadisticaManager.add(EstadisticasConstants.CANCELACION_PRESTAMO_CONFIRMACION,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			Respuesta respuesta = respuestaFactory.crearRespuestaErrorPersonalizado(GenericRestResponseDto.class,
					e.getResponseErrorDTO().getMessage(), e.getResponseErrorDTO().getCode());
			respuesta.setRespuesta(e.getResponseErrorDTO());
			return respuesta;
		} catch (Exception e) {
			LOGGER.error("Error al cancelar anticipadamente el prestamo {}: message {} - SID {} - CID {}", numeroPrestamo,
					e.getMessage(), PrestamosUtils.getSessionId(), PrestamosUtils.getCorrelationId());
			estadisticaManager.add(EstadisticasConstants.CANCELACION_PRESTAMO_CONFIRMACION,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			Respuesta respuesta = respuestaFactory.crearRespuestaErrorPersonalizado(GenericRestResponseDto.class,
					"No se pudo cancelar anticipadamente el préstamo", "loan.default.error");
			GenericRestResponseDto dto = new GenericRestResponseDto("loan.default.error",
					"Por favor, volvé a intentar en unos minutos.", "No podemos continuar con esta operación");
			respuesta.setRespuesta(dto);
			return respuesta;
		}
	}

}
