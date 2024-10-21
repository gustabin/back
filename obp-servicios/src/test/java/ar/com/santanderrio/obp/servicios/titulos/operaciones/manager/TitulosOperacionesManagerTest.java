package ar.com.santanderrio.obp.servicios.titulos.operaciones.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Calendar;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaBancaPrivada;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.bo.TitulosOperacionesBO;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.dto.OperacionTitulosDTO;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.manager.impl.SesionTitulosOperaciones;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.manager.impl.TitulosOperacionesManagerImpl;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.CuentasOperativasView;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.DetalleOperacionCompraVentaView;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.OperacionesTitulosView;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.ParametrosOperacionesView;

@RunWith(MockitoJUnitRunner.class)
public class TitulosOperacionesManagerTest {

	@Mock
	SesionCliente sesionCliente;

	@Mock
	SesionParametros sesionParametros;

	@Mock
	TitulosOperacionesBO titulosOperacionesBO;

	@Mock
	EstadisticaManager estadisticaManager;

	@Spy
	private SesionTitulosOperaciones sesionTitulosOperaciones;

	@Spy
	@InjectMocks
	RespuestaFactory respuestaFactory = new RespuestaFactory();

	@Mock
	MensajeBO mensajeBO;

	@InjectMocks
	TitulosOperacionesManager titulosOperacionesManager = new TitulosOperacionesManagerImpl();

	@Mock
	ReporteComprobantePDFBO reporteBO;

	@Test
	public void obtenerOperacionesPrimeraVezTest() {

		Cliente cliente = new Cliente();
		cliente.setNup("123456");
		ParametrosOperacionesView parametrosOperacionesRecibidas = new ParametrosOperacionesView();
		parametrosOperacionesRecibidas.setTipoOperacion("compra");
		parametrosOperacionesRecibidas.setNumeroCuenta("123-123456/7");
		parametrosOperacionesRecibidas.setBanca("BP");
		parametrosOperacionesRecibidas.setBuscador(false);
		ParametrosOperacionesView parametrosOperacionesSesion = new ParametrosOperacionesView();
		parametrosOperacionesSesion.setTipoOperacion("compra");
		parametrosOperacionesSesion.setNumeroCuenta("123-123456/7");
		parametrosOperacionesSesion.setFechaDesde("25/03/2018");
		parametrosOperacionesSesion.setFechaHasta("27/04/2018");
		sesionTitulosOperaciones.setParametrosOperacionesView(parametrosOperacionesSesion);
		sesionTitulosOperaciones.setNumeroConsulta(0);
		Respuesta<List<OperacionTitulosDTO>> respuestaListaOperacionesTitulosDTO = new Respuesta<List<OperacionTitulosDTO>>();
		respuestaListaOperacionesTitulosDTO.setEstadoRespuesta(EstadoRespuesta.OK);
		OperacionTitulosDTO operacionTituloDTO = crearOperacionDeCompra();
		OperacionTitulosDTO operacionTituloDTO2 = crearOperacionDeCompra();

		List<OperacionTitulosDTO> listaOperaciones = new ArrayList<OperacionTitulosDTO>();
		listaOperaciones.add(operacionTituloDTO);
		listaOperaciones.add(operacionTituloDTO2);
		respuestaListaOperacionesTitulosDTO.setRespuesta(listaOperaciones);

		Mensaje mensaje = new Mensaje();
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(titulosOperacionesBO.obtenerOperacionesCompraVenta(Matchers.any(ParametrosOperacionesView.class),
				Matchers.any(Cliente.class))).thenReturn(respuestaListaOperacionesTitulosDTO);

		Respuesta<OperacionesTitulosView> respuesta = titulosOperacionesManager
				.obtenerOperacionesPrimeraVez(parametrosOperacionesRecibidas);

		assertNotNull(respuesta);
		assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
		assertEquals(respuesta.getRespuesta().getOperaciones().get(0).getMoneda(), "Dólares");
		assertEquals(respuesta.getRespuesta().getOperaciones().get(0).getTipo(), "Acciones");
		assertEquals(respuesta.getRespuesta().getOperaciones().get(1).getMoneda(), "Dólares");
		assertEquals(respuesta.getRespuesta().getOperaciones().get(1).getPrecio(), "u$s 20,00");
	}

