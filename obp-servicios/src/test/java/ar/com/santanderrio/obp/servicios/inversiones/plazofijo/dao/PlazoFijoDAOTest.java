package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dao;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.inversiones.exception.ImporteMayorAlMaximoException;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaGralPlazoFijoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaGralPlazoFijoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaInteresantePlazoFijoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaInteresantePlazoFijoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaPrecancelablePlazoFijoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaPrecancelablenPlazoFijoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaTasasPlazoFijoBPrivOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultaTiposPlazoFijoBPrivOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ImposicionPlazoFijoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ImposicionPlazoFijoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.MantenimientoPlazoFijoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.MantenimientoPlazoFijoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.PrecancelacionPlazoFijoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.SimulacionPlazoFijoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.SimulacionPlazoFijoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.SimulacionPrecancelableOutEntity;
import ar.com.santanderrio.obp.servicios.transferencias.exception.SaldoInsuficienteException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TimeOutException;

/**
 * The class PlazoFijoDaoTest
 * 
 * @author juan.pablo.picate
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		PlazoFijoDAOTest.PlazoFijoDAOTestConfiguration.class })
@TestPropertySource(properties = { "DB.TIMEOUT = 30000" })

public class PlazoFijoDAOTest extends IatxBaseDAOTest {

	private static final String TIMEOUT_EXCEPTION = "iatx.exceptions.IatxConnectException: Se envió la transacción al CICS pero no se recibió respuesta.õ";

	/** The plazo fijo DAO. */
	@Autowired
	@InjectMocks
	private PlazoFijoDAO plazoFijoDAO;
	
	@InjectMocks
	private PlazoFijoDAOImpl plazoFijoDAOImpl;

	/*
	 * @Mock private IatxComm iatxComm;
	 */

	/** The cliente. */
	private Cliente cliente = new Cliente();

	/** The Constant BANCA_PERSONAL. */
	private static final String BANCA_PERSONAL = "BANCAPERSONAL";

	/** The Constant BANCA_RETAIL. */
	private static final String BANCA_RETAIL = "BR";
    
    /**
     * Inits the.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Before
    public void init() throws ServiceException {
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
     * The Class PlazoFijoDAOTestConfiguration.
     */
    @Configuration
    public static class PlazoFijoDAOTestConfiguration {

		/**
		 * Fondo DAO.
		 *
		 * @return the fondo DAO
		 */
		@Bean
		public PlazoFijoDAO plazoFijoDAO() {
			return new PlazoFijoDAOImpl();
		}

	}

	/**
	 * Consultar Tipos Test
	 * 
	 * @throws IatxException
	 * @throws DAOException
	 */
	@Ignore
	@Test
	public void consultarTiposTest() throws IatxException, DAOException {
		String servicio = "CNSPFTIPOS";
		String version = "110";
		String tramaResponse = "200000000000P04HTML0009900010301PRQP31  ********00029728000000072017011717095600000000IBF24277000000000000CNSPFTIPOS1000000            01576531    00        00 017019913201701171709500000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0011700000000õ004õ03õTRADICIONAL         õNõ16õPRECANCELABLE       õNõ17õINTERESANTE         õNõ92õDOLAR AHORRO        õNõ";

		when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
		ConsultaTiposPlazoFijoBPrivOutEntity respuesta = plazoFijoDAO.consultarTipos(cliente, BANCA_RETAIL, false);
		Assert.assertNotNull(respuesta);
		Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
	}

	/**
	 * Consultar Tipos TimeOutException
	 * 
	 * @throws DAOException
	 * @throws IatxException
	 */
	@Ignore
	@Test
	public void consultarTiposTimeOutException() throws DAOException, IatxException {
		String servicio = "CNSPFTIPOS";
		String version = "110";
		when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version))))
				.thenThrow(new IatxException(TIMEOUT_EXCEPTION));
		try {
			ConsultaTiposPlazoFijoBPrivOutEntity respuesta = plazoFijoDAO.consultarTipos(cliente, BANCA_RETAIL, false);
		} catch (Exception e) {
			Assert.assertEquals(TimeOutException.class, e.getClass());
		}
	}

	/**
	 * Consultar Tipos DAOExcepton
	 * 
	 * @throws DAOException
	 * @throws IatxException
	 */
	@Ignore
	@Test
	public void consultarTiposDAOException() throws DAOException, IatxException {
		String servicio = "CNSPFTIPOS";
		String version = "110";
		when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version))))
				.thenThrow(new IatxException("Error no Timeout"));
		try {
			ConsultaTiposPlazoFijoBPrivOutEntity respuesta = plazoFijoDAO.consultarTipos(cliente, BANCA_RETAIL, false);
		} catch (Exception e) {
			Assert.assertEquals(DAOException.class, e.getClass());
		}
	}

	/**
	 * Consultar tenencias test
	 * 
	 * @throws IatxException
	 * @throws DAOException
	 */
	@Ignore
	@Test
	public void consultarTenenciaTest() throws IatxException, DAOException {
		String servicio = "CNSPFGRAL_";
		String version = "130";
		String tramaResponse = "200000000000P04HTML0009900010301PRQP31  ********00833300000000062017011612273400000000IBF26171000000000000CNSPFGRAL_1300000            01576531    00        00 000000000201701161227290000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0658400000000õNõ09õ                              õ0072õ0000õ001000011622õ00023õ00000õ2015-09-23õAõ02358000õARSõPESO ARGENTINO      õ000000000150000õ00030õVENCIMIENTO    õ02358000õNõNõEõ04õNõ2015-10-23õ000000000000000õ000000000002907õNõ000000000000000õ000000000000000õ000000000150000õ2015-10-23õARSõ000000000150000õTRADICIONAL                   õ   õ0072õ0000õ007000638801õ0072õ0000õ007000638801õ00000000õ00000000õ0000õVõNõ                      õ000000000000000õ000000000000000õ õ                      õ000000000000000õ000000000000000õ õ                      õ000000000000000õ000000000000000õ õ                      õ000000000000000õ000000000000000õ õ                      õ000000000000000õ000000000000000õ õ000000000152907õ01õ0003õ00000000000000000õ                              õ0072õ0000õ001000011622õ00024õ00000õ2015-09-23õAõ00245000õUSDõDOLAR USA AMERICANO õ000000000300000õ00030õVENCIMIENTO    õ00245000õNõNõEõ04õNõ2015-10-23õ000000000000000õ000000000000604õNõ000000000000000õ000000000000000õ000000000300000õ2015-10-23õUSDõ000000000300000õTRADICIONAL                   õ   õ0072õ0000õ007000638801õ0072õ0000õ007000638801õ00000000õ00000000õ0000õVõNõ                      õ000000000000000õ000000000000000õ õ                      õ000000000000000õ000000000000000õ õ                      õ000000000000000õ000000000000000õ õ                      õ000000000000000õ000000000000000õ õ                      õ000000000000000õ000000000000000õ õ000000000300604õ01õ0003õ00000000000000000õ                              õ0072õ0000õ001000011622õ00025õ00000õ2015-09-23õAõ02358000õARSõPESO ARGENTINO      õ000000000500000õ00030õVENCIMIENTO    õ02358000õNõNõEõ04õNõ2015-10-23õ000000000000000õ000000000009690õNõ000000000000000õ000000000000000õ000000000500000õ2015-10-23õARSõ000000000500000õTRADICIONAL                   õ   õ0072õ0000õ007000638801õ0072õ0000õ007000638801õ00000000õ00000000õ0000õVõNõ                      õ000000000000000õ000000000000000õ õ                      õ000000000000000õ000000000000000õ õ                      õ000000000000000õ000000000000000õ õ                      õ000000000000000õ000000000000000õ õ                      õ000000000000000õ000000000000000õ õ000000000509690õ01õ0003õ00000000000000000õ                              õ0072õ0000õ001000011622õ00026õ00000õ2016-02-11õAõ02400000õARSõPESO ARGENTINO      õ000000001000000õ00032õVENCIMIENTO    õ02600000õNõNõEõ04õNõ2016-03-14õ000000000000000õ000000000022795õNõ000000000000000õ000000000000000õ000000001000000õ2016-03-14õARSõ000000001000000õTRADICIONAL                   õ   õ0072õ0000õ007000638801õ0072õ0000õ007000638801õ00000000õ00000000õ0000õVõNõ                      õ000000000000000õ000000000000000õ õ                      õ000000000000000õ000000000000000õ õ                      õ000000000000000õ000000000000000õ õ                      õ000000000000000õ000000000000000õ õ                      õ000000000000000õ000000000000000õ õ000000001022795õ01õ0003õ00000000000000000õ                              õ0072õ0000õ001000011622õ00027õ00000õ2016-06-27õAõ02725000õARSõPESO ARGENTINO      õ000000001000000õ00050õVENCIMIENTO    õ02925000õNõNõEõ04õNõ2016-08-16õ000000000000000õ000000000040068õNõ000000000000000õ000000000000000õ000000001000000õ2016-08-16õARSõ000000001000000õTRADICIONAL                   õ   õ0072õ0000õ007000638801õ0072õ0000õ007000638801õ00000000õ00000000õ0000õVõNõ                      õ000000000000000õ000000000000000õ õ                      õ000000000000000õ000000000000000õ õ                      õ000000000000000õ000000000000000õ õ                      õ000000000000000õ000000000000000õ õ                      õ000000000000000õ000000000000000õ õ000000001040068õ01õ0003õ00000000000000000õ                              õ0072õ0000õ001000011622õ00028õ00000õ2016-12-14õAõ01895000õARSõPESO ARGENTINO      õ000000000500000õ00180õDIAS FIJOS     õ01895000õNõNõEõ04õNõ2017-06-12õ000000000000000õ000000000046727õNõ000000000000000õ000000000000000õ000000000500000õ2017-01-13õARSõ000000000500000õINTERESANTE                   õ   õ0072õ0000õ007000638801õ0072õ0000õ007000638801õ00000000õ00000000õ0000õFõNõ                      õ000000000000000õ000000000000000õ õ                      õ000000000000000õ000000000000000õ õ                      õ000000000000000õ000000000000000õ õ                      õ000000000000000õ000000000000000õ õ                      õ000000000000000õ000000000000000õ õ000000000546727õ01õ0017õ00000000000000000õ                              õ0072õ0000õ001000011622õ00029õ00000õ2016-12-14õAõ02000000õARSõPESO ARGENTINO      õ000000000600000õ00180õVENCIMIENTO    õ02000000õNõNõEõ04õNõ2017-06-12õ000000000000000õ000000000059178õNõ000000000000000õ000000000000000õ000000000600000õ2017-06-12õARSõ000000000600000õPRECANCELABLE                 õ   õ0072õ0000õ007000638801õ0072õ0000õ007000638801õ00000000õ00000000õ0000õVõNõ                      õ000000000000000õ000000000000000õ õ                      õ000000000000000õ000000000000000õ õ                      õ000000000000000õ000000000000000õ õ                      õ000000000000000õ000000000000000õ õ                      õ000000000000000õ000000000000000õ õ000000000659178õ01õ0016õ00000000000000000õ                              õ0072õ0000õ001000011622õ00030õ00000õ2016-12-14õAõ01800000õARSõPESO ARGENTINO      õ000000000050000õ00030õVENCIMIENTO    õ02000000õNõNõEõ04õNõ2017-01-13õ000000000000000õ000000000000822õNõ000000000000000õ000000000000000õ000000000050000õ2017-01-13õARSõ000000000050000õTRADICIONAL                   õ   õ0072õ0000õ007000638801õ0072õ0000õ007000638801õ00000000õ00000000õ0000õVõNõ                      õ000000000000000õ000000000000000õ õ                      õ000000000000000õ000000000000000õ õ                      õ000000000000000õ000000000000000õ õ                      õ000000000000000õ000000000000000õ õ                      õ000000000000000õ000000000000000õ õ000000000050822õ01õ0003õ00000000000000000õ                              õ0072õ0000õ001000011622õ00031õ00000õ2016-12-26õAõ01800000õARSõPESO ARGENTINO      õ000000000078900õ00035õVENCIMIENTO    õ02000000õNõNõEõ04õNõ2017-01-30õ000000000000000õ000000000001513õNõ000000000000000õ000000000000000õ000000000078900õ2017-01-30õARSõ000000000078900õTRADICIONAL                   õ   õ0072õ0000õ007000638801õ0072õ0000õ007000638801õ00000000õ00000000õ0000õVõNõ                      õ000000000000000õ000000000000000õ õ                      õ000000000000000õ000000000000000õ õ                      õ000000000000000õ000000000000000õ õ                      õ000000000000000õ000000000000000õ õ                      õ000000000000000õ000000000000000õ õ000000000080413õ01õ0003õ00000000000000000õ";

		when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
		ConsultaGralPlazoFijoInEntity inEntity = new ConsultaGralPlazoFijoInEntity();

		inEntity.setSucursalCuenta("    ");
		inEntity.setCuentaPlazo("                    ");
		inEntity.setEjecutivo("    ");
		inEntity.setIndicadorEstadoPF("A");
		inEntity.setCustodia(" ");
		inEntity.setTipoFecha(" ");
		inEntity.setFechaDesde("");
		inEntity.setFechaHasta("");
		inEntity.setFechaContable("");
		inEntity.setIndicadorEstado2("X");
		inEntity.setCantidadRegistrosAConsultar("010");
		inEntity.setCuentaRellamada("                    0000000000");
		inEntity.setNroSecuenciaRellamada("000000000");
		inEntity.setNroSecuencia("00000");

		ConsultaGralPlazoFijoOutEntity respuesta = plazoFijoDAO.consultarTenencia(inEntity, cliente);
		Assert.assertNotNull(respuesta);
		Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));

	}

	/**
	 * Consultar Tenencias TimeOutException
	 * 
	 * @throws DAOException
	 * @throws IatxException
	 */
	@Ignore
	@Test
	public void consultarTenenciaTimeOutException() throws DAOException, IatxException {
		String servicio = "CNSPFGRAL_";
		String version = "130";
		when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version))))
				.thenThrow(new IatxException(TIMEOUT_EXCEPTION));
		ConsultaGralPlazoFijoInEntity inEntity = new ConsultaGralPlazoFijoInEntity();

		inEntity.setSucursalCuenta("    ");
		inEntity.setCuentaPlazo("                    ");
		inEntity.setEjecutivo("    ");
		inEntity.setIndicadorEstadoPF("A");
		inEntity.setCustodia(" ");
		inEntity.setTipoFecha(" ");
		inEntity.setFechaDesde("");
		inEntity.setFechaHasta("");
		inEntity.setFechaContable("");
		inEntity.setIndicadorEstado2("X");
		inEntity.setCantidadRegistrosAConsultar("010");
		inEntity.setCuentaRellamada("                    0000000000");
		inEntity.setNroSecuenciaRellamada("000000000");
		inEntity.setNroSecuencia("00000");

		try {
			ConsultaGralPlazoFijoOutEntity respuesta = plazoFijoDAO.consultarTenencia(inEntity, cliente);
		} catch (Exception e) {
			Assert.assertEquals(TimeOutException.class, e.getClass());
		}
	}

	/**
	 * Consultar Tenencias DAOException
	 * 
	 * @throws DAOException
	 * @throws IatxException
	 */
	@Ignore
	@Test
	public void consultarTenenciaDAOException() throws DAOException, IatxException {
		String servicio = "CNSPFGRAL_";
		String version = "130";
		when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version))))
				.thenThrow(new IatxException("Error no Timeout"));
		ConsultaGralPlazoFijoInEntity inEntity = new ConsultaGralPlazoFijoInEntity();

		inEntity.setSucursalCuenta("    ");
		inEntity.setCuentaPlazo("                    ");
		inEntity.setEjecutivo("    ");
		inEntity.setIndicadorEstadoPF("A");
		inEntity.setCustodia(" ");
		inEntity.setTipoFecha(" ");
		inEntity.setFechaDesde("");
		inEntity.setFechaHasta("");
		inEntity.setFechaContable("");
		inEntity.setIndicadorEstado2("X");
		inEntity.setCantidadRegistrosAConsultar("010");
		inEntity.setCuentaRellamada("                    0000000000");
		inEntity.setNroSecuenciaRellamada("000000000");
		inEntity.setNroSecuencia("00000");

		try {
			ConsultaGralPlazoFijoOutEntity respuesta = plazoFijoDAO.consultarTenencia(inEntity, cliente);
		} catch (Exception e) {
			Assert.assertEquals(DAOException.class, e.getClass());
		}
	}

	/**
	 * Consultar Tasas Test
	 * 
	 * @throws IatxException
	 * @throws DAOException
	 */
	@Ignore
	@Test
	public void consultarTasasTest() throws IatxException, DAOException {
		String servicio = "CNSTASPFCN";
		String version = "120";
		String tramaResponse = "200000000000P04HTML0009900010300CEAA00  ********00938683000000102017011815542200000000IBF33361000000000000CNSTASPFCN1200000            00240000    00        00 015577835201701181554170000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0529100000000õ058õARSõ00045õ99999999999999õ01740000õ01940000õ03õTRADICIONAL         õ00000000õ00000000050000õARSõ00060õ99999999999999õ01750000õ01950000õ03õTRADICIONAL         õ00000000õ00000000050000õARSõ00090õ99999999999999õ01760000õ01960000õ03õTRADICIONAL         õ00000000õ00000000050000õARSõ00120õ99999999999999õ01780000õ01980000õ03õTRADICIONAL         õ00000000õ00000000050000õARSõ00150õ99999999999999õ01800000õ02000000õ03õTRADICIONAL         õ00000000õ00000000050000õARSõ00180õ99999999999999õ01825000õ02025000õ03õTRADICIONAL         õ00000000õ00000000050000õARSõ00240õ99999999999999õ01840000õ02040000õ03õTRADICIONAL         õ00000000õ00000000050000õARSõ00300õ99999999999999õ01840000õ02040000õ03õTRADICIONAL         õ00000000õ00000000050000õARSõ00365õ99999999999999õ01840000õ02040000õ03õTRADICIONAL         õ00000000õ00000000050000õARSõ00548õ99999999999999õ01840000õ02040000õ03õTRADICIONAL         õ00000000õ00000000050000õARSõ00730õ99999999999999õ01840000õ02040000õ03õTRADICIONAL         õ00000000õ00000000050000õARSõ00913õ99999999999999õ01840000õ02040000õ03õTRADICIONAL         õ00000000õ00000000050000õUSDõ00030õ99999999999999õ00245000õ00245000õ03õTRADICIONAL         õ00000000õ00000000300000õUSDõ00045õ99999999999999õ00020000õ00020000õ03õTRADICIONAL         õ00000000õ00000000300000õUSDõ00060õ99999999999999õ00020000õ00020000õ03õTRADICIONAL         õ00000000õ00000000200000õUSDõ00090õ99999999999999õ00310000õ00310000õ03õTRADICIONAL         õ00000000õ00000000100000õUSDõ00106õ99999999999999õ00020000õ00020000õ03õTRADICIONAL         õ00000000õ00000000100000õUSDõ00120õ99999999999999õ00020000õ00020000õ03õTRADICIONAL         õ00000000õ00000000100000õUSDõ00150õ99999999999999õ00020000õ00020000õ03õTRADICIONAL         õ00000000õ00000000100000õUSDõ00180õ99999999999999õ00320000õ00320000õ03õTRADICIONAL         õ00000000õ00000000100000õUSDõ00196õ99999999999999õ00040000õ00040000õ03õTRADICIONAL         õ00000000õ00000000100000õUSDõ00240õ99999999999999õ00040000õ00040000õ03õTRADICIONAL         õ00000000õ00000000100000õUSDõ00300õ99999999999999õ00040000õ00040000õ03õTRADICIONAL         õ00000000õ00000000100000õUSDõ00365õ99999999999999õ00340000õ00340000õ03õTRADICIONAL         õ00000000õ00000000050000õUSDõ00381õ99999999999999õ00040000õ00040000õ03õTRADICIONAL         õ00000000õ00000000050000õUSDõ00548õ99999999999999õ00040000õ00040000õ03õTRADICIONAL         õ00000000õ00000000050000õARSõ00180õ99999999999999õ02025000õ02025000õ16õPRECANCELABLE       õ00080000õ00000000500000õARSõ00300õ99999999999999õ01000000õ01000000õ16õPRECANCELABLE       õ00050000õ00000000500000õARSõ00365õ99999999999999õ02075000õ02075000õ16õPRECANCELABLE       õ00200000õ00000000500000õARSõ00548õ99999999999999õ02075000õ02075000õ16õPRECANCELABLE       õ00325000õ00000000500000õARSõ00180õ99999999999999õ02290000õ02290000õ17õINTERESANTE         õ00000000õ00000000500000õARSõ00300õ99999999999999õ02265000õ02265000õ17õINTERESANTE         õ00000000õ00000000500000õARSõ00365õ99999999999999õ02250000õ02250000õ17õINTERESANTE         õ00000000õ00000000500000õARSõ00548õ99999999999999õ02140000õ02140000õ17õINTERESANTE         õ00000000õ00000000500000õARSõ00730õ99999999999999õ02040000õ02040000õ17õINTERESANTE         õ00000000õ00000000500000õARSõ00913õ99999999999999õ02040000õ02040000õ17õINTERESANTE         õ00000000õ00000000500000õUSDõ00180õ99999999999999õ00060000õ00060000õ17õINTERESANTE         õ00000000õ00000000300000õUSDõ00300õ99999999999999õ00125000õ00125000õ17õINTERESANTE         õ00000000õ00000000300000õUSDõ00365õ99999999999999õ00150000õ00150000õ17õINTERESANTE         õ00000000õ00000000300000õUSDõ00548õ99999999999999õ00125000õ00125000õ17õINTERESANTE         õ00000000õ00000000300000õUSDõ00730õ99999999999999õ00125000õ00125000õ17õINTERESANTE         õ00000000õ00000000300000õUSDõ00913õ99999999999999õ00125000õ00125000õ17õINTERESANTE         õ00000000õ00000000300000õARSõ00365õ99999999999999õ00010000õ00010000õ30õAJUSTABLE           õ00000000õ00000000500000õARSõ00548õ99999999999999õ00010000õ00010000õ30õAJUSTABLE           õ00000000õ00000000500000õARSõ00730õ99999999999999õ00010000õ00010000õ30õAJUSTABLE           õ00000000õ00000000500000õARSõ00913õ99999999999999õ00010000õ00010000õ30õAJUSTABLE           õ00000000õ00000000500000õARSõ00365õ99999999999999õ00010000õ00025000õ32õINTERESANTE AJUSTABLõ00000000õ00000000500000õARSõ00548õ99999999999999õ00020000õ00050000õ32õINTERESANTE AJUSTABLõ00000000õ00000000500000õARSõ00730õ99999999999999õ00025000õ00050000õ32õINTERESANTE AJUSTABLõ00000000õ00000000500000õARSõ00913õ99999999999999õ00025000õ00050000õ32õINTERESANTE AJUSTABLõ00000000õ00000000500000õARSõ00180õ99999999999999õ00000000õ00000000õ60õUVA                 õ00000000õ00000000050000õUSDõ00030õ99999999999999õ00245000õ00245000õ92õDOLAR AHORRO        õ00000000õ00000000050000õUSDõ00045õ99999999999999õ00020000õ00020000õ92õDOLAR AHORRO        õ00000000õ00000000050000õUSDõ00090õ99999999999999õ00310000õ00310000õ92õDOLAR AHORRO        õ00000000õ00000000050000õUSDõ00106õ99999999999999õ00020000õ00020000õ92õDOLAR AHORRO        õ00000000õ00000000050000õUSDõ00180õ99999999999999õ00320000õ00320000õ92õDOLAR AHORRO        õ00000000õ00000000050000õUSDõ00196õ99999999999999õ00040000õ00040000õ92õDOLAR AHORRO        õ00000000õ00000000050000õUSDõ00365õ99999999999999õ00340000õ00340000õ92õDOLAR AHORRO        õ00000000õ00000000050000õ";

		when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
		ConsultaTasasPlazoFijoBPrivOutEntity respuesta = plazoFijoDAO.consultarTasas(cliente, BANCA_PERSONAL, false);
		Assert.assertNotNull(respuesta);
		Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
	}

	/**
	 * Consultar Tasas TimeOutException
	 * 
	 * @throws DAOException
	 * @throws IatxException
	 */
	@Ignore
	@Test
	public void consultarTasasTimeOutException() throws DAOException, IatxException {
		String servicio = "CNSTASPFCN";
		String version = "120";
		when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version))))
				.thenThrow(new IatxException(TIMEOUT_EXCEPTION));
		try {
			ConsultaTasasPlazoFijoBPrivOutEntity respuesta = plazoFijoDAO.consultarTasas(cliente, BANCA_PERSONAL,
					false);
		} catch (Exception e) {
			Assert.assertEquals(TimeOutException.class, e.getClass());
		}
	}

	/**
	 * Consultar tasas DAOException
	 * 
	 * @throws DAOException
	 * @throws IatxException
	 */
	@Ignore
	@Test
	public void consultarTasasDAOException() throws DAOException, IatxException {
		String servicio = "CNSTASPFCN";
		String version = "120";
		when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version))))
				.thenThrow(new IatxException("Error no Timeout"));
		try {
			ConsultaTasasPlazoFijoBPrivOutEntity respuesta = plazoFijoDAO.consultarTasas(cliente, BANCA_PERSONAL,
					false);
		} catch (Exception e) {
			Assert.assertEquals(DAOException.class, e.getClass());
		}
	}

	/**
	 * Consultar Interesante test
	 * 
	 * @throws IatxException
	 * @throws DAOException
	 */
	@Ignore
	@Test
	public void consultarInteresanteTest() throws IatxException, DAOException {
		String servicio = "CNSPFINTE_";
		String version = "100";
		String tramaResponse = "200000000000P04HTML0009900010301PRQP31  ********00898489000000082017011916031500000000IBF20217000000000000CNSPFINTE_1000000            01576531    00        00 016020196201701191603100000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0060800000000õ000000000000000õ+õ        õ        õ00000õNõ                        0    0           0                                    0                     0              0          õ05õ14122016õ13012017õ000000000000000õ000000000007788õ000000000000000+õ00000000000007788õ13012017õ13022017õ000000000000000õ000000000008047õ000000000000000+õ00000000000008047õ13022017õ15032017õ000000000000000õ000000000007788õ000000000000000+õ00000000000007788õ15032017õ14042017õ000000000000000õ000000000007788õ000000000000000+õ00000000000007788õ14042017õ12062017õ000000000500000õ000000000015316õ000000000000000+õ00000000000515316õ";

		when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
		ConsultaInteresantePlazoFijoInEntity inEntity = new ConsultaInteresantePlazoFijoInEntity();

		inEntity.setCuentaPlazo("00720000001000011622");
		inEntity.setSecuencia("00028");
		inEntity.setVencImpuestoRell("               ");
		inEntity.setSignoVencImpuestoRell(" ");
		inEntity.setFechaUltLiquidacionRell("        ");
		inEntity.setFechaProxLiquidacionRell("        ");
		inEntity.setPlazoRell("     ");
		inEntity.setIndicadorMasDatosRell(" ");
		inEntity.setDatosRell(
				"                                                                                                                  ");

		ConsultaInteresantePlazoFijoOutEntity respuesta = plazoFijoDAO.consultarInteresante(inEntity, cliente);
		Assert.assertNotNull(respuesta);
		Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
	}

	/**
	 * Consultar interesante TimeOutException
	 * 
	 * @throws DAOException
	 * @throws IatxException
	 */
	@Ignore
	@Test
	public void consultarInteresanteTimeOutException() throws DAOException, IatxException {
		String servicio = "CNSPFINTE_";
		String version = "100";
		when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version))))
				.thenThrow(new IatxException(TIMEOUT_EXCEPTION));

		ConsultaInteresantePlazoFijoInEntity inEntity = new ConsultaInteresantePlazoFijoInEntity();

		inEntity.setCuentaPlazo("00720000001000011622");
		inEntity.setSecuencia("00028");
		inEntity.setVencImpuestoRell("               ");
		inEntity.setSignoVencImpuestoRell(" ");
		inEntity.setFechaUltLiquidacionRell("        ");
		inEntity.setFechaProxLiquidacionRell("        ");
		inEntity.setPlazoRell("     ");
		inEntity.setIndicadorMasDatosRell(" ");
		inEntity.setDatosRell(
				"                                                                                                                  ");

		try {
			ConsultaInteresantePlazoFijoOutEntity respuesta = plazoFijoDAO.consultarInteresante(inEntity, cliente);
		} catch (Exception e) {
			Assert.assertEquals(TimeOutException.class, e.getClass());
		}
	}

	/**
	 * Consultar Interesante DAOException
	 * 
	 * @throws DAOException
	 * @throws IatxException
	 */
	@Ignore
	@Test
	public void consultarInteresanteDAOException() throws DAOException, IatxException {
		String servicio = "CNSPFINTE_";
		String version = "100";
		when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version))))
				.thenThrow(new IatxException("Error no Timeout"));

		ConsultaInteresantePlazoFijoInEntity inEntity = new ConsultaInteresantePlazoFijoInEntity();

		inEntity.setCuentaPlazo("00720000001000011622");
		inEntity.setSecuencia("00028");
		inEntity.setVencImpuestoRell("               ");
		inEntity.setSignoVencImpuestoRell(" ");
		inEntity.setFechaUltLiquidacionRell("        ");
		inEntity.setFechaProxLiquidacionRell("        ");
		inEntity.setPlazoRell("     ");
		inEntity.setIndicadorMasDatosRell(" ");
		inEntity.setDatosRell(
				"                                                                                                                  ");

		try {
			ConsultaInteresantePlazoFijoOutEntity respuesta = plazoFijoDAO.consultarInteresante(inEntity, cliente);
		} catch (Exception e) {
			Assert.assertEquals(DAOException.class, e.getClass());
		}
	}

	/**
	 * Confirmar Accion vencimiento test
	 * 
	 * @throws IatxException
	 * @throws DAOException
	 */
	@Ignore
	@Test
	public void confirmarAccionVencimientoTest() throws IatxException, DAOException {
		String servicio = "CMBPFCNLS_";
		String version = "100";
		String tramaResponse = "200000000000P04HTML0009900010300CEAA00  ********00938681000000092017011811074800000000IBF34427000011029623CMBPFCNLS_1000000            00240000    00        00 011029623201701181107430000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0001100000000õNõ";

		when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
		MantenimientoPlazoFijoInEntity inEntity = new MantenimientoPlazoFijoInEntity();

		inEntity.setCertificado("                              ");
		inEntity.setSucursal("0041");
		inEntity.setCuenta("001200001784");
		inEntity.setSecuencia("2");
		inEntity.setMoneda("ARS");
		inEntity.setRenovacion("S");
		inEntity.setTasa("00000000");
		inEntity.setCircular("00");
		inEntity.setFormaPago(" ");
		inEntity.setSecuenciaCan("00002");
		inEntity.setCentroGestor("    ");
		inEntity.setBloqueoCta(" ");
		inEntity.setNssf("0000");
		inEntity.setTarifa("    ");
		inEntity.setIndicadorTransferible(" ");
		inEntity.setCuentaCredito("                    ");
		inEntity.setIndicadorCapitalizaIntereses("S");

		MantenimientoPlazoFijoOutEntity respuesta = plazoFijoDAO.confirmarAccionVencimiento(inEntity, cliente);
		Assert.assertNotNull(respuesta);
		Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));

	}

	/**
	 * Confirmar accion vencimiento TimeOutException
	 * 
	 * @throws DAOException
	 * @throws IatxException
	 */
	@Ignore
	@Test
	public void confirmarAccionVencimientoTimeOutException() throws DAOException, IatxException {
		String servicio = "CMBPFCNLS_";
		String version = "100";
		when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version))))
				.thenThrow(new IatxException(TIMEOUT_EXCEPTION));
		MantenimientoPlazoFijoInEntity inEntity = new MantenimientoPlazoFijoInEntity();

		inEntity.setCertificado("                              ");
		inEntity.setSucursal("0041");
		inEntity.setCuenta("001200001784");
		inEntity.setSecuencia("2");
		inEntity.setMoneda("ARS");
		inEntity.setRenovacion("S");
		inEntity.setTasa("00000000");
		inEntity.setCircular("00");
		inEntity.setFormaPago(" ");
		inEntity.setSecuenciaCan("00002");
		inEntity.setCentroGestor("    ");
		inEntity.setBloqueoCta(" ");
		inEntity.setNssf("0000");
		inEntity.setTarifa("    ");
		inEntity.setIndicadorTransferible(" ");
		inEntity.setCuentaCredito("                    ");
		inEntity.setIndicadorCapitalizaIntereses("S");

		try {
			MantenimientoPlazoFijoOutEntity respuesta = plazoFijoDAO.confirmarAccionVencimiento(inEntity, cliente);
		} catch (Exception e) {
			Assert.assertEquals(TimeOutException.class, e.getClass());
		}
	}

	/**
	 * Confirmar accion vencimiento DAOException
	 * 
	 * @throws DAOException
	 * @throws IatxException
	 */
	@Ignore
	@Test
	public void confirmarAccionVencimientoDAOException() throws DAOException, IatxException {
		String servicio = "CMBPFCNLS_";
		String version = "100";
		when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version))))
				.thenThrow(new IatxException("Error no Timeout"));

		MantenimientoPlazoFijoInEntity inEntity = new MantenimientoPlazoFijoInEntity();

		inEntity.setCertificado("                              ");
		inEntity.setSucursal("0041");
		inEntity.setCuenta("001200001784");
		inEntity.setSecuencia("2");
		inEntity.setMoneda("ARS");
		inEntity.setRenovacion("S");
		inEntity.setTasa("00000000");
		inEntity.setCircular("00");
		inEntity.setFormaPago(" ");
		inEntity.setSecuenciaCan("00002");
		inEntity.setCentroGestor("    ");
		inEntity.setBloqueoCta(" ");
		inEntity.setNssf("0000");
		inEntity.setTarifa("    ");
		inEntity.setIndicadorTransferible(" ");
		inEntity.setCuentaCredito("                    ");
		inEntity.setIndicadorCapitalizaIntereses("S");

		try {
			MantenimientoPlazoFijoOutEntity respuesta = plazoFijoDAO.confirmarAccionVencimiento(inEntity, cliente);
		} catch (Exception e) {
			Assert.assertEquals(DAOException.class, e.getClass());
		}
	}

	/**
	 * Confirmar constitucion test
	 * 
	 * @throws IatxException
	 * @throws DAOException
	 * @throws BusinessException
	 */
	@Ignore
	@Test
	public void confirmarConstitucionTest() throws IatxException, DAOException, BusinessException {
		String servicio = "ALTPFCNLS_";
		String version = "150";
		String tramaResponse = "200000000000P04HTML0009900010300OMNR62  ********00556634000000172017040412152200000000IBF24622000012118907ALTPFCNLS_1500000            00423762    00        00 012118907201704041215150000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0090400000000õ0072õ0179õ001000149299õ000000002õ2017-10-02õFõ00000001500000õ00000000140957õ00181õ00000001505921õ00000000005921õ00000000000000õ2017-04-04õBPX8IX2017-04-0412151575õNõ00000õ          õ00000000õ000000000õ000000000õ00000000õ+õUVAõ00000000000224700õ0002225000000õ003õIMP. SELLOS SAN JUANõ18õ00000000004229õ00000000000000õAõImp.Lote Hogar S.Juaõ18õ00000000000846õ00000000000000õAõImp. Acc.Soc. S.Juanõ18õ00000000000846õ00000000000000õAõ                    õ  õ00000000000000õ00000000000000õ õ                    õ  õ00000000000000õ00000000000000õ õ005õ2017-05-04õ000000000000000õ000000000023363õ000000000000000õ00000000000023363õ2017-06-05õ000000000000000õ000000000024921õ000000000000000õ00000000000024921õ2017-07-05õ000000000000000õ000000000023363õ000000000000000õ00000000000023363õ2017-08-04õ000000000000000õ000000000023363õ000000000000000õ00000000000023363õ2017-10-02õ000000001500000õ000000000045947õ000000000000000õ00000000001545947õ";

		when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
		ImposicionPlazoFijoInEntity inEntity = new ImposicionPlazoFijoInEntity();

		inEntity.setTipoCuenta("09");
		inEntity.setSucursalCuentaDebito("179");
		inEntity.setNroCuentaDebito("0600514");
		inEntity.setProducto("01");
		inEntity.setSubproducto("0017");
		inEntity.setEntidadCuentaPlazo("    ");
		inEntity.setSucursalCuentaPlazo("0000");
		inEntity.setNroCuentaPlazo("000000000000");
		inEntity.setFechaAlta("2017-04-04");
		inEntity.setPlazo("00180");
		inEntity.setImporteCertificado("000000001500000");
		inEntity.setDivisa("ARS");
		inEntity.setTasa("01895000");
		inEntity.setCodigoTarifa("INTE");
		inEntity.setCodigoTarifaRenovacion("    ");
		inEntity.setIndicadorRenovacion("N");
		inEntity.setIndicadorCapitalizaIntereses("N");
		inEntity.setPeriodoLiquidacion("F");
		inEntity.setSpreadRenovacion("00000000");
		inEntity.setIndicadorCertificadoTransferencia("N");
		inEntity.setSucursalCuentaDebito("0179");
		inEntity.setResponsableImpuesto("B");
		inEntity.setFormaPago("C");
		inEntity.setSecuencia("000000000");
		inEntity.setFormaCancel(" ");
		inEntity.setImporteDebitoReversa("000000000000000");
		inEntity.setTipoPF("17");
		inEntity.setNio("                        ");
		inEntity.setCantidadDias("00030");
		inEntity.setTasaVariable("00000000");
		inEntity.setSpread("00000000");
		inEntity.setSignoSpread(" ");
		inEntity.setTipoDiaLiqVariable("   ");
		inEntity.setNumDiaLiqVariable("00");
		inEntity.setSaldoInicUr("00000000000224700");
		inEntity.setCotizacionCodigoUr("0002225000000");

		ImposicionPlazoFijoOutEntity respuesta = plazoFijoDAO.confirmarConstitucion(inEntity, cliente, BANCA_RETAIL,
				false);
		Assert.assertNotNull(respuesta);
		Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));

	}

	/**
	 * Confirmar constitucion TimeOutException
	 * 
	 * @throws DAOException
	 * @throws IatxException
	 */
	@Ignore
	@Test
	public void confirmarConstitucionTimeOutException() throws DAOException, IatxException {
		String servicio = "ALTPFCNLS_";
		String version = "150";
		when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version))))
				.thenThrow(new IatxException(TIMEOUT_EXCEPTION));

		ImposicionPlazoFijoInEntity inEntity = new ImposicionPlazoFijoInEntity();

		inEntity.setTipoCuenta("09");
		inEntity.setSucursalCuentaDebito("179");
		inEntity.setNroCuentaDebito("0600514");
		inEntity.setProducto("01");
		inEntity.setSubproducto("0017");
		inEntity.setEntidadCuentaPlazo("    ");
		inEntity.setSucursalCuentaPlazo("0000");
		inEntity.setNroCuentaPlazo("000000000000");
		inEntity.setFechaAlta("2017-04-04");
		inEntity.setPlazo("00180");
		inEntity.setImporteCertificado("000000001500000");
		inEntity.setDivisa("ARS");
		inEntity.setTasa("01895000");
		inEntity.setCodigoTarifa("INTE");
		inEntity.setCodigoTarifaRenovacion("    ");
		inEntity.setIndicadorRenovacion("N");
		inEntity.setIndicadorCapitalizaIntereses("N");
		inEntity.setPeriodoLiquidacion("F");
		inEntity.setSpreadRenovacion("00000000");
		inEntity.setIndicadorCertificadoTransferencia("N");
		inEntity.setSucursalCuentaDebito("0179");
		inEntity.setResponsableImpuesto("B");
		inEntity.setFormaPago("C");
		inEntity.setSecuencia("000000000");
		inEntity.setFormaCancel(" ");
		inEntity.setImporteDebitoReversa("000000000000000");
		inEntity.setTipoPF("17");
		inEntity.setNio("                        ");
		inEntity.setCantidadDias("00030");
		inEntity.setTasaVariable("00000000");
		inEntity.setSpread("00000000");
		inEntity.setSignoSpread(" ");
		inEntity.setTipoDiaLiqVariable("   ");
		inEntity.setNumDiaLiqVariable("00");
		inEntity.setSaldoInicUr("00000000000224700");
		inEntity.setCotizacionCodigoUr("0002225000000");
		try {
			ImposicionPlazoFijoOutEntity respuesta = plazoFijoDAO.confirmarConstitucion(inEntity, cliente, BANCA_RETAIL,
					false);
		} catch (Exception e) {
			Assert.assertEquals(TimeOutException.class, e.getClass());
		}
	}

	/**
	 * Confirmar Constitucion DAOException
	 * 
	 * @throws DAOException
	 * @throws IatxException
	 */
	@Ignore
	@Test
	public void confirmarConstitucionDAOException() throws DAOException, IatxException {
		String servicio = "ALTPFCNLS_";
		String version = "150";
		when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version))))
				.thenThrow(new IatxException("Error no Timeout"));

		ImposicionPlazoFijoInEntity inEntity = new ImposicionPlazoFijoInEntity();

		inEntity.setTipoCuenta("09");
		inEntity.setSucursalCuentaDebito("179");
		inEntity.setNroCuentaDebito("0600514");
		inEntity.setProducto("01");
		inEntity.setSubproducto("0017");
		inEntity.setEntidadCuentaPlazo("    ");
		inEntity.setSucursalCuentaPlazo("0000");
		inEntity.setNroCuentaPlazo("000000000000");
		inEntity.setFechaAlta("2017-04-04");
		inEntity.setPlazo("00180");
		inEntity.setImporteCertificado("000000001500000");
		inEntity.setDivisa("ARS");
		inEntity.setTasa("01895000");
		inEntity.setCodigoTarifa("INTE");
		inEntity.setCodigoTarifaRenovacion("    ");
		inEntity.setIndicadorRenovacion("N");
		inEntity.setIndicadorCapitalizaIntereses("N");
		inEntity.setPeriodoLiquidacion("F");
		inEntity.setSpreadRenovacion("00000000");
		inEntity.setIndicadorCertificadoTransferencia("N");
		inEntity.setSucursalCuentaDebito("0179");
		inEntity.setResponsableImpuesto("B");
		inEntity.setFormaPago("C");
		inEntity.setSecuencia("000000000");
		inEntity.setFormaCancel(" ");
		inEntity.setImporteDebitoReversa("000000000000000");
		inEntity.setTipoPF("17");
		inEntity.setNio("                        ");
		inEntity.setCantidadDias("00030");
		inEntity.setTasaVariable("00000000");
		inEntity.setSpread("00000000");
		inEntity.setSignoSpread(" ");
		inEntity.setTipoDiaLiqVariable("   ");
		inEntity.setNumDiaLiqVariable("00");
		inEntity.setSaldoInicUr("00000000000224700");
		inEntity.setCotizacionCodigoUr("0002225000000");

		try {
			ImposicionPlazoFijoOutEntity respuesta = plazoFijoDAO.confirmarConstitucion(inEntity, cliente, BANCA_RETAIL,
					false);
		} catch (Exception e) {
			Assert.assertEquals(DAOException.class, e.getClass());
		}
	}

	/**
	 * Consultar condiciones test
	 * 
	 * @throws IatxException
	 * @throws DAOException
	 */
	@Ignore
	@Test
	public void consultarCondicionesTest() throws IatxException, DAOException {
		String servicio = "CNSSIMPFCN";
		String version = "160";
		String tramaResponse = "200000000000P04HTML0009900010300OMNR62  ********00556634000000162017040412150600000000IBF24599000000000000CNSSIMPFCN1600000            00423762    00        00 012118899201704041214590000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0093700000000õ01õPLAZO FIJO          õ0017õPLAZO FIJO INTERESANõINTEõ00181õ2017-10-02õFõ000000001500000õ01895000õ000000000140957õ000000001505921õ000000000005921õ000000000000000õ00000000õ2017-04-04õ17õNõ00000õ          õ00000000õ000000000õ000000000õ00000000õ+õUVAõ00000000000224700õ0002225000000õ003õIMP. SELLOS SAN JUANõ18õ000000000004229õ000000000000000õAõImp.Lote Hogar S.Juaõ18õ000000000000846õ000000000000000õAõImp. Acc.Soc. S.Juanõ18õ000000000000846õ000000000000000õAõ                    õ  õ               õ               õ õ                    õ  õ               õ               õ õ005õ2017-05-04õ000000000000000õ000000000023363õ000000000000000õ00000000000023363õ2017-06-05õ000000000000000õ000000000024921õ000000000000000õ00000000000024921õ2017-07-05õ000000000000000õ000000000023363õ000000000000000õ00000000000023363õ2017-08-04õ000000000000000õ000000000023363õ000000000000000õ00000000000023363õ2017-10-02õ000000001500000õ000000000045947õ000000000000000õ00000000001545947õ";

		when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
		SimulacionPlazoFijoInEntity inEntity = new SimulacionPlazoFijoInEntity();

		inEntity.setTipoCuentaDebito("09");
		inEntity.setSucursalCuentaDebito("179");
		inEntity.setNumeroCuentaDebito("0600514");
		inEntity.setProducto("  ");
		inEntity.setSubproducto("    ");
		inEntity.setEntidadCuentaPlazo("    ");
		inEntity.setSucursalCuentaPlazo("0000");
		inEntity.setCuentaPlazo("000000000000");
		inEntity.setFechaAlta("20170404");
		inEntity.setPlazo("00180");
		inEntity.setImportePlazoFijo("000000001500000");
		inEntity.setDivisa("ARS");
		inEntity.setTasa("000000000");
		inEntity.setTarifa("    ");
		inEntity.setPeriodoLiquidacion(" ");
		inEntity.setIndicadorTransferible("N");
		inEntity.setSucursalRadicacion("0179");
		inEntity.setRespImpuesto("B");
		inEntity.setTipoPF("17");
		inEntity.setCantidadDias("00030");
		inEntity.setTasaVariable("000000000");
		inEntity.setSpread("000000000");
		inEntity.setSpreadSigno("+");
		inEntity.setTipoDiaLiqVariable("IF ");
		inEntity.setNumDiaLiqVariable("05");
		inEntity.setCanal("04");
		inEntity.setSimulacionOriginal("S");

		SimulacionPlazoFijoOutEntity respuesta = plazoFijoDAO.simularPlazoFijo(inEntity, cliente, false);
		Assert.assertNotNull(respuesta);
		Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
	}

	/**
	 * Consultar condiciones TimeOutException
	 * 
	 * @throws DAOException
	 * @throws IatxException
	 */
	@Ignore
	@Test
	public void consultarCondicionesTimeOutException() throws DAOException, IatxException {
		String servicio = "CNSSIMPFCN";
		String version = "160";
		when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version))))
				.thenThrow(new IatxException(TIMEOUT_EXCEPTION));

		SimulacionPlazoFijoInEntity inEntity = new SimulacionPlazoFijoInEntity();

		inEntity.setTipoCuentaDebito("09");
		inEntity.setSucursalCuentaDebito("179");
		inEntity.setNumeroCuentaDebito("0600514");
		inEntity.setProducto("  ");
		inEntity.setSubproducto("    ");
		inEntity.setEntidadCuentaPlazo("    ");
		inEntity.setSucursalCuentaPlazo("0000");
		inEntity.setCuentaPlazo("000000000000");
		inEntity.setFechaAlta("20170404");
		inEntity.setPlazo("00180");
		inEntity.setImportePlazoFijo("000000001500000");
		inEntity.setDivisa("ARS");
		inEntity.setTasa("000000000");
		inEntity.setTarifa("    ");
		inEntity.setPeriodoLiquidacion(" ");
		inEntity.setIndicadorTransferible("N");
		inEntity.setSucursalRadicacion("0179");
		inEntity.setRespImpuesto("B");
		inEntity.setTipoPF("17");
		inEntity.setCantidadDias("00030");
		inEntity.setTasaVariable("000000000");
		inEntity.setSpread("000000000");
		inEntity.setSpreadSigno("+");
		inEntity.setTipoDiaLiqVariable("IF ");
		inEntity.setNumDiaLiqVariable("05");
		inEntity.setCanal("04");
		inEntity.setSimulacionOriginal("S");

		try {
			SimulacionPlazoFijoOutEntity respuesta = plazoFijoDAO.simularPlazoFijo(inEntity, cliente, false);
		} catch (Exception e) {
			Assert.assertEquals(TimeOutException.class, e.getClass());
		}
	}

	/**
	 * Consultar condiciones DAOException
	 * 
	 * @throws DAOException
	 * @throws IatxException
	 */
	@Ignore
	@Test
	public void consultarCondicionesDAOException() throws DAOException, IatxException {
		String servicio = "CNSSIMPFCN";
		String version = "160";
		when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version))))
				.thenThrow(new IatxException("Error no Timeout"));

		SimulacionPlazoFijoInEntity inEntity = new SimulacionPlazoFijoInEntity();

		inEntity.setTipoCuentaDebito("09");
		inEntity.setSucursalCuentaDebito("179");
		inEntity.setNumeroCuentaDebito("0600514");
		inEntity.setProducto("  ");
		inEntity.setSubproducto("    ");
		inEntity.setEntidadCuentaPlazo("    ");
		inEntity.setSucursalCuentaPlazo("0000");
		inEntity.setCuentaPlazo("000000000000");
		inEntity.setFechaAlta("20170404");
		inEntity.setPlazo("00180");
		inEntity.setImportePlazoFijo("000000001500000");
		inEntity.setDivisa("ARS");
		inEntity.setTasa("000000000");
		inEntity.setTarifa("    ");
		inEntity.setPeriodoLiquidacion(" ");
		inEntity.setIndicadorTransferible("N");
		inEntity.setSucursalRadicacion("0179");
		inEntity.setRespImpuesto("B");
		inEntity.setTipoPF("17");
		inEntity.setCantidadDias("00030");
		inEntity.setTasaVariable("000000000");
		inEntity.setSpread("000000000");
		inEntity.setSpreadSigno("+");
		inEntity.setTipoDiaLiqVariable("IF ");
		inEntity.setNumDiaLiqVariable("05");
		inEntity.setCanal("04");
		inEntity.setSimulacionOriginal("S");

		try {
			SimulacionPlazoFijoOutEntity respuesta = plazoFijoDAO.simularPlazoFijo(inEntity, cliente, false);
		} catch (Exception e) {
			Assert.assertEquals(DAOException.class, e.getClass());
		}
	}

	/**
	 * Consultar condiciones SuperaLimiteMaximoException
	 * 
	 * @throws IatxException
	 * @throws DAOException
	 */
	@Ignore
	@Test
	public void consultarCondicionesSuperaLimiteMaximo() throws IatxException, DAOException {
		String servicio = "CNSSIMPFCN";
		String version = "160";
		String tramaResponse = "200000000000P04HTML0009900010300OMNR62  ********00556634000000162017040412150600000000IBF24599000000000000CNSSIMPFCN1400000            00423762    00        00 012118899201704041214590000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0093710000004õFCIõ01õ12345678901234567890123456789012345678901234567890123456789012345678901234567890õ";
		when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
		SimulacionPlazoFijoInEntity inEntity = new SimulacionPlazoFijoInEntity();

		inEntity.setTipoCuentaDebito("09");
		inEntity.setSucursalCuentaDebito("179");
		inEntity.setNumeroCuentaDebito("0600514");
		inEntity.setProducto("  ");
		inEntity.setSubproducto("    ");
		inEntity.setEntidadCuentaPlazo("    ");
		inEntity.setSucursalCuentaPlazo("0000");
		inEntity.setCuentaPlazo("000000000000");
		inEntity.setFechaAlta("20170404");
		inEntity.setPlazo("00180");
		inEntity.setImportePlazoFijo("000000001500000");
		inEntity.setDivisa("ARS");
		inEntity.setTasa("000000000");
		inEntity.setTarifa("    ");
		inEntity.setPeriodoLiquidacion(" ");
		inEntity.setIndicadorTransferible("N");
		inEntity.setSucursalRadicacion("0179");
		inEntity.setRespImpuesto("B");
		inEntity.setTipoPF("17");
		inEntity.setCantidadDias("00030");
		inEntity.setTasaVariable("000000000");
		inEntity.setSpread("000000000");
		inEntity.setSpreadSigno("+");
		inEntity.setTipoDiaLiqVariable("IF ");
		inEntity.setNumDiaLiqVariable("05");
		inEntity.setCanal("04");
		inEntity.setSimulacionOriginal("S");

		try {
			SimulacionPlazoFijoOutEntity respuesta = plazoFijoDAO.simularPlazoFijo(inEntity, cliente, false);
		} catch (Exception e) {
			Assert.assertEquals(ImporteMayorAlMaximoException.class, e.getClass());
		}
	}

	/**
	 * Consultar condiciones SaldoCuentaInsuficienteException
	 * 
	 * @throws IatxException
	 * @throws DAOException
	 */
	@Ignore
	@Test
	public void consultarCondicionesSaldoCuentaInsuficiente() throws IatxException, DAOException {
		String servicio = "CNSSIMPFCN";
		String version = "160";
		String tramaResponse = "200000000000P04HTML0009900010300OMNR62  ********00556634000000162017040412150600000000IBF24599000000000000CNSSIMPFCN1400000            00423762    00        00 012118899201704041214590000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0093710002122õFCIõ01õ12345678901234567890123456789012345678901234567890123456789012345678901234567890õ";
		when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
		SimulacionPlazoFijoInEntity inEntity = new SimulacionPlazoFijoInEntity();

		inEntity.setTipoCuentaDebito("09");
		inEntity.setSucursalCuentaDebito("179");
		inEntity.setNumeroCuentaDebito("0600514");
		inEntity.setProducto("  ");
		inEntity.setSubproducto("    ");
		inEntity.setEntidadCuentaPlazo("    ");
		inEntity.setSucursalCuentaPlazo("0000");
		inEntity.setCuentaPlazo("000000000000");
		inEntity.setFechaAlta("20170404");
		inEntity.setPlazo("00180");
		inEntity.setImportePlazoFijo("000000001500000");
		inEntity.setDivisa("ARS");
		inEntity.setTasa("000000000");
		inEntity.setTarifa("    ");
		inEntity.setPeriodoLiquidacion(" ");
		inEntity.setIndicadorTransferible("N");
		inEntity.setSucursalRadicacion("0179");
		inEntity.setRespImpuesto("B");
		inEntity.setTipoPF("17");
		inEntity.setCantidadDias("00030");
		inEntity.setTasaVariable("000000000");
		inEntity.setSpread("000000000");
		inEntity.setSpreadSigno("+");
		inEntity.setTipoDiaLiqVariable("IF ");
		inEntity.setNumDiaLiqVariable("05");
		inEntity.setCanal("04");
		inEntity.setSimulacionOriginal("S");

		try {
			SimulacionPlazoFijoOutEntity respuesta = plazoFijoDAO.simularPlazoFijo(inEntity, cliente, false);
		} catch (Exception e) {
			Assert.assertEquals(SaldoInsuficienteException.class, e.getClass());
		}
	}

	/**
	 * Consultar condiciones SaldoCuentaInsuficienteException
	 * 
	 * @throws IatxException
	 * @throws DAOException
	 */
	@Ignore
	@Test
	public void consultarCondicionesSaldoCuentaInsuficiente2() throws IatxException, DAOException {
		String servicio = "CNSSIMPFCN";
		String version = "160";
		String tramaResponse = "200000000000P04HTML0009900010300OMNR62  ********00556634000000162017040412150600000000IBF24599000000000000CNSSIMPFCN1400000            00423762    00        00 012118899201704041214590000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0093710000515õFCIõ01õ12345678901234567890123456789012345678901234567890123456789012345678901234567890õ";
		when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
		SimulacionPlazoFijoInEntity inEntity = new SimulacionPlazoFijoInEntity();

		inEntity.setTipoCuentaDebito("09");
		inEntity.setSucursalCuentaDebito("179");
		inEntity.setNumeroCuentaDebito("0600514");
		inEntity.setProducto("  ");
		inEntity.setSubproducto("    ");
		inEntity.setEntidadCuentaPlazo("    ");
		inEntity.setSucursalCuentaPlazo("0000");
		inEntity.setCuentaPlazo("000000000000");
		inEntity.setFechaAlta("20170404");
		inEntity.setPlazo("00180");
		inEntity.setImportePlazoFijo("000000001500000");
		inEntity.setDivisa("ARS");
		inEntity.setTasa("000000000");
		inEntity.setTarifa("    ");
		inEntity.setPeriodoLiquidacion(" ");
		inEntity.setIndicadorTransferible("N");
		inEntity.setSucursalRadicacion("0179");
		inEntity.setRespImpuesto("B");
		inEntity.setTipoPF("17");
		inEntity.setCantidadDias("00030");
		inEntity.setTasaVariable("000000000");
		inEntity.setSpread("000000000");
		inEntity.setSpreadSigno("+");
		inEntity.setTipoDiaLiqVariable("IF ");
		inEntity.setNumDiaLiqVariable("05");
		inEntity.setCanal("04");
		inEntity.setSimulacionOriginal("S");

		try {
			SimulacionPlazoFijoOutEntity respuesta = plazoFijoDAO.simularPlazoFijo(inEntity, cliente, false);
		} catch (Exception e) {
			Assert.assertEquals(SaldoInsuficienteException.class, e.getClass());
		}
	}

	/**
	 * Consultar Condiciones Codigo no Esperado
	 * 
	 * @throws IatxException
	 * @throws DAOException
	 */
	@Ignore
	@Test
	public void consultarCondicionesCodigoNoEsperado() throws IatxException, DAOException {
		String servicio = "CNSSIMPFCN";
		String version = "160";
		String tramaResponse = "200000000000P04HTML0009900010300OMNR62  ********00556634000000162017040412150600000000IBF24599000000000000CNSSIMPFCN1400000            00423762    00        00 012118899201704041214590000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0093711111111õFCIõ01õ12345678901234567890123456789012345678901234567890123456789012345678901234567890õ";
		when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
		SimulacionPlazoFijoInEntity inEntity = new SimulacionPlazoFijoInEntity();

		inEntity.setTipoCuentaDebito("09");
		inEntity.setSucursalCuentaDebito("179");
		inEntity.setNumeroCuentaDebito("0600514");
		inEntity.setProducto("  ");
		inEntity.setSubproducto("    ");
		inEntity.setEntidadCuentaPlazo("    ");
		inEntity.setSucursalCuentaPlazo("0000");
		inEntity.setCuentaPlazo("000000000000");
		inEntity.setFechaAlta("20170404");
		inEntity.setPlazo("00180");
		inEntity.setImportePlazoFijo("000000001500000");
		inEntity.setDivisa("ARS");
		inEntity.setTasa("000000000");
		inEntity.setTarifa("    ");
		inEntity.setPeriodoLiquidacion(" ");
		inEntity.setIndicadorTransferible("N");
		inEntity.setSucursalRadicacion("0179");
		inEntity.setRespImpuesto("B");
		inEntity.setTipoPF("17");
		inEntity.setCantidadDias("00030");
		inEntity.setTasaVariable("000000000");
		inEntity.setSpread("000000000");
		inEntity.setSpreadSigno("+");
		inEntity.setTipoDiaLiqVariable("IF ");
		inEntity.setNumDiaLiqVariable("05");
		inEntity.setCanal("04");
		inEntity.setSimulacionOriginal("S");

		try {
			SimulacionPlazoFijoOutEntity respuesta = plazoFijoDAO.simularPlazoFijo(inEntity, cliente, false);
		} catch (Exception e) {
			Assert.assertEquals(DAOException.class, e.getClass());
		}
	}

	/**
	 * Consultar precancelable Test
	 * 
	 * @throws IatxException
	 * @throws DAOException
	 */
	@Ignore
	@Test
	public void consultarPrecancelableTest() throws IatxException, DAOException {
		String servicio = "CNSPFPRECA";
		String version = "110";
		String tramaResponse = "200000000000P04HTML0009900010301PRQP31  ********00898484000000082017011915083300000000IBF25716000000000000CNSPFPRECA1100000            01576531    00        00 015018389201701191508270000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0134400000000õ000000000600000õ02000000õ00000000õ12062017õ12062017õ00000000005917800õ                              õARSõSõ00030õ00080000õ00000õ000000000000000+õ000000000000000+õ000000000659178+õ00720000012000638801õARSõ00720000012000638801õARSõ00õ    õ                              õ000000000000000 õ õ    õ                              õ000000000000000 õ õ    õ                              õ000000000000000 õ õ    õ                              õ000000000000000 õ õ    õ                              õ000000000000000 õ õ    õ                              õ000000000000000 õ õ    õ                              õ000000000000000 õ õ    õ                              õ000000000000000 õ õ    õ                              õ000000000000000 õ õ    õ                              õ000000000000000 õ õ00õ    õ                              õ000000000000000 õ õ    õ                              õ000000000000000 õ õ    õ                              õ000000000000000 õ õ    õ                              õ000000000000000 õ õ    õ                              õ000000000000000 õ õ    õ                              õ000000000000000 õ õ    õ                              õ000000000000000 õ õ    õ                              õ000000000000000 õ õ    õ                              õ000000000000000 õ õ    õ                              õ000000000000000 õ õ";

		when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
		ConsultaPrecancelablePlazoFijoInEntity inEntity = new ConsultaPrecancelablePlazoFijoInEntity();

		inEntity.setCuentaPlazo("00720000001000011622");
		inEntity.setSecuencia("00029");

		ConsultaPrecancelablenPlazoFijoOutEntity respuesta = plazoFijoDAO.consultarPrecancelable(inEntity, cliente,
				false);
		Assert.assertNotNull(respuesta);
		Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));

	}

	/**
	 * Consultar precancelable TimeOutException
	 * 
	 * @throws DAOException
	 * @throws IatxException
	 */
	@Ignore
	@Test
	public void consultarPrecancelableTimeOutException() throws DAOException, IatxException {
		String servicio = "CNSPFPRECA";
		String version = "110";
		when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version))))
				.thenThrow(new IatxException(TIMEOUT_EXCEPTION));
		ConsultaPrecancelablePlazoFijoInEntity inEntity = new ConsultaPrecancelablePlazoFijoInEntity();

		inEntity.setCuentaPlazo("00720000001000011622");
		inEntity.setSecuencia("00029");

		try {
			ConsultaPrecancelablenPlazoFijoOutEntity respuesta = plazoFijoDAO.consultarPrecancelable(inEntity, cliente,
					false);
		} catch (Exception e) {
			Assert.assertEquals(TimeOutException.class, e.getClass());
		}
	}

	/**
	 * Consultar precancelable DAOException
	 * 
	 * @throws DAOException
	 * @throws IatxException
	 */
	@Ignore
	@Test
	public void consultarPrecancelableDAOException() throws DAOException, IatxException {
		String servicio = "CNSPFPRECA";
		String version = "110";
		when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version))))
				.thenThrow(new IatxException("Error no Timeout"));
		ConsultaPrecancelablePlazoFijoInEntity inEntity = new ConsultaPrecancelablePlazoFijoInEntity();

		inEntity.setCuentaPlazo("00720000001000011622");
		inEntity.setSecuencia("00029");

		try {
			ConsultaPrecancelablenPlazoFijoOutEntity respuesta = plazoFijoDAO.consultarPrecancelable(inEntity, cliente,
					false);
		} catch (Exception e) {
			Assert.assertEquals(DAOException.class, e.getClass());
		}
	}

	/**
	 * Simular Precancelar test
	 * 
	 * @throws IatxException
	 * @throws DAOException
	 */
	@Ignore
	@Test
	public void simularPrecancelarTest() throws IatxException, DAOException {
		String servicio = "PRECASIMCN";
		String version = "110";
		String tramaResponse = "200000000000P04HTML0009900010300AAAJ19  ********00274643000000142017021416163800000000IBF37873000016195053PRECASIMCN1100000            00000919    00        00 016195053201702141616350000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0039200000000õ00720194001000020774õ00009õCõ0778õ000000000500000õ000000000212262õ000000000000000õ000000000000000õ00080000õ000000000712262õ000000000032055õ000000000000000õ000000000165616õ000000000000000õ01300000õ00180õ00930õ30072014õ26012015õ                    õ   õ                    õ   õ000000000046646õ000000000000000õ000000000180207õ000000000000000õ02025000õ000002025000õ000000000000000+õ000õ";

		when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
		PrecancelacionPlazoFijoInEntity inEntity = new PrecancelacionPlazoFijoInEntity();

		inEntity.setOpcion("S");
		inEntity.setCuentaPlazo("00720000001000011622");
		inEntity.setSecuencia("9");
		inEntity.setTipoCuentaCredito("09");
		inEntity.setSucursalCuentaCredito("000");
		inEntity.setNroCuentaCredito("0638801");
		inEntity.setDivisaCuentaCredito("ARS");

		SimulacionPrecancelableOutEntity respuesta = plazoFijoDAO.simularPrecancelable(inEntity, cliente);
		Assert.assertNotNull(respuesta);
		Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
	}

	/**
	 * Simular precancelar TimeOutException
	 * 
	 * @throws DAOException
	 * @throws IatxException
	 */
	@Ignore
	@Test
	public void simularPrecancelarTimeOutException() throws DAOException, IatxException {
		String servicio = "PRECASIMCN";
		String version = "110";
		when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version))))
				.thenThrow(new IatxException(TIMEOUT_EXCEPTION));
		PrecancelacionPlazoFijoInEntity inEntity = new PrecancelacionPlazoFijoInEntity();

		inEntity.setOpcion("S");
		inEntity.setCuentaPlazo("00720000001000011622");
		inEntity.setSecuencia("9");
		inEntity.setTipoCuentaCredito("09");
		inEntity.setSucursalCuentaCredito("000");
		inEntity.setNroCuentaCredito("0638801");
		inEntity.setDivisaCuentaCredito("ARS");

		try {
			SimulacionPrecancelableOutEntity respuesta = plazoFijoDAO.simularPrecancelable(inEntity, cliente);
		} catch (Exception e) {
			Assert.assertEquals(TimeOutException.class, e.getClass());
		}
	}

	/**
	 * Simular precancelar DAOException
	 * 
	 * @throws DAOException
	 * @throws IatxException
	 */
	@Ignore
	@Test
	public void simularPrecancelarDAOException() throws DAOException, IatxException {
		String servicio = "PRECASIMCN";
		String version = "110";
		when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version))))
				.thenThrow(new IatxException("Error no Timeout"));
		PrecancelacionPlazoFijoInEntity inEntity = new PrecancelacionPlazoFijoInEntity();

		inEntity.setOpcion("S");
		inEntity.setCuentaPlazo("00720000001000011622");
		inEntity.setSecuencia("9");
		inEntity.setTipoCuentaCredito("09");
		inEntity.setSucursalCuentaCredito("000");
		inEntity.setNroCuentaCredito("0638801");
		inEntity.setDivisaCuentaCredito("ARS");

		try {
			SimulacionPrecancelableOutEntity respuesta = plazoFijoDAO.simularPrecancelable(inEntity, cliente);
		} catch (Exception e) {
			Assert.assertEquals(DAOException.class, e.getClass());
		}
	}
	
}
