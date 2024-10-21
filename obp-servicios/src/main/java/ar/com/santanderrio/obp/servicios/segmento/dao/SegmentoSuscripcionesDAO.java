package ar.com.santanderrio.obp.servicios.segmento.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.segmento.dto.CajaSeguridadEntity;
import ar.com.santanderrio.obp.servicios.segmento.dto.SubscriptionRequest;
import ar.com.santanderrio.obp.servicios.segmento.dto.SubscriptionResponse;

public interface SegmentoSuscripcionesDAO {

	SubscriptionResponse consultarEstadoSuscripciones(SubscriptionRequest request) throws DAOException;
	
	CajaSeguridadEntity consultarCajasSeguridad(Cliente cliente) throws DAOException;	
	
}
