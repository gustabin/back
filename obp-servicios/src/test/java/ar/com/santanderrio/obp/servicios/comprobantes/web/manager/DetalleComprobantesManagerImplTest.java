/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.web.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
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
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.CoordinadorComprobantesBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.DetallePMCBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.LimitesFiltrosBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobantePagoMisCuentasDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteTransferenciaProgramadaDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.FiltroComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TipoDetalleComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TipoOperacionComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.web.manager.impl.DetalleComprobantesManagerImpl;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

import java.util.GregorianCalendar;

/**
 * The Class DetalleComprobantesManagerImplTest.
 * 
 * @author sabrina.cis
 */
@RunWith(MockitoJUnitRunner.class)
public class DetalleComprobantesManagerImplTest {

	@InjectMocks
	private DetalleComprobantesManager manager = new DetalleComprobantesManagerImpl();

	@Mock
	private CoordinadorComprobantesBO coordinadorBO;

	/** The detalle PMC BO. */
	@Mock
	private DetallePMCBO detallePMCBO;

	@Mock
	private SesionParametros sesionParametros;

	@Mock
	private EstadisticaManager estadisticaManager;

	@Mock
	private SesionCliente sesionCliente;

	@Mock
	private MensajeBO mensajeBO;

	@Mock
	private CuentaBO cuentaBO;

	@Mock
	private RegistroSesion registroSesion;

	@Mock
	private ReporteComprobantePDFBO reporteBO;

	@Mock
	private LimitesFiltrosBO limitesFiltrosBO;

	@InjectMocks
	@Spy
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	private ComprobantesDTO crearComprobantesDTO() {
		ComprobanteDTO comprobante = new ComprobanteDTO();
		comprobante.setFecha(new GregorianCalendar().getTime());
		comprobante.setTipoOperacion(TipoOperacionComprobanteEnum.TRANSFERENCIA_INMEDIATA);
		comprobante.setDestinatario("Destinatario");
		comprobante.setCtaMedioDePagoPesos("123-123456/7");
		comprobante.setTipoCtaMedioDePagoPesos(TipoCuenta.CAJA_AHORRO_PESOS);
		comprobante.setImportePesos(new BigDecimal("1500.00"));
		return new ComprobantesDTO(new LinkedList<ComprobanteDTO>(Arrays.asList(comprobante)));
	}

