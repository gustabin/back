package ar.com.santanderrio.obp.servicios.prestamos.bo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.google.gson.Gson;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.bo.ClienteBO;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.login.bo.MyaBO;
import ar.com.santanderrio.obp.servicios.login.dto.MyaDTOIn;
import ar.com.santanderrio.obp.servicios.login.dto.MyaDTOOut;
import ar.com.santanderrio.obp.servicios.prestamos.bo.impl.PrestamosRsaBOImpl;
import ar.com.santanderrio.obp.servicios.prestamos.dto.SimuladorPrestamoDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.SolicitudPrestamoDTO;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaUpdateUserRequestData;
import ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager;

@RunWith(MockitoJUnitRunner.class)
public class PrestamosRsaBOImplTest {

	private SolicitudPrestamoDTO solicitudDTO = new Gson().fromJson(
			"{\"montoTotal\":1547000,\"cuenta\":{\"codigo\":\"00720013007000114307\",\"numero\":\"007000114307\",\"sucursal\":\"0013\",\"moneda\":\"ars\",\"tipo\":\"09\"},\"codigoProducto\":35,\"codigoSubProducto\":16,\"sucursalPaquete\":\"0013\",\"destinoFondos\":35002,\"cantidadCuotas\":48,\"fechaAprobacion\":\"2023-08-18\",\"fechaFormalizacion\":\"2023-08-18\",\"tna\":115,\"lineaUva\":false,\"tipoOferta\":\"PREACORDADO\",\"disponible\":1547821,\"fechaPrimerVencimiento\":\"2023-09-17\",\"tokenSimulacion\":\"VTJGc2RHVmtYMStGUTU1bXZYaXF2dVF1YVErY3FEY0RNRnYrcVNaTnVWcFNseWxhQUs4NFRwa3dWcUFST0pxa3VncEhqMDB4SEtud2NMWG8wN0Y4SEgyZ0F5OEsvbEJzN3BxVjAwc09rNFdTQkJvM0xkVzA2UmxDejF6UzZTQkk0TFZDeFptUEZSUlBRN3gyei9ROGtpcWJWSnRYdCtsWkkrODhLa2hDNGprdUdRcVRGSDFrQXZTRUZYMldBREpTTzBKLzh5SUNtRjNicFdhSCs0NTZmNnM3WjVqRzJoaWd4cW5PYU1rRnNCZ3hzOU0vNVAyTC9zUFY2bHBRNlU1UmkyQUUxcUI5Ym1oNWEyRjIxekxlQUpDYUJQc05CUFQ1aTRFbmV6QUZVc1UzRmJzQmlNMXVTcGZKYWJvYUVwS3ZaZ3FPdzhJcE9aYmxOTEYyQW51SCtnPT0=\",\"numeroPaquete\":\"090000431752\",\"codigoContable\":\"Z11\"}",
			SolicitudPrestamoDTO.class);

	@Mock
	private RsaManager rsaManager;

	@Mock
	private AutentificacionManager autentificacionManager;

	@Mock
	private EstadisticaManager estadisticaManager;

	@Mock
	protected MyaBO myaBO;

	@Mock
	private SesionCliente sesionCliente;

	@Mock
	private ClienteBO clienteBO;

	@Mock
	private RespuestaFactory respuestaFactory;

	@InjectMocks
	private PrestamosRsaBOImpl prestamosRsaBO;

	@SuppressWarnings("rawtypes")
	private Respuesta respuestaExitosa = Mockito.mock(Respuesta.class);
	@SuppressWarnings("rawtypes")
	private Respuesta respuestaWarning = Mockito.mock(Respuesta.class);
	@SuppressWarnings("rawtypes")
	private Respuesta respuestaError = Mockito.mock(Respuesta.class);

