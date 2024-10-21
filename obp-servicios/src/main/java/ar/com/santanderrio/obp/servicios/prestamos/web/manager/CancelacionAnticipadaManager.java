package ar.com.santanderrio.obp.servicios.prestamos.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.prestamos.view.CancelacionAnticipadaInView;
import ar.com.santanderrio.obp.servicios.prestamos.view.CancelacionAnticipadaOutView;

public interface CancelacionAnticipadaManager {

	Respuesta<CancelacionAnticipadaOutView> cancelarAnticipadamente(String numeroPrestamo,
			CancelacionAnticipadaInView cancelacionAnticipadaInView);

}