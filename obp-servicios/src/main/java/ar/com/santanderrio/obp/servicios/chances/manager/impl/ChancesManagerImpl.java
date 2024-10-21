package ar.com.santanderrio.obp.servicios.chances.manager.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.chances.bo.ChancesBO;
import ar.com.santanderrio.obp.servicios.chances.manager.ChancesManager;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.ChancesDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.ChanceView;

/**
 * The Class ChancesManager.
 */
@Component
public class ChancesManagerImpl implements ChancesManager{

	/** The Chances BO. */
    @Autowired
	private ChancesBO chancesBO;
    
    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;
    
    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;
    
    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The Constant MOBILE *. */
    private static final String MOBILE = "MOBILE";
    
    /** The Constant CERO *. */
    private static final String DESKTOP = "DESKTOP";
    
	/**
	 * Obtiene chances segun el mes seleccionado.
	 *
	 * @param ChanceView
	 *            the ChanceView
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ChanceView> obtenerChancesMes(ChanceView chanceView) {
		Cliente cliente = sesionCliente.getCliente();
 
		if(cliente == null || cliente.getNup().isEmpty() || StringUtils.isBlank(chanceView.getMesSeleccionado())) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
		}
		
		String dispositivo = sesionParametros.getRegistroSession().isMobile() ? MOBILE :DESKTOP;
		Respuesta<ChancesDTO> respuestaDTO =  chancesBO.obtenerChancesMes(dispositivo, cliente, chanceView.getMesSeleccionado());
		
		if(EstadoRespuesta.ERROR.equals(respuestaDTO.getEstadoRespuesta())) {
			return respuestaFactory.transformar(null, respuestaDTO);
		}
		return respuestaFactory.crearRespuestaOk(new ChanceView(respuestaDTO.getRespuesta(), chanceView.getHistorial()));
	}

}
