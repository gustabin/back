package ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.manager;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.bo.InfoMercadoBO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.dto.EspecieDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.dto.IndiceDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.dto.ParametroInfoMercadoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.dto.TipoInstrumentoInfoMercadoEnum;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.IndicesView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.InfoMercadoView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.InfoMercadoViewIn;

@RunWith(MockitoJUnitRunner.class)
public class InfoMercadoManagerTest {

	@InjectMocks
	private InfoMercadoManagerImpl infomercadoManagerImpl;

	@Mock
	private InfoMercadoBO infoMercadoBO;

	@Mock
	private SesionCliente sesionCliente;

	@Mock
	private EstadisticaManager estadisticaManager;

	@Spy
	@InjectMocks
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	@Mock
	private SesionParametros sesionParametros;

	@Mock
	private MensajeBO mensajeBO;

	@Test
	public void inicioInfoMercadoOk() {
		Cliente cliente = mock(Cliente.class);
		Mensaje msj = new Mensaje();
		msj.setMensaje("Prueba");
		Respuesta<List<ParametroInfoMercadoDTO>> respuestaBO = crearRespuestaListaParametros();
		Respuesta<List<EspecieDTO>> respuestaGrilla = crearRespuestaGrilla();
		InfoMercadoViewIn view = new InfoMercadoViewIn();
		view.setAgrupamientoId("1");
		view.setEsBancaPrivada(false);

		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(infoMercadoBO.obtenerParametrosInfoMercado(cliente, view.getEsBancaPrivada())).thenReturn(respuestaBO);
		when(infoMercadoBO.obtenerGrillaInfoMercado(Matchers.any(Cliente.class), Matchers.any(InfoMercadoViewIn.class))).thenReturn(respuestaGrilla);
		when(mensajeBO.obtenerMensajePorCodigo("10454")).thenReturn(msj);
		
		Respuesta<InfoMercadoView> respuestaView = infomercadoManagerImpl.inicioInfoMercado(view);

		Assert.assertNotNull(respuestaView);
		Assert.assertEquals(EstadoRespuesta.OK, respuestaView.getEstadoRespuesta());
		Assert.assertEquals(6, respuestaView.getRespuesta().getParametros().size());
		Assert.assertEquals("1", respuestaView.getRespuesta().getParametros().get(0).getAgrupamientoId());
		Assert.assertEquals("Titulos Publico", respuestaView.getRespuesta().getParametros().get(1).getDescripcion());
		Assert.assertEquals(true, respuestaView.getRespuesta().getParametros().get(0).getDefecto());
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(EstadisticasConstants.INFOMERCADO_PARAMETROS,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(EstadisticasConstants.INFOMERCADO_GRILLA,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	@Test
	public void inicioInfoMercadoWarning() {
		Cliente cliente = mock(Cliente.class);
		Respuesta<List<ParametroInfoMercadoDTO>> respuestaBO = crearRespuestaListaParametros();
		Respuesta<List<EspecieDTO>> respuestaGrilla = new Respuesta<List<EspecieDTO>>();
		respuestaGrilla.setEstadoRespuesta(EstadoRespuesta.ERROR);
		InfoMercadoViewIn view = new InfoMercadoViewIn();
		view.setAgrupamientoId("1");
		view.setEsBancaPrivada(false);


		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(infoMercadoBO.obtenerParametrosInfoMercado(cliente, view.getEsBancaPrivada())).thenReturn(respuestaBO);
		when(infoMercadoBO.obtenerGrillaInfoMercado(Matchers.any(Cliente.class), Matchers.any(InfoMercadoViewIn.class))).thenReturn(respuestaGrilla);

		Respuesta<InfoMercadoView> respuestaView = infomercadoManagerImpl.inicioInfoMercado(view);

		Assert.assertNotNull(respuestaView);
		Assert.assertEquals(EstadoRespuesta.WARNING, respuestaView.getEstadoRespuesta());
		Assert.assertEquals(6, respuestaView.getRespuesta().getParametros().size());
		Assert.assertEquals("1", respuestaView.getRespuesta().getParametros().get(0).getAgrupamientoId());
		Assert.assertEquals("Titulos Publico", respuestaView.getRespuesta().getParametros().get(1).getDescripcion());
		Assert.assertEquals(true, respuestaView.getRespuesta().getParametros().get(0).getDefecto());
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(EstadisticasConstants.INFOMERCADO_PARAMETROS,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(EstadisticasConstants.INFOMERCADO_GRILLA,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
	}

	@Test
	public void inicioInfoMercadoError() {
		Cliente cliente = mock(Cliente.class);
		Respuesta<List<ParametroInfoMercadoDTO>> respuestaBO = new Respuesta<List<ParametroInfoMercadoDTO>>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		InfoMercadoViewIn view = new InfoMercadoViewIn();
		view.setEsBancaPrivada(false);


		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(infoMercadoBO.obtenerParametrosInfoMercado(cliente, view.getEsBancaPrivada())).thenReturn(respuestaBO);

		Respuesta<InfoMercadoView> respuestaView = infomercadoManagerImpl.inicioInfoMercado(view);

		Assert.assertNotNull(respuestaView);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuestaView.getEstadoRespuesta());
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(EstadisticasConstants.INFOMERCADO_PARAMETROS,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
	}

	@Test
	public void obtenerIndicesOk() {
		Cliente cliente = mock(Cliente.class);
		Respuesta<List<IndiceDTO>> respuestaBO = crearRespuestaListaIndices();
		InfoMercadoViewIn view = new InfoMercadoViewIn();
		view.setEsBancaPrivada(false);


		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(infoMercadoBO.obtenerIndices(cliente, view.getEsBancaPrivada())).thenReturn(respuestaBO);

		Respuesta<IndicesView> respuestaView = infomercadoManagerImpl.obtenerIndices(view);

		Assert.assertNotNull(respuestaView);
		Assert.assertEquals(EstadoRespuesta.OK, respuestaView.getEstadoRespuesta());
		Assert.assertEquals(3, respuestaView.getRespuesta().getListaIndices().size());
		Assert.assertEquals("BURCAP", respuestaView.getRespuesta().getListaIndices().get(0).getNombre());
		Assert.assertEquals("4,50", respuestaView.getRespuesta().getListaIndices().get(1).getUltimo());
		Assert.assertEquals("flechita_verde", respuestaView.getRespuesta().getListaIndices().get(2).getIndicador());
		Assert.assertEquals("0,40 %", respuestaView.getRespuesta().getListaIndices().get(2).getValor());
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(EstadisticasConstants.INFO_MERCADO_INDICES,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

	}

	@Test
	public void obtenerIndicesError() {
		Cliente cliente = mock(Cliente.class);
		Respuesta<List<IndiceDTO>> respuestaBO = new Respuesta<List<IndiceDTO>>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		InfoMercadoViewIn view = new InfoMercadoViewIn();
		view.setEsBancaPrivada(false);


		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(infoMercadoBO.obtenerIndices(cliente, view.getEsBancaPrivada())).thenReturn(respuestaBO);

		Respuesta<IndicesView> respuestaView = infomercadoManagerImpl.obtenerIndices(view);

		Assert.assertNotNull(respuestaView);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuestaView.getEstadoRespuesta());
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(EstadisticasConstants.INFO_MERCADO_INDICES,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
	}

	@Test
	public void obtenerGrillaOk() {
		Cliente cliente = mock(Cliente.class);
		Respuesta<List<EspecieDTO>> respuestaBO = crearRespuestaGrilla();
		InfoMercadoViewIn viewIn = new InfoMercadoViewIn();
		Mensaje msj = new Mensaje();
		msj.setMensaje("Prueba");
		viewIn.setAgrupamientoId("1");
		viewIn.setEsBancaPrivada(false);

		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(infoMercadoBO.obtenerGrillaInfoMercado(cliente, viewIn)).thenReturn(respuestaBO);
		when(mensajeBO.obtenerMensajePorCodigo("10454")).thenReturn(msj);
		Respuesta<InfoMercadoView> respuestaView = infomercadoManagerImpl.obtenerGrillaInfoMercado(viewIn, true);

		Assert.assertNotNull(respuestaView);
		Assert.assertEquals(EstadoRespuesta.OK, respuestaView.getEstadoRespuesta());
		Assert.assertEquals("AGROMETAL S.A.I. ESCR. 1",
				respuestaView.getRespuesta().getListasEspecies().getEspeciesPesos().getAcciones().get(0).getDescripcion());
		Assert.assertEquals("$ 35,55",
				respuestaView.getRespuesta().getListasEspecies().getEspeciesPesos().getAcciones().get(0).getUltimaCotizacion());
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(EstadisticasConstants.INFOMERCADO_GRILLA,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	@Test
	public void obtenerGrillaError() {
		Cliente cliente = mock(Cliente.class);
		Respuesta<List<EspecieDTO>> respuestaBO = new Respuesta<List<EspecieDTO>>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		InfoMercadoViewIn viewIn = new InfoMercadoViewIn();
		viewIn.setAgrupamientoId("1");
		viewIn.setEsBancaPrivada(false);

		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(infoMercadoBO.obtenerGrillaInfoMercado(cliente, viewIn)).thenReturn(respuestaBO);

		Respuesta<InfoMercadoView> respuestaView = infomercadoManagerImpl.obtenerGrillaInfoMercado(viewIn, true);

		Assert.assertNotNull(respuestaView);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuestaView.getEstadoRespuesta());
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(EstadisticasConstants.INFOMERCADO_GRILLA,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
	}

	@Test
	public void busquedaPalabraClaveTest() {
		Cliente cliente = mock(Cliente.class);
		Respuesta<List<EspecieDTO>> respuestaBO = crearRespuestaGrilla();
		InfoMercadoViewIn viewIn = new InfoMercadoViewIn();
		Mensaje msj = new Mensaje();
		msj.setMensaje("Prueba");
		viewIn.setAgrupamientoId("1");
		viewIn.setPalabraClave("agr");
		viewIn.setEsBancaPrivada(false);

		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(infoMercadoBO.obtenerGrillaInfoMercado(cliente, viewIn)).thenReturn(respuestaBO);
		when(mensajeBO.obtenerMensajePorCodigo("10454")).thenReturn(msj);
		
		Respuesta<InfoMercadoView> respuestaView = infomercadoManagerImpl.obtenerGrillaInfoMercado(viewIn, false);

		Assert.assertNotNull(respuestaView);
		Assert.assertEquals(EstadoRespuesta.OK, respuestaView.getEstadoRespuesta());
		Assert.assertEquals("AGROMETAL S.A.I. ESCR. 1",
				respuestaView.getRespuesta().getListasEspecies().getEspeciesPesos().getAcciones().get(0).getDescripcion());
		Assert.assertEquals("$ 35,55",
				respuestaView.getRespuesta().getListasEspecies().getEspeciesPesos().getAcciones().get(0).getUltimaCotizacion());
	}

	@Test
	public void busquedaPorPlazoTest() {
		Cliente cliente = mock(Cliente.class);
		Respuesta<List<EspecieDTO>> respuestaBO = crearRespuestaGrilla();
		InfoMercadoViewIn viewIn = new InfoMercadoViewIn();
		Mensaje msj = new Mensaje();
		msj.setMensaje("Prueba");
		viewIn.setAgrupamientoId("1");
		viewIn.setPlazo("48 hs");
		viewIn.setEsBancaPrivada(false);

		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(infoMercadoBO.obtenerGrillaInfoMercado(cliente, viewIn)).thenReturn(respuestaBO);
		when(mensajeBO.obtenerMensajePorCodigo("10454")).thenReturn(msj);
		Respuesta<InfoMercadoView> respuestaView = infomercadoManagerImpl.obtenerGrillaInfoMercado(viewIn, false);

		Assert.assertNotNull(respuestaView);
		Assert.assertEquals(EstadoRespuesta.OK, respuestaView.getEstadoRespuesta());
		Assert.assertEquals("AGROMETAL S.A.I. ESCR. 1",
				respuestaView.getRespuesta().getListasEspecies().getEspeciesPesos().getAcciones().get(0).getDescripcion());
		Assert.assertEquals("$ 35,55",
				respuestaView.getRespuesta().getListasEspecies().getEspeciesPesos().getAcciones().get(0).getUltimaCotizacion());
	}

	@Test
	public void busquedaPorVariacionTest() {
		Cliente cliente = mock(Cliente.class);
		Respuesta<List<EspecieDTO>> respuestaBO = crearRespuestaGrilla();
		InfoMercadoViewIn viewIn = new InfoMercadoViewIn();
		Mensaje msj = new Mensaje();
		msj.setMensaje("Prueba");
		viewIn.setAgrupamientoId("1");
		viewIn.setVariacion("sin variación");
		viewIn.setEsBancaPrivada(false);

		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(infoMercadoBO.obtenerGrillaInfoMercado(cliente, viewIn)).thenReturn(respuestaBO);
		when(mensajeBO.obtenerMensajePorCodigo("10454")).thenReturn(msj);
		
		Respuesta<InfoMercadoView> respuestaView = infomercadoManagerImpl.obtenerGrillaInfoMercado(viewIn, false);

		Assert.assertNotNull(respuestaView);
		Assert.assertEquals(EstadoRespuesta.OK, respuestaView.getEstadoRespuesta());
		Assert.assertEquals("AGROMETAL S.A.I. ESCR. 1",
				respuestaView.getRespuesta().getListasEspecies().getEspeciesPesos().getAcciones().get(0).getDescripcion());
		Assert.assertEquals("$ 35,55",
				respuestaView.getRespuesta().getListasEspecies().getEspeciesPesos().getAcciones().get(0).getUltimaCotizacion());
	}

	@Test
	public void busquedaSinResultadosTest() {
		Cliente cliente = mock(Cliente.class);
		Respuesta<List<EspecieDTO>> respuestaBO = crearRespuestaGrilla();
		InfoMercadoViewIn viewIn = new InfoMercadoViewIn();
		viewIn.setAgrupamientoId("2");
		viewIn.setPalabraClave("qwerty");
		viewIn.setEsBancaPrivada(false);
		Mensaje msj = new Mensaje();
		msj.setMensaje("mensaje prueba");

		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(infoMercadoBO.obtenerGrillaInfoMercado(cliente, viewIn)).thenReturn(respuestaBO);
		when(mensajeBO.obtenerMensajePorCodigo("10454")).thenReturn(msj);
		when(sesionParametros.getIdAgrupamientoInformacionMercado()).thenReturn("2");

		Respuesta<InfoMercadoView> respuestaView = infomercadoManagerImpl.obtenerGrillaInfoMercado(viewIn, false);

		Assert.assertNotNull(respuestaView);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuestaView.getEstadoRespuesta());
		Assert.assertEquals(TipoError.SIN_RESULTADOS.getDescripcion(),
				respuestaView.getItemsMensajeRespuesta().get(0).getTipoError());
	}

	private Respuesta<List<ParametroInfoMercadoDTO>> crearRespuestaListaParametros() {
		List<ParametroInfoMercadoDTO> listaParametros = new ArrayList<ParametroInfoMercadoDTO>();
		List<String> descripciones = new ArrayList<String>(
				Arrays.asList("Lo mas Operado", "Titulos Publico", "Titulo Privado", "Merval", "Merval 25", "Cedear"));
		List<Integer> ordenes = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6));
		for (int i = 0; i < 6; i++) {
			ParametroInfoMercadoDTO parametro = new ParametroInfoMercadoDTO();
			parametro.setAgrupamientoId(ordenes.get(i).toString());
			parametro.setDescripcion(descripciones.get(i));
			parametro.setOrden(ordenes.get(i));
			parametro.setDefecto(i == 0 ? true : false);
			parametro.setLegal("Leyenda " + i);
			listaParametros.add(parametro);
		}
		return respuestaFactory.crearRespuestaOk(listaParametros);
	}

	private Respuesta<List<IndiceDTO>> crearRespuestaListaIndices() {
		List<IndiceDTO> listaIndices = new ArrayList<IndiceDTO>();
		List<String> nombresIndice = new ArrayList<String>(
				Arrays.asList("BURCAP                                                                ",
						"MERVAL                                                                ",
						"MERVAL 25                                                             "));
		List<BigDecimal> ultimosPrecios = new ArrayList<BigDecimal>(
				Arrays.asList(new BigDecimal(1.5), new BigDecimal(4.5), new BigDecimal(2.5)));
		List<BigDecimal> variaciones = new ArrayList<BigDecimal>(
				Arrays.asList(new BigDecimal(-1.07), new BigDecimal(-1.5), new BigDecimal(0.4)));
		for (int i = 0; i < 3; i++) {
			IndiceDTO indiceDTO = new IndiceDTO();
			indiceDTO.setNombre(nombresIndice.get(i));
			indiceDTO.setUltimo(ultimosPrecios.get(i));
			indiceDTO.setValor(variaciones.get(i));
			listaIndices.add(indiceDTO);
		}
		return respuestaFactory.crearRespuestaOk(listaIndices);
	}

	private Respuesta<List<EspecieDTO>> crearRespuestaGrilla() {
		List<EspecieDTO> listaEspecies = new ArrayList<EspecieDTO>();
		EspecieDTO especie = new EspecieDTO();
		especie.setTipo(TipoInstrumentoInfoMercadoEnum.ACCIONES);
		especie.setDescripcion("AGROMETAL S.A.I. ESCR. 1");
		especie.setCodigoAmigable("AGRO");
		especie.setMonedaEspecie("ARS");
		especie.setMonedaNegociacion("ARS");
		especie.setPlazo(48);
		especie.setUltimaCotizacion(new BigDecimal(35.55));
		especie.setUltimaCotizacionFecha("25/04/2018 00:00:00");
		especie.setUltimaCotizacionHora("06:00:20");
		especie.setVariacionValor(BigDecimal.ZERO);
		especie.setImporteOperado(BigDecimal.ZERO);
		especie.setCodigoEspecie("1234");
		especie.setValorNominal(new BigDecimal("10.20"));

		EspecieDTO especie2 = new EspecieDTO();
		especie2.setTipo(TipoInstrumentoInfoMercadoEnum.CEDEARS);
		especie2.setDescripcion("ALUMINUM CO OF AM. (ALCOA) (CEDEAR)");
		especie2.setCodigoAmigable("AA");
		especie2.setMonedaEspecie("ARS");
		especie2.setMonedaNegociacion("USD");
		especie2.setPlazo(48);
		especie2.setUltimaCotizacion(new BigDecimal(71));
		especie2.setUltimaCotizacionFecha("25/04/2018 00:00:00");
		especie2.setUltimaCotizacionHora("06:00:20");
		especie2.setVariacionValor(BigDecimal.ZERO);
		especie2.setImporteOperado(BigDecimal.ZERO);
		especie2.setCodigoEspecie("1234");
		especie2.setValorNominal(new BigDecimal("23.58"));

		listaEspecies.add(especie);
		listaEspecies.add(especie2);
		return respuestaFactory.crearRespuestaOk(listaEspecies);
	}

}
