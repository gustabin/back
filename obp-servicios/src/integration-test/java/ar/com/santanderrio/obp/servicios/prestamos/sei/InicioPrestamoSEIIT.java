package ar.com.santanderrio.obp.servicios.prestamos.sei;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.AbstractSEITest;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConfiguracionPagoCuotaPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultaPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultarPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.web.view.DetallePrestamoView;
import ar.com.santanderrio.obp.servicios.prestamos.sei.impl.InicioPrestamoSEIImpl;
import ar.com.santanderrio.obp.servicios.prestamos.view.CabeceraPrestamosView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ComprobantePrestamoView;
import ar.com.santanderrio.obp.servicios.prestamos.view.DetalleCuotaPrestamoView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ObtenerPrestamoInView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ObtenerPrestamoInViewMock;
import ar.com.santanderrio.obp.servicios.prestamos.view.PrestamosCanceladosView;
import ar.com.santanderrio.obp.servicios.prestamos.view.PrestamosView;
import ar.com.santanderrio.obp.servicios.prestamos.view.PrestamosViewMock;
import ar.com.santanderrio.obp.servicios.prestamos.view.ProximasCuotasView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ProximasCuotasViewMock;
import ar.com.santanderrio.obp.servicios.prestamos.view.ResultadoSimulacionView;
import ar.com.santanderrio.obp.servicios.prestamos.view.SolicitudSimulacionView;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.CuotasPrestamoManager;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.InicioPrestamoManager;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.SimuladorPrestamoManager;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.entity.DestinoPrestamo;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.view.PuntoDeAccesoView;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuentaMock;
import ar.com.santanderrio.obp.servicios.tarjetas.view.ComprobanteFeedbackView;

/**
 * The Class InicioPrestamoSEITest.
 *
 * @author mariano.g.pulera
 *
 */

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		ar.com.santanderrio.obp.servicios.prestamos.sei.InicioPrestamoSEIIT.InicioPrestamoSEITestConfiguration.class })
public class InicioPrestamoSEIIT extends AbstractSEITest {

	/** The simulador prestamo manager. */
	@Autowired
	private SimuladorPrestamoManager simuladorPrestamoManager;

	/** The prestamo manager. */
	@Autowired
	private InicioPrestamoManager prestamoManager;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The cuotas prestamo manager. */
	@Autowired
	private CuotasPrestamoManager cuotasPrestamoManager;

	/** The respuesta factory. */
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.AbstractSEITest#getServiceBeanToTest()
	 */
	@Override
	protected Object getServiceBeanToTest() {
		return applicationContext.getBean("inicioPrestamoSEI");
	}

	/**
	 * The Class InicioPrestamoSEITestConfiguration.
	 */
	@Configuration
	public static class InicioPrestamoSEITestConfiguration {

		/**
		 * Inicio prestamo SEI.
		 *
		 * @return the inicio prestamo SEI
		 */
		@Bean(name = "inicioPrestamoSEI")
		public InicioPrestamoSEI inicioPrestamoSEI() {
			return new InicioPrestamoSEIImpl();
		}

		/**
		 * Estadistica manager.
		 *
		 * @return the estadistica manager
		 */
		@Bean
		public EstadisticaManager estadisticaManager() {
			return Mockito.mock(EstadisticaManager.class);
		}

		/**
		 * Inicio prestamo manager.
		 *
		 * @return the inicio prestamo manager
		 */
		@Bean
		public InicioPrestamoManager inicioPrestamoManager() {
			return Mockito.mock(InicioPrestamoManager.class);
		}

		/**
		 * Simulador prestamo manager.
		 *
		 * @return the simulador prestamo manager
		 */
		@Bean
		public SimuladorPrestamoManager simuladorPrestamoManager() {
			return Mockito.mock(SimuladorPrestamoManager.class);
		}

		/**
		 * Mensaje BO.
		 *
		 * @return the mensaje BO
		 */
		@Bean
		public MensajeBO mensajeBO() {
			return Mockito.mock(MensajeBO.class);
		}

