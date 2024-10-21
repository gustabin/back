package ar.com.santanderrio.obp.servicios.inversiones.ventameprsa.bo;

import ar.com.santanderrio.obp.servicios.inversiones.ventameprsa.entity.MepRsaNotification;
import ar.com.santanderrio.obp.servicios.inversiones.ventameprsa.entity.VentaMepRequest;

public interface VentaMepRsaBO {
	
	MepRsaNotification notificarVtaMep(VentaMepRequest request);
	

}
