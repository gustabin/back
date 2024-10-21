package ar.com.santanderrio.obp.servicios.perfil.dao;

import static org.mockito.Mockito.when;

import java.io.IOException;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.perfil.dao.impl.CambioDomicilioDAOImpl;
import ar.com.santanderrio.obp.servicios.perfil.entities.CambioDomicilioInEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.CambioDomicilioOutEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaDatosDomicilioInEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaDatosDomicilioOutEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaDatosTelefonoInEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaDatosTelefonoOutEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaDomiciliosInEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaDomiciliosOutEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.DatosComprobanteEntity;


@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        CambioDomicilioDAOTest.CambioDomicilioDAOTestConfiguration.class })
@Ignore
public class CambioDomicilioDAOTest extends IatxBaseDAOTest {

	/** The CambioDomicilioDAO. */
	@Autowired
	@InjectMocks
	private CambioDomicilioDAO cambioDomicilioDAO;

	/** The app context. */
	ApplicationContext appContext = new ClassPathXmlApplicationContext();

	/** The cliente. */
	private Cliente cliente = new Cliente();

	@Configuration
	public static class CambioDomicilioDAOTestConfiguration {

		/**
		 * CambioDomicilioDAO.
		 *
		 * @return the CambioDomicilioDAO
		 */
		@Bean
		public CambioDomicilioDAO cambioDomicilioDAO() {
			return new CambioDomicilioDAOImpl();
		}

//		@Bean
//	    public RespuestaFactory respuestaFactory() {
//		    RespuestaFactory respuestaFactoryMock = Mockito.mock(RespuestaFactory.class);
//		    when(respuestaFactoryMock.obtenerListItemMensaje(Matchers.anyString(), Matchers.anyString())).thenReturn(null);
//		    return respuestaFactoryMock;
//		}
//
//		@Bean
//		public MensajeBO mensajeBO() {
//		    MensajeBO mensajeBOMock = Mockito.mock(MensajeBO.class);
//		    Mensaje mensaje = new Mensaje();
//		    mensaje.setMensaje("Mensaje mockeado");
//		    when(mensajeBOMock.obtenerMensajePorCodigoConErrorGenerico(Matchers.anyString())).thenReturn(mensaje);
//		    return mensajeBOMock;
//		}

	}

	/**
	 * Inits.
	 */
	@Before
	public void init() throws ServiceException {
		MockitoAnnotations.initMocks(this);

		cliente = new Cliente();
		Segmento segmento = new Segmento();
		segmento.setSelect(false);
		segmento.setDuo(false);
        segmento.setPyme(false);
		segmento.setUniversidad(false);
		cliente.setNombre("CONSTANCIO PERCY");
		cliente.setApellido1("MILANDO");
		cliente.setApellido2("M");
		cliente.setSegmento(segmento);
	}

	/**
	 * consultarDomiciliosRegistradosTest
	 * 
	 * @throws IatxException
	 * @throws DAOException
	 */
	@Test(expected = DAOException.class)
	public void consultarDomiciliosRegistradosErrorTest() throws IatxException, DAOException {
		String servicio = "CNSDOMPROD";
		String version = "100";
		String tramaResponse = "200000000001P04HTML9999900010302GLPE92  ********00539021000003242017081416454200000000IBF00CNJ346 00000000CNSDOMPROD1000000            02615492    00        00 000000000201708141645320000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ00054 - FALLO READQ IATBM19K                             õ";

		ConsultaDomiciliosInEntity consultaDomiciliosInEntity = new ConsultaDomiciliosInEntity();
		consultaDomiciliosInEntity.setCliente(cliente);
		when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
		Respuesta<ConsultaDomiciliosOutEntity> respuesta = cambioDomicilioDAO
				.consultaDomiciliosRegistrados(consultaDomiciliosInEntity);
		Assert.assertNotNull(respuesta);
		Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
	}

