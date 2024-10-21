/**
 * 
 */
package ar.com.santanderrio.obp.servicios.prelogin.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.login.encrypines.EncryPines;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.prelogin.web.view.PreLoginInView;
import ar.com.santanderrio.obp.servicios.prelogin.web.view.PreLoginView;
import ar.com.santanderrio.obp.servicios.queue.bo.QueueSTBO;
import ar.com.santanderrio.obp.servicios.queue.entities.QueueSTDTO;
import ar.com.santanderrio.obp.servicios.queue.entities.QueueSTOperations;
import ar.com.santanderrio.obp.servicios.queue.entities.TurnStatesEnum;
import ar.com.santanderrio.obp.servicios.queue.utils.QueueSTUtils;

/**
 * The Class PreLoginMangerImpl.
 *
 * @author sergio.e.goldentair
 */
@Component("preLoginManger")
public class PreLoginMangerImpl implements PreLoginManger {

    /**
     * Obtener los datos a devolver.
     */
    @Autowired
    private EncryPines encryPines;

    /** The ModuloPermiso BO. */
    @Autowired
    private ModuloPermisoBO moduloPermisoBO;

    /** The queue ST BO. */
    @Autowired
    private QueueSTBO queueSTBO;

    /**
     * RespuestaFactory para devolver al front.
     */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.prelogin.manager.PreLoginManger#
     * obtenerEncPub()
     */
    @Override
    public Respuesta<String> obtenerEncPub() {
        String encPub = encryPines.obtenerEncPub();
        return respuestaFactory.crearRespuestaOk(encPub);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.prelogin.manager.PreLoginManger#
     * preLogin(java.lang.String)
     */
    @Override
    public Respuesta<PreLoginView> preLogin(String datoEntrada) {
        if (moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.QUEUE_ST_LOGIN).tienePermisoDeVisibilidad()
				&& QueueSTUtils.verificarHorarioQueue(QueueSTOperations.LOGIN)) {
        	String cryptoInput = encryPines.obtenerSoloCrypto(datoEntrada);
        	PreLoginInView preLoginInView = encryPines.obtenerViewFromJson(cryptoInput,
        			PreLoginInView.class);
        	Respuesta<QueueSTDTO> respuestaTurno = queueSTBO.crearTurno(preLoginInView.getDniOri());			
        	if (EstadoRespuesta.OK.equals(respuestaTurno.getEstadoRespuesta())) {
        		QueueSTDTO queueSTDTO = respuestaTurno.getRespuesta();
        		if (TurnStatesEnum.TURN_STATUS_READY.getCodigo().equals(queueSTDTO.getTurn().getStatus())) {
        			PreLoginView view = new PreLoginView(queueSTDTO.getTurn().getId(), "0", "0");
            		return respuestaFactory.crearRespuestaWarning(PreLoginView.class, view, null);
        		}
				boolean noWait = moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.QUEUE_ST_LOGIN_NO_WAIT)
						.tienePermisoDeVisibilidad();
        		String remainingTime = noWait ? "0" : queueSTDTO.getTurn().getRemainingTime();
        		String count = noWait ? "0" : queueSTDTO.getCount();
				PreLoginView view = new PreLoginView(queueSTDTO.getTurn().getId(), remainingTime, count);
        		return respuestaFactory.crearRespuestaWarning(PreLoginView.class, view, null);
        	}
        }
        return respuestaFactory.crearRespuestaOk(new PreLoginView());
    }

}
