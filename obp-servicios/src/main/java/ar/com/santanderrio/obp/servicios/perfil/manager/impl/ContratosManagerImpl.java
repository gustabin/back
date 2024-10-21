/**
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.perfil.bo.ContratosPerfilBO;
import ar.com.santanderrio.obp.servicios.perfil.entities.ContratosPerfil;
import ar.com.santanderrio.obp.servicios.perfil.manager.ContratosManager;

/**
 * The Class ContratosManagerImpl.
 */
@Component
public class ContratosManagerImpl implements ContratosManager {

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The contratos perfil BO. */
	@Autowired
	private ContratosPerfilBO contratosPerfilBO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.perfil.manager.ContratosManager#
	 * consultaContratos()
	 */
	@Override
	public Respuesta<ContratosPerfil> consultaContratos() {
		Respuesta<ContratosPerfil> contratosPerfil = contratosPerfilBO.consultaContratos();
		contratosPerfil.getRespuesta().setSinProductos(sesionCliente.getCliente().getSinProductos());
		estadisticaManager.add(EstadisticasConstants.PERFIL_CONSULTA_CONTRATOS,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return contratosPerfil;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.perfil.manager.ContratosManager#
	 * estadisticaDescargaContrato(java.lang.Boolean)
	 */
	@Override
	public Respuesta<Void> estadisticaDescargaContrato(Boolean statContratoError) {
		estadisticaManager.add(EstadisticasConstants.PERFIL_DESCARGA_CONTRATOS, statContratoError
				? EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR : EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

}
