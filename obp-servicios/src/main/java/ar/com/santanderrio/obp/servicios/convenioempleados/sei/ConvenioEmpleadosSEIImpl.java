package ar.com.santanderrio.obp.servicios.convenioempleados.sei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.convenioempleados.manager.ConvenioEmpleadosManager;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

@Component
public class ConvenioEmpleadosSEIImpl implements ConvenioEmpleadosSEI{

	@Autowired
	ConvenioEmpleadosManager convenioEmpleadosManager;
	
	@Override
	public Respuesta<TokenView> obtenerTokenEncriptado() {
		return convenioEmpleadosManager.obtenerTokenConvenioEmpleados();
	}

}
