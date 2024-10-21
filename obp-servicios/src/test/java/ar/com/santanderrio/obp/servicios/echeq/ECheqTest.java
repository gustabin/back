package ar.com.santanderrio.obp.servicios.echeq;

import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.web.client.RestTemplate;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.iatx.helpers.IatxSender;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.PropertyMap;
import ar.com.santanderrio.obp.generated.webservices.echeq.ResponseFull;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.estadistica.bo.EstadisticaBO;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.impl.EstadisticaManagerImpl;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.echeq.bo.ECheqBO;
import ar.com.santanderrio.obp.servicios.echeq.bo.impl.ECheqBOImpl;
import ar.com.santanderrio.obp.servicios.echeq.dao.ECheqAmcoDAO;
import ar.com.santanderrio.obp.servicios.echeq.dao.ECheqDAO;
import ar.com.santanderrio.obp.servicios.echeq.dao.impl.ECheqDAOImpl;
import ar.com.santanderrio.obp.servicios.echeq.entities.DetalleECheqInEntity;
import ar.com.santanderrio.obp.servicios.echeq.entities.ECheqEndosadoInEntity;
import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;
import ar.com.santanderrio.obp.servicios.echeq.manager.ECheqManager;
import ar.com.santanderrio.obp.servicios.echeq.manager.impl.ECheqManagerImpl;
import ar.com.santanderrio.obp.servicios.echeq.view.ConfirmarOperacionECheqInView;
import ar.com.santanderrio.obp.servicios.echeq.view.ConfirmarOperacionECheqOutView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.impl.IatxCommImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        IatxBaseDAOTest.IatxBaseDAOTestConfiguration.class })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = { "IATX.TIMEOUT = 90000", "IATX.PROGRAMA = FEINICIO", "IATX.TRANSID = FE04",
        "IATX.LOG = 1", "SERVICIO.PREFIJO.CNSCOTCN=CNSCOTCN__" ,
        "ECHEQ.HORA.DESDE = 00:00", "ECHEQ.HORA.HASTA = 23:00",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.ALTA.05.0001.ARS.TI.S = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.ALTA.05.0001.ARS.CT.S = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.ALTA.05.1001.ARS.TI.S = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.ALTA.05.1001.ARS.CT.S = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.ALTA.05.2001.ARS.TI.S = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.ALTA.05.2001.ARS.CT.S = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.ALTA.05.2003.ARS.TI.S = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.ALTA.05.2003.ARS.CT.S = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.ALTA.07.0001.ARS.TI.S = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.ALTA.07.0001.ARS.CT.S = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.CUSTODIA.05.0001.*.TI = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.CUSTODIA.05.0001.*.CT = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.CUSTODIA.05.1001.*.TI = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.CUSTODIA.05.1001.*.CT = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.CUSTODIA.05.1002.*.TI = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.CUSTODIA.05.1002.*.CT = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.CUSTODIA.05.2001.*.TI = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.CUSTODIA.05.2001.*.CT = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.CUSTODIA.05.2005.*.TI = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.CUSTODIA.05.2005.*.CT = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.CUSTODIA.07.0001.*.TI = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.CUSTODIA.07.0001.*.CT = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.CUSTODIA.02.0001.*.TI = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.CUSTODIA.02.0001.*.CT = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.CUSTODIA.02.1001.*.TI = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.CUSTODIA.02.1001.*.CT = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.CUSTODIA.02.2001.*.TI = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.CUSTODIA.02.2001.*.CT = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.CUSTODIA.99.*.*.TI = 1   ",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.CUSTODIA.99.*.*.CT = 1   ",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.DEPOSITO.05.0001.*.TI = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.DEPOSITO.05.0001.*.CT = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.DEPOSITO.05.1001.*.TI = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.DEPOSITO.05.1001.*.CT = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.DEPOSITO.05.1002.*.TI = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.DEPOSITO.05.1002.*.CT = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.DEPOSITO.05.2001.*.TI = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.DEPOSITO.05.2001.*.CT = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.DEPOSITO.05.2005.*.TI = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.DEPOSITO.05.2005.*.CT = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.DEPOSITO.07.0001.*.TI = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.DEPOSITO.07.0001.*.CT = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.DEPOSITO.02.0001.*.TI = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.DEPOSITO.02.0001.*.CT = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.DEPOSITO.02.1001.*.TI = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.DEPOSITO.02.1001.*.CT = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.DEPOSITO.02.2001.*.TI = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.DEPOSITO.02.2001.*.CT = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.DEPOSITO.99.*.*.TI = 1",
        "ECHEQ.TIPO.CUENTAS.HABILITADAS.DEPOSITO.99.*.*.CT = 1",
        "ECHEQ.CANTIDAD.DIAS.VENCIMIENTO = 30",
        "IATX.DIRLOG = /aplicaciones/hb/logs/http/wasobptbdesa-0", "IATX.GATENAME = mvscpum.ar.bsch",
        "IATX.GATEPORT = 2300", "IATX.CICS = FEINIH2"})
@ActiveProfiles(value = Profiles.TEST)
@Ignore
public class ECheqTest {
    
    private final Logger LOGGER = LoggerFactory.getLogger(ECheqTest.class);

    
    /** The date formatter. */
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
    
    @Mock
    private RestTemplate restTemplate;
    
    /** The ECheq BO. */
    @InjectMocks
    private ECheqManager eCheqManager = new ECheqManagerImpl();

    /** The ECheq BO. */
    @InjectMocks
    @Spy
    private ECheqBO eCheqBO = new ECheqBOImpl();

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The Property Map. */
    @Mock
    private PropertyMap propertyMap;

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    @InjectMocks
    @Spy
    private ECheqDAO eCheqDAO = new ECheqDAOImpl();
    
    @Mock
    private ECheqAmcoDAO eCheqAmcoDAO;

    @Spy
    @InjectMocks
    private IatxComm iatxComm = new IatxCommImpl();
    
    @InjectMocks
    @Spy
    private IatxSender iatxSender = new IatxSenderEcheqMock();
    
    @Spy
    private EstadisticaManager estadisticaManager = new EstadisticaManagerMockImpl();

    /** The estadistica BO. */
    @Mock
    private EstadisticaBO estadisticaBO;

    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;
    
    @Mock
    private CuentaBO cuentaBO;
    
