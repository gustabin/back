package ar.com.santanderrio.obp.servicios.documentacion.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

public interface DocumentacionManager {

	Respuesta<TokenView> obtenerTokenDocumentacion();
	
}
