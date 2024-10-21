/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenenciaConsolidada.comun.manager;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dto.RendimientoBPrivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dto.RendimientoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.manager.BaseManager;
import ar.com.santanderrio.obp.servicios.inversiones.comun.manager.InversionesManagerImpl;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RendimientoBPrivView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RendimientoView;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.bo.RendimientoBOImpl;

/**
 * The Class TenenciaConsolidadaManagerImpl.
 */
@Component
public class TenenciaConsolidadaManagerImpl extends BaseManager implements TenenciaConsolidadaManager {

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The rendimiento BO impl. */
	@Autowired
	private RendimientoBOImpl rendimientoBOImpl;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(InversionesManagerImpl.class);

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.tenenciaConsolidada.comun.manager.TenenciaConsolidadaManager#obtenerRendimiento()
	 */
	@Override
	public Respuesta<RendimientoView> obtenerRendimiento() {

		Respuesta<RendimientoDTO> rtaRendimiento = rendimientoBOImpl.obtenerRendimientoTenenciaRTL(sesionCliente.getCliente(), null);
		String codigoEstadistica = EstadisticasConstants.RENDIMIENTO_TENENCIA_CONSOLIDADA;

		RendimientoDTO rendimientoDTO = rtaRendimiento.getRespuesta();
		RendimientoView rendimientoView = createRetornoView(rendimientoDTO);
		rendimientoView.setTooltip(obtenerMensajeRendimiento());

		if (EstadoRespuesta.OK.equals(rtaRendimiento.getEstadoRespuesta())) {
			estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(RendimientoView.class, rendimientoView);
		}
		if (EstadoRespuesta.WARNING.equals(rtaRendimiento.getEstadoRespuesta())) {
			estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL);
			return respuestaFactory.crearRespuestaWarning(rendimientoView, "",TipoError.WARNING_PARCIAL_TENENCIA_CONSOLIDADA, "");
		}
		estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
	}

	/**
	 * Creates the retorno view.
	 *
	 * @param dto
	 *            the dto
	 * @return the rendimiento view
	 */
	private RendimientoView createRetornoView(RendimientoDTO dto) {
		RendimientoView outView = new RendimientoView();
		if (dto != null) {
			try {
				BeanUtils.copyProperties(outView, dto);
			} catch (Exception e) {
				LOGGER.error("Error creando View", e);
			}
		}
		return outView;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.tenenciaConsolidada.comun.manager.TenenciaConsolidadaManager#obtenerRendimientoBPriv()
	 */
	@Override
	public Respuesta<RendimientoBPrivView> obtenerRendimientoBPriv() {
		Respuesta<RendimientoBPrivDTO> rtaRendimiento = rendimientoBOImpl.obtenerRendimientoTenenciaBPriv(sesionCliente.getCliente(), null);
		String codigoEstadistica = EstadisticasConstants.RENDIMIENTO_TENENCIA_CONSOLIDADA_BP;
		RendimientoBPrivDTO rendimientoBPrivDTO = rtaRendimiento.getRespuesta();
		RendimientoBPrivView rendimientoBPrivView = createRetornoViewBPriv(rendimientoBPrivDTO);
		rendimientoBPrivView.setTooltip(obtenerMensajeRendimiento());

		if (EstadoRespuesta.OK.equals(rtaRendimiento.getEstadoRespuesta())) {
			estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(RendimientoBPrivView.class, rendimientoBPrivView);
		}
		if (EstadoRespuesta.WARNING.equals(rtaRendimiento.getEstadoRespuesta())) {
			estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR_PARCIAL);
			return respuestaFactory.crearRespuestaWarning(rendimientoBPrivView, "",TipoError.WARNING_PARCIAL_TENENCIA_CONSOLIDADA, "");
		}
		estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);

	}

	/**
	 * Creates the retorno view B priv.
	 *
	 * @param dto
	 *            the dto
	 * @return the rendimiento B priv view
	 */
	private RendimientoBPrivView createRetornoViewBPriv(RendimientoBPrivDTO dto) {
		RendimientoBPrivView outView = new RendimientoBPrivView();
		if (dto != null) {
			try {
				BeanUtils.copyProperties(outView, dto);
			} catch (Exception e) {
				LOGGER.error("Error creando View", e);
			}
		}
		return outView;
	}

}