    private Boolean ok = Boolean.TRUE;

    private Boolean throwIatxException = Boolean.FALSE;
    
    
    @Before
    public void init() throws IatxException, BusinessException {
        MockitoAnnotations.initMocks(this);
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(new RegistroSesion());
        Cliente cliente = new Cliente();
        cliente.setNroDocCnsDocXNup("32424");
        cliente.setTipoDocCnsDocXNup("T");
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuenta("09");
        cuenta.setCbu("123");
        cuenta.setMonedaAltair("ARS");
        cuenta.setNroCuentaProducto("000001234567");
        cuenta.setNroSucursal("033");
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
        cliente.getCuentas().add(cuenta);
        Respuesta<ResumenDetalleCuenta> respCuenta = respuestaFactory.crearRespuestaOk(new ResumenDetalleCuenta());
        Mockito.when(cuentaBO.obtenerCuenta(Matchers.any(Cliente.class),Matchers.anyString())).thenReturn(respCuenta);
        
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(2));
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Mockito.anyString())).thenAnswer(new Answer<Mensaje>() {
            public Mensaje answer(InvocationOnMock   invocation) {
                Mensaje msje = new Mensaje();
                msje.setMensaje("MensajeFeedback: {}" + (String)invocation.getArguments()[0]);
                LOGGER.info("MensajeFeedback: {} ", (String)invocation.getArguments()[0]);
                return msje;
            }
        });
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Mockito.anyString(), Mockito.anyString())).thenAnswer(new Answer<Mensaje>() {
            public Mensaje answer(InvocationOnMock   invocation) {
                Mensaje msje = new Mensaje();
                msje.setMensaje("MensajeFeedback: " + (String)invocation.getArguments()[0]);
                LOGGER.info("MensajeFeedback: {} {}", (String)invocation.getArguments()[0], (String)invocation.getArguments()[1]);
                return msje;
            }
        });
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenAnswer(new Answer<Mensaje>() {
            public Mensaje answer(InvocationOnMock   invocation) {
                Mensaje msje = new Mensaje();
                msje.setMensaje("MensajeFeedback: " + (String)invocation.getArguments()[0]);
                LOGGER.info("MensajeFeedback: {} {} {}", (String)invocation.getArguments()[0], 
                        (String)invocation.getArguments()[1], (String)invocation.getArguments()[2]);
                return msje;
            }
        });
        
    }

    @Test
    public void confirmarOperacionOkTest() throws  IatxException, DAOException, IOException, JAXBException {
        
        Mockito.when(sesionParametros.getIdECheqs()).thenReturn(Arrays.asList("123456789012345"));
        for(OperacionEcheqEnum operacion: OperacionEcheqEnum.values()) {
            if (!OperacionEcheqEnum.VER_DETALLE.equals(operacion) && !OperacionEcheqEnum.ALTA.equals(operacion) &&
                    !OperacionEcheqEnum.ENDOSAR.equals(operacion)) {
                Respuesta<ConfirmarOperacionECheqOutView> respuesta = operacionTest(operacion);
                if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
                    Assert.assertTrue(respuesta.getRespuesta().getMensajeFeedback().contains("MensajeFeedback:"));
                }
            }
        }
    }

    private Respuesta<ConfirmarOperacionECheqOutView> operacionTest(OperacionEcheqEnum operacion)
            throws DAOException, IOException, JAXBException {
        ConfirmarOperacionECheqInView confirmarOperacionECheqInView = prepararMocksOperacion(operacion);
        Respuesta<ConfirmarOperacionECheqOutView> respuesta = eCheqManager.confirmar(confirmarOperacionECheqInView);
        Assert.assertNotNull(respuesta);
        return respuesta;
    }
    
    private ConfirmarOperacionECheqInView prepararMocksOperacion(OperacionEcheqEnum operacion) throws DAOException, IOException, JAXBException {
        ConfirmarOperacionECheqInView confirmarOperacionECheqInView = new ConfirmarOperacionECheqInView();
        confirmarOperacionECheqInView.setOperacion(operacion);
        switch (operacion) {
            case ACEPTAR:
            case REPUDIAR:
            case RESCATAR:
            case ENDOSAR:
            case DEPOSITAR:
            case CUSTODIAR:
            case ACEPTAR_ACUERDO_DEVOLUCION:
            case REPUDIAR_ACUERDO_DEVOLUCION:
            case EMITIR_CERTIFICADO:
            case ACEPTAR_PEDIDO_DEVOLUCION:
            case REPUDIAR_PEDIDO_DEVOLUCION: 
                confirmarOperacionECheqInView.setIngresoDesdeEmitidos(Boolean.FALSE);
                confirmarOperacionECheqInView.setIngresoDesdeEndosados(Boolean.FALSE);
                confirmarOperacionECheqInView.setIngresoDesdeRecibidos(Boolean.TRUE);
                confirmarOperacionECheqInView.setCuentaSeleccionada("033-123456/7");
                confirmarOperacionECheqInView.setMotivoRepudio("Motivo Repudio");
                confirmarOperacionECheqInView.setId("123456789012345");
                break; 
            case ANULAR:
            case SOLICITAR_ACUERDO_DEVOLUCION:
            case SOLICITAR_PEDIDO_DEVOLUCION:
                confirmarOperacionECheqInView.setIngresoDesdeEmitidos(Boolean.TRUE);
                confirmarOperacionECheqInView.setIngresoDesdeEndosados(Boolean.FALSE);
                confirmarOperacionECheqInView.setIngresoDesdeRecibidos(Boolean.FALSE);
                confirmarOperacionECheqInView.setId("123456789012345");
                break; 
            default: 
                confirmarOperacionECheqInView.setIngresoDesdeEmitidos(Boolean.FALSE);
                confirmarOperacionECheqInView.setIngresoDesdeEndosados(Boolean.TRUE);
                confirmarOperacionECheqInView.setIngresoDesdeRecibidos(Boolean.FALSE);
                confirmarOperacionECheqInView.setId("123456789012345");
                break;
        }
        List<ResponseFull> responseFullEcheq = obtenerDetalle(operacion);
        Mockito.when(eCheqAmcoDAO.obtenerDetalle(Matchers.any(DetalleECheqInEntity.class))).thenReturn(responseFullEcheq);
        Mockito.when(eCheqAmcoDAO.obtenerChequesEndosados(Matchers.any(ECheqEndosadoInEntity.class))).thenReturn(responseFullEcheq);
        return confirmarOperacionECheqInView;
    }

    private List<ResponseFull> obtenerDetalle(OperacionEcheqEnum operacion) throws IOException, JAXBException {
        String listaCheques = getEcheqsMock(operacion);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ResponseFullMock.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            ResponseFullMock responseFullMock = (ResponseFullMock)unmarshaller.unmarshal(new StringReader(listaCheques));
            ResponseFull responseFull = new ResponseFull();
            BeanUtils.copyProperties(responseFullMock, responseFull);
            List<ResponseFull> responseList = new ArrayList<ResponseFull>();
            responseList.add(responseFull);
            return responseList;
            
        } catch (Exception e) {
        }
        return null;
    }
    
    
    
    private String getEcheqsMock(OperacionEcheqEnum operacion) {
        switch (operacion) {
        case ACEPTAR:
        case REPUDIAR:
            return "<respuesta><code>0</code><message></message><status><bae_CCERegstatus>E</bae_CCERegstatus><bae_Optimeposted></bae_Optimeposted><bae_Optimeexec></bae_Optimeexec><bae_Opid></bae_Opid></status><intcheque_id></intcheque_id><cod_visualizacion></cod_visualizacion><beneficiario_documento_tipo>CUIT</beneficiario_documento_tipo><beneficiario_documento>30123456789</beneficiario_documento><beneficiario_razon_social></beneficiario_razon_social>"
                    + "<cheques><echeq><intcheque_id>123456789012349</intcheque_id><numero_chequera>4</numero_chequera><cheque_numero>00000060</cheque_numero>"
                    + "<estado>EMITIDO-PENDIENTE</estado><emitido_a><beneficiario_documento_tipo>CUIT</beneficiario_documento_tipo><beneficiario_documento>30696394217</beneficiario_documento><beneficiario_nombre>AMEVER</beneficiario_nombre></emitido_a><monto>1.2</monto><fecha_pago>2019-05-08T00:00:00.000</fecha_pago><fecha_emision>2019-05-08T22:55:13.490</fecha_emision><cheque_tipo>CC</cheque_tipo><cheque_caracter>a la orden</cheque_caracter><cheque_modo>1</cheque_modo><cheque_concepto>Varios</cheque_concepto><cheque_motivo_pago>Motivo 1</cheque_motivo_pago><agrupador_id>0</agrupador_id><fecha_ult_modif>2019-05-08T22:55:13.490</fecha_ult_modif><fecha_pago_vencida>false</fecha_pago_vencida><cheque_acordado>false</cheque_acordado><certificado_emitido>false</certificado_emitido><cmc7>07201611060000006000000107428</cmc7><onp>false</onp><cuenta_emisora><banco_codigo>072</banco_codigo><banco_nombre>BANCO SANTANDER RIO S.A.</banco_nombre><sucursal_codigo>0016</sucursal_codigo><sucursal_nombre>PRUEBAS AMCO OBE</sucursal_nombre><sucursal_domicilio>Corrientes 123</sucursal_domicilio><sucursal_cp>1106</sucursal_cp><sucursal_provincia>Buenos Aires</sucursal_provincia><emisor_cuit>30693283724</emisor_cuit><emisor_razon_social>PRUEBA TEST 30693283724</emisor_razon_social><emisor_cbu>0720016820000001074286</emisor_cbu><emisor_cuenta>00000107428</emisor_cuenta><emisor_subcuenta /><emisor_moneda>032</emisor_moneda><emisor_domicilio>Bulnes 2233</emisor_domicilio><emisor_cp>1414</emisor_cp></cuenta_emisora></echeq></cheques></respuesta>";
        case ANULAR:
            return "<respuesta><code>0</code><message></message><status><bae_CCERegstatus>E</bae_CCERegstatus><bae_Optimeposted></bae_Optimeposted><bae_Optimeexec></bae_Optimeexec><bae_Opid></bae_Opid></status><intcheque_id></intcheque_id><cod_visualizacion></cod_visualizacion><beneficiario_documento_tipo>CUIT</beneficiario_documento_tipo><beneficiario_documento>30123456789</beneficiario_documento><beneficiario_razon_social></beneficiario_razon_social>"
                    + "<cheques><echeq><intcheque_id>123456789012349</intcheque_id><numero_chequera>4</numero_chequera><cheque_numero>00000060</cheque_numero>"
                    + "<estado>EMITIDO-PENDIENTE</estado><emitido_a><beneficiario_documento_tipo>CUIT</beneficiario_documento_tipo>"
                    + "<beneficiario_documento>30696394217</beneficiario_documento><beneficiario_nombre>AMEVER</beneficiario_nombre></emitido_a><monto>1.2</monto><fecha_pago>2019-05-08T00:00:00.000</fecha_pago><fecha_emision>2019-05-08T22:55:13.490</fecha_emision><cheque_tipo>CC</cheque_tipo><cheque_caracter>a la orden</cheque_caracter><cheque_modo>1</cheque_modo><cheque_concepto>Varios</cheque_concepto><cheque_motivo_pago>Motivo 1</cheque_motivo_pago><agrupador_id>0</agrupador_id><fecha_ult_modif>2019-05-08T22:55:13.490</fecha_ult_modif><fecha_pago_vencida>false</fecha_pago_vencida><cheque_acordado>false</cheque_acordado><certificado_emitido>false</certificado_emitido><cmc7>07201611060000006000000107428</cmc7><onp>false</onp><cuenta_emisora><banco_codigo>072</banco_codigo><banco_nombre>BANCO SANTANDER RIO S.A.</banco_nombre><sucursal_codigo>0016</sucursal_codigo><sucursal_nombre>PRUEBAS AMCO OBE</sucursal_nombre><sucursal_domicilio>Corrientes 123</sucursal_domicilio><sucursal_cp>1106</sucursal_cp><sucursal_provincia>Buenos Aires</sucursal_provincia><emisor_cuit>30693283724</emisor_cuit><emisor_razon_social>PRUEBA TEST 30693283724</emisor_razon_social><emisor_cbu>0720016820000001074286</emisor_cbu><emisor_cuenta>00000107428</emisor_cuenta><emisor_subcuenta /><emisor_moneda>032</emisor_moneda><emisor_domicilio>Bulnes 2233</emisor_domicilio><emisor_cp>1414</emisor_cp></cuenta_emisora></echeq></cheques></respuesta>";
        case DEPOSITAR:
            return "<respuesta><code>0</code><message></message><status><bae_CCERegstatus>E</bae_CCERegstatus><bae_Optimeposted></bae_Optimeposted><bae_Optimeexec></bae_Optimeexec><bae_Opid></bae_Opid></status><intcheque_id></intcheque_id><cod_visualizacion></cod_visualizacion><beneficiario_documento_tipo>CUIT</beneficiario_documento_tipo><beneficiario_documento>30123456789</beneficiario_documento><beneficiario_razon_social></beneficiario_razon_social>"
                    + "<cheques><echeq><intcheque_id>123456789012348</intcheque_id><numero_chequera>4</numero_chequera><cheque_numero>00000036</cheque_numero>"
                    + "<estado>ACTIVO</estado><emitido_a><beneficiario_documento_tipo>CUIT</beneficiario_documento_tipo><beneficiario_documento>30696394217</beneficiario_documento><beneficiario_nombre>AMEVER</beneficiario_nombre></emitido_a><monto>5000.0</monto>"
                    + "<fecha_pago>"+ dateFormatter.format(new Date())+ "</fecha_pago><fecha_emision>2019-05-30T22:50:30.533</fecha_emision><cheque_tipo>CC</cheque_tipo><cheque_caracter>a la orden</cheque_caracter><cheque_modo>1</cheque_modo><cheque_concepto>Varios</cheque_concepto><cheque_motivo_pago>Motivo 1</cheque_motivo_pago><agrupador_id>0</agrupador_id><cod_visualizacion>M8PDX4OGNYG0MN0L6EY51R</cod_visualizacion><fecha_ult_modif>2019-05-08T23:09:58.373</fecha_ult_modif><fecha_pago_vencida>false</fecha_pago_vencida><cheque_acordado>false</cheque_acordado><certificado_emitido>false</certificado_emitido><cmc7>07201611060000003600000107428</cmc7><onp>false</onp><cuenta_emisora><banco_codigo>072</banco_codigo><banco_nombre>BANCO SANTANDER RIO S.A.</banco_nombre><sucursal_codigo>0016</sucursal_codigo><sucursal_nombre>PRUEBAS AMCO OBE</sucursal_nombre><sucursal_domicilio>Corrientes 123</sucursal_domicilio><sucursal_cp>1106</sucursal_cp><sucursal_provincia>Buenos Aires</sucursal_provincia><emisor_cuit>30693283724</emisor_cuit><emisor_razon_social>PRUEBA TEST 30693283724</emisor_razon_social><emisor_cbu>0720016820000001074286</emisor_cbu><emisor_cuenta>00000107428</emisor_cuenta><emisor_subcuenta /><emisor_moneda>032</emisor_moneda><emisor_domicilio>Bulnes 2233</emisor_domicilio><emisor_cp>1414</emisor_cp></cuenta_emisora></echeq></cheques></respuesta>";
        case CUSTODIAR:
        case ENDOSAR:
            return "<respuesta><code>0</code><message></message><status><bae_CCERegstatus>E</bae_CCERegstatus><bae_Optimeposted></bae_Optimeposted><bae_Optimeexec></bae_Optimeexec><bae_Opid></bae_Opid></status><intcheque_id></intcheque_id><cod_visualizacion></cod_visualizacion><beneficiario_documento_tipo>CUIT</beneficiario_documento_tipo><beneficiario_documento>30123456789</beneficiario_documento><beneficiario_razon_social></beneficiario_razon_social>"
                    + "<cheques><echeq><intcheque_id>123456789012345</intcheque_id><numero_chequera>1</numero_chequera><cheque_numero>83457788</cheque_numero>"
                    + "<estado>ACTIVO</estado><emitido_a><beneficiario_documento_tipo>CUIL</beneficiario_documento_tipo><beneficiario_documento>20000148327</beneficiario_documento><beneficiario_nombre>PRUEBA TEST 20000148327</beneficiario_nombre></emitido_a><monto>8503.0</monto>"
                    + "<fecha_pago>2019-06-30T00:00:00.000</fecha_pago><fecha_emision>2019-05-20T10:15:06.100</fecha_emision><cheque_tipo>CC</cheque_tipo><cheque_caracter>a la orden</cheque_caracter><cheque_modo>0</cheque_modo><cheque_concepto>Varios</cheque_concepto><cheque_motivo_pago>Motivo x</cheque_motivo_pago><agrupador_id>326</agrupador_id><cod_visualizacion>LWZ0KV8794OXJ9PEYDX45M</cod_visualizacion><fecha_ult_modif>2019-05-22T16:28:32.273</fecha_ult_modif><fecha_pago_vencida>false</fecha_pago_vencida><cheque_acordado>false</cheque_acordado><repudio_endoso>false</repudio_endoso><certificado_emitido>false</certificado_emitido><cmc7>07200211068345778800000014833</cmc7><onp>false</onp><cuenta_emisora><banco_codigo>072</banco_codigo><banco_nombre>BANCO SANTANDER RIO S.A.</banco_nombre><sucursal_codigo>8002</sucursal_codigo><sucursal_nombre>PRUEBAS AMCO 1</sucursal_nombre><sucursal_domicilio>Corrientes 123</sucursal_domicilio><sucursal_cp>1106</sucursal_cp><sucursal_provincia>BuenosAires</sucursal_provincia><emisor_cuit>20000148327</emisor_cuit><emisor_razon_social>PRUEBA TEST 20000148327</emisor_razon_social><emisor_cbu>0728002900000000148333</emisor_cbu><emisor_cuenta>00000014833</emisor_cuenta><emisor_subcuenta /><emisor_moneda>032</emisor_moneda><emisor_domicilio>Bulnes 2233</emisor_domicilio><emisor_cp>1414</emisor_cp></cuenta_emisora></echeq></cheques></respuesta>";
        case SOLICITAR_PEDIDO_DEVOLUCION:
            return "<respuesta><code>0</code><message></message><status><bae_CCERegstatus>E</bae_CCERegstatus><bae_Optimeposted></bae_Optimeposted><bae_Optimeexec></bae_Optimeexec><bae_Opid></bae_Opid></status><intcheque_id></intcheque_id><cod_visualizacion></cod_visualizacion><beneficiario_documento_tipo>CUIT</beneficiario_documento_tipo><beneficiario_documento>30123456789</beneficiario_documento><beneficiario_razon_social></beneficiario_razon_social>"
            + "<cheques><echeq><intcheque_id>123456789012345</intcheque_id><numero_chequera>1</numero_chequera><cheque_numero>83457788</cheque_numero>"
            + "<estado>ACTIVO</estado><emitido_a><beneficiario_documento_tipo>CUIL</beneficiario_documento_tipo><beneficiario_documento>20000148327</beneficiario_documento><beneficiario_nombre>PRUEBA TEST 20000148327</beneficiario_nombre></emitido_a>"
            + "<tenencia><beneficiario_documento_tipo>cuit</beneficiario_documento_tipo><beneficiario_documento>30696394217</beneficiario_documento><beneficiario_nombre>AMEVER</beneficiario_nombre></tenencia><monto>8500.0</monto><fecha_pago>2019-05-20T00:00:00.000</fecha_pago><fecha_emision>2019-05-20T10:15:06.100</fecha_emision><cheque_tipo>CC</cheque_tipo><cheque_caracter>a la orden</cheque_caracter><cheque_modo>0</cheque_modo><cheque_concepto>Varios</cheque_concepto><cheque_motivo_pago>Motivo x</cheque_motivo_pago><agrupador_id>326</agrupador_id><cod_visualizacion>LWZ0KV8794OXJ9PEYDX45M</cod_visualizacion><fecha_ult_modif>2019-05-22T16:28:32.273</fecha_ult_modif><fecha_pago_vencida>false</fecha_pago_vencida><cheque_acordado>false</cheque_acordado><repudio_endoso>false</repudio_endoso><certificado_emitido>false</certificado_emitido><cmc7>07200211068345778800000014833</cmc7><onp>false</onp><cuenta_emisora><banco_codigo>072</banco_codigo><banco_nombre>BANCO SANTANDER RIO S.A.</banco_nombre><sucursal_codigo>8002</sucursal_codigo><sucursal_nombre>PRUEBAS AMCO 1</sucursal_nombre><sucursal_domicilio>Corrientes 123</sucursal_domicilio><sucursal_cp>1106</sucursal_cp><sucursal_provincia>BuenosAires</sucursal_provincia><emisor_cuit>20000148327</emisor_cuit><emisor_razon_social>PRUEBA TEST 20000148327</emisor_razon_social><emisor_cbu>0728002900000000148333</emisor_cbu><emisor_cuenta>00000014833</emisor_cuenta><emisor_subcuenta /><emisor_moneda>032</emisor_moneda><emisor_domicilio>Bulnes 2233</emisor_domicilio><emisor_cp>1414</emisor_cp></cuenta_emisora><chq_referencias_pago><referencia>Para</referencia><valor>Juan</valor></chq_referencias_pago><chq_referencias_pago><referencia>Alquiler</referencia><valor>Mayo</valor></chq_referencias_pago><endosos><tipo_endoso>NEG</tipo_endoso><motivo_repudio /><estado_endoso>aceptado</estado_endoso><fecha_hora>2019-05-22T16:15:28.850</fecha_hora><benef_documento_tipo>cuit</benef_documento_tipo><benef_documento>30696394217</benef_documento><benef_razon_social>AMEVER</benef_razon_social><emisor_documento_tipo>cuil</emisor_documento_tipo><emisor_documento>30502793175</emisor_documento><emisor_razon_social>INTERKYT</emisor_razon_social><referencias_pago><referencia>Pago de Alquiler</referencia><valor>14000</valor></referencias_pago></endosos><endosos><tipo_endoso>NOM</tipo_endoso><motivo_repudio /><estado_endoso>aceptado</estado_endoso><fecha_hora>2019-05-22T14:33:12.690</fecha_hora><benef_documento_tipo>cuil</benef_documento_tipo><benef_documento>30502793175</benef_documento><benef_razon_social>INTERKYT</benef_razon_social><emisor_documento_tipo>cuil</emisor_documento_tipo><emisor_documento>20000148327</emisor_documento><emisor_razon_social>PRUEBA TEST 20000148327</emisor_razon_social>"
            + "<referencias_pago><referencia>Pago de Alquiler</referencia><valor>14000</valor></referencias_pago></endosos></echeq></cheques></respuesta>";
        case RESCATAR:
            return "<respuesta><code>0</code><message></message><status><bae_CCERegstatus>E</bae_CCERegstatus><bae_Optimeposted></bae_Optimeposted><bae_Optimeexec></bae_Optimeexec><bae_Opid></bae_Opid></status><intcheque_id></intcheque_id><cod_visualizacion></cod_visualizacion><beneficiario_documento_tipo>CUIT</beneficiario_documento_tipo><beneficiario_documento>30123456789</beneficiario_documento><beneficiario_razon_social></beneficiario_razon_social>"
                    + "<cheques><echeq><intcheque_id>123456789012345</intcheque_id><numero_chequera>1</numero_chequera><cheque_numero>83457788</cheque_numero>"
                    + "<estado>CUSTODIA</estado><emitido_a><beneficiario_documento_tipo>CUIL</beneficiario_documento_tipo><beneficiario_documento>20000148327</beneficiario_documento><beneficiario_nombre>PRUEBA TEST 20000148327</beneficiario_nombre></emitido_a><monto>8503.0</monto>"
                    + "<fecha_pago>2019-06-30T00:00:00.000</fecha_pago><fecha_emision>2019-05-20T10:15:06.100</fecha_emision>"
                    + "<cheque_tipo>CC</cheque_tipo><cbu_custodia>123</cbu_custodia><cheque_caracter>a la orden</cheque_caracter><cheque_modo>0</cheque_modo><cheque_concepto>Varios</cheque_concepto><cheque_motivo_pago>Motivo x</cheque_motivo_pago><agrupador_id>326</agrupador_id><cod_visualizacion>LWZ0KV8794OXJ9PEYDX45M</cod_visualizacion><fecha_ult_modif>2019-05-22T16:28:32.273</fecha_ult_modif><fecha_pago_vencida>false</fecha_pago_vencida><cheque_acordado>false</cheque_acordado><repudio_endoso>false</repudio_endoso><certificado_emitido>false</certificado_emitido><cmc7>07200211068345778800000014833</cmc7><onp>false</onp><cuenta_emisora><banco_codigo>072</banco_codigo><banco_nombre>BANCO SANTANDER RIO S.A.</banco_nombre><sucursal_codigo>8002</sucursal_codigo><sucursal_nombre>PRUEBAS AMCO 1</sucursal_nombre><sucursal_domicilio>Corrientes 123</sucursal_domicilio><sucursal_cp>1106</sucursal_cp><sucursal_provincia>BuenosAires</sucursal_provincia><emisor_cuit>20000148327</emisor_cuit><emisor_razon_social>PRUEBA TEST 20000148327</emisor_razon_social><emisor_cbu>0728002900000000148333</emisor_cbu><emisor_cuenta>00000014833</emisor_cuenta><emisor_subcuenta /><emisor_moneda>032</emisor_moneda><emisor_domicilio>Bulnes 2233</emisor_domicilio><emisor_cp>1414</emisor_cp></cuenta_emisora></echeq></cheques></respuesta>";
        case EMITIR_CERTIFICADO:
        case ACEPTAR_ACUERDO_DEVOLUCION:
        case REPUDIAR_ACUERDO_DEVOLUCION:
        case ANULAR_ACUERDO_DEVOLUCION:
            return "<respuesta><code>0</code><message></message><status><bae_CCERegstatus>E</bae_CCERegstatus><bae_Optimeposted></bae_Optimeposted><bae_Optimeexec></bae_Optimeexec><bae_Opid></bae_Opid></status><intcheque_id></intcheque_id><cod_visualizacion></cod_visualizacion><beneficiario_documento_tipo>CUIT</beneficiario_documento_tipo><beneficiario_documento>30123456789</beneficiario_documento><beneficiario_razon_social></beneficiario_razon_social>"
                    + "<cheques><echeq><intcheque_id>123456789012345</intcheque_id><numero_chequera>1</numero_chequera><cheque_numero>83457788</cheque_numero>"
                    + "<tenencia><beneficiario_documento_tipo>cuit</beneficiario_documento_tipo><beneficiario_documento>30696394217</beneficiario_documento><beneficiario_nombre>AMEVER</beneficiario_nombre></tenencia>"
                    + "<estado>RECHAZADO</estado><emitido_a><beneficiario_documento_tipo>CUIL</beneficiario_documento_tipo><beneficiario_documento>20000148327</beneficiario_documento><beneficiario_nombre>PRUEBA TEST 20000148327</beneficiario_nombre></emitido_a><monto>8503.0</monto><fecha_pago>2019-05-30T00:00:00.000</fecha_pago><fecha_emision>2019-05-20T10:15:06.100</fecha_emision><cheque_tipo>CC</cheque_tipo><cheque_caracter>a la orden</cheque_caracter><cheque_modo>0</cheque_modo><cheque_concepto>Varios</cheque_concepto><cheque_motivo_pago>Motivo x</cheque_motivo_pago><agrupador_id>326</agrupador_id><cod_visualizacion>LWZ0KV8794OXJ9PEYDX45M</cod_visualizacion><fecha_ult_modif>2019-05-22T16:28:32.273</fecha_ult_modif><fecha_pago_vencida>false</fecha_pago_vencida><cheque_acordado>false</cheque_acordado><solicitando_acuerdo>true</solicitando_acuerdo><repudio_endoso>false</repudio_endoso><certificado_emitido>false</certificado_emitido><cmc7>07200211068345778800000014833</cmc7><onp>false</onp><cuenta_emisora><banco_codigo>072</banco_codigo><banco_nombre>BANCO SANTANDER RIO S.A.</banco_nombre><sucursal_codigo>8002</sucursal_codigo><sucursal_nombre>PRUEBAS AMCO 1</sucursal_nombre><sucursal_domicilio>Corrientes 123</sucursal_domicilio><sucursal_cp>1106</sucursal_cp><sucursal_provincia>BuenosAires</sucursal_provincia><emisor_cuit>20000148327</emisor_cuit><emisor_razon_social>PRUEBA TEST 20000148327</emisor_razon_social><emisor_cbu>0728002900000000148333</emisor_cbu><emisor_cuenta>00000014833</emisor_cuenta><emisor_subcuenta /><emisor_moneda>032</emisor_moneda><emisor_domicilio>Bulnes 2233</emisor_domicilio><emisor_cp>1414</emisor_cp></cuenta_emisora></echeq></cheques></respuesta>";
        case SOLICITAR_ACUERDO_DEVOLUCION:
            return "<respuesta><code>0</code><message></message><status><bae_CCERegstatus>E</bae_CCERegstatus><bae_Optimeposted></bae_Optimeposted><bae_Optimeexec></bae_Optimeexec><bae_Opid></bae_Opid></status><intcheque_id></intcheque_id><cod_visualizacion></cod_visualizacion><beneficiario_documento_tipo>CUIT</beneficiario_documento_tipo><beneficiario_documento>30123456789</beneficiario_documento><beneficiario_razon_social></beneficiario_razon_social>"
            + "<cheques><echeq><intcheque_id>123456789012345</intcheque_id><numero_chequera>1</numero_chequera><cheque_numero>83457788</cheque_numero>"
            + "<tenencia><beneficiario_documento_tipo>cuit</beneficiario_documento_tipo><beneficiario_documento>30696394217</beneficiario_documento><beneficiario_nombre>AMEVER</beneficiario_nombre></tenencia>"
            + "<estado>RECHAZADO</estado><emitido_a><beneficiario_documento_tipo>CUIL</beneficiario_documento_tipo><beneficiario_documento>20000148327</beneficiario_documento><beneficiario_nombre>PRUEBA TEST 20000148327</beneficiario_nombre></emitido_a><monto>8503.0</monto><fecha_pago>2019-05-30T00:00:00.000</fecha_pago><fecha_emision>2019-05-20T10:15:06.100</fecha_emision><cheque_tipo>CC</cheque_tipo><cheque_caracter>a la orden</cheque_caracter><cheque_modo>0</cheque_modo><cheque_concepto>Varios</cheque_concepto><cheque_motivo_pago>Motivo x</cheque_motivo_pago><agrupador_id>326</agrupador_id><cod_visualizacion>LWZ0KV8794OXJ9PEYDX45M</cod_visualizacion><fecha_ult_modif>2019-05-22T16:28:32.273</fecha_ult_modif><fecha_pago_vencida>false</fecha_pago_vencida><cheque_acordado>false</cheque_acordado><solicitando_acuerdo>false</solicitando_acuerdo><repudio_endoso>false</repudio_endoso><certificado_emitido>false</certificado_emitido><cmc7>07200211068345778800000014833</cmc7><onp>false</onp><cuenta_emisora><banco_codigo>072</banco_codigo><banco_nombre>BANCO SANTANDER RIO S.A.</banco_nombre><sucursal_codigo>8002</sucursal_codigo><sucursal_nombre>PRUEBAS AMCO 1</sucursal_nombre><sucursal_domicilio>Corrientes 123</sucursal_domicilio><sucursal_cp>1106</sucursal_cp><sucursal_provincia>BuenosAires</sucursal_provincia><emisor_cuit>20000148327</emisor_cuit><emisor_razon_social>PRUEBA TEST 20000148327</emisor_razon_social><emisor_cbu>0728002900000000148333</emisor_cbu><emisor_cuenta>00000014833</emisor_cuenta><emisor_subcuenta /><emisor_moneda>032</emisor_moneda><emisor_domicilio>Bulnes 2233</emisor_domicilio><emisor_cp>1414</emisor_cp></cuenta_emisora></echeq></cheques></respuesta>";
        case ACEPTAR_PEDIDO_DEVOLUCION:
        case REPUDIAR_PEDIDO_DEVOLUCION: 
            return "<respuesta><code>0</code><message></message><status><bae_CCERegstatus>E</bae_CCERegstatus><bae_Optimeposted></bae_Optimeposted><bae_Optimeexec></bae_Optimeexec><bae_Opid></bae_Opid></status><intcheque_id></intcheque_id><cod_visualizacion></cod_visualizacion><beneficiario_documento_tipo>CUIT</beneficiario_documento_tipo><beneficiario_documento>30123456789</beneficiario_documento><beneficiario_razon_social></beneficiario_razon_social>"
            + "<cheques><echeq><intcheque_id>123456789012345</intcheque_id><numero_chequera>1</numero_chequera><cheque_numero>83457788</cheque_numero>"
            + "<estado>DEVOLUCION-PENDIENTE</estado><emitido_a><beneficiario_documento_tipo>CUIL</beneficiario_documento_tipo><beneficiario_documento>20000148327</beneficiario_documento><beneficiario_nombre>PRUEBA TEST 20000148327</beneficiario_nombre></emitido_a><monto>8503.0</monto><fecha_pago>2019-05-30T00:00:00.000</fecha_pago><fecha_emision>2019-05-20T10:15:06.100</fecha_emision><cheque_tipo>CC</cheque_tipo><cheque_caracter>a la orden</cheque_caracter><cheque_modo>0</cheque_modo><cheque_concepto>Varios</cheque_concepto><cheque_motivo_pago>Motivo x</cheque_motivo_pago><agrupador_id>326</agrupador_id><cod_visualizacion>LWZ0KV8794OXJ9PEYDX45M</cod_visualizacion><fecha_ult_modif>2019-05-22T16:28:32.273</fecha_ult_modif><fecha_pago_vencida>false</fecha_pago_vencida><cheque_acordado>false</cheque_acordado><repudio_endoso>false</repudio_endoso><certificado_emitido>false</certificado_emitido><cmc7>07200211068345778800000014833</cmc7><onp>false</onp><cuenta_emisora><banco_codigo>072</banco_codigo><banco_nombre>BANCO SANTANDER RIO S.A.</banco_nombre><sucursal_codigo>8002</sucursal_codigo><sucursal_nombre>PRUEBAS AMCO 1</sucursal_nombre><sucursal_domicilio>Corrientes 123</sucursal_domicilio><sucursal_cp>1106</sucursal_cp><sucursal_provincia>BuenosAires</sucursal_provincia><emisor_cuit>20000148327</emisor_cuit><emisor_razon_social>PRUEBA TEST 20000148327</emisor_razon_social><emisor_cbu>0728002900000000148333</emisor_cbu><emisor_cuenta>00000014833</emisor_cuenta><emisor_subcuenta /><emisor_moneda>032</emisor_moneda><emisor_domicilio>Bulnes 2233</emisor_domicilio><emisor_cp>1414</emisor_cp></cuenta_emisora></echeq></cheques></respuesta>";
        case ANULAR_PEDIDO_DEVOLUCION: 
            return "<respuesta><code>0</code><message></message><status><bae_CCERegstatus>E</bae_CCERegstatus><bae_Optimeposted></bae_Optimeposted><bae_Optimeexec></bae_Optimeexec><bae_Opid></bae_Opid></status><intcheque_id></intcheque_id><cod_visualizacion></cod_visualizacion><beneficiario_documento_tipo>CUIT</beneficiario_documento_tipo><beneficiario_documento>30123456789</beneficiario_documento><beneficiario_razon_social></beneficiario_razon_social>"
            + "<cheques><echeq><intcheque_id>123456789012345</intcheque_id><numero_chequera>1</numero_chequera><cheque_numero>83457788</cheque_numero>"
            + "<tenencia><beneficiario_documento_tipo>cuit</beneficiario_documento_tipo><beneficiario_documento>30696394217</beneficiario_documento><beneficiario_nombre>AMEVER</beneficiario_nombre></tenencia>"
            + "<estado>DEVOLUCION-PENDIENTE</estado><emitido_a><beneficiario_documento_tipo>CUIL</beneficiario_documento_tipo><beneficiario_documento>20000148327</beneficiario_documento><beneficiario_nombre>PRUEBA TEST 20000148327</beneficiario_nombre></emitido_a><monto>8503.0</monto><fecha_pago>2019-05-30T00:00:00.000</fecha_pago><fecha_emision>2019-05-20T10:15:06.100</fecha_emision><cheque_tipo>CC</cheque_tipo><cheque_caracter>a la orden</cheque_caracter><cheque_modo>0</cheque_modo><cheque_concepto>Varios</cheque_concepto><cheque_motivo_pago>Motivo x</cheque_motivo_pago><agrupador_id>326</agrupador_id><cod_visualizacion>LWZ0KV8794OXJ9PEYDX45M</cod_visualizacion><fecha_ult_modif>2019-05-22T16:28:32.273</fecha_ult_modif><fecha_pago_vencida>false</fecha_pago_vencida><cheque_acordado>false</cheque_acordado><repudio_endoso>false</repudio_endoso><certificado_emitido>false</certificado_emitido><cmc7>07200211068345778800000014833</cmc7><onp>false</onp><cuenta_emisora><banco_codigo>072</banco_codigo><banco_nombre>BANCO SANTANDER RIO S.A.</banco_nombre><sucursal_codigo>8002</sucursal_codigo><sucursal_nombre>PRUEBAS AMCO 1</sucursal_nombre><sucursal_domicilio>Corrientes 123</sucursal_domicilio><sucursal_cp>1106</sucursal_cp><sucursal_provincia>BuenosAires</sucursal_provincia><emisor_cuit>20000148327</emisor_cuit><emisor_razon_social>PRUEBA TEST 20000148327</emisor_razon_social><emisor_cbu>0728002900000000148333</emisor_cbu><emisor_cuenta>00000014833</emisor_cuenta><emisor_subcuenta /><emisor_moneda>032</emisor_moneda><emisor_domicilio>Bulnes 2233</emisor_domicilio><emisor_cp>1414</emisor_cp></cuenta_emisora></echeq></cheques></respuesta>";
        default:
            break;
        }
        return null;
    }
    
    
    @Test
    public void confirmarOperacionErrorTest() throws  IatxException, DAOException, IOException, JAXBException {
        Mockito.when(sesionParametros.getIdECheqs()).thenReturn(Collections.<String>emptyList());
        for(OperacionEcheqEnum operacion: OperacionEcheqEnum.values()) {
            if (!OperacionEcheqEnum.VER_DETALLE.equals(operacion) && !OperacionEcheqEnum.ALTA.equals(operacion) &&
                    !OperacionEcheqEnum.ENDOSAR.equals(operacion)) {
                Respuesta<ConfirmarOperacionECheqOutView> respuesta = operacionTest(operacion);
                if (EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())) {
                    Assert.assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                            TipoError.ERROR_GENERICO.getDescripcion());
                }
            }
        }
    }
    
    
    @Test
    public void confirmarOperacionCordinadorErrorTest() throws  IatxException, DAOException, IOException, JAXBException {
        Mockito.when(sesionParametros.getIdECheqs()).thenReturn(Arrays.asList("123456789012345"));
        this.ok = Boolean.FALSE;
        for(OperacionEcheqEnum operacion: OperacionEcheqEnum.values()) {
            if (!OperacionEcheqEnum.VER_DETALLE.equals(operacion) && !OperacionEcheqEnum.ALTA.equals(operacion) &&
                    !OperacionEcheqEnum.ENDOSAR.equals(operacion)) {
                Respuesta<ConfirmarOperacionECheqOutView> respuesta = operacionTest(operacion);
                if (EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())) {
                    Assert.assertTrue(respuesta.getItemsMensajeRespuesta().get(0).getTipoError()
                    .equals(TipoError.ECHEQ_ERROR_CONFIRMAR_OPERACION.getDescripcion()) ||
                    respuesta.getItemsMensajeRespuesta().get(0).getTipoError()
                    .equals(TipoError.ERROR_REINTENTOS_AGOTADOS.getDescripcion()) ||
                    respuesta.getItemsMensajeRespuesta().get(0).getTipoError()
                    .equals(TipoError.ERROR_GENERICO.getDescripcion()));
                }
            }
        }
    }

    
    @Test
    public void confirmarOperacionCordinadorExceptionTest() throws  IatxException, DAOException, IOException, JAXBException {
        Mockito.when(sesionParametros.getIdECheqs()).thenReturn(Arrays.asList("123456789012345"));
        this.throwIatxException = Boolean.TRUE;
        for(OperacionEcheqEnum operacion: OperacionEcheqEnum.values()) {
            if (!OperacionEcheqEnum.VER_DETALLE.equals(operacion) && !OperacionEcheqEnum.ALTA.equals(operacion) &&
                    !OperacionEcheqEnum.ENDOSAR.equals(operacion)) {
                Respuesta<ConfirmarOperacionECheqOutView> respuesta = operacionTest(operacion);
                if (EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())) {
                    Assert.assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),TipoError.ERROR_GENERICO.getDescripcion());
                }
            }
        }
    }

    public class IatxSenderEcheqMock implements IatxSender {
        
        private final Logger LOGGER = LoggerFactory.getLogger(IatxSenderEcheqMock.class);

        
        @Override
        public String send(IatxRequest iatxRequest) throws IatxException {

            LOGGER.info("{}, {}", iatxRequest.getNombreServicio(), iatxRequest.getVersionServicio());
            IatxRequestData iatxRequestData = iatxRequest.getData();
            iatxRequestData.setNombreServicio(iatxRequest.getNombreServicio());
            iatxRequestData.setVersionServicio(iatxRequest.getVersionServicio());
            String rqd = iatxRequestData.getData();
            LOGGER.info("IATREQ<{}>", rqd);
            if (throwIatxException) {
                throw new IatxException();
            }
            if (ok) {
                return  "200000000000P04HTML00099000104USRDOBP   ********00115686000000002019051614055800000000IBF00KOL000000000000SEGINFORM_1300000                        00        0  014028392201905161405270000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0005100000000õ02ITKA69õ@DS6MIF0õ02890069õ19300403õIõ õAõ";
            }
            return  "200000000000P04HTML00099000104USR" ;
        }    
    }

    public class EstadisticaManagerMockImpl extends EstadisticaManagerImpl implements EstadisticaManager {
        
        @Override
        public boolean add(String codigoTransaccion, String resultado) {
            LOGGER.info("Generando estadistica...- Codigo Transaccion: {}  Resultado: {} .-", codigoTransaccion, resultado) ;
            return true;
        }
    }
    
    @XmlRootElement(name = "respuesta")
    @XmlType(namespace="http://echeq.amco.com.ar/")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class ResponseFullMock extends ResponseFull {
        
    }
}
