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
import ar.com.santanderrio.obp.servicios.prestamos.dto.GenericRestResponseDto;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PagoCuotaRequestDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PagoCuotaResponseDTO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.GenericRestException;
import ar.com.santanderrio.obp.servicios.prestamos.utils.PagoCuotaMapper;
import ar.com.santanderrio.obp.servicios.prestamos.utils.PrestamosUtils;
import ar.com.santanderrio.obp.servicios.prestamos.view.PagoCuotaInView;
import ar.com.santanderrio.obp.servicios.prestamos.view.PagoCuotaOutView;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.PagoCuotaManager;

@Component
public class PagoCuotaManagerImpl implements PagoCuotaManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(PagoCuotaManagerImpl.class);

	@Autowired
	private PrestamoBO prestamoBO;

	@Autowired
	private PagoCuotaMapper pagoCuotaMapper;

	@Autowired
	private EstadisticaManager estadisticaManager;

	@Autowired
	private RespuestaFactory respuestaFactory;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Respuesta pagar(String numeroPrestamo, PagoCuotaInView pagoCuotaInView) {
		PagoCuotaRequestDTO request = new PagoCuotaRequestDTO(pagoCuotaInView.getSucursalCuenta(),
				pagoCuotaInView.getTipoCuenta(), pagoCuotaInView.getNroCuenta(), pagoCuotaInView.getSucursalPrestamo(),
				pagoCuotaInView.getMonto(), numeroPrestamo, pagoCuotaInView.getCotizacionPrestamo());
		try {
			LOGGER.info("Se va a pagar la cuota del prestamo {} - SID {} - CID {}",
					numeroPrestamo, PrestamosUtils.getSessionId(), PrestamosUtils.getCorrelationId());
			PagoCuotaResponseDTO response = prestamoBO.pagarCuota(request);
			PagoCuotaOutView out = pagoCuotaMapper.from(response);
			estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_PAGO_PRESTAMO,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(out);
		} catch (GenericRestException e) {
			LOGGER.error("Error al pagar la cuota del prestamo {}: code {} - SID {} - CID {}", numeroPrestamo,
					e.getResponseErrorDTO().getCode(), PrestamosUtils.getSessionId(),
					PrestamosUtils.getCorrelationId());
			estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_PAGO_PRESTAMO,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			Respuesta respuesta = respuestaFactory.crearRespuestaErrorPersonalizado(GenericRestResponseDto.class,
					e.getMessage(), e.getErrorCode());
			respuesta.setRespuesta(e.getResponseErrorDTO());
			return respuesta;
		} catch (Exception e) {
			LOGGER.error("Error al pagar la cuota del prestamo {}: message {} - SID {} - CID {}", numeroPrestamo,
					e.getMessage(), PrestamosUtils.getSessionId(), PrestamosUtils.getCorrelationId());
			estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_PAGO_PRESTAMO,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			Respuesta respuesta = respuestaFactory.crearRespuestaErrorPersonalizado(GenericRestResponseDto.class,
					"No se pudo pagar la cuota del préstamo", "loan.default.error");
			GenericRestResponseDto dto = new GenericRestResponseDto("loan.default.error",
					"Por favor, volvé a intentar en unos minutos.", "No podemos continuar con esta operación");
			respuesta.setRespuesta(dto);
			return respuesta;
		}
	}

}
