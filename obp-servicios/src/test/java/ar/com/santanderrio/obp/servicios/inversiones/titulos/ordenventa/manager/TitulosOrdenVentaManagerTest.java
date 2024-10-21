package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.manager;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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

import ar.com.santanderrio.obp.base.bo.BusinessException;
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
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaBancaPrivada;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.bo.TituloOrdenVentaBO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.dto.CompraVentaTitulosValoresDTOMock;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.dto.ConsultaDeTenenciaResponseDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.dto.ConsultaDeTenenciaResponseDTOMock;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.dto.ConsultaSuscripcionSaldoPDCDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.dto.ConsultaSuscripcionSaldoPDCDTOMock;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.ComprobanteOrdenVenta;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.DatosConsultaDeTenenciaResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.DatosTenencia;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.ConfirmacionVentaTitulosInView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.ConfirmacionVentaTitulosView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.IngresoOrdenVentaInView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.TituloView;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuentaMock;

/**
 * The Class TitulosOrdenVentaManagerTest.
 *
 * @author mariano.g.pulera
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class TitulosOrdenVentaManagerTest {

	@InjectMocks
	private TitulosOrdenVentaManagerImpl titulosOrdenVentaManager;

	@Mock
	private SesionCliente sesionCliente;

	@Mock
	private TituloOrdenVentaBO tituloOrdenVentaBO;

	@Mock
	private EstadisticaManager estadisticaManager;

	@Mock
	private MensajeBO mensajeBO;

	@Mock
	private CuentaManager cuentaManager;

	@Mock
	private SesionParametros sessionParametros;

	@Mock
	private ReporteComprobantePDFBO reporteBO;

	@Spy
	@InjectMocks
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	@Test
	public void ingresoOrdenVentaOk() throws BusinessException {

		// When
		Cliente cliente = ClienteMock.completarInfoCliente();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		Cuenta cuenta = CuentaMock.completarInfoCuenta();
		cuentas.add(cuenta);
		cliente.setCuentasRetail(cuentas);

		IngresoOrdenVentaInView ingresoView = new IngresoOrdenVentaInView();
		ingresoView.setEsBancaPrivada(Boolean.FALSE);

		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(tituloOrdenVentaBO.consultarCuentasTitulosWebService(Matchers.any(Cliente.class),
				Matchers.anyListOf(Cuenta.class), Matchers.anyBoolean()))
						.thenReturn(ConsultaDeTenenciaResponseDTOMock.armarConsultaDeTenenciaResponseDTO());

		// Then
		Respuesta<List<TituloView>> respuesta = titulosOrdenVentaManager.ingresoOrdenVenta(ingresoView);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(
				EstadisticasConstants.INGRESO_FUNCION_VENTA_TITULOS_VALORES,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

	}

	@Test
	public void ingresoOrdenVentaBancaPrivadaOk() throws BusinessException {

		// When
		Cliente cliente = ClienteMock.completarInfoCliente();

		List<CuentaBancaPrivada> cuentasBP = new ArrayList<CuentaBancaPrivada>();
		CuentaBancaPrivada cuentaBP = new CuentaBancaPrivada();
		cuentaBP.setCuentaOperativa(CuentaMock.completarInfoCuenta());
		cuentaBP.setCuentaTitulo(CuentaMock.completarInfoCuenta());
		cuentasBP.add(cuentaBP);
		cliente.setCuentasBancaPrivada(cuentasBP);

		IngresoOrdenVentaInView ingresoView = new IngresoOrdenVentaInView();
		ingresoView.setEsBancaPrivada(Boolean.TRUE);

		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(tituloOrdenVentaBO.consultarCuentasTitulosWebService(Matchers.any(Cliente.class),
				Matchers.anyListOf(Cuenta.class), Matchers.anyBoolean()))
						.thenReturn(ConsultaDeTenenciaResponseDTOMock.armarConsultaDeTenenciaResponseDTO());

		// Then
		Respuesta<List<TituloView>> respuesta = titulosOrdenVentaManager.ingresoOrdenVenta(ingresoView);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(
				EstadisticasConstants.INGRESO_FUNCION_VENTA_TITULOS_VALORES_BPRIV,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	@Test
	public void ingresoOrdenVentaErrorNoHayTitulos() throws BusinessException {

		// When
		Cliente cliente = ClienteMock.completarInfoCliente();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		Cuenta cuenta = CuentaMock.completarInfoCuenta();
		cuentas.add(cuenta);
		cliente.setCuentasRetail(cuentas);

		ConsultaDeTenenciaResponseDTO consultaDeTenencia = new ConsultaDeTenenciaResponseDTO();
		DatosConsultaDeTenenciaResponse datos = new DatosConsultaDeTenenciaResponse();
		List<DatosTenencia> listaTenencia = new ArrayList<DatosTenencia>();
		datos.setListaTenencia(listaTenencia);
		consultaDeTenencia.setDatos(datos);

		IngresoOrdenVentaInView ingresoView = new IngresoOrdenVentaInView();
		ingresoView.setEsBancaPrivada(Boolean.FALSE);

		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(tituloOrdenVentaBO.consultarCuentasTitulosWebService(Matchers.any(Cliente.class),
				Matchers.anyListOf(Cuenta.class), Matchers.anyBoolean())).thenReturn(consultaDeTenencia);

		// Then
		Respuesta<List<TituloView>> respuesta = titulosOrdenVentaManager.ingresoOrdenVenta(ingresoView);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(
				EstadisticasConstants.INGRESO_FUNCION_VENTA_TITULOS_VALORES,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
	}

	@Test
	public void ingresoOrdenVentaBancaPrivadaErrorNoHayTitulos() throws BusinessException {

		// When
		Cliente cliente = ClienteMock.completarInfoCliente();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		Cuenta cuenta = CuentaMock.completarInfoCuenta();
		cuentas.add(cuenta);
		cliente.setCuentasRetail(cuentas);

		ConsultaDeTenenciaResponseDTO consultaDeTenencia = new ConsultaDeTenenciaResponseDTO();
		DatosConsultaDeTenenciaResponse datos = new DatosConsultaDeTenenciaResponse();
		List<DatosTenencia> listaTenencia = new ArrayList<DatosTenencia>();
		datos.setListaTenencia(listaTenencia);
		consultaDeTenencia.setDatos(datos);

		IngresoOrdenVentaInView ingresoView = new IngresoOrdenVentaInView();
		ingresoView.setEsBancaPrivada(Boolean.TRUE);

		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(tituloOrdenVentaBO.consultarCuentasTitulosWebService(Matchers.any(Cliente.class),
				Matchers.anyListOf(Cuenta.class), Matchers.anyBoolean())).thenReturn(consultaDeTenencia);

		// Then
		Respuesta<List<TituloView>> respuesta = titulosOrdenVentaManager.ingresoOrdenVenta(ingresoView);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(
				EstadisticasConstants.INGRESO_FUNCION_VENTA_TITULOS_VALORES_BPRIV,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void ingresoOrdenVentaErrorBusinessException() throws BusinessException {

		// When
		Cliente cliente = ClienteMock.completarInfoCliente();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		Cuenta cuenta = CuentaMock.completarInfoCuenta();
		cuentas.add(cuenta);
		cliente.setCuentasRetail(cuentas);

		Mensaje mensajeError = new Mensaje();
		mensajeError.setMensaje("Error inesperado al consultar web service");

		IngresoOrdenVentaInView ingresoView = new IngresoOrdenVentaInView();
		ingresoView.setEsBancaPrivada(Boolean.FALSE);

		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(tituloOrdenVentaBO.consultarCuentasTitulosWebService(Matchers.any(Cliente.class),
				Matchers.anyListOf(Cuenta.class), Matchers.anyBoolean())).thenThrow(BusinessException.class);
		when(mensajeBO.obtenerMensajePorCodigo("10429")).thenReturn(mensajeError);

		// Then
		Respuesta<List<TituloView>> respuesta = titulosOrdenVentaManager.ingresoOrdenVenta(ingresoView);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(
				EstadisticasConstants.INGRESO_FUNCION_VENTA_TITULOS_VALORES,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void ingresoOrdenVentaBancaPrivadaErrorBusinessException() throws BusinessException {

		// When
		Cliente cliente = ClienteMock.completarInfoCliente();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		Cuenta cuenta = CuentaMock.completarInfoCuenta();
		cuentas.add(cuenta);
		cliente.setCuentasRetail(cuentas);

		Mensaje mensajeError = new Mensaje();
		mensajeError.setMensaje("Error inesperado al consultar web service");

		IngresoOrdenVentaInView ingresoView = new IngresoOrdenVentaInView();
		ingresoView.setEsBancaPrivada(Boolean.TRUE);

		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(tituloOrdenVentaBO.consultarCuentasTitulosWebService(Matchers.any(Cliente.class),
				Matchers.anyListOf(Cuenta.class), Matchers.anyBoolean())).thenThrow(BusinessException.class);
		when(mensajeBO.obtenerMensajePorCodigo("10429")).thenReturn(mensajeError);

		// Then
		Respuesta<List<TituloView>> respuesta = titulosOrdenVentaManager.ingresoOrdenVenta(ingresoView);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(
				EstadisticasConstants.INGRESO_FUNCION_VENTA_TITULOS_VALORES_BPRIV,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);

	}

	@Test
	public void confirmacionOrdenVentaOK() throws BusinessException {

		// When
		ConfirmacionVentaTitulosInView confirmacionInView = new ConfirmacionVentaTitulosInView();
		confirmacionInView.setFechaLiquidacion("2018-05-07T00:00:00-03:00");
		confirmacionInView.setCuentaTitulo("1323393/8");
		confirmacionInView.setEsBancaPrivada(false);

		when(tituloOrdenVentaBO.ejecutarMetodoCompraVentaTitulosValores(Matchers.any(Cliente.class),
				Matchers.any(ConfirmacionVentaTitulosInView.class), Matchers.anyString()))
						.thenReturn(CompraVentaTitulosValoresDTOMock.armarMockCompraVentaTitulosValores());

		when(tituloOrdenVentaBO.consultarDatosSuscripcionSaldoPDC(Matchers.any(Cliente.class),
				Matchers.any(ConfirmacionVentaTitulosInView.class)))
						.thenReturn(ConsultaSuscripcionSaldoPDCDTOMock.armarConsultaSuscripcionSaldoPDCDTOMock());

		// Then
		Respuesta<ConfirmacionVentaTitulosView> respuesta = titulosOrdenVentaManager
				.confirmacionOrdenVenta(confirmacionInView);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

	}

	@Test
	public void confirmacionOrdenVentaOKPoderCompraAdherido() throws BusinessException {

		// When
		ConfirmacionVentaTitulosInView confirmacionInView = new ConfirmacionVentaTitulosInView();
		confirmacionInView.setFechaLiquidacion("2018-05-07T00:00:00-03:00");
		confirmacionInView.setCuentaTitulo("1323393/8");
		confirmacionInView.setEsBancaPrivada(false);

		when(tituloOrdenVentaBO.ejecutarMetodoCompraVentaTitulosValores(Matchers.any(Cliente.class),
				Matchers.any(ConfirmacionVentaTitulosInView.class), Matchers.anyString()))
						.thenReturn(CompraVentaTitulosValoresDTOMock.armarMockCompraVentaTitulosValores());

		ConsultaSuscripcionSaldoPDCDTO saldoPoderDeCompra = ConsultaSuscripcionSaldoPDCDTOMock
				.armarConsultaSuscripcionSaldoPDCDTOMock();
		saldoPoderDeCompra.getDatos().getListaCuentas().get(0).setIndicadorPDC("AD");

		when(tituloOrdenVentaBO.consultarDatosSuscripcionSaldoPDC(Matchers.any(Cliente.class),
				Matchers.any(ConfirmacionVentaTitulosInView.class))).thenReturn(saldoPoderDeCompra);

		// Then
		Respuesta<ConfirmacionVentaTitulosView> respuesta = titulosOrdenVentaManager
				.confirmacionOrdenVenta(confirmacionInView);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

	}

	@SuppressWarnings("unchecked")
	@Test
	public void confirmacionOrdenVentaBusinessException() throws BusinessException {

		// When
		ConfirmacionVentaTitulosInView confirmacionInView = new ConfirmacionVentaTitulosInView();
		confirmacionInView.setFechaLiquidacion("2018-05-07T00:00:00-03:00");
		confirmacionInView.setCuentaTitulo("1323393/8");
		confirmacionInView.setEsBancaPrivada(false);

		when(tituloOrdenVentaBO.ejecutarMetodoCompraVentaTitulosValores(Matchers.any(Cliente.class),
				Matchers.any(ConfirmacionVentaTitulosInView.class), Matchers.anyString()))
						.thenThrow(BusinessException.class);

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Error generico");

		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ORDEN_VENTA_TITULOS_ERROR_SERVICIO))
				.thenReturn(mensaje);

		// Then
		Respuesta<ConfirmacionVentaTitulosView> respuesta = titulosOrdenVentaManager
				.confirmacionOrdenVenta(confirmacionInView);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());

	}

	@Test
	public void ejecutarOrdenVentaOK() throws BusinessException {

		// When
		ConfirmacionVentaTitulosInView confirmacionInView = new ConfirmacionVentaTitulosInView();
		confirmacionInView.setFechaLiquidacion("2018-05-07T00:00:00-03:00");
		confirmacionInView.setCuentaTitulo("1323393/8");
		confirmacionInView.setNombreEspecie("Tenaris");
		confirmacionInView.setCodigoEspecie("TS");
		confirmacionInView.setPlazo("48 hs");
		confirmacionInView.setMonedaOperacion("Pesos");
		confirmacionInView.setCotizacion("28");
		confirmacionInView.setFechaReferencia("08/05/2018");
		confirmacionInView.setHoraReferencia("15:05");
		confirmacionInView.setCantidadTitulo("10");
		confirmacionInView.setCotizacionLimite("15,50");
		confirmacionInView.setNumeroCuenta("32029192");
		confirmacionInView.setTipoCuenta("CUP");

		when(tituloOrdenVentaBO.ejecutarMetodoCompraVentaTitulosValores(Matchers.any(Cliente.class),
				Matchers.any(ConfirmacionVentaTitulosInView.class), Matchers.anyString()))
						.thenReturn(CompraVentaTitulosValoresDTOMock.armarMockCompraVentaTitulosValores());

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("MensajeFeedback");

		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.FEEDBACK_VENTA_TITULOS_OK)).thenReturn(mensaje);

		// Then
		Respuesta<ConfirmacionVentaTitulosView> respuesta = titulosOrdenVentaManager
				.ejecutarOrdenVenta(confirmacionInView);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

	}
	
	@Test
	public void ejecutarOrdenVentaBancaPrivadaOK() throws BusinessException {

		// When
		ConfirmacionVentaTitulosInView confirmacionInView = new ConfirmacionVentaTitulosInView();
		confirmacionInView.setFechaLiquidacion("2018-05-07T00:00:00-03:00");
		confirmacionInView.setCuentaTitulo("1323393/8");
		confirmacionInView.setNombreEspecie("Tenaris");
		confirmacionInView.setCodigoEspecie("TS");
		confirmacionInView.setPlazo("48 hs");
		confirmacionInView.setMonedaOperacion("Pesos");
		confirmacionInView.setCotizacion("28");
		confirmacionInView.setFechaReferencia("08/05/2018");
		confirmacionInView.setHoraReferencia("15:05");
		confirmacionInView.setCantidadTitulo("10");
		confirmacionInView.setCotizacionLimite("15,50");
		confirmacionInView.setNumeroCuenta("32029192");
		confirmacionInView.setTipoCuenta("CUP");
		confirmacionInView.setEsBancaPrivada(true);
		
		when(tituloOrdenVentaBO.ejecutarMetodoCompraVentaTitulosValores(Matchers.any(Cliente.class),
				Matchers.any(ConfirmacionVentaTitulosInView.class), Matchers.anyString()))
						.thenReturn(CompraVentaTitulosValoresDTOMock.armarMockCompraVentaTitulosValores());

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("MensajeFeedback");

		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.FEEDBACK_VENTA_TITULOS_OK)).thenReturn(mensaje);

		// Then
		Respuesta<ConfirmacionVentaTitulosView> respuesta = titulosOrdenVentaManager
				.ejecutarOrdenVenta(confirmacionInView);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

	}

	@SuppressWarnings("unchecked")
	@Test
	public void ejecutarOrdenVentaBusinessException() throws BusinessException {

		// When
		ConfirmacionVentaTitulosInView confirmacionInView = new ConfirmacionVentaTitulosInView();
		confirmacionInView.setFechaLiquidacion("2018-05-07T00:00:00-03:00");
		confirmacionInView.setCuentaTitulo("1323393/8");

		when(tituloOrdenVentaBO.ejecutarMetodoCompraVentaTitulosValores(Matchers.any(Cliente.class),
				Matchers.any(ConfirmacionVentaTitulosInView.class), Matchers.anyString()))
						.thenThrow(BusinessException.class);

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Error generico");

		ContadorIntentos contador = mock(ContadorIntentos.class);

		when(sessionParametros.getContador()).thenReturn(contador);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.FEEDBACK_VENTA_TITULOS_ERROR))
				.thenReturn(mensaje);

		// Then
		Respuesta<ConfirmacionVentaTitulosView> respuesta = titulosOrdenVentaManager
				.ejecutarOrdenVenta(confirmacionInView);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());

	}

    @Test
    public void descargaComprobantePDFBancaRetailOK() throws IOException {

        // When
        IngresoOrdenVentaInView descargaInView = new IngresoOrdenVentaInView();
        descargaInView.setEsBancaPrivada(false);
        Respuesta<Reporte> respuestaReporte = new Respuesta<Reporte>();
        Reporte reporte = new Reporte();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] byteArray = outStream.toByteArray();
        reporte.setBytes(byteArray);
        reporte.setNombre("PDF");
        reporte.setTipoArchivo(TipoArchivoEnum.PDF);
        respuestaReporte.setRespuesta(reporte);
        respuestaReporte.setEstadoRespuesta(EstadoRespuesta.OK);

        ComprobanteOrdenVenta comprobante = new ComprobanteOrdenVenta();
        when(sessionParametros.getComprobanteOrdenVenta()).thenReturn(comprobante);
        when(reporteBO.obtenerComprobantePDF(Matchers.any(ComprobanteOrdenVenta.class))).thenReturn(respuestaReporte);

        // Then
        Respuesta<ReporteView> respuesta = titulosOrdenVentaManager.descargarComprobanteOrdenVentaPDF(descargaInView);

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

    }

    @Test
    public void descargaComprobantePDFBancaRetailError() throws IOException {

        // When
        IngresoOrdenVentaInView descargaInView = new IngresoOrdenVentaInView();
        descargaInView.setEsBancaPrivada(false);
        Respuesta<Reporte> respuestaReporte = new Respuesta<Reporte>();
        Reporte reporte = new Reporte();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] byteArray = outStream.toByteArray();
        reporte.setBytes(byteArray);
        reporte.setNombre("PDF");
        reporte.setTipoArchivo(TipoArchivoEnum.PDF);
        respuestaReporte.setRespuesta(reporte);
        respuestaReporte.setEstadoRespuesta(EstadoRespuesta.ERROR);

        ComprobanteOrdenVenta comprobante = new ComprobanteOrdenVenta();
        when(sessionParametros.getComprobanteOrdenVenta()).thenReturn(comprobante);
        when(reporteBO.obtenerComprobantePDF(Matchers.any(ComprobanteOrdenVenta.class))).thenReturn(respuestaReporte);

        // Then
        Respuesta<ReporteView> respuesta = titulosOrdenVentaManager.descargarComprobanteOrdenVentaPDF(descargaInView);

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());

    }

    @Test
    public void descargaComprobantePDFBancaPrivadaOK() throws IOException {

        // When
        IngresoOrdenVentaInView descargaInView = new IngresoOrdenVentaInView();
        descargaInView.setEsBancaPrivada(true);
        Respuesta<Reporte> respuestaReporte = new Respuesta<Reporte>();
        Reporte reporte = new Reporte();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] byteArray = outStream.toByteArray();
        reporte.setBytes(byteArray);
        reporte.setNombre("PDF");
        reporte.setTipoArchivo(TipoArchivoEnum.PDF);
        respuestaReporte.setRespuesta(reporte);
        respuestaReporte.setEstadoRespuesta(EstadoRespuesta.OK);

        ComprobanteOrdenVenta comprobante = new ComprobanteOrdenVenta();
        when(sessionParametros.getComprobanteOrdenVenta()).thenReturn(comprobante);
        when(reporteBO.obtenerComprobantePDF(Matchers.any(ComprobanteOrdenVenta.class))).thenReturn(respuestaReporte);

        // Then
        Respuesta<ReporteView> respuesta = titulosOrdenVentaManager.descargarComprobanteOrdenVentaPDF(descargaInView);

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

    }

    @Test
    public void descargaComprobantePDFBancaPrivadaError() throws IOException {

        // When
        IngresoOrdenVentaInView descargaInView = new IngresoOrdenVentaInView();
        descargaInView.setEsBancaPrivada(true);
        Respuesta<Reporte> respuestaReporte = new Respuesta<Reporte>();
        Reporte reporte = new Reporte();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] byteArray = outStream.toByteArray();
        reporte.setBytes(byteArray);
        reporte.setNombre("PDF");
        reporte.setTipoArchivo(TipoArchivoEnum.PDF);
        respuestaReporte.setRespuesta(reporte);
        respuestaReporte.setEstadoRespuesta(EstadoRespuesta.ERROR);

        ComprobanteOrdenVenta comprobante = new ComprobanteOrdenVenta();
        when(sessionParametros.getComprobanteOrdenVenta()).thenReturn(comprobante);
        when(reporteBO.obtenerComprobantePDF(Matchers.any(ComprobanteOrdenVenta.class))).thenReturn(respuestaReporte);

        // Then
        Respuesta<ReporteView> respuesta = titulosOrdenVentaManager.descargarComprobanteOrdenVentaPDF(descargaInView);

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
	
    }
	
}