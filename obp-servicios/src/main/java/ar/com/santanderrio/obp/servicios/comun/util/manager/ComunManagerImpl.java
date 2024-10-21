/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.util.manager;

import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.util.view.MicroFEAccessView;
import ar.com.santanderrio.obp.servicios.comun.view.FechaView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class ComunManagerImpl.
 */
@Component("comunManager")
public class ComunManagerImpl implements ComunManager {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComunManagerImpl.class);

	/** The Constant LEN_MAX. */
	private static final int LEN_MAX = 100;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	@Autowired
	private RespuestaFactory respuestaFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.util.manager.ComunManager#
	 * getFechaActual()
	 */
	@Override
	public FechaView getFechaActual() {

		Calendar current = Calendar.getInstance();

		FechaView fechaView = new FechaView();
		fechaView.setDay(current.get(Calendar.DAY_OF_MONTH));
		fechaView.setMonth(current.get(Calendar.MONTH) + 1);
		fechaView.setYear(current.get(Calendar.YEAR));
		return fechaView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.util.manager.ComunManager#
	 * cancelarDesafioEnCurso()
	 */
	@Override
	public void cancelarDesafioEnCurso() {
		sesionParametros.setDesafioEnCurso(null);
		sesionParametros.setExisteDesafioEnCurso(Boolean.FALSE);
		sesionParametros.setDesafioPrestamosActivo(false);
	}

	@Override
	public Respuesta<Void> grabarEstadisticaVisualizacionResumenTyC() {
		estadisticaManager.add(EstadisticasConstants.VISUALIZACION_RESUMEN_TYC,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return this.respuestaFactory.crearRespuestaOk(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.util.manager.ComunManager#
	 * accesoMF(ar.com.santanderrio.obp.servicios.comun.util.view.MicroFEAccessView)
	 */
	@Override
	public Respuesta<Void> accesoMF(MicroFEAccessView microFEAccessView) {
		String action = StringUtils.substring(microFEAccessView.getAction(), 0, LEN_MAX);
		String path = StringUtils.substring(microFEAccessView.getPath(), 0, LEN_MAX);
		LOGGER.info("Accediendo a microfront {} desde action {}", path, action);
		estadisticaManager.add(EstadisticasConstants.ACCESO_MICROFRONT, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return new Respuesta<Void>();
	}

}
