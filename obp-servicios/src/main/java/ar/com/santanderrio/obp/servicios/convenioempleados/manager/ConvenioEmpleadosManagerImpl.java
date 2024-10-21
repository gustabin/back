package ar.com.santanderrio.obp.servicios.convenioempleados.manager;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

@Component
public class ConvenioEmpleadosManagerImpl implements ConvenioEmpleadosManager {

    @Autowired
    private ConvenioEmpleadosTokenManagerImpl convenioEmpleadosTokenManager;
	
	@Override
	public Respuesta<TokenView> obtenerTokenConvenioEmpleados() {
        Respuesta<TokenView> respuesta = convenioEmpleadosTokenManager.obtenerTokenEncriptado();
        Respuesta<TokenView> respuestaView = Respuesta.copy(TokenView.class, respuesta);
        if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
            TokenView view = new TokenView();
            try {
				view.setTokenFirmado(URLEncoder.encode(respuesta.getRespuesta().getTokenFirmado(), "UTF-8"));
	            view.setUrl(respuesta.getRespuesta().getUrl());
	            respuestaView.setRespuesta(view);
			} catch (UnsupportedEncodingException e) {
				return respuestaView;
			}
        }
        return respuestaView;
	}

}
