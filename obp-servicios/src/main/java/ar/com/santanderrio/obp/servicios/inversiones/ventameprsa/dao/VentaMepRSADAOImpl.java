package ar.com.santanderrio.obp.servicios.inversiones.ventameprsa.dao;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.inversiones.ventameprsa.entity.VentaMepRequest;

@Component
public class VentaMepRSADAOImpl implements VentaMepRSADAO{

	@Override
	public boolean notificarVtaMep(VentaMepRequest request) {
		return true;
	}

	
}
