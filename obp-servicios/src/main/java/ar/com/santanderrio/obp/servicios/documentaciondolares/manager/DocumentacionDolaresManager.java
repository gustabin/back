package ar.com.santanderrio.obp.servicios.documentaciondolares.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

public interface DocumentacionDolaresManager {

	Respuesta<TokenView> obtenerTokenDocumentacionDolares();
	
}
