package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.manager;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo.TitulosBO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteAceptacionCNV;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.FirmarCuentasViewRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.bo.ConfiguracionOrdenVentaTituloBO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.dto.ConfiguracionOrdenVentaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.dto.RespuestaConfiguracionOrdenVentaDTOMock;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.AceptacionCondicionesCuentasCustodiaViewIn;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.ConfiguracionOrdenVentaInView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.ConfiguracionOrdenVentaView;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;

@RunWith(MockitoJUnitRunner.class)
public class ConfiguracionOrdenVentaTitulosManagerTest {

	@InjectMocks
	private ConfiguracionOrdenVentaTituloManagerImpl configuracionOrdenVentaTituloManager;

	@Mock
	private SesionCliente sesionCliente;

	@Mock
	private ConfiguracionOrdenVentaTituloBO configuracionOrdenVentaTituloBO;

	@Mock
	private EstadisticaManager estadisticaManager;

	@Mock
	private MensajeBO mensajeBO;

	@Mock
	private CuentaManager cuentaManager;

	@Mock
	private SesionParametros sesionParametros;

	@Mock
	private TitulosBO titulosBO;

	@Mock
	private ReporteComprobantePDFBO reporteBO;
	
	@Mock
	private ModuloPermisoBO moduloPermisoBO;

	@Spy
	@InjectMocks
	private RespuestaFactory respuestaFactory = new RespuestaFactory();
	