	@Test
	public void obtenerOperacionesPrimeraVezIgualNumeroDeCuentaDistintaOperacionTest() {

		Cliente cliente = new Cliente();
		cliente.setNup("123456");
		ParametrosOperacionesView parametrosOperacionesSesion = new ParametrosOperacionesView();
		parametrosOperacionesSesion.setTipoOperacion("compra");
		parametrosOperacionesSesion.setNumeroCuenta("123-123456/7");

		sesionTitulosOperaciones.setParametrosOperacionesView(parametrosOperacionesSesion);
		ParametrosOperacionesView parametrosOperacionesRecibidas = new ParametrosOperacionesView();
		parametrosOperacionesRecibidas.setTipoOperacion("venta");
		parametrosOperacionesRecibidas.setNumeroCuenta("123-123456/7");
		parametrosOperacionesRecibidas.setBanca("BP");
		parametrosOperacionesRecibidas.setBuscador(false);
		sesionTitulosOperaciones.setNumeroConsulta(0);
		Respuesta<List<OperacionTitulosDTO>> respuestaListaOperacionesTitulosDTO = new Respuesta<List<OperacionTitulosDTO>>();
		respuestaListaOperacionesTitulosDTO.setEstadoRespuesta(EstadoRespuesta.OK);
		OperacionTitulosDTO operacionTituloDTO = crearOperacionDeCompra();
		OperacionTitulosDTO operacionTituloDTO2 = crearOperacionDeVenta();

		List<OperacionTitulosDTO> listaOperaciones = new ArrayList<OperacionTitulosDTO>();
		listaOperaciones.add(operacionTituloDTO);
		listaOperaciones.add(operacionTituloDTO2);
		respuestaListaOperacionesTitulosDTO.setRespuesta(listaOperaciones);

		Mensaje mensaje = new Mensaje();
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(titulosOperacionesBO.obtenerOperacionesCompraVenta(Matchers.any(ParametrosOperacionesView.class),
				Matchers.any(Cliente.class))).thenReturn(respuestaListaOperacionesTitulosDTO);

		Respuesta<OperacionesTitulosView> respuesta = titulosOperacionesManager
				.obtenerOperacionesPrimeraVez(parametrosOperacionesRecibidas);

		assertNotNull(respuesta);
		assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
		assertEquals(respuesta.getRespuesta().getOperaciones().get(0).getMoneda(), "Pesos");
		assertEquals(respuesta.getRespuesta().getOperaciones().get(0).getTipo(), "Acciones");
		assertEquals(respuesta.getRespuesta().getOperaciones().get(0).getPrecio(), "$ 30,00");
		verify(titulosOperacionesBO).limpiarCache(cliente);
	}

