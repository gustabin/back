package ar.com.santanderrio.obp.servicios.transferencias.sei.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.DestinatarioView;
import ar.com.santanderrio.obp.servicios.transferencias.sei.DestinatariosFrecuentesSEI;
import ar.com.santanderrio.obp.servicios.transferencias.web.manager.DestinatariosFrecuentesManager;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.DestinatariosFrecuentesView;

@Component
public class DestinatariosFrecuentesSEIImpl implements DestinatariosFrecuentesSEI {

	@Autowired
	private DestinatariosFrecuentesManager destinatariosFrecuentesManager;

	@Override
	public Respuesta<DestinatariosFrecuentesView> obtenerDestinatariosFrecuentes() {
		return destinatariosFrecuentesManager.obtenerDestinatariosFrecuentes();
	}

	@Override
	public Respuesta<String> altaFavorito(DestinatarioView destinatarioView) {
		return destinatariosFrecuentesManager.altaFavorito(destinatarioView);
	}

	@Override
	public Respuesta<String> bajaFavorito(DestinatarioView destinatarioView) {
		return destinatariosFrecuentesManager.bajaFavorito(destinatarioView);
	}

}
