/**
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.inversiones.TipoPapel;
import ar.com.santanderrio.obp.servicios.inversiones.exception.ImporteMayorAlMaximoException;
import ar.com.santanderrio.obp.servicios.inversiones.exception.ImporteMenorAlMinimoException;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.*;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.singleton.FondosCotizacionesSingleton;
import ar.com.santanderrio.obp.servicios.transferencias.exception.CuentaSinOperarException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.FueraDeHorarioException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.SaldoInsuficienteException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TimeOutException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
 * The Class FondoDAOTest.
 *
 * @author
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        FondoDAOTest.FondoDAOTestConfiguration.class })
@TestPropertySource(properties = { "DB.TIMEOUT = 30000" })
public class FondoDAOTest extends IatxBaseDAOTest {

    private static final int FINALIZAR_SUSCRIPCION_FUERA_HORARIO_CODIGO_RETORNO = 99000024;
    private static final String TIMEOUT_EXCEPTION = "iatx.exceptions.IatxConnectException: Se envió la transacción al CICS pero no se recibió respuesta.õ";
    /** The fondo DAO. */
    @Autowired
    @InjectMocks
    private FondoDAO fondoDAO;

    @Mock
    private SesionCliente sesionCliente;
    /*
     * @Mock private IatxComm iatxComm;
     */

    @Autowired
    private FondosCotizacionesSingleton cotizacionesSingleton;

    /** The cliente. */
    private Cliente cliente = new Cliente();

    ComprobanteSuscripcionFondoInEntity comprobanteSuscFondo;
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
        cliente.setApellido2("MOLINA RIVERA");
        cliente.setSegmento(segmento);
        cliente.setPasswordRacf("XSR96DBP");
        cliente.setUsuarioRacf("USRDOBP");
        cliente.setNup("00276937");
        cliente.setTipoRacf("04");
        cliente.setValorSinonimo("0");
        cliente.setTipoDocumento("N");
        cliente.setDni("00021531419");
        cliente.setTipoPersona("I");
        cliente.setTipoClave("A");
        cliente.setFechaNacimiento(null);
        
        comprobanteSuscFondo = new ComprobanteSuscripcionFondoInEntity();
        comprobanteSuscFondo.setTerminalSafe(comprobanteSuscFondo.getTerminalSafe());
        comprobanteSuscFondo.setCodigoFondo("007");
        comprobanteSuscFondo.setCodigoCliente("00109374933");
        comprobanteSuscFondo.setCodigoAgente("001");
        comprobanteSuscFondo.setCodigoCanal("991");
        comprobanteSuscFondo.setImporteBruto("00000000034500");
        comprobanteSuscFondo.setPorcentajeComision(comprobanteSuscFondo.getPorcentajeComision());
        comprobanteSuscFondo.setFormaDePago("02");
        comprobanteSuscFondo.setNombreBanco(comprobanteSuscFondo.getNombreBanco());
        comprobanteSuscFondo.setNumeroCheque(comprobanteSuscFondo.getNumeroCheque());
        comprobanteSuscFondo.setTipoCuentaDebito("09");
        comprobanteSuscFondo.setSucursalCuentaDebito("000");
        comprobanteSuscFondo.setNumeroCuentaDebito("4031569");
        comprobanteSuscFondo.setImpreSolicitud(comprobanteSuscFondo.getImpreSolicitud());
        comprobanteSuscFondo.setCotizacionCambio(comprobanteSuscFondo.getCotizacionCambio());
        comprobanteSuscFondo.setFechaRescateFuturo(comprobanteSuscFondo.getFechaRescateFuturo());
        comprobanteSuscFondo.setCodigoCustodiaActual("4");
        comprobanteSuscFondo.setDiaClearingCheques("0");
        comprobanteSuscFondo.setMoneda("0");
        comprobanteSuscFondo.setNroCertificReversar("0000000000");
        comprobanteSuscFondo.setMontoReversar("00000000000000");
    }

    /**
     * The Class FondoDAOTestConfiguration.
     */
    @Configuration
    public static class FondoDAOTestConfiguration {

        /**
         * Fondo DAO.
         *
         * @return the fondo DAO
         */
        @Bean
        public FondoDAO fondoDAO() {
            return new FondoDAOImpl();
        }

        @Bean
        public SesionCliente sesionCliente() {
            return new SesionCliente();
        }

        @Bean
        public FondosCotizacionesSingleton cotizacionesSingleton() { return new FondosCotizacionesSingleton() ; }
    }

    /**
     * Consultar cotizaciones test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void consultarCotizacionesTest() throws IatxException, DAOException {
        String servicio = "CNSCOTIFCI";
        String version = "120";
        String tramaResponse = "200000000000P04HTML0009900010300LSKL29  ********00855522000000042016102414283900000000IBF20552000000000000CNSCOTIFCI1200000            00180129    00        00 000000000201610241428340000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0438100000000õ052õ001õSUPERFONDO ACCIONES õ00000000197382õ000000+õ000000+õ002858+õ000000+õ19961104õ00õ002õSUPERFONDO RENTA $ Cõ00000000143555õ000000+õ000000+õ002800+õ000000+õ19961104õ00õ003õSUPERFONDO 2000 CUOTõ00000000000207õ000000+õ000000+õ000072+õ000000+õ19961104õ02õ005õSUPERFONDO 2001 CUOTõ00000000010371õ000000+õ000000+õ000505-õ000000+õ19970304õ00õ006õSUPER AHORRO U$S CUOõ00000000012505õ000000+õ000000+õ001348-õ000000+õ19961105õ00õ007õSUPER AHORRO $ CUOTAõ00000000052283õ000000+õ000000+õ001182+õ000000+õ19961105õ00õ011õSUPER ACCIONES BRASIõ00000000111237õ000000+õ000000+õ001516-õ000000+õ20060123õ02õ013õSUPER ACCIONES BRASIõ00000001596262õ000000+õ000000+õ002932+õ000000+õ20060123õ00õ022õSUPERFONDO AHORRO U$õ00000000080800õ000000+õ000000+õ002797-õ000000+õ19950516õ00õ023õSUPERFONDO RTA VARIAõ00000002684829õ000000+õ000000+õ003384+õ000000+õ19950516õ00õ025õSUPER RTA LATINOAMERõ00000000100481õ000000+õ000000+õ003653-õ000000+õ19961122õ02õ027õSUPER LATINOAMERICA õ00000000321693õ000000+õ000000+õ000954-õ000000+õ19971015õ02õ031õSUPER BONOS FCI CUOTõ00000002427793õ000000+õ000000+õ002535+õ000000+õ20020927õ00õ033õSUPERGESTION FCI CUOõ00000001147337õ000000+õ000000+õ002920+õ000000+õ20021007õ00õ034õSUPERFDO RENTA FIJA õ00000000162576õ000000+õ000000+õ003231+õ000000+õ20021007õ00õ035õSUPER RTA LATINOAMERõ00000001470021õ000000+õ000000+õ000136-õ000000+õ20020403õ00õ037õSUPER LATINOAMERICA õ00000004616298õ000000+õ000000+õ003788+õ000000+õ20020403õ00õ039õSUPER RENTA FUTURA Cõ00000000968995õ000000+õ000000+õ004356+õ000000+õ20060502õ00õ041õSUPERGESTION BALANCEõ00000000172793õ000000+õ000000+õ004064+õ000000+õ20121030õ00õ042õSUPER RENTA FUTURA Cõ00000000969009õ000000+õ000000+õ004356+õ000000+õ20060502õ00õ050õSUPERGESTION BALANCEõ00000000176943õ000000+õ000000+õ004142+õ000000+õ20121030õ00õ051õSUPER AHORRO U$S EX õ00000000011161õ000000+õ000000+õ001242+õ000000+õ20011228õ02õ054õSUPERGEST MULTIMERCAõ00000000233075õ000000+õ000000+õ004338+õ000000+õ20121030õ00õ055õSUPERFONDO AHORRO U$õ00000000077414õ000000+õ000000+õ002090+õ000000+õ20011228õ02õ056õSUPER 2001 EX LET CUõ00000000009724õ000000+õ000000+õ000969+õ000000+õ20020111õ02õ058õSUPERFONDO AHORRO U$õ00000000034812õ000000+õ000000+õ002777-õ000000+õ20020125õ00õ060õSUPER 2000 PLAZO FIJõ00000000011846õ000000+õ000000+õ003437-õ000000+õ20020307õ00õ063õSUPERGEST MULTIMERCAõ00000000247129õ000000+õ000000+õ004517+õ000000+õ20121030õ00õ064õSUPERFONDO RENTA PESõ00000000102105õ000000+õ000000+õ000000+õ000000+õ20160608õ00õ067õSUPERFONDO RTA VARIAõ00000000111334õ000000+õ000000+õ000000+õ000000+õ20160601õ00õ070õSUPERFONDO ACCIONES õ00000000112208õ000000+õ000000+õ000000+õ000000+õ20160520õ00õ072õSUPERGESTION MIX VI õ00000000416282õ000000+õ000000+õ002078+õ000000+õ20060828õ00õ082õSUPER AHORRO PLUS CUõ00000001898420õ000000+õ000000+õ001479+õ000000+õ20120409õ00õ100õSUPER FONDO EQUILIBRõ00000000088938õ000000+õ000000+õ001106-õ000000+õ20150827õ00õ110õSUPER FONDO COMBINADõ00000000066467õ000000+õ000000+õ003353-õ000000+õ20150827õ00õ117õSUPERFDO RENTA FIJA õ00000000105733õ000000+õ000000+õ000000+õ000000+õ20160601õ00õ118õSUPERGESTION FCI CUOõ00000001147335õ000000+õ000000+õ000000+õ000000+õ20160520õ00õ119õSUPERGESTION FCI CUOõ00000001147337õ000000+õ000000+õ000000+õ000000+õ20160520õ00õ120õSUPER FONDO RENTA MIõ00000000100000õ000000+õ000000+õ000000+õ000000+õ20150827õ00õ127õSUPERGESTION FCI CUOõ00000001147337õ000000+õ000000+õ000000+õ000000+õ20160520õ00õ129õSUPEGESTION FCI CUOTõ00000000107601õ000000+õ000000+õ000000+õ000000+õ20160520õ00õ140õSUPERFONDO MULTIRENTõ00000000100000õ000000+õ000000+õ000000+õ000000+õ20160210õ00õ142õSUPERFONDO MULTIRENTõ00000000100000õ000000+õ000000+õ000000+õ000000+õ20160210õ00õ145õSUPERFONDO MULTIRENTõ00000000100000õ000000+õ000000+õ000000+õ000000+õ20160210õ00õ147õSUPER BONOS CUOTA F õ00000000104675õ000000+õ000000+õ000000+õ000000+õ20160520õ00õ150õSUPERFONDO ESTRATEGIõ00000000100000õ000000+õ000000+õ000000+õ000000+õ20160210õ00õ152õSUPERFONDO ESTRATEGIõ00000000100000õ000000+õ000000+õ000000+õ000000+õ20160210õ00õ155õSUPERFONDO ESTRATEGIõ00000000100000õ000000+õ000000+õ000000+õ000000+õ20160210õ00õ160õSUPERFONDO RENTA BALõ00000000100000õ000000+õ000000+õ000000+õ000000+õ20160210õ00õ162õSUPERFONDO RENTA BALõ00000000100000õ000000+õ000000+õ000000+õ000000+õ20160210õ00õ165õSUPERFONDO RENTA BALõ00000000100000õ000000+õ000000+õ000000+õ000000+õ20160210õ00õ170õSUPERFONDO RENTA FIJõ00000000102469õ000000+õ000000+õ000000+õ000000+õ20160404õ02õ";

        when(sesionCliente.getCliente()).thenReturn(cliente);

        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        FondoOutEntity respuesta = fondoDAO.consultarCotizaciones();
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(cotizacionesSingleton.getOutEntity(), respuesta);
        Mockito.verify(iatxSender, times(1)).send(Matchers.argThat(new IatxMatcher(servicio, version)));
    }

    @Test
    public void consultarCotizacionesTestReturnLastOkResult() throws IatxException, DAOException {
        FondosCotizacionesSingleton singletonMock = Mockito.mock(FondosCotizacionesSingleton.class);

        ReflectionTestUtils.setField(fondoDAO, "cotizacionesSingleton", singletonMock);

        String servicio = "CNSCOTIFCI";
        String version = "120";
        String tramaResponse = "200000000000P04HTML00099000104USRDOBP   ********00576849000000182021092913302300000000IBF202P5000000000000CNSCOTIFCI1200000N           00078048    00        00 000000000202109291329330000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0034018888801õCCOõ03õLa operacion solicitada no puede ser realizada.Por favor, reintente mas tarde.                                                                                                                                                                                õ               õ - IAT3LINK: TXN NO DISPONIBLE SAFE NOCT.           õ";

        FondoOutEntity lastOkResult = new FondoOutEntity();
        FondoEntity fondoEntity = new FondoEntity();
        List<FondoEntity> fondoEntities = new ArrayList<FondoEntity>();

        fondoEntities.add(fondoEntity);

        lastOkResult.setHeaderTrama("SOMETRAMA");
        lastOkResult.setCodigoRetornoExtendido("00000000");
        lastOkResult.setfondos(fondoEntities);
        lastOkResult.setCantidadFondos(1L);

        when(sesionCliente.getCliente()).thenReturn(cliente);

        when(singletonMock.getOutEntity()).thenReturn(lastOkResult);

        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);

        FondoOutEntity respuesta = fondoDAO.consultarCotizaciones();
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(singletonMock.getOutEntity(), respuesta);
        Mockito.verify(iatxSender, times(1)).send(Matchers.argThat(new IatxMatcher(servicio, version)));
    }

    /**
     * Consultar cotizaciones DAOException.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void consultarCotizacionesDAOException() throws IatxException {
        String servicio = "CNSCOTIFCI";
        String version = "120";
        String tramaError = "200000000000P04HTML0009900010300LSKL29  ********00855522000000042016102414283900000000IBF20552000000000000CNSCOTIFCI1200000            00180129    00        00 000000000201610241428340000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0438100000001õ052õ001õSUPERFONDO ACCIONES õ00000000197382õ000000+õ000000+õ002858+õ000000+õ19961104õ00õ002õSUPERFONDO RENTA $ Cõ00000000143555õ000000+õ000000+õ002800+õ000000+õ19961104õ00õ003õSUPERFONDO 2000 CUOTõ00000000000207õ000000+õ000000+õ000072+õ000000+õ19961104õ02õ005õSUPERFONDO 2001 CUOTõ00000000010371õ000000+õ000000+õ000505-õ000000+õ19970304õ00õ006õSUPER AHORRO U$S CUOõ00000000012505õ000000+õ000000+õ001348-õ000000+õ19961105õ00õ007õSUPER AHORRO $ CUOTAõ00000000052283õ000000+õ000000+õ001182+õ000000+õ19961105õ00õ011õSUPER ACCIONES BRASIõ00000000111237õ000000+õ000000+õ001516-õ000000+õ20060123õ02õ013õSUPER ACCIONES BRASIõ00000001596262õ000000+õ000000+õ002932+õ000000+õ20060123õ00õ022õSUPERFONDO AHORRO U$õ00000000080800õ000000+õ000000+õ002797-õ000000+õ19950516õ00õ023õSUPERFONDO RTA VARIAõ00000002684829õ000000+õ000000+õ003384+õ000000+õ19950516õ00õ025õSUPER RTA LATINOAMERõ00000000100481õ000000+õ000000+õ003653-õ000000+õ19961122õ02õ027õSUPER LATINOAMERICA õ00000000321693õ000000+õ000000+õ000954-õ000000+õ19971015õ02õ031õSUPER BONOS FCI CUOTõ00000002427793õ000000+õ000000+õ002535+õ000000+õ20020927õ00õ033õSUPERGESTION FCI CUOõ00000001147337õ000000+õ000000+õ002920+õ000000+õ20021007õ00õ034õSUPERFDO RENTA FIJA õ00000000162576õ000000+õ000000+õ003231+õ000000+õ20021007õ00õ035õSUPER RTA LATINOAMERõ00000001470021õ000000+õ000000+õ000136-õ000000+õ20020403õ00õ037õSUPER LATINOAMERICA õ00000004616298õ000000+õ000000+õ003788+õ000000+õ20020403õ00õ039õSUPER RENTA FUTURA Cõ00000000968995õ000000+õ000000+õ004356+õ000000+õ20060502õ00õ041õSUPERGESTION BALANCEõ00000000172793õ000000+õ000000+õ004064+õ000000+õ20121030õ00õ042õSUPER RENTA FUTURA Cõ00000000969009õ000000+õ000000+õ004356+õ000000+õ20060502õ00õ050õSUPERGESTION BALANCEõ00000000176943õ000000+õ000000+õ004142+õ000000+õ20121030õ00õ051õSUPER AHORRO U$S EX õ00000000011161õ000000+õ000000+õ001242+õ000000+õ20011228õ02õ054õSUPERGEST MULTIMERCAõ00000000233075õ000000+õ000000+õ004338+õ000000+õ20121030õ00õ055õSUPERFONDO AHORRO U$õ00000000077414õ000000+õ000000+õ002090+õ000000+õ20011228õ02õ056õSUPER 2001 EX LET CUõ00000000009724õ000000+õ000000+õ000969+õ000000+õ20020111õ02õ058õSUPERFONDO AHORRO U$õ00000000034812õ000000+õ000000+õ002777-õ000000+õ20020125õ00õ060õSUPER 2000 PLAZO FIJõ00000000011846õ000000+õ000000+õ003437-õ000000+õ20020307õ00õ063õSUPERGEST MULTIMERCAõ00000000247129õ000000+õ000000+õ004517+õ000000+õ20121030õ00õ064õSUPERFONDO RENTA PESõ00000000102105õ000000+õ000000+õ000000+õ000000+õ20160608õ00õ067õSUPERFONDO RTA VARIAõ00000000111334õ000000+õ000000+õ000000+õ000000+õ20160601õ00õ070õSUPERFONDO ACCIONES õ00000000112208õ000000+õ000000+õ000000+õ000000+õ20160520õ00õ072õSUPERGESTION MIX VI õ00000000416282õ000000+õ000000+õ002078+õ000000+õ20060828õ00õ082õSUPER AHORRO PLUS CUõ00000001898420õ000000+õ000000+õ001479+õ000000+õ20120409õ00õ100õSUPER FONDO EQUILIBRõ00000000088938õ000000+õ000000+õ001106-õ000000+õ20150827õ00õ110õSUPER FONDO COMBINADõ00000000066467õ000000+õ000000+õ003353-õ000000+õ20150827õ00õ117õSUPERFDO RENTA FIJA õ00000000105733õ000000+õ000000+õ000000+õ000000+õ20160601õ00õ118õSUPERGESTION FCI CUOõ00000001147335õ000000+õ000000+õ000000+õ000000+õ20160520õ00õ119õSUPERGESTION FCI CUOõ00000001147337õ000000+õ000000+õ000000+õ000000+õ20160520õ00õ120õSUPER FONDO RENTA MIõ00000000100000õ000000+õ000000+õ000000+õ000000+õ20150827õ00õ127õSUPERGESTION FCI CUOõ00000001147337õ000000+õ000000+õ000000+õ000000+õ20160520õ00õ129õSUPEGESTION FCI CUOTõ00000000107601õ000000+õ000000+õ000000+õ000000+õ20160520õ00õ140õSUPERFONDO MULTIRENTõ00000000100000õ000000+õ000000+õ000000+õ000000+õ20160210õ00õ142õSUPERFONDO MULTIRENTõ00000000100000õ000000+õ000000+õ000000+õ000000+õ20160210õ00õ145õSUPERFONDO MULTIRENTõ00000000100000õ000000+õ000000+õ000000+õ000000+õ20160210õ00õ147õSUPER BONOS CUOTA F õ00000000104675õ000000+õ000000+õ000000+õ000000+õ20160520õ00õ150õSUPERFONDO ESTRATEGIõ00000000100000õ000000+õ000000+õ000000+õ000000+õ20160210õ00õ152õSUPERFONDO ESTRATEGIõ00000000100000õ000000+õ000000+õ000000+õ000000+õ20160210õ00õ155õSUPERFONDO ESTRATEGIõ00000000100000õ000000+õ000000+õ000000+õ000000+õ20160210õ00õ160õSUPERFONDO RENTA BALõ00000000100000õ000000+õ000000+õ000000+õ000000+õ20160210õ00õ162õSUPERFONDO RENTA BALõ00000000100000õ000000+õ000000+õ000000+õ000000+õ20160210õ00õ165õSUPERFONDO RENTA BALõ00000000100000õ000000+õ000000+õ000000+õ000000+õ20160210õ00õ170õSUPERFONDO RENTA FIJõ00000000102469õ000000+õ000000+õ000000+õ000000+õ20160404õ02õ";

        when(sesionCliente.getCliente()).thenReturn(cliente);

        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaError);
        try {
            fondoDAO.consultarCotizaciones();
        } catch (Exception e) {
            Assert.assertEquals(DAOException.class, e.getClass());
        }
    }

    /**
     * Consultar cotizaciones IatxException.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void consultarCotizacionesIatxException() throws IatxException {
        String servicio = "CNSCOTIFCI";
        String version = "120";
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenThrow(new IatxException());
        try {
            fondoDAO.consultarCotizaciones();
        } catch (Exception e) {
            System.out.println(e);
            Assert.assertEquals(DAOException.class, e.getClass());
            Assert.assertEquals(e.getCause().getClass(), IatxException.class);
        }
    }

    /**
     * Consultar cotizaciones Exception.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void consultarCotizacionesException() throws IatxException {
        String servicio = "CNSCOTIFCI";
        String version = "120";
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenThrow(new RuntimeException());
        try {
            fondoDAO.consultarCotizaciones();
        } catch (Exception e) {
            Assert.assertEquals(DAOException.class, e.getClass());
        }
    }

    @Test
    public void suscribirErrorFueraHorarioTest() throws IatxException, DAOException {
        String servicio = "CNSSUSFCI_";
        String version = "130";
        String tramaResponse = "200000000000P04HTML0009900010301PRQP31  ********00598587000000072016121510555400000000IBF25976000000000000CNSSUSFCI_1300000            01576531    00        00 000000000201612151055480000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0036890000024õFCIõ03õEl monto ingresado es insuficiente para realizar su operacion. Por favor, reingrese un monto mayor                                                                                                                                                            õIMP.MENOR MINIMõ100- IMPORTE NETO ES MENOR QUE EL LIMITE MINIMO PERMITIDO                       õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        SuscripcionFondoInEntity suscripcionFondoInEntity = new SuscripcionFondoInEntity();
        suscripcionFondoInEntity.setCliente(cliente);
        try {
            SuscripcionFondoOutEntity respuesta = fondoDAO.suscribir(suscripcionFondoInEntity);
        } catch (Exception e) {
            Assert.assertEquals(FueraDeHorarioException.class, e.getClass());
        }
    }

    /**
     * test TimeOutException en suscripcion
     * 
     * @throws DAOException
     * @throws IatxException
     */
    @Test
    public void suscribirTimeOutException() throws DAOException, IatxException {
        String servicio = "CNSSUSFCI_";
        String version = "130";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version))))
                .thenThrow(new IatxException(TIMEOUT_EXCEPTION));
        SuscripcionFondoInEntity suscripcionFondoInEntity = new SuscripcionFondoInEntity();
        suscripcionFondoInEntity.setCliente(cliente);
        try {
            fondoDAO.suscribir(suscripcionFondoInEntity);
        } catch (Exception e) {
            Assert.assertEquals(TimeOutException.class, e.getClass());
        }
    }

    /**
     * test DAOException en suscripcion
     * 
     * @throws DAOException
     * @throws IatxException
     */
    @Test
    public void suscribirDAOException() throws DAOException, IatxException {
        String servicio = "CNSSUSFCI_";
        String version = "130";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version))))
                .thenThrow(new IatxException("Error no Timeout"));
        SuscripcionFondoInEntity suscripcionFondoInEntity = new SuscripcionFondoInEntity();
        suscripcionFondoInEntity.setCliente(cliente);
        try {
            fondoDAO.suscribir(suscripcionFondoInEntity);
        } catch (Exception e) {
            Assert.assertEquals(DAOException.class, e.getClass());
        }
    }

    /**
     * Consulta tenencia FCI test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void consultaTenenciaFCITest() throws IatxException, DAOException {
        String servicio = "CNSTENVAL_";
        String version = "110";
        List<SucursalCuentaEntity> sucursalCuentaList = new ArrayList<SucursalCuentaEntity>();
        SucursalCuentaEntity sucursaCuenta1 = new SucursalCuentaEntity("000", "0000000026382210");
        SucursalCuentaEntity sucursaCuenta2 = new SucursalCuentaEntity("000", "0000000042281258");
        sucursalCuentaList.add(sucursaCuenta1);
        sucursalCuentaList.add(sucursaCuenta2);
        String tramaResponse = "200000000000P04HTML0009900010300LSKL29  ********00855526000000042016102415455300000000IBF26410000000000000CNSTENVAL_1100000            00180129    00        00 015466426201610241545480000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0072700000000õ002õ000õ11036645õFCIõ00007õSUPER AHORRO $ CUOTA A        õ00000208480768õ000000000õ00000001090000õ00000052283000õ00õPESOSõ00000000õ000000õ  õ                              õAHPõ00000001089999õ00000001090000õ00000208480768õ00000000000000õ00000000000000õ00000000õ00000000õ000000õ000õ                                                                                õ000õ11036645õFCIõ00072õSUPERGESTION MIX VI CUOTA A   õ00000006005545õ000000000õ00000000250000õ00000416282000õ00õPESOSõ00000000õ000000õ  õ                              õMVIõ00000000250000õ00000000250000õ00000006005545õ00000000000000õ00000000000000õ00000000õ00000000õ000000õ000õ                                                                                õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        ConsultaTenenciaFCIInEntity consultaTenenciaFCIInEntity = new ConsultaTenenciaFCIInEntity();

        consultaTenenciaFCIInEntity.setTipoPapelEnum(TipoPapel.FCI);
        consultaTenenciaFCIInEntity.setEspecieCodigo(null);
        consultaTenenciaFCIInEntity.setFechaPeriodo("20220816");
        consultaTenenciaFCIInEntity.setHoraPeriodo("15:45:53");
        consultaTenenciaFCIInEntity.setSucursalCuentaList(sucursalCuentaList);

        ConsultaTenenciaFCIOutEntity respuesta = fondoDAO.consultaTenenciaFCI(cliente, consultaTenenciaFCIInEntity);
        Assert.assertNotNull(respuesta);
        Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
    }

    /**
     * Consulta tenencia FCI test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void consultaTenenciaFCIError() throws IatxException, DAOException {
        String servicio = "CNSTENVAL_";
        String version = "110";
        List<SucursalCuentaEntity> sucursalCuentaList = new ArrayList<SucursalCuentaEntity>();
        SucursalCuentaEntity sucursaCuenta1 = new SucursalCuentaEntity("000", "0000000026382210");
        SucursalCuentaEntity sucursaCuenta2 = new SucursalCuentaEntity("000", "0000000042281258");
        sucursalCuentaList.add(sucursaCuenta1);
        sucursalCuentaList.add(sucursaCuenta2);
        String tramaerror = "200000000000P04HTML0009900010300LSKL29  ********00855526000000042016102415455300000000IBF26410000000000000CNSTENVAL_1100000            00180129    00        00 015466426201610241545480000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ007270000001õ002õ000õ11036645õFCIõ00007õSUPER AHORRO $ CUOTA A        õ00000208480768õ000000000õ00000001090000õ00000052283000õ00õPESOSõ00000000õ000000õ  õ                              õAHPõ00000001089999õ00000001090000õ00000208480768õ00000000000000õ00000000000000õ00000000õ00000000õ000000õ000õ                                                                                õ000õ11036645õFCIõ00072õSUPERGESTION MIX VI CUOTA A   õ00000006005545õ000000000õ00000000250000õ00000416282000õ00õPESOSõ00000000õ000000õ  õ                              õMVIõ00000000250000õ00000000250000õ00000006005545õ00000000000000õ00000000000000õ00000000õ00000000õ000000õ000õ                                                                                õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaerror);
        ConsultaTenenciaFCIInEntity consultaTenenciaFCIInEntity = new ConsultaTenenciaFCIInEntity();

        consultaTenenciaFCIInEntity.setTipoPapelEnum(TipoPapel.FCI);
        consultaTenenciaFCIInEntity.setEspecieCodigo(null);
        consultaTenenciaFCIInEntity.setFechaPeriodo("20220816");
        consultaTenenciaFCIInEntity.setHoraPeriodo("15:45:53");
        consultaTenenciaFCIInEntity.setSucursalCuentaList(sucursalCuentaList);

        try {
            ConsultaTenenciaFCIOutEntity respuesta = fondoDAO.consultaTenenciaFCI(cliente, consultaTenenciaFCIInEntity);
        } catch (Exception e) {
            Assert.assertEquals(DAOException.class, e.getClass());
        }
    }

    @Test
    public void consultaTenenciaFCIIatxException() throws IatxException, DAOException {
        String servicio = "CNSTENVAL_";
        String version = "110";
        List<SucursalCuentaEntity> sucursalCuentaList = new ArrayList<SucursalCuentaEntity>();
        SucursalCuentaEntity sucursaCuenta1 = new SucursalCuentaEntity("000", "0000000026382210");
        SucursalCuentaEntity sucursaCuenta2 = new SucursalCuentaEntity("000", "0000000042281258");
        sucursalCuentaList.add(sucursaCuenta1);
        sucursalCuentaList.add(sucursaCuenta2);
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenThrow(new IatxException());
        ConsultaTenenciaFCIInEntity consultaTenenciaFCIInEntity = new ConsultaTenenciaFCIInEntity();

        consultaTenenciaFCIInEntity.setTipoPapelEnum(TipoPapel.FCI);
        consultaTenenciaFCIInEntity.setEspecieCodigo(null);
        consultaTenenciaFCIInEntity.setFechaPeriodo("20220816");
        consultaTenenciaFCIInEntity.setHoraPeriodo("15:45:53");
        consultaTenenciaFCIInEntity.setSucursalCuentaList(sucursalCuentaList);

        try {
            ConsultaTenenciaFCIOutEntity respuesta = fondoDAO.consultaTenenciaFCI(cliente, consultaTenenciaFCIInEntity);
        } catch (Exception e) {
            Assert.assertEquals(DAOException.class, e.getClass());
        }
    }

    /**
     * Consultar movimientos test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void consultarMovimientosTest() throws IatxException, DAOException {
        String servicio = "CNSMOVIFCI";
        String version = "110";
        String tramaResponse = "200000000000P04HTML0009900010300LSKL29  ********00855520000000042016102412472400000000IBF29025000000000000CNSMOVIFCI1100000            00180129    00        00 000000000201610241247180000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0032100000000õ002õ007õ00111036645õ001õ991õSUSC.        01õ24/10/2016õ24/10/2016õ0005912436õ00000000000090000õ00000000017214008õ00000000052283000õ0000000000õACTIVA       02õ007õ00111036645õ001õ991õSUSC.        01õ24/10/2016õ24/10/2016õ0005912451õ00000000001000000õ00000000191266760õ00000000052283000õ0000000000õACTIVA       02õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        MovimientosFondoInEntity fondoAConsultar = new MovimientosFondoInEntity(30);
        fondoAConsultar.setCodigoFondo(null);
        fondoAConsultar.setCodigoCliente(null);
        fondoAConsultar.setFechaDesde(null);
        fondoAConsultar.setFechaHasta(null);

        MovimientosFondoOutEntity respuesta = fondoDAO.consultarMovimientos(cliente, fondoAConsultar);
        Assert.assertNotNull(respuesta);
        Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
    }

    /**
     * Error en consulta
     * @throws IatxException
     * @throws DAOException
     */
    @Test
    public void consultarMovimientosError() throws IatxException, DAOException {
        String servicio = "CNSMOVIFCI";
        String version = "110";
        String tramaResponse = "200000000000P04HTML0009900010300LSKL29  ********00855520000000042016102412472400000000IBF29025000000000000CNSMOVIFCI1100000            00180129    00        00 000000000201610241247180000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0032100000001õ002õ007õ00111036645õ001õ991õSUSC.        01õ24/10/2016õ24/10/2016õ0005912436õ00000000000090000õ00000000017214008õ00000000052283000õ0000000000õACTIVA       02õ007õ00111036645õ001õ991õSUSC.        01õ24/10/2016õ24/10/2016õ0005912451õ00000000001000000õ00000000191266760õ00000000052283000õ0000000000õACTIVA       02õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        MovimientosFondoInEntity fondoAConsultar = new MovimientosFondoInEntity(30);
        fondoAConsultar.setCodigoFondo(null);
        fondoAConsultar.setCodigoCliente(null);
        fondoAConsultar.setFechaDesde(null);
        fondoAConsultar.setFechaHasta(null);
        try{
        	MovimientosFondoOutEntity respuesta = fondoDAO.consultarMovimientos(cliente, fondoAConsultar);
        }catch (DAOException e){
        	Assert.assertEquals(DAOException.class, e.getClass());
        }
    }
    
    
    @Test
    public void consultarMovimientosIatxException() throws IatxException, DAOException {
        String servicio = "CNSMOVIFCI";
        String version = "110";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenThrow(new IatxException());
        MovimientosFondoInEntity fondoAConsultar = new MovimientosFondoInEntity(30);
        fondoAConsultar.setCodigoFondo(null);
        fondoAConsultar.setCodigoCliente(null);
        fondoAConsultar.setFechaDesde(null);
        fondoAConsultar.setFechaHasta(null);
        try {
            MovimientosFondoOutEntity respuesta = fondoDAO.consultarMovimientos(cliente, fondoAConsultar);
        } catch (Exception e) {
            Assert.assertEquals(DAOException.class, e.getClass());
        }
    }

    @Test
    public void consultarMovimientosRuntimeException() throws IatxException, DAOException {
        String servicio = "CNSMOVIFCI";
        String version = "110";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenThrow(new RuntimeException());
        MovimientosFondoInEntity fondoAConsultar = new MovimientosFondoInEntity(30);
        fondoAConsultar.setCodigoFondo(null);
        fondoAConsultar.setCodigoCliente(null);
        fondoAConsultar.setFechaDesde(null);
        fondoAConsultar.setFechaHasta(null);
        try {
            MovimientosFondoOutEntity respuesta = fondoDAO.consultarMovimientos(cliente, fondoAConsultar);
        } catch (Exception e) {
            Assert.assertEquals(RuntimeException.class, e.getClass());
        }
    }

    /**
     * Suscribir test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void suscribirTest() throws IatxException, DAOException {
        String servicio = "CNSSUSFCI_";
        String version = "130";
        String tramaResponse = "200000000000P04HTML0009900010302QLPO92  ********00855423000000062016101812082300000000IBF22210000000000000CNSSUSFCI_1300000            02615492    00        00 000000000201610181208180000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0014400000000õPESOSõ0000000000õ00000000045600õ0000õ00000000000000õ             õ             õ00000000000000õ000õSUPER AHORRO $ CUOTAõ00000000000000õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        SuscripcionFondoInEntity suscripcionFondoInEntity = new SuscripcionFondoInEntity();
        suscripcionFondoInEntity.setCliente(cliente);

        SuscripcionFondoOutEntity respuesta = fondoDAO.suscribir(suscripcionFondoInEntity);
        Assert.assertNotNull(respuesta);
        Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
    }

    @Test
    public void suscribirErrorImporteTest() throws IatxException, DAOException {
        String servicio = "CNSSUSFCI_";
        String version = "130";
        String tramaResponse = "200000000000P04HTML0009900010301PRQP31  ********00598587000000072016121510555400000000IBF25976000000000000CNSSUSFCI_1300000            01576531    00        00 000000000201612151055480000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0036890000100õFCIõ03õEl monto ingresado es insuficiente para realizar su operacion. Por favor, reingrese un monto mayor                                                                                                                                                            õIMP.MENOR MINIMõ100- IMPORTE NETO ES MENOR QUE EL LIMITE MINIMO PERMITIDO                       õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        SuscripcionFondoInEntity suscripcionFondoInEntity = new SuscripcionFondoInEntity();
        suscripcionFondoInEntity.setCliente(cliente);
        try {
            SuscripcionFondoOutEntity respuesta = fondoDAO.suscribir(suscripcionFondoInEntity);
        } catch (Exception e) {
            Assert.assertEquals(ImporteMenorAlMinimoException.class, e.getClass());
        }
    }
    
    
    @Test
    public void suscribirErrorImporteMayorAlMaximoException() throws IatxException, DAOException {
        String servicio = "CNSSUSFCI_";
        String version = "130";
        String tramaResponse = "200000000000P04HTML0009900010301PRQP31  ********00598587000000072016121510555400000000IBF25976000000000000CNSSUSFCI_1300000            01576531    00        00 000000000201612151055480000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0036890000101õFCIõ03õEl monto ingresado es insuficiente para realizar su operacion. Por favor, reingrese un monto mayor                                                                                                                                                            õIMP.MENOR MINIMõ100- IMPORTE NETO ES MENOR QUE EL LIMITE MINIMO PERMITIDO                       õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        SuscripcionFondoInEntity suscripcionFondoInEntity = new SuscripcionFondoInEntity();
        suscripcionFondoInEntity.setCliente(cliente);
        try {
            SuscripcionFondoOutEntity respuesta = fondoDAO.suscribir(suscripcionFondoInEntity);
        } catch (Exception e) {
            Assert.assertEquals(ImporteMayorAlMaximoException.class, e.getClass());
        }
    }

    @Test
    public void suscribirErrorInesperado() throws IatxException, DAOException {
        String servicio = "CNSSUSFCI_";
        String version = "130";
        String tramaResponse = "200000000000P04HTML0009900010301PRQP31  ********00598587000000072016121510555400000000IBF25976000000000000CNSSUSFCI_1300000            01576531    00        00 000000000201612151055480000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0036890000102õFCIõ03õEl monto ingresado es insuficiente para realizar su operacion. Por favor, reingrese un monto mayor                                                                                                                                                            õIMP.MENOR MINIMõ100- IMPORTE NETO ES MENOR QUE EL LIMITE MINIMO PERMITIDO                       õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        SuscripcionFondoInEntity suscripcionFondoInEntity = new SuscripcionFondoInEntity();
        suscripcionFondoInEntity.setCliente(cliente);
        try {
            SuscripcionFondoOutEntity respuesta = fondoDAO.suscribir(suscripcionFondoInEntity);
        } catch (Exception e) {
            Assert.assertEquals(DAOException.class, e.getClass());
        }
    }
    /**
     * Comprobante suscripcion fondo test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void comprobanteSuscripcionFondoTest() throws IatxException, DAOException {
        String servicio = "SUSFCI____";
        String version = "150";

        String tramaResponse = "200000000000P04HTML0009900010302QLPO92  ********00855425000000072016101812265800000000IBF22790000000000000SUSFCI____1500000            02615492    00        00 000000000201610181226530000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0014400000000õPESOSõ0005912402õ00000000034500õ0000õ00000000000000õACTIVA       õCOBRADA      õ00000000000000õ001õSUPER AHORRO $ CUOTAõ00000000000000õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);

        SuscripcionFondoOutEntity respuesta = fondoDAO.comprobanteSuscripcionFondo(cliente,
                comprobanteSuscFondo);
        Assert.assertNotNull(respuesta);
        Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
    }

    @Test
    public void comprobanteSuscripcionFondoTimeOutException() throws IatxException, DAOException {
        String servicio = "SUSFCI____";
        String version = "150";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenThrow(new IatxException (TIMEOUT_EXCEPTION));
        try {
            SuscripcionFondoOutEntity respuesta = fondoDAO.comprobanteSuscripcionFondo(cliente, comprobanteSuscFondo);
        } catch (Exception e) {
            Assert.assertEquals(TimeOutException.class, e.getClass());
        }
        
    }
    
    
    @Test
    public void comprobanteSuscripcionFondoDAOException() throws IatxException, DAOException {
        String servicio = "SUSFCI____";
        String version = "150";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenThrow(new IatxException ("Otro error"));
        try {
            SuscripcionFondoOutEntity respuesta = fondoDAO.comprobanteSuscripcionFondo(cliente, comprobanteSuscFondo);
        } catch (Exception e) {
            Assert.assertEquals(DAOException.class, e.getClass());
        }
    }

/**
 * Error Fuera de horario : 99000024
 * @throws IatxException
 * @throws DAOException
 */
    @Test
    public void comprobanteSuscripcionFondoErrorCode() throws IatxException, DAOException {
        String servicio = "SUSFCI____";
        String version = "150";
        String tramaError = "200000000000P04HTML0009900010302QLPO92  ********00855425000000072016101812265800000000IBF22790000000000000SUSFCI____1500000            02615492    00        00 000000000201610181226530000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0014499000024õFCIõ01õASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASõ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaError);
        
        try {
            SuscripcionFondoOutEntity respuesta = fondoDAO.comprobanteSuscripcionFondo(cliente, comprobanteSuscFondo);
        } catch (Exception e) {
            Assert.assertEquals(FueraDeHorarioException.class, e.getClass());
        }
    }
    
    
    /**
     * Error Saldo insuficuente : 10000515
     * @throws IatxException
     * @throws DAOException
     */
        @Test
        public void comprobanteSuscripcionFondoSaldoInsificiente() throws IatxException, DAOException {
            String servicio = "SUSFCI____";
            String version = "150";
            String tramaError = "200000000000P04HTML0009900010302QLPO92  ********00855425000000072016101812265800000000IBF22790000000000000SUSFCI____1500000            02615492    00        00 000000000201610181226530000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0014410000515õABGõ01õASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASõ";
            when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaError);
            
            try {
                SuscripcionFondoOutEntity respuesta = fondoDAO.comprobanteSuscripcionFondo(cliente, comprobanteSuscFondo);
            } catch (Exception e) {
                Assert.assertEquals(SaldoInsuficienteException.class, e.getClass());
            }
        }
    
        /**
         * Error Cuenta sin operar : 10000117
         * @throws IatxException
         * @throws DAOException
         */
            @Test
            public void comprobanteSuscripcionFondoCuentaSinOperar() throws IatxException, DAOException {
                String servicio = "SUSFCI____";
                String version = "150";
                String tramaError = "200000000000P04HTML0009900010302QLPO92  ********00855425000000072016101812265800000000IBF22790000000000000SUSFCI____1500000            02615492    00        00 000000000201610181226530000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0014410000117õABGõ01õASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASõ";
                when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaError);
                
                try {
                    SuscripcionFondoOutEntity respuesta = fondoDAO.comprobanteSuscripcionFondo(cliente, comprobanteSuscFondo);
                } catch (Exception e) {
                    Assert.assertEquals(CuentaSinOperarException.class, e.getClass());
                }
            }
    
    
}