	@Test
	public void obtenerOperacionesPrimeraVezFechaRecibidaNullTest() {

		Cliente cliente = new Cliente();
		cliente.setNup("123456");
		ParametrosOperacionesView parametrosOperacionesSesion = new ParametrosOperacionesView();
		parametrosOperacionesSesion.setTipoOperacion("compra");
		parametrosOperacionesSesion.setNumeroCuenta("123-123456/7");
		parametrosOperacionesSesion.setFechaDesde("28/08/2018");
		parametrosOperacionesSesion.setFechaHasta("29/08/2018");
		sesionTitulosOperaciones.setParametrosOperacionesView(parametrosOperacionesSesion);
		sesionTitulosOperaciones.setNumeroConsulta(0);

		ParametrosOperacionesView parametrosOperacionesRecibidas = new ParametrosOperacionesView();
		parametrosOperacionesRecibidas.setTipoOperacion("compra");
		parametrosOperacionesRecibidas.setNumeroCuenta("123-123456/7");
		parametrosOperacionesRecibidas.setFechaDesde(null);
		parametrosOperacionesRecibidas.setFechaHasta(null);
		parametrosOperacionesRecibidas.setBanca("BP");
		parametrosOperacionesRecibidas.setBuscador(false);

		Respuesta<List<OperacionTitulosDTO>> respuestaListaOperacionesTitulosDTO = new Respuesta<List<OperacionTitulosDTO>>();
		respuestaListaOperacionesTitulosDTO.setEstadoRespuesta(EstadoRespuesta.OK);
		OperacionTitulosDTO operacionTituloDTO = crearOperacionDeCompra();
		OperacionTitulosDTO operacionTituloDTO2 = crearOperacionDeVenta();

		List<OperacionTitulosDTO> listaOperaciones = new ArrayList<OperacionTitulosDTO>();
		listaOperaciones.add(operacionTituloDTO);
		listaOperaciones.add(operacionTituloDTO2);
		respuestaListaOperacionesTitulosDTO.setRespuesta(listaOperaciones);

		Mensaje mensaje = new Mensaje();
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(titulosOperacionesBO.obtenerOperacionesCompraVenta(Matchers.any(ParametrosOperacionesView.class),
				Matchers.any(Cliente.class))).thenReturn(respuestaListaOperacionesTitulosDTO);

		Respuesta<OperacionesTitulosView> respuesta = titulosOperacionesManager
				.obtenerOperacionesPrimeraVez(parametrosOperacionesRecibidas);

		assertNotNull(respuesta);
		assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
		assertEquals(respuesta.getRespuesta().getOperaciones().get(0).getMoneda(), "Dólares");
		assertEquals(respuesta.getRespuesta().getOperaciones().get(0).getTipo(), "Acciones");
		verify(titulosOperacionesBO).limpiarCache(cliente);
	}

	@Test
	public void obtenerOperacionesPrimeraVezFechaSesionYRecibidaNullTest() {

		Cliente cliente = new Cliente();
		cliente.setNup("123456");
		ParametrosOperacionesView parametrosOperacionesSesion = new ParametrosOperacionesView();
		parametrosOperacionesSesion.setTipoOperacion("compra");
		parametrosOperacionesSesion.setNumeroCuenta("123-123456/7");
		sesionTitulosOperaciones.setParametrosOperacionesView(parametrosOperacionesSesion);
		sesionTitulosOperaciones.setNumeroConsulta(0);

		ParametrosOperacionesView parametrosOperacionesRecibidas = new ParametrosOperacionesView();
		parametrosOperacionesRecibidas.setTipoOperacion("compra");
		parametrosOperacionesRecibidas.setNumeroCuenta("123-123456/7");
		parametrosOperacionesRecibidas.setBanca("BP");
		parametrosOperacionesRecibidas.setBuscador(false);

		Respuesta<List<OperacionTitulosDTO>> respuestaListaOperacionesTitulosDTO = new Respuesta<List<OperacionTitulosDTO>>();
		respuestaListaOperacionesTitulosDTO.setEstadoRespuesta(EstadoRespuesta.OK);
		OperacionTitulosDTO operacionTituloDTO = crearOperacionDeCompra();
		OperacionTitulosDTO operacionTituloDTO2 = crearOperacionDeCompra();
		Calendar calendar = Calendar.getInstance();
		Date fechaHoy = calendar.getTime();
		operacionTituloDTO.setFecha(fechaHoy);
		operacionTituloDTO2.setFecha(fechaHoy);

		List<OperacionTitulosDTO> listaOperaciones = new ArrayList<OperacionTitulosDTO>();
		listaOperaciones.add(operacionTituloDTO);
		listaOperaciones.add(operacionTituloDTO2);
		respuestaListaOperacionesTitulosDTO.setRespuesta(listaOperaciones);

		Mensaje mensaje = new Mensaje();
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(titulosOperacionesBO.obtenerOperacionesCompraVenta(Matchers.any(ParametrosOperacionesView.class),
				Matchers.any(Cliente.class))).thenReturn(respuestaListaOperacionesTitulosDTO);

		Respuesta<OperacionesTitulosView> respuesta = titulosOperacionesManager
				.obtenerOperacionesPrimeraVez(parametrosOperacionesRecibidas);

		assertNotNull(respuesta);
		assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
		assertEquals(respuesta.getRespuesta().getOperaciones().get(0).getMoneda(), "Dólares");
		assertEquals(respuesta.getRespuesta().getOperaciones().get(0).getTipo(), "Acciones");
		assertEquals(respuesta.getRespuesta().getOperaciones().get(1).getMoneda(), "Dólares");
		assertEquals(respuesta.getRespuesta().getOperaciones().get(1).getPrecio(), "u$s 20,00");
		verify(titulosOperacionesBO).limpiarCache(cliente);
	}

