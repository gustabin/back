package ar.com.santanderrio.obp.servicios.documentacion.sei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.documentacion.manager.DocumentacionManager;
//import ar.com.santanderrio.obp.servicios.documentaciondolares.manager.DocumentacionDolaresManager;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

@Component
public class DocumentacionSEIImpl implements DocumentacionSEI{

	@Autowired
	DocumentacionManager documentacionManager;
	
	@Override
	public Respuesta<TokenView> obtenerTokenEncriptado() {
		return documentacionManager.obtenerTokenDocumentacion();
	}

}
