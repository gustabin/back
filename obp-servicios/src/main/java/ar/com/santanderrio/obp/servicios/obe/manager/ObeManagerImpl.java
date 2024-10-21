/*
 * 
 */
package ar.com.santanderrio.obp.servicios.obe.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.login.manager.LoginManager;
import ar.com.santanderrio.obp.servicios.login.view.LogOutResponseView;
import ar.com.santanderrio.obp.servicios.obe.bo.ObeToken;
import ar.com.santanderrio.obp.servicios.token.externos.AbstractTokenEntesExternosManager;
import ar.com.santanderrio.obp.servicios.token.externos.TokenExternoDTO;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

/**
 * Clase que genera la respuesta conteniendo la url para la conexion de obe.
 */
@Component("obeManager")
public class ObeManagerImpl extends AbstractTokenEntesExternosManager implements ObeManager {

    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    /** The obe token. */
    @Autowired
    private ObeToken obeToken;

    /** The login manager. */
    @Autowired
    private LoginManager loginManager;
    
    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;


    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.token.externos.TokenManager#
     * obtenerTokenEncriptado()
     */
    @SuppressWarnings("rawtypes")
    @Override
    public Respuesta obtenerTokenEncriptado(org.apache.cxf.jaxrs.ext.MessageContext mc) {
        TokenExternoDTO tokenExternoDTO = new TokenExternoDTO(sesionCliente.getCliente());
        Respuesta<TokenView> respuestaToken = this.armarRespuesta(mc, obeToken, tokenExternoDTO);

        if (EstadoRespuesta.ERROR.equals(respuestaToken.getEstadoRespuesta())) {
            return respuestaToken;
        }

        Respuesta<LogOutResponseView> respuestaLogout = loginManager.logout(mc.getHttpServletRequest());
        if (EstadoRespuesta.ERROR.equals(respuestaLogout.getEstadoRespuesta())) {
            estadisticaManager.add(EstadisticasConstants.SALTO_TBANCO_OBE,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            return respuestaLogout;
        }
        estadisticaManager.add(EstadisticasConstants.SALTO_TBANCO_OBE,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

        return respuestaToken;
    }

}