	@Test
	public void obtenerOperacionesPrimeraVezCambioEnFechaRecibidaTest() {

		Cliente cliente = new Cliente();
		cliente.setNup("123456");
		ParametrosOperacionesView parametrosOperacionesSesion = new ParametrosOperacionesView();
		parametrosOperacionesSesion.setTipoOperacion("compra");
		parametrosOperacionesSesion.setNumeroCuenta("123-123456/7");
		parametrosOperacionesSesion.setFechaDesde("28/08/2018");
		parametrosOperacionesSesion.setFechaHasta("29/08/2018");
		sesionTitulosOperaciones.setParametrosOperacionesView(parametrosOperacionesSesion);
		sesionTitulosOperaciones.setNumeroConsulta(0);

		ParametrosOperacionesView parametrosOperacionesRecibidas = new ParametrosOperacionesView();
		parametrosOperacionesRecibidas.setTipoOperacion("compra");
		parametrosOperacionesRecibidas.setNumeroCuenta("123-123456/7");
		parametrosOperacionesRecibidas.setBanca("BP");
		parametrosOperacionesRecibidas.setBuscador(false);

		Respuesta<List<OperacionTitulosDTO>> respuestaListaOperacionesTitulosDTO = new Respuesta<List<OperacionTitulosDTO>>();
		respuestaListaOperacionesTitulosDTO.setEstadoRespuesta(EstadoRespuesta.OK);
		OperacionTitulosDTO operacionTituloDTO = crearOperacionDeCompra();
		OperacionTitulosDTO operacionTituloDTO2 = crearOperacionDeCompra();

		List<OperacionTitulosDTO> listaOperaciones = new ArrayList<OperacionTitulosDTO>();
		listaOperaciones.add(operacionTituloDTO);
		listaOperaciones.add(operacionTituloDTO2);
		respuestaListaOperacionesTitulosDTO.setRespuesta(listaOperaciones);

		Mensaje mensaje = new Mensaje();
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(titulosOperacionesBO.obtenerOperacionesCompraVenta(Matchers.any(ParametrosOperacionesView.class),
				Matchers.any(Cliente.class))).thenReturn(respuestaListaOperacionesTitulosDTO);

		Respuesta<OperacionesTitulosView> respuesta = titulosOperacionesManager
				.obtenerOperacionesPrimeraVez(parametrosOperacionesRecibidas);

		assertNotNull(respuesta);
		assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
		assertEquals(respuesta.getRespuesta().getOperaciones().get(0).getMoneda(), "Dólares");
		assertEquals(respuesta.getRespuesta().getOperaciones().get(0).getTipo(), "Acciones");
		assertEquals(respuesta.getRespuesta().getOperaciones().get(1).getMoneda(), "Dólares");
		assertEquals(respuesta.getRespuesta().getOperaciones().get(1).getPrecio(), "u$s 20,00");
		verify(titulosOperacionesBO).limpiarCache(cliente);
	}

