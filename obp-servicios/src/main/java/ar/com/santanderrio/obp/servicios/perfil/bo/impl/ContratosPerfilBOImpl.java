/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.perfil.bo.ContratosPerfilBO;
import ar.com.santanderrio.obp.servicios.perfil.dao.ContratosPerfilDAO;
import ar.com.santanderrio.obp.servicios.perfil.entities.ContratosPerfil;

/**
 * The Class ContratosPerfilBOImpl.
 */
@Component
public class ContratosPerfilBOImpl implements ContratosPerfilBO {

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The contratos perfil DAO. */
	@Autowired
	private ContratosPerfilDAO contratosPerfilDAO;

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.perfil.bo.ContratosPerfilBO#consultaContratos()
	 */
	@Override
	public Respuesta<ContratosPerfil> consultaContratos() {
		return respuestaFactory.crearRespuestaOk(contratosPerfilDAO.consultarContratos());
	}

}
