/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cosmos.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cosmos.entitiy.TipoOpeCosmosEnum;
import ar.com.santanderrio.obp.servicios.cosmos.web.view.CosmosInView;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

@Component("cosmosManager")
public class CosmosManagerImpl implements CosmosManager {

    /** The cosmos BO. */
    @Autowired
    private CosmosTokenManager cosmosTokenManager;
    
    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;


	@Override
	public Respuesta<TokenView> obtenerTokenCosmos(CosmosInView inView) {
	 	String tipReclamo = inView.getTipoReclamo() != null ? inView.getTipoReclamo() : "";
    	if (!"".contentEquals(tipReclamo) && TipoOpeCosmosEnum.DEVOLUCION_PAGO.getDescripcion().equals(tipReclamo)) {
    		sesionParametros.setOperacionParaToken(TipoOpeCosmosEnum.DEVOLUCION_PAGO.getId());    	
    	} else if (TipoOpeCosmosEnum.PROBLEMAS_EXTRACCION.getDescripcion().equals(tipReclamo)) {
    		sesionParametros.setOperacionParaToken(TipoOpeCosmosEnum.PROBLEMAS_EXTRACCION.getId());    	
    	} else if (TipoOpeCosmosEnum.PROBLEMAS_DEPOSITO_CUENTA.getDescripcion().equals(tipReclamo)) {
    		sesionParametros.setOperacionParaToken(TipoOpeCosmosEnum.PROBLEMAS_DEPOSITO_CUENTA.getId());    	
    	} else if (TipoOpeCosmosEnum.PROBLEMAS_DEPOSITO_TARJETA.getDescripcion().equals(tipReclamo)) {
    		sesionParametros.setOperacionParaToken(TipoOpeCosmosEnum.PROBLEMAS_DEPOSITO_TARJETA.getId());    	
    	}   	    

    	
    	Respuesta<TokenView> respuesta = cosmosTokenManager.obtenerTokenEncriptado();
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