	@Before
	public void init() throws BusinessException {
		MockitoAnnotations.initMocks(this);
		Cuenta cuenta = new Cuenta();
		cuenta.setNroSucursal("0013");
		cuenta.setNroCuentaProducto("007000114307");
		cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
		Cliente cliente = new Cliente();
		cliente.setNup("30477004");
		cliente.setCuentas(Collections.singletonList(cuenta));
		cliente.setFechaNacimiento("19900101");

		Mockito.when(respuestaExitosa.getEstadoRespuesta()).thenReturn(EstadoRespuesta.OK);
		Mockito.when(respuestaWarning.getEstadoRespuesta()).thenReturn(EstadoRespuesta.WARNING);
		Mockito.when(respuestaError.getEstadoRespuesta()).thenReturn(EstadoRespuesta.ERROR);

		List<BigDecimal> listAntiguedades = new ArrayList<BigDecimal>();
		listAntiguedades.add(BigDecimal.valueOf(12l));
		listAntiguedades.add(BigDecimal.valueOf(12l));
		Respuesta<List<BigDecimal>> antiguedades = new Respuesta<List<BigDecimal>>();
		antiguedades.setEstadoRespuesta(EstadoRespuesta.OK);
		antiguedades.setRespuesta(listAntiguedades);

		Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
		Mockito.when(clienteBO.obtenerAntiguedadDiasUltCambioClaveToken(anyLong())).thenReturn(antiguedades);
		ReflectionTestUtils.setField(prestamosRsaBO, "valorDesafioPrestamo", "2");
	}

