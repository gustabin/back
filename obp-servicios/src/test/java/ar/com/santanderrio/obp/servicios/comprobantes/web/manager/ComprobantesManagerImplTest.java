/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.web.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
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

import java.util.GregorianCalendar;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.CoordinadorComprobantesBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.DetallePMCBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.LimitesFiltrosBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.impl.TransaccionEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.FiltroComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TipoOperacionComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.exceptions.ValidarFechaBuscadorException;
import ar.com.santanderrio.obp.servicios.comprobantes.web.manager.impl.ComprobantesManagerImpl;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.ComprobantesView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.ComprobantesViewIn;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.TransaccionViewIn;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class ComprobantesManagerImplTest.
 * 
 * @author sabrina.cis
 */
@RunWith(MockitoJUnitRunner.class)
public class ComprobantesManagerImplTest {

	/** The manager. */
	@InjectMocks
	private ComprobantesManager manager = new ComprobantesManagerImpl();

	/** The coordinador BO. */
	@Mock
	private CoordinadorComprobantesBO coordinadorBO;

	/** The detalle PMC BO. */
	@Mock
	private DetallePMCBO detallePMCBO;

	/** The sesion parametros. */
	@Mock
	private SesionParametros sesionParametros;

	/** The estadistica manager. */
	@Mock
	private EstadisticaManager estadisticaManager;

	/** The sesion cliente. */
	@Mock
	private SesionCliente sesionCliente;

	/** The mensaje BO. */
	@Mock
	private MensajeBO mensajeBO;

	/** The cuenta BO. */
	@Mock
	private CuentaBO cuentaBO;

	/** The registro sesion. */
	@Mock
	private RegistroSesion registroSesion;

	/** The reporte BO. */
	@Mock
	private ReporteComprobantePDFBO reporteBO;

	/** The limites filtros BO. */
	@Mock
	private LimitesFiltrosBO limitesFiltrosBO;

