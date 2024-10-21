package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.PoliticasPrivacidadDTO;

public interface AceptarCompartirDatosDAO {

	String grabarDecisionCliente(PoliticasPrivacidadDTO politicasPrivacidadDTO) throws DAOException;
	
	
}
