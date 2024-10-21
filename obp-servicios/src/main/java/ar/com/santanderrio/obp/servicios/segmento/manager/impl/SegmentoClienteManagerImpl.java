/*
 * 
 */
package ar.com.santanderrio.obp.servicios.segmento.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.segmento.bo.SegmentoClienteBO;
import ar.com.santanderrio.obp.servicios.segmento.manager.SegmentoClienteManager;

/**
 * The Class SegmentoClienteManagerImpl.
 */
@Component
public class SegmentoClienteManagerImpl implements SegmentoClienteManager {

	/** The segmento BO. */
	@Autowired
	private SegmentoClienteBO segmentoBO;
	
	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.segmento.manager.SegmentoClienteManager
	 * #obtenerSegmento()
	 */
	@Override
	public Respuesta<Segmento> obtenerSegmento(Cliente cliente) {
		Respuesta<Segmento> respuesta = segmentoBO.obtenerSegmento(cliente); 
		if (EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())) {
			respuesta = respuestaFactory.crearRespuestaOk(respuesta.getRespuesta());
			estadisticaManager.add(EstadisticasConstants.CONSULTA_SERVICIO_SEGMENTO_CLIENTE,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}else {
			estadisticaManager.add(EstadisticasConstants.CONSULTA_SERVICIO_SEGMENTO_CLIENTE,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}
		return respuesta;

	}

}
