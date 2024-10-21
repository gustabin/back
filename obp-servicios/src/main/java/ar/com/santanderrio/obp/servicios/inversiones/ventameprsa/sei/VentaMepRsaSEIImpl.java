package ar.com.santanderrio.obp.servicios.inversiones.ventameprsa.sei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.inversiones.ventameprsa.entity.MepRsaNotification;
import ar.com.santanderrio.obp.servicios.inversiones.ventameprsa.entity.VentaMepRequest;
import ar.com.santanderrio.obp.servicios.inversiones.ventameprsa.manager.VentaMepRsaManager;

@Component("ventaMepRsaSEI")
public class VentaMepRsaSEIImpl implements VentaMepRsaSEI {
	@Autowired
	 private VentaMepRsaManager vtamepRsaMgr;

	@Override
	public Respuesta<MepRsaNotification> notificarVtaMepRsa(VentaMepRequest request) {
		System.out.println(request);
		Respuesta<MepRsaNotification> respuesta = vtamepRsaMgr.notificarVtaMepRsa(request);
		return respuesta;
	}

}