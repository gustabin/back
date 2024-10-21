/*
 * 
 */
package ar.com.santanderrio.obp.servicios.fecp.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.fecp.bo.FecpToken;
import ar.com.santanderrio.obp.servicios.token.externos.TokenManager;
import ar.com.santanderrio.obp.servicios.token.externos.dto.TokenDTO;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;


/**
 * The Class FecpTokenManagerImpl.
 */
@Component("fecpTokenManager")
public class FecpTokenManagerImpl implements TokenManager {

    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    /** The fecp BO. */
    @Autowired
    private FecpToken fecpBO;

    /**
     * Obtener token encriptado.
     *
     * @return the respuesta
     */
    @Override
    public Respuesta<TokenView> obtenerTokenEncriptado() {
        Respuesta<TokenDTO> respuesta = fecpBO.obtenerHash(sesionCliente);
        Respuesta<TokenView> respuestaView = Respuesta.copy(TokenView.class, respuesta);
        if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
            TokenView view = new TokenView();
            view.setTokenFirmado(respuesta.getRespuesta().getTokenFirmado());
            view.setUrl(respuesta.getRespuesta().getUrl());

            respuestaView.setRespuesta(view);

        }
        return respuestaView;
    }

}
