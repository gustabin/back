package ar.com.santanderrio.obp.servicios.documentacion.manager;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

@Component
public class DocumentacionManagerImpl implements DocumentacionManager {

    @Autowired
    private DocumentacionTokenManagerImpl documentacionTokenManager;
	
    @Autowired
	EstadisticaManager estadisticaManager;
    
	@Override
	public Respuesta<TokenView> obtenerTokenDocumentacion() {
        Respuesta<TokenView> respuesta = documentacionTokenManager.obtenerTokenEncriptado();
        Respuesta<TokenView> respuestaView = Respuesta.copy(TokenView.class, respuesta);
        if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
            TokenView view = new TokenView();
            try {
				view.setTokenFirmado(URLEncoder.encode(respuesta.getRespuesta().getTokenFirmado(), "UTF-8"));
	            view.setUrl(respuesta.getRespuesta().getUrl());
	            respuestaView.setRespuesta(view);
			} catch (UnsupportedEncodingException e) {
				estadisticaManager.add(EstadisticasConstants.ENVIO_TOKEN_SOLICITUD_DOCUMENTACION, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				return respuestaView;
			}
        }
        estadisticaManager.add(EstadisticasConstants.ENVIO_TOKEN_SOLICITUD_DOCUMENTACION, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        return respuestaView;
	}

}
