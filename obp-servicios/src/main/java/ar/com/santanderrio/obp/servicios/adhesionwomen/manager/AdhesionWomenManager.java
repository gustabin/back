package ar.com.santanderrio.obp.servicios.adhesionwomen.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.solicitudes.view.AdhesionWomenView;
import ar.com.santanderrio.obp.servicios.solicitudes.view.ConfirmacionBajaAdhesionView;

public interface AdhesionWomenManager {

	AdhesionWomenView recuperarTarjetasAdhesionWomen();
	
	Respuesta<AdhesionWomenView> inicioAdhesionWomen();
	
	Respuesta<AdhesionWomenView> configuracionAdhesionWomen();
	
	Respuesta<AdhesionWomenView> confirmacionAdhesionWomen();

	Respuesta<AdhesionWomenView> ejecutarAdhesionWomen(AdhesionWomenView tarjetasParaHabilitar);
	
	Respuesta<AdhesionWomenView> ejecutarBajaAdhesionWomen(AdhesionWomenView tarjetasParaHabilitadas);
	
	Respuesta<ConfirmacionBajaAdhesionView> confirmacionBajaAdhesionWomen();
}