	@Test
	public void obtenerOperacionesConFiltrosVarios() {

		Cliente cliente = new Cliente();
		cliente.setNup("123456");
		sesionTitulosOperaciones.setNumeroConsulta(0);

		ParametrosOperacionesView parametrosOperacionesSesion = new ParametrosOperacionesView();
		parametrosOperacionesSesion.setTipoOperacion("compra");
		parametrosOperacionesSesion.setNumeroCuenta("123-123456/7");
		sesionTitulosOperaciones.setParametrosOperacionesView(parametrosOperacionesSesion);
		ParametrosOperacionesView parametrosOperacionesRecibidas = new ParametrosOperacionesView();
		parametrosOperacionesRecibidas.setTipoOperacion("compra");
		parametrosOperacionesRecibidas.setNumeroCuenta("123-123456/7");
		parametrosOperacionesRecibidas.setNominalesDesde("10.50");
		parametrosOperacionesRecibidas.setNominalesHasta("30");
		parametrosOperacionesRecibidas.setTipoEspecie("todas");
		parametrosOperacionesRecibidas.setBanca("BP");
		parametrosOperacionesRecibidas.setBuscador(false);

		Respuesta<List<OperacionTitulosDTO>> respuestaListaOperacionesTitulosDTO = new Respuesta<List<OperacionTitulosDTO>>();
		respuestaListaOperacionesTitulosDTO.setEstadoRespuesta(EstadoRespuesta.OK);
		OperacionTitulosDTO operacionTituloDTO = crearOperacionDeCompra();
		OperacionTitulosDTO operacionTituloDTO2 = crearOperacionDeCompra();

		List<OperacionTitulosDTO> listaOperaciones = new ArrayList<OperacionTitulosDTO>();
		listaOperaciones.add(operacionTituloDTO);
		listaOperaciones.add(operacionTituloDTO2);
		respuestaListaOperacionesTitulosDTO.setRespuesta(listaOperaciones);

		Mensaje mensaje = new Mensaje();
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(titulosOperacionesBO.obtenerOperacionesCompraVenta(Matchers.any(ParametrosOperacionesView.class),
				Matchers.any(Cliente.class))).thenReturn(respuestaListaOperacionesTitulosDTO);

		Respuesta<OperacionesTitulosView> respuesta = titulosOperacionesManager
				.obtenerOperacionesPrimeraVez(parametrosOperacionesRecibidas);

		assertNotNull(respuesta);
		assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
		assertEquals(respuesta.getRespuesta().getOperaciones().get(0).getMoneda(), "Dólares");
		assertEquals(respuesta.getRespuesta().getOperaciones().get(0).getTipo(), "Acciones");
		assertEquals(respuesta.getRespuesta().getOperaciones().get(1).getMoneda(), "Dólares");
		assertEquals(respuesta.getRespuesta().getOperaciones().get(1).getPrecio(), "u$s 20,00");
	}

	@Test
	public void obtenerCuentasOperativasTest() {

		Cliente cliente = new Cliente();
		List<CuentaBancaPrivada> cuentasBancaPrivada = new ArrayList<CuentaBancaPrivada>();
		CuentaBancaPrivada cuentaBancaPrivada = new CuentaBancaPrivada();
		Cuenta cuenta = new Cuenta();
		List<Interviniente> intervinientes = new ArrayList<Interviniente>();
		Interviniente interviniente = new Interviniente();

		interviniente.setApellido("Montaña");
		interviniente.setNombre("Alejo");
		intervinientes.add(interviniente);
		cuenta.setIntervinientes(intervinientes);
		cuenta.setNroSucursal("123");
		cuenta.setNroCuentaProducto("1234567");
		cuentaBancaPrivada.setCuentaOperativa(cuenta);
		cuentasBancaPrivada.add(cuentaBancaPrivada);
		cliente.setCuentasBancaPrivada(cuentasBancaPrivada);
		ParametrosOperacionesView parametrosOperacionesView = new ParametrosOperacionesView();
		parametrosOperacionesView.setBanca("BP");

		when(sesionCliente.getCliente()).thenReturn(cliente);

		Respuesta<List<CuentasOperativasView>> respuesta = titulosOperacionesManager
				.obtenerCuentasOperativas(parametrosOperacionesView);

		assertNotNull(respuesta);
		assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
		assertEquals(respuesta.getRespuesta().get(0).getNumeroCuenta(), "123-123456/7");
		assertEquals(respuesta.getRespuesta().get(0).getIntervinientes().get(0).getApellido(), "Montaña");
	}

