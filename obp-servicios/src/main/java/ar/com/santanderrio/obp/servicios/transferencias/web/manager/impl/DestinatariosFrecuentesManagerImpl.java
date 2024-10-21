package ar.com.santanderrio.obp.servicios.transferencias.web.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.DestinatarioAgendaDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.DestinatarioView;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.transferencias.bo.DestinatariosFrecuentesBO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.DestinatariosFrecuentesDTO;
import ar.com.santanderrio.obp.servicios.transferencias.web.manager.DestinatariosFrecuentesManager;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.DestinatariosFrecuentesView;

@Component
public class DestinatariosFrecuentesManagerImpl implements DestinatariosFrecuentesManager {

	@Autowired
	private DestinatariosFrecuentesBO destFrecBO;

	@Autowired
	private SesionCliente sesionCliente;

	@Autowired
	private RespuestaFactory respuestaFactory;

	@Autowired
	private MensajeBO mensajeBO;

	@Override
	public Respuesta<DestinatariosFrecuentesView> obtenerDestinatariosFrecuentes() {
		Respuesta<DestinatariosFrecuentesDTO> respuestaBO = destFrecBO
				.obtenerDestinatariosFrecuentes(sesionCliente.getCliente());
		if (EstadoRespuesta.ERROR.equals(respuestaBO.getEstadoRespuesta())) {
			return respuestaFactory.crearRespuestaError(DestinatariosFrecuentesView.class,
					respuestaBO.getItemsMensajeRespuesta());
		}
		DestinatariosFrecuentesView destinatariosFrecuentesView = obtenerDestinatariosFrecuentesView(
				respuestaBO.getRespuesta());
		if (EstadoRespuesta.WARNING.equals(respuestaBO.getEstadoRespuesta())) {
			return respuestaFactory.crearRespuestaWarning(destinatariosFrecuentesView,
					respuestaBO.getItemsMensajeRespuesta());
		}
		return respuestaFactory.crearRespuestaOk(destinatariosFrecuentesView);
	}

	private DestinatariosFrecuentesView obtenerDestinatariosFrecuentesView(
			DestinatariosFrecuentesDTO destinatariosFrecuentesDTO) {
		DestinatariosFrecuentesView destinatariosFrecuentesView = new DestinatariosFrecuentesView();
		List<DestinatarioView> destinatariosFavoritosView = new ArrayList<DestinatarioView>();
		List<DestinatarioView> destinatariosNoFavoritosView = new ArrayList<DestinatarioView>();
		for (DestinatarioAgendaDTO destinatarioDTO : destinatariosFrecuentesDTO.getDestinatariosFavoritos()) {
			destinatariosFavoritosView.add(new DestinatarioView(destinatarioDTO));
		}
		for (DestinatarioAgendaDTO destinatarioDTO : destinatariosFrecuentesDTO.getDestinatariosNoFavoritos()) {
			destinatariosNoFavoritosView.add(new DestinatarioView(destinatarioDTO));
		}
		destinatariosFrecuentesView.setDestinatariosFavoritos(destinatariosFavoritosView);
		destinatariosFrecuentesView.setDestinatariosNoFavoritos(destinatariosNoFavoritosView);
		destinatariosFrecuentesView.setMensajeAltaFavorito(
				mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.DESTINATARIOSFRECUENTES_MENSAJE_ALTA_FAVORITO)
						.getMensaje());
		destinatariosFrecuentesView.setMensajeBajaFavorito(
				mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.DESTINATARIOSFRECUENTES_MENSAJE_BAJA_FAVORITO)
						.getMensaje());
		return destinatariosFrecuentesView;
	}

	@Override
	public Respuesta<String> altaFavorito(DestinatarioView destinatario) {
		return destFrecBO.altaFavorito(destinatario, sesionCliente.getCliente());
	}

	@Override
	public Respuesta<String> bajaFavorito(DestinatarioView destinatario) {
		return destFrecBO.bajaFavorito(destinatario);
	}

}