	/** The respuesta factory. */
	@InjectMocks
	@Spy
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	/**
	 * Obtener comprobantes ok test.
	 *
	 * @throws ValidarFechaBuscadorException the validar fecha buscador exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerComprobantesOkTest() throws ValidarFechaBuscadorException {
		Mockito.when(sesionParametros.getContadorPaginaComprobantes()).thenReturn(0);
		Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
		Mockito.when(registroSesion.isMobile()).thenReturn(false);
		Mockito.when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
		ComprobantesDTO comprobante = crearComprobantesDTO();
		comprobante.setCodigoError("1");
		comprobante.setCodigoEstadistica("13744");
		List<TransaccionViewIn> transacciones = new ArrayList<TransaccionViewIn>();
		TransaccionViewIn transaccion = new TransaccionViewIn();
		transaccion.setIdTransaccion(0);
		transacciones.add(transaccion);
		FiltroComprobanteDTO filtro = new FiltroComprobanteDTO();
		TransaccionDTO transaccionDto = new TransaccionDTO();
		transaccionDto.setTransaccion(TransaccionEnum.PAGOS_DE_SERVICIOS);
		List<TransaccionDTO> transaccionesFiltro = new ArrayList<TransaccionDTO>();
		transaccionesFiltro.add(transaccionDto);
		filtro.setTransacciones(transaccionesFiltro);
		Respuesta<ComprobantesDTO> comprobantesDTO = respuestaFactory.crearRespuestaOk(comprobante);
		Mockito.when(
				coordinadorBO.obtenerComprobantes(Matchers.any(Cliente.class), Matchers.any(FiltroComprobanteDTO.class)))
				.thenReturn(comprobantesDTO);
		List<ComprobanteDTO> comprobantes = new ArrayList<ComprobanteDTO>();
		comprobantes.addAll(comprobantesDTO.getRespuesta().getComprobantes());
		Mockito.when(
				coordinadorBO.filtrar(Matchers.anyInt(), Matchers.anyBoolean(),
						(LinkedList<ComprobanteDTO>) Matchers.anyCollection())).thenReturn(comprobantes);
		Mockito.when(
				limitesFiltrosBO.obtenerFiltroComprobanteDTO(Matchers.any(ComprobantesViewIn.class),
						Matchers.any(Cliente.class))).thenReturn(filtro);
		ComprobantesViewIn viewIn = new ComprobantesViewIn();
		viewIn.setRecargaPagina(false);
		Respuesta<ComprobantesView> view = manager.obtenerComprobantes(viewIn);
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(
				EstadisticasConstants.SOLAPA_COMPROBANTES_PMC,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		Mockito.verify(sesionParametros, Mockito.times(1)).setContadorPaginaComprobantes(1);
		Assert.assertEquals(EstadoRespuesta.OK, view.getEstadoRespuesta());
		Assert.assertEquals(1, view.getRespuesta().getComprobantes().size());
	}

	/**
	 * Obtener repuesta sin comprobantes test.
	 *
	 * @throws ValidarFechaBuscadorException the validar fecha buscador exception
	 */
	@Test
	public void obtenerRepuestaSinComprobantesTest() throws ValidarFechaBuscadorException {
		Mockito.when(sesionParametros.getContadorPaginaComprobantes()).thenReturn(0);
		Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
		Respuesta<ComprobantesDTO> respuestaDTO = crearRespuestaDTOErrorSinComprobantes();
		respuestaDTO.setRespuesta(new ComprobantesDTO());
		respuestaDTO.getRespuesta().setCodigoError("1");
		respuestaDTO.getRespuesta().setCodigoEstadistica("13744");
		List<String> listaAnios = Arrays.asList("10", "2", "1");
		List<TransaccionViewIn> transacciones = new ArrayList<TransaccionViewIn>();
		TransaccionViewIn transaccion = new TransaccionViewIn();
		transaccion.setIdTransaccion(0);
		transacciones.add(transaccion);
		FiltroComprobanteDTO filtro = new FiltroComprobanteDTO();
		TransaccionDTO transaccionDto = new TransaccionDTO();
		transaccionDto.setTransaccion(TransaccionEnum.PAGOS_DE_SERVICIOS);
		List<TransaccionDTO> transaccionesFiltro = new ArrayList<TransaccionDTO>();
		transaccionesFiltro.add(transaccionDto);
		filtro.setTransacciones(transaccionesFiltro);
		Mockito.when(
				coordinadorBO.obtenerComprobantes(Matchers.any(Cliente.class), Matchers.any(FiltroComprobanteDTO.class)))
				.thenReturn(respuestaDTO);
		Mockito.when(
				limitesFiltrosBO.obtenerFiltroComprobanteDTO(Matchers.any(ComprobantesViewIn.class),
						Matchers.any(Cliente.class))).thenReturn(filtro);
		Mockito.when(limitesFiltrosBO.obtenerLimitesDeFecha()).thenReturn(listaAnios);
		ComprobantesViewIn viewIn = new ComprobantesViewIn();
		viewIn.setRecargaPagina(false);
		viewIn.setTransacciones(transacciones);

		Respuesta<ComprobantesView> view = manager.obtenerComprobantes(viewIn);
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(
				EstadisticasConstants.SOLAPA_COMPROBANTES_PMC,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		Assert.assertEquals(EstadoRespuesta.ERROR, view.getEstadoRespuesta());
		Assert.assertEquals(TipoError.SIN_COMPROBANTES.getDescripcion(), view.getItemsMensajeRespuesta().get(0)
				.getTipoError());
	}

	/**
	 * Obtener repuesta error test.
	 *
	 * @throws ValidarFechaBuscadorException the validar fecha buscador exception
	 */
	@Test
	public void obtenerRepuestaErrorTest() throws ValidarFechaBuscadorException {
		Mockito.when(sesionParametros.getContadorPaginaComprobantes()).thenReturn(1);
		Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
		Respuesta<ComprobantesDTO> respuestaDTO = crearRespuestaDTOErrorSinComprobantes();
		respuestaDTO.setRespuesta(new ComprobantesDTO());
		respuestaDTO.getRespuesta().setCodigoError("9");
		respuestaDTO.getRespuesta().setCodigoEstadistica("13744");
		respuestaDTO.add(new ItemMensajeRespuesta());
		List<String> listaAnios = Arrays.asList("10", "2", "1");
		List<TransaccionViewIn> transacciones = new ArrayList<TransaccionViewIn>();
		TransaccionViewIn transaccion = new TransaccionViewIn();
		transaccion.setIdTransaccion(0);
		transacciones.add(transaccion);
		FiltroComprobanteDTO filtro = new FiltroComprobanteDTO();
		TransaccionDTO transaccionDto = new TransaccionDTO();
		transaccionDto.setTransaccion(TransaccionEnum.PAGOS_DE_SERVICIOS);
		List<TransaccionDTO> transaccionesFiltro = new ArrayList<TransaccionDTO>();
		transaccionesFiltro.add(transaccionDto);
		filtro.setTransacciones(transaccionesFiltro);
		Mockito.when(
				coordinadorBO.obtenerComprobantes(Matchers.any(Cliente.class), Matchers.any(FiltroComprobanteDTO.class)))
				.thenReturn(respuestaDTO);
		Mockito.when(
				limitesFiltrosBO.obtenerFiltroComprobanteDTO(Matchers.any(ComprobantesViewIn.class),
						Matchers.any(Cliente.class))).thenReturn(filtro);
		Mockito.when(limitesFiltrosBO.obtenerLimitesDeFecha()).thenReturn(listaAnios);
		ComprobantesViewIn viewIn = new ComprobantesViewIn();
		viewIn.setRecargaPagina(false);
		Respuesta<ComprobantesView> view = manager.obtenerComprobantes(viewIn);
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(
				EstadisticasConstants.SOLAPA_COMPROBANTES_PMC,
				EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL);
		Assert.assertEquals(EstadoRespuesta.ERROR, view.getEstadoRespuesta());
		Assert.assertEquals(TipoError.SIN_COMPROBANTES.getDescripcion(), view.getItemsMensajeRespuesta().get(0)
				.getTipoError());
	}

	/**
	 * Obtener repuesta error otro caso test.
	 *
	 * @throws ValidarFechaBuscadorException the validar fecha buscador exception
	 */
	@Test
	public void obtenerRepuestaErrorOtroCasoTest() throws ValidarFechaBuscadorException {
		Mockito.when(sesionParametros.getContadorPaginaComprobantes()).thenReturn(0);
		Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
		Respuesta<ComprobantesDTO> respuestaDTO = crearRespuestaDTOErrorSinComprobantes();
		respuestaDTO.setRespuesta(new ComprobantesDTO());
		respuestaDTO.getRespuesta().setCodigoError("2");
		respuestaDTO.getRespuesta().setCodigoEstadistica("13744");
		respuestaDTO.getItemsMensajeRespuesta().get(0).setTipoError(TipoError.ERROR_COMPROBANTES.getDescripcion());
		List<String> listaAnios = Arrays.asList("10", "2", "1");
		List<TransaccionViewIn> transacciones = new ArrayList<TransaccionViewIn>();
		TransaccionViewIn transaccion = new TransaccionViewIn();
		transaccion.setIdTransaccion(0);
		transacciones.add(transaccion);
		FiltroComprobanteDTO filtro = new FiltroComprobanteDTO();
		TransaccionDTO transaccionDto = new TransaccionDTO();
		transaccionDto.setTransaccion(TransaccionEnum.PAGOS_DE_SERVICIOS);
		List<TransaccionDTO> transaccionesFiltro = new ArrayList<TransaccionDTO>();
		transaccionesFiltro.add(transaccionDto);
		filtro.setTransacciones(transaccionesFiltro);
		Mockito.when(
				coordinadorBO.obtenerComprobantes(Matchers.any(Cliente.class), Matchers.any(FiltroComprobanteDTO.class)))
				.thenReturn(respuestaDTO);
		Mockito.when(
				limitesFiltrosBO.obtenerFiltroComprobanteDTO(Matchers.any(ComprobantesViewIn.class),
						Matchers.any(Cliente.class))).thenReturn(filtro);
		Mockito.when(limitesFiltrosBO.obtenerLimitesDeFecha()).thenReturn(listaAnios);
		ComprobantesViewIn viewIn = new ComprobantesViewIn();
		viewIn.setRecargaPagina(false);
		Respuesta<ComprobantesView> view = manager.obtenerComprobantes(viewIn);
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(
				EstadisticasConstants.SOLAPA_COMPROBANTES_PMC,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		Assert.assertEquals(EstadoRespuesta.ERROR, view.getEstadoRespuesta());
		Assert.assertEquals(TipoError.ERROR_COMPROBANTES.getDescripcion(), view.getItemsMensajeRespuesta().get(0)
				.getTipoError());
	}

	/**
	 * Obtener comprobantes warning test.
	 *
	 * @throws ValidarFechaBuscadorException the validar fecha buscador exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerComprobantesWarningTest() throws ValidarFechaBuscadorException {
		Mockito.when(sesionParametros.getContadorPaginaComprobantes()).thenReturn(0);
		Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
		Mockito.when(registroSesion.isMobile()).thenReturn(false);
		Mockito.when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
		Respuesta<ComprobantesDTO> respuestaDTO = crearRespuestaDTOWarning();
		respuestaDTO.getRespuesta().setCodigoError("9");
		respuestaDTO.getRespuesta().setCodigoEstadistica("13744");
		Mockito.when(
				coordinadorBO.obtenerComprobantes(Matchers.any(Cliente.class), Matchers.any(FiltroComprobanteDTO.class)))
				.thenReturn(respuestaDTO);
		List<ComprobanteDTO> comprobantes = new ArrayList<ComprobanteDTO>();
		comprobantes.addAll(respuestaDTO.getRespuesta().getComprobantes());
		List<TransaccionViewIn> transacciones = new ArrayList<TransaccionViewIn>();
		TransaccionViewIn transaccion = new TransaccionViewIn();
		transaccion.setIdTransaccion(0);
		transacciones.add(transaccion);
		FiltroComprobanteDTO filtro = new FiltroComprobanteDTO();
		TransaccionDTO transaccionDto = new TransaccionDTO();
		transaccionDto.setTransaccion(TransaccionEnum.PAGOS_DE_SERVICIOS);
		List<TransaccionDTO> transaccionesFiltro = new ArrayList<TransaccionDTO>();
		transaccionesFiltro.add(transaccionDto);
		filtro.setTransacciones(transaccionesFiltro);
		Mockito.when(
				coordinadorBO.filtrar(Matchers.anyInt(), Matchers.anyBoolean(),
						(LinkedList<ComprobanteDTO>) Matchers.anyCollection())).thenReturn(comprobantes);
		Mockito.when(
				limitesFiltrosBO.obtenerFiltroComprobanteDTO(Matchers.any(ComprobantesViewIn.class),
						Matchers.any(Cliente.class))).thenReturn(filtro);
		ComprobantesViewIn viewIn = new ComprobantesViewIn();
		viewIn.setRecargaPagina(false);
		Respuesta<ComprobantesView> view = manager.obtenerComprobantes(viewIn);
		Mockito.verify(estadisticaManager, Mockito.times(1)).add(
				EstadisticasConstants.SOLAPA_COMPROBANTES_PMC,
				EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL);
		Assert.assertEquals(EstadoRespuesta.WARNING, view.getEstadoRespuesta());
		Assert.assertEquals(TipoError.ALERTA_COMPROBANTES.getDescripcion(), view.getItemsMensajeRespuesta().get(0)
				.getTipoError());
		Assert.assertFalse(view.getRespuesta().getComprobantes().isEmpty());
		Mockito.verify(sesionParametros, Mockito.times(1)).setContadorPaginaComprobantes(1);
	}

	/**
	 * Crear comprobantes DTO.
	 *
	 * @return the comprobantes DTO
	 */
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

	/**
	 * Crear respuesta DTO error sin comprobantes.
	 *
	 * @return the respuesta
	 */
	private Respuesta<ComprobantesDTO> crearRespuestaDTOErrorSinComprobantes() {
		ItemMensajeRespuesta item = new ItemMensajeRespuesta();
		item.setTipoError(TipoError.SIN_COMPROBANTES.getDescripcion());
		List<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>(Arrays.asList(item));
		return respuestaFactory.crearRespuestaError(ComprobantesDTO.class, items);
	}

	/**
	 * Crear respuesta DTO warning.
	 *
	 * @return the respuesta
	 */
	private Respuesta<ComprobantesDTO> crearRespuestaDTOWarning() {
		ItemMensajeRespuesta item = new ItemMensajeRespuesta();
		item.setTipoError(TipoError.ALERTA_COMPROBANTES.getDescripcion());
		List<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>(Arrays.asList(item));
		return respuestaFactory.crearRespuestaWarning(ComprobantesDTO.class, crearComprobantesDTO(), items);
	}

}
