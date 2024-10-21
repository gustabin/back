package ar.com.santanderrio.obp.servicios.transferencias.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.DestinatarioView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.DestinatariosFrecuentesView;

public interface DestinatariosFrecuentesManager {

	public Respuesta<DestinatariosFrecuentesView> obtenerDestinatariosFrecuentes();

	public Respuesta<String> altaFavorito(DestinatarioView destinatario);

	public Respuesta<String> bajaFavorito(DestinatarioView destinatario);
}