	@SuppressWarnings("unchecked")
	@Test
	public void errorRsaDeshabilitado() {
		Mockito.when(sesionCliente.getTieneTokenRSA()).thenReturn(false);
		Mockito.when(respuestaFactory.crearRespuestaError(Mockito.anyString(), Mockito.any(TipoError.class),
				Mockito.anyString())).thenReturn(respuestaError);
		Respuesta<SimuladorPrestamoDTO> respuesta = prestamosRsaBO.validarRsa(solicitudDTO, null, "1", false);
		assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void validarRsaNuevoDesafioRespuestaAllow() {
		Mockito.when(sesionCliente.getTieneTokenRSA()).thenReturn(true);
		Mockito.when(
				respuestaFactory.transformar(Mockito.any(SimuladorPrestamoDTO.class), Mockito.any(Respuesta.class)))
				.thenReturn((Respuesta) respuestaExitosa);
		Mockito.when(
				autentificacionManager.verificarEstadoDesafio(Mockito.any(AutentificacionDTO.class), Mockito.eq(2)))
				.thenReturn((Respuesta) respuestaExitosa);
		Mockito.when(autentificacionManager.ejecutarValidacionRSA(Mockito.any(AutentificacionDTO.class)))
				.thenReturn(respuestaExitosa);
		Respuesta<SimuladorPrestamoDTO> respuesta = prestamosRsaBO.validarRsa(solicitudDTO, null, "1", false);
		assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void validarRsaNuevoDesafioRespuestaChallenge() {
		Mockito.when(sesionCliente.getTieneTokenRSA()).thenReturn(true);
		Mockito.when(
				respuestaFactory.transformar(Mockito.any(SimuladorPrestamoDTO.class), Mockito.any(Respuesta.class)))
				.thenReturn(respuestaWarning);

		MyaDTOOut myaout = Mockito.mock(MyaDTOOut.class);
		Mockito.when(myaBO.consultaWsEstadoClienteV3(Mockito.any(Cliente.class), Mockito.any(MyaDTOIn.class)))
				.thenReturn(myaout);
		Mockito.when(myaout.getFechaModificadoCelular()).thenReturn("10");
		Mockito.when(myaout.getFechaModificadoEmail()).thenReturn("11");
		Mockito.when(
				autentificacionManager.verificarEstadoDesafio(Mockito.any(AutentificacionDTO.class), Mockito.eq(2)))
				.thenReturn((Respuesta) respuestaWarning);
		Mockito.when(autentificacionManager.ejecutarValidacionRSA(Mockito.any(AutentificacionDTO.class)))
				.thenReturn(respuestaWarning);
		Respuesta<SimuladorPrestamoDTO> respuesta = prestamosRsaBO.validarRsa(solicitudDTO, null, "1", false);
		assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void validarRsaNuevoDesafioRespuestaSinDesafio() {
		Mockito.when(sesionCliente.getTieneTokenRSA()).thenReturn(true);

		MyaDTOOut myaout = Mockito.mock(MyaDTOOut.class);
		Mockito.when(myaBO.consultaWsEstadoClienteV3(Mockito.any(Cliente.class), Mockito.any(MyaDTOIn.class)))
				.thenReturn(myaout);
		Mockito.when(myaout.getFechaModificadoCelular()).thenReturn("10");
		Mockito.when(myaout.getFechaModificadoEmail()).thenReturn("11");
		Mockito.when(
				autentificacionManager.verificarEstadoDesafio(Mockito.any(AutentificacionDTO.class), Mockito.eq(2)))
				.thenReturn((Respuesta) respuestaError);
		Mockito.when(autentificacionManager.ejecutarValidacionRSA(Mockito.any(AutentificacionDTO.class)))
				.thenReturn(respuestaError);

		ItemMensajeRespuesta item = new ItemMensajeRespuesta(TipoError.SIN_METODO_DESAFIO.toString());
		item.setTipoError(TipoError.SIN_METODO_DESAFIO.toString());
		List<ItemMensajeRespuesta> errors = new ArrayList<ItemMensajeRespuesta>();
		errors.add(item);

		Mockito.when(respuestaError.getItemsMensajeRespuesta()).thenReturn(errors);
		Mockito.when(respuestaFactory.crearRespuestaErrorPersonalizado(Mockito.eq(SimuladorPrestamoDTO.class),
				Mockito.anyString(), Mockito.anyString())).thenReturn(respuestaError);
		Respuesta<SimuladorPrestamoDTO> respuesta = prestamosRsaBO.validarRsa(solicitudDTO, null, "1", false);
		assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
		assertEquals(TipoError.SIN_METODO_DESAFIO.toString(),
				respuesta.getItemsMensajeRespuesta().get(0).getTipoError());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void validarRsaNuevoDesafioRespuestaBloquearUsuarioOk() {
		Mockito.when(sesionCliente.getTieneTokenRSA()).thenReturn(true);

		MyaDTOOut myaout = Mockito.mock(MyaDTOOut.class);
		Mockito.when(myaBO.consultaWsEstadoClienteV3(Mockito.any(Cliente.class), Mockito.any(MyaDTOIn.class)))
				.thenReturn(myaout);
		Mockito.when(myaout.getFechaModificadoCelular()).thenReturn("10");
		Mockito.when(myaout.getFechaModificadoEmail()).thenReturn("11");
		Mockito.when(
				autentificacionManager.verificarEstadoDesafio(Mockito.any(AutentificacionDTO.class), Mockito.eq(2)))
				.thenReturn((Respuesta) respuestaError);
		Mockito.when(autentificacionManager.ejecutarValidacionRSA(Mockito.any(AutentificacionDTO.class)))
				.thenReturn(respuestaError);

		ItemMensajeRespuesta item = new ItemMensajeRespuesta(TipoError.RSA_BLOQUEAR_USUARIO.toString());
		item.setTipoError(TipoError.RSA_BLOQUEAR_USUARIO.toString());
		List<ItemMensajeRespuesta> errors = new ArrayList<ItemMensajeRespuesta>();
		errors.add(item);

		Mockito.when(respuestaError.getItemsMensajeRespuesta()).thenReturn(errors);
		AutentificacionDTO mockAuth = Mockito.mock(AutentificacionDTO.class);
		Mockito.when(respuestaError.getRespuesta()).thenReturn(mockAuth);
		Mockito.when(mockAuth.getBloquearUsuario()).thenReturn(true);
		Mockito.when(rsaManager.updateUser(Mockito.any(RsaUpdateUserRequestData.class))).thenReturn(respuestaExitosa);

		Mockito.when(respuestaFactory.crearRespuestaError(Mockito.eq(StringUtils.EMPTY),
				Mockito.eq(TipoError.RSA_BLOQUEAR_USUARIO),
				Mockito.eq(CodigoMensajeConstantes.RSA_LOGIN_USUARIO_BLOQUEADO))).thenReturn(respuestaError);
		Respuesta<SimuladorPrestamoDTO> respuesta = prestamosRsaBO.validarRsa(solicitudDTO, null, "1", false);
		assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
		assertEquals(TipoError.RSA_BLOQUEAR_USUARIO.toString(),
				respuesta.getItemsMensajeRespuesta().get(0).getTipoError());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void validarRsaNuevoDesafioRespuestaBloquearUsuarioError() {
		Mockito.when(sesionCliente.getTieneTokenRSA()).thenReturn(true);

		MyaDTOOut myaout = Mockito.mock(MyaDTOOut.class);
		Mockito.when(myaBO.consultaWsEstadoClienteV3(Mockito.any(Cliente.class), Mockito.any(MyaDTOIn.class)))
				.thenReturn(myaout);
		Mockito.when(myaout.getFechaModificadoCelular()).thenReturn("10");
		Mockito.when(myaout.getFechaModificadoEmail()).thenReturn("11");
		Mockito.when(
				autentificacionManager.verificarEstadoDesafio(Mockito.any(AutentificacionDTO.class), Mockito.eq(2)))
				.thenReturn((Respuesta) respuestaError);
		Mockito.when(autentificacionManager.ejecutarValidacionRSA(Mockito.any(AutentificacionDTO.class)))
				.thenReturn(respuestaError);

		ItemMensajeRespuesta item = new ItemMensajeRespuesta(TipoError.RSA_OFFLINE.toString());
		item.setTipoError(TipoError.RSA_OFFLINE.toString());
		List<ItemMensajeRespuesta> errors = new ArrayList<ItemMensajeRespuesta>();
		errors.add(item);

		Mockito.when(respuestaError.getItemsMensajeRespuesta()).thenReturn(errors);
		AutentificacionDTO mockAuth = Mockito.mock(AutentificacionDTO.class);
		Mockito.when(respuestaError.getRespuesta()).thenReturn(mockAuth);
		Mockito.when(mockAuth.getBloquearUsuario()).thenReturn(true);
		Mockito.when(rsaManager.updateUser(Mockito.any(RsaUpdateUserRequestData.class))).thenReturn(respuestaError);

		Mockito.when(respuestaFactory.crearRespuestaError(Mockito.eq(StringUtils.EMPTY),
				Mockito.eq(TipoError.RSA_OFFLINE), Mockito.eq(CodigoMensajeConstantes.ERROR_GENERICO_RSA_OFFLINE)))
				.thenReturn(respuestaError);
		Respuesta<SimuladorPrestamoDTO> respuesta = prestamosRsaBO.validarRsa(solicitudDTO, null, "1", false);
		assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
		assertEquals(TipoError.RSA_OFFLINE.toString(), respuesta.getItemsMensajeRespuesta().get(0).getTipoError());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void validarRsaDesafioExistenteRespuestaChallenge() {
		Mockito.when(sesionCliente.getTieneTokenRSA()).thenReturn(true);
		Mockito.when(
				respuestaFactory.transformar(Mockito.any(SimuladorPrestamoDTO.class), Mockito.any(Respuesta.class)))
				.thenReturn(respuestaWarning);
		Mockito.when(
				autentificacionManager.verificarEstadoDesafio(Mockito.any(AutentificacionDTO.class), Mockito.eq(2)))
				.thenReturn((Respuesta) respuestaWarning);
		Mockito.when(autentificacionManager.ejecutarValidacionRSA(Mockito.any(AutentificacionDTO.class)))
				.thenReturn(respuestaWarning);
		Respuesta<SimuladorPrestamoDTO> respuesta = prestamosRsaBO.validarRsa(solicitudDTO, null, "1", true);
		assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
	}

}
