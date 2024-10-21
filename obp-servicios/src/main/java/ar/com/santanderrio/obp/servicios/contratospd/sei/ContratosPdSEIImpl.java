package ar.com.santanderrio.obp.servicios.contratospd.sei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.contratospd.manager.ContratosPdManager;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

@Component
public class ContratosPdSEIImpl implements ContratosPdSEI{

	@Autowired
	ContratosPdManager contratosManager;
	
	@Override
	public Respuesta<TokenView> obtenerTokenEncriptado() {
		return contratosManager.obtenerTokenContratos();
	}

}
