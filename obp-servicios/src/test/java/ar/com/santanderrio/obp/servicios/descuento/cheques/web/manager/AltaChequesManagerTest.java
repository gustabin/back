package ar.com.santanderrio.obp.servicios.descuento.cheques.web.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.gson.Gson;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.descuento.cheques.bo.AltaChequesBO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequeSimuladoDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequesAltaDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequesSimuladosDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.DatosCesionDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.manager.impl.AltaChequesManagerImpl;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.AltaChequeAceptadoViewOut;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.AltaChequesViewIn;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.AltaChequesViewOut;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

@RunWith(MockitoJUnitRunner.class)
public class AltaChequesManagerTest {

	@InjectMocks
	AltaChequesManager manager = new AltaChequesManagerImpl();

	@Mock
	AltaChequesBO simulacionBO;

	@Mock
	private MensajeBO mensajeBO;

	/** The estadistica manager. */
	@Mock
	private EstadisticaManager estadisticaManager;

	/** The sesion parametros. */
	@Mock
	private SesionParametros sesionParametros;

	/** The sesion cliente. */
	@Mock
	private SesionCliente sesionCliente;

	@Spy
	@InjectMocks
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	@Test
	public void simularAltaChequesOkTest() {
		ObjectMapper mapper = new ObjectMapper();
		ChequesSimuladosDTO dto = null;
		try {
			dto = mapper.readValue(
					"{\"importeAcreditar\":500.00,\"importeTotal\":500.00,\"cuentaDebito\":{\"nroSucursal\":\"00\",\"nroCuentaProducto\":\"065489\"},\"numeroDeOperacion\":130217,\"chequesDescontados\":10,\"importeCheque\":500.00,\"importeImpuestos\":500.00,\"importeIntereses\":0.00,\"importeNeto\":500.00,\"listaAceptados\":[{\"numeroCheque\":\"07200010361000016139000002306700\",\"banco\":\"SANTANDER\",\"diasAAdelantar\":7,\"importeCheque\":50.00,\"importeImpuestos\":0.00,\"importeIntereses\":0.00,\"importeNeto\":50.00},{\"numeroCheque\":\"07200010361963858252000001716700\",\"banco\":\"SANTANDER\",\"diasAAdelantar\":7,\"importeCheque\":50.00,\"importeImpuestos\":0.00,\"importeIntereses\":0.00,\"importeNeto\":50.00},{\"numeroCheque\":\"07200010361963858283000001716700\",\"banco\":\"SANTANDER\",\"diasAAdelantar\":7,\"importeCheque\":50.00,\"importeImpuestos\":0.00,\"importeIntereses\":0.00,\"importeNeto\":50.00},{\"numeroCheque\":\"07200010361963858498000001716700\",\"banco\":\"SANTANDER\",\"diasAAdelantar\":7,\"importeCheque\":50.00,\"importeImpuestos\":0.00,\"importeIntereses\":0.00,\"importeNeto\":50.00},{\"numeroCheque\":\"07200010361963858658000001716700\",\"banco\":\"SANTANDER\",\"diasAAdelantar\":7,\"importeCheque\":50.00,\"importeImpuestos\":0.00,\"importeIntereses\":0.00,\"importeNeto\":50.00},{\"numeroCheque\":\"07228016428810612987000032056314\",\"banco\":\"SANTANDER\",\"diasAAdelantar\":7,\"importeCheque\":50.00,\"importeImpuestos\":0.00,\"importeIntereses\":0.00,\"importeNeto\":50.00},{\"numeroCheque\":\"07228016428810613034000032056314\",\"banco\":\"SANTANDER\",\"diasAAdelantar\":7,\"importeCheque\":50.00,\"importeImpuestos\":0.00,\"importeIntereses\":0.00,\"importeNeto\":50.00},{\"numeroCheque\":\"07228016428810613164000032056314\",\"banco\":\"SANTANDER\",\"diasAAdelantar\":7,\"importeCheque\":50.00,\"importeImpuestos\":0.00,\"importeIntereses\":0.00,\"importeNeto\":50.00},{\"numeroCheque\":\"07228016428810613171000032056314\",\"banco\":\"SANTANDER\",\"diasAAdelantar\":7,\"importeCheque\":50.00,\"importeImpuestos\":0.00,\"importeIntereses\":0.00,\"importeNeto\":50.00},{\"numeroCheque\":\"07228016428810613218000032056314\",\"banco\":\"SANTANDER\",\"diasAAdelantar\":7,\"importeCheque\":50.00,\"importeImpuestos\":0.00,\"importeIntereses\":0.00,\"importeNeto\":50.00}],\"listaRechazados\":[],\"comisionAdic\":50.00,\"tasaAplicada\":0.00,\"tasaEfectivaAnual\":0.00,\"costoFinancieroTotal\":0.00}",
					
					ChequesSimuladosDTO.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				for(ChequeSimuladoDTO resSimulado:dto.getListaAceptados()) {
					resSimulado.setFechaDePago(new Date());	
				}
		Respuesta<ChequesSimuladosDTO> resDto = respuestaFactory.crearRespuestaOk(ChequesSimuladosDTO.class, dto);
		Mockito.when(simulacionBO.simularAltaCheques(Matchers.any(Cliente.class), Matchers.anyString(),
				Matchers.any(DatosCesionDTO.class), Matchers.any(Cuenta.class), Matchers.any(AltaChequesViewIn.class)))
				.thenReturn(resDto);
		try {
			Mockito.when(sesionParametros.getCesionCheques()).thenReturn(mapper.writeValueAsString(new DatosCesionDTO()));
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
		Mockito.when(sesionParametros.getCuentaSeleccionadaParaTransferencia(Matchers.anyString()))
				.thenReturn("000-060162/3");
		Respuesta<AltaChequesViewOut> res = manager.simularAltaCheques(new AltaChequesViewIn());
		Assert.assertTrue(EstadoRespuesta.OK.equals(res.getEstadoRespuesta()));
	}

	@Test
	public void simularAltaChequesRechazadaTest() {
		Gson gson = new Gson();
		Respuesta<ChequesSimuladosDTO> dto = gson.fromJson(
				"{\"estadoRespuesta\":\"ERROR\",\"itemsMensajeRespuesta\":[{\"tipoError\":\"SIMULACION_ALTA_CHEQUES_RECHAZADO\",\"tag\":\"simulacionAltaRechazado\"}],\"respuestaVacia\":true,\"skipLog\":false,\"id\":8658834086931488499}",
				Respuesta.class);
		Mockito.when(simulacionBO.simularAltaCheques(Matchers.any(Cliente.class), Matchers.anyString(),
				Matchers.any(DatosCesionDTO.class), Matchers.any(Cuenta.class), Matchers.any(AltaChequesViewIn.class)))
				.thenReturn(dto);
		Mockito.when(sesionParametros.getCesionCheques()).thenReturn(gson.toJson(new DatosCesionDTO()));
		Mockito.when(sesionParametros.getCuentaSeleccionadaParaTransferencia(Matchers.anyString()))
				.thenReturn("000-060162/3");
		Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
		Respuesta<AltaChequesViewOut> res = manager.simularAltaCheques(new AltaChequesViewIn());
		res.getItemsMensajeRespuesta().get(0).setMensaje("Mensaje de error");
		Assert.assertTrue(EstadoRespuesta.ERROR.equals(res.getEstadoRespuesta()));
	}

	@Test
	public void simularAltaChequesFueraHorarioTest() {
		Gson gson = new Gson();
		Respuesta<ChequesSimuladosDTO> dto = gson.fromJson(
				"{\"estadoRespuesta\":\"ERROR\",\"itemsMensajeRespuesta\":[{\"tipoError\":\"FUERA_DE_HORARIO\",\"tag\":\"fueraDeHorario\"}],\"respuestaVacia\":true,\"skipLog\":false,\"id\":8837052406588787237}",
				Respuesta.class);
		Mockito.when(simulacionBO.simularAltaCheques(Matchers.any(Cliente.class), Matchers.anyString(),
				Matchers.any(DatosCesionDTO.class), Matchers.any(Cuenta.class), Matchers.any(AltaChequesViewIn.class)))
				.thenReturn(dto);
		Mockito.when(sesionParametros.getCesionCheques()).thenReturn(gson.toJson(new DatosCesionDTO()));
		Mockito.when(sesionParametros.getCuentaSeleccionadaParaTransferencia(Matchers.anyString()))
				.thenReturn("000-060162/3");
		Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
		Respuesta<AltaChequesViewOut> res = manager.simularAltaCheques(new AltaChequesViewIn());
		res.getItemsMensajeRespuesta().get(0).setMensaje("Mensaje de error");
		Assert.assertTrue(EstadoRespuesta.ERROR.equals(res.getEstadoRespuesta()));
	}

	@Test
	public void simularAltaChequesFechaInvalidaTest() {
		Gson gson = new Gson();
		Respuesta<ChequesSimuladosDTO> dto = gson.fromJson(
				"{\"estadoRespuesta\":\"ERROR\",\"itemsMensajeRespuesta\":[{\"tipoError\":\"ERROR_FECHA\",\"tag\":\"fechaInvalida\"}],\"respuestaVacia\":true,\"skipLog\":false,\"id\":8837052406588787237}",
				Respuesta.class);
		Mockito.when(simulacionBO.simularAltaCheques(Matchers.any(Cliente.class), Matchers.anyString(),
				Matchers.any(DatosCesionDTO.class), Matchers.any(Cuenta.class), Matchers.any(AltaChequesViewIn.class)))
				.thenReturn(dto);
		Mockito.when(sesionParametros.getCesionCheques()).thenReturn(gson.toJson(new DatosCesionDTO()));
		Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
		Mockito.when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(2));
		Mockito.when(sesionParametros.getCuentaSeleccionadaParaTransferencia(Matchers.anyString()))
				.thenReturn("000-060162/3");
		Respuesta<AltaChequesViewOut> res = manager.simularAltaCheques(new AltaChequesViewIn());
		res.getItemsMensajeRespuesta().get(0).setMensaje("Mensaje de error");
		Assert.assertTrue(EstadoRespuesta.ERROR.equals(res.getEstadoRespuesta()));
	}

	@Test
	public void simularAltaChequesErrorGenericoTest() {
		Gson gson = new Gson();
		Respuesta<ChequesSimuladosDTO> dto = gson.fromJson(
				"{\"estadoRespuesta\":\"ERROR\",\"itemsMensajeRespuesta\":[{\"tipoError\":\"ERROR_GENERICO\",\"tag\":\"errorGenerico\"}],\"respuestaVacia\":true,\"skipLog\":false,\"id\":-7745543608948764518}",
				Respuesta.class);
		Mockito.when(simulacionBO.simularAltaCheques(Matchers.any(Cliente.class), Matchers.anyString(),
				Matchers.any(DatosCesionDTO.class), Matchers.any(Cuenta.class), Matchers.any(AltaChequesViewIn.class)))
				.thenReturn(dto);
		Mockito.when(sesionParametros.getCuentaSeleccionadaParaTransferencia(Matchers.anyString()))
				.thenReturn("000-060162/3");
		Mockito.when(sesionParametros.getCesionCheques()).thenReturn(gson.toJson(new DatosCesionDTO()));
		Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
		Mockito.when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(2));
		Respuesta<AltaChequesViewOut> res = manager.simularAltaCheques(new AltaChequesViewIn());
		res.getItemsMensajeRespuesta().get(0).setMensaje("Mensaje de error");
		Assert.assertTrue(EstadoRespuesta.ERROR.equals(res.getEstadoRespuesta()));
	}

	@Test
	public void simularAltaChequesWarningAlgunosRechazadosTest() {
		Gson gson = new Gson();
		ChequesSimuladosDTO dto = gson.fromJson(
				"{\"importeAcreditar\":20.00,\"importeTotal\":20.00,\"cuentaDebito\":{\"nroSucursal\":\"00\",\"nroCuentaProducto\":\"065489\"},\"numeroDeOperacion\":130211,\"chequesDescontados\":2,\"importeCheque\":20.00,\"importeImpuestos\":20.00,\"importeIntereses\":0.00,\"importeNeto\":20.00,\"listaAceptados\":[{\"numeroCheque\":\"01511210597490692534243001482448\",\"banco\":\"SANTANDER\",\"diasAAdelantar\":12,\"importeCheque\":10.00,\"importeImpuestos\":0.00,\"importeIntereses\":0.00,\"importeNeto\":10.00},{\"numeroCheque\":\"07200010002511000441000036165346\",\"banco\":\"SANTANDER\",\"diasAAdelantar\":8,\"importeCheque\":10.00,\"importeImpuestos\":0.00,\"importeIntereses\":0.00,\"importeNeto\":10.00}],\"listaRechazados\":[{\"numeroCheque\":\"07228016428810612987000032056314\",\"diasAAdelantar\":0,\"importeCheque\":3003.00,\"firmante\":\" 00.023.333.444\",\"fechaDePago\":\"Nov 2, 2016 12:00:00 AM\"},{\"numeroCheque\":\"07228016428810613034000032056314\",\"diasAAdelantar\":0,\"importeCheque\":1003.00,\"firmante\":\" 00.023.333.444\",\"fechaDePago\":\"Nov 2, 2016 12:00:00 AM\"}],\"comisionAdic\":0.00,\"tasaAplicada\":0.00,\"tasaEfectivaAnual\":0.00,\"costoFinancieroTotal\":0.00}",
				ChequesSimuladosDTO.class);
				for(ChequeSimuladoDTO resSimulado:dto.getListaAceptados()) {
					resSimulado.setFechaDePago(new Date());	
				}
		ItemMensajeRespuesta itemRespuesta = gson.fromJson(
				"{\"tipoError\":\"SIMULACION_ALTA_ALGUNOS_RECHAZADOS\",\"tag\":\"algunosRechazados\"}",
				ItemMensajeRespuesta.class);
		Respuesta<ChequesSimuladosDTO> resDto = respuestaFactory.crearRespuestaWarning(dto,
				new ArrayList<ItemMensajeRespuesta>());
		resDto.add(itemRespuesta);
		Mockito.when(simulacionBO.simularAltaCheques(Matchers.any(Cliente.class), Matchers.anyString(),
				Matchers.any(DatosCesionDTO.class), Matchers.any(Cuenta.class), Matchers.any(AltaChequesViewIn.class)))
				.thenReturn(resDto);
		Mockito.when(sesionParametros.getCuentaSeleccionadaParaTransferencia(Matchers.anyString()))
				.thenReturn("000-060162/3");
		Mockito.when(sesionParametros.getCesionCheques()).thenReturn(gson.toJson(new DatosCesionDTO()));
		Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
		Respuesta<AltaChequesViewOut> res = manager.simularAltaCheques(new AltaChequesViewIn());
		res.getItemsMensajeRespuesta().get(0).setMensaje("Mensaje de error");
		Assert.assertTrue(EstadoRespuesta.WARNING.equals(res.getEstadoRespuesta()));
	}

	@Test
	public void simularAltaChequesWarningTodosRechazadosTest() {
		Gson gson = new Gson();
		ChequesSimuladosDTO dto = gson.fromJson(
				"{\"importeAcreditar\":20.00,\"importeTotal\":20.00,\"cuentaDebito\":{\"nroSucursal\":\"00\",\"nroCuentaProducto\":\"065489\"},\"numeroDeOperacion\":130211,\"chequesDescontados\":2,\"importeCheque\":20.00,\"importeImpuestos\":20.00,\"importeIntereses\":0.00,\"importeNeto\":20.00,\"listaAceptados\":[{\"numeroCheque\":\"01511210597490692534243001482448\",\"banco\":\"SANTANDER\",\"diasAAdelantar\":12,\"importeCheque\":10.00,\"importeImpuestos\":0.00,\"importeIntereses\":0.00,\"importeNeto\":10.00},{\"numeroCheque\":\"07200010002511000441000036165346\",\"banco\":\"SANTANDER\",\"diasAAdelantar\":8,\"importeCheque\":10.00,\"importeImpuestos\":0.00,\"importeIntereses\":0.00,\"importeNeto\":10.00}],\"listaRechazados\":[{\"numeroCheque\":\"07228016428810612987000032056314\",\"diasAAdelantar\":0,\"importeCheque\":3003.00,\"firmante\":\" 00.023.333.444\",\"fechaDePago\":\"Nov 2, 2016 12:00:00 AM\"},{\"numeroCheque\":\"07228016428810613034000032056314\",\"diasAAdelantar\":0,\"importeCheque\":1003.00,\"firmante\":\" 00.023.333.444\",\"fechaDePago\":\"Nov 2, 2016 12:00:00 AM\"}],\"comisionAdic\":0.00,\"tasaAplicada\":0.00,\"tasaEfectivaAnual\":0.00,\"costoFinancieroTotal\":0.00}",
				ChequesSimuladosDTO.class);
				for(ChequeSimuladoDTO resSimulado:dto.getListaAceptados()) {
					resSimulado.setFechaDePago(new Date());	
				}
		ItemMensajeRespuesta itemRespuesta = gson.fromJson(
				"{\"tipoError\":\"SIMULACION_ALTA_TODOS_RECHAZADOS\",\"tag\":\"todosRechazados\"}",
				ItemMensajeRespuesta.class);
		Respuesta<ChequesSimuladosDTO> resDto = respuestaFactory.crearRespuestaWarning(dto,
				new ArrayList<ItemMensajeRespuesta>());
		resDto.add(itemRespuesta);
		Mockito.when(simulacionBO.simularAltaCheques(Matchers.any(Cliente.class), Matchers.anyString(),
				Matchers.any(DatosCesionDTO.class), Matchers.any(Cuenta.class), Matchers.any(AltaChequesViewIn.class)))
				.thenReturn(resDto);
		Mockito.when(sesionParametros.getCuentaSeleccionadaParaTransferencia(Matchers.anyString()))
				.thenReturn("000-060162/3");
		Mockito.when(sesionParametros.getCesionCheques()).thenReturn(gson.toJson(new DatosCesionDTO()));
		Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
		Respuesta<AltaChequesViewOut> res = manager.simularAltaCheques(new AltaChequesViewIn());
		res.getItemsMensajeRespuesta().get(0).setMensaje("Mensaje de error");
		res.getRespuesta().setListaAceptados(new ArrayList<AltaChequeAceptadoViewOut>());
		Assert.assertTrue(EstadoRespuesta.WARNING.equals(res.getEstadoRespuesta()));
	}

	@Test
	public void efectuarAltaChequesOkTest() {
		Gson gson = new Gson();
		ChequesAltaDTO dto = gson.fromJson(
				"{\"cuentaCredito\":{\"nroSucursal\":\"000\",\"nroCuentaProducto\":\"0601623\"},\"importeTotalCheque\":500.00,\"importeAAcreditar\":450.00,\"fechaAlta\":\"Nov 2, 2016 12:00:00 AM\",\"importeAcreditar\":450.00,\"importeTotal\":500.00,\"numeroDeOperacion\":218564,\"chequesDescontados\":10,\"importeImpuestos\":0.00,\"importeIntereses\":0.00,\"listaAceptados\":[{\"numeroCheque\":\"07200010361000016139000002306700\",\"banco\":\"SANTANDER\",\"diasAAdelantar\":0,\"importeImpuestos\":0.00,\"importeIntereses\":0.00,\"importeTotal\":50.00,\"importeAAcreditar\":50.00,\"fechaDePago\":\"Dec 1, 2017 12:00:00 AM\"},{\"numeroCheque\":\"07200010361963858252000001716700\",\"banco\":\"SANTANDER\",\"diasAAdelantar\":0,\"importeImpuestos\":0.00,\"importeIntereses\":0.00,\"importeTotal\":50.00,\"importeAAcreditar\":50.00,\"fechaDePago\":\"Dec 1, 2017 12:00:00 AM\"},{\"numeroCheque\":\"07200010361963858283000001716700\",\"banco\":\"SANTANDER\",\"diasAAdelantar\":0,\"importeImpuestos\":0.00,\"importeIntereses\":0.00,\"importeTotal\":50.00,\"importeAAcreditar\":50.00,\"fechaDePago\":\"Dec 1, 2017 12:00:00 AM\"},{\"numeroCheque\":\"07200010361963858498000001716700\",\"banco\":\"SANTANDER\",\"diasAAdelantar\":0,\"importeImpuestos\":0.00,\"importeIntereses\":0.00,\"importeTotal\":50.00,\"importeAAcreditar\":50.00,\"fechaDePago\":\"Dec 1, 2017 12:00:00 AM\"},{\"numeroCheque\":\"07200010361963858658000001716700\",\"banco\":\"SANTANDER\",\"diasAAdelantar\":0,\"importeImpuestos\":0.00,\"importeIntereses\":0.00,\"importeTotal\":50.00,\"importeAAcreditar\":50.00,\"fechaDePago\":\"Dec 1, 2017 12:00:00 AM\"},{\"numeroCheque\":\"07228016428810612987000032056314\",\"banco\":\"SANTANDER\",\"diasAAdelantar\":0,\"importeImpuestos\":0.00,\"importeIntereses\":0.00,\"importeTotal\":50.00,\"importeAAcreditar\":50.00,\"fechaDePago\":\"Dec 1, 2017 12:00:00 AM\"},{\"numeroCheque\":\"07228016428810613034000032056314\",\"banco\":\"SANTANDER\",\"diasAAdelantar\":0,\"importeImpuestos\":0.00,\"importeIntereses\":0.00,\"importeTotal\":50.00,\"importeAAcreditar\":50.00,\"fechaDePago\":\"Dec 1, 2017 12:00:00 AM\"},{\"numeroCheque\":\"07228016428810613164000032056314\",\"banco\":\"SANTANDER\",\"diasAAdelantar\":0,\"importeImpuestos\":0.00,\"importeIntereses\":0.00,\"importeTotal\":50.00,\"importeAAcreditar\":50.00,\"fechaDePago\":\"Dec 1, 2017 12:00:00 AM\"},{\"numeroCheque\":\"07228016428810613171000032056314\",\"banco\":\"SANTANDER\",\"diasAAdelantar\":0,\"importeImpuestos\":0.00,\"importeIntereses\":0.00,\"importeTotal\":50.00,\"importeAAcreditar\":50.00,\"fechaDePago\":\"Dec 1, 2017 12:00:00 AM\"},{\"numeroCheque\":\"07228016428810613218000032056314\",\"banco\":\"SANTANDER\",\"diasAAdelantar\":0,\"importeImpuestos\":0.00,\"importeIntereses\":0.00,\"importeTotal\":50.00,\"importeAAcreditar\":50.00,\"fechaDePago\":\"Dec 1, 2017 12:00:00 AM\"}],\"listaRechazados\":[],\"comisionAdic\":50.00,\"tasaAplicada\":\"0,00 %\",\"tasaEfectivaAnual\":\"0,00 %\",\"costoFinancieroTotal\":\"0,00 %\"}",
				ChequesAltaDTO.class);
		Respuesta<ChequesAltaDTO> resDto = respuestaFactory.crearRespuestaOk(ChequesAltaDTO.class, dto);
		Mockito.when(simulacionBO.efectuarAltaCheques(Matchers.any(Cliente.class), Matchers.anyString(),
				Matchers.anyString())).thenReturn(resDto);
		Mockito.when(sesionParametros.getCesionCheques()).thenReturn(gson.toJson(new DatosCesionDTO()));
		Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
		Mockito.when(sesionParametros.getCuentaSeleccionadaParaTransferencia(Matchers.anyString()))
				.thenReturn("000-060162/3");
		Respuesta<AltaChequesViewOut> res = manager.efectuarAltaCheques();
		res.getRespuesta().setMensajeFeedback("mensaje feedback");
		res.getRespuesta().setLegal1("legal1");
		res.getRespuesta().setLegal2("Esta comisión se verá reflejada en el resumen de cuenta");
		res.getRespuesta().setLegal3("Sujeto a confirmación de la presente operación de parte de la Sucursal respectiva.");
		res.getRespuesta().setLegal4("La tasa mencionada se encuentra sujeta a confirmación por parte de Banco Santander Río S.A. La simulación es al solo efecto de la consulta y no de carácter contractual.");
		Assert.assertTrue(EstadoRespuesta.OK.equals(res.getEstadoRespuesta()));
	}

	@Test
	public void efectuarAltaChequesErrorGenericoTest() {
		Gson gson = new Gson();
		Respuesta<ChequesSimuladosDTO> dto = gson.fromJson(
				"{\"estadoRespuesta\":\"ERROR\",\"itemsMensajeRespuesta\":[{\"tipoError\":\"ERROR_GENERICO\",\"tag\":\"errorGenerico\"}],\"respuestaVacia\":true,\"skipLog\":false,\"id\":-7745543608948764518}",
				Respuesta.class);
		Mockito.when(simulacionBO.simularAltaCheques(Matchers.any(Cliente.class), Matchers.anyString(),
				Matchers.any(DatosCesionDTO.class), Matchers.any(Cuenta.class), Matchers.any(AltaChequesViewIn.class)))
				.thenReturn(dto);
		Mockito.when(sesionParametros.getCuentaSeleccionadaParaTransferencia(Matchers.anyString()))
				.thenReturn("000-060162/3");
		Mockito.when(sesionParametros.getCesionCheques()).thenReturn(gson.toJson(new DatosCesionDTO()));
		Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
		Mockito.when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(2));
		Respuesta<AltaChequesViewOut> res = manager.simularAltaCheques(new AltaChequesViewIn());
		res.getItemsMensajeRespuesta().get(0).setMensaje("Mensaje de error");
		Assert.assertTrue(EstadoRespuesta.ERROR.equals(res.getEstadoRespuesta()));
	}
}