	@Before
	public void init() {
		when(moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.VENTAS_SIN_VALIDAR_FIRMAS)).thenReturn(Mockito.mock(ModuloPermiso.class));
	}

	@Test
	public void configuracionOrdenVentaOk() {

		// When
		Cliente cliente = mock(Cliente.class);
		ContadorIntentos contador = mock(ContadorIntentos.class);

		ConfiguracionOrdenVentaInView datosEntrada = new ConfiguracionOrdenVentaInView();
		datosEntrada.setCodigoRossi("55451");
		datosEntrada.setNumeroCuentaTitulo("Cuenta títulos 14074/2");
		datosEntrada.setPlazo("48");
		datosEntrada.setIngresoDesdeGrilla(false);
		datosEntrada.setEsBancaPrivada(false);

		Respuesta<CuentasView> respuestaCuentas = new Respuesta<CuentasView>();
		respuestaCuentas.setEstadoRespuesta(EstadoRespuesta.OK);
		CuentasView cuentasView = new CuentasView();
		cuentasView.setCuentas(new ArrayList<CuentasAdhesionDebitoView>());
		respuestaCuentas.setRespuesta(cuentasView);

		when(sesionParametros.getContador()).thenReturn(contador);
		when(configuracionOrdenVentaTituloBO.obtenerConfiguracionOrdenVenta(Matchers.any(Cliente.class),
				Matchers.any(ConfiguracionOrdenVentaInView.class)))
						.thenReturn(RespuestaConfiguracionOrdenVentaDTOMock.armarRespuestaDTO());
		when(cuentaManager.getCuentasSaldo()).thenReturn(respuestaCuentas);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.VENTAS_SIN_VALIDAR_FIRMAS).tienePermisoDeVisibilidad()).thenReturn(true);

		// Then
		Respuesta<ConfiguracionOrdenVentaView> respuesta = configuracionOrdenVentaTituloManager
				.configuracionOrdenVenta(datosEntrada);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(
				EstadisticasConstants.ORDEN_VENTA_TITULOS_CONFIGURACION, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	@Test
	public void configuracionOrdenVentaBancaPrivadaOk() {

		// When
		Cliente cliente = mock(Cliente.class);
		ContadorIntentos contador = mock(ContadorIntentos.class);

		ConfiguracionOrdenVentaInView datosEntrada = new ConfiguracionOrdenVentaInView();
		datosEntrada.setCodigoRossi("55451");
		datosEntrada.setNumeroCuentaTitulo("Cuenta títulos 14074/2");
		datosEntrada.setPlazo("48");
		datosEntrada.setIngresoDesdeGrilla(false);
		datosEntrada.setEsBancaPrivada(true);

		Respuesta<CuentasView> respuestaCuentas = new Respuesta<CuentasView>();
		respuestaCuentas.setEstadoRespuesta(EstadoRespuesta.OK);
		CuentasView cuentasView = new CuentasView();
		cuentasView.setCuentas(new ArrayList<CuentasAdhesionDebitoView>());
		respuestaCuentas.setRespuesta(cuentasView);

		when(sesionParametros.getContador()).thenReturn(contador);
		when(configuracionOrdenVentaTituloBO.obtenerConfiguracionOrdenVenta(Matchers.any(Cliente.class),
				Matchers.any(ConfiguracionOrdenVentaInView.class)))
						.thenReturn(RespuestaConfiguracionOrdenVentaDTOMock.armarRespuestaDTO());
		when(cuentaManager.getCuentasSaldo()).thenReturn(respuestaCuentas);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.VENTAS_SIN_VALIDAR_FIRMAS).tienePermisoDeVisibilidad()).thenReturn(true);
		
		// Then
		Respuesta<ConfiguracionOrdenVentaView> respuesta = configuracionOrdenVentaTituloManager
				.configuracionOrdenVenta(datosEntrada);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(
				EstadisticasConstants.ORDEN_VENTA_TITULOS_CONFIGURACION_BPRIV,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

	}

	@Test
	public void configuracionOrdenVentaErrorCargaCuentas() {

		// When
		Cliente cliente = mock(Cliente.class);
		ContadorIntentos contador = mock(ContadorIntentos.class);

		ConfiguracionOrdenVentaInView datosEntrada = new ConfiguracionOrdenVentaInView();
		datosEntrada.setCodigoRossi("55451");
		datosEntrada.setNumeroCuentaTitulo("Cuenta títulos 14074/2");
		datosEntrada.setPlazo("48");
		datosEntrada.setIngresoDesdeGrilla(false);
		datosEntrada.setEsBancaPrivada(false);

		Respuesta<CuentasView> respuestaCuentas = new Respuesta<CuentasView>();
		respuestaCuentas.setEstadoRespuesta(EstadoRespuesta.ERROR);

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Error en cuentas");

		when(sesionParametros.getContador()).thenReturn(contador);
		when(configuracionOrdenVentaTituloBO.obtenerConfiguracionOrdenVenta(Matchers.any(Cliente.class),
				Matchers.any(ConfiguracionOrdenVentaInView.class)))
						.thenReturn(RespuestaConfiguracionOrdenVentaDTOMock.armarRespuestaDTO());
		when(cuentaManager.getCuentasSaldo()).thenReturn(respuestaCuentas);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(mensajeBO.obtenerMensajePorCodigo("10429")).thenReturn(mensaje);

		// Then
		Respuesta<ConfiguracionOrdenVentaView> respuesta = configuracionOrdenVentaTituloManager
				.configuracionOrdenVenta(datosEntrada);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(
				EstadisticasConstants.ORDEN_VENTA_TITULOS_CONFIGURACION,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);

	}

	@Test
	public void configuracionOrdenVentaOkDesdeGrilla() {

		// When
		Cliente cliente = mock(Cliente.class);
		ContadorIntentos contador = mock(ContadorIntentos.class);

		ConfiguracionOrdenVentaInView datosEntrada = new ConfiguracionOrdenVentaInView();
		datosEntrada.setCodigoRossi("55451");
		datosEntrada.setNumeroCuentaTitulo("Cuenta títulos 14074/2");
		datosEntrada.setPlazo("48");
		datosEntrada.setIngresoDesdeGrilla(true);
		datosEntrada.setEsBancaPrivada(false);

		Respuesta<CuentasView> respuestaCuentas = new Respuesta<CuentasView>();
		respuestaCuentas.setEstadoRespuesta(EstadoRespuesta.OK);
		CuentasView cuentasView = new CuentasView();
		cuentasView.setCuentas(new ArrayList<CuentasAdhesionDebitoView>());
		respuestaCuentas.setRespuesta(cuentasView);

		when(sesionParametros.getContador()).thenReturn(contador);
		when(configuracionOrdenVentaTituloBO.obtenerConfiguracionOrdenVenta(Matchers.any(Cliente.class),
				Matchers.any(ConfiguracionOrdenVentaInView.class)))
						.thenReturn(RespuestaConfiguracionOrdenVentaDTOMock.armarRespuestaDTO());
		when(cuentaManager.getCuentasSaldo()).thenReturn(respuestaCuentas);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.VENTAS_SIN_VALIDAR_FIRMAS).tienePermisoDeVisibilidad()).thenReturn(true);
		
		// Then
		Respuesta<ConfiguracionOrdenVentaView> respuesta = configuracionOrdenVentaTituloManager
				.configuracionOrdenVenta(datosEntrada);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(
				EstadisticasConstants.ACCESO_A_ORDEN_VENTA_BANCA_PERSONAL_DESDE_GRILLA,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	@Test
	public void configuracionOrdenVentaErrorServicioWeb() {

		// When
		Cliente cliente = mock(Cliente.class);

		ConfiguracionOrdenVentaInView datosEntrada = new ConfiguracionOrdenVentaInView();
		datosEntrada.setCodigoRossi("55451");
		datosEntrada.setNumeroCuentaTitulo("Cuenta títulos 14074/2");
		datosEntrada.setPlazo("48");
		datosEntrada.setEsBancaPrivada(false);

		Respuesta<CuentasView> respuestaCuentas = new Respuesta<CuentasView>();
		respuestaCuentas.setEstadoRespuesta(EstadoRespuesta.OK);
		CuentasView cuentasView = new CuentasView();
		cuentasView.setCuentas(new ArrayList<CuentasAdhesionDebitoView>());
		respuestaCuentas.setRespuesta(cuentasView);

		Respuesta<ConfiguracionOrdenVentaDTO> respuestaWebService = new Respuesta<ConfiguracionOrdenVentaDTO>();
		respuestaWebService.setEstadoRespuesta(EstadoRespuesta.ERROR);

		when(configuracionOrdenVentaTituloBO.obtenerConfiguracionOrdenVenta(Matchers.any(Cliente.class),
				Matchers.any(ConfiguracionOrdenVentaInView.class))).thenReturn(respuestaWebService);
		when(cuentaManager.getCuentasSaldo()).thenReturn(respuestaCuentas);
		when(sesionCliente.getCliente()).thenReturn(cliente);

		// Then
		Respuesta<ConfiguracionOrdenVentaView> respuesta = configuracionOrdenVentaTituloManager
				.configuracionOrdenVenta(datosEntrada);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(
				EstadisticasConstants.ORDEN_VENTA_TITULOS_CONFIGURACION,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);

	}

	@Test
	public void configuracionOrdenVentaErrorServicioWebIngresoDesdeGrilla() {

		// When
		Cliente cliente = mock(Cliente.class);

		ConfiguracionOrdenVentaInView datosEntrada = new ConfiguracionOrdenVentaInView();
		datosEntrada.setCodigoRossi("55451");
		datosEntrada.setNumeroCuentaTitulo("Cuenta títulos 14074/2");
		datosEntrada.setPlazo("48");
		datosEntrada.setIngresoDesdeGrilla(true);
		datosEntrada.setEsBancaPrivada(false);

		Respuesta<CuentasView> respuestaCuentas = new Respuesta<CuentasView>();
		respuestaCuentas.setEstadoRespuesta(EstadoRespuesta.OK);
		CuentasView cuentasView = new CuentasView();
		cuentasView.setCuentas(new ArrayList<CuentasAdhesionDebitoView>());
		respuestaCuentas.setRespuesta(cuentasView);

		Respuesta<ConfiguracionOrdenVentaDTO> respuestaWebService = new Respuesta<ConfiguracionOrdenVentaDTO>();
		respuestaWebService.setEstadoRespuesta(EstadoRespuesta.ERROR);

		when(configuracionOrdenVentaTituloBO.obtenerConfiguracionOrdenVenta(Matchers.any(Cliente.class),
				Matchers.any(ConfiguracionOrdenVentaInView.class))).thenReturn(respuestaWebService);
		when(cuentaManager.getCuentasSaldo()).thenReturn(respuestaCuentas);
		when(sesionCliente.getCliente()).thenReturn(cliente);

		// Then
		Respuesta<ConfiguracionOrdenVentaView> respuesta = configuracionOrdenVentaTituloManager
				.configuracionOrdenVenta(datosEntrada);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(
				EstadisticasConstants.ACCESO_A_ORDEN_VENTA_BANCA_PERSONAL_DESDE_GRILLA,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
	}

	@Test
	public void aceptacionResolucionOrigenDeFondos() {

		configuracionOrdenVentaTituloManager.aceptacionResolucionOrigenDeFondos(false);
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(
				EstadisticasConstants.ORDEN_VENTA_ACEPTACION_RESOLUCION_ORIGEN_FONDOS,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	@Test
	public void aceptacionResolucionOrigenDeFondosBancaPrivada() {

		configuracionOrdenVentaTituloManager.aceptacionResolucionOrigenDeFondos(true);
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(
				EstadisticasConstants.ORDEN_VENTA_ACEPTACION_RESOLUCION_ORIGEN_FONDOS_BPRIV,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	@Test
	public void aceptacionCondicionesCuentasOrdenVentaOK() {

		// When
		AceptacionCondicionesCuentasCustodiaViewIn aceptacionView = new AceptacionCondicionesCuentasCustodiaViewIn();
		aceptacionView.setEsBancaPrivada(false);

		Respuesta<String> respuesta = new Respuesta<String>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);

		when(titulosBO.firmarCuentas(Matchers.any(Cliente.class), Matchers.any(FirmarCuentasViewRequest.class)))
				.thenReturn(respuesta);

		// Then
		configuracionOrdenVentaTituloManager.aceptacionCondicionesCuentasOrdenVenta(aceptacionView);
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(
				EstadisticasConstants.ORDEN_VENTA_ACEPTACION_CONDICIONES_CUENTAS,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

	}
	
	@Test
	public void aceptacionCondicionesCuentasOrdenVentaBancaPrivadaOK() {

		// When
		AceptacionCondicionesCuentasCustodiaViewIn aceptacionView = new AceptacionCondicionesCuentasCustodiaViewIn();
		aceptacionView.setEsBancaPrivada(true);

		Respuesta<String> respuesta = new Respuesta<String>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);

		when(titulosBO.firmarCuentas(Matchers.any(Cliente.class), Matchers.any(FirmarCuentasViewRequest.class)))
				.thenReturn(respuesta);

		// Then
		configuracionOrdenVentaTituloManager.aceptacionCondicionesCuentasOrdenVenta(aceptacionView);
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(
				EstadisticasConstants.ORDEN_VENTA_ACEPTACION_CONDICIONES_CUENTAS_BPRIV,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}


	@Test
	public void aceptacionCondicionesCuentasOrdenVentaError() {

		// When
		AceptacionCondicionesCuentasCustodiaViewIn aceptacionView = new AceptacionCondicionesCuentasCustodiaViewIn();
		aceptacionView.setEsBancaPrivada(false);

		Respuesta<String> respuesta = new Respuesta<String>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);

		when(titulosBO.firmarCuentas(Matchers.any(Cliente.class), Matchers.any(FirmarCuentasViewRequest.class)))
				.thenReturn(respuesta);

		// Then
		configuracionOrdenVentaTituloManager.aceptacionCondicionesCuentasOrdenVenta(aceptacionView);
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(
				EstadisticasConstants.ORDEN_VENTA_ACEPTACION_CONDICIONES_CUENTAS,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
	}

	@Test
	public void aceptacionCondicionesCuentasOrdenVentaBancaPrivadaError() {

		// When
		AceptacionCondicionesCuentasCustodiaViewIn aceptacionView = new AceptacionCondicionesCuentasCustodiaViewIn();
		aceptacionView.setEsBancaPrivada(true);

		Respuesta<String> respuesta = new Respuesta<String>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);

		when(titulosBO.firmarCuentas(Matchers.any(Cliente.class), Matchers.any(FirmarCuentasViewRequest.class)))
				.thenReturn(respuesta);

		// Then
		configuracionOrdenVentaTituloManager.aceptacionCondicionesCuentasOrdenVenta(aceptacionView);
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(
				EstadisticasConstants.ORDEN_VENTA_ACEPTACION_CONDICIONES_CUENTAS_BPRIV,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
	}


	@Test
	public void descargarComprobanteAceptacionCNVOrdenVentaOK() throws IOException {

		// When
		Respuesta<Reporte> respuestaReporte = new Respuesta<Reporte>();
		Reporte reporte = new Reporte();
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] byteArray = outStream.toByteArray();
		reporte.setBytes(byteArray);
		reporte.setNombre("PDF");
		reporte.setTipoArchivo(TipoArchivoEnum.PDF);
		respuestaReporte.setRespuesta(reporte);
		respuestaReporte.setEstadoRespuesta(EstadoRespuesta.OK);

		when(reporteBO.obtenerComprobantePDF(Matchers.any(ComprobanteAceptacionCNV.class)))
				.thenReturn(respuestaReporte);

		// Then
		Respuesta<ReporteView> respuesta = configuracionOrdenVentaTituloManager
				.descargarComprobanteAceptacionCNVOrdenVenta(false);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(
				EstadisticasConstants.ORDEN_VENTA_DESCARGA_COMPROBANTE_CNV,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

	}
	
	@Test
	public void descargarComprobanteAceptacionCNVOrdenVentaBancaPrivadaOK() throws IOException {

		// When
		Respuesta<Reporte> respuestaReporte = new Respuesta<Reporte>();
		Reporte reporte = new Reporte();
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] byteArray = outStream.toByteArray();
		reporte.setBytes(byteArray);
		reporte.setNombre("PDF");
		reporte.setTipoArchivo(TipoArchivoEnum.PDF);
		respuestaReporte.setRespuesta(reporte);
		respuestaReporte.setEstadoRespuesta(EstadoRespuesta.OK);

		when(reporteBO.obtenerComprobantePDF(Matchers.any(ComprobanteAceptacionCNV.class)))
				.thenReturn(respuestaReporte);

		// Then
		Respuesta<ReporteView> respuesta = configuracionOrdenVentaTituloManager
				.descargarComprobanteAceptacionCNVOrdenVenta(true);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(
				EstadisticasConstants.ORDEN_VENTA_DESCARGA_COMPROBANTE_CNV_BPRIV,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	@Test
	public void descargarComprobanteAceptacionCNVOrdenVentaError() throws IOException {

		// When
		Respuesta<Reporte> respuestaReporte = new Respuesta<Reporte>();
		respuestaReporte.setRespuestaVacia(true);
		respuestaReporte.setEstadoRespuesta(EstadoRespuesta.ERROR);

		when(reporteBO.obtenerComprobantePDF(Matchers.any(ComprobanteAceptacionCNV.class)))
				.thenReturn(respuestaReporte);

		// Then
		Respuesta<ReporteView> respuesta = configuracionOrdenVentaTituloManager
				.descargarComprobanteAceptacionCNVOrdenVenta(false);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(
				EstadisticasConstants.ORDEN_VENTA_DESCARGA_COMPROBANTE_CNV,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		
	}
	
	@Test
	public void descargarComprobanteAceptacionCNVOrdenVentaBancaPrivadaError() throws IOException {

		// When
		Respuesta<Reporte> respuestaReporte = new Respuesta<Reporte>();
		respuestaReporte.setRespuestaVacia(true);
		respuestaReporte.setEstadoRespuesta(EstadoRespuesta.ERROR);

		when(reporteBO.obtenerComprobantePDF(Matchers.any(ComprobanteAceptacionCNV.class)))
				.thenReturn(respuestaReporte);

		// Then
		Respuesta<ReporteView> respuesta = configuracionOrdenVentaTituloManager
				.descargarComprobanteAceptacionCNVOrdenVenta(true);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(
				EstadisticasConstants.ORDEN_VENTA_DESCARGA_COMPROBANTE_CNV_BPRIV,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		
	}


}