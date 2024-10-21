/*
 * 
 */
package ar.com.santanderrio.obp.servicios.token.externos;

import org.apache.cxf.jaxrs.ext.MessageContext;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.token.externos.dto.TokenDTO;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

/**
 * Clase abstracta que tiene la logica para armr la respuesta para enviar al
 * front end.
 */
public abstract class AbstractTokenEntesExternosManager {

    /**
	 * Arma la respuesta que se envia al front end.
	 *
	 * @param mc
	 *            the mc
	 * @param tokenEntesExternosBuilder
	 *            the token entes externos builder
	 * @param tokenExternoDTO
	 *            the token externo DTO
	 * @return the respuesta
	 */
    public Respuesta<TokenView> armarRespuesta(MessageContext mc, AbstractTokenEntesExternos tokenEntesExternosBuilder,
            TokenExternoDTO tokenExternoDTO) {
        Respuesta<TokenDTO> respuesta = tokenEntesExternosBuilder.armarToken(mc, tokenExternoDTO);

        TokenView view = new TokenView();
        view.setTokenFirmado(respuesta.getRespuesta().getTokenFirmado());
        view.setUrl(respuesta.getRespuesta().getUrl());

        Respuesta<TokenView> respuestaView = Respuesta.copy(TokenView.class, respuesta);

        respuestaView.setRespuesta(view);

        return respuestaView;
    }

}
