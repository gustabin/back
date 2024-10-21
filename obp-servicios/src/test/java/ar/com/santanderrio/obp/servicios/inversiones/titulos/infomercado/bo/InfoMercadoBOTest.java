package ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.bo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;

import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.SessionUsuarioData;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.dto.EspecieDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.dto.IndiceDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.dto.ParametroInfoMercadoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.dto.TipoInstrumentoInfoMercadoEnum;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.ConsultaFiltroInformacionMercadoRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.ConsultaFiltroInformacionMercadoResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.ConsultaIndicesRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.ConsultaIndicesResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.ConsultaInformacionMercadoRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.ConsultaInformacionMercadoResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.DatosConsultaFiltroInformacionMercado;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.DatosConsultaIndicesResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.DatosConsultaInformacionMercadoResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.IndiceResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.InfoMercadoResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.ParametroFiltroInformacionMercado;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.InfoMercadoViewIn;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.OrdenesTitulosDAO;

@RunWith(MockitoJUnitRunner.class)
public class InfoMercadoBOTest {

	@InjectMocks
	private InfoMercadoBOImpl infoMercadoBOImpl;

	@Mock
	private OrdenesTitulosDAO ordenesTitulosDAO;

	@Mock
	private MensajeBO mensajeBO;
	
	@Mock
	private SesionCliente sesionCliente;

	@Spy
	@InjectMocks
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	@Test
	public void obtenerParametrosInfoMercadoOk() throws IllegalAccessException, DAOException {
		Cliente cliente = mock(Cliente.class);
		ApplicationContext ctx = Mockito.mock(ApplicationContext.class);
		SessionUsuarioData request = Mockito.mock(SessionUsuarioData.class);
		FieldUtils.writeDeclaredStaticField(ContextHolder.class, "context", ctx, true);
		ConsultaFiltroInformacionMercadoResponse response = crearConsultaFiltroInformacionMercadoResponse();

		when(ctx.getBean(SessionUsuarioData.class)).thenReturn(request);
		when(ordenesTitulosDAO
				.consultaFiltroInfoMercado(Matchers.any(ConsultaFiltroInformacionMercadoRequestEntity.class)))
						.thenReturn(response);

		Respuesta<List<ParametroInfoMercadoDTO>> respuestaBO = infoMercadoBOImpl.obtenerParametrosInfoMercado(cliente, false);

		Assert.assertNotNull(respuestaBO);
		Assert.assertEquals(EstadoRespuesta.OK, respuestaBO.getEstadoRespuesta());
		Assert.assertEquals(6, respuestaBO.getRespuesta().size());
		Assert.assertEquals("1", respuestaBO.getRespuesta().get(0).getAgrupamientoId());
		Assert.assertEquals("Titulos Publico", respuestaBO.getRespuesta().get(1).getDescripcion());
		Assert.assertEquals(new Integer(3), respuestaBO.getRespuesta().get(2).getOrden());
		Assert.assertEquals(true, respuestaBO.getRespuesta().get(0).getDefecto());
	}

