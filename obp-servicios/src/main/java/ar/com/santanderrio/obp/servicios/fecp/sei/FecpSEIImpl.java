/*
 * 
 */
package ar.com.santanderrio.obp.servicios.fecp.sei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.fecp.manager.FecpManager;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.OfertaComercialView;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

/**
 * The Class FecpSEIImpl.
 */
@Component("fecpSEI")
public class FecpSEIImpl implements FecpSEI {

	/** The fecp manager. */
	@Autowired
	@Qualifier("fecpManager")
	private FecpManager fecpManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.fecp.sei.FecpSEI#obtenerTokenEncriptado
	 * ()
	 */
	@Override
	public Respuesta<TokenView> obtenerTokenEncriptado(OfertaComercialView oferta) {
		return fecpManager.obtenerTokenFecp(oferta);
	}

}