	private OperacionTitulosDTO crearOperacionDeCompra() {
		OperacionTitulosDTO operacionTituloDTO = new OperacionTitulosDTO();
		operacionTituloDTO.setTipoOperacion("C");
		operacionTituloDTO.setCuenta("1234567");
		operacionTituloDTO.setTipo("Acciones");
		operacionTituloDTO.setMoneda("D");
		Calendar calendario = Calendar.getInstance();
		calendario.add(Calendar.DATE, -15);
		operacionTituloDTO.setFecha(calendario.getTime());
		operacionTituloDTO.setCantidadNominales(20.0);
		operacionTituloDTO.setPrecio(20.0);
		operacionTituloDTO.setNumero("1212121212");
		operacionTituloDTO.setDescripcion("accion de telecom 1");
		return operacionTituloDTO;
	}

	private OperacionTitulosDTO crearOperacionDeVenta() {
		OperacionTitulosDTO operacionTitulosDTO = new OperacionTitulosDTO();
		operacionTitulosDTO.setTipoOperacion("V");
		operacionTitulosDTO.setCuenta("1234567");
		operacionTitulosDTO.setTipo("Acciones");
		operacionTitulosDTO.setMoneda("P");
		Calendar calendario = Calendar.getInstance();
		calendario.add(Calendar.DATE, -15);
		operacionTitulosDTO.setFecha(calendario.getTime());
		operacionTitulosDTO.setCantidadNominales(30.0);
		operacionTitulosDTO.setPrecio(30.0);
		operacionTitulosDTO.setNumero("123131313133");
		operacionTitulosDTO.setDescripcion("accion de telecom 2");
		return operacionTitulosDTO;
	}

	@Test
	public void descargaDetalleOperacionCompraOKTest() {
		when(sesionParametros.getDetalleComprobanteView()).thenReturn(new DetalleOperacionCompraVentaView());

		Respuesta<Reporte> respuestaReporte = respuestaFactory.crearRespuestaOk(Reporte.class);
		when(reporteBO.obtenerComprobantePDF(Mockito.any(DetalleOperacionCompraVentaView.class)))
				.thenReturn(respuestaReporte);

		Respuesta<ReporteView> respuestaView = titulosOperacionesManager.descargarComprobanteDetalleOperacion("compra");
		verify(estadisticaManager).add(EstadisticasConstants.PDF_TITULOS_CONSULTA_OPERACION_COMPRA,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		assertEquals(EstadoRespuesta.OK, respuestaView.getEstadoRespuesta());
	}

	@Test
	public void descargaDetalleOperacionVentaErrorTest() {
		when(sesionParametros.getDetalleComprobanteView()).thenReturn(new DetalleOperacionCompraVentaView());

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("msj prueba");
		when(mensajeBO.obtenerMensajePorCodigoConErrorGenerico(Matchers.anyString())).thenReturn(mensaje);

		Respuesta<Reporte> respuestaReporte = new Respuesta<Reporte>();
		respuestaReporte.setEstadoRespuesta(EstadoRespuesta.ERROR);
		when(reporteBO.obtenerComprobantePDF(Mockito.any(DetalleOperacionCompraVentaView.class)))
				.thenReturn(respuestaReporte);

		Respuesta<ReporteView> respuestaView = titulosOperacionesManager.descargarComprobanteDetalleOperacion("venta");
		verify(estadisticaManager).add(EstadisticasConstants.PDF_TITULOS_CONSULTA_OPERACION_VENTA,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		assertEquals(EstadoRespuesta.ERROR, respuestaView.getEstadoRespuesta());
	}
}