	/**
	 * consultarDomiciliosRegistradosTest
	 * 
	 * @throws IatxException
	 * @throws DAOException
	 */
	@Test
	public void consultarDomiciliosRegistradosTest() throws IatxException, DAOException {
		String servicio = "CNSDOMPROD";
		String version = "100";
		String tramaResponse = "200000000000P04HTML0009900010302GLPE92  ********00460836000000062018031614440600000000IBF00JDY000000000000CNSDOMPROD1000000            02615492    00        00 014418437201803161443500000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0448300000000õSõ007õ005õ001õPRIõTACUARI                                           õ                                                                                                 199õ    õ0000000350õCAPITAL                       õ                              õ 1071   õ01õ080õPõ011    -4925-16871387õ005õLABõCEVALLOS                                          õ                            2   A                                                                   õ    õ0000000480õQUILMES                       õ                              õ 1878   õ02õ080õPõ011    -4343-20911391õ006õELEõ                                                  õVALERIANOPAULTADEO@COMIGNAGH.AR                                                                     õ    õ          õ                              õ                              õ        õ  õ   õ õ       -    -        õ007õELEõ                                                  õVALERIANOPAULTADEO@COMIGNAGH.AR                                                                     õ    õ          õ                              õ                              õ        õ  õ   õ õ       -    -        õ008õELEõ                                                  õVALERIANOPAULTADEO@COMIGNAGH                                                                        õ    õ0000000000õ                              õ                              õ        õ  õ   õ õ       -    -        õ000õ   õ                                                  õ                                                                                                    õ    õ          õ                              õ                              õ        õ  õ   õ õ                     õ000õ   õ                                                  õ                                                                                                    õ    õ          õ                              õ                              õ        õ  õ   õ õ                     õ000õ   õ                                                  õ                                                                                                    õ    õ          õ                              õ                              õ        õ  õ   õ õ                     õ000õ   õ                                                  õ                                                                                                    õ    õ          õ                              õ                              õ        õ  õ   õ õ                     õ000õ   õ                                                  õ                                                                                                    õ    õ          õ                              õ                              õ        õ  õ   õ õ                     õ015õ001õ000004007236õ0000õCASA CENTRAL                            õ02õCAJA DE AHORRO PESOS                    õ0001õ001õ000019406154õ0000õCASA CENTRAL                            õ14õFondo de desempleo                      õ0000õ001õ001000167318õ0099õCASA MATRIZ                             õ01õPLAZO FIJO - CUENTA INVERSORA           õ0000õ001õ001000237893õ0066õCORDOBA                                 õ01õPLAZO FIJO - CUENTA INVERSORA           õ0000õ001õ001000237930õ0066õCORDOBA                                 õ01õPLAZO FIJO - CUENTA INVERSORA           õ0000õ001õ001000237947õ0066õCORDOBA                                 õ01õPLAZO FIJO - CUENTA INVERSORA           õ0000õ001õ001000237954õ0066õCORDOBA                                 õ01õPLAZO FIJO - CUENTA INVERSORA           õ0000õ001õ001000237961õ0066õCORDOBA                                 õ01õPLAZO FIJO - CUENTA INVERSORA           õ0000õ001õ001000300681õ0000õCASA CENTRAL                            õ01õPLAZO FIJO - CUENTA INVERSORA           õ0000õ001õ001001411124õ0000õCASA CENTRAL                            õ01õPLAZO FIJO - CUENTA INVERSORA           õ0000õ001õ001001411131õ0000õCASA CENTRAL                            õ01õPLAZO FIJO - CUENTA INVERSORA           õ0000õ001õ001001411148õ0000õCASA CENTRAL                            õ01õPLAZO FIJO - CUENTA INVERSORA           õ0000õ001õ001001411155õ0000õCASA CENTRAL                            õ01õPLAZO FIJO - CUENTA INVERSORA           õ0000õ001õ001001411179õ0000õCASA CENTRAL                            õ01õPLAZO FIJO - CUENTA INVERSORA           õ0000õ001õ001001412134õ0000õCASA CENTRAL                            õ01õPLAZO FIJO - CUENTA INVERSORA           õ0000õ";

		ConsultaDomiciliosInEntity consultaDomiciliosInEntity = new ConsultaDomiciliosInEntity();
		consultaDomiciliosInEntity.setCliente(cliente);
		when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
		Respuesta<ConsultaDomiciliosOutEntity> respuesta = cambioDomicilioDAO
				.consultaDomiciliosRegistrados(consultaDomiciliosInEntity);
		Assert.assertNotNull(respuesta);
		Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
	}

	@Test
	public void consultaDatosDomicilioTest() throws IatxException, DAOException {
		String servicio = "CNSDOMPROD";
		String version = "100";
		String tramaResponse = "200000000000P04HTML0009900010300MRQT37  ********00976895000000542017052313224100000000IBF23984000000000000CMBDOMICRM1000000            "
				+ "00276937    00        00 000000000201705231322340000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0050300000000õ001õ00276937õ001õPRIõ  õCHACABUCO"
				+ "õ    õ    õ                            2   B                                                                199õ8-01-01                 "
				+ "000000000{000000000{                                                        õ    õ0000000402õ       õLUJAN                         õ     õ                              õ 6700   õ000000000õ02õ080õNOõ0001-01-01õNõPõNõ  õ00009947õ1997-12-11õB038448 õ7YTSõ2266õ2017-02-03-16.08.01.519050õ";

		ConsultaDatosDomicilioInEntity consultaDatosDomicilioInEntity = new ConsultaDatosDomicilioInEntity();
		consultaDatosDomicilioInEntity.setCliente(cliente);
		when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
		ConsultaDatosDomicilioOutEntity respuesta = cambioDomicilioDAO
				.consultaDatosDomicilio(consultaDatosDomicilioInEntity);
		Assert.assertNotNull(respuesta);
		Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
	}

