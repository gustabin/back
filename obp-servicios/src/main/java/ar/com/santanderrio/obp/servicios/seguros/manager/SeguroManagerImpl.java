/*
 * 
 */
package ar.com.santanderrio.obp.servicios.seguros.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.seguros.bo.SeguroToken;
import ar.com.santanderrio.obp.servicios.token.externos.AbstractTokenEntesExternosManager;
import ar.com.santanderrio.obp.servicios.token.externos.TokenExternoDTO;
import ar.com.santanderrio.obp.servicios.token.externos.TokenManager;
import ar.com.santanderrio.obp.servicios.token.externos.dto.TokenDTO;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

/**
 * Clase que arma la respuesta con la url de seguros.
 */
@Component("seguroManager")
public class SeguroManagerImpl extends AbstractTokenEntesExternosManager implements TokenManager {
    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    /** The seguro BO. */
    @Autowired
    private SeguroToken seguroToken;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;
    
    /** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.seguros.manager.SeguroManager#
     * obtenerTokenEncriptado()
     */
    @Override
    public Respuesta<TokenView> obtenerTokenEncriptado() {
    	estadisticaManager.add(EstadisticasConstants.SALTO_A_SEGUROS,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    	
        TokenExternoDTO tokenExternoDTO = new TokenExternoDTO(sesionCliente.getCliente());
        Respuesta<TokenView> rtaToken = this.armarRespuesta(null, seguroToken, tokenExternoDTO);
        
        if (EstadoRespuesta.ERROR.equals(rtaToken.getEstadoRespuesta())) {
            return rtaToken;
        }

        String tokenFirmado = rtaToken.getRespuesta().getTokenFirmado();

        Respuesta<TokenDTO> rtaLogin = seguroToken.obtenerUrlConexion(tokenFirmado, sesionCliente.getCliente());

        if (EstadoRespuesta.ERROR.equals(rtaLogin.getEstadoRespuesta())) {
            return respuestaFactory.crearRespuestaError(TokenView.class, "", "");
        }

        TokenView view = new TokenView();
        view.setTokenFirmado(rtaLogin.getRespuesta().getTokenFirmado());
        view.setUrl(rtaLogin.getRespuesta().getUrl());

        Respuesta<TokenView> respuestaView = Respuesta.copy(TokenView.class, rtaLogin);
        respuestaView.setRespuesta(view);
        return respuestaView;
    }
}
