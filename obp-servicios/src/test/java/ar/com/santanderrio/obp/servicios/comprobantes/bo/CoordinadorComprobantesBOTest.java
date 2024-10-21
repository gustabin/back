package ar.com.santanderrio.obp.servicios.comprobantes.bo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.commons.lang3.reflect.FieldUtils;
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
import org.springframework.scheduling.annotation.AsyncResult;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.impl.CoordinadorComprobantesBOImpl;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.impl.DebitosAutomaticosBOImpl;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.impl.TransaccionEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.FiltroComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TipoOperacionComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.TransaccionViewIn;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class CoordinadorComprobantesBOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class CoordinadorComprobantesBOTest {
	/** The alta destinatario manager. */
	@InjectMocks
	private CoordinadorComprobantesBO coordinadorComprobantesBO = new CoordinadorComprobantesBOImpl();

	/** The respuesta factory. */
	@InjectMocks
	@Spy
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	/** The transferencia inmediata BO. */
	@Mock
	private ScompBO transferenciaInmediataBO;

	/** The recarga tarjeta BO. */
	@Mock
	private RecargaTarjetaBO recargaTarjetaBO;

	/** The recarga tarjeta BO. */
	@Mock
	private DebitosAutomaticosBOImpl debitosAutomatomaticosBOImpl;

	@Mock
	private ComprobantesSietePorVenticuatroBO comprobantes7x24BO;

	@Mock
	private ComprobantesDebitoAutomaticoEnCuentaBO debitoAutomaticoEnCuentaBO;

	@Mock
	private ComprobantesTransferenciasUnificadoBO transferencias;

	@Mock
	private ComprobantesDebitosAutomaticosUnificadosBO debitosUnificadosBO;

	@Mock
	private ComprobantesPagoMisCuentasBO pagoMisCuentasBO;

	@Mock
	private ComprobantesPagoTarjetasBO comprobantesPagoTarjetasBO;

	@Mock
	private ComprobantesPMCUnificadoBO comprobantesPMCUnificadoBO;

	@Mock
	private Future<Respuesta<ComprobantesDTO>> futureComprobantes1;

	@Mock
	private Future<Respuesta<ComprobantesDTO>> futureComprobantes2;

	@Mock
	private Future<Respuesta<ComprobantesDTO>> futureComprobantes3;

	@Mock
	private Future<Respuesta<ComprobantesDTO>> futureComprobantes4;

	@Mock
	private MensajeBO mensajeBO;

	/**
	 * Iniciar mocks.
	 * 
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws BusinessException
	 *             the business exception
	 */
	@Before
	public void iniciarMocks() throws IllegalAccessException, BusinessException {

		FieldUtils.writeDeclaredField(coordinadorComprobantesBO, "paginadoDesktop", "10", true);
		FieldUtils.writeDeclaredField(coordinadorComprobantesBO, "paginadoMobile", "50", true);

		Respuesta<ComprobantesDTO> respuestaDTOScomp = crearRespuesta(ComprobantesDTO.class, obtenerMocks(),
				EstadoRespuesta.OK, false, new ArrayList<ItemMensajeRespuesta>());

		Mockito.when(debitosAutomatomaticosBOImpl.obtenerComprobantesAsync(Matchers.any(Cliente.class)))
				.thenReturn(new AsyncResult<Respuesta<ComprobantesDTO>>(respuestaDTOScomp));

	}

	@Test
	public void hayMasComprobantesTest() {
		List<ComprobanteDTO> comprobantes = obtenerMocks().getComprobantes().subList(0, 10);
		Boolean hayMasComprobantes = coordinadorComprobantesBO.hayMasComprobantes(comprobantes,
				obtenerMocks().getComprobantes(), false, 0);
		Assert.assertFalse(!hayMasComprobantes);
	}

	@Test
	public void noHayMasComprobantesTest() {
		List<ComprobanteDTO> comprobantes = obtenerMocks().getComprobantes().subList(0, 10);
		Boolean hayMasComprobantes = coordinadorComprobantesBO.hayMasComprobantes(comprobantes,
				obtenerMocks().getComprobantes(), true, 0);
		Assert.assertFalse(hayMasComprobantes);
	}

	@Test
	public void obtenerRespuestaComprobantesPagoDeServicios()
			throws InterruptedException, ExecutionException, IllegalAccessException {
		Mockito.when(comprobantesPMCUnificadoBO.obtenerComprobantesPMCAsync(Matchers.any(Cliente.class),
				Matchers.any(TransaccionDTO.class))).thenReturn(futureComprobantes3);
		Mockito.when(futureComprobantes3.isDone()).thenReturn(true);
		Respuesta<ComprobantesDTO> respuesta = respuestaFactory.crearRespuestaOk(obtenerMocks());
		Mockito.when(futureComprobantes3.get()).thenReturn(respuesta);
		FieldUtils.writeDeclaredField(coordinadorComprobantesBO, "topeComprobantes", "100", true);

		FiltroComprobanteDTO filtroComprobanteDTO = obtenerFiltroPagoDeServicios();
		Respuesta<ComprobantesDTO> respuestaComprobantes = coordinadorComprobantesBO.obtenerComprobantes(new Cliente(),
				filtroComprobanteDTO);

		Assert.assertEquals(EstadoRespuesta.OK, respuestaComprobantes.getEstadoRespuesta());
		Assert.assertNull(respuestaComprobantes.getItemsMensajeRespuesta());
		Assert.assertFalse(respuestaComprobantes.getRespuesta().getComprobantes().isEmpty());
	}

	@Test
	public void obtenerRespuestaComprobantesPagoDeServiciosInterruptedException()
			throws InterruptedException, ExecutionException, IllegalAccessException {
		Mockito.when(comprobantesPMCUnificadoBO.obtenerComprobantesPMCAsync(Matchers.any(Cliente.class),
				Matchers.any(TransaccionDTO.class))).thenReturn(futureComprobantes3);
		Mockito.when(futureComprobantes3.isDone()).thenReturn(true);
		Mockito.when(futureComprobantes3.get()).thenThrow(new InterruptedException());
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
		FieldUtils.writeDeclaredField(coordinadorComprobantesBO, "topeComprobantes", "100", true);

		FiltroComprobanteDTO filtroComprobanteDTO = obtenerFiltroPagoDeServicios();
		Respuesta<ComprobantesDTO> respuestaComprobantes = coordinadorComprobantesBO.obtenerComprobantes(new Cliente(),
				filtroComprobanteDTO);

		Assert.assertEquals(EstadoRespuesta.ERROR, respuestaComprobantes.getEstadoRespuesta());
	}

	@Test
	public void obtenerRespuestaComprobantesPagoDeServiciosExecutionException()
			throws InterruptedException, ExecutionException, IllegalAccessException {
		Mockito.when(comprobantesPMCUnificadoBO.obtenerComprobantesPMCAsync(Matchers.any(Cliente.class),
				Matchers.any(TransaccionDTO.class))).thenReturn(futureComprobantes3);
		Mockito.when(futureComprobantes3.isDone()).thenReturn(true);
		Mockito.when(futureComprobantes3.get()).thenThrow(new ExecutionException(new DAOException()));
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
		FieldUtils.writeDeclaredField(coordinadorComprobantesBO, "topeComprobantes", "100", true);

		FiltroComprobanteDTO filtroComprobanteDTO = obtenerFiltroPagoDeServicios();
		Respuesta<ComprobantesDTO> respuestaComprobantes = coordinadorComprobantesBO.obtenerComprobantes(new Cliente(),
				filtroComprobanteDTO);

		Assert.assertEquals(EstadoRespuesta.ERROR, respuestaComprobantes.getEstadoRespuesta());
	}

	@Test
	public void obtenerRespuestaComprobantesPagoDeServiciosNullPointerException()
			throws InterruptedException, ExecutionException, IllegalAccessException {
		Mockito.when(comprobantesPMCUnificadoBO.obtenerComprobantesPMCAsync(Matchers.any(Cliente.class),
				Matchers.any(TransaccionDTO.class))).thenReturn(futureComprobantes3);
		Mockito.when(futureComprobantes3.isDone()).thenReturn(true);
		Mockito.when(futureComprobantes3.get()).thenThrow(new NullPointerException());
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
		FieldUtils.writeDeclaredField(coordinadorComprobantesBO, "topeComprobantes", "100", true);

		FiltroComprobanteDTO filtroComprobanteDTO = obtenerFiltroPagoDeServicios();
		Respuesta<ComprobantesDTO> respuestaComprobantes = coordinadorComprobantesBO.obtenerComprobantes(new Cliente(),
				filtroComprobanteDTO);

		Assert.assertEquals(EstadoRespuesta.ERROR, respuestaComprobantes.getEstadoRespuesta());
	}

	@Test
	public void obtenerRespuestaComprobantesTransferencias()
			throws InterruptedException, ExecutionException, IllegalAccessException {
		Mockito.when(transferencias.obtenerComprobantesTransferenciaAsync(Matchers.any(Cliente.class),
				Matchers.any(TransaccionDTO.class))).thenReturn(futureComprobantes3);
		Mockito.when(futureComprobantes3.isDone()).thenReturn(true);
		Respuesta<ComprobantesDTO> respuesta = respuestaFactory.crearRespuestaOk(obtenerMocks());
		Mockito.when(futureComprobantes3.get()).thenReturn(respuesta);
		FieldUtils.writeDeclaredField(coordinadorComprobantesBO, "topeComprobantes", "100", true);

		FiltroComprobanteDTO filtroComprobanteDTO = obtenerFiltroPagoDeServicios();
		filtroComprobanteDTO.getTransacciones().get(0).setTransaccion(TransaccionEnum.TRANSFERENCIAS);
		Respuesta<ComprobantesDTO> respuestaComprobantes = coordinadorComprobantesBO.obtenerComprobantes(new Cliente(),
				filtroComprobanteDTO);

		Assert.assertEquals(EstadoRespuesta.OK, respuestaComprobantes.getEstadoRespuesta());
		Assert.assertNull(respuestaComprobantes.getItemsMensajeRespuesta());
		Assert.assertFalse(respuestaComprobantes.getRespuesta().getComprobantes().isEmpty());
	}

	@Test
	public void obtenerRespuestaComprobantesDebitosAutomaticos()
			throws InterruptedException, ExecutionException, IllegalAccessException {
		Mockito.when(debitosUnificadosBO.obtenerComprobantesAsync(Matchers.any(Cliente.class),
				Matchers.any(TransaccionDTO.class))).thenReturn(futureComprobantes3);
		Mockito.when(futureComprobantes3.isDone()).thenReturn(true);
		Respuesta<ComprobantesDTO> respuesta = respuestaFactory.crearRespuestaOk(obtenerMocks());
		Mockito.when(futureComprobantes3.get()).thenReturn(respuesta);
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
		FieldUtils.writeDeclaredField(coordinadorComprobantesBO, "topeComprobantes", "100", true);

		FiltroComprobanteDTO filtroComprobanteDTO = obtenerFiltroPagoDeServicios();
		filtroComprobanteDTO.getTransacciones().get(0).setTransaccion(TransaccionEnum.DEBITO_AUTOMATICO);
		Respuesta<ComprobantesDTO> respuestaComprobantes = coordinadorComprobantesBO.obtenerComprobantes(new Cliente(),
				filtroComprobanteDTO);

		Assert.assertEquals(EstadoRespuesta.OK, respuestaComprobantes.getEstadoRespuesta());
		Assert.assertNull(respuestaComprobantes.getItemsMensajeRespuesta());
		Assert.assertFalse(respuestaComprobantes.getRespuesta().getComprobantes().isEmpty());
	}

	@Test
	public void obtenerRespuestaComprobantesPagoTarjetas()
			throws InterruptedException, ExecutionException, IllegalAccessException {
		Mockito.when(comprobantesPagoTarjetasBO.obtenerComprobantesPagoTarjetasAsync(Matchers.any(Cliente.class),
				Matchers.any(TransaccionDTO.class), Matchers.anyBoolean())).thenReturn(futureComprobantes3);
		Mockito.when(futureComprobantes3.isDone()).thenReturn(true);
		Respuesta<ComprobantesDTO> respuesta = respuestaFactory.crearRespuestaOk(obtenerMocks());
		Mockito.when(futureComprobantes3.get()).thenReturn(respuesta);
		FieldUtils.writeDeclaredField(coordinadorComprobantesBO, "topeComprobantes", "100", true);

		FiltroComprobanteDTO filtroComprobanteDTO = obtenerFiltroPagoDeServicios();
		filtroComprobanteDTO.getTransacciones().get(0).setTransaccion(TransaccionEnum.PAGO_DE_TARJETA);
		Respuesta<ComprobantesDTO> respuestaComprobantes = coordinadorComprobantesBO.obtenerComprobantes(new Cliente(),
				filtroComprobanteDTO);

		Assert.assertEquals(EstadoRespuesta.OK, respuestaComprobantes.getEstadoRespuesta());
		Assert.assertNull(respuestaComprobantes.getItemsMensajeRespuesta());
		Assert.assertFalse(respuestaComprobantes.getRespuesta().getComprobantes().isEmpty());
	}

	@Test
	public void obtenerRespuestaComprobantesTodosLosComprobantes()
			throws InterruptedException, ExecutionException, IllegalAccessException {
		Mockito.when(pagoMisCuentasBO.obtenerComprobantesPMCAsync(Matchers.any(Cliente.class),
				Matchers.any(TransaccionDTO.class))).thenReturn(futureComprobantes3);
		Mockito.when(transferencias.obtenerComprobantesTransferenciaAsync(Matchers.any(Cliente.class),
				Matchers.any(TransaccionDTO.class))).thenReturn(futureComprobantes1);
		Mockito.when(debitosUnificadosBO.obtenerComprobantesAsync(Matchers.any(Cliente.class),
				Matchers.any(TransaccionDTO.class))).thenReturn(futureComprobantes3);
		Mockito.when(comprobantesPagoTarjetasBO.obtenerComprobantesPagoTarjetasAsync(Matchers.any(Cliente.class),
				Matchers.any(TransaccionDTO.class), Matchers.anyBoolean())).thenReturn(futureComprobantes2);
		Mockito.when(transferenciaInmediataBO.obtenerComprobantesCompraVentaDolarAsync(Matchers.anyString(),
				Matchers.any(TransaccionDTO.class))).thenReturn(futureComprobantes3);
		Mockito.when(comprobantes7x24BO.obtenerComprobantesPagoHaberesYHonorariosUnificadosAsync(
				Matchers.any(Cliente.class), Matchers.any(TransaccionDTO.class))).thenReturn(futureComprobantes4);
		Mockito.when(transferenciaInmediataBO.obtenerComprobantesPagoDeComprasAsync(Matchers.any(Cliente.class),
				Matchers.any(TransaccionDTO.class))).thenReturn(futureComprobantes3);
		Mockito.when(futureComprobantes1.isDone()).thenReturn(true);
		Mockito.when(futureComprobantes2.isDone()).thenReturn(true);
		Mockito.when(futureComprobantes3.isDone()).thenReturn(true);
		Mockito.when(futureComprobantes4.isDone()).thenReturn(true);
		Respuesta<ComprobantesDTO> respuesta = respuestaFactory.crearRespuestaOk(obtenerMocks());
		Respuesta<ComprobantesDTO> respuesta2 = respuestaFactory.crearRespuestaWarning(ComprobantesDTO.class,
				obtenerMocks(), new ArrayList<ItemMensajeRespuesta>());
		Mockito.when(futureComprobantes1.get()).thenReturn(respuesta);
		Mockito.when(futureComprobantes2.get()).thenReturn(respuesta2);
		Mockito.when(futureComprobantes3.get()).thenReturn(respuesta2);
		Mockito.when(futureComprobantes4.get()).thenReturn(respuesta2);
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
		FieldUtils.writeDeclaredField(coordinadorComprobantesBO, "topeComprobantes", "100", true);

		FiltroComprobanteDTO filtroComprobanteDTO = obtenerFiltroPagoDeServicios();
		filtroComprobanteDTO.getTransacciones().get(0).setTransaccion(TransaccionEnum.TRANSFERENCIAS);
		Respuesta<ComprobantesDTO> respuestaComprobantes = coordinadorComprobantesBO.obtenerComprobantes(new Cliente(),
				filtroComprobanteDTO);

		Assert.assertEquals(EstadoRespuesta.OK, respuestaComprobantes.getEstadoRespuesta());
		Assert.assertNull(respuestaComprobantes.getItemsMensajeRespuesta());
		Assert.assertFalse(respuestaComprobantes.getRespuesta().getComprobantes().isEmpty());
	}

	@Test
	public void obtenerRespuestaSinComprobantes()
			throws InterruptedException, ExecutionException, IllegalAccessException {
		Mockito.when(comprobantesPMCUnificadoBO.obtenerComprobantesPMCPorEmpresaAsync(Matchers.any(Cliente.class),
				Matchers.any(TransaccionDTO.class))).thenReturn(futureComprobantes3);
		Mockito.when(transferencias.obtenerComprobantesTransferenciaAsync(Matchers.any(Cliente.class),
				Matchers.any(TransaccionDTO.class))).thenReturn(futureComprobantes1);
		Mockito.when(debitosUnificadosBO.obtenerComprobantesAsync(Matchers.any(Cliente.class),
				Matchers.any(TransaccionDTO.class))).thenReturn(futureComprobantes3);
		Mockito.when(comprobantesPagoTarjetasBO.obtenerComprobantesPagoTarjetasAsync(Matchers.any(Cliente.class),
				Matchers.any(TransaccionDTO.class), Matchers.anyBoolean())).thenReturn(futureComprobantes2);
		Mockito.when(transferenciaInmediataBO.obtenerComprobantesCompraVentaDolarAsync(Matchers.anyString(),
				Matchers.any(TransaccionDTO.class))).thenReturn(futureComprobantes3);
		Mockito.when(comprobantes7x24BO.obtenerComprobantesPagoHaberesYHonorariosUnificadosAsync(
				Matchers.any(Cliente.class), Matchers.any(TransaccionDTO.class))).thenReturn(futureComprobantes4);
		Mockito.when(transferenciaInmediataBO.obtenerComprobantesPagoDeComprasAsync(Matchers.any(Cliente.class),
				Matchers.any(TransaccionDTO.class))).thenReturn(futureComprobantes3);
		Mockito.when(transferenciaInmediataBO.obtenerComprobantesPagoDeComprasAsync(Matchers.anyString(),
				Matchers.any(TransaccionDTO.class))).thenReturn(futureComprobantes3);
		Mockito.when(futureComprobantes1.isDone()).thenReturn(true);
		Mockito.when(futureComprobantes2.isDone()).thenReturn(true);
		Mockito.when(futureComprobantes3.isDone()).thenReturn(true);
		Mockito.when(futureComprobantes4.isDone()).thenReturn(true);
		FieldUtils.writeDeclaredField(coordinadorComprobantesBO, "topeComprobantes", "1000", true);

		LinkedList<ComprobanteDTO> listaComprobantes = new LinkedList<ComprobanteDTO>();
		ComprobantesDTO comprobantesDTO = new ComprobantesDTO(listaComprobantes);
		Respuesta<ComprobantesDTO> respuesta = respuestaFactory.crearRespuestaOk(comprobantesDTO);
		Mockito.when(futureComprobantes1.get()).thenReturn(respuesta);
		Mockito.when(futureComprobantes2.get()).thenReturn(respuesta);
		Mockito.when(futureComprobantes3.get()).thenReturn(respuesta);
		Mockito.when(futureComprobantes4.get()).thenReturn(respuesta);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		FiltroComprobanteDTO filtroComprobanteDTO = obtenerFiltroPagoDeServicios();
		filtroComprobanteDTO.setTransacciones(obtenerLista());
		Respuesta<ComprobantesDTO> respuestaComprobantes = coordinadorComprobantesBO.obtenerComprobantes(new Cliente(),
				filtroComprobanteDTO);

		Assert.assertEquals(EstadoRespuesta.ERROR, respuestaComprobantes.getEstadoRespuesta());
		/*Assert.assertEquals(TipoError.SIN_COMPROBANTES.getDescripcion(),
				respuestaComprobantes.getItemsMensajeRespuesta().get(0).getTipoError());*/
	}

	private List<TransaccionDTO> obtenerLista() {
		List<TransaccionViewIn> lisTransaccion = new ArrayList<TransaccionViewIn>();
		for (TransaccionEnum trans : TransaccionEnum.values()) {
         if (!TransaccionEnum.TRANSACCIONES.equals(trans)
            //se quita temporalmente compra_venta
            && !TransaccionEnum.COMPRA_VENTA.equals(trans)) {
				lisTransaccion.add(new TransaccionViewIn(trans.getId()));
         }
		}
		List<TransaccionDTO> lisTransaccionDTO = new ArrayList<TransaccionDTO>();
		for (TransaccionViewIn viewIn : lisTransaccion) {
			lisTransaccionDTO.add(
					new TransaccionDTO(Integer.valueOf(viewIn.getIdTransaccion()), new Date(), new Date(), "pepe"));
		}
		return lisTransaccionDTO;
	}

	@Test
	public void obtenerRespuestaWarningConComprobantes()
			throws InterruptedException, ExecutionException, IllegalAccessException {
		Mockito.when(debitosAutomatomaticosBOImpl.obtenerComprobantesAsync(Matchers.any(Cliente.class)))
				.thenReturn(futureComprobantes3);
		Mockito.when(comprobantesPagoTarjetasBO.obtenerComprobantesPagoTarjetasAsync(Matchers.any(Cliente.class),
				Matchers.any(TransaccionDTO.class), Matchers.anyBoolean())).thenReturn(futureComprobantes3);
		Mockito.when(pagoMisCuentasBO.obtenerComprobantesPMCAsync(Matchers.any(Cliente.class),
				Matchers.any(TransaccionDTO.class))).thenReturn(futureComprobantes3);
		Mockito.when(debitoAutomaticoEnCuentaBO
				.obtenerComprobantesDebitoAutomaticoEnCuentaAsync(Matchers.any(Cliente.class)))
				.thenReturn(futureComprobantes3);

		Mockito.when(transferencias.obtenerComprobantesTransferenciaAsync(Matchers.any(Cliente.class),
				Matchers.any(TransaccionDTO.class))).thenReturn(futureComprobantes3);
		Mockito.when(comprobantesPMCUnificadoBO.obtenerComprobantesPMCAsync(Matchers.any(Cliente.class),
				Matchers.any(TransaccionDTO.class))).thenReturn(futureComprobantes1);
		Mockito.when(futureComprobantes1.isDone()).thenReturn(true);
		Mockito.when(futureComprobantes2.isDone()).thenReturn(true);
		Mockito.when(futureComprobantes3.isDone()).thenReturn(true);
		Mockito.when(futureComprobantes4.isDone()).thenReturn(true);
		Respuesta<ComprobantesDTO> respuesta = respuestaFactory.crearRespuestaOk(obtenerMocks());
		Respuesta<ComprobantesDTO> respuestaError = new Respuesta<ComprobantesDTO>();
		respuestaError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		ComprobantesDTO comprobantesDTO = new ComprobantesDTO(new LinkedList<ComprobanteDTO>());
		respuestaError.setRespuesta(comprobantesDTO);
		Mockito.when(futureComprobantes1.get()).thenReturn(respuesta);
		Mockito.when(futureComprobantes2.get()).thenReturn(respuestaError);
		Mockito.when(futureComprobantes3.get()).thenReturn(respuestaError);
		Mockito.when(futureComprobantes4.get()).thenReturn(respuestaError);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		FieldUtils.writeDeclaredField(coordinadorComprobantesBO, "topeComprobantes", "100", true);
		FiltroComprobanteDTO filtroComprobanteDTO = obtenerFiltroPagoDeServicios();
		TransaccionDTO transaccionDTO = new TransaccionDTO(2, new Date(), new Date(), null);
		filtroComprobanteDTO.getTransacciones().add(transaccionDTO);
		Respuesta<ComprobantesDTO> respuestaComprobantes = coordinadorComprobantesBO.obtenerComprobantes(new Cliente(),
				filtroComprobanteDTO);

		Assert.assertEquals(TipoError.ALERTA_COMPROBANTES.getDescripcion(),
				respuestaComprobantes.getItemsMensajeRespuesta().get(0).getTipoError());
		Assert.assertFalse(respuestaComprobantes.getRespuesta().getComprobantes().isEmpty());
	}

	@Test
	public void obtenerRespuestaWarningSinComprobantes()
			throws InterruptedException, ExecutionException, IllegalAccessException {
		Mockito.when(debitosAutomatomaticosBOImpl.obtenerComprobantesAsync(Matchers.any(Cliente.class)))
				.thenReturn(futureComprobantes3);
		Mockito.when(comprobantesPagoTarjetasBO.obtenerComprobantesPagoTarjetasAsync(Matchers.any(Cliente.class),
				Matchers.any(TransaccionDTO.class), Matchers.anyBoolean())).thenReturn(futureComprobantes3);
		Mockito.when(pagoMisCuentasBO.obtenerComprobantesPMCAsync(Matchers.any(Cliente.class),
				Matchers.any(TransaccionDTO.class))).thenReturn(futureComprobantes3);
		Mockito.when(debitoAutomaticoEnCuentaBO
				.obtenerComprobantesDebitoAutomaticoEnCuentaAsync(Matchers.any(Cliente.class)))
				.thenReturn(futureComprobantes3);

		Mockito.when(comprobantesPMCUnificadoBO.obtenerComprobantesPMCAsync(Matchers.any(Cliente.class),
				Matchers.any(TransaccionDTO.class))).thenReturn(futureComprobantes3);
		Mockito.when(futureComprobantes1.isDone()).thenReturn(true);
		Mockito.when(futureComprobantes2.isDone()).thenReturn(true);
		Mockito.when(futureComprobantes3.isDone()).thenReturn(true);
		Mockito.when(futureComprobantes4.isDone()).thenReturn(true);
		ComprobantesDTO comprobantesDTO = new ComprobantesDTO(new LinkedList<ComprobanteDTO>());
		Respuesta<ComprobantesDTO> respuesta = respuestaFactory.crearRespuestaOk(comprobantesDTO);
		Respuesta<ComprobantesDTO> respuestaError = new Respuesta<ComprobantesDTO>();
		respuestaError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		respuestaError.setRespuesta(comprobantesDTO);
		Mockito.when(futureComprobantes1.get()).thenReturn(respuesta);
		Mockito.when(futureComprobantes2.get()).thenReturn(respuestaError);
		Mockito.when(futureComprobantes3.get()).thenReturn(respuestaError);
		Mockito.when(futureComprobantes4.get()).thenReturn(respuestaError);
		FieldUtils.writeDeclaredField(coordinadorComprobantesBO, "topeComprobantes", "100", true);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		FiltroComprobanteDTO filtroComprobanteDTO = obtenerFiltroPagoDeServicios();
		Respuesta<ComprobantesDTO> respuestaComprobantes = coordinadorComprobantesBO.obtenerComprobantes(new Cliente(),
				filtroComprobanteDTO);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuestaComprobantes.getEstadoRespuesta());
		Assert.assertEquals(1, respuestaComprobantes.getItemsMensajeRespuesta().size());
	}

	@Test
	public void obtenerRespuestaComprobantesErrorTotal()
			throws InterruptedException, ExecutionException, IllegalAccessException {
		Mockito.when(debitosAutomatomaticosBOImpl.obtenerComprobantesAsync(Matchers.any(Cliente.class)))
				.thenReturn(futureComprobantes3);
		Mockito.when(comprobantesPagoTarjetasBO.obtenerComprobantesPagoTarjetasAsync(Matchers.any(Cliente.class),
				Matchers.any(TransaccionDTO.class), Matchers.anyBoolean())).thenReturn(futureComprobantes3);
		Mockito.when(pagoMisCuentasBO.obtenerComprobantesPMCAsync(Matchers.any(Cliente.class),
				Matchers.any(TransaccionDTO.class))).thenReturn(futureComprobantes3);
		Mockito.when(debitoAutomaticoEnCuentaBO
				.obtenerComprobantesDebitoAutomaticoEnCuentaAsync(Matchers.any(Cliente.class)))
				.thenReturn(futureComprobantes3);
		Mockito.when(comprobantesPMCUnificadoBO.obtenerComprobantesPMCAsync(Matchers.any(Cliente.class),
				Matchers.any(TransaccionDTO.class))).thenReturn(futureComprobantes3);
		Mockito.when(futureComprobantes1.isDone()).thenReturn(true);
		Mockito.when(futureComprobantes2.isDone()).thenReturn(true);
		Mockito.when(futureComprobantes3.isDone()).thenReturn(true);
		Mockito.when(futureComprobantes4.isDone()).thenReturn(true);
		ComprobantesDTO comprobantesDTO = new ComprobantesDTO(new LinkedList<ComprobanteDTO>());
		Respuesta<ComprobantesDTO> respuestaError = new Respuesta<ComprobantesDTO>();
		respuestaError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		respuestaError.setRespuesta(comprobantesDTO);
		Mockito.when(futureComprobantes1.get()).thenReturn(respuestaError);
		Mockito.when(futureComprobantes2.get()).thenReturn(respuestaError);
		Mockito.when(futureComprobantes3.get()).thenReturn(respuestaError);
		Mockito.when(futureComprobantes4.get()).thenReturn(respuestaError);
		FieldUtils.writeDeclaredField(coordinadorComprobantesBO, "topeComprobantes", "100", true);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		FiltroComprobanteDTO filtroComprobanteDTO = obtenerFiltroPagoDeServicios();
		Respuesta<ComprobantesDTO> respuestaComprobantes = coordinadorComprobantesBO.obtenerComprobantes(new Cliente(),
				filtroComprobanteDTO);

		Assert.assertEquals(EstadoRespuesta.ERROR, respuestaComprobantes.getEstadoRespuesta());
		Assert.assertEquals(TipoError.ERROR_COMPROBANTES.getDescripcion(),
				respuestaComprobantes.getItemsMensajeRespuesta().get(0).getTipoError());
	}

	/**
	 * Obtener comprobantes.
	 */
	@Test
	public void filtrarPaginaCeroDesktop() {
		List<ComprobanteDTO> comprobantes = obtenerMocks().getComprobantes();
		List<ComprobanteDTO> respuesta = coordinadorComprobantesBO.filtrar(0, false, comprobantes);
		System.out.println(" PAGINA 0,  TOTAL COMPROBANTES: " + comprobantes.size() + " TOTAL FILTROS: "
				+ +respuesta.size() + " COMPROBANTES [" + respuesta + "]");
		Assert.assertNotNull(respuesta);
	}

	/**
	 * Obtener comprobantes.
	 */
	@Test
	public void filtrarPaginaUnoDesktop() {
		List<ComprobanteDTO> comprobantes = obtenerMocks().getComprobantes();
		List<ComprobanteDTO> respuesta = coordinadorComprobantesBO.filtrar(1, false, comprobantes);
		System.out.println(" PAGINA 1,  TOTAL COMPROBANTES: " + comprobantes.size() + " REGISTROS PAGINADOS: "
				+ respuesta.size() + " comprobantes [" + respuesta + "]");
		Assert.assertNotNull(respuesta);
	}

	/**
	 * Obtener comprobantes.
	 */
	@Test
	public void filtrarPaginaTresDeTresDesktop() {
		List<ComprobanteDTO> comprobantes = obtenerMocks().getComprobantes();
		List<ComprobanteDTO> respuesta = coordinadorComprobantesBO.filtrar(2, false, comprobantes);
		System.out.println(" PAGINA 3,  TOTAL COMPROBANTES: " + comprobantes.size() + " REGISTROS PAGINADOS: "
				+ respuesta.size() + " comprobantes [" + respuesta + "]");
		Assert.assertNotNull(respuesta);
	}

	/**
	 * Obtener mocks.
	 * 
	 * @return the comprobantes DTO
	 */
	private ComprobantesDTO obtenerMocks() {
		LinkedList<ComprobanteDTO> comprobantes = new LinkedList<ComprobanteDTO>();
		for (int i = 1; i < 29; i++) {
			comprobantes.add(obtenerComprobanteDTO(i, TipoOperacionComprobanteEnum.TRANSFERENCIA_INMEDIATA));
		}
		return new ComprobantesDTO(comprobantes);
	}

	/**
	 * Obtener comprobante DTO.
	 * 
	 * @param dia
	 *            the dia
	 * @return the comprobante DTO
	 */
	private ComprobanteDTO obtenerComprobanteDTO(int dia, TipoOperacionComprobanteEnum op) {
		ComprobanteDTO comprobanteDTO = new ComprobanteDTO();
		comprobanteDTO.setFecha(new GregorianCalendar(2017, 4, dia).getTime());
		comprobanteDTO.setTipoOperacion(op);
		comprobanteDTO.setCtaMedioDePagoPesos("000-063917/0");
		comprobanteDTO.setTipoCtaMedioDePagoPesos(TipoCuenta.CUENTA_UNICA);
		comprobanteDTO.setDestinatario("Credito ciudad Mock");
		comprobanteDTO.setImporteDolares(null);
		comprobanteDTO.setImportePesos(new BigDecimal(1200));
		return comprobanteDTO;
	}

	/**
	 * Crear respuesta.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param respuestaClass
	 *            the respuesta class
	 * @param data
	 *            the data
	 * @param estado
	 *            the estado
	 * @param vacia
	 *            the vacia
	 * @param itemMensajes
	 *            the item mensajes
	 * @return the respuesta
	 */
	public <T> Respuesta<T> crearRespuesta(Class<T> respuestaClass, T data, EstadoRespuesta estado, Boolean vacia,
			List<ItemMensajeRespuesta> itemMensajes) {
		Respuesta<T> rta = new Respuesta<T>();
		rta.setRespuesta(data);
		rta.setEstadoRespuesta(estado);
		rta.setRespuestaVacia(vacia);
		rta.setItemMensajeRespuesta(itemMensajes);
		return rta;
	};

	private FiltroComprobanteDTO obtenerFiltroPagoDeServicios() {
		FiltroComprobanteDTO filtroComprobanteDTO = new FiltroComprobanteDTO();
		filtroComprobanteDTO.setCliente(new Cliente());
		TransaccionDTO transaccion = new TransaccionDTO();
		transaccion.setFechaDesde(new Date());
		transaccion.setFechaHasta(new Date());
		filtroComprobanteDTO.setComprobantesExcedidos(false);
		filtroComprobanteDTO.setExcedidoLimiteParcial(false);
		filtroComprobanteDTO.setFechaError(false);
		transaccion.setTransaccion(TransaccionEnum.PAGOS_DE_SERVICIOS);
		List<TransaccionDTO> transacciones = new ArrayList<TransaccionDTO>();
		transacciones.add(transaccion);
		filtroComprobanteDTO.setTransacciones(transacciones);
		return filtroComprobanteDTO;
	}

	@Test
	public void determinarEstadoRespuestaFinalTest() {
		List<EstadoRespuesta> estadosRespuesta = new ArrayList<EstadoRespuesta>();
		estadosRespuesta.add(EstadoRespuesta.OK);
		estadosRespuesta.add(EstadoRespuesta.ERROR);
		EstadoRespuesta estado;
		if (estadosRespuesta.contains(EstadoRespuesta.WARNING) || (estadosRespuesta.contains(EstadoRespuesta.OK)
				&& estadosRespuesta.contains(EstadoRespuesta.ERROR))) {
			estado = EstadoRespuesta.WARNING;
		} else {
			if (estadosRespuesta.contains(EstadoRespuesta.OK)) {
				estado = EstadoRespuesta.OK;
			} else {
				estado = EstadoRespuesta.ERROR;
			}
		}
		Assert.assertEquals(estado, EstadoRespuesta.WARNING);
	}

}
