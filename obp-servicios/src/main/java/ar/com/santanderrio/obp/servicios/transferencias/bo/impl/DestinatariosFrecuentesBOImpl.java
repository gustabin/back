package ar.com.santanderrio.obp.servicios.transferencias.bo.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.AgendaDestinatarioBO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.AgendaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.DestinatarioAgendaDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoAgendaEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.DestinatarioView;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.DatoItemMensaje;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.transferencias.bo.DestinatariosFrecuentesBO;
import ar.com.santanderrio.obp.servicios.transferencias.dao.DestinatariosFrecuentesDAO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.DestinatariosFrecuentesDTO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.DestinatariosFrecuentesEntity;

@Component
public class DestinatariosFrecuentesBOImpl implements DestinatariosFrecuentesBO {

	@Autowired
	private DestinatariosFrecuentesDAO destFrecDAO;

	@Autowired
	private AgendaDestinatarioBO agendaBO;

	@Autowired
	private RespuestaFactory respuestaFactory;

	@Override
	public Respuesta<DestinatariosFrecuentesDTO> obtenerDestinatariosFrecuentes(Cliente cliente) {
		Respuesta<AgendaDestinatarioDTO> respuestaAgenda = agendaBO.obtenerAgendaDestinatarios(cliente, true);
		if (EstadoRespuesta.ERROR.equals(respuestaAgenda.getEstadoRespuesta())
				|| (respuestaAgenda.getRespuesta().isTieneErrorCuentasNoPropias()
						&& !respuestaAgenda.getRespuesta().isTieneErrorRellamado())
				|| respuestaAgenda.getRespuesta().isTieneErrorTimeOut()) {
			return obtenerRespuestaError(respuestaAgenda);
		} else if (respuestaAgenda.getRespuesta().getCantidadCuentasNoPropias() == 0) {
			return errorSinDestinatarios();
		}
		List<DestinatarioAgendaDTO> destinatariosOrdenados = quitarCuentasPropiasYOrdenar(respuestaAgenda);
		try {
			List<DestinatariosFrecuentesEntity> destFrec = destFrecDAO.obtenerDestinatariosFrecuentes(cliente.getNup());
			DestinatariosFrecuentesDTO destFrecDTO = obtenerDestinatariosFrecuentesDTO(destinatariosOrdenados,
					destFrec);
			if (respuestaAgenda.getRespuesta().isTieneErrorRellamado()) {
				return warningErrorRellamada(destFrecDTO);
			}
			return respuestaFactory.crearRespuestaOk(destFrecDTO);
		} catch (DAOException e) {
			if (respuestaAgenda.getRespuesta().isTieneErrorRellamado()) {
				return warningRellamadaYFavoritos(destinatariosOrdenados);
			}
			return warningFavoritos(destinatariosOrdenados);
		}
	}

	private DestinatariosFrecuentesDTO obtenerDestinatariosFrecuentesDTO(
			List<DestinatarioAgendaDTO> destinatariosOrdenados, List<DestinatariosFrecuentesEntity> destFrec) {
		List<DestinatarioAgendaDTO> destinatariosFavoritos = new ArrayList<DestinatarioAgendaDTO>();
		for (DestinatarioAgendaDTO destinatarioDTO : destinatariosOrdenados) {
			for (DestinatariosFrecuentesEntity favorito : destFrec) {
				if (coincidenDatosCuentaRio(destinatarioDTO, favorito)
						|| coincidenDatosOtrosBancos(destinatarioDTO, favorito)) {
					destinatarioDTO.setIdFavorito(String.valueOf(favorito.getId()));
					destinatariosFavoritos.add(destinatarioDTO);
					break;
				}
			}
		}
		List<DestinatarioAgendaDTO> destinatariosNoFavoritos = destinatariosOrdenados;
		destinatariosNoFavoritos.removeAll(destinatariosFavoritos);
		return new DestinatariosFrecuentesDTO(destinatariosFavoritos, destinatariosNoFavoritos);
	}

	private boolean coincidenDatosOtrosBancos(DestinatarioAgendaDTO destinatarioDTO,
			DestinatariosFrecuentesEntity favorito) {
		return TipoAgendaEnum.AGENDA_OTROS_BANCOS.equals(destinatarioDTO.getTipoAgendaEnum())
				&& StringUtils.equals(destinatarioDTO.getCbu(), favorito.getCbu());
	}

	private boolean coincidenDatosCuentaRio(DestinatarioAgendaDTO destinatarioDTO,
			DestinatariosFrecuentesEntity favorito) {
		if (TipoAgendaEnum.AGENDA_RIO.equals(destinatarioDTO.getTipoAgendaEnum())) {
			return StringUtils.equals(destinatarioDTO.getSucursalServicio(), favorito.getSucursal())
					&& StringUtils.equals(destinatarioDTO.getNroCuentaServicio(), favorito.getNroCuenta())
					&& StringUtils.equals(destinatarioDTO.getTipoCuentaServicio(), favorito.getTipoCuenta());
		}
		return false;
	}