		/**
		 * Cuotas prestamo manager.
		 *
		 * @return the cuotas prestamo manager
		 */
		@Bean
		public CuotasPrestamoManager cuotasPrestamoManager() {
			return Mockito.mock(CuotasPrestamoManager.class);
		}

		/**
		 * Respuesta factory.
		 *
		 * @return the respuesta factory
		 */
		@Bean
		public RespuestaFactory respuestaFactory() {
			return Mockito.mock(RespuestaFactory.class);
		}

	}

	/**
	 * Obtener cabecera.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerCabecera() {

		// When
		when(prestamoManager.obtenerCabecera()).thenReturn(
				respuestaFactory.crearRespuestaOk(CabeceraPrestamosView.class, new CabeceraPrestamosView()));
		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/prestamos/obtenerCabecera");

		// Then
		Respuesta<CabeceraPrestamosView> respuesta = client.post(null, Respuesta.class);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

	}

	/**
	 * Obtener prestamos.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerPrestamos() {

		// When
		when(prestamoManager.obtenerPrestamos(Matchers.any(ObtenerPrestamoInView.class)))
				.thenReturn(respuestaFactory.crearRespuestaOk(PrestamosView.class, new PrestamosView()));
		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/prestamos/obtenerPrestamos");

		// Then
		Respuesta<PrestamosView> respuesta = client.post(new ObtenerPrestamoInView(), Respuesta.class);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

	}

	/**
	 * Notificar acceso desde home.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void notificarAccesoDesdeHome() {

		// When
		when(prestamoManager.notificarAccesoDesdeHome()).thenReturn(respuestaFactory.crearRespuestaOk(Void.class));
		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/prestamos/notificarAccesoDesdeHome");

		// Then
		Respuesta<Void> respuesta = client.post(null, Respuesta.class);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

	}

	/**
	 * Gets the detalle cuotas.
	 *
	 * @return the detalle cuotas
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void getDetalleCuotas() {

		// When
		when(prestamoManager.getDetallePrestamos(Matchers.any(ConsultaPrestamo.class), Matchers.any(Boolean.class)))
				.thenReturn(respuestaFactory.crearRespuestaOk(DetallePrestamoView.class, new DetallePrestamoView()));
		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/prestamos/obtenerDetalleCuota");
		addSleepTime(5000);

		// Then
		Respuesta<DetallePrestamoView> respuesta = client.post(new ConsultarPrestamo(), Respuesta.class);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

	}

	/**
	 * Obtener configuracion pago cuota prestamo.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerConfiguracionPagoCuotaPrestamo() {

		// When
		when(prestamoManager.obtenerConfiguracionPagoCuotaPrestamo(Matchers.any(ConsultaPrestamo.class),
				Matchers.any(Boolean.class)))
						.thenReturn(respuestaFactory.crearRespuestaOk(ConfiguracionPagoCuotaPrestamo.class,
								new ConfiguracionPagoCuotaPrestamo()));
		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/prestamos/obtenerConfiguracionPagoCuotaPrestamo");
		addSleepTime(5000);
		
		// Then
		Respuesta<ConfiguracionPagoCuotaPrestamo> respuesta = client.post(new ConsultaPrestamo(), Respuesta.class);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

	}

	/**
	 * Grabar estadistica inicio simulador prestamo.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void grabarEstadisticaInicioSimuladorPrestamo() {

		// When
		when(simuladorPrestamoManager.grabarEstadisticaInicioSimuladorPrestamo(Matchers.any(PuntoDeAccesoView.class)))
				.thenReturn(respuestaFactory.crearRespuestaOk(Void.class));
		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/prestamos/grabarEstadisticaInicioSimuladorPrestamo");
		addSleepTime(5000);
		
		// Then
		Respuesta<Void> respuesta = client.post(new PuntoDeAccesoView(), Respuesta.class);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

	}

	/**
	 * Obtener resultado simulacion.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerResultadoSimulacion() {

		// When
		DestinoPrestamo destinoPrestamo = new DestinoPrestamo();
		destinoPrestamo.setDescripcionUG("Viajes y turismo");
		destinoPrestamo.setDestinoDeFondosUG("35001");
		destinoPrestamo.setDivisaProductoUG("ARS");
		destinoPrestamo.setProductoUG("35");
		destinoPrestamo.setSubproductoUG("0027");

		Cuenta cuenta = CuentaMock.completarInfoCuenta();

		ResultadoSimulacionView resultadoSimulacionView = new ResultadoSimulacionView.ResultadoSimulacionViewBuilder()
				.importe(new BigDecimal("13261")).importeNetoItem("00000000132610000").cuentaDestinoItem(cuenta)
				.cantidadCuotasItem("6").motivoPrestamoItem(destinoPrestamo)
				.importePrimeraCuotaItem("00000000026142500").fechaPrimerPagoItem("23/05/2017")
				.capitalInteresesPeriodoItem("00000000025027900").cargoSeguroVidaItem("00000000000000000")
				.ivaItem("00000000000975100").otrosImpuestosItem("00000000000139500").tipoTasaItem("F")
				.tnaItem("038730000").teaItem("046401294").cftnaItem("046800000").cftnaSinImpItem("038500000").build();

		when(simuladorPrestamoManager.simular(Matchers.any(SolicitudSimulacionView.class)))
				.thenReturn(respuestaFactory.crearRespuestaOk(ResultadoSimulacionView.class, resultadoSimulacionView));
		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/prestamos/simulador");

		// Then
		Respuesta<ResultadoSimulacionView> respuesta = client.post(new SolicitudSimulacionView(), Respuesta.class);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

	}

	/**
	 * Confirmacion solicitud prestamo.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void confirmacionSolicitudPrestamo() {

		// When
		when(simuladorPrestamoManager.adquirirPrestamo(Matchers.any(SolicitudSimulacionView.class)))
				.thenReturn(respuestaFactory.crearRespuestaOk(ComprobanteFeedbackView.class));
		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/prestamos/confirmacionSolicitudPrestamo");
		addSleepTime(5000);
		
		// Then
		Respuesta<ComprobanteFeedbackView> respuesta = client.post(new SolicitudSimulacionView(), Respuesta.class);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

	}

	/**
	 * Grabar estadistica visualizacion comprobante.
	 */
	@Test
	public void grabarEstadisticaVisualizacionComprobante() {

		// When
		when(simuladorPrestamoManager
				.grabarEstadisticaVisualizacionComprobante(Matchers.any(ComprobantePrestamoView.class)))
						.thenReturn(true);
		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/prestamos/grabarEstadisticaVisualizacionComprobante");
		addSleepTime(5000);
		
		// Then
		Boolean respuesta = client.post(new ComprobantePrestamoView(), Boolean.class);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(true, respuesta);

	}

