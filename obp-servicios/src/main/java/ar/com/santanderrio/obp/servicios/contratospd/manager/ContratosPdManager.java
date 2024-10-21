package ar.com.santanderrio.obp.servicios.contratospd.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

public interface ContratosPdManager {

	Respuesta<TokenView> obtenerTokenContratos();
	
}
