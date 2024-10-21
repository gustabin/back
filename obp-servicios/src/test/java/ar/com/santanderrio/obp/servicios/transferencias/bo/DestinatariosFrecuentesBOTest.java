package ar.com.santanderrio.obp.servicios.transferencias.bo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.AgendaDestinatarioBO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.AgendaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.DestinatarioAgendaDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoAgendaEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.DestinatarioView;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.transferencias.bo.impl.DestinatariosFrecuentesBOImpl;
import ar.com.santanderrio.obp.servicios.transferencias.dao.DestinatariosFrecuentesDAO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.DestinatariosFrecuentesDTO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.DestinatariosFrecuentesEntity;

@RunWith(MockitoJUnitRunner.class)
public class DestinatariosFrecuentesBOTest {

	@InjectMocks
	private DestinatariosFrecuentesBOImpl destFrecuentesBO = new DestinatariosFrecuentesBOImpl();

	@Mock
	private DestinatariosFrecuentesDAO destFrecuentesDAO;

	@Mock
	private AgendaDestinatarioBO agendaBO;

	@InjectMocks
	@Spy
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	@Mock
	private MensajeBO mensajeBO;

	@Test
	public void obtenerDestinatariosFrecuentesOk() throws DAOException {
		Cliente cliente = new Cliente();
		cliente.setNup("0123456");
		when(agendaBO.obtenerAgendaDestinatarios(cliente, true)).thenReturn(respuestaAgendaOK());
		when(destFrecuentesDAO.obtenerDestinatariosFrecuentes(cliente.getNup()))
				.thenReturn(new ArrayList<DestinatariosFrecuentesEntity>());
		Respuesta<DestinatariosFrecuentesDTO> respuesta = destFrecuentesBO.obtenerDestinatariosFrecuentes(cliente);
		assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}