	@Test
	public void obtenerParametrosInfoMercadoError() throws DAOException, IllegalAccessException {
		Cliente cliente = mock(Cliente.class);
		ApplicationContext ctx = Mockito.mock(ApplicationContext.class);
		SessionUsuarioData request = Mockito.mock(SessionUsuarioData.class);
		FieldUtils.writeDeclaredStaticField(ContextHolder.class, "context", ctx, true);
		Mensaje msj = new Mensaje();
		msj.setMensaje("Ha ocurrido un error en nuestros servicios...");

		when(ctx.getBean(SessionUsuarioData.class)).thenReturn(request);
		when(ordenesTitulosDAO
				.consultaFiltroInfoMercado(Matchers.any(ConsultaFiltroInformacionMercadoRequestEntity.class)))
						.thenThrow(new DAOException());
		when(mensajeBO.obtenerMensajePorCodigo("10422")).thenReturn(msj);

		Respuesta<List<ParametroInfoMercadoDTO>> respuestaBO = infoMercadoBOImpl.obtenerParametrosInfoMercado(cliente, false);

		Assert.assertNotNull(respuestaBO);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuestaBO.getEstadoRespuesta());
		Assert.assertEquals(TipoError.ERROR_SERVICIO.getDescripcion(),
				respuestaBO.getItemsMensajeRespuesta().get(0).getTipoError());
	}

	@Test
	public void obtenerIndicesOk() throws IllegalAccessException, DAOException {
		Cliente cliente = mock(Cliente.class);
		ApplicationContext ctx = Mockito.mock(ApplicationContext.class);
		SessionUsuarioData request = Mockito.mock(SessionUsuarioData.class);
		FieldUtils.writeDeclaredStaticField(ContextHolder.class, "context", ctx, true);
		ConsultaIndicesResponse response = crearConsultaIndicesResponse();

		when(ctx.getBean(SessionUsuarioData.class)).thenReturn(request);
		when(ordenesTitulosDAO.consultaIndices(Matchers.any(ConsultaIndicesRequest.class))).thenReturn(response);

		Respuesta<List<IndiceDTO>> respuestaBO = infoMercadoBOImpl.obtenerIndices(cliente, false);

		Assert.assertNotNull(respuestaBO);
		Assert.assertEquals(EstadoRespuesta.OK, respuestaBO.getEstadoRespuesta());
		Assert.assertEquals(3, respuestaBO.getRespuesta().size());
		Assert.assertEquals("BURCAP                                                                ",
				respuestaBO.getRespuesta().get(0).getNombre());
		Assert.assertEquals(new BigDecimal(4.5), respuestaBO.getRespuesta().get(1).getUltimo());
		Assert.assertEquals(new BigDecimal(0.4), respuestaBO.getRespuesta().get(2).getValor());
	}

	@Test
	public void obtenerIndicesError() throws DAOException, IllegalAccessException {
		Cliente cliente = mock(Cliente.class);
		ApplicationContext ctx = Mockito.mock(ApplicationContext.class);
		SessionUsuarioData request = Mockito.mock(SessionUsuarioData.class);
		FieldUtils.writeDeclaredStaticField(ContextHolder.class, "context", ctx, true);
		Mensaje msj = new Mensaje();
		msj.setMensaje("Ha ocurrido un error en nuestros servicios...");

		when(ctx.getBean(SessionUsuarioData.class)).thenReturn(request);
		when(ordenesTitulosDAO.consultaIndices(Matchers.any(ConsultaIndicesRequest.class)))
				.thenThrow(new DAOException());
		when(mensajeBO.obtenerMensajePorCodigo("10422")).thenReturn(msj);

		Respuesta<List<IndiceDTO>> respuestaBO = infoMercadoBOImpl.obtenerIndices(cliente, false);

		Assert.assertNotNull(respuestaBO);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuestaBO.getEstadoRespuesta());
		Assert.assertEquals(TipoError.ERROR_SERVICIO.getDescripcion(),
				respuestaBO.getItemsMensajeRespuesta().get(0).getTipoError());
	}

	@Test
	public void obtenerGrillaInfoMercadoOk() throws IllegalAccessException, DAOException {
		Cliente cliente = mock(Cliente.class);
		ApplicationContext ctx = Mockito.mock(ApplicationContext.class);
		SessionUsuarioData request = Mockito.mock(SessionUsuarioData.class);
		FieldUtils.writeDeclaredStaticField(ContextHolder.class, "context", ctx, true);
		InfoMercadoViewIn view = new InfoMercadoViewIn();
		view.setAgrupamientoId("1");
		view.setEsBancaPrivada(false);

		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(ctx.getBean(SessionUsuarioData.class)).thenReturn(request);
		when(ordenesTitulosDAO.consultaInformacionMercado(Matchers.any(ConsultaInformacionMercadoRequest.class)))
				.thenReturn(crearConsultaInformacionMercadoResponse());

		Respuesta<List<EspecieDTO>> respuestaBO = infoMercadoBOImpl.obtenerGrillaInfoMercado(cliente, view);

		Assert.assertNotNull(respuestaBO);
		Assert.assertEquals(EstadoRespuesta.OK, respuestaBO.getEstadoRespuesta());
		Assert.assertEquals(TipoInstrumentoInfoMercadoEnum.ACCIONES, respuestaBO.getRespuesta().get(0).getTipo());
		Assert.assertEquals("AGRO", respuestaBO.getRespuesta().get(0).getCodigoAmigable());
		Assert.assertEquals("ARS", respuestaBO.getRespuesta().get(0).getMonedaEspecie());
	}

	@Test
	public void obtenerGrillaInfoMercadoError() throws DAOException, IllegalAccessException {
		Cliente cliente = mock(Cliente.class);
		ApplicationContext ctx = Mockito.mock(ApplicationContext.class);
		SessionUsuarioData request = Mockito.mock(SessionUsuarioData.class);
		FieldUtils.writeDeclaredStaticField(ContextHolder.class, "context", ctx, true);
		Mensaje msj = new Mensaje();
		msj.setMensaje("Ha ocurrido un error en nuestros servicios...");
		InfoMercadoViewIn view = new InfoMercadoViewIn();
		view.setAgrupamientoId("1");
		view.setEsBancaPrivada(false);


		when(ctx.getBean(SessionUsuarioData.class)).thenReturn(request);
		when(ordenesTitulosDAO.consultaInformacionMercado(Matchers.any(ConsultaInformacionMercadoRequest.class)))
				.thenThrow(new DAOException());
		when(mensajeBO.obtenerMensajePorCodigo("10422")).thenReturn(msj);

		Respuesta<List<EspecieDTO>> respuestaBO = infoMercadoBOImpl.obtenerGrillaInfoMercado(cliente, view);

		Assert.assertNotNull(respuestaBO);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuestaBO.getEstadoRespuesta());
		Assert.assertEquals(TipoError.ERROR_GRILLA_INFOMERCADO.getDescripcion(),
				respuestaBO.getItemsMensajeRespuesta().get(0).getTipoError());
	}

	private ConsultaFiltroInformacionMercadoResponse crearConsultaFiltroInformacionMercadoResponse() {
		ConsultaFiltroInformacionMercadoResponse response = new ConsultaFiltroInformacionMercadoResponse();
		DatosConsultaFiltroInformacionMercado datos = new DatosConsultaFiltroInformacionMercado();
		List<ParametroFiltroInformacionMercado> listaParametros = new ArrayList<ParametroFiltroInformacionMercado>();
		List<String> descripciones = new ArrayList<String>(
				Arrays.asList("Lo mas Operado", "Titulos Publico", "Titulo Privado", "Merval", "Merval 25", "Cedear"));
		List<Integer> ordenes = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6));
		for (int i = 0; i < 6; i++) {
			ParametroFiltroInformacionMercado parametro = new ParametroFiltroInformacionMercado();
			parametro.setAgrupamientoId(ordenes.get(i).toString());
			parametro.setDescripcionParametro(descripciones.get(i));
			parametro.setOrden(ordenes.get(i));
			parametro.setDefecto(i == 0 ? "S" : "N");
			parametro.setLeyendaLegal("Leyenda " + i);
			listaParametros.add(parametro);
		}
		datos.setListaParametros(listaParametros);
		response.setDatos(datos);
		return response;
	}

	private ConsultaIndicesResponse crearConsultaIndicesResponse() {
		ConsultaIndicesResponse response = new ConsultaIndicesResponse();
		DatosConsultaIndicesResponse datos = new DatosConsultaIndicesResponse();
		List<IndiceResponse> listaIndices = new ArrayList<IndiceResponse>();
		List<String> secIds = new ArrayList<String>(
				Arrays.asList("90004               ", "90000               ", "90042               "));
		List<String> nombresIndice = new ArrayList<String>(
				Arrays.asList("BURCAP                                                                ",
						"MERVAL                                                                ",
						"MERVAL 25                                                             "));
		List<String> codigosIndice = new ArrayList<String>(
				Arrays.asList("BURC        ", "MERV        ", "MV25        "));
		List<BigDecimal> ultimosPrecios = new ArrayList<BigDecimal>(
				Arrays.asList(new BigDecimal(1.5), new BigDecimal(4.5), new BigDecimal(2.5)));
		List<BigDecimal> variaciones = new ArrayList<BigDecimal>(
				Arrays.asList(new BigDecimal(-1.07), new BigDecimal(-1.5), new BigDecimal(0.4)));
		for (int i = 0; i < 3; i++) {
			IndiceResponse indiceResponse = new IndiceResponse();
			indiceResponse.setSecId(secIds.get(i));
			indiceResponse.setNombreIndice(nombresIndice.get(i));
			indiceResponse.setCodigoIndice(codigosIndice.get(i));
			indiceResponse.setUltimoPrecioOperado(ultimosPrecios.get(i));
			indiceResponse.setVariacion(variaciones.get(i));
			indiceResponse.setFecha("11052018");
			indiceResponse.setHora("11052018");
			listaIndices.add(indiceResponse);
		}
		datos.setListaIndices(listaIndices);
		response.setDatos(datos);
		return response;
	}

	private ConsultaInformacionMercadoResponse crearConsultaInformacionMercadoResponse() {
		ConsultaInformacionMercadoResponse response = new ConsultaInformacionMercadoResponse();
		DatosConsultaInformacionMercadoResponse datos = new DatosConsultaInformacionMercadoResponse();
		InfoMercadoResponse infoMercado = new InfoMercadoResponse();
		infoMercado.setInstrumentoCodigo("ACC");
		infoMercado.setCodigoAmigable("AGRO");
		infoMercado.setEmisionMonedaEspecie("ARS");
		infoMercado.setPlazo(48);
		infoMercado.setPrecioReferencia(new BigDecimal(35.55));
		infoMercado.setFechaPrecioRef("25/04/2018 00:00:00");
		infoMercado.setHoraPrecioRef("06:00:20");
		infoMercado.setVariacion(BigDecimal.ZERO);
		infoMercado.setMontoOperado(BigDecimal.ZERO);
		infoMercado.setCodigoEspecieRossi("1234");
		datos.setListaInfoMercado(new ArrayList<InfoMercadoResponse>(Arrays.asList(infoMercado)));
		datos.setActualizacionAutomatica(true);
		datos.setTiempoActualizacion(500);
		response.setDatos(datos);
		return response;
	}

}