	/**
	 * Grabar estadistica visualizacion ayuda.
	 */
	@Test
	public void grabarEstadisticaVisualizacionAyuda() {

		// When
		when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(true);
		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/prestamos/grabarEstadisticaVisualizacionAyuda");
		addSleepTime(5000);

		// Then
		Boolean respuesta = client.post(null, Boolean.class);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(true, respuesta);

	}

	/**
	 * Grabar estadistica visualizacion tasas.
	 */
	@Test
	public void grabarEstadisticaVisualizacionTasas() {

		// When
		when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(true);
		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/prestamos/grabarEstadisticaVisualizacionTasas");
		addSleepTime(5000);

		// Then
		Boolean respuesta = client.post(null, Boolean.class);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(true, respuesta);

	}

	/**
	 * Obtener proximas cuotas.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerProximasCuotas() {

		// When
		when(cuotasPrestamoManager.obtenerProximasCuotas(Matchers.any(ConsultaPrestamo.class)))
				.thenReturn(respuestaFactory.crearRespuestaOk(ProximasCuotasView.class));
		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/prestamos/obtenerProximasCuotas");
		addSleepTime(5000);

		// Then
		Respuesta<ProximasCuotasView> respuesta = client.post(new ConsultaPrestamo(), Respuesta.class);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

	}

	/**
	 * Grabar estadistica detalle proxima cuota.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void grabarEstadisticaDetalleProximaCuota() {

		// When
		when(cuotasPrestamoManager.grabarEstadisticaDetalleProximaCuota(Matchers.any(ConsultaPrestamo.class)))
				.thenReturn(respuestaFactory.crearRespuestaOk(Void.class));
		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/prestamos/grabarEstadisticaDetalleProximaCuota");
		addSleepTime(5000);

		// Then
		Respuesta<Void> respuesta = client.post(new ConsultaPrestamo(), Respuesta.class);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

	}

	/**
	 * Grabar estadistica detalle cuota paga.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void grabarEstadisticaDetalleCuotaPaga() {

		// When
		when(cuotasPrestamoManager.grabarEstadisticaDetalleCuotaPaga(Matchers.any(ConsultaPrestamo.class)))
				.thenReturn(respuestaFactory.crearRespuestaOk(Void.class));
		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/prestamos/grabarEstadisticaDetalleCuotaPaga");
		addSleepTime(5000);

		// Then
		Respuesta<Void> respuesta = client.post(new ConsultaPrestamo(), Respuesta.class);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

	}

	/**
	 * Obtener prestamos cancelados.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerPrestamosCancelados() {

		// When
		when(prestamoManager.obtenerPrestamosCancelados()).thenReturn(
				respuestaFactory.crearRespuestaOk(PrestamosCanceladosView.class, new PrestamosCanceladosView()));
		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/prestamos/obtenerPrestamosCancelados");
		addSleepTime(5000);

		// Then
		Respuesta<PrestamosCanceladosView> respuesta = client.post(null, Respuesta.class);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

	}

	/**
	 * Obtener cuotas de cancelado.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerCuotasDeCancelado() {

		// When
		when(cuotasPrestamoManager.obtenerCuotasDeCancelado(Matchers.any(ConsultaPrestamo.class)))
				.thenReturn(respuestaFactory.crearRespuestaOk(ProximasCuotasView.class));
		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/prestamos/obtenerCuotasDeCancelado");
		addSleepTime(5000);

		// Then
		Respuesta<ProximasCuotasView> respuesta = client.post(new ConsultaPrestamo(), Respuesta.class);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

	}

	/**
	 * Grabar estadistica detalle cuota de cancelado.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void grabarEstadisticaDetalleCuotaDeCancelado() {

		// When
		when(cuotasPrestamoManager.grabarEstadisticaDetalleCuotaDeCancelado())
				.thenReturn(respuestaFactory.crearRespuestaOk(Void.class));
		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/prestamos/grabarEstadisticaDetalleCuotaDeCancelado");
		addSleepTime(5000);

		// Then
		Respuesta<Void> respuesta = client.post(null, Respuesta.class);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

	}

	/**
	 * Obtener detalle.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerDetalle() {

		// When
		when(cuotasPrestamoManager.obtenerDetalle(Matchers.any(ConsultaPrestamo.class)))
				.thenReturn(respuestaFactory.crearRespuestaOk(ProximasCuotasView.class));
		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/prestamos/obtenerDetalle");
		addSleepTime(5000);

		// Then
		Respuesta<ProximasCuotasView> respuesta = client.post(new ConsultaPrestamo(), Respuesta.class);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

	}

	/**
	 * Ver detalle prestamo.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void verDetallePrestamo() {

		// When
		when(prestamoManager.verDetallePrestamo(Matchers.any(ConsultaPrestamo.class))).thenReturn(
				respuestaFactory.crearRespuestaOk(DetalleCuotaPrestamoView.class, new DetalleCuotaPrestamoView()));
		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/prestamos/verDetallePrestamo");
		addSleepTime(5000);

		// Then
		Respuesta<DetalleCuotaPrestamoView> respuesta = client.post(new ConsultaPrestamo(), Respuesta.class);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

	}

	/**
	 * Obtiene las cuotas pagas Ok de un prestamos.
	 * 
	 * @author florencia.n.martinez
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerCuotasPagasPrestamosTest() {
		Mockito.when(cuotasPrestamoManager.obtenerCuotasPagas(Matchers.any(ConsultaPrestamo.class)))
				.thenReturn(respuestaFactory.crearRespuestaOk(ProximasCuotasView.class,
						ProximasCuotasViewMock.completarInfoProximasCuotasView()));

		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/prestamos/obtenerCuotasPagas");
		addSleepTime(5000);
		
		Respuesta<PrestamosView> retorno = client.post(new ConsultaPrestamo(), Respuesta.class);

		Assert.assertNotNull(retorno);
		System.out.println(retorno);
	}
	
	/**
	 * Test para obtener el listado de prestamos OK.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerPrestamosTest() {
		Mockito.when(prestamoManager.obtenerPrestamos(Matchers.any(ObtenerPrestamoInView.class))).thenReturn(
				respuestaFactory.crearRespuestaOk(PrestamosView.class, PrestamosViewMock.obtenerPretamosViewOK()));

		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/prestamos/obtenerPrestamos");
		addSleepTime(5000);
		
		Respuesta<PrestamosView> retorno = client.post(ObtenerPrestamoInViewMock.obtenerPrestamoInViewPersonal(),
				Respuesta.class);

		Assert.assertNotNull(retorno);
	}

	/**
	 * Test para obtener el listado de prestamos con Error en la consulta al
	 * servicio de Preformalizacion.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerPrestamosConErrorPreformalizacionTest() {
		Mockito.when(prestamoManager.obtenerPrestamos(Matchers.any(ObtenerPrestamoInView.class)))
				.thenReturn(obtenerRespuestaPrestamosViewConErrorPreformalizacion());

		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/prestamos/obtenerPrestamos");
		addSleepTime(5000);
		Respuesta<PrestamosView> retorno = client.post(ObtenerPrestamoInViewMock.obtenerPrestamoInViewPersonal(),
				Respuesta.class);

		Assert.assertNotNull(retorno);
	}

	/**
	 * Obtiene la respuesta de prestamos view con error en la preformalizacion.
	 *
	 * @return the respuesta
	 */
	private Respuesta<PrestamosView> obtenerRespuestaPrestamosViewConErrorPreformalizacion() {
		Respuesta<PrestamosView> respuestaPrestamosView = new Respuesta<PrestamosView>();
		respuestaPrestamosView.setEstadoRespuesta(EstadoRespuesta.WARNING);
		respuestaPrestamosView.setItemMensajeRespuesta(obtenerItemsMensaje());
		respuestaPrestamosView.setRespuesta(PrestamosViewMock.obtenerPretamosViewConErrorPreformalizacion());
		respuestaPrestamosView.setRespuestaVacia(false);
		return respuestaPrestamosView;
	}

	/**
	 * Obtiene la lista de items mensaje.
	 *
	 * @return the list
	 */
	private List<ItemMensajeRespuesta> obtenerItemsMensaje() {
		List<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>();
		items.add(obtenerItem("", TipoError.ERROR_PLAZO_PRESTAMOS.getDescripcion(),
				"Ourrió un error en nuestros servicios y no podemos mostrarte el plazo de tus préstamos.  Por favor volvé a intentar en unos minutos."));
		return items;
	}

	/**
	 * Obtiene el item.
	 *
	 * @param tag
	 *            the tag
	 * @param tipoError
	 *            the tipo error
	 * @param mensaje
	 *            the mensaje
	 * @return the item mensaje respuesta
	 */
	private ItemMensajeRespuesta obtenerItem(String tag, String tipoError, String mensaje) {
		ItemMensajeRespuesta item = new ItemMensajeRespuesta();
		item.setTag(tag);
		item.setTipoError(tipoError);
		item.setMensaje(mensaje);
		return item;
	}
}