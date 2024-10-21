/*
 * 
 */
package ar.com.santanderrio.obp.servicios.fecp.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.OfertaComercialView;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;


/**
 * The Class FecpManagerImpl.
 */
@Component("fecpManager")
public class FecpManagerImpl implements FecpManager {
    
    private static final String DEEPLINK = "deeplink";

	/** The fecp token manager. */
    @Autowired
    private FecpTokenManagerImpl fecpTokenManager;
    
    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /**
     * Obtener token fecp.
     *
     * @param oferta the oferta
     * @return the respuesta
     */
    @Override
    public Respuesta<TokenView> obtenerTokenFecp(OfertaComercialView oferta) {
    	
	 	String datosOfertaSC = "";
	 	
	 	if(DEEPLINK.equalsIgnoreCase(oferta.getId())) {
	 		// oferta de superclub segun producto y categoria
	 		if(oferta.getIdLoyalty() != null && !oferta.getIdLoyalty().isEmpty() 
	 				&& (oferta.getTipoProductoLoyalty() == null || oferta.getTipoProductoLoyalty().isEmpty())) // viene solo producto
	 			datosOfertaSC = oferta.getIdLoyalty();
	 		else if(oferta.getTipoProductoLoyalty() != null && !oferta.getTipoProductoLoyalty().isEmpty()) // viene solo categoria o ambos
	 			datosOfertaSC = (oferta.getIdLoyalty() != null ? oferta.getIdLoyalty() : "") + "," + oferta.getTipoProductoLoyalty();
	 		
	 	}else{
	 		// para oferta comercial
	 		datosOfertaSC = (oferta.getIdLoyalty() != null ? oferta.getIdLoyalty() +";" : "" ) + 
	 				(oferta.getTipoProductoLoyalty() != null ? oferta.getTipoProductoLoyalty() : "" );
	 	}
    	sesionParametros.setOperacionParaToken(datosOfertaSC);    	
    	
        Respuesta<TokenView> respuesta = fecpTokenManager.obtenerTokenEncriptado();
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