	@Test(expected = DAOException.class)
	public void consultaDatosDomicilioErrorTest() throws IatxException, DAOException {
		String servicio = "CMBDOMICRM";
		String version = "100";
		String tramaResponse = "200000000001P04HTML9999900010302GLPE92  ********00539021000003242017081416454200000000IBF00CNJ346 00000000CNSDOMPROD1000000            02615492    00        00 000000000201708141645320000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ00054 - FALLO READQ IATBM19K                             õ";

		ConsultaDatosDomicilioInEntity consultaDomiciliosInEntity = new ConsultaDatosDomicilioInEntity();
		consultaDomiciliosInEntity.setCliente(cliente);
		when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
		ConsultaDatosDomicilioOutEntity respuesta = cambioDomicilioDAO
				.consultaDatosDomicilio(consultaDomiciliosInEntity);
		Assert.assertNotNull(respuesta);
		Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
	}

	@Test(expected = DAOException.class)
	public void consultaDatosTelefonoTest() throws IatxException, DAOException {
		String servicio = "CMBTELCRM_";
		String version = "100";
		String tramaResponse = "200000000000P04HTML0009900010300MRQT37  ********00976895000000552017052313224200000000IBF23986000000000000CMBTELCRM_1000000            00276937    00        00 013229544201705231322340000000000"
				+ "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0024700000000õ002õ001õ001õ003õ001õ011    õ4545õ4549    õ                    õ                             "
				+ "2õ2015-03-05-10.11.47.295311õ003õ001õ003õ002õ011    õ4393õ86797279õ                    õ                             2õ2010-02-03-16.34.56.369762õ";

		ConsultaDatosTelefonoInEntity consultaDatosTelefonoInEntity = new ConsultaDatosTelefonoInEntity();
		consultaDatosTelefonoInEntity.setCliente(cliente);
		consultaDatosTelefonoInEntity.setOpcion("C");
		consultaDatosTelefonoInEntity.setSecuenciaDom("001");

		when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
		ConsultaDatosTelefonoOutEntity respuesta = cambioDomicilioDAO
				.consultaDatosTelefono(consultaDatosTelefonoInEntity);
		Assert.assertNotNull(respuesta);
		Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
	}

	@Test(expected = DAOException.class)
	public void guardarCambioDomicilioTest() throws IatxException, DAOException {
		String servicio = "CMBDOMYTEL";
		String version = "100";
		String tramaResponse = "200000000000P04HTML0009900010302GLQG47  ********00069728000000122017060217143200000000IBF00DYM000000000000CMBDOMYTEL1000000"
				+ "02616647    00        00 017118961201706021714240000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0000900000000õ";

		CambioDomicilioInEntity cambioDomicilioInEntity = new CambioDomicilioInEntity();
		cambioDomicilioInEntity.setCliente(cliente);
		when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
		CambioDomicilioOutEntity respuesta = cambioDomicilioDAO.guardarCambioDomicilio(cambioDomicilioInEntity);
		Assert.assertNotNull(respuesta);
		Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
	}

	/**
	 * Test reporte.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */

	@Test
	public void testReporte() throws IOException, IllegalAccessException {
		DatosComprobanteEntity view = new DatosComprobanteEntity();
		view.setDomicilioCompletoActual("Las FLores 380 1F CP 1654 Wilde Buenos Aires \n Tel 42423432");
		view.setDomicilioCompletoAnterior("Matheu 1791 21 3 CP 1285 Capital Federal \n Tel 23453232423");
		view.setFecha("21/07/2009");
		view.setNroComprobante("123456789");

		FieldUtils.writeField(cambioDomicilioDAO, "logoCabecera",
				appContext.getResource("classpath:/report/comprobantes/logo_cabecera_comprobante.png"), true);
		FieldUtils.writeField(cambioDomicilioDAO, "logoCierre",
				appContext.getResource("classpath:/report/comprobantes/logo_cierre_comprobante.png"), true);
		FieldUtils.writeField(cambioDomicilioDAO, "fileJasperComprobanteCambioDomicilio",
				appContext.getResource("classpath:/report/perfil/cambioDomicilio/ComprobanteCambioDomicilio.jasper"),
				true);
		Reporte reporte = cambioDomicilioDAO.descargarComprobante(view);
		Assert.assertNotNull(reporte.getBytes());

	}
}
