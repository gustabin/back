/*
 * 
 */
package ar.com.santanderrio.obp.servicios.token.mobile.bo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.token.mobile.dao.TokenMobileDAO;
import ar.com.santanderrio.obp.servicios.token.mobile.entities.TokenMobile;
import ar.com.santanderrio.obp.servicios.token.mobile.entities.TokenMobile.TokenMobileBuilder;

/**
 * The Class TokenMobileBOImpl.
 */
@Component
public class TokenMobileBOImpl implements TokenMobileBO {

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The token mobile DAO. */
	@Autowired
	private TokenMobileDAO tokenMobileDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.token.mobile.bo.TokenMobileBO#
	 * consultarTokenMobile(java.lang.String)
	 */
	@Override
	public Respuesta<TokenMobile> consultarTokenMobile(String nup) {
		try {
			return respuestaFactory.crearRespuestaOk(TokenMobile.class, tokenMobileDAO.consultarTokenMobile(nup));
		} catch (EmptyResultDataAccessException e) {
			return respuestaFactory.crearRespuestaWarning((new TokenMobileBuilder()).build(),
					new ArrayList<ItemMensajeRespuesta>());
		} catch (DAOException e) {
			return respuestaFactory.crearRespuestaError(TokenMobile.class, new ArrayList<ItemMensajeRespuesta>());
		}
	}

}
