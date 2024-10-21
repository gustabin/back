package ar.com.santanderrio.obp.servicios.inversiones.ventameprsa.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.inversiones.ventameprsa.entity.MepRsaNotification;
import ar.com.santanderrio.obp.servicios.inversiones.ventameprsa.entity.VentaMepRequest;

public interface VentaMepRsaManager {
	
	Respuesta<MepRsaNotification> notificarVtaMepRsa(VentaMepRequest request);
	
}