	@Test
	@SuppressWarnings("unchecked")
	public void comprobantePMCOKTest() throws ImporteConvertException {
		Respuesta<ComprobantesDTO> respuestaDTO = new Respuesta<ComprobantesDTO>();
		Respuesta<DetalleComprobanteDTO> respuestaDetalle = new Respuesta<DetalleComprobanteDTO>();
		DetalleComprobantePagoMisCuentasDTO detalle = new DetalleComprobantePagoMisCuentasDTO();
		String primeraLinea = "Test";
		String segundaLinea = "Test";
		String terceraLinea = "Test";
		Respuesta<DetalleComprobanteView> respuesta = new Respuesta<DetalleComprobanteView>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		ComprobantesDTO comprobantes = crearComprobantesDTO();
		detalle.setEmpresa("Test");
		detalle.setCodigoValidacion("123456");
		detalle.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA);
		detalle.setleyendaFactura(Arrays.asList(primeraLinea, segundaLinea, terceraLinea));
		detalle.setFechaDePago(ISBANStringUtils.formatearFecha("06/04/2017"));
		detalle.setImporte(ISBANStringUtils.convertirStrToBigDecimal("00000021783671236", 2));
		detalle.setFechaDePago(new Date());
        detalle.setTipoComprobante(TipoDetalleComprobanteEnum.IATX_PMC_SIN_DEUDA);
		comprobantes.getComprobantes().get(0).setTipoOperacion(TipoOperacionComprobanteEnum.PAGO_SERVICIOS);
		comprobantes.getComprobantes().get(0).setDetalle(detalle);
		respuestaDTO.setRespuesta(comprobantes);
		respuestaDTO.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaDetalle.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaDetalle.setRespuesta(detalle);
		String id = "0";
		Mockito.when(
				coordinadorBO.obtenerComprobantes(Matchers.any(Cliente.class), Matchers.any(FiltroComprobanteDTO.class)))
				.thenReturn(respuestaDTO);
		Mockito.when(
				detallePMCBO.obtenerDetallePMC(Matchers.anyString(), Matchers.anyString(), Matchers.anyString(),
						Matchers.any(Cliente.class))).thenReturn(respuestaDetalle);
		Mockito.when(
				respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(DetalleComprobanteView.class)))
				.thenReturn(respuesta);
		Mockito.when(registroSesion.isMobile()).thenReturn(false);
		Mockito.when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
		Respuesta<DetalleComprobanteView> res = manager.obtenerDetalleComprobantes(id, true);
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(EstadisticasConstants.DETALLE_COMPROBANTES,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		Assert.assertNotNull(res);

	}

	@Test
	@SuppressWarnings("unchecked")
	public void comprobantePMCERRORTest() {
		Respuesta<ComprobantesDTO> respuestaDTO = new Respuesta<ComprobantesDTO>();
		Respuesta<DetalleComprobanteDTO> respuestaDetalle = new Respuesta<DetalleComprobanteDTO>();
		DetalleComprobantePagoMisCuentasDTO detalle = new DetalleComprobantePagoMisCuentasDTO();
		Respuesta<DetalleComprobanteView> respuesta = new Respuesta<DetalleComprobanteView>();
		List<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>();
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		itemMensajeRespuesta.setDetalleTipoError("ERROR");
		itemMensajeRespuesta.setTipoError(TipoError.ERROR_DETALLE_COMPROBANTES.getDescripcion());
		items.add(itemMensajeRespuesta);
		respuesta.setItemMensajeRespuesta(items);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		ComprobantesDTO comprobantes = crearComprobantesDTO();
		detalle.setEmpresa("Test");
		detalle.setCodigoValidacion("123456");
		detalle.setFechaDePago(new Date());
		detalle.setTipoComprobante(TipoDetalleComprobanteEnum.IATX_PMC_SIN_DEUDA);
		comprobantes.getComprobantes().get(0).setTipoOperacion(TipoOperacionComprobanteEnum.PAGO_SERVICIOS);
		comprobantes.getComprobantes().get(0).setDetalle(detalle);
		respuestaDTO.setRespuesta(comprobantes);
		respuestaDTO.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaDetalle.setEstadoRespuesta(EstadoRespuesta.ERROR);
		respuestaDetalle.setItemMensajeRespuesta(items);
		String id = "0";
		Mockito.when(
				coordinadorBO.obtenerComprobantes(Matchers.any(Cliente.class), Matchers.any(FiltroComprobanteDTO.class)))
				.thenReturn(respuestaDTO);
		Mockito.when(
				detallePMCBO.obtenerDetallePMC(Matchers.anyString(), Matchers.anyString(), Matchers.anyString(),
						Matchers.any(Cliente.class))).thenReturn(respuestaDetalle);
		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.any(Class.class), Matchers.anyList())).thenReturn(
				respuesta);
		Mockito.when(registroSesion.isMobile()).thenReturn(false);
		Mockito.when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
		Respuesta<DetalleComprobanteView> res = manager.obtenerDetalleComprobantes(id, true);
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(EstadisticasConstants.DETALLE_COMPROBANTES,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR_PARCIAL);
		Assert.assertNotNull(res);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void comprobanteRio7x24Test() throws BusinessException {
		Respuesta<ComprobantesDTO> respuestaDTO = new Respuesta<ComprobantesDTO>();
		Respuesta<DetalleComprobanteDTO> respuestaDetalle = new Respuesta<DetalleComprobanteDTO>();
		DetalleComprobanteTransferenciaProgramadaDTO detalle = new DetalleComprobanteTransferenciaProgramadaDTO();
		Respuesta<DetalleComprobanteView> respuesta = new Respuesta<DetalleComprobanteView>();
		List<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>();
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		Respuesta<Cliente> respuestaCuenta = new Respuesta<Cliente>();

		itemMensajeRespuesta.setDetalleTipoError("ERROR");
		respuesta.setItemMensajeRespuesta(items);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		ComprobantesDTO comprobantes = crearComprobantesDTO();
		detalle.setDestinatario("Test");
		detalle.setTipoComprobante(TipoDetalleComprobanteEnum.TRANSFERENCIA_PROGRAMADA_RIO_RIO);
		detalle.setNroCuentaDestino("017-123456/7");
		detalle.setInformacionAdicional("info adicional");
		detalle.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_PESOS);
		comprobantes.getComprobantes().get(0).setTipoOperacion(TipoOperacionComprobanteEnum.PAGO_TARJETA);
		comprobantes.getComprobantes().get(0).setDetalle(detalle);
		respuestaDTO.setRespuesta(comprobantes);
		respuestaDTO.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaDetalle.setEstadoRespuesta(EstadoRespuesta.OK);
		String id = "0";
		Mockito.when(
				coordinadorBO.obtenerComprobantes(Matchers.any(Cliente.class), Matchers.any(FiltroComprobanteDTO.class)))
				.thenReturn(respuestaDTO);
		Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
		Mockito.when(
				detallePMCBO.obtenerDetallePMC(Matchers.anyString(), Matchers.anyString(), Matchers.anyString(),
						Matchers.any(Cliente.class))).thenReturn(respuestaDetalle);
		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.any(Class.class), Matchers.anyList())).thenReturn(
				respuesta);
		Mockito.when(registroSesion.isMobile()).thenReturn(false);
		Mockito.when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
		Mockito.when(
				cuentaBO.verificarCuenta(Matchers.any(Cliente.class), Matchers.anyString(), Matchers.anyString(),
						Matchers.anyString())).thenReturn(respuestaCuenta);

		Respuesta<DetalleComprobanteView> res = manager.obtenerDetalleComprobantes(id, true);
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(EstadisticasConstants.DETALLE_COMPROBANTES,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		Assert.assertNotNull(res);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void comprobanteRioRespuestaNullConCuentasTest() throws BusinessException {
		Respuesta<ComprobantesDTO> respuestaDTO = new Respuesta<ComprobantesDTO>();
		Respuesta<DetalleComprobanteDTO> respuestaDetalle = new Respuesta<DetalleComprobanteDTO>();
		DetalleComprobanteTransferenciaProgramadaDTO detalle = new DetalleComprobanteTransferenciaProgramadaDTO();
		Respuesta<DetalleComprobanteView> respuesta = new Respuesta<DetalleComprobanteView>();
		List<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>();
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		Respuesta<Cliente> respuestaCuenta = new Respuesta<Cliente>();
		Cliente cliente = new Cliente();
		Cuenta cuenta = new Cuenta();

		itemMensajeRespuesta.setDetalleTipoError("ERROR");
		respuesta.setItemMensajeRespuesta(items);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		ComprobantesDTO comprobantes = crearComprobantesDTO();
		detalle.setDestinatario("Test");
		detalle.setTipoComprobante(TipoDetalleComprobanteEnum.TRANSFERENCIA_PROGRAMADA_RIO_RIO);
		detalle.setNroCuentaDestino("017-123456/7");
		detalle.setInformacionAdicional("09");
		detalle.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_PESOS);
		comprobantes.getComprobantes().get(0).setTipoOperacion(TipoOperacionComprobanteEnum.PAGO_TARJETA);
		comprobantes.getComprobantes().get(0).setDetalle(detalle);
		respuestaDTO.setRespuesta(comprobantes);
		respuestaDTO.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaDetalle.setEstadoRespuesta(EstadoRespuesta.OK);
		cliente.setCuentas(new ArrayList<Cuenta>());
		cliente.getCuentas().add(cuenta);
		cuenta.setNroCuentaProducto("1234567");
		cuenta.setNroSucursal("017");
		cuenta.setTipoCuenta("09");

		String id = "0";
		Mockito.when(
				coordinadorBO.obtenerComprobantes(Matchers.any(Cliente.class), Matchers.any(FiltroComprobanteDTO.class)))
				.thenReturn(respuestaDTO);
		Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
		Mockito.when(
				detallePMCBO.obtenerDetallePMC(Matchers.anyString(), Matchers.anyString(), Matchers.anyString(),
						Matchers.any(Cliente.class))).thenReturn(respuestaDetalle);
		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.any(Class.class), Matchers.anyList())).thenReturn(
				respuesta);
		Mockito.when(registroSesion.isMobile()).thenReturn(false);
		Mockito.when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
		Mockito.when(
				cuentaBO.verificarCuenta(Matchers.any(Cliente.class), Matchers.anyString(), Matchers.anyString(),
						Matchers.anyString())).thenReturn(respuestaCuenta);

		Respuesta<DetalleComprobanteView> res = manager.obtenerDetalleComprobantes(id, true);
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(EstadisticasConstants.DETALLE_COMPROBANTES,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		Assert.assertNotNull(res);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void comprobanteRio7x24Test2() throws BusinessException {
		Respuesta<ComprobantesDTO> respuestaDTO = new Respuesta<ComprobantesDTO>();
		Respuesta<DetalleComprobanteDTO> respuestaDetalle = new Respuesta<DetalleComprobanteDTO>();
		DetalleComprobanteTransferenciaProgramadaDTO detalle = new DetalleComprobanteTransferenciaProgramadaDTO();
		Respuesta<DetalleComprobanteView> respuesta = new Respuesta<DetalleComprobanteView>();
		List<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>();
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		Respuesta<Cliente> respuestaCuenta = new Respuesta<Cliente>();

		itemMensajeRespuesta.setDetalleTipoError("ERROR");
		respuesta.setItemMensajeRespuesta(items);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		ComprobantesDTO comprobantes = crearComprobantesDTO();
		detalle.setDestinatario("Test");
		detalle.setTipoComprobante(TipoDetalleComprobanteEnum.TRANSFERENCIA_PROGRAMADA_RIO_RIO);
		detalle.setNroCuentaDestino("017-123456/7");
		detalle.setInformacionAdicional("info adicional");
		detalle.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_PESOS);
		comprobantes.getComprobantes().get(0).setTipoOperacion(TipoOperacionComprobanteEnum.PAGO_TARJETA);
		comprobantes.getComprobantes().get(0).setDetalle(detalle);
		respuestaDTO.setRespuesta(comprobantes);
		respuestaDTO.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaDetalle.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaCuenta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaCuenta.setRespuesta(new Cliente());

		String id = "0";
		Mockito.when(
				coordinadorBO.obtenerComprobantes(Matchers.any(Cliente.class), Matchers.any(FiltroComprobanteDTO.class)))
				.thenReturn(respuestaDTO);
		Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
		Mockito.when(
				detallePMCBO.obtenerDetallePMC(Matchers.anyString(), Matchers.anyString(), Matchers.anyString(),
						Matchers.any(Cliente.class))).thenReturn(respuestaDetalle);
		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.any(Class.class), Matchers.anyList())).thenReturn(
				respuesta);
		Mockito.when(registroSesion.isMobile()).thenReturn(false);
		Mockito.when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
		Mockito.when(
				cuentaBO.verificarCuenta(Matchers.any(Cliente.class), Matchers.anyString(), Matchers.anyString(),
						Matchers.anyString())).thenReturn(respuestaCuenta);

		Respuesta<DetalleComprobanteView> res = manager.obtenerDetalleComprobantes(id, true);
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(EstadisticasConstants.DETALLE_COMPROBANTES,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		Assert.assertNotNull(res);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void comprobanteRio7x24Test3() throws BusinessException {
		Respuesta<ComprobantesDTO> respuestaDTO = new Respuesta<ComprobantesDTO>();
		Respuesta<DetalleComprobanteDTO> respuestaDetalle = new Respuesta<DetalleComprobanteDTO>();
		DetalleComprobanteTransferenciaProgramadaDTO detalle = new DetalleComprobanteTransferenciaProgramadaDTO();
		Respuesta<DetalleComprobanteView> respuesta = new Respuesta<DetalleComprobanteView>();
		List<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>();
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		Respuesta<Cliente> respuestaCuenta = new Respuesta<Cliente>();

		itemMensajeRespuesta.setDetalleTipoError("ERROR");
		respuesta.setItemMensajeRespuesta(items);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		ComprobantesDTO comprobantes = crearComprobantesDTO();
		detalle.setDestinatario("Test");
		detalle.setTipoComprobante(TipoDetalleComprobanteEnum.TRANSFERENCIA_PROGRAMADA_RIO_RIO);
		detalle.setNroCuentaDestino("017-123456/7");
		detalle.setInformacionAdicional("info adicional");
		detalle.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_PESOS);
		comprobantes.getComprobantes().get(0).setTipoOperacion(TipoOperacionComprobanteEnum.PAGO_TARJETA);
		comprobantes.getComprobantes().get(0).setDetalle(detalle);
		respuestaDTO.setRespuesta(comprobantes);
		respuestaDTO.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaDetalle.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaCuenta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaCuenta.setRespuesta(new Cliente());

		String id = "0";
		Mockito.when(
				coordinadorBO.obtenerComprobantes(Matchers.any(Cliente.class), Matchers.any(FiltroComprobanteDTO.class)))
				.thenReturn(respuestaDTO);
		Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
		Mockito.when(
				detallePMCBO.obtenerDetallePMC(Matchers.anyString(), Matchers.anyString(), Matchers.anyString(),
						Matchers.any(Cliente.class))).thenReturn(respuestaDetalle);
		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.any(Class.class), Matchers.anyList())).thenReturn(
				respuesta);
		Mockito.when(registroSesion.isMobile()).thenReturn(false);
		Mockito.when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
		Mockito.when(
				cuentaBO.verificarCuenta(Matchers.any(Cliente.class), Matchers.anyString(), Matchers.anyString(),
						Matchers.anyString())).thenThrow(new BusinessException());

		Respuesta<DetalleComprobanteView> res = manager.obtenerDetalleComprobantes(id, true);
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(EstadisticasConstants.DETALLE_COMPROBANTES,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		Assert.assertNotNull(res);
	}

	@Test
	public void descargarPDFOkTest() {
		Respuesta<Reporte> reporte = new Respuesta<Reporte>();
		Reporte pdf = new Reporte();
		pdf.setTipoArchivo(TipoArchivoEnum.PDF);

		reporte.setEstadoRespuesta(EstadoRespuesta.OK);
		reporte.setRespuesta(pdf);

		pdf.setNombre("pdfTest");

		Mockito.when(reporteBO.obtenerComprobantePDF(Matchers.any(DetalleComprobanteView.class))).thenReturn(reporte);

		Respuesta<ReporteView> res = manager.descargaComprobanteGrilla();
		Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
		Assert.assertEquals("pdfTest", res.getRespuesta().getNombre());
	}

	@Test
	public void descargarPDFErrorTest() {
		Respuesta<Reporte> reporte = new Respuesta<Reporte>();
		reporte.setEstadoRespuesta(EstadoRespuesta.ERROR);

		Mockito.when(reporteBO.obtenerComprobantePDF(Matchers.any(DetalleComprobanteView.class))).thenReturn(reporte);

		Respuesta<ReporteView> res = manager.descargaComprobanteGrilla();
		Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
	}

}
