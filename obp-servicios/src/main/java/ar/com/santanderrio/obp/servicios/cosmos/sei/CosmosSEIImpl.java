package ar.com.santanderrio.obp.servicios.cosmos.sei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cosmos.manager.CosmosManager;
import ar.com.santanderrio.obp.servicios.cosmos.web.view.CosmosInView;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

/**
 * The Class CosmosSEI.
 */
@Component("cosmosSEI")
public class CosmosSEIImpl implements CosmosSEI {

	/** The cosmos manager. */
	@Autowired
	@Qualifier("cosmosManager")
	private CosmosManager cosmosManager;


	/**
	 * Obtener token encriptado.
	 *
	 * @param inView the in view
	 * @return the respuesta
	 */
	@Override
	public Respuesta<TokenView> obtenerTokenEncriptado(CosmosInView inView) {		
		return cosmosManager.obtenerTokenCosmos(inView);
	}

}
