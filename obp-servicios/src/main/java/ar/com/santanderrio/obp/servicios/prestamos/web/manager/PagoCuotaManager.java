package ar.com.santanderrio.obp.servicios.prestamos.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.prestamos.view.PagoCuotaInView;
import ar.com.santanderrio.obp.servicios.prestamos.view.PagoCuotaOutView;

public interface PagoCuotaManager {

	Respuesta<PagoCuotaOutView> pagar(String numeroPrestamo, PagoCuotaInView pagoCuotaInView);

}