	private List<DestinatarioAgendaDTO> quitarCuentasPropiasYOrdenar(Respuesta<AgendaDestinatarioDTO> respuestaAgenda) {
		List<DestinatarioAgendaDTO> destinatariosDTO = respuestaAgenda.getRespuesta().getListaDestinatarios();
		CollectionUtils.filter(destinatariosDTO, new Predicate() {
			@Override
			public boolean evaluate(Object object) {
				DestinatarioAgendaDTO destinatarioDTO = (DestinatarioAgendaDTO) object;
				return !destinatarioDTO.getEsCuentaPropia();
			}
		});
		return agendaBO.ordenarAlfabeticamente(destinatariosDTO);
	}

	private Respuesta<DestinatariosFrecuentesDTO> obtenerRespuestaError(
			Respuesta<AgendaDestinatarioDTO> respuestaAgenda) {
		if (TipoError.ERROR_SIN_AGENDADOS.getDescripcion()
				.equals(respuestaAgenda.getItemsMensajeRespuesta().get(0).getTipoError())) {
			return errorSinDestinatarios();
		}
		return errorServicioAgenda();
	}

	private Respuesta<DestinatariosFrecuentesDTO> errorServicioAgenda() {
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SERVICIO,
				CodigoMensajeConstantes.DESTINATARIOSFRECUENTES_ERROR_AGENDA);
	}

	private Respuesta<DestinatariosFrecuentesDTO> errorSinDestinatarios() {
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SIN_AGENDADOS,
				CodigoMensajeConstantes.DESTINATARIOSFRECUENTES_ERROR_SIN_DESTINATARIOS);
	}

	private Respuesta<DestinatariosFrecuentesDTO> warningErrorRellamada(DestinatariosFrecuentesDTO destFrecDTO) {
		return respuestaFactory.crearRespuestaWarning(destFrecDTO, "", TipoError.WARNING_ERROR_RELLAMADO,
				CodigoMensajeConstantes.DESTINATARIOSFRECUENTES_ERROR_RELLAMADA);
	}

	private Respuesta<DestinatariosFrecuentesDTO> warningFavoritos(List<DestinatarioAgendaDTO> destinatariosOrdenados) {
		return respuestaFactory.crearRespuestaWarning(new DestinatariosFrecuentesDTO(destinatariosOrdenados), "",
				TipoError.ERROR_OBTENER_FAVORITO, CodigoMensajeConstantes.DESTINATARIOSFRECUENTES_ERROR_BBDD_FAVORITOS);
	}

	private Respuesta<DestinatariosFrecuentesDTO> warningRellamadaYFavoritos(
			List<DestinatarioAgendaDTO> destinatariosOrdenados) {
		List<DatoItemMensaje> datoItems = new ArrayList<DatoItemMensaje>(Arrays.asList(
				new DatoItemMensaje(CodigoMensajeConstantes.DESTINATARIOSFRECUENTES_ERROR_RELLAMADA,
						TipoError.WARNING_ERROR_RELLAMADO, ""),
				new DatoItemMensaje(CodigoMensajeConstantes.DESTINATARIOSFRECUENTES_ERROR_BBDD_FAVORITOS,
						TipoError.ERROR_OBTENER_FAVORITO, "")));
		return respuestaFactory.crearRespuestaWarning(datoItems,
				new DestinatariosFrecuentesDTO(destinatariosOrdenados));
	}

	@Override
	public Respuesta<String> altaFavorito(DestinatarioView destinatario, Cliente cliente) {
		DestinatariosFrecuentesEntity destinatarioEntity = obtenerDestinatarioEntity(destinatario, cliente);
		try {
			String id = destFrecDAO.altaDestinatarioFavorito(destinatarioEntity);
			return respuestaFactory.crearRespuestaOk(id);
		} catch (DAOException e) {
			return errorAltaFavorito();
		}
	}

	private Respuesta<String> errorAltaFavorito() {
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SERVICIO,
				CodigoMensajeConstantes.DESTINATARIOSFRECUENTES_ERROR_ALTA_FAVORITO);
	}

	private DestinatariosFrecuentesEntity obtenerDestinatarioEntity(DestinatarioView destinatario, Cliente cliente) {
		DestinatariosFrecuentesEntity entity = new DestinatariosFrecuentesEntity();
		entity.setNup(cliente.getNup());
		if (StringUtils.isNotBlank(destinatario.getCbu())) {
			entity.setCbu(destinatario.getCbu());
		} else {
			entity.setTipoCuenta(destinatario.getTipoCuentaServicio());
			entity.setSucursal(destinatario.getSucursalServicio());
			entity.setNroCuenta(destinatario.getNroCuentaServicio());
		}
		return entity;
	}

	@Override
	public Respuesta<String> bajaFavorito(DestinatarioView destinatario) {
		try {
			Boolean bajaOk = destFrecDAO.bajaDestinatarioFavorito(destinatario.getIdFavorito());
			if (bajaOk) {
				return respuestaFactory.crearRespuestaOk(String.class);
			}
			return errorBajaFavorito();
		} catch (DAOException e) {
			return errorBajaFavorito();
		}
	}

	private Respuesta<String> errorBajaFavorito() {
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SERVICIO,
				CodigoMensajeConstantes.DESTINATARIOSFRECUENTES_ERROR_BAJA_FAVORITO);
	}

}