	@Test
	public void obtenerDestinatariosFrecuentesErrorSinDestinatarios() throws DAOException {
		Cliente cliente = new Cliente();
		cliente.setNup("0123456");
		when(agendaBO.obtenerAgendaDestinatarios(cliente, true)).thenReturn(respuestaAgendaSinDestinatarios());
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.DESTINATARIOSFRECUENTES_ERROR_SIN_DESTINATARIOS))
				.thenReturn(new Mensaje());
		Respuesta<DestinatariosFrecuentesDTO> respuesta = destFrecuentesBO.obtenerDestinatariosFrecuentes(cliente);
		assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
		assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
				TipoError.ERROR_SIN_AGENDADOS.getDescripcion());
	}

	@Test
	public void obtenerDestinatariosFrecuentesErrorServicioAgenda() {
		Cliente cliente = new Cliente();
		cliente.setNup("0123456");
		when(agendaBO.obtenerAgendaDestinatarios(cliente, true)).thenReturn(respuestaAgendaErrorServicio());
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.DESTINATARIOSFRECUENTES_ERROR_AGENDA))
				.thenReturn(new Mensaje());
		Respuesta<DestinatariosFrecuentesDTO> respuesta = destFrecuentesBO.obtenerDestinatariosFrecuentes(cliente);
		assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
		assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
				TipoError.ERROR_SERVICIO.getDescripcion());
	}

	@Test
	public void obtenerDestinatariosFrecuentesWarningRellamada() throws DAOException {
		Cliente cliente = new Cliente();
		cliente.setNup("0123456");
		when(agendaBO.obtenerAgendaDestinatarios(cliente, true)).thenReturn(respuestaAgendaErrorRellamada());
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.DESTINATARIOSFRECUENTES_ERROR_RELLAMADA))
				.thenReturn(new Mensaje());
		when(destFrecuentesDAO.obtenerDestinatariosFrecuentes(cliente.getNup()))
				.thenReturn(new ArrayList<DestinatariosFrecuentesEntity>());
		Respuesta<DestinatariosFrecuentesDTO> respuesta = destFrecuentesBO.obtenerDestinatariosFrecuentes(cliente);
		assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
		assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
				TipoError.WARNING_ERROR_RELLAMADO.getDescripcion());
	}

	@Test
	public void obtenerDestinatariosFrecuentesWarningFavoritos() throws DAOException {
		Cliente cliente = new Cliente();
		cliente.setNup("0123456");
		when(agendaBO.obtenerAgendaDestinatarios(cliente, true)).thenReturn(respuestaAgendaOK());
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.DESTINATARIOSFRECUENTES_ERROR_BBDD_FAVORITOS))
				.thenReturn(new Mensaje());
		when(destFrecuentesDAO.obtenerDestinatariosFrecuentes(cliente.getNup())).thenThrow(new DAOException());
		Respuesta<DestinatariosFrecuentesDTO> respuesta = destFrecuentesBO.obtenerDestinatariosFrecuentes(cliente);
		assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
		assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
				TipoError.ERROR_OBTENER_FAVORITO.getDescripcion());
	}

	@Test
	public void obtenerDestinatariosFrecuentesWarningRellamadoYFavoritos() throws DAOException {
		Cliente cliente = new Cliente();
		cliente.setNup("0123456");
		when(agendaBO.obtenerAgendaDestinatarios(cliente, true)).thenReturn(respuestaAgendaErrorRellamada());
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.DESTINATARIOSFRECUENTES_ERROR_BBDD_FAVORITOS))
				.thenReturn(new Mensaje());
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.DESTINATARIOSFRECUENTES_ERROR_RELLAMADA))
				.thenReturn(new Mensaje());
		when(destFrecuentesDAO.obtenerDestinatariosFrecuentes(cliente.getNup())).thenThrow(new DAOException());
		Respuesta<DestinatariosFrecuentesDTO> respuesta = destFrecuentesBO.obtenerDestinatariosFrecuentes(cliente);
		assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
		assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
				TipoError.WARNING_ERROR_RELLAMADO.getDescripcion());
		assertEquals(respuesta.getItemsMensajeRespuesta().get(1).getTipoError(),
				TipoError.ERROR_OBTENER_FAVORITO.getDescripcion());
	}

	@Test
	public void altaFavoritoOK() throws DAOException {
		Cliente cliente = new Cliente();
		cliente.setNup("0123456");
		when(destFrecuentesDAO.altaDestinatarioFavorito(Matchers.any(DestinatariosFrecuentesEntity.class)))
				.thenReturn("1");
		Respuesta<String> respuesta = destFrecuentesBO.altaFavorito(obtenerDestinatarioView(), cliente);
		assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}

	@Test
	public void altaFavoritoError() throws DAOException {
		Cliente cliente = new Cliente();
		cliente.setNup("0123456");
		when(destFrecuentesDAO.altaDestinatarioFavorito(Matchers.any(DestinatariosFrecuentesEntity.class)))
				.thenThrow(new DAOException());
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.DESTINATARIOSFRECUENTES_ERROR_ALTA_FAVORITO))
				.thenReturn(new Mensaje());
		Respuesta<String> respuesta = destFrecuentesBO.altaFavorito(obtenerDestinatarioView(), cliente);
		assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
		assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
				TipoError.ERROR_SERVICIO.getDescripcion());
	}

	@Test
	public void bajaFavoritoOK() throws DAOException {
		Cliente cliente = new Cliente();
		cliente.setNup("0123456");
		when(destFrecuentesDAO.bajaDestinatarioFavorito(Matchers.anyString())).thenReturn(true);
		Respuesta<String> respuesta = destFrecuentesBO.bajaFavorito(obtenerDestinatarioView());
		assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}

	@Test
	public void bajaFavoritoError() throws DAOException {
		Cliente cliente = new Cliente();
		cliente.setNup("0123456");
		when(destFrecuentesDAO.bajaDestinatarioFavorito(Matchers.anyString())).thenThrow(new DAOException());
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.DESTINATARIOSFRECUENTES_ERROR_BAJA_FAVORITO))
				.thenReturn(new Mensaje());
		Respuesta<String> respuesta = destFrecuentesBO.bajaFavorito(obtenerDestinatarioView());
		assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
		assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
				TipoError.ERROR_SERVICIO.getDescripcion());
	}

	private Respuesta<AgendaDestinatarioDTO> respuestaAgendaOK() {
		AgendaDestinatarioDTO agendaDTO = new AgendaDestinatarioDTO();
		agendaDTO.setCantidadCuentasNoPropias(2);
		agendaDTO.setListaDestinatarios(crearListaDestinatarios());
		agendaDTO.setTieneErrorRellamado(false);
		Respuesta<AgendaDestinatarioDTO> respuesta = new Respuesta<AgendaDestinatarioDTO>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuesta(agendaDTO);
		return respuesta;
	}

	private Respuesta<AgendaDestinatarioDTO> respuestaAgendaErrorRellamada() {
		AgendaDestinatarioDTO agendaDTO = new AgendaDestinatarioDTO();
		agendaDTO.setCantidadCuentasNoPropias(2);
		agendaDTO.setListaDestinatarios(crearListaDestinatarios());
		agendaDTO.setTieneErrorRellamado(true);
		Respuesta<AgendaDestinatarioDTO> respuesta = new Respuesta<AgendaDestinatarioDTO>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuesta(agendaDTO);
		return respuesta;
	}

	private Respuesta<AgendaDestinatarioDTO> respuestaAgendaSinDestinatarios() {
		Respuesta<AgendaDestinatarioDTO> respuesta = new Respuesta<AgendaDestinatarioDTO>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		ItemMensajeRespuesta item = new ItemMensajeRespuesta();
		item.setTipoError(TipoError.ERROR_SIN_AGENDADOS.getDescripcion());
		respuesta.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>(Arrays.asList(item)));
		return respuesta;
	}

	private Respuesta<AgendaDestinatarioDTO> respuestaAgendaErrorServicio() {
		Respuesta<AgendaDestinatarioDTO> respuesta = new Respuesta<AgendaDestinatarioDTO>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		ItemMensajeRespuesta item = new ItemMensajeRespuesta();
		item.setTipoError(TipoError.ERROR_SERVICIO.getDescripcion());
		respuesta.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>(Arrays.asList(item)));
		return respuesta;
	}

	private List<DestinatarioAgendaDTO> crearListaDestinatarios() {
		DestinatarioAgendaDTO dest1 = new DestinatarioAgendaDTO();
		dest1.setEsCuentaPropia(false);
		dest1.setReferenciaApodo("Apodo");
		List<DestinatarioAgendaDTO> lista = new ArrayList<DestinatarioAgendaDTO>(Arrays.asList(dest1));
		return lista;
	}

	private DestinatarioView obtenerDestinatarioView() {
		DestinatarioAgendaDTO dto = new DestinatarioAgendaDTO();
		dto.setCbu("01234567489");
		dto.setTipoAgendaEnum(TipoAgendaEnum.AGENDA_OTROS_BANCOS);
		dto.setIdFavorito("1");
		DestinatarioView destinatario = new DestinatarioView(dto);
		return destinatario;
	}

}
