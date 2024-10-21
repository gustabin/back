package ar.com.santanderrio.obp.servicios.convenioempleados.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

public interface ConvenioEmpleadosManager {

	Respuesta<TokenView> obtenerTokenConvenioEmpleados();
	
}